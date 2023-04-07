package retail;

class SplayTree {
    public WordSearchCount root;

    public SplayTree() {
        this.root = null;
    }

    public WordSearchCount maximum(WordSearchCount x) {
        while (x.right != null)
            x = x.right;
        return x;
    }

    public void leftRotate(WordSearchCount x) {
        WordSearchCount y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) { // x is root
            this.root = y;
        } else if (x == x.parent.left) { // x is left child
            x.parent.left = y;
        } else { // x is right child
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(WordSearchCount x) {
        WordSearchCount y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) { // x is root
            this.root = y;
        } else if (x == x.parent.right) { // x is left child
            x.parent.right = y;
        } else { // x is right child
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void splay(WordSearchCount n) {
        while (n.parent != null) { 
            if (n.parent == this.root) {
                if (n == n.parent.left) {
                    this.rightRotate(n.parent);
                } else {
                    this.leftRotate(n.parent);
                }
            } else {
                WordSearchCount p = n.parent;
                WordSearchCount g = p.parent; // grandparent

                if (n.parent.left == n && p.parent.left == p) { // both are left children
                    this.rightRotate(g);
                    this.rightRotate(p);
                } else if (n.parent.right == n && p.parent.right == p) { // both are right children
                    this.leftRotate(g);
                    this.leftRotate(p);
                } else if (n.parent.right == n && p.parent.left == p) {
                    this.leftRotate(p);
                    this.rightRotate(g);
                } else if (n.parent.left == n && p.parent.right == p) {
                    this.rightRotate(p);
                    this.leftRotate(g);
                }
            }
        }
    }

    public void insert(WordSearchCount n) {
        WordSearchCount y = null;
        WordSearchCount temp = this.root;
        while (temp != null) {
            y = temp;
            if (n.compareTo(temp) < 0)
                temp = temp.left;
            else
                temp = temp.right;
        }
        n.parent = y;

        if (y == null)
            this.root = n;
        else if (n.compareTo(y) < 0)
            y.left = n;
        else
            y.right = n;

        this.splay(n);
    }

    public WordSearchCount search(WordSearchCount x) {
        return search(root, x);
    }

    private WordSearchCount search(WordSearchCount n, WordSearchCount x) {
        if (n == null)
            return null;
        if (x.compareTo(n) == 0) {
            this.splay(n);
            return n;
        } else if (x.compareTo(n) < 0)
            return this.search(n.left, x);
        else if (x.compareTo(n) > 0)
            return this.search(n.right, x);
        else
            return null;
    }

    public void delete(WordSearchCount n) {
        this.splay(n);

        SplayTree leftSubtree = new SplayTree();
        leftSubtree.root = this.root.left;
        if (leftSubtree.root != null)
            leftSubtree.root.parent = null;

        SplayTree rightSubtree = new SplayTree();
        rightSubtree.root = this.root.right;
        if (rightSubtree.root != null)
            rightSubtree.root.parent = null;

        if (leftSubtree.root != null) {
            WordSearchCount m = leftSubtree.maximum(leftSubtree.root);
            leftSubtree.splay(m);
            leftSubtree.root.right = rightSubtree.root;
            this.root = leftSubtree.root;
        } else {
            this.root = rightSubtree.root;
        }
    }

    public void inorder(WordSearchCount n) {
        if (n != null) {
            inorder(n.left);
            System.out.println(n.getWord());
            inorder(n.right);
        }
    }
}