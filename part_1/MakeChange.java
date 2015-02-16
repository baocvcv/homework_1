public class MakeChange {

   private static int targetnum;
   private static boolean isUsed25 = false;
   private static boolean isUsed10 = false;
 
   private static int sequenceOfChange[];
   private static int currentPosition = 0;

   /*print out the changes
    * parameters: int num
    */
   public static void change (int num, int limit){
   
       if(num == 0){
           System.out.print("Change for "+targetnum+" =");
           for(int i = 0; i < currentPosition; i++)
               System.out.print(" "+sequenceOfChange[i]);
           System.out.println();
           return;
       }

       if(num>=25 && limit>=25){
           sequenceOfChange[currentPosition++]=25;
           change(num-25,25);
           sequenceOfChange[--currentPosition]=0;
       }

       if(num>=10 && limit>=10){
           sequenceOfChange[currentPosition++]=10;
           change(num-10,10);
           sequenceOfChange[--currentPosition]=0;
       }
       
       if(num>=5 && limit>=5){
           sequenceOfChange[currentPosition++]=5;
           change(num-5,5); 
           sequenceOfChange[--currentPosition]=0;
       }
   }

   public static void main(String[] args){

       if(args.length < 1){
           System.err.println("Missing Argument");
           System.exit(1);
       }

       targetnum = Integer.parseInt(args[0]);

       if(targetnum%5 != 0){
           System.out.println(">\n"+args[0]+" can't be changed");
           System.exit(0);
       }

       sequenceOfChange = new int[targetnum/5+1];
       change(targetnum,targetnum);

   }
}
