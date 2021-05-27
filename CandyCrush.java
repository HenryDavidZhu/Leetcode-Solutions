class Solution {
    int boardHeight = 0;
    int boardWidth = 0;
    
    int currCandyCrushCount = 0;
    
    // Returns board storing whether each cell has a candy to be crushed or not
    private boolean[][] findCandiesToCrush(int[][] board) {
        boolean[][] candiesToCrush = new boolean[boardHeight][boardWidth];
        currCandyCrushCount = 0;
        
        // Iterate through every possible base cell to search from, row by row, from left to right
        for (int r = 0; r < boardHeight; r++) {
            for (int c = 0; c < boardWidth; c++) {
                int candyValue = board[r][c];                
                
                // Only crush candies that are not empty (not 0)
                if (candyValue != 0) {
	                int leftPointer = c - 1;
	                int rightPointer = c + 1;
	                int topPointer = r - 1;
	                int bottomPointer = r + 1;
	                
	                // A list of columns of the cells to be crushed, horizontal to our base cell
	                Set<Integer> crushHorizontalCellsCols = new HashSet<Integer>();
	                crushHorizontalCellsCols.add(c);
	                
	                // A list of rows of the cells to be crushed, vertical to our base cell
	                Set<Integer> crushVerticalCellsRows = new HashSet<Integer>();
	                crushVerticalCellsRows.add(r);
	                
	                // Check left for candies to crush
	                while (leftPointer >= 0) {
	                    // Found a candy to crush
	                    if (board[r][leftPointer] == candyValue) {
	                    	// Add the col of the current cell to our list of horizontal cells to be crushed
	                    	crushHorizontalCellsCols.add(leftPointer);
	                    	
	                        // Move the pointer to the left cell
	                        leftPointer--;
	                    }
	                    // Didn't find a candy to crush, stop search
	                    else {
	                        break;
	                    }
	                }
	                
	                // Check right for candies to crush
	                while (rightPointer <= boardWidth - 1) {
	                    // Found a candy to crush
	                    if (board[r][rightPointer] == candyValue) {
	                    	// Add the col of the current cell to our list of horizontal cells to be crushed
	                    	crushHorizontalCellsCols.add(rightPointer);
	                    	
	                        // Move the pointer to the right cell
	                        rightPointer++;
	                    }
	                    // Didn't find a candy to crush, stop search
	                    else {
	                        break;
	                    } 
	                }
	                
	                // Check up for candies to crush
	                while (topPointer >= 0) {
	                    // Found a candy to crush
	                    if (board[topPointer][c] == candyValue) { 
	                    	// Add the row of the current cell to our list of horizontal cells to be crushed
	                    	crushVerticalCellsRows.add(topPointer);
	                    	
	                        // Move to the above cell
	                        topPointer--;
	                    }
	                    // Didn't find a candy to crush, stop search
	                    else {
	                        break;
	                    } 
	                }
	                
	                // Check down for candies to crush
	                while (bottomPointer <= boardHeight - 1) {
	                    // Found a candy to crush
	                    if (board[bottomPointer][c] == candyValue) {
	                    	// Add the row of the current cell to our list of horizontal cells to be crushed
	                    	crushVerticalCellsRows.add(bottomPointer);
	
	                        // Move the pointer down a cell
	                        bottomPointer++;
	                    }
	                    // Didn't find a candy to crush, stop search
	                    else {
	                        break;
	                    }                  
	                }
	                
	                // Crush horizontal cells if there are 3+ of them
	                if (crushHorizontalCellsCols.size() >= 3) {
	                	for (int col : crushHorizontalCellsCols) {
	                		// If we haven't already processed the current cell
	                		if (!candiesToCrush[r][col]) {
		                		// Mark the cell as to be crushed
		                		candiesToCrush[r][col] = true;

		                		currCandyCrushCount++;
	                		}
	                	}
	                }
	                
	                // Crush vertical cells if there are 3+ of them
	                if (crushVerticalCellsRows.size() >= 3) {
	                	for (int row : crushVerticalCellsRows) {
	                		// If we haven't already processed the current cell
	                		if (!candiesToCrush[row][c]) {
		                		// Mark the cell as to be crushed
		                		candiesToCrush[row][c] = true;
	
		                		currCandyCrushCount++;
	                		}
	                	}
	                }
                }
            }
        }
        
        return candiesToCrush;
    }
    
    // Updates the board given the board containing the candies to crush
    private int[][] crushCandies(int[][] board, boolean[][] candiesToCrush) {
        int[][] updatedBoard = new int[boardHeight][boardWidth];
        
        // Iterate through every column of the board containing the candies to crush
        for (int c = 0; c < boardWidth; c++) {
            // Retrieve the following:
            // 1. currColumn: board's corresponding column
            // 2. currColumnCrushState: Current column showing candies to be crushed
            int[] currColumn = new int[boardHeight];
            boolean[] currColumnCrushState = new boolean[boardHeight];
            
            for (int r = 0; r < boardHeight; r++) {
                currColumn[r] = board[r][c];
                currColumnCrushState[r] = candiesToCrush[r][c];
            }
            
            // Mapping of the top level of a block to its corresponding height
            Map<Integer, Integer> blocksToCrush = new TreeMap<Integer, Integer>();
            
            int crushBlockTopLevel = -1;
            boolean extendingBlock = false;
            
            // Determine all the blocks of candies in the current column to be crushed
            for (int currLevel = 0; currLevel < currColumnCrushState.length; currLevel++) {
                // Found the start of a block of candies to be crushed
                if (currColumnCrushState[currLevel]) {
                	if (extendingBlock == false) {
	                    crushBlockTopLevel = currLevel;
	                    extendingBlock = true;
                	}
                } 
                // End of a block of candies to be crushed, add that block to our
                // list of candy crush blocks
                else {
                	if (extendingBlock == true) {
	                    int blockHeight = currLevel - crushBlockTopLevel;
	                    blocksToCrush.put(crushBlockTopLevel, blockHeight);
                	}
                	
                	if (currLevel <= currColumnCrushState.length - 1) {
                		extendingBlock = false;
                	}
                }
            }
            
            // Handle the edge case that the block ends at the bottom block
            if (extendingBlock == true) {
            	int currLevel = (currColumnCrushState.length - 1);
                int blockHeight = (currLevel - crushBlockTopLevel) + 1;

                blocksToCrush.put(crushBlockTopLevel, blockHeight);
            }
            
            // Move all the candies in the column to their correct location:
            // Note by default, all elements in an initialized integer array are 0
            int[] updatedColumn = new int[boardHeight];
            
            // Go through every element in the original board's column
	        for (int currLvl = 0; currLvl < boardHeight; currLvl++) {
	        	// Only process a block if it is not being crushed
	        	if (!currColumnCrushState[currLvl]) {
		            // Determine collective height of all the blocks that the current
		            // candy is above.
		            int belowBlocksHeight = 0;
		                    
		            for (Map.Entry<Integer, Integer> block : blocksToCrush.entrySet()) {
		                int blockTopLevel = block.getKey();
		                int blockHeight = block.getValue();
		                        
		                // If the current cell is above the block
		                if (currLvl < blockTopLevel) {
		                    belowBlocksHeight += blockHeight;
		                }
		            }
		                    
		            // Update the candy's position
		            updatedColumn[currLvl + belowBlocksHeight] = currColumn[currLvl];
		            updatedBoard[currLvl + belowBlocksHeight][c] = currColumn[currLvl];
	        	}
	        }
        }
        

        return updatedBoard;
    }
    
    public int[][] candyCrush(int[][] board) {
        int[][] updatedBoard = board;
        
        boardWidth = board[0].length;
        boardHeight = board.length;
        
        int iteration = 1;
        
        while (true) {
            // See what candies need to be crushed
            boolean[][] candiesToCrush = findCandiesToCrush(updatedBoard);
            
            if (currCandyCrushCount == 0) {
                return updatedBoard;
            }
            
            // Crush the candies
            updatedBoard = crushCandies(updatedBoard, candiesToCrush);
            
            iteration++;
        }
    }
}