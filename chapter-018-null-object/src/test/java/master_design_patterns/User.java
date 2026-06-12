package master_design_patterns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

interface UseRepository {

    User getUserById(long userId);
}

@Getter
public class User {
    private final String username;
    private final String displayName;
    private final String password;
    // Các trường khác.

    public User(
        String username,
        String displayName,
        String password
    ) {
        this.username = username;
        this.displayName = displayName;
        this.password = password;
    }

    public static final User DEFAULT_USER =
        new User("", "", "");

    public static void main(String[] args) {
        UseRepository useRepository = new UseRepository() {
            @Override
            public User getUserById(long userId) {
                return null;
            }
        };
        User user = useRepository.getUserById(1L);
        if (user == null) {
            user = DEFAULT_USER;
        }
        String username = Optional
            .ofNullable(useRepository.getUserById(1L))
            .orElse(DEFAULT_USER)
            .getDisplayName();
    }
}

class NullUser extends User {

    private static final NullUser INSTANCE =
        new NullUser();

    public NullUser() {
        super("", "", "");
    }

    public static NullUser getInstance() {
        return INSTANCE;
    }
}