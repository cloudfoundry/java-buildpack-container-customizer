# Cloud Foundry Spring Boot Tomcat Customizer
This project provides a [Spring Boot `EmbeddedServletContainerCustomizer`][m] that configures Tomcat to read from symbolic links.

## Development
The project depends on Java 8.  To build from source, run the following:

```shell
$ ./mvnw clean package
```
## Contributing
[Pull requests][u] and [Issues][i] are welcome.

## License
This project is released under version 2.0 of the [Apache License][l].

[i]: https://github.com/cloudfoundry/cf-java-client/issues
[l]: https://www.apache.org/licenses/LICENSE-2.0
[m]: http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metric-writers
[u]: https://help.github.com/articles/using-pull-requests
