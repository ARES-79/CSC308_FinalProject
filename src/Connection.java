import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 * Connection - each box holds a list of connections
 * and the box containing the list is the origin, while the box in the list is the destination
 * the enum typeOfConnection shows what type should be drawn/used to connect the two boxes
 */
public class Connection implements java.io.Serializable {
    private UMLComponent destination;
    private UMLComponent origin;
    private ConnectionType type;

    /**
     * Connection constructor
     *      creates a Connection which has an origin Box, destination Box, and a type
     * @param origin the Box the connection starts at
     * @param dest the Box the connection ends at
     * @param t the Type of connection being made
     */
    public Connection(UMLComponent origin, UMLComponent dest, ConnectionType t){
        this.origin = origin;
        this.destination = dest;
        this.type = t;
    }

    /**
     * getDestination - Getter method for the destination Box
     * @return the destination UMLComponent (contains a Box)
     */
    public UMLComponent getDestination() {
        return destination;
    }

    /**
     * getDestination - Getter method for the destination Box
     * @return the destination UMLComponent (contains a Box)
     */
    public UMLComponent getOrigin() { return origin; }

    /**
     * getType - Getter method for the type of the connection
     * @return the type of the connection
     */
    public ConnectionType getType() {
        return type;
    }

    /**
     * paintConnection - paints each type of connection based on the enum ConnectionType
     * @param g the Graphics object to put things on the screen
     */
    public void paintConnection(Graphics g){
        //makes the lines thicker (looks nicer)
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        switch (type){
            case INHERITANCE -> {
                drawInheritance(g);
            }
            case ASSOCIATION -> {
                List<Integer> coords = drawAssociation(g);
                g.drawLine(coords.get(0), coords.get(1), coords.get(2), coords.get(3));
            }
            case COMPOSITION -> {
                List<Integer> coords = drawComposition(g);
                g.drawLine(coords.get(0), coords.get(1), coords.get(2), coords.get(3));
            }
        }
    }

    /**
     * drawInheritance - draws a line between the origin and destination and a triangle pointing
     *      toward the destination
     * @param g the Graphics object to put things on the screen
     */
    private void drawInheritance(Graphics g){
        int x1, y1, x2, y2;
        int[] x_coords;
        int[] y_coords;

        if (origin.getX() - origin.getWidth() <= destination.getX() &&
                destination.getX() <= origin.getX() + origin.getWidth()) { //up and down
            x1 = origin.getX();
            x2 = destination.getX();
            x_coords = new int[]{x2 - 10, x2, x2 + 10};

            if(origin.getY() >= destination.getY()){ //dest above
                y1 = origin.getY() - origin.getHeight()/2 ;
                y2 = destination.getY() + destination.getHeight()/2 + 20;
                y_coords = new int[]{y2, y2 - 20, y2};
            }else{
                y1 = origin.getY() + origin.getHeight()/2;
                y2 = destination.getY() - destination.getHeight()/2 - 20;
                y_coords = new int[]{y2, y2 + 20, y2};
            }
        } else {
            y1 = origin.getY();
            y2 = destination.getY();
            y_coords = new int[]{y2 + 10, y2, y2 - 10};

            if(origin.getX() <= destination.getX()){
                x1 = origin.getX() + origin.getWidth()/2;
                x2 = destination.getX() - destination.getWidth()/2 - 20;
                x_coords = new int[]{x2, x2 + 20, x2};
            } else{
                x1 = origin.getX() - origin.getWidth()/2;
                x2 = destination.getX() + destination.getWidth()/2 + 20;
                x_coords = new int[]{x2, x2 - 20, x2};
            }
        }

        g.drawLine(x1, y1, x2, y2);

        Polygon triangle = new Polygon(x_coords, y_coords, 3);
        g.drawPolygon(triangle);
    }

    /**
     * drawAssociation - finds the coordinates to draw a line between the origin
     *      and destination and a pointed triangle pointing toward the destination
     * @param g the Graphics object to put things on the screen
     * @return the coordinates to draw the line between
     */
    private List<Integer> drawAssociation(Graphics g){
        int x1, y1, x2, y2;
        int[] x_coords;
        int[] y_coords;

        if (origin.getX() - origin.getWidth() <= destination.getX() &&
                destination.getX() <= origin.getX() + origin.getWidth()) { //up and down
            x1 = origin.getX();
            x2 = destination.getX();
            x_coords = new int[]{x2 - 5, x2, x2 + 5};

            if (origin.getY() >= destination.getY()) { //dest above
                y1 = origin.getY() - origin.getHeight() / 2 ;
                y2 = destination.getY() + destination.getHeight() / 2 + 10;
                y_coords = new int[]{y2, y2 - 10, y2};
            } else {
                y1 = origin.getY() + origin.getHeight() / 2;
                y2 = destination.getY() - destination.getHeight() / 2 - 10;
                y_coords = new int[]{y2, y2 + 10, y2};
            }
        } else {
            y1 = origin.getY();
            y2 = destination.getY();
            y_coords = new int[]{y2 + 5, y2, y2 - 5};

            if(origin.getX() <= destination.getX()){
                x1 = origin.getX() + origin.getWidth()/2;
                x2 = destination.getX() - destination.getWidth()/2 - 10;
                x_coords = new int[]{x2, x2 + 10, x2};
            } else{
                x1 = origin.getX() - origin.getWidth()/2;
                x2 = destination.getX() + destination.getWidth()/2 + 10;
                x_coords = new int[]{x2, x2 - 10, x2};
            }
        }

        Polygon triangle = new Polygon(x_coords, y_coords, 3);
        g.fillPolygon(triangle);

        return new ArrayList<Integer>(Arrays.asList(x1, y1, x2, y2));
    }

    /**
     drawComposition - finds the coordinates to draw a line between the origin
     *      and destination, paints a pointed triangle pointing toward the destination,
     *      paints a diamond at the edge of the origin
     * @param g the Graphics object to put things on the screen
     * @return the coordinates to draw the line between
     */
    private List<Integer> drawComposition(Graphics g){
        ArrayList<Integer> coords = (ArrayList<Integer>) drawAssociation(g);
        int x1 = coords.get(0);
        int y1 = coords.get(1);
        int[] x_coords;
        int[] y_coords;

        if (origin.getX() - origin.getWidth() <= destination.getX() &&
                destination.getX() <= origin.getX() + origin.getWidth()){
            x_coords = new int[]{x1, x1 - 10, x1, x1 + 10};
            if (origin.getY() >= destination.getY()) { //dest above
                coords.set(1, y1 - 20);
                y_coords = new int[]{y1, y1 - 10, y1 - 20, y1 - 10};
            } else{
                coords.set(1, y1 + 20);
                y_coords = new int[]{y1, y1 + 10, y1 + 20, y1 + 10};
            }
        } else{ //right and left
            y_coords = new int[]{y1, y1 - 10, y1, y1 + 10};
            if(origin.getX() <= destination.getX()){ //dest to right
                x_coords = new int[]{x1, x1 + 10, x1 + 20, x1 + 10};
                coords.set(0, x1 + 20);
            } else{
                x_coords = new int[]{x1, x1 - 10, x1 - 20, x1 - 10};
                coords.set(0, x1 - 20);
            }
        }

        Polygon diamond = new Polygon(x_coords, y_coords, 4);
        g.fillPolygon(diamond);

        return coords;
    }
}
