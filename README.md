# Job Finder App - Server Side

In today's technological age, we often hear the phrase that life is very fast. One of the reasons for this phenomenon can be found in the networking of people in the world and the availability of a large amount of information with the click of a mouse.

The goal of our idea is to provide fast and easy job search for each user of our application with all the necessary information about the job, employer, reviews of the employer, but also easy and direct communication with other job seekers and employers.

On the other hand, we would also like to provide employers with an easy job posting service, insight into all users which have applied, information and reviews for each user.

The main focus of the Job Finder web application is on speed and simplicity. Therefore, we came up with the idea that users who favor certain employers (firms, companies, etc.) can get a direct notification when those employers post some ads.

### Technologies used for implementation of this Job Finder App - Server Side
Analyzing the problem itself, it was noticed that this application resembles a social network in its structure, ie. it has a large number of connections - relationships between entities. Most of our queries require searching for specific links and attributes of those links. From this we conclude that the best solution for presenting data is NoSql database, even better graph oriented database. I opted for the graph-oriented Neo4j database

For the implementation of the server side, I opted for Spring Boot technology because of its compactness and simple configuration, and mostly because it supports Neo4j graph-oriented database, via the Spring Data Neo4j (SDN) package.

To ensure the security of the Job Finder application, I opted for a combination of the Spring Security package and the JWT (JSON Web Token).

## What you will need to run this API
First, you will need to clone this repo to your local machine 
and open this project in your favorite IDE (IDE have to support Spring Boot app, I recommend InteliJ or STS).
Next, you will need to build this project as maven and let your IDE pull all the necessary dependencies. 
After that, you can run this app as Spring Boot Project, but installation isn't finished yet, we still need a data base!
As mentioned, the best solution for this project is to use Neo4j graph oriented data base. 
### How to setup Neo4j database?
You will need to download Neo4j Desktop app. 
It is a Gui based app which gives convenient way for developers to work with local Neo4j databases. (https://neo4j.com/download/)
After the download, run the Neo4j Desktop app. Now is time to create data base for our project. 
In the central window of Desktop app you will see big button that says: Add Database. Click it. Now click on the "Create a Local Graph".
You will have to enter some initial data for out data base. Name your graph: "jobfinder", for password enter "jobfinder" 
and choose 3.5.18 version of the data base. Finally click on Create. Start the data base. And in your favorite browser open new tab with
address http://localhost:7474/browser/ , it will open neo4j browser for started data base. It will ask you to enter username and password. Write neo4j for username, and for password write jobfinder. Now you can write queries here. More about neo4j queries and Cypher language you can find in their official web site.
Now our data base is set and running.
### How to use this API
All of the available REST api's, you can find in package named "resource", 
and logic behind this REST endpoint you can find in package named "service".
You can build your own client side app, and use this REST api endpoints or even test the api from Postman.
But, i suggest you to fork and clone my client side Angular8 app from repo: https://github.com/MarkoMatko/JobFinderWebApp
It has implemented all of the base functionalities for this Job Finder social network app.

