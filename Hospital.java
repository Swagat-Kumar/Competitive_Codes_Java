import java.io.*;
import java.util.*;

public class Hospital {
    public void addDoctor(String details) {
        try {
            PrintWriter docWriter = new PrintWriter(new FileOutputStream(new File("Doctor.txt"), true));
            docWriter.println(details);
            docWriter.flush();
            docWriter.close();
            System.out.println();
            System.out.println("Doctor Registered Successfully!");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Please Create Doctor.txt file in default directory");
        }
    }

    public void bookAppointment(String Dept) throws IOException {
        Dept = Dept.toLowerCase();
        Scanner sc = new Scanner(new File("Doctor.txt"));
        String oldContent = "";
        boolean booked = false;
        String bookingDetails = "";
        while (sc.hasNextLine()) {
            Doctor d = new Doctor(sc.nextLine());
            if (!booked) {
                if (d.department().equals(Dept)) {
                    if (d.isAvailable()) {
                        bookingDetails += d.name() + " | Appointment Time: " + d.bookNext();
                        booked = true;
                    }
                }
            }
            oldContent += d.feedDetails() + "\n";
        }
        if (!booked) {
            System.out.println("We don't have Doctors available for the department: " + Dept);
        } else {
            System.out.println();
            System.out.println("Your Appointment is Booked. Please be on time:");
            System.out.println(bookingDetails);
            System.out.println();
        }
        PrintWriter docWriter = new PrintWriter(new File("Doctor.txt"));
        docWriter.write(oldContent);
        docWriter.flush();
        docWriter.close();
        sc.close();
    }

    private void menu() {
        System.out.println();
        System.out.println("| Main Menu |");
        System.out.println("1. Book Appointment");
        System.out.println("2. Add Doctor");
        System.out.println("3. Exit");
        System.out.println("Enter Choice :");
    }

    private void book(String choice) {
        this.bookingMenu();

        try {
            switch (choice) {
                case "1":
                    this.bookAppointment("Skin");
                    break;
                case "2":
                    this.bookAppointment("Paediatric");
                    break;
                case "3":
                    this.bookAppointment("Heart");
                    break;
                case "4":
                    this.bookAppointment("Gyna");
                    break;
                default:
                    System.out.println("No Such Department Exists");
            }
        } catch (IOException e) {
            System.out.println("Please Create Doctor.txt file in default directory");
        }
    }

    private void bookingMenu() {
        System.out.println("");
        System.out.println("| Department Menu |");
        System.out.println("1. Skin");
        System.out.println("2. Pediatric");
        System.out.println("3. Heart");
        System.out.println("2. Gyna");
        System.out.println("Enter Choice :");

    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        this.menu();
        String line = sc.nextLine();
        while (!line.equals("3")) {
            if (line.equals("2")) {
                System.out.println("Dr.first_name last_name dept 0 appointment_time1 appointment_time2.......");
                String details = sc.nextLine();
                this.addDoctor(details);
            } else {
                if (line.equals("1")) {
                    this.bookingMenu();
                    String choice = sc.nextLine();
                    this.book(choice);
                } else {
                    if (line.equals("3")) {
                        break;
                    }
                }
            }
            this.menu();
            line = sc.nextLine();
        }
        sc.close();
    }

    public void loading() {
        System.out.println("Loading..... Please Wait");
        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 200000000; j++) {
                for (int k = 0; k < 2000; k++) {

                }
            }
            System.out.print("#");
        }
        System.out.println("#");
        System.out.println("Welcome to Dipsikha Care");
    }

    public static void main(String[] args) {
        Hospital mHospital = new Hospital();
        mHospital.loading();
        mHospital.run();

    }
}

class Doctor {
    private String[] details;
    private int apmt;
    private int maxA;

    public Doctor(String docInput) {
        details = docInput.split(" ");
        apmt = Integer.parseInt(details[3]);
        maxA = details.length;
    }

    public String feedDetails() {
        String detailed = "";
        for (int i = 0; i < details.length; i++) {
            if (i == 3) {
                detailed += apmt + " ";
            } else {
                if (i == maxA - 1) {
                    detailed += details[i];
                } else {
                    detailed += details[i] + " ";
                }
            }
        }
        return detailed;
    }

    public String name() {
        return details[0] + " " + details[1];
    }

    public String department() {
        return details[2];
    }

    public boolean isAvailable() {
        if (apmt + 4 < maxA) {
            return true;
        }
        return false;
    }

    public String bookNext() {
        return details[4 + apmt++];
    }
}