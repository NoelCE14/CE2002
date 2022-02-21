package Lab4;

public class Triangle implements Shape{
    private int base;
    private int height;

    public Triangle (int base, int height){
        this.base = base;
        this.height = height;
    }

    public int getbase(){
        return this.base;
    }
    public int getheight(){
        return this.height;
    }


    @Override
    public double getArea() {
        return 0.5*this.base*this.height;
    }
}
