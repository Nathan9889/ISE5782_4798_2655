package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import renderer.Camera;
import primitives.*;
import geometries.*;
import java.util.LinkedList;
import java.util.List;

public class CameraIntegrationTest {

    /**
     *
     * @param cam camera for test
     * @param geo body
     * @param expected num of intersections
     */
    private void assertCountIntersections(int expected, Camera cam, Intersectable geo){
        cam.setVPSize(3,3).setVPDistance(1);

        int nX = 3, nY = 3, count = 0;
        for (int i = 0 ;i < nX; i++){
            for (int j = 0 ; j < nY; j++){
                var ray = cam.constructRay(nX,nY,j,i);
                var intersections = geo.findIntersections(ray);

                if(intersections != null){
                    count += intersections.size();
                }
            }
        }
        assertEquals(expected,count,"incorrect number of intersections");


}

    @Test
    public void cameraSphereIntergration(){
        Camera cam1 = new Camera(
                new Point(0,0,0),
                new Vector(0,0,-1),
                new Vector(0,1,0)
        );
        Camera cam2 = new Camera(
                new Point(0,0,0.5),
                new Vector(0,0,-1),
                new Vector(0,1,0)
        );

        // TC01:  Sphere 2 points
        assertCountIntersections(2,cam1, new Sphere(new Point(0, 0, -3),1));

       // TC02: Sphere 18 points
        assertCountIntersections(18,cam2, new Sphere( new Point(0, 0, -2.5),2.5));

        // TC03: Sphere 10 points
        assertCountIntersections(10,cam2, new Sphere( new Point(0, 0, -2),2));

        // TC04: Inside Sphere 9 points
        assertCountIntersections(9,cam2, new Sphere( new Point(0, 0, -1),4));

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(0,cam1, new Sphere( new Point(0, 0, 1),0.5));

}



    @Test
    public void cameraPlaneIntegration(){

        Camera cam = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Plane against camera 9 points
        assertCountIntersections(9,cam, new Plane( new Vector(0, 0, 1),new Point(0, 0, -5)));

        // TC02: Plane with small angle 9 points
        assertCountIntersections(9,cam, new Plane( new Vector(0, 1, 2),new Point(0, 0, -5)));

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(6,cam, new Plane(new Vector(0, 1, 1),new Point(0, 0, -5)));

        // TC04: Beyond Plane 0 points
        assertCountIntersections(6,cam, new Plane( new Vector(0, 1, 1),new Point(0, 0, -5)));


    }





    @Test
    public void cameraTriangleIntegration(){
        Camera cam = new Camera(new Point(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small triangle 1 point
        assertCountIntersections(1,cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2)));

        // TC02: Medium triangle 2 points
        assertCountIntersections(2,cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)));
    }

}
