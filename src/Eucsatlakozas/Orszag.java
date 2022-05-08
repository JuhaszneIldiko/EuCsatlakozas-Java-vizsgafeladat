package Eucsatlakozas;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Orszag {
    public static void main(String[] args) {
        ArrayList<Csatlakozas> eu = new ArrayList<>();
        String uri = "eucsatlakozas.txt";
        List<String> rows = readIn(uri);
        eu = load(rows);
        kiiratas(eu);
        int orsz2007 = feladat4(eu);
        System.out.println(orsz2007);

        System.out.println("5.feladat: ");
        for (Csatlakozas sor:eu) {
            if (sor.orszag.equals("Magyarország")){
                System.out.println(sor.csatlakozas);
            }
        }

        System.out.println("6. feladat: ");
        boolean volt = false;
        for (Csatlakozas sor:eu) {
            if (sor.csatlakozas.getMonth()== Month.MAY){
                volt = true;
                break;
            }
        }
        if(volt){
            System.out.println("Volt májusban csatlakozás");
        }else{
            System.out.println("Nem volt májusban csatlakozás");
        }


    }

    private static void kiiratas(ArrayList<Csatlakozas> eu) {
        System.out.println("3. feladat: ");
        System.out.println(eu.size());
    }
    private static int feladat4(ArrayList<Csatlakozas> eu) {
        System.out.println("4.feladat: ");
        int ossz=0;
        for (Csatlakozas sor:eu) {
            if (sor.csatlakozas.getYear()==2007){
                ossz++;
            }
        }
        return ossz;
    }



    private static ArrayList<Csatlakozas> load(List<String> rows) {
    ArrayList<Csatlakozas> orszagok = new ArrayList<>();
        for (String row:rows) {
            orszagok.add(new Csatlakozas(row));
        }
    return orszagok;
    }

    private static List<String> readIn(String uri) {
            List<String> rows = new ArrayList<>();
            try {
                rows = Files.readAllLines(Paths.get(uri), Charset.forName("UTF-8"));
            } catch (IOException e) {
                System.out.println("Hiba");
            }
            rows.remove(0);
            return rows;
        }
}

class Csatlakozas {
    public final String orszag;
    public LocalDate csatlakozas;

    //public Csatlakozas(String orszag, LocalDate csatlakozas) {
    //  this.orszag = orszag;
    //this.csatlakozas = csatlakozas;
    //}


    public Csatlakozas(String row) {
        String[] slices = row.split(";");
        this.orszag = slices[0];
        try {
            this.csatlakozas = LocalDate.parse(slices[1].replace(".","-"));
        } catch (Exception e) {
            System.out.println("HIba a parsolásban");
        }
    }
    public String getOrszag() {
        return orszag;
    }

    public LocalDate getCsatlakozas() {
        return csatlakozas;
    }

    @Override
    public String toString() {
        return "Csatlakozas{" +
                "orszag='" + orszag + '\'' +
                ", csatlakozas=" + csatlakozas +
                '}';
    }
}
