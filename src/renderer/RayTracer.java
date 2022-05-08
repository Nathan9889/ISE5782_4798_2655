package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracer {

    /**
     * Scene
     */
    protected Scene scene;

    /**
     * setter builder
     * @param scene
     */
    protected RayTracer(Scene scene){
        this.scene = scene;
    }

    /**
     * TraceRay
     * @param ray
     * @return
     */
    abstract Color traceRay(Ray ray);

}
