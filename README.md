# Online Shop für eine Cloud-Umgebung ☁

Entwicklung eines Online Shops auf der Basis einer Microservice-Architektur.

## Loslegen 

Befolge diese einfachen Schritte, um eine lokale Kopie des Projektes zum Laufen zu bringen.

### Vorraussetzungen 

- [Git](https://git-scm.com/) installieren
- [Docker](https://www.docker.com/) installieren
- [Minikube](https://minikube.sigs.k8s.io/docs/start/) installieren

### Installation 

1. Minikube starten
   ```sh
   minikube start --vm-driver=hyperv --network-plugin=cni --cpus 4 --memory 8192
   ```
2. Ingress aktivieren 
   ```sh
   minikube addons enable ingress
   ```
3. Calico für Network Policies installieren
   ```sh
   kubectl apply -f https://docs.projectcalico.org/manifests/calico.yaml
   ``` 
4. Metrics Server für den Horizontal Pod Autoscaler installieren
   ```sh
   minikube addons enable metrics-server
   ```   

