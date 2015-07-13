package net.obviam.opengl;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Vector;

public class DataReader
{

	private int index;
	private int istat =0;
	private float[] temp3F = new float[3];
	private float[] temp2F = new float[2];
	private int[] temp3I = new int[3];
	private int[] temp4I = new int[4];
	
	private Vector indexV = new Vector(50);
	private Vector textureV = new Vector(50);
	private Vector vertexV = new Vector(50);
	private Vector quadV = new Vector(50);
	private Vector triangleV = new Vector(50);
	private String textFile;
	private String newT;
	
	public DataReader (String filename ) {
		
		// read data files
		try {
			/////////////////////////////////////
			// use this to read from resources
			//FileInputStream fis = this.getResource().openRawResource(R.raw.house.txt);
			///////////////////////////////////
			FileInputStream fis = new FileInputStream(filename);
			Log.d ("3DDemo","opened data file");
			// set up a stream tokenizer with comments and eol`s
			StreamTokenizer fst = new StreamTokenizer (fis );

			// ignore comments
			fst.slashStarComments(true);
			fst.slashSlashComments(true);

			// endoflines matter
			fst.eolIsSignificant(true);

			// read a ine and interpret it
			istat = fst.nextToken();
			newT= fst.sval;
			
			while (istat != StreamTokenizer.TT_EOF ) {
				// texture line
				if (newT.equalsIgnoreCase("texture")) {
					// new texture- index and filename
					istat = fst.nextToken();
					index = Integer.valueOf(fst.sval);
					istat = fst.nextToken();
					textFile =fst.sval;
					textureV.add ( textFile );
				}
			
				// vertex line
				if (newT.equalsIgnoreCase("vertex")){
					// new vertex- index, 3d coords, uv coords
					istat = fst.nextToken();
					index = Integer.valueOf(fst.sval);
					// get xyz data
					for (int ijk=0;ijk<3;ijk++){
						istat=fst.nextToken();
						temp3F[ijk]=Float.valueOf(fst.sval);
					}
					// get uv data
					for (int ijk=0;ijk<2;ijk++){
						istat=fst.nextToken();
						temp2F[ijk]=Float.valueOf(fst.sval);
					}
					vertexV.add(new Vertex(temp3F,temp2F));
				}
			
				// a triangle should have 4 indices- one for texture,
				// then 3 for vertices
				if (newT.equalsIgnoreCase("triangle")){
					Log.d("3DDemo","found triangle");
					istat = fst.nextToken();
					int textI=Integer.valueOf(fst.sval);
					for(int ijk=0;ijk<3;ijk++){
						istat=fst.nextToken();
						temp3I[ijk]=Integer.valueOf(fst.sval);
					}
					triangleV.add(new Triangle(textI,temp3I));
				}
			
				// a quad should have a texture index and 
				// 4 vertex indices
				if (newT.equalsIgnoreCase("quad")){
					Log.d("3DDemo","found quad");
					istat = fst.nextToken();
					int textI=Integer.valueOf(fst.sval);
					for(int ijk=0;ijk<4;ijk++){
						istat=fst.nextToken();
						temp4I[ijk]=Integer.valueOf(fst.sval);
					}
					quadV.add(new Quad(textI,temp4I));				
				}
				istat = fst.nextToken();
				newT = fst.sval;
 			}
			
		// log problems	
		} catch (java.io.FileNotFoundException ex ) {
			Log.e ("3DDemo","no or bad 3D data file ");
		} catch (java.io.IOException ex2 ) {
			Log.e ("3DDemo","cant read 3d data file");
		} catch (NumberFormatException ex3 ) {
			Log.e ("3DDemo","Bad number format on input");
		}
	}
}
