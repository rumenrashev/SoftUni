package com.softuni.springadvancedquering.lab.shampoocompany.repositories;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {

}
