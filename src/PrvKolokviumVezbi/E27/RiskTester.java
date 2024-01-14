package PrvKolokviumVezbi.E27;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

class AttacksDefense {
    ArrayList<Integer> attackList;
    ArrayList<Integer> defenseList;

    AttacksDefense(String line) {
        attackList = new ArrayList<>();
        defenseList = new ArrayList<>();
        String[] splitedLine = line.split(";");
        ArrayList<String> stringArrayList = (ArrayList<String>) Arrays.stream(splitedLine).collect(Collectors.toList());

        attackList.add(Integer.parseInt(stringArrayList.get(0).split("\\s+")[0]));
        attackList.add(Integer.parseInt(stringArrayList.get(0).split("\\s+")[1]));
        attackList.add(Integer.parseInt(stringArrayList.get(0).split("\\s+")[2]));

        defenseList.add(Integer.parseInt(stringArrayList.get(1).split("\\s+")[0]));
        defenseList.add(Integer.parseInt(stringArrayList.get(1).split("\\s+")[1]));
        defenseList.add(Integer.parseInt(stringArrayList.get(1).split("\\s+")[2]));
        sort();
    }

    void sort() {
        attackList.sort(Comparator.reverseOrder());
        defenseList.sort(Comparator.reverseOrder());
    }

    public ArrayList<Integer> checkWin() {
        int totalAtack = 0;
        int totalDefense = 0;
        if (attackList.get(0) > defenseList.get(0)) {
            totalAtack+=1;
        }
        else {
            totalDefense+=1;
        }

        if (attackList.get(1) > defenseList.get(1)) {
            totalAtack+=1;
        }
        else{
            totalDefense+=1;
        }
        if (attackList.get(2) > defenseList.get(2)) {
            totalAtack+=1;
        }
        else{
            totalDefense+=1;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(totalAtack);
        arr.add(totalDefense);
        return arr;
    }

}

class Risk {
    ArrayList<AttacksDefense> attacksDefenses;

    void processAttacksData(InputStream is) {
        attacksDefenses = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        bufferedReader.lines().map(AttacksDefense::new).forEach(r -> this.attacksDefenses.add(r));

        for(ArrayList<Integer> item : checkWinForEveryone())
        {
            System.out.printf("%d %d\n", item.get(0), item.get(1));
        }
    }

    private ArrayList<ArrayList<Integer>> checkWinForEveryone() {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        this.attacksDefenses.forEach(r -> arrayList.add(r.checkWin()));
        return arrayList;
    }
}

public class RiskTester {
    public static void main(String[] args) throws FileNotFoundException {
        Risk risk = new Risk();
        File file = new File("G:\\NaprednoProgramiranjeZadaci\\Napredno_Programiranje\\src\\PrvKolokviumVezbi\\E27\\file.txt");
//        risk.processAttacksData(System.in);
        risk.processAttacksData(new FileInputStream(file));
    }
}