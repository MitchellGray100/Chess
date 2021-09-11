package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	private Pane root = new Pane();
	private Tile[][] tileBoard = new Tile[8][8];

	private Parent createContent() {
		root.setPrefSize(800, 800);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Tile tile;
				if ((i + j) % 2 == 0) {
					tile = new Tile(Color.WHITE);
				} else {
					tile = new Tile(Color.BLACK);
				}

				tile.setTranslateX(j * 100);
				tile.setTranslateY(i * 100);
				tileBoard[i][j] = tile;
				root.getChildren().add(tile);
			}
		}
		return root;
	}

	private class Tile extends StackPane {
		private Text text = new Text();

		public Tile(Color color) {
			Rectangle border = new Rectangle(100, 100);
			border.setFill(color);
			border.setStroke(Color.BLACK);
			text.setFont(Font.font(84));
			setAlignment(Pos.CENTER);
			getChildren().addAll(border, text);
		};

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.show();
		primaryStage.setScene(new Scene(createContent()));

	}

	public static void main(String[] args) {
		launch(args);
	}

}
