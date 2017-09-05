package com.zoomRx.eb.entity;

public class Commercial extends Connection{
	public Commercial(int currentReading, int previousReading,float slabs[])
	{
		super(currentReading, previousReading, slabs);
	}
	
	public float computeBill()
	{
		float electricityDuty=0.00f;
		float billAmt=0.00f;
		float cur=0.00f;
		int units=currentReading-previousReading;
		int i=0;
		while(i<3)
		{
				
				if(units>=50&&i!=2)
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
		if(billAmt>=10000)
		{
			electricityDuty=billAmt*0.09f;
		}
		else if(billAmt>=5000)
		{
			electricityDuty=billAmt*0.06f;
		}
		else
		{
			electricityDuty=billAmt*0.02f;
		}
		
		return billAmt+electricityDuty;
	}
}
