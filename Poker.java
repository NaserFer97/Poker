package org.example;


import java.util.ArrayList;
import java.util.*;

public class Poker {            //clase naipes con 2 atributos, el palo y el valor
    int [] indices = new int[5];
    String valoresNumericos = "A23456789TJQK";
    String palos []= {
            "S","C","H","D"};
    String cartas []={
            "","","","",""
    };

    private HashMap<String,Integer> hashPoker = new HashMap<>();



    public void getRandom(){
        Random random = new Random();
        String cartaActual;
        int cartasGeneradas=0;
         do{
             boolean flag =false;
            int indexNumerico =random.nextInt(13);
            int indexPalos = random.nextInt(4);


            cartaActual = String.format("%s%s",Character.toString(valoresNumericos.charAt(indexNumerico)),palos[indexPalos]);
            for (int i=0; i<5; i++){
                if (cartas[i].equals(cartaActual)){
                    flag = true;
                    break;
                }
            }
            if (!flag){
                cartas[cartasGeneradas]=cartaActual;
                cartasGeneradas++;
            }
        }while (cartasGeneradas<5);
         hashPoker();
        System.out.println(Arrays.asList(cartas));
    }
    public void hashPoker(){
        for (int x=0;x<5; x++){
            String numerico=String.valueOf(cartas[x].charAt(0));
            if (hashPoker.containsKey(numerico)){
                hashPoker.put(numerico,hashPoker.get(numerico)+Integer.parseInt("1"));
            }else {
                hashPoker.put(numerico,1);
            }
        }
    }
    public String cartaAlta(){
        if(indices[0]==0)
            for(int i = 0 ; i<5 ; i++)
                if(cartas[i].charAt(0) == 'A')
                    return cartas[i];
        for (int i = 0 ; i<5 ; i++)
            if(cartas[i].charAt(0) == valoresNumericos.charAt(indices[4]))
                return cartas[i];

        return "";
    }
    public boolean poker(){
        return hashPoker.containsValue(4);
    }
    public  boolean fullHouse(){
        return hashPoker.containsValue(3)&&hashPoker.containsValue(2);
    }
    public boolean par(){
        int contador=0;
        for (int i:hashPoker.values()){
            if (i==2){
                contador++;

            }
        }return contador==1;
    }
    public boolean doblePar(){
        int contador=0;
        for (int i:hashPoker.values()){
            if (i==2){
                contador++;

            }
        }return contador==2;
    }
    public boolean trio(){
        int contador=0;
        for (int i:hashPoker.values()){
            if (i==3){
                contador++;

            }
        }return contador==1;
    }
   public String check(){
        if (poker()){
            return "poker";
        }
       if (fullHouse()) {

            return "full house";
        }
       if (par()){
            return "par";
        }
       if (doblePar()){
            return "Doble par";
        }
       if (trio()){
            return "trio";
        }
       if(escalera() && color()){
           return "ESCALERA COLOR";
       }
       if(escalera())
           return "ESCALERA";
       if(color())
           return "COLOR";
       return "CARTA ALTA:  " + cartaAlta();

   }
    public boolean escalera(){

        for(int i = 0 ; i<5 ; i++)
            indices[i] = valoresNumericos.indexOf(cartas[i].charAt(0));

        Arrays.sort(indices);
        int contador = 0;
        for(int i = 0 ; i<4 ; i++)
            if(indices[i]+1 != indices[i+1]) {
                return false;
            }

        return true;


    }
   public boolean color(){
        char color = cartas[0].charAt(1);
        for(int i = 1 ; i<cartas.length ; i++)
            if(color != cartas[i].charAt(1))
                return false;

        return true;

   }
    public static void main(String[] args) {
        Poker p = new Poker();


        p.getRandom();
        //p.check();
        System.out.println(p.check());

    }


    }


