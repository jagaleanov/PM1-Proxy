
/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorador;

import proxy.*;

/**
 *
 * @author jgale
 */
public class CifradoHill implements ICifrado{

    int[][] matrizCifrado = new int[2][2];
    //int[][] matrizCifradoInversa = new int[2][2];
    int[][] matrizMsjIni;
    int[][] matrizMsjIniT;
    int[][] matrizMsjFin;
    char[] vector;
    int cantidadColumnas;

    public CifradoHill() {

        matrizCifrado[0][0] = 2;
        matrizCifrado[0][1] = 7;
        matrizCifrado[1][0] = 1;
        matrizCifrado[1][1] = 4;

        /*
        matrizCifradoInversa[0][0] = 4;
        matrizCifradoInversa[0][1] = -7;
        matrizCifradoInversa[1][0] = -1;
        matrizCifradoInversa[1][1] = 2;
         */
    }

    public String cifrar(String msj) {
        
        cantidadColumnas = calcularColumnas(msj);

        matrizMsjIni = new int[cantidadColumnas][2];
        matrizMsjIniT = new int[2][cantidadColumnas];
        matrizMsjFin = new int[2][cantidadColumnas];

        //SETEA MATRIZ FINAL EN 0
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < cantidadColumnas; j++) {
                matrizMsjFin[i][j] = 0;
            }
        }

        imprimirMatriz(matrizMsjFin);

        try {
            vector = msj.toCharArray();

        } catch (Exception e) {
            System.out.println("Dato no enviado: " + e.getMessage());
        }
        
        construirMatriz();
        imprimirMatriz(matrizMsjIni);
        transponerMatriz();
        imprimirMatriz(matrizMsjIniT);
        multiplicarMatrices();
        imprimirMatriz(matrizMsjFin);
        
        return construirString();
    }

    private void imprimirMatriz(int[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
            System.out.println("-----------");
    }

    private int calcularColumnas(String msj) {

        int largo = msj.length();
        int cantidadColumnas = 0;

        if ((largo % 2) == 1) {
            cantidadColumnas = (largo / 2) + 1;
        } else {
            cantidadColumnas = (largo / 2);
        }

        return cantidadColumnas;
    }

    private void construirMatriz() {

        int posicion = 0;

        //SETEA MATRIZ INICIAL
        for (int i = 0; i < cantidadColumnas; i++) {
            for (int j = 0; j < 2; j++) {

                if (posicion == vector.length) {
                    continue;
                } else {
                    matrizMsjIni[i][j] = vector[posicion];
                    posicion++;
                }
            }
        }
    }

    private void transponerMatriz() {
        for (int i = 0; i < matrizMsjIni.length; i++) {
            for (int j = 0; j < matrizMsjIni[i].length; j++) {
                matrizMsjIniT[j][i] = matrizMsjIni[i][j];
            }
        }
    }

    private void multiplicarMatrices() {
        for (int i = 0; i < matrizCifrado.length; i++) {
            for (int j = 0; j < matrizMsjIniT[0].length; j++) {
                for (int k = 0; k < matrizCifrado[0].length; k++) {
                    System.out.println(i + " " + j + " " + k);
                    matrizMsjFin[i][j] += matrizCifrado[i][k] * matrizMsjIniT[k][j];
                }
            }
        }
    }

    private String construirString() {
        String retorno = "";

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < cantidadColumnas; j++) {
                retorno += ((char) matrizMsjFin[i][j]) + " ";
            }
        }
        
        return retorno;
    }

}
