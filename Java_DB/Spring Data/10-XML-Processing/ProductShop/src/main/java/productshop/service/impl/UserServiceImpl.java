package productshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productshop.domain.dto.in.users.UserCollectionXmlImportDto;
import productshop.domain.dto.out.task02.SoldProductDto;
import productshop.domain.dto.out.task02.UserWithSoldProductsDto;
import productshop.domain.dto.out.task02.SetOfUsersWithSoldProductsDto;
import productshop.domain.dto.out.task04.SetOfUsersDto;
import productshop.domain.dto.out.task04.UserDto;
import productshop.domain.entity.Product;
import productshop.domain.entity.User;
import productshop.domain.repository.UserRepository;
import productshop.service.api.UserService;
import productshop.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

import java.util.LinkedHashSet;
import java.util.Set;

import static productshop.constants.FilePaths.*;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void seedUsers() throws JAXBException {
        UserCollectionXmlImportDto usersDto = this.xmlParser.fromXml(UserCollectionXmlImportDto.class, USERS_IN);
        usersDto.getUsers()
                .stream()
                .map(udto-> this.modelMapper.map(udto, User.class))
                .forEach(this.userRepository::save);
    }

    @Override
    public void getUsersSoldProducts() throws JAXBException {
        Set<User> users = this.userRepository.getAllWithAtLeastOneProductSoldOrderByLastNameAndFirstName();
        SetOfUsersWithSoldProductsDto setOfUsersWithSoldProductsDto = new SetOfUsersWithSoldProductsDto();
        Set<UserWithSoldProductsDto> userDtos = new LinkedHashSet<>();
        for (User user : users) {
            UserWithSoldProductsDto userDto =
                    this.modelMapper.map(user, UserWithSoldProductsDto.class);
            userDtos.add(userDto);
        }
        setOfUsersWithSoldProductsDto.setUsers(userDtos);
        this.xmlParser.toXml(SetOfUsersWithSoldProductsDto.class, setOfUsersWithSoldProductsDto,USERS_SOLD_PRODUCTS);
    }

    @Override
    public void getUsersAndProducts() throws JAXBException {
        Set<User> users = this.userRepository.getAllWithAtLeastOneProductSoldOrderBySoldProductsCountDescAndLastName();
        Set<UserDto> userDtos = new LinkedHashSet<>();
        for (User user : users) {
            UserDto userDto = this.modelMapper.map(user, UserDto.class);
            userDto.getSoldProducts().setCount(user.getSoldProducts().size());
            userDtos.add(userDto);
        }
        SetOfUsersDto setOfUsersDto = new SetOfUsersDto();
        setOfUsersDto.setUsers(userDtos);
        setOfUsersDto.setCount(userDtos.size());
        this.xmlParser.toXml(SetOfUsersDto.class,setOfUsersDto,USERS_AND_PRODUCTS);
    }
}
