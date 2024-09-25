module farm.neighbour {
    // We need the service contract module.
    requires owner.api;

    // This tells the Service Registry to register this providers
    // implementation of OwnerService; namely, NeighbourOwnerService.
    // Note that the package farm.neighbour is NOT exported => the type
    // NeighbourOwnerService is encapsulated within this module.
    provides owner.api.OwnerService
      with farm.neighbour.NeighbourOwnerService;
}
