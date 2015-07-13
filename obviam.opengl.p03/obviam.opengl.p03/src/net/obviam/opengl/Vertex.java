package net.obviam.opengl;

public class Vertex
{
	//holds data for a vertex
	// this basically a struct
	// PMB 11/21/12
	private float[] Pos = new float[3];
	private float[] Uv = new float[2];
	
	public Vertex( float[] posIn, float[] uvIn ) {
		for (int ijk=0;ijk<3;ijk++){
			Pos[ijk]=posIn[ijk];
		}
		Uv[0]=uvIn[0];
		Uv[1]=uvIn[1];
	}
	// setters and getters
	public void setPos( float[]posIn){
		for (int ijk=0;ijk<3;ijk++){
			Pos[ijk]=posIn[ijk];
		}		
	}
	public void setUv( float[]uvIn){
		Uv[0]=uvIn[0];
		Uv[1]=uvIn[1];
	}
	
	// getters
	public float[] getPos(){
		float[] posOut = new float[3];
		for (int ijk=0;ijk<3;ijk++){
			posOut[ijk]=Pos[ijk];
		}		
		return posOut;
	}
	public float[] getUv(){
		float[] uvOut = new float[3];
		uvOut[0]=Uv[0];
		uvOut[1]=Uv[1];
		return uvOut;
	}
}
