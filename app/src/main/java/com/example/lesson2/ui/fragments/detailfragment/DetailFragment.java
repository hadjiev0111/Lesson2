package com.example.lesson2.ui.fragments.detailfragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lesson2.R;
import com.example.lesson2.data.models.Film;
import com.example.lesson2.data.network.GhibliService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFragment extends Fragment {

    private String mParam1;
    private TextView title;
    private TextView description;
    private TextView director;
    private TextView producer;
    private TextView releaseDate;
    private TextView rtScore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("filmId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setDetail(mParam1);
    }

    private void setDetail(String id) {
        GhibliService.getInstance().getFilm(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Film film = response.body();
                    Log.e("tag", "yaai");
                    title.setText("title: " + film.getTitle());
                    description.setText("description: " + film.getDescription());
                    director.setText("director: " + film.getDirector());
                    producer.setText("producer: " + film.getProducer());
                    releaseDate.setText("release date: " + film.getReleaseDate());
                    rtScore.setText("rtScore: " + film.getRtScore());
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }

    private void init(View view) {
        title = view.findViewById(R.id.txt_title);
        description = view.findViewById(R.id.txt_description);
        director = view.findViewById(R.id.txt_director);
        producer = view.findViewById(R.id.txt_producer);
        releaseDate = view.findViewById(R.id.release_date);
        rtScore = view.findViewById(R.id.rt_score);
    }
}