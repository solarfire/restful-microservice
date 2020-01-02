package sco.co.so.web.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.LocaleResolver;
import sco.co.so.model.Bean;
import sco.co.so.service.api.ResourceService;
import sco.co.so.web.exception.ResourceNotFoundException;

/**
 * Created on 29/10/2017.
 */
@RestController
public class ResourceRestController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private MessageSource messageSource;

    /*Read*/
    @GetMapping(value="/beans/{id}")
    public Bean get(@PathVariable(value = "id") Long id) {
        Optional<Bean> bean = resourceService.get(id);
        if(bean.isPresent()) {
            return bean.get();
        }
        throw new ResourceNotFoundException(String.format("User with id [%s] not Found", id));
    }

    /**
     * Read HATEOAS Version
     * TODO FIXME Not working,
     * Get Could not marshal
     *  [Resource { content: sco.co.so.model.Bean@20,
     *          links: [<http://localhost:8080/archetype-rest-ms/beans>;rel="all-resources"] }]: null; nested exception is javax.xml.bind.MarshalException
     * @return
     */
    @GetMapping(value="/beans/hateoas/{id}")
    public Resource<Bean> getHatoas(@PathVariable(value = "id") Long id) {
        Optional<Bean> bean = resourceService.get(id);
        if(bean.isPresent()) {

            //HATEOAS - Use a resource form hateos jar.
            Resource<Bean> resource = new Resource<Bean>(bean.get());
            //Use LinkBuilder use the method of list all, rather than hardcoding the method name.
            ControllerLinkBuilder linkToAllResources =
                    ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).list());
            // Add link to resource with a string reference.
            resource.add(linkToAllResources.withRel("all-resources"));
            return resource;
        }

        throw new ResourceNotFoundException(String.format("User with id [%s] not Found", id));
    }

    /**
     * List
     * @return
     */
    @GetMapping(value="/beans")
    public Collection<Bean> list() {
        return resourceService.list();
    }

    /**
     * Create
     */
    @PostMapping(value="/beans")
    public ResponseEntity<Bean> add(@Valid @RequestBody Bean bean) {
        resourceService.add(bean);
        URI location = fromCurrentRequest().path("{id}").buildAndExpand(bean.getId()).toUri();
        return created(location).build();
    }

    /**
     * Update
     */
    @PutMapping(value="/beans")
    public ResponseEntity<Bean> update(@Valid @RequestBody Bean bean) {

        Optional<Bean> updatedBean = resourceService.update(bean);
        if (updatedBean.isPresent()) {
            return noContent().build();
        }
        throw new ResourceNotFoundException(String.format("Resource with id [%s] not Found", bean.getId()));
    }

    /*Delete*/
    @DeleteMapping(value="/beans/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        Boolean success = resourceService.delete(id);
        if (!success) {
            throw new ResourceNotFoundException(String.format("User with id [%s] not Found", id));
        }

        return ResponseEntity.noContent().build();
    }
    // i18ln **************************************************************************************
    /**
     * i18ln
     * Use the request header and define that we will use the Accept-Language header.  And it is not required
     *
     * Bit painful aadding request header to all methods.  So can do {@ResourceRestController.i18ln2}.
     * @param locale
     * @return
     */
    @GetMapping(value="/beans/hello")
    public String i18ln(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        return messageSource.getMessage("hello", null, locale);

    }

    /**
     * i18ln
     * Use the LocaleContextHolder to get it, as we configured {@WebConfiguration.AcceptHeaderLocaleResolver} to do it for us.
     * @param locale
     * @return
     */
    @GetMapping(value="/beans/hello2")
    public String i18ln2() {

        return messageSource.getMessage("hello", null, LocaleContextHolder.getLocale());

    }

    // i18ln **************************************************************************************
}