# Lazy value calculation for Java
![Maven Central](https://img.shields.io/badge/Maven%20Central-0.0.1-blue?style=flat)
![License](https://img.shields.io/badge/License-MIT%20License-green?style=flat)
![Status](https://img.shields.io/badge/Status-Active-lime?style=flat)

The library that provides tools for lazy value calculation in Java, supports caching and thread-safe lazy calculations. 
The minimum supported version of JDK is 1.8.

## Prerequisites
While working on a few libraries for my pet projects, I needed to develop additional supporting libs - including a library 
that would allow me to work with lazy calculations in Java.

At the moment, the library contains classes and methods that allow you to use lazy calculations both without thread safety
support and taking into account work in a multithreaded environment.

Separately, it is worth noting that the implementation of `SafeLazy` simply makes the methods described in the `Lazy` 
interface synchronized. 
This is due to the fact that at the same time, an additional request for caching manipulation can be performed.

## Usage

> **Note**
> 
> This library provides the `Lazy` interfaces and the `AbstractLazy` class for those who want to make their own implementation. 
> For those who have enough library functionality, ready-made implementations of `Unsafe` and `SafeLazy` are provided, 
> which can be created, including through factory methods of the `Lazy` interface.

##### Example 1. Lazy non-thread-safe random integer value calculation with caching.

```java
import com.sloppydobby.java.lazy.Lazy;

import java.util.Random;

public class MyClass {

    private final Random random = new Random();

    private final Lazy<Integer> lazy = Lazy.unsafe(random::nextInt);

    public void myMethod() {
        // Get value (for example, Random::nextInt returns 5)
        final int a = lazy.get(); // 5
        
        // a == b because 1st generated value is cached
        final int b = lazy.get(); // 5
        
        lazy.clearCache();
        final int c = lazy.get(); // new random int because cache was cleared
    }

}
```

##### Example 2. Lazy thread-safe random integer value calculation with caching

```java
import com.sloppydobby.java.lazy.Lazy;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClass {

    private final Random random = new Random();

    private final Lazy<Integer> lazy = Lazy.safe(random::nextInt);

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public void myMethod() {
        final Runnable runnable = () -> {
            // Lazy cached 1st generated value and 2nd thread waits for unlock Lazy
            final int ignore = lazy.get();
        };
        
        executorService.submit(runnable);
        executorService.submit(runnable);
    }

}
```

##### Example 3. Lazy non-thread-safe random integer value calculation without caching

```java
import com.sloppydobby.java.lazy.Lazy;

import java.util.Random;

public class MyClass {

    private final Random random = new Random();

    private final Lazy<Integer> lazy = Lazy.unsafe(random::nextInt, false);

    public void myMethod() {
        final int a = lazy.get(); // new int
        final int b = lazy.get(); // new int
    }

}
```

## Maven
If you are using the Maven build system, then you can add the library to your project by adding dependency in to target module `pom.xml` file:

```xml
<dependency>
    <groupId>com.sloppydobby</groupId>
    <artifactId>java-lazy</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Gradle
If you are using the Gradle build system, then you can add the library to your project by adding dependency in to target module `build.gradle` file:

```groovy
implementation 'com.sloppydobby:java-lazy:0.0.1'
```

## Build
If for some reason you can't add the library to your project as Maven Central dependency, you can build the library manually by following commamd:

```bash
mvn clean package
```
