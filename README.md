# EncryptionDecryption

Program to be run through terminal.
Program can take data directly as text or in the form of a file and can output in a file or in the command line.

How to execute:-

java Main -mode [1*] -in [2*] -out [3*] -key [4*] -alg [5*]
java Main -mode [1*] -key [4*] -data [6*] -alg [5*]

1* - type of operation; 'enc' for encryption or 'dec' for decryption.
2* - input file name
3* - output file name
4* - key for encryption (int)
5* - type of algorithm; 'shift' for ceasar or 'unicode' for unicode.
5* - direct data input in the form of a string. Example: -data "Welcome to life!" 

Commands :- (order doesn't matter)
-mode , -in, -out, -key, -alg, -data.
