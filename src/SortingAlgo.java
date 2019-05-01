public class SortingAlgo {
    public static void main(String[] args) {
//        new SortingAlgo().bubbleSort();
//        new SortingAlgo().selectionSort();
//        new SortingAlgo().insertionSort();
        new SortingAlgo().shellSort();

    }

    private void swap (int[] array,int i,int j){
        if(i==j) return;

        int temp = array[i];
        array[i]=array[j];
        array[j] = temp;
    }

    private void print(int[] arr){
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }

    public void selectionSort() {
        int[] unsorted = {22, 1, 4, 2, 44};

        for (int unsortedMaxIndex = unsorted.length - 1; unsortedMaxIndex > 0; unsortedMaxIndex--) {
            int largest = 0;
            for (int i = 1; i <= unsortedMaxIndex; i++) {
                if (unsorted[i] > unsorted[largest]) {
                   largest = i;
                }
            }
            swap(unsorted,largest,unsortedMaxIndex);
        }
        print(unsorted);
    }

    public void bubbleSort() {
        int[] unsorted = {22, 1, 4, -2, 44};
        for (int unsortedMaxIndex = unsorted.length - 1; unsortedMaxIndex > 0; unsortedMaxIndex--) {
            for (int i = 0; i < unsortedMaxIndex; i++) {
                if (unsorted[i] > unsorted[unsortedMaxIndex]) {
                    swap(unsorted,i,unsortedMaxIndex);
                }
            }

        }
        print(unsorted);

    }

    public void insertionSort(int[] arr){

        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0;j--){
               if(arr[j]<arr[j-1]){
                   swap(arr,j-1,j);
               }else break;
            }
        }

        print(arr);
    }

    public void shellSort(){
        int [] arr = {22,1,4,-2,44};

        for(int gap = arr.length/2; gap >0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                if(arr[i]<arr[i-gap]){
                    swap(arr,i,i-gap);
                }
            }
        }
        insertionSort(arr);

    }

}
