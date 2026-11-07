public class Main {
    public static void main(String[] args) {
        Masukan masukan = new Masukan();
        RepositoriPasien repositori = new RepositoriPasien();
        FormPendaftaran form = new FormPendaftaran(masukan);
        MenuRumahSakit menu = new MenuRumahSakit(masukan, repositori, form);

        menu.jalankan();
        masukan.tutup();
    }
}
