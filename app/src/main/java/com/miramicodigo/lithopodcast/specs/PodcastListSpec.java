package com.miramicodigo.lithopodcast.specs;

import android.support.v7.widget.OrientationHelper;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;
import com.miramicodigo.lithopodcast.data.Podcast;

import java.util.List;

@LayoutSpec
public class PodcastListSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c,
                                          @Prop List<Podcast> podcasts) {

        RecyclerBinder recyclerBinder = new RecyclerBinder(
                c,
                new LinearLayoutInfo(
                        c.getApplicationContext(),
                        OrientationHelper.VERTICAL,
                        false
                ));

        if (podcasts != null) {
            populateList(c, recyclerBinder, podcasts);
        }

        return Recycler.create(c)
                .binder(recyclerBinder)
                .buildWithLayout();
    }

    private static void populateList(ComponentContext c,
                                     RecyclerBinder recyclerBinder,
                                     List<Podcast> podcasts) {
        int i = 0;
        for (Podcast podcast : podcasts) {
            ComponentInfo.Builder componentInfoBuilder = ComponentInfo.create();
            componentInfoBuilder.component(
                    PodcastRowCard.create(c)
                            .podcast(podcast)
                            .build());

            recyclerBinder.insertItemAt(i++,  componentInfoBuilder.build());
        }
    }
}
