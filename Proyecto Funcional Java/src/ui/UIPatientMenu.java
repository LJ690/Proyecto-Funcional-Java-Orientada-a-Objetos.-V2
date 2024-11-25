package ui;

import Model.Doctor;

import java.util.*;

public class UIPatientMenu {

    public static void showPatientMenu(){
        int response = 0;
        do {

            System.out.println("n");
            System.out.println("Patient");
            System.out.println("Welcome: " + UIMenu.patinetLogged.getName());
            System.out.println("1. Boock an Appointment");
            System.out.println("2. My Appointments");
            System.out.println("0. Logout");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    showBookAppointmentMenu();
                    break;
                case 2:
                    showPatientMyAppointments();
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }

        }while (response!=0);
    }

    private static void showBookAppointmentMenu(){
        int response = 0;
        do {
            System.out.println("::Book an Appointment");
            System.out.println(":: Select date: ");
            System.out.println("");
            //Numeraciòn de la lista de fechas
            //Induce de la fecha selecionada
            //[doctors]
            // 1.- doctor1
                // -1 fecha1
                // -2 fecha2
            // 2.- doctor2
            // 3.- doctor3
            Map<Integer, Map<Integer, Doctor>> doctors = new TreeMap<>();
            int k = 0;
            for (int i = 0; i < UIDoctorMenu.doctorsAvailableAppointments.size(); i++){
                ArrayList<Doctor.AvailableAppointment> availableAppointments = UIDoctorMenu.doctorsAvailableAppointments.get(i).getAvailableAppointments();

                Map<Integer, Doctor> doctorAppointments = new TreeMap<>();

                for (int j = 0; j < availableAppointments.size(); j++) {
                    k++;
                    System.out.println(k + ". " + availableAppointments.get(j).getDate());
                    doctorAppointments.put(Integer.valueOf(j), UIDoctorMenu.doctorsAvailableAppointments.get(i));

                    doctors.put(Integer.valueOf(k), doctorAppointments);
                }
            }
            Scanner sc = new Scanner(System.in);
            int responseDateSelected = Integer.valueOf(sc.nextLine());
            Map<Integer, Doctor> doctorAvailableSelected = doctors.get(responseDateSelected);
            Integer indexDate = 0;
            Doctor doctorSelected = new Doctor("", "");

            for (Map.Entry<Integer, Doctor> doc :doctorAvailableSelected.entrySet()) {
                indexDate =  doc.getKey();
                doctorSelected = doc.getValue();
            }

            System.out.println(doctorSelected.getName() +
                    ". Date: " +
                    doctorSelected.getAvailableAppointments().get(indexDate).getDate() +
                    ". Time: " +
                    doctorSelected.getAvailableAppointments().get(indexDate).getTime());

            System.out.println("Confirm your appointment: \n1. Yes \n2. Change Data");
            response = Integer.valueOf(sc.nextLine());

            if (response == 1){
                UIMenu.patinetLogged.addAppointmentDoctors(
                        doctorSelected,
                        doctorSelected.getAvailableAppointments().get(indexDate).getDate(null),
                        doctorSelected.getAvailableAppointments().get(indexDate).getTime());

                showPatientMenu();

            }



        }while (response!= 0);
    }

    private static void showPatientMyAppointments(){
        int response = 0;
        do{
            System.out.println("::My Appointments");
            if (UIMenu.patinetLogged.getAppointmentDoctors().size() == 0){
                System.out.println("Don't have appointments");
                break;
            }
            for (int i = 0; i < UIMenu.patinetLogged.getAppointmentDoctors().size(); i++){
                int j = i + 1;
                System.out.println(j + ". " + "Date: " + UIMenu.patinetLogged.getAppointmentDoctors().get(i).getDate() + " Time: " + UIMenu.patinetLogged.getAppointmentDoctors().get(i).getTime() + "\nDoctor: " + UIMenu.patinetLogged.getAppointmentDoctors().get(i).getDoctor().getName());
            }

            System.out.println("0. Return");

        }while (response!=0);
    }
}
