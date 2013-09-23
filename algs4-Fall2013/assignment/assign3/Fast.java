import java.util.Collections;
import java.util.ArrayList;

public class Fast
{
    public static void main(String[] args)
    {
        In infile = new In(args[0]);
        int N = infile.readInt(); 
        StdDraw.setXscale(0, 32768) ;
        StdDraw.setYscale(0, 32768);
        ArrayList<Point> pts = new ArrayList<Point>();
        for(int i = 0; i < N; i++)
        {
            int x = infile.readInt();
            int y = infile.readInt();
            Point curPt = new Point(x,y);
            pts.add(curPt);
            curPt.draw();
        }
        Collections.sort(pts);
        
        /* 
        for(int i = 0; i < N; i++)
            System.out.print(pts.get(i) + " ");
        System.out.println();
        */
        
        
        ArrayList<Point> new_pts = (ArrayList<Point>)pts.clone();
        boolean []visited = new boolean[N];
        for(int i = 0; i < N; i++)
        {
            Point cur_point = pts.get(i);
            Collections.sort(new_pts);
            Collections.sort(new_pts, cur_point.SLOPE_ORDER);

            //debug
            /*
            System.out.print(i+","+cur_point+" ");
            for(int m = 0; m < N; m++)
                System.out.print(new_pts.get(m) + "," + cur_point.slopeTo(new_pts.get(m)) + " ");
            System.out.println();
            */

            int j = 0; 
            while(j < N)
            {
                Point start_point = new_pts.get(j);
                double cur_slope = cur_point.slopeTo(start_point);
                int k;
                for(k = j+1; k < N; k++)
                {
                    if(cur_point.slopeTo(new_pts.get(k)) != cur_slope)
                        break;
                }
                if(k - j < 2)
                {
                    j = k;
                    continue;
                }
                int idx = pts.indexOf(start_point);
                if(visited[idx])
                {
                    j = k;
                    continue;
                }
                visited[pts.indexOf(cur_point)] = true;
                //StdOut.print(i + " " + cur_point + "=>");
                StdOut.print(cur_point);
                //cur_point.draw();
                for(int l = j; l < k; l++)
                {
                    Point tmpPoint = new_pts.get(l);
                    visited[pts.indexOf(tmpPoint)] = true;
                    StdOut.print( " -> " + tmpPoint);
                    //tmpPoint.draw();
                }
                cur_point.drawTo(new_pts.get(k-1));

                StdOut.println();
                j = k;

                /*
                for(int l = 0; l < N; l++)
                    System.out.print(visited[l] + " ");
                System.out.println();
                */
            }
        }
   }
}
