package json.service.impl;

import com.google.gson.Gson;
import json.constant.InOutPaths;
import json.domain.dto.UserSeedDto;
import json.domain.dto.export.ProductExportDto;
import json.domain.dto.export.SoldProductExportDto;
import json.domain.dto.export.UserExportDto;
import json.domain.dto.export.taks4.ProductRepoDto;
import json.domain.dto.export.taks4.ProductViewDto;
import json.domain.dto.export.taks4.UserRepoDto;
import json.domain.dto.export.taks4.UserViewDto;
import json.domain.entities.Product;
import json.domain.entities.User;
import json.domain.repositories.ProductRepository;
import json.domain.repositories.UserRepository;
import json.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final Gson gson;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, ProductRepository productRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.gson = gson;
    }

    @Override
    @Transactional
    public void seedUsers() throws IOException {
        String fileContent = String.join("", Files.readAllLines(Path.of(InOutPaths.USERS_IN)));
        UserSeedDto[] userSeedDtos = this.gson.fromJson(fileContent, UserSeedDto[].class);
        for (UserSeedDto userSeedDto : userSeedDtos) {
            User user = this.modelMapper.map(userSeedDto, User.class);
            this.userRepository.save(user);
        }
    }

    @Override
    public void getAllWithAtLeastOneSoldItem() {
        Set<User> users = this.userRepository
                .getAllWithAtLeastOneSoldProduct();
        Set<UserExportDto> result = new LinkedHashSet<>();
        for (User user : users) {
            UserExportDto userDto = modelMapper.map(user, UserExportDto.class);
            for (Product product : user.getSoldProducts()) {
                userDto.getSoldProducts().add(modelMapper.map(product, SoldProductExportDto.class));
            }
            result.add(userDto);
        }
        try {
            FileWriter writer = new FileWriter(InOutPaths.TASK_02_OUT);
            this.gson.toJson(result,writer);
            writer.flush();
            writer.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void selectAllUsersWithSoldItem() {
        Set<User> users = this.userRepository.findAllWithAtLeastOneProductSold();
        UserRepoDto userRepoDto = new UserRepoDto();
        userRepoDto.setCount(users.size());
        for (User user : users) {
            UserViewDto userDto = modelMapper.map(user, UserViewDto.class);
            ProductRepoDto productRepoDto = new ProductRepoDto();
            for (Product product : user.getSoldProducts()) {
                productRepoDto.getProducts().add(modelMapper.map(product,ProductViewDto.class));
            }
            productRepoDto.setCount(productRepoDto.getProducts().size());
            userDto.setProductRepoDto(productRepoDto);
            userRepoDto.getUsers().add(userDto);
        }

        try {
            FileWriter writer = new FileWriter(InOutPaths.TASK_04_OUT);
            this.gson.toJson(userRepoDto,writer);
            writer.flush();
            writer.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
