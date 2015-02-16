import java.awt.*;
import java.applet.*;
import graph.*;
import java.util.Random;

public class PermutationTest2 extends Applet {

      static Graph2D graph;
      static DataSet data1;
      static DataSet data2;
      static DataSet data3;
      static Axis    xaxis;
      static Axis    yaxis_left;
      static Axis    yaxis_right;
      static double d1[],d2[],d3[];
      static int np = 100000;
 

      public static void main(String[] args) {
      //  Graph2D graph;
        final int MAXPOINTS=20;
        double d1[] = new double[MAXPOINTS*2];
        double d2[] = new double[MAXPOINTS*2];
        double d3[] = new double[MAXPOINTS*2];
        double x,y;

        graph = new Graph2D();
        graph.drawzero = false;
        graph.drawgrid = false;
        graph.borderTop = 50;
        graph.borderRight=100;
       // setLayout( new BorderLayout() );
       // add("Center", graph);
       
        //Algo1
        int  size = 100;
        d1 = new double[MAXPOINTS];
        int i;
        for(i = 0; i < MAXPOINTS; i++){
            long startTime, endTime;
            double elapsedTime;
            startTime = System.currentTimeMillis();
            algorithm1(size);
            size *= 2;
            endTime = System.currentTimeMillis();
            elapsedTime = (endTime - startTime) / 1000.0;

            d1[i*2] = i;
            d1[i*2+1] = elapsedTime;
            
            if(elapsedTime > 30.0)
                break;
        } 
        
 //test       
        for(int j = 0; j < i; j++)
            System.out.print(d1[j*2]+" "+d1[j*2+1]+" ; ");
        
        data1 = graph.loadDataSet(d1,i);
        data1.linestyle = 1;
        data1.linecolor   =  new Color(0,255,0);
        data1.marker    = 1;
        data1.markerscale = 1;
        data1.markercolor = new Color(0,0,255);
        data1.legend(200,100,"Y=X, linear");
        data1.legendColor(Color.black);
        
        //test2
        size = 100;
        d2 = new double[MAXPOINTS];
        for(i = 0; i < MAXPOINTS; i++){
            long startTime, endTime;
            double elapsedTime;
            startTime = System.currentTimeMillis();
            algorithm2(size);
            size *= 2;
            endTime = System.currentTimeMillis();
            elapsedTime = (endTime - startTime) / 1000.0;

            d2[i*2] = i;
            d2[i*2+1] = elapsedTime;
            
            if(elapsedTime > 30.0)
                break;
        }      
        data2 = graph.loadDataSet(d2,i);
        data2.linestyle = 1;
        data2.linecolor   =  new Color(255,0,0);
        data2.marker    = 1;
        data2.markerscale = 1;
        data2.markercolor = new Color(0,0,255);
        data2.legend(200,120,"Algorithm2");
        data2.legendColor(Color.black);

        //test3
        size = 100;
        d3 = new double[MAXPOINTS];
        for(i = 0; i < MAXPOINTS; i++){
            long startTime, endTime;
            double elapsedTime;
            startTime = System.currentTimeMillis();
            algorithm3(size);
            size *= 2;
            endTime = System.currentTimeMillis();
            elapsedTime = (endTime - startTime) / 1000.0;

            d3[i*2] = i;
            d3[i*2+1] = elapsedTime;
            
            if(elapsedTime > 30.0)
                break;
        }     
        data3 = graph.loadDataSet(d3,i);
        data3.linestyle = 1;
        data3.linecolor   =  new Color(0,0,255);
        data3.marker    = 1;
        data3.markerscale = 1;
        data3.markercolor = new Color(0,0,255);
        data3.legend(200,120,"Algorithm3");
        data3.legendColor(Color.black);

        xaxis = graph.createAxis(Axis.BOTTOM);
        xaxis.attachDataSet(data1);
        xaxis.attachDataSet(data2);
        xaxis.attachDataSet(data3);
        xaxis.setTitleText("X values");
        xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
/*
**      Attach the first data set to the Left Axis
*/
        yaxis_left = graph.createAxis(Axis.LEFT);
        yaxis_left.attachDataSet(data1);
        yaxis_left.attachDataSet(data2);
        yaxis_left.attachDataSet(data3);
        yaxis_left.setTitleText("Y VALUES");
        yaxis_left.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        yaxis_left.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        yaxis_left.setTitleColor( new Color(0,0,255) );
      
      
   }  
 
   public static void algorithm1(int size){

        int a[] = new int[size];
        Random generator = new Random();

        for(int i = 0; i < size; i++){
            boolean isDuplicate=true;
            int x=generator.nextInt(size);
            while(isDuplicate){
                isDuplicate = false;
                x = generator.nextInt(size);
                for(int j = 0; j < i; j++){
                    if(x == a[j])
                        isDuplicate = true;
                 }
            }
            a[i] = x;
        }
    }

    public static  void algorithm2(int size){
        int a[] = new int[size];
        boolean used[] = new boolean[size];
        Random generator = new Random();

        //initialize used
        for(int i = 0; i < size; i++)
            used[i] = false;


        for(int i = 0; i < size; i++){
            int x = generator.nextInt(size);
            while(used[x])
                x = generator.nextInt(size);
            used[x] = true;
            a[i] = x;
        }
    }

    public static void algorithm3(int size){
        int a[] = new int[size];
        Random generator = new Random();

        //initialize a
        for(int i = 0; i < size; i++)
            a[i] = i+1;
        
        //swap
        for(int i = 1; i < size; i++){
            int temp = a[i];
            int x = generator.nextInt(size);
            a[i] = a[x];
            a[x] = temp;
        }

    }

 }
