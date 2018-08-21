# SuperheroSightings
My Software Guild Milestone 9 and Capstone project:  Superhero Sightings

The Superhero Sightings project was the culmination of my Software Guild coursework.  In this project I was able to demonstrate my proficiency in everything I had learned throughout the course.  This is a Spring MVC web app that uses a relational database that I created along with Spring security.  Application security is limited to three types:  anonymous, sidekick(user), and admin.  It is possible to add as many users or admins within the program as desired.  It is also possible to change roles; e.g., an user can be changed to an admin or an admin can be changed to a user.  Users and admins can also be removed from the system.  All passwords are encoded using BCrypt.
_______________________________________________________________________________________________________________

Anonymous Access Screenhots

Anybody with an internet connection can view this page
<img width="1271" alt="superhero sightings home" src="https://user-images.githubusercontent.com/30512121/44425506-11daed00-a55a-11e8-8c85-a443a2eef3a0.png">

Anybody can view this page, but it does not have add, edit, or delete functionality
![ss anonymous superheroes supervillains](https://user-images.githubusercontent.com/30512121/44425524-1acbbe80-a55a-11e8-9838-9fa41ea3d126.png)

_______________________________________________________________________________________________________________

Login Page

The system asks for login information if a restricted page is requested
![login page](https://user-images.githubusercontent.com/30512121/44426152-e35e1180-a55b-11e8-9e3b-728431b9c97f.png)

_______________________________________________________________________________________________________________

User Access Screenshots

Users can create, read, or update superheroes/supervillains, but they cannot delete.  Additional sightings, superpowers, or organizations can be added to the superhero.
![user superheroes supervillains](https://user-images.githubusercontent.com/30512121/44425537-26b78080-a55a-11e8-9e75-e70ce703d109.png)

Users can create, read, or update sightings, but they cannot delete
![user sightings](https://user-images.githubusercontent.com/30512121/44425863-1e137a00-a55b-11e8-8790-cbb9d4283aab.png)

Users can create, read, or udpdate locations but they cannot delete
![user locations](https://user-images.githubusercontent.com/30512121/44425867-210e6a80-a55b-11e8-9aad-9d84b5057166.png)

Users can only view organizations, they cannot create, update, or delete
![user organizations](https://user-images.githubusercontent.com/30512121/44425871-24095b00-a55b-11e8-9495-a42ffa50a5c1.png)

Users can create, read, or update superpowers but they cannot delete
![user superpowers](https://user-images.githubusercontent.com/30512121/44426105-c6294300-a55b-11e8-90f1-7eee9aba7ee6.png)

A details page example
![user shsv details](https://user-images.githubusercontent.com/30512121/44426133-d5a88c00-a55b-11e8-9b78-248e641f79f7.png)

Editing superheroes/supervillains
![user edit superhero](https://user-images.githubusercontent.com/30512121/44426138-d93c1300-a55b-11e8-8833-66552f846ad2.png)

Editing sightings
![user edit sighting](https://user-images.githubusercontent.com/30512121/44426142-dc370380-a55b-11e8-93ee-88365280218e.png)

Editing locations
![user edit location](https://user-images.githubusercontent.com/30512121/44428290-ecea7800-a561-11e8-9b31-c4701024b35a.png)

Editing superpowers
![user edit superpower](https://user-images.githubusercontent.com/30512121/44426150-e0fbb780-a55b-11e8-9adf-d3c9bacc4ba2.png)

_______________________________________________________________________________________________________________

Admin Access Screenshots - Notice screens now have the 'User Admin' selection in the navbar and the delete options are visible

Admin superheroes/supervillains page
![admin shsv](https://user-images.githubusercontent.com/30512121/44426159-e8bb5c00-a55b-11e8-9d9f-1f9c5f059847.png)

Admin sightings page
![admin sightings](https://user-images.githubusercontent.com/30512121/44426161-eb1db600-a55b-11e8-8039-a9c9d5282882.png)

Admin locations page
![admin locations](https://user-images.githubusercontent.com/30512121/44426164-ed801000-a55b-11e8-83fc-81f02adac3d4.png)

Admin organizations page
![admin organizations](https://user-images.githubusercontent.com/30512121/44426168-ef49d380-a55b-11e8-8926-c1bea70106b6.png)

Admin superpowers page
![admin superpowers](https://user-images.githubusercontent.com/30512121/44426169-f1ac2d80-a55b-11e8-8bbf-3a40f7ef6266.png)

_______________________________________________________________________________________________________________

Admins can create, update, or disable users of the application

This page diplays the users of the application
![ss user list](https://user-images.githubusercontent.com/30512121/44429063-43f14c80-a564-11e8-9bf7-bca0acba5c97.png)

This page allows an admin to add a user or another admin by selecting the checkbox.
![ss add user](https://user-images.githubusercontent.com/30512121/44429067-481d6a00-a564-11e8-9910-d8ee66b33370.png)

This page allows for editing.  Access can be changed by selecting or not selecting checkbox.  New password is required.
![ss edit users](https://user-images.githubusercontent.com/30512121/44429065-4653a680-a564-11e8-8573-52bff7954ba6.png)

_______________________________________________________________________________________________________________

Photos (The ability to add photos to the application was a challenge and not required)

Add a profile photo
![add profile picture](https://user-images.githubusercontent.com/30512121/44426109-c9243380-a55b-11e8-8c41-c8af206a7694.png)

Add a sighting photo
![add sighting photo](https://user-images.githubusercontent.com/30512121/44426116-ccb7ba80-a55b-11e8-9790-4907a0351c5c.png)

Picture Gallery

![picture gallery](https://user-images.githubusercontent.com/30512121/44426125-d2ad9b80-a55b-11e8-937a-2ce6a3a9b712.png)

_______________________________________________________________________________________________________________

Custom Error Page

The error page has been customized and is not a Tomcat error page
![custom error page](https://user-images.githubusercontent.com/30512121/44426154-e5c06b80-a55b-11e8-861f-5eda5632248e.png)

