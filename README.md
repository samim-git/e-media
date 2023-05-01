# e-media
### Run the project?
To run this project you need to follow the below steps:
- Download the project source from GitHub or easily clone the code by running the ```git clone https://github.com/samim-git/e-media.git```
- To run locally you need to go the services in the intellij IDE and run each service separately. In this case, make sure you configure your local mySQL server in ```application.properties``` file
- To run on the docker:
  - Go to the root project where docker compose file exist.
  - Run the command ```mvn clean compile jib:build```. This command will build the image of each service and push to the docker hub. Make sure to add your docker hub details in the ```pom.xml``` file in the build section.
  - After completing pushing the images in the docker hub you need to run ```docker-compose up``` or ```docker-compose up -d```.
### Deploy the project to the k8s
To deploy the project to the k8s, simply follow the below steps.
- install ```Minikube``` using the below command in mac ```brew install minikube```
- Create a docker container by running the command ```minikube start --driver=docker```
- Verify the installation ```kubectl cluster-info```. You should be able to see the following information
  - [x] _Kubernetes control plane is running at https://127.0.0.1:54159_
  - [x] _CoreDNS is running at https://127.0.0.1:54159/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy_
- Start the mini kube by running the command ```minikube start```
- Apply the deployment by running the command ```kubectl apply -f k8s-deployment.yaml```
- Apply the services by running the command ```kubectl apply -f k8s-service.yaml```