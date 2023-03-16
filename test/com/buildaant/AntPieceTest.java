package com.buildaant;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AntPieceTest {
    @Test
    public void imagesAreLoaded() {
        for (AntPiece piece : AntPiece.values()) {
            String image = piece.getImage();
            List<String> imageLines = piece.getImageLines();
        }
    }

}