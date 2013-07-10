package org.etz.core;

import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.component.Dialog;

public class SMSNotifier extends Application {
	private ListeningThread _listener;

	public SMSNotifier() {

		_listener = new ListeningThread();
		_listener.start();

	}

	private static class ListeningThread extends Thread {

		public void run() {
			try {
				DatagramConnection _dc = (DatagramConnection) Connector
						.open("sms://");
				for (;;) {
					Datagram d = _dc.newDatagram(_dc.getMaximumLength());
					_dc.receive(d);
					byte[] bytes = d.getData();
					String address = d.getAddress();
					String msg = new String(bytes);
					System.out.println("Received SMS text from " + address
							+ " : " + msg);
				}

			} catch (IOException e) {
				Dialog.inform(e.getMessage());
			}
		}
	}
}
