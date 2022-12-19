package com.bdsecuac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdsecuac.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
