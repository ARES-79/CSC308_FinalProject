import java.awt.*;

public class TempMethodDec extends TempDecoration{
    private String methodName;
    private int decWidth;

    public TempMethodDec(String method, UMLComponent component){
        this.methodName = method;
        super.setComponent(component);
        super.setNumVars(component.getNumVars());
        super.setTotalVars(component.getTotalVars());
        super.setNumMethods(component.getNumMethods() +1);
//        super.setVarY(component.getVarY());
//        super.setMethodY(component.getMethodY() + decHeight);
        this.decWidth = component.getWidth()-5;
    }

    @Override
    public void paintBox(Graphics g){
        super.paintBox(g);
        int baseBoxX = component.getX();
//        int baseBoxY = component.getY();
        //paint Var
        g.setColor(Color.PINK);
//        g.fillRect(baseBoxX - component.getWidth()/2+2, super.getMethodY()-decHeight, decWidth, decHeight);
//        g.setColor(Color.black);
//        g.drawString(methodName, baseBoxX- methodName.length()*5, super.getMethodY() -decHeight+ 15);
        if (super.getNumVars() == 0){
            g.fillRect(baseBoxX - component.getWidth()/2+2,
                    super.getY() - super.getHeight()/2 + 25 + ((super.getNumMethods()-1)* decHeight) + 5,
                    decWidth, decHeight);
            g.setColor(Color.black);
            g.drawString(methodName, baseBoxX- methodName.length()*5, super.getY() - super.getHeight()/2 + 25 + ((super.getNumMethods()-1)* decHeight) + 5 + 15);
        } else{
            g.fillRect(baseBoxX - component.getWidth()/2+2,
                    super.getY() - super.getHeight()/2 + 25 + ((super.getTotalVars() + super.getNumMethods()-1)* decHeight) + 5,
                    decWidth, decHeight);
            g.setColor(Color.black);
            g.drawString(methodName, baseBoxX- methodName.length()*5, super.getY() - super.getHeight()/2 + 25 + ((super.getTotalVars() + super.getNumMethods()-1)* decHeight) + 5 + 15);
        }
    }
}
