/*

Copyright 2024 Robert J. Hodges

this program is the sole production of Robert J Hodges for the Advent of code 2024
Day 2 I accept no responsibility for any damages that may be incurred from the
use and application of this code. For educational use only.*/
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.lang.Math;

/*Problem1:
a text file of numbers separated by lines is provided as ReactorLevels.txt the object
is to determine the number of "Safe" iterations of the lines provided. A safe iteration
must be continuously decreasing or increasing through the line. the integer cannot be more
than a factor of 2 difference than its adjoining integer, and must not be the same
ie: 1 2 3 5 6 -Good
2 5 6 7 8 -bad
3 3 4 5 6 -bad
4 5 6 4 -bad */
/*Problem 2:
 now we can tolerate 1 bad level so if removing a single number would make the process work,
  we can now accept it.*/

public class RudolfReactor {
    static ArrayList<int[]> lines;

    public static void toArrayList(String file){
        lines= new ArrayList<int[]>();
        try {
            String wholeFile=Files.readString(Paths.get(file));
            String[] allines = wholeFile.split("[\r\n]+");
            for(String line : allines){
                String[] nums=line.split(" ");
                int[] numarr = new int[nums.length];
                for (int i = 0; i < nums.length; i++){
                    nums[i].replace("\n", "");
                    numarr[i] = Integer.parseInt(nums[i]);
                }
                lines.add(numarr);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void safeRuns(){
        int counter=0;
        //for each array (line) in the array list of lines
        for (int[] line : lines) {
            //Begin switch statements that will determine if the trend is up or down.
            if (line[0] < line[1]) {
                if (goingUp(line)) {
                    counter++;
                }
            }
            else if (line[0] > line[1]){
                if(goingDown(line)){
                    counter++;
                }
            }
            else{ continue;}
        }
        System.out.println(counter);
    }
    public static boolean goingDown(int[] line){
        // add counter to mark a false
        boolean broken=false;
        for (int i = 0; i < line.length-1; i++){
            if (line[i] < line[i+1]){
                broken=true;
                break;
            }
            else if (line[i] == line[i+1]){
                broken=true;
                break;
            }
            else{
                if(line[i]-3 > line[i+1]){
                    broken=true;
                    break;
                }
                else{continue;}
            }
        }
        if (broken==true){
            broken=dubCkUp(line);
            if(broken==false){
                return true;
            }
            broken=dubCkDn(line);
            if(broken==false){
                return true;
            }
            else{return false;}
        }

        else{return true;}
    }
    public static boolean goingUp(int[] line){
        boolean broken=false;
        for (int i = 0; i < line.length-1; i++){
            if (line[i] > line[i+1]){
                broken=true;
                break;
            }
            else if (line[i] == line[i+1]){
                broken=true;
                break;
            }
            else{
                if(line[i]+3 < line[i+1]){
                    broken=true;
                    break;
                }
                else{continue;}
            }
        }
        if (broken==true){
            broken=dubCkUp(line);
            if(broken==false){
                return true;
            }
            broken=dubCkDn(line);
            if(broken==false){
                return true;
            }
            else{return false;}
        }

        else{return true;}
    }
    public static boolean dubCkUp(int[] line){
        int j=line.length-1;
        int i=0;
        boolean broken=false;
        while(j>=0){
            while (i<line.length-1){
                broken=false;                
                if(i==j){
                    i++;
                }
                if (i>line.length-1){break;}
                if (j==line.length-1 && i+1==j){
                    break;
                }
                if (line[i] > line[i+1]){
                    broken=true;
                    break;
                }
                else if (line[i] == line[i+1]){
                    broken=true;
                    break;
                }
                else{
                    if(line[i]+3 < line[i+1] ){
                        broken=true;
                        break;
                    }
                    else{i++;}
                }
                
            }
            if (broken==false){
                return broken;
            }
            else{j--;}
        }
        return broken;
    }
    public static boolean dubCkDn(int[] line){
        int j=line.length-1;
        int i=0;
        boolean broken=false;
        while(j>=0){
            
            while (i<line.length-1){
                broken=false;                
                if(i==j){
                    i++;
                }
                if (i>line.length-1){break;}
                if (j==line.length-1 && i+1==j){
                    break;
                }
                if (line[i] < line[i+1]){
                    broken=true;
                    break;
                }
                else if (line[i] == line[i+1]){
                    broken=true;
                    break;
                }
                else{
                    if(line[i]-3 > line[i+1]){
                        broken=true;
                        break;
                    }
                    else{i++;}
                }
            }
            if (broken==false){
                return false;
            }
            else{j--;}
        }
        return true;
    }
    public static void main(String[] args) {
        String test = "LevelsTest.txt";
        String file = "ReactorLevels.txt";
        toArrayList(file);
        safeRuns();
    }
}