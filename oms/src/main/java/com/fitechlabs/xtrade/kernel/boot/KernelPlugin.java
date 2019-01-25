// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KernelPlugin.java

package com.fitechlabs.xtrade.kernel.boot;

import com.fitechlabs.xtrade.kernel.data.DataSources;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfigPlugin;
import com.fitechlabs.xtrade.kernel.data.db.KernelDBExtensions;
import com.fitechlabs.xtrade.kernel.error.ErrorResponseRegistry;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.license.*;
import com.fitechlabs.xtrade.kernel.message.Request;
import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.boot:
//            Plugin

public final class KernelPlugin extends Plugin
{
    public static class GCHandler
        implements MessageHandler
    {

        public GCResponse handle(GCRequest request)
            throws Exception
        {
            if(KernelPlugin.DBG)
                KernelPlugin.log.debug("Explicit GC begin...");
            System.gc();
            if(KernelPlugin.DBG)
                KernelPlugin.log.debug("Explicit GC done...");
            return response;
        }

        private static GCResponse response = new GCResponse();


        public GCHandler()
        {
        }
    }

    public static class GCResponse extends Response
    {

        public static final String PTYPE = "gc";

        public GCResponse()
        {
        }
    }

    public static class GCRequest extends Request
    {

        public static final String PTYPE = "gc";

        public GCRequest()
        {
        }
    }

    public static class SleepHandler
        implements MessageHandler
    {

        public SleepResponse handle(SleepRequest request)
            throws Exception
        {
            Thread.sleep(request.sleep_time);
            KernelPlugin.log.info("SleepHandler.handle() has sleeped for " + request.sleep_time);
            return response;
            Exception e;
            e;
            KernelPlugin.log.warn("Other Exception thrown in SleepHandler.handle()");
            KernelPlugin.log.warn(e);
            throw e;
        }

        private static SleepResponse response = new SleepResponse();


        public SleepHandler()
        {
        }
    }

    public static class SleepResponse extends Response
    {

        public static final String PTYPE = "sleep";

        public SleepResponse()
        {
        }
    }

    public static class SleepRequest extends Request
    {

        public static final String PTYPE = "sleep";
        public long sleep_time;

        public SleepRequest()
        {
        }
    }

    public static class NoopHandler
        implements MessageHandler
    {

        public NoopResponse handle(NoopRequest request)
            throws Exception
        {
            return response;
        }

        private static NoopResponse response = new NoopResponse();


        public NoopHandler()
        {
        }
    }

    public static class NoopResponse extends Response
    {

        public static final String PTYPE = "noop";

        public NoopResponse()
        {
        }
    }

    public static class NoopRequest extends Request
    {

        public static final String PTYPE = "noop";

        public NoopRequest()
        {
        }
    }


    public static String getSDKVersion()
    {
        return SDK_VERSION;
    }

    public static Key getPublicKey()
        throws IOException
    {
        LicenseService ls = Licensing.getLicenseServiceInstance();
        return ls.keyFromHexString("aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4034300024015c52e64151623507cbedeccbd34353c98d14935e2cc6b830b30d34f44b1144fc9b40b091758ac49c20d73c80bb0130deb1f4812cb2f388c34ab09569251585578737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fdfffffffefffffffe00000001757200025b42acf317f8060854e002000078700000004015c52e64151623507cbedeccbd34353c98d14935e2cc6b830b30d34f44b1144fc9b40b091758ac49c20d73c80bb0130deb1f4812cb2f388c34ab095692515855");
    }

    private KernelPlugin()
    {
    }

