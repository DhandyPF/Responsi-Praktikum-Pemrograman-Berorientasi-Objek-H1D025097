public class FormPendaftaran {
    private final Masukan masukan;

    public FormPendaftaran(Masukan masukan) {
        this.masukan = masukan;
    }

    public Pasien buatPasien(String id) {
        System.out.println("Jenis pasien: 1. Rawat Inap  2. Rawat Jalan  3. UGD");
        int jenis = masukan.bacaAngka("Pilih jenis", 1, 3);
        String nama = masukan.bacaTeks("Nama pasien");
        int umur = masukan.bacaAngka("Umur", 0, 120);

        Pasien pasien;
        if (jenis == 1) {
            pasien = buatRawatInap(id, nama);
        } else if (jenis == 2) {
            pasien = buatRawatJalan(id, nama);
        } else {
            pasien = buatUGD(id, nama);
        }
        pasien.setUmur(umur);
        return pasien;
    }

    private Pasien buatRawatInap(String id, String nama) {
        Integer keparahan = masukan.bacaAngkaOpsional("Tingkat keparahan (Enter untuk standar)", 0, 10);
        if (keparahan == null) {
            return new PasienRawatInap(id, nama);
        }
        double tarif = masukan.bacaDesimal("Tarif kamar per hari");
        return new PasienRawatInap(id, nama, keparahan, tarif);
    }

    private Pasien buatRawatJalan(String id, String nama) {
        Double biaya = masukan.bacaDesimalOpsional("Biaya konsultasi (Enter untuk standar)");
        if (biaya == null) {
            return new PasienRawatJalan(id, nama);
        }
        return new PasienRawatJalan(id, nama, biaya);
    }

    private Pasien buatUGD(String id, String nama) {
        Integer keparahan = masukan.bacaAngkaOpsional("Tingkat keparahan (Enter untuk standar)", 0, 10);
        if (keparahan == null) {
            return new PasienUGD(id, nama);
        }
        return new PasienUGD(id, nama, keparahan);
    }
}
