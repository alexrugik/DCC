package myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import com.google.gson.Gson;

public class DispatchControlCenter {

	private JChannel channel;
	private Map<Integer, Object> la = new HashMap<Integer, Object>();
	private ArrayList<Object> fullLogs = new ArrayList<Object>();
	public static final DispatchControlCenter INSTANCE = new DispatchControlCenter();

	private DispatchControlCenter() {

	}

	public void start() throws Exception {

		channel = new JChannel();
		channel.setName("ControlCenter");
		channel.setReceiver(new ReceiverAdapter() {
			@Override
			public void receive(Message m) {
				
			System.out.println(m.getObject());	
	
			if(m.src().toString().equals("helicopters")){
				Helicopter obj = new Gson().fromJson((String) m.getObject(), Helicopter.class);
				la.put(obj.getId(), obj);
				fullLogs.add(obj);
			}
				
			if(m.src().toString().equals("airplanes")){
				Airplane obj = new Gson().fromJson((String) m.getObject(), Airplane.class);
				la.put(obj.getId(), obj);
				fullLogs.add(obj);
			}
			}
		});
		
		
		channel.connect("cluster");
		
		
	}
	
	public static void main(String args[]) throws Exception {

		
		DispatchControlCenter.INSTANCE.start();

	}
	
}
