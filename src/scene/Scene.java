package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;
import primitives.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
     * List of lightsource
     */
    public List<LightSource> lights = new LinkedList<>();




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

    /**
     * builder type setter for backround
     * @param background
     * @return
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }


    /**
     * builder type setter for ambient light
     * @param ambientLight
     * @return
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }


    /**
     * builder setter for geometries
     * @param geometries
     * @return
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }


    /**
     * builder setter for list of lightsource
     * @param lights
     * @return
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

}
