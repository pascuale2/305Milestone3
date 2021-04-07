# 305Milestone3

Authors: Laroy Milton, Erwin Pascal, Jason Lee, Korey Sniezek

Description: We used Korey's base MS2 and built new functionality on top of it. We added several features and refactored the code as needed. We followed an MVC pattern using the controller as a mediator between model and controller. The controller itself uses a handler for property objects that encapsulate our data, functionality added is listed below.

Functionality added: 
  Loading from the database
  Google maps pin drop for property accounts
  Graphs that breakdown statistics by ward, and then by neighbourhood. (Due to the unforseen circumstance of the city of Edmonton database removing the ward classification, these 
    graphs due not function when loading from the database. They require rework to function with the database. This was not done due to time constraints.
  Column visibility checkboxes added
  CSS styling added. 

Instructions to run:
  Beyond setting up javafx in the normal manner, please add this to project properties -> run -> VM: 
      --module-path "YOUR_PATH_TO_JAVAFX" --add-modules=javafx.swing,javafx.graphics,javafx.fxml,javafx.media,javafx.web --add-reads javafx.graphics=ALL-UNNAMED --add-opens 
        javafx.controls/com.sun.javafx.charts=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio.common=ALL-
        UNNAMED --add-opens javafx.graphics/com.sun.javafx.css=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.runtime=ALL-UNNAMED

  To disable loading from URL, uncomment the line shown below, and comment out the line below it
    ![image](https://user-images.githubusercontent.com/46851615/113911674-7a247e80-9797-11eb-87f4-3a8b4551b319.png)
