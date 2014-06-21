package com.example.google;

public class BoundingBox {
	int beginPositionX;
	int beginPositionY;
	int width;
	int height;
	
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
    
    public void setBeginPositionX(int amount){
    	beginPositionX = amount;
    }
    
    public void setBeginPositionY(int amount){
    	beginPositionY = amount;
    }
    
    public int getWidth(){
    	return width;
    }
    
    public int getHeight(){
    	return height;
    }
    
    public void decrementBeginPositionX(int amount){
    	beginPositionX-=amount;
    }
    
    public void decrementBeginPositionY(int amount){
    	beginPositionY-=amount;
    }
    
    public void incrementBeginPositionX(int amount){
    	beginPositionX+=amount;
    }
    
    public void incrementBeginPositionY(int amount){
    	beginPositionY+=amount;
    }
    
    
    public boolean intersectsWithAnotherBox(BoundingBox anotherBox){	
    	return !(
    			(beginPositionY + height < anotherBox.beginPositionY) ||
    			(beginPositionY > anotherBox.beginPositionY + anotherBox.height) ||
    			(beginPositionX > anotherBox.beginPositionX + anotherBox.width) ||
    			(beginPositionX + width  < anotherBox.beginPositionX) );
    }
    
    public BoundingBox clone(){
    	return new BoundingBox(beginPositionX, beginPositionY, width, height);
    }

}
