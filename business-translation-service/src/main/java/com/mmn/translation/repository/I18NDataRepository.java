package com.mmn.translation.repository;

import java.util.List;
import java.util.Optional;

import com.mmn.translation.model.I18NData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I18NDataRepository extends MongoRepository<I18NData, String> {
    List<I18NData> findAllByLanguage(final String language);
    Optional<I18NData> findByLanguageAndKey(final String language, final String key);
}
