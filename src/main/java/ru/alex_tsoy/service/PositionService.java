package ru.alex_tsoy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex_tsoy.dao.PositionDao;
import ru.alex_tsoy.entity.Position;
import ru.alex_tsoy.exception.EntityNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionDao positionDao;

    public Position findById(int id) throws EntityNotFoundException {
        Position position = positionDao.findById(id);
        if (position != null) {
            return position;
        } else {
            throw new EntityNotFoundException("Position not found.");
        }
    }

    public List<Position> findAllPositions() {
        return positionDao.findAllPositions();
    }

    public void save(Position position) {
        positionDao.save(position);
    }

    public void update(Position updatePosition) throws EntityNotFoundException {
        Position position = positionDao.findById(updatePosition.getId());
        if (position != null) {
            position.setPosition(updatePosition.getPosition());
            positionDao.update(position);
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }

    public void deleteById(int id) throws EntityNotFoundException {
        Position position = positionDao.findById(id);
        if (position != null) {
            positionDao.delete(position);
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }
}
