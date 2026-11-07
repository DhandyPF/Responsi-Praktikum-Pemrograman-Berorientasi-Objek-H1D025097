import java.util.ArrayList;
import java.util.List;

public class RepositoriPasien {
    private final List<Pasien> daftarPasien;
    private int idBerikutnya;

    public RepositoriPasien() {
        this.daftarPasien = new ArrayList<>();
        this.idBerikutnya = 1;
    }

    public String getIdBerikutnya() {
        return String.valueOf(idBerikutnya);
    }

    public void tambah(Pasien pasien) {
        daftarPasien.add(pasien);
        idBerikutnya++;
    }

    public Pasien cari(String id) {
        for (Pasien pasien : daftarPasien) {
            if (pasien.getNoRekamMedis().equalsIgnoreCase(id)) {
                return pasien;
            }
        }
        return null;
    }

    public List<Pasien> getSemua() {
        return daftarPasien;
    }

    public boolean isKosong() {
        return daftarPasien.isEmpty();
    }

    public int jumlah() {
        return daftarPasien.size();
    }
}
