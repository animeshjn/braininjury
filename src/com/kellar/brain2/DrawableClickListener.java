package com.kellar.brain2;

public interface DrawableClickListener {
	public static enum DrawablePosition { TOP, BOTTOM, LEFT, RIGHT };
    public void onClick(DrawablePosition target); 
}
