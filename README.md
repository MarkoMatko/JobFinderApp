# Job Finder App - Server Side

-uvod-o ideji,tehnologije
## What you will need to run this API
First, you will need to clone this repo to your local machine 
and open this project in your favorite IDE (IDE have to suport Spring Boot app, I recommend InteliJ or STS).
Now, you will need to build this project as maven and let your IDE pull all the neccessery dependencies. 
After that, you can run this app as Spring Boot Project, but installation isn't finished yet, we need data base!
As i mentioned, the best solution for this project is to use Neo4j graph oriented data base. 
### How to setup Neo4j database?
You will need to download Neo4j Desktop app. 
It is a Gui based app which gives convenient way for developers to work with local Neo4j databases. (https://neo4j.com/download/)
After a download, run the Neo4j Desktop app. Now is time to create data base for our project. 
In the central window of Desktop app you will see big button that says: Add Database. Click it. Now click on the "Create a Local Graph".
You will have to enter some initial data for out data base. Name your graph: "jobfinder", for password enter "jobfinder" 
and choose 3.5.18 version of the data base. Finally click on Create. Start the data base. And in your favorite browser open new tab with
address http://localhost:7474/browser/ , it will open neo4j browser for started data base. It will ask you to enter username and password. Write neo4j for username, and for password write jobfinder. Now you can write queryes here. More about neo4j queryes and Cypher language you can find in their official web site.
Now our data base is set and running.
### How to use this API
All of the avalabile REST api's, you can find in package named "resource", 
and logic behind this REST endpoint you can find in package named "service".
You can build your own client side app, and use this REST api endpoints or even test the api from Postman.
But, i suggest you to fork and clone my client side Angular8 app from repo: 
It has implemented all of the base functionalities for this Job Finder social network app.

