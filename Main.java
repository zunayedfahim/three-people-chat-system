
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Chat chat = new Chat();
        
        new T1(chat).start();
        new T2(chat).start();
        new T3(chat).start();
    }
}


class Chat {
    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);
    Scanner sc3 = new Scanner(System.in);
    String str1,str2,str3;
    int flag = 1;

    public synchronized void Person1() throws InterruptedException {
        while(flag==2 || flag==3) {
            wait();
        }
        System.out.print("Person 1: ");
        str1 = sc1.nextLine();
        if(str1.equalsIgnoreCase("bye")) {
            System.out.println("\nPerson 1 has left the chat. Conversation ended.");
            System.exit(0);
        }
        flag = 2;
        notifyAll();
    }

    public synchronized void Person2() throws InterruptedException {
        while(flag==1 || flag==3) {
            wait();
        }
        System.out.print("Person 2: ");
        str2 = sc2.nextLine();
        if(str2.equalsIgnoreCase("bye")) {
            System.out.println("\nPerson 2 has left the chat. Conversation ended.");
            System.exit(0);
        }

        flag = 3;
        notifyAll();
    }

    public synchronized void Person3() throws InterruptedException {
        while(flag==1 || flag==2) {
            wait();
        }
        System.out.print("Person 3: ");
        str3 = sc3.nextLine();
        if(str3.equalsIgnoreCase("bye")) {
            System.out.println("\nPerson 3 has left the chat. Conversation ended.");
            System.exit(0);
        }

        flag = 1;
        notifyAll();
    }
}

class T1 extends Thread {
    Chat chat;

    public T1(Chat chat) {
        this.chat = chat;
    }


    public void run() {
        try {
            while(true) {
                chat.Person1();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class T2 extends Thread {
    Chat chat;

    public T2(Chat chat) {
        this.chat = chat;
    }


    public void run() {
        try {
            while(true) {
                chat.Person2();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class T3 extends Thread {
    Chat chat;

    public T3(Chat chat) {
        this.chat = chat;
    }


    public void run() {
        try {
            while(true) {
                chat.Person3();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

