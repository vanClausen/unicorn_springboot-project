package de.vanclausen.date4u.core.photo;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class Photo {
    public Long id;
    @Min(1) public Long profile;
    @NotNull @Pattern(regexp = "[\\w_-]{1,200}") public String name;
    public boolean isProfilePhoto;
    @NotNull @Past public LocalDateTime created;

    public Photo() {
    }

    public Photo(long id, long profile, String name, boolean isProfilePhoto, LocalDateTime created) {
        this.id = id;
        this.profile = profile;
        this.name = name;
        this.isProfilePhoto = isProfilePhoto;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfile() {
        return profile;
    }

    public void setProfile(Long profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProfilePhoto() {
        return isProfilePhoto;
    }

    public void setProfilePhoto(boolean profilePhoto) {
        isProfilePhoto = profilePhoto;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", profile=" + profile +
                ", name='" + name + '\'' +
                ", isProfilePhoto=" + isProfilePhoto +
                ", created=" + created +
                '}';
    }
}
