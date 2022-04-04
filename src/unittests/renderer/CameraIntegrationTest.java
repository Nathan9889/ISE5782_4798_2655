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
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected){
    int count = 0;
    List<Point> allPoints = null;


    cam.setVPSize(3,3);
    cam.setVPDistance(1);
     int nX = 3;
     int nY = 3;

     for(int i = 0 ; i < nY; ++i){
         for(int j = 0; j < nX; ++j){
             var intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));
             if(intersections != null){
                 if(allPoints == null){
                     allPoints = new LinkedList<>();
                 }
                 allPoints.addAll(intersections);
             }
             count += intersections == null ? 0 : intersections.size();

         }
     }

    System.out.format("there are %d points: %n", count);

     if(allPoints != null){
        for(var item: allPoints){
            System.out.println(item);
        }
     }
     System.out.println();
     assertEquals(expected, count,"wrong amount of intersections");
}





}
