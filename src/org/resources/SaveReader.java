package org.resources;

import static java.lang.Math.pow;
import static java.lang.System.in;
import static java.lang.System.out;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SaveReader {
	private static List<BigInteger> primes = new LinkedList<>();
	private static BigInteger workingSave = BigInteger.ONE;
	private static BigInteger currentPrime = BigInteger.ONE;
	private static String curUser;
	static {
		primes.add(BigInteger.valueOf(2l));
	}
	public static void main(String[] args) throws IOException {
		BufferedSaves.loadProfiles();
		out.println("usernames:");
		out.println(Arrays.deepToString(BufferedSaves.usernames()));
		Scanner yolo = new Scanner(in);
		out.println("select username:");
		do {
			curUser = yolo.next();
			workingSave = BufferedSaves.getProfile(curUser);
			if (workingSave != null)
				break;
			out.println("Profile does not exist");
		} while (true);
		out.println("loading");
		reset();
		String password = nextDynamicString();
		out.println("password?");
		String userin;
		while (true) {
			userin = yolo.next();
			if (userin.equals(password))
				break;
			out.println("nope!");
		}
		out.println("you're in!");
		yolo.close();
	}
	public static void reset() {
		currentPrime = BigInteger.valueOf(1l);
	}
	public static void setSave(BigInteger b) {
		reset();
		workingSave = b;
	}
	public static boolean next() {
		currentPrime = nextPrime(currentPrime);
		return (workingSave.remainder(currentPrime).equals(BigInteger.ZERO));
	}
	public static String nextDynamicString() {
		return nextString(nextByte());
	}
	public static String nextString(int length) {
		String a = "";
		for (; length > 0; length--) {
			a += nextChar();
		}
		return a;
	}
	public static long getBits(int bits) {
		long b = 0;
		double end = pow(2, bits);
		for (double i = 1; i < end; i *= 2) {
			if (next())
				b += i;
		}
		return b;
	}
	public static byte nextByte() {
		return (byte) getBits(8);
	}
	public static char nextChar() {
		return (char) nextByte();
	}

	public static short nextShort() {
		return (short) getBits(16);
	}
	public static int nextInt() {
		return (int) getBits(32);
	}
	public static long nextLong() {
		return getBits(64);
	}
	public static int[] nextIntArray() {
		int[] temp = new int[nextInt()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = nextInt();
		}
		return temp;
	}
	public static BigInteger nextPrime(BigInteger b) {
		if (b.compareTo(primes.get(primes.size() - 1)) < 0) {
			for (BigInteger p : primes) {
				if (p.compareTo(b) > 0)
					return p;
			}
		}
		main : while (true) {
			b = b.add(BigInteger.ONE);
			for (BigInteger p : primes) {
				if (b.mod(p).equals(BigInteger.ZERO)) {
					continue main;
				}
			}
			primes.add(b);
			return b;
		}
	}
}