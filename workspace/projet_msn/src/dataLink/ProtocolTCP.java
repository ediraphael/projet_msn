/**
 * 
 */
package dataLink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
<<<<<<< HEAD
=======
import java.net.DatagramPacket;
import java.net.DatagramSocket;
>>>>>>> 5f12c63e013d0450d5c06ee0d018adb09f6dfe85
import java.net.InetAddress;
import java.net.Socket;

import clientServer.ThreadListenerUDP;

/**
 * @author Mickael
 * 
 */
public class ProtocolTCP extends Protocol
{
	public Socket socket;
	public PrintWriter writer;
	public BufferedReader reader;

	public ProtocolTCP(Socket socket)
	{
		try
		{
			this.socket = socket;
			this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);
			this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (IOException e)
		{
			System.err.println("Erreur d'initialisation de Protocol, message: " + e.getMessage());
		}
	}

	public void sendMessage(String message, InetAddress adress, int port)
	{
		this.writer.println(message);
	}

	public void sendMessage(String message)
	{
		this.sendMessage(message, null, 0);
	}

	public String readMessage()
	{
		try
		{
			return this.reader.readLine();
		} catch (IOException e)
		{
			System.err.println("Erreur lecture message dans la class Protocol, message: " + e.getMessage());
		}
		return null;
	}

	public PrintWriter getWriter()
	{
		return writer;
	}

	public void setWriter(PrintWriter writer)
	{
		this.writer = writer;
	}

	public BufferedReader getReader()
	{
		return reader;
	}

	public void setReader(BufferedReader reader)
	{
		this.reader = reader;
	}
		
	public void send(String message, String ip)
	{
		InetAddress server;
		try {
			server = InetAddress.getByName(ip);
			byte buffer[] = message.getBytes("UTF-8"); 
			int length = buffer.length;			
			// System.out.println("BUFFER : " + message + " ET TAILLE : " + length);
			DatagramPacket dataSent = new DatagramPacket(buffer,length,server,ThreadListenerUDP.getPort()); 
			DatagramSocket socket = new DatagramSocket(); 
			socket.send(dataSent); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
}
