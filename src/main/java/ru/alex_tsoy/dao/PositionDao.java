package ru.alex_tsoy.dao;

import ru.alex_tsoy.entity.Position;

import java.util.List;

public interface PositionDao {
    Position findById(int id);
    List<Position> findAllPositions();
    void save(Position position);
    void update(Position position);
    void delete(Position position);
}
