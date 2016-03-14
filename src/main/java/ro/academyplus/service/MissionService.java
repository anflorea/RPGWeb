package ro.academyplus.service;

import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.academyplus.model.Hero;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by azaha on 14.03.2016.
 */
@Service
public class MissionService {

    @Autowired
    HttpServletRequest request;

    public int[][] initMap(int heroLevel) {

        int mapSize;
        Random random = new Random();
        int numberOfMonsters = 0;

        mapSize = getMapSize(heroLevel);

        int[][] map = new int[mapSize][mapSize];

        while (numberOfMonsters < mapSize) {
            int i = random.nextInt(10);
            int j = random.nextInt(10);
            map[i][j] = 2;
            numberOfMonsters++;
        }

        return (map);
    }

    public int getMapSize(int heroLevel) {

        int mapSize;

        if (heroLevel >= 10)
            mapSize = 21;
        else if (heroLevel >= 5)
            mapSize = 15;
        else
            mapSize = 11;

        return mapSize;

    }

    public void insesrtInsation(String whatToDo, Hero hero) {
        request.setAttribute(whatToDo, hero);
    }



}