    public static void plug()
        throws Exception
    {
        Plugin.plug(com.fitechlabs.xtrade.kernel.boot.KernelPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        LicensingPlugin.plug();
        if(DataSources.getDefaultDataSource() != null)
        {
            ServerConfigPlugin.plug();
            Plugin.regProcessor("session", com.fitechlabs.xtrade.kernel.data.impl.QPFStdImpl.class);
            Plugin.regProcessor("master", com.fitechlabs.xtrade.kernel.data.impl.QPFCachingImpl.class);
            Plugin.regProcessor("account", com.fitechlabs.xtrade.kernel.data.impl.QPFBeanImpl.class);
            Plugin.regProcessor("default", com.fitechlabs.xtrade.kernel.data.impl.QPFDefaultImpl.class);
            KernelDBExtensions.plug();
            ErrorResponseRegistry.getBaseErrors();
        }
        LicenseService ls = Licensing.getLicenseServiceInstance();
        Collection c = ls.getValidLicenses("xtrade.core", getPublicKey());
        if(c.size() <= 0)
            throw new LicenseException("no valid license for 'xtrade.core'");
        String hostname = InetAddress.getLocalHost().getHostName();
        String ip = InetAddress.getLocalHost().getHostAddress();
        if(!containsHostnameIp(c, hostname, ip))
            throw new LicenseException("no valid 'xtrade.core' license for hostname=" + hostname + ", ip=" + ip);
        Plugin.regBaseClass(com.fitechlabs.xtrade.kernel.message.Request.class);
        Plugin.regBaseClass(com.fitechlabs.xtrade.kernel.message.Response.class);
        Plugin.regBaseClass(com.fitechlabs.dbind.Row.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.boot.KernelPlugin$NoopRequest.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.boot.KernelPlugin$NoopResponse.class);
        Plugin.regHandler(com.fitechlabs.xtrade.kernel.boot.KernelPlugin$NoopRequest.class, com.fitechlabs.xtrade.kernel.boot.KernelPlugin$NoopHandler.class, "handle");
        Plugin.regClass(com.fitechlabs.xtrade.kernel.boot.KernelPlugin$SleepRequest.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.boot.KernelPlugin$SleepResponse.class);
        Plugin.regHandler(com.fitechlabs.xtrade.kernel.boot.KernelPlugin$SleepRequest.class, com.fitechlabs.xtrade.kernel.boot.KernelPlugin$SleepHandler.class, "handle");
        Plugin.regClass(com.fitechlabs.xtrade.kernel.boot.KernelPlugin$GCRequest.class);
        Plugin.regClass(com.fitechlabs.xtrade.kernel.boot.KernelPlugin$GCResponse.class);
        Plugin.regHandler(com.fitechlabs.xtrade.kernel.boot.KernelPlugin$GCRequest.class, com.fitechlabs.xtrade.kernel.boot.KernelPlugin$GCHandler.class, "handle");
        if(DataSources.getDefaultDataSource() != null)
        {
            Properties plugins = ServerConfig.getConfigCategory("plugin.class");
            if(plugins != null)
            {
                TreeMap map = new TreeMap();
                java.util.Map.Entry e;
                for(Iterator it = plugins.entrySet().iterator(); it.hasNext(); map.put(e.getKey(), e.getValue()))
                    e = (java.util.Map.Entry)it.next();

                Class pluginClass;
                for(Iterator it = map.values().iterator(); it.hasNext(); Plugin.plug(pluginClass))
                {
                    String className = (String)it.next();
                    log.debug("pluggin class from config: " + className);
                    pluginClass = Class.forName(className);
                }

            }
        }
    }

    private static boolean containsHostnameIp(Collection c, String hostname, String ip)
    {
        for(Iterator it = c.iterator(); it.hasNext();)
        {
            License lic = (License)it.next();
            Properties p = lic.getAttributes();
            if(p != null)
            {
                String lic_hostname = p.getProperty("hostname");
                String lic_ip = p.getProperty("ip");
                if(matches(lic_hostname, hostname) && matches(lic_ip, ip))
                    return true;
            }
        }

        return false;
    }

    private static boolean matches(String lic_valu, String comp_valu)
    {
        if(lic_valu == null)
            return false;
        if(lic_valu.equals(comp_valu))
            return true;
        if(lic_valu.equals("*"))
            return true;
        if(lic_valu.endsWith("*"))
        {
            int n = lic_valu.length();
            return comp_valu.startsWith(lic_valu.substring(0, n - 1));
        } else
        {
            return false;
        }
    }

    private static final Logit log;
    private static final boolean DBG;
    private static String SDK_VERSION = "xTrade 3.7.2s2 GA";
    private static final String publicKeyString = "aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4034300024015c52e64151623507cbedeccbd34353c98d14935e2cc6b830b30d34f44b1144fc9b40b091758ac49c20d73c80bb0130deb1f4812cb2f388c34ab09569251585578737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fdfffffffefffffffe00000001757200025b42acf317f8060854e002000078700000004015c52e64151623507cbedeccbd34353c98d14935e2cc6b830b30d34f44b1144fc9b40b091758ac49c20d73c80bb0130deb1f4812cb2f388c34ab095692515855";

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.boot.KernelPlugin.class);
        DBG = log.ison();
    }


}
