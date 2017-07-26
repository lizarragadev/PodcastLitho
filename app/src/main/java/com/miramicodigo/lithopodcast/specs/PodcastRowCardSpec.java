package com.miramicodigo.lithopodcast.specs;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Card;
import com.miramicodigo.lithopodcast.data.Podcast;

@LayoutSpec
public class PodcastRowCardSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop Podcast podcast) {

        return Column.create(c)
                .child(Card.create(c)
                        .content(PodcastRow
                                .create(c)
                                .podcast(podcast)))
                .build();
    }
}
