package org.acme;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.acme.entities.Gift;
import org.acme.services.GiftStoreService;

@Path("/gifts")
@Produces("application/json")
@Consumes("application/json")
public class GiftService {

  @Inject
  GiftStoreService giftService;

  @GET
  public Gift[] getGifts() {
    return giftService.findAll();
  }

  @POST
  public Response create(Gift gift) {
    return giftService.createGift(gift);
  }

  @PUT
  public Gift update(Gift gift) {
    return giftService.updateGift(gift);
  }

  @DELETE
  public Response delete(Gift gift) {
    return giftService.deleteGift(gift);
  }

}
