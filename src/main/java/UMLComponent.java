import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 2.0
 * UMLComponent - abstract parent class for the box decorator model
 */
@Getter
@Setter
public abstract class UMLComponent implements java.io.Serializable{
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UMLComponent that = (UMLComponent) o;
        return x == that.x && y == that.y && width == that.width && height == that.height && numVars == that.numVars && totalVars == that.totalVars && numMethods == that.numMethods && name.equals(that.name) && connections.equals(that.connections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x, y, width, height, connections, numVars, totalVars, numMethods);
    }

    private int x;
    private int y;
    private int width = 120;
    private int height = 25;
    private ArrayList<Connection> connections = new ArrayList<Connection>();
    private int numVars = 0;
    private int totalVars = 0;
    private int numMethods = 0;

    /**
     * paintBox - abstract method for drawing the UML component on the screen
     * @param g - Graphics object used to create what is shown on screen
     */
    public abstract void paintBox(Graphics g);

    /**
     * addConnection - adds a connection to the connection list
     * @param destination - UML component that the arrow will be pointing to
     * @param connectionType - type of connection to be made
     */
    public void addConnection(UMLComponent destination, ConnectionType connectionType) {
        if ( connectionType == ConnectionType.INHERITANCE){
            this.getConnections().add(new Inheritance(this, destination, connectionType));
        } else if (connectionType == ConnectionType.COMPOSITION){
            this.getConnections().add(new Composition(this, destination, connectionType));
        } else {
            this.getConnections().add(new Association(this, destination, connectionType));
        }
        Blackboard.getBlackboard().statusBarNewConnection(connectionType.name(), this.getName(), destination.getName());
        Blackboard.getBlackboard().notifying();
    }

    /**
     * checkConnection
     * @param destination - boolean
     *      true if there is already a connection to the destination component
     * @return
     */
    public boolean checkConnection(UMLComponent destination) {
        for(Connection c: this.getConnections()){
            if (c.getDestination() == destination){
                return true;
            }
        }
        return false;
    }

    /**
     * checkCollision - checks if the mouse is pressed within the class
     * @param x - x-coordinate of click
     * @param y - y-coordinate of click
     * @return true if the mouse is pressed within the class, false otherwise
     */
    public boolean checkCollision(int x, int y){
        return this.getX() - this.getWidth() / 2 <= x && x <= this.getX() + this.getWidth() / 2 &&
                this.getY() - this.getHeight() / 2 <= y && y <= this.getY() + this.getHeight() / 2;
    }
}