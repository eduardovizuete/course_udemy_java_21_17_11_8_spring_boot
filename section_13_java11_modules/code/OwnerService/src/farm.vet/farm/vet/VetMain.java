package farm.vet;

import java.util.ServiceLoader;
import owner.api.OwnerService;

//import farm.owner.Owner; // no longer accessible

public class VetMain {
    public static void main(String[] args) {
      ServiceLoader<OwnerService> ownerServices = ServiceLoader.load(OwnerService.class);

      for(OwnerService ownerService: ownerServices){
        System.out.println(ownerService.getAnimal("C2"));
      }

    }
}
