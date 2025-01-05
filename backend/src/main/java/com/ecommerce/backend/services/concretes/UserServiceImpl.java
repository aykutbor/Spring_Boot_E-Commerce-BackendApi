package com.ecommerce.backend.services.concretes;

import com.ecommerce.backend.core.services.JwtService;
import com.ecommerce.backend.core.utils.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.dtos.requests.AddressRequestDTO;
import com.ecommerce.backend.dtos.requests.LoginRequestDTO;
import com.ecommerce.backend.dtos.requests.UserRequestDTO;
import com.ecommerce.backend.dtos.responses.AddressResponseDTO;
import com.ecommerce.backend.dtos.responses.UserResponseDTO;
import com.ecommerce.backend.entities.Address;
import com.ecommerce.backend.entities.User;
import com.ecommerce.backend.mappers.AddressMapper;
import com.ecommerce.backend.mappers.UserMapper;
import com.ecommerce.backend.repositories.AddressRepository;
import com.ecommerce.backend.repositories.UserRepository;
import com.ecommerce.backend.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        User savedUser = userRepository.save(user);

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setFirstName(savedUser.getFirstName());
        responseDTO.setLastName(savedUser.getLastName());
        responseDTO.setEmail(savedUser.getEmail());

        return responseDTO;
    }

    @Override
    public UserResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("Email not found");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );

        String token = jwtService.generateToken(userDetails);

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setFirstName(user.getFirstName());
        responseDTO.setLastName(user.getLastName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setToken(token);

        return responseDTO;
    }

    @Override
    public UserResponseDTO getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.responseFromUser(user);
    }

    @Override
    public UserResponseDTO updateUserProfile(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUserFromRequest(userRequestDTO, user);

        if (userRequestDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return userMapper.responseFromUser(updatedUser);
    }

    @Override
    public AddressResponseDTO addUserAddress(Long userId, AddressRequestDTO addressRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = addressMapper.addressFromRequest(addressRequestDTO);
        address.setUser(user);

        Address savedAddress = addressRepository.save(address);
        return addressMapper.responseFromAddress(savedAddress);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}