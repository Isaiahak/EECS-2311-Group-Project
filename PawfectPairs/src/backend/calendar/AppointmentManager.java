package backend.calendar;

import java.util.ArrayList;

public class AppointmentManager {
private int userID;
private ArrayList <Appointment> userAppointments = new ArrayList<>();

public AppointmentManager(int userID, ArrayList<Appointment> userAppointments) {
	super();
	this.userID = userID;
	this.userAppointments = userAppointments;
}

public void reset() {
	this.userAppointments.clear();
}

public int getUserID() {
	return userID;
}

public void setUserID(int userID) {
	this.userID = userID;
}

public ArrayList<Appointment> getUserAppointments() {
	return userAppointments;
}

public void setUserAppointments(ArrayList<Appointment> userAppointments) {
	this.userAppointments = userAppointments;
}

public void addAppointment (Appointment appointment) {
	this.userAppointments.add(appointment);
}

public void removeAppointment(Appointment appointment) {
	this.userAppointments.remove(appointment);
	
}


public boolean appointmentExists(Appointment appointment) {
	int posterID = appointment.getPosterID();
    int dogID = appointment.getDogID();
    
    for (Appointment existingAppointment : this.userAppointments) {
        if (existingAppointment.getPosterID() == posterID && existingAppointment.getDogID() == dogID) {
        	return true;
        }
    }
    
    return false;
	
}
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("User ID: ").append(userID).append("\n");
    sb.append("User Appointments:\n");
    for (Appointment appointment : userAppointments) {
        sb.append(appointment.toString()).append("\n");
    }
    return sb.toString();
}
public boolean contains(Appointment currentAppointment) {
	return userAppointments.contains(currentAppointment);
}
}
