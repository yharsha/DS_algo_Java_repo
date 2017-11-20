package practise;

import java.util.Scanner;

public class MergeSort_pract {

    public static void mergeSort(int arr[],int start,int end)
    {
        if(start>=end)return;
        
        int mid=(start+end)/2;
        mergeSort(arr,start,mid);
        mergeSort(arr,mid+1,end);
        merge(arr,start,mid,end);
    }
    public static void merge(int arr[],int start,int mid,int end)
    {
        int [] tempArr = new int[end-start+1];
        int s1=start,e1=mid,s2=mid+1,e2=end;
        int index=0;
        
        while(s1<=e1 && s2<=e2 && index<tempArr.length)
        {
            if(arr[s1]<arr[s2])
            {
                tempArr[index]=arr[s1];
                s1++;
                index++;
            }
            else
            {
                  tempArr[index]=arr[s2];
                s2++;
                index++;
            }
        }
        //remaining
        while(s1<=e1)
        {
                tempArr[index]=arr[s1];
                s1++;
                index++;
        }
        
              while(s2<=e2)
        {
                tempArr[index]=arr[s2];
                s2++;
                index++;
        }
        int si=start;
        for(int k=0;k<tempArr.length && si<=end;k++,si++)
        {
            arr[si]=tempArr[k];
        }
        
    }
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
*/
        //Scanner
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int arr[]= new int[N];
        for (int i = 0; i < N; i++) {
            arr[i]=s.nextInt();
        }
        mergeSort(arr,0,N-1);
        System.out.println("MergeSort...");
             for (int j = 0; j < N; j++) {
                   System.out.println(arr[j]+" ");
        }

      
    }
}
