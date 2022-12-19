package com.bdsecuac;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.bdsecuac.model.Categoria;
import com.bdsecuac.model.Perfil;
import com.bdsecuac.model.Usuario;
import com.bdsecuac.model.Vacante;
import com.bdsecuac.repository.CategoriasRepository;
import com.bdsecuac.repository.PerfilesRepository;
import com.bdsecuac.repository.UsuariosRepository;
import com.bdsecuac.repository.VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repoCategorias;
	
	@Autowired
	private VacantesRepository repoVacantes;
	
	@Autowired
	private UsuariosRepository repoUsuarios;
	
	@Autowired
	private PerfilesRepository repoPerfiles;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//guardar();
		//buscarPorId();
		//modificar();
		//eliminar();
		//conteo();
		//eliminarTodos();
		//buscarTodasPorId();
		//buscarTodos();
		//existePorId();
		//guardarTodas();
		//buscarTodosJPA();
		//borrarTodoEnBloque();
		//buscarTodosOrdenados();
		//buscarTodosPaginados();
		buscarTodosPaginadosOrdenados();
		//guardarVacante();
		//buscarVacantes();
		//buscarTodosUsuarios();
		//crearUsuarioConVariosPerfiles();
		crearUsuarioConUnPerfil();
		buscarUsuario();
		//buscarVacantePorEstatus();
		//buscarVacantePorSalarioMayor10k();
		//buscarVacantePorDestacadoEstatus();
		//buscarVacantePorSalarioRango();
		//buscarVacantePorVariosEstatus();
	}
	
	public void guardar() {
		Categoria cat = new Categoria();
		cat.setNombre("Construccion");
		cat.setDescripcion("Trabajos relacionados con Construccion");
		repoCategorias.save(cat);
		System.out.println(cat);

	}

	public void eliminar() {
		int idCategoria = 1;
		Optional<Categoria> optional = repoCategorias.findById(idCategoria);				
		if (optional.isPresent()) {
			repoCategorias.deleteById(idCategoria);
			System.out.println("Registro eliminado: " + optional.get().getNombre());
		} else {
			System.out.println("Categoria no encontrada");
		}
	}
	
	public void buscarPorId() {
		
		Optional<Categoria> optional = repoCategorias.findById(1);
		
		if (optional.isPresent())
			System.out.println(optional.get().toString());
		else
			System.out.println("Categoria no encontrada");
	}
	
	public void buscarTodasPorId() {
		
		List<Integer> categoriasList = new LinkedList<>();
		categoriasList.add(1);
		categoriasList.add(3);
		categoriasList.add(5);
		categoriasList.add(7);
		categoriasList.add(9);
		categoriasList.add(11);
		
		Iterable<Categoria> categorias = repoCategorias.findAllById(categoriasList);
		
		for (Categoria cat : categorias) {
			System.out.println(cat);			
		}
	}
	
	public void modificar() {
		Optional<Categoria> optional = repoCategorias.findById(3);
		
		if (optional.isPresent()) {
			Categoria cat = optional.get();
			cat.setNombre("Telecomunicaciones");
			cat.setDescripcion("Trabajos relacionados con telecomunicaciones y redes");
			repoCategorias.save(cat);
			System.out.println(cat.toString());
		} else {
			System.out.println("Categoria no encontrada");
		}
		
	}
	
	public void conteo() {		
		long contador = repoCategorias.count();
		System.out.println("Cantidad de registros: " + contador);
		 
	 }
	
	public void eliminarTodos() {
		repoCategorias.deleteAll();		
	}
	
	public void buscarTodos() {
		Iterable<Categoria> categorias = repoCategorias.findAll();
		for (Categoria cat : categorias) {
			System.out.println(cat);			
		}
		
	}
	
	public void buscarTodosJPA() {
		List<Categoria> categorias = repoCategorias.findAll();
		for (Categoria cat : categorias) {
			System.out.println(cat.getId() + " " + cat.getNombre());			
		}
	}
	
	public void existePorId() {
		boolean existe = repoCategorias.existsById(12);
		System.out.println("La categoria existe: " + existe);		
		
	};
	
	public void guardarTodas() {
		List<Categoria> categorias = new LinkedList<>();
		Categoria cat1 = new Categoria();
		cat1.setNombre("Transporte");
		cat1.setDescripcion("Servicios de transporte y movilizacion");
		
		Categoria cat2 = new Categoria();
		cat2.setNombre("Logistica");
		cat2.setDescripcion("Servicios de logistica y movilizacion");
		
		Categoria cat3 = new Categoria();
		cat3.setNombre("Seguridad");
		cat3.setDescripcion("Servicios de seguridad y vigilancia");
		
		categorias.add(cat1);
		categorias.add(cat2);
		categorias.add(cat3);
		
		repoCategorias.saveAll(categorias);
		
	}
	
	public void borrarTodoEnBloque() {
		repoCategorias.deleteAllInBatch();
		
	}
	
	public void buscarTodosOrdenados() {
		
		List<Categoria> categoriasSorted =  repoCategorias.findAll(Sort.by("id").descending());
		
		for(Categoria cat : categoriasSorted) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}		
		
	}
	
	public void buscarTodosPaginados() {
		
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(1, 5));
		for(Categoria cat : page.getContent()) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
		
		System.out.println("Cantidad de elementos: " + page.getTotalElements());
		System.out.println("Cantidad de páginas: " + page.getTotalPages());
		
	}
	
	public void buscarTodosPaginadosOrdenados() {
		
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5, Sort.by("nombre").descending()));
		for(Categoria cat : page.getContent()) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		} 
		
		System.out.println("Cantidad de elementos: " + page.getTotalElements());
		System.out.println("Cantidad de páginas: " + page.getTotalPages());
		
	}

	public void buscarVacantes() {
		List<Vacante> lista = repoVacantes.findAll();
		for ( Vacante vacante : lista ) {
			System.out.println(vacante.getId() + " " + vacante.getNombre() + " - " + vacante.getCategoria().getNombre());			
		}
	}
	
	public void guardarVacante() {
		Vacante vacante = new Vacante();
		vacante.setDescripcion("Profesor de educación física");
		vacante.setDestacado(1);
		vacante.setDetalles("Se busca profesor para unidad educativa en Venezuela");
		vacante.setEstatus("Creada");
		vacante.setImagen("docente.png");
		vacante.setFecha(new Date());
		vacante.setSalario(2500.0);
		vacante.setNombre("Docente escolar");
		Categoria cat = new Categoria();
		cat.setId(15);
		vacante.setCategoria(cat);
		
		repoVacantes.save(vacante);
	}
	
	public void buscarTodosUsuarios() {		
		List<Usuario> usuarios = repoUsuarios.findAll();
		
		for (Usuario user : usuarios) {
			System.out.println("Id: " + user.getId() + " " + user.getNombre());			
		}
	}
	
	public void crearUsuarioConUnPerfil() {
		Usuario user = new Usuario();
		user.setNombre("Marica Jimenez");
		user.setEmail("majmenez@gmail.com");
		user.setUsername("majimenez");
		user.setPassword("1245678");
		user.setEstatus(1);
		user.setFechaRegistro(new Date());
		
		List<Perfil> perfiles = new LinkedList<Perfil>();
		Perfil perfil = new Perfil();
		perfil.setId(2);		
		perfiles.add(perfil); // Administrador
		
		user.setPerfiles(perfiles);		
		repoUsuarios.save(user);
		
	}
	
	public void crearUsuarioConVariosPerfiles() {
		Usuario user = new Usuario();
		user.setNombre("Maria Gomez");
		user.setEmail("mgomez@gmail.com");
		user.setUsername("mgomez");
		user.setPassword("1245678");
		user.setEstatus(1);
		user.setFechaRegistro(new Date());
		
		Perfil perfil = new Perfil();		
		perfil.setId(1);
								
		Perfil perfil2 = new Perfil();
		perfil2.setId(2);
		
		Perfil perfil3 = new Perfil();
		perfil3.setId(3);		
		
		user.agregar(perfil); // Administrador
		user.agregar(perfil2); // Operador
		user.agregar(perfil3); // Invitado
		
		repoUsuarios.save(user);
		
	}
	
	public void buscarUsuario() {
		Optional<Usuario> optional = repoUsuarios.findById(8);
		if (optional.isPresent()) {
			Usuario user = optional.get();
			System.out.println("UsuarioId: " + user.getId() + " " + user.getNombre());
			System.out.println("Perfiles asignados:");
			for (Perfil perfil : user.getPerfiles()) {
				System.out.println(perfil.getPerfil());				
			}
		} else {
			System.out.println("Usuario no encontrado");
		}
	}
	
	public void buscarVacantePorEstatus() {
		List<Vacante> list = repoVacantes.findByEstatus("Eliminada");
		System.out.println("Cantidad de registros encontrados: " + list.size());
		for (Vacante vacante : list) {
			System.out.println(vacante.getId() + " " + vacante.getNombre() + " " + vacante.getEstatus());			
		}
	}
	
	public void buscarVacantePorSalarioMayor10k() {
		List<Vacante> list = repoVacantes.findBySalarioGreaterThan(10000.0);
		System.out.println("Cantidad de registros encontrados: " + list.size());
		for (Vacante vacante : list) {
			System.out.println(vacante.getId() + " " + vacante.getNombre() + " " + vacante.getSalario());			
		}
	}
	
	/*
	 * Query Method: Buscar vacante por destacado y estatus, ordenado por id descendente
	 */
	public void buscarVacantePorDestacadoEstatus() {
		List<Vacante> lista = repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
		for (Vacante vacante : lista) {
			System.out.println(vacante.getId() + " " + vacante.getNombre() + " " + vacante.getEstatus());
		}
		
	}
	
	/*
	 * Query Method: Buscar vacante por salario entre montos, ordenado por salario descendente
	 */
	
	public void buscarVacantePorSalarioRango() {
		List<Vacante> lista = repoVacantes.findTop5BySalarioBetweenOrderBySalarioDesc(15000, 30000);
		for (Vacante vacante : lista) {
			System.out.println(vacante.getId() + " " + vacante.getNombre() + " " + vacante.getSalario());
		}
	}	
	
	/*
	 * Query Method: Buscar vacante por estatus en un conjunto de valores
	 */
	
	public void buscarVacantePorVariosEstatus() {
		List<Vacante> lista = repoVacantes.findByEstatusIn(new String[] {"Creada", "Eliminada"});
		System.out.println("Cantidad de registros: " + lista.size());
		for (Vacante vacante : lista) {
			System.out.println(vacante.getId() + " - " + vacante.getNombre() + " - " + vacante.getEstatus());
		}
	}
	
}
