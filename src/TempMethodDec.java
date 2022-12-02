import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TempMethodDec extends TempDecoration {
    private String methodName;
    private UMLComponent component;
    private int decWidth;
    private int decHeight;
    private ArrayList<String> methods;

    public UMLComponent getComponent() {
        return component;
    }

    @Override
    public void setComponent(UMLComponent component) {
        this.component = component;
    }

    public TempMethodDec(String method, UMLComponent component) {
        this.component = component;
        this.methodName = method;
        this.methods = (ArrayList<String>) Arrays.stream(this.methodName.split("\n")).collect(Collectors.toList());

        super.setComponent(component);
        super.setNumVars(component.getNumVars());
        super.setTotalVars(component.getTotalVars());
        super.setNumMethods(component.getNumMethods() + 1);
//        super.setVarY(component.getVarY());
//        super.setMethodY(component.getMethodY() + decHeight);
        this.decWidth = component.getWidth() - 5;
        this.decHeight = TempDecoration.decHeight * this.methods.size();
    }

    @Override
    public void paintBox(Graphics g) {
        this.methods = (ArrayList<String>) Arrays.stream(this.methodName.split("\n")).collect(Collectors.toList());
        this.methods.remove(0);
        int baseBoxX = component.getX();
        this.decHeight = TempDecoration.decHeight * this.methods.size();
        ((TempVarDec) this.component).getComponent().setNumMethods(this.methods.size());
        super.paintBox(g);
        super.paintBox(g);
//        int baseBoxX = component.getX();
//        int baseBoxY = component.getY();
        //paint Var
        g.setColor(Color.PINK);
//        g.fillRect(baseBoxX - component.getWidth()/2+2, super.getMethodY()-decHeight, decWidth, decHeight);
//        g.setColor(Color.black);
//        g.drawString(methodName, baseBoxX- methodName.length()*5, super.getMethodY() -decHeight+ 15);
        if (super.getNumVars() == 0) {
            g.fillRect(baseBoxX - component.getWidth() / 2 + 2,
                    super.getY() - super.getHeight() / 2 + 25 + 5,
                    decWidth, decHeight);
            g.setColor(Color.black);
            g.drawString(methodName, baseBoxX - methodName.length() * 5, super.getY() - super.getHeight() / 2 + 25 + ((super.getNumMethods() - 1) * decHeight) + 5 + 15);
        } else {
            g.fillRect(baseBoxX - component.getWidth() / 2 + 2,
                    super.getY() - super.getHeight() / 2 + 30 + ((TempVarDec) this.component).getDecHeight(),
                    decWidth, decHeight);
            g.setColor(Color.black);
            int baseHeight = 45;
            for (String method : methods) {
                g.drawString(method + "()", baseBoxX - method.length() * 5, super.getY() - super.getHeight() / 2 +
                        ((TempVarDec) this.component).getDecHeight() + baseHeight);
                baseHeight += TempDecoration.decHeight;
            }
        }
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
