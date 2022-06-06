# Boruto
Boruto compose app with CLEAN architecture

## Jetpack Compose
Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android.
Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.

_[See More](https://developer.android.com/jetpack/compose)_

### Navigating with Compose
The _[Navigation component](https://developer.android.com/jetpack/androidx/releases/room)_ provides support for Jetpack Compose applications.
You can navigate between composables while taking advantage of the Navigation component’s infrastructure and features.

_[See More](https://developer.android.com/jetpack/compose/navigation)_

### State and Jetpack Compose

State in an app is any value that can change over time. This is a very broad definition and encompasses everything from a Room database to a variable on a class.

All Android apps display state to the user. A few examples of state in Android apps:

- A Snackbar that shows when a network connection can't be established.
- A blog post and associated comments.
- Ripple animations on buttons that play when a user clicks them.
- Stickers that a user can draw on top of an image.

_[See More](https://developer.android.com/jetpack/compose/state)_

#### StateFlow and SharedFlow

`StateFlow` and `SharedFlow` are Flow APIs that enable flows to optimally emit state updates and emit values to multiple consumers.

_[See More](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)_

##### StateFlow

`StateFlow` is a state-holder observable flow that emits the current and new state updates to its collectors.
The current state value can also be read through its `value` property. To update state and send it to the flow,
assign a new value to the value property of the `MutableStateFlow` class.

In Android, StateFlow is a great fit for classes that need to maintain an observable mutable state.

To convert any flow to a StateFlow, use the _[stateIn](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/state-in.html)_ intermediate operator.

###### StateFlow, Flow, and LiveData

StateFlow and LiveData have similarities. Both are observable data holder classes, and both follow a similar pattern when used in your app architecture.

Note, however, that StateFlow and LiveData do behave differently:

- StateFlow requires an initial state to be passed in to the constructor, while LiveData does not.
- LiveData.observe() automatically unregisters the consumer when the view goes to the STOPPED state, whereas collecting from a StateFlow or any other flow does not stop collecting automatically. To achieve the same behavior,you need to collect the flow from a Lifecycle.repeatOnLifecycle block.


_[See More](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow)_


##### SharedFlow

The `shareIn` function returns a `SharedFlow`, a hot flow that emits values to all consumers that collect from it.
A `SharedFlow` is a highly-configurable generalization of `StateFlow`.

_[See More](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#sharedflow)_


### Accompanist

A collection of extension libraries for Jetpack Compose

_[See More](https://github.com/google/accompanist)_

#### Pager

A library that provides utilities for building paginated layouts in Jetpack Compose, similar to Android's `ViewPager`.

_[See More](https://github.com/google/accompanist/tree/main/pager)_


## Room
The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.

_[See More](https://developer.android.com/jetpack/androidx/releases/room)_

_[Referencing complex data using Room](https://developer.android.com/training/data-storage/room/referencing-data)_

## Dependency Injection
Dependency injection (DI) is a technique widely used in programming and well suited to Android development. By following the principles of DI, you lay the groundwork for good app architecture.

Implementing dependency injection provides you with the following advantages:

- Reusability of code
- Ease of refactoring
- Ease of testing

_[See More](https://developer.android.com/training/dependency-injection)_

### Hilt
Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
Hilt provides a standard way to use DI in your application by providing containers for every Android class in your project and managing their lifecycles automatically.
Hilt is built on top of the popular DI library Dagger to benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support that Dagger provides

_[See More](https://developer.android.com/training/dependency-injection/hilt-android)_

_[Use Hilt with other Jetpack libraries](https://developer.android.com/training/dependency-injection/hilt-jetpack)_


## DataStore

Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers.
DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.

_[See More](https://developer.android.com/topic/libraries/architecture/datastore)_

## Retrofit

A type-safe HTTP client for Android and Java

_[See More](https://square.github.io/retrofit/)_


