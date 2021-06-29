import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.io.IOException;
import java.math.BigDecimal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public  class GuiDemo extends Application {
   private ComboBox<String> stateOptions = new ComboBox<String>();
   private ChoiceBox<String> population = new ChoiceBox<String>();  

   public static void main(String[] args) {
      Application.launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws IOException {
      ArrayList<CovidPrisonCases> data = CovidPrisonCases.readDataFile("data/covid_prison_cases.csv");

      // PROBLEM 1: % COVID CASES AND DEATHS

      primaryStage.setTitle("% Covid Cases and Deaths");

      GridPane pane = new GridPane();
      pane.setAlignment(Pos.CENTER);
      pane.setHgap(5.5);
      pane.setVgap(5.5);
      
      pane.add(new Label("Choose state: "), 0, 0);
      pane.add(new Label("Staff: "), 2, 0);
      pane.add(new Label("Prisoners: "), 2, 2);
      pane.add(stateOptions, 1, 0);
      
      
      Label a = new Label();
      Label b = new Label();

      pane.add(a, 3, 0);
      pane.add(b, 3, 2);

      // populating stateOptions ComboBox

      for (int i = 0; i < data.size(); i++) {
         String st = data.get(i).getState();
         stateOptions.getItems().add(st);
      }

      EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

         public void handle(ActionEvent e) {
         String stateSelection = stateOptions.getValue().toString();
         BigDecimal prisoners = Analysis.covidPrisonerDeath(data, stateSelection);
         BigDecimal staff = Analysis.covidStaffDeath(data, stateSelection);

         String prisonersString = prisoners.toString();
         String staffString = staff.toString();

         a.setText(staffString + "%");
         b.setText(prisonersString + "%");
      }
      
   };

      stateOptions.setOnAction(event);
      
      Scene scene = new Scene(pane);
      primaryStage.setScene(scene);
      primaryStage.show();

      // PROBLEM 2: NATIONAL AVERAGES

      population.getItems().add("Staff");
      population.getItems().add("Prisoners");

      GridPane pane2 = new GridPane();
      pane2.setAlignment(Pos.CENTER);
      pane2.setHgap(5.5);
      pane2.setVgap(5.5);

      Label c = new Label("Choose population: ");
      Label d = new Label();

      pane2.add(c, 0, 1);
      pane2.add(population, 2, 1);
      pane2.add(d, 3, 1);
      
      EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {

         public void handle(ActionEvent e) {
         String popSelect = (String) population.getValue();

         if (popSelect.equals("Staff")) {
            int val = Analysis.averageCovidCasesPrisoners(data);
            String valString = String.valueOf(val);
            d.setText(valString + "%");
         }

         else {
            int val = Analysis.averageCovidCasesStaff(data);
            String valString = String.valueOf(val);
            d.setText(valString + "%");
         }
      }};

      population.setOnAction(event2);

      Stage stage = new Stage();
      stage.setTitle("National Case Averages");
      stage.setScene(new Scene(pane2));
      stage.show();

      // PROBLEM 3: VACCINATIONS

      ComboBox<String> stateOptions2 = new ComboBox<String>();

      for (int i = 0; i < data.size(); i++) {
         String st = data.get(i).getState();
         stateOptions2.getItems().add(st);
      }

      ChoiceBox<String> pop2 = new ChoiceBox<String>();
      pop2.getItems().add("Staff");
      pop2.getItems().add("Prisoners");

      Label f = new Label("State: ");
      Label g = new Label("Population: ");
      Label h = new Label();
      Button button = new Button("Check rates");

      EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {

         public void handle(ActionEvent e) {
            String stateSelect2 = (String) stateOptions2.getValue();
            String popSelect2 = (String) pop2.getValue();

            TreeMap<String, Double> map = Analysis.percentPrisonerVax(data);
            TreeMap<String, Double> map2 = Analysis.percentStaffVax(data);

            if (popSelect2.equals("Prisoners")) {
               for (Entry<String, Double> entry : map.entrySet()) {
                  if (entry.getKey().equals(stateSelect2)) {
                     Double result = entry.getValue();
                     String resultString = String.valueOf(result);
                     h.setText(resultString + "%");
                  }
                  }
            }
            else if (popSelect2.equals("Staff")) {
               for (Entry<String, Double> entry : map2.entrySet()) {
                  if (entry.getKey().equals(stateSelect2)) {
                     Double result = entry.getValue();
                     String resultString = String.valueOf(result);
                     h.setText(resultString + "%");
                  }
               }
            }
            }
      };

      button.setOnAction(event3);
      HBox hbox = new HBox(20, f, stateOptions2, g, pop2, button, h);
      hbox.setAlignment(Pos.TOP_CENTER);


      Stage finalStage = new Stage();
      finalStage.setTitle("Vaccination Rates");
      finalStage.setScene(new Scene(hbox, 200, 100));
      finalStage.show();
   }

}

