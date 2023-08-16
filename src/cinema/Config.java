package cinema;

import cinema.room.Room;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Integer rowNumber() {
        return 9;
    }

    @Bean
    public Integer seatsInRowNumber() {
        return 9;
    }

    @Bean
    public Room room(@Qualifier("rowNumber") Integer rowNumber, @Qualifier("seatsInRowNumber") Integer seatsInRowNumber) {
        return new Room(rowNumber, seatsInRowNumber);
    }
}
