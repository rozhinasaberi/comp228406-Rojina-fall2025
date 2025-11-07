//Lab4 JavaDev
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentInfoApp extends Application {
    
    public void start(Stage primaryStage) {
       
        TextField name = new TextField();
        TextField address = new TextField();
        TextField city = new TextField();
        TextField province = new TextField();
        TextField postalcode = new TextField();
        TextField phone = new TextField();
        TextField email = new TextField();

        GridPane leftPane = new GridPane();
        leftPane.setPadding(new Insets(10));
        leftPane.setHgap(5);
        leftPane.setVgap(5);

        leftPane.add(new Label("Name:"), 0, 0);
        leftPane.add(name, 1, 0);

        leftPane.add(new Label("Address:"), 0, 1);
        leftPane.add(address, 1, 1);

        leftPane.add(new Label("City:"), 0, 2);
        leftPane.add(city, 1, 2);

        leftPane.add(new Label("Province:"), 0, 3);
        leftPane.add(province, 1, 3);

        leftPane.add(new Label("Postal Code:"), 0, 4);
        leftPane.add(postalcode, 1, 4);

        leftPane.add(new Label("Phone Number:"), 0, 5);
        leftPane.add(phone, 1, 5);

        leftPane.add(new Label("Email:"), 0, 6);
        leftPane.add(email, 1, 6);
        
        RadioButton csRadio = new RadioButton("Computer Science");
        RadioButton softdevRadio = new RadioButton("Software Development");
        RadioButton aiRadio = new RadioButton("AI Engineering");

        ToggleGroup programGroup = new ToggleGroup();
        csRadio.setToggleGroup(programGroup);
        csRadio.setSelected(true); //DEFULT
        softdevRadio.setToggleGroup(programGroup);
        aiRadio.setToggleGroup(programGroup);
        

        // course lists for each program
        ObservableList<String> csCourseList = FXCollections.observableArrayList(
                "Math", "Physics", "Java Programming", "Data Structures");
        ObservableList<String> softdevCourseList = FXCollections.observableArrayList(
                "Java Programming", "AI programming", "Web Development", "Information Technology");
        ObservableList<String> AICourseList = FXCollections.observableArrayList(
                "Web Development", "AI Development", "Web Applications", "IT Project Management");        

        ComboBox<String> courseCombo = new ComboBox<>();
        courseCombo.setPrefWidth(160);
        courseCombo.setItems(csCourseList); // start with CS courses

       //selected courses list
       javafx.scene.control.ListView<String> chosenCourses = new javafx.scene.control.ListView<>();
       chosenCourses.setPrefWidth(200);
       chosenCourses.setPrefHeight(140);
       chosenCourses.setFixedCellSize(24);
       chosenCourses.setPlaceholder(new Label("No courses selected")); //Defult
        
       // activities check boxes
        CheckBox homeCheck = new CheckBox("Homework");
        CheckBox volunteerCheck = new CheckBox("Volunteer Work");
        CheckBox groupCheck = new CheckBox("Group Project");

        VBox activityBox = new VBox(5);
        activityBox.getChildren().addAll(homeCheck, volunteerCheck, groupCheck);

        VBox rightPane = new VBox(8);
        rightPane.setPadding(new Insets(10));
        rightPane.getChildren().addAll(
                new Label("Program:"),
                csRadio,
                softdevRadio,
                aiRadio,
                new Label("Activities:"),
                activityBox,
                new Label("Available Courses:"),
                courseCombo,
                new Label("Selected Courses:"),
                chosenCourses
        );

        // change course list when user switches program
        programGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == softdevRadio) {
                courseCombo.setItems(softdevCourseList);
            } else if (newVal == csRadio){
                courseCombo.setItems(csCourseList);
            }
            else { 
                courseCombo.setItems(AICourseList) ;}
            courseCombo.getSelectionModel().clearSelection();
        });

        courseCombo.setOnAction(e -> {
            String selected = courseCombo.getValue();
            if (selected != null && !chosenCourses.getItems().contains(selected)) {
                chosenCourses.getItems().add(selected);
            }
        });

        
        Button displayBtn = new Button("Show Information Selected");
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefRowCount(4);

        FlowPane buttonPane = new FlowPane();
        buttonPane.setPadding(new Insets(5));
        buttonPane.setAlignment(Pos.CENTER_LEFT);
        buttonPane.getChildren().add(displayBtn);

        VBox bottomPane = new VBox(5);
        bottomPane.setPadding(new Insets(5));
        bottomPane.getChildren().addAll(buttonPane, outputArea);

        // buttons 
        displayBtn.setOnAction(e -> {
            String program;
            if (programGroup.getSelectedToggle() == csRadio) {
                program = "Computer Science";
            } else if (programGroup.getSelectedToggle() == softdevRadio) {
                program = "Software Development";
            } else if (programGroup.getSelectedToggle() == aiRadio) {
                program = "AI Engineering";
            } else {
                program = "N/A";
            }

            // activities 
            String act = "";
            if (homeCheck.isSelected()) act += "Homework, ";
            if (volunteerCheck.isSelected()) act += "Volunteer Work, ";
            if (groupCheck.isSelected()) act += "Group Project, ";
            if (act.endsWith(", ")) {
                act = act.substring(0, act.length() - 2);
            }
            if (act.isEmpty()) act = "None";

            // courses 
            String courses;
            if (chosenCourses.getItems().isEmpty()) {
                courses = "None";
            } else {
                courses = String.join(", ", chosenCourses.getItems());
            }

            String result = name.getText() + ", " +
                    address.getText() + ", " +
                    city.getText() + ", " +
                    province.getText() + ", " +
                    postalcode.getText() + ", " +
                    phone.getText() + ", " +
                    email.getText() + "\n" +
                    "Program: " + program + "\n" +
                    "Activities: " + act + "\n" +
                    "Courses: " + courses;

            outputArea.setText(result);
        });

        // BorderPane
        HBox centerBox = new HBox(10);
        centerBox.setPadding(new Insets(5));
        centerBox.getChildren().addAll(leftPane, rightPane);

        BorderPane root = new BorderPane();
        root.setCenter(centerBox);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, 850, 450);
        primaryStage.setTitle("Student Information Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
