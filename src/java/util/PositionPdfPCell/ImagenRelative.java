package Util.PositionPdfPCell;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class ImagenRelative implements PdfPCellEvent {

    public enum POSITION {

        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    };

    protected Image img;

    public ImagenRelative(Image img) {
        this.img = img;
    }

    @Override
    public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
        img.scaleToFit(position.getWidth(), position.getHeight());
        img.setAbsolutePosition(position.getLeft() + (position.getWidth() - img.getScaledWidth()) / 2,
                position.getBottom() + (position.getHeight() - img.getScaledHeight()) / 2);
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
        protected POSITION pos;

        public PositionEvent(Phrase content, POSITION pos) {
            this.content = content;
            this.pos = pos;
        }

        @Override
        public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
            float x = 0;
            float y = 0;
            int alignment = 0;
            switch (pos) {
                case TOP_LEFT:
                    x = position.getLeft(3);
                    y = position.getTop(content.getLeading());
                    alignment = Element.ALIGN_LEFT;
                    break;
                case TOP_RIGHT:
                    x = position.getRight(3);
                    y = position.getTop(content.getLeading());
                    alignment = Element.ALIGN_RIGHT;
                    break;
                case BOTTOM_LEFT:
                    x = position.getLeft(3);
                    y = position.getBottom(3);
                    alignment = Element.ALIGN_LEFT;
                    break;
                case BOTTOM_RIGHT:
                    x = position.getRight(3);
                    y = position.getBottom(3);
                    alignment = Element.ALIGN_RIGHT;
                    break;
            }
            ColumnText.showTextAligned(canvas, alignment, content, x, y, 0);
        }
    }
    */
}
