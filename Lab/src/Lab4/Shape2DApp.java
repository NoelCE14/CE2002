package Lab4;

public class Shape2DApp {
    public static void main (String[] args){
        Circle c = new Circle(10);
        Triangle t = new Triangle(20, 25);
        Rectangle r = new Rectangle(50, 20);
        Square s = new Square(10);
        s.getbreadth();

        double totalArea = c.getArea() + t.getArea() + r.getArea();
        System.out.println("The total Area is " + totalArea);
    }
}
