package PrvKolokviumVezbi.E24;

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

    public int checkWin() {
        if (attackList.get(0) > defenseList.get(0) &&
                attackList.get(1) > defenseList.get(1) &&
                attackList.get(2) > defenseList.get(2))
            return 1;
        return 0;
    }

}

class Risk {
    ArrayList<AttacksDefense> attacksDefenses;

    int processAttacksData(InputStream is) {
        attacksDefenses = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        bufferedReader.lines().map(AttacksDefense::new).forEach(r -> this.attacksDefenses.add(r));
        return checkWinForEveryone();
    }

    private int checkWinForEveryone()
    {
        return this.attacksDefenses.stream().mapToInt(AttacksDefense::checkWin).sum();
    }
}

public class RiskTester {
    public static void main(String[] args) throws FileNotFoundException {

        Risk risk = new Risk();
        File file = new File("G:\\NaprednoProgramiranjeZadaci\\Napredno_Programiranje\\src\\PrvKolokviumVezbi\\E24\\file.txt");
//        System.out.println(risk.processAttacksData(System.in));
        System.out.println(risk.processAttacksData(new FileInputStream(file)));

    }
}