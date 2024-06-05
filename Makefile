APP_NAME=rulojuka/libridge-backend

all: package

clean:
	mvn clean
	rm -f ./libridge-server.jar
	docker rmi $(APP_NAME); true

package: kill_server package_server copy_server

kill_server:
	@./kill_server.sh

package_server:
	mvn clean package

copy_server:
	cp target/libridgebackend-server-1.0.0-alpha.jar ./libridge-server.jar

build:
	docker build -t $(APP_NAME) .

deploy: build
	docker login
	docker push $(APP_NAME)

run:
	docker run -p 8080:8080 --rm $(APP_NAME)
