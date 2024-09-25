package farm.neighbour;

import owner.api.OwnerService;

public class NeighbourOwnerService implements OwnerService{
    @Override
    public String getAnimal(String tag){
        return "Neighbour: Found animal with tag number: "+tag;
    }
}
