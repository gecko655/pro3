package pro3.ex6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TaskPatternGenerator {

	public static void makePattern(int numOfTasks, String f) {
		int taskCount = 0;
		try (PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
			long prepareTime;
			long taskWeight;

			while (taskCount++ < numOfTasks) {
				prepareTime = Math.round((Math.random() * 50));
				taskWeight = Math.round((Math.random() * 50)) * 5;
				fout.println(prepareTime + "," + taskWeight);
			}
			System.out.println("Successfully Generated : " + f);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void makePatternFixWeight(int numOfTasks, int taskWeight, String f) {
		int taskCount = 0;
		try (PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
			long prepareTime;
			//long taskWeight;

			while (taskCount++ < numOfTasks) {
				prepareTime = Math.round((Math.random() * 50));
				//taskWeight = 50 * 5;
				fout.println(prepareTime + "," + taskWeight);
			}
			System.out.println("Successfully Generated : " + f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void makePatternFixWeightFixInterval(int numOfTasks, int taskWeight, int prepareTime, String f) {
		int taskCount = 0;
		try (PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
			//long prepareTime;
			//long taskWeight;

			while (taskCount++ < numOfTasks) {
				//prepareTime = Math.round((Math.random() * 50));
				//taskWeight = 50 * 5;
				fout.println(prepareTime + "," + taskWeight);
			}
			System.out.println("Successfully Generated :" + f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//makePattern(1000, "pattern1.txt");
		//makePatternFixWeight(1000, 50 * 5, "pattern2.txt");
		//makePatternFixWeight(1000, 10 * 5, "pattern3.txt");
		//makePatternFixWeightFixInterval(1000, 10 * 5, 10 * 5, "pattern4.txt");
		makePatternFixWeightFixInterval(500, 10000, 10, "pattern5.txt");
	}

}
