# JSON converter

## Purpose: 

Get familiar with basic Kotlin syntax (types, conditions, functions). You will have experience with DSL, infix functions, sealed classes. 

## Description: 

Create DSL for building json that meets the following requirements: 
1. DSL should support primitive Kotlin types (both nullable and non-nullable) 
2. DSL should support objects 
3. DSL should support arrays 
4. Json primitives should be used as is 
5. Custom types must be converted to json as plain strings 
* Add pretty printing for json 

Example: 

```
val user = obj {
    "firstName" by "John" 
    "lastName" by "Smith" 
    "age" by 20 
    "email" by null 
    "interests" by arr["kotlin", "programming", "computer science"] 
    "metadata" by obj { 
        "token" by UUID.randomUUID()// 075a68b5-05f0-4e90-84d9-e418ab68fe3a 
        "expires" by LocalDateTime.now().plusDays(7)// 2021-02-21T19:28:50.307 
        "role" by "admin" 
    } 
}
```

## Task: 

Create a program that meets the requirements from the description. What do you need to do: 

- Read java doc (you can find it inside of classes). Java doc includes some additional information. 
- Check codebase and solve all compilation errors 
- Instead “TODO()” fill functions body by your own Kotlin code that will complete the program and will pass unit tests 
