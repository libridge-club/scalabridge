APP_NAME=rulojuka/libridge

all: package

clean:
	mvn clean
	rm -f ./libridge-client.jar ./libridge-server.jar
	docker rmi $(APP_NAME); true

package: server client

server: kill_server package_server copy_server

kill_server:
	@./kill_server.sh

package_server:
	mvn -f pom-server.xml clean package

copy_server:
	cp target/libridgebackend-server-1.0.0-alpha.jar ./libridge-server.jar

client: kill_server package_client copy_client

package_client:
	mvn -f pom-client.xml clean package

copy_client:
	cp target/libridgebackend-client-1.0.0-alpha.jar ./libridge-client.jar && chmod +x ./libridge-client.jar

build:
	docker build -t $(APP_NAME) .

deploy: build
	docker login
	docker push rulojuka/sbking

run:
	docker run -p 8080:8080 --rm $(APP_NAME)
