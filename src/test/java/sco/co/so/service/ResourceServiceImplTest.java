package sco.co.so.service;

import org.junit.Before;
import org.junit.Test;
import sco.co.so.model.Bean;
import sco.co.so.service.impl.ResourceServiceImpl;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.setField;

public class ResourceServiceImplTest {

    private ResourceServiceImpl service = new ResourceServiceImpl();

    private Field beansField = findField(ResourceServiceImpl.class, "beans");

    @Before
    public void before() {

        beansField.setAccessible(true);
        setField(beansField, service, new HashSet<Bean>() {{
            add(new Bean(1L, "Unit Test 1"));
        }});
    }

    @Test
    public void list() {
        assertEquals(1, service.list().size());
    }

    @Test
    public void get() {

        /* 1. Null parameter */
        assertFalse(service.get(null).isPresent());

        /* 2. Success */
        Optional<Bean> existingBean = service.get(1L);
        assertTrue("Bean with the ID should exist in the mock beans list", existingBean.isPresent());
        assertTrue("IDs should match", existingBean.get().getId().equals(1L));
        assertNotNull("Bean name isn't null in the mock beans list", existingBean.get().getName());
    }

    @Test
    public void add() {

        /* 1. Null parameter */
        assertNumberOfBeans(1);
        assertFalse(service.add(null).isPresent());
        assertNumberOfBeans(1);

        /* 2. Successful addition */
        Bean bean = new Bean(null, "New Bean");
        assertTrue(service.add(bean).isPresent());
        assertNumberOfBeans(2);
    }

    @Test
    public void update() {
        /* 1. null parameter */
        assertFalse(service.update(null).isPresent());

        /* 2. Supplied bean's id doesn't exist */
        assertFalse(service.update(new Bean(-999L, "Id doesn't exist")).isPresent());

        /* 3. Supplied bean's exists and the name is updated */
        Optional<Bean> updatedBean = service.update(new Bean(1L, "New Name"));
        assertTrue(updatedBean.isPresent());
        assertEquals("Name should have been updated", "New Name", updatedBean.get().getName());
    }

    @Test
    public void delete() {
        /* 1. */
        assertNumberOfBeans(1);
        service.delete(-999L);
        assertNumberOfBeans(1);

        service.delete(1L);
        assertNumberOfBeans(0);
    }

    private void assertNumberOfBeans(int size) {

        try {
            Set<Bean> beans = ((Set<Bean>) beansField.get(service));
            assertEquals(size, beans.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
