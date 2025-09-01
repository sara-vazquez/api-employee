package dev.sara.request;

import java.util.List;

import dev.sara.implementations.IGenericService;

public interface IRequestService extends IGenericService<RequestDTOResponse, RequestDTORequest>{
    List<RequestDTOResponse> getEntitiesSortedByDate();
    RequestDTOResponse markAsAttended(Long id, String attendedBy);
}
