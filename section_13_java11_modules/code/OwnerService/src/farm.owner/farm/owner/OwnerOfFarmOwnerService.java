package farm.owner;

import owner.api.OwnerService;

public class OwnerOfFarmOwnerService implements OwnerService{
    @Override
    public String getAnimal(String tag){
        return "Owner: Found animal with tag number: "+tag;
    }
}
