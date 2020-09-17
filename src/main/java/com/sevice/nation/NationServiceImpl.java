package com.sevice.nation;

import com.model.Nation;
import com.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class NationServiceImpl implements NationService {

    @Autowired
    private NationRepository nationRepository;

    @Override
    public Page<Nation> findAll(Pageable pageable) {
        return nationRepository.findAll(pageable);
    }

    @Override
    public void save(Nation entity) {
        nationRepository.save(entity);
    }

    @Override
    public void update(Nation entity) {
        nationRepository.save(entity);
    }

    @Override
    public Optional<Nation> findById(Long id) {
        return nationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        nationRepository.deleteById(id);
    }

    @Override
    public void delete(Nation entity) {
        nationRepository.delete(entity);
    }
}
