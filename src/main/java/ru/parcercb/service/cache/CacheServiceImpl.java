package ru.parcercb.service.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheServiceImpl implements CacheService {
    private final CacheManager cacheManager;

    @Override
    public void viewCacheContents() {
        Cache cache = cacheManager.getCache("currencyRates");
        ConcurrentMap<Object, Object> nativeCache = (ConcurrentMap<Object, Object>) Objects.requireNonNull(cache.getNativeCache());
        for (Object key : nativeCache.keySet()) {
            Object value = nativeCache.get(key);
            log.info("ключ: " + key + " значение: " + value);
        }
    }
}