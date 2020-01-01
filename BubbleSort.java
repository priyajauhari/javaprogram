/**
 * Sorting Technique-
 * Repeatedly swapping the adjacent elements if they are in wrong order.
 *
 * Time Complexity
 * Best Case = Average Case = Worst Case = O(n^2)
 *
 * Optimized Bubble Sort -
 * Time Complexity
 * Best Case = O(n)
 * Average Case = O(n^2)
 * Worst Case = O(n^2)
 */

import java.util.Arrays;

public class BubbleSort {
    public static void main(String args[]){
        int arr[] = {4, 3, 2, 1, 0};
        //int arr[] = {0, 1, 2, 3, 4};
        System.out.println("Given Array :- "+ Arrays.toString(arr));
        sort(arr);
        System.out.println("Sorted Array :- "+ Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        int arrLength = arr.length;
        boolean sortFlag = false;
        for(int i = 1; i < arrLength; i++ ){
            for(int j = 0; j < arrLength-i; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    sortFlag = true;
                }
            }
            if(!sortFlag)
                break;
        }
    }
}