package com.buildaant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.buildaant.AntPiece.*;
import static org.junit.Assert.*;

public class AntTest {
    private Ant ant;

    @Before
    public void setUp() {
        ant = new Ant();
    }

    @Test
    public void add_shouldThrowNotPossibleException_ifPieceAlreadyPresent() throws NotPossibleException {
        ant.add(HEAD);
        ant.add(BODY);
        ant.add(ANTENNA);
        ant.add(EYES);
        ant.add(LEGS);
        ant.add(TAIL);

        for (AntPiece piece : AntPiece.values()) {
            try {
                ant.add(piece);
            }
            catch (NotPossibleException e) {
                assertEquals("Already have this piece: " + piece, e.getMessage());
            }
        }
    }

    @Test
    public void add_shouldAcceptEverything_ifHeadAndBodyPresent() throws NotPossibleException {
        ant.add(HEAD);
        ant.add(BODY);
        ant.add(ANTENNA);
        ant.add(EYES);
        ant.add(LEGS);
        ant.add(TAIL);

        for (AntPiece piece : AntPiece.values()) {
            assertTrue(ant.hasPiece(piece));
        }
        assertTrue(ant.isComplete());
        ant.show();
    }

    @Test
    public void add_shouldThrowNotPossibleException_ifOnlyBodyPresent_forAntennaAndEyes() {
        try {
            ant.add(BODY);
            ant.add(ANTENNA);
            fail("Should have thrown NotPossibleException");
        }
        catch (NotPossibleException e) {
            assertEquals("Only legs and tail can be added to body", e.getMessage());
            assertTrue(ant.hasPiece(BODY));
            assertFalse(ant.hasPiece(ANTENNA));
        }

        try {
            ant.add(EYES);
            fail("Should have thrown NotPossibleException");
        }
        catch (NotPossibleException e) {
            assertEquals("Only legs and tail can be added to body", e.getMessage());
            assertFalse(ant.hasPiece(EYES));
        }
    }

    @Test
    public void add_shouldAcceptTailAndLegs_ifBodyPresent() throws NotPossibleException {
        ant.add(BODY);
        ant.add(TAIL);
        ant.add(LEGS);
        assertTrue(ant.hasPiece(BODY));
        assertTrue(ant.hasPiece(TAIL));
        assertTrue(ant.hasPiece(LEGS));
        ant.show();
    }

    @Test
    public void add_shouldThrowNotPossibleException_ifOnlyHeadPresent_forTailAndLegs() {
        try {
            ant.add(HEAD);
            ant.add(TAIL);
            fail("Should have thrown NotPossibleException");
        }
        catch (NotPossibleException e) {
            assertEquals("Only antenna and eyes can be added to head", e.getMessage());
            assertTrue(ant.hasPiece(HEAD));
            assertFalse(ant.hasPiece(TAIL));
        }

        try {
            ant.add(LEGS);
            fail("Should have thrown NotPossibleException");
        }
        catch (NotPossibleException e) {
            assertEquals("Only antenna and eyes can be added to head", e.getMessage());
            assertFalse(ant.hasPiece(LEGS));
        }
    }

    @Test
    public void add_shouldAcceptAntennaAndEyes_ifHeadPresent() throws NotPossibleException {
        ant.add(HEAD);
        ant.add(ANTENNA);
        ant.add(EYES);
        assertTrue(ant.hasPiece(HEAD));
        assertTrue(ant.hasPiece(ANTENNA));
        assertTrue(ant.hasPiece(EYES));
        ant.show();
    }

    @Test
    public void add_shouldAcceptBody_ifNoBodyPresent() throws NotPossibleException {
        ant.add(BODY);
        assertTrue(ant.hasPiece(BODY));
        ant.show();
    }

    @Test
    public void add_shouldAcceptHead_ifNoHeadPresent() throws NotPossibleException {
        ant.add(HEAD);
        assertTrue(ant.hasPiece(HEAD));
        ant.show();
    }

    @Test
    public void add_shouldThrowNotPossibleException_whenNoBodyOrHeadPresent_forTail() {
        try {
            ant.add(TAIL);
            fail("Should have thrown NotPossibleException");
        }
        catch (NotPossibleException e) {
            assertEquals("Must have head or body first", e.getMessage());
            assertFalse(ant.hasPiece(TAIL));
        }
        ant.show();
    }

    @Test
    public void add_shouldThrowNotPossibleException_whenNoBodyOrHeadPresent_forLegs() {
        try {
            ant.add(LEGS);
            fail("Should have thrown NotPossibleException");
        }
        catch (NotPossibleException e) {
            assertEquals("Must have head or body first", e.getMessage());
            assertFalse(ant.hasPiece(LEGS));
        }
        ant.show();
    }

    @Test
    public void add_shouldThrowNotPossibleException_whenNoBodyOrHeadPresent_forAntenna() {
        try {
            ant.add(ANTENNA);
            fail("Should have thrown NotPossibleException");
        }
        catch (NotPossibleException e) {
            assertEquals("Must have head or body first", e.getMessage());
            assertFalse(ant.hasPiece(ANTENNA));
        }
        ant.show();
    }

    @Test
    public void add_shouldThrowNotPossibleException_whenNoBodyOrHeadPresent_forEyes() {
        try {
            ant.add(EYES);
            fail("Should have thrown NotPossibleException");
        }
        catch (NotPossibleException e) {
            assertEquals("Must have head or body first", e.getMessage());
            assertFalse(ant.hasPiece(EYES));
        }
        ant.show();
    }
}