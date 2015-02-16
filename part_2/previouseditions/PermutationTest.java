import java.util.Random;
import java.awt.*;
import java.applet.*;
import graph.*;

public class PermutationTest extends Applet{

    static int maxPoints = 20; 
    Graph2D graph;
    DataSet data1;
    DataSet data2;
    Dataset data3;
    Axis    xaxis;
    Axis    yaxis_left;
    Axis    yaxis_right;
    double d1[],d2[],d3[];
    int np = 100000;


    public static void main(){

               
        //set graph
        graph = new Graph2D();
        graph.drawzero = false;
        graph.drawgrid = false;
        graph.borderTop = 50;
        graph.borderRight=100;
        setLayout( new BorderLayout() );
        add("Center", graph);

       // Graph graph;
      //  graph = new Graph();

        DataSet dataset; //int maxPoints; int totalPoints; double data[];
        dataset = new DataSet(maxPoints);

        //test1
        int  size = 100;
        d1 = new double[maxPoints];
        int i;
        for(i = 0; i < maxPoints; i++){
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
        data1 = graph.loadDataSet(d1,i);
        data1.linestyle = 1;
        data1.linecolor   =  new Color(0,255,0);
        data1.marker    = 1;
        data1.markerscale = 1;
        data1.markercolor = new Color(0,0,255);
        data1.legend(200,100,"Algorithm1");
        data1.legendColor(Color.black);


        //test2
        size = 100;
        d2 = new double[maxPoints];
        for(int i = 0; i < maxPoints; i++){
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
        data2.legend(200,100,"Algorithm2");
        data2.legendColor(Color.black);


        //test3
        size = 100;
        d3 = new double[maxPoints];
        for(int i = 0; i < maxPoints; i++){
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
        data3.legend(200,100,"Algorithm3");
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
            int x;
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
    
    public static void draw(DataSet dataset,String legendName, int color){

        data1 = graph.loadDataSet(dataset.data,dataset.totalPoints);
        data1.linestyle = 1;
        switch (color){
          case 1: data1.linecolor   =  new Color(0,255,0);
                  break;
          case 2: data1.linecolor   =  new Color(255,0,0);
                  break;
          case 3: data1.linecolor   =  new Color(0,0,255);
                  break;
        }
        data1.marker    = 1;
        data1.markerscale = 1;
        data1.markercolor = new Color(0,0,255);
        data1.legend(200,100,legendName);
        data1.legendColor(Color.black);
        
        xaxis = graph.createAxis(Axis.BOTTOM);
        xaxis.attachDataSet(data1);
        xaxis.setTitleText("X values");
        xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
/*
**      Attach the first data set to the Left Axis
*/
        yaxis_left = graph.createAxis(Axis.LEFT);
        yaxis_left.attachDataSet(data1);
        yaxis_left.setTitleText("Y VALUES");
        yaxis_left.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        yaxis_left.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        yaxis_left.setTitleColor( new Color(0,0,255) );
      
      
   }

}

class DataSet{

    public int maxPoints;
    public int totalPoints = 0;
    public double data[];

    public DataSet(int max){
        maxPoints = max;
        data = new double[max*2];
    }
}

