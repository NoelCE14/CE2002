package Lab4;

public class Cylinder extends Circle implements Shape3D{
    private int height;

    public Cylinder(int radius, int height) {
        super(radius);
        this.height = height;
    }

    public int getHeight(){
        return this.height;
    }

    @Override
    public double getVolume() {
        return super.getArea()*this.height;
    }

    @Override
    public double getSurfaceArea() {
        return 2*3.14*super.getRadius()*this.height + 2*super.getArea();
    }

    
    
}
