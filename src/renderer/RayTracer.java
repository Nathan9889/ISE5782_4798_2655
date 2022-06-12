package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
     * @param rays
     * @return
     */
    public abstract Color traceRays(List<Ray> rays);


}
