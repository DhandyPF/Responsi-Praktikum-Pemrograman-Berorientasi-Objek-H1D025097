public class PasienRawatInap extends Pasien implements Administrasi {
    private int lamaInap;
    private double tarifKamarPerHari;
    private double sudahDibayar;

    public PasienRawatInap(String noRM, String nama) {
        this(noRM, nama, 5, 500000.0);
    }

    public PasienRawatInap(String noRM, String nama, int keparahan, double tarifKamar) {
        super(noRM, nama, 40, keparahan);
        this.lamaInap = 1;
        this.tarifKamarPerHari = tarifKamar;
        this.sudahDibayar = 0;
    }

    @Override
    public void prosesPerawatan() {
        if (getTingkatKeparahan() > 3) {
            lamaInap++;
            setTingkatKeparahan(getTingkatKeparahan() - 2);
            setStatus("DIRAWAT INAP");
            System.out.println(getNama() + " dirawat hari ke-" + lamaInap
                    + ". Keparahan turun ke " + getTingkatKeparahan());
        } else {
            setStatus("BOLEH PULANG");
            System.out.println(getNama() + " sudah stabil dan boleh pulang");
        }
    }

    @Override
    public double hitungBiaya() {
        return BIAYA_PENDAFTARAN + (lamaInap * tarifKamarPerHari);
    }

    @Override
    public void bayar(double jumlah) {
        sudahDibayar += jumlah;
        if (sudahDibayar >= hitungBiaya()) {
            System.out.println("Biaya rawat inap lunas");
        } else {
            System.out.println("Sisa tagihan: Rp" + (hitungBiaya() - sudahDibayar));
        }
    }

    @Override
    public void tampilkanData() {
        super.tampilkanData();
        System.out.println("   Lama inap: " + lamaInap + " hari | Tagihan Rp" + hitungBiaya());
    }

    public int getLamaInap() { return lamaInap; }
    public void setLamaInap(int lamaInap) { this.lamaInap = lamaInap; }
    public double getTarifKamarPerHari() { return tarifKamarPerHari; }
    public void setTarifKamarPerHari(double t) { this.tarifKamarPerHari = t; }
}
