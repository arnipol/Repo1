package Training;

import java.util.List;
import java.util.Random;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;

public class MainProgram {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello, this my first program");
		System.out.println(String.format("hello, this my %d program", 1));
		System.out.println(MessageFormat.format("heelo, this is {0} program.", 1));
		TestList();
	}


	private static void TestList()
	{
		List<Cars> myList = new ArrayList<>();
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for (int i=1; i<10; i++)
		{
			Integer id = rand.nextInt(99);
			String name = "Car_" + ("0" + String.valueOf(id)).substring((id>9)?1:0,(id>9)?3:2);
			Integer power = 70 + rand.nextInt(250);
			myList.add(new Cars(name, power));
		}
//		String a = "a";
//		String b = "b";

		System.out.println(" not sorted:");
		myList.forEach((x)->System.out.println(x.toString()));
		Collections.sort(myList, (x,y)->x.getName().compareTo(y.getName()));
		Collections.sort(myList, (x,y)->x.getPower().compareTo(y.getPower()));
		System.out.println("sorted:");
		myList.forEach((x)->System.out.println(x.toString()));

		/*
		Thread[] threads = new Thread[10]; //Runnable[10];
		for (int i=0; i<10; i++) {
			threads[i] = new Thread(new MyRun(i));
			threads[i].start();
		}*/

		JoisonTest jtest = new JoisonTest();
		jtest.Deserializable();

		//System.out.println(Test.SwapString("koza"));

	}
}
