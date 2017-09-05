package com.zoomRx.eb.entity;

public class Domestic extends Connection{

	public Domestic(int currentReading, int previousReading,float slabs[])
	{
		super(currentReading, previousReading, slabs);
	}


	public float computeBill()
	{
		float billAmt=0.0f;
		float cur=0.0f;
		int units=currentReading-previousReading;
		int i=0;
		while(i<3)
		{
				
				if(units>=50)
				{
					cur=50;
					billAmt=billAmt+(cur*slabs[i]);
					units=units-50;
				}
				else if(units>0)
				{
					cur=units;
					billAmt=billAmt+(cur*slabs[i]);
					units=0;
				}
				else
				{
					break;
				}
				i++;
		}
		return billAmt;
	}
	
}
