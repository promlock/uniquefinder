all: image database apps

image:
	@echo
	@echo "create image"
	@echo
	podman build -t uniquefinderlocal1 --build-arg USER=user8081 .
#	podman build -t uniquefinderlocal2 --build-arg USER=user8082 .

database:
	@echo
	@echo "create database"
	@echo
	kubectl apply -f ./k3s/postgresdb.yaml

apps:
	@echo
	@echo "create apps"
	@echo
	podman save localhost/uniquefinderlocal1 | sudo k3s ctr images import -
#	podman save localhost/uniquefinderlocal2 | sudo k3s ctr images import -
	kubectl apply -f ./k3s/uniquefinder1.yaml
#	kubectl apply -f ./k3s/uniquefinder2.yaml
	kubectl apply -f ./k3s/service.yaml
	kubectl get node -o wide

clean:
	@echo
	@echo "clean up"
	@echo
	kubectl delete -f ./k3s/postgresdb.yaml
	kubectl delete -f ./k3s/uniquefinder1.yaml
#	kubectl delete -f ./k3s/uniquefinder2.yaml
	kubectl delete -f ./k3s/service.yaml