import java.awt.*;
import java.applet.*;
import graph.*;
import java.util.Random;

public class PermutationTest3 extends Applet {

      Graph2D graph;
      DataSet data1;
      DataSet data2;
      DataSet data3;
      Axis    xaxis;
      Axis    yaxis_left;
      Axis    yaxis_right;
 
      public void init() {
        //Initialize fields
        final int MAXPOINTS=10000;
        double runTimeLimit = 30;

        double d1[] = new double[MAXPOINTS*2];
        double d2[] = new double[MAXPOINTS*2];
        double d3[] = new double[MAXPOINTS*2];

        //initialize graph
        graph = new Graph2D();
        graph.drawzero = false;
        graph.drawgrid = false;
        graph.borderTop = 50;
        graph.borderRight=100;
        setLayout( new BorderLayout() );
        add("Center", graph);
       
        //Algo1
        int size = 100;
        d1 = new double[MAXPOINTS];
        int  i;
        for(i = 0; i < MAXPOINTS; i++){
            long startTime, endTime;
            double elapsedTime;
            startTime = System.currentTimeMillis();
            algorithm1(size);
            endTime = System.currentTimeMillis();
            elapsedTime = (endTime - startTime) / 1000.0;

            d1[i*2] = size;
            d1[i*2+1] = elapsedTime;
            size *= 2;

            if(elapsedTime > runTimeLimit || size > 7000000)
                break;
        } 
               
        System.out.println("set 1");
        for(int j = 0; j <= i; j++)
            System.out.println(d1[j*2]+" "+d1[j*2+1]);
        System.out.println();

        data1 = graph.loadDataSet(d1,i+1);
        data1.linestyle = 1;
        data1.linecolor   =  new Color(0,255,0);
        data1.marker    = 1;
        data1.markerscale = 1;
        data1.markercolor = new Color(0,0,255);
        data1.legend(200,100,"Algorithm1");
        data1.legendColor(Color.black);
        
        //algo2
        size = 100;
        d2 = new double[MAXPOINTS];
        for(i = 0; i < MAXPOINTS; i++){
            long startTime, endTime;
            double elapsedTime;
            startTime = System.currentTimeMillis();
            algorithm2(size);
            endTime = System.currentTimeMillis();
            elapsedTime = (endTime - startTime) / 1000.0;

            d2[i*2] = size;
            d2[i*2+1] = elapsedTime;              
            
            size *= 2;
            if(elapsedTime > runTimeLimit || size > 7000000)
                break;
        }      
        data2 = graph.loadDataSet(d2,i+1);
        data2.linestyle = 1;
        data2.linecolor   =  new Color(255,0,0);
        data2.marker    = 1;
        data2.markerscale = 1;
        data2.markercolor = new Color(0,0,255);
        data2.legend(200,120,"Algorithm2");
        data2.legendColor(Color.black);
 
      //  System.out.println("set 2");
      //  for(int j = 0; j < i; j++)
      //      System.out.println(d2[j*2]+" "+d2[j*2+1]);
      //  System.out.println();

        //algo3
        size = 100;
        d3 = new double[MAXPOINTS];
        for(i = 0; i < MAXPOINTS; i++){
            long startTime, endTime;
            double elapsedTime;
            startTime = System.currentTimeMillis();
            algorithm3(size);
            endTime = System.currentTimeMillis();
            elapsedTime = (endTime - startTime) / 1000.0;

            d3[i*2] = size;
            d3[i*2+1] = elapsedTime;// System.out.println(d3[i*2]+" "+d3[i*2+1]);
            
            size *= 2;
            if(elapsedTime > runTimeLimit || size > 7000000)
                break;
        }   
        data3 = graph.loadDataSet(d3,i+1);
        data3.linestyle = 1;
        data3.linecolor   =  new Color(0,0,255);
        data3.marker    = 1;
        data3.markerscale = 1;
        data3.markercolor = new Color(0,0,255);
        data3.legend(200,140,"Algorithm3");
        data3.legendColor(Color.black);

      //  System.out.println("set 3");
     //   for(int j = 0; j < i; j++)
     //       System.out.println(d3[j*2]+" "+d3[j*2+1]);
     //   System.out.println();


        //Attach dataset to Axis
        xaxis = graph.createAxis(Axis.BOTTOM);
        xaxis.attachDataSet(data1);
        xaxis.attachDataSet(data2);
        xaxis.attachDataSet(data3);
        xaxis.setTitleText("N (array length)");
        xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));

        yaxis_left = graph.createAxis(Axis.LEFT);
        yaxis_left.attachDataSet(data1);
        yaxis_left.attachDataSet(data2);
        yaxis_left.attachDataSet(data3);
        yaxis_left.setTitleText("Time(s)");
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
