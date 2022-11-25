import java.awt.*;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 * Connection - each box holds a list of connections
 * and the box containing the list is the origin, while the box in the list is the destination
 * the enum typeOfConnection shows what type should be drawn/used to connect the two boxes
 */
public class Connection {
    private Box destination;
    private Box origin;
    private ConnectionType type;

//    private enum typeOfConnection{
//        INHERITANCE,
//        ASSOCIATION,
//        COMPOSITION
//    }

    public Connection(Box origin, Box dest, ConnectionType t){
        this.origin = origin;
        this.destination = dest;
        this.type = t;
    }

    public Box getDestination() {
        return destination;
    }

    public Box getOrigin() { return origin; }

    public ConnectionType getType() {
        return type;
    }

    public void paintConnection(Graphics g){
        switch (type){
            //line with triangle arrow
            case INHERITANCE -> {
                System.out.println("Painting Inheritance");
                g.drawLine(origin.getX(), origin.getY(), destination.getX(), destination.getY());
            }
            //line, no arrow
            case ASSOCIATION -> {
                System.out.println("Painting Association");
                g.drawLine(origin.getX(), origin.getY(), destination.getX(), destination.getY());
            }
            //line with black diamond
            case COMPOSITION -> {
                System.out.println("Painting Composition");
                g.drawLine(origin.getX(), origin.getY(), destination.getX(), destination.getY());
                int[] x_coords = new int[]{destination.getX() - 20, destination.getX() - 10, destination.getX(), destination.getX() - 10};
                int[] y_coords = new int[]{destination.getY(), destination.getY() - 10, destination.getY(), destination.getY() + 10};
                Polygon diamond = new Polygon(x_coords, y_coords, 4);
                g.fillPolygon(diamond);
            }
        }
    }
}