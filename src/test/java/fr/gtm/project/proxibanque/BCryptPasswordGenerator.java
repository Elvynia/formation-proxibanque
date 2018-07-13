package fr.gtm.project.proxibanque;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordGenerator {

	public static void main(final String[] args) {
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final Scanner scanner = new Scanner(System.in);
		final String password = scanner.nextLine();
		for (int i = 0; i < 5; ++i) {
			System.out.println(encoder.encode(password));
		}
		scanner.close();
	}
}
