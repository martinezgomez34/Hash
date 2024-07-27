import models.HashTable;
import models.HashFunctionFNV_1a;
import models.HashFunctionMultiplicativa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long startTime1, endTime1, totalTime1;
        long startTime2, endTime2, totalTime2;

        HashTable hashTable1 = new HashTable(1000000, new HashFunctionMultiplicativa());
        HashTable hashTable2 = new HashTable(1000000, new HashFunctionFNV_1a());

        String line;
        String splitBy = ",";
        int id = 1;

        try {
            BufferedReader br = new BufferedReader(new FileReader("bussines.csv"));
            startTime1 = System.nanoTime();
            while ((line = br.readLine()) != null) {
                String[] business = line.split(splitBy);
                if (business.length >= 5) {
                    String key = business[1];
                    String value = "ID=" + business[0] + ", Address=" + business[2] +
                            ", City=" + business[3] + ", State=" + business[4];
                    hashTable1.insert(key, value);
                    id++;
                }
            }
            endTime1 = System.nanoTime();
            totalTime1 = endTime1 - startTime1;
            System.out.println("Tiempo de inserción usando HashFunctionMultiplicativa: " + totalTime1 + " nanosegundos");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        id = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader("bussines.csv"));
            startTime2 = System.nanoTime();
            while ((line = br.readLine()) != null) {
                String[] business = line.split(splitBy);
                if (business.length >= 5) {
                    String key = business[1];
                    String value = "ID=" + business[0] + ", Address=" + business[2] +
                            ", City=" + business[3] + ", State=" + business[4];
                    hashTable2.insert(key, value);
                    id++;
                }
            }
            endTime2 = System.nanoTime();
            totalTime2 = endTime2 - startTime2;
            System.out.println("Tiempo de inserción usando HashFunctionFNV_1a: " + totalTime2 + " nanosegundos");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese clave para buscar: ");
        String searchKey = scanner.nextLine().trim();

        String dataFromHashTable1 = hashTable1.search(searchKey);
        String dataFromHashTable2 = hashTable2.search(searchKey);

        if (dataFromHashTable1 != null) {
            System.out.println("Datos en Tabla Hash 1:");
            System.out.println(dataFromHashTable1);
        } else {
            System.out.println("Clave '" + searchKey + "' no encontrada en Tabla Hash 1.");
        }

        if (dataFromHashTable2 != null) {
            System.out.println("Datos en Tabla Hash 2:");
            System.out.println(dataFromHashTable2);
        } else {
            System.out.println("Clave '" + searchKey + "' no encontrada en Tabla Hash 2.");
        }

        scanner.close();
    }
}
