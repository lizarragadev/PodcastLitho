package com.miramicodigo.lithopodcast.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.miramicodigo.lithopodcast.R;
import com.miramicodigo.lithopodcast.data.Api;
import com.miramicodigo.lithopodcast.data.Constants;
import com.miramicodigo.lithopodcast.data.Podcast;
import com.miramicodigo.lithopodcast.data.PodcastsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements MainFragment.OnFragmentInteractionListener {

    private Api.ApiService api;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String podcastsCategory = null;
            switch (item.getItemId()) {
                case R.id.navigation_popular:
                    podcastsCategory = Constants.POPULAR; break;
                case R.id.navigation_featured:
                    podcastsCategory = Constants.FEATURED; break;
                case R.id.navigation_trending:
                    podcastsCategory = Constants.TRENDING; break;
            }
            if (podcastsCategory != null) {
                loadPodcasts(podcastsCategory);
            }
            return true;
        }

    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        api = Api.get();
        loadPodcasts(Constants.FEATURED);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void showLoading() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new LoadingFragment())
                .commit();
    }

    public void showPodcastList(final String podcastsCategory, final List<Podcast> podcasts) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, MainFragment.newInstance(
                        podcastsCategory,
                        new ArrayList<>(podcasts)))
                .commit();
    }

    private void loadPodcasts(final String podcastsCategory) {
        showLoading();

        api.getPodcasts(podcastsCategory)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<PodcastsResponse>() {
                    @Override
                    public void onNext(PodcastsResponse value) {
                        showPodcastList(podcastsCategory, value.data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(),
                                "Could not load podcasts",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void onPodcastsLoaded(List<Podcast> podcasts) {}
}
