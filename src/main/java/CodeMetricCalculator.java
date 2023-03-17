import javax.swing.*;

/**
 * Final Project
 * @author Andrew Estrada
 * @version 1.0
 * Class that can calculate the total LOC, eLOC, or lLOC of a chunk of code
 */
public class CodeMetricCalculator implements MyObserver {

    private CustomTextArea codeText;
    private JTextArea textArea;

    /**
     * default constructor
     */
    public CodeMetricCalculator(){}

    /**
     * overridden constructor for use as an observer
     *
     * @param textArea textArea whose text should be updated
     * @param codeText CustomTextArea with code text
     */
    public CodeMetricCalculator(JTextArea textArea, CustomTextArea codeText){
        this.codeText = codeText;
        this.textArea = textArea;
    }

    /**
     * Determines if the input is a valid LOC
     *
     * @param loc a single line from a chunk of code
     * @return true if the String is a valid LOC, false otherwise
     */
    public boolean isLOC(String loc) {
        return (loc.trim()).length() > 0;
    }

    /**
     * Calculates the total LOC of a chunk of code
     *
     * @param codeInput chunk of code
     * @return the number of valid LOC in the code chunk
     */
    public int totalLOC(String codeInput) {
        String[] lines = codeInput.split("\\r?\\n|\\r");
        int count = 0;
        for (String line: lines){
            if (isLOC(line)){ count++;}
        }
        return count;
    }

    /**
     * Determines if the input is a valid eLOC
     *
     * @param loc a single line from a chunk of code
     * @return true if the String is a valid eLOC, false otherwise
     */
    public boolean isELOC(String loc) {
        loc = loc.trim();
        String parens = "{}()";
        if (loc.length() == 0){return false;}
        if (loc.length() == 1){
            if (parens.contains(loc)){
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the total eLOC of a chunk of code
     *
     * @param codeInput chunk of code
     * @return the number of valid eLOC in the code chunk
     */
    public int totalELOC(String codeInput) {
        String[] lines = codeInput.split("\\r?\\n|\\r");
        int count = 0;
        for (String line: lines){
            if (isELOC(line)){ count++;}
        }
        return count;
    }

    /**
     * Determines if the input is a valid lLOC
     *
     * @param loc a single line from a chunk of code
     * @return true if the String is a valid lLOC, false otherwise
     */
    public boolean isLLOC(String loc) {
        return (loc.trim()).contains(";");
    }

    /**
     * Calculates the total lLOC of a chunk of code
     *
     * @param codeInput chunk of code
     * @return the number of valid lLOC in the code chunk
     */
    public int totalLLOC(String codeInput) {
        String[] lines = codeInput.split("\\r?\\n|\\r");
        int count = 0;
        for (String line: lines){
            if (isLLOC(line)){ count++;}
        }
        return count;
    }

    /**
     * overridden method to update the text area with new metrics
     *
     * @param ob the overservable that calls update
     */
    @Override
    public void update(MyObservable ob) {
        String code = codeText.getText();
        textArea.setText("\t LOC: " + totalLOC(code)
                        + "\t\t eLOC: " + totalELOC(code)
                        + "\t\t lLOC: " + totalLLOC(code));
    }
}
