package tn.esprit.integration1.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.integration1.Entities.Charity;
import tn.esprit.integration1.Entities.Command;
import tn.esprit.integration1.Interfaces.ICharityService;
import tn.esprit.integration1.Repositories.CharityRepository;
import tn.esprit.integration1.Repositories.CommandRepository;
import tn.esprit.integration1.dto.CharityDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharityServiceImpl implements ICharityService {
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    CharityRepository charityRepository;
    @Override
    public List<CharityDto> getAllCharities() {
        List<Charity> charities = charityRepository.findAll();
        return charities.stream()
                .map(CharityDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CharityDto getCharityById(Integer id) {
        Charity charity = charityRepository.findById(id).orElse(null);
        return CharityDto.fromEntity(charity);
    }

    @Override
    public CharityDto createCharity(CharityDto charityDto) {
        Charity charity = CharityDto.toEntity(charityDto);
        Charity createdCharity = charityRepository.save(charity);
        return CharityDto.fromEntity(createdCharity);
    }

    @Override
    public CharityDto updateCharity(Integer id, CharityDto charityDto) {
        Charity existingCharity = charityRepository.findById(id).orElse(null);
        if (existingCharity == null) {
            return null;
        }
        existingCharity.setName(charityDto.getName());
        existingCharity.setDescription(charityDto.getDescription());
        existingCharity.setWebsite(charityDto.getWebsite());
        existingCharity.setContactInformation(charityDto.getContactInformation());
        Charity updatedCharity = charityRepository.save(existingCharity);
        return CharityDto.fromEntity(updatedCharity);
    }

    @Override
    public boolean deleteCharity(Integer id) {
        Charity existingCharity = charityRepository.findById(id).orElse(null);
        if (existingCharity == null) {
            return false;
        }
        charityRepository.delete(existingCharity);
        return true;
    }
    @Override
    public String assignCharityToCommand(Integer idCommand, Integer idCharity) {
        Command command = commandRepository.findById(idCommand).orElse(null);
        Charity charity = charityRepository.findById(idCharity).orElse(null);

        if (command != null && charity != null && command.getDonation()) {
            // assign the charity to the command
            command.setCharity(charity);

            // update the charity's total donations
            BigDecimal totalDonation = command.getTotalC();
            if (totalDonation != null && totalDonation.compareTo(BigDecimal.ZERO) > 0) {
                charity.setTotalDonations(charity.getTotalDonations().add(totalDonation));
            }

            // save the changes to the command and charity objects
            commandRepository.save(command);
            charityRepository.save(charity);

            return "Charity assigned to command successfully.";
        } else {
            return "Invalid command or charity ID, or donation is not selected for the command.";
        }
    }


    @Override
    public String markCommandAsDonation(Integer idCommand) {
        Command command = commandRepository.findById(idCommand).orElse(null);

        if (command != null) {
            if (command.getDonation()) {
                return "Command is already marked as a donation.";
            } else {
                command.setDonation(true);
                commandRepository.save(command);
                return "Command marked as a donation successfully.";
            }
        } else {
            return "Invalid command ID.";
        }
    }


    }



