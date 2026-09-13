package delivery;

public class Main {
    public static void main(String[] args) {

        Address dest = new Address("Astana", "Uly Dala", 29);

        DeliveryOrder order =
                new DeliveryOrderBuilder(
                        "order-001",
                        "YandexEda",
                        "Sanzhar",
                        dest
                )
                        .withWeight(1.2)
                        .expressDelivery()
                        .assignCourier("Serzhan")
                        .maxDeliveryTimeMinutes(90)
                        .enableTracking()
                        .enableInsurance()
                        .withPriority(1)
                        .build();

        System.out.println("delivery order created");
    }
}