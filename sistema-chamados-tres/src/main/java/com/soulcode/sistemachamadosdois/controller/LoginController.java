package com.soulcode.sistemachamadosdois.controller;

import com.soulcode.sistemachamadosdois.dtos.RequestDTO;
import com.soulcode.sistemachamadosdois.model.Client;
import com.soulcode.sistemachamadosdois.repositories.UserRepository;
import com.soulcode.sistemachamadosdois.service.UserPrincipal;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String showLoginForm(@ModelAttribute("requestDto") RequestDTO requestDto, Model model) {
        model.addAttribute("requestDto", requestDto);
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute("requestDto") RequestDTO requestDto, RedirectAttributes redirectAttributes, HttpSession session) {
        Optional<Client> client = clientRepository.findByEmail(requestDto.email());
        if (client.isEmpty() || !client.get().isLoginCorrect(requestDto, passwordEncoder)) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/login";
        } else {
            // Defina o cliente na sessão
            session.setAttribute("client", client.get());
            return "redirect:/dashboard-user";
        }
    }

}
