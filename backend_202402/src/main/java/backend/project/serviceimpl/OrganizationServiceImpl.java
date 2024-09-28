package backend.project.serviceimpl;

import backend.project.dtos.OrganizationDTO;
import backend.project.entities.Organization;
import backend.project.repositories.OrganizationRepository;
import backend.project.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDTO registerOrganization(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();
        organization.setOrganizationName(organizationDTO.getOrganizationName());
        organization.setOrgType(organizationDTO.getOrgType());
        organization.setContactInfo(organizationDTO.getContactInfo());
        organization.setCollaborationArea(organizationDTO.getCollaborationArea());


        Organization savedOrganization = organizationRepository.save(organization);
        return convertToDTO(savedOrganization);
    }
    @Override
    public List<OrganizationDTO> getAllOrganizations() {
        return organizationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //ESTA PARTE VERIFICARRRRRRRRRRRR , TODOO DE CONVERSIONES A DTO
    @Override
    public OrganizationDTO getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }

    private OrganizationDTO convertToDTO(Organization organization) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(organization.getId());
        dto.setOrganizationName(organization.getOrganizationName());
        dto.setOrgType(organization.getOrgType());
        dto.setContactInfo(organization.getContactInfo());
        dto.setCollaborationArea(organization.getCollaborationArea());
        dto.setEventIds(organization.getEvents().stream().map(event -> event.getId()).collect(Collectors.toList()));
        dto.setConsultationIds(organization.getConsultations().stream().map(consultation -> consultation.getId()).collect(Collectors.toList()));
        return dto;
    }
}
