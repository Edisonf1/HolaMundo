implementation 'com.squareup.retrofit2:retrofit:2.9.0'

public interface ApiInterface {
    @GET("timeline")
    Call<List<Photo>> getTimeline();
}

Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();
ApiInterface apiInterface = retrofit.create(ApiInterface.class);

Call<List<Photo>> call = apiInterface.getTimeline();
call.enqueue(new Callback<List<Photo>>() {
    @Override
    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
        if (response.isSuccessful()) {
            List<Photo> photos = response.body();
            // Procesar las fotos recibidas
        } else {
            // Manejar el error de la respuesta
        }
    }

    @Override
    public void onFailure(Call<List<Photo>> call, Throwable t) {
        // Manejar el error de la llamada
    }
});

public class Photo {
    private String id;
    private String url;
    // Otros campos de la foto

    // Getters y setters
}

Gson gson = new GsonBuilder()
    .registerTypeAdapter(Photo.class, new PhotoDeserializer())
    .create();
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build();

public class PhotoDeserializer implements JsonDeserializer<Photo> {
    @Override
    public Photo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String id = jsonObject.get("id").getAsString();
        String url = jsonObject.get("url").getAsString();
        // Obtener otros campos de la foto

        Photo photo = new Photo();
        photo.setId(id);
        photo.setUrl(url);
        // Establecer otros campos de la foto

        return photo;
    }
}
