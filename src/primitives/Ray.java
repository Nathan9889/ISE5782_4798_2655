package primitives;

import geometries.Intersectable;

import java.nio.channels.FileLockInterruptionException;
import java.util.List;
import java.util.Objects;

import static geometries.Intersectable.*;
import static primitives.Util.isZero;

/**
 * Ray class in 3D world
 */
public class Ray {

    /**
     * Point for stating ray
     */
    final Point p0;

    /**
     * Vector in direction of ray
     */
    final Vector dir;

    /**
     * Constructor of Ray
     * @param v1
     * @param _p0
     */
    public Ray(Vector v1, Point _p0) {
        p0 = _p0;
        dir = v1.normalize();
    }

    /**
     * get the starting point of the ray
     * @return starting point of the ray (p0)
     */
    public Point getP0() {
        return p0;
    }


    /**
     * get point with specific distance
     * @param t distance to reach new point
     * @return new {@link Point}
     */
    public Point getPoint(double t){
        if(isZero(t)){
            throw new IllegalArgumentException("t = 0");
        }
        return p0.add(dir.scale(t));
    }

    /**
     * get the direction of the ray
     * @return the direction of the ray
     */
    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }


    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * Find the closest point inrterseted by the ray
     * @param points inter points
     * @return point
     */
    public Point findClosestPoint(List<Point> points){
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;



    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
        if(geoPoints == null){
            return null;
        }

        GeoPoint res = geoPoints.get(0);
        double distance = p0.distance(res.point);
        double d;
        for (var pt: geoPoints){
            d = pt.point.distance(p0);
            if(d < distance){
                distance = d;
                res  = pt;
            }
        }
        return res;

    }


}
