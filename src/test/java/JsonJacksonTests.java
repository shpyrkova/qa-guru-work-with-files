import com.fasterxml.jackson.databind.ObjectMapper;
import model.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonJacksonTests {

    private final ClassLoader cl = JsonJacksonTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка json объекта")
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("pet.json"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            Pet actual = objectMapper.readValue(reader, Pet.class);

            assertThat(actual.getName()).isEqualTo("Doosya");
            assertThat(actual.getSpecies()).isEqualTo("Dog");
            assertThat(actual.getBirthYear()).isEqualTo(2014);
            assertThat(actual.getColour()).isEqualTo("brown");
            assertThat(actual.getFavFoods()).isEqualTo(new String[]{"meat", "porridge"});
        }
    }
}





