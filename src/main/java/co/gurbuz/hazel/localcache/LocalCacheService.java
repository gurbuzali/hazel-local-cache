package co.gurbuz.hazel.localcache;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.spi.ManagedService;
import com.hazelcast.spi.NodeEngine;
import com.hazelcast.spi.RemoteService;

import java.util.Properties;

/**
 * TODO add a proper JavaDoc
 */
public class LocalCacheService implements RemoteService, ManagedService {

    public static final String SERVICE_NAME = "grbz:localCacheService";

    private NodeEngine nodeEngine;

    public LocalCacheService() {
    }

    @Override
    public void init(NodeEngine nodeEngine, Properties properties) {
        this.nodeEngine = nodeEngine;
    }

    @Override
    public void reset() {
    }

    @Override
    public void shutdown(boolean terminate) {
    }

    @Override
    public DistributedObject createDistributedObject(String objectName) {
        return new LocalCacheProxy(objectName, nodeEngine, this);
    }

    @Override
    public void destroyDistributedObject(String objectName) {
    }
}
