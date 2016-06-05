package cinema.client.logging;

import cinema.client.entity.Cinema;
import cinema.client.service.CinemaService;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class Logging {

 /*   @AfterReturning("execution(**cinema.client.service.CinemaService.saveCinemas(Iterable)) && args(cinemas)")
    public void writeLogSaveCinema(Iterable<Cinema> cinemas){
        Logger logger = Logger.getLogger(CinemaService.class);
        for (Cinema cinema : cinemas) {
            logger.info("Создан/изменён Кнонеатр: Название = " + cinema.getName() + " Ид = " + cinema.getId());
        }
    }*/

    @AfterReturning("execution(**cinema.client.service.CinemaService.saveCinemas(..))")
    public void writeLogSaveCinema(){
        Logger logger = Logger.getLogger(CinemaService.class);
        logger.info("Создан/изменён Кнонеатр");
    }
}
