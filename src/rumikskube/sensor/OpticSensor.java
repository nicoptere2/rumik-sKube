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
		opticSensor = new EV3ColorSensor(LocalEV3.get().getPort("S2"));

		sample = new float[3];
		colorSampler = opticSensor.getRGBMode();
		//opticSensor.setFloodlight(false);
	}
	
	public void initSensor(){
		colorSampler = opticSensor.getRGBMode();
		//opticSensor.setFloodlight(true);
		//if(opticSensor.getFloodlight() != Color.WHITE)
		//	this.opticSensor.setFloodlight(Color.WHITE);
	}
	
	public void shutdown() {
		//opticSensor.setFloodlight(false);
	}
	
	public int[] readColor() {
		colorSampler = opticSensor.getRGBMode();
		colorSampler.fetchSample(sample, 0);
		return normalizeColor(sample);
	}
	
	private int[] normalizeColor(float [] sample) {
		int[] ret = new int[3];
		for(int i=0; i<3; i++)
			ret[i] = (int)(sample[i] * 255);

		return ret;
	}
	
	public String sampleToString(float[] sample) {
		StringBuilder s = new StringBuilder();
		s.append("[");
		s.append(sample[0] + ", ");
		s.append(sample[1] + ", ");
		s.append(sample[2] + "]");
		return s.toString();
	}
}
