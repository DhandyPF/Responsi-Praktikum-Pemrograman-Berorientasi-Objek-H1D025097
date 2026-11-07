public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. State dan Behavior ===");
        PasienRawatJalan p1 = new PasienRawatJalan("RM001", "Siti");
        System.out.println("Nama: " + p1.getNama());
        System.out.println("Keparahan: " + p1.getTingkatKeparahan());
        p1.tampilkanData();
        System.out.println("Kritis: " + p1.isKritis());

        System.out.println("\n=== 2. Immutability ===");
        System.out.println("Biaya pendaftaran: " + Pasien.BIAYA_PENDAFTARAN);
        System.out.println("Nomor rekam medis: " + p1.getNoRekamMedis());

        System.out.println("\n=== 3. Enkapsulasi dan Overloading ===");
        PasienRawatJalan p2 = new PasienRawatJalan("RM002", "Budi", 250000.0);
        System.out.println("Biaya konsultasi default: " + p1.getBiayaKonsultasi());
        System.out.println("Biaya konsultasi custom: " + p2.getBiayaKonsultasi());
        p1.setUmur(29);
        System.out.println("Umur setelah diubah: " + p1.getUmur());

        System.out.println("\n=== 4. Inheritance ===");
        PasienRawatInap inap = new PasienRawatInap("RM003", "Dewi", 7, 450000.0);
        PasienUGD ugd = new PasienUGD("RM004", "Fajar");
        System.out.println("Pasien rawat inap termasuk pasien: " + (inap instanceof Pasien));
        System.out.println("Pasien rawat jalan termasuk pasien: " + (p1 instanceof Pasien));
        System.out.println("Pasien UGD termasuk pasien: " + (ugd instanceof Pasien));

        System.out.println("\n=== 5. Super Chaining ===");
        System.out.println("Umur dari pasien: " + inap.getUmur());
        inap.tampilkanData();

        System.out.println("\n=== 6. Overriding ===");
        inap.prosesPerawatan();
        p1.prosesPerawatan();
        ugd.prosesPerawatan();

        System.out.println("\n=== 7. Abstract Class ===");
        Pasien pasien = new PasienUGD("RM005", "Kiki", 9);
        System.out.println("Pasien umum tidak bisa dibuat langsung");
        System.out.print("Hasil perawatan: ");
        pasien.prosesPerawatan();

        System.out.println("\n=== 8. Logika State Percabangan ===");
        PasienRawatInap lina = new PasienRawatInap("RM006", "Lina", 7, 450000.0);
        for (int i = 1; i <= 3; i++) {
            System.out.print("Proses " + i + " (keparahan " + lina.getTingkatKeparahan() + "): ");
            lina.prosesPerawatan();
        }
        System.out.println("Status akhir: " + lina.getStatus());

        System.out.println("\n=== 9. Polimorfisme Interface ===");
        Administrasi a1 = new PasienRawatInap("RM007", "Maya");
        Administrasi a2 = new PasienRawatJalan("RM008", "Nanda");
        Administrasi a3 = new PasienUGD("RM009", "Omar", 5);
        System.out.println("Biaya rawat inap: Rp" + a1.hitungBiaya());
        System.out.println("Biaya rawat jalan: Rp" + a2.hitungBiaya());
        System.out.println("Biaya UGD: Rp" + a3.hitungBiaya());

        System.out.println("\n=== 10. Multiple Interface ===");
        PasienRawatJalan putri = new PasienRawatJalan("RM010", "Putri");
        Administrasi adm = putri;
        Penjadwalan jadwal = putri;
        jadwal.jadwalkanKontrol("15 Desember 2026");
        System.out.println("Jadwal kontrol: " + jadwal.getJadwalKontrol());
        System.out.println("Biaya: Rp" + adm.hitungBiaya());
    }
}