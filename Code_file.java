import java.util.Scanner;
public class Hospital {
    String password;
    int Store[]= {2,3,1,2,3,1,2,3}; //for storing blood units in inventory
    String bloodtype[]= {"O+","O-","A+","A-","B+","B-","AB+","AB-"};
    String[] doctors = new String[50]; //to store doctors
    Hospital() {
        password = "password";
    }

    //Patients linkedlist 
    patients first; //head of patient linkedlist
    public static class patients { //linkedlist class
        int priority;
        String patientName;
        patients next;
        patients(int p, String n) {
            priority = p;
            patientName = n;
            next = null;
        }
    }

    public void addPatient(String patientName, int priority) { //function to add patients in the queue
        patients p = new patients(priority, patientName);
        if(first == null) {
            first = p;
        } else if(first.priority > p.priority) {
            p.next = first;
            first = p;
        }
        else {
            patients temp = first;
            patients temp2 = first;
            while(temp != null && p.priority >= (temp).priority) {
                temp2 = temp;
                temp = temp.next;
            }
            if(temp == null) {
                temp2.next = p;
            } else {
                p.next = temp;
                temp2.next = p;
            }
        }
        patientPrint();
    }

    public void removePatient() { //function to remove patients from the queue as they are treated
        if(first == null) {
            System.out.println("No patients to remove");
        } else if(first.next == null) {
            System.out.println(first.patientName + " treated! ");
            first = null;
        }
        else {
            System.out.println(first.patientName + " treated! ");
            first = first.next;
            patientPrint();
        }
    }

    //main function
    public static void main(String[] args) { //main function - first menu 
        Hospital hosp = new Hospital();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int choice;
        while (flag) {
            System.out.println("Choose one: ");
            System.out.println("Please select an option:");
            System.out.println("1. Patient addition");
            System.out.println("2. Patient treated");
            System.out.println("3. Print Patient queue");
            System.out.println("4. Blood Panel");
            System.out.println("5. Admin Panel");
            System.out.println("0. Exit");
            choice = hosp.input();
            if(choice == 0) {
                flag = false;
                System.out.println("Exited from main menu.");
            } else if(choice == 1) { 
                hosp.patient();
            } else if(choice == 2) { 
                hosp.removePatient();
            } else if(choice == 3) { 
                hosp.patientPrint();
            } else if(choice == 4) {
                hosp.blood();
            } else if(choice == 5) {
                hosp.admin();
            } else {
                System.out.println("Wrong choice, try again");
            }
        }
    }

    //patient addition function
    public void patient() { //main patient function
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Patient name: ");
        String name = sc.nextLine();
        boolean flag = true;
        while(flag) {
            System.out.println("Enter disease type: 1 for extreme, 2 for moderate, 3 for general");
            int priority = input();
            if(priority == 1 || priority == 2 || priority == 3) {
                addPatient(name, priority);
                flag = false;
            }
        }       
    }

    
    public void patientPrint() { //function to print the name of patients in the queue
        patients temp = first;
        if(first == null) {
            System.out.println("No patients in queue!");
        }
        System.out.println("Patients in queue are as follows: ");
        while(temp!=null) {
            System.out.print(temp.patientName+" - ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void admin() { //main admin function
        System.out.println("Enter Password");
        String pass = inputString();
        if(!pass.equals(password)) {
            System.out.println("Wrong Password!");
            return;
        }
        int option;
        boolean flag = true;
        while(flag) {
            System.out.println("Choose one: ");
            System.out.println("Please select an option: ");
            System.out.println("1. Doctor's Registration");
            System.out.println("2. Change password");
            System.out.println("3. Inventory");
            System.out.println("0. Exit");
            option = input();
            switch (option) {
                case 1:
                    System.out.println("Doctor's Registration");
                    doctor();
                    break;
                case 2:
                    System.out.println("Enter new Password");
                    password = inputString();
                    System.out.println("Password Changed.");
                    break;
                case 3:
                    System.out.println("Inventory management");
                    inventory();
                    break;
                case 0:
                    System.out.println("Exited from admin panel.");
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    //blood donation part
	public void BloodDonate(String[] bt,int[] Store ) { //main donation function 
        System.out.println("Thank you for donating blood! Enter your blood type:");
		Scanner s=new Scanner(System.in);
		String key=s.nextLine();
		int c=-1;
		for(int i=0;i<bt.length;i++) {
			if(key.equalsIgnoreCase(bt[i])) {
				c=i;
			}
		}
		if(c!=-1) {
		    ++Store[c];
		} else {
			System.out.println("Invalid blood group enterd");
		}
		System.out.println("Bloodtype :"+bt[c]+" blood per unit :"+Store[c]);
	}

	public void checkBloodInventory(String[] bt, int[] store) { //function to check blood inventory
		 System.out.println("The current inventory is as follow");
		 for(int i=0;i<bt.length;i++) {
			 System.out.println("Blood Group :"+bt[i]+"Blood per Unit :"+store[i]);
		 }
		
	}
	public void blood() { //main blood donation function
		//bloods are in units
		boolean flag=true;
		while (flag) {
			System.out.println("enter 0 to check inventory || enter 1 to donate || enter 2 to withdraw ||3 to exit");
			int op=input();
			if (op==0) {
				checkBloodInventory(bloodtype,Store);
			}
			else if(op==1) {
				BloodDonate(bloodtype,Store);
			}
			else if(op==2) {
				Bloodwithdraw(bloodtype,Store);
			}
			else if(op==3) {
				System.out.println("Exited;");
			    flag=false;
			} else {
                System.out.println("Wrong choice");
            }			
		}
	}

	public void Bloodwithdraw(String []bt,int[] store) { //blood withdrawl function
		 System.out.println("Enter the blood type you need:");
			Scanner s=new Scanner(System.in);
			String key=s.nextLine();
			int c=-1;
			for(int i=0;i<bt.length;i++) {
				if(key.equals(bt[i])) {
					c=i;
				}
			}
			if(c==-1) {
				System.out.println("Invalid blood group entered");
			} else if(store[c]<1) {
				System.out.println("Not enough blood available!");
			} else {
				--store[c];
			}
			System.out.println("Bloodtype now :"+bt[c]+" blood per unit :"+store[c]);	
	}
