package javaProject;

/**
 *
 * @author Julka
 */
import java.util.Date;

public class Contractors extends Employee
{
    private int hrsWorked;
    private double hrsRate;
    
    public Contractors(String name , String email , int phone  , Date dob , int hrsW , double hrsR)
    {
        super(name , email , phone , dob);
        this.hrsWorked = hrsW;
        this.hrsRate = hrsR;
    }
    
    public void setWork(int hrsW)
    {
        this.hrsWorked = hrsW;
    }
    
    public void setRate(double hrsW)
    {
        this.hrsRate = hrsW;
    }
    
    public int getWork()
    {
        return hrsWorked;
    }
    
    public double getRate()
    {
        return hrsRate;
    }
    
    @Override
    public String toString()
    {
        return "\n" + "Contractors:" + super.toString() + "Hours worked:" + hrsWorked + "\n" + "Hourly rate" + hrsRate + "\n"; 
    }
}
