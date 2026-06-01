package de.bankolino.candidateinsight;

/**
 * Abstract base class for all CandidateInsight users.
 * Shared attributes (name, email, role) are defined here to avoid duplication.
 * Concrete subclasses (HiringManager, HRAdmin) may add role-specific behaviour
 * in later iterations.
 */
public abstract class User {

    protected final String name;
    protected final String surname;
    protected final String email;
    protected final String password;
    protected final Role role;

    /**
     * @param name     first name
     * @param surname  last name
     * @param email    unique email address (used as login identifier)
     * @param password user's password
     * @param role     role assigned to this user
     */
    protected User(String name, String surname, String email, String password, Role role) {
        this.name     = name;
        this.surname  = surname;
        this.email    = email;
        this.password = password;
        this.role     = role;
    }

    /** @return first name */
    public String getName()    { return name; }

    /** @return last name */
    public String getSurname() { return surname; }

    /** @return email (login identifier) */
    public String getEmail()   { return email; }

    /** @return role of this user */
    public Role getRole()      { return role; }

    /**
     * Returns password for authentication use only.
     * Package-private: only UserManagement should call this.
     */
    String getPassword() { return password; }

    @Override
    public String toString() {
        return name + " " + surname + " [" + role + "]";
    }
}
