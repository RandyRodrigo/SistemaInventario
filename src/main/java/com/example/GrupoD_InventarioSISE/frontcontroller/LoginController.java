package com.example.GrupoD_InventarioSISE.frontcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.example.GrupoD_InventarioSISE.model.Usuario;
import com.example.GrupoD_InventarioSISE.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Credenciales inválidas");
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario != null && usuario.getPassword()
                .equalsIgnoreCase(org.apache.commons.codec.digest.DigestUtils.sha256Hex(password))) {
            session.setAttribute("usuario", usuario.getUsername());
            return "redirect:/producto";
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }

    @GetMapping("/logout")

    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
