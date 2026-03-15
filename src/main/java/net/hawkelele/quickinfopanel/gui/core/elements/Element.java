package net.hawkelele.quickinfopanel.gui.core.elements;

public abstract class Element {
    protected boolean hidden = false;

    public boolean shouldBeHidden() {
        return hidden;
    }

    public void hide() {
        this.hidden = true;
    }

    public void show() {
        if (this.shouldBeHidden()) return;

        this.hidden = false;
    }

    abstract int getWidth();
    abstract int getHeight();

    abstract void render(int x, int y);

}
