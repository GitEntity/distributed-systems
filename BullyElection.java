import java.io.*;
import java.util.Scanner;

class BullyElection
{
    static int n;
    static int pro[] = new int[100];
    static int sta[] = new int[100];
    static int co;

    public static void main(String args[]) throws IOException
    {
        System.out.print("Enter the number of processes: ");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        int i, j, k, l, m;

        for (i = 0; i < n; i++)
        {
            System.out.println("For process " + (i + 1) + "...");
            System.out.print("Status (1 for alive, 0 for dead): ");
            sta[i] = in.nextInt();
            System.out.print("Process id (1, 2, 3, .., n): ");
            pro[i] = in.nextInt();
        }

        System.out.print("Which process will initiate election? ");
        int ele = in.nextInt();

        elect(ele);
        System.out.println("Final coordinator is " + co);
    }

    // Implement the bully election algorithm
    // The argument is the process id that will initiate the election
    static void elect(int ele)
    {
        ele = ele-1;
        co = ele+1;
        for(int i=0;i<n;i++)
        {
            if(pro[ele]<pro[i])
            {
                System.out.println("Election message is sent from "+(ele+1)+" to "+(i+1));
                if(sta[i]==1){
                    System.out.println((i+1) + " replies to " + (ele+1));
                    elect(i+1);
                }
            }
        }
    }
}