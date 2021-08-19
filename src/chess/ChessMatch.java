package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Rook;
import chess.pieces.king;

public class ChessMatch {
	
	private Board board; 
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toposition(); 
		Position target = targetPosition.toposition();
		validateSourcePosition(source); 
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placepiece(p, target);
		return capturedPiece;
		
	}
	
	
	private void validateSourcePosition(Position position) {
		if (! board.theresIsApice(position)) {
			throw new ChessException("There is no piece on source position");
		}
	}
	
	
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placepiece(piece, new ChessPosition(column, row).toposition());
	}
	
	private void initialSetup() {
		placeNewPiece('B', 6, new Rook(board, Color.WHITE));
		placeNewPiece('C', 1, new Rook(board, Color.WHITE));
        placeNewPiece('C', 2, new Rook(board, Color.WHITE));

        placeNewPiece('C', 7, new Rook(board, Color.BLACK));
        placeNewPiece('C', 8, new Rook(board, Color.BLACK));
        placeNewPiece('D', 7, new Rook(board, Color.BLACK));
	}

}
