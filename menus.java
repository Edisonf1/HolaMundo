public class ContactoFragment extends Fragment {
    // Variables para los EditTexts y el botón
    private EditText nombreEditText;
    private EditText correoEditText;
    private EditText mensajeEditText;
    private Button enviarButton;

    // Constructor vacío requerido para los Fragments
    public ContactoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del Fragment
        View view = inflater.inflate(R.layout.fragment_contacto, container, false);

        // Obtener referencias de los elementos del layout
        nombreEditText = view.findViewById(R.id.editTextNombre);
        correoEditText = view.findViewById(R.id.editTextCorreo);
        mensajeEditText = view.findViewById(R.id.editTextMensaje);
        enviarButton = view.findViewById(R.id.buttonEnviar);

        // Configurar el listener del botón
        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los EditTexts
                String nombre = nombreEditText.getText().toString();
                String correo = correoEditText.getText().toString();
                String mensaje = mensajeEditText.getText().toString();

                // Realizar el envío del correo con la información recopilada
                enviarCorreo(nombre, correo, mensaje);
            }
        });

        return view;
    }

    // Método para enviar el correo utilizando la librería JavaMail
    private void enviarCorreo(String nombre, String correo, String mensaje) {
        // Implementa aquí la lógica para enviar el correo utilizando JavaMail
    }
}

public class AcercaDeFragment extends Fragment {
    // Constructor vacío requerido para los Fragments
    public AcercaDeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del Fragment
        View view = inflater.inflate(R.layout.fragment_acerca_de, container, false);

        // Configurar el contenido del Fragment (biografía del desarrollador, etc.)

        return view;
    }
}

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia del ViewPager
        viewPager = findViewById(R.id.viewPager);

        // Crear un ArrayList de Fragments para agregar al ViewPager
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ContactoFragment());
        fragments.add(new AcercaDeFragment());

        // Crear un FragmentStateAdapter para gestionar los Fragments en el ViewPager
        FragmentStateAdapter fragmentAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        };

        // Configurar el adaptador en el ViewPager
        viewPager.setAdapter(fragmentAdapter);
    }
}

public class MascotaFragment extends Fragment {
    // Variables para la foto de perfil, nombre y RecyclerView
    private ImageView fotoImageView;
    private TextView nombreTextView;
    private RecyclerView fotosRecyclerView;

    // Constructor vacío requerido para los Fragments
    public MascotaFragment() {
