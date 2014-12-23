package co.gurbuz.hazel.localcache;

import com.hazelcast.core.EntryListener;
import com.hazelcast.core.EntryView;
import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.IMap;
import com.hazelcast.map.EntryProcessor;
import com.hazelcast.map.MapInterceptor;
import com.hazelcast.map.impl.NearCache;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.aggregation.Aggregation;
import com.hazelcast.mapreduce.aggregation.Supplier;
import com.hazelcast.monitor.LocalMapStats;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.query.Predicate;
import com.hazelcast.spi.AbstractDistributedObject;
import com.hazelcast.spi.NodeEngine;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * TODO add a proper JavaDoc
 */
public class LocalCacheProxy extends AbstractDistributedObject<LocalCacheService> implements IMap {

    String name;
    NearCache nearCache;

    public LocalCacheProxy(String name, NodeEngine nodeEngine, LocalCacheService service) {
        super(nodeEngine, service);
        this.name = name;
        nearCache = new NearCache(name, nodeEngine);
    }

    @Override
    public Object put(Object key, Object value) {
        NodeEngine nodeEngine = getNodeEngine();
        Data keyData = nodeEngine.toData(key);
        Data valueData = nodeEngine.toData(value);
        Object old = nearCache.put(keyData, valueData);
        return nodeEngine.toObject(old);
    }

    @Override
    public Object get(Object key) {
        NodeEngine nodeEngine = getNodeEngine();
        Data keyData = nodeEngine.toData(key);
        Object value = nearCache.get(keyData);
        return nodeEngine.toObject(value);
    }

    @Override
    public Object remove(Object key) {
        NodeEngine nodeEngine = getNodeEngine();
        Data keyData = nodeEngine.toData(key);
        nearCache.invalidate(keyData);
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public void delete(Object key) {

    }

    @Override
    public void flush() {

    }

    @Override
    public Map getAll(Set keys) {
        return null;
    }

    @Override
    public void loadAll(boolean replaceExistingValues) {

    }

    @Override
    public void loadAll(Set keys, boolean replaceExistingValues) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Future getAsync(Object key) {
        return null;
    }

    @Override
    public Future putAsync(Object key, Object value) {
        return null;
    }

    @Override
    public Future putAsync(Object key, Object value, long ttl, TimeUnit timeunit) {
        return null;
    }

    @Override
    public Future removeAsync(Object key) {
        return null;
    }

    @Override
    public boolean tryRemove(Object key, long timeout, TimeUnit timeunit) {
        return false;
    }

    @Override
    public boolean tryPut(Object key, Object value, long timeout, TimeUnit timeunit) {
        return false;
    }

    @Override
    public Object put(Object key, Object value, long ttl, TimeUnit timeunit) {
        return null;
    }

    @Override
    public void putTransient(Object key, Object value, long ttl, TimeUnit timeunit) {

    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public Object putIfAbsent(Object key, Object value, long ttl, TimeUnit timeunit) {
        return null;
    }

    @Override
    public boolean replace(Object key, Object oldValue, Object newValue) {
        return false;
    }

    @Override
    public Object replace(Object key, Object value) {
        return null;
    }

    @Override
    public void set(Object key, Object value) {

    }

    @Override
    public void set(Object key, Object value, long ttl, TimeUnit timeunit) {

    }

    @Override
    public void lock(Object key) {

    }

    @Override
    public void lock(Object key, long leaseTime, TimeUnit timeUnit) {

    }

    @Override
    public boolean isLocked(Object key) {
        return false;
    }

    @Override
    public boolean tryLock(Object key) {
        return false;
    }

    @Override
    public boolean tryLock(Object key, long time, TimeUnit timeunit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock(Object key) {

    }

    @Override
    public void forceUnlock(Object key) {

    }

    @Override
    public String addLocalEntryListener(EntryListener listener) {
        return null;
    }

    @Override
    public String addLocalEntryListener(EntryListener listener, Predicate predicate, boolean includeValue) {
        return null;
    }

    @Override
    public String addLocalEntryListener(EntryListener listener, Predicate predicate, Object key, boolean includeValue) {
        return null;
    }

    @Override
    public String addInterceptor(MapInterceptor interceptor) {
        return null;
    }

    @Override
    public void removeInterceptor(String id) {

    }

    @Override
    public String addEntryListener(EntryListener listener, boolean includeValue) {
        return null;
    }

    @Override
    public boolean removeEntryListener(String id) {
        return false;
    }

    @Override
    public String addEntryListener(EntryListener listener, Object key, boolean includeValue) {
        return null;
    }

    @Override
    public String addEntryListener(EntryListener listener, Predicate predicate, boolean includeValue) {
        return null;
    }

    @Override
    public String addEntryListener(EntryListener listener, Predicate predicate, Object key, boolean includeValue) {
        return null;
    }

    @Override
    public EntryView getEntryView(Object key) {
        return null;
    }

    @Override
    public boolean evict(Object key) {
        return false;
    }

    @Override
    public void evictAll() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    @Override
    public Set keySet(Predicate predicate) {
        return null;
    }

    @Override
    public Set<Entry> entrySet(Predicate predicate) {
        return null;
    }

    @Override
    public Collection values(Predicate predicate) {
        return null;
    }

    @Override
    public Set localKeySet() {
        return null;
    }

    @Override
    public Set localKeySet(Predicate predicate) {
        return null;
    }

    @Override
    public void addIndex(String attribute, boolean ordered) {

    }

    @Override
    public LocalMapStats getLocalMapStats() {
        return null;
    }

    @Override
    public Object executeOnKey(Object key, EntryProcessor entryProcessor) {
        return null;
    }

    @Override
    public Map executeOnKeys(Set keys, EntryProcessor entryProcessor) {
        return null;
    }

    @Override
    public void submitToKey(Object key, EntryProcessor entryProcessor, ExecutionCallback callback) {

    }

    @Override
    public Future submitToKey(Object key, EntryProcessor entryProcessor) {
        return null;
    }

    @Override
    public Map executeOnEntries(EntryProcessor entryProcessor) {
        return null;
    }

    @Override
    public Map executeOnEntries(EntryProcessor entryProcessor, Predicate predicate) {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getServiceName() {
        return LocalCacheService.SERVICE_NAME;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public Object aggregate(Supplier supplier, Aggregation aggregation) {
        return null;
    }

    @Override
    public Object aggregate(Supplier supplier, Aggregation aggregation, JobTracker jobTracker) {
        return null;
    }
}
