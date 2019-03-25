package br.com.sbk.sbking.learning;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CapitalizeClient {
	Socket socket;

	public void connect() throws IOException {
		this.socket = new Socket("localhost", 60000);
	}

	public String capitalize(String text) throws IOException {
		System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
        Scanner scanner = new Scanner(text);
        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(scanner.nextLine());
		return (in.nextLine());

	}
}
