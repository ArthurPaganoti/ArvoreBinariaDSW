import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NoArvore {

    int info;
    NoArvore direita = null;
    NoArvore esquerda = null;
    int valor;

    void Imp_Cres(NoArvore tree) {
        if (tree != null) {
            Imp_Cres(tree.esquerda);
            System.out.println(tree.info);
            Imp_Cres(tree.direita);
        }
    }

    NoArvore Search(NoArvore tree, int valor) {
        if (tree == null) {
            return null;
        } else if (tree.info == valor) {
            return tree;
        } else if (valor < tree.info) {
            return Search(tree.esquerda, valor);
        } else {
            return Search(tree.direita, valor);
        }
    }

    NoArvore InsereRaiz(NoArvore tree, int valor) {
        tree.info = valor;
        tree.direita = null;
        tree.esquerda = null;

        return tree;
    }

    NoArvore Insere(NoArvore tree, int valor) {
        if (tree == null) {
            tree = new NoArvore();
            tree.info = valor;
            tree.direita = null;
            tree.esquerda = null;
        } else if (valor < tree.info) {
            tree.esquerda = Insere(tree.esquerda, valor);
        } else {
            tree.direita = Insere(tree.direita, valor);
        }
        return tree;
    }

    NoArvore retira(NoArvore tree, int valor) {
        if (tree == null) {
            System.out.println("Elemento não encontrado..: " + valor);
            return null;
        } else if (tree.info > valor) {
            tree.esquerda = retira(tree.esquerda, valor);
        } else if (tree.info < valor) {
            tree.direita = retira(tree.direita, valor);
        } else {
            // Nó sem filhos
            if (tree.esquerda == null && tree.direita == null) {
                tree = null;
            } else if (tree.esquerda == null) {
                // Nó só tem filho à direita
                tree = tree.direita;
            } else if (tree.direita == null) {
                // Nó só tem filho à esquerda
                tree = tree.esquerda;
            } else {
                // Nó tem dois filhos
                NoArvore novo = encontraMenor(tree.direita);
                tree.info = novo.info; // Troca de informações
                tree.direita = retira(tree.direita, novo.info);
            }
        }
        return tree;
    }

    // Função auxiliar para encontrar o nó com o menor valor em uma subárvore
    NoArvore encontraMenor(NoArvore tree) {
        while (tree.esquerda != null) {
            tree = tree.esquerda;
        }
        return tree;
    }

    void Imp_Decres(NoArvore tree) {
        if (tree != null) {
            Imp_Decres(tree.esquerda);
            Imp_Decres(tree.direita);
            System.out.println(tree.info);
        }
    }

    void ImpNivel(NoArvore tree) {
        if (tree == null) {
            return;
        }

        Queue<NoArvore> queue = new LinkedList<>();
        queue.add(tree);

        while (!queue.isEmpty()) {
            NoArvore node = queue.poll();
            System.out.println(node.info);

            if (node.esquerda != null) {
                queue.add(node.esquerda);
            }
            if (node.direita != null) {
                queue.add(node.direita);
            }
        }
    }

    int tamanho(NoArvore tree) {
        if (tree == null) {
            return 0;
        } else {
            return 1 + tamanho(tree.esquerda) + tamanho(tree.direita);
        }
    }

    List<Integer> transformarArvoreEmLista() {
        return transformarArvoreEmLista(this);
    }

    private List<Integer> transformarArvoreEmLista(NoArvore arvore) {
        if (arvore == null) {
            return new ArrayList<>();
        } else {
            List<Integer> lista = transformarArvoreEmLista(arvore.esquerda);
            lista.add(arvore.valor);
            lista.addAll(transformarArvoreEmLista(arvore.direita));
            return lista;
        }
    }


}