## CV Creator Project

### Motivation
This is private project for practising implementing a web application with database support, 
and to learn ...
* ... how to upload images to- and store them in cloud
* ... how to create pdf
* ... how to implement drag and drop feature


### Project Repos
The whole Project consist of <b>2 Repos:</b><br>
1. <b>Frontend Repo:</b> https://github.com/KriszProg/CV-creator-frontend <br>
2. <b>Backend Repo:</b> https://github.com/KriszProg/cv-creator-backend _(this repo)_ <br>

### Features
* With CV Creator you can create your CV and save in a nice formatted pdf file.
* You can customize the CV Header by adding a background image, and your profile photo as well.
* You can add a unique name for your CV, to make them easier to find.
* In the multi-item sections _(such as: Educations, Work Experience, Spoken Languages)_, you can 
flexibly rearrange the content by supporting of drag and drop.
* The created pdf file will be named automatically  and contains your name and the exact date 
and time of creation.


### Technologies

The following technologies have been used for implementing the project:<br>

<b>Frontend:</b>:
* ReactJS
* Cloudinary
* React-pdf
* Beautiful dnd

<b>Backend:</b>
* Java
* Spring Boot
* Project Lombok
* H2 Database
* Hibernate

<!-- GETTING STARTED -->

### How to Download and Try it out
<br>
1. You should clone <b>both Repo</b> (Frontend and Backend) as well.<br>

<br>
2. After you cloned the Backend Repo, you should create a folder with name: 
<b>'jpa_databases_for_own_projects'</b> in the root folder.<br> 

```
cd ~
mkdir jpa_databases_for_own_projects
```
_Backend App will store the Database in this folder._

<br>
3. Open the Backend App in your IDE and run the 
<b>CvCreatorBackendApplication</b> file.<br>

<br>
4. Open the Frontend App in your IDE

<br>
Open a Terminal inside your IDE and run the App by:

```
npm start
```
