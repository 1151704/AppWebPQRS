package Util.PositionPdfPCell;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class ImagenAbsolute implements PdfPCellEvent {

    protected Image img;

    public ImagenAbsolute(Image img) {
        this.img = img;
    }

    @Override
    public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
        img.scaleAbsolute(position.getWidth(), position.getHeight());
        img.setAbsolutePosition(position.getLeft(), position.getBottom());
        PdfContentByte canvas = canvases[PdfPTable.BACKGROUNDCANVAS];
        try {
            canvas.addImage(img);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }
    /*
     class PositionEvent implements PdfPCellEvent {

     protected Phrase content;
     protected float wPct;
     protected float hPct;
     protected int alignment;

     public PositionEvent(Phrase content, float wPct, float hPct, int alignment) {
     this.content = content;
     this.wPct = wPct;
     this.hPct = hPct;
     this.alignment = alignment;
     }

     @Override
     public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
     PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
     float x = position.getLeft() + wPct * position.getWidth();
     float y = position.getBottom() + hPct * (position.getHeight() - content.getLeading());
     ColumnText.showTextAligned(canvas, alignment, content, x, y, 0);
     }
     }
     */
}
