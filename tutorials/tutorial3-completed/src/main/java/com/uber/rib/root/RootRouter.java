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

package com.uber.rib.root;

import android.support.annotation.Nullable;

import com.uber.rib.core.ViewRouter;
import com.uber.rib.root.logged_in.LoggedInBuilder;
import com.uber.rib.root.home.HomeBuilder;
import com.uber.rib.root.home.HomeRouter;

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 */
public class RootRouter extends ViewRouter<RootView, RootInteractor, RootBuilder.Component> {

  private final HomeBuilder homeBuilder;
  private final LoggedInBuilder loggedInBuilder;
  @Nullable private HomeRouter homeRouter;

  RootRouter(
      RootView view,
      RootInteractor interactor,
      RootBuilder.Component component,
      HomeBuilder homeBuilder,
      LoggedInBuilder loggedInBuilder) {
    super(view, interactor, component);
    this.homeBuilder = homeBuilder;
    this.loggedInBuilder = loggedInBuilder;
  }

  void attachHome() {
    homeRouter = homeBuilder.build(getView());
    attachChild(homeRouter);
    getView().addView(homeRouter.getView());
  }

  void detachHome() {
    if (homeRouter != null) {
      detachChild(homeRouter);
      getView().removeView(homeRouter.getView());
      homeRouter = null;
    }
  }

  void attachLoggedIn() {
    attachChild(loggedInBuilder.build());
  }
}
