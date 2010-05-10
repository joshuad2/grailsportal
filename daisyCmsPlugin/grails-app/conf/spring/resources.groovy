pirateCache(EhCacheFactoryBean) { bean ->
    cacheManager = ref("springcacheCacheManager")
    cacheName = "daisyCache"
    // these are just examples of properties you could set
    eternal = false
    diskPersistent = false
    memoryStoreEvictionPolicy = "LRU"
}

