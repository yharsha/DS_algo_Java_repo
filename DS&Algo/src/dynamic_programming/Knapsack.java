package dynamic_programming;

public class Knapsack {

	  /**
    public static int knapsack_recursive(int w,int value[],int weight[],int n)
    {
        
        if(w==0 || n==0){return 0;}
        //if weight of nth item of > w only case 2 will occur
        if(weight[n-1]>w)
        {
            return knapsack(w,value,weight,n-1);
        }
        
        /**whether to include nth item or not
        1.if included add max_value and reduce weight
        2.if not go recursive 
         return Math.max(value[n-1]+knapsack(w-weight[n-1],value,weight,n-1),knapsack(w,value,weight,n-1));
    }**/
    
         public static int knapsack_dp(int cap,int value[],int weight[],int n)
    {
        
        int k[][]=new int[n+1][cap+1];
        for(int i=0;i<=n;i++)
        {
            for(int w=0;w<=cap;w++)
            {
                if(i==0 || w==0)
                {k[i][w]=0;}
                else if(weight[i-1]>w)
                {
                    k[i][w]=k[i-1][w];
                }
                else if(weight[i-1]<=w)
                {
                    k[i][w]=Math.max(value[i-1]+k[i-1][w-weight[i-1]],k[i-1][w]);
                }
            }
        }
        return k[n][cap];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	     int val[] = new int[]{60, 100, 120};
	     int wt[] = new int[]{10, 20, 30};
	     int  W = 50;
		    int n = val.length;
		    System.out.println(knapsack_dp(W, val,wt, n));
	}

}
