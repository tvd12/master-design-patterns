package com.tvd12.designparttern.templatemethod;

public class PersonTemplateMethod {

	public static abstract class Person {
		public final void actionInAday() {
			wakeUp();
			haveBreakfast();
			workInMorning();
			haveLunch();
			workInAfternoon();
			haveDinner();
			personalHygiene();
			goToSleep();
		}

		protected abstract void wakeUp();

		protected abstract void haveBreakfast();

		protected abstract void workInMorning();

		protected abstract void haveLunch();

		protected abstract void workInAfternoon();

		protected abstract void haveDinner();

		protected abstract void personalHygiene();

		protected abstract void goToSleep();
	}

	public static class Asian extends Person {
		@Override
		protected void wakeUp() {
			System.out.println("wake up at 07:00 AM");
		}

		@Override
		protected void haveBreakfast() {
			System.out.println("have breakfast with noodle");
		}

		@Override
		protected void workInMorning() {
			System.out.println("work in morning about 4 hours");
		}

		@Override
		protected void haveLunch() {
			System.out.println("have lunch with rice");
		}

		@Override
		protected void workInAfternoon() {
			System.out.println("work in afternoon about 4 hours");
		}

		@Override
		protected void haveDinner() {
			System.out.println("have dinner with rice");
		}

		@Override
		protected void personalHygiene() {
			System.out.println("take a shower and brush teeth");
		}

		@Override
		protected void goToSleep() {
			System.out.println("go to sleep at 10:00 PM");
		}
	}

	public static class European extends Person {
		@Override
		protected void wakeUp() {
			System.out.println("wake up at 08:00 AM and take a shower");
		}

		@Override
		protected void haveBreakfast() {
			System.out.println("have breakfast with cereals");
		}

		@Override
		protected void workInMorning() {
			System.out.println("work in morning about 3 hours");
		}

		@Override
		protected void haveLunch() {
			System.out.println("have lunch with pizza");
		}

		@Override
		protected void workInAfternoon() {
			System.out.println("work in afternoon about 5 hours");
		}

		@Override
		protected void haveDinner() {
			System.out.println("have dinner with bread");
		}

		@Override
		protected void personalHygiene() {
			System.out.println("brush teeth");
		}

		@Override
		protected void goToSleep() {
			System.out.println("go to sleep at 11:00 PM");
		}
	}

	public static void main(String[] args) {
		Person asian = new Asian();
		Person european = new European();
		asian.actionInAday();
		european.actionInAday();
	}
}
