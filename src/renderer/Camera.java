package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {

    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double distance;
    private double width;
    private double height;
    private ImageWriter imageWriter;
    private RayTracer rayTracer;
    private int numRowPixels;
    private int numColPixels;

    public Point getP0() {
        return p0;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvRight() {
        return vRight;
    }

    public Camera(Point p0, Vector vTo, Vector vUp) {
        if(!isZero(vUp.dotProduct(vTo))){
            throw new IllegalArgumentException("The vectors are not orthogonal !");
        }

        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        vRight = this.vTo.crossProduct(this.vUp).normalize();
    }

    public Camera setVPSize(double width, double height){

        if(isZero(width) || isZero(height)){
            throw new IllegalArgumentException("Error: width or height are zero");
        }
        this.width = width;
        this.height = height;
        return this;
    }


    public Camera setVPDistance(double distance){
        if(isZero(distance)){
            throw new IllegalArgumentException("distance cannot be zero");
        }
        this.distance = distance;
        return this;
    }


    public Ray constructRay(int nX, int nY, int j, int i){
        Point Pc = p0.add(vTo.scale(distance));
        double Rx = alignZero(width/nX);
        double Ry = alignZero(height/nY);

        // Xj = (j - (Nx - 1)/2) * Rx
        double Xj = alignZero((j-((nX - 1d) / 2d)) * Rx);
        //Yi = -(i - (Ny - 1) / 2) *Ry
        double Yi = alignZero(- (i-((nY - 1d) / 2d)) * Ry);

        Point Pij =  Pc;

        if(! isZero(Xj)){
            Pij = Pij.add(vRight.scale(Xj));
        }
        if(! isZero(Yi)){
            Pij = Pij.add(vUp.scale(Yi));
        }
        if(Pij.equals(p0)){
            throw new IllegalArgumentException("Pij = _p0");
        }

        return new Ray( Pij.subtract(p0).normalize(), p0);
    }




    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        if (numColPixels <= 0 || numRowPixels <= 0) {
            return List.of(constructRay(nX, nY, j, i));
        }
        Point Pc = p0.add(vTo.scale(distance));
        List<Ray> rays = new LinkedList<>();
        //ratio
        double Ry = height / nY;
        double Rx = width / nX;
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
        //Pixel[i,j]center:
        Point Pij = Pc;
        if (!isZero(Yi)) {
            Pij = Pij.add(vUp.scale(Yi));
        }
        if (!isZero(Xj)) {
            Pij = Pij.add(vRight.scale(Xj));
        }
        Ry = Ry / numColPixels;
        Rx = Rx / numRowPixels;
        for (int k = 0; k < numRowPixels; k++) {
            for (int l = 0; l < numColPixels; l++) {

                Point point = Pij;
                double Yii = -(k -
                        (numColPixels - 1) / 2d) *
                        Ry;
                double Xjj = -(l -
                        (numRowPixels - 1) / 2d) * Rx;
                if (!isZero(Yii)) {
                    point = point.add(vUp.scale(Yii
                    ));
                }
                if (!isZero(Xjj)) {
                    point = point.add(vRight.scale(
                            Xjj));
                }
                rays.add(new Ray(point.subtract(p0),p0));
            }
        }
        return rays;
    }


    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera writeToImage() {
        if(imageWriter == null){
            throw new MissingResourceException("not set", "Camera","Camera");
        }
        imageWriter.writeToImage();
        return this;
    }

    public void printGrid(int gap, Color intervalColor) {
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                // 800/16 = 50
                if (i % gap == 0) {
                    imageWriter.writePixel(i, j,intervalColor);
                }
                // 500/10 = 50
                else if (j % gap == 0) {
                    imageWriter.writePixel(i, j, intervalColor);
                }
            }
        }
    }

    public Camera setRayTracer(RayTracer rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }


    public Camera renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", rayTracer.getClass().getSimpleName(),"");
            }

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    castRay(nX, nY, i, j);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        return this;
    }



    public Camera setPixels(int amountRowPixels, int amountColumnPixels) {
        this.numRowPixels = amountRowPixels;
        this.numColPixels = amountColumnPixels;
        return this;
    }


    private void castRay(int nX, int nY, int i, int j) {
        List<Ray> rays = constructRays(nX, nY, i, j);
        Color pixelColor = rayTracer.traceRays(rays);
        imageWriter.writePixel(i, j, pixelColor);
    }
}

