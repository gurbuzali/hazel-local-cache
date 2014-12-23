package co.gurbuz.hazel.localcache;

import co.gurbuz.hazel.localcache.client.ProxyFactory;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ProxyFactoryConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.ServiceConfig;
import com.hazelcast.config.ServicesConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import com.hazelcast.instance.GroupProperties;

import java.util.Random;

/**
 * @ali 05/11/13
 */
public class MainTest {

    static {
        System.setProperty(GroupProperties.PROP_WAIT_SECONDS_BEFORE_JOIN, "0");
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("hazelcast.local.localAddress", "127.0.0.1");
        System.setProperty("hazelcast.version.check.enabled", "false");
        System.setProperty("hazelcast.socket.bind.any", "false");

        Random rand = new Random();
        int g1 = rand.nextInt(255);
        int g2 = rand.nextInt(255);
        int g3 = rand.nextInt(255);
        System.setProperty("hazelcast.multicast.group", "224." + g1 + "." + g2 + "." + g3);

    }

    public static void main(String[] args) {

        final ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setEnabled(true);
        serviceConfig.setClassName(LocalCacheService.class.getName());
        serviceConfig.setName(LocalCacheService.SERVICE_NAME);

        final Config config = new Config();
        final ServicesConfig servicesConfig = config.getServicesConfig();
        servicesConfig.addServiceConfig(serviceConfig);

        final HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        IMap localCache = instance.getDistributedObject(LocalCacheService.SERVICE_NAME, "foo");


        System.err.println("lc: " + localCache);

//        ClientConfig clientConfig = new ClientConfig();
//        ProxyFactoryConfig proxyFactoryConfig = new ProxyFactoryConfig();
//        proxyFactoryConfig.setClassName(ProxyFactory.class.getName());
//        proxyFactoryConfig.setService(LocalCacheService.SERVICE_NAME);
//        clientConfig.addProxyFactoryConfig(proxyFactoryConfig);
//
//        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
//
//        IQueue clientQ = client.getDistributedObject(LocalCacheService.SERVICE_NAME, "foo");

    }
}
