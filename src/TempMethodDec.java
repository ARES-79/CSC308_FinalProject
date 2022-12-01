import java.awt.*;

public class TempMethodDec extends TempDecoration{
    private String methodName;
    private int decWidth;

    public TempMethodDec(String method, UMLComponent component){
        this.methodName = method;
        super.setComponent(component);
        super.setVarY(component.getVarY());
        super.setMethodY(component.getMethodY() + decHeight);
        this.component.setHeight(component.getHeight() + decHeight);
        this.decWidth = component.getWidth()-5;
    }

    @Override
    public void paintBox(Graphics g){
        super.paintBox(g);
        int baseBoxX = component.getX();
//        int baseBoxY = component.getY();
        //paint Var
        g.setColor(Color.PINK);
        g.fillRect(baseBoxX - component.getWidth()/2+2, super.getMethodY()-decHeight, decWidth, decHeight);
        g.setColor(Color.black);
        g.drawString(methodName, baseBoxX- methodName.length()*5, super.getMethodY() -decHeight+ 15);

    }
}
