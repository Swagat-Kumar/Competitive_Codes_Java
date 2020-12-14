import java.io.*;
import java.util.*;

public class Hospital {
    public void addPatient(String details) {
        try {
            PrintWriter patWriter = new PrintWriter(new FileOutputStream(new File("Patient.txt"), true));
            patWriter.println(details);
            patWriter.flush();
            patWriter.close();
            System.out.println();
            System.out.println("Patient Registered Successfully!");
            System.out.println();
        } catch (IOException e) {
            System.out.println("Please Create Patient.txt file in default directory");
        }
    }

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

    public void showAppointment(String id) throws IOException {
        Scanner sc = new Scanner(new File("Patient.txt"));
        String oldContent = "";
        boolean found = false;
        while (sc.hasNextLine()) {
            Patient p = new Patient(sc.nextLine());
            if (!found) {
                if (p.getId().equals(id)) {
                    p.appointments();
                    found = true;
                }
            }
            oldContent += p.feedDetails() + "\n";
        }
        if (!found) {
            System.out.println("We don't have Patients for id: " + id);
        }
        PrintWriter docWriter = new PrintWriter(new File("Patient.txt"));
        docWriter.write(oldContent);
        docWriter.flush();
        docWriter.close();
        sc.close();

    }

    public String bookAppointment(String Dept) throws IOException {
        Dept = Dept.toLowerCase();
        Scanner sc = new Scanner(new File("Doctor.txt"));
        String oldContent = "";
        boolean booked = false;
        String bookingDetails = "";
        String returnDetails = "";
        while (sc.hasNextLine()) {
            Doctor d = new Doctor(sc.nextLine());
            if (!booked) {
                if (d.department().equals(Dept)) {
                    if (d.isAvailable()) {
                        String bn = d.bookNext();
                        bookingDetails += d.name() + " | Appointment Time: " + bn;
                        returnDetails += d.name() + " " + bn;
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
        return returnDetails;
    }

    private void menu() {
        System.out.println();
        System.out.println("| Main Menu |");
        System.out.println("1. Book Appointment");
        System.out.println("2. Add Doctor");
        System.out.println("3. Add Patient");
        System.out.println("4. Show Appointments From Patient_ID");
        System.out.println("5. Exit");
        System.out.println("Enter Choice :");
    }

    private String book(String choice) {
        this.bookingMenu();
        String returnBook = "";
        try {
            switch (choice) {
                case "1":
                    returnBook = this.bookAppointment("Skin");
                    break;
                case "2":
                    returnBook = this.bookAppointment("Paediatric");
                    break;
                case "3":
                    returnBook = this.bookAppointment("Heart");
                    break;
                case "4":
                    returnBook = this.bookAppointment("Gyna");
                    break;
                default:
                    System.out.println("No Such Department Exists");
            }
        } catch (IOException e) {
            System.out.println("Please Create Doctor.txt file in default directory");
        }
        return returnBook;
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

    public boolean checkPatientExists(String id) throws IOException {
        Scanner sc = new Scanner(new File("Patient.txt"));
        boolean found = false;
        while (sc.hasNextLine()) {
            Patient p = new Patient(sc.nextLine());
            if (!found) {
                if (p.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addToPatient(String id, String bookPassed) {
        try {
            Scanner sc = new Scanner(new File("Patient.txt"));
            String oldContent = "";
            String pdetails = "";
            while (sc.hasNextLine()) {
                Patient p = new Patient(sc.nextLine());
                pdetails = p.feedDetails();
                if (p.getId().equals(id)) {
                    pdetails += " " + bookPassed;
                }
                oldContent += pdetails + "\n";
            }
            PrintWriter docWriter = new PrintWriter(new File("Patient.txt"));
            docWriter.write(oldContent);
            docWriter.flush();
            docWriter.close();
            sc.close();
        } catch (IOException e) {
            System.out.println("Please Create Patient.txt file in default directory");
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        this.menu();
        String line = sc.nextLine();
        while (!line.equals("5")) {
            if (line.equals("2")) {
                System.out.println("Dr.first_name last_name dept 0 appointment_time1 appointment_time2.......");
                String details = sc.nextLine();
                this.addDoctor(details);
            } else {
                if (line.equals("1")) {
                    this.bookingMenu();
                    String choice = sc.nextLine();
                    System.out.println("Enter Patient_ID :");
                    String id = sc.nextLine();
                    String returnBook = "";
                    try {
                        if (checkPatientExists(id)) {
                            returnBook = this.book(choice);
                        } else {
                            System.out.println("");
                            System.out.println("No Such Patient Exists");
                            System.out.println("");
                        }
                    } catch (IOException e) {
                        System.out.println("Please Create Patient.txt file in default directory");
                    }
                    if (!returnBook.equals("")) {
                        this.addToPatient(id, returnBook);
                    }
                } else {
                    if (line.equals("3")) {
                        System.out.println("Patient_ID First Name Doctorn Timen....");
                        String details = sc.nextLine();
                        this.addPatient(details);
                    } else {
                        if (line.equals("4")) {
                            System.out.println("Enter Patient_ID :");
                            String id = sc.nextLine();
                            try {
                                this.showAppointment(id);
                            } catch (IOException e) {
                                System.out.println("Please Create Patient.txt file in default directory");
                            }
                        } else {
                            if (line.equals("5")) {
                                break;
                            }
                        }
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

class Patient {
    private String original;
    private String[] details;
    private final String id;
    private String name;

    public Patient(String patInput) {
        original = patInput;
        details = patInput.split(" ");
        id = details[0];
        name = details[1] + " " + details[2];
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void appointments() {
        System.out.println();
        System.out.println("The Appointments booked for " + this.getName() + " :");
        System.out.println();
        boolean zero = false;
        for (int i = 3; i < details.length; i += 3) {
            System.out.println(details[i] + " " + details[i + 1] + " : " + details[i + 2]);
            zero = true;
        }
        if (!zero) {
            System.out.println("");
            System.out.println("No Appointments Booked Yet!");
            System.out.println("");
        }
    }

    public String feedDetails() {
        return original;
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