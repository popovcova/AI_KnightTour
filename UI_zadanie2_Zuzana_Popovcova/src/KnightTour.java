import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class KnightTour {

	int[][] chessBoard;

	List<Point>[][] possibleMoves;

	static int ns = 0;

	final static int UNVISITED = Integer.MIN_VALUE;

	final static Point[] DIRECTIONS = new Point[] { new Point(-1, -2), new Point(1, -2), new Point(2, -1),
			new Point(2, 1), new Point(1, 2), new Point(-1, 2), new Point(-2, 1), new Point(-2, -1) };

	public KnightTour(int sizeOfEdge) {

		chessBoard = new int[sizeOfEdge][sizeOfEdge];
		possibleMoves = new List[sizeOfEdge][sizeOfEdge];
		
		for (int y = 0; y < sizeOfEdge; y++) {
			for (int x = 0; x < sizeOfEdge; x++) {
				chessBoard[x][y] = UNVISITED;
				possibleMoves[x][y] = new ArrayList<>();
				for (Point step : DIRECTIONS) {
					int nx = x + step.x;
					int ny = y + step.y;

					if ((nx >= 0 && nx < sizeOfEdge) && (ny >= 0 && ny < sizeOfEdge)) {
						possibleMoves[x][y].add(new Point(nx, ny));
					}
				}
			}
		}
	}
	
	private void printChessBoard(int sizeOfEdge) {
		
		for (int y = 0; y < sizeOfEdge; y++) {
			for (int x = 0; x < sizeOfEdge; x++) {
				System.out.printf("%2d ", chessBoard[x][y]);
			}
			System.out.println();
		}
		
		System.out.println();

	}

	KnightTour findSolution(int a, int b, int sizeOfEdge, int numberOfSteps) {
		
		int x = a;
		int y = b;
		int orderNumber = 0;
		ns = numberOfSteps;

		if (findKnightTour(x, y, orderNumber, sizeOfEdge, ns)) {
			printChessBoard(sizeOfEdge);
		} 
		else {
			System.out.println("Solution does not exit or it takes lots of steps to find solution.\n");
		}
		
		return this;
	}

	private boolean findKnightTour(int x, int y, int orderNumber, int sizeOfEdge, int numberOfSteps) {
		
		if (chessBoard[x][y] != UNVISITED) {
			return false;
		}

		chessBoard[x][y] = orderNumber;

		if (orderNumber == sizeOfEdge * sizeOfEdge - 1) {
			return true;
		}

		for (Point d : possibleMoves[x][y]) {
			ns++;

			if (ns >= 2000000) {
				return false;
			}
			if (findKnightTour(d.x, d.y, orderNumber + 1, sizeOfEdge, ns)) {
				return true;
			}
		}

		chessBoard[x][y] = UNVISITED;

		return false;
	}

}
