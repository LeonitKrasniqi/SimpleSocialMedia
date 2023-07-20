package cacttus.simplesocialmedia.controllers;

import cacttus.simplesocialmedia.dto.AuthenticationRequestDto;
import cacttus.simplesocialmedia.dto.AuthenticationResponseDto;
import cacttus.simplesocialmedia.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody AuthenticationRequestDto requestDto) {
        AuthenticationResponseDto responseDto = service.register(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        AuthenticationResponseDto responseDto = service.login(requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            service.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete user: " + e.getMessage());
        }
    }
    @PatchMapping("/{Id}")
    public ResponseEntity<String> changePassword(@PathVariable Integer Id,
                                                 @RequestBody Map<String, String> request) {
        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("newPassword");

        try {
            service.changePassword(Id, currentPassword, newPassword);
            return ResponseEntity.ok("Password changed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to change password: " + e.getMessage());
        }
    }



}


