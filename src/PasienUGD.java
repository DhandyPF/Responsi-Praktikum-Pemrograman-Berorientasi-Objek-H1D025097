public class PasienUGD extends Pasien implements Administrasi {
    private boolean masukICU;
    private double biayaTindakan;

    public PasienUGD(String noRM, String nama) {
        this(noRM, nama, 9);
    }

    public PasienUGD(String noRM, String nama, int keparahan) {
        super(noRM, nama, 35, keparahan);
        this.masukICU = false;
        this.biayaTindakan = 750000.0;
    }

    @Override
    public void prosesPerawatan() {
        if (isKritis()) {
            masukICU = true;
            biayaTindakan += 2000000.0;
            setStatus("DI ICU");
            System.out.println(getNama() + " kritis, dipindahkan ke ICU");
        } else if (getTingkatKeparahan() >= 4) {
            setStatus("OBSERVASI UGD");
            setTingkatKeparahan(getTingkatKeparahan() - 1);
            System.out.println(getNama() + " dalam observasi UGD");
        } else {
            masukICU = false;
            setStatus("STABIL");
            System.out.println(getNama() + " stabil, siap dipindah ke ruang rawat");
        }
    }

    @Override
    public double hitungBiaya() {
        return BIAYA_PENDAFTARAN + biayaTindakan;
    }

    @Override
    public void bayar(double jumlah) {
        System.out.println("Jaminan UGD diterima Rp" + jumlah + ". Tagihan total Rp" + hitungBiaya());
    }

    @Override
    public void tampilkanData() {
        super.tampilkanData();
        System.out.println("   ICU: " + masukICU + " | Biaya tindakan Rp" + biayaTindakan);
    }

    public boolean isMasukICU() {
        return masukICU;
    }

    public void setMasukICU(boolean masukICU) {
        this.masukICU = masukICU;
    }

    public double getBiayaTindakan() {
        return biayaTindakan;
    }

    public void setBiayaTindakan(double b) {
        this.biayaTindakan = b;
    }
}
