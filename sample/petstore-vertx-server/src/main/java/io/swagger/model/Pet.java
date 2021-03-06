package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.Category;
import io.swagger.model.Tag;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Pet   {
  
  private Long id = null;
  private Category category = null;
  private String name = null;
  private List<String> photoUrls = new ArrayList<String>();
  private List<Tag> tags = new ArrayList<Tag>();


  public enum StatusEnum {
    AVAILABLE(""available""),
    PENDING(""pending""),
    SOLD(""sold"");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private StatusEnum status = null;

  public Pet () {

  }
  public Pet (Long id, Category category, String name, List<String> photoUrls, List<Tag> tags, StatusEnum status) {
    this.id = id;    this.category = category;    this.name = name;    this.photoUrls = photoUrls;    this.tags = tags;    this.status = status;
  }
    
  @JsonProperty("id")
  public Long getId() {
    return id;
  }
  public Pet setId(Long id) {
    this.id = id;
    return this;
  }
    
  @JsonProperty("category")
  public Category getCategory() {
    return category;
  }
  public Pet setCategory(Category category) {
    this.category = category;
    return this;
  }
    
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public Pet setName(String name) {
    this.name = name;
    return this;
  }
    
  @JsonProperty("photoUrls")
  public List<String> getPhotoUrls() {
    return photoUrls;
  }
  public Pet setPhotoUrls(List<String> photoUrls) {
    this.photoUrls = photoUrls;
    return this;
  }
    
  @JsonProperty("tags")
  public List<Tag> getTags() {
    return tags;
  }
  public Pet setTags(List<Tag> tags) {
    this.tags = tags;
    return this;
  }
    
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }
  public Pet setStatus(StatusEnum status) {
    this.status = status;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pet pet = (Pet) o;
    return Objects.equals(id, pet.id) &&
        Objects.equals(category, pet.category) &&
        Objects.equals(name, pet.name) &&
        Objects.equals(photoUrls, pet.photoUrls) &&
        Objects.equals(tags, pet.tags) &&
        Objects.equals(status, pet.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, category, name, photoUrls, tags, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pet {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    photoUrls: ").append(toIndentedString(photoUrls)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
