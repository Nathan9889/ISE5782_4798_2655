package renderer;

import lighting.Light;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static geometries.Intersectable.*;
import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracer {

    /**
     * delta
     */
    private static final double DELTA = 0.1;

    /**for transparency
     *
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * for transpaency
     */
    private static final double MIN_CALC_COLOR_K = 0.001;


    /**
     * if the ray intersect with object than the point is shadow
     * @param light
     * @param gp
     * @param l
     * @param n
     * @return
     */
    private boolean unshaded(LightSource light,GeoPoint gp, Vector l, Vector n){

       Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        List<GeoPoint> inters = this.scene.geometries.findGeoIntersections(lightRay,light.getDistance(gp.point));
        if (inters != null){
            for(GeoPoint intersection : inters){
                if(intersection.geometry.getMaterial().Kt ==Double3.ZERO){
                    return false;
                }
            }
        }
        return true;
    }

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
                if(unshaded(lightSource,gp,l,n)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));

                }
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
