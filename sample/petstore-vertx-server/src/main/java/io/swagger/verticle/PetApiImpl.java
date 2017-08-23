package io.swagger.verticle;

import java.io.File;
import io.swagger.MainApiException;
import io.swagger.model.ModelApiResponse;
import io.swagger.model.Pet;

import java.util.List;
import java.util.Map;

public class PetApiImpl implements PetApi {

    private final Vertx vertx;

    PetApiImpl(final Vertx vertx){
        this.vertx = vertx;
    }

    //addPet
    public void addPet(Pet body){
        
    }
    
    //deletePet
    public void deletePet(Long petId,String apiKey){
        
    }
    
    //findPetsByStatus
    public List<Pet> findPetsByStatus(List<String> status){
        return null;
        
    }
    
    //findPetsByTags
    public List<Pet> findPetsByTags(List<String> tags){
        return null;
        
    }
    
    //getPetById
    public Pet getPetById(Long petId){
        return null;
        
    }
    
    //updatePet
    public void updatePet(Pet body){
        
    }
    
    //updatePetWithForm
    public void updatePetWithForm(Long petId,String name,String status){
        
    }
    
    //uploadFile
    public ModelApiResponse uploadFile(Long petId,String additionalMetadata,File file){
        return null;
        
    }
    
}
