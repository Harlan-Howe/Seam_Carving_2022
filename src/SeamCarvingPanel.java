import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;

public class SeamCarvingPanel extends JPanel
{
	private BufferedImage source1;
	private BufferedImage startImage;
	private BufferedImage energyImage;
	private BufferedImage reducedImage;
	private int [][] energyArray;
	private int [] theSeam;
	
	SeamCarvingPanel()
	{
		super();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (source1 == null)
		{
			g.setColor(Color.RED);
			g.drawString("No image loaded", 50, 50);
			return;
		}
		int w = source1.getWidth();
		int h = source1.getHeight();
		g.drawImage(source1, 0, 0, null);
		g.drawImage(startImage, 0, h, null);
		g.drawImage(energyImage, w, 0, null);
		g.drawImage(reducedImage, w, h, null);
		
		if (theSeam != null)
		{
			g.setColor(Color.RED);
			for (int i=0; i<h; i++)
				g.fillRect(w+theSeam[i], i, 1, 1);
		}
		
		g.setColor(Color.BLACK);
		g.drawString("Original", 5,   12);
		g.drawString("Energy",   w+5, 12);
		g.drawString("Start",    5,   h+12);
		g.drawString("Reduced",  w+5, h+12);
		g.setColor(Color.WHITE);
		g.drawString("Original", 4,   11);
		g.drawString("Energy",   w+4, 11);
		g.drawString("Start",    4,   h+11);
		g.drawString("Reduced",  w+4, h+11);
	}
	
	public void loadImage(File theFile)
	{
		source1 = ImageManager.loadImage(theFile);
		startImage = ImageManager.deepCopy(source1);
	    energyImage = ImageManager.createBufferedImageOfSize(source1.getWidth(), source1.getHeight());
	    reducedImage = ImageManager.createBufferedImageOfSize(source1.getWidth(), source1.getHeight());
	    repaint();
	}
	
	public void doCalculateEnergy()
	{
		int [][] startArray = ImageManager.grayscaleArrayFromImage(startImage);
		energyArray = calculateEnergies(startArray);
		energyImage = ImageManager.ImageFromArray(normalizeArrayTo255(energyArray));
		repaint();
	}
	
	public void doFindSeam()
	{
		int[][][] changeMap = buildChangeMap(energyArray);
		theSeam = findSeam(changeMap);
		repaint();
	}
	
	public void doShrink()
	{
		int[][] shrunkArray = removeSeam(ImageManager.grayscaleArrayFromImage(startImage),theSeam);
		reducedImage = ImageManager.ImageFromArray(shrunkArray);
		repaint();
	}
	
	public void doReducedToStart()
	{
		startImage=reducedImage;
		repaint();
	}
	
	public void doShrinkCycle()
	{
		doCalculateEnergy();
		doFindSeam();
		doShrink();
		doReducedToStart();
	}
	
	public void doShrinkCycle(int N)
	{
		for (int i=0; i<N; i++)
			doShrinkCycle();
	}
	
	/**
	 * Finds the level of change associated with this pixel. There are several ways you could do this, but
	 * in this case we will use one that finds the absolute value difference between each pixel and the one 
	 * to the right of it plus the absolute value of the difference between it and the one to the left.
	 * (In the case of left/right boundary pixels, double the difference from the one neighbor it does have.)
	 * @param source - an array of pixels
	 * @return result - an array of pixels the same size.
	 */
	public int[][] calculateEnergies(int [][] source)
	{
		int[][] result = null;
		// HINT: I strongly recommend you go to the bottom of this file and do NormalizeArrayTo255 first.
		//TODO: Add your code here.
		

		//-----------------------------
		return result;
	}
	
