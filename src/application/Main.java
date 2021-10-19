package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.Controller;
import controller.ControllerImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import location.LocationImpl;

public class Main extends Application {
	private Controller game = new ControllerImpl();
	private Pane root = new Pane();
	private Piece[][] pieceBoard = new Piece[8][8];
	private Tile[][] tileBoard = new Tile[8][8];
	private PromotionTile[] promotionBoard = new PromotionTile[4];
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
	private Image blackBishopImage;
	private Image whiteBishopImage;
	private Image blackKnightImage;
	private Image whiteKnightImage;
	private Image blackRookImage;
	private Image whiteRookImage;
	private Image blackPawnImage;
	private Image whitePawnImage;
	private Image blackQueenImage;
	private Image whiteQueenImage;
	private Image blackKingImage;
	private Image whiteKingImage;
	private Image image;
	private boolean debugging = true;
	private final Object PAUSE_KEY = new Object();
	private boolean endGame = false;
	private boolean clickedPromotion = true;
	private PromotionTile knightTile;
	private PromotionTile bishopTile;
	private PromotionTile queenTile;
	private PromotionTile rookTile;
	private piecesPackage.Piece.Type promotionPieceType = piecesPackage.Piece.Type.QUEEN;
	private Text endGameText = new Text();

	private Parent createContent(Stage primaryStage) throws FileNotFoundException {
		knightTile = new PromotionTile(blackKnightImage, piecesPackage.Piece.Type.KNIGHT, primaryStage);
		bishopTile = new PromotionTile(blackBishopImage, piecesPackage.Piece.Type.BISHOP, primaryStage);
		queenTile = new PromotionTile(blackQueenImage, piecesPackage.Piece.Type.QUEEN, primaryStage);
		rookTile = new PromotionTile(blackRookImage, piecesPackage.Piece.Type.ROOK, primaryStage);
		AnchorPane anchor = new AnchorPane();
		knightTile.imageView.setFitHeight(100);
		knightTile.imageView.setFitWidth(100);
		bishopTile.imageView.setFitHeight(100);
		bishopTile.imageView.setFitWidth(100);
		queenTile.imageView.setFitHeight(100);
		queenTile.imageView.setFitWidth(100);
		rookTile.imageView.setFitHeight(100);
		rookTile.imageView.setFitWidth(100);
		blackBishopImage = new Image(new FileInputStream("src/Black Bishop.png"));
		whiteBishopImage = new Image(new FileInputStream("src/White Bishop.png"));
		whiteKnightImage = new Image(new FileInputStream("src/White Knight.png"));
		blackRookImage = new Image(new FileInputStream("src/Black Rook.png"));
		whiteRookImage = new Image(new FileInputStream("src/White Rook.png"));
		blackPawnImage = new Image(new FileInputStream("src/Black Pawn.png"));
		whitePawnImage = new Image(new FileInputStream("src/White Pawn.png"));
		blackQueenImage = new Image(new FileInputStream("src/Black Queen.png"));
		whiteQueenImage = new Image(new FileInputStream("src/White Queen.png"));
		blackKingImage = new Image(new FileInputStream("src/Black King.png"));
		whiteKingImage = new Image(new FileInputStream("src/White King.png"));
		GridPane grid = new GridPane();
		GridPane gridWithFrame = new GridPane();
		GridPane frameWithIndexes = new GridPane();
		GridPane indexesWithScore = new GridPane();
		grid.setCache(true);
		gridWithFrame.setCache(true);
		frameWithIndexes.setCache(true);
		indexesWithScore.setCache(true);
		grid.setCacheHint(CacheHint.SPEED);
		gridWithFrame.setCacheHint(CacheHint.SPEED);
		frameWithIndexes.setCacheHint(CacheHint.SPEED);
		indexesWithScore.setCacheHint(CacheHint.SPEED);
		// GRID BUILDING
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece;
				Tile tile;
				if ((i + j) % 2 == 0) {
					piece = new Piece(Color.WHITE, new LocationImpl(i, j), primaryStage);
					tile = new Tile(Color.WHITE, primaryStage);
				} else {
					piece = new Piece(Color.BLACK, new LocationImpl(i, j), primaryStage);
					tile = new Tile(Color.BLACK, primaryStage);
				}

				tile.setCache(true);
				tile.setCacheHint(CacheHint.SPEED);
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
			gridWithFrame.add(new HorizontalBorderTile(Color.GRAY, primaryStage), i, 0);
		}
		for (int i = 1; i < 9; i++) {
			gridWithFrame.add(new HorizontalBorderTile(Color.GRAY, primaryStage), i, 10);
		}
		for (int i = 1; i < 9; i++) {
			gridWithFrame.add(new VerticalBorderTile(Color.GRAY, primaryStage), 0, i);
		}
		for (int i = 1; i < 9; i++) {
			gridWithFrame.add(new VerticalBorderTile(Color.GRAY, primaryStage), 10, i);
		}

