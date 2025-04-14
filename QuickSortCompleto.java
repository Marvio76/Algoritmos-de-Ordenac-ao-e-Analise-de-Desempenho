import java.util.Random;

public class QuickSortCompleto {

    public static void quickSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = particionar(array, inicio, fim);

            if (pivoIndex > inicio) {
                quickSort(array, inicio, pivoIndex - 1);
            }

            if (pivoIndex < fim) {
                quickSort(array, pivoIndex + 1, fim);
            }
        }
    }

    // ✅ Pivô aleatório para evitar recursão infinita
    public static int particionar(int[] array, int inicio, int fim) {
        Random rand = new Random();
        int pivoIndex = inicio + rand.nextInt(fim - inicio + 1);

        int temp = array[pivoIndex];
        array[pivoIndex] = array[fim];
        array[fim] = temp;

        int pivo = array[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (array[j] <= pivo) {
                i++;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        temp = array[i + 1];
        array[i + 1] = array[fim];
        array[fim] = temp;

        return i + 1;
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

        System.out.printf("\n🔎 Testando QuickSort - Entrada: %s | Tamanho: %d\n", tipoEntrada, tamanho);

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
            quickSort(vetor, 0, vetor.length - 1);
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
