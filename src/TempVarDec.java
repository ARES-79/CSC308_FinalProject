import java.awt.*;

public class TempVarDec extends TempDecoration{

    private String varName;
    private int decWidth;

    public TempVarDec(String var, UMLComponent component){
        this.varName = var;
        super.setComponent(component);
        super.setNumVars(component.getNumVars() +1);
        super.setTotalVars(component.getTotalVars() +1);
//        component.setTotalVars(component.getNumVars() +1);
        super.setNumMethods(component.getNumMethods());
//            super.setVarY(component.getVarY() + decHeight);
//            super.setMethodY(component.getMethodY() + decHeight);
        this.decWidth = component.getWidth()-5;
    }

    @Override
    public void paintBox(Graphics g){
        super.paintBox(g);
        int baseBoxX = component.getX();
//        int baseBoxY = component.getY()- component.getHeight()/2;
        //paint Var
        g.setColor(Color.PINK);
//        g.fillRect(baseBoxX - component.getWidth()/2+2, super.getVarY()-decHeight, decWidth, decHeight);
//        g.setColor(Color.black);
//        g.drawString(varName, baseBoxX- varName.length()*5, super.getVarY()-decHeight+ 15);

//        super.setVarY(y- this.getHeight()/2 + 15);
//        super.setMethodY(super.getVarY() + TempDecoration.decHeight + 5);
        g.fillRect(baseBoxX - component.getWidth()/2+2,
                super.getY() - super.getHeight()/2 + 25 + ((super.getNumVars()-1)* decHeight), decWidth, decHeight);
        g.setColor(Color.black);
        g.drawString(varName, baseBoxX- varName.length()*5, super.getY() - super.getHeight()/2 + 25 + ((super.getNumVars()-1)* decHeight)+ 15);

    }
}
