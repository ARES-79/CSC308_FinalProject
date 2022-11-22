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
    private typeOfConnection type;


    private enum typeOfConnection{
        INHERITANCE,
        ASSOCIATION,
        COMPOSITION
    }

    public Connection(Box b, typeOfConnection t){
        this.destination = b;
        this.type = t;
    }

    public Box getDestination() {
        return destination;
    }

    public typeOfConnection getType() {
        return type;
    }

    public void paintConnection(Graphics g){
        switch (type){
            case INHERITANCE -> System.out.println("Paint Inheritance");
            case ASSOCIATION -> System.out.println("Paint Association");
            case COMPOSITION -> System.out.println("Paint Composition");
        }
    }
}

