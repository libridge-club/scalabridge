# libridge - a free/libre, open-source bridge server

## About the project

libridge is a free/libre, open-source bridge server and it is (soon) available in [libridge.club](https://libridge.club/).

## Java version

libridge uses Java 17 in development.

## Compiling

libridge uses Makefile and [Maven](https://maven.apache.org/) to build. The following line should be enough:

```
make server
```

This should clean your directory, compile, run all tests, package the final .jar at the `target/` directory and copy it into `./libridge-server.jar`. Use `java -jar ./libridge-server.jar` to run it.


## Code quality

You can also run checks to verify the package is valid and meets quality criteria.

### JaCoCo

```
mvn verify
```

This will include [JaCoCo](https://www.jacoco.org/jacoco/)'s code coverage report at `target/site/jacoco/index.html`

### SpotBugs
You can see potential bugs with [SpotBugs](https://spotbugs.github.io/) running:

```
mvn spotbugs:gui
```

### pitest
To execute mutation tests using [pitest](https://pitest.org/) run:  

```
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```

and see the coverage report at `target/pit-reports/`

## Using Docker

If you prefer to user Docker, you can
```
make build
```
to build the server image and
```
make run
```
to run the server on the default port.

You can also pull the image directly from the [Docker Hub](https://hub.docker.com/r/rulojuka/sbking).

If you want to just start the server on the default port:
```
docker run -p 8080:8080 rulojuka/sbking
```

### On DigitalOcean
Create a [docker based droplet](https://marketplace.digitalocean.com/apps/docker) and
```
docker run -p 8080:8080 rulojuka/sbking
```

### Pushing to DockerHub
```
make deploy
```

## Authors and copyright

### Authors:
See file AUTHORS

### Copyright information:
See file COPYRIGHT

### Full license text:
See file COPYING
