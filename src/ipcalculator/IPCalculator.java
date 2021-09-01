/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipcalculator;

/**
 *
 * @author Abdul Rahman Saleh
 */
import java.util.Scanner;
public class IPCalculator {

    /**
     * @param args the command line arguments
     */
    static String nolTambahan(String s){
        String temp = new String("00000000");
        return temp.substring(s.length())+ s;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int prefix;
        String ip, kelas = " ", subnet = "";
        
        System.out.println("===================================");
        System.out.println("     IP Calculator Sederhana");
        System.out.println("===================================");
        System.out.print("Masukkan IP       : ");
        ip = sc.nextLine();
        
        System.out.print("Masukkan prefix   : /");
        prefix = sc.nextInt();
        System.out.println("-----------------------------------");
        System.out.println("Format IP: " + ip + "/" + prefix);
        System.out.println("-----------------------------------");
        System.out.println("Keterangan: ");
        
        // Membagi IP dengan 4 oktet 
        String[] oktet = new String[4];
        oktet = ip.split("\\.");
        
        //menentukan subnet mask berdasarkan prefix
        switch(prefix){
            case 9:
                subnet = "255.128.0.0";
                break;
            case 10:
                subnet = "255.192.0.0";
                break;
            case 11:
                subnet = "255.224.0.0";
                break;
            case 12:
                subnet = "255.240.0.0";
                break;
            case 13:
                subnet = "255.248.0.0";
                break;
            case 14:
                subnet = "255.252.0.0";
                break;
            case 15:
                subnet = "255.254.0.0";
                break;
            case 16:
                subnet = "255.255.0.0";
                break;
            case 17:
                subnet = "255.255.128.0";
                break;
            case 18:
                subnet = "255.255.192.0";
                break;
            case 19:
                subnet = "255.255.224.0";
                break;
            case 20:
                subnet = "255.255.240.0";
                break;
            case 21:
                subnet = "255.255.248.0";
                break;
            case 22:
                subnet = "255.255.252.0";
                break;
            case 23:
                subnet = "255.255.254.0";
                break;
            case 24:
                subnet = "255.255.255.0";
                break;
            case 25:
                subnet = "255.255.255.128";
                break;
            case 26:
                subnet = "255.255.255.192";
                break;
            case 27:
                subnet = "255.255.255.224";
                break;
            case 28:
                subnet = "255.255.255.240";
                break;
            case 29:
                subnet = "255.255.255.248";
                break;
            case 30:
                subnet = "255.255.255.252";
                break;
            default:
                System.out.println("Mohon isi dengan prefix 9-30");
        }

        //menentukan kelas IP
        int oktet1 = Integer.valueOf(oktet[0]);
        if(oktet1 >= 1 && oktet1 <=255){
            if(oktet1 >= 1 && oktet1 <=127){
                kelas = "A";
            }else if(oktet1 >= 128 && oktet1 <=191){
                kelas = "B";
            }else if(oktet1 >= 192 && oktet1 <=223){
               kelas = "C";
            }else if(oktet1 >= 223 && oktet1 <=239){
               kelas = "D";
            }else if(oktet1 >= 240 && oktet1 <=255){
               kelas = "E";
            }
            System.out.println("Kelas             : " + kelas);
            System.out.println("Subnet mask       : " + subnet);
            
            
            //menentukan Net ID (Network Address)
            String split_binerIP[] = new String[4];
            String biner = "";
            for(int i=0;i<4;i++){
                split_binerIP[i] = nolTambahan(Integer.toBinaryString(Integer.parseInt(oktet[i])));
                //menggabungkan seluruh bilangan biner dari 4 oktet
                biner += split_binerIP[i];
            }
            int bits = (int)Math.ceil(Math.log(prefix)/Math.log(2));
            int binerIP[] = new int[32];
            for(int i=0; i<32; i++) {
                //mengonversi setiap character 0,1 ke integer 0,1
                binerIP[i] = (int)biner.charAt(i)-48;
            }
            for(int i=31; i>31-bits; i--){    
                //menentukan net id dengan akhiran 0
                binerIP[i] &= 0;
            }
            String netID[] = {"","","",""};

            for(int i=0;i<32;i++){
                netID[i/8] = new String(netID[i/8]+binerIP[i]);
            }
            System.out.print("Network address   : ");
            for(int i=0;i<4;i++){
                System.out.print(Integer.parseInt(netID[i],2));
                if(i!=3) System.out.print(".");
            }
            System.out.println();
            
            //menentukan Host ID
            int oktet4 = Integer.valueOf(oktet[3]);
            System.out.println("Host ID           : " + oktet4);
            
        }else{
            System.out.println("Mohon masukkan range IP 1-255");
        }
        System.out.println("===================================");
    }
}