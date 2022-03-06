package primitives;

import java.util.Objects;

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
}
