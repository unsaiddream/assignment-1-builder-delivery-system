package delivery;

public class DeliveryOrder {

    private String orderId;
    private String sender;
    private String recipient;
    private Address destination;
    private double weightKg;
    private DeliveryType deliveryType;
    private String courier;
    private int maxDeliveryTimeMinutes;
    private boolean trackingEnabled;
    private boolean fragile;
    private boolean insuranceEnabled;
    private int priority;

    public DeliveryOrder(
            String orderId,
            String sender,
            String recipient,
            Address destination,
            double weightKg,
            DeliveryType deliveryType,
            String courier,
            int maxDeliveryTimeMinutes,
            boolean trackingEnabled,
            boolean fragile,
            boolean insuranceEnabled,
            int priority
    ) {
        this.orderId = orderId;
        this.sender = sender;
        this.recipient = recipient;
        this.destination = destination;
        this.weightKg = weightKg;
        this.deliveryType = deliveryType;
        this.courier = courier;
        this.maxDeliveryTimeMinutes = maxDeliveryTimeMinutes;
        this.trackingEnabled = trackingEnabled;
        this.fragile = fragile;
        this.insuranceEnabled = insuranceEnabled;
        this.priority = priority;
    }
}