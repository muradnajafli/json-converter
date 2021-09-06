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

Example: 

```
val student = obj {
        "name" relatedToValue "Alex"
        "age" relatedToValue 19
        "email" relatedToValue null
        "skills" relatedToValue obj {
            "hard" relatedToValue arr["Kotlin", "Android"]
            "soft" relatedToValue arr[listOf("Customer communication", "Team player")]
        }
        "token" relatedToValue UUID.randomUUID()
        "role" relatedToValue "user"
    }
```

## Task: 

Create a program that meets the requirements from the description. What do you need to do: 

- Read java doc (you can find it inside classes). Java doc includes some additional information.
- Check codebase and solve all compilation errors.
- Change “TODO()” and fill the functions body with your own Kotlin code that will complete the program and will pass unit tests.
