
public class MergeSort {

	public static int[] mergeSort(int[] array)
	{
		if (array.length<1)
		{
			return array ;
		}
		int midpoint=array.length/2;
		int left[]=new int[midpoint];
		int right[];
		if (array.length%2==0)
		{
			right=new int[midpoint];
		}
		else
			right=new int [midpoint+1];
		for(int i=0;i<=midpoint;i++)
		{
			left[i]=array[i];
			
		}
		int x=0;
		for(int j=midpoint;j<array.length;j++)
		{
			if (x<right.length)
			{
				right[x]=array[j];
				x++;
			}
		}
		int result[]=new int[array.length];
		left=mergeSort(left);
		right=mergeSort(right);
		result=merge(left,right);
		return result;
		
	}
	
	public static int[] merge(int left[],int right[])
	{
		int resultlength=left.length+right.length;
		int resultarray[]=new int[resultlength];
		int indexLeft=0;
		int indexRight=0;
		int indexResult=0;
		while(indexLeft<left.length||indexRight<right.length)
		{
			if(indexLeft<left.length&&indexRight<right.length)
			{
				if(left[indexLeft]<=right[indexRight])
				{
					resultarray[indexResult]=left[indexLeft];
					indexLeft++;
					indexResult++;
				}
				else {
					resultarray[indexResult]=right[indexRight];
					indexRight++;
					indexResult++;
				}
			}
		
		}
		
		return resultarray;
		
	}
	
	public static void main(String arg[])
	{
		int array[]={12,1,34,6,3,23};
		System.out.println("array before sorting");
		for (int i=0;i<=array.length-1;i++)
		{
			System.out.print(array[i]+" ");
			
		}
		
		new MergeSort().mergeSort(array);
		
		System.out.println("array after sorting");
		for (int i=0;i<=array.length-1;i++)
		{
			System.out.print(array[i]+" ");
			
		}
		
	}
	
}
