package com.uber.rib.root.logged_out;

import com.uber.rib.core.RibTestBasePlaceholder;
import com.uber.rib.root.home.LoggedOutBuilder;
import com.uber.rib.root.home.LoggedOutInteractor;
import com.uber.rib.root.home.LoggedOutRouter;
import com.uber.rib.root.home.LoggedOutView;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoggedOutRouterTest extends RibTestBasePlaceholder {

  @Mock LoggedOutBuilder.Component component;
  @Mock
  LoggedOutInteractor interactor;
  @Mock
  LoggedOutView view;

  private LoggedOutRouter router;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    router = new LoggedOutRouter(view, interactor, component);
  }
}
