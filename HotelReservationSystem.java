import java.util.*;

class Room {
    private int roomId;
    private String category;
    private double price;
    private boolean isAvailable;
    public Room(int roomId, String category) {
        this.roomId = roomId;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }
    public int getRoomId() {
        return roomId;
    }
    public String getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailability(boolean availability) {
        isAvailable = availability;
    }
}
class Reservation {
    private int reservationId;
    private int userId;
    private int roomId;
    private double price;
    private Date startDate;
    private Date endDate;
    public Reservation(int reservationId, int userId,int roomId, Date startDate, Date endDate) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.roomId = roomId;
        
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public int getUserId() {
        return userId;
    }
    public int getRoomId() {
        return roomId;
    }
    public int getReservationId()
    {
        return reservationId;
    }
   
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
}
class BookingSystem {
    private List<Room> rooms;
    private List<Reservation> reservations;
    public BookingSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(int roomId, String category) {
        rooms.add(new Room(roomId, category));
    }
    public List<Room> searchAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    public boolean makeReservation(int userId, int roomId,Date startDate, Date endDate) {
        double totalPrice = 2000;
         for (Room room : rooms) {
            if (room.getRoomId() == roomId && room.isAvailable()) {
                totalPrice = room.getPrice();
                reservations.add(new Reservation(reservations.size() + 1, userId, roomId, startDate, endDate));
                room.setAvailability(false);
                System.out.println("Payment of Rs." + totalPrice + " processed successfully.");
                return true;
            }
        }
        return false;
    }
    public List<Reservation> getBookingDetails(int userId) {
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getUserId() == userId) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }
}
public class HotelReservationSystem {
    public static void main(String[] args) {
        BookingSystem bookingSystem = new BookingSystem();
        Scanner scanner = new Scanner(System.in);
        bookingSystem.addRoom(1, "Single");
        bookingSystem.addRoom(2, "Double");
        bookingSystem.addRoom(3, "Single");
        bookingSystem.addRoom(4, "Double");
        while (true) {
            System.out.println("Welcome to the Hotel Reservation System!");
            System.out.println("1. Search available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Single/Double): ");
                    String category = scanner.nextLine();
                    List<Room> availableRooms = bookingSystem.searchAvailableRooms(category);
                    System.out.println("Available " + category + " Rooms:");
                    for (Room room : availableRooms) {
                        System.out.println("Room ID: " + room.getRoomId() + ", Category: " + room.getCategory());
                    }
                    break;
                case 2:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter room ID for reservation: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    String startDateStr = scanner.nextLine();
                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    String endDateStr = scanner.nextLine();
                    Date startDate = new Date();
                    Date endDate = new Date();
                    boolean reservationSuccess = bookingSystem.makeReservation(userId,roomId, startDate, endDate);
                    if (reservationSuccess) {
                        System.out.println("Reservation successful!");
                    } else {
                        System.out.println("Reservation failed. Room not available.");
                    }
                    break;
                case 3:
                    System.out.print("Enter user ID to view bookings: ");
                    int viewUserId = scanner.nextInt();
                    List<Reservation> userBookings = bookingSystem.getBookingDetails(viewUserId);
                    System.out.println("User " + viewUserId + "'s Bookings:");
                    for (Reservation booking : userBookings) {
                        System.out.println("Reservation ID: " + booking.getReservationId() +
                                ", Room ID: " + booking.getRoomId() +
                                ", Start Date: " + booking.getStartDate() +
                                ", End Date: " + booking.getEndDate());
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}