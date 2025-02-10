// Feyza ÇELİK 150123082
// Yusuf KURT 150123078
// İsa ŞİMŞEK 150122038
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Building extends Pane {
	private int type;
	private double rotation;
	private int color;
	private double x;
	private double y;
	private Color[] colors = { Color.GREEN, Color.PURPLE, Color.ORANGE, Color.RED };
	// our unit length for arrenging the positon for building
	private final double ul = 800 / 15.;

	public Building(int type, double rotation, int color, double x, double y) {
		x = x * 800 / 15.;
		y = y * 800 / 15.;

		switch (type) {
		case 0:
			switch ((int) rotation) {
			// changing th rotation
			case 0:
				Rectangle building = new Rectangle(x - 5, y - 5, 10 + 2 * ul, 10 + 3 * ul);
				building.setFill(Color.WHITE);
				building.setStroke(Color.GRAY);
				building.setArcHeight(10);
				building.setArcWidth(10);

				Rectangle square1 = new Rectangle(x + 10, y + 10, 85, 85);
				square1.setFill(colors[color]);
				square1.setStroke(colors[color].darker());
				square1.setStrokeWidth(5);
				square1.setArcHeight(10);
				square1.setArcWidth(10);

				Rectangle square2 = new Rectangle(x + 20, y + 20, 65, 65);
				square2.setFill(colors[color]);
				square2.setStrokeWidth(5);
				square2.setStroke(colors[color].darker());
				square2.setArcHeight(10);
				square2.setArcWidth(10);

				getChildren().addAll(building, square1, square2);
				break;
			case 90:
				Rectangle buildingA = new Rectangle(x - 5, y - 5, 10 + 3 * ul, 10 + 2 * ul);
				buildingA.setFill(Color.WHITE);
				buildingA.setStroke(Color.GRAY);
				buildingA.setArcHeight(10);
				buildingA.setArcWidth(10);

				Rectangle square1A = new Rectangle(x + 10, y + 10, 85, 85);
				square1A.setFill(colors[color]);
				square1A.setStroke(colors[color].darker());
				square1A.setStrokeWidth(5);
				square1A.setArcHeight(10);
				square1A.setArcWidth(10);

				Rectangle square2A = new Rectangle(x + 20, y + 20, 65, 65);
				square2A.setFill(colors[color]);
				square2A.setStrokeWidth(5);
				square2A.setStroke(colors[color].darker());
				square2A.setArcHeight(10);
				square2A.setArcWidth(10);

				getChildren().addAll(buildingA, square1A, square2A);
				break;
			case 180:
				Rectangle buildingB = new Rectangle(x - 5, y - 5, 10 + 2 * ul, 10 + 3 * ul);
				buildingB.setFill(Color.WHITE);
				buildingB.setStroke(Color.GRAY);
				buildingB.setArcHeight(10);
				buildingB.setArcWidth(10);

				Rectangle square1B = new Rectangle(x + 10, y + 10 + 800 / 15., 85, 85);
				square1B.setFill(colors[color]);
				square1B.setStroke(colors[color].darker());
				square1B.setStrokeWidth(5);
				square1B.setArcHeight(10);
				square1B.setArcWidth(10);

				Rectangle square2B = new Rectangle(x + 20, y + 20 + 800 / 15., 65, 65);
				square2B.setFill(colors[color]);
				square2B.setStrokeWidth(5);
				square2B.setStroke(colors[color].darker());
				square2B.setArcHeight(10);
				square2B.setArcWidth(10);

				getChildren().addAll(buildingB, square1B, square2B);
				break;
			case 270:
				Rectangle buildingC = new Rectangle(x - 5, y - 5, 10 + 3 * ul, 10 + 2 * ul);
				buildingC.setFill(Color.WHITE);
				buildingC.setStroke(Color.GRAY);
				buildingC.setArcHeight(10);
				buildingC.setArcWidth(10);

				Rectangle square1C = new Rectangle(x + 10 + 800 / 15., y + 10, 85, 85);
				square1C.setFill(colors[color]);
				square1C.setStroke(colors[color].darker());
				square1C.setStrokeWidth(5);
				square1C.setArcHeight(10);
				square1C.setArcWidth(10);

				Rectangle square2C = new Rectangle(x + 20 + 800 / 15., y + 20, 65, 65);
				square2C.setFill(colors[color]);
				square2C.setStrokeWidth(5);
				square2C.setStroke(colors[color].darker());
				square2C.setArcHeight(10);
				square2C.setArcWidth(10);

				getChildren().addAll(buildingC, square1C, square2C);
				break;
			}
			break;
		case 1:
			double bx = x - 5, by = y - 5, ba = 10 + ul * 2, bb = 10 + ul * 3;
			double cx = x, cy = y, c1a = ul * 9 / 10.;
			double c2a = ul * 6 / 10.;
			switch ((int) rotation) {
			case 0:
				cx += ul;
				cy += ul;
				break;
			case 90:
				ba += ul;
				bb -= ul;
				cx += ul;
				cy += ul;
				break;
			case 180:
				cx += ul;
				cy += 2 * ul;
				break;
			case 270:
				ba += ul;
				bb -= ul;
				cx += 2 * ul;
				cy += ul;
				break;
			default:
			}
			Rectangle building2 = new Rectangle(bx, by, ba, bb);
			Circle circle1 = new Circle(cx, cy, c1a);
			Circle circle2 = new Circle(cx, cy, c2a);

			building2.setFill(Color.WHITE);
			building2.setStroke(Color.GRAY);
			building2.setArcHeight(10);
			building2.setArcWidth(10);

			circle1.setFill(colors[color]);
			circle1.setStroke(colors[color].darker());
			circle1.setStrokeWidth(5);

			circle2.setFill(colors[color]);
			circle2.setStroke(colors[color].darker());
			circle2.setStrokeWidth(5);
			getChildren().addAll(building2, circle1, circle2);

			break;
		case 2:
			Rectangle squareBuilding = new Rectangle(x - 5, y - 5, ul + 10, ul + 10);
			squareBuilding.setFill(colors[color]);
			squareBuilding.setStroke(colors[color].darker());
			squareBuilding.setArcHeight(10);
			squareBuilding.setArcWidth(10);
			getChildren().add(squareBuilding);
			break;

		}

	}
}