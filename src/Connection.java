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
    private UMLComponent destination;
    private UMLComponent origin;
    private ConnectionType type;

//    private enum typeOfConnection{
//        INHERITANCE,
//        ASSOCIATION,
//        COMPOSITION
//    }

    public Connection(UMLComponent origin, UMLComponent dest, ConnectionType t){
        this.origin = origin;
        this.destination = dest;
        this.type = t;
    }

    public UMLComponent getDestination() {
        return destination;
    }

    public UMLComponent getOrigin() { return origin; }

    public ConnectionType getType() {
        return type;
    }

    public void paintConnection(Graphics g){
        //makes lines thicker (looks nicer)
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        g.drawLine(origin.getX(), origin.getY(), destination.getX(), destination.getY());

        switch (type){
            //line with triangle arrow
            case INHERITANCE -> {
                System.out.println("Painting Inheritance");
                //arrow always drawn on destination side
                int[] x_coords;
                int[] y_coords;
                if (origin.getX() != destination.getX()){
                    y_coords = new int[]{destination.getY() + 10, destination.getY(), destination.getY() - 10};
                    if(origin.getX() <= destination.getX()){
                        x_coords = new int[]{destination.getX(), destination.getX() + 20, destination.getX()};
                    } else{x_coords = new int[]{destination.getX(), destination.getX() - 20, destination.getX()};}
                } else {
                    x_coords = new int[]{destination.getX() - 10, destination.getX(), destination.getX() + 10};
                    if(origin.getY() > destination.getY()){
                        y_coords = new int[]{destination.getY(), destination.getY() - 20, destination.getY()};
                    } else{y_coords = new int[]{destination.getY(), destination.getY() + 20, destination.getY()};}
                }

                Polygon triangle = new Polygon(x_coords, y_coords, 3);
                g.drawPolygon(triangle);
            }
            //line, no arrow
            case ASSOCIATION -> {
                System.out.println("Painting Association");
            }
            //line with black diamond
            case COMPOSITION -> {
                System.out.println("Painting Composition");
                //not very well tested, lmk if you find a bug
                //diamond always goes on destination side
                int[] x_coords = new int[]{destination.getX() - 15, destination.getX() - 5, destination.getX() + 5, destination.getX() - 5};
                int[] y_coords = new int[]{destination.getY(), destination.getY() - 10, destination.getY(), destination.getY() + 10};
                Polygon diamond = new Polygon(x_coords, y_coords, 4);
                g.fillPolygon(diamond);
            }
        }
    }
}