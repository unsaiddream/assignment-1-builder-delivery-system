package delivery;
import delivery.DeliveryOrder.DeliveryOrderBuilder;

public class DeliveryDirector {

    private DeliveryOrderBuilder trackedDelivery(
            String orderId,
            String sender,
            String recipient,
            Address destination
    ) {
        return new DeliveryOrderBuilder(
                orderId, sender, recipient, destination
        ).enableTracking();
    }

    public DeliveryOrder createStandardDelivery(
            String orderId,
            String sender,
            String recipient,
            Address destination
    ) {
        return trackedDelivery(orderId, sender, recipient, destination)
                .withWeight(2.0)
                .withDeliveryTimeLimitMinutes(1440)
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
        return trackedDelivery(orderId, sender, recipient, destination)
                .withWeight(2.0)
                .expressDelivery()
                .assignCourier(courier)
                .withDeliveryTimeLimitMinutes(120)
                .withPriority(5)
                .build();
    }

    public DeliveryOrder createFragileDelivery(
            String orderId,
            String sender,
            String recipient,
            Address destination
    ) {
        return trackedDelivery(orderId, sender, recipient, destination)
                .withWeight(1.0)
                .markAsFragile()
                .enableInsurance()
                .withPriority(3)
                .build();
    }
}