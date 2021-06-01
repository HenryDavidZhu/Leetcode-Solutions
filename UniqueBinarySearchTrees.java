class Solution {
    public int numTrees(int N) {
        /* 
        Define the following functions:
        - f(n) = # of unique trees that can be constructed gven N nodes:
        - g(n, r) = # of unique trees that can be constructed given n nodes with
                    the root at node i.
        
        Given n nodes, there are n options for the root. Thus:
        f(n) = Sum of g(n, r) from r = 1 to n
        
        Let's express g(n, r) in terms of f(n):
        g(n, r) = # of possible left subtrees * # of right subtrees
        
        Since node i is already used used, 
        # of possible left subtrees = g(n - 1) <- use all nodes up to node i
        # of possible right subtrees = g(n - i) <- use all nodes after node i
        
        Thus, g(n, r) = f(r - 1) * f(n - r)
        
        Rewrite f(n) as:
        f(n) = Sum of f(r - 1) * f(n - r) from i = 1 to n
        
        Base Cases: 
        f(0) = 1 <- Only tree we can build is an empty tree
        f(1) = 1 <- Only tree we can build is the root
        
        Because we compute f(n) from f(r - 1) and f(n - r), we should conduct
        a bottom-up approach where we compute f(n) starting from n = 2 to
        n = N.
        */
        
        // fArray[i] = f(i)
        int[] fArray = new int[N + 1];
        fArray[0] = 1;
        fArray[1] = 1;

        // Compute f(N) from the bottom-up
        for (int n = 2; n <= N; n++) {
            // Compute f(n)
            for (int r = 1; r <= n; r++) {
                fArray[n] += fArray[r - 1] * fArray[n - r];
            }
        }
        
        return fArray[N];
    }
}