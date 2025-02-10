// Feyza ÇELİK 150123082
// Yusuf KURT 150123078
// İsa ŞİMŞEK 150122038
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Metadata {
	double height;
	double width;
	int cellX;
	int cellY;
	int numberOfPaths;
	int toWin;
	int toOver;

	public Metadata(double height, double width, int cellX, int cellY, int numberOfPaths, int toWin, int toOver) {
		this.height = height;
		this.width = width;
		this.cellX = cellX;
		this.cellY = cellY;
		this.numberOfPaths = numberOfPaths;
		this.toWin = toWin;
		this.toOver = toOver;
	}
	// adding lines to background
	public void lines(Pane pane) {
		for (int i = 0; i < cellX; i++) {
			Line line = new Line(0, i * height / 15., height, i * height / 15.);
			line.setOpacity(0.3);
			line.setStrokeWidth(0.5);
			pane.getChildren().add(line);
		}

		for (int i = 0; i < cellY; i++) {
			Line line = new Line(i * width / 15., 0, i * width / 15., width);
			line.setStrokeWidth(0.5);
			line.setOpacity(0.3);
			pane.getChildren().add(line);
		}
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public int getNumberOfPaths() {
		return numberOfPaths;
	}

	public int getToWin() {
		return toWin;
	}

	public int getToOver() {
		return toOver;
	}

}
