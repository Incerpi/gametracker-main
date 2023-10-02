package br.com.fiap.gametracker.games;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamesService {

    @Autowired
    GamesRepository repository;

    public List<Games> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id){
        var game = repository.findById(id);
        if(game.isEmpty()) return false;

        repository.deleteById(id);
        return true;
    }
    
}
