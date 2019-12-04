/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsainjava;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author madonna
 */
public class RSAINJAVA {

    // Bit length of each prime number.    
    int primeSize;
    // Two distinct large prime numbers p , q & Modulus N.
    BigInteger p, q, N;

    // phi = ( p – 1 ) * ( q – 1 )
    BigInteger phi;
    BigInteger E, D;
    String nt, dt, et;

    // String inputMessage, encryptedData, decryptedMessage;
    String publicKey, privateKey, randomNumber;
    // Generate two distinct large prime numbers p and q.

    public void generatePrimeNumbers() {
        p = new BigInteger(primeSize, new Random());
        while (!isprime(p)) {
            p = new BigInteger(primeSize, new Random());
        }
        do {
            q = new BigInteger(primeSize, new Random());
            while (!isprime(q)) {
                q = new BigInteger(primeSize, new Random());
            }
        } while (q.compareTo(p) == 0);
        System.out.println("q = " + q);
        System.out.println("p =" + p);
    }

    // Generate Public and Private Keys. 
    public void generatePublicPrivateKeys() {
// N = p * q
        N = p.multiply(q);

// phi = ( p – 1 ) * ( q – 1 )
        phi = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));

// Choose E, coprime to and less than phi
        do {
            E = new BigInteger(2 * primeSize, new Random());
        } while ((E.compareTo(phi) != -1) || (E.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));

        E = new BigInteger("7");
// Compute D, the inverse of E mod phi
        D = E.modInverse(phi);

    }

    // Get prime number p.    
    public BigInteger getp() {
        return (p);
    }

    // Get prime number q.
    public BigInteger getq() {
        return (q);
    }

    // Get phi.
    public BigInteger getphi() {
        return (phi);
    }

    //Get modulus N. 
    public BigInteger getN() {
        return (N);
    }

    // Get Public exponent E.
    public BigInteger getE() {
        return (E);
    }
    // Get Private exponent D.

    public BigInteger getD() {
        return (D);
    }

    public void RSA(int primeSize) {

        this.primeSize = primeSize;
// Generate two distinct large prime numbers p and q.
        generatePrimeNumbers();
// Generate Public and Private Keys.
        generatePublicPrivateKeys();
        /*
        BigInteger publicKeyB = getE();
        BigInteger privateKeyB = getD();
        BigInteger randomNumberB = getN();
        publicKey = publicKeyB.toString();
        privateKey = privateKeyB.toString();
        randomNumber = randomNumberB.toString();
        System.out.println("Public Key (E,N): " + publicKey + "," + randomNumber);
        System.out.println("Private Key (D,N): " + privateKey + "," + randomNumber);
         */
    }

    public String RSAencrypt(String plaintext) {

        E = getE();
        N = getN();
        System.out.println("************Encryption*************");
        System.out.println("E = " + E);
        System.out.println("D = " + D);
        System.out.println("N = " + N);
        BigInteger plaintext1 = new BigInteger(plaintext);
        String recoveredciphertext = decrypt(plaintext1, E, N);

        System.out.println(recoveredciphertext);
        return recoveredciphertext;
    }

    public String encrypt(BigInteger decrypted, BigInteger E, BigInteger N) {

        BigInteger encrypted;
        encrypted = decrypted.modPow(E, N);
        return encrypted.toString();
    }

    public String RSAdecrypt(String ciphertext) {
        D = getD();
        N = getN();
        System.out.println("*************Decryption*************");
        System.out.println("E = " + E);
        System.out.println("D = " + D);
        System.out.println("N = " + N);
        BigInteger ciphertext1 = new BigInteger(ciphertext);
        String recoveredPlaintext = decrypt(ciphertext1, D, N);

        System.out.println(recoveredPlaintext);
        return recoveredPlaintext;

    }

    public String decrypt(BigInteger encrypted, BigInteger D, BigInteger N) {

        BigInteger decrypted;
        decrypted = encrypted.modPow(D, N);

        return decrypted.toString();
    }

    private static boolean isprime(BigInteger x) {
        BigInteger b = new BigInteger(String.valueOf(x));
        return b.isProbablePrime(1);
    }
}
