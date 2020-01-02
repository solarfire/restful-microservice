package sco.co.so.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sco.co.so.model.Bean;

import javax.validation.constraints.NotNull;

import static java.util.Optional.empty;

/**
 * Created on 01/12/2017.
 */
@Service
@Transactional
public class ResourceServiceImpl implements sco.co.so.service.api.ResourceService {

    private static Set<Bean> beans = new HashSet<>();

    static Long beanId = 6L;

    static {
        beans.add(new Bean(1L, "Name 1"));
        beans.add(new Bean(2L, "Name 2"));
        beans.add(new Bean(3L, "Name 3"));
        beans.add(new Bean(4L, "Name 4"));
        beans.add(new Bean(5L, "Name 5"));
    }

    @Override
    public Collection<Bean> list() {
        return beans;
    }

    @Override
    public Optional<Bean> get(Long id) {
        // TODO change to @NotNull
        if (id==null) {
            return Optional.empty();
        }
        return beans.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Bean> add(Bean bean) {

        // TODO change to @NotNull
        if (bean==null) {
            return Optional.empty();
        }

        bean.setId(beanId++);
        beans.add(bean);
        return Optional.of(bean);
    }

    @Override
    public Optional<Bean> update(Bean bean) { //TODO Not null

        // TODO change to @NotNull
        if (bean==null) {
            return Optional.empty();
        }

        Optional<Bean> existingBean = beans.stream().filter(b -> b.equals(bean)).findFirst();
        if (existingBean.isPresent()) {
            existingBean.get().setName(bean.getName());
        }
        return existingBean;
    }

    @Override
    public Boolean delete(Long id) {

        for (Bean bean : beans) {
            if (id.equals(bean.getId())) {
                beans.remove(bean);
                return true;
            }
        }
        return false;
    }
}
