import java.awt.*;
import java.applet.*;
import graph.*;

public class Graph extends Applet {

      public Graph2D graph;
      public DataSet data1;
      public DataSet data2;
      public Axis    xaxis;
      public Axis    yaxis_left;
      public Axis    yaxis_right;
      double d1[],d2[];
      int np = 100000;
 

      public Graph() {
        int i,j;
        final int MAXPOINTS=12;
        double d1[] = new double[MAXPOINTS*2];
        double d2[] = new double[MAXPOINTS*2];
        double x,y;

        graph = new Graph2D();
        graph.drawzero = false;
        graph.drawgrid = false;
        graph.borderTop = 50;
        graph.borderRight=100;
        setLayout( new BorderLayout() );
        add("Center", graph);

      }
        

      public void draw(DataSet dataset,String legendName, int color){

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

