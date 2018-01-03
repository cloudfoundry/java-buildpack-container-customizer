# Cloud Foundry Spring Boot Tomcat Customizer

| Job | Status
| --- | ------
| `unit-test` | [![unit-test-master](https://java-experience.ci.springapps.io/api/v1/teams/java-experience/pipelines/container-customizer/jobs/unit-test/badge)](https://java-experience.ci.springapps.io/teams/java-experience/pipelines/container-customizer/jobs/unit-test)
| `unit-test-release` | [![unit-test-master](https://java-experience.ci.springapps.io/api/v1/teams/java-experience/pipelines/container-customizer/jobs/unit-test-release/badge)](https://java-experience.ci.springapps.io/teams/java-experience/pipelines/container-customizer/jobs/unit-test-release)
| `deploy` | [![deploy-master](https://java-experience.ci.springapps.io/api/v1/teams/java-experience/pipelines/container-customizer/jobs/deploy/badge)](https://java-experience.ci.springapps.io/teams/java-experience/pipelines/container-customizer/jobs/deploy)
| `deploy-release` | [![deploy-master](https://java-experience.ci.springapps.io/api/v1/teams/java-experience/pipelines/container-customizer/jobs/deploy-release/badge)](https://java-experience.ci.springapps.io/teams/java-experience/pipelines/container-customizer/jobs/deploy-release)

This project provides a [Spring Boot `EmbeddedServletContainerCustomizer`][m] that configures Tomcat to read from symbolic links.

## Development
The project depends on Java 7.  To build from source, run the following:

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
