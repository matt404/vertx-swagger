package io.swagger.verticle;

import java.io.File;
import io.swagger.MainApiException;
import io.swagger.model.ModelApiResponse;
import io.swagger.model.Pet;

public final class PetApiException extends MainApiException {
    public PetApiException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
    
    public static final PetApiException Pet_addPet_405_Exception = new PetApiException(405, "Invalid input");
    public static final PetApiException Pet_deletePet_400_Exception = new PetApiException(400, "Invalid ID supplied");
    public static final PetApiException Pet_deletePet_404_Exception = new PetApiException(404, "Pet not found");
    public static final PetApiException Pet_findPetsByStatus_400_Exception = new PetApiException(400, "Invalid status value");
    public static final PetApiException Pet_findPetsByTags_400_Exception = new PetApiException(400, "Invalid tag value");
    public static final PetApiException Pet_getPetById_400_Exception = new PetApiException(400, "Invalid ID supplied");
    public static final PetApiException Pet_getPetById_404_Exception = new PetApiException(404, "Pet not found");
    public static final PetApiException Pet_updatePet_400_Exception = new PetApiException(400, "Invalid ID supplied");
    public static final PetApiException Pet_updatePet_404_Exception = new PetApiException(404, "Pet not found");
    public static final PetApiException Pet_updatePet_405_Exception = new PetApiException(405, "Validation exception");
    public static final PetApiException Pet_updatePetWithForm_405_Exception = new PetApiException(405, "Invalid input");
    

}