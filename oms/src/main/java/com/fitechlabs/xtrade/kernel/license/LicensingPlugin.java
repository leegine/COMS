// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LicensingPlugin.java

package com.fitechlabs.xtrade.kernel.license;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.data.db.ServerLicenseRow;
import com.fitechlabs.xtrade.kernel.data.impl.QPFStdImpl;
import com.fitechlabs.xtrade.kernel.util.PropertiesFinder;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.license:
//            Licensing, LicenseService

public class LicensingPlugin extends Plugin
{

    private LicensingPlugin()
    {
    }

    public static void plug()
        throws Exception
    {
        Plugin.plug(com.fitechlabs.xtrade.kernel.license.LicensingPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        Properties p = PropertiesFinder.getProperties("license.properties", null);
        installLicenses(p, "license.properties");
        if(DataSources.getDefaultDataSource() != null)
        {
            Properties configProps = PropertiesFinder.getProperties("config.properties", null);
            QueryProcessorFactory fact = new QPFStdImpl(configProps, "license");
            fact.extendInstance(ServerLicenseRow.TYPE.getTableName(), com.fitechlabs.xtrade.kernel.data.db.ServerLicensePK.class, com.fitechlabs.xtrade.kernel.data.db.ServerLicenseParams.class, null, null);
            QueryProcessor proc = fact.getQueryProcessor(null);
            List list = proc.doFindAllQuery(ServerLicenseRow.TYPE);
            ServerLicenseRow row;
            for(Iterator it = list.iterator(); it.hasNext(); installLicense(row.getLicenseId(), row.getLicenseXml(), "server_license"))
                row = (ServerLicenseRow)it.next();

        }
    }

    private static void installLicenses(Properties p, String source)
        throws Exception
    {
        if(p == null)
            return;
        plug();
        String name;
        String xml;
        for(Iterator it = p.entrySet().iterator(); it.hasNext(); installLicense(name, xml, source))
        {
            java.util.Map.Entry me = (java.util.Map.Entry)it.next();
            name = (String)me.getKey();
            xml = (String)me.getValue();
        }

    }

    public static void installLicense(String name, String xml, String source)
    {
        try
        {
            plug();
            LicenseService ls = Licensing.getLicenseServiceInstance();
            ls.installLicense(xml);
            log.info("installed license " + name + " from " + source);
        }
        catch(Exception e)
        {
            log.warn("license install from " + source + " failed for name='" + name + "', xml=" + xml);
        }
    }

    private static final Logit log;
    private static final boolean DBG;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.license.LicensingPlugin.class);
        DBG = log.ison();
    }
}
