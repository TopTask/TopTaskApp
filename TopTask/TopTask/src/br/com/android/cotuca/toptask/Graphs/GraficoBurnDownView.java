package br.com.android.cotuca.toptask.Graphs;

import org.afree.ui.Drawable;
import org.afree.util.ObjectUtilities;
import org.afree.ui.RectangleEdge;

import java.io.Serializable;

import android.graphics.Canvas;

import org.afree.util.PublicCloneable;
import org.afree.chart.annotations.AbstractXYAnnotation;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.Plot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.PlotRenderingInfo;
import org.afree.chart.plot.XYPlot;
import org.afree.graphics.geom.RectShape;


/**
 * A general annotation that can be placed on an {@link XYPlot}.
 */
public class GraficoBurnDownView extends AbstractXYAnnotation
        implements Cloneable, PublicCloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -6540812859722691020L;

    /** The scaling factor. */
    private double drawScaleFactor;

    /** The x-coordinate. */
    private double x;

    /** The y-coordinate. */
    private double y;

    /** The width. */
    private double displayWidth;

    /** The height. */
    private double displayHeight;

    /** The drawable object. */
    private Drawable drawable;

    /**
     * Creates a new annotation to be displayed within the given area.
     *
     * @param x  the x-coordinate for the area.
     * @param y  the y-coordinate for the area.
     * @param width  the width of the area.
     * @param height  the height of the area.
     * @param drawable  the drawable object (<code>null</code> not permitted).
     */
    public GraficoBurnDownView(double x, double y, double width, double height,
                                Drawable drawable) {
        this(x, y, width, height, 1.0, drawable);
    }

    /**
     * Creates a new annotation to be displayed within the given area.  If you
     * specify a <code>drawScaleFactor</code> of 2.0, the <code>drawable</code>
     * will be drawn at twice the requested display size then scaled down to
     * fit the space.
     *
     * @param x  the x-coordinate for the area.
     * @param y  the y-coordinate for the area.
     * @param displayWidth  the width of the area.
     * @param displayHeight  the height of the area.
     * @param drawScaleFactor  the scaling factor for drawing.
     * @param drawable  the drawable object (<code>null</code> not permitted).
     *
     * @since JFreeChart 1.0.11
     */
    public GraficoBurnDownView(double x, double y, double displayWidth,
            double displayHeight, double drawScaleFactor, Drawable drawable) {

        if (drawable == null) {
            throw new IllegalArgumentException("Null 'drawable' argument.");
        }
        this.x = x;
        this.y = y;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.drawScaleFactor = drawScaleFactor;
        this.drawable = drawable;

    }

    /**
     * Draws the annotation.
     *
     * @param canvas  the graphics device.
     * @param plot  the plot.
     * @param dataArea  the data area.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param rendererIndex  the renderer index.
     * @param info  if supplied, this info object will be populated with
     *              entity information.
     */
    public void draw(Canvas canvas, XYPlot plot, RectShape dataArea,
                     ValueAxis domainAxis, ValueAxis rangeAxis,
                     int rendererIndex,
                     PlotRenderingInfo info) {

        PlotOrientation orientation = plot.getOrientation();
        RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(
                plot.getDomainAxisLocation(), orientation);
        RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(
                plot.getRangeAxisLocation(), orientation);
        float j2DX = (float) domainAxis.valueToJava2D(this.x, dataArea,
                domainEdge);
        float j2DY = (float) rangeAxis.valueToJava2D(this.y, dataArea,
                rangeEdge);
        RectShape displayArea = new RectShape(
                j2DX - this.displayWidth / 2.0,
                j2DY - this.displayHeight / 2.0, this.displayWidth,
                this.displayHeight);

        // here we change the AffineTransform so we can draw the annotation
        // to a larger area and scale it down into the display area
        // afterwards, the original transform is restored

        canvas.save();
        
        RectShape drawArea = new RectShape(0.0, 0.0,
                this.displayWidth * this.drawScaleFactor,
                this.displayHeight * this.drawScaleFactor);

        canvas.scale((float)(1/this.drawScaleFactor), (float)(1/this.drawScaleFactor));
        canvas.translate((float)((j2DX - this.displayWidth / 2.0) * this.drawScaleFactor),
                (float)((j2DY - this.displayHeight / 2.0) * this.drawScaleFactor));
        
        this.drawable.draw(canvas, drawArea);

        canvas.restore();
        
        String toolTip = getToolTipText();
        String url = getURL();
        if (toolTip != null || url != null) {
            addEntity(info, displayArea, rendererIndex, toolTip, url);
        }
    }

    /**
     * Tests this annotation for equality with an arbitrary object.
     *
     * @param obj  the object to test against.
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean equals(Object obj) {

        if (obj == this) { // simple case
            return true;
        }
        // now try to reject equality...
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof GraficoBurnDownView)) {
            return false;
        }
        GraficoBurnDownView that = (GraficoBurnDownView) obj;
        if (this.x != that.x) {
            return false;
        }
        if (this.y != that.y) {
            return false;
        }
        if (this.displayWidth != that.displayWidth) {
            return false;
        }
        if (this.displayHeight != that.displayHeight) {
            return false;
        }
        if (this.drawScaleFactor != that.drawScaleFactor) {
            return false;
        }
        if (!ObjectUtilities.equal(this.drawable, that.drawable)) {
            return false;
        }
        // seem to be the same...
        return true;

    }

    /**
     * Returns a hash code.
     *
     * @return A hash code.
     */
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.y);
        result = 29 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.displayWidth);
        result = 29 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.displayHeight);
        result = 29 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Returns a clone of the annotation.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the annotation can't be cloned.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}