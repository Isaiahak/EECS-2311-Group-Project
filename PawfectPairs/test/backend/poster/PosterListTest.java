package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.poster.PosterList;
import java.util.TreeMap;

public class PosterListTest {

    @Test
    public void testAddPosterAndGetPoster() {
        PosterList posterList = new PosterList();
        Poster poster = new Poster(0, "John", 1);
        posterList.addPoster("John", poster);
        assertEquals(poster, posterList.getPoster("John"));
    }

    @Test
    public void testGetDogMap() {
        PosterList posterList = new PosterList();
        TreeMap<String, Poster> expectedMap = new TreeMap<>();
        assertEquals(expectedMap, posterList.getDogMap());
        Poster poster = new Poster(0, "John", 1);
        posterList.addPoster("John", poster);
        expectedMap.put("John", poster);
        assertEquals(expectedMap, posterList.getDogMap());
    }

   

    
}