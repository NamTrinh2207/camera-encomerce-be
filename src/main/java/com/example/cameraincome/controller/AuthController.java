package com.example.cameraincome.controller;

import com.example.cameraincome.model.DTO.ICountRole;
import com.example.cameraincome.model.DTO.JwtResponse;
import com.example.cameraincome.model.DTO.request.SignInForm;
import com.example.cameraincome.model.DTO.request.SignUpForm;
import com.example.cameraincome.model.DTO.response.Message;
import com.example.cameraincome.model.user.Roles;
import com.example.cameraincome.model.user.Users;
import com.example.cameraincome.service.jwt.JwtService;
import com.example.cameraincome.service.role.IRoleService;
import com.example.cameraincome.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //create user
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpForm user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>(new Message("Tên tài khoản đã tồn tại"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(new Message("Email đã tồn tại"), HttpStatus.BAD_REQUEST);
        }
        Users users = new Users(user.getName(), user.getPhone(),user.getEmail(),user.getAddress(),
                user.getAvatar(),user.getUsername(),passwordEncoder.encode(user.getPassword()));
        Set<String> roleNames = user.getRoles();
        Set<Roles> roles = roleService.getRolesByName(roleNames);
        users.setRoleSet(roles);
        users.setAvatar("https://cdn-icons-png.flaticon.com/512/149/149071.png");
        userService.save(users);
        return new ResponseEntity<>(new Message("Tạo tài khoản thành công"), HttpStatus.OK);
    }

    /**
     * Xác thực thông tin đăng nhập và tạo JWT token cho người dùng.
     *
     * @param user thông tin đăng nhập của người dùng
     * @return ResponseEntity chứa thông tin JWT token và thông tin người dùng
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody SignInForm user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Users currentUser = userService.findByUsername(user.getUsername());
        JwtResponse jwtResponse = new JwtResponse(jwt,currentUser.getId(), currentUser.getName(),
                currentUser.getAvatar(), currentUser.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.ok(jwtResponse);
    }


    //edit user
    @PutMapping("/{id}")
    public ResponseEntity<Users> update(@PathVariable Long id, @RequestBody Users user) {
        Optional<Users> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            user.setId(id);
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/admin/hello")
    public ResponseEntity<Iterable<ICountRole>> hello() {
        Iterable<ICountRole> iCountRoles = userService.getRoleNumber();
        return new ResponseEntity<>(iCountRoles, HttpStatus.OK);
    }

    @GetMapping("/user/hello")
    public ResponseEntity<String> user() {
        return new ResponseEntity<>("User", HttpStatus.OK);
    }

}
