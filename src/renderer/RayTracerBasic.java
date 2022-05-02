package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import static geometries.Intersectable.*;

public class RayTracerBasic extends RayTracer {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    Color calcColor(GeoPoint geoPoint){
        return scene.ambientLight.getIntensity()
                .add(geoPoint.geometry.getEmission());
    }
    @Override
    Color traceRay(Ray ray) {
        var inters = scene.geometries.findGeoIntersections(ray);
        if(inters == null){
            return scene.background;
        }
        GeoPoint gp = ray.findClosestGeoPoint(inters);
        return  calcColor(gp);
    }
}
