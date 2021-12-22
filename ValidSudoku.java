class Solution { 
    public boolean isValidSudoku(char[][] board) { 
        // Validate rows. 
        for (int i = 0; i < 9; i++) { 
            HashSet<Character> row = new HashSet<Character>(); 
             
            for (int j = 0; j < 9; j++) { 
                if (board[i][j] != '.') { 
                    if (row.contains(board[i][j])) { 
                        System.out.println("Duplicate in row " + i); 
                        return false; 
                    } 
                    else { 
                        row.add(board[i][j]); 
                    } 
                } 
            } 
        } 
         
        // Validate columns. 
        for (int i = 0; i < 9; i++) { 
            HashSet<Character> column = new HashSet<Character>(); 
             
            for (int j = 0; j < 9; j++) { 
                if (board[j][i] != '.') { 
                    if (column.contains(board[j][i])) { 
                        return false; 
                    } 
                    else { 
                        column.add(board[j][i]); 
                    } 
                } 
            } 
        } 
         
        // Validate 9 3x3 sub-boxes. 
        int[][] indexRanges = new int[][] {{0, 2}, {3, 5}, {6, 8}}; 
        for (int[] rRange : indexRanges) { 
            for (int[] cRange : indexRanges) { 
                HashSet<Character> subbox = new HashSet<Character>(); 
                 
                for (int r = rRange[0]; r <= rRange[1]; r++) { 
                    for (int c = cRange[0]; c <= cRange[1]; c++) { 
                        if (board[r][c] != '.') { 
                            if (subbox.contains(board[r][c])) { 
                                return false; 
                            } 
                            else { 
                                subbox.add(board[r][c]); 
                            } 
                        } 
                    } 
                } 
            } 
        } 
        return true; 
    } 
} 
