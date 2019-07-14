package com.circularuins.simplemercari.infra.repository

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider {

    fun ui(): Scheduler = Schedulers.trampoline()

    fun computation(): Scheduler = Schedulers.trampoline()

    fun trampoline(): Scheduler = Schedulers.trampoline()

    fun newThread(): Scheduler = Schedulers.trampoline()

    fun io(): Scheduler = Schedulers.trampoline()
}