// Feyza ÇELİK 150123082
// Yusuf KURT 150123078
// İsa ŞİMŞEK 150122038
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class RoadTile extends Pane {
	private int type;
	private double rotation;
	private double x;
	private double y;
	private final double ul=800 / 15.;
//unitLength
//spaces in each side of a given unit is 1/10 of ul(unit length)
	public RoadTile(int type, double rotation, double x, double y) {
		x = x*ul;
		y = y*ul;
		//creating roadtiles with rotation cases
		switch (type) {
			case 0:
				if(rotation>90)rotation-=180;
				double a,b,c,d;
				double centerX=x+(ul/2.);
				double centerY=y+(ul/2.);
				double e=ul/2.;
				switch ((int)rotation) {
					//horizontal
					case 0:a=ul;b=ul*8/10.;y+=ul/10.;c= ul/2.;d=0;
					break;
					//vertical
					case 90: a=ul*8/10.;b=ul;x+=ul/10.;c=0;d=ul/2.;
					break;
					default: a=0; b=0; c=0; d=0;
					break;
				}
				Rectangle rec = new Rectangle(x, y, a, b);
				rec.setFill(Color.WHITE);
				Line line = new Line(centerX-c, centerY+d, centerX+c, centerY-d);
				getChildren().addAll(rec,line);
			break;

		case 1:
			switch ((int)rotation) {
				case 0:
					y+=ul;
					break;
				case 90:
					x+=ul;
					y+=ul;
					break;
				case 180:
					x+=ul;
					break;
				case 270:
					break;
			}
			Arc arc = new Arc(x, y, ul*9/10., ul*9/10., rotation, 90);
			arc.setType(ArcType.ROUND);
			arc.setFill(Color.WHITE);
			Arc arc2 = new Arc(x, y, ul/10., ul/10., rotation, 90);
			arc2.setType(ArcType.ROUND);
			arc2.setFill(Color.LIGHTBLUE);
			arc2.setStroke(Color.LIGHTBLUE);
			Arc arcLine = new Arc(x, y, ul*1/2.,ul*1/2. , rotation, 90);
			arcLine.setType(ArcType.OPEN);
			arcLine.setFill(Color.WHITE);
			arcLine.setStroke(Color.BLACK);
			getChildren().addAll(arc, arcLine, arc2);
			break;

		case 2:
			Rectangle rec1 = new Rectangle(x, y+ul/10., ul, ul*8/10.);
			rec1.setFill(Color.WHITE);
			Rectangle rec2 = new Rectangle(x+ul/10., y, ul*8/10, ul);
			rec2.setFill(Color.WHITE);;
			getChildren().addAll(rec1,rec2);
			break;

		case 3:
			double f=0,g=0,h=0,i=0,x1=x,y1=y,x2=x,y2=y;
			switch ((int)rotation) {
				case 0:x1+=ul/10.;y1+=ul/10.;y2+=ul/10.;f=ul;g=ul*8/10;h=ul*8/10;i=ul*9/10.;
					break;
				case 90:x2=x+ul/10.;f=ul*8/10.;g=ul;y1=y+ul/10.;h=ul*9/10.;i=ul*8/10.;
					break;
				case 180:x1+=ul/10.;y1-=ul/10.;y2+=ul/10.;f=ul;g=ul*8/10.;h=ul*8/10.;i=ul*9/10.;
					break;
				case 270:x2=x+ul/10.;f=ul*8/10.;g=ul;y1=y+ul/10.;h=ul*9/10.;i=ul*8/10.;x1+=ul/10.;
					break;
			}
			Rectangle r=new Rectangle(x2, y2, f, g);
			Rectangle r1=new Rectangle(x1, y1, h, i);
			r.setFill(Color.WHITE);
			r.setStroke(Color.WHITE);
			r1.setFill(Color.WHITE);
			r1.setStroke(Color.WHITE);
			getChildren().addAll(r,r1);
			break;
		}
	}
}