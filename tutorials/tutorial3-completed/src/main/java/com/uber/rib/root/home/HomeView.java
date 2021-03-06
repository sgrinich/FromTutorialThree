package com.uber.rib.root.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.core.Initializer;
import com.uber.rib.tutorial1.R;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Top level view for {@link HomeBuilder.HomeScope}.
 */
public class HomeView extends LinearLayout implements HomeInteractor.HomePresenter {

    private Button playButton;


    public HomeView(Context context) {
    this(context, null);
  }

  public HomeView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public HomeView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }



    @Initializer
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        playButton = (Button) findViewById(R.id.play_button);
    }

  @Override
  public Observable<Boolean> playGame() {
    return RxView.clicks(findViewById(R.id.play_button))
            .map(new Function<Object, Boolean>() {
              @Override
              public Boolean apply(Object o) throws Exception {
                return true;
              }
            });
  }

  @Override
  public Observable<Boolean> choseRedColor() {
    return RxView.clicks(findViewById(R.id.red_button))
            .map(new Function<Object, Boolean>() {
              @Override
              public Boolean apply(Object o) throws Exception {
                return true;
              }
            });
  }

  @Override
  public Observable<Boolean> choseBlueColor() {
    return RxView.clicks(findViewById(R.id.blue_button))
            .map(new Function<Object, Boolean>() {
              @Override
              public Boolean apply(Object o) throws Exception {
                return true;
              }
            });
  }

  @Override
  public Observable<Boolean> chosePlayerFirst() {
    final RadioButton radioButton = (RadioButton) findViewById(R.id.you_radio_button);

    return RxView.clicks(radioButton)
            .map(new Function<Object, Boolean>() {
              @Override
              public Boolean apply(Object o) throws Exception {
                return true;
              }
            });
  }

  @Override
  public Observable<Boolean> choseComputerFirst() {
    final RadioButton radioButton = (RadioButton) findViewById(R.id.computer_radio_button);

    return RxView.clicks(radioButton)
            .map(new Function<Object, Boolean>() {
              @Override
              public Boolean apply(Object o) throws Exception {
                return true;
              }
            });

  }
}
