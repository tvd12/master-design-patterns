package com.tvd12.designparttern.strategy;

public class BehaviourExample {
	public static void main(String[] args) {

		Robot r1 = new Robot("Big Robot");
		Robot r2 = new Robot("George v.2.1");
		Robot r3 = new Robot("R2");

		r1.setBehaviour(new AgressiveBehaviour());
		r2.setBehaviour(new DefensiveBehaviour());
		r3.setBehaviour(new NormalBehaviour());

		r1.move();
		r2.move();
		r3.move();

		System.out.println("\r\nNew behaviours: " + 
				"\r\n\t'Big Robot' gets really scared" + 
				"\r\n\t, 'George v.2.1' becomes really mad because" + "it's always attacked by other robots" + 
				"\r\n\t and R2 keeps its calm\r\n");

		r1.setBehaviour(new DefensiveBehaviour());
		r2.setBehaviour(new AgressiveBehaviour());

		r1.move();
		r2.move();
		r3.move();
	}
	
	private static interface IBehaviour {
		public void moveCommand();
	}

	private static class AgressiveBehaviour implements IBehaviour {

		@Override
		public void moveCommand() {
			System.out.println("\tAgressive Behaviour: if find another robot attack it");
		}

	}

	private static class DefensiveBehaviour implements IBehaviour {

		@Override
		public void moveCommand() {
			System.out.println("\tDefensive Behaviour: if find another robot run from it");
		}
	}

	private static class NormalBehaviour implements IBehaviour {

		@Override
		public void moveCommand() {
			System.out.println("\tNormal Behaviour: if find another robot ignore it");
		}
	}

	private static class Robot {
		private IBehaviour behaviour;
		private String name;
		
		public Robot(String name) {
			this.name = name;
		}

		public void setBehaviour(IBehaviour behaviour) {
			this.behaviour = behaviour;
		}

		public void move() {
			System.out.println(
					this.name + ": Based on current position" + 
					" the behaviour object decide the next move:"
			);
			
			behaviour.moveCommand();
			
			System.out.println(
					"\tThe result returned by behaviour object " + 
					"is sent to the movement mechanisms " + 
					" for the robot '" + this.name + "'"
			);
		}
	}
}
