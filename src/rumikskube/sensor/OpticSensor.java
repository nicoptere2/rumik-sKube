package rumikskube.sensor;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

public class OpticSensor {
	
	private EV3ColorSensor opticSensor;
	private SampleProvider colorSampler;
	float[] sample;
	
	public OpticSensor() {
		opticSensor = new EV3ColorSensor(LocalEV3.get().getPort("S1"));
		//opticSensor.getColorID();
		
		sample = new float[6];
		colorSampler = opticSensor.getRGBMode();
		
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
	}
	
	public float[] readColor() {
		if(opticSensor.getFloodlight() != Color.WHITE)
			this.opticSensor.setFloodlight(Color.WHITE);
			
		
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		colorSampler.fetchSample(sample, 0);
		//this.opticSensor.setFloodlight(false);
		return normalizeColor(sample);
	}
	
	private float[] normalizeColor(float [] sample) {
		sample[0] *= 100;
		sample[1] *= 100;
		sample[2] *= 100;
		sample[4] *= 100;
		sample[5] *= 100;

		return sample;
	}
}
