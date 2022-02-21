package Lab4;

public class Rectangle implements Shape{
    private int length;
    private int breadth;

    public Rectangle (int length, int breadth){
        this.length = length;
        this.breadth = breadth;
    }

    public int getlength(){
        return this.length;
    }
    public int getbreadth(){
        return this.breadth;
    }


    @Override
    public double getArea() {
        return this.length*this.breadth;
    }
    
}
