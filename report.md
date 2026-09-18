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

