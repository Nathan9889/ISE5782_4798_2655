package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {

    private Point _p0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;
    private double _distance;
    private double _width;
    private double _height;
    private ImageWriter imageWriter;
    private RayTracer rayTracer;


    public Point getP0() {
        return _p0;
    }

    public Vector getvUp() {
        return _vUp;
    }

    public Vector getvTo() {
        return _vTo;
    }

    public Vector getvRight() {
        return _vRight;
    }

    public Camera(Point p0, Vector vTo, Vector vUp) {
        if(!isZero(vUp.dotProduct(vTo))){
            throw new IllegalArgumentException("The vectors are not orthogonal !");
        }

        _p0 = p0;
        _vUp = vUp.normalize();
        _vTo = vTo.normalize();
        _vRight = _vTo.crossProduct(_vUp).normalize();
    }

    public Camera setVPSize(double width, double height){

        if(isZero(width) || isZero(height)){
            throw new IllegalArgumentException("Error: width or height are zero");
        }
        _width = width;
        _height = height;
        return this;
    }


    public Camera setVPDistance(double distance){
        if(isZero(distance)){
            throw new IllegalArgumentException("distance cannot be zero");
        }
        _distance = distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i){
       Point Pc = _p0.add(_vTo.scale(_distance));
       double Rx = alignZero(_width/nX);
       double Ry = alignZero(_height/nY);

       // Xj = (j - (Nx - 1)/2) * Rx
       double Xj = alignZero((j-((nX - 1d) / 2d)) * Rx);
       //Yi = -(i - (Ny - 1) / 2) *Ry
        double Yi = alignZero(- (i-((nY - 1d) / 2d)) * Ry);

        Point Pij =  Pc;

        if(! isZero(Xj)){
            Pij = Pij.add(_vRight.scale(Xj));
        }
        if(! isZero(Yi)){
            Pij = Pij.add(_vUp.scale(Yi));
        }
        if(Pij.equals(_p0)){
            throw new IllegalArgumentException("Pij = _p0");
        }

        return new Ray( Pij.subtract(_p0).normalize(), _p0);
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
        int nx = imageWriter.getNx();;
        int ny = imageWriter.getNy();;

        for (int i = 0; i < nx; i++) {
            for (int j = 0; j < ny  ; j++) {
                Ray ray = constructRay(nx,ny,i,j);
                Color pixelcolor = rayTracer.traceRay(ray);
                imageWriter.writePixel(i,j,pixelcolor);
            }
        }
        return this;

    }
}

