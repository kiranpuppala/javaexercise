public class Recursion {
    public static void main(String ... args){

        Recursion r = new Recursion();
        System.out.println(r.factorial(5));

    }

    public int factorial(int n){
        if(n==0)
            return 1;

        return n*factorial(n-1);

    }

}
