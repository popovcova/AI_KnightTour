import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class KnightTour {

	int[][] chessBoard;

	List<Point>[][] possibleMoves;

	static int numberOfSteps = 0;

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

	private KnightTour findSolution(int a, int b, int sizeOfEdge) {
		
		int x = a;
		int y = b;
		int orderNumber = 0;

		if (findKnightTour(x, y, orderNumber, sizeOfEdge)) {
			printChessBoard(sizeOfEdge);
		} 
		else {
			System.out.println("Solution does not exit or it takes lots of steps to find solution.\n");
		}
		
		return this;
	}

	private boolean findKnightTour(int x, int y, int orderNumber, int sizeOfEdge) {
		
		boolean lotsOfSteps = false;

		if (chessBoard[x][y] != UNVISITED) {
			return false;
		}

		chessBoard[x][y] = orderNumber;

		if (orderNumber == sizeOfEdge * sizeOfEdge - 1) {
			return true;
		}

		for (Point d : possibleMoves[x][y]) {
			numberOfSteps++;

			if (numberOfSteps >= 2000000) {
				lotsOfSteps = true;
				return false;
			}
			if (findKnightTour(d.x, d.y, orderNumber + 1, sizeOfEdge)) {
				return true;
			}
		}

		chessBoard[x][y] = UNVISITED;

		return false;
	}

	public static void main(String[] args) {

		numberOfSteps = 0;
		new KnightTour(5).findSolution(0, 4, 5);
		numberOfSteps = 0;
		new KnightTour(5).findSolution(4, 3, 5);
		numberOfSteps = 0;
		new KnightTour(5).findSolution(1, 1, 5);
		numberOfSteps = 0;
		new KnightTour(5).findSolution(2, 2, 5);
		numberOfSteps = 0;
		new KnightTour(5).findSolution(1, 4, 5);
		numberOfSteps = 0;
		new KnightTour(6).findSolution(0, 5, 6);
		numberOfSteps = 0;
		new KnightTour(6).findSolution(1, 4, 6);
		numberOfSteps = 0;
		new KnightTour(6).findSolution(4, 4, 6);
		numberOfSteps = 0;
		new KnightTour(6).findSolution(2, 3, 6);
		numberOfSteps = 0;
		new KnightTour(6).findSolution(4, 2, 6);
	}
}
