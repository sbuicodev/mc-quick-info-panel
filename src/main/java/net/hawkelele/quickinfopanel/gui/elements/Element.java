package net.hawkelele.quickinfopanel.gui.elements;

public abstract class Element {
    protected boolean hidden = false;

    public boolean shouldBeHidden() {
        return hidden;
    }

    abstract int getWidth();
    abstract int getHeight();

    abstract void render(int x, int y);
}
