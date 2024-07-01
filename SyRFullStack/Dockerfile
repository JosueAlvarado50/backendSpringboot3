# Usa una imagen base de OpenJDK con Maven instalado
FROM maven:3.8.4-openjdk-17-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml para descargar las dependencias
COPY pom.xml .

# Descarga todas las dependencias y las guarda en el contenedor
RUN mvn dependency:go-offline

# Copia el resto de tu aplicación
COPY src ./src

# Empaqueta la aplicación Spring Boot como un JAR ejecutable
RUN mvn package -DskipTests

# Exponer el puerto 8080 para la aplicación Spring Boot
EXPOSE 8081

# Define el comando a ejecutar cuando se inicie el contenedor
CMD ["java", "-jar", "target/SyRFullStack.jar"]
