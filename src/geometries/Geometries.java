package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that represent different form of geometry using the interface implemented
 * @autor Nathan sayag
 */
public class Geometries implements Intersectable{


    /**
     * List of geometries
     */
    private List<Intersectable> _intersectables;


    /**
     * default Constructor for Geometries
     *Initialize list of intersectables objects
     */
    public Geometries() {
        _intersectables = new LinkedList<>(); //Linked list is better use for runtime of O(1) when we add an element to the list
    }

    /**
     * constructor with intersectable in parameters
     * @param geometries
     */
    public Geometries(Intersectable... geometries){
        this();
        add(geometries);
    }

    /**
     * Add interfaces to the list of the geometries
     * @param geometries
     */
    public void add(Intersectable... geometries){
        for(var newItem : geometries)
            _intersectables.add(newItem);
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
