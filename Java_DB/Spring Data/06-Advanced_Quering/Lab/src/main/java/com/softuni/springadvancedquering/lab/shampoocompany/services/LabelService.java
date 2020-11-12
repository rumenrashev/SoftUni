package com.softuni.springadvancedquering.lab.shampoocompany.services;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Label;
import org.springframework.stereotype.Service;

@Service
public interface LabelService {

    Label getLabelById(Long id);



}
