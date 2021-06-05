package encryptiondecryption;

import java.io.FileInputStream;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {

        String type = "enc";
        int key = 0;
        String myString = "";
        String in = "";
        String out = "";
        Algorithm algo = algo = new Algorithm(new Shift());

        for(int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    type = args[i+1];
                    break;
                case "-key":
                    key = Integer.valueOf(args[i+1]);
                    break;
                case "-data":
                    myString = args[i+1];
                    break;
                case "-in":
                    in = args[i+1];
                    break;
                case "-out":
                    out = args[i+1];
                    break;
                case "-alg":
                    if("unicode".equals(args[i+1])) {
                        algo = new Algorithm(new Unicode());
                    }
                    break;
                default:
                    break;
            }
        }

        if(myString.isEmpty()) {
            if(!in.isEmpty()) {
                try(FileInputStream reader = new FileInputStream(in)) {
                    myString = new String(reader.readAllBytes());
                }catch(Exception e) {
                    System.out.println("Error");
                }
            }
        }

        String answer = algo.encrypterDecryptor(type, key, myString);

        if("".equals(out)) {
            System.out.println(answer);
        }else {
            try (FileWriter writer = new FileWriter(out)) {
                writer.write(answer);
            } catch(Exception e) {
                System.out.println("Error");
            }
        }
    }
}

class Algorithm {
    private IAlgorithm strategy;


    public Algorithm(IAlgorithm strategy) {
        this.strategy = strategy;
    }

    public String encrypterDecryptor(String type, int key, String text) {
        if(type.equals("dec")) {
            return strategy.dec(key, text);
        }
        return strategy.enc(key, text);
    }
}

interface IAlgorithm {
    String enc(int key, String text);
    String dec(int key, String text);
}

class Shift implements IAlgorithm {
    private String mySimple ;
    private String newString;

    public Shift() {
        mySimple = new String("abcdefghijklmnopqrstuvwxyz");
        newString = "";
    }

    @Override
    public String enc(int key, String text) {
        for(int i = 0; i<text.length(); i++) {
            if(mySimple.indexOf(text.charAt(i)) != -1) {
                int length = mySimple.indexOf(text.charAt(i)) + key;
                newString += mySimple.charAt(length % 26);
            }else {
                newString += text.charAt(i);
            }
        }
        return newString;
    }

    @Override
    public String dec(int key, String text) {
        for(int i = 0; i<text.length(); i++) {
            if(mySimple.indexOf(text.charAt(i)) != -1) {
                int length = mySimple.indexOf(text.charAt(i)) - key;
                if(length < 0) {
                    length = 26 + length ;
                }
                newString += mySimple.charAt(length);
            }else {
                newString += text.charAt(i);
            }
        }
        return newString;
    }

}

class Unicode implements IAlgorithm {
    private String newString;

    public Unicode() {
        newString = "";
    }

    @Override
    public String enc(int key, String text) {
        for(int i = 0; i < text.length(); i++) {
            newString += Character.toString(text.charAt(i) + key);
        }
        return newString;
    }

    @Override
    public String dec(int key, String text) {
        for(int i = 0; i < text.length(); i++) {
            newString += Character.toString(text.charAt(i) - key);
        }
        return newString;
    }

}