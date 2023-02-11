package fr.iutfbleau.SAE32_2022.view;

import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class WrapLayout extends FlowLayout {

    /**
     * This value indicates that each row of components should be left-justified.
     */
    public static final int LEFT = 0;

    /**
     * This value indicates that each row of components should be centered.
     */
    public static final int CENTER = 1;

    /**
     * This value indicates that each row of components should be right-justified.
     */
    public static final int RIGHT = 2;

    /**
     * This value indicates that each row of components should be justified to the
     * leading edge of the container's orientation, for example, to the left in
     * left-to-right orientations.
     *
     * @see java.awt.Component#getComponentOrientation
     * @see java.awt.ComponentOrientation
     * @since 1.2
     */
    public static final int LEADING = 3;

    /**
     * This value indicates that each row of components should be justified to the
     * trailing edge of the container's orientation, for example, to the right in
     * left-to-right orientations.
     *
     * @see java.awt.Component#getComponentOrientation
     * @see java.awt.ComponentOrientation
     * @since 1.2
     */
    public static final int TRAILING = 4;

    /**
     * <code>align</code> is the property that determines how each row distributes
     * empty space. It can be one of the following values:
     * <ul>
     * <li><code>LEFT</code>
     * <li><code>RIGHT</code>
     * <li><code>CENTER</code>
     * </ul>
     *
     * @serial
     * @see #getAlignment
     * @see #setAlignment
     */
    int align; // This is for 1.1 serialization compatibility

    /**
     * <code>newAlign</code> is the property that determines how each row
     * distributes empty space for the Java 2 platform, v1.2 and greater. It can be
     * one of the following three values:
     * <ul>
     * <li><code>LEFT</code>
     * <li><code>RIGHT</code>
     * <li><code>CENTER</code>
     * <li><code>LEADING</code>
     * <li><code>TRAILING</code>
     * </ul>
     *
     * @serial
     * @since 1.2
     * @see #getAlignment
     * @see #setAlignment
     */
    int newAlign; // This is the one we actually use

    /**
     * The flow layout manager allows a seperation of components with gaps. The
     * horizontal gap will specify the space between components and between the
     * components and the borders of the <code>Container</code>.
     *
     * @serial
     * @see #getHgap()
     * @see #setHgap(int)
     */
    int hgap;

    /**
     * The flow layout manager allows a seperation of components with gaps. The
     * vertical gap will specify the space between rows and between the the rows and
     * the borders of the <code>Container</code>.
     *
     * @serial
     * @see #getHgap()
     * @see #setHgap(int)
     */
    int vgap;

    /**
     * If true, components will be aligned on their baseline.
     */
    private boolean alignOnBaseline;

    /*
     * JDK 1.1 serialVersionUID
     */
    private static final long serialVersionUID = -7262534875583282631L;

    /**
     * Constructs a new <code>FlowLayout</code> with a centered alignment and a
     * default 5-unit horizontal and vertical gap.
     */
    public WrapLayout() {
        this(CENTER, 5, 5);
    }

    /**
     * Constructs a new <code>FlowLayout</code> with the specified alignment and a
     * default 5-unit horizontal and vertical gap. The value of the alignment
     * argument must be one of <code>FlowLayout.LEFT</code>,
     * <code>FlowLayout.RIGHT</code>, <code>FlowLayout.CENTER</code>,
     * <code>FlowLayout.LEADING</code>, or <code>FlowLayout.TRAILING</code>.
     * 
     * @param align the alignment value
     */
    public WrapLayout(int align) {
        this(align, 5, 5);
    }

    /**
     * Creates a new flow layout manager with the indicated alignment and the
     * indicated horizontal and vertical gaps.
     * <p>
     * The value of the alignment argument must be one of
     * <code>FlowLayout.LEFT</code>, <code>FlowLayout.RIGHT</code>,
     * <code>FlowLayout.CENTER</code>, <code>FlowLayout.LEADING</code>, or
     * <code>FlowLayout.TRAILING</code>.
     * 
     * @param align the alignment value
     * @param hgap  the horizontal gap between components and between the components
     *              and the borders of the <code>Container</code>
     * @param vgap  the vertical gap between components and between the components
     *              and the borders of the <code>Container</code>
     */
    public WrapLayout(int align, int hgap, int vgap) {
        this.hgap = hgap;
        this.vgap = vgap;
        setAlignment(align);
    }

    /**
     * Gets the alignment for this layout. Possible values are
     * <code>FlowLayout.LEFT</code>, <code>FlowLayout.RIGHT</code>,
     * <code>FlowLayout.CENTER</code>, <code>FlowLayout.LEADING</code>, or
     * <code>FlowLayout.TRAILING</code>.
     * 
     * @return the alignment value for this layout
     * @see java.awt.FlowLayout#setAlignment
     * @since JDK1.1
     */
    public int getAlignment() {
        return newAlign;
    }

    /**
     * Sets the alignment for this layout. Possible values are
     * <ul>
     * <li><code>FlowLayout.LEFT</code>
     * <li><code>FlowLayout.RIGHT</code>
     * <li><code>FlowLayout.CENTER</code>
     * <li><code>FlowLayout.LEADING</code>
     * <li><code>FlowLayout.TRAILING</code>
     * </ul>
     * 
     * @param align one of the alignment values shown above
     * @see #getAlignment()
     * @since JDK1.1
     */
    public void setAlignment(int align) {
        this.newAlign = align;

        // this.align is used only for serialization compatibility,
        // so set it to a value compatible with the 1.1 version
        // of the class

        switch (align) {
        case LEADING:
            this.align = LEFT;
            break;
        case TRAILING:
            this.align = RIGHT;
            break;
        default:
            this.align = align;
            break;
        }
    }

    /**
     * Gets the horizontal gap between components and between the components and the
     * borders of the <code>Container</code>
     *
     * @return the horizontal gap between components and between the components and
     *         the borders of the <code>Container</code>
     * @see java.awt.FlowLayout#setHgap
     * @since JDK1.1
     */
    public int getHgap() {
        return hgap;
    }

    /**
     * Sets the horizontal gap between components and between the components and the
     * borders of the <code>Container</code>.
     *
     * @param hgap the horizontal gap between components and between the components
     *             and the borders of the <code>Container</code>
     * @see java.awt.FlowLayout#getHgap
     * @since JDK1.1
     */
    public void setHgap(int hgap) {
        this.hgap = hgap;
    }

    /**
     * Gets the vertical gap between components and between the components and the
     * borders of the <code>Container</code>.
     *
     * @return the vertical gap between components and between the components and
     *         the borders of the <code>Container</code>
     * @see java.awt.FlowLayout#setVgap
     * @since JDK1.1
     */
    public int getVgap() {
        return vgap;
    }

    /**
     * Sets the vertical gap between components and between the components and the
     * borders of the <code>Container</code>.
     *
     * @param vgap the vertical gap between components and between the components
     *             and the borders of the <code>Container</code>
     * @see java.awt.FlowLayout#getVgap
     * @since JDK1.1
     */
    public void setVgap(int vgap) {
        this.vgap = vgap;
    }

    /**
     * Sets whether or not components should be vertically aligned along their
     * baseline. Components that do not have a baseline will be centered. The
     * default is false.
     *
     * @param alignOnBaseline whether or not components should be vertically aligned
     *                        on their baseline
     * @since 1.6
     */
    public void setAlignOnBaseline(boolean alignOnBaseline) {
        this.alignOnBaseline = alignOnBaseline;
    }

    /**
     * Returns true if components are to be vertically aligned along their baseline.
     * The default is false.
     *
     * @return true if components are to be vertically aligned along their baseline
     * @since 1.6
     */
    public boolean getAlignOnBaseline() {
        return alignOnBaseline;
    }

    /**
     * Adds the specified component to the layout. Not used by this class.
     * 
     * @param name the name of the component
     * @param comp the component to be added
     */
    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    /**
     * Removes the specified component from the layout. Not used by this class.
     * 
     * @param comp the component to remove
     * @see java.awt.Container#removeAll
     */
    @Override
    public void removeLayoutComponent(Component comp) {
    }

    /**
     * Returns the preferred dimensions for this layout given the <i>visible</i>
     * components in the specified target container.
     * 
     * @param target the component which needs to be laid out
     * @return the preferred dimensions to lay out the subcomponents of the
     *         specified container
     */
    @Override
    public Dimension preferredLayoutSize(Container target) {
        return layoutSize(target, true);
    }

    /**
     * Returns the minimum dimensions needed to layout the <i>visible</i> components
     * contained in the specified target container.
     * 
     * @param target the component which needs to be laid out
     * @return the minimum dimensions to lay out the subcomponents of the specified
     *         container
     */
    @Override
    public Dimension minimumLayoutSize(Container target) {
        Dimension minimum = layoutSize(target, false);
        minimum.width -= (getHgap() + 1);
        return minimum;
    }

    /**
     * Returns the minimum or preferred dimension needed to layout the target
     * container.
     *
     * @param target    target to get layout size for
     * @param preferred should preferred size be calculated
     * @return the dimension to layout the target container
     */
    private Dimension layoutSize(Container target, boolean preferred) {
        synchronized (target.getTreeLock()) {
            // Each row must fit with the width allocated to the containter.
            // When the container width = 0, the preferred width of the container
            // has not yet been calculated so lets ask for the maximum.

            int targetWidth = target.getSize().width;
            Container container = target;

            while (container.getSize().width == 0 && container.getParent() != null) {
                container = container.getParent();
            }

            targetWidth = container.getSize().width;

            if (targetWidth == 0)
                targetWidth = Integer.MAX_VALUE;

            int hgap = getHgap();
            int vgap = getVgap();
            Insets insets = target.getInsets();
            int horizontalInsetsAndGap = insets.left + insets.right + (hgap * 2);
            int maxWidth = targetWidth - horizontalInsetsAndGap;

            // Fit components into the allowed width

            Dimension dim = new Dimension(0, 0);
            int rowWidth = 0;
            int rowHeight = 0;

            int nmembers = target.getComponentCount();

            for (int i = 0; i < nmembers; i++) {
                Component m = target.getComponent(i);

                if (m.isVisible()) {
                    Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();

                    // Can't add the component to current row. Start a new row.

                    if (rowWidth + d.width > maxWidth) {
                        addRow(dim, rowWidth, rowHeight);
                        rowWidth = 0;
                        rowHeight = 0;
                    }

                    // Add a horizontal gap for all components after the first

                    if (rowWidth != 0) {
                        rowWidth += hgap;
                    }

                    rowWidth += d.width;
                    rowHeight = Math.max(rowHeight, d.height);
                }
            }

            addRow(dim, rowWidth, rowHeight);

            dim.width += horizontalInsetsAndGap;
            dim.height += insets.top + insets.bottom + vgap * 2;

            // When using a scroll pane or the DecoratedLookAndFeel we need to
            // make sure the preferred size is less than the size of the
            // target containter so shrinking the container size works
            // correctly. Removing the horizontal gap is an easy way to do this.

            Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, target);

            if (scrollPane != null && target.isValid()) {
                dim.width -= (hgap + 1);
            }

            return dim;
        }
    }

    /*
     * A new row has been completed. Use the dimensions of this row to update the
     * preferred size for the container.
     *
     * @param dim update the width and height when appropriate
     * 
     * @param rowWidth the width of the row to add
     * 
     * @param rowHeight the height of the row to add
     */
    private void addRow(Dimension dim, int rowWidth, int rowHeight) {
        dim.width = Math.max(dim.width, rowWidth);

        if (dim.height > 0) {
            dim.height += getVgap();
        }

        dim.height += rowHeight;
    }

    /**
     * Centers the elements in the specified row, if there is any slack.
     * 
     * @param target      the component which needs to be moved
     * @param x           the x coordinate
     * @param y           the y coordinate
     * @param width       the width dimensions
     * @param height      the height dimensions
     * @param rowStart    the beginning of the row
     * @param rowEnd      the the ending of the row
     * @param useBaseline Whether or not to align on baseline.
     * @param ascent      Ascent for the components. This is only valid if
     *                    useBaseline is true.
     * @param descent     Ascent for the components. This is only valid if
     *                    useBaseline is true.
     * @return actual row height
     */
    private int moveComponents(Container target, int x, int y, int width, int height, int rowStart, int rowEnd,
            boolean ltr, boolean useBaseline, int[] ascent, int[] descent) {
        switch (newAlign) {
        case LEFT:
            x += ltr ? 0 : width;
            break;
        case CENTER:
            x += width / 2;
            break;
        case RIGHT:
            x += ltr ? width : 0;
            break;
        case LEADING:
            break;
        case TRAILING:
            x += width;
            break;
        }
        int maxAscent = 0;
        int nonbaselineHeight = 0;
        int baselineOffset = 0;
        if (useBaseline) {
            int maxDescent = 0;
            for (int i = rowStart; i < rowEnd; i++) {
                Component m = target.getComponent(i);
                if (m.isVisible()) {
                    if (ascent[i] >= 0) {
                        maxAscent = Math.max(maxAscent, ascent[i]);
                        maxDescent = Math.max(maxDescent, descent[i]);
                    } else {
                        nonbaselineHeight = Math.max(m.getHeight(), nonbaselineHeight);
                    }
                }
            }
            height = Math.max(maxAscent + maxDescent, nonbaselineHeight);
            baselineOffset = (height - maxAscent - maxDescent) / 2;
        }
        for (int i = rowStart; i < rowEnd; i++) {
            Component m = target.getComponent(i);
            if (m.isVisible()) {
                int cy;
                if (useBaseline && ascent[i] >= 0) {
                    cy = y + baselineOffset + maxAscent - ascent[i];
                } else {
                    cy = y;
                }
                if (ltr) {
                    m.setLocation(x, cy);
                } else {
                    m.setLocation(target.getWidth() - x - m.getWidth(), cy);
                }
                x += m.getWidth() + hgap;
            }
        }
        return height;
    }

    /**
     * Lays out the container. This method lets each <i>visible</i> component take
     * its preferred size by reshaping the components in the target container in
     * order to satisfy the alignment of this <code>FlowLayout</code> object.
     *
     * @param target the specified component being laid out
     * @see Container
     * @see java.awt.Container#doLayout
     */
    @Override
    public void layoutContainer(Container target) {
        synchronized (target.getTreeLock()) {
            Insets insets = target.getInsets();
            int maxwidth = target.getWidth() - (insets.left + insets.right + hgap * 2);
            int nmembers = target.getComponentCount();
            int x = 0, y = insets.top + vgap;
            int rowh = 0, start = 0;

            boolean ltr = target.getComponentOrientation().isLeftToRight();

            boolean useBaseline = getAlignOnBaseline();
            int[] ascent = null;
            int[] descent = null;

            if (useBaseline) {
                ascent = new int[nmembers];
                descent = new int[nmembers];
            }

            for (int i = 0; i < nmembers; i++) {
                Component m = target.getComponent(i);
                if (m.isVisible()) {
                    Dimension d = m.getPreferredSize();
                    m.setSize(d.width, d.height);

                    if (useBaseline) {
                        int baseline = m.getBaseline(d.width, d.height);
                        if (baseline >= 0) {
                            ascent[i] = baseline;
                            descent[i] = d.height - baseline;
                        } else {
                            ascent[i] = -1;
                        }
                    }
                    if ((x == 0) || ((x + d.width) <= maxwidth)) {
                        if (x > 0) {
                            x += hgap;
                        }
                        x += d.width;
                        rowh = Math.max(rowh, d.height);
                    } else {
                        rowh = moveComponents(target, insets.left + hgap, y, maxwidth - x, rowh, start, i, ltr,
                                useBaseline, ascent, descent);
                        x = d.width;
                        y += vgap + rowh;
                        rowh = d.height;
                        start = i;
                    }
                }
            }
            moveComponents(target, insets.left + hgap, y, maxwidth - x, rowh, start, nmembers, ltr, useBaseline, ascent,
                    descent);
        }
    }

    /**
     * Returns a string representation of this <code>FlowLayout</code> object and
     * its values.
     * 
     * @return a string representation of this layout
     */
    @Override
    public String toString() {
        String str = "";
        switch (align) {
        case LEFT:
            str = ",align=left";
            break;
        case CENTER:
            str = ",align=center";
            break;
        case RIGHT:
            str = ",align=right";
            break;
        case LEADING:
            str = ",align=leading";
            break;
        case TRAILING:
            str = ",align=trailing";
            break;
        }
        return getClass().getName() + "[hgap=" + hgap + ",vgap=" + vgap + str + "]";
    }

}