package br.com.seartv.config;

public class ConstantsUrl {

    /** API KEY */
    public static final String API_KEY = "?api_key=d272326e467344029e68e3c4ff0b4059";

    /** Language */
    public static final String LANGUAGE = "&language=pt-br";

    /** Server endpoint. */
    public static final String SERVER_URL = "http://api.themoviedb.org/3";
    public static final String IMAGE_SERVER_URL = "https://image.tmdb.org/t/p/";

    /** Poster size. */
    public static final String POSTER_SIZE = "/w1000";

    /** API KEY */
    public static final String PAGE = "&page=%d";

    /** Movies endpoint. */
    public static final String MOVIE_ENDPOINT_URL = SERVER_URL + "/movie";

    /** Movies services. */
    public static final String POPULAR_URL = MOVIE_ENDPOINT_URL + "/popular" + API_KEY + LANGUAGE + PAGE;
    public static final String TOP_RATED_URL = MOVIE_ENDPOINT_URL + "/top_rated" + API_KEY + LANGUAGE + PAGE;
    public static final String UPCOMING_URL = MOVIE_ENDPOINT_URL + "/upcoming" + API_KEY + LANGUAGE + PAGE;
    public static final String POSTER_URL = IMAGE_SERVER_URL + POSTER_SIZE + "/%1$s" + API_KEY;

    /** Volley timeouts. */
    public static int SHORT_SOCKET_TIME_OUT = 10000;
}
