public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlaceAdapter placeAdapter;

    private List<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la lista de lugares
        places = getPlaces();

        // Configurar el RecyclerView y su adaptador
        recyclerView = findViewById(R.id.recyclerView);
        placeAdapter = new PlaceAdapter(places);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(placeAdapter);

        // Configurar el listener de clic en el adaptador
        placeAdapter.setOnItemClickListener(new PlaceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Obtener el lugar seleccionado
                Place selectedPlace = places.get(position);

                // Iniciar la actividad de ubicación y pasar el lugar seleccionado
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                intent.putExtra("place", selectedPlace);
                startActivity(intent);
            }
        });
    }

    // Método para obtener la lista de lugares (debes reemplazar esto con tus propios lugares)
    private List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        places.add(new Place("Lugar 1", R.drawable.ic_place_1));
        places.add(new Place("Lugar 2", R.drawable.ic_place_2));
        places.add(new Place("Lugar 3", R.drawable.ic_place_3));
        places.add(new Place("Lugar 4", R.drawable.ic_place_4));
        return places;
    }
}
public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    private List<Place> places;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener
