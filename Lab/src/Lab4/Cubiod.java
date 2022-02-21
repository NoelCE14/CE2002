package Lab4;

public class Cubiod extends Rectangle implements Shape3D{
    private int height;

    public Cubiod(int length, int breadth, int height) {
        super(length, breadth);
        this.height = height;
    }

    public int getHeight(){
        return this.height;
    }

    public double getSurfaceArea(){
        return 2*(super.getArea() + this.height*super.getbreadth() + this.height*super.getlength());
    }

    public double getVolume(){
        return super.getArea()*this.height;
    }
    
}
