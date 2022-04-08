import static org.junit.Assert.*;

import org.junit.Test;

public class SeamCarvingTests
{

	@Test
	public void removeSeamTest()
	{
		int[][] source = {{ 1, 2, 3, 4, 5, 6, 7},
						  {11,12,13,14,15,16,17},
						  {21,22,23,24,25,26,27},
						  {31,32,33,34,35,36,37},
						  {41,42,43,44,45,46,47},
						  {51,52,53,54,55,56,57},
						  {61,62,63,64,65,66,67},
						  {71,72,73,74,75,76,77},
						  {81,82,83,84,85,86,87},
						  {91,92,93,94,95,96,97}};
		
		int [] seam = {5,5,4,3,2,2,3,4,5,6};
		
		int[][] target = {{ 1, 2, 3, 4, 5, 7},
				  		  {11,12,13,14,15,17},
				  		  {21,22,23,24,26,27},
				  		  {31,32,33,35,36,37},
				  		  {41,42,44,45,46,47},
				  		  {51,52,54,55,56,57},
				  		  {61,62,63,65,66,67},
				  		  {71,72,73,74,76,77},
				  		  {81,82,83,84,85,87},
				  		  {91,92,93,94,95,96}};
		SeamCarvingPanel scp = new SeamCarvingPanel();
		assertArrayEquals(target, scp.removeSeam(source, seam));
	}
	@Test
	public void testCalculateEnergies()
	{
		int [][] source = {{ 1, 2, 3, 4, 5, 6, 7},
						   {17,16,15,14,13,12,11},
						   {25,25,25,25,25,25,25},
						   {33,43,33,43,53,43,33},
						   {68,61,64,69,62,64,69},
						   {73,73,70,79,72,75,74}};
		
		int [][] target = {{ 2, 2, 2, 2, 2, 2, 2},
						   { 2, 2, 2, 2, 2, 2, 2},
						   { 0, 0, 0, 0, 0, 0, 0},
						   {20,20,20,20,20,20,20},
						   {14,10, 8,12, 9, 7,10},
						   { 0, 3,12,16,10, 4, 2}};
		SeamCarvingPanel scp = new SeamCarvingPanel();
		assertArrayEquals(target,scp.calculateEnergies(source));
	}
	
	@Test
	public void testBuildChangeMap()
	{
		int[][] source1 = {{ 3, 4, 7, 8, 1},
						  { 2, 1, 5, 2, 4},
						  { 3, 4, 2, 2, 4},
						  { 3, 3, 1, 3, 1},
						  { 2, 2, 2, 4, 1},
						  { 4, 5, 3, 3, 2}};
		
		int [][][] target1 ={{{12,+1},{13, 0},{15,+1},{16, 0},{ 9,-1}},
							 {{12,+1},{ 9,+1},{11,+1},{ 8, 0},{10,-1}},
							 {{11, 0},{10,+1},{ 8, 0},{ 6,+1},{ 8, 0}},
							 {{ 8,+1},{ 8, 0},{ 6, 0},{ 6,+1},{ 4, 0}},
							 {{ 6, 0},{ 5,+1},{ 5, 0},{ 6,+1},{ 3, 0}},
							 {{ 4, 0},{ 5, 0},{ 3, 0},{ 3, 0},{ 2, 0}}};
		
		int[][] source2 = {{ 4, 2, 3, 1, 3},
						   { 3, 2, 2, 2, 4},
						   { 2, 3, 4, 3, 3},
						   { 1, 1, 3, 2, 2},
						   { 3, 2, 2, 4, 3},
						   { 2, 3, 4, 3, 3}};
		
		int [][][] target2 = {{{13,+1},{11, 0},{12,-1},{11,-1},{14,-1}},
				 			  {{10, 0},{ 9,-1},{10,-1},{11,-1},{14, 0}},
				 			  {{ 7, 0},{ 8, 0},{ 9,-1},{10, 0},{10,-1}},
				 			  {{ 5,+1},{ 5, 0},{ 7,-1},{ 7,-1},{ 8, 0}},
				 			  {{ 5, 0},{ 4,-1},{ 5,+1},{ 7, 0},{ 6, 0}},
				 			  {{ 2, 0},{ 3, 0},{ 4, 0},{ 3, 0},{ 3, 0}}};
		SeamCarvingPanel scp = new SeamCarvingPanel();
		assertArrayEquals(target1,scp.buildChangeMap(source1));
		assertArrayEquals(target2,scp.buildChangeMap(source2));
	}
	@Test
	public void testFindSeam()
	{
		int [][][] source1 ={{{12,+1},{13, 0},{15,+1},{16, 0},{ 9,-1}},
				             {{12,+1},{ 9,+1},{11,+1},{ 8, 0},{10,-1}},
				             {{11, 0},{10,+1},{ 8, 0},{ 6,+1},{ 8, 0}},
				             {{ 8,+1},{ 8, 0},{ 6, 0},{ 6,+1},{ 4, 0}},
				             {{ 6, 0},{ 5,+1},{ 5, 0},{ 6,+1},{ 3, 0}},
				             {{ 4, 0},{ 5, 0},{ 3, 0},{ 3, 0},{ 2, 0}}};
		
		
		int [][][] source2 = {{{13,+1},{11, 0},{12,-1},{11,-1},{14,-1}},
	 			              {{10, 0},{ 9,-1},{10,-1},{11,-1},{14, 0}},
	 			              {{ 7, 0},{ 8, 0},{ 9,-1},{10, 0},{10,-1}},
	 			              {{ 5,+1},{ 5, 0},{ 7,-1},{ 7,-1},{ 8, 0}},
	 			              {{ 5, 0},{ 4,-1},{ 5,+1},{ 7, 0},{ 6, 0}},
	 			              {{ 2, 0},{ 3, 0},{ 4, 0},{ 3, 0},{ 3, 0}}};
		
		int [] target1 = {4,3,3,4,4,4};
		int [] target2 = {1,1,0,0,1,0};
		SeamCarvingPanel scp = new SeamCarvingPanel();
		assertArrayEquals(target1,scp.findSeam(source1));
		assertArrayEquals(target2,scp.findSeam(source2));	
	}
}