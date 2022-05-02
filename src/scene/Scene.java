package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * Scen for holding all of the elements
 */
public class Scene {

    /**
     * name of the scene
     */
    final public String name;

    /**
     * the background color in the scene
     */
    public Color background;

    /**
     * the ambient light in the scene
     */
    public AmbientLight ambientLight;

    /**
     * all kind of geometries elements like sphere, triangle, plane ect'
     */
    public Geometries geometries;


    /**
     * Ctor
     * @param _name name of builder
     */
    public Scene(String _name) {
        name = _name;
        background = Color.BLACK;
        ambientLight = new AmbientLight();
        geometries = new Geometries();
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

}
