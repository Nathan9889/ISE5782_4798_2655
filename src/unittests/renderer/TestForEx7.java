package unittests.renderer;

import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class TestForEx7 {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of 3 spheres on a triangle mirror
     */
    @Test
    public void allFeature(){
        Camera camera = new Camera(new Point(0, 0, 5000),
                new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        Scene scen = new Scene("Test scene")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)))
                .setBackground(new Color(94,97,99));

        scen.geometries.add( //
                new Triangle(new Point(450, -350, 0),
                        new Point(-500, -400, 0), new Point(0, 500, -80)) //
                        .setEmission(new Color(75,75,75)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),//
                new Sphere(new Point(-100, 100, 300), 100d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.2).setShininess(30).setKt(0.6)),//
                new Sphere(new Point(100, -200, 400), 100).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKt(0.8)),//
                new Sphere(new Point(200, 100, 800), 50d).setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(20).setKt(0.3)));

        scen.lights.add(new SpotLight(new Color(700, 400, 400),
                new Point(0, 0, 900), new Vector(0.5, 2.5, -7.5)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("TestForEx7", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scen)) //
                .renderImage() //
                .writeToImage();
    }


































    /**
     * Produce a picture of 3 spheres on a triangle mirror
     */
    @Test
    public void Xylo(){
        Camera camera = new Camera(new Point(-6000, -6000, 3200),
                new Vector(1, 1, -1d/2), new Vector(1, 1, 4)) //
                .setVPSize(200, 200).setVPDistance(2000);

        Scene scen = new Scene("Test scene")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)))
                .setBackground(new Color(94,97,99));

        scen.geometries.add( //
                new Polygon(
                        new Point(-300,200,75),
                        new Point(-300,-200,75),
                        new Point(400,-140,75),
                        new Point(400,140,75))//
                        .setEmission(new Color(75,75,75)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),//


                new Polygon(
                        new Point(-300,200,-75),
                        new Point(-300,-200,-75),
                        new Point(400,-140,-75),
                        new Point(400,140,-75))//
                        .setEmission(new Color(75,75,75)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),



                new Polygon(
                        new Point(-300,200,-75),
                        new Point(-300,-200,-75),
                        new Point(-300,-200,75),
                        new Point(-300,200,75))//
                        .setEmission(new Color(75,75,75)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),


                new Polygon(
                        new Point(-300,-200,75),
                        new Point(-300,-200,-75),
                        new Point(400,-140,-75),
                        new Point(400,-140,75))//
                        .setEmission(new Color(75,75,75)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),


                new Sphere(new Point(-100, 100, 300), 100d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.2).setShininess(30).setKt(0.6)),//
                new Sphere(new Point(100, -200, 400), 100).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKt(0.8)),//
                new Sphere(new Point(200, 100, 800), 50d).setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(20).setKt(0.3)));

        scen.lights.add(new SpotLight(new Color(700, 400, 400),
                new Point(0, 0, 1200), new Vector(0.5, 2.5, -7.5)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("Xylo", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scen)) //
                .renderImage() //
                .writeToImage();
    }


}
