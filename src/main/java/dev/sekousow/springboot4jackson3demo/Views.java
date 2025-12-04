package dev.sekousow.springboot4jackson3demo;

/**
 * @author Sékou Sallah Sow <sowsekou@hotmail.com>
 */
public class Views {

    /**
     * Summary View: For simple quick data return
     */
    public interface Summary {

    }

    /**
     * Public View: for public APIs needed fields
     */
    public interface Public extends Summary {

    }

    /**
     * Internal View: Use for sensitive fields
     */
    public interface Internal extends Public {
    }
}
