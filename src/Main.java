import java.util.Random;

public class Main {
    public static void main(String[] args) {

        NoArvore tree = null;
        Random random = new Random();
        DSW dsw = new DSW();

        System.out.println("Inserindo números na árvore: ");
        for (int i = 0; i < 20; i++) {
            int num = random.nextInt(101);
            System.out.println("Inserindo " + num);
            if (tree == null) {
                tree = new NoArvore();
                tree = tree.InsereRaiz(tree, num);
            } else {
                tree = tree.Insere(tree, num);
            }
        }

        System.out.println("Imprimindo árvore antes de balancear:");
        tree.Imp_Cres(tree);

        System.out.println("Balanceando a árvore...");
        tree = dsw.balancear(tree);

        System.out.println("Imprimindo árvore balanceada em ordem crescente:");
        tree.Imp_Cres(tree);
    }
}