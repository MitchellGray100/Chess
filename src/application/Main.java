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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import location.LocationImpl;

public class Main extends Application {
	private Controller game = new ControllerImpl();
	private Pane root = new Pane();
	private Piece[][] pieceBoard = new Piece[8][8];
	private Tile[][] tileBoard = new Tile[8][8];
	private DropShadow borderGlow = new DropShadow();
	private boolean clicked = false;
	private int movingPieceX = -1;
	private int movingPieceY = -1;
	private int blackScore = game.getScore(piecesPackage.Piece.Color.BLACK);
	private int whiteScore = game.getScore(piecesPackage.Piece.Color.WHITE);
	private ScoreTile[] scores = new ScoreTile[2];
	private int lastMovementX = -1;
	private int lastMovementY = -1;
	private int movedPieceX = -1;
	private int movedPieceY = -1;

	private Parent createContent() {
		GridPane grid = new GridPane();
		GridPane gridWithFrame = new GridPane();
		GridPane frameWithIndexes = new GridPane();
		GridPane indexesWithScore = new GridPane();
		root.setPrefSize(1400, 1050);

		// GRID BUILDING
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

				grid.add(tile, j * 100, i * 100);
				grid.add(piece, j * 100, i * 100);
//				piece.setTranslateX(j * 100);
//				piece.setTranslateY(i * 100);
//				tile.setTranslateX(j * 100);
//				tile.setTranslateY(i * 100);
				pieceBoard[i][j] = piece;
				tileBoard[i][j] = tile;
				// root.getChildren().addAll(tile, piece);

			}
		}

		// FRAME BUILDING
		gridWithFrame.add(grid, 1, 1, 9, 9);
		for (int i = 1; i < 9; i++) {
			gridWithFrame.add(new HorizontalBorderTile(Color.GRAY), i, 0);
		}
		for (int i = 1; i < 9; i++) {
			gridWithFrame.add(new HorizontalBorderTile(Color.GRAY), i, 10);
		}
		for (int i = 1; i < 9; i++) {
			gridWithFrame.add(new VerticalBorderTile(Color.GRAY), 0, i);
		}
		for (int i = 1; i < 9; i++) {
			gridWithFrame.add(new VerticalBorderTile(Color.GRAY), 10, i);
		}

		// NUMBER BUILDING

		// frameWithIndexes.add(new ScoreTile(Color.RED), 0, 0);
		for (int i = 1; i < 9; i++) {
			frameWithIndexes.add(new LeftTextTile(Integer.toString(i)), 0, i + 1);
		}
		int counter = 1;
		for (int i = 104; i >= 97; i--) {
			frameWithIndexes.add(new TopTextTile(Character.toString(i)), counter, 0);
			counter++;
		}
		frameWithIndexes.add(gridWithFrame, 1, 1, 9, 9);
		// frameWithIndexes.setGridLinesVisible(true);
		frameWithIndexes.setAlignment(Pos.CENTER_LEFT);

		// SCORE BUILDING
		scores[0] = new ScoreTile(Color.GRAY, "Black Score: " + blackScore);
		scores[1] = new ScoreTile(Color.GRAY, "White Score: " + whiteScore);
		indexesWithScore.add(frameWithIndexes, 0, 0, 2, 2);
		indexesWithScore.add(scores[0], 2, 4);
		indexesWithScore.add(scores[1], 2, 0);
		// indexesWithScore.setGridLinesVisible(true);
		root.getChildren().addAll(indexesWithScore);
		drawBoardPieces();
		return root;
	}

	private class Tile extends StackPane {
		private Color color;
		private Rectangle border = new Rectangle(100, 100);
		private Circle indicator = new Circle(50, 50, 20, null);

		public Tile(Color color) {
			this.color = color;
			border.setFill(color);
			border.setStroke(Color.WHITE);
			indicator.setFill(Color.RED);
			setAlignment(Pos.CENTER);
			getChildren().addAll(border, indicator);
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);
			borderGlow.setColor(Color.DARKGREEN);
			borderGlow.setWidth(110);
			borderGlow.setHeight(110);

		};
	}

	private class HorizontalBorderTile extends StackPane {

		public HorizontalBorderTile(Color color) {
			Rectangle border = new Rectangle(100, 10);
			border.setFill(color);
			border.setStroke(Color.SADDLEBROWN);
			setAlignment(Pos.TOP_CENTER);
			getChildren().addAll(border);
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);
			borderGlow.setColor(Color.DARKGREEN);
			borderGlow.setWidth(110);
			borderGlow.setHeight(110);

		};
	}

	private class VerticalBorderTile extends StackPane {

		public VerticalBorderTile(Color color) {
			Rectangle border = new Rectangle(10, 100);
			border.setFill(color);
			border.setStroke(Color.SADDLEBROWN);
			setAlignment(Pos.CENTER_RIGHT);
			getChildren().addAll(border);
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);
			borderGlow.setColor(Color.DARKGREEN);
			borderGlow.setWidth(110);
			borderGlow.setHeight(110);

		};
	}

	private class ScoreTile extends StackPane {
		private Text score = new Text();

		public ScoreTile(Color color, String input) {
			Rectangle border = new Rectangle(450, 100);
			score.setText(input);
			score.setX(this.getLayoutX());
			score.setY(this.getLayoutY());
			score.setFont(Font.font(40));
			border.setFill(color);
			border.setStroke(Color.RED);
			setAlignment(Pos.CENTER);

			getChildren().addAll(border, score);

		};
	}

	private class LeftTextTile extends StackPane {
		private Text text = new Text();

		public LeftTextTile(String textInput) {
			Rectangle border = new Rectangle(100, 100);
			border.setFill(null);
			border.setStroke(Color.WHITE);
			text.setText(textInput);
			text.setFont(Font.font(60));
			text.setX(this.getLayoutX());
			text.setY(this.getLayoutY());
			setAlignment(Pos.CENTER_RIGHT);
			getChildren().addAll(border, text);

		};
	}

	private class TopTextTile extends StackPane {
		private Text text = new Text();

		public TopTextTile(String textInput) {
			Rectangle border = new Rectangle(100, 100);
			border.setFill(null);
			border.setStroke(Color.WHITE);
			text.setText(textInput);
			text.setFont(Font.font(60));
			text.setX(this.getLayoutX());
			text.setY(this.getLayoutY());
			setAlignment(Pos.BASELINE_CENTER);
			getChildren().addAll(border, text);

		};
	}

	private class Piece extends StackPane {
		private Text text = new Text();
		private LocationImpl location;
		private Color color;
		private Circle indicator = new Circle(50, 50, 20, null);

		public Piece(Color color, LocationImpl location) {
			this.color = color;
			this.location = location;
			Rectangle border = new Rectangle(100, 100);

//			indicator.setFill(Color.GREEN);
			border.setFill(color);
//			border.setStroke(null);
			text.setFont(Font.font(12));
			text.setX(this.getLayoutX());
			text.setY(this.getLayoutY());
			setAlignment(Pos.CENTER);
			getChildren().addAll(border, indicator, text);
//			borderGlow.setOffsetY(0f);
//			borderGlow.setOffsetX(0f);
//			borderGlow.setColor(Color.DARKGREEN);
//			borderGlow.setWidth(110);
//			borderGlow.setHeight(110);

			setOnMouseClicked(event -> {

				if (game.getTurns() % 2 != 0) {
					if (event.getButton() == MouseButton.PRIMARY) {

						if (!clicked && game.squareInfo(location.getXAxis(), location.getYAxis()) != null
								&& game.squareInfo(location.getXAxis(), location.getYAxis())
										.getColor() != piecesPackage.Piece.Color.WHITE) {
							displayValidMoves(location);
							movingPieceX = location.getXAxis();
							movingPieceY = location.getYAxis();
							clicked = true;
						} else if (clicked
								&& game.isValidMove(movingPieceX, movingPieceY, location.getXAxis(),
										location.getYAxis()).toBoolean()
								&& !game.putsKingInCheck(movingPieceX, movingPieceY, location.getXAxis(),
										location.getYAxis())) {
							game.move(movingPieceX, movingPieceY, location.getXAxis(), location.getYAxis());
							game.setSquareInfo(movingPieceX, movingPieceY, null);
//							pieceBoard[location.getXAxis()][location
//									.getYAxis()] = pieceBoard[movingPieceX][movingPieceY];
//						pieceBoard[location.getXAxis()][location.getYAxis()].setEffect(null);
//							pieceBoard[movingPieceX][movingPieceY] = new Piece(
//									pieceBoard[movingPieceX][movingPieceY].color,
//									new LocationImpl(movingPieceX, movingPieceY));
							updateScores();
							drawBoardPieces();
							removeValidMoves();
							removeLastPieceColor();
							movedPieceX = location.getXAxis();
							movedPieceY = location.getYAxis();
							lastMovementX = movingPieceX;
							lastMovementY = movingPieceY;
							lastPieceColor();
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
					LocationImpl[] returner = game.aiMoveReturn(piecesPackage.Piece.Color.WHITE);
					removeLastPieceColor();
					movedPieceX = returner[0].getXAxis();
					movedPieceY = returner[0].getYAxis();
					lastMovementX = returner[1].getXAxis();
					lastMovementY = returner[1].getYAxis();
					lastPieceColor();
					game.aiMove(piecesPackage.Piece.Color.WHITE);
					updateScores();
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
					pieceBoard[r][c].text.setText("");
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
					pieceBoard[r][c].text.setStroke(Color.PURPLE);
				}
			}

		}
	}

	public void removeValidMoves() {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				{
					pieceBoard[r][c].indicator.setFill(null);
				}
			}
		}
	}

	public void lastPieceColor() {
		pieceBoard[lastMovementX][lastMovementY].indicator.setFill(Color.YELLOW);
		pieceBoard[movedPieceX][movedPieceY].indicator.setFill(Color.YELLOW);
	}

	public void removeLastPieceColor() {
		if (lastMovementX != -1 && lastMovementY != -1 && movedPieceX != -1 && movedPieceY != -1) {
			pieceBoard[lastMovementX][lastMovementY].indicator.setFill(null);
			pieceBoard[movedPieceX][movedPieceY].indicator.setFill(null);
		}
	}

	public void updateScores() {
		scores[0].score.setText("Black Score: " + Integer.toString(game.getScore(piecesPackage.Piece.Color.BLACK)));
		scores[1].score.setText("White Score: " + Integer.toString(game.getScore(piecesPackage.Piece.Color.WHITE)));
	}

	public void displayValidMoves(LocationImpl location) {
		int x = location.getXAxis();
		int y = location.getYAxis();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (game.isValidMove(x, y, r, c).toBoolean() && !game.putsKingInCheck(x, y, r, c)) {
					pieceBoard[r][c].indicator.setFill(Color.LIME);
					;
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
