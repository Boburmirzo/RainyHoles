# Calculate the volume of water 

Write an application which takes an array as an input, and calculates the volume of water
which remained after the rain, in units.


![Overview](doc/images/userInterface.png)

## Algorithm

Initialize left pointer to 0 and right pointer to size-1
While left<right, do:
If height[left] is smaller than height[right]
   -If height[left]>=left_max, update left_max
Else add left_max−height[left] to ans
Add 1 to left.
Else
If height[right] >= right_max, update right_max
Else add right_max−height[right] to ans
Subtract 1 from right.

## Design

App uses JSF 2.2 for model/view layer and stateless EJB 3.2 for calculation. It can be deployed
to any Java EE 7 compatible container.

On UI side following additional components are used:
 - PrimeFaces 6.2.2
 - BootsFaces used for Bootstrap-friendly layout and components.

App consists of following components:
 - Hole model -  represents the hole profile.
 - WaterVolume model - represents result of water volume calculation for particular hole.
 - JSF view for application UI which binds to surfaceController.
 - RainyHolesController - provides input handling, calculation request and chart model representation.
 - HoleSurfaceVolumeService - Stateless EJB which provides calculation for the hole.
 - HoleSurfaceVolumeServiceTest - tests all possible cases for the calculation algorithm.   

## Installation
Prerequisites:
 - Java JDK 8 http://www.oracle.com/technetwork/java/javase/
 - Maven 3 https://maven.apache.org
 - Wildfly 12 http://wildfly.org

Installation Steps:
1. Install & Run Wildfly application server.
    ```
    cd wildfly-12.0.0.Final
    ./bin/standalone.sh
    ```
    See documentation at http://wildfly.org for more details on running Wildfly on different platforms.    
2. Unzip rainyhills.zip archive or clone repository
    ```
    unzip rainyhills.zip
    cd rainyhills
    ``` 
    or
    ```
    git clone https://github.com/Boburmirzo/RainyHoles.git
    ```
3. Run test & build with maven
    ```
    mvn clean install
    ```
4. Deploy rainyhills to application server
    ```
    mvn package wildfly:deploy
    ```
    or deploy `rainyholes.war` located in `rainyholes/target/`
    to your application server.
    
## Usage
Open application at http://localhost:8080/rainyholes/index.xhtml.
Specify your hole profile and click "Calculate".        
