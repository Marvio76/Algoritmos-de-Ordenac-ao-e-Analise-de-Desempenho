

import java.util.Random;

public class InsertionSortCompleto {

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

        System.out.printf("\n🔎 Testando Insertion Sort - Entrada: %s | Tamanho: %d\n", tipoEntrada, tamanho);

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
            insertionSort(vetor);
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

            if (tamanho > 100000) {
                System.out.println("⚠️ AVISO: Pode demorar com Insertion Sort. Aguarde pacientemente...");
            }

            testarCenario("aleatória", tamanho, repeticoes);
            testarCenario("inversa", tamanho, repeticoes);
        }
    }
}
