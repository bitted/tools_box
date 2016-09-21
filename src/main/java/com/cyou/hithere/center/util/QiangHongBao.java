package com.cyou.hithere.center.util;

import java.util.Scanner;

/*著作权归作者所有。
 商业转载请联系作者获得授权，非商业转载请注明出处。
 作者：汪阳
 链接：http://www.zhihu.com/question/22625187/answer/40500915
 来源：知乎*/

public class QiangHongBao {

	public static void main(String[] args)

	{

		int num = 0;// 红包的个数

		double cash = 0;// 总钱数

		int pin = 0;// 判断是否拼手气的临时变量

		int MaxNo = 0;// 记录最大值的位置

		int MinNo = 0;// 记录最小值的位置

		while (true)

		{

			System.out.println("1.请问要发多少个红包？");

			Scanner number = new Scanner(System.in);

			num = number.nextInt();// 读取用户输入，并存入num变量

			if (num == 0)// 如果用户输入了0

			{

				System.out.println("输入错误，请输入大于0的整数！" + "\n" + "请重新输入！" + "\n");// 返回警告

				// System.exit(0);//结束程序

			}

			else

				break;

		}

		System.out.println();

		double[] a = new double[num];// 定义一个double型的有num个元素的数组

		while (true)

		{

			System.out.println("2.请问要发多少钱？");

			Scanner coin = new Scanner(System.in);

			cash = coin.nextDouble();// 读取用户输入，并存入cash变量

			if ((100 * cash) % 1 != 0)// 如果用户输入了三位或更多位的小数

			{

				System.out.println("输入错误，请输入最多保留两位的小数" + "\n" + "请重新输入！" + "\n");// 返回警告

				// System.exit(0);//结束程序

			}

			else if ((cash / num) < 0.01)// 如果每个红包平均分到的金额小于0.01元

			{

				System.out.println("金额错误，单个红包金额不可小于0.01元，请调整金额和红包数!" + "\n" + "请重新输入！" + "\n");// 返回警告

				// System.exit(0);//结束程序

			} else if ((cash / num) > 200000)// 如果每个红包平均分到的金额大于200元

			{

				System.out.println("金额错误，单个红包金额不可大于200元，请调整金额和红包数！" + "\n" + "请重新输入！" + "\n");// 返回警告

				// System.exit(0);//结束程序

			}

			else

				break;

		}

		System.out.println();

		while (true)

		{

			System.out.println("是否设定为拼手气红包？(请输“1”或“2”,1代表是,2代表否)");

			Scanner pi = new Scanner(System.in);

			pin = Integer.parseInt(pi.nextLine());// 读取用户输入，并存入pin变量

			if (pin == 0)

				System.out.println("选择错误，请选择1或2！" + "\n" + "请重新输入！" + "\n");// 返回警告
			else if (pin > 2)
				System.out.println("选择错误，请选择1或2！" + "\n" + "请重新输入！" + "\n");// 返回警告
			else
				break;

		}

		if (pin == 1)

		{

			System.out.println("\n正在生成总金额为" + cash + "元的" + num + "个拼手气红包");

			pinshouqiArray(a, cash);

			 printArray(a);

			double sum2 = Math.round(sum(a) * 100);

			// System.out.println(sum2/100);

			int cash1 = (int) (cash * 100);

			int sum1 = (int) (sum2);

			int resi = cash1 - sum1;

			for (int i = 0; i < resi; i++)

			{

				a[(int) (Math.floor(a.length * Math.random()))] += 0.01;

				sishewuru(a);

			}

			printArray(a);

			double sum3 = Math.round(sum(a) * 100);

			System.out.println("共发出：" + sum3 / 100 + "元");

			MaxNo = Max(a);

			MinNo = Min(a, cash);

			if (MaxNo == MinNo)

				System.out.println("大家拿的都一样╮(╯▽╰)╭");

			else

			{

				System.out.println("第" + MaxNo + "位童鞋手气最佳！");// 显示手气最佳的人

				System.out.println("第" + MinNo + "位童鞋请再接再厉！");// 显示手气最差的人

			}

		}
		else if (pin == 2)
		{
			System.out.println("\n正在生成总金额为" + cash + "元的" + num + "个普通红包");
			if (cash * 100 / num != 1)
			{
				System.out.println("金额不能平均分配给每个红包，程序结束");
				System.exit(0);
			}
			else
			{
				a = putongArray(a, cash);// 普通红包，存入数组
				printArray(a);// 打印每个红包到屏幕
				double sum3 = Math.round(sum(a) * 100);
				System.out.println("总金额：" + sum3 / 100);
			}
		}
	}
	public static double[] initial(double[] a)
	{
		for (int i = 0; i <= a.length - 1; i++)
			a[i] = 0.01;
		return a;
	}
	public static void printArray(double[] a)// 方法：打印数组到屏幕
	{
		System.out.print("结果:" + "\n");
		for (int i = 0; i <= a.length - 1; i++)
			System.out.println("第" + (i + 1) + "个红包里面有" + a[i] + "元");
	}
	public static double[] baoliu(double[] a)// 保留两位小数，不要四舍五入
	{
		double b = 0;
		double c = 0;
		for (int i = 0; i < a.length; i++) {
			b = a[i];
			c = (Math.floor(100 * b));// 向下取整
			c /= 100;
			a[i] = c;
		}
		return a;
	}

	public static double[] arraygenerate(double[] a)// 产生随机数组
	{
		for (int i = 0; i <= a.length - 1; i++) {
			a[i] = Math.random();
			a = baoliu(a);
		}
		return a;
	}

	public static double[] pinshouqiArray(double[] a, double cash)

	{
		arraygenerate(a);// 随机给数组中的每个元素分配一个0～1之间的小数
		double sum = 0;// 定义变量
		sum = sum(a);
		for (int i = 0; i <= a.length - 1; i++) {
			a[i] *= ((cash - a.length * 0.01) / sum);
			a[i] += 0.01;
		}
		baoliu(a);
		return a;// 返回数组
	}

	public static double[] sishewuru(double[] a)// 保留两位小数，四舍五入
	{
		double b = 0;
		double c = 0;
		for (int i = 0; i < a.length; i++) {
			b = a[i];
			c = Math.round(100 * b);// 向下取整
			c /= 100;
			a[i] = c;
		}
		return a;
	}

	public static double sum(double[] a) {
		double sum = 0;
		for (int i = 0; i <= a.length - 1; i++)
			sum += a[i];// 算出所有元素的总和
		return sum;
	}

	public static double[] putongArray(double[] a, double cash)// 普通红包
	{
		for (int i = 0; i <= a.length - 1; i++) {
			double temp = 0;
			temp = Math.floor(cash * 10);// 向下取整，为避免系统自动四舍五入导致超额的情况
			temp /= 10;
			a[i] = temp / a.length;// 取平均值
		}
		return a;// 返回数组
	}

	public static int Max(double[] a)// 求最大值的序号
	{
		double max = 0;
		int recmax = 0;
		for (int i = 0; i <= a.length - 1; i++) {
			if (a[i] > max) {
				max = a[i];
				recmax = i + 1;
			}
		}
		return recmax;// 返回最大值的序号
	}

	public static int Min(double[] a, double cash)// 求最小值的序号
	{
		double min = cash + 1;
		int recmin = 0;
		for (int i = 0; i <= a.length - 1; i++) {
			if (a[i] < min) {
				min = a[i];
				recmin = i + 1;
			}
		}
		return recmin;// 返回最小值的序号

	}
}