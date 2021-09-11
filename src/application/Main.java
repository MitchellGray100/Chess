package application;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import location.LocationImpl;

public class Main extends Application {
	private Controller game = new ControllerImpl();
	private Pane root = new Pane();
	private Piece[][] pieceBoard = new Piece[8][8];
	private DropShadow borderGlow = new DropShadow();
	private boolean clicked = false;
	private int movingPieceX = -1;
	private int movingPieceY = -1;

	private Parent createContent() {
		GridPane grid = new GridPane();
		root.setPrefSize(800, 800);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece;
				Tile tile;
				if ((i + j) % 2 == 0) {
					piece = new Piece(Color.WHITE, new LocationImpl(i, j));
					tile = new Tile(Color.WHITE);
				} else {
					piece = new Piece(Color.BLACK, new LocationImpl(i, j));
					tile = new Tile(Color.BLACK);
				}

				grid.add(piece, i, j);

				grid.add(tile, i, j);
				piece.setTranslateX(j * 100);
				piece.setTranslateY(i * 100);
				tile.setTranslateX(j * 100);
				tile.setTranslateY(i * 100);
				pieceBoard[i][j] = piece;
				root.getChildren().addAll(tile, piece);

			}
		}
		drawBoardPieces();
		return root;
	}

	private class Tile extends StackPane {
		private Color color;

		public Tile(Color color) {
			this.color = color;
			Rectangle border = new Rectangle(100, 100);
			border.setFill(color);
			border.setStroke(null);
			setAlignment(Pos.CENTER);
			getChildren().addAll(border);
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);
			borderGlow.setColor(Color.DARKGREEN);
			borderGlow.setWidth(110);
			borderGlow.setHeight(110);

		};
	}

	private class Piece extends StackPane {
		private Text text = new Text();
		private LocationImpl location;
		private Color color;

		public Piece(Color color, LocationImpl location) {
			this.color = color;
			this.location = location;
			Rectangle border = new Rectangle(100, 100);
			border.setFill(color);
			border.setStroke(null);
			text.setFont(Font.font(12));
			text.setX(this.getLayoutX());
			text.setY(this.getLayoutY());
			setAlignment(Pos.CENTER);
			getChildren().addAll(border, text);
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);
			borderGlow.setColor(Color.DARKGREEN);
			borderGlow.setWidth(110);
			borderGlow.setHeight(110);

			setOnMouseClicked(event -> {

				if (game.getTurns() % 2 != 0) {
					if (event.getButton() == MouseButton.PRIMARY) {

						if (!clicked && game.squareInfo(location.getXAxis(), location.getYAxis()) != null) {
							displayValidMoves(location);
							movingPieceX = location.getXAxis();
							movingPieceY = location.getYAxis();
							clicked = true;
						} else if (clicked && game
								.isValidMove(movingPieceX, movingPieceY, location.getXAxis(), location.getYAxis())
								.toBoolean()) {
							game.move(movingPieceX, movingPieceY, location.getXAxis(), location.getYAxis());

//						pieceBoard[location.getXAxis()][location.getYAxis()] = pieceBoard[movingPieceX][movingPieceY];
//						pieceBoard[location.getXAxis()][location.getYAxis()].setEffect(null);
//						pieceBoard[movingPieceX][movingPieceY] = new Piece(pieceBoard[movingPieceX][movingPieceY].color,
//								new LocationImpl(movingPieceX, movingPieceY));

							drawBoardPieces();
							removeValidMoves();
							clicked = false;
							movingPieceX = -1;
							movingPieceY = -1;
							game.incrementTurns();
						} else {
							removeValidMoves();
							clicked = false;
							movingPieceX = -1;
							movingPieceY = -1;
						}
					} else if (event.getButton() == MouseButton.SECONDARY) {
						removeValidMoves();
						clicked = false;
						movingPieceX = -1;
						movingPieceY = -1;
					}
				} else {
					game.aiMove(piecesPackage.Piece.Color.WHITE);
					drawBoardPieces();
					game.incrementTurns();
				}
			});
		};

	}

	public void drawBoardPieces() {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				try {
					switch (game.squareInfo(r, c).getType()) {
					case BISHOP:
						pieceBoard[r][c].text.setText("Bishop");
						break;
					case KING:
						pieceBoard[r][c].text.setText("King");
						break;
					case KNIGHT:
						pieceBoard[r][c].text.setText("Knight");
						break;
					case PAWN:
						pieceBoard[r][c].text.setText("Pawn");
						break;
					case QUEEN:
						pieceBoard[r][c].text.setText("Queen");
						break;
					case ROOK:
						pieceBoard[r][c].text.setText("Rook");
						break;
					default:
						pieceBoard[r][c].text.setText("");
						break;
					}
				} catch (NullPointerException ex) {

				}
				try {
					switch (game.squareInfo(r, c).getColor()) {
					case BLACK:
						pieceBoard[r][c].text.setStroke(Color.RED);
						break;
					case WHITE:
						pieceBoard[r][c].text.setStroke(Color.BLUE);
						break;
					default:
						break;

					}
				} catch (NullPointerException ex) {

				}
			}

		}
	}

	public void removeValidMoves() {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				{
					pieceBoard[r][c].setEffect(null);
				}
			}
		}
	}

	public void displayValidMoves(LocationImpl location) {
		int x = location.getXAxis();
		int y = location.getYAxis();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (game.isValidMove(x, y, r, c).toBoolean() && !game.putsKingInCheck(x, y, r, c)) {
					pieceBoard[r][c].setEffect(borderGlow);
				}
			}
		}
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
