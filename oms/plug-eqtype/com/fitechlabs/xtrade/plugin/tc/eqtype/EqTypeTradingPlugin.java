// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeTradingPlugin.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.enum.EnumeratedManager;
import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.license.LicenseException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqTypeAccountDatabaseExtensions;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqTypeMasterDatabaseExtensions;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.TradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlComponentLicenseCheckerUtil;

public final class EqTypeTradingPlugin extends Plugin
{

    private EqTypeTradingPlugin()
    {
    }

    public static void plug()
        throws Exception
    {
        plug(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradingPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        KernelPlugin.plug();
        if(!m_licenseChecker.isValidLicenseAvailable())
        {
            m_log.error(NO_VALID_EQTYPE_BASIC_LICENSE.toString());
            throw new LicenseException(NO_VALID_EQTYPE_BASIC_LICENSE.toString());
        }
        EqTypeAccountDatabaseExtensions.plug();
        EqTypeMasterDatabaseExtensions.plug();
        FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
        m_log.info("Installing TradingModule : eqtype");
        finApp.installTradingModule(new TradingModuleImpl());
        m_log.info("Installed TradingModule : eqtype");
        Class enums[] = {
            com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType.class
        };
        for(int i = 0; i < enums.length; i++)
        {
            m_log.info("Installing Enumerated : " + enums[i]);
            EnumeratedManager.getInstance().addEnumeratedClass(enums[i]);
        }

        m_log.info("Done plugging in " + (com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradingPlugin.class));
        Class preloadClasses[] = {
            com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderManagerInterceptor.class
        };
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static final String LICENSE_PUBLIC_KEY_FOR_EQTYPE_BASIC = "aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405c098525066a589c8576adadb4540f7f397b827d61ea9dd3e52c76543cf38d865c682219b474549c42a98671658c370ca1374baebb14ec845de2a35f25d9144d78737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fffffffffefffffffe00000001757200025b42acf317f8060854e00200007870000000405c098525066a589c8576adadb4540f7f397b827d61ea9dd3e52c76543cf38d865c682219b474549c42a98671658c370ca1374baebb14ec845de2a35f25d9144d";
    private static final String EQTYPE_BASIC_COMPONENT_NAME_FOR_LICENSE_VERIFICATION = "xblocks.eqtype.basic";
    private static ErrorManager m_errorMgr;
    public static final ErrorInfo NO_VALID_EQTYPE_BASIC_LICENSE;
    private static GtlComponentLicenseCheckerUtil m_licenseChecker = new GtlComponentLicenseCheckerUtil("xblocks.eqtype.basic", "aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405c098525066a589c8576adadb4540f7f397b827d61ea9dd3e52c76543cf38d865c682219b474549c42a98671658c370ca1374baebb14ec845de2a35f25d9144d78737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fffffffffefffffffe00000001757200025b42acf317f8060854e00200007870000000405c098525066a589c8576adadb4540f7f397b827d61ea9dd3e52c76543cf38d865c682219b474549c42a98671658c370ca1374baebb14ec845de2a35f25d9144d");

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradingPlugin.class);
        DBG = m_log.ison();
        m_errorMgr = ErrorManager.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradingPlugin.class);
        NO_VALID_EQTYPE_BASIC_LICENSE = m_errorMgr.defineErrorInfo("XB_NO_VALID_EQTYPE_BASIC_LICENSE", "No valid license for : xblocks.eqtype.basic");
    }
}
