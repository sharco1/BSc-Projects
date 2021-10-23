import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

class RSA {
    BigInteger public_key;
    BigInteger private_key;
    BigInteger n;
    BigInteger phi_n;
    BigInteger e;
    BigInteger d;

    int[] Messege;
    BigInteger[] ciphered;

    public RSA(int[] messege) {
        Messege = new int[messege.length];
        for (int i = 0; i < messege.length; i++) this.Messege[i] = messege[i];
        SecureRandom r = new SecureRandom();
        // Generating 2 Prime Numbers for the Values of Public Key and Private Key
        // Everyone Has the Private Key But Only the Person with the Private Key can Decode the Messeges
        // Delivered to the address with the Public Key
        public_key = BigInteger.probablePrime(3,r);
        // q
        private_key = BigInteger.probablePrime(3,r);
        while(public_key.equals(private_key))   private_key = BigInteger.probablePrime(3,r);
        // n = p * q
        n = public_key.multiply(private_key);
        // phi_n = (p-1) * (q-1)
        phi_n = (public_key.subtract(BigInteger.ONE)).multiply(private_key.subtract(BigInteger.ONE));
        // gcd(e, phi_n) = 1
        e = new BigInteger("3");
        while (phi_n.gcd(e).intValue() > 1) e = e.add(new BigInteger("2"));
        // e * d mod(phi_n) = 1
        d = e.modInverse(phi_n);

        ciphered = new BigInteger[Messege.length];
        for (int i = 0; i < messege.length; i++) {
            Integer integer = Messege[i];
            BigInteger value = BigInteger.valueOf(integer.intValue());
            ciphered[i] = value;
        }
    }

    // Encryption : cipher txt = (plain txt)^e mod(n)
    // We Do the process letter by letter
    // So we can easily change them to String later According to the Chart Given
    public BigInteger[] RSA_Enc() {
        //System.out.println(public_key+" "+private_key);
        for (int i = 0; i < Messege.length; i++) {
            Integer myInteger = Messege[i];
            BigInteger value = BigInteger.valueOf(myInteger.intValue());
            BigInteger cipher_value = value.modPow(e, n);
            ciphered[i] = cipher_value;
        }

        return ciphered;
    }

    // Decryption : plain txt = (cipher txt)^d mod(n)
    public BigInteger[] RSA_Dec() {
        BigInteger[] plain = new BigInteger[Messege.length];
        for (int i = 0; i < Messege.length; i++) {
            plain[i] = ciphered[i].modPow(d, n);
        }

        return plain;
    }
}

public class Main {

