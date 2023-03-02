import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Composition extends Connection{
    /**
     * Connection constructor
     * creates a Connection which has an origin Box, destination Box, and a type
     *
     * @param origin the Box the connection starts at
     * @param dest   the Box the connection ends at
     * @param t      the Type of connection being made
     */
    public Composition(UMLComponent origin, UMLComponent dest, ConnectionType t) {
        super(origin, dest, t);
    }

    @Override
    public void paintConnection(Graphics g) {
        int x1, y1, x2, y2;
        int[] x_coords, y_coords, tri_x_coords, tri_y_coords;
        ArrayList<Integer> line_coords = new ArrayList<>(Arrays.asList(0,0,0,0));

        //makes the lines thicker (looks nicer)
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        if (this.getOrigin().getX() - this.getOrigin().getWidth() <= this.getDestination().getX() &&
                this.getDestination().getX() <= this.getOrigin().getX() + this.getOrigin().getWidth()) { //up and down
            x1 = this.getOrigin().getX();
            x2 = this.getDestination().getX();
            x_coords = new int[]{x1, x1 - 10, x1, x1 + 10};
            line_coords.set(0, x1);
            line_coords.set(2, x2);
            tri_x_coords = new int[]{x2 - 5, x2, x2 + 5};

            if (this.getOrigin().getY() >= this.getDestination().getY()) { //dest above
                y1 = this.getOrigin().getY() - this.getOrigin().getHeight() / 2 ;
                y2 = this.getDestination().getY() + this.getDestination().getHeight() / 2 + 10;
                y_coords = new int[]{y1, y1 - 10, y1 - 20, y1 - 10};
                line_coords.set(1, y1 - 20);
                line_coords.set(3, y2);
                tri_y_coords = new int[]{y2, y2 - 10, y2};

            } else {
                y1 = this.getOrigin().getY() + this.getOrigin().getHeight() / 2;
                y2 = this.getDestination().getY() - this.getDestination().getHeight() / 2 - 10;
                y_coords = new int[]{y1, y1 + 10, y1 + 20, y1 + 10};
                line_coords.set(1, y1 + 20);
                line_coords.set(3, y2);
                tri_y_coords = new int[]{y2, y2 + 10, y2};
            }
        } else {
            y1 = this.getOrigin().getY();
            y2 = this.getDestination().getY();
            y_coords = new int[]{y1, y1 - 10, y1, y1 + 10};
            line_coords.set(1, y1);
            line_coords.set(3, y2);
            tri_y_coords = new int[]{y2 + 5, y2, y2 - 5};

            if(this.getOrigin().getX() <= this.getDestination().getX()){
                x1 = this.getOrigin().getX() + this.getOrigin().getWidth()/2;
                x2 = this.getDestination().getX() - this.getDestination().getWidth()/2 - 10;
                x_coords = new int[]{x1, x1 + 10, x1 + 20, x1 + 10};
                line_coords.set(0, x1 + 20);
                line_coords.set(2, x2);
                tri_x_coords = new int[]{x2, x2 + 10, x2};
            } else{
                x1 = this.getOrigin().getX() - this.getOrigin().getWidth()/2;
                x2 = this.getDestination().getX() + this.getDestination().getWidth()/2 + 10;
                x_coords = new int[]{x1, x1 - 10, x1 - 20, x1 - 10};
                line_coords.set(0, x1 - 20);
                line_coords.set(2, x2);
                tri_x_coords = new int[]{x2, x2 - 10, x2};
            }
        }

        Polygon diamond = new Polygon(x_coords, y_coords, 4);
        g.fillPolygon(diamond);

        Polygon triangle = new Polygon(tri_x_coords, tri_y_coords, 3);
        g.fillPolygon(triangle);

        g.drawLine(line_coords.get(0), line_coords.get(1), line_coords.get(2), line_coords.get(3));
    }
}
