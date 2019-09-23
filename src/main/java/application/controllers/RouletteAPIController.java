package application.controllers;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/blueprints")
public class RouletteAPIController{

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> GetAllBlueprintFilter(){
        try {
            return new ResponseEntity<>("",HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}