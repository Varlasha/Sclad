package sklad;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class Queue
{
    private static final  int SIZE =10; //������ ������
    private static  int[] queue;      //������ ������
    private static int start;
    private static int end; //��������� �� ������, ����� ������
    private ReentrantLock lock;
    private  Condition isNotFull;
    private  Condition isNotEmpty ;
    private boolean prov=true;
    public Queue()      //������ �����
    {
    	lock = new ReentrantLock();
    	isNotFull = lock.newCondition();
        isNotEmpty = lock.newCondition();
        queue=new int [SIZE];
        start=0;
        end=-1;
    }
    private int nextCycle(int pos)//����� ���������� ��������
    {
    	return (pos + 1) % SIZE;
      
    }
    private void put(int val)//��������� �������
    {
    	end=nextCycle(end);
    	queue[end]=val;
    }
    private void print() //����� ��������� ������
    {
        int i=start;
        while(i!=nextCycle(end))
        {
            System.out.print(queue[i]+" ");
            i=nextCycle(i);
        }
    }
    private boolean  full()
    {
        return(nextCycle(nextCycle(end))==start);
    } //�������� �� ������������� ������
    private boolean empty()
    {
        return(nextCycle(end)==start);
    } //�������� �� �������
    public void putCheck (int val) throws InterruptedException //��������� ������� �� �����
    {
        lock.lock();
        try
        {
            while (full())
            {
                System.out.println("Sorry, the sklad is full");
                isNotFull.await();
            }
            prov=false;
            put(val);
            System.out.println("Producer put a new product" + " "+val);
            System.out.print(" On sklad "+" ");
            print();
            System.out.println();
            isNotFull.signal();
        }
        finally
        {
            lock.unlock();
        }
    }
    public int get () throws InterruptedException //������ ������� �� ������
    {
        prov=true;
        int k;
        lock.lock();
        try
        {
            while (empty())
            {
                System.out.println("Sorry, the sklad is empty");
                isNotEmpty.await();
            }
            System.out.println("Customer get a new product "+queue[start]);
            k=queue[start];
            start=nextCycle(start);
            System.out.print("On sklad ");
            print();
            System.out.println();
            isNotEmpty.signal();
            return(k);
        }
        finally
        {
            lock.unlock();
        }
    }
    public int razmer()
    {
        return(SIZE);
    } //������ ������
    public boolean proverit () { return(prov);}//�������� �� �������/�������
}