import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TempVarDec extends TempDecoration{

    private String varName;
    private int decWidth;

    public int getDecHeight() {
        return decHeight;
    }

    private int decHeight;
    private ArrayList<String> vars;

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public TempVarDec(String var, UMLComponent component){
        this.varName = var;
        this.vars = new ArrayList<String>();

        super.setComponent(component);
        super.setNumVars(component.getNumVars() +1);
        super.setTotalVars(component.getTotalVars() +1);
//        component.setTotalVars(component.getNumVars() +1);
        super.setNumMethods(component.getNumMethods());
//            super.setVarY(component.getVarY() + decHeight);
//            super.setMethodY(component.getMethodY() + decHeight);
        this.decWidth = component.getWidth()-5;
        this.decHeight = TempDecoration.decHeight * this.vars.size();
    }

    @Override
    public void paintBox(Graphics g){
        this.vars = (ArrayList<String>) Arrays.stream(this.varName.split("\n")).collect(Collectors.toList());
        this.vars.remove(0);
        int baseBoxX = component.getX();
        this.decHeight = TempDecoration.decHeight * this.vars.size();
        this.component.setNumVars(this.vars.size());
        super.paintBox(g);
//        int baseBoxY = component.getY()- component.getHeight()/2;
        //paint Var
        g.setColor(Color.PINK);
//        g.fillRect(baseBoxX - component.getWidth()/2+2, super.getVarY()-decHeight, decWidth, decHeight);
//        g.setColor(Color.black);
//        g.drawString(varName, baseBoxX- varName.length()*5, super.getVarY()-decHeight+ 15);

//        super.setVarY(y- this.getHeight()/2 + 15);
//        super.setMethodY(super.getVarY() + TempDecoration.decHeight + 5);
        g.fillRect(baseBoxX - component.getWidth()/2+2,
                super.getY() - super.getHeight()/2 + 25, decWidth, decHeight);
        g.setColor(Color.black);
        int baseHeight = 40;
        for(String var : vars){
            g.drawString(var, baseBoxX- var.length()*5, super.getY() - super.getHeight()/2 + baseHeight);
            baseHeight += TempDecoration.decHeight;
        }
    }
}
