package PrvKolokviumVezbi.SecondExercise;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

enum ShapeType{
    S,
    C
}

class Shape implements Comparable
{
    private ShapeType shapeType;
    private double length;
    private double maxArea;
    private static final double PI = Math.PI;

    public Shape(ShapeType shapeType, double length, double maxArea){
        this.maxArea = maxArea;
        this.shapeType = shapeType;
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public double area()
    {
        if(shapeType.equals(ShapeType.C))
        {
            return (length*length) * PI;
        }
        else
        {
            return (length*length);
        }
    }


    public ShapeType getShapeType() {
        return shapeType;
    }

    public double perimeter()
    {
        if(shapeType == ShapeType.C)
        {
            return 2*length*PI;
        }
        else if(shapeType == ShapeType.S)
        {
            return 4*length;
        }
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(this.area(), ((Shape)o).area());
    }
}


class Canvas implements Comparable
{
    double maxArea;
    private String canvasId;
    private List<Shape> shapeList;

    public Canvas(String canvasId, List<Shape> shapeList) {
        this.canvasId = canvasId;
        this.shapeList = shapeList;
    }

    public int totalSquares()
    {
        return this.shapeList.stream().filter(r -> r.getShapeType().equals(ShapeType.S)).collect(Collectors.toList()).size();
    }

    public int totalCircles()
    {
        return this.shapeList.stream().filter(r -> r.getShapeType().equals(ShapeType.C)).collect(Collectors.toList()).size();
    }

    public double minArea()
    {
        return this.shapeList.stream().min(Shape::compareTo).get().area();
    }

    public double maxArea()
    {
        return this.shapeList.stream().max(Shape::compareTo).get().area();
    }

    public double averageArea()
    {
        return this.shapeList.stream().mapToDouble(Shape::area).sum() / this.shapeList.size();
    }
    public Canvas(String line, double maxArea)
    {
        this.shapeList = new ArrayList<>();
        this.maxArea = maxArea;
        String [] str = line.split("\\s+");
        List<String> splitedString = new ArrayList<>(Arrays.stream(str).collect(Collectors.toList()));
        this.canvasId = splitedString.remove(0);
        List<String> shapeTypes = new ArrayList<>();
        List<Double> shapeLengths = new ArrayList<>();

        for(int i = 0; i < splitedString.size(); i++)
        {
            if(i % 2 == 0)
            {
                shapeTypes.add(splitedString.get(i));
            }
            else {
                shapeLengths.add(Double.parseDouble(splitedString.get(i)));
            }
        }

        if(shapeLengths.size() == shapeTypes.size())
        {
            for(int i = 0; i < shapeLengths.size(); i++)
            {
                Shape newShape = new Shape(shapeTypes.get(i).equals("C") ? ShapeType.C : ShapeType.S, shapeLengths.get(i), maxArea);
                if(newShape.area() > maxArea)
                {
                    throw new IrregularCanvasException(String.format("Canvas %s has a shape with area larger than %.2f", canvasId, maxArea));
                }
                else
                {
                    this.shapeList.add(newShape);
                }
            }

        }
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public String getCanvasId() {
        return canvasId;
    }

    private double sumOfAllAreas()
    {
        return this.shapeList.stream().mapToDouble(r -> r.area()).sum();
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(this.sumOfAllAreas(), ((Canvas) o).sumOfAllAreas());
    }

    @Override
    public String toString() {
        return String.format("%s %d %d %d %.2f %.2f %.2f\n",canvasId,shapeList.size(),totalCircles(),totalSquares(),minArea(),maxArea(),averageArea());
    }
}

class IrregularCanvasException extends RuntimeException
{
    public IrregularCanvasException(String message) {
        super(message);
    }
}

class ShapesApplication
{
    private double maxArea;
    List<Canvas> canvasList;
    ShapesApplication(double maxArea)
    {
        this.maxArea = maxArea;
        canvasList = new ArrayList<>();
    }

    void readCanvases(InputStream inputStream) throws IrregularCanvasException
    {
        canvasList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        bufferedReader.lines().map(new Function<String, Object>() {
            @Override
            public Object apply(String s) {
                try{
                    Canvas newCanvas = new Canvas(s, maxArea);
                    return newCanvas;
                }
                catch (IrregularCanvasException e)
                {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        }).forEach(r -> this.canvasList.add((Canvas)r));
        this.canvasList = this.canvasList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }


    public void printCanvases (OutputStream os)
    {
        PrintWriter printWriter = new PrintWriter(os);
        try{
            List<Canvas> printCanvas = (List<Canvas>) this.canvasList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            printCanvas.forEach(printWriter::print);
            printWriter.flush();
            printWriter.close();
        }catch (NullPointerException e)
        {
            e.getMessage();
        }
    }

}



public class Shapes2Test {

    public static void main(String[] args) throws FileNotFoundException {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        File file = new File("G:\\NaprednoProgramiranjeZadaci\\Napredno_Programiranje\\src\\PrvKolokviumVezbi\\VtoraZadaca\\file.txt");

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
//      shapesApplication.readCanvases(System.in);
        shapesApplication.readCanvases(new FileInputStream(file));

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);


    }
}
