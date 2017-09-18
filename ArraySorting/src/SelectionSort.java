
public class SelectionSort {

	static void display(int a[], int length) {
		int tmp = 0;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (a[j] < a[i]) {
					tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}

			}

		}

	}

	public static void main(String[] args) {
		int array[] = { 2, 3, 8, 1, 49, 13 };
		System.out.println("Array before sorting");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("\nArray after sorting");
		display(array, array.length);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}

	}

}
