package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracer {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    Color calcColor(Point point){
        return scene.ambientLight.getIntensity();
    }
    @Override
    Color traceRay(Ray ray) {
        List<Point> inters = scene.geometries.findIntersections(ray);
        if(inters == null){
            return scene.background;
        }
        return  calcColor(ray.findClosestPoint(inters));
    }
}
