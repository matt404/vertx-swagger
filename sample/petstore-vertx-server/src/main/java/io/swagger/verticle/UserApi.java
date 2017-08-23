package io.swagger.verticle;

import io.swagger.model.InlineResponseDefault;
import io.swagger.MainApiException;
import io.swagger.model.ModelUser;
import java.util.UUID;
import java.util.List;
import java.util.Map;

public interface UserApi  {
    //createUser
    public void createUser(ModelUser body);
    
    //createUsersWithArrayInput
    public void createUsersWithArrayInput(List<ModelUser> body);
    
    //createUsersWithListInput
    public void createUsersWithListInput(List<ModelUser> body);
    
    //deleteUser
    public void deleteUser(String username);
    
    //getUserByName
    public ModelUser getUserByName(String username);
    
    //loginUser
    public String loginUser(String username,String password);
    
    //logoutUser
    public void logoutUser();
    
    //updateUser
    public void updateUser(String username,ModelUser body);
    
    //uuid
    public InlineResponseDefault uuid(UUID uuidParam);
    
}
