package org.acme.services;


import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.acme.entities.Gift;

@ApplicationScoped
public class GiftStoreService {
  @Inject
  EntityManager entityManager;


  public Gift[] findAll() {
    return entityManager.createNamedQuery("Gift.findAll", Gift.class)
        .getResultList().toArray(new Gift[0]);
  }

  @Transactional
  public Response createGift(Gift gift) {
    if(gift.getId() != 0) {
      throw new WebApplicationException("id was invalidly set on request.", 422);
    }
    entityManager.persist(gift);
    return Response.ok(gift).status(201).build();
  }

  @Transactional
  public Gift updateGift(Gift gift) {
    if(gift.getId() == 0) {
      throw new WebApplicationException("id was invalidly set on request.", 422);
    }
    Gift entity = entityManager.find(Gift.class, gift.getId());
    if( entity == null) {
      throw new WebApplicationException("Gift with id of " + gift.getId() + " does not exist.", 404);
    }
    entity.setName(gift.getName());
    entity.setValue(gift.getValue());
    entity.setSource(gift.getSource());
    entity.setDestination(gift.getDestination());

    return entity;
  }

  @Transactional
  public Response deleteGift(Gift gift) {
    Gift entity = entityManager.find(Gift.class, gift.getId());
    if( entity == null) {
      throw new WebApplicationException("Gift with id of " + gift.getId() + " does not exist.", 404);
    }

    entityManager.remove(entity);
    return Response.status(204).build();
  }
}
