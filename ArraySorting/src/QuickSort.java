
public class QuickSort {

	public static void quickSort(int a[], int start, int end) {
		if (start < end) {
			int pIndex = partition(a, start, end);
			quickSort(a, start, pIndex - 1);
			quickSort(a, pIndex + 1, end);
		}

	}

	public static int partition(int a[], int start, int end) {
		int tmp = 0;
		int tmp2 = 0;
		int pivot = a[end];
		int pIndex = start; // pIndex is that element from where we brake array
							// for further sorting
		for (int i = start; i < end; i++) {
			if (a[i] <= pivot) // take a pIndex and check all element of array
								// with pivot .if any element is less than or
								// equal to pivot then replace it with pIndex
								// and then check for next if find then replace
								// it with pIndex then finally replace pIndex
								// wiht pivot and return pIndex for iteration
			{
				tmp = a[pIndex];
				a[pIndex] = a[i];
				a[i] = tmp;
				pIndex++;
			}
		}
		tmp2 = a[pIndex];
		a[pIndex] = a[end];
		a[end] = tmp2;
		return pIndex;
	}

	public static void main(String args[]) {
		int array[] = { 55, 12, 45, 89, 34, 15 };
		System.out.println("Array before sorting");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("\nArray after sorting");
		quickSort(array, 0, array.length - 1);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}
