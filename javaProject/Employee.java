package javaProject;

/**
 *
 * @author Allai
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Employee
{
    private String name;
    private String email;
    private int id;
    private static int UniqueId = 34420;
    private int phone;
    private Date dob;

    public Employee()
    {
        Date date = new Date();
    }

    public Employee(String n, String e, int p, Date d)
    {
        this.name = n;
        this.email = e;
        this.phone = p;
        this.dob = d;
        id = UniqueId++;
    }

    public void setName(String n)
    {
        name = n;
    }

    public void setEmail(String e)
    {
        email = e;
    }

    public void setPhone(int p)
    {
        phone = p;
    }

    public void setDOB(Date d)
    {
        dob = d;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public int getPhone()
    {
        return phone;
    }

    public Date getDOB()
    {
        return dob;
    }

    //Getting ID
    public void setId(int getId)
    {
        this.id = getId;
    }

    public int getId()
    {
        return id;
    }

    public static int getUniqueId()
    {
        return UniqueId;
    }

    @Override
    public String toString()
    {
        return String.format("Name\t" + name + "\n" + "Email\t" + email + "\n" + "Phone\t" + phone + "\n" + "DoB\t" + dob + "\n" + "ID\t" + id + "\n");
    }

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException
    {
        ArrayList<Employee> emp = new ArrayList<>();
        ArrayList<Employee> show = new ArrayList<>();
        ArrayList<Employee> agents = new ArrayList<>();
        ArrayList<Double> earnings = new ArrayList<>();
        int option = 0;
        //Saving into a file
        //Reading From File
        readFromFile(emp);
        while(option != 8)
        {
            System.out.println("Menu: ");
            System.out.println("1 : Add New Employee");
            System.out.println("2 : Print All Details");
            System.out.println("3 : Login Using Employee ID(Available After Adding Employee Details)");
            System.out.println("4 : Search Employee(Available Only After Adding Employees)");
            System.out.println("5 : Edit Employee Details(Available Only After Adding Employees)");
            System.out.println("6 : Show all contractors and employees");
            System.out.println("7 : Show all agents");
            System.out.println("8 : Show the earnings of Agents and Contractors");
            System.out.println("\nEnter Option: ");
            Scanner scan = new Scanner(System.in);
            option = scan.nextInt();
            switch(option)
            {
                case 1:
                {
                    addItems(emp);//Add Employee Details
                    addToFile(emp);
                    break;
                }
                case 2:
                {
                    printDetails(emp);
                    break;
                }
                case 3:
                {
                    employeeLogin(emp);
                    break;
                }
                case 4:
                {
                    searchName(emp); //searches through the array
                    break;
                }
                case 5:
                {
                    editEmpDetails(emp);//Edit's the employee details
                    break;
                }
                case 6:
                {
                    show = getAgentContract(emp);
                    for(int x = 0; x < show.size(); x++)
                    {
                        System.out.println(show.get(x).toString());
                    }
                    break;
                }
                case 7:
                {
                    agents = getAgents(emp);
                    for(int y = 0; y < agents.size(); y++)
                    {
                        System.out.println(agents.get(y));
                    }
                    break;
                }
                case 8:
                {
                    earnings = getEarnings(emp);
                    for(int z = 0; z < earnings.size(); z++)
                    {
                        System.out.println(earnings.get(z));
                    }
                }
                case 9:
                {
                    return;
                }
            }
        }
        System.out.println("***************************");
    }

    public static void addItems(ArrayList<Employee> emp) throws ParseException
    {
        Scanner enter = new Scanner(System.in);
        String addName;
        String addEmail;
        String addDOB;
        int addPhone;
        int type;
        double salary;
        int work;
        double rate;
        System.out.println("*****************************");
        for(int i = 0; i < 2; i++)
        {
            System.out.println("Enter Name: ");
            Scanner name = new Scanner(System.in);
            addName = name.nextLine();
            System.out.println("Enter Email: ");
            Scanner email = new Scanner(System.in);
            addEmail = email.nextLine();
            System.out.println("Enter Phone: ");
            Scanner phone = new Scanner(System.in);
            addPhone = phone.nextInt();
            System.out.println("Enter DoB: ");
            Scanner dob = new Scanner(System.in);
            addDOB = dob.nextLine();
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Date date1 = df.parse(addDOB);
            System.out.print("Please type in 1 if the employee is an agent and type in 2 if the employee is a contractor:");
            type = enter.nextInt();
            if(!addEmail.contains("@"))
            {
                System.out.println("Error , invalid email addresses");
            }
            else
            {
                emp.add(new Employee(addName, addEmail, addPhone, date1));
                if(type == 1)
                {
                    System.out.print("Please type in the salary of the agent:");
                    salary = enter.nextDouble();
                    emp.add(new Agent(addName, addEmail, addPhone, date1, salary));
                }
                else if(type == 2)
                {
                    System.out.print("Please type in the hours worked:");
                    work = enter.nextInt();
                    System.out.print("Please type in the pay per hour:");
                    rate = enter.nextDouble();
                    emp.add(new Contractors(addName, addEmail, addPhone, date1, work, rate));
                }
                else if(type < 0 || type > 2)
                {
                    System.out.println("Error plese type in 1 or 2");
                }
            }
            System.out.println("*************************");
        }
    }

    public static void printDetails(ArrayList<Employee> emp)
    {
        for(Employee list : emp)
        {
            System.out.println("*********Details List*********");
            System.out.println("Name:" + list.getName() + "\nEmail: " + list.getEmail() + "\nPhone: " + list.getPhone() + "\nDOB: " + list.getDOB() + "\nID: " + list.getId());
            System.out.println("*******************");
            //System.out.println(list);
        }
    }

    public static void searchName(ArrayList<Employee> emp)
    {
        System.out.println("**************************");
        Scanner scan = new Scanner(System.in);
        System.out.println("Search Employee: ");
        String searchEmp = scan.nextLine();
        boolean isExist = false;
        for(int x = 0; x < emp.size(); x++)
        {
            if(emp.get(x).getName().equals(searchEmp))
            {
                System.out.println("*******************");
                System.out.println("Name Found: ");
                System.out.println(emp.get(x).toString());
                isExist = true;
            }
            else
            {
                isExist = false;
                System.out.println("Only 1 Employee Found!");
            }
        }
        System.out.println("***************************");
    }

    public static void editEmpDetails(ArrayList<Employee> emp)
    {
        System.out.println("***************************");
        Scanner scan = new Scanner(System.in);
        System.out.println("Search Emp. ID: ");
        int searchID = scan.nextInt();
        boolean isExist = false;
        for(int x = 0; x < emp.size(); x++)
        {
            if(emp.get(x).getId() == searchID);
            {
                System.out.println("Edit Employee");
                System.out.println("1: Edit Name");
                System.out.println("2: Edit Email");
                System.out.println("3: Edit Phone");
                Scanner type = new Scanner(System.in);
                int getMenu = type.nextInt();
                switch(getMenu)
                {
                    case 1:
                    {
                        System.out.println("Enter New Name: ");
                        Scanner editName = new Scanner(System.in);
                        String newName = editName.next();
                        emp.get(x).setName(newName);
                        System.out.println(emp.get(x));
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Enter New Email: ");
                        Scanner editEmail = new Scanner(System.in);
                        String newEmail = editEmail.next();
                        emp.get(x).setEmail(newEmail);
                        System.out.println(emp.get(x));
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Enter New Phone: ");
                        Scanner editPhone = new Scanner(System.in);
                        int newPhone = editPhone.nextInt();
                        emp.get(x).setPhone(newPhone);
                        System.out.println(emp.get(x));
                        break;
                    }
                }
            }
            System.out.println("*******************************");
        }
    }

    public static void employeeLogin(ArrayList<Employee> emp)
    {
        System.out.println("*******************************");
        String password = "employee";
        int username;
        String enterPassword;
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        System.out.println("Enter Username: ");
        username = scan.nextInt();
        System.out.println("Enter Password: ");
        enterPassword = scan2.nextLine();
        boolean isExist = false;
        for(int x = 0; x < emp.size(); x++)
        {
            if(emp.get(x).getId() == username && password.equals(enterPassword))
            {
                System.out.println("****************************");
                System.out.println("Login Complete!");
                isExist = true;
            }
            else if(emp.get(x).getId() == username && !password.equals(enterPassword))
            {
                System.out.println("Password Does Not match");
            }
        }
        System.out.println("*******************************");
    }

    public static void addToFile(ArrayList<Employee> emp) throws FileNotFoundException
    {
        PrintWriter pw = new PrintWriter(new FileOutputStream("store.txt"));
        for(Employee listed : emp)
        {
            pw.println(listed.getName());
            pw.println(listed.getEmail());
            pw.println(listed.getPhone());
            pw.print(listed.getDOB());
            pw.println(listed.getId());
            pw.println("*******************************");
        }
        pw.close();
    }

    public static void readFromFile(ArrayList<Employee> emp) throws FileNotFoundException, IOException
    {
        BufferedReader bf;
        bf = new BufferedReader(new FileReader("store.txt"));
        System.out.println("-Details Saved In Text File-");
        System.out.println("**********************");
        while(true)
        {
            String line = bf.readLine();
            if(line == null)
            {
                break;
            }
            System.out.println(line);
        }
        System.out.println("**********************");
    }

    public static ArrayList<Employee> getAgentContract(ArrayList<Employee> emp)
    {
        ArrayList<Employee> agentsContracts = new ArrayList<>();
        for(Employee e : emp)
        {
            if(e instanceof Agent)
            {
                agentsContracts.add(e);
            }
            else if(e instanceof Contractors)
            {
                agentsContracts.add(e);
            }
        }
        return agentsContracts;
    }

    public static ArrayList<Employee> getAgents(ArrayList<Employee> emp)
    {
        ArrayList<Employee> agents = new ArrayList<>();
        boolean agent = false;
        for(Employee e : emp)
        {
            if(e instanceof Agent)
            {
                agents.add(e);
                agent = true;
            }
        }
        if(agent == false)
        {
            System.out.println("Error , we have no agent in our database");
        }
        return agents;
    }

    public static ArrayList<Double> getEarnings(ArrayList<Employee> emp)
    {
        ArrayList<Double> earnings = new ArrayList<>();
        for(Employee e : emp)
        {
            if(e instanceof Agent)
            {
                earnings.add(((Agent) e).getSalary());
            }
            else if(e instanceof Contractors)
            {
                earnings.add(((Contractors) e).getRate() * ((Contractors) e).getWork());
            }
        }
        return earnings;
    }
}
