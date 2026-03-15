package net.hawkelele.quickinfopanel.gui.elements;

import net.hawkelele.quickinfopanel.gui.properties.Alignment;
import net.hawkelele.quickinfopanel.gui.properties.Justifying;
import net.hawkelele.quickinfopanel.gui.properties.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Panel extends Element {
    protected final List<Element> children = new ArrayList<>();

    protected Direction direction = Direction.HORIZONTAL;
    protected Justifying justifying = Justifying.START;
    protected Alignment alignment = Alignment.START;
    protected Alignment contentAlignment = Alignment.START;

    protected boolean visible = true;

    protected int preferredWidth = -1;
    protected int preferredHeight = -1;

    protected int gap = 0;
    protected boolean reverse = false;

    public void hide() {
        this.visible = false;
    }

    public void show()
    {
        this.visible = true;
    }

    public Panel direction(Direction direction) {
        this.direction = direction;
        return this;
    }

    public Panel gap(int gap) {
        this.gap = gap;
        return this;
    }

    public Panel justify(Justifying justifying) {
        this.justifying = justifying;
        return this;
    }

    public Panel align(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public Panel size(int width, int height) {
        this.preferredWidth = width;
        this.preferredHeight = height;
        return this;
    }

    public Panel child(Element child) {
        this.children.add(child);
        return this;
    }

    public Panel children(Element... elements) {
        this.children.addAll(Arrays.asList(elements));
        return this;
    }

    @Override
    public int getWidth() {
        return Math.max(getContentWidth(), preferredWidth);
    }

    @Override
    public int getHeight() {
        return Math.max(getContentHeight(), preferredHeight);
    }

    private int getContentWidth() {
        if (direction == Direction.HORIZONTAL) {
            return children.stream().mapToInt(Element::getWidth).sum() + (gap * (children.size() - 1));
        }
        return children.stream().mapToInt(Element::getWidth).max().orElse(0);
    }


    private int getContentHeight() {
        if (direction == Direction.HORIZONTAL) {
            return children.stream().mapToInt(Element::getHeight).max().orElse(0);
        }
        return children.stream().mapToInt(Element::getHeight).sum() + (gap * (children.size() - 1));
    }

    public Panel reverse() {
        this.reverse = true;
        return this;
    }

    @Override
    public void render(int x, int y) {
        if (!this.visible) return;

        int currentX = x, currentY = y;
        int currentGap = gap;

        if (direction == Direction.HORIZONTAL) {
            if (justifying == Justifying.END) currentX = getWidth() - getContentWidth();
            if (justifying == Justifying.CENTER) currentX = (getWidth() / 2) - (getContentWidth() / 2);
            if (justifying == Justifying.SPACE_BETWEEN) {
                int blankSpace = getWidth() - children.stream().mapToInt(Element::getWidth).sum();

                if (blankSpace > currentGap) {
                    currentGap = children.size() > 1 ? blankSpace / (children.size() - 1) : currentGap;
                }
            }

        } else {
            if (justifying == Justifying.END) currentY = getHeight() - getContentHeight();
            if (justifying == Justifying.CENTER) currentY = (getHeight() / 2) - (getContentHeight() / 2);
            if (alignment == Alignment.CENTER) currentX = (getWidth() / 2) - (getContentWidth() / 2);
            if (justifying == Justifying.SPACE_BETWEEN) {
                int blankSpace = getHeight() - children.stream().mapToInt(Element::getHeight).sum();

                if (blankSpace > currentGap) {
                    currentGap = children.size() > 1 ? blankSpace / (children.size() - 1) : currentGap;
                }
            }
        }

        for (Element child : children) {
            if (alignment == Alignment.CENTER) {
                currentX = (getWidth() / 2) - (child.getWidth() / 2);
            }


            child.render(currentX, currentY);

            if (direction == Direction.HORIZONTAL) {
                if (reverse) {
                    currentX -= child.getWidth() + currentGap;
                } else {
                    currentX += child.getWidth() + currentGap;
                }
            } else {
                if (reverse) {
                    currentY -= child.getHeight() + currentGap;
                } else {
                    currentY += child.getHeight() + currentGap;
                }
            }
        }
    }

    public void render() {
        this.render(0, 0);
    }

}
