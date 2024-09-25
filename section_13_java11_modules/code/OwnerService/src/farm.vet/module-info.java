module farm.vet {
  // We need the service contract module.
  requires owner.api;

  // This tells the Service Registry that this consumer
  // is interested in implementations of OwnerService.
  uses owner.api.OwnerService;
}
