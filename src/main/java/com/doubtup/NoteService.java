package com.doubtup;

// NEW: We need to import 'List'
import java.util.List;
import java.util.Optional; // <--- NEW IMPORT
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This is our "Cook"
@Service
public class NoteService {

    // Ask the Boss for the "Filing Cabinet Manager"
    @Autowired
    private NoteRepository noteRepository;

    // (The HashMap and Constructor are now GONE)

    /**
     * This is our upgraded "find" logic.
     * It now uses the Database Repository.
     */
    public Notes findTheNote(String title) throws NoteNotFoundException {
        
        // 1. Ask the "Filing Cabinet Manager" to find the note by its title
        //    (This uses the magic method we just built)
        Optional<Notes> foundNoteBox = noteRepository.findByTitle(title);

        // 2. Check if the "box" has a note in it
        if (foundNoteBox.isPresent()) {
            return foundNoteBox.get(); // Take the note out of the box
        } else {
            // 3. The box is empty! Throw our custom error.
            //    (This is your exception handling from before!)
            throw new NoteNotFoundException("Could not find note with title: " + title);
        }
    }
    /**
     * This is our new "create" logic.
     * It just takes a Note object and tells the
     * "Filing Cabinet Manager" to save it.
     */
    public Notes createNewNote(Notes note) {
        // We just call the built-in "save" method
        // from JpaRepository. It does all the work!
        return noteRepository.save(note);
    }

    /**
     * This is our new "read all" logic.
     * It asks the "Filing Cabinet Manager" for
     * every note it has.
     */
    public List<Notes> getAllNotes() {
        // We just call the built-in "findAll" method
        // from JpaRepository. It does all the work!
        return noteRepository.findAll();
    }

    // ... inside NoteService.java ...
    // (This REPLACES your old updateNote method)

    /**
     * This is our NEW "update" logic, using title instead of id.
     * You were 100% right, this is a better design for our app.
     */
    public Notes updateNote(String title, Notes noteDetails) throws NoteNotFoundException {
        
        // 1. Find the "original" book on the shelf *by its title*
        //    (We can re-use the "magic" findByTitle method we already built!)
        Notes originalNote = noteRepository.findByTitle(title)
            .orElseThrow(() -> new NoteNotFoundException("Could not find note with title: " + title));

        // 2. Update the original note with the new details.
        //    This even lets you change the title itself!
        originalNote.setTitle(noteDetails.getTitle());
        originalNote.setContent(noteDetails.getContent());

        // 3. Save the updated note. JPA is smart: it sees the ID
        //    on originalNote and knows to UPDATE, not create a new one.
        return noteRepository.save(originalNote);
    }

    // ... inside NoteService.java ...
    // (You have your other methods here)

    /**
     * This is our new "delete" logic.
     * It finds the note by its title and tells the
     * "Filing Cabinet Manager" (Repository) to delete it.
     */
    public void deleteNote(String title) throws NoteNotFoundException {
        
        // 1. Find the book on the shelf (same as update!)
        //    This also ensures we throw an error if it doesn't exist.
        Notes noteToDelete = noteRepository.findByTitle(title)
            .orElseThrow(() -> new NoteNotFoundException("Could not find note with title: " + title));

        // 2. Tell the "Manager" to delete it.
        //    We use the built-in delete() method from JpaRepository.
        noteRepository.delete(noteToDelete);
    }
}