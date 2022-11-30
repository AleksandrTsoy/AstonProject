package ru.alex_tsoy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex_tsoy.entity.Position;
import ru.alex_tsoy.exception.EntityNotFoundException;
import ru.alex_tsoy.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Position>> getAllPositions() {
        return ResponseEntity.ok(positionService.findAllPositions());
    }

    @PostMapping("/create")
    public ResponseEntity<Position> createPosition(@RequestBody @Valid Position position) {
        try {
            positionService.save(position);
            return ResponseEntity.ok(positionService.findById(position.getId()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Position> updatePosition(@RequestBody @Valid Position position) {
        try {
            positionService.update(position);
            return ResponseEntity.ok(positionService.findById(position.getId()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deletePosition(@RequestBody @Valid Position position) {
        try {
            positionService.deleteById(position.getId());
            return ResponseEntity.ok("Delete success.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
