package net.hawkelele.quickinfopanel.gui.elements;

import net.hawkelele.quickinfopanel.gui.properties.Alignment;
import net.hawkelele.quickinfopanel.gui.properties.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Panel extends Element {
    protected final List<Element> children = new ArrayList<>();

    protected Direction direction = Direction.HORIZONTAL;
    protected Alignment alignment = Alignment.START;

    protected int preferredWidth = -1;
    protected int preferredHeight = -1;

    protected int gap = 0;
    protected boolean reverse = false;

    public Panel direction(Direction direction) {
        this.direction = direction;
        return this;
    }

    public Panel gap(int gap) {
        this.gap = gap;
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
        int currentX = x, currentY = y;
        int currentGap = gap;

        if (direction == Direction.HORIZONTAL) {
            if (alignment == Alignment.END) currentX = getWidth() - getContentWidth();
            if (alignment == Alignment.CENTER) currentX = (getWidth() / 2) - (getContentWidth() / 2);
            if (alignment == Alignment.SPACE_BETWEEN) {
                int blankSpace = getWidth() - children.stream().mapToInt(Element::getWidth).sum();

                if (blankSpace > currentGap) {
                    currentGap = children.size() > 1 ? blankSpace / (children.size() - 1) : currentGap;
                }
            }
        } else {
            if (alignment == Alignment.END) currentY = getHeight() - getContentHeight();
            if (alignment == Alignment.CENTER) currentX = (getHeight() / 2) - (getContentHeight() / 2);
            if (alignment == Alignment.SPACE_BETWEEN) {
                int blankSpace = getHeight() - children.stream().mapToInt(Element::getHeight).sum();

                if (blankSpace > currentGap) {
                    currentGap = children.size() > 1 ? blankSpace / (children.size() - 1) : currentGap;
                }
            }
        }

        for (Element child : children) {
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