    // This Function Turns an Array of Bigintegers into a String According to the Chart Given
    public static String ToString(BigInteger[] array) {
        String text = "";
        int[] messege = new int[array.length];
        for (int i = 0; i < array.length; i++)
            messege[i] = array[i].intValue();

        for (int i = 0; i < array.length; i++) {
            if (messege[i] == 0) text += " ";
            else if (messege[i] == 1) text += "A";
            else if (messege[i] == 2) text += "B";
            else if (messege[i] == 3) text += "C";
            else if (messege[i] == 4) text += "D";
            else if (messege[i] == 5) text += "E";
            else if (messege[i] == 6) text += "F";
            else if (messege[i] == 7) text += "G";
            else if (messege[i] == 8) text += "H";
            else if (messege[i] == 9) text += "I";
            else if (messege[i] == 10) text += "J";
            else if (messege[i] == 11) text += "K";
            else if (messege[i] == 12) text += "L";
            else if (messege[i] == 13) text += "M";
            else if (messege[i] == 14) text += "N";
            else if (messege[i] == 15) text += "O";
            else if (messege[i] == 16) text += "P";
            else if (messege[i] == 17) text += "Q";
            else if (messege[i] == 18) text += "R";
            else if (messege[i] == 19) text += "S";
            else if (messege[i] == 20) text += "T";
            else if (messege[i] == 21) text += "U";
            else if (messege[i] == 22) text += "V";
            else if (messege[i] == 23) text += "W";
            else if (messege[i] == 24) text += "X";
            else if (messege[i] == 25) text += "Y";
            else if (messege[i] == 26) text += "Z";
            else if (messege[i] == 27) text += "0";
            else if (messege[i] == 28) text += "1";
            else if (messege[i] == 29) text += "2";
            else if (messege[i] == 30) text += "3";
            else if (messege[i] == 31) text += "4";
            else if (messege[i] == 32) text += "5";
            else if (messege[i] == 33) text += "6";
            else if (messege[i] == 34) text += "7";
            else if (messege[i] == 35) text += "8";
            else if (messege[i] == 36) text += "9";
            else if (messege[i] == 37) text += ".";
            else if (messege[i] == 38) text += "?";
            else if (messege[i] == 39) text += ",";
            else if (messege[i] == 40) text += "-";
        }
        return text;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("messege.txt")));
        String Messege = sc.nextLine();
        int[] array = new int[Messege.length()];
        // Changes the String of Original Messege into Numbers According to the Chart Given
        for (int i = 0; i < Messege.length(); i++) {
            char c = Messege.charAt(i);
            if (c == ' ') array[i] = 0;
            else if (c == 'A' || c == 'a') array[i] = 1;
            else if (c == 'B' || c == 'b') array[i] = 2;
            else if (c == 'C' || c == 'c') array[i] = 3;
            else if (c == 'D' || c == 'd') array[i] = 4;
            else if (c == 'E' || c == 'e') array[i] = 5;
            else if (c == 'F' || c == 'f') array[i] = 6;
            else if (c == 'G' || c == 'g') array[i] = 7;
            else if (c == 'H' || c == 'h') array[i] = 8;
            else if (c == 'I' || c == 'i') array[i] = 9;
            else if (c == 'J' || c == 'j') array[i] = 10;
            else if (c == 'K' || c == 'k') array[i] = 11;
            else if (c == 'L' || c == 'l') array[i] = 12;
            else if (c == 'M' || c == 'm') array[i] = 13;
            else if (c == 'N' || c == 'n') array[i] = 14;
            else if (c == 'O' || c == 'o') array[i] = 15;
            else if (c == 'P' || c == 'p') array[i] = 16;
            else if (c == 'Q' || c == 'q') array[i] = 17;
            else if (c == 'R' || c == 'r') array[i] = 18;
            else if (c == 'S' || c == 's') array[i] = 19;
            else if (c == 'T' || c == 't') array[i] = 20;
            else if (c == 'U' || c == 'u') array[i] = 21;
            else if (c == 'V' || c == 'v') array[i] = 22;
            else if (c == 'W' || c == 'w') array[i] = 23;
            else if (c == 'X' || c == 'x') array[i] = 24;
            else if (c == 'Y' || c == 'y') array[i] = 25;
            else if (c == 'Z' || c == 'z') array[i] = 26;
            else if (c == '0') array[i] = 27;
            else if (c == '1') array[i] = 28;
            else if (c == '2') array[i] = 29;
            else if (c == '3') array[i] = 30;
            else if (c == '4') array[i] = 31;
            else if (c == '5') array[i] = 32;
            else if (c == '6') array[i] = 33;
            else if (c == '7') array[i] = 34;
            else if (c == '8') array[i] = 35;
            else if (c == '9') array[i] = 36;
            else if (c == '.') array[i] = 37;
            else if (c == '?') array[i] = 38;
            else if (c == ',') array[i] = 39;
            else if (c == '-') array[i] = 40;
        }

        // Making an Instance of RSA Class
        int starttime = (int) System.nanoTime();
        int endtime;
        RSA rsa = new RSA(array);
        // Encode/Cipher the Messege
        BigInteger[] enc = rsa.RSA_Enc();
        endtime = (int) System.nanoTime();
        // Turn the Ciphered Integers into Text According to the Chart Given
        String cypher = ToString(enc);
        int res = endtime-starttime;
        System.out.println("Cipher Text is '"+cypher+"'"+"" +"\tIt took\t"+res+"\tnano s");
        // Decode the Ciphered Messege
        BigInteger[] dec = rsa.RSA_Dec();
        // Turn the Ciphered Integers into Text According to the Chart Given
        String ans = ToString(dec);
        System.out.print("Plain Text is '"+ans+"'");
    }
}




