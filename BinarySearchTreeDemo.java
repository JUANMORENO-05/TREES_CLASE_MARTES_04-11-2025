public class BinarySearchTreeDemo {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int value) {
            this.value = value;
        }
    }

    static class BinarySearchTree {

        TreeNode root;

        public TreeNode findValue(TreeNode current, int target) {
            if (current == null) return null;
            if (current.value == target) return current;
            if (target < current.value) return findValue(current.left, target);
            return findValue(current.right, target);
        }

        public TreeNode find(int target) {
            return findValue(root, target);
        }

        public void insertNode(TreeNode current, int newValue) {
            if (newValue == current.value) return;

            if (newValue < current.value) {
                if(current.left != null) insertNode(current.left, newValue);
                else {
                    current.left = new TreeNode(newValue);
                    current.left.parent = current;
                }
            } else {
                if(current.right != null) insertNode(current.right, newValue);
                else {
                    current.right = new TreeNode(newValue);
                    current.right.parent = current;
                }
            }
        }

        public void insert(int newValue) {
            if (root == null) root = new TreeNode(newValue);
            else insertNode(root, newValue);
        }

        private void inorderRec(TreeNode node) {
            if (node != null) {
                inorderRec(node.left);
                System.out.print(node.value + " ");
                inorderRec(node.right);
            }
        }

        public void inorder() {
            System.out.print("In-Order: ");
            inorderRec(root);
            System.out.println();
        }

        private void preorderRec(TreeNode node) {
            if (node != null) {
                System.out.print(node.value + " ");
                preorderRec(node.left);
                preorderRec(node.right);
            }
        }

        public void preorder() {
            System.out.print("Pre-Order: ");
            preorderRec(root);
            System.out.println();
        }

        private void postorderRec(TreeNode node) {
            if (node != null) {
                postorderRec(node.left);
                postorderRec(node.right);
                System.out.print(node.value + " ");
            }
        }

        public void postorder() {
            System.out.print("Post-Order: ");
            postorderRec(root);
            System.out.println();
        }

        public void delete(int value) {
            root = deleteRec(root, value);
        }

        private TreeNode deleteRec(TreeNode node, int value) {
            if (node == null) return null;

            if (value < node.value) {
                node.left = deleteRec(node.left, value);
            } else if (value > node.value) {
                node.right = deleteRec(node.right, value);
            } else {
                if (node.left == null && node.right == null) return null;
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;

                TreeNode successor = findMin(node.right);
                node.value = successor.value;
                node.right = deleteRec(node.right, successor.value);
            }
            return node;
        }

        private TreeNode findMin(TreeNode node) {
            while (node.left != null) node = node.left;
            return node;
        }
    }

    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("Creando árbol binario de búsqueda");

        int[] values = {8,3,10,1,6,14,4,7,13};

        for(int v : values) {
            tree.insert(v);
        }

        tree.inorder();
        tree.preorder();
        tree.postorder();

        tree.delete(3);

        tree.inorder();
        tree.preorder();
        tree.postorder();
    }
}

