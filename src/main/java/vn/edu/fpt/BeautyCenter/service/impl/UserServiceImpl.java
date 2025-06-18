package vn.edu.fpt.BeautyCenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import vn.edu.fpt.BeautyCenter.entity.PasswordResetToken;
import vn.edu.fpt.BeautyCenter.entity.User;
import vn.edu.fpt.BeautyCenter.entity.enums.Role;
import vn.edu.fpt.BeautyCenter.entity.enums.UserStatus;
import vn.edu.fpt.BeautyCenter.repository.PasswordResetTokenRepository;
import vn.edu.fpt.BeautyCenter.repository.UserRepository;
import vn.edu.fpt.BeautyCenter.service.EmailService;
import vn.edu.fpt.BeautyCenter.service.UserService;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public void registerUser(String fullName, String username, String email, String phone, String password) {
        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole(Role.customer);
        user.setStatus(UserStatus.inactive);
        userRepository.save(user);
        emailService.sendVerificationEmail(email);
    }

    @Override
    public void verifyEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        user.setStatus(UserStatus.active);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void sendPasswordResetEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        String otp = generateOtp();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUser(user);
        passwordResetToken.setToken(otp);
        passwordResetToken.setUsed(false);
        passwordResetToken.setExpiration(LocalDateTime.now().plusMinutes(5)); // 5 phut
        passwordResetTokenRepository.save(passwordResetToken);
        emailService.sendPasswordResetEmail(email, otp);
    }

    @Override
    public User verifyOtp(String otp) {
        Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByToken(otp);
        if(passwordResetToken.isEmpty())
            return null;
        PasswordResetToken token = passwordResetToken.get();

        if (!token.isUsed() && token.getExpiration().isAfter(LocalDateTime.now())) {
            token.setUsed(true);
            passwordResetTokenRepository.save(token);
            return token.getUser();
        }
        return null;
    }

    @Override
    public void resetPassword(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        user.setPassword(password);
        userRepository.save(user);
    }

    private String generateOtp() {
        StringBuilder otp = new StringBuilder(6);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            otp.append(digit);
        }
        return otp.toString();
    }

    /**
     * Retrieves a mapping of user IDs to user names for all users who have created services.
     * <p>
     * This method builds a comprehensive map of service creators, providing both the unique
     * user identifier (key) and the human-readable display name (value). This map is primarily
     * used for populating author filter dropdowns and displaying creator information in service
     * listings. The method handles user data privacy and provides fallback values for cases
     * where user information might be incomplete or inaccessible.
     * </p>
     *
     * @return a map with user IDs as keys and user display names as values
     * @throws RuntimeException if critical errors occur during user data retrieval
     */
    public Map<String, String> getAllAuthorsMap() {
        try {
            // Initialize the result map to store user ID to name mappings
            Map<String, String> authorsMap = new HashMap<>();

            // Retrieve all unique user IDs who have created at least one service
            // This query focuses only on users who are actually service creators
            // to avoid cluttering the filter dropdown with inactive users
            List<String> serviceCreatorIds = userRepository.findDistinctServiceCreators();

            // Process each creator ID to build the display mapping
            for (String userId : serviceCreatorIds) {
                try {
                    // Attempt to retrieve user details by ID
                    Optional<User> userOpt = userRepository.findById(userId);

                    if (userOpt.isPresent()) {
                        User user = userOpt.get();

                        // Build display name with preference for full name over username
                        String displayName = buildUserDisplayName(user);

                        // Add the mapping to our result map
                        authorsMap.put(userId, displayName);

                    } else {
                        // Handle case where user ID exists in services but user is deleted
                        // This maintains referential integrity while providing meaningful display
                        authorsMap.put(userId, "Unknown User (ID: " + userId.substring(0, 8) + "...)");

                        // Log this situation for administrative awareness
                        System.err.println("Service creator user not found: " + userId);
                    }

                } catch (Exception e) {
                    // Handle individual user lookup failures without breaking entire operation
                    // This ensures robust operation even if some user records are corrupted
                    authorsMap.put(userId, "Error Loading User");
                    System.err.println("Error loading user details for ID " + userId + ": " + e.getMessage());
                }
            }

            // Log successful completion for monitoring purposes
            System.out.println("Successfully built authors map with " + authorsMap.size() + " entries");

            // Return the complete mapping for UI consumption
            return authorsMap;

        } catch (DataAccessException e) {
            // Handle database connectivity or query execution errors
            String errorMessage = "Database error while building authors map: " + e.getMessage();
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);

        } catch (Exception e) {
            // Handle any other unexpected errors during map building process
            String errorMessage = "Unexpected error while building authors map: " + e.getMessage();
            System.err.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    @Override
    public void updateUser(User sessionUser) {
        userRepository.save(sessionUser);
    }

    @Override
    public User getUserById(String userId) {
        User user= userRepository.findById(userId).orElse(null);
        return user;
    }

    /**
     * Builds a user-friendly display name from user entity information.
     * <p>
     * This helper method creates consistent display names for users across the application.
     * It prioritizes full names when available, falls back to usernames, and provides
     * meaningful defaults for incomplete user records. The method also handles privacy
     * considerations and data formatting for optimal UI presentation.
     * </p>
     *
     * @param user the user entity containing name and username information
     * @return a formatted display name suitable for UI presentation
     */
    private String buildUserDisplayName(User user) {
        // Initialize StringBuilder for efficient string construction
        StringBuilder displayName = new StringBuilder();

        // Check if user has first name and last name defined
        if (user.getFullName() != null) {

            // Build full name with proper spacing
            displayName.append(user.getFullName().trim());

            // Add username in parentheses for administrative clarity
            if (user.getUsername() != null && !user.getUsername().trim().isEmpty()) {
                displayName.append(" (").append(user.getUsername().trim()).append(")");
            }

        } else if (user.getUsername() != null && !user.getUsername().trim().isEmpty()) {
            // Fall back to username if full name is not available
            displayName.append(user.getUsername().trim());

        } else {
            // Ultimate fallback for incomplete user records
            displayName.append("User ID: ").append(user.getUserId().substring(0, 8)).append("...");
        }

        // Return the constructed display name
        return displayName.toString();
    }

    /**
     * Retrieves the display name for a specific user by their ID.
     * <p>
     * This method provides a convenient way to get a user's display name when only
     * the user ID is available. It's commonly used in service listings and detail
     * views where creator information needs to be displayed in a user-friendly format.
     * The method includes error handling to ensure application stability.
     * </p>
     *
     * @param userId the unique identifier of the user
     * @return the user's display name, or a fallback value if user not found
     */
    public String getUserName(String userId) {
        // Return fallback for null or empty user IDs
        if (userId == null || userId.trim().isEmpty()) {
            return "Unknown User";
        }

        try {
            // Attempt to retrieve user by ID
            Optional<User> userOpt = userRepository.findById(userId.trim());

            // Build and return display name for found user
            // Return meaningful fallback for missing user records
            return userOpt.map(this::buildUserDisplayName).orElseGet(() -> "Unknown User (ID: " + userId.substring(0, Math.min(8, userId.length())) + "...)");

        } catch (Exception e) {
            // Log error and return fallback to maintain application stability
            System.err.println("Error retrieving user name for ID " + userId + ": " + e.getMessage());
            return "Error Loading User";
        }
    }


}
