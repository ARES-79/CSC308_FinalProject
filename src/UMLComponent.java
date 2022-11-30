import java.awt.*;
import java.util.ArrayList;

/**
 * Assignment 01
 * @author Andrew Estrada
 * @version 1.0
 * UMLComponent - parent class for the box decorator model
 */
public abstract class UMLComponent implements java.io.Serializable{
    private String name;
    private int x;
    private int y;
    private int width = 120;
    private int height = 30;
    private ArrayList<Connection> connections = new ArrayList<Connection>();

    /**
     * paintBox - abstract method for drawing the UML component on the screen
     * @param g - Graphics object used to create what is shown on screen
     */
    public abstract void paintBox(Graphics g);

    /**
     * getName
     * @return String - name value
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * @param name - Sting value to replace/set name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getX
     * @return int - current x value
     */
    public int getX() {
        return x;
    }

    /**
     * setX
     * @param x - int value to replace current x value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getY
     * @return int - current y value
     */
    public int getY() {
        return y;
    }

    /**
     * setY
     * @param y - int value to replace the current y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * getWidth
     * @return int - current width value
     */
    public int getWidth() {
        return width;
    }

    /**
     * setWidth
     * @param width - int value to replace the current width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * getHeight
     * @return int - current height value
     */
    public int getHeight() {
        return height;
    }

    /**
     * getConnections
     * @return ArrayList - list of connections that originate for this UML Component
     */
    public ArrayList<Connection> getConnections() {
        return connections;
    }

    /**
     * setHeight
     * @param height - int value to replace the current height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * addConnection - adds a connection to the connection list
     * @param destination - UML component that the arrow will be pointing to
     * @param connectionType - type of connection to be made
     */
    public void addConnection(UMLComponent destination, ConnectionType connectionType) {
        this.getConnections().add(new Connection(this, destination, connectionType));
        Blackboard.getBlackboard().notifying();
        System.out.println("Connection from " + this.getName() + " to " + destination.getName());
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