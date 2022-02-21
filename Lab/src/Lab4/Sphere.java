package Lab4;

public class Sphere extends Circle implements Shape3D{

    public Sphere(int radius) {
        super(radius);
    }

    @Override
    public double getVolume() {
        
        return getSurfaceArea()*1/3*super.getRadius();
    }

    @Override
    public double getSurfaceArea() {
        
        return 4*super.getArea();
    }
    
}