	public boolean pixelInBounds(int r, int c, int[][] array)
	{
		return r>-1 && r<array.length && c>-1 && c<array[0].length;
	}
	
	
	/**
	 * starting at the highest row and working backwards
	 * @param source
	 * @return
	 */
	public int[][][] buildChangeMap(int[][] source)
	{
		int num_rows = source.length;
		int num_cols = source[0].length;
		int[][][] result = new int[num_rows][num_cols][2];   //[*][*][0] will represent lowest cost to this point.
															 //[*][*][1] will represent direction to take (-1,0,1)
		int left, center, right;
		/*
		 * 1) copy highest-numbered row from source into result[*][*][0] for highest numbered row. Set the 
		 *     result[*][*][1] value for this row to zero.
		 * 2) loop from the second-highest row down to zero:
		 *     3) loop through columns across this row:
		 *     		4) for each cell, look at the cells one (below & left), directly below, and one (below & right) 
		 *             to find the lowest value. (Treat out of bounds locations as very high numbers.) Figure out
		 *             which has the lowest path-so-far (result[*][*][0]) value. 
		 *          5) Take that value, plus the source value for this cell and put them into result[*][*][0] for 
		 *             this cell.
		 *          6) Based on which cell you picked, put -1, 0, or +1 (left/center/right) into result[*][*][1] for
		 *             this cell. (Note: In case of tie, "center" beats "right." "Right" beats "left.")
		 * See https://en.wikipedia.org/wiki/Seam_carving#Dynamic_Programming for more detail.
		 */
		// TODO: Add your code here.
//		 1) copy highest-numbered row from source into result[*][*][0] for highest numbered row. Set the 
//		      result[*][*][1] value for this row to zero.
		
		
		
		
//		 2) loop from the second-highest row down to zero:
		
		
		
//		      3) loop through columns across this row:

		
		
//		      	   4) for each cell, look at the cells one (below & left), directly below, and one (below & right) 
//		              to find the lowest value. (Treat out of bounds locations as very high numbers.) Figure out
//		              which has the lowest path-so-far (result[*][*][0]) value. 
		
		
//		           5) Take that value, plus the source value for this cell and put them into result[*][*][0] for 
//		              this cell.
		
		
//		           6) Based on which cell you picked, put -1, 0, or +1 (left/center/right) into result[*][*][1] for
//		              this cell. (Note: In case of tie, "center" beats "right." "Right" beats "left.")
		
		
		//--------------------------
		return result;
	}
	/**
	 * starts with the lowest value in row 0 and then follows the path in changeMap[*][*][1]
	 * to generate a list of columns (one for each row).
	 * @param changeMap
	 * @return
	 */
	public int[] findSeam(int[][][] changeMap)
	{
		int num_rows = changeMap.length;
		int num_cols = changeMap[0].length;
		
		int[] result = new int[num_rows];
		// find the column of the lowest value in changeMap[0][*][0].
		// then use the (-1, 0, +1) values in each of changeMap[*][*][1] to figure out what the
		// column value should be for the next row.
		// Use this to build up a list of column numbers, one for each row.
		// TODO: Insert your code here.
		
		
		
		
		//--------------------------------	
		return result;
	}
	/**
	 * all pixels in each row of source are copied into result, except 
	 * for the one whose index is in seam for that row; all later values
	 * in the row are shifted to the left by one.
	 * 
	 * @param source - an P x Q grid of 8-bit ints.
	 * @param seam - an array of P indices, each of which is less than Q.
	 * @return an P x (Q-1) grid of 8-bit ints, a copy of source with the seam removed.
	 */
	public int[][] removeSeam(int[][] source, int[] seam)
	{
		int[][] result = new int[source.length][source[0].length-1];
		//TODO: write your code here.
		
		
		
		
		//--------------------------
		return result;
	}

	/**
	 * multiplies every value in the unnormalized array by the same amount, so that the largest
	 * value in the resulting array is 255.
	 * @param unnormalized -a 2-D array of integers, not all of which are zero.
	 * @return - a 2-D array of integers of the same dimensions, proportional to the original array,
	 *           but with a maximum of 255.
	 * postcondition: the original, unnormalized array is unchanged.
	 * Note: this is shared with the Hough project, so feel free to copy/paste.
	 */
	public int[][] normalizeArrayTo255(int[][] unnormalized)
	{
		int max =0;
		//TODO: Find the maximum value in the array.
		
		// Note: this is shared with the Hough project, so feel free to copy/paste.
		
		//-----------------------------------------
		if (max == 0)
			throw new RuntimeException("Could not normalize the array to 0 to 255; array was empty.");
		int [][] normalized = new int[unnormalized.length][unnormalized[0].length];
		//TODO: set every element of "normalized" to the "unnormalized" times 255 and divided by the max.
		// note: integer math makes a difference here. Get the right order.
			
		
		
		//
		return normalized;
	}
	
	
}
