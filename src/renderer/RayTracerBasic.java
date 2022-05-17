package renderer;

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
     private boolean unshaded(LightSource light,GeoPoint gp, Vector l, Vector n, double nv){

     Vector lightDirection = l.scale(-1);
     Vector deltaVect = n.scale(nv<0? DELTA : -DELTA);
     Point point = gp.point.add(deltaVect);
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
     }*/




    private Double3 transparency(GeoPoint gp, Vector l, Vector n, double nv, LightSource lightSource)
    {
        Vector lightDirection = l.scale(-1);
        Vector deltaVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(deltaVector);
        Ray lightRay = new Ray(lightDirection,point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);;
        if (intersections == null )return Double3.ONE;

        Double3 ktr = Double3.ONE;
        double lightDistance = lightSource.getDistance(gp.point);
        for (GeoPoint geo : intersections){
            if ( alignZero( geo.point.distance(gp.point) - lightDistance) <= 0 )
                ktr = ktr.product(geo.geometry.getMaterial().Kt);
            if(ktr.lowerThan(MIN_CALC_COLOR_K )) return Double3.ZERO;
        }
        return ktr;

    }






/*

    private Double3 transparency(GeoPoint geoPoint, LightSource lightSource, Vector l,Vector n){
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = lightSource.getDistance(geoPoint.point);

        var intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null){
            return new Double3(1.0);
        }


        Double3 Ktr = new Double3(1.0);
        for (GeoPoint gp :intersections){
            if(alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0){
                Ktr *= gp.geometry.getMaterial().Kt;
                Ktr = Ktr.product(gp.geometry.getMaterial().Kt);

                if (Ktr.lowerThan(MIN_CALC_COLOR_K)){
                    return Double3.ZERO;
                }
            }

        }
        return Ktr;
    }*/


    private Ray constructReflectedRay(Vector n, Point geoPoint, Ray inRay)
    {
        Vector v = inRay.getDir();
        double vn = v.dotProduct(n);
        Vector reflected = v.subtract(n.scale(2*v.dotProduct(n))).normalize();
        return new Ray(geoPoint, reflected, n);
    }


    private Ray constructRefractedRay(Vector n, Point geoPoint, Ray inRay)
    {
        return new Ray(geoPoint, inRay.getDir(), n);
    }

























    /**
     * ctor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     *
     * @param gp
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint gp,Ray ray,int level, Double3 k ) {
        Color color = CalcLocalEffects(gp,ray,k);
        if(level == 1) return color;
        return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
    }

    private Color calcGlobalEffects(GeoPoint gp,primitives.Ray ray, int level, Double3 k)
    {
        Color color = Color.BLACK;
        Double3 kr = gp.geometry.getMaterial().Kr, kkr = k.product(kr);
        if (! kkr.lowerThan(MIN_CALC_COLOR_K))
        {
            Ray reflectedRay = constructReflectedRay(gp.geometry.getNormal(gp.point), gp.point,ray );
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if(reflectedPoint != null)color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));

        }

        Double3 kt = gp.geometry.getMaterial().Kt, kkt = k.product(kt);
        if (! kkt.lowerThan(MIN_CALC_COLOR_K))
        {
            Ray refractedRay = constructRefractedRay(gp.geometry.getNormal(gp.point),gp.point, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if(refractedPoint != null)color = color.add(calcColor(refractedPoint, refractedRay, level-1,kkt).scale(kt));

        }

        return color;
    }



    private Color calcColor(GeoPoint gp, Ray ray)
    {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, new Double3(1d)).add(scene.ambientLight.getIntensity());
    }


    private GeoPoint findClosestIntersection(Ray ray)
    {
        List<GeoPoint> intersections= scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * trace a ray to the geometries
     * @param ray
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closeIntersections= findClosestIntersection(ray);
        if (closeIntersections==null)
            return scene.background;
        return calcColor(closeIntersections,ray);
    }





    private Color CalcLocalEffects(GeoPoint gp, Ray ray,Double3 k){
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
                Double3 Ktr = transparency(gp,l,n,nv,lightSource);

                if (!(Ktr.product(k).lowerThan(MIN_CALC_COLOR_K))){

                    Color iL = lightSource.getIntensity(gp.point).scale(Ktr);
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
