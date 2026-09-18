package delivery;

public class DeliveryDirector {

    public DeliveryOrder createStandardDelivery(
            String orderId,
            String sender,
            String recipient,
            Address destination
    ) {
        return new DeliveryOrderBuilder(
                orderId,
                sender,
                recipient,
                destination
        )
                .withWeight(2.0)
                .maxDeliveryTimeMinutes(1440)
                .enableTracking()
                .withPriority(1)
                .build();
    }

    public DeliveryOrder createExpressDelivery(
            String orderId,
            String sender,
            String recipient,
            Address destination,
            String courier
    ) {
        return new DeliveryOrderBuilder(
                orderId,
                sender,
                recipient,
                destination
        )
                .withWeight(2.0)
                .expressDelivery()
                .assignCourier(courier)
                .maxDeliveryTimeMinutes(120)
                .enableTracking()
                .withPriority(5)
                .build();
    }

    public DeliveryOrder createFragileDelivery(
            String orderId,
            String sender,
            String recipient,
            Address destination
    ) {
        return new DeliveryOrderBuilder(
                orderId,
                sender,
                recipient,
                destination
        )
                .withWeight(1.0)
                .markAsFragile()
                .enableInsurance()
                .enableTracking()
                .withPriority(3)
                .build();
    }
}