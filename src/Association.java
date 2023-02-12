import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Association extends Connection{
    /**
     * Connection constructor
     * creates a Connection which has an origin Box, destination Box, and a type
     *
     * @param origin the Box the connection starts at
     * @param dest   the Box the connection ends at
     * @param t      the Type of connection being made
     */
    public Association(UMLComponent origin, UMLComponent dest, ConnectionType t) {
        super(origin, dest, t);
    }


    /**
     * drawAssociation - finds the coordinates to draw a line between the origin
     *      and destination and a pointed triangle pointing toward the destination
     * @param g the Graphics object to put things on the screen
     * @return the coordinates to draw the line between
     */
    @Override
    public void paintConnection(Graphics g) {
        int x1, y1, x2, y2;
        int[] x_coords;
        int[] y_coords;

        //makes the lines thicker (looks nicer)
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        if (this.getOrigin().getX() - this.getOrigin().getWidth() <= this.getDestination().getX() &&
                this.getDestination().getX() <= this.getOrigin().getX() + this.getOrigin().getWidth()) { //up and down
            x1 = this.getOrigin().getX();
            x2 = this.getDestination().getX();
            x_coords = new int[]{x2 - 5, x2, x2 + 5};

            if (this.getOrigin().getY() >= this.getDestination().getY()) { //dest above
                y1 = this.getOrigin().getY() - this.getOrigin().getHeight() / 2 ;
                y2 = this.getDestination().getY() + this.getDestination().getHeight() / 2 + 10;
                y_coords = new int[]{y2, y2 - 10, y2};
            } else {
                y1 = this.getOrigin().getY() + this.getOrigin().getHeight() / 2;
                y2 = this.getDestination().getY() - this.getDestination().getHeight() / 2 - 10;
                y_coords = new int[]{y2, y2 + 10, y2};
            }
        } else {
            y1 = this.getOrigin().getY();
            y2 = this.getDestination().getY();
            y_coords = new int[]{y2 + 5, y2, y2 - 5};

            if(this.getOrigin().getX() <= this.getDestination().getX()){
                x1 = this.getOrigin().getX() + this.getOrigin().getWidth()/2;
                x2 = this.getDestination().getX() - this.getDestination().getWidth()/2 - 10;
                x_coords = new int[]{x2, x2 + 10, x2};
            } else{
                x1 = this.getOrigin().getX() - this.getOrigin().getWidth()/2;
                x2 = this.getDestination().getX() + this.getDestination().getWidth()/2 + 10;
                x_coords = new int[]{x2, x2 - 10, x2};
            }
        }

        Polygon triangle = new Polygon(x_coords, y_coords, 3);
        g.fillPolygon(triangle);


        g.drawLine(x1, y1, x2, y2);



        //return new ArrayList<Integer>(Arrays.asList(x1, y1, x2, y2));
    }
}
