module ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori to javafx.fxml;
    exports main.java.ca.bcit.comp2522.termproject.comp2522202410termprojecttatstatsunori;
}