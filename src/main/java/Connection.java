import java.awt.*;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 * Connection - each box holds a list of connections
 * and the box containing the list is the origin, while the box in the list is the destination
 * the enum typeOfConnection shows what type should be drawn/used to connect the two boxes
 */
public abstract class Connection implements java.io.Serializable {
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
    public abstract void paintConnection(Graphics g);
}