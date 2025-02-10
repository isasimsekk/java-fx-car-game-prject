// Feyza ÇELİK 150123082
// Yusuf KURT 150123078
// İsa ŞİMŞEK 150122038

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.*;

public class Traffic_control_simulator extends Application {
	private Timeline timeline;
	private AnimationTimer timer;
	double timeForSpawn;
	int carIndex = 0;
	Integer currentLevel;
	int win, lose;
	File level1 = new File("level1.txt");
	File level2 = new File("level2.txt");
	File level3 = new File("level3.txt");
	File level4 = new File("level4.txt");
	File level5 = new File("level5.txt");

	ArrayList<PathTransition> pts = new ArrayList<>();
	ArrayList<TrafficLight> lights = new ArrayList<TrafficLight>();
	ArrayList<TrafficLight> tls = new ArrayList<>();
	ArrayList<Car> cars = new ArrayList<>();
	ArrayList<Polyline> paths = new ArrayList<Polyline>();

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {

		int screenwidth = 512;
		int screenheight = 512;

		// create start-exit screen
		Text label = new Text("Welcome to");
		label.setFill(Color.CORNSILK);

		label.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 65));
		Text label2 = new Text("the Traffic Control Simulator");
		label2.setFill(Color.CORNSILK);

		label2.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 35));

		// Start-exit screen
		Button startButton = new Button("Start");
		Button exitButton = new Button("Exit");
		// BUtton customization
		startButton.setScaleX(2.3);
		startButton.setScaleY(2.3);
		startButton.setStyle(
				"-fx-background-color: #4CAF50;  -fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: Arial;");

		exitButton.setScaleX(2);
		exitButton.setScaleY(2);
		exitButton.setStyle(
				"-fx-background-color: #FF6666; -fx-text-fill: white; -fx-font-size: 13px; -fx-font-family: Arial;");

		exitButton.setOnAction(e -> {
			stage.close();
		});

		VBox menuLayout = new VBox(40);
		menuLayout.setPadding(new Insets(20));
		menuLayout.setAlignment(Pos.CENTER);
		menuLayout.getChildren().addAll(label, label2, startButton, exitButton);
		// background rectangle for easy reading
		Rectangle r = new Rectangle(500, 320);
		r.setOpacity(0.4);
		r.setFill(Color.GRAY);
		// start background
		Image backgroundImage = new Image("file:menu.jpg");
		ImageView backgroundView = new ImageView(backgroundImage);
		StackPane backgroundPane = new StackPane();
		backgroundPane.getChildren().addAll(backgroundView, r, menuLayout);

		backgroundView.setPreserveRatio(true);
		// bind the view at the center
		backgroundView.fitWidthProperty().bind(stage.widthProperty());
		backgroundView.fitHeightProperty().bind(stage.heightProperty());

		Scene menuScene = new Scene(backgroundPane, screenwidth, screenheight, Color.LIGHTBLUE);
		// level select screen and buttons
		Button level1Button = new Button("Level 1");
		level1Button.setScaleX(1.5);
		level1Button.setScaleY(1.5);
		Button level2Button = new Button("Level 2");
		level2Button.setScaleX(1.5);
		level2Button.setScaleY(1.5);
		Button level3Button = new Button("Level 3");
		level3Button.setScaleX(1.5);
		level3Button.setScaleY(1.5);
		Button level4Button = new Button("Level 4");
		level4Button.setScaleX(1.5);
		level4Button.setScaleY(1.5);
		Button level5Button = new Button("Level 5");
		level5Button.setScaleX(1.5);
		level5Button.setScaleY(1.5);
		Button backButton = new Button("Back");

		backButton.setOnAction(e -> stage.setScene(menuScene));

		VBox levelSelectLayout = new VBox(20);
		//levelSelectLayout.setPadding(new Insets(screenwidth / 2 - 30));
		levelSelectLayout.setAlignment(Pos.CENTER);
		Image backgroundImage2 = new Image("file:levels.jpg");
		ImageView backgroundView2 = new ImageView(backgroundImage2);

		levelSelectLayout.getChildren().addAll(level1Button, level2Button, level3Button, level4Button, level5Button,
				backButton);
		StackPane backgroundPane2 = new StackPane();
		backgroundPane2.getChildren().addAll(backgroundView2, levelSelectLayout);

		backgroundView2.setPreserveRatio(true);
		backgroundView2.fitWidthProperty().bind(stage.widthProperty());
		backgroundView2.fitHeightProperty().bind(stage.heightProperty());

		Scene levelSelectScene = new Scene(backgroundPane2, screenwidth, screenheight, Color.LIGHTBLUE);
		startButton.setOnAction(e -> stage.setScene(levelSelectScene));

		stage.getIcons().add(new Image("file:icon.png"));
		stage.setScene(menuScene);
		// giving actions to buttons
		level1Button.setOnAction(e -> {
			try {
				currentLevel = 1;
				Pane pane = createLevel(level1, currentLevel, stage, levelSelectScene);
				Scene scene = new Scene(pane, 800, 800, Color.LIGHTBLUE);
				stage.setScene(scene);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		level2Button.setOnAction(e -> {
			try {
				currentLevel = 2;
				Pane pane = createLevel(level2, 2, stage, levelSelectScene);
				Scene scene = new Scene(pane, 800, 800, Color.LIGHTBLUE);
				stage.setScene(scene);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		level3Button.setOnAction(e -> {
			try {
				currentLevel = 3;
				Pane pane = createLevel(level3, 3, stage, levelSelectScene);
				Scene scene = new Scene(pane, 800, 800, Color.LIGHTBLUE);
				stage.setScene(scene);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		level4Button.setOnAction(e -> {
			try {
				currentLevel = 4;
				Pane pane = createLevel(level4, 4, stage, levelSelectScene);
				Scene scene = new Scene(pane, 800, 800, Color.LIGHTBLUE);
				stage.setScene(scene);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		level5Button.setOnAction(e -> {
			try {
				currentLevel = 5;
				Pane pane = createLevel(level5, 5, stage, levelSelectScene);
				Scene scene = new Scene(pane, 800, 800, Color.LIGHTBLUE);
				stage.setScene(scene);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});

		stage.setTitle("Traffic Control Simulator");
		stage.setX(70);
		stage.setY(10);
		stage.setResizable(false);
		stage.show();
	}

	public Pane createLevel(File file, int levelNumber, Stage stage, Scene levelSelectScene)
			throws FileNotFoundException {
		// clear all things before creating new scene
		paths.clear();
		pts.clear();
		tls.clear();
		lights.clear();
		cars.clear();
		carIndex = 0;
		timeForSpawn = 0;
		Pane pane = new Pane();
		Rectangle background = new Rectangle(800, 800);
		background.setFill(Color.LIGHTBLUE);
		pane.getChildren().add(background);
		Scanner input = new Scanner(file);
		double xBuilding;
		double yBuilding;
		int buildingtype;

		// file reading
		while (input.hasNext()) {
			switch (input.next()) {
			case "Metadata":
				Metadata metadata = new Metadata(input.nextDouble(), input.nextDouble(), input.nextInt(),
						input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt());
				metadata.lines(pane);
				win = metadata.getToWin();
				lose = metadata.getToOver();
				break;
			case "RoadTile":
				RoadTile r = new RoadTile(input.nextInt(), input.nextDouble(), input.nextDouble(), input.nextDouble());
				pane.getChildren().add(r);
				break;
			case "Building":

				Building b = new Building(buildingtype = input.nextInt(), input.nextDouble(), input.nextInt(),
						xBuilding = input.nextDouble(), yBuilding = input.nextDouble());
				pane.getChildren().add(b);

				break;
			case "TrafficLight":
				TrafficLight t = new TrafficLight(input.nextDouble(), input.nextDouble(), input.nextDouble(),
						input.nextDouble());
				lights.add(t);
				tls.add(t);
				break;
			case "Path":
				Path path = new Path(input.nextInt(), input.next(), input.nextDouble(), input.nextDouble(), paths);
				break;
			}
		}
		input.close();
		// adding paths to the pane
		for (int i = 0; i < paths.size(); i++) {
			paths.get(i).setOpacity(0);
			pane.getChildren().add(paths.get(i));
		}

		// fixing the lights which are not working because of bounds overlapping
		ArrayList<Double> Xvalues = new ArrayList<Double>();
		for (int i = 0; i < lights.size(); i++) {
			Xvalues.add(lights.get(i).getX());
		}
		Xvalues.sort(null);

		for (int i = Xvalues.size() - 1; i >= 0; i--) {
			for (int j = 0; j < lights.size(); j++) {
				if (lights.get(j).getX() == Xvalues.get(i))
					if (!pane.getChildren().contains(lights.get(j))) {
						pane.getChildren().add(lights.get(j));
					}
			}
		}
		// creating an array with win lose because this valuese pass to methods by
		// references
		int[] winlose = { win, lose };
		// showing win and lose condiitons on the screen
		Label label = new Label("WIN: ");
		label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
		label.setLayoutX(80);
		Label label2 = new Label("LOSE: ");
		label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
		label2.setLayoutX(80);
		label2.setLayoutY(20);
		// Create a timeline to continuously update the value
		Timeline timelineWinLose = new Timeline(new KeyFrame(Duration.seconds(0.16), event -> {

			label.setText("WIN: " + winlose[0] + " cars should reach the target");
			label2.setText("LOSE: After " + winlose[1] + " crashes");
		}));
		timelineWinLose.setCycleCount(Timeline.INDEFINITE); // Run indefinitely

		// Start the timeline
		timelineWinLose.play();
		pane.getChildren().addAll(label, label2);

		addXbutton(pane, stage, levelSelectScene);
		createTraffic(pane, currentLevel, stage, levelSelectScene, winlose);
		return pane;
	}

	private void createTraffic(Pane pane, int levelNumber, Stage stage, Scene levelSelectScene, int[] winlose) {
		// initialize the timer
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update(pane, winlose);
				carChecks(pane, winlose);
			}
		};
		timer.start();
		// controlling whether the current level is won or lost
		controlWinLose(pane, stage, levelSelectScene, winlose);
	}

	private void update(Pane pane, int[] winlose) {
		timeForSpawn += 0.16;
		// car spawn every 2 seconds
		if (timeForSpawn > 2) {
			if (Math.random() < 0.3) {
				boolean b = Car.spawnCar(winlose, pane, cars, paths, pts, carIndex);
				if (b)
					carIndex++;
			}
			timeForSpawn = 0;
		}
	}

	private void carChecks(Pane pane, int[] winlose) {
		for (int j = 0; j < cars.size(); j++) {

			// Declarations of carJ and related Objects
			Car carJ = cars.get(j);
			Bounds checkBoxBoundsJ = carJ.getChildren().get(3).getBoundsInParent();
			Bounds tlCheckBoxBoundsJ = carJ.getChildren().get(4).getBoundsInParent();
			Bounds boundsHC1 = carJ.getChildren().get(5).getBoundsInParent();
			Bounds boundsHC2 = carJ.getChildren().get(6).getBoundsInParent();
			Circle jHC1 = (Circle) (carJ.getChildren().get(5));
			Circle jHC2 = (Circle) (carJ.getChildren().get(6));

			for (int k = 0; k < cars.size(); k++) {

				// Declarations of carK and related Objects
				Car carK = cars.get(k);
				Bounds rectangle0BoundsK = carK.getChildren().get(0).getBoundsInParent();
				Bounds rearCheckBoxBoundsK = carK.getChildren().get(7).getBoundsInParent();
				Circle kHC1 = (Circle) (carK.getChildren().get(5));
				Circle kHC2 = (Circle) (carK.getChildren().get(6));

				// This block checks for cars in front
				// Main if block responsible for checking
				if (
				// j and k are not equals and checkboxes used here intersects and they are
				// collidible
				!(j == k) && carJ.getChildren().get(3).localToScene(checkBoxBoundsJ).intersects(
						carK.getChildren().get(7).localToScene(rearCheckBoxBoundsK)) && carK.isCollidible()) {
					// marks tailed car's index in the cars arraylist
					carJ.tailedCarIndex = k;
					carJ.slowDownThenStop(pts, j);
				}

				// Else if block responsible for moving normally after slowing and stopping
				else if (carJ.tailedCarIndex >= 0) {
					Car tailedCar = cars.get(carJ.tailedCarIndex);
					Bounds boundsOfTailedCarRectangle1 = tailedCar.getChildren().get(0).getBoundsInParent();
					if (!(carJ.getChildren().get(3).localToScene(checkBoxBoundsJ).intersects(
							tailedCar.localToScene(boundsOfTailedCarRectangle1)) && tailedCar.isCollidible())) {

						carJ.unPause(pts, j);
						carJ.tailedCarIndex = -1;
					}
				}

				// This block checks for collisions
				if (
				// j and k are not equals and hitCircles intersects and they are collidible
				j != k && (jHC1.localToScene(boundsHC1).intersects(kHC1.localToScene(boundsHC1))
						|| jHC1.localToScene(boundsHC1).intersects(kHC2.localToScene(boundsHC2))
						|| jHC2.localToScene(boundsHC2).intersects(kHC1.localToScene(boundsHC1))
						|| jHC2.localToScene(boundsHC2).intersects(kHC2.localToScene(boundsHC2))) && carJ.isCollidible()
						&& carK.isCollidible()) {
					// cars' timeBooleans are used for switching functionality
					// in first case it sets cars' time1s to current time and changes booleans back
					if (carJ.isTimeBoolean1() && carK.isTimeBoolean1()) {
						carJ.time1 = System.currentTimeMillis();
						carK.time1 = System.currentTimeMillis();
						carJ.setTimeBoolean1(false);
						carK.setTimeBoolean1(false);
					}
					// both cars stop
					pts.get(j).stop();
					pts.get(k).stop();

					// in second case when half a second passes cars get "deleted" and crash count
					// increases
					// also booleans reset just for good measrue
					if (System.currentTimeMillis() - carJ.time1 >= 500
							&& System.currentTimeMillis() - carK.time1 >= 500) {
						pane.getChildren().remove(cars.get(j));
						cars.get(j).setCollidible(false);

						pane.getChildren().remove(cars.get(k));
						cars.get(k).setCollidible(false);

						carJ.setTimeBoolean1(true);
						carK.setTimeBoolean1(true);
						winlose[1]--;
					}
				}
			}
			
			
			
			//For traffic light checks
			for (int k = 0; k < tls.size(); k++) {
				TrafficLight trafficLightK = tls.get(k);
				Bounds trafficLightKLineBounds = trafficLightK.getChildren().get(0).getBoundsInParent();
				// This block checks for traffic lights work with same principle as the ones
				// above
				if (
				// checks intersections and color of the traffic light
				carJ.getChildren().get(4).localToScene(tlCheckBoxBoundsJ)
						.intersects(trafficLightK.getChildren().get(0).localToScene(trafficLightKLineBounds))
						&& ((Circle) (trafficLightK.getChildren().get(1))).getFill() == Color.RED) {
					// marks stopped light's index in the cars arraylist
					carJ.stoppedTLIndex = k;
					carJ.slowDownThenStop(pts, j);
				}
				// unpauses car if light turns green
				else if (carJ.stoppedTLIndex >= 0) {
					TrafficLight stoppedTL = tls.get(carJ.stoppedTLIndex);
					Bounds boundsOfStoppedTL = stoppedTL.getChildren().get(0).getBoundsInParent();
					if (!(carJ.getChildren().get(4).localToScene(tlCheckBoxBoundsJ)
							.intersects(stoppedTL.getChildren().get(0).localToScene(boundsOfStoppedTL))
							&& ((Circle) (stoppedTL.getChildren().get(1))).getFill() == Color.RED)) {
						carJ.unPause(pts, j);
						carJ.stoppedTLIndex = -1;
					}
				}
			}
		}
	}

	public Pane winLevel(Pane pane, Stage stage, File nextLevelFile, Scene levelSelectScene) {
		// win massage
		Text message = new Text("YOU WON THIS LEVEL !");
		message.setFont(Font.font("Arial", 40));
		message.setFill(Color.RED);
		message.setStroke(Color.BLACK);
		message.setStrokeWidth(0.5);
		Rectangle background = new Rectangle(475, 250);
		background.setFill(Color.LIGHTGREEN);
		background.setOpacity(0.5);
		background.setArcHeight(20);
		background.setArcWidth(20);

		Button nextLevelButton = new Button("NEXT LEVEL");
		nextLevelButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
		// setting the continue button to level-up
		nextLevelButton.setOnMouseClicked(e -> {
			try {
				clearPane(pane);
				Scene newLevel = new Scene(createLevel(nextLevelFile, currentLevel, stage, levelSelectScene), 800, 800,
						Color.LIGHTBLUE);
				stage.setScene(newLevel);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});

		VBox v = new VBox(20);
		v.getChildren().addAll(message, nextLevelButton);
		v.setAlignment(Pos.CENTER);
		StackPane sPane = new StackPane();
		sPane.getChildren().addAll(background, v);

		return sPane;
	}

	public Pane loseLevel(Pane pane, Stage stage, Scene levelSelectScene, File nextLevelFile) {
		Button exitButton = new Button("Exit");
		Button tryAgainButton = new Button("Try Again");

		// returning the level select when the exit clicked
		exitButton.setOnAction(e -> {
			clearPane(pane);
			stage.setScene(levelSelectScene);
		}); // Exit the application
		tryAgainButton.setOnAction(e -> {
			// restart the level
			try {
				clearPane(pane);
				Scene newLevel = new Scene(createLevel(nextLevelFile, currentLevel, stage, levelSelectScene), 800, 800,
						Color.LIGHTBLUE);
				stage.setScene(newLevel);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		// Create a rectangle
		Rectangle rectangle = new Rectangle(300, 250);
		rectangle.setFill(Color.DARKBLUE);
		rectangle.setOpacity(0.5);
		rectangle.setArcHeight(15);
		rectangle.setArcWidth(15);

		// Create layout for buttons
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);

		Text message = new Text("YOU LOST !");
		message.setFont(Font.font("Arial", 40));
		message.setFill(Color.WHITE);
		message.setStroke(Color.BLACK);
		message.setStrokeWidth(0.5);
		buttonBox.getChildren().addAll(exitButton, tryAgainButton);
		VBox V = new VBox(20);
		V.getChildren().addAll(message, buttonBox);
		V.setAlignment(Pos.CENTER);
		// Create a StackPane and add the rectangle and button layout
		StackPane sPane = new StackPane();
		sPane.getChildren().addAll(rectangle, V);

		return sPane;
	}

	public Pane win5Level(Stage stage, Scene levelSelectScene, Pane pane) {
		// when the fifth level has been won showing a celebration message and iamge
		Image win = new Image("file:win.jpg");
		ImageView winView = new ImageView(win);
		winView.setFitHeight(800);
		winView.setFitWidth(800);
		Text message = new Text("YOU WON THE GAME !");
		message.setFont(Font.font("Arial", 50));
		message.setFill(Color.WHITE);
		message.setStroke(Color.BLACK);
		message.setStrokeWidth(1);
		Rectangle background = new Rectangle(550, 350);
		background.setFill(Color.GRAY);
		background.setOpacity(0.5);
		background.setArcHeight(20);
		background.setArcWidth(20);
		// continue button for returning level select
		Button continueButton = new Button("CONTINUE");
		continueButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
		continueButton.setOnMouseClicked(e -> {
			clearPane(pane);
			stage.setScene(levelSelectScene);
		});

		VBox v = new VBox(20);
		v.getChildren().addAll(message, continueButton);
		v.setAlignment(Pos.CENTER);
		StackPane sPane = new StackPane();
		sPane.getChildren().addAll(winView, background, v);
		return sPane;
	}

	public void addXbutton(Pane pane, Stage stage, Scene levelSelectScene) {
		// x button for returning the level select
		ImageView X = new ImageView("file:x.png");
		X.setLayoutX(5);
		X.setLayoutY(5);
		X.setFitHeight(56);
		X.setFitWidth(56);
		pane.getChildren().add(X);
		X.setOnMouseClicked(ex -> {
			clearPane(pane);
			stage.setScene(levelSelectScene);
		});
	}

	public void clearPane(Pane pane) {
		// Reset the animation timer
		if (timer != null) {
			timer.stop(); // Stop the timer if it's running
			timeline.stop();
		}
		// Clear all elements from the pane and reset cars and other variables
		pane.getChildren().clear();
		for (PathTransition pt : pts) {
			pt.stop(); // Stop each PathTransition
		}
		for (Car car : cars) {
			car.reset();
		}
	}

	public void controlWinLose(Pane pane, Stage stage, Scene levelSelectScene, int[] winlose) {
		timeline = new Timeline(new KeyFrame(Duration.seconds(0.4), event -> {
			if (winlose[0] <= 0) {
				if (timer != null)
					timer.stop();
				timeline.stop();

				if (currentLevel == 5) {
					// when the fifth level is won
					Pane p = win5Level(stage, levelSelectScene, pane);
					pane.getChildren().add(p);
				} else {
					// creating next level when a level is won
					currentLevel++;
					File nextLevelFile = returnNextLevelFile();
					Pane p = winLevel(pane, stage, nextLevelFile, levelSelectScene);
					p.setLayoutX(175);
					p.setLayoutY(255);
					pane.getChildren().add(p);
				}
			}
			if (winlose[1] <= 0) {
				if (timer != null)
					timer.stop();
				timeline.stop();
				// when a level is lost
				File nextLevelFile = returnNextLevelFile();
				Pane p = loseLevel(pane, stage, levelSelectScene, nextLevelFile);
				p.setLayoutX(250);
				p.setLayoutY(275);
				pane.getChildren().add(p);
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely

		// Start the timeline
		timeline.play();
	}

	public File returnNextLevelFile() {
		switch (currentLevel) {
		case 1:
			return level1;
		case 2:
			return level2;
		case 3:
			return level3;
		case 4:
			return level4;
		case 5:
			return level5;
		default:
			return null;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
}