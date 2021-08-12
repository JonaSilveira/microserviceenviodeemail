package com.ms.email.repository;

import com.ms.email.model.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, String> {

}
