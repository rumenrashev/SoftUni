package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.json.BranchJsonImportDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static hiberspring.common.Constants.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final TownRepository townRepository;

    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository,
                             ModelMapper modelMapper, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(BRANCHES_PATH);
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(this.gson.fromJson(readBranchesJsonFile(), BranchJsonImportDto[].class))
                .forEach(bdto->{
                    Optional<Town> townByName = this.townRepository.getByName(bdto.getTown());
                    if (townByName.isPresent() && this.validationUtil.isValid(bdto)){
                        Branch branch = this.modelMapper.map(bdto, Branch.class);
                        branch.setTown(townByName.get());
                        this.branchRepository.saveAndFlush(branch);
                        sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,BRANCH,branch.getName()));
                    }else {
                        sb.append(INCORRECT_DATA_MESSAGE);
                    }
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }
}
