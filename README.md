# Book store

To start this application navigate to the root directory of this project in a terminal window and run `./gradlew bootrun`. 

Alternatively open this project in an IDE and navigate to the `Application` class and click the green arrow next to the class name.

#Book Sales

To purchase a book send a request to the following rest API endpoint:
`http://localhost:8080/book/{book title}/buy/{quantity}` 
If there is enough stock for the given title and quantity a success message will be returned but if the book is not stocked
or there is not enough stock to fulfil the request then the response will contain a message to that effect.

Upon a successful sale a transaction will be written to the transaction repository with details on the title, quantity sold 
and total value of that transaction.

#Book reports

To generate a report indicating number of sales and amount of profit for each of the titles on offer a request can be made 
to the following rest API endpoint:
`http://localhost:8080/book-store/report` 

A report will print out the number of copies sold and total amount of profit made on each title. The total amount of profit 
will include any transactions required to purchase books when the stock level has fallen too low.