		// NUMBER BUILDING

		// frameWithIndexes.add(new ScoreTile(Color.RED), 0, 0);
		for (int i = 1; i < 9; i++) {
			frameWithIndexes.add(new LeftTextTile(Integer.toString(i), primaryStage), 0, i + 1);
		}
		int counter = 1;
		for (int i = 104; i >= 97; i--) {
			frameWithIndexes.add(new TopTextTile(Character.toString(i), primaryStage), counter, 0);
			counter++;
		}
		frameWithIndexes.add(gridWithFrame, 1, 1, 9, 9);
		// frameWithIndexes.setGridLinesVisible(true);
		frameWithIndexes.setAlignment(Pos.CENTER_LEFT);

		// SCORE BUILDING
		scores[0] = new ScoreTile(Color.GRAY, "Black Score: " + blackScore, primaryStage);
		scores[1] = new ScoreTile(Color.GRAY, "White Score: " + whiteScore, primaryStage);
		indexesWithScore.add(frameWithIndexes, 0, 0, 2, 2);
		indexesWithScore.add(scores[0], 3, 4);
		indexesWithScore.add(scores[1], 3, 0);

		// PROMOTION BUILDING
		GridPane promotionGrid = new GridPane();
//		promotionGrid.setGridLinesVisible(true);
		promotionGrid.add(queenTile, 1, 1);
		promotionGrid.add(rookTile, 1, 2);
		promotionGrid.add(bishopTile, 1, 3);
		promotionGrid.add(knightTile, 1, 4);
		promotionBoard[0] = queenTile;
		promotionBoard[1] = rookTile;
		promotionBoard[2] = bishopTile;
		promotionBoard[3] = knightTile;
		setPromotionImages();
		removePromotionColors();
		promotionBoard[0].border.setFill(Color.GRAY);
		VBox rightHolder = new VBox();
		rightHolder.prefHeightProperty().bind(primaryStage.heightProperty().multiply(.95));
		rightHolder.getChildren().addAll(scores[1], promotionGrid, scores[0]);
		rightHolder.setAlignment(Pos.CENTER_LEFT);
		indexesWithScore.add(rightHolder, 3, 0, 3, 4);
		// endGameText.setText("STALEMATE. Tie Game");
		endGameText.setFont(new Font(100));
		endGameText.setTextAlignment(TextAlignment.CENTER);
		endGameText.scaleXProperty().bind(primaryStage.widthProperty().multiply(.0005));
		endGameText.scaleYProperty().bind(primaryStage.heightProperty().multiply(.001));
		endGameText.translateXProperty().bind(primaryStage.widthProperty().divide(10));
		endGameText.translateYProperty().bind(primaryStage.heightProperty().divide(36));
		indexesWithScore.add(endGameText, 0, 0, 2, 2);
		endGameText.setFill(Color.RED);
		promotionGrid.setAlignment(Pos.CENTER);
		// indexesWithScore.setGridLinesVisible(true);
		LocationImpl[] returner = game.aiMoveReturn(piecesPackage.Piece.Color.WHITE);
		movedPieceX = returner[0].getXAxis();
		movedPieceY = returner[0].getYAxis();
		lastMovementX = returner[1].getXAxis();
		lastMovementY = returner[1].getYAxis();
		lastPieceColor();
		game.aiMove(piecesPackage.Piece.Color.WHITE);
		anchor.getChildren().addAll(indexesWithScore);
//		anchor.prefWidthProperty().bind(primaryStage.widthProperty());
//		anchor.prefHeightProperty().bind(primaryStage.heightProperty());
		root.getChildren().addAll(anchor);
		updateScores();
		drawBoardPieces();
		game.incrementTurns();
		drawBoardPieces();
		return root;
	}

	private class Tile extends StackPane {
		private Color color;
		private Rectangle border = new Rectangle(100, 100);
		// private Circle indicator = new Circle(50, 50, 20, null);

		public Tile(Color color, Stage primaryStage) {

			border.widthProperty().bind(primaryStage.widthProperty().multiply(.08));

			border.heightProperty().bind(primaryStage.heightProperty().multiply(.1));
			this.color = color;
			border.setFill(color);
			border.setStroke(Color.WHITE);
			// indicator.setFill(Color.RED);
			setAlignment(Pos.CENTER);
			getChildren().addAll(border);
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);
			borderGlow.setColor(Color.DARKGREEN);
			borderGlow.setWidth(110);
			borderGlow.setHeight(110);

		};
	}

	private class HorizontalBorderTile extends StackPane {

		public HorizontalBorderTile(Color color, Stage primaryStage) {
			Rectangle border = new Rectangle(100, 10);
			border.widthProperty().bind(primaryStage.widthProperty().multiply(.08));
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

		public VerticalBorderTile(Color color, Stage primaryStage) {
			Rectangle border = new Rectangle(10, 100);
			border.heightProperty().bind(primaryStage.heightProperty().multiply(.1));
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

		public ScoreTile(Color color, String input, Stage primaryStage) {
			score.setTextAlignment(TextAlignment.CENTER);
			score.scaleXProperty().bind(primaryStage.widthProperty().multiply(.0008));
			score.scaleYProperty().bind(primaryStage.heightProperty().multiply(.002));
			score.translateXProperty().bind(primaryStage.widthProperty().divide(20));
			Rectangle border = new Rectangle(450, 100);
			border.widthProperty().bind(primaryStage.widthProperty().multiply(.3));

			border.heightProperty().bind(primaryStage.heightProperty().multiply(.1));
			score.setText(input);
			score.setX(this.getLayoutX());
			score.setY(this.getLayoutY());
			score.setFont(Font.font(40));
			border.setFill(color);
			border.setStroke(Color.RED);
			setAlignment(Pos.CENTER_LEFT);

			getChildren().addAll(border, score);

		};
	}

	private class LeftTextTile extends StackPane {
		private Text text = new Text();

		public LeftTextTile(String textInput, Stage primaryStage) {

			Rectangle border = new Rectangle(10, 100);
			text.scaleXProperty().bind(primaryStage.widthProperty().multiply(.001));
			text.scaleYProperty().bind(primaryStage.heightProperty().multiply(.001));
			border.widthProperty().bind(primaryStage.widthProperty().multiply(.03));

			border.heightProperty().bind(primaryStage.heightProperty().multiply(.1));
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

		public TopTextTile(String textInput, Stage primaryStage) {
			Rectangle border = new Rectangle(100, 100);

			text.scaleXProperty().bind(primaryStage.widthProperty().multiply(.001));
			text.scaleYProperty().bind(primaryStage.heightProperty().multiply(.0010));
			border.widthProperty().bind(primaryStage.widthProperty().multiply(.08));

			border.heightProperty().bind(primaryStage.heightProperty().multiply(.04));
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

	private class PromotionTile extends StackPane {
		// private Text text = new Text();
		private ImageView imageView = new ImageView(image);
		private piecesPackage.Piece.Type type;
		private Rectangle border = new Rectangle(200, 200);

		public PromotionTile(Image pieceImage, piecesPackage.Piece.Type type, Stage primaryStage) {

			border.widthProperty().bind(primaryStage.widthProperty().multiply(.15));

			border.heightProperty().bind(primaryStage.heightProperty().multiply(.18));
			this.type = type;
			imageView = new ImageView(pieceImage);
			imageView.setImage(pieceImage);
			imageView.setX(this.getLayoutX());
			imageView.setY(this.getLayoutY());
			imageView.setImage(pieceImage);
			imageView.setFitHeight(100);
			imageView.setFitWidth(100);
			// this.color = color;
			// this.location = location;
			border.setFill(Color.WHITE);
			border.setStroke(Color.BLACK);
//			indicator.setFill(Color.GREEN);

//			border.setStroke(null);
//			text.setFont(Font.font(12));
//			text.setX(this.getLayoutX());
//			text.setY(this.getLayoutY());
			setAlignment(Pos.CENTER);
			getChildren().addAll(border, imageView);

			setOnMouseClicked(event -> {
				clickedPromotion = true;
				if (event.getButton() == MouseButton.PRIMARY) {
					removePromotionColors();
					border.setFill(Color.GRAY);
					promotionPieceType = this.type;
					if (Platform.isNestedLoopRunning()) {
						clickedPromotion = false;
						Platform.exitNestedEventLoop(PAUSE_KEY, null);
					}
				}
			});
//			setOnMouseMoved(event -> {
//				if (Platform.isNestedLoopRunning() && clickedPromotion == true) {
//					clickedPromotion = false;
//					Platform.exitNestedEventLoop(PAUSE_KEY, null);
//				}
//			});
		}
	}

	private class Piece extends StackPane {
		// private Text text = new Text();
		private ImageView imageView = null;
		// private LocationImpl location;
		// private Color color;
		private Circle indicator = new Circle(70, 70, 32, null);

//		private GridPane promotionGrid = new GridPane();

		public Piece(Color color, LocationImpl location, Stage primaryStage) {
			indicator.scaleXProperty().bind(primaryStage.widthProperty().divide(1100));

			indicator.scaleYProperty().bind(primaryStage.heightProperty().divide(900));
			imageView = new ImageView(image);
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			imageView.fitHeightProperty().bind(primaryStage.heightProperty().divide(20));
			imageView.fitWidthProperty().bind(primaryStage.widthProperty().divide(25));
			// this.color = color;
			// this.location = location;
			Rectangle border = new Rectangle(100, 100);
			border.widthProperty().bind(primaryStage.widthProperty().multiply(.08));

			border.heightProperty().bind(primaryStage.heightProperty().multiply(.1));
//			indicator.setFill(Color.GREEN);
			border.setFill(color);
//			border.setStroke(null);
//			text.setFont(Font.font(12));
			imageView.setX(this.getLayoutX());
			imageView.setY(this.getLayoutY());
//			text.setX(this.getLayoutX());
//			text.setY(this.getLayoutY());
			setAlignment(Pos.CENTER);
			getChildren().addAll(border, indicator, imageView);
//			borderGlow.setOffsetY(0f);
//			borderGlow.setOffsetX(0f);
//			borderGlow.setColor(Color.DARKGREEN);
//			borderGlow.setWidth(110);
//			borderGlow.setHeight(110);

			setOnMouseClicked(event -> {
				if (!endGame) {

					if (game.getTurns() % 2 != 0) {
						if (event.getButton() == MouseButton.PRIMARY) {

							if (game.isCheckmate(piecesPackage.Piece.Color.BLACK)) {
								endGameText.setText("White Won The Game!");
								endGame = true;
							} else if (game.isCheckmate(piecesPackage.Piece.Color.WHITE)) {
								endGameText.setText("Black Won The Game!");
								endGame = true;
							} else if (game.isStalemate(piecesPackage.Piece.Color.BLACK)) {
								endGameText.setText("STALEMATE. Tie Game");
								endGame = true;
							} else if (!clicked && game.squareInfo(location.getXAxis(), location.getYAxis()) != null
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
								if ((location.getXAxis() == 0 || location.getXAxis() == 7)
										&& game.squareInfo(movingPieceX, movingPieceY)
												.getType() == piecesPackage.Piece.Type.PAWN) {
									if (promotionPieceType == null) {
										Platform.enterNestedEventLoop(PAUSE_KEY);
									}
									game.move(movingPieceX, movingPieceY, location.getXAxis(), location.getYAxis(),
											promotionPieceType);

								} else {
									game.move(movingPieceX, movingPieceY, location.getXAxis(), location.getYAxis(),
											piecesPackage.Piece.Type.QUEEN);
								}
								if (debugging) {
									debugMoves(movingPieceX, movingPieceY, location.getXAxis(), location.getYAxis());
								}

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

								if (!endGame) {

									if (game.getTurns() % 2 == 0) {
										if (event.getButton() == MouseButton.PRIMARY) {
											if (game.isCheckmate(piecesPackage.Piece.Color.BLACK)) {
												endGameText.setText("White Won The Game!");
												endGame = true;
											} else if (game.isCheckmate(piecesPackage.Piece.Color.WHITE)) {
												endGameText.setText("Black Won The Game!");
												endGame = true;
											} else if (game.isStalemate(piecesPackage.Piece.Color.WHITE)) {
												endGameText.setText("STALEMATE. Tie Game");
												endGame = true;
											}
											removeLastPieceColor();
											LocationImpl[] returner = game
													.aiMoveReturn(piecesPackage.Piece.Color.WHITE);
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
									}
								}
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
						if (!endGame) {

							if (game.getTurns() % 2 == 0) {
								if (event.getButton() == MouseButton.PRIMARY) {

									if (game.isCheckmate(piecesPackage.Piece.Color.BLACK)) {
										endGameText.setText("White Won The Game!");
										endGame = true;
									} else if (game.isCheckmate(piecesPackage.Piece.Color.WHITE)) {
										endGameText.setText("Black Won The Game!");
										endGame = true;
									} else if (game.isStalemate(piecesPackage.Piece.Color.WHITE)) {
										endGameText.setText("STALEMATE. Tie Game");
										endGame = true;
									}
									removeLastPieceColor();
									LocationImpl[] returner = game.aiMoveReturn(piecesPackage.Piece.Color.WHITE);
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
							}
						}
					}
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
						try {
							switch (game.squareInfo(r, c).getColor()) {
							case BLACK:
								pieceBoard[r][c].imageView.setImage(blackBishopImage);
								break;
							case WHITE:
								pieceBoard[r][c].imageView.setImage(whiteBishopImage);
								break;
							}
						} catch (NullPointerException ex) {

						}
						break;
					case KING:
						try {
							switch (game.squareInfo(r, c).getColor()) {
							case BLACK:
								pieceBoard[r][c].imageView.setImage(blackKingImage);
								break;
							case WHITE:
								pieceBoard[r][c].imageView.setImage(whiteKingImage);
								break;
							}
						} catch (NullPointerException ex) {

						}
						break;
					case KNIGHT:
						try {
							switch (game.squareInfo(r, c).getColor()) {
							case BLACK:
								pieceBoard[r][c].imageView.setImage(blackKnightImage);
								break;
							case WHITE:
								pieceBoard[r][c].imageView.setImage(whiteKnightImage);
								break;
							}
						} catch (NullPointerException ex) {

						}
						break;
					case PAWN:

						try {
							switch (game.squareInfo(r, c).getColor()) {
							case BLACK:
								pieceBoard[r][c].imageView.setImage(blackPawnImage);
								break;
							case WHITE:
								pieceBoard[r][c].imageView.setImage(whitePawnImage);
								break;
							}
						} catch (NullPointerException ex) {

						}
						break;
					case QUEEN:
						try {
							switch (game.squareInfo(r, c).getColor()) {
							case BLACK:
								pieceBoard[r][c].imageView.setImage(blackQueenImage);
								break;
							case WHITE:
								pieceBoard[r][c].imageView.setImage(whiteQueenImage);
								break;
							}
						} catch (NullPointerException ex) {

						}
						break;
					case ROOK:
						try {
							switch (game.squareInfo(r, c).getColor()) {
							case BLACK:
								pieceBoard[r][c].imageView.setImage(blackRookImage);
								break;
							case WHITE:
								pieceBoard[r][c].imageView.setImage(whiteRookImage);
								break;
							}
						} catch (NullPointerException ex) {

						}
						break;
					default:
						pieceBoard[r][c].imageView.setImage(null);
						break;
					}
				} catch (NullPointerException ex) {
					pieceBoard[r][c].imageView.setImage(null);
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
		if (lastMovementX >= 0 && lastMovementY >= 0 && movedPieceX >= 0 && movedPieceY >= 0) {
			pieceBoard[lastMovementX][lastMovementY].indicator.setFill(Color.GOLD);
			pieceBoard[movedPieceX][movedPieceY].indicator.setFill(Color.GOLD);
		}
	}

	public void removeLastPieceColor() {
		if (lastMovementX >= 0 && lastMovementY >= 0 && movedPieceX >= 0 && movedPieceY >= 0) {
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

	public void debugMoves(int x, int y, int r, int c) {
		System.out.println("Moved From: " + (x + 1) + " " + (char) (-y + 8 + 96) + " Moved To: " + (r + 1) + " "
				+ (char) (-c + 8 + 96));
	}

	public void setPromotionImages() {
		promotionBoard[0].imageView.setImage(blackQueenImage);
		promotionBoard[1].imageView.setImage(blackRookImage);
		promotionBoard[2].imageView.setImage(blackBishopImage);
		promotionBoard[3].imageView.setImage(blackKnightImage);
	}

	public void removePromotionColors() {
		promotionBoard[0].border.setFill(Color.WHITE);
		promotionBoard[1].border.setFill(Color.WHITE);
		promotionBoard[2].border.setFill(Color.WHITE);
		promotionBoard[3].border.setFill(Color.WHITE);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		blackKnightImage = new Image(new FileInputStream("src/Black Knight.png"));
		primaryStage.getIcons().add(blackKnightImage);
		Scene scene = new Scene(createContent(primaryStage));
		primaryStage.show();
		primaryStage.setScene(scene);
		primaryStage.setMinHeight(1000);
		primaryStage.setMinWidth(1350);
		primaryStage.setHeight(1000);
		primaryStage.setWidth(1350);
	}

	public static void main(String[] args) {
		launch(args);

	}

}
