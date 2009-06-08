package interpret;

import getNextPCode.*;

public class Interpreter {
	public static void payBack() {
		PCodeStructure pcode;
		pcode = GetNext.payBack();
		System.out.println("----------------------------------------PCode的解释执行----------------------------------------");

		int i = 0, j = -1;
		int array[][] = new int[100][100];
		int declare[][] = new int[100][100];
		int m = Integer.parseInt(pcode.getA());
		pcode.getF();
		while (!pcode.getA().equals("END")) {
			if (pcode.getF().equals("JMP")) {
				pcode = PCodeStructure.getPCodeByNO(Integer.parseInt(pcode
						.getA()));
			}
			if (pcode.getF().equals("LIT")) {

				// if(PCodeStructure.getPCodeByNO(pcode.getNum() +
				// 1).getF().equals("WRT")){
				// System.out.print(temp);
				// pcode =
				// PCodeStructure.getPCodeByNO(PCodeStructure.getPCodeByNO(pcode.getNum()
				// + 1).getNum() + 1);
				// continue;
				// }

				
				/*
				 * 这个地方有问题 因为"n="不可以转化为整数
				 */
				/*
				 * 判断字符串是不是全由数字组成
				 */
				String str1 = pcode.getA();
				int flag = 1;
				for (int q = 0; q < str1.length(); q++) {
					if (!(str1.charAt(q) >= '0' && str1.charAt(q) <= '9')) {
						flag = 0;

					}
				}
				if (flag == 1) {
					j++;
					array[i][j] = Integer.parseInt(pcode.getA());
				}
				else{
//					pcode = GetNext.payBack();
					System.out.print(str1);
					pcode = PCodeStructure.getPCodeByNO(pcode.getNum() + 1);
				}
				pcode = GetNext.payBack();

			}

			if (pcode.getF().equals("OPR")) {
				array[i][j] = array[i][j] + array[i][j - 1];
				pcode = PCodeStructure.getPCodeByNO(pcode.getNum() + 1);
			}

			if (pcode.getF().equals("STO")) {
				declare[pcode.getL()][Integer.parseInt(pcode.getA()) - 1] = array[i][j];
				pcode = PCodeStructure.getPCodeByNO(pcode.getNum() + 1);
			}

			if (pcode.getF().equals("WRT")) {
				System.out.print(array[i][j]);
				pcode = PCodeStructure.getPCodeByNO(pcode.getNum() + 1);
			}

			if (pcode.getF().equals("LOD")) {
				j++;
				array[i][j] = declare[pcode.getL()][Integer.parseInt(pcode
						.getA()) - 1];
				pcode=GetNext.payBack();
			}

		}
		
		System.out.println("\n\n----------------------------------------PCode的解释执行----------------------------------------");
	}

	// public static void main(String args[]) {
	// Interpreter.payBack();
	// }
}
