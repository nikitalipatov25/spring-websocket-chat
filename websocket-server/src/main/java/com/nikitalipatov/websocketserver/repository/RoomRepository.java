package com.nikitalipatov.websocketserver.repository;

import com.nikitalipatov.common.dto.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class RoomRepository {

    public static final String HASH_KEY_NAME = "ROOM-ID";
    private final RedisTemplate redisTemplate;


    public Room save(Room room){
        // SETS menu object in MENU-ITEM hashmap at menuId key
        redisTemplate.opsForHash().put(HASH_KEY_NAME,room.getId(),room);
        return room;
    }

    public List<Room> findAll(){
        // GET all Menu values
        return redisTemplate.opsForHash().values(HASH_KEY_NAME);
    }

    public Room findItemById(String id){
        // GET menu object from MENU-ITEM hashmap by menuId key
        return (Room) redisTemplate.opsForHash().get(HASH_KEY_NAME,id);
    }

    public void deleteRoom(String id){
        // DELETE the hashkey by menuId from MENU-ITEM hashmap
        redisTemplate.opsForHash().delete(HASH_KEY_NAME,id);
    }
}
