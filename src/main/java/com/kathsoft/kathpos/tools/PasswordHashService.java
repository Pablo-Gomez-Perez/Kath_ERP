package com.kathsoft.kathpos.tools;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordHashService {

	private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
	private static final String PREFIX = "PBKDF2";
	private static final int ITERATIONS = 120000;
	private static final int SALT_LENGTH_BYTES = 16;
	private static final int KEY_LENGTH_BITS = 256;
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	private PasswordHashService() {
	}

	public static String hashPassword(String password) {
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException("La contraseña es obligatoria");
		}

		byte[] salt = new byte[SALT_LENGTH_BYTES];
		SECURE_RANDOM.nextBytes(salt);
		byte[] hash = pbkdf2(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH_BITS);

		return PREFIX + "$" + ITERATIONS + "$" + Base64.getEncoder().encodeToString(salt) + "$"
				+ Base64.getEncoder().encodeToString(hash);
	}

	public static String hashIfPlain(String password) {
		if (password == null || password.isBlank()) {
			return password;
		}

		if (isHash(password)) {
			return password;
		}

		return hashPassword(password);
	}

	public static boolean verifyPassword(char[] password, String storedHash) {
		if (password == null || password.length == 0 || storedHash == null || storedHash.isBlank()) {
			return false;
		}

		if (!isHash(storedHash)) {
			return false;
		}

		String[] parts = storedHash.split("\\$");
		if (parts.length != 4) {
			return false;
		}

		try {
			int iterations = Integer.parseInt(parts[1]);
			byte[] salt = Base64.getDecoder().decode(parts[2]);
			byte[] expectedHash = Base64.getDecoder().decode(parts[3]);
			byte[] actualHash = pbkdf2(password, salt, iterations, expectedHash.length * 8);
			return constantTimeEquals(expectedHash, actualHash);
		} catch (IllegalArgumentException er) {
			return false;
		}
	}

	public static boolean isHash(String value) {
		if (value == null) {
			return false;
		}

		String[] parts = value.split("\\$", -1);
		if (parts.length != 4 || !PREFIX.equals(parts[0])) {
			return false;
		}

		try {
			int iterations = Integer.parseInt(parts[1]);
			byte[] salt = Base64.getDecoder().decode(parts[2]);
			byte[] hash = Base64.getDecoder().decode(parts[3]);
			return iterations > 0 && salt.length > 0 && hash.length > 0;
		} catch (IllegalArgumentException er) {
			return false;
		}
	}

	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int keyLengthBits) {
		try {
			KeySpec spec = new PBEKeySpec(password, salt, iterations, keyLengthBits);
			SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
			return factory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException er) {
			throw new IllegalStateException("No se pudo generar el hash de contraseña", er);
		}
	}

	private static boolean constantTimeEquals(byte[] a, byte[] b) {
		if (a == null || b == null || a.length != b.length) {
			return false;
		}

		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result |= a[i] ^ b[i];
		}

		return result == 0;
	}
}
