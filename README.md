# e-media
# Run the project?
To run this project you need to follow the below steps:
- Download the project source from GitHub or easily clone the code by running the ```git clone https://github.com/samim-git/e-media.git```
- To run locally you need to go the services in the intellij IDE and run each service separately. In this case, make sure you configure your local mySQL server in ```application.properties``` file
- To run on the docker:
  - Go to the root project where docker compose file exist.
  - Run the command ```mvn clean compile jib:build```. This command will build the image of each service and push to the docker hub. Make sure to add your docker hub details in the ```pom.xml``` file in the build section.
  - After completing pushing the images in the docker hub you need to run ```docker-compose up``` or ```docker-compose up -d```.