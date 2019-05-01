package metubev1.repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<E, K> {

    E save(E entity);

    List<E> findAll();

    Optional<E> findById(K id);

}
