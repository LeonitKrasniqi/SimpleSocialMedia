package cacttus.simplesocialmedia.service;

import cacttus.simplesocialmedia.config.JwtService;
import cacttus.simplesocialmedia.dto.AuthenticationRequestDto;
import cacttus.simplesocialmedia.dto.AuthenticationResponseDto;
import cacttus.simplesocialmedia.model.Role;
import cacttus.simplesocialmedia.model.User;
import cacttus.simplesocialmedia.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponseDto register(AuthenticationRequestDto dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First Name is required");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }
        var user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .role(Role.USER)
                .build();
        user = userRepository.save(user);
        return AuthenticationResponseDto.builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }

    public AuthenticationResponseDto login(AuthenticationRequestDto dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getPassword()
        ));
        var user = userRepository.findByEmail(dto.getEmail());
        if (user.isPresent()) {
            return AuthenticationResponseDto.builder()
                    .token(jwtService.generateToken(user.get()))
                    .email(user.get().getEmail())
                    .id(user.get().getId())
                    .build();
        }

        return AuthenticationResponseDto.builder().build();

    }

    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    public void changePassword(Integer Id, String currentPassword, String newPassword) {

        User user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                currentPassword
        ));
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("New password is required");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public User getUserByID(Integer id) {
        var user = userRepository.findById(id);
        return user.orElse(null);
    }
}






