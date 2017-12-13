package arrays2D;

class Cord {
	int x;
	int y;
}

public class WordSearch {
	public static boolean wordSearch(char[][] board, String word) {
		if (word.length() == 0) return true;
		int m = board.length;
		if (m == 0) return false;
		int n = board[0].length;
		if (n == 0) return false;
		boolean[][] visited = new boolean[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0) && wordSearchRe(board, word, ""+board[i][j], i, j, visited))
					return true;
			}
		}
		return false;
	}
	
	public static boolean wordSearchRe(char[][] board, String word, String path, 
									int i, int j, boolean visited[][]) {
		if (path.compareTo(word) == 0) return true; //Don't use path == word here!
//		System.out.println(path);
		if (path.length() >= word.length()) return false;
		visited[i][j] = true;
		boolean ret = false;
		if (i > 0 && !visited[i-1][j]) ret |= wordSearchRe(board, word, path+board[i-1][j], i-1, j, visited);
		if (i < board.length-1 && !visited[i+1][j]) ret |= wordSearchRe(board, word, path+board[i+1][j], i+1, j, visited);
		if (j > 0 && !visited[i][j-1]) ret |= wordSearchRe(board, word, path+board[i][j-1], i, j-1, visited);
		if (j < board[0].length - 1 && !visited[i][j+1]) ret |= wordSearchRe(board, word, path+board[i][j+1], i, j+1, visited);
		if (ret) return true; 
		visited[i][j] = false; //This is where we need to trace back.
		return false;
	}
	
	public static void main(String args[]) {
		char[][] board = {{'A','B','C','E'},
						  {'S','F','C','S'},
						  {'A','D','E','E'}};
		String word = "ABCCED";
		System.out.println(WordSearch.wordSearch(board, word));
	}
}
