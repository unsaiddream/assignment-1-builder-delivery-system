package delivery;

public class Main {
    public static void main(String[] args) {

        Address dest = new Address("Astana", "Uly Dala", 29);

        DeliveryOrder order = new DeliveryOrder(
                "ORD-001",
                "Amazon",
                "Alex",
                dest,
                5.5,
                DeliveryType.EXPRESS,
                "Courier-17",
                90,
                true,
                false,
                true,
                1
        );

        System.out.println("Delivery order created");
    }
}