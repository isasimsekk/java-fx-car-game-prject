// Feyza ÇELİK 150123082
// Yusuf KURT 150123078
// İsa ŞİMŞEK 150122038
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TrafficLight extends Pane {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private final double ul = 800 / 15.;

	public TrafficLight(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		
		//line
		Line line = new Line(x1, y1, x2, y2);
		line.setOpacity(0.4);
		
		//colored circle
		Circle circle1 = new Circle((x1 + x2) / 2, (y1 + y2) / 2, 5);
		circle1.setFill(Color.GREEN);
		circle1.setStroke(Color.BLACK);

		//Gold colored clickable circle
		Circle circle2 = new Circle((x1 + x2) / 2, (y1 + y2) / 2, 20);
		circle2.setFill(Color.TRANSPARENT);
		circle2.setStroke(Color.GOLD);
		circle2.setOpacity(1);

		//Nodes get added
		getChildren().addAll(line, circle1, circle2);

		//handler for clicking circle2
		circle2.setOnMouseClicked(e -> {
			if (circle1.getFill() == Color.GREEN)
				circle1.setFill(Color.RED);
			else
				circle1.setFill(Color.GREEN);
		});
	}

	public double getX() {
		return x1;
	}

}