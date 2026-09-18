## Part A - add initial constructor based delivery order implementation

In a part i created the initital version of delivery sys without using builder pattern, the DeliveryOrder object is created using a conventional constructor with many parametrs.
So this version is working, but it is difficult to read cuz code contains many values without clear names

## Part B - refactor DeliveryOrder with Builder pattern

After indentifying the problems of the constructor based approach, i refactored DeliveryOrder creation process using the builder design patterns

## Part c - added validation rules

So in this part i added three single-fields validation for weight, deliverytime, priority and two cross-field validation for expressdelivery and fagile orders

## Part d - added delivery preset configurations

i implimented three predefined delivery configurations:

'Standard' - preset represents a regular delivery with normal priority and
tracking enabled

'Express' - preset represents a fast delivery with maximum time delivery 120 minutes

'Fragile' - preset represents a fragile package to satisfy the validation rule that
fragile deliveries must be insured

those presets were created using DeliveryDirector class, without director the same Builder sequences would have to be repeated in multiple places 

## Part e - clean code refractoring

I wrote clean code from the begging, so i did not change that much 

BEFORE:

```
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
```
AFTER:

```
private void validateExpressDelivery() {
    if (deliveryType != DeliveryType.EXPRESS) {
        return;
    }

    if (courier == null || courier.isBlank()) {
        throw new IllegalArgumentException(
                "Express delivery requires an assigned courier"
        );
    }

    if (maxDeliveryTimeMinutes > 120) {
        throw new IllegalArgumentException(
                "Express delivery must be completed within 120 minutes"
        );
    }
}
```
1) Early return and exit of code removes the extra level of nesting
2) Principe Blocks and Indenting, Small Functions
3) Cuz for regular delivery, the method ends immediately, and the EXPRESS rules are read sequentially


BEFORE:
```
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
```

AFTER common method:

```
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
```

STANDARD, FRAGILE, EXPRESS:

```
return trackedDelivery(orderId, sender, recipient, destination)
        .withWeight(2.0)
        .maxDeliveryTimeMinutes(1440)
        .withPriority(1)
        .build();
```
1) Too much repeatative code 
2) Principe Don’t Repeat Yourself, Descriptive Names
3) More comfortable and clean to write code without writing same code multiple times 

BEFORE:

```
public DeliveryOrderBuilder maxDeliveryTimeMinutes(int minutes) {
    this.maxDeliveryTimeMinutes = minutes;
    return this;
}
```

AFTER:

```
public DeliveryOrderBuilder withDeliveryTimeLimitMinutes(int minutes) {
    this.maxDeliveryTimeMinutes = minutes;
    return this;
}
```
1) Its not cruel but i should use more descriptive and noticeble name for time limit
2) Principe Use Descriptive Name
3) Cuz its more noticebale and descriptive which is follow Descriptive Name principle

Also added validation for required field, cuz up to this point validation did not check no string values and etc
Secondly i refactored DeliveryOrderBuilder, paste into DeliveryOrder class to prevent creation of order without Builder
And removed fragile as type in Delivery Type for those caase when delivery is fragile and express at the same time

## Part F

### Decision
Validation in DeliveryOrderBuilder, build() method calls validate() before creating a DeliveryOrder

DeliveryOrderBuilder is a static class inside DeliveryOrder class, the product constructor is private so external client code must use builder before creating any order

### Alternative
Other solution i think its perform validation inside the DeliveryOrder constructor, this would make the prosuct responsible for enforcing its own validity  

### Reasoning
i chose builder validation cuz the builder already stores all configurations and parameters values before construction 

so the private product consturctor prevents external code bypass the builder 

## Part G 

| Builder Role | Your Class | Responsibility                                                                     |
|---|---|------------------------------------------------------------------------------------|
| Product | `DeliveryOrder` | stores the completed delivery configuration, has a private constructor.            |
| Builder | `DeliveryOrder.DeliveryOrderBuilder` | configures optional properties, validates the configuration, and creates the Order |
| Client | `Main` | requests preset orders from the Director and prints creation messages              |
| Director | `DeliveryDirector` | defines standard, express, fragile presets using the Builder.                      |
| Supporting object | `Address` | stores the destination city, street, and house number                              |
| Supporting enum | `DeliveryType` | represents the delivery types standard and express                                 |
`Main` uses `DeliveryDirector` to create preset orders and creates an `Address`

`DeliveryDirector` configures `DeliveryOrderBuilder` and returns completed orders

`DeliveryOrderBuilder` is public static class inside `DeliveryOrder`

`DeliveryOrderBuilder` creates `DeliveryOrder` after validation

Builder and Product reference an `Address` and use `DeliveryType`.