import variable.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadInput {
    public static Variable[][] initialize(String filename) throws FileNotFoundException {
        String filePath = new File("").getAbsolutePath();

        File file =new File(filePath+filename);
        Scanner scanner=new Scanner(file);
        int n= scanner.nextInt();
        Variable[][] variables=new Variable[n][];
        for (int i = 0; i < n; i++) {
            variables[i]=new Variable[n];
            for (int j = 0; j < n; j++) {
                variables[i][j]=new Variable(n);
                variables[i][j].setIndex(i,j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val= scanner.nextInt();
                if(val==0){
                    continue;
                }
                else{
                    variables[i][j].value=val;
                    for (int k = 0; k < n; k++) {
                        variables[i][k].update(val-1);
                    }
                    for (int k = 0; k < n; k++) {
                        variables[k][j].update(val-1);
                    }
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < n; k++) {
//                    System.out.print(variables[i][j].domain[k]+" ");
//                }
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
        scanner.close();
        return variables;
    }
}
