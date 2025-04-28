### Explique la diferencia entre @Component y @Configuration en Spring.
- Ambas anotaciones funcionan para marcar una clase como un bean de Spring, pero `@Component` esta destinada a definir una clase como un componente genérico que puede ser inyectada por otros componentes específicos, mientras `@Configuration` define la clase para una configuración explícita o fuente de definiciones.
### Muestre un ejemplo práctico en código donde una clase sea inyectada como dependencia mediante el constructor.

```
    @Autowired
    public ServicioBiblioteca(LibroRepositorio libroRepositorio, PeriodicoRepositorio periodicoRepositorio, ComputadorRepositorio computadorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.periodicoRepositorio = periodicoRepositorio;
        this.computadorRepositorio = computadorRepositorio;
    }
```

### Explique el principio de inversión de control y cómo se aplica en esta solución.

Generamos un Bean a partir de  `@Componenet` 
```
@Component 
public class ComputadorRepositorio implements RecursoRepositorios <Computador> { 

	private Collection<Computador> computadores; 
	
	public ComputadorRepositorio() { 
		this.computadores = new ArrayList<>(); 
	}
}
```

### Muestre un ejemplo de cómo se pueden combinar los resultados de múltiples repositorios en el método obtenerTodos().

```
    public Collection<Recurso> obtenerTodos() {
        
        Collection<Libro> libros = libroRepositorio.obtenerTodos();
        Collection<Periodico> periodicos = periodicoRepositorio.obtenerTodos();
        Collection<Computador> computadores = computadorRepositorio.obtenerTodos();

        List<Recurso> todosLosRecursos = new ArrayList<>();
        todosLosRecursos.addAll(libros);
        todosLosRecursos.addAll(periodicos);
        todosLosRecursos.addAll(computadores);

        return todosLosRecursos;
    }
```

### Explique cómo funciona la inyección de propiedades en Spring utilizando @Value y qué precedencia tiene cada fuente.

@Value nos permite inyectar valores de propiedades en campos, métodos o parámetros de un componente. Lo podemos definir de la siguiente manera

```
    @Value("${app.name}")
    private String nombreAplicacion;
```

Y puede ser inyectado sobre el campo `nombreAplicacion`.

La precedencia de cada fuente se da en el siguiente orden (De mayor a menor):

- Propiedades Definidas en la Linea de Comandos
- Variables de Entorno del Sistema Operativo
- Archivos de Propiedades Externos
- Valores Predeterminados en @Value

En nuestro caso, la propiedad usada es por Archivo de Propiedades Externos `Spring: @PropertySource`, ya que no se define por CLI ninguna propiedad para `app.name` en el momento de la ejecución; tampoco tenemos definida ninguna variable de entorno en el sistema operativo, por orden de jerarquia termina tomando el valor de los Archivos de propiedad externo.