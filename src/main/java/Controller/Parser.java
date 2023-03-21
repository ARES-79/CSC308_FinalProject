package Controller;

import Model.ConnectionType;
import Model.MethodDec;
import Model.UMLComponent;
import Model.VarDec;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Assignment 01
 * @author Archie Jones
 * @version 2.1
 * Controller.Parser - parses a list of UMLComponents to convert them into code
 */
public class Parser {
    /**
     * parseClasses - method to update the text area when new classes are created in the View.DrawPanel
     * @param boxes List<Model.UMLComponent> the current list of boxes which will be parsed to extract data
     */
    public String parseClasses(List<UMLComponent> boxes) {
        String completeText = "";
        for (UMLComponent box : boxes) {
            String text = "";
            List<String> inheritenceCons = box.getConnections().stream()
                    .filter(connection -> connection.getType().equals(ConnectionType.INHERITANCE))
                    .map(connection -> connection.getDestination().getName())
                    .collect(Collectors.toList());
            List<String> associationCons = box.getConnections().stream()
                    .filter(connection -> connection.getType().equals(ConnectionType.ASSOCIATION))
                    .map(connection -> connection.getDestination().getName())
                    .collect(Collectors.toList());
            List<String> compositionCons = box.getConnections().stream()
                    .filter(connection -> connection.getType().equals(ConnectionType.COMPOSITION))
                    .map(connection -> connection.getDestination().getName())
                    .collect(Collectors.toList());
            MethodDec tempMethodDec = (MethodDec) box;
            List<String> variables = Arrays.stream(((VarDec) tempMethodDec.getComponent())
                    .getVarName().split("\n")).collect(Collectors.toList());
            List<String> methods = Arrays.stream(((MethodDec) box).getMethodName().split("\n"))
                    .collect(Collectors.toList());
            variables.remove(0);
            methods.remove(0);
            text = "class " + box.getName();
            if (inheritenceCons.size() > 0) {
                text += " extends " + String.join(" ", inheritenceCons);
            }
            text += " {\n";
            if (compositionCons.size() > 0) {
                text += " " + String.join(";\n ", compositionCons) + ";\n";
            }
            if (variables.size() > 0) {
                text += " " + String.join(";\n ", variables) + ";\n";
            }
            if (associationCons.size() > 0) {
                text += " methods() {\n  " + String.join(";\n  ", associationCons) + ";\n }\n";
            }
            if (methods.size() > 0) {
                text += " " + String.join("() {\n }\n ", methods) + "() {\n }";
            }
            text += "\n}\n";
            completeText += (text);
        }
        return completeText;
    }
}
