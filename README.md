# user-service

| Service | Port  |  CI  |
| :---:   | :---: | :---:   |
| user-service | 8080   | [![CircleCI](https://dl.circleci.com/status-badge/img/gh/Artemiy7/user-service/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/Artemiy7/user-service/tree/main)  |
| mongodb | 27017   |  |


<h3 align="left">Languages and Tools: </h3>

Java 11, Spring Boot, Spring Data MongoDB, Maven, Docker and Docker Compose, JUnit, Mockito, Swagger.

<p align="left"> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="79" height="79"/> </a> 
<a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="70" height="70"/> </a>
<a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/mongodb/mongodb-icon.svg" alt="postman" width="70" height="70"/> </a> 
<a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/docker/docker-icon.svg" alt="postman" width="70" height="70"/> </a> 
<a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://github.com/vscode-icons/vscode-icons/blob/master/icons/folder_type_maven.svg" alt="postman" width="70" height="70"/> </a> 
<a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://upload.vectorlogo.zone/logos/mockito/images/36c60459-46b2-46dd-87b7-5ed157df95d4.svg" alt="postman" width="110" height="70"/> </a>  



To run docker-compose you need:
1) Open cmd in project directory e.g.: ~/IdeaProjects/user-service$
2) Create project jar file.



        mvn install


  
4) Build docker images.



        docker-compose build



5) Run docker containers.



        docker-compose up
       
        

!   To run user-service without docker, you need specify profile -Dspring.profiles.active=local   !



You can test API using Swagger http://localhost:8080/swagger-ui.html#/user-controller  or using Postman.





<h3 align="left">UserController endpoints:</h3>



![image](https://github.com/Artemiy7/user-service/assets/83453822/d7d51129-e31d-44ce-966f-b7214a90586b)






<h2 align="left">Testing:</h2>





<h3 align="left">To find User by id:</h3>



        GET localhost:8080/api/v1/user/3





<h4 align="left">Result:</h4>



![image](https://github.com/Artemiy7/user-service/assets/83453822/72310457-ecaa-40d5-b2d1-5ae12e415781)





<h3 align="left">To find Users using Pagination and Sorting</h3>




        GET localhost:8080/api/v1/users?size=3&page=0&sort_field=id&sort_order=DESC




<h4 align="left">Result:</h4>




![image](https://github.com/Artemiy7/user-service/assets/83453822/b2aaea82-625a-45df-a6a4-be4ca4fe3922)






<h3 align="left">You can search User by firstName, lastName, email:</h3>



        GET localhost:8080/api/v1/users?size=3&page=0&sort_field=id&sort_order=DESCfirstName=Jo&lastName=Smith



<h4 align="left">Result:</h4>




![image](https://github.com/Artemiy7/user-service/assets/83453822/cb702849-3373-408f-98d0-06f004d44869)






<h3 align="left">To create a User:</h3>



        POST localhost:8080/api/v1/users

        
        {
            "firstName" : "Alec",
            "lastName" : "Guinness",
            "email" : "benkenobi@gmail.com"
        }






<h4 align="left">Executing request:</h4>


  
![image](https://github.com/Artemiy7/user-service/assets/83453822/fa723fcb-1d0f-4327-a451-4eef7341ea68)





<h4 align="left">Checking result:</h4>



![image](https://github.com/Artemiy7/user-service/assets/83453822/2744afe8-3148-43d3-98ce-a26b8a7a5a60)




<h3 align="left">To update a full User object:</h3>



        PUT localhost:8080/api/v1/users/4
        
        
        {
            "firstName": "Tommy",
            "lastName": "Vercetti",
            "email": "tommy@gmail.com"        
        }


        


<h4 align="left">Executing request:</h4>



![image](https://github.com/Artemiy7/user-service/assets/83453822/d8935bb5-233c-4db7-a6ce-408dfe09b3c9)





<h4 align="left">Checking result:</h4>



![image](https://github.com/Artemiy7/user-service/assets/83453822/081052c0-709b-4005-9a61-b45ef25d2e39)





<h3 align="left">To update ONLY certain field in User object:</h3>



        PATCH localhost:8080/api/v1/users/1
        
        
        {
            "firstName": "Joe",
            "lastName": "Vercetti",
            "email": "tommy@gmail.com"        
        }





<h4 align="left">Executing Request:</h4>



![image](https://github.com/Artemiy7/user-service/assets/83453822/05680214-9c47-46f4-976e-92dc3e8db798)





<h4 align="left">Checking result:</h4>



![image](https://github.com/Artemiy7/user-service/assets/83453822/eeca95e3-5216-4c69-a3b6-b4eee5012e69)





<h3 align="left">To delete User by Id:</h3>





<h4 align="left">Executing Request:</h4>



![image](https://github.com/Artemiy7/user-service/assets/83453822/9f7d7671-bfad-426d-881c-885acbdd1f84)






<h4 align="left">Checking result:</h4>



![image](https://github.com/Artemiy7/user-service/assets/83453822/ae9a5192-5fb3-4f00-af10-8d504945be83)








  


