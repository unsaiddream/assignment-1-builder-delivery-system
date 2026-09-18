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

Principe Blocks and Indenting, Small Functions

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

Principe Don’t Repeat Yourself, Descriptive Names

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
Principe Use Descriptive Name

Also added validation for required field, cuz up to this point validation did not check no string values and etc
Secondly i refactored DeliveryOrderBuilder, paste into DeliveryOrder class to prevent creation of order without Builder
And removed fragile as type in Delivery Type for those caase when delivery is fragile and express at the same time