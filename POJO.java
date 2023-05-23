public class MascotaDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mascota.db";
    private static final int DATABASE_VERSION = 1;

    public MascotaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla "mascota"
        String createTableQuery = "CREATE TABLE mascota (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "foto TEXT," +
                "raiting INTEGER" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Realizar acciones necesarias en caso de actualización de la base de datos
    }
}

public class Mascota {
    private int id;
    private String nombre;
    private String foto;
    private int raiting;

    public Mascota(String nombre, String foto, int raiting) {
        this.nombre = nombre;
        this.foto = foto;
        this.raiting = raiting;
    }

    // Getters y setters

    // Resto de métodos y lógica de la entidad Mascota
}
public class MainActivity extends AppCompatActivity {
    private MascotaDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el MascotaDatabaseHelper
        databaseHelper = new MascotaDatabaseHelper(this);

        // Ejemplo de uso para guardar una mascota con rating
        Mascota mascota = new Mascota("Nombre de la mascota", "ruta_de_la_foto", 5);
        guardarMascota(mascota);
    }

    private void guardarMascota(Mascota mascota) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // Insertar la mascota en la base de datos
        ContentValues values = new ContentValues();
        values.put("nombre", mascota.getNombre());
        values.put("foto", mascota.getFoto());
        values.put("raiting", mascota.getRaiting());
        db.insert("mascota", null, values);

        // Verificar el número de mascotas en la tabla
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM mascota", null);
        cursor.moveToFirst();
        int mascotasCount = cursor.getInt(0);
        cursor.close();

        // Eliminar la mascota más antigua si se supera el límite de 5 mascotas
        if (mascotasCount > 5) {
            String deleteQuery = "DELETE FROM mascota WHERE id IN (SELECT id FROM mascota ORDER BY id ASC LIMIT 1)";
            db.execSQL(deleteQuery);
        }

        db.close();
    }
}
