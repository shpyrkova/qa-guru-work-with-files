import com.fasterxml.jackson.databind.ObjectMapper;
import model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class JsonJacksonTests {

    private final ClassLoader cl = JsonJacksonTests.class.getClassLoader();

    @Test
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("pet.json")
        )) {

            ObjectMapper objectMapper = new ObjectMapper();
            Pet actual = objectMapper.readValue(reader, Pet.class);
            Assertions.assertEquals("Doosya", actual.getName());
            Assertions.assertEquals("Dog", actual.getSpecies());
            Assertions.assertEquals(2014, actual.getBirthYear());
            Assertions.assertEquals("brown", actual.getColour());
            Assertions.assertArrayEquals(new List[]{List.of(new String[]{"meat", "porridge"})}, actual.getFavFoods()); // выглядит не очень, как верно?

        }
    }
}





