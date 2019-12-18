# Library_Project

## API documentation

La documentation de l'API se trouve sur le site : http://localhost:9005/swagger-ui.html.

Certaines requêtes n'apparaissent pas. Celles-ci sont fournient par Spring DATA REST. 
Il n'était (à priori) pas possible de l'intégrer à SWAGGER dans sa release actuelle (maven repository).

# Configuration

### API

Vous devez mettre un secret jwt et retirer la ligne spring.profil = dev ou la mettre en commentaire dans le fichier de configuration application.properties de l'api : library-books-service. 

Ce secret est important pour décoder le token.

### BATCH

Vous devrez mettre votre adresse mail dans le fichier de configuration application.properties du batch : library-batch-service.

Par défaut, la conf mail fonctionne pour gmail, mais il vous est possible de changer le web service en modifiant les paramètres de conf.


Par défaut, il nécessaire d'avoir un compte google avec google authenticator pour permettre de générer un mot de passe d'application qu'il faudra renseigner dans le fichier conf. 


# Build

Pour le build de l'application : 

Spring boot importe un conteneur web.

Pour démarrer l'application, vous avez plusieurs possibilités :

- Via l'exécuteur de votre IDE directement

- Sur le terminal (une fois l'application packagée dans un dossier target) : 
 
        $ java -jar target/myapplication-0.0.1-SNAPSHOT.jar

- En utilisant Maven : 

        mvn spring-boot:run
        
- En utilisant Gradle : 

        gradle bootRun


