public class FormActivity extends AppCompatActivity {
    private EditText etNombre, etFechaNacimiento, etTelefono, etEmail, etDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etNombre = findViewById(R.id.etNombre);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);
        etDescripcion = findViewById(R.id.etDescripcion);

        Button btnSiguiente = findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos ingresados por el usuario
                String nombre = etNombre.getText().toString();
                String fechaNacimiento = etFechaNacimiento.getText().toString();
                String telefono = etTelefono.getText().toString();
                String email = etEmail.getText().toString();
                String descripcion = etDescripcion.getText().toString();

                // Crear un Intent para pasar a la siguiente actividad
                Intent intent = new Intent(FormActivity.this, ConfirmationActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("fechaNacimiento", fechaNacimiento);
                intent.putExtra("telefono", telefono);
                intent.putExtra("email", email);
                intent.putExtra("descripcion", descripcion);
                startActivity(intent);
            }
        });
    }
}
