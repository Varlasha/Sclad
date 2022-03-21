package sklad;

public class Main
{
    public static void main (String[] args)
    {
        Queue queue =new Queue();//Создаём склад
        Producer p=new Producer(queue);//Запускаем производителей
        Customer cus1=new Customer(queue);//Запускаем 1 потребителя
        Customer cus2=new Customer(queue);//Запускаем 2 потребителя
    }
}
