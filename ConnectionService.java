package com.zoomRx.eb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zoomRx.eb.db.*;
import com.zoomRx.eb.entity.Commercial;
import com.zoomRx.eb.entity.Domestic;
import com.zoomRx.eb.exception.InvalidConnectionException;
import com.zoomRx.eb.exception.InvalidReadingException;

import com.zoomRx.eb.db.*;

public class ConnectionService {

	public boolean validate(int currentReading, int previousReading, String type) throws InvalidReadingException, InvalidConnectionException
	{
		if(currentReading<previousReading||currentReading<0||previousReading<0)
		{
			throw new InvalidReadingException();
		}
		else if(!type.equalsIgnoreCase("Domestic")&&!type.equalsIgnoreCase("Commercial"))
		{
			throw new InvalidConnectionException();
		}
		else
		return true;
	}
	
	public float calculateBillAmt(int currentReading, int previousReading, String type) 
	{
		
		try {
			float t=0.00f;
			boolean b=validate(currentReading,previousReading,type);
			if(b)
			{
				if(type.equalsIgnoreCase("Commercial"))
				{
					
					float[] slabs ={5.2f,6.8f,8.3f};
					Commercial c=new Commercial(currentReading, previousReading, slabs);
					t=c.computeBill();
				}
				else if(type.equalsIgnoreCase("Domestic"))
				{
					float[] slabs ={2.3f,4.2f,5.5f};
					Domestic d=new Domestic(currentReading, previousReading, slabs);
					t=d.computeBill();
				}
			}
			return t;
		} catch (InvalidReadingException e) {
			return -1;
		}
		catch(InvalidConnectionException e)
		{
			return -2;
		}
	}
	float f=0f;
	public String generateBill(int id,int currentReading, int previousReading, String type)
	{
		f=calculateBillAmt(currentReading,previousReading,type);
		if(f==-1)
			return "Incorrect Reading";
		else if(f==-2)
			return "Invalid ConnectionType";
		else{
			int tmp=0;
			Connection conn=util.getDBConnection();
			try {
				PreparedStatement ps=conn.prepareStatement("update eb set curr=?,pre=?,amount_paid=? where id=?");
				ps.setInt(4, id);
				ps.setInt(1, currentReading);
				ps.setInt(2, previousReading);
				ps.setFloat(3,f);
				if(ps.executeUpdate()!=0)
					tmp=1;
				else
					tmp=0;
			} catch (SQLException e) {
				e.toString();
			}

			return "Amount to be paid:"+f;
		}
		
	}
	
}
