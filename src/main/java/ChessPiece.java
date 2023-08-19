import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ChessPiece {
    private PieceType type;
    private PieceColor color;
    private ImageIcon icon;
    private boolean hasMoved = false;

    public ChessPiece(PieceType type, PieceColor color) {
        this.type = type;
        this.color = color;
    
        String iconName = type.name().toLowerCase() + "_" + color.name().toLowerCase() + ".png";
        this.icon = new ImageIcon(ChessPiece.class.getResource("icons/" + iconName));
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    public void markAsMoved() {
        this.hasMoved = true;
    }    

    public ImageIcon getScaledIcon(int width, int height) {
        if (icon == null) return null;
        
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public List<Point> getPossibleMoves(int startX, int startY, ChessSquare[][] board, PieceColor playerColor) {
        List<Point> moves = new ArrayList<>();
        switch (type) {
            case PAWN:
                if (color == playerColor) {
                    // Player's pawns always move upwards

                    // Forward moves
                    if (startY > 0 && board[startY - 1][startX].getPiece() == null) {
                        moves.add(new Point(startX, startY - 1));

                        if (!hasMoved && startY > 1 && board[startY - 2][startX].getPiece() == null) {
                            moves.add(new Point(startX, startY - 2));
                        }
                    }

                    // Capture moves to the left and right
                    if (startX > 0 && startY > 0 && board[startY - 1][startX - 1].getPiece() != null && 
                        board[startY - 1][startX - 1].getPiece().getColor() != color) {
                        moves.add(new Point(startX - 1, startY - 1));
                    }
                    if (startX < 7 && startY > 0 && board[startY - 1][startX + 1].getPiece() != null && 
                        board[startY - 1][startX + 1].getPiece().getColor() != color) {
                        moves.add(new Point(startX + 1, startY - 1));
                    }

                } else {
                    // Computer's pawns always move downwards
                    // Forward moves
                    if (startY < 7 && board[startY + 1][startX].getPiece() == null) {
                        moves.add(new Point(startX, startY + 1));

                        if (!hasMoved && startY < 6 && board[startY + 2][startX].getPiece() == null) {
                            moves.add(new Point(startX, startY + 2));
                        }
                    }

                    // Capture moves to the left and right
                    if (startX > 0 && startY < 7 && board[startY + 1][startX - 1].getPiece() != null && 
                        board[startY + 1][startX - 1].getPiece().getColor() != color) {
                        moves.add(new Point(startX - 1, startY + 1));
                    }
                    if (startX < 7 && startY < 7 && board[startY + 1][startX + 1].getPiece() != null && 
                        board[startY + 1][startX + 1].getPiece().getColor() != color) {
                        moves.add(new Point(startX + 1, startY + 1));
                    }
                }
                break;


            case ROOK:
                // Horizontal movement to the right
                for (int x = startX + 1; x < 8; x++) {
                    if (board[startY][x].getPiece() == null) {
                        moves.add(new Point(x, startY));
                    } else {
                        if (board[startY][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, startY));
                        }
                        break;
                    }
                }
                
                // Horizontal movement to the left
                for (int x = startX - 1; x >= 0; x--) {
                    if (board[startY][x].getPiece() == null) {
                        moves.add(new Point(x, startY));
                    } else {
                        if (board[startY][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, startY));
                        }
                        break;
                    }
                }
                
                // Vertical movement upwards
                for (int y = startY - 1; y >= 0; y--) {
                    if (board[y][startX].getPiece() == null) {
                        moves.add(new Point(startX, y));
                    } else {
                        if (board[y][startX].getPiece().getColor() != color) {
                            moves.add(new Point(startX, y));
                        }
                        break;
                    }
                }
                
                // Vertical movement downwards
                for (int y = startY + 1; y < 8; y++) {
                    if (board[y][startX].getPiece() == null) {
                        moves.add(new Point(startX, y));
                    } else {
                        if (board[y][startX].getPiece().getColor() != color) {
                            moves.add(new Point(startX, y));
                        }
                        break;
                    }
                }
                break;
            case QUEEN:
                // Horizontal movement to the right (Rook's movement)
                for (int x = startX + 1; x < 8; x++) {
                    if (board[startY][x].getPiece() == null) {
                        moves.add(new Point(x, startY));
                    } else {
                        if (board[startY][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, startY));
                        }
                        break;
                    }
                }
                
                // Horizontal movement to the left
                for (int x = startX - 1; x >= 0; x--) {
                    if (board[startY][x].getPiece() == null) {
                        moves.add(new Point(x, startY));
                    } else {
                        if (board[startY][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, startY));
                        }
                        break;
                    }
                }
                
                // Vertical movement upwards
                for (int y = startY - 1; y >= 0; y--) {
                    if (board[y][startX].getPiece() == null) {
                        moves.add(new Point(startX, y));
                    } else {
                        if (board[y][startX].getPiece().getColor() != color) {
                            moves.add(new Point(startX, y));
                        }
                        break;
                    }
                }
                
                // Vertical movement downwards
                for (int y = startY + 1; y < 8; y++) {
                    if (board[y][startX].getPiece() == null) {
                        moves.add(new Point(startX, y));
                    } else {
                        if (board[y][startX].getPiece().getColor() != color) {
                            moves.add(new Point(startX, y));
                        }
                        break;
                    }
                }
                
                // Diagonal movement top-left (Bishop's movement)
                for (int x = startX - 1, y = startY - 1; x >= 0 && y >= 0; x--, y--) {
                    if (board[y][x].getPiece() == null) {
                        moves.add(new Point(x, y));
                    } else {
                        if (board[y][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                        break;
                    }
                }
            
                // Diagonal movement top-right
                for (int x = startX + 1, y = startY - 1; x < 8 && y >= 0; x++, y--) {
                    if (board[y][x].getPiece() == null) {
                        moves.add(new Point(x, y));
                    } else {
                        if (board[y][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                        break;
                    }
                }
            
                // Diagonal movement bottom-left
                for (int x = startX - 1, y = startY + 1; x >= 0 && y < 8; x--, y++) {
                    if (board[y][x].getPiece() == null) {
                        moves.add(new Point(x, y));
                    } else {
                        if (board[y][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                        break;
                    }
                }
            
                // Diagonal movement bottom-right
                for (int x = startX + 1, y = startY + 1; x < 8 && y < 8; x++, y++) {
                    if (board[y][x].getPiece() == null) {
                        moves.add(new Point(x, y));
                    } else {
                        if (board[y][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                        break;
                    }
                }
                break;
            case KNIGHT:
                int[][] knightMoves = {
                    {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
                    {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
                };
            
                for (int[] move : knightMoves) {
                    int x = startX + move[0];
                    int y = startY + move[1];
            
                    if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                        ChessPiece pieceAtDestination = board[y][x].getPiece();
                        if (pieceAtDestination == null || pieceAtDestination.getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                    }
                }
                break;
            case BISHOP:
                // Diagonal movement top-left
                for (int x = startX - 1, y = startY - 1; x >= 0 && y >= 0; x--, y--) {
                    if (board[y][x].getPiece() == null) {
                        moves.add(new Point(x, y));
                    } else {
                        if (board[y][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                        break;
                    }
                }
            
                // Diagonal movement top-right
                for (int x = startX + 1, y = startY - 1; x < 8 && y >= 0; x++, y--) {
                    if (board[y][x].getPiece() == null) {
                        moves.add(new Point(x, y));
                    } else {
                        if (board[y][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                        break;
                    }
                }
            
                // Diagonal movement bottom-left
                for (int x = startX - 1, y = startY + 1; x >= 0 && y < 8; x--, y++) {
                    if (board[y][x].getPiece() == null) {
                        moves.add(new Point(x, y));
                    } else {
                        if (board[y][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                        break;
                    }
                }
            
                // Diagonal movement bottom-right
                for (int x = startX + 1, y = startY + 1; x < 8 && y < 8; x++, y++) {
                    if (board[y][x].getPiece() == null) {
                        moves.add(new Point(x, y));
                    } else {
                        if (board[y][x].getPiece().getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                        break;
                    }
                }
                break;
            case KING:
                int[][] kingMoves = {
                    {1, 0}, {-1, 0}, {0, 1}, {0, -1},  // Horizontal and vertical moves
                    {1, 1}, {-1, -1}, {1, -1}, {-1, 1} // Diagonal moves
                };
            
                for (int[] move : kingMoves) {
                    int x = startX + move[0];
                    int y = startY + move[1];
            
                    if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                        ChessPiece pieceAtDestination = board[y][x].getPiece();
                        if (pieceAtDestination == null || pieceAtDestination.getColor() != color) {
                            moves.add(new Point(x, y));
                        }
                    }
                }
                break;            

        }
        
        return moves;
    }
}
