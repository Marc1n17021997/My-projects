package project;

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
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class MessageStore
{
    private String sender;
    private String receiver;
    private String subject;
    private String message;
    private String priority;
    private static int uniqueID = 10000;
    private int id;
    private LocalDate date;
//    private SimpleDateFormat df = new SimpleDateFormat();
    //Default Constructor

    public MessageStore()
    {
    }

    public MessageStore(String inputSender, String inputReceiver, String inputSubject, String inputMessage, String inputPriority, LocalDate getDate)
    {
        this.sender = inputSender;
        this.receiver = inputReceiver;
        this.subject = inputSubject;
        this.message = inputMessage;
        this.priority = inputPriority;
        id = uniqueID++;
        this.date = getDate;
    }

    public MessageStore(String p)
    {
        this.priority = p;
    }

    //Setters
    public void setSender(String inputSender)
    {
        sender = inputSender;
    }

    public void setReceiver(String inputReceiver)
    {
        receiver = inputReceiver;
    }

    public void setSubject(String inputSubject)
    {
        subject = inputSubject;
    }

    public void setMessage(String inputMessage)
    {
        message = inputMessage;
    }

    public void setId(int getId)
    {
        this.id = getId;
    }

    public void setPriority(String inputPriority)
    {
        priority = inputPriority;
    }

    //Getters
    public String getSender()
    {
        return sender;
    }

    public String getReceiver()
    {
        return receiver;
    }

    public String getSubject()
    {
        return subject;
    }

    public String getMessage()
    {
        return message;
    }

    public String getPriority()
    {
        return priority;
    }

    //Generating Unique ID
    public int getId()
    {
        return id;
    }

    public static int getUniqueId()
    {
        //uniqueID = uniqueID + 1;
        return uniqueID;
    }

    public void setDate(LocalDate getDate)
    {
        this.date = getDate;
    }

    public LocalDate getDate()
    {
        return date;
    }

    @Override
    public String toString()
    {
        return "Sender: " + sender + "\n" + "Receiver: " + receiver + "\n" + "Subject: " + subject + "\n" + "Message: " + message + "\n" + "Priority: " + priority + "\n" + "ID: " + id + "\n*************************\n";
    }

    public int hashCode()
    {
        return (int) id
                * sender.hashCode()
                * receiver.hashCode()
                * subject.hashCode()
                * message.hashCode();
    }

    //Main Method
    public static void main(String[] args) throws IOException 
    {
        ArrayList<MessageStore> arr = new ArrayList<>();
        int option = 0;
        readFromFile(arr);
        while(option != 6)
        {
            System.out.println("MENU: ");
            System.out.println("1: Send Messages");
            System.out.println("2: Print Messages");
            System.out.println("3: Search Messages");
            System.out.println("4: Search ID");
            System.out.println("5: Search Priority");
            System.out.println("6: Search Substring");
            System.out.println("\nEnter Option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            switch(option)
            {
                case 1:
                {
                    addMessages(arr);
                    addToFile(arr);
                    break;
                }
                case 2:
                {
                    printMessages(arr);
                    break;
                }
                case 3:
                {
                    searchMessages(arr);
                    break;
                }
                case 4:
                {
                    searchID(arr);
                    break;
                }
                case 5:
                {
                    searchPriority(arr);
                    break;
                }
                case 6:
                {
                    searchSubstring(arr);
                    break;
                }
                case 7:
                {
                    return;
                }
            }
        }
    }

    public static void addMessages(ArrayList<MessageStore> arr)
    {
        String addSender = " ";
        String addReceiver;
        String addSubject;
        String addMessage;
        String addPriority = null;
        String at = "@";
        //Storing sender,receiver,subject and message into seperate arraylists
        for(int i = 0; i < 2; i++)
        {
            System.out.println("Sender: ");
            Scanner sender = new Scanner(System.in);
            addSender = sender.nextLine();
            System.out.println("Receiver: ");
            Scanner receiver = new Scanner(System.in);
            addReceiver = receiver.nextLine();
            System.out.println("Subject: ");
            Scanner subject = new Scanner(System.in);
            addSubject = subject.nextLine();
            System.out.println("Message: ");
            Scanner message = new Scanner(System.in);
            addMessage = message.nextLine();
            System.out.println("Priority: ");
            Scanner priority = new Scanner(System.in);
            addPriority = priority.nextLine();
            if(!addSender.contains(at) && !addReceiver.contains(at))
            {
                System.out.println("Error , invalid email addresses");
            }
            else
            {
                System.out.print("Date sent:");
                LocalDate current = LocalDate.now();
                arr.add(new MessageStore(addSender, addReceiver, addSubject, addMessage, addPriority, current));
                System.out.println((arr.get(0).getDate()));
            }
            System.out.println("*******************");
        }
        //System.out.println(arr);
    }

    public static void printMessages(ArrayList<MessageStore> arr)
    {
        System.out.println("****************Enhanced For Loop*****************");
        for(MessageStore list : arr)
        {
            System.out.println(list);
            System.out.println("*******************");
            //System.out.println("Sender :" + list.getSender()+ "\nReceiver: " + list.getReceiver() + "\nSubject: " + list.getSubject() + "\nMessage: "+ list.getMessage() + "\nID:" + MessageStore.getUniqueId() );
        }
    }

    //Searching For Messages
    public static void searchMessages(ArrayList<MessageStore> arr)
    {
        //Search for a specific email
        Scanner sc = new Scanner(System.in);
        System.out.println("**********SEARCH**************");
        System.out.println("Email Search: ");
        String search = sc.nextLine();
        boolean found = false;
        for(int x = 0; x < arr.size(); x++)
        {
            if(arr.get(x).getSender().equals(search))
            {
                System.out.println("Email Found!");
                System.out.println(arr.get(x).toString());
                found = true;
            }
        }
        if(found == false)
        {
            System.out.println("Error , email not found");
        }
    }

    //Search For ID
    public static void searchID(ArrayList<MessageStore> arr)
    {
        Scanner sc3 = new Scanner(System.in);
        System.out.println("Search ID: ");
        int searchID = sc3.nextInt();
        boolean exist = false;
        for(int id = 0; id < arr.size(); id++)
        {
            if(arr.get(id).getId() == searchID)
            {
                System.out.println("ID Found!");
                System.out.println(arr.get(id).toString());
                exist = true;
            }
        }
        if(exist == false)
        {
            System.out.println("Error , the id isn't in our database");
        }
    }

    public static void searchPriority(ArrayList<MessageStore> arr)
    {
        //Search for user defined date
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Search Priority: ");
        String searchPriority = sc2.nextLine();
        boolean isExist2 = false;
        for(int p = 0; p < arr.size(); p++)
        {
            if(arr.get(p).getPriority().equals(searchPriority))
            {
                System.out.println("Priority Found!");
                System.out.println(arr.get(p).toString());
                isExist2 = true;
            }
        }
        if(isExist2 == false)
        {
            System.out.println("Error , no message contains that priority");
        }
    }

    public static void searchSubstring(ArrayList<MessageStore> arr)
    {
        //Search for a substring in message or subject
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Search substring: ");
        String substring = sc1.nextLine();
        boolean isExist = false;
        for(int j = 0; j < arr.size(); j++)
        {
            if(arr.get(j).getMessage().contains(substring) || arr.get(j).getSubject().contains(substring))
            {
                System.out.println("Substring Found!");
                System.out.println(arr.get(j).toString());
                isExist = true;
            }
        }
        if(isExist == false)
        {
            System.out.println("No such substring detected in any of the messages");
        }
        System.out.println("*************************");
    }
        public static void addToFile(ArrayList<MessageStore> arr) throws FileNotFoundException
    {
        PrintWriter pw = new PrintWriter(new FileOutputStream("messages.txt"));
        for(MessageStore listed : arr)
        {
            pw.println(listed.getSender());
            pw.println(listed.getReceiver());
            pw.println(listed.getSubject());
            pw.print(listed.getDate());
            pw.println(listed.getMessage());
            pw.println(listed.getPriority());
            pw.println("*******************************");
        }
        pw.close();
    }

    public static void readFromFile(ArrayList<MessageStore> arr) throws FileNotFoundException, IOException
    {
        BufferedReader bf;
        bf = new BufferedReader(new FileReader("messages.txt"));
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
}
