package practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HelloWorld {

	int a = 10;
	int b = 2;
	int c = 5;
	{
		c = a + b;
	}

	private static void printMergedSortedArrays(int[] arr1, int[] arr2){
        int m=arr1.length,n=arr2.length;
        int[] result = new int[m+n];
        int i=0,j=0,k=0;
        while(i<m && j<n){
            if(arr1[i]<arr2[j]){
                result[k++]=arr1[i++];
            }else{
                result[k++]=arr2[j++];
            }
        }
        while(i<m){
            result[k++]=arr1[i++];
        }
        while(j<n){
            result[k++]=arr2[j++];
        }

        for(int p=0;p<m+n;p++){
            System.out.print(result[p]+" ");
        }

    }
	
	
	//print yes if an array has unique elements
	// 1 2 3 4 5 6 3
	private static boolean isArrayUnique(int[] arr) {
		Set<Integer> arrSet = new HashSet<>();
		for(int i=0;i<arr.length;i++) {
			if(!arrSet.contains(arr[i])) {
				arrSet.add(arr[i]);
			}else {
				return false;
			}
		}
		return true;
	}
	
	
	
	private static int maxFreq(String str) {
		if(str.length()==0)
			return 0;
		int max=1;
		Map<Character,Integer> freqMap = new HashMap<>();
		for(Character ch : str.toCharArray()) {
			if(!freqMap.containsKey(ch)) {
				freqMap.put(ch, 1);
			}else {
				int currFreq=freqMap.get(ch);
				max=Math.max(max, currFreq+1);
				freqMap.put(ch,currFreq+1);
			}
		}
		return max;
	}
	
    public static void main (String[] args) {
                      // Your code here
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        
        int[] arr1=new int[n];
        int[] arr2=new int[m]; 
        
        for(int i=0;i<n;i++){
            arr1[i]=sc.nextInt();
        }

        for(int i=0;i<m;i++){
            arr2[i]=sc.nextInt();
        }

        printMergedSortedArrays(arr1,arr2);

    }
}
