package com.example.quizapp.data.remote;

import android.util.Log;

import com.example.quizapp.model.QuizResponse;

import org.apache.http.params.HttpParams;

import java.net.SocketException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();


    QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(int amountIndex, int categoryIndex, String difficultyIndex, QuestionCallBack callBack) {
        Call<QuizResponse> call = client.getQuestions(amountIndex, categoryIndex, difficultyIndex);

        Log.e("ololo", "Url - " + call.request().url());

        call.enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            callBack.onSuccess(response.body().getResults());
                        } else {
                            callBack.onFailure(new Exception("Response is empty" + response.code()));
                        }
                    } else {
                        callBack.onFailure(new Exception("Response is empty" + response.code()));

                    }
            }
            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                callBack.onFailure(new Exception(t));
            }
        });
    }

    interface QuizApi {
        @GET("api.php")
        Call<QuizResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty
        );
    }
}
