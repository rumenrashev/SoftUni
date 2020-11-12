package com.softuni.springadvancedquering.lab.shampoocompany.services.impl;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Label;
import com.softuni.springadvancedquering.lab.shampoocompany.repositories.LabelRepository;
import com.softuni.springadvancedquering.lab.shampoocompany.services.LabelService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label getLabelById(Long id) {
        Optional<Label> label = this.labelRepository.findById(id);
        return label.orElse(null);
    }
}
