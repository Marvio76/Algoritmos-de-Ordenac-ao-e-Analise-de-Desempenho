

import java.util.Random;

public class MergeSortCompleto {

    public static void mergeSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            mergeSort(array, inicio, meio);
            mergeSort(array, meio + 1, fim);
            merge(array, inicio, meio, fim);
        }
    }

    public static void merge(int[] array, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        int[] esquerda = new int[n1];
        int[] direita = new int[n2];

        for (int i = 0; i < n1; i++)
            esquerda[i] = array[inicio + i];
        for (int j = 0; j < n2; j++)
            direita[j] = array[meio + 1 + j];

        int i = 0, j = 0, k = inicio;

        while (i < n1 && j < n2) {
            if (esquerda[i] <= direita[j]) {
                array[k] = esquerda[i];
                i++;
            } else {
                array[k] = direita[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = esquerda[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = direita[j];
            j++;
            k++;
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

    public static int[] gerarVetorInversamenteOrdenado(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = tamanho - i;
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

    public static void testarCenario(String tipoEntrada, int tamanho, int repeticoes) {
        long[] tempos = new long[repeticoes];

        System.out.printf("\n🔎 Testando Merge Sort - Entrada: %s | Tamanho: %d\n", tipoEntrada, tamanho);

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor;

            if (tipoEntrada.equals("aleatória")) {
                vetor = gerarVetorAleatorio(tamanho);
            } else if (tipoEntrada.equals("inversa")) {
                vetor = gerarVetorInversamenteOrdenado(tamanho);
            } else {
                throw new IllegalArgumentException("Tipo de entrada inválido.");
            }

            long inicio = System.currentTimeMillis();
            mergeSort(vetor, 0, vetor.length - 1);
            long fim = System.currentTimeMillis();

            tempos[i] = fim - inicio;
            System.out.printf("Execução %2d: %d ms (%.3f s)\n", i + 1, tempos[i], tempos[i] / 1000.0);
        }

        double media = calcularMedia(tempos);
        double desvioPadrao = calcularDesvioPadrao(tempos, media);

        System.out.printf("📊 Média: %.2f ms | Desvio Padrão: %.2f ms\n", media, desvioPadrao);
    }

    public static void main(String[] args) {
        int[] tamanhos = {10000, 50000, 100000, 300000, 800000, 1000000};
        int repeticoes = 10;

        for (int tamanho : tamanhos) {
            System.out.println("\n===============================================");
            System.out.printf("⚙️  Tamanho do vetor: %d\n", tamanho);

            testarCenario("aleatória", tamanho, repeticoes);
            testarCenario("inversa", tamanho, repeticoes);
        }
    }
}
