package org.youngmonkeys.cursor;

public class UserModel {
    private long id;
    private String username;
    private String displayName;
    private String email;
    private String phoneNumber;
    private int age;
    private boolean isActive;
    private String address;

    // Private constructor to enforce builder usage
    private UserModel() {}

    // Getters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getAddress() {
        return address;
    }

    // Builder class
    public static class Builder {
        private UserModel userModel;

        public Builder() {
            userModel = new UserModel();
        }

        public Builder id(long id) {
            userModel.id = id;
            return this;
        }

        public Builder username(String username) {
            userModel.username = username;
            return this;
        }

        public Builder displayName(String displayName) {
            userModel.displayName = displayName;
            return this;
        }

        public Builder email(String email) {
            userModel.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            userModel.phoneNumber = phoneNumber;
            return this;
        }

        public Builder age(int age) {
            userModel.age = age;
            return this;
        }

        public Builder isActive(boolean isActive) {
            userModel.isActive = isActive;
            return this;
        }

        public Builder address(String address) {
            userModel.address = address;
            return this;
        }

        public UserModel build() {
            // Validation can be added here
            if (userModel.username == null || userModel.username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be null or empty");
            }
            if (userModel.email == null || userModel.email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }
            return userModel;
        }
    }

    // Static method to create a new builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                ", address='" + address + '\'' +
                '}';
    }
}
