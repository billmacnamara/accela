# Accela Tech Test App
Bill MacNamara

billmacnamara@gmail.com

https://www.linkedin.com/in/billmacnamara

_____________________________________________

# Running the Application
### Pre-Requisites

This application needs the following software to be installed in order to run

* [Java version 8 or higher](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=2ahUKEwih3biNusblAhXzunEKHfIMALIQFjAAegQIBRAB&url=https%3A%2F%2Fwww.oracle.com%2Ftechnetwork%2Fjava%2Fjavase%2Fdownloads%2Fjdk8-downloads-2133151.html&usg=AOvVaw1UV3vTG44ykNLIQTB0_Hsl)
* [Apache Maven](https://maven.apache.org/download.cgi)
* PostgreSQL 
    * [Docker Image](https://hub.docker.com/_/postgres)
    * [Manual installation](https://www.postgresql.org/download/)
    
### Downloading the Application
To download the application, open a command line terminal at the directory you wish to save the app in and enter the
command 
```bash
git clone https://github.com/billmacnamara/accela.git
```

You may need to re-enter your git credentials in order to complete the download
    
### Database setup

Before running the application, some setup for the database is required. The three steps we need to complete before 
running the app are:

* Creating the database
* Creating a database user
* Creating the table we will be querying

The SQL script to achieve all three of these steps can be found in the `db.sql` file in the resources directory.
By default, PostgreSQL will run on port 5432. If this port is in use and you need to use a different port, 
you will need to update the `persistence.xml` and `HibernateConfiguration.java` files to reflect this change
This would ideally be handled by a configuration file in an actual production environment but I unfortunately did not 
have time to implement this for this project. 

### Building the app

In order to build the application, open a command line terminal and navigate to the directory you downloaded the 
application to earlier and enter the command
```bash
mvn clean package
```

### Starting the app

Once the app has been built, you can run it with the command
```bash
java -jar target\accela-1.0-SNAPSHOT.jar
```
