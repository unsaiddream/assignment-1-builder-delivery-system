package delivery;

import delivery.DeliveryOrder.DeliveryOrderBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryOrderTest {

    private Address destination() {
        return new Address("Astana", "Uly Dala", 29);
    }

    private DeliveryOrderBuilder validBuilder() {
        return new DeliveryOrderBuilder(
                "ord-001",
                "YandexMarket",
                "Sanzhar",
                destination()
        );
    }

    @Test
    void shouldCreateStandardPreset() {
        DeliveryOrder order = new DeliveryDirector()
                .createStandardDelivery(
                        "ord-001", "YandexMarket", "Sanzhar",
                        destination()
                );

        assertEquals(DeliveryType.STANDARD, order.getDeliveryType());
        assertTrue(order.isTrackingEnabled());
    }

    @Test
    void shouldCreateExpressPreset() {
        DeliveryOrder order = new DeliveryDirector()
                .createExpressDelivery(
                        "ord-002", "Indrive", "Sanzhar",
                        destination(), "Courier-33"
                );

        assertEquals(DeliveryType.EXPRESS, order.getDeliveryType());
        assertEquals("Courier-33", order.getCourier());
        assertEquals(120, order.getMaxDeliveryTimeMinutes());
    }

    @Test
    void shouldCreateFragilePreset() {
        DeliveryOrder order = new DeliveryDirector()
                .createFragileDelivery(
                        "ord-003", "YandexMarket", "Sanzhar",
                        destination()
                );

        assertTrue(order.isFragile());
        assertTrue(order.isInsuranceEnabled());
    }

    @Test
    void shouldRejectNegativeWeight() {
        assertThrows(
                IllegalArgumentException.class,
                () -> validBuilder().withWeight(-1).build()
        );
    }

    @Test
    void shouldRejectBlankOrderId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new DeliveryOrderBuilder(
                        "   ", "YandexMarket", "Sanzhar",
                        destination()
                ).build()
        );
    }

    @Test
    void shouldRejectFragileDeliveryWithoutInsurance() {
        assertThrows(
                IllegalArgumentException.class,
                () -> validBuilder().markAsFragile().build()
        );
    }

    @Test
    void shouldAcceptMinimumPriority() {
        DeliveryOrder order = validBuilder()
                .withPriority(1)
                .build();

        assertEquals(1, order.getPriority());
    }

    @Test
    void shouldAcceptMaximumPriority() {
        DeliveryOrder order = validBuilder()
                .withPriority(5)
                .build();

        assertEquals(5, order.getPriority());
    }

    @Test
    void shouldEnforceExpressConstraint() {
        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> validBuilder()
                                .expressDelivery()
                                .withDeliveryTimeLimitMinutes(120)
                                .build()
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> validBuilder()
                                .expressDelivery()
                                .assignCourier("Courier-33")
                                .withDeliveryTimeLimitMinutes(121)
                                .build()
                )
        );
    }

    @Test
    void shouldKeepFirstOrderUnchangedWhenBuilderIsReused() {
        DeliveryOrderBuilder builder = validBuilder();

        DeliveryOrder first = builder.withWeight(2.0).build();
        DeliveryOrder second = builder.withWeight(5.0).build();

        assertNotSame(first, second);
        assertEquals(2.0, first.getWeightKg(), 0.000001);
        assertEquals(5.0, second.getWeightKg(), 0.000001);
    }
}