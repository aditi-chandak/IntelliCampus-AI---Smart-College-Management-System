package dto;
/**
 *
 * @author HP
 */
public class AddStudentDTO 
{
   private String  username, password,email,mobile,id,branchName,section;
public String getUsername(){
    return username;
}
public void setUsername(String username){
    this.username= username;
}
public String getPassword(){
    return password;
}
public void setPassword(String password){
    this.password= password;
}
public String getEmail(){
    return email;
}
public void setEmail(String email ){
    this.email=email ;
}
public String getMobile(){
    return mobile ;
}
public void setMobile(String mobile){
    this.mobile= mobile;
}
public String getId(){
    return  id;
}
public void setId(String id){
    this.id= id;
} 
public String getBranchName(){
    return branchName;
}
public void setBranchName(String branchName){
    this.branchName= branchName;
}
public String getSection(){
    return section;
}
public void setSection(String section){
    this.section= section;
}
}
