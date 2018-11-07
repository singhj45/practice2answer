package ranges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OverlappingRanges {

	/**
	 * Returns the smallest integer that is present in the maximum number of
	 * input ranges
	 * 
	 * @param startPoints
	 *            the list of starting points for the ranges
	 * @param endPoints
	 *            the list of end points for the ranges
	 * @return the smallest integer that is present in the maximum number of
	 *         ranges
	 * @throws NoOverlapException
	 *             when there is no overlap between any of the ranges
	 */
	public static Integer maxOverlapInt(ArrayList<Integer> startPoints, ArrayList<Integer> endPoints) throws NoOverlapException {

		int size = startPoints.size();//startPoint and endPoint have same size, we need to know anyone
		Map<Integer,Integer> count = new HashMap<>();

		/**
		 * This for loop goes through every start and end pair and adds all the integers between those numbers(inclusive)
		 * To the map. If a number already exists, it increments it's value.
		 * NOTE that the initial values of integers being added is 1, so if there was no overlap of time,
		 * that hour will have it's count = 1
		 */
		for(int i=0; i<size; i++) {//First forloop, goes through every pair
			for(int j=startPoints.get(i);j<=endPoints.get(i);j++) {//Second forloop, adds all the integers to the map
				if(!count.containsKey(j))
					count.put(j,1);
				else
					count.put(j,count.get(j)+1);
			}
		}

		int max_pos =0;//The position of the maximum value, Actually this is the hour having maximum overlap
		int max_val =0;//Maximum overlap count
		for (int k : count.keySet())
		{
			if(count.get(k)>max_val){
				max_pos=k;
				max_val=count.get(k);
			}
		}
		if(max_val==1)//Remember, if max_val is 1, means there was no hour overlap
			throw new NoOverlapException();

		return max_pos;//We need to return the hour having maximum overlap and not the overlap value, I was making a mistake here by returning the overlap value

	}

}
