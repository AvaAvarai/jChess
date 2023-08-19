import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChessApp {

    private JFrame frame;
    private JPanel boardPanel;
    private ChessSquare[][] squares = new ChessSquare[8][8];
    private ChessPiece selectedPiece = null;
    private List<ChessSquare> highlightedSquares = new ArrayList<>();
    private PieceColor playerColor;

    public ChessApp() {
        frame = new JFrame("Java Chess");
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/icons/king_black.png"));
        frame.setIconImage(appIcon.getImage());
        playerColor = StartMenu.showMenu();
        
        boardPanel = new JPanel(new GridLayout(8, 8));
    
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Color squareColor = (row + col) % 2 == 0 ? Color.WHITE : Color.BLACK;
                ChessSquare square = new ChessSquare(row, col, squareColor, this::handleSquareClicked);
                square.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                square.setTransferHandler(new ChessPieceTransferHandler(playerColor));
                squares[row][col] = square;
                boardPanel.add(square);
            }
        }

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        squares[row][col].updateIconSize();
                    }
                }
            }
        });

        if (playerColor == PieceColor.WHITE) {
            // Place white pieces at the bottom
        
            // Pawns
            for (int col = 0; col < 8; col++) {
                squares[6][col].setPiece(new ChessPiece(PieceType.PAWN, PieceColor.WHITE));
            }
        
            // Rooks
            squares[7][0].setPiece(new ChessPiece(PieceType.ROOK, PieceColor.WHITE));
            squares[7][7].setPiece(new ChessPiece(PieceType.ROOK, PieceColor.WHITE));
        
            // Knights
            squares[7][1].setPiece(new ChessPiece(PieceType.KNIGHT, PieceColor.WHITE));
            squares[7][6].setPiece(new ChessPiece(PieceType.KNIGHT, PieceColor.WHITE));
        
            // Bishops
            squares[7][2].setPiece(new ChessPiece(PieceType.BISHOP, PieceColor.WHITE));
            squares[7][5].setPiece(new ChessPiece(PieceType.BISHOP, PieceColor.WHITE));
        
            // King and Queen
            squares[7][3].setPiece(new ChessPiece(PieceType.QUEEN, PieceColor.WHITE));
            squares[7][4].setPiece(new ChessPiece(PieceType.KING, PieceColor.WHITE));
        
            // Pawns
            for (int col = 0; col < 8; col++) {
                squares[1][col].setPiece(new ChessPiece(PieceType.PAWN, PieceColor.BLACK));
            }

            // Rooks
            squares[0][0].setPiece(new ChessPiece(PieceType.ROOK, PieceColor.BLACK));
            squares[0][7].setPiece(new ChessPiece(PieceType.ROOK, PieceColor.BLACK));

            // Knights
            squares[0][1].setPiece(new ChessPiece(PieceType.KNIGHT, PieceColor.BLACK));
            squares[0][6].setPiece(new ChessPiece(PieceType.KNIGHT, PieceColor.BLACK));

            // Bishops
            squares[0][2].setPiece(new ChessPiece(PieceType.BISHOP, PieceColor.BLACK));
            squares[0][5].setPiece(new ChessPiece(PieceType.BISHOP, PieceColor.BLACK));

            // King and Queen
            squares[0][3].setPiece(new ChessPiece(PieceType.QUEEN, PieceColor.BLACK));
            squares[0][4].setPiece(new ChessPiece(PieceType.KING, PieceColor.BLACK));

        } else {
            // Place black pieces at the bottom
        
            // Pawns
            for (int col = 0; col < 8; col++) {
                squares[6][col].setPiece(new ChessPiece(PieceType.PAWN, PieceColor.BLACK));
            }
        
            // Rooks
            squares[7][0].setPiece(new ChessPiece(PieceType.ROOK, PieceColor.BLACK));
            squares[7][7].setPiece(new ChessPiece(PieceType.ROOK, PieceColor.BLACK));
        
            // Knights
            squares[7][1].setPiece(new ChessPiece(PieceType.KNIGHT, PieceColor.BLACK));
            squares[7][6].setPiece(new ChessPiece(PieceType.KNIGHT, PieceColor.BLACK));
        
            // Bishops
            squares[7][2].setPiece(new ChessPiece(PieceType.BISHOP, PieceColor.BLACK));
            squares[7][5].setPiece(new ChessPiece(PieceType.BISHOP, PieceColor.BLACK));
        
            // King and Queen
            squares[7][3].setPiece(new ChessPiece(PieceType.QUEEN, PieceColor.BLACK));
            squares[7][4].setPiece(new ChessPiece(PieceType.KING, PieceColor.BLACK));
        
            // Pawns
            for (int col = 0; col < 8; col++) {
                squares[1][col].setPiece(new ChessPiece(PieceType.PAWN, PieceColor.WHITE));
            }

            // Rooks
            squares[0][0].setPiece(new ChessPiece(PieceType.ROOK, PieceColor.WHITE));
            squares[0][7].setPiece(new ChessPiece(PieceType.ROOK, PieceColor.WHITE));

            // Knights
            squares[0][1].setPiece(new ChessPiece(PieceType.KNIGHT, PieceColor.WHITE));
            squares[0][6].setPiece(new ChessPiece(PieceType.KNIGHT, PieceColor.WHITE));

            // Bishops
            squares[0][2].setPiece(new ChessPiece(PieceType.BISHOP, PieceColor.WHITE));
            squares[0][5].setPiece(new ChessPiece(PieceType.BISHOP, PieceColor.WHITE));

            // King and Queen
            squares[0][3].setPiece(new ChessPiece(PieceType.QUEEN, PieceColor.WHITE));
            squares[0][4].setPiece(new ChessPiece(PieceType.KING, PieceColor.WHITE));

        }

        if (playerColor == PieceColor.BLACK) {
            executeRandomComputerMove(PieceColor.WHITE);
        }

        frame.add(boardPanel);
        frame.pack();  // This will set the frame size based on its contents
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void handleSquareClicked(int row, int col) {
        ChessSquare clickedSquare = squares[row][col];
    
        if (selectedPiece == null) {
            // First click: Select the piece
            if (clickedSquare.getPiece() != null && clickedSquare.getPiece().getColor() == playerColor) {
                selectedPiece = clickedSquare.getPiece();
                
                // Highlight possible moves
                List<Point> possibleMoves = selectedPiece.getPossibleMoves(col, row, squares, playerColor);
                for (Point move : possibleMoves) {
                    squares[move.y][move.x].highlight();
                    highlightedSquares.add(squares[move.y][move.x]);
                }
            }
        } else {
            // Second click: Try to move the piece
            if (highlightedSquares.contains(clickedSquare)) {
                // Move the piece
                movePieceToSquare(row, col);
                unhighlightAllSquares();
                selectedPiece = null;  // Reset for the next selection
    
                if (playerColor == PieceColor.WHITE) {
                    executeRandomComputerMove(PieceColor.BLACK);
                } else if (playerColor == PieceColor.BLACK) {
                    executeRandomComputerMove(PieceColor.WHITE);
                }
            } else {
                unhighlightAllSquares();
                selectedPiece = null;  // Reset for the next selection

                checkGameOverConditions();
            }
        }
    }    

    private void movePieceToSquare(int row, int col) {
        ChessSquare originalSquare = getSquareOfPiece(selectedPiece);
        originalSquare.setPiece(null);  // Remove the piece from its original position
        squares[row][col].setPiece(selectedPiece);
        selectedPiece.markAsMoved();

        // Check for pawn promotion
        if (selectedPiece.getType() == PieceType.PAWN) {
            if (selectedPiece.getColor() == playerColor && row == 0) {
                // Player's pawn promoted to a queen
                squares[row][col].setPiece(new ChessPiece(PieceType.QUEEN, playerColor));
            } else if (selectedPiece.getColor() != playerColor && row == 7) {
                // Computer's pawn promoted to a queen
                PieceColor computerColor = (playerColor == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
                squares[row][col].setPiece(new ChessPiece(PieceType.QUEEN, computerColor));
            }
        }
    }

    private boolean hasLostKing(PieceColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = squares[row][col].getPiece();
                if (piece != null && piece.getType() == PieceType.KING && piece.getColor() == color) {
                    return false;  // King is found
                }
            }
        }
        return true;  // King is not found
    }

    private boolean hasLostAllPieces(PieceColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = squares[row][col].getPiece();
                if (piece != null && piece.getColor() == color) {
                    return false;  // At least one piece is found
                }
            }
        }
        return true;  // No pieces found
    }    

    private ChessSquare getSquareOfPiece(ChessPiece piece) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (squares[row][col].getPiece() == piece) {
                    return squares[row][col];
                }
            }
        }
        return null;  // Should not happen
    }

    private void unhighlightAllSquares() {
        for (ChessSquare square : highlightedSquares) {
            square.unhighlight();
        }
        highlightedSquares.clear();
    }

    private void checkGameOverConditions() {
        PieceColor opponentColor = (playerColor == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
        if (hasLostKing(opponentColor) || hasLostAllPieces(opponentColor)) {
            showEndGamePopup("Congratulations, You Win!", "Would you like to play again?");
        } else if (hasLostKing(playerColor) || hasLostAllPieces(playerColor)) {
            showEndGamePopup("Sorry, You Lose.", "Would you like to play again?");
        }
    }
    
    private List<Move> getAllLegalMovesForComputer(PieceColor computerColor) {
        List<Move> legalMoves = new ArrayList<>();
    
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = squares[row][col].getPiece();
                if (piece != null && piece.getColor() == computerColor) {
                    List<Point> possibleDestinations = piece.getPossibleMoves(col, row, squares, playerColor);
                    for (Point destination : possibleDestinations) {
                        legalMoves.add(new Move(new Point(col, row), destination));
                    }
                }
            }
        }
    
        return legalMoves;
    }
    
    private static class Move {
        Point start;
        Point end;
    
        Move(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }

    private void showEndGamePopup(String title, String message) {
        // Load and scale the black king icon
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/icons/king_black.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
    
        int option = JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, scaledIcon);
        if (option == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);  // Close the application
        }
    }

    private void executeRandomComputerMove(PieceColor computerColor) {
        List<Move> legalMoves = getAllLegalMovesForComputer(computerColor);

        if (!legalMoves.isEmpty()) {
            Move randomMove = legalMoves.get(new Random().nextInt(legalMoves.size()));
            ChessPiece pieceToMove = squares[randomMove.start.y][randomMove.start.x].getPiece();
            squares[randomMove.end.y][randomMove.end.x].setPiece(pieceToMove);
            squares[randomMove.start.y][randomMove.start.x].setPiece(null);
            checkGameOverConditions();
        } else {
            declareStalemate();
        }
    }

    private void declareStalemate() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/king_black.png"));
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(15, 25, Image.SCALE_SMOOTH));

        Object[] options = {"Restart", "Exit"};
        int choice = JOptionPane.showOptionDialog(frame, 
                                                "The game is a stalemate! What would you like to do next?", 
                                                "Stalemate", 
                                                JOptionPane.YES_NO_OPTION, 
                                                JOptionPane.INFORMATION_MESSAGE, 
                                                scaledIcon, 
                                                options, 
                                                options[0]);
        
        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
        
        // Here, you can also add options to restart the game or exit.
    }

    private void restartGame() {
        frame.dispose(); // Close the current game window
        new ChessApp();  // Start a new game instance
    }

    public static void main(String[] args) {
        new ChessApp();
    }
}
