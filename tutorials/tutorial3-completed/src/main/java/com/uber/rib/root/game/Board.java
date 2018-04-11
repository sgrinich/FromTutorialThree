/*
 * Copyright (C) 2017. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.rib.root.game;

import android.util.Log;

import javax.inject.Inject;

public class Board {

  static final int ROWS = 4;
  static final int COLS = 4;

  MarkerType[][] cells;

  @Inject
  Board() {
    cells = new MarkerType[ROWS][COLS];
    for (int row = 0; row < ROWS; ++row) {
      for (int col = 0; col < COLS; ++col) {
        cells[row][col] = null;
      }
    }
  }

  /**
   * Return true if it is a draw (i.e., no more EMPTY cell)
   */
  boolean isDraw() {
    for (int row = 0; row < ROWS; ++row) {
      for (int col = 0; col < COLS; ++col) {
        if (cells[row][col] == null) {
          return false;
        }
      }
    }
    return !hasWon();
  }

  boolean canPlace(Integer column) {
      return cells[0][column] == null;
  }

  BoardCoordinate placePiece(Integer column, MarkerType type) {

    BoardCoordinate coordinate;
    for (int row = (ROWS - 1); row >= 0; row--) {

        if (cells[row][column] == null) {
            cells[row][column] = type;
            coordinate = new BoardCoordinate(row, column);

            return coordinate;
        }
    }

    Log.e("ERROR: ", "Could not place piece on board");
    coordinate = new BoardCoordinate(-1, -1);

    return coordinate;
  }


  boolean hasWon() {
    // Check for horizontal wins
    for (int row = 0; row < ROWS; row++) {
        if ((this.cells[row][0] != null) && (this.cells[row][1] == this.cells[row][0]) && (this.cells[row][2] == this.cells[row][0]) && (this.cells[row][3] == this.cells[row][0])) {
            return true;
        }
    }

    // Check for vertical wins
    for (int col = 0; col < COLS; col++) {
        if ((this.cells[0][col] != null) && (this.cells[1][col] == this.cells[0][col]) && (this.cells[2][col] == this.cells[0][col]) && (this.cells[3][col] == this.cells[0][col])) {
            return true;
        }
    }

    // Check right diagonal win
    if ((this.cells[0][0] != null) && (this.cells[1][1] == this.cells[0][0]) && (this.cells[2][2] == this.cells[0][0]) && (this.cells[3][3] == this.cells[0][0])) {
        return true;
    }

    // Check left diagonal win
    if ((this.cells[0][3] != null) && (this.cells[1][2] == this.cells[0][3]) && (this.cells[2][1] == this.cells[0][3])  && (this.cells[3][0] == this.cells[3][3])) {
        return true;
    }

    return false;
  }





  // TODO: Replace this with the recursive function
  boolean hasWon(MarkerType theSeed) {
    return false;
//    return ((cells[currentRow][0] == theSeed
//        && cells[currentRow][1] == theSeed
//        && cells[currentRow][2] == theSeed)
//        || (cells[0][currentCol] == theSeed
//        && cells[1][currentCol] == theSeed
//        && cells[2][currentCol] == theSeed)
//        || (currentRow == currentCol
//        && cells[0][0] == theSeed
//        && cells[1][1] == theSeed
//        && cells[2][2] == theSeed)
//        || (currentRow + currentCol == 2
//        && cells[0][2] == theSeed
//        && cells[1][1] == theSeed
//        && cells[2][0] == theSeed));
  }

  enum MarkerType {
    RED,
    BLUE
  }
}