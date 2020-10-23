FROM openjdk:8
MAINTAINER cesar castillo <genesiscastillo@hotmail.com>
ADD target/${project.artifactId}-${project.version}.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
