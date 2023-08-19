import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ChessSquare extends JButton {
    private ChessPiece piece;
    private Color originalColor;
    private int row;
    private int col;

    public interface SquareClickListener {
        void onSquareClicked(int row, int col);
    }

    private final SquareClickListener clickHandler;

    public ChessSquare(int row, int col, Color color, SquareClickListener clickHandler) {
        super();

        this.originalColor = color;
        this.row = row;
        this.col = col;
        this.clickHandler = clickHandler;

         // Add mouse click listener
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickHandler != null) {
                    clickHandler.onSquareClicked(row, col);
                }
            }
        });
    }

    public void highlight() {
        // Change the background color to indicate highlight (you can choose a different color)
        this.setBackground(Color.YELLOW);
    }

    public void unhighlight() {
        // Reset the background color to its original color
        this.setBackground(originalColor);
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
        updateIcon();
    }

    public void updateIconSize() {
        if (piece != null) {
            setIcon(piece.getScaledIcon(getWidth(), getHeight()));
        }
    }

    private void updateIcon() {
        if (piece == null) {
            this.setIcon(null);
        } else {
            String iconName = piece.getType().toString().toLowerCase() + "_" + piece.getColor().toString().toLowerCase() + ".png";
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("icons/" + iconName));
    
            // Use the current dimensions of the ChessSquare for the scaled icon, only if they're greater than zero
            int scaledWidth = this.getWidth() > 0 ? this.getWidth() : 25; // default to 50 if width is 0
            int scaledHeight = this.getHeight() > 0 ? this.getHeight() : 25; // default to 50 if height is 0
    
            Image image = originalIcon.getImage();
            Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
    
            this.setIcon(scaledIcon);
        }
    }
}

