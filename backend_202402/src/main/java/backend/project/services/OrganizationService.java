package backend.project.services;

import backend.project.dtos.OrganizationDTO;
import backend.project.entities.Organization;

import java.util.List;

public interface OrganizationService {

    OrganizationDTO registerOrganization(OrganizationDTO organizationDTO);

    List<OrganizationDTO> getAllOrganizations();
    OrganizationDTO getOrganizationById(Long id);
    void deleteOrganization(Long id);


}
