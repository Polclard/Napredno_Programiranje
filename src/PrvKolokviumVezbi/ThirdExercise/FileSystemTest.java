//package PrvKolokviumVezbi.ThirdExercise;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//
//interface IFile{
//    String getFileName();
//    long getFileSize();
//    String getFileInfo();
//    void sortBySize();
//    long findLargestFile();
//}
//
//
//class File implements IFile
//{
//
//    private String name;
//    private long size;
//
//    public File(String name, long size) {
//        this.name = name;
//        this.size = size;
//    }
//
//    @Override
//    public String getFileName() {
//        return name;
//    }
//
//    @Override
//    public long getFileSize() {
//        return size;
//    }
//
//    @Override
//    public String getFileInfo() {
//        return toString();//TODO
//    }
//
//    @Override
//    public void sortBySize() {
//
//    }
//
//    @Override
//    public long findLargestFile() {
//        return 0;
//    }
//
//    @Override
//    public String toString() {
//        return "File{" +
//                "name='" + name + '\'' +
//                ", size=" + size +
//                '}';
//    }
//}
//class FileNameExistsException extends RuntimeException
//{
//    public FileNameExistsException(String message) {
//        super(message);
//    }
//}
//
//class Folder implements IFile
//{
//    private String name;
//    private long size;
//    List<IFile> fileList;
//
//    public Folder(String name, long size, List<IFile> fileList) {
//        this.name = name;
//        this.size = size;
//        this.fileList = fileList;
//    }
//
//    void add(IFile file)
//    {
//        if(fileList.stream().anyMatch(r -> r.getFileName().equals(file.getFileName())))
//        {
//            throw new FileNameExistsException(file.getFileName());
//        }
//        else
//        {
//            this.fileList.add(file);
//        }
//    }
//
//    void addFile(IFile file)
//    {
//        try{
//            this.add(file);
//        }catch (FileNameExistsException e)
//        {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public String getFileName() {
//        return name;
//    }
//
//    @Override
//    public long getFileSize() {
//        return this.fileList.stream().mapToLong(IFile::getFileSize).sum();
//    }
//
//    @Override
//    public String getFileInfo() {
//        return toString();
//    }
//
//    @Override
//    public void sortBySize() {
//        this.fileList = this.fileList.stream().sorted().collect(Collectors.toList());
//    }
//
//    @Override
//    public long findLargestFile() {
//        return this.fileList.stream().mapToLong(r -> r.getFileSize()).sum();
//    }
//
//    @Override
//    public String toString() {
//        return "Folder{" +
//                "name='" + name + '\'' +
//                ", size=" + size +
//                ", fileList=" + fileList +
//                '}';
//    }
//}
//
//
//class FileSystem{
//
//}
//
//public class FileSystemTest {
//
//    public static Folder readFolder (Scanner sc)  {
//
//        Folder folder = new Folder(sc.nextLine());
//        int totalFiles = Integer.parseInt(sc.nextLine());
//
//        for (int i=0;i<totalFiles;i++) {
//            String line = sc.nextLine();
//
//            if (line.startsWith("0")) {
//                String fileInfo = sc.nextLine();
//                String [] parts = fileInfo.split("\\s+");
//                try {
//                    folder.addFile(new File(parts[0], Long.parseLong(parts[1])));
//                } catch (FileNameExistsException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//            else {
//                try {
//                    folder.addFile(readFolder(sc));
//                } catch (FileNameExistsException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//
//        return folder;
//    }
//
//    public static void main(String[] args)  {
//
//        //file reading from input
//
//        Scanner sc = new Scanner (System.in);
//
//        System.out.println("===READING FILES FROM INPUT===");
//        FileSystem fileSystem = new FileSystem();
//        try {
//            fileSystem.addFile(readFolder(sc));
//        } catch (FileNameExistsException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("===PRINTING FILE SYSTEM INFO===");
//        System.out.println(fileSystem.toString());
//
//        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
//        fileSystem.sortBySize();
//        System.out.println(fileSystem.toString());
//
//        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
//        System.out.println(fileSystem.findLargestFile());
//
//    }
//}