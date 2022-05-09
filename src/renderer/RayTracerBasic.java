package renderer;

import lighting.Light;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import static geometries.Intersectable.*;
import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracer {

    /**
     * ctor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * func to calculate color of the point
     * @param geoPoint
     * @return
     */
    Color calcColor(GeoPoint geoPoint,Ray ray){
        return scene.ambientLight.getIntensity().add(CalcLocalEffects(geoPoint,ray));
    }

    /**
     * trace a ray to the geometries
     * @param ray
     * @return
     */
    @Override
    Color traceRay(Ray ray) {
        var inters = scene.geometries.findGeoIntersections(ray);
        if(inters == null){
            return scene.background;
        }
        GeoPoint gp = ray.findClosestGeoPoint(inters);
        return  calcColor(gp,ray);
    }





    private Color CalcLocalEffects(GeoPoint gp, Ray ray){
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);

        double nv = alignZero(n.dotProduct(v));

        if(nv == 0){
            return color;
        }

        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights){
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));

            if(nl*nv > 0){
                Color iL =lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l ,nl, v)));
            }
        }
        return color;
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {


        Vector r = l.add(n.scale(-2*nl));
        double vr = v.scale(-1).dotProduct(r);
        return material.Ks.scale(Math.pow(Math.max(0, vr),material.nShininess));
    }



    /**
     * calculate kd of color
     * @param material
     * @param nl
     * @return
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.Kd.scale(Math.abs(nl));
    }


}
