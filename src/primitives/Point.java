package primitives;

import java.util.Objects;

/**
 * Point class that represent a Point in 3D world
 */
public class Point {

    public static Point ZERO = new Point(0,0,0);
    /**
     * Point in 3D
     */
    final Double3 xyz;

    /**
     * Constructor of Double3 point
     *
     * @param _xyz
     */
    public Point(Double3 _xyz) {
        xyz = _xyz;
    }

    /**
     * Constructor with 3 value for x,y,z
     *
     * @param x double
     * @param y double
     * @param z double
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * function that add a point from a Vector
     *
     * @param other vector gotten
     * @return New Point from the Vector
     */
    public Point add(Vector other) {
        Double3 help = xyz.add(other.xyz);
        return new Point(help.d1, help.d2, help.d3);
    }

    /**
     * Substract a vector from a Point
     *
     * @param p1 Point to substract from
     * @return
     */
    public Vector subtract(Point p1) {
        Double3 help = xyz.subtract(p1.xyz);
        return new Vector(help.d1, help.d2, help.d3);
    }

    /**
     * distance squared of 2 points
     *
     * @param p1 Point
     * @return the disatnce of 2 point pow by 2
     */
    public double distanceSquared(Point p1) {
        double x = xyz.d1 - p1.xyz.d1;
        double y = xyz.d2 - p1.xyz.d2;
        double z = xyz.d3 - p1.xyz.d3;
        return x * x + y * y + z * z;
    }

    /**
     * Distance between two points
     *
     * @param p1 Point
     * @return sqrt of the distance squared
     */
    public double distance(Point p1) {
        return Math.sqrt(distanceSquared(p1));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * getters to get coordinate of X, Y , Z
     * @return coordinate of X, Y , Z
     */
    public double getX() {
        return xyz.d1;
    }
    public double getY() {
        return xyz.d2;
    }
    public double getZ() {
        return xyz.d3;
    }

}