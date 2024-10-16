package com.postech.lending.client.repository;

import com.postech.lending.client.dto.ClientDTO;
import com.postech.lending.client.model.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client as c where c.profileState = c.profileState")
    List<ClientDTO> findClientsByProfileState();

    @Query("select c from Client as c where c.document like %?1%")
    List<ClientDTO> findClientsByDocument(@Param("document") String document);

}
