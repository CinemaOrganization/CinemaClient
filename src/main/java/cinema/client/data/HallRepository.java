package cinema.client.data;

import cinema.client.entity.Hall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends CrudRepository<Hall,Long> {
    @Override
    List<Hall> findAll();
}
