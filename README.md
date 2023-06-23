# user-service

https://docs.google.com/document/d/1AdmYWAsjNcBM5zdg4jQxBLpiPr9HtQ76HIfZR_2KWjE/edit

to run

  mvn install
  
  docker-compose build

  docker-compose up


  UserController

![image](https://github.com/Artemiy7/user-service/assets/83453822/d7d51129-e31d-44ce-966f-b7214a90586b)


![image](https://github.com/Artemiy7/user-service/assets/83453822/1e56737f-e1f6-46de-b85b-048b6406f5b5)

![image](https://github.com/Artemiy7/user-service/assets/83453822/616e14d9-3e4e-4e09-b6a1-c505396f20f8)

        GET localhost:8080/api/v1/user/25

![image](https://github.com/Artemiy7/user-service/assets/83453822/e8334834-bf1e-49ab-9f33-5acd42822ac6)


        GET localhost:8080/api/v1/user/


        {
            "size" : 7,
            "page" : 0,
            "sortField" : "id",
            "sort" : "DESC"
        }
        

![image](https://github.com/Artemiy7/user-service/assets/83453822/087a9b86-0579-4fee-b9e0-b5d4197920fd)


        GET localhost:8080/api/v1/user?firstName=Jo&firstName=Smith
        
        
        
        {
            "size" : 7,
            "page" : 0,
            "sortField" : "id",
            "sort" : "DESC"
        }


![image](https://github.com/Artemiy7/user-service/assets/83453822/425302e3-31d9-4255-8146-8c5c75644fc6)


        POST localhost:8080/api/v1/user

        
        {
            "firstName" : "Alec",
            "lastName" : "Guinness",
            "email" : "benkenobi@gmail.com"
        }

  
![image](https://github.com/Artemiy7/user-service/assets/83453822/fa723fcb-1d0f-4327-a451-4eef7341ea68)


        PUT localhost:8080/api/v1/user/28
        
        
        {
            "id": "28",
            "firstName": "Tommy",
            "lastName": "Vercetti",
            "email": "tommy@gmail.com"        
        }
        


![image](https://github.com/Artemiy7/user-service/assets/83453822/6f141d8a-86af-4e8a-bfbc-f03f2d2f0fbd)


        PATCH localhost:8080/api/v1/user/29
        
        
        {
            "firstName" : "Neil",
            "lastName" : "Guinness",
            "email" : "benkenobi@gmail.com"        
        }



![image](https://github.com/Artemiy7/user-service/assets/83453822/872df458-2b2b-4e22-89cb-ef9dc0b8212a)








  


