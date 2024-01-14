package PrvKolokviumVezbi.FirstExercise;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Canvas implements Comparable {
    private String canvasId;
    private List<Integer> sides;

    public Canvas(String canvasId, List<Integer> sides) {
        this.canvasId = canvasId;
        this.sides = sides;
    }

    public Canvas(String line)
    {
        this.sides = new ArrayList<>();
        String [] arr = line.split("\\s+");
        List<String> splitLine = new ArrayList<>(Arrays.stream(arr).collect(Collectors.toList()));
        this.canvasId = splitLine.remove(0);
        splitLine.forEach(e -> sides.add(Integer.parseInt(e)));
    }

    public int totalSquaresPerimeter()
    {
        return sides.stream().mapToInt(r -> r*4).sum();
    }

    public List<Integer> getSides() {
        return sides;
    }

    public String getCanvasId() {
        return canvasId;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.totalSquaresPerimeter(), ((Canvas)o).totalSquaresPerimeter());
    }

    @Override
    public String toString() {

        return String.format("%s ", canvasId) +
                String.format("%d ", sides.size()) +
                String.format("%d ", totalSquaresPerimeter());
    }
}

class ShapesApplication
{

    List<Canvas> canvases;

    public ShapesApplication()
    {
        canvases = new ArrayList<Canvas>();
    }
    public int readCanvases(InputStream inputStream){
        //Input line: < 364fbe94 24 30 22 33 32 30 37 18 29 27 33 21 27 26 >
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        bf.lines().map(Canvas::new).forEach(r -> canvases.add(r));
        return canvases.stream().mapToInt(r -> r.getSides().size()).sum();
    }


    public void printLargestCanvasTo(OutputStream outputStream)
    {
        PrintWriter printWriter = new PrintWriter(outputStream);
        Optional<Canvas> Max = canvases.stream().max(Canvas::compareTo);
        Max.ifPresent(printWriter::println);
        printWriter.flush();
        printWriter.close();
    }

}
public class Shapes1Test {

    public static void main(String[] args) throws FileNotFoundException {
        ShapesApplication shapesApplication = new ShapesApplication();
        File file = new File("G:\\NaprednoProgramiranjeZadaci\\Napredno_Programiranje\\src\\PrvKolokviumVezbi\\PrvaZadaca\\file.txt");
        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(new FileInputStream(file)));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}