import java.util.Scanner;

public class Masukan {
    private final Scanner scanner;

    public Masukan() {
        this.scanner = new Scanner(System.in);
    }

    public String bacaTeks(String pertanyaan) {
        System.out.print(pertanyaan + ": ");
        return scanner.nextLine().trim();
    }

    public int bacaAngka(String pertanyaan, int min, int max) {
        while (true) {
            String teks = bacaTeks(pertanyaan + " (" + min + " sampai " + max + ")");
            Integer nilai = ubahKeAngka(teks, min, max);
            if (nilai != null) {
                return nilai;
            }
            System.out.println("Input tidak valid, coba lagi.");
        }
    }

    public Integer bacaAngkaOpsional(String pertanyaan, int min, int max) {
        while (true) {
            String teks = bacaTeks(pertanyaan + " [" + min + " sampai " + max + "]");
            if (teks.isEmpty()) {
                return null;
            }
            Integer nilai = ubahKeAngka(teks, min, max);
            if (nilai != null) {
                return nilai;
            }
            System.out.println("Input tidak valid, coba lagi.");
        }
    }

    public double bacaDesimal(String pertanyaan) {
        while (true) {
            Double nilai = ubahKeDesimal(bacaTeks(pertanyaan));
            if (nilai != null) {
                return nilai;
            }
            System.out.println("Input tidak valid, coba lagi.");
        }
    }

    public Double bacaDesimalOpsional(String pertanyaan) {
        while (true) {
            String teks = bacaTeks(pertanyaan);
            if (teks.isEmpty()) {
                return null;
            }
            Double nilai = ubahKeDesimal(teks);
            if (nilai != null) {
                return nilai;
            }
            System.out.println("Input tidak valid, coba lagi.");
        }
    }

    public void tutup() {
        scanner.close();
    }

    private Integer ubahKeAngka(String teks, int min, int max) {
        try {
            int nilai = Integer.parseInt(teks);
            return (nilai >= min && nilai <= max) ? nilai : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double ubahKeDesimal(String teks) {
        try {
            double nilai = Double.parseDouble(teks);
            return nilai >= 0 ? nilai : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
