package com.meli.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidadorCadenaAdn {

    private static final String PATRON_SECUENCIAS = "CCCC|TTTT|GGGG|AAAA";
    private static final String PATRON_MATRIX_VALIDA = "[^ACTG]";
    private final Pattern pattern = Pattern.compile(PATRON_SECUENCIAS);
    private final Pattern patternValidacion = Pattern.compile(PATRON_MATRIX_VALIDA);

    public boolean validarSecuencia(String[] matrixAdn) {
        long coincidencias = 0L;

         if (buscarAdn(matrixAdn, patternValidacion) > 0)
             throw new IllegalArgumentException("La matriz contiene caracteres invalidos");

        coincidencias += buscarAdn(matrixAdn, pattern);
        if (coincidencias<2) {
            coincidencias += buscarAdn(transponerMatrix(matrixAdn), pattern);
            if (coincidencias < 2)
                coincidencias += buscarAdn(diagonales(matrixAdn), pattern);
        }
        return (coincidencias > 1);
    }

    //busca la occurrencia  de las secuencias mutantes en la matrix
    private long buscarAdn(String[] matrix, Pattern patron) {
        return Stream.of(matrix)
                .map(x -> new Scanner(x).findAll(patron)
                        .map(m -> m.group())
                        .collect(Collectors.toList())
                )
                .flatMap(List::stream)
                .count();
    }

    // Transponer una matriz
    private String[] transponerMatrix(String[] matrix) {
        String[] matrixTranspuesta = new String[matrix[0].length()];
        for (int i = 0; i < matrix[0].length(); i++) {
            for (String s : matrix) matrixTranspuesta[i] += s.charAt(i);
        }
        return matrixTranspuesta;
    }

    private String[] diagonales(String[] matrix) {
        StringBuilder diagonalSI = new StringBuilder();
        StringBuilder diagonalSD = new StringBuilder();
        StringBuilder diagonalII = new StringBuilder();
        StringBuilder diagonalID = new StringBuilder();
        int length = matrix.length - 1;
        List<String> diagonales = new ArrayList<>();
        for (int i = 3; i <= length; i++) {
            int c = 0;
            for (int f = i; f >= 0; f--) {
                diagonalSI.append(matrix[f].charAt(c));
                diagonalSD.append(matrix[f].charAt(length - c));
                diagonalID.append(matrix[length - f].charAt(length - c));
                diagonalII.append(matrix[length - c].charAt(f));
                c++;
            }
            diagonales.add(diagonalSI.toString());
            diagonales.add(diagonalSD.toString());
            if (i < length) {
                diagonales.add(diagonalID.toString());
                diagonales.add(diagonalII.toString());
            }
            diagonalSI.setLength(0);
            diagonalID.setLength(0);
            diagonalSD.setLength(0);
            diagonalII.setLength(0);
        }
        return diagonales.toArray(new String[0]);
    }
}
