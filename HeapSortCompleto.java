

import java.util.Random;

public class HeapSortCompleto {

    public static void heapSort(int[] array) {
        int n = array.length;

        // Constrói o heap (reorganiza o array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extrai os elementos um por um do heap
        for (int i = n - 1; i > 0; i--) {
            // Move o maior elemento para o final
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Chama heapify na subárvore reduzida
            heapify(array, i, 0);
        }
    }

    public static void heapify(int[] array, int n, int i) {
        int maior = i; // raiz
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n && array[esquerda] > array[maior])
            maior = esquerda;

        if (direita < n && array[direita] > array[maior])
            maior = direita;

        if (maior != i) {
            int troca = array[i];
            array[i] = array[maior];
            array[maior] = troca;

            // Recursivamente heapify a subárvore afetada
            heapify(array, n, maior);
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

        System.out.printf("\n🔎 Testando Heap Sort - Entrada: %s | Tamanho: %d\n", tipoEntrada, tamanho);

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
            heapSort(vetor);
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
