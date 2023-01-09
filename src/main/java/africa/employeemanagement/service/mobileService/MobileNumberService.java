package africa.semicolon.employeemanagement.service.mobileService;

import africa.semicolon.employeemanagement.data.dto.request.MobileNumberRequest;
import africa.semicolon.employeemanagement.data.dto.response.MobileNumberResponse;

public interface MobileNumberService {
    MobileNumberResponse addMobileNumber(MobileNumberRequest request);
    void deleteAllMobileNumber();
}
