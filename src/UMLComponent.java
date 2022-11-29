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
    private int height = 60;
    private ArrayList<Connection> connections = new ArrayList<Connection>();

    public abstract void paintBox(Graphics g);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void addConnection(UMLComponent destination, ConnectionType connectionType) {
//        this.connections.add(new Connection(this, destination, connectionType));
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * checkCollision - checks if the mouse is pressed within the class
     * @param x - x-coordinate of click
     * @param y - y-coordinate of click
     * @return true if the mouse is pressed within the class, false otherwise
     */
    public boolean checkCollision(int x, int y){
        return this.x - width / 2 <= x && x <= this.x + width / 2 &&
                this.y - height / 2 <= y && y <= this.y + height / 2;
    }
}