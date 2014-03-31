/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 * All Rights Reserved
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package heronarts.lx.transition;

import heronarts.lx.LX;
import processing.core.PConstants;

public class DodgeTransition extends BlendTransition {
  public DodgeTransition(LX lx) {
    super(lx, PConstants.DODGE);
  }
}
