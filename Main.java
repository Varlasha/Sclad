package sklad;

public class Main
{
    public static void main (String[] args)
    {
        Queue queue =new Queue();//������ �����
        Producer p=new Producer(queue);//��������� ��������������
        Customer cus1=new Customer(queue);//��������� 1 �����������
        Customer cus2=new Customer(queue);//��������� 2 �����������
    }
}
