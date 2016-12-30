package com.yunfei.net;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yunfei on 2016/12/30.
 * email mayunfei6@gmail.com
 */

public class RetrofitUtil {
  /**
   * 工具类不可实例化
   */
  private RetrofitUtil() {
  }

  /**
   * 线程转换
   */
  public static <T extends BaseResponse> Observable.Transformer<T, T> getSchedulerTransformer() {
    return new Observable.Transformer<T, T>() {
      @Override public Observable<T> call(Observable<T> observable) {
        return observable
            //网络请求在io 线程
            .subscribeOn(Schedulers.io())
            //处理数据在主线程
            .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  /**
   * 检测服务异常 Server Transformer
   */
  public static <T extends BaseResponse> Observable.Transformer<T, T> getCheckResponseTransformer() {
    return new Observable.Transformer<T, T>() {
      @Override public Observable<T> call(Observable<T> observable) {
        return observable.flatMap(new Func1<T, Observable<T>>() { //检查服务器异常
          @Override public Observable<T> call(T response) {
            return checkServerObservable(response);
          }
        });
      }
    };
  }

  public static <K, T extends BaseResponse<K>> Observable.Transformer<T, K> getSimpleHttpTransformer() {
    return new Observable.Transformer<T, K>() {
      @Override public Observable<K> call(Observable<T> observable) {
        return observable.compose(RetrofitUtil.getSchedulerTransformer()).compose(RetrofitUtil.getCheckResponseTransformer()).flatMap(new Func1<BaseResponse, Observable<K>>() {
          @Override public Observable<K> call(final BaseResponse baseResponse) {
            return Observable.create(new Observable.OnSubscribe<K>() {
              @Override public void call(Subscriber<? super K> subscriber) {
                subscriber.onNext((K) baseResponse.getResult().getData());
                subscriber.onCompleted();
              }
            });
          }
        });
      }
    };
  }

  /**
   * 检查返回值 是否是服务器异常
   */
  private static <T extends BaseResponse> Observable<T> checkServerObservable(final T response) {
    return Observable.create(new Observable.OnSubscribe<T>() {
      @Override public void call(Subscriber<? super T> subscriber) {
        if (!subscriber.isUnsubscribed()) {
          if (response.getError_code() > 0 || response.getResult() == null) {
            subscriber.onError(new ServerException(response.getError_code()));
          } else {
            subscriber.onNext(response);
            subscriber.onCompleted();
          }
        }
      }
    });
  }
}
