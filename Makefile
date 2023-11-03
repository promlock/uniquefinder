all: build image pod database apps

build:
	@echo
	@echo "build app"
	@echo
	./mvnw clean package

image:
	@echo
	@echo "create image"
	@echo
	podman build -t uniquefinder .

pod:
	@echo
	@echo "create pod"
	@echo
	podman pod create -p 8081:8080 --name uniquepod

database:
	@echo
	@echo "create database"
	@echo
	podman run --name posgres_db -dt --pod uniquepod -e POSTGRES_PASSWORD=postgres docker.io/postgres

apps:
	@echo
	@echo "create apps"
	@echo
	podman run --name uniquefinder -d --pod uniquepod docker.io/kovacsp22/uniquefinder

clean:
	@echo
	@echo "clean up"
	@echo
	podman pod stop uniquepod
	podman pod rm uniquepod

