package cardealer.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomGenerator {

    private  final Random random;

    @Autowired
    public RandomGenerator(Random random) {
        this.random = random;
    }

    public  Long generateId(long size){
        return (long)random.nextInt((int)size) + 1;
    }

    public int generateRandomCountOfParts(){
        int random =  this.random.nextInt(11);
        random += 10;
        return random;
    }
}
