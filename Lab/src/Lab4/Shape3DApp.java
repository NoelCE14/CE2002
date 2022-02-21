package Lab4;

public class Shape3DApp {
    public static void main(String args[]){
        Sphere s = new Sphere(10);
        Pyramid p = new Pyramid(20, 25);
        Cubiod c = new Cubiod(50, 20, 10);
        Cone C = new Cone(10, 20);
        Cylinder y = new Cylinder(10, 20);

        System.out.println("The surface area of Sphere is " + s.getSurfaceArea());
        System.out.println("The Volume of Sphere is " + s.getVolume());

        System.out.println("The surface area of Pyramid is " + p.getSurfaceArea());
        System.out.println("The Volume of Pyramid is " + p.getVolume());

        System.out.println("The surface area of Cubiod is " + c.getSurfaceArea());
        System.out.println("The Volume of Cubiod is " + c.getVolume());

        System.out.println("The surface area of Cone is " + C.getSurfaceArea());
        System.out.println("The Volume of Cone is " + C.getVolume());

        System.out.println("The surface area of Cylinder is " + y.getSurfaceArea());
        System.out.println("The Volume of Cylinder is " + y.getVolume());
    }
    
}
