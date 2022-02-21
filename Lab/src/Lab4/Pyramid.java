package Lab4;

public class Pyramid extends Triangle implements Shape3D{

    public Pyramid(int base, int height) {
        super(base, height);
    }

    public int getBaseSquare(){
        return super.getbase()*super.getbase();
    }

    @Override
    public double getVolume() {       
        return (1/3.0)*getBaseSquare()*super.getheight();
    }

    @Override
    public double getSurfaceArea() {        
        return getBaseSquare() + 2*super.getbase()*Math.sqrt(getBaseSquare()/4+super.getheight()*super.getheight());
    }


    
}
