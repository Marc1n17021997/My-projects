package javaProject;

/**
 *
 * @author Julka
 * Date:02/12/2016
 * Task: Inheriting from contractor
 */
import java.util.Date;
public class Agent extends Employee
{
    private double salary;

    public Agent(String n, String e, int p, Date d, double s)
    {
        super(n, e, p, d);
        this.salary = s;
    }

    public void setSalary(double s)
    {
        this.salary = s;
    }

    public double getSalary()
    {
        return salary;
    }

    // @Override
    public String toString()
    {
        return "Agents" + super.toString() + "\n" + "salary = " + salary;
    }
}
