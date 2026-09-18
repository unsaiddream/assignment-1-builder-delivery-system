package delivery;

public class Main {
    public static void main(String[] args) {

        Address dest = new Address("Astana", "Uly Dala", 29);

        DeliveryDirector director = new DeliveryDirector();

        DeliveryOrder standart = director.createStandardDelivery(
                "ord-001",
                "YandexEda",
                "Zhumabay",
                dest
        );

        DeliveryOrder express = director.createExpressDelivery("ord-002", "Indrive", "Sanzhar", dest, "Courier-33");

        DeliveryOrder fragile = director.createFragileDelivery("ord-003", "YandexMarket", "Ansar", dest);



        System.out.println("standart delivery created");
        System.out.println("express delivery created \uD83C\uDF4C");
        System.out.println("fragile delivery created");

    }
}