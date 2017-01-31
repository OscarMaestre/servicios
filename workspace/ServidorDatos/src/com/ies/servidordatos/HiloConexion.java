package com.ies.servidordatos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable{

	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;
	public HiloConexion(Socket socket){
		this.socket=socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
