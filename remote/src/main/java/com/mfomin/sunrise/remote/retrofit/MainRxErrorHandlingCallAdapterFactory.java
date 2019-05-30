package com.mfomin.sunrise.remote.retrofit;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import retrofit2.*;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class MainRxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory mOriginalCallAdapterFactory;

    private MainRxErrorHandlingCallAdapterFactory() {
        mOriginalCallAdapterFactory = RxJava2CallAdapterFactory.createAsync();
    }

    public static CallAdapter.Factory createAsync() {
        return new MainRxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(final Type returnType, final Annotation[] annotations, final Retrofit retrofit) {
        return new RxCallAdapterWrapper<>(retrofit, mOriginalCallAdapterFactory.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Single<R>> {
        private final Retrofit mRetrofit;
        private final CallAdapter<R, ?> mWrappedCallAdapter;

        public RxCallAdapterWrapper(final Retrofit retrofit, final CallAdapter<R, ?> wrapped) {
            mRetrofit = retrofit;
            mWrappedCallAdapter = wrapped;
        }

        @Override
        public Type responseType() {
            return mWrappedCallAdapter.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Single<R> adapt(final Call<R> call) {
            return ((Single) mWrappedCallAdapter.adapt(call)).onErrorResumeNext(
                    (Function<Throwable, Single>) throwable ->
                            Single.error(asRetrofitException(throwable)));
        }

        private RetrofitException asRetrofitException(final Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                final HttpException httpException = (HttpException) throwable;
                final Response response = httpException.response();

                return RetrofitException.Companion.httpError(response.raw().request().url().toString(), response, mRetrofit);
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RetrofitException.Companion.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error

            return RetrofitException.Companion.unexpectedError(throwable);
        }
    }
}