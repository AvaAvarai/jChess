import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

class ChessPieceTransferHandler extends TransferHandler {
    private final PieceColor playerColor;

    public ChessPieceTransferHandler(PieceColor playerColor) {
        this.playerColor = playerColor;
    }

    // This method indicates which transfer actions are supported (only MOVE in this case)
    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    // This method creates the Transferable object
    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof ChessSquare) {
            ChessPiece piece = ((ChessSquare) c).getPiece();
            if (piece != null && piece.getColor() == playerColor) {
                return new StringSelection(piece.getType().toString());
            }
        }
        return null;
    }

    // This method provides visual feedback during the drag
    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (action == MOVE && source instanceof ChessSquare) {
            ((ChessSquare) source).setPiece(null);
        }
    }

    // This method checks if the drop location is valid
    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        // For simplicity, we'll allow all drops for now
        // You can expand this to check for valid chess moves later
        return true;
    }

    // This method handles the piece drop
    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        Component component = support.getComponent();
        System.out.println("Dropped on: " + component);
        // You can add more logic here to handle capturing pieces, etc.
        return true;
    }
}
