package com.uber.rib.root.game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.core.Initializer;
import com.uber.rib.tutorial1.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Top level view for {@link GameBuilder.GameScope}.
 */
class GameView extends PercentRelativeLayout implements GameInteractor.GamePresenter {

  public GameView(Context context) {
    this(context, null);
  }

  public GameView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public GameView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  private TextView[][] imageButtons;
  private TextView titleView;

  @Override
  public void setPromptPlayer() {
    this.hideProgressBar();
    this.showPlayerPrompt();
  }

  @Override
  public void setWaitingForMove() {
    this.showProgressBar();
    this.hidePlayerPrompt();
  }

  @Initializer
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    imageButtons = new TextView[4][];
    imageButtons[0] =
            new TextView[]{
                    (TextView) findViewById(R.id.button11),
                    (TextView) findViewById(R.id.button12),
                    (TextView) findViewById(R.id.button13),
                    (TextView) findViewById(R.id.button14)
            };
    imageButtons[1] =
            new TextView[]{
                    (TextView) findViewById(R.id.button21),
                    (TextView) findViewById(R.id.button22),
                    (TextView) findViewById(R.id.button23),
                    (TextView) findViewById(R.id.button24)
            };
    imageButtons[2] =
            new TextView[]{
                    (TextView) findViewById(R.id.button31),
                    (TextView) findViewById(R.id.button32),
                    (TextView) findViewById(R.id.button33),
                    (TextView) findViewById(R.id.button34)
            };
    imageButtons[3] =
            new TextView[]{
                    (TextView) findViewById(R.id.button41),
                    (TextView) findViewById(R.id.button42),
                    (TextView) findViewById(R.id.button43),
                    (TextView) findViewById(R.id.button44)
            };
    titleView = (TextView) findViewById(R.id.title);
  }

  @Override
  public Observable<BoardCoordinate> pieceTouched() {
    ArrayList<Observable<BoardCoordinate>> observables = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        final int finalI = i;
        final int finalJ = j;
        observables.add(
                RxView.clicks(imageButtons[i][j])
                        .map(
                          new Function<Object, BoardCoordinate>() {
                            @Override
                            public BoardCoordinate apply(Object irrelevant) throws Exception {
                              return new BoardCoordinate(finalI, finalJ);
                            }
                          }));
      }
    }
    return Observable.merge(observables);
  }

  @Override
  public void addRedPiece(BoardCoordinate xy) {
    TextView textView = imageButtons[xy.getX()][xy.getY()];
    textView.setBackground(getResources().getDrawable(R.drawable.red_piece));
    textView.setClickable(false);
    // set background to
  }

  @Override
  public void addBluePiece(BoardCoordinate xy) {
    TextView textView = imageButtons[xy.getX()][xy.getY()];
    textView.setText("O");
    textView.setClickable(false);
  }

  private void hideProgressBar() {
    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_loader);
    progressBar.setVisibility(View.INVISIBLE);
  }

  private void showProgressBar() {
    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_loader);
    progressBar.setVisibility(View.VISIBLE);
  }

  private void showPlayerPrompt() {
    TextView textView = (TextView) findViewById(R.id.prompt);
    textView.setVisibility(View.VISIBLE);
  }

  private void hidePlayerPrompt() {
    TextView textView = (TextView) findViewById(R.id.prompt);
    textView.setVisibility(View.INVISIBLE);
  }


}
