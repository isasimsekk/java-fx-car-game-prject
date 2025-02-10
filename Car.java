// Feyza ÇELİK 150123082
// Yusuf KURT 150123078
// İsa ŞİMŞEK 150122038
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Car extends Pane {
	long time1;
	public boolean timeBoolean1 = true;
	int tailedCarIndex;
	int stoppedTLIndex;
	long timeForStop;
	public boolean collidible = true;
	public boolean timeBoolean0 = true;
	int stoppingTime = 200;
	static double carCoefficient = 11;
	static ArrayList<Duration> durationsOfPaths = new ArrayList<>();

	public Car(Pane pane, ArrayList<Car> cars) {
		// Visual rectangle
		Rectangle rectangle1 = new Rectangle();
		rectangle1.setHeight(carCoefficient);
		rectangle1.setWidth(2 * carCoefficient);
		rectangle1.setFill(Color.DIMGREY);
		rectangle1.setArcHeight(carCoefficient * 2 / 3.);
		rectangle1.setArcWidth(carCoefficient * 2 / 3.);
		// Visual rectangle
		Rectangle rectangle2 = new Rectangle();
		rectangle2.setHeight(carCoefficient * 2 / 3.);
		rectangle2.setWidth(carCoefficient * 4 / 3.);
		rectangle2.setFill(Color.LIGHTCYAN);
		rectangle2.setArcHeight(carCoefficient * 4 / 9.);
		rectangle2.setArcWidth(carCoefficient * 4 / 9.);
		rectangle2.setX(carCoefficient * 1 / 3.);
		rectangle2.setY(carCoefficient * 1 / 6.);
		// Visual rectangle
		Rectangle rectangle3 = new Rectangle();
		rectangle3.setHeight(carCoefficient * 2 / 3.);
		rectangle3.setWidth(carCoefficient * 7 / 7.5);
		rectangle3.setFill(Color.DIMGREY);
		rectangle3.setArcHeight(carCoefficient * 2 / 7.5);
		rectangle3.setArcWidth(carCoefficient * 2 / 7.5);
		rectangle3.setX(carCoefficient * 7 / 15.);
		rectangle3.setY(carCoefficient * 1 / 6.);

		// Functional and transparent rectangle
		Rectangle checkBox = new Rectangle();
		checkBox.setHeight(4);
		checkBox.setWidth(carCoefficient / 1.25);
		checkBox.setFill(Color.RED);
		checkBox.setOpacity(0);
		checkBox.setX(2 * carCoefficient);
		checkBox.setY(-2 + carCoefficient / 2.);

		// Functional and transparent rectangle
		Rectangle tlCheckBox = new Rectangle();
		tlCheckBox.setHeight(1);
		tlCheckBox.setWidth(carCoefficient * 1.5);
		tlCheckBox.setFill(Color.BLUE);
		tlCheckBox.setOpacity(0);
		tlCheckBox.setX(2 * carCoefficient);
		tlCheckBox.setY(carCoefficient / 2.);

		// Functional and transparent circle
		Circle hitCircle1 = new Circle();
		hitCircle1.setRadius(carCoefficient / 4.);
		hitCircle1.setFill(Color.DARKRED);
		hitCircle1.setCenterX(carCoefficient / 2.);
		hitCircle1.setCenterY(carCoefficient / 2.);
		hitCircle1.setOpacity(0);

		// Functional and transparent circle
		Circle hitCircle2 = new Circle();
		hitCircle2.setRadius(carCoefficient / 4.);
		hitCircle2.setFill(Color.DARKRED);
		hitCircle2.setCenterX(carCoefficient * 3 / 2.);
		hitCircle2.setCenterY(carCoefficient / 2.);
		hitCircle2.setOpacity(0);

		// Functional and transparent rectangle
		Rectangle rearCheckBox = new Rectangle();
		rearCheckBox.setHeight(carCoefficient);
		rearCheckBox.setWidth(10);
		rearCheckBox.setFill(Color.PURPLE);
		rearCheckBox.setOpacity(0);

		// Nodes get added
		this.getChildren().addAll(rectangle1, rectangle2, rectangle3, checkBox, tlCheckBox, hitCircle1, hitCircle2,
				rearCheckBox);
		// cars are mouse transparent
		this.mouseTransparentProperty().set(true);
	}

	public static boolean spawnCar(int winlose[], Pane pane, ArrayList<Car> cars, ArrayList<Polyline> paths,
			ArrayList<PathTransition> pts, int carIndex) {
		// choosing a random path
		int pathIndex = (int) Math.round(Math.random() * (paths.size() - 1));

		// checking if that path is suited for spawn
		for (int i = 0; i < pts.size(); i++) {
			if (pts.get(i).getPath().equals(paths.get(pathIndex))
					&& (pts.get(i).getCurrentTime().lessThanOrEqualTo(Duration.millis(300))
							&& cars.get(i).isCollidible())) {
				return false;
			}
		}

		Car car = new Car(pane, cars);
		pane.getChildren().add(car);
		cars.add(car);

		// Handler for car removal
		EventHandler<ActionEvent> removeFinishedCar = e -> {
			pane.getChildren().remove(cars.get(carIndex));
			cars.get(carIndex).setCollidible(false);
			winlose[0]--;
		};

		pts.add(new PathTransition());
		pts.get(carIndex).setPath(paths.get(pathIndex));
		ObservableList<Double> points = paths.get(pathIndex).getPoints();
		durationsOfPaths.add(Duration.millis(calculateLength(points)));
		pts.get(carIndex).setNode(cars.get(carIndex));
		pts.get(carIndex).setInterpolator(Interpolator.LINEAR);
		pts.get(carIndex).setDuration(durationsOfPaths.get(carIndex).multiply(10));
		pts.get(carIndex).setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pts.get(carIndex).play();
		pts.get(carIndex).setOnFinished(removeFinishedCar);

		return true;
	}
	
	//Used for calculating the length of the path
	public static double calculateLength(ObservableList<Double> pointsOfPath) {
		double length = 0;
		for (int i = 0; i < pointsOfPath.size() / 2 - 1; i++) {
			length += Math.sqrt(Math.pow((pointsOfPath.get(i + 2) - pointsOfPath.get(i)), 2)
			+ Math.pow((pointsOfPath.get(i + 3) - pointsOfPath.get(i + 1)), 2));
		}
		return length;
	}

	//Self explanetory
	public void slowDownThenStop(ArrayList<PathTransition> pts, int j) {
		if (isTimeBoolean0()) {
			timeForStop = System.currentTimeMillis();
			setTimeBoolean0(false);
		}
		pts.get(j).setRate(0.4);
		pts.get(j).setInterpolator(Interpolator.EASE_IN);
		if (System.currentTimeMillis() - timeForStop >= 150) {
			pts.get(j).pause();
			pts.get(j).setInterpolator(Interpolator.LINEAR);
			setTimeBoolean0(true);
		}
	}

	//Self explanetory
	public void unPause(ArrayList<PathTransition> pts, int j) {
		pts.get(j).play();
		pts.get(j).setRate(1);
	}

	//Self explanetory
	public void stop(ArrayList<PathTransition> pts, int j) {
		pts.get(j).stop();
	}
	
	// Reset all the variables to their initial values
	public void reset() {
		time1 = 0;
		timeBoolean1 = true;
		tailedCarIndex = -1;
		stoppedTLIndex = -1;
		timeForStop = 0;
		collidible = true;
		timeBoolean0 = true;
		durationsOfPaths.clear();
	}
	
	public boolean isCollidible() {
		return collidible;
	}

	public void setCollidible(boolean collidible) {
		this.collidible = collidible;
	}

	public boolean isTimeBoolean0() {
		return timeBoolean0;
	}

	public void setTimeBoolean0(boolean timeBoolean0) {
		this.timeBoolean0 = timeBoolean0;
	}

	public long getTime1() {
		return time1;
	}

	public void setTime1(long time1) {
		this.time1 = time1;
	}

	public boolean isTimeBoolean1() {
		return timeBoolean1;
	}

	public void setTimeBoolean1(boolean timeBoolean1) {
		this.timeBoolean1 = timeBoolean1;
	}

}