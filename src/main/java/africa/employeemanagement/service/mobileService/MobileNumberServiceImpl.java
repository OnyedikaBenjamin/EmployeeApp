package africa.semicolon.employeemanagement.service.mobileService;

import africa.semicolon.employeemanagement.data.dto.request.MobileNumberRequest;
import africa.semicolon.employeemanagement.data.dto.response.MobileNumberResponse;
import africa.semicolon.employeemanagement.data.model.MobileNumber;
import africa.semicolon.employeemanagement.data.repository.MobileNumberRepository;
import africa.semicolon.employeemanagement.web.exception.mobileNumberException.MobileNumberAlreadyExistException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileNumberServiceImpl implements MobileNumberService{

    private final MobileNumberRepository mobileNumberRepository;
    private final ObjectMapper mapper;
    private final ModelMapper modelMapper;

    @Autowired
    public MobileNumberServiceImpl(MobileNumberRepository mobileNumberRepository, ObjectMapper mapper, ModelMapper modelMapper) {
        this.mobileNumberRepository = mobileNumberRepository;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public MobileNumberResponse addMobileNumber(MobileNumberRequest request) {
        MobileNumber number = mobileNumberRepository.findByNumber(request.getNumber());
        if (number != null) throw new MobileNumberAlreadyExistException("Mobile number already exist");
        MobileNumber mapped = modelMapper.map(request, MobileNumber.class);
        MobileNumber mobileNumber = mobileNumberRepository.save(mapped);
        return modelMapper.map(mobileNumber, MobileNumberResponse.class);
    }

    @Override
    public void deleteAllMobileNumber() {
        mobileNumberRepository.deleteAll();
    }

}
