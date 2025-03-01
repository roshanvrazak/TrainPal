package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.CORBA.Object;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {

    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH = "../localDb/users.json";

    // Constructor initialising User logged in
    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }


    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {
        });
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }


    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings() {
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        if (userFetched.isPresent()) {
            userFetched.get().printTickets();
        }
    }

    public Boolean cancelBooking(String ticketId) {
        // todo: Complete this function
        public Boolean cancelBooking (String ticketId){

            Scanner s = new Scanner(System.in);
            System.out.println("Enter the ticket id to cancel");
            ticketId = s.next();

            if (ticketId == null || ticketId.isEmpty()) {
                System.out.println("Ticket ID cannot be null or empty.");
                return Boolean.FALSE;
            }

            String finalTicketId1 = ticketId;  //Because strings are immutable
            boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(finalTicketId1));

            String finalTicketId = ticketId;
            user.getTicketsBooked().removeIf(Ticket -> Ticket.getTicketId().equals(finalTicketId));
            if (removed) {
                System.out.println("Ticket with ID " + ticketId + " has been canceled.");
                return Boolean.TRUE;
            } else {
                System.out.println("No ticket found with ID " + ticketId);
                return Boolean.FALSE;
            }
        }

    }
}
