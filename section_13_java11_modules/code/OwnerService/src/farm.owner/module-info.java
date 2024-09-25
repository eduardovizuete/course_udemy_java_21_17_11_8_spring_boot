module farm.owner {
    // We need the service contract module.
    requires owner.api;

    // This tells the Service Registry to register this providers
    // implementation of OwnerService; namely, OwnerOfFarmOwnerService.
    // Note that the package farm.owner is NOT exported => the type
    // OwnerOfFarmOwnerService is encapsulated within this module.
    provides owner.api.OwnerService
      with farm.owner.OwnerOfFarmOwnerService;
}
