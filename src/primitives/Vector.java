package primitives;

import java.util.Objects;

/**
 * Vector class that represent a Vector in 3D world
 */
public class Vector extends Point {

    /**
     * Constructor of Vector from 3 numbers
     * @param x double
     * @param y double
     * @param z double
     */
    public Vector(double x, double y, double z) {
        super(x,y,z);
        if(xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0");
    }

    /**
     * Constructor of Vector from a double3 class
     * @param xyz Double3
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if(this.xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector 0");

    }

    /**
     *get a vector and do an vectorial adddition between them
     * @param other Vector added
     * @return new vector after vectorial add
     */
    public Vector add(Vector other) {
        return new Vector((this.xyz.add(other.xyz)));
    }


    /**
     * function that procure dot product function with another Vector
     * @param v1 Vector applying dot product to
     * @return Value of the dot product result
     */
    public double dotProduct(Vector v1) {
        Double3 temp = this.xyz.product(v1.xyz);
        return temp.d1 + temp.d2 + temp.d3;
    }

    /**
     * function that procure cross product function with another Vector
     * @param v1 Vector applying cross product to
     * @return new Vector cross producted
     */
    public Vector crossProduct(Vector v1) {
        return new Vector(
                this.xyz.d2 * v1.xyz.d3 - this.xyz.d3 * v1.xyz.d2,
                this.xyz.d3 * v1.xyz.d1 - this.xyz.d1 * v1.xyz.d3,
                this.xyz.d1 * v1.xyz.d2 - this.xyz.d2 * v1.xyz.d1
        );
    }

    /**
     * function that get scalar number and multiply each axis of vector by that scalar
     * @param rhs scalar
     * @return  vector multiplied by this scalar
     */
    public Vector scale(double rhs){
        return  new Vector(xyz.scale(rhs));
    }

    /**
     * the squared length of the vector
     * @return x*x +y*y + z*z
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * the length of the vector (x, y, z)
     * @return sqrt of lenghsquared
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * func that return the normal of this vector of (x, y, z)
     * @return (x, y, z) / |(x, y, z)| = (x, y, z) / sqrt(x*x + y*y + z*z)
     */
    public Vector normalize() {
        return new Vector(this.xyz.reduce(length()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }
    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }
}
