import java.awt.*;

/**
 * Final Project
 * @author Jamie Luna
 * @version 1.0
 * Box variables-
 * String name, Int x, Int y, Int width, Int height
 */
public class Box extends UMLComponent{
    int addedHeight = 0;
    public Box(String name, int x, int y) {
        super.setName(name);
        super.setX(x);
        super.setY(y);
        if(name.length()*8 > getWidth()){
            super.setWidth(name.length()*8);
        }
    }


    @Override
    public void paintBox(Graphics g){
        int tempAddedHeight = (super.getNumVars() + super.getNumMethods()) * Decoration.decHeight == 0 ? 0 :
                (super.getNumVars() + super.getNumMethods()) * Decoration.decHeight;
        if(tempAddedHeight != this.addedHeight){
            super.setHeight(super.getHeight() - this.addedHeight + tempAddedHeight);
            this.addedHeight = tempAddedHeight;
        }
        g.setColor(Color.YELLOW);
        g.fillRect(super.getX() - super.getWidth()/2, super.getY() - super.getHeight()/2,
                super.getWidth(), super.getHeight());
        g.setColor(Color.black);
        g.drawString(this.getName(), super.getX()-super.getName().length()*3, super.getY() - super.getHeight()/2 + 20);

        for(Connection c: super.getConnections()){
            c.paintConnection(g);
        }
    }

    public void setHeight(int height){
        super.setHeight(height);
    }

}
