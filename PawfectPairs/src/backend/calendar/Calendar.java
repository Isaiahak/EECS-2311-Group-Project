package backend.calendar;
import backend.user.*;
import java.util.Date;
import backend.dog.*;

public class Calendar {
	private User user;
    private Date appointmentTime;
    private Dog dog;

    public Calendar(User user, Date appointmentTime, Dog dog) {
        this.user = user;
        this.appointmentTime = appointmentTime;
        this.dog = dog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter and setter methods for appointmentTime
    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

}
