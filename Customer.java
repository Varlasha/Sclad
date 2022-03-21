package sklad;

import java.util.Random;

public class Customer implements Runnable

{

private Queue queue;

Customer(Queue queue)//Запуск потребителей

{

this.queue = queue;

Thread customer=new Thread (this);

customer.start();

}

public void run()//Работа Потребителя

{

Random R=new Random();

try {

for (int i=0; i<9; i++){

int k=R.nextInt(queue.razmer()-1)+1;

for (int j=0; j<k; j++){

queue.get();

}

Thread.sleep(200);

}

}

catch(Exception e){}

}

}
