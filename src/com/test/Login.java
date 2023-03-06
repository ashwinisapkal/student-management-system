package com.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

   public class Login{

    public static void main(String[] args) throws NumberFormatException, IOException, SQLException, ClassNotFoundException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));


        System.out.println("==============================================================================");
        System.out.println("=====================  WELCOME TO STUDENT MANAGEMENT================================");
        System.out.println("==============================================================================");
        System.out.println("\t\t 1 --> ADMIN");
        System.out.println("\t\t 2 --> students");
        System.out.println("==============================================================================");
        System.out.println("Enter your choice:");
        int choice=Integer.parseInt(br.readLine());


            System.out.println("==============================================================================");
            System.out.println("===========================    LOGIN DETAILS  ================================");
            System.out.println("==============================================================================");

            System.out.print("\t Enter your username:");
            String userName=br.readLine();
            System.out.print("\t Enter your password:");
            String userPassword=br.readLine();
            try
            {
            Connection conn=Mysqlconnection.getConnection();
            System.out.println("connection sucess");
            PreparedStatement ps=conn.prepareStatement("select password from admin where username=?");
            ps.setString(1, userName);
            ResultSet result=ps.executeQuery();
            String password=null;
            boolean login = false;
			while (result.next()) {
				password = result.getString("password");
				if (password.equals(userPassword)) {
				login = true;
				System.out.println(login);
				}
			}
			
            while(result.next())
            {
                password=result.getString("Password");
            }

            if(login)
            {
            	String status ="Y";
                System.out.println("==============================================================================");
                System.out.println("===========================   Login successful ================================");
                System.out.println("==============================================================================");
                System.out.println("============================== WELCOME "+  userName.toUpperCase()  +" ===============================");
                System.out.println("==============================================================================");





                do {
                    System.out.println("==============================================================================");
                    System.out.println("\t\t  1 --> add student");
                    System.out.println("\t\t  2 --> delete");

                    System.out.println("\t\t  3 --> Change Password");
                    System.out.println("\t\t  4 --> update");
                    System.out.println("\t\t  5 --> Exit/Logout");
                    System.out.println("==============================================================================");

        		System.out.println("Enter your choice:");
				int operation = Integer.parseInt(br.readLine());

				switch (operation) {
				case 1:
					System.out.println("Enter sid:");
					long sid= Long.parseLong(br.readLine());

					System.out.println("Enter student  name:");
					String sname = br.readLine();

					System.out.println("Date of Birth:(dd/MM/YYYY)");
					String dob = br.readLine();

					System.out.println("Gender:");
					String gender = br.readLine();

					System.out.println("Enter address: ");
					String address = br.readLine();

					System.out.println("Enter phone number:");
					long phone = Long.parseLong(br.readLine());


					System.out.println("Enter email id:");
					String email = br.readLine();

					System.out.println("Enter course: ");
					String course = br.readLine();
					
					System.out.println("Enter class: ");
					String class1 = br.readLine();
					System.out.println("Enter password: ");
					String passwd = br.readLine();
					

					
					try {

					ps = conn.prepareStatement("insert into students values(?,?,?,?,?,?,?,?,?,?)");
					ps.setLong(1, sid);
					ps.setString(2, sname);
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
					java.util.Date utilDate = format.parse(dob);
					java.sql.Date date = new java.sql.Date(utilDate.getTime());
					ps.setDate(3, date);
					ps.setString(4, gender);
					ps.setString(5, address);
					ps.setLong(6, phone);
					ps.setString(7, email);
					ps.setString(8, course);
					ps.setString(9, class1);
					ps.setString(10, passwd);
					
					if (ps.executeUpdate() > 0) {
						System.out.println("==============================================================================");
						System.out.println("Account created successfully!!");
						System.out.println("==============================================================================");

					}
					else {
						System.out.println("Sorry");
					}


					   if (status.equals("n") || status.equals("N")) {
					  	login = false;
					     }
					}
					catch(Exception e) {
						System.out.println("Error="+e);
					}
					
					   break;

				    case 2:

				    	System.out.println("Enter student id:");
						int sid1 = Integer.parseInt(br.readLine());

						ps = conn.prepareStatement("delete from students where sid=?");
						ps.setLong(1, sid1);

						if (ps.executeUpdate() > 0) {
							System.out.println(
									"==============================================================================");
							System.out.println("student account delete successfully!!");
							System.out.println(
									"==============================================================================");

						} else {
							System.out.println(
									"==============================================================================");
							System.out.println("Problem in account closing!!");
							System.out.println(
									"==============================================================================");

						}
						System.out.println("Do you want to continue?(Y/N)");
						status = br.readLine();

						if (status.equals("n") || status.equals("N")) {
							login = false;
						}
					break;
					
				case 3: 
					System.out.println("Enter the old password1:");
					String password1 = br.readLine();

					System.out.println("Enter the new password:");
					String newPassword = br.readLine();

					System.out.println("Re-enter the new password:");
					String rePassword = br.readLine();

					ps = conn.prepareStatement("select * from admin where username=?");
					ps.setString(1, userName);

					result = ps.executeQuery();
					String existingPassword = null;
					while (result.next()) {
						existingPassword = result.getString("password");

					}

					if (existingPassword.equals(password1)) {
						if (newPassword.equals(rePassword)) {
							
							ps = conn.prepareStatement("update admin set password=? where username=?");
							ps.setString(1, newPassword);
							ps.setString(2, userName);

							if (ps.executeUpdate() > 0) {
								System.out.println("==============================================================================");
								System.out.println("Password changed successfully!!");
								System.out.println("==============================================================================");

							} else {
								System.out.println("==============================================================================");
								System.out.println("Problem in password changed!!");
								System.out.println("==============================================================================");

							}

						} else {
						    System.out.println("==============================================================================");
							System.out.println("New password and retype password must be same!!");
							System.out.println("==============================================================================");

						}
					} else {
						System.out.println("==============================================================================");
						System.out.println("Old password is wrong!!");
						System.out.println("==============================================================================");

					}
					System.out.println("Do you want to continue?(Y/N)");
					status = br.readLine();

					if (status.equals("n") || status.equals("N")) {
						login = false;
					}
					break;
					
				case 4:
					   System.out.println("Enter student sid:");
					   int id =  Integer.parseInt(br.readLine());
					   
						System.out.println("Enter student sname:");
						String  sname1 = br.readLine();


						ps = conn.prepareStatement("update students set sname=? where sid=?");
						ps.setInt(2,id);
						ps.setString(1,sname1) ;
						if (ps.executeUpdate() > 0) {
							System.out.println("==============================================================================");
							System.out.println("stunt students update successfully!!");
							System.out.println("==============================================================================");

						} else {
							System.out.println("==============================================================================");
							System.out.println("Problem in account closing!!");
							System.out.println("==============================================================================");

						}
						System.out.println("Do you want to continue?(Y/N)");
						status = br.readLine();

						if (status.equals("n") || status.equals("N")) {
							login = false;
						}
					
					
				}


			} while (login);

			System.out.println("==============================================================================");
			System.out.println("Bye. Have a nice day!!");
			System.out.println("==============================================================================");


            }
            else {
				System.out.println("Enter a valid input!!");
			}
            
    } 
    catch(Exception e) {}        
    }
   }