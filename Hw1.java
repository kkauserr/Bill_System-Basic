package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Hw1 extends Application 
{
    final double ES_PRICE = 7.99;
    final double CS_PRICE = 9.99;
    final double BAGEL_PRICE = 2.50;
    final double PS_PRICE = 4.49;
    final double BT_PRICE = 1.25;
    final double GT_PRICE = 0.99;
    final double COFFEE_PRICE = 1.99;
    final double OJ_PRICE = 2.25;
    double total = 0;
    @Override
    public void start(Stage primaryStage) 
    {
        BorderPane brdrPane = new BorderPane();
        VBox eatBox = new VBox(30);
        eatBox.setPadding(new Insets(40));
        Label eatLabel = new Label("Eat:");
        CheckBox eggSNDW = new CheckBox("Egg Sandwich");
        CheckBox chickenSNDW = new CheckBox("Chicken Sandwich");
        CheckBox bagel = new CheckBox("Bagel");
        CheckBox potatoSLD = new CheckBox("Potato salad");
        eatBox.getChildren().addAll(eatLabel,eggSNDW, chickenSNDW, bagel, potatoSLD);
        VBox drinksBox = new VBox(30);
        drinksBox.setPadding(new Insets(40));
        Label drinksLabel = new Label("Drink:");
        ToggleGroup drinksGroup = new ToggleGroup();
        RadioButton blackTea = new RadioButton("Black Tea");
        blackTea.setToggleGroup(drinksGroup);
        RadioButton greenTea = new RadioButton("Green Tea");
        greenTea.setToggleGroup(drinksGroup);
        RadioButton coffee = new RadioButton("Coffee");
        coffee.setToggleGroup(drinksGroup);
        RadioButton oJ = new RadioButton("Orange Juice");
        oJ.setToggleGroup(drinksGroup);
        drinksBox.getChildren().addAll(drinksLabel, blackTea, greenTea, coffee, oJ);
        HBox menuBox = new HBox(10); 
        menuBox.getChildren().addAll(eatBox, drinksBox);
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        Button orderBTN = new Button("Order");
        Button cancelBTN = new Button("Cancel");
        Button confirmBTN = new Button("Confirm");
        buttonBox.getChildren().addAll(orderBTN, cancelBTN, confirmBTN);
        TextArea billTXT = new TextArea();
        billTXT.setEditable(false);
        billTXT.setPrefHeight(100);
        TitledPane billPane = new TitledPane("Bill", billTXT);
        billPane.setCollapsible(false);
        billPane.setPrefWidth(250);
        billPane.setPrefHeight(250);
        brdrPane.setLeft(menuBox);
        brdrPane.setBottom(buttonBox);
        brdrPane.setRight(billPane);
        orderBTN.setOnAction(e -> {
        	total = 0;
            if (eggSNDW.isSelected()) 
            	total += ES_PRICE;
            if (chickenSNDW.isSelected()) 
            	total += CS_PRICE;
            if (bagel.isSelected()) 
            	total += BAGEL_PRICE;
            if (potatoSLD.isSelected()) 
            	total += PS_PRICE;
            RadioButton selectedDrink = (RadioButton) drinksGroup.getSelectedToggle();
            if (selectedDrink != null) 
            {
                switch (selectedDrink.getText()) 
                {
                    case "Black Tea": total += BT_PRICE; 
                    break;
                    case "Green Tea": total += GT_PRICE; 
                    break;
                    case "Coffee": total += COFFEE_PRICE; 
                    break;
                    case "Orange Juice": total += OJ_PRICE; 
                    break;
                }
            }
            billTXT.setText("Ordered Items:\n" + getSLCTDitems(eatBox, drinksBox) + "Total: $" +(total));
        });
        cancelBTN.setOnAction(e -> {
            clearSLCTNS(eatBox, drinksBox, drinksGroup);
            billTXT.clear();
            total = 0;
        });
        confirmBTN.setOnAction(e -> {
            double tax = total * 0.07;
            double totalWithTax = total + tax;
            billTXT.setText("Order Items:\n" + getSLCTDitems(eatBox, drinksBox) + "Items Total: $" + (total) +
            "\nTotal: $" + String.format("%.2f",totalWithTax));
            clearSLCTNS(eatBox, drinksBox, drinksGroup);
            total=0;
        });
        Scene scene = new Scene(brdrPane, 600, 400);
        primaryStage.setTitle("Joe's Deli");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void clearSLCTNS(VBox eatBox, VBox drinksBox, ToggleGroup drinksGroup) 
    {
        for (Node node : eatBox.getChildren()) 
        {
            if (node instanceof CheckBox) 
            {
                ((CheckBox) node).setSelected(false);
            }
        }
        for (Node node : drinksBox.getChildren()) 
        {
            if (node instanceof RadioButton) 
            { 
                ((RadioButton) node).setSelected(false);
            }
        }
        if (drinksGroup.getSelectedToggle() != null) 
        {
            drinksGroup.getSelectedToggle().setSelected(false);
        }
    }
    private String getSLCTDitems(VBox eatBox, VBox drinksBox) 
    {
        StringBuilder items = new StringBuilder();
        for (Node node : eatBox.getChildren()) 
        {
            if (node instanceof CheckBox) 
            { 
                CheckBox cb = (CheckBox) node;
                if (cb.isSelected()) 
                {
                    items.append(cb.getText());
                    items.append("\n");
                }
            }
        }
        for (Node node : drinksBox.getChildren()) 
        {
            if (node instanceof RadioButton) 
            { 
                RadioButton rb = (RadioButton) node;
                if (rb.isSelected()) 
                {
                    items.append(rb.getText());
                    items.append("\n");
                }
            }
        }
        return items.toString();
    }
    public static void main(String[] args) 
    {
        launch();
    }
}

