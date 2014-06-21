package com.example.google;

public class BoundingBox {
	private int beginPositionX;
	private int beginPositionY;
	private int width;
	private int height;
	
	public BoundingBox(int beginX, int beginY,int  width, int height){
		this.beginPositionX = beginX;
		this.beginPositionY = beginY;
		this.width = width;
		this.height = height;
	}
	
    public boolean isWithinBox(int x, int y){
    	return (x>=beginPositionX 
    			&& x <= beginPositionX + width
    			
    			&& y >= beginPositionY 
    			&& y <= beginPositionY + height);

    }
    
    public int getBeginPositionX(){
    	return beginPositionX;
    }
    
    public int getBeginPositionY(){
    	return beginPositionY;
    }
    
    public int getWidth(){
    	return width;
    }
    
    public int getHeight(){
    	return height;
    }

}
