public class PasienRawatJalan extends Pasien implements Administrasi, Penjadwalan {
    private int jumlahKunjungan;
    private double biayaKonsultasi;
    private String jadwalKontrol;
    private boolean sudahBayar;

    public PasienRawatJalan(String noRM, String nama) {
        this(noRM, nama, 100000.0);
    }

    public PasienRawatJalan(String noRM, String nama, double biayaKonsultasi) {
        super(noRM, nama, 28, 2);
        this.biayaKonsultasi = biayaKonsultasi;
        this.jumlahKunjungan = 0;
        this.jadwalKontrol = "Belum dijadwalkan";
        this.sudahBayar = false;
    }

    @Override
    public void prosesPerawatan() {
        jumlahKunjungan++;
        if (getTingkatKeparahan() >= 5) {
            setStatus("DIRUJUK RAWAT INAP");
            System.out.println(getNama() + " dirujuk ke rawat inap");
        } else {
            setStatus("KONSULTASI SELESAI");
            setTingkatKeparahan(getTingkatKeparahan() - 1);
            System.out.println(getNama() + " selesai konsultasi. Kunjungan ke-" + jumlahKunjungan);
        }
    }

    @Override
    public double hitungBiaya() {
        return BIAYA_PENDAFTARAN + biayaKonsultasi * Math.max(1, jumlahKunjungan);
    }

    @Override
    public void bayar(double jumlah) {
        if (jumlah >= hitungBiaya()) {
            sudahBayar = true;
            System.out.println("Pembayaran rawat jalan lunas. Kembalian Rp" + (jumlah - hitungBiaya()));
        } else {
            System.out.println("Pembayaran kurang Rp" + (hitungBiaya() - jumlah));
        }
    }

    @Override
    public void jadwalkanKontrol(String tanggal) {
        this.jadwalKontrol = tanggal;
        System.out.println("Kontrol " + getNama() + " dijadwalkan pada " + tanggal);
    }

    @Override
    public String getJadwalKontrol() {
        return jadwalKontrol;
    }

    @Override
    public void tampilkanData() {
        super.tampilkanData();
        System.out.println("   Kunjungan: " + jumlahKunjungan + " | Kontrol: " + jadwalKontrol
                + " | Lunas: " + sudahBayar);
    }

    public int getJumlahKunjungan() {
        return jumlahKunjungan;
    }

    public void setJumlahKunjungan(int n) {
        this.jumlahKunjungan = n;
    }

    public double getBiayaKonsultasi() {
        return biayaKonsultasi;
    }
    
    public void setBiayaKonsultasi(double b) {
        this.biayaKonsultasi = b;
    }
}
