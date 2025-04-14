package Atividade02;

import java.util.Random;

public class InsertionSortStats {

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int chave = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > chave) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = chave;
        }
    }

    public static int[] gerarVetorAleatorio(int tamanho) {
        Random rand = new Random();
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = rand.nextInt(100000);
        }
        return vetor;
    }

    public static double calcularMedia(long[] tempos) {
        long soma = 0;
        for (long tempo : tempos) {
            soma += tempo;
        }
        return (double) soma / tempos.length;
    }

    public static double calcularDesvioPadrao(long[] tempos, double media) {
        double soma = 0;
        for (long tempo : tempos) {
            soma += Math.pow(tempo - media, 2);
        }
        return Math.sqrt(soma / tempos.length);
    }

    public static void testarComTamanho(int tamanho, int repeticoes) {
        long[] tempos = new long[repeticoes];

        System.out.printf("\n⏱️ Iniciando testes para vetor de %d elementos:\n", tamanho);

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor = gerarVetorAleatorio(tamanho);
            long inicio = System.currentTimeMillis();

            insertionSort(vetor);

            long fim = System.currentTimeMillis();
            tempos[i] = fim - inicio;

            double tempoSegundos = tempos[i] / 1000.0;
            System.out.printf("Execução %2d: %d ms (%.3f s)\n", i + 1, tempos[i], tempoSegundos);
        }

        double media = calcularMedia(tempos);
        double desvioPadrao = calcularDesvioPadrao(tempos, media);

        System.out.printf("\n📊 Resultados para vetor com %d elementos:\n", tamanho);
        System.out.printf("Média de tempo: %.2f ms (%.3f s)\n", media, media / 1000.0);
        System.out.printf("Desvio padrão: %.2f ms (%.3f s)\n", desvioPadrao, desvioPadrao / 1000.0);
    }

    public static void main(String[] args) {
        int[] tamanhos = {10000, 50000, 100000, 300000, 800000, 1000000};
        int repeticoes = 10;

        for (int tamanho : tamanhos) {
            System.out.println("=============================================");
            if (tamanho > 100000) {
                System.out.println("⚠️ AVISO: Vetor com " + tamanho + " elementos pode demorar MUITO. Aguarde...");
            }
            testarComTamanho(tamanho, repeticoes);
        }
    }
}
