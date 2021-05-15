package com.tvd12.designparttern.strategy;

public class BehaviourExampleVi {
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

		System.out.println("\r\nCác hành động tiếp theo: " + 
				"\r\n\t'Big Robot' đã cảm thấy sợ hãi vì nó yếu hơn và không đủ sức tấn công nữa" + 
				"\r\n\t'George v.2.1' cảm thấy phát điên sau khi nạp đủ năng lượng" + 
				"\r\n\tvà 'R2' ở một vị trí rất xa nên không vấn đề gì\r\n");

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
			System.out.println("\tTấn công: nếu tìm thấy bất cứ mục tiêu nào");
		}

	}

	private static class DefensiveBehaviour implements IBehaviour {

		@Override
		public void moveCommand() {
			System.out.println("\tPhòng thủ: Nếu có bất kì mối nguy hiểm nào");
		}
	}

	private static class NormalBehaviour implements IBehaviour {

		@Override
		public void moveCommand() {
			System.out.println("\tTiếp tục di chuyển: nếu không có bất kì mối nguy hiểm nào");
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
					this.name + ": Dựa trên hoàn cảnh hiện tại," + 
					" chiến thuật tiếp theo sẽ là:"
			);
			
			behaviour.moveCommand();
			
			System.out.println("\tDành cho robot: '" + this.name + "'");
		}
	}
}
