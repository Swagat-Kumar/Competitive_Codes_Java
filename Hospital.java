import java.io.*;
import java.util.*;

public class Hospital {
    public void addDoctor(String details) {
        System.out.println("Dr.first_name last_name dept 0 appointment_time1 appointment_time2.......");
        try {
            PrintWriter docWriter = new PrintWriter(new FileOutputStream(new File("Doctor.txt"), true));
            docWriter.println(details);
            docWriter.flush();
            docWriter.close();
        } catch (IOException e) {
            System.out.println("Please Create Doctor.txt file in default directory");
        }
    }

    public void bookAppointment(String Dept) throws IOException {
        Dept = Dept.toLowerCase();
        Scanner sc = new Scanner(new File("Doctor.txt"));
        String oldContent = "";
        boolean booked = false;
        while (sc.hasNextLine()) {
            Doctor d = new Doctor(sc.nextLine());
            System.out.println(d.feedDetails());
            if (!booked) {
                if (d.department().equals(Dept)) {
                    if (d.isAvailable()) {
                        System.out.println(d.bookNext());
                        booked = true;
                    }
                }
            }
            oldContent += d.feedDetails() + "\n";
        }
        PrintWriter docWriter = new PrintWriter(new File("Doctor.txt"));
        docWriter.write(oldContent);
        docWriter.flush();
        docWriter.close();
        sc.close();
    }

    public static void main(String[] args) {
        Hospital mHospital = new Hospital();
        try {
            mHospital.bookAppointment("Heart");
        } catch (IOException e) {
            System.out.println("Please Create Doctor.txt file in default directory");
        }
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
        if (apmt + 5 < maxA) {
            return true;
        }
        return false;
    }

    public String bookNext() {
        return details[5 + apmt++];
    }
}