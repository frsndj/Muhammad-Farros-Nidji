import java.util.Scanner;
import java.time.LocalDate;

public class MyClass {
    static int[] purchaseCount = new int[15]; 

    // Variabel untuk total pemasukan
    static int totalPemasukan = 0;
    static int mCari(String data[], String kata) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null && data[i].equalsIgnoreCase(kata)) {
                return i; // Kembalikan indeks saat data ditemukan
            }
        }
        return -1; // Jika tidak ketemu
    }

    public static void main(String[] args) {
        // PIN masing2 user
        final int PIN_KASIR = 3306;
        final int PIN_ADMIN = 1607;
        final int PIN_OWNER = 1603;

        Scanner sc = new Scanner(System.in);
        int user = 0;

        // Array untuk data master Papeda sapoe angin
        String[] Menu = new String[15];
        int[] Harga = new int[15];
        int jumlah = 6;

        // menu dan harga
        Menu[0] = "Papeda Ori               \t";
        Harga[0] = 1000;
        Menu[1] = "Papeda Telur Puyuh       \t";
        Harga[1] = 2000;
        Menu[2] = "Papeda 1 Telur Ayam      \t";
        Harga[2] = 5000;
        Menu[3] = "Sosis Bakar              \t";
        Harga[3] = 2000;
        Menu[4] = "Telur Puyuh Goreng 3 biji\t";
        Harga[4] = 2000;
        Menu[5] = "Papeda Sosis             \t";
        Harga[5] = 2000;

        // Array untuk pembelian
        String[] menu = new String[50];
        int[] harga = new int[50];
        int[] porsi = new int[50];
        int idx = 0;

        do {
            System.out.println(" xxxxx Papeda Sapoe Angin xxxxx ");
            System.out.println("Sang Bakul Keliling Yang Siap Mengantar Pesanan");
            System.out.println("====================================");
            System.out.println("\nUser yang Tersedia");
            System.out.println("1. Kasir");
            System.out.println("2. Admin");
            System.out.println("3. Owner");
            System.out.println("4. Exit");
            System.out.print("Pilih :  ");
            user = sc.nextInt();

            switch (user) {
                case 1:
                    System.out.print("Masukkan PIN Kasir dulu ya!:  ");
                    int pinKasir = sc.nextInt();
                    if (pinKasir == PIN_KASIR) {
                        int pilihKasir;
                        do {
                            System.out.println(" ");
                            System.out.println("           Kasir Mode ON           ");
                            System.out.println("        Tanggal:    " + LocalDate.now());
                            System.out.println("------------------------------------------");
                            System.out.println("\nDaftar Menu");
                            for (int i = 0; i < jumlah; i++) {
                                System.out.print((i + 1) + ". " + Menu[i]);
                                System.out.println(" \t\t[Rp." + Harga[i] + "]");
                            }
                            System.out.println("------------------------------------------");
                            System.out.println("\n1. Pesan ");
                            System.out.println("2. Urutkan Nama Menu");
                            System.out.println("3. Urutkan Harga Menu");
                            System.out.println("4. Exit");
                            System.out.print("Pilih:  ");
                            pilihKasir = sc.nextInt();

                            switch (pilihKasir) {
                                case 1:
                                    int pilih;
                                    do {
                                        // Tampilkan menu
                                        System.out.println("\nDaftar Menu");
                                        for (int i = 0; i < jumlah; i++) {
                                            System.out.print((i + 1) + ". " + Menu[i]);
                                            System.out.println(" \t\t[Rp." + Harga[i] + "]");
                                        }

                                        System.out.println("0. Selesai Memesan");
                                        System.out.print("Pilih Menu (0 jika selesai):  ");
                                        pilih = sc.nextInt();

                                        if (pilih > 0 && pilih <= jumlah) {
                                            // Input porsi
                                            System.out.print("Jumlah Porsi:  ");
                                            porsi[idx] = sc.nextInt();

                                            // Simpan data pesanan
                                            menu[idx] = Menu[pilih - 1];
                                            harga[idx] = Harga[pilih - 1];

                                            // Hitung total harga untuk item 
                                            int total = porsi[idx] * harga[idx];
                                            System.out.println("Porsi:  " + harga[idx]);
                                            System.out.println("Total       :  " + total);
                                            
                                            // Perhitungan menu yang sering dibeli
                                            purchaseCount[pilih - 1]++;
                                            idx++; // Pindah ke indeks berikutnya untuk pesanan baru
                                        } else if (pilih != 0) {
                                            System.out.println("Menu ngga valid. Pilih lagi yaa.");
                                        }

                                    } while (pilih != 0);

                                    // List daftar pesanan
                                    System.out.println("-----------------------------------------------------------------");
                                    System.out.println("                       Daftar Pembelian                          ");
                                    System.out.println("-----------------------------------------------------------------");
                                    System.out.println("No\tMenu\t\t\tHarga\tQty\tTotal");
                                    for (int i = 0; i < idx; i++) {
                                        if (menu[i] != null) { // Hanya tampilkan data yang tidak null
                                            System.out.print((i + 1) + "\t" + menu[i]);
                                            System.out.print("\t" + harga[i]);
                                            System.out.print("\t" + porsi[i]);
                                            System.out.println("\t" + harga[i] * porsi[i]);
                                        }
                                    }

                                    // Menghitung grand total
                                    int grandTotal = 0;
                                    for (int i = 0; i < idx; i++) {
                                        grandTotal += harga[i] * porsi[i];
                                    }

                                    System.out.println("-----------------------------------------------------------------");
                                    System.out.println("                                        Grand Total :  " + grandTotal);
                                    System.out.println("-----------------------------------------------------------------");

                                    // Menambahkan ke total pemasukan
                                    totalPemasukan += grandTotal;

                                    int uangTunai;
                                    do {
                                        // Input uang tunai
                                        System.out.print("                           Masukkan jumlah uang tunai:  ");
                                        uangTunai = sc.nextInt();

                                        if (uangTunai < grandTotal) {
                                            System.out.println("Uangmu kurang ini, harusnya tuh segini!:  " + grandTotal);
                                            System.out.println("Gimanasih, Masukin uang yang cukup.");
                                        }

                                    } while (uangTunai < grandTotal); // Ulangi input jika uang kurang

                                    // Menghitung kembalian
                                    int kembalian = uangTunai - grandTotal;
                                    System.out.println("Kembalian               :  " + kembalian);
                                    // when an item is purchased, increment the count
                                     if (pilih > 0 && pilih <= jumlah) {
                                      // Increment purchase count for the selected menu item
                                    }
                                    break;

                                case 2:
                                    // Mengurutkan berdasarkan nama
                                    for (int i = 0; i < jumlah - 1; i++) {
                                        for (int j = 0; j < jumlah - 1 - i; j++) {
                                            if (Menu[j].compareToIgnoreCase(Menu[j + 1]) > 0) {
                                                // Tukar nama menu
                                                String tempNama = Menu[j];
                                                Menu[j] = Menu[j + 1];
                                                Menu[j + 1] = tempNama;

                                                // Tukar harga menu
                                                int tempHarga = Harga[j];
                                                Harga[j] = Harga[j + 1];
                                                Harga[j + 1] = tempHarga;
                                            }
                                        }
                                    }

                                    System.out.println("\nMenu udah diurutin berdasarkan nama: ");
                                    for (int i = 0; i < jumlah; i++) {
                                        System.out.println((i + 1) + ". " + Menu[i] + " [" + Harga[i] + "]");
                                    }
                                    break;

                                case 3:
                                    // Mengurutkan berdasarkan harga
                                    for (int i = 0; i < jumlah - 1; i++) {
                                        for (int j = 0; j < jumlah - 1 - i; j++) {
                                            if (Harga[j] > Harga[j + 1]) {
                                                // Tukar nama menu
                                                String tempNama = Menu[j];
                                                Menu[j] = Menu[j + 1];
                                                Menu[j + 1] = tempNama;

                                                // Tukar harga menu
                                                int tempHarga = Harga[j];
                                                Harga[j] = Harga[j + 1];
                                                Harga[j + 1] = tempHarga;
                                            }
                                        }
                                    }

                                    System.out.println("\nMenu udah diurutin berdasarkan harga: ");
                                    for (int i = 0; i < jumlah; i++) {
                                        System.out.println((i + 1) + ". " + Menu[i] + " [" + Harga[i] + "]");
                                    }
                                    break;

                                case 4:
                                    break;

                                default:
                                    System.out.println("Pilihanmu ngga valid.");
                                    break;
                            }
                        } while (pilihKasir != 4); // Kembali ke submenu Kasir selama belum milih opsi 4
                    } else {
                        System.out.println("Kalo lupa PIN bilang dasar pikun.");
                    }
                    break;

               case 2: // Admin Section
    System.out.print("Masukkan PIN Admin dulu ya!:  ");
    int pinAdmin = sc.nextInt();
    if (pinAdmin == PIN_ADMIN) {
        System.out.println("\nAdmin mode ON");
        int pilihAdmin;
        do {
            // Display current menu
            System.out.println("\nDaftar Menu:");
            for (int i = 0; i < jumlah; i++) {
                if (Menu[i] != null) { // Check if the menu item is not null
                    System.out.println((i + 1) + ". " + Menu[i] + " - Rp." + Harga[i]);
                }
            }

            System.out.println("\n1. Tambah Menu");
            System.out.println("2. Ubah Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            pilihAdmin = sc.nextInt();

            switch (pilihAdmin) {
                case 1: // Tambah menu
                    if (jumlah < Menu.length) {
                        System.out.print("Masukkan nama menu baru: ");
                        sc.nextLine(); // Clear the buffer
                        String namaMenuBaru = sc.nextLine();
                        System.out.print("Masukkan harga menu baru: ");
                        int hargaMenuBaru = sc.nextInt();

                        // output menu baru
                        Menu[jumlah] = namaMenuBaru;
                        Harga[jumlah] = hargaMenuBaru;
                        jumlah++; // Increment menu count
                        System.out.println("WAHH MENU BARUUU.");
                    } else {
                        System.out.println("Menu kamu penuh sayang banget.");
                    }
                    break;

                case 2: // Ubah menu
                    System.out.print("Masukkan nomor menu yang mau diubah: ");
                    int nomorMenu = sc.nextInt();
                    if (nomorMenu > 0 && nomorMenu <= jumlah) {
                        System.out.print("Masukkan nama menu baru: ");
                        sc.nextLine(); 
                        String namaMenuBaru = sc.nextLine();
                        System.out.print("Masukkan harga menu baru: ");
                        int hargaMenuBaru = sc.nextInt();

                        // output
                        Menu[nomorMenu - 1] = namaMenuBaru;
                        Harga[nomorMenu - 1] = hargaMenuBaru;
                        System.out.println("Menunya udah diubah.");
                    } else {
                        System.out.println("Nomor menunya ngga valid.");
                    }
                    break;

                case 3: // Delete Menu
                    System.out.print("Masukkan nomor menu yang mau dihapus: ");
                    int hapusMenu = sc.nextInt();
                    if (hapusMenu > 0 && hapusMenu <= jumlah) {
                        // Shift elements to the left
                        for (int i = hapusMenu - 1; i < jumlah - 1; i++) {
                            Menu[i] = Menu[i + 1];
                            Harga[i] = Harga[i + 1];
                        }
                        Menu[jumlah - 1] = null; // Clear last element
                        Harga[jumlah - 1] = 0; // Clear last price
                        jumlah--; // Decrement menu count
                        System.out.println("Menunya udah dihapus.");
                    } else {
                        System.out.println("Nomor menunya ngga valid.");
                    }
                    break;

                case 4: // Back
                    break;

                default:
                    System.out.println("Pilihanmu ngga valid.");
                    break;
            }
        } while (pilihAdmin != 4);
    } else {
        System.out.println("Kalo lupa PIN bilang dasar pikun.");
    }
                    break;

                case 3: // Owner 
    System.out.print("Masukkan PIN Owner dulu ya!:  ");
    int pinOwner = sc.nextInt();
    if (pinOwner == PIN_OWNER) {
        System.out.println("\nAkses Owner");
        System.out.println("HAII OWNER LAMA NGGA KETEMU");
        System.out.println("Total pemasukan kamu saat ini:  Rp." + totalPemasukan);
        
        // Tampilkan menu
        System.out.println("\nDaftar Menu:");
        for (int i = 0; i < jumlah; i++) {
            if (Menu[i] != null) { // Check if the menu item is not null
                System.out.println((i + 1) + ". " + Menu[i] + " - Rp." + Harga[i]);
            }
        }

        // frekuensi pembelian terbanyak
        System.out.println("\nMenu yang terjual hari ini :");
        for (int i = 0; i < jumlah; i++) {
            if (purchaseCount[i] > 0) {
                System.out.println(Menu[i] + " - " + purchaseCount[i] + " kali");
            }
        }
    } else {
        System.out.println("Kamu udah lupain aku ya?.....");
    }
                    break;

                case 4:
                    System.out.println("Kamsahamida!");
                    break;

                default:
                    System.out.println("Pilihanmu ngga valid!");
            }
        } while (user != 4);
    }
}
