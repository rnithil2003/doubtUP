package com.doubtup;

import java.util.List; // <- NEW IMPORT

import org.springframework.web.bind.annotation.DeleteMapping;
// we need to import the labels we are about to use
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired; // <- NEW IMPORT
import org.springframework.web.bind.annotation.PathVariable; // <- NEW IMPORT
 import org.springframework.web.bind.annotation.RestController;
 // NEW: We need to import PostMapping and RequestBody
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  this is our waiter
 * we are telling Spring boot that this class will handle
 *  incoming web requests from a user's browser.
 */

 @RestController // <- NEW WORD 1
public class NotesController {

    @Autowired
    // creating a @Autowired to manage our notes in a seperate class
    private NoteService noteService;

    
    /**
     * This is a "rule" for our waiter
     * it connects a URL path to a java method
     * 
     */

     @GetMapping("/hello") // <- NEW word 2
    public String sayHello() {
        // this is just a normal java method
        // that return a simple string
        return "Hello from my first server!";
    }

    // NEW RULE: It listens for "/note"
@GetMapping("/note")
// NEW "CATCHER": It uses @RequestParam to look
// for a "title" parameter *after* the "?"
public Notes getNoteByTitle(@RequestParam String title) {
    // The service logic is the same!
    return noteService.findTheNote(title);
}
    /**
     * --- THIS IS YOUR NEW METHOD ---
     * 1. It uses @PostMapping to CREATE new data.
     * 2. It listens on the path "/notes".
     * 3. It uses @RequestBody to "catch" the incoming JSON
     * and turn it into a Note object.
     */
    @PostMapping("/notes")
    public Notes createNote(@RequestBody Notes note) {
        
        // Just pass the new Note object (from the user)
        // to our "Cook" (NoteService) to be saved.
        return noteService.createNewNote(note);
    }
    /**
     * --- THIS IS YOUR NEW METHOD ---
     * 1. It uses @GetMapping to READ data.
     * 2. It listens on the path "/notes".
     * 3. It returns a List of all notes.
     */
    @GetMapping("/notes")
    public List<Notes> getAllNotes() {
        // Just pass the work to the "Cook" (NoteService)
        return noteService.getAllNotes();
    }
    // ... inside NoteController.java ...
    // (This REPLACES your old updateNote method)

    // 2. This REPLACES your old @PutMapping
@PutMapping("/note") // Listens for "/note"
public Notes updateNote(@RequestParam String title, @RequestBody Notes noteDetails) {
    // The service logic is the same!
    return noteService.updateNote(title, noteDetails);
}

// 3. This REPLACES your old @DeleteMapping
@DeleteMapping("/note") // Listens for "/note"
public String deleteNote(@RequestParam String title) {
    noteService.deleteNote(title);
    return "Note with title '" + title + "' was successfully deleted.";
}

    
    
 }

    

