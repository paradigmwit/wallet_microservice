package com.leovegas.wallet.controller;

import com.leovegas.wallet.dto.PlayerDTO;
import com.leovegas.wallet.exceptions.PlayerDoesntExistException;
import com.leovegas.wallet.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerDTO>> getUsers(){

        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping(value = "/player/{playerName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> player(@PathVariable("playerName") final String playerName) throws PlayerDoesntExistException {

        return ResponseEntity.ok(playerService.getPlayerByPlayerName(playerName));
    }

    @PostMapping(value = "/player", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity player(@RequestBody final PlayerDTO playerDTO) throws RuntimeException{

        Optional<Long> playerId = playerService.createPlayer(playerDTO);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
