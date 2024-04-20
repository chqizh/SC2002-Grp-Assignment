package Branch;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class EditBranches implements Serializable {

    public Branches branches;

    public EditBranches(Branches branches){
        this.branches=branches;
    }

    public Branch addBranch(Branches branches) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter branch name: ");
        String branchName=sc.nextLine();

        System.out.println("Enter branch location: ");
        String branchLocation=sc.nextLine();

        System.out.println("Enter staff quota: ");
        int staffQuota =sc.nextInt();
        sc.nextLine();

        Branch newBranch = new Branch (branchName, branchLocation, staffQuota);
        boolean result = branches.insertBranch(newBranch.getbranchID(),newBranch);

        if (result == true){
            System.out.println("You have successfully opened a branch. (Branch ID: "+newBranch.getbranchID()+" )");
        }
        else{
            System.out.println("Unsuccessful in opening of branch.");
        }
        return newBranch;
    }

    public Branches removeBranch(Branches branches) throws IOException{
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter ID of branch you would like to close: ");
        int closeBranch = sc.nextInt();
        sc.nextLine();

        boolean flag = branches.closeBranch(closeBranch);

        if (flag == false){
            System.out.println("Branch ID not found. Branch not closed.");
        }
        else{
            System.out.println("Branch successfully closed.");
        }
        return branches;
    }


    
}
