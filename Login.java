import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Login {

    private static Map<String, String> userDatabase = new HashMap<>();
    private static final int CAPTCHA_LENGTH = 6;

    public static void main(String[] args) {

        userDatabase.put("username1", "password11");
        userDatabase.put("username2", "password22");

        Scanner scanner = new Scanner(System.in);

        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (login(username, password)) {
                loggedIn = true;
                System.out.println("Login berhasil!");
            } else {
                System.out.println("Login gagal. Apakah Anda ingin mencoba login dengan username dan password baru? (y/n)");

                String response = scanner.nextLine().toLowerCase();

                if (!response.equals("y")) {
                    System.out.println("Terima kasih. Selamat tinggal.");
                    break;
                }

                // Memasukkan username dan password baru
                System.out.print("Masukkan username baru: ");
                String newUsername = scanner.nextLine();
                
                System.out.print("Masukkan password baru: ");
                String newPassword = scanner.nextLine();

                // Menambahkan pengguna baru ke dalam database
                userDatabase.put(newUsername, newPassword);

                // Menampilkan captcha
                String captcha = generateCaptcha();
                System.out.println("Captcha: " + captcha);
                System.out.print("Masukkan captcha: ");
                String userCaptcha = scanner.nextLine();

                if (captcha.equals(userCaptcha)) {
                    System.out.println("Pengguna baru ditambahkan dengan sukses!");
                } else {
                    System.out.println("Verifikasi captcha gagal. Coba lagi nanti.");
                }
            }
        }
    }

    private static boolean login(String username, String password) {
        // Mengambil password yang disimpan untuk username tertentu
        String storedPassword = userDatabase.get(username);

        // Memeriksa apakah username ditemukan dan password cocok
        return storedPassword != null && storedPassword.equals(password);
    }

    private static String generateCaptcha() {
        // Generate a random captcha string
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A'); // Generate a random uppercase letter
            captcha.append(randomChar);
        }
        return captcha.toString();
    }
}
