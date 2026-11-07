public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. State dan Behavior ===");
        PasienRawatInap inap = new PasienRawatInap("RM001", "Budi", 7, 450000.0);
        inap.tampilkanData();
        System.out.println("Budi kritis? " + inap.isKritis());

        System.out.println("\n=== 2. Immutability ===");
        System.out.println("Biaya pendaftaran (konstanta): " + Pasien.BIAYA_PENDAFTARAN);
        System.out.println("No rekam medis (final): " + inap.getNoRekamMedis());

        System.out.println("\n=== 3. Enkapsulasi dan Overloading ===");
        PasienRawatJalan jalan = new PasienRawatJalan("RM002", "Siti");
        PasienRawatJalan jalanMahal = new PasienRawatJalan("RM003", "Andi", 250000.0);
        System.out.println("Biaya konsultasi default: " + jalan.getBiayaKonsultasi());
        System.out.println("Biaya konsultasi custom: " + jalanMahal.getBiayaKonsultasi());
        jalan.setUmur(29);
        System.out.println("Umur Siti setelah setter: " + jalan.getUmur());

        System.out.println("\n=== 4, 5, 6. Inheritance, Super Chaining, Overriding ===");
        PasienUGD ugd = new PasienUGD("RM004", "Rina");
        jalan.tampilkanData();
        ugd.tampilkanData();

        System.out.println("\n=== 7, 8. Abstract Class dan Logika State ===");
        Pasien[] semua = {inap, jalan, ugd};
        for (Pasien p : semua) {
            p.prosesPerawatan();
        }
        inap.prosesPerawatan();
        inap.prosesPerawatan();
        System.out.println("Pasien rawat jalan dengan keparahan tinggi:");
        jalanMahal.setTingkatKeparahan(6);
        jalanMahal.prosesPerawatan();
        for (Pasien p : semua) {
            p.tampilkanData();
        }

        System.out.println("\n=== 9. Polimorfisme Interface ===");
        Administrasi a1 = new PasienRawatInap("RM005", "Dewi");
        Administrasi a2 = new PasienRawatJalan("RM006", "Eko");
        Administrasi a3 = new PasienUGD("RM007", "Fajar", 5);
        Administrasi[] tagihan = {a1, a2, a3};
        for (Administrasi a : tagihan) {
            System.out.println("Tagihan: Rp" + a.hitungBiaya());
        }
        a1.bayar(100000.0);
        a2.bayar(500000.0);

        System.out.println("\n=== 10. Multiple Interface ===");
        Penjadwalan jadwal = new PasienRawatJalan("RM008", "Gita");
        jadwal.jadwalkanKontrol("15 Desember 2026");
        System.out.println("Jadwal: " + jadwal.getJadwalKontrol());
        Administrasi adm = (Administrasi) jadwal;
        System.out.println("Biaya: Rp" + adm.hitungBiaya());
        System.out.println("PasienRawatJalan bertipe Administrasi dan Penjadwalan sekaligus");
    }
}
