package com.haui.dao.repositories;

import com.haui.dao.models.entities.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api,String> {
    Api findByUrl(String url);
}
