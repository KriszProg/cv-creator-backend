package hu.kriszprog.cvcreatorbackend.repository;

import hu.kriszprog.cvcreatorbackend.entity.Image;
import hu.kriszprog.cvcreatorbackend.entity.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getImageById(Long id);

    @Query("SELECT i FROM Image i WHERE i.imageType = :imageType AND i.url = :url")
    Image getImageIfExist(@Param("imageType") ImageType imageType, @Param("url") String url);
}
