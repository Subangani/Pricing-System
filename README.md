# Pricing-System

Use case:
The price structures are as follows:
- The rare product "Penguin-ears" is 20 units per carton. A carton is 175,-
- The product "Horseshoe" is 5 units per carton, a carton is 825,-
- If you purchase single units, the price is acquired using the carton price multiplied by an increase of
30% (1.3). (to compensate for manual labor)
- If you purchase 3 cartons or more, you will receive a 10% discount off the carton price

System consist of front end application in react inside /my-app and spring boot application inside /service to get the data from postgres Db and process it.

The react application is developed using Umi frawork.

Start the front end app: Yarn install
                          Yarn start

Technology stack: React, Java, Postgres


The list of products are shown as below
![image](https://user-images.githubusercontent.com/11584838/123569119-59daed80-d7e3-11eb-9a9d-501ec76e2cc9.png)

The number of requested quantity of products vs unit price of the products are also shown up to 1 to 50

![image](https://user-images.githubusercontent.com/11584838/123569285-ae7e6880-d7e3-11eb-893b-d1d3c7d72770.png)


The user can select the product type and the quantity to find the total price of the products

![image](https://user-images.githubusercontent.com/11584838/123569398-dc63ad00-d7e3-11eb-985c-ad04f4718898.png)


