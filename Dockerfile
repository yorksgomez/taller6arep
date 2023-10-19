FROM openjdk:11

WORKDIR /usrapp/bin

ENV PORT 6000

# Instala MySQL Server y establece la contraseña de root
RUN apt-get update && apt-get install -y mariadb-server && apt-get clean && rm -rf /var/lib/apt/lists/*
ENV MYSQL_ROOT_PASSWORD=abc123

# Copia tus scripts SQL (por ejemplo, create-table.sql) al contenedor
COPY create_table.sql /docker-entrypoint-initdb.d/create-table.sql

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.eci.arep.Main"]

