/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naive.bayes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Septy
 */
public class NaiveBayes {

    public static ArrayList<String> readTeks(String bacateks) throws FileNotFoundException, IOException {
        File bacafile = new File(bacateks);
        FileReader inputDokumen = new FileReader(bacafile);
        BufferedReader bufferBaca = new BufferedReader(inputDokumen);
        StringBuffer content = new StringBuffer();
        String barisData;
        ArrayList<String> data = new ArrayList<String>();
        while ((barisData = bufferBaca.readLine()) != null) {
            content.append(barisData);
            content.append(System.getProperty("line.separator"));
            data.add(barisData);
        }
        return data;
}
     
      public static ArrayList<String> token(String kalimat) throws FileNotFoundException, IOException {
        ArrayList<String> listKata = new ArrayList<String>();
        StringTokenizer token = new StringTokenizer(kalimat, ",");//pemisahan kata dengan delimiter koma
        while (token.hasMoreTokens()) {
            listKata.add(token.nextToken());
        }
        return listKata;
    }
 
    public static String[][] saveToArray(ArrayList<String> input) throws FileNotFoundException, IOException{
        String[][] data=new String[input.size()][4];
        for (int i = 0; i < input.size(); i++) {
             ArrayList<String> item=token(input.get(i));
             for (int j = 0; j < item.size(); j++) {
                data[i][j]=item.get(j);//memasukkan data ke dalam array
            }
        }
        return data;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //inisialisasi
        ArrayList<String> listData          = readTeks("E:/DataLearningBayes.txt");
        String[][] dataLearning             = saveToArray(listData);
        String[][] label                    = new String[8][4];
        String[][] labelYes                 = new String[8][4];
        String[][] labelNo                  = new String[8][4];
        String[][] fiturCuaca               = new String[8][4];
        String[][] fiturTemperatur          = new String[8][4];
        String[][] fiturKecepatanAngin      = new String[8][4];
        String[][] cuaca                    = new String[8][4];
        String[][] temperatur               = new String[8][4];
        String[][] kecepatan                = new String[8][4];
        String[][] dataTes                  = new String[2][3];
        int i=0, jumlah;
        
        double py = 0,pn = 0;
        double pyakh = 0,pnakh = 0;
        double pcuaca_dalamy=0, pcuaca_dalamn=0, pcuacay=0,pcuacan=0;
        double ptemp_dalamy=0, ptemp_dalamn=0, ptempy=0,ptempn=0;
        double pkec_dalamy=0, pkec_dalamn=0, pkecy=0,pkecn=0;
        double psimpuly=0, psimpuln=0;
        int jumdata;
        
        //----------SEMANGAT SEPTY--------------------
        
        System.out.println("--------------------------------------------------");
        System.out.println("                    NAIVE BAYES");
        System.out.println("--------------------------------------------------");
        System.out.println("Class Label : " + dataLearning[0][0] + " , " + dataLearning[0][1] );
        System.out.println("Fitur       : " + dataLearning[1][0] + " , " + dataLearning[1][1]+ " , " + dataLearning[1][2] );
        for(int c = 2; c < 8 ; c++){
            System.out.println("Data Set ke-"  + (c-1) +" :" + " " +dataLearning[c][0] + " , " + dataLearning[c][1] + " , " + dataLearning[c][2]+ " , " + dataLearning[c][3]);
        }
        jumdata = dataLearning.length-2;
        for(i=2;i<dataLearning.length;i++){
             label[i][3] = dataLearning[i][3];
             cuaca[i][0] = dataLearning[i][0];
             temperatur[i][1] = dataLearning[i][1];
             kecepatan[i][2] = dataLearning[i][2];
             if(label[i][3].equals(dataLearning[0][0])){
            py+=1;
        } else {
                 pn+=1;
             }
             pyakh=py/jumdata;
             pnakh=pn/jumdata;
         }
      
        //inputan
         
          Scanner cs = new Scanner (System.in);
          System.out.println("--------------------------------------------------");
          System.out.println("Masukkan Fitur [1.Cuaca, 2.Temperatur, 3.Kecepatan]");
           for(int a=0;a<1;a++){
              for(int b=0;b<3;b++){
                  System.out.print("Fitur: ");
                  dataTes[a][b]=cs.nextLine();
              }
          }
        System.out.println("--------------------------------------------------");
        //cuaca
       if(dataTes[0][0]!="-"){
            for(i=2;i<dataLearning.length;i++){
           if(dataTes[0][0].equals(cuaca[i][0]) & label[i][3].equals(dataLearning[0][0]))
               pcuaca_dalamy+=1;
            else if(dataTes[0][0].equals(cuaca[i][0]) & label[i][3].equals(dataLearning[0][1]))
               pcuaca_dalamn+=1;
           }
           pcuaca_dalamy=pcuaca_dalamy/jumdata;
           pcuaca_dalamn=pcuaca_dalamn/jumdata;
           
           pcuacay=pcuaca_dalamy/pyakh;
           pcuacan=pcuaca_dalamn/pnakh;
       } else{
           pcuacan=1;
           pcuacay=1;
       }
       System.out.println("Cuaca | Yes : "+pcuacay);
       System.out.println("Cuaca | No : "+pcuacan);  
       
       //temperatur
        if(dataTes[0][1]!="-"){
        for(i=2;i<dataLearning.length;i++){
           if(dataTes[0][1].equals(temperatur[i][1]) & label[i][3].equals(dataLearning[0][0]))
               ptemp_dalamy+=1;
            else if(dataTes[0][0].equals(temperatur[i][1]) & label[i][3].equals(dataLearning[0][1]))
               ptemp_dalamn+=1;
           }
           ptemp_dalamy=ptemp_dalamy/jumdata;
           ptemp_dalamn=ptemp_dalamn/jumdata;
           
           ptempy=ptemp_dalamy/pyakh;
           ptempn=ptemp_dalamn/pnakh;
       } else{
           ptempn=1;
           ptempy=1;
       }
       System.out.println("Temperatur | Yes : "+ptempy);
       System.out.println("Temperatur | No : "+ptempn);  
       
       //Kecepatan angin
        if(dataTes[0][2]!="-"){
        for(i=2;i<dataLearning.length;i++){
           if(dataTes[0][2].equals(kecepatan[i][2]) & label[i][3].equals(dataLearning[0][0]))
               pkec_dalamy+=1;
            else if(dataTes[0][2].equals(kecepatan[i][2]) & label[i][3].equals(dataLearning[0][1]))
               pkec_dalamn+=1;
           }
           pkec_dalamy=pkec_dalamy/jumdata;
           pkec_dalamn=pkec_dalamn/jumdata;
           
           pkecy=pkec_dalamy/pyakh;
           pkecn=pkec_dalamn/pnakh;
       } else{
           pkecn=1;
           pkecy=1;
       }
       System.out.println("Kecepatan angin | Yes : "+pkecy);
       System.out.println("Kecepatan angin | No : "+pkecn); 
       
//       psimpuly=pcuacay*ptempy*pkecy;
//       psimpuln=pcuacan*ptempn*pkecn;
//       
       if(dataTes[0][0]!="-" &&dataTes[0][2]!="-"){
           psimpuly=pcuacay*pkecy*pyakh;
           psimpuln=pcuacan*pkecn*pnakh;
           System.out.println("--------------------------------------------------");
           System.out.println("P(cuaca,kecepatan angin|y) = "+ psimpuly);
           System.out.println("P(cuaca,kecepatan angin|n) = "+ psimpuln);
           if(psimpuly>psimpuln){
               System.out.println("--------------------------------------------------");
               System.out.println("Hasil : YES,dia berolahraga");
           } else{
               System.out.println("Hasil : No,dia tidak berolahraga");
           }
       }
       else if(dataTes[0][0]!="-" && dataTes[0][1]!="-"){
           psimpuly=pcuacay*ptempy*pyakh;
           psimpuln=pcuacan*ptempn*pnakh;
           System.out.println("--------------------------------------------------");
           System.out.println("P(cuaca,temperatur|y) = "+ psimpuly);
           System.out.println("P(cuaca,temperatur|n) = "+ psimpuln);
           if(psimpuly>psimpuln){
               System.out.println("--------------------------------------------------");
               System.out.println("Hasil : YES,dia berolahraga");
           } else{
               System.out.println("Hasil : No,dia tidak berolahraga");
           }
       }
       
       else if(dataTes[0][1]!="-" && dataTes[0][2]!="-"){
           psimpuly=ptempy*pkecy*pyakh;
           psimpuln=ptempn*pkecn*pnakh;
           System.out.println("--------------------------------------------------");
           System.out.println("P(temperatur,kecepatan angin|y) = "+ psimpuly);
           System.out.println("P(temperatur,kecepatan angin|n) = "+ psimpuln);
           if(psimpuly>psimpuln){
               System.out.println("--------------------------------------------------");
               System.out.println("Hasil : YES,dia berolahraga");
           } else{
               System.out.println("Hasil : No,dia tidak berolahraga");
           }
       }
       
       else if(dataTes[0][1]!="-" && dataTes[0][0]!="-" && dataTes[0][2]!="-"){
           psimpuly=pcuacay*ptempy*pkecy*pyakh;
           psimpuln=pcuacan*ptempn*pkecn*pnakh;
           System.out.println("--------------------------------------------------");
           System.out.println("P(cuaca,temperatur,kecepatan angin|y) = "+ psimpuly);
           System.out.println("P(cuaca,temperatur,kecepatan angin|n) = "+ psimpuln);
           if(psimpuly>psimpuln){
               System.out.println("--------------------------------------------------");
               System.out.println("Hasil : YES,dia berolahraga");
           } else{
               System.out.println("Hasil : No,dia tidak berolahraga");
           }
       }
    }
}