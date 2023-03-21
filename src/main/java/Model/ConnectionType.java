package Model;

import java.io.Serializable;

/**
 * Final Project
 * @author Jamie Luna, Andrew Estrada, Mitashi Parikh
 * @version 1.0
 * Model.ConnectionType - hold all possible types of connections that can be
 * drawn/used to connect two boxes
 */

public enum ConnectionType implements Serializable {
    ASSOCIATION,
    INHERITANCE,
    COMPOSITION
}
