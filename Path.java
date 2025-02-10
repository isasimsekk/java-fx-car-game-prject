// Feyza ÇELİK 150123082
// Yusuf KURT 150123078
// İsa ŞİMŞEK 150122038
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polyline;

public class Path {

	int number;
	String type;
	double X, Y;
	ArrayList<Polyline> paths;
	ArrayList<Double> pointsOfPath = new ArrayList<Double>();
	

	public Path(int number, String type, double x, double y, ArrayList<Polyline> paths) {
		this.number = number;
		this.type = type;
		X = x;
		Y = y;
		this.paths = paths;

		if (type.equals("MoveTo")) {
			//for starting point
			Polyline a = new Polyline();
			ObservableList<Double> list = a.getPoints();
			list.add(X);
			list.add(Y);
			paths.add(a);
			pointsOfPath.add(X);
			pointsOfPath.add(Y);
		}

		else {
			//for next points
			ObservableList<Double> list = paths.get(number).getPoints();
			list.add(X);
			list.add(Y);
			pointsOfPath.add(X);
			pointsOfPath.add(Y);
		}

	}
}
