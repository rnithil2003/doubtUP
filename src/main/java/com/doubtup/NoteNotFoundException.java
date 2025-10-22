package com.doubtup;

/**
 * This is our custom "error blueprint."
 * We throw this error when we can't find a note.
 *
 * We extend RuntimeException, which is an "unchecked" exception.
 */

public class NoteNotFoundException  extends RuntimeException{
    /**
     * This is the "message in a bottle" constructor.
     * It passes the error message up to the parent
     * RuntimeException class.
     */
    public NoteNotFoundException(String message) {
        super(message);
    }
}
