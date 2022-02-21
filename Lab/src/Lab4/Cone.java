package Lab4;

public class Cone extends Triangle implements Shape3D{

    public Cone(int base, int height) {
        super(base, height);
    }

    @Override
    public double getVolume() {
        return 3.14*super.getbase()*super.getbase()*super.getheight()/3;
    }

    @Override
    public double getSurfaceArea() {
        return 3.14*super.getbase()*(super.getbase()+Math.sqrt(super.getheight()*super.getheight()+super.getbase()*super.getbase()));
    }


    
}
