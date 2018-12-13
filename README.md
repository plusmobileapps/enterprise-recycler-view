# Hello World Enterprise Recyclerview 

This is a sample app to illustrate a pattern for implementing an abstracted out version of a [`RecyclerView`](https://developer.android.com/reference/android/support/v7/widget/RecyclerView) with different `ViewHolder` types that is also meant to keep items of the list in a specific order based off various sources of data being input. The basic architecture is based off the [Guide to app architecture](https://developer.android.com/jetpack/docs/guide) from the Android Developer docs with the diagram below. 

![guide to app architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

### Tools

* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - used for all asynchronous work
* [Koin](https://insert-koin.io/)  - dependency injection framework
* [Glide](https://github.com/bumptech/glide) - image loading library

**Architecture Components**

* [Room](https://developer.android.com/topic/libraries/architecture/room) 
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [`ViewModel`](https://developer.android.com/topic/libraries/architecture/viewmodel) 

