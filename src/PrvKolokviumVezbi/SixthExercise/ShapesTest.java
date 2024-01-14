package PrvKolokviumVezbi.SixthExercise;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;


interface Scalable
{
    void scale(float scaleFactor);
}

interface Stackable
{
    float weight();
}

enum Color {
    RED, GREEN, BLUE
}

enum ShapeType
{
    Circle,
    Rectangle
}

abstract class Shape implements Scalable, Stackable, Comparable
{
    String id;
    Color color;
    ShapeType type;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    protected abstract double area();

    public ShapeType getType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(((Shape)o).area(),(this.area()));
    }
}

class Circle extends Shape
{
    private float radius;
    private static final double PI = Math.PI;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
        this.type = ShapeType.Circle;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void scale(float scaleFactor) {
        this.radius *= scaleFactor;
    }

    @Override
    public float weight() {
        return (float)area();
    }

    @Override
    protected double area() {
        return radius*radius*PI;
    }
}

class Rectangle extends Shape
{
    private float width;
    private float height;

    public Rectangle(String id, Color color, float width, float height) {
        super(id, color);
        this.width = width;
        this.height = height;
        this.type = ShapeType.Rectangle;
    }

    @Override
    public void scale(float scaleFactor) {
        this.width *= scaleFactor;
        this.height *= scaleFactor;
    }

    @Override
    public float weight() {
        return (float)area();
    }

    @Override
    protected double area() {
        return this.width*this.height;
    }
}

class Canvas {

    ArrayList<Shape> shapes;

    public Canvas() {
        this.shapes = new ArrayList<>();
    }

    void add(String id, Color color, float radius)
    {
        shapes.add(new Circle(id, color, radius));
        sort();
    }
    void add(String id, Color color, float width, float height)
    {
        shapes.add(new Rectangle(id, color, width, height));
        sort();
    }

    void scale(String id, float scaleFactor)
    {
       this.shapes.stream().filter(r -> r.getId().equals(id)).findFirst().ifPresent(r -> r.scale(scaleFactor));
       sort();
    }

    private void sort()
    {
        this.shapes = (ArrayList<Shape>) this.shapes.stream().sorted(Shape::compareTo).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Shape shape : shapes)
        {
            if(shape.getType().equals(ShapeType.Circle))
            {
                stringBuilder.append(String.format("C: %-4s %-9s %10.2f\n", shape.getId(), shape.getColor().toString(), shape.weight()));
            }
            else if(shape.getType().equals(ShapeType.Rectangle))
            {
                stringBuilder.append(String.format("R: %-4s %-9s %10.2f\n", shape.getId(), shape.getColor().toString(), shape.weight()));
            }
        }
        return stringBuilder.toString();
    }
}

public class ShapesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Canvas canvas = new Canvas();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int type = Integer.parseInt(parts[0]);
            String id = parts[1];
            if (type == 1) {
                Color color = Color.valueOf(parts[2]);
                float radius = Float.parseFloat(parts[3]);
                canvas.add(id, color, radius);
            } else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
                float width = Float.parseFloat(parts[3]);
                float height = Float.parseFloat(parts[4]);
                canvas.add(id, color, width, height);
            } else if (type == 3) {
                float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
                System.out.print(canvas);
                canvas.scale(id, scaleFactor);
                System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
                System.out.print(canvas);
            }

        }
    }
}
