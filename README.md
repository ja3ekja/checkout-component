# Checkout component

## Description

Example of platform independent market checkout component application.  

## Requirements 

* Docker Version 17.09.1-ce-mac42 (21090) or later

## Technology stack 

* Java 8
* SpringBoot 1.5.9.RELEASE
* Hibernate
* SQL Server
* Docker
* Lombok

## Git
* No code commits to master
* Each sprint had its own branch
* Pull request
* Squash and merge for better history

## Scrum master

* Application was planed on 6 sprint for stable version 
* On end of each sprint was already prepared demo app
* Some part was implemented in TDD methodology  

## Product owner

* Planning session basing on Hamburger [Hamburger](https://github.com/ja3ekja/checkout-component/blob/master/Hamburger.xlsx) method
* There were minor changes between sprint
* There were minor changes in prioritization 

## Implementation description

* JpaRepository (projection)
* In memory Bucket
* Bucket bases on browser session (@SessionScope)
* Flow controllers -> services -=> repositories
* Initialized data by data.sql file
* Docker container with separate services for db and backend
* Backend bases on my private backed image stored on [hub.docker.com](https://hub.docker.com/r/ja3ekja/maven/)
* @OneToOne & @OneToMany one directional and default fetch type
* @Transactional for create receipt 
* @ControllerAdvice for handle exception from controllers



## How to run
* Clone [checkout-component](https://github.com/ja3ekja/checkout-component) latest version

 ```
 $ git clone https://github.com/ja3ekja/checkout-component.git
 $ cd checkout-component
 $ docker-compose up backend
 ```
 [http://localhost:8080/checkout/get-item/1](http://localhost:8080/checkout/get-item/1)
 
## Docker troubleshooting 

* Docker -> Preferences -> File Sharing -> {share app folder}
* Docker -> Preferences -> Advanced -> {move the memory indicator to 4 GB or more}
* Delete env var if overrides declared in docker-compose.yml file
* Release 8080 and 1433 ports if occupied  
 
