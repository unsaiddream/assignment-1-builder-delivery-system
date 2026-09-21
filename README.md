# Delivery System — Builder Pattern

Software Design Patterns — Assignment 1  
Author: Sanzhar Karaulov

## Overview

This Java project demonstrates the Builder design pattern using a delivery system.

A delivery order contains required information and optional settings such as weight, delivery mode, courier, tracking, insurance, and priority.

The Builder provides a fluent API, validates the configuration, and creates a `DeliveryOrder`.

## Individual Variant

**Domain:** Delivery System

**Constraint:** EXPRESS delivery requires an assigned courier and a maximum delivery time of no more than 120 minutes.

**Required preset:** EXPRESS

## Technologies

- Java 17+
- JUnit 5
- IntelliJ IDEA

## Project Structure

```text
assignment-1-builder-delivery-system/
├── src/
│   └── main/
│       └── java/
│           └── delivery/
│               ├── Address.java
│               ├── DeliveryType.java
│               ├── DeliveryOrder.java
│               ├── DeliveryDirector.java
│               └── Main.java
├── test/
│   └── delivery/
│       └── DeliveryOrderTest.java
├── docs/
│   └── builder-uml.png
├── README.md
└── report.md
```

`DeliveryOrderBuilder` is a public static nested class inside `DeliveryOrder`.

## Builder Participants

| Role | Class | Responsibility |
|---|---|---|
| Product | `DeliveryOrder` | Stores the completed delivery configuration |
| Builder | `DeliveryOrder.DeliveryOrderBuilder` | Configures, validates, and creates orders |
| Director | `DeliveryDirector` | Defines reusable delivery presets |
| Client | `Main` | Demonstrates preset creation |
| Supporting object | `Address` | Stores destination details |
| Supporting enum | `DeliveryType` | Defines STANDARD and EXPRESS delivery modes |

## Presets

- **STANDARD:** Standard delivery with tracking.
- **EXPRESS:** Express delivery with an assigned courier, tracking, and a 120-minute delivery limit.
- **FRAGILE:** Delivery with fragile handling, insurance, and tracking.

Fragility is a separate property, so a fragile order can also use EXPRESS delivery.

## Validation

Before creating an order, the Builder checks that:

- Order ID, sender, and recipient are not blank.
- Destination is not null.
- Weight and delivery time are greater than zero.
- Priority is between 1 and 5.
- EXPRESS delivery has an assigned courier and a delivery time limit of at most 120 minutes.
- Fragile delivery includes insurance.

The Product constructor is private, so external code creates orders through the Builder.

## How to Run

1. Open the project in IntelliJ IDEA.
2. Configure JDK 17 or later as the Project SDK.
3. Ensure `src/main/java` is marked as Sources Root.
4. Open `Main.java`.
5. Run `Main.main()` using the green Run button.

The program creates STANDARD, EXPRESS, and FRAGILE orders and prints confirmation messages. Exactly one successful configuration message includes 🍌.

## How to Run Tests

1. Ensure JUnit 5 is available in the project dependencies.
2. Mark `test` as Test Sources Root if necessary.
3. Open `DeliveryOrderTest.java`.
4. Run the class using the green Run button.

The test suite covers:

- Three valid preset configurations.
- Three invalid configurations.
- Minimum and maximum priority values.
- The EXPRESS delivery constraint.
- Builder reuse and independence of previously created orders.

## Documentation

- [Assignment report](report.md)
- [UML class diagram](docs/builder-uml.png)