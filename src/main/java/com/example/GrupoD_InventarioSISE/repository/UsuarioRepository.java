package com.example.GrupoD_InventarioSISE.repository;
 
 import com.example.GrupoD_InventarioSISE.model.Usuario;
 import org.springframework.data.jpa.repository.JpaRepository;
 
 public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
     Usuario findByUsername(String username);
 }
