package ra.securities.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ra.securities.dto.requset.SignInForm;
import ra.securities.dto.requset.SignUpForm;
import ra.securities.dto.response.JwtResponse;
import ra.securities.dto.response.ResponseMessage;
import ra.securities.model.Role;
import ra.securities.model.RoleName;
import ra.securities.model.User;
import ra.securities.security.jwt.JwtProvider;
import ra.securities.security.userPrincipal.UserPrincipal;
import ra.securities.service.role.IRoleService;
import ra.securities.service.user.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> doSignUp(@RequestBody SignUpForm signUpForm) {
        boolean isExistUsername = userService.existsByUsername(signUpForm.getUsername());
        boolean isExistEmail = userService.existsByEmail(signUpForm.getEmail());
        if (isExistUsername) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    ResponseMessage.builder()
                            .status("FAILED").message("Đã tồn tại người dùng, vui lòng chọn tên đăng nhập khác")
                            .data("").build()
            );
        }
        if (isExistEmail) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    ResponseMessage.builder()
                            .status("FAILED").message("Đã tồn tại người dùng, vui lòng nhập email khác")
                            .data("").build()
            );
        }
        Set<Role> roles = new HashSet<>();
        if (signUpForm.getRoles() == null || signUpForm.getRoles().isEmpty()) {
            Role role = roleService.findByName(RoleName.USER)
                    .orElseThrow(() -> new RuntimeException("Failed -> NOT FOUND ROLE"));
            roles.add(role);
        } else {
            signUpForm.getRoles().forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(RoleName.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Failed -> NOT FOUND ROLE"));
                        roles.add(adminRole);
                    case "pm":
                        Role pmRole = roleService.findByName(RoleName.PM)
                                .orElseThrow(() -> new RuntimeException("Failed -> NOT FOUND ROLE"));
                        roles.add(pmRole);
                    case "user":
                        Role userRole = roleService.findByName(RoleName.USER)
                                .orElseThrow(() -> new RuntimeException("Failed -> NOT FOUND ROLE"));
                        roles.add(userRole);
                }
            });
        }
        User user = User.builder()
                .name(signUpForm.getName())
                .username(signUpForm.getUsername())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .email(signUpForm.getEmail())
                .avatar(signUpForm.getAvatar())
                .address(signUpForm.getAddress())
                .phoneNumber(signUpForm.getPhoneNumber())
                .roles(roles)
                .build();

        return ResponseEntity.ok().body(
                ResponseMessage.builder()
                        .status("OK")
                        .message("Account created successfully!")
                        .data(userService.save(user))
                        .build()
        );
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> doSignIn(@RequestBody SignInForm signInForm) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            signInForm.getUsername(),
                            signInForm.getPassword())
                    );

            String token = jwtProvider.generateToken(authentication);
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            return new ResponseEntity<>(
                    JwtResponse.builder()
                            .status("OK")
                            .type("Bearer")
                            .name(userPrincipal.getName())
                            .email(userPrincipal.getEmail())
                            .address(userPrincipal.getAddress())
                            .phoneNumber(userPrincipal.getPhoneNumber())
                            .token(token)
                            .roles(userPrincipal.getAuthorities())
                            .build(), HttpStatus.OK);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .status("Failed")
                            .message("Invalid username or password!")
                            .data("")
                            .build(), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/show-all-user")
    public List<User> findAll(){
        return userService.findAll();
    }
}
