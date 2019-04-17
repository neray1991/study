package elevator;

import java.util.*;

public class Elevator {
	private int floors;
	private int floor;

	int sta;				//  2: Going up
							//  1: Going down
							//  0: Stop

	private int[] button;	// -2: Inside pressed, outside up
						  	// -1: Inside pressed, outside down
						  	//  0: Inside pressed only
							//  1: Outside down only
							//	2: Outside up only
							//  3: Outside up and down (inside doesn't matter)
	private Set<Integer> goes;
	Moving moving;
	
	Elevator(int f) {
		floors = f;
		floor = 1;
		sta = 0;
		button = new int[floors + 1];
		goes = new LinkedHashSet<Integer>();
		moving = new Moving();
	}
	
	private boolean check() {
		if (goes.contains(floor)) {
			if (button[floor] > 0 && button[floor] < 3 && button[floor] != sta)
				return false;
			System.out.print("Get to floor " + floor + ". ");
			System.out.println("Open door for 3s...");
			if (button[floor] < 0) 
				button[floor] = -button[floor];
			if (button[floor] > 0) button[floor] -= sta;
			if (button[floor] == 0) goes.remove(floor);

			return true;
		}
		return false;
	}
	
	class Moving extends Thread {
		public void run() {
			while (true) {	
				if (goes.size() == 0) {

					sta = 0;
					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (goes.size() > 0) {
					int goingTo = goes.iterator().next();
					while (goingTo > floor) {
						sta = 2;
						System.out.println("Moving up, floor " + floor + ".");
						try {
							sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						floor++;
						if (check()) {
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
					while (goingTo < floor) {
						sta = 1;
						System.out.println("Moving down, floor " + floor + ".");
						
						try {
							sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						floor--;
						if (check()) {
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					if (button[goingTo] != 3){
						button[goingTo] = -Math.abs(button[goingTo]);
						sta = Math.abs(button[goingTo]);
					}
					if (goes.size() > 0) {
						if (check()) {
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	public void going(int f) {
		goes.add(f);
		if (button[f] > 0 && button[f] < 3) button[f] = -button[f];
	}
	
	public void calling(int f, int dir) {
		goes.add(f);
		if (button[f] == dir || button[f] == 3) return;
		if (button[f] >= 0) button[f] += dir;
		else if (button[f] != -dir) button[f] = 3;
	}
	
	public static void main(String args[]) {
		String ss = "1r7l1";
		String[] n = ss.split("(?=r)|(?=l)");
		for (String t : n)
			System.out.println(t);
		Elevator ele = new Elevator(10);
		ele.moving.start();
		Scanner input = new Scanner(System.in);
		while (true) {
			String s = input.next();
			String a[] = s.split(",");
			if (a.length == 1)
				ele.going(Integer.parseInt(a[0]));
			else if (a.length == 2)
				ele.calling(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
		}
	}
}
