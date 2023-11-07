all: image pod database apps

image:
	@echo
	@echo "create image"
	@echo
    podman build -t uniquefinderlocal1 --build-arg USER=user8081 .
	podman build -t uniquefinderlocal2 --build-arg USER=user8082 .

pod:
	@echo
	@echo "create pod"
	@echo
	podman pod create -p 8081:8081 -p 8082:8082 --name uniquepod

database:
	@echo
	@echo "create database"
	@echo
	podman run --name posgres_db -dt --pod uniquepod -e POSTGRES_PASSWORD=postgres docker.io/postgres

apps:
	@echo
	@echo "create apps"
	@echo
	podman run --name uniquefinder1 --env PORT=8081 --pod uniquepod -d uniquefinderlocal1
	podman run --name uniquefinder2 --env PORT=8082 --pod uniquepod -d uniquefinderlocal2

clean:
	@echo
	@echo "clean up"
	@echo
	podman pod stop uniquepod
	podman pod rm uniquepod
	podman rmi uniquefinderlocal1
	podman rmi uniquefinderlocal2

