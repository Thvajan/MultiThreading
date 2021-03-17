package me.learning.multithreading.threadcommunication.semaphores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		ArrayList strings = new ArrayList();
		strings.add("aAaa");
		strings.add("AaA");
		strings.add("aAA");
		strings.add("AaAaa");
		Collections.sort(strings);
	}
}
