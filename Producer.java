package sklad;

import java.util.Random;

public class Producer implements Runnable

{

private int pr=0;

private Queue queue;

Producer(Queue queue)//Запуск Производителя

{

this.queue = queue;

Thread producer=new Thread(this);

producer.start();

}

public void run()//Работа Производителя

{

Random R=new Random();

try {

for (int i=0; i<9; i++)

{

if (queue.proverit())

{pr+=1;}

int k=R.nextInt(queue.razmer()-1)+1;

for (int j=0; j<k; j++)

{

queue.putCheck(pr);

}

Thread.sleep(200);

}

}

catch(Exception e)

{

}

}

}
