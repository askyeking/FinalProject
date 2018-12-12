##  Final Project at SkillDistillery

### Overview
  The project is a website that allows customers and vendors to rent clothing articles.
  At the landing page, an unlogged user can view items available for rental. In the navbar, the user can insert login information or register.

  Upon successfully registering / logging in, the user can view all items, as well as the details of any individual item by clicking on it. If the user is not the vendor of the item, and the item is not currently rented, the user will see a form that they can fill in to rent the item.

  Upon successfully renting an item, the user will view the item-rental information - total amount paid, date from, date to. The user can mark an item as returned which will make the item rentable. Also, both the user and the vendor have an option to post a comment on the item-rental, allowing the customer and the vendor to communicate. They will also have an option to remove their comments. The comments are sorted from the newest to the oldest.

  The user can also navigate to their customer-profile by clicking a button in the navbar. They will see information such as their display-name and their e-mail. They will also be able to view a summary of their rental history - the item they rented, the amount paid, dates, etc. The items that the customer has not yet returned will be displayed first. If the item has not been returned the user will see a "return" button. Otherwise, the user will see a "rent again" button.

  If the user does not have a vendor profile, they will see a "create vendor profile" button on their customer profile. Clicking this button will take them to a form that will, upon confirmation, persist their vendor profile to a database.

  If the user has a vendor profile, they will see a "Vendor Profile" button. On the vendor profile, the user will see the picture of the vendor, the display name, and the description. If they are looking at their vendor profile (automatically if navigated through the "Vendor Profile" button), the user will also have an "edit" button that allows them to access a form to edit their vendor profile.

  On their own vendor-profile, a user also a "view inventory" button. This button takes user to a view where they can view, edit or delete each item. They can also add a new inventory item. Finally, the user can click the "Rental history" button under any item's description to view that item's rental history - total income, how many times was the item rented, has the item been returned, how much was paid for each individual rental... By clicking on any rental description user can go into rental view where in addition to other information about the rental and the item, the user can view the communication between them and the customer.

  In the navigation bar, there is also a "view vendors button". This routes user to a view where info about each vendor is listed (image, name, about). The user also has a "view vendor" button below each vendor info, that would take the user to vendor's profile.

  In the navbar, a logged-in user can also see a search box. The user can choose to search by category, the name of the item, or the name of the vendor. A list of items/vendors will be returned. By clicking on the item/vendor user will be able to access that element's detail view.



### Building blocks summary:
  * Database built using SQL and MySQL Workbench
  * Java and Spring (MVC, Security, Hibernate, Bootstrap, Data JPA) back-end for a RESTful web app
  * Angular (TypeScript, HTML, CSS) front-end.


#### Deployed at http://18.188.120.152:8080/SwagREST/


### Stretch goals:

##### Implement Ratings
Allow customers and vendors to rate each other based on the transaction experience. This would allow us to show, for example, the average rating of the vendor before a customer decides if they want to rent an item from that person.

##### Improve payment functionality
Allow the vendor to chose which type of payments they accept - PayPal, credit card, etc. Allow the customer to save their preffered payment method information. Implementing this goal would improve usability for both the vendor and the customer.

##### Vendor should be able to confirm that an item is returned, or report it as missing
Currently, the website trusts the customer when they mark an item as returned. The vendor has no way of combating this issue, other than through comments on the item rental page, which may never be seen by the administrator.

##### Customers and Vendors can flag each-other
For reasons such as the invasion of privacy, damaged item, item not as described, etc.


### Database
The database contains fourteen tables. Below, you will find the schema.
![Alt text](DB/DBSchema.png?raw=true "Database Schema")

### Technologies used
* Java
* Angular
* JavaScript
* SQL
* REST API
* JSON API
* UNIX
* MySQL Workbench
* Spring Boot
* Spring Security
* Spring Hibernate
* Spring Data JPA
* Gradle
* JUnit Jupiter
* AWS

### Team Members
Newel Miole, Anthony King, Novak Dobrosavjevic
