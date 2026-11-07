public abstract class Pasien {
    public static final double BIAYA_PENDAFTARAN = 25000.0;

    private final String noRekamMedis;
    private String nama;
    private int umur;
    private int tingkatKeparahan;
    private String status;

    public Pasien(String noRekamMedis, String nama) {
        this(noRekamMedis, nama, 30, 1);
    }

    public Pasien(String noRekamMedis, String nama, int umur, int tingkatKeparahan) {
        this.noRekamMedis = noRekamMedis;
        this.nama = nama;
        this.umur = umur;
        this.tingkatKeparahan = tingkatKeparahan;
        this.status = "TERDAFTAR";
    }

    // Method fungsi (return value)
    public boolean isKritis() {
        return tingkatKeparahan >= 8;
    }

    // Method prosedur (void)
    public void tampilkanData() {
        System.out.println("[" + noRekamMedis + "] " + nama + " | Umur " + umur
                + " | Keparahan " + tingkatKeparahan + " | Status " + status);
    }

    public abstract void prosesPerawatan();

    public String getNoRekamMedis() {
        return noRekamMedis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public int getTingkatKeparahan() {
        return tingkatKeparahan;
    }

    public void setTingkatKeparahan(int t) {
        this.tingkatKeparahan = Math.max(0, Math.min(10, t));
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
