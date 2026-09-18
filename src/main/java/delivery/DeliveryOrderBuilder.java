package delivery;

public class DeliveryOrderBuilder {
    private final String orderId;
    private final String sender;
    private final String recipient;
    private final Address destination;

    private double weightKg = 1.0;
    private DeliveryType deliveryType = DeliveryType.STANDARD;
    private String courier;
    private int maxDeliveryTimeMinutes = 1440;
    private boolean trackingEnabled = false;
    private boolean fragile = false;
    private boolean insuranceEnabled = false;
    private int priority = 1;

    public DeliveryOrderBuilder(
            String orderId,
            String sender,
            String recipient,
            Address destination
    ) {
        this.orderId = orderId;
        this.sender = sender;
        this.recipient = recipient;
        this.destination = destination;
    }

    public DeliveryOrderBuilder withWeight(double weightKg) {
        this.weightKg = weightKg;
        return this;
    }

    public DeliveryOrderBuilder expressDelivery() {
        this.deliveryType = DeliveryType.EXPRESS;
        return this;
    }

    public DeliveryOrderBuilder assignCourier(String courier) {
        this.courier = courier;
        return this;
    }

    public DeliveryOrderBuilder maxDeliveryTimeMinutes(int minutes) {
        this.maxDeliveryTimeMinutes = minutes;
        return this;
    }

    public DeliveryOrderBuilder enableTracking() {
        this.trackingEnabled = true;
        return this;
    }

    public DeliveryOrderBuilder markAsFragile() {
        this.fragile = true;
        return this;
    }

    public DeliveryOrderBuilder enableInsurance() {
        this.insuranceEnabled = true;
        return this;
    }

    public DeliveryOrderBuilder withPriority(int priority) {
        this.priority = priority;
        return this;
    }

    private void validateWeight(){
        if(weightKg <= 0){
            throw new IllegalArgumentException(
                    "weight must be greater than 0"
            );
        }
    }

    private void validateDeliveryTime(){
        if (maxDeliveryTimeMinutes <= 0){
            throw new IllegalArgumentException(
                    "delivery time must be greater than 0"
            );
        }
    }

    private void validatePriority() {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException(
                    "priority must be between 1 and 5"
            );
        }
    }

    private void validateExpressDelivery(){
        if (deliveryType == DeliveryType.EXPRESS){
            if (courier == null || courier.isBlank()){
                throw new IllegalArgumentException(
                        "express delivery require an courier"
                );
            }

            if (maxDeliveryTimeMinutes > 120) {
                throw new IllegalArgumentException(
                        "express delivery must be complected within 120 m"
                );
            }
        }
    }

    private void validateFragileDelivery() {
        if (fragile && !insuranceEnabled) {
            throw new IllegalArgumentException(
                    "fragile delivery requires insurance"
            );
        }
    }

    private void validate() {
        validateWeight();
        validateDeliveryTime();
        validatePriority();
        validateExpressDelivery();
        validateFragileDelivery();
    }

    public DeliveryOrder build() {

        validate();

        return new DeliveryOrder(
                orderId,
                sender,
                recipient,
                destination,
                weightKg,
                deliveryType,
                courier,
                maxDeliveryTimeMinutes,
                trackingEnabled,
                fragile,
                insuranceEnabled,
                priority
        );
    }
}