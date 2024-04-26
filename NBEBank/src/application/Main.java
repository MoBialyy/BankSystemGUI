package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			//Stage stage1=new Stage();
			//Group root = new Group();
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene= new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image icon = new Image("images.png");
			stage.setTitle("NBE App");
			stage.getIcons().add(icon);
		
			stage.setScene(scene);
			stage.show();
			/*stage.setOnCloseRequest(event -> {
				event.consume();
				logout(stage);
			});*/
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*public void logout(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to logout!");
		alert.setContentText("Would you like to save?");
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
		}
	}*/
	
	
	
}

//stages
/*
 * stage.setWidth(420); stage.setHeight(420); stage.setResizable(false);
 * //stage.setX(50); //stage.setY(50); stage.setFullScreen(true);
 * stage.setFullScreenExitHint("YOU CANT GET OUT UNLESS YOU PRESSE ONE");
 * stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("e"));
 * Text text = new Text();
		text.setText("a7a!!!");
		text.setX(100);
		text.setY(100);
		text.setFont(Font.font("verdana",50));
		text.setFill(Color.BLUEVIOLET);
		root.getChildren().add(text);
		
		Line line = new Line();
		line.setStartX(95);
		line.setStartY(110);
		line.setEndX(280);
		line.setEndY(110);
		line.setStrokeWidth(5);
		line.setStroke(Color.DARKKHAKI);
		line.setOpacity(0.8);
		//line.setRotate(45);
		root.getChildren().add(line);
		
		Rectangle rect = new Rectangle();
		rect.setX(95);
		rect.setY(200);
		rect.setWidth(300);
		rect.setHeight(200);
		rect.setFill(Color.BLANCHEDALMOND);
		rect.setStroke(Color.SILVER);
		rect.setStrokeWidth(5);
		root.getChildren().add(rect);
		
		Polygon tri1 = new Polygon();
		tri1.getPoints().setAll(
				99.0,205.0,
				245.0,300.0,
				99.0,300.0
				);
		tri1.setFill(Color.PERU);
		root.getChildren().add(tri1);
		
		Polygon tri2 = new Polygon();
		tri2.getPoints().setAll(
				390.0,205.0,
				245.0,300.0,
				390.0,300.0
				);
		tri2.setFill(Color.PERU);
		root.getChildren().add(tri2);
		
		Circle circle = new Circle();
		circle.setCenterX(245);
		circle.setCenterY(300);
		circle.setRadius(10);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLUE);
		circle.setStrokeWidth(2);
		root.getChildren().add(circle);
		
		ImageView imgview = new ImageView(icon);
		imgview.setX(220);
		imgview.setY(300);
		imgview.setFitWidth(50);
		imgview.setFitHeight(50);
		root.getChildren().add(imgview);
 */