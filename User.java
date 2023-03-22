import org.json.simple.JSONAware;

import java.time.LocalDate;
import java.util.UUID;

/*
 * Basic abstract user to provide functionality to teachers and students
 */
public class User implements JSONAware {
    public UUID getUUID() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClearance() {
        return clearance;
    }

    public void setClearance(String clearance) {
        this.clearance = clearance;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
// changed from private final UUID uuid to private UUID uuid 
    private UUID uuid;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;


    private String email;
    private String phoneNumber;
    private String clearance;

    /**
     * Constructor meant to be called when loading user from existing data
     *
     * 3/21/23 changed to void type so the data can be entered without expecting any return 
     * 
     * @param uuid        UserUniqueIdentifier, the unique string used to differentiate users
     * @param username    Username the user uses to log in
     * @param password    Password the user uses to log in
     * @param firstName   User's first name
     * @param lastName    User's last name
     * @param email       User's email address
     * @param phoneNumber User's phone number
     * @param clearance   User's clearance level
     */
    public User(UUID uuid, String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.clearance = clearance;
    }


    /**
     * Constructor meant to be called when creating a new user
     * Generates a new UUID and assigns it on construction
     *
     * 3/21/23 changed to void type so the data can be entered without expecting any return 
     * 
     * @param username    Username the user uses to log in
     * @param password    Password the user uses to log in
     * @param firstName   User's first name
     * @param lastName    User's last name
     * @param email       User's email address
     * @param phoneNumber User's phone number
     * @param clearance   User's clearance level
     */
    public User(String username, String password, String firstName, String lastName, String email, String phoneNumber, String clearance) {
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.clearance = clearance;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", clearance='" + clearance + '\'' +
                '}';
    }

    private void appendToStringBuilderJSONStyle(String key, String value, StringBuilder sb) {
        // looks like "key":"value"
        sb.append('"');
        sb.append(key);
        sb.append('"');
        sb.append(":");
        sb.append('"');
        sb.append(value);
        sb.append('"');
    }

    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');

        appendToStringBuilderJSONStyle("firstName", firstName, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("lastName", lastName, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("email", email, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("phoneNumber", phoneNumber, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("username", username, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("password", password, sb);
        sb.append(',');
        appendToStringBuilderJSONStyle("clearance", clearance, sb);

        sb.append('}');

        return sb.toString();
    }

    /**
     * Wrapper function to prevent confusion, since other classes use a similar call to
     * create JSONAware objects containing only the data they need, whilst User itself is JSONAware
     * It is good practice to call this instead of the user itself.
     *
     * @return An instance of self.
     */
    public JSONAware getUserData() {
        return this;
    }
}
