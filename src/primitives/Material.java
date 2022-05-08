package primitives;

public class Material {

    /**
     * the diffuse reduce factor
     */
    public Double3 Kd = Double3.ZERO;
    /**
     * the specular reduce factor
     */
    public Double3 Ks = Double3.ZERO;
    /**
     * The amount of shininess that goes from the object in which the light strikes
     */
    public int nShininess = 0;


    public Material setKd(double kd) {
        Kd = new Double3(kd);
        return this;
    }

    public Material setKs(double ks) {
        Ks = new Double3(ks);
        return this;
    }





    /**
     * setter for nshininess
     * @param nShininess
     * @return
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }





}
