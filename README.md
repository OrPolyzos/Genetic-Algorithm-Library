# Genetic Algorithm Library
Library that contains base functionality to implement a Genetic Algorithm tailored to the needs of any project.

## Technical Instructions
* Clone the **genetic-algorithm-library** project (this one) and run mvn clean install from a terminal in the root directory (where pom.xml is located). This will install the forementioned project in your local .m2 to be used/imported by any projects that will be needing it.
* Add the following dependency to the `<dependencies>` section of your project's `pom.xml`
```xml
    <dependency>
        <groupId>com.algorithms.ai</groupId>
        <artifactId>genetic-algorithm</artifactId>
        <version>1.0.0</version>
    </dependency>
```

Sample projects/implementations
* <em>Infinite Monkey Theorem</em> https://github.com/OrPolyzos/ga-infinite-monkey-theorem
* <em>Convex Hull Ptoblem</em> https://github.com/OrPolyzos/ga-convex-hull
