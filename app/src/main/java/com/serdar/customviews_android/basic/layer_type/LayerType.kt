package com.serdar.customviews_android.basic.layer_type

/*
During animations your views may be redrawn each frame.
 If you use view layers, instead of having to redraw each frame,
  views render once into an off-screen buffer which can be reused.

In addition, hardware layers are cached on the GPU,
which makes certain operations during animation much faster.
 Simple transformations (translation, rotation, scaling and alpha) can be rendered quickly with layers.
 Since many animations are just a combination of these transformations, layers can supercharge animation performance.
 */

//View.setLayerType(View.LAYER_TYPE_HARDWARE, null)