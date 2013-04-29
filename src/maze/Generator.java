package maze;
import java.util.*;

/**
 *
 * @author Jacob Mason 2013
 * Description: This is the class that represents a new randomly 
 *              generated maze and the methods that are associated
 *              with it. The maze algorithm being implemented here
 *              is what's known as randomized prims.
 */

public class Generator {
    // variable declarations
    private int mazeWidth;
    private int mazeHeight;
    private char [][] mazeStructure;
    private static Palette myPalette;
    
    // constructor method
    // takes 2 ints describing the 
    // dimension of the graph to be 
    // generated.
    public Generator(int height, int width, Palette palette) {
        this.mazeHeight = height;
        this.mazeWidth = width;
        setPalette(palette);
        mazeStructure = new char [mazeHeight][mazeWidth];
        StringBuilder s = new StringBuilder(mazeWidth);
        
        // intialize maze to only walls
        for (int i = 0; i < mazeWidth; i++)
            s.append('*');
        for(int i = 0; i < mazeHeight; i++)
            mazeStructure[i] = s.toString().toCharArray();

        // Select a random starting point
        Point start = new Point((int)(Math.random()* mazeHeight),
            (int)(Math.random()*mazeWidth), null);
        mazeStructure[start.getR()][start.getC()] = 'S';

        // go through neighbors in direct proximity of the vertex
        ArrayList<Point> frontier = new ArrayList<Point>();
        for (int x=-1;x<=1;x++)
            for(int y=-1; y<=1;y++) {
                if (x==0&&y==0||x!=0&&y!=0)
                    continue;
                try {
                    if (mazeStructure[start.getR()+x][start.getC()+y] == '.') continue;
                }catch (Exception ex) {
                    continue;
                }
                //add valid vertices to frontier
                frontier.add(new Point(start.getR()+x, start.getC()+y,start));
            }

        Point last = null;
        
        // continue prims algorithm until all vertices in the frontier
        // are removed
        while(!frontier.isEmpty()) {
            // pick current vertex randomly 
            Point cur = frontier.remove((int)(Math.random() * frontier.size()));
            Point opp = cur.opposite();
            try {
                // if both current vertex and opposite vertex are walls
                if (mazeStructure[cur.getR()][cur.getC()] == '*') {
                    if (mazeStructure[opp.getR()][opp.getC()] == '*') {

                        // open path between vertices
                        mazeStructure[cur.getR()][cur.getC()] = '.';
                        mazeStructure[opp.getR()][opp.getC()] = '.';

                        //store last vertex to mark it later
                        last = opp;

                        // go through neighbors of vertex, same as earlier
                        for (int x=-1;x<=1;x++)
                            for (int y=-1;y<=1;y++) {
                                if (x==0&&y==0||x!=0&&y!=0)
                                    continue;
                                try {
                                    if (mazeStructure[opp.getR()+x][opp.getC()+y] == '.') continue;
                                }catch(Exception ex) {
                                    continue;
                                }
                                frontier.add(new Point(opp.getR()+x,opp.getC()+y,opp));
                            }
                    }
                }
            }
            catch (Exception ex) {/*ignore exceptions*/};   

            // if algorithm has finished, mark end vertex
            if (frontier.isEmpty())
                mazeStructure[last.getR()][last.getC()] = 'E';    
        }
    }

    // provides a means of passing a palette object in for use 
    // in this class
    public static void setPalette(Palette palette) {
        myPalette = palette;
    }
    
    // provides a means of passing a pallete object back for use
    // outside the class
    public static Palette getPalette() {
        return myPalette;
    }
    
    // return maze as a string
    public String toString() {
        StringBuilder result = new StringBuilder(mazeWidth * mazeHeight +
                mazeHeight); 
        boolean putNewLine = false;

        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
               if (mazeStructure[i][j] == 'S') {
                   result.append(myPalette.getStart());
               }
               else if (mazeStructure[i][j] == 'E') {
                   result.append(myPalette.getExit());
               }
               else if (mazeStructure[i][j] == '*') {
                   result.append(myPalette.getWall());
               }
               else if (mazeStructure[i][j] == '.') {
                   result.append(myPalette.getPath());
               }
            }
            result.append('\n');
        }

        return result.toString();
    }

    static class Point{
        private Integer r;
        private Integer c;
        Point parent;
        
        public Point(int x, int y, Point p){
            r=x;c=y;parent=p;
        }
        // compute opposite node given that it is in the other direction from the parent
        public Point opposite(){
            if(this.r.compareTo(parent.r)!=0)
                return new Point(this.r+this.r.compareTo(parent.r),this.c,this);
            if(this.c.compareTo(parent.c)!=0)
                return new Point(this.r,this.c+this.c.compareTo(parent.c),this);
            return null;
        }

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }
    }
}
    
