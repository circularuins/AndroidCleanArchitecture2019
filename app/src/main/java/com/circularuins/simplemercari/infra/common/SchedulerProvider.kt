package com.circularuins.simplemercari.infra.common

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 非同期処理時に指定するスレッドの定数管理
 */
class SchedulerProvider {

    val ui: Scheduler = AndroidSchedulers.mainThread()

    val computation: Scheduler = Schedulers.computation()

    val trampoline: Scheduler = Schedulers.trampoline()

    val newThread: Scheduler = Schedulers.newThread()

    val io: Scheduler = Schedulers.io()
}