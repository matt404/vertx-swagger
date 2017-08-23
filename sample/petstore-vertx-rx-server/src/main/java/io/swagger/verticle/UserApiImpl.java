package io.swagger.verticle;

import io.swagger.model.InlineResponseDefault;
import io.swagger.MainApiException;
import io.swagger.model.ModelUser;
import java.util.UUID;

import java.util.List;
import java.util.Map;

public class UserApiImpl implements UserApi {

    private final Vertx vertx;

    UserApiImpl(final Vertx vertx){
        this.vertx = vertx;
    }

    //createUser
    public void createUser(ModelUser body){
        
    }
    
    //createUsersWithArrayInput
    public void createUsersWithArrayInput(List<ModelUser> body){
        
    }
    
    //createUsersWithListInput
    public void createUsersWithListInput(List<ModelUser> body){
        
    }
    
    //deleteUser
    public void deleteUser(String username){
        
    }
    
    //getUserByName
    public ModelUser getUserByName(String username){
        return null;
        
    }
    
    //loginUser
    public String loginUser(String username,String password){
        return null;
        
    }
    
    //logoutUser
    public void logoutUser(){
        
    }
    
    //updateUser
    public void updateUser(String username,ModelUser body){
        
    }
    
    //uuid
    public InlineResponseDefault uuid(UUID uuidParam){
        return null;
        
    }
    
}
