public class MenuRumahSakit {
    private final Masukan masukan;
    private final RepositoriPasien repositori;
    private final FormPendaftaran form;

    public MenuRumahSakit(Masukan masukan, RepositoriPasien repositori, FormPendaftaran form) {
        this.masukan = masukan;
        this.repositori = repositori;
        this.form = form;
    }

    public void jalankan() {
        System.out.println("=================================");
        System.out.println("   SISTEM INFORMASI RUMAH SAKIT");
        System.out.println("=================================");

        boolean berjalan = true;
        while (berjalan) {
            tampilkanMenu();
            int pilihan = masukan.bacaAngka("Pilih menu", 0, 6);
            System.out.println();
            switch (pilihan) {
                case 1: daftarkanPasien(); break;
                case 2: lihatDaftarPasien(); break;
                case 3: prosesPerawatan(); break;
                case 4: tagihanDanPembayaran(); break;
                case 5: jadwalkanKontrol(); break;
                case 6: ubahDataPasien(); break;
                default:
                    berjalan = false;
                    System.out.println("Terima kasih, program selesai.");
            }
        }
    }

    private void tampilkanMenu() {
        System.out.println("\n--------- MENU UTAMA ---------");
        System.out.println("1. Daftarkan pasien baru");
        System.out.println("2. Lihat daftar pasien");
        System.out.println("3. Proses perawatan pasien");
        System.out.println("4. Tagihan dan pembayaran");
        System.out.println("5. Jadwalkan kontrol");
        System.out.println("6. Ubah data pasien");
        System.out.println("0. Keluar");
    }

    private void daftarkanPasien() {
        System.out.println("--- Pendaftaran Pasien ---");
        String id = repositori.getIdBerikutnya();
        System.out.println("ID pasien: " + id);
        Pasien pasien = form.buatPasien(id);
        repositori.tambah(pasien);
        System.out.println("Pasien " + pasien.getNama() + " berhasil didaftarkan dengan ID " + id + ".");
    }

    private void lihatDaftarPasien() {
        System.out.println("--- Daftar Pasien ---");
        if (repositori.isKosong()) {
            System.out.println("Belum ada pasien terdaftar.");
            return;
        }
        for (Pasien pasien : repositori.getSemua()) {
            System.out.println("Jenis: " + namaJenis(pasien) + (pasien.isKritis() ? "  (KRITIS)" : ""));
            pasien.tampilkanData();
            System.out.println();
        }
        System.out.println("Total pasien: " + repositori.jumlah());
    }

    private void prosesPerawatan() {
        System.out.println("--- Proses Perawatan ---");
        Pasien pasien = pilihPasien();
        if (pasien == null) {
            return;
        }
        pasien.prosesPerawatan();
        System.out.println("Status sekarang: " + pasien.getStatus());
    }

    private void tagihanDanPembayaran() {
        System.out.println("--- Tagihan dan Pembayaran ---");
        Pasien pasien = pilihPasien();
        if (pasien == null) {
            return;
        }
        Administrasi administrasi = (Administrasi) pasien;
        System.out.println("Total tagihan " + pasien.getNama() + ": Rp" + administrasi.hitungBiaya());
        String jawab = masukan.bacaTeks("Bayar sekarang? (y/n)");
        if (jawab.equalsIgnoreCase("y")) {
            double jumlah = masukan.bacaDesimal("Jumlah pembayaran");
            administrasi.bayar(jumlah);
        }
    }

    private void jadwalkanKontrol() {
        System.out.println("--- Jadwalkan Kontrol ---");
        Pasien pasien = pilihPasien();
        if (pasien == null) {
            return;
        }
        if (pasien instanceof Penjadwalan) {
            Penjadwalan jadwal = (Penjadwalan) pasien;
            String tanggal = masukan.bacaTeks("Tanggal kontrol");
            jadwal.jadwalkanKontrol(tanggal);
            System.out.println("Jadwal tersimpan: " + jadwal.getJadwalKontrol());
        } else {
            System.out.println("Pasien " + namaJenis(pasien).toLowerCase() + " tidak memiliki jadwal kontrol.");
        }
    }

    private void ubahDataPasien() {
        System.out.println("--- Ubah Data Pasien ---");
        Pasien pasien = pilihPasien();
        if (pasien == null) {
            return;
        }
        String namaBaru = masukan.bacaTeks("Nama baru (Enter untuk tidak diubah)");
        if (!namaBaru.isEmpty()) {
            pasien.setNama(namaBaru);
        }
        Integer umurBaru = masukan.bacaAngkaOpsional("Umur baru (Enter untuk tidak diubah)", 0, 120);
        if (umurBaru != null) {
            pasien.setUmur(umurBaru);
        }
        Integer keparahanBaru = masukan.bacaAngkaOpsional("Tingkat keparahan baru (Enter untuk tidak diubah)", 0, 10);
        if (keparahanBaru != null) {
            pasien.setTingkatKeparahan(keparahanBaru);
        }
        System.out.println("Data berhasil diperbarui.");
        pasien.tampilkanData();
    }

    private Pasien pilihPasien() {
        if (repositori.isKosong()) {
            System.out.println("Belum ada pasien terdaftar.");
            return null;
        }
        for (Pasien pasien : repositori.getSemua()) {
            System.out.println(pasien.getNoRekamMedis() + " - " + pasien.getNama()
                    + " (" + namaJenis(pasien) + ")");
        }
        String id = masukan.bacaTeks("ID pasien");
        Pasien pasien = repositori.cari(id);
        if (pasien == null) {
            System.out.println("Pasien tidak ditemukan.");
        }
        return pasien;
    }

    private String namaJenis(Pasien pasien) {
        if (pasien instanceof PasienRawatInap) {
            return "Rawat Inap";
        } else if (pasien instanceof PasienRawatJalan) {
            return "Rawat Jalan";
        }
        return "UGD";
    }
}
