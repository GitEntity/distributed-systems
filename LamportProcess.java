import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class LamportProcess extends Thread {
    private LamportClock clock;
    private int pid;
    private List<LamportProcess> pList;
    private List<LamportTask> tasks;

    private OutPrinter print;

    private int remoteClockValue = 0;

    private static class LamportTask {
        public static final int ACT_SEND = 1;
        public static final int ACT_RECV = 2;
        public static final int ACT_LOCL = 3;
        public static final int ACTIONC = 3;
        public int action;
        public int target;
		
        public LamportTask(int action) {
			// TO DO 1: initialize
            this.action = action;
        }
        public LamportTask(int action, int target) {
            // TO DO 2: initialize
            this.action = action;
            this.target = target;
        }
    }

    private static class OutPrinter {
        public synchronized void print1(int procNum, int taskNum, int clockValue, LamportTask task) {
            System.out.printf("Process %d: Task %d: clock=%d",
                procNum, taskNum, clockValue);
			// TO DO 3: Check what task it is (task.action) and print info accordingly
			if (task.action == 1)
			{
			    System.out.println("Action is SEND");
			}
			else if(task.action == 2)
			{
			    System.out.println("Action is RECEIVE");
			}
			else // task = 3 ()
			    System.out.println("LOCAL STEP");
        }
        public synchronized void prints(String input) {
            System.out.print(input);
        }
    }

    public int GetPID() {
        // TO DO 4: return process id
        return pid;
    }

    public LamportProcess(
        OutPrinter print,
        int pid, List<LamportProcess> pList, List<LamportTask> tasks
    ) {
        this.print = print;
        this.pid = pid;
        this.pList = pList;
        this.tasks = tasks;
        this.clock = new LamportClock();
    }

    public static void main(String[] args) {
        OutPrinter printer = new OutPrinter();

        List<LamportProcess> pList = new ArrayList<LamportProcess>();


        Scanner sc=new Scanner(System.in);

        System.out.print("How many processes? ");
        int pcount = sc.nextInt();

        // Create the processes
        for (int i=0; i < pcount; i++) {
            System.out.printf("Working with process %d\n", i+1);
            List<LamportTask> tasks = makeUserTasks(sc);
            LamportProcess proc = new LamportProcess(printer, i+1, pList, tasks);
            pList.add(proc);
        }

        // Run the processes
        for (LamportProcess p : pList) {
            p.start();
        }
    }

    public static List<LamportTask> makeD2Tasks(int pid) {
        List<LamportTask> list = new ArrayList<LamportTask>();
		
		// TO DO 5: based on the pid (1, 2 or 3) , add the necessary tasks to the list
        
        return list;
    }

    public static List<LamportTask> makeUserTasks(Scanner sc) {
        List<LamportTask> list = new ArrayList<LamportTask>();       
        System.out.print("How many tasks? ");
        int ntasks = sc.nextInt();
        for (int t=0; t < ntasks; t++) {
            System.out.println("Enter 1 for SEND, 2 for RECV, 3 for LOCL");
            System.out.print("> ");
            int action = sc.nextInt();
            int target = 0;

            if (action == LamportTask.ACT_SEND) {
                System.out.println("To which process it's sending?");
				System.out.print("> ");
                target = sc.nextInt();
            }

            list.add(new LamportTask(action, target));
        }

        return list;
    }

    private static List<LamportTask> makeRandomTasks(int amount) {
        List<LamportTask> list = new ArrayList<LamportTask>();
        for (int i=0; i < amount; i++) {
            list.add(makeRandomTask());
        }
        return list;
    }

    private static LamportTask makeRandomTask() {
        int action = ThreadLocalRandom.current().nextInt(
            1,LamportTask.ACTIONC
        );
        LamportTask task = new LamportTask(action);
        return task;
    }

    public void run() {
        int taskNum = 0;
        for (LamportTask task : this.tasks) {
            taskNum++;

			// TO DO 6: check the task.action and process the tasks accordingly
            switch (task.action) {
            case LamportTask.ACT_SEND:
                this.clock.sendEvent();
                this.sendClock(task.target);
                break;
            case LamportTask.ACT_RECV:
                int clockVal = this.recvClock();
                int clockValArr[] = {clockVal};
                this.remoteClockValue = 0;
                clock.receiveEvent(0, clockVal);
                break;
            case LamportTask.ACT_LOCL:
                clock.localStep();
                break;
        }

            this.print.print1(this.pid, taskNum, this.clock.getValue(), task);
        }
    }

    private void sendClock(int rpid) {
        for (LamportProcess proc : this.pList) {
            if (proc.pid == rpid) {
                proc.onReceive(this.clock.getValue());
                return;
            }
        }
        this.print.prints(String.format("Missing process: %d\n", rpid));
    }

    private int recvClock() {
        while (this.remoteClockValue == 0) this.waitAwhile();
        return this.remoteClockValue;
    }

    public synchronized void onReceive(int clockValue) {
        while (this.remoteClockValue != 0) this.waitAwhile();
        this.remoteClockValue = clockValue;
    }

    private void waitAwhile() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            //
        }
    }
}