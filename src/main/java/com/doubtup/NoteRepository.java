package com.doubtup;

import java.util.Optional; // <--- NEW IMPORT
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is our "Filing Cabinet Manager" (a Repository).
 * It's an interface that gets "magic methods" from JpaRepository
 * to talk to the database for us.
 */
public interface NoteRepository extends JpaRepository<Notes, Long> { // <- NEW WORDS 2, 3, 4
    
    // That's it! We don't write anything in here (for now).
    // All the magic methods like save(), findById(), findAll()
    // are automatically given to us by JpaRepository.

    // --- THIS IS YOUR NEW LINE ---
    // This is a "magic" method.
    Optional<Notes> findByTitle(String title); // <--- NEW WORD: Optional
} 
    

