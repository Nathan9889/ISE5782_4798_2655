package geometries;

import primitives.*;
import java.util.List;
import java.util.Objects;

/**
 * Interface for fiding intersections points
 */
public abstract class Intersectable {

    /**
     *
     * @param ray {@link Ray} point to object
     * @return List of intersections {@link Point}s
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray,Double.MAX_VALUE);
    }

    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        return findGeoIntersectionsHelper(ray,maxDistance);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);



    public static class GeoPoint {
        public final Geometry geometry;
        public final Point point;

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }


}
