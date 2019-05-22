### Supermarket pricing solution

This is a simple supermarket checkout that calculates the total price of a number of items in shopping basket. Applying discounts if any available.

Some items available are based on price per item, some items are based on weight. 

Following discounts considered:
* Three cans of beans for the price of two.
* Two cans of Coke, for £1.


### Building

The project uses [Gradle](https://gradle.org) as a build tool but you don't have to install it locally since there is a
`./gradlew` wrapper script.

To build project please execute the following command:

```bash
    ./gradlew build
```

The project also uses [Maven](https://maven.apache.org/) as a build tool.

To build project using maven please execute the following command:

```bash
    mvn clean package
```