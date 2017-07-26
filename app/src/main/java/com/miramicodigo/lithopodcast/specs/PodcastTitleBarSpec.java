package com.miramicodigo.lithopodcast.specs;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

@LayoutSpec
public class PodcastTitleBarSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c,
                                          @Prop String title,
                                          @Prop String author) {

        return Column.create(c)
                .paddingDip(YogaEdge.ALL, 16)
                .child(
                        Text.create(c)
                                .text(title)
                                .textSizeSp(20)
                ).child(
                        Text.create(c)
                                .text(author)
                                .textSizeSp(12)
                ).build();
    }
}
