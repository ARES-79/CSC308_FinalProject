package Model;

import Model.Connection;
import Model.ConnectionType;
import Model.UMLComponent;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 */
@Getter
@Setter
public class Inheritance extends Connection {
    /**
     * Model.Connection constructor
     * creates a Model.Connection which has an origin Box, destination Box, and a type
     *
     * @param origin the Box the connection starts at
     * @param dest   the Box the connection ends at
     * @param t      the Type of connection being made
     */
    public Inheritance(UMLComponent origin, UMLComponent dest, ConnectionType t) {
        super(origin, dest, t);
    }

    /**
     * drawInheritance - draws a line between the origin and destination and a triangle pointing
     *      toward the destination
     * @param g the Graphics object to put things on the screen
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
            x_coords = new int[]{x2 - 10, x2, x2 + 10};

            if(this.getOrigin().getY() >= this.getDestination().getY()){ //dest above
                y1 = this.getOrigin().getY() - this.getOrigin().getHeight()/2 ;
                y2 = this.getDestination().getY() + this.getDestination().getHeight()/2 + 20;
                y_coords = new int[]{y2, y2 - 20, y2};
            }else{
                y1 = this.getOrigin().getY() + this.getOrigin().getHeight()/2;
                y2 = this.getDestination().getY() - this.getDestination().getHeight()/2 - 20;
                y_coords = new int[]{y2, y2 + 20, y2};
            }
        } else {
            y1 = this.getOrigin().getY();
            y2 = this.getDestination().getY();
            y_coords = new int[]{y2 + 10, y2, y2 - 10};

            if(this.getOrigin().getX() <= this.getDestination().getX()){
                x1 = this.getOrigin().getX() + this.getOrigin().getWidth()/2;
                x2 = this.getDestination().getX() - this.getDestination().getWidth()/2 - 20;
                x_coords = new int[]{x2, x2 + 20, x2};
            } else{
                x1 = this.getOrigin().getX() - this.getOrigin().getWidth()/2;
                x2 = this.getDestination().getX() + this.getDestination().getWidth()/2 + 20;
                x_coords = new int[]{x2, x2 - 20, x2};
            }
        }

        g.drawLine(x1, y1, x2, y2);

        Polygon triangle = new Polygon(x_coords, y_coords, 3);
        g.drawPolygon(triangle);
    }
}
