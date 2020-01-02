package sco.co.so.service.api;

import sco.co.so.model.Bean;

import java.util.Collection;
import java.util.Optional;

/**
 * Created on 01/12/2017.
 */
public interface ResourceService {

    Collection<Bean> list();

    Optional<Bean> get(Long id);

    Optional<Bean> add(Bean bean);

    Optional<Bean> update(Bean bean);

    Boolean delete(Long id);

}
