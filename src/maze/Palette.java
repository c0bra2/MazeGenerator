package maze;

/**
 *
 * @author Jacob Mason
 * Description: A class defining customizable options which can be used to 
 *              customize the maze to give it a unique look, feel, and behavior
 */
public class Palette {
    // variable declarations
    private String walls;
    private String paths;
    private String start;
    private String exit;
    
    public Palette() {
        
    }
    
    // set the string that will represent walls in 
    // the maze output, this could be a unicode or ascii
    // character but will be represented as a string within
    // the program
    public void setWall(String wall) {
        this.walls = wall;
    }
    
    // set the string that will represent paths in the
    // maze output, this could be a unicode or ascii character
    // but will be represented as a string within the program
    public void setPath(String path) {
        this.paths = path;
    }
    
    public void setStart(String start) {
        this.start = start;
    }
    
    public void setExit(String exit) {
        this.exit = exit;
    }
    
    // returns the string being used to represent a wall 
    public String getWall() {
        return this.walls;
    }
    
    // returns the string being used to represent a path
    public String getPath() {
        return this.paths;
    }
    
    // returns the string being used to represent an Exit
    public String getExit() {
        return this.exit;
    }
    
    // returns the string being used to represent a starting point
    public String getStart() {
        return this.start;
    }
}
