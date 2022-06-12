package unittests.renderer;

import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class Xylophone {

    private Scene scene = new Scene("Test scene");


    /**
     * Image of Xylophonoe using anialiasing multithreading
     */
    @Test
    public void Xylophone(){
        Camera camera = new Camera(new Point(-6000, -6000, 3200),
                new Vector(1, 1, -1d/2), new Vector(1, 1, 4)) //
                .setVPSize(200, 200).setVPDistance(1700);

        Scene scene = new Scene("Test scene")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.05)))
                .setBackground(new Color(25,50,100));

        scene.geometries.add( //

                /**
                 * Body
                 */
                new Polygon(
                        new Point(-300,200,75),
                        new Point(-300,-200,75),
                        new Point(400,-140,75),
                        new Point(400,140,75))//
                        .setEmission(new Color(42, 80, 87)) //
                        .setMaterial(new Material().setKr(0.4).setKs(0.1).setShininess(10)),//


                new Polygon(
                        new Point(-300,200,0),
                        new Point(-300,-200,0),
                        new Point(400,-140,0),
                        new Point(400,140,0))//
                        .setEmission(new Color(42, 115, 87)) //
                        .setMaterial(new Material().setKr(0.4).setKs(0.1).setShininess(10)),



                new Polygon(
                        new Point(-300,200,0),
                        new Point(-300,-200,0),
                        new Point(-300,-200,75),
                        new Point(-300,200,75))//
                        .setEmission(new Color(42, 115, 87)) //
                        .setMaterial(new Material().setKr(0.4).setKs(0.1).setShininess(10)),


                new Polygon(
                        new Point(400,-140,75),
                        new Point(400,140,75),
                        new Point(400,140,0),
                        new Point(400,-140,0))//
                        .setEmission(new Color(42, 115, 87)) //
                        .setMaterial(new Material().setKr(0.4).setKs(0.1).setShininess(10)),





                new Polygon(
                        new Point(-300,-200,75),
                        new Point(-300,-200,0),
                        new Point(400,-140,0),
                        new Point(400,-140,75))//
                        .setEmission(new Color(42, 115, 87)) //
                        .setMaterial(new Material().setKr(0.1).setKs(0.1).setShininess(10)),



                new Polygon(
                        new Point(-300,200,75),
                        new Point(-300,200,0),
                        new Point(400,140,0),
                        new Point(400,140,75))//
                        .setEmission(new Color(42, 115, 87)) //
                        .setMaterial(new Material().setKr(0.1).setKs(0.1).setShininess(10)),



                /**
                 * Pads
                 */



                new Polygon(

                        new Point(-260,180,75.2),
                        new Point(-260,-180,75.2),
                        new Point(-200,-180,75.2),
                        new Point(-200,180,75.2))//
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),



                new Polygon(

                        new Point(-180,173,75.2),
                        new Point(-180,-173,75.2),
                        new Point(-120,-173 ,75.2),
                        new Point(-120,173,75.2))//
                        .setEmission(new Color(255,150,0)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),


                new Polygon(

                        new Point(-100,166,75.2),
                        new Point(-100,-166,75.2),
                        new Point(-40,-166 ,75.2),
                        new Point(-40,166,75.2))//
                        .setEmission(new Color(YELLOW)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),


                new Polygon(

                        new Point(-20,159,75.2),
                        new Point(-20,-159,75.2),
                        new Point(40,-159 ,75.2),
                        new Point(40,159,75.2))//
                        .setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),


                new Polygon(

                        new Point(60,151,75.2),
                        new Point(60,-151,75.2),
                        new Point(120,-151 ,75.2),
                        new Point(120,151,75.2))//
                        .setEmission(new Color(CYAN)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),


                new Polygon(

                        new Point(140,144,75.2),
                        new Point(140,-144,75.2),
                        new Point(200,-144 ,75.2),
                        new Point(200,144,75.2))//
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),



                new Polygon(

                        new Point(220,137,75.2),
                        new Point(220,-137,75.2),
                        new Point(280,-137 ,75.2),
                        new Point(280,137,75.2))//
                        .setEmission(new Color(MAGENTA)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),


                new Polygon(

                        new Point(300,130,75.2),
                        new Point(300,-130,75.2),
                        new Point(360,-130 ,75.2),
                        new Point(360,130,75.2))//
                        .setEmission(new Color(PINK)) //
                        .setMaterial(new Material().setKr(1).setKs(0.1).setShininess(10)),


                /**
                 * floor
                 */


                new Polygon(
                        new Point(-400,300,-10),
                        new Point(-400,-330,-10),
                        new Point(450,-330,-10),
                        new Point(450,350,-10))//
                        .setEmission(new Color(197, 80, 87).reduce(2)) //
                        .setMaterial(new Material().setKr(0.1).setKs(0.1).setShininess(10)),


                new Polygon(
                        new Point(-400,300,250),
                        new Point(-400,300,-10),
                        new Point(450,350,-10),
                        new Point(450,350,250))//
                        .setEmission(new Color(197, 80, 87).reduce(3)) //
                        .setMaterial(new Material().setKr(0.1).setKs(0.1).setShininess(10)),





                new Cylinder(new Ray(new Vector(0, 0, -1), new Point(-350,280,-10)), 15d, 120).setEmission(new Color(53, 47, 70))// Foot
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Vector(0, 0, -1), new Point(-350,-306,-10)), 15d, 120).setEmission(new Color(53, 47, 70))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Vector(0, 0, -1), new Point(410,-306,-10)), 15d, 120).setEmission(new Color(53, 47, 70))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Vector(0, 0, -1), new Point(410,280,-10)), 15d, 120).setEmission(new Color(53, 47, 70))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),



                new Cylinder(new Ray(new Vector(1, 0, 0), new Point(-100, 123, 100)), 7d, 185).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Vector(1, 0, 0), new Point(-180, -77, 100)), 7d, 180).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),

                new Sphere(new Point(-100, 123, 100), 26d).setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(20).setKt(0.2)),//
                new Sphere(new Point(-180, -77, 100), 26d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(20).setKt(0.3)),







                new Cylinder(new Ray(new Vector(-0.77, -2.53, 0), new Point(350, -200, 10)), 7d, 185).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Vector(-0.51, -2.39, 0), new Point(260,-220,10)), 7d, 185).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Vector(-0.5, -2.33, 0), new Point(150, -240, 10)), 7d, 185).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),
                new Cylinder(new Ray(new Vector(0.23, -2.73, 0), new Point(40,-250,10)), 7d, 185).setEmission(new Color(BLACK))//
                        .setMaterial(new Material().setKd(0.4).setKs(0.25).setShininess(30)),

                new Sphere(new Point(350, -200, 10), 20d).setEmission(new Color(GREEN).reduce(3)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(20).setKt(0.2)),//
                new Sphere(new Point(260, -220, 10), 20).setEmission(new Color(YELLOW).reduce(3)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(20).setKt(0.2)),//
                new Sphere(new Point(150, -240, 10), 20).setEmission(new Color(WHITE).reduce(3)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(1).setKt(0.01)),//
                new Sphere(new Point(40, -250, 10), 20).setEmission(new Color(2, 9, 197)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(20).setKt(0.2))//


        );

        scene.lights.add(new DirectionalLight(new Color(1000,800,400),new Vector(891,-828,-401)));

        scene.lights.add(new DirectionalLight(new Color(800,600,300),new Vector(-600,400,-300)));

        scene.lights.add(new PointLight(new Color(700, 400, 400), new Point(-550, -300, 500)).setKl(4E-5).setKq(0.0000006));
        scene.lights.add(new SpotLight(new Color(700, 400, 400),
                new Point(-0, -0, -5000), new Vector(0, 0, 1)) //
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add(new SpotLight( new Color(800, 500, 0),
                new Point(-50, -50, 25), new Vector(1, 1, -0.5))
                .setNarrowBeam(10).setKl(0.001).setKq(0.00004));
        scene.lights.add(new SpotLight( new Color(800, 500, 400),
                new Point( 0, 0, 500), new Vector(0, 0, -1))
                .setNarrowBeam(30).setKl(0.001).setKq(0.00004));



        ImageWriter imageWriter = new ImageWriter("MyImage", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .setPixels(5,5)
                .renderImage() //
                .writeToImage();
    }

}
