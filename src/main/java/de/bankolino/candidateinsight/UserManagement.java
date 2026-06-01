package de.bankolino.candidateinsight;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of user management.
 * Production version would persist users to Bankolino's identity store
 * and delegate authentication to the IAM system (single sign-on).
 */
public class UserManagement implements IUserManagement {

    /**
     * In-memory user store: email -> User.
     * In production: backed by LDAP or a relational database.
     */
    private final Map<String, User> usersByEmail = new HashMap<String, User>();

    /**
     * Creates and stores a new user of the appropriate subtype.
     *
     * <p>Hint: use a switch statement on {@code role} to create
     * either a {@link HiringManager} or an {@link HRAdmin}.
     * Throw {@link IllegalArgumentException} if the email is already taken.</p>
     *
     * @param name     first name
     * @param surname  last name
     * @param email    unique email (login identifier)
     * @param password initial password
     * @param role     determines which User subclass to create
     * @return the newly created user
     * @throws IllegalArgumentException if the email is already registered
     */
    @Override
    public User registerUser(String name, String surname, String email,
                             String password, Role role) {
        // TODO [Unit03 - Step 1a]: Implement user registration.
        //   1. Check if email is already in usersByEmail; if so, throw IllegalArgumentException.
        //   2. Use a switch on role to create HiringManager or HRAdmin.
        //   3. Put the new user in usersByEmail.
        //   4. Print a confirmation to stdout (e.g., "[UserManagement] Registered: " + user).
        //   5. Return the new user.
        // throw new UnsupportedOperationException("Not yet implemented");
        if (usersByEmail.containsKey(email)) {
            throw new IllegalArgumentException("Email already registered: " + email);
        }
        User user;
        switch (role) {
            case HIRING_MANAGER:
                user = new HiringManager(name, surname, email, password);
                break;
            case HR_ADMIN:
                user = new HRAdmin(name, surname, email, password);
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
        usersByEmail.put(email, user);
        System.out.println("[UserManagement] Registered: " + user);
        return user;
    }

    /**
     * Validates credentials and returns the matching User.
     *
     * <p>Hint: look up the user by {@code credentials.getEmail()}, then compare
     * the stored password with {@code credentials.getPassword()}.</p>
     *
     * @param credentials the login credentials to verify
     * @return the authenticated user
     * @throws IllegalArgumentException if email is unknown or password is incorrect
     */
    @Override
    public User authenticate(Credentials credentials) {
        // TODO [Unit03 - Step 1b]: Implement authentication.
        //   1. Look up the user by credentials.getEmail() in usersByEmail.
        //   2. If not found or password does not match, throw IllegalArgumentException.
        //   3. Print a confirmation to stdout (e.g., "[UserManagement] Authenticated: " + user).
        //   4. Return the authenticated user.
        // throw new UnsupportedOperationException("Not yet implemented");
        User user = usersByEmail.get(credentials.getEmail());
        if (user == null || !user.getPassword().equals(credentials.getPassword())) {
            throw new IllegalArgumentException(
                    "Invalid credentials for: " + credentials.getEmail());
        }
        System.out.println("[UserManagement] Authenticated: " + user);
        return user;
    }
}
