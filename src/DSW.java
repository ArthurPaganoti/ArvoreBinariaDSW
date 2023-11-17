import java.util.LinkedList;

class DSW {
    // ...

    public void realizarRotacoesParaEquilibrar(LinkedList<NoArvore> lista) {
        while (!lista.isEmpty()) {
            if (lista.size() > 1) {
                NoArvore no1 = lista.removeFirst();
                NoArvore no2 = lista.removeFirst();
                lista.addLast(no2);
                lista.addLast(no1);
            }
        }
    }

    public NoArvore reconstruirArvore(LinkedList<NoArvore> lista) {
        if (lista.isEmpty()) {
            return null;
        } else {
            NoArvore raiz = lista.removeFirst();
            while (!lista.isEmpty()) {
                NoArvore no = lista.removeFirst();
                raiz = inserir(raiz, no);
            }
            return raiz;
        }
    }

    private NoArvore inserir(NoArvore raiz, NoArvore no) {
        if (raiz == null) {
            raiz = no;
        } else if (no.valor < raiz.valor) {
            raiz.esquerda = inserir(raiz.esquerda, no);
        } else {
            raiz.direita = inserir(raiz.direita, no);
        }
        return raiz;
    }

    public NoArvore balancear(NoArvore root) {
        root = treeToVine(root);
        root = vineToTree(root, countNodes(root));
        return root;
    }

    private NoArvore treeToVine(NoArvore root) {
        NoArvore vineTail = root;
        NoArvore remainder = vineTail.direita;
        NoArvore tempPtr;

        while (remainder != null) {
            if (remainder.esquerda == null) {
                vineTail = remainder;
                remainder = remainder.direita;
            } else {
                tempPtr = remainder.esquerda;
                remainder.esquerda = tempPtr.direita;
                tempPtr.direita = remainder;
                remainder = tempPtr;
                vineTail.direita = tempPtr;
            }
        }

        return root;
    }

    private int countNodes(NoArvore root) {
        int count = 0;
        NoArvore currNode = root;
        while (currNode != null) {
            currNode = currNode.direita;
            count++;
        }
        return count;
    }

    private NoArvore vineToTree(NoArvore root, int nodes) {
        int log2Nodes = (int) (Math.log(nodes + 1) / Math.log(2));
        int leaves = nodes + 1 - (int) (Math.pow(2, log2Nodes));
        compress(root, leaves);

        nodes = nodes - leaves;
        while (nodes > 1) {
            compress(root, nodes / 2);
            nodes = nodes / 2;
        }

        return root;
    }

    private void compress(NoArvore root, int count) {
        NoArvore scanner = root;
        NoArvore child;
        NoArvore grandChild;

        for (int i = 0; i < count; i++) {
            child = scanner.direita;
            grandChild = scanner.direita.direita;
            scanner.direita = grandChild;
            scanner = grandChild;
            child.direita = grandChild.esquerda;
            grandChild.esquerda = child;
        }
    }
}