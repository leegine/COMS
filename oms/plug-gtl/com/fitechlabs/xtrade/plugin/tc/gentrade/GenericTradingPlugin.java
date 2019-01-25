// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericTradingPlugin.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.enum.EnumeratedManager;
import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.license.LicenseException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenTradeAccountDatabaseExtensions;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenTradeMasterDatabaseExtensions;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.FinAppImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlComponentLicenseCheckerUtil;

public final class GenericTradingPlugin extends Plugin
{

    private GenericTradingPlugin()
    {
    }

    public static void plug()
        throws Exception
    {
        plug(com.fitechlabs.xtrade.plugin.tc.gentrade.GenericTradingPlugin.class);
    }

    public static void onPlug()
        throws Exception
    {
        KernelPlugin.plug();
        if(!m_licenseCheker.isValidLicenseAvailable())
        {
            m_log.error(NO_VALID_LICENSE.toString());
            throw new LicenseException(NO_VALID_LICENSE.toString());
        }
        GenTradeAccountDatabaseExtensions.plug();
        GenTradeMasterDatabaseExtensions.plug();
        FinApp finAppImpl = new FinAppImpl();
        Services.registerService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class, finAppImpl);
        m_log.info("Registered FinApp Service.");
        Class enums[] = {
            com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg.class, com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType.class, com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum.class, 
            com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum.class, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum.class, 
            com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum.class
        };
        for(int i = 0; i < enums.length; i++)
        {
            m_log.info("Installing Enumerated : " + enums[i]);
            EnumeratedManager.getInstance().addEnumeratedClass(enums[i]);
        }

        Class preloadClasses[] = {
            com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerErrorCatalog.class
        };
        m_log.info("Done plugging in " + (com.fitechlabs.xtrade.plugin.tc.gentrade.GenericTradingPlugin.class));
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static final String LICENSE_PUBLIC_KEY = "aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002406d74c300378fa8bc46fbbd2a97c7f33d43a29bdeead54abcc6b16da1a853bd35a62745edc5474764a23f7d996fe5d105d2a3276ead5f0056534377a95454d6f878737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fffffffffefffffffe00000001757200025b42acf317f8060854e00200007870000000406d74c300378fa8bc46fbbd2a97c7f33d43a29bdeead54abcc6b16da1a853bd35a62745edc5474764a23f7d996fe5d105d2a3276ead5f0056534377a95454d6f8";
    private static final String COMPONENT_NAME_FOR_LICENSE_VERIFICATION = "xblocks.gtl";
    private static ErrorManager m_errorMgr;
    public static final ErrorInfo NO_VALID_LICENSE;
    private static final GtlComponentLicenseCheckerUtil m_licenseCheker = new GtlComponentLicenseCheckerUtil("xblocks.gtl", "aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002406d74c300378fa8bc46fbbd2a97c7f33d43a29bdeead54abcc6b16da1a853bd35a62745edc5474764a23f7d996fe5d105d2a3276ead5f0056534377a95454d6f878737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fffffffffefffffffe00000001757200025b42acf317f8060854e00200007870000000406d74c300378fa8bc46fbbd2a97c7f33d43a29bdeead54abcc6b16da1a853bd35a62745edc5474764a23f7d996fe5d105d2a3276ead5f0056534377a95454d6f8");

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.GenericTradingPlugin.class);
        DBG = m_log.ison();
        m_errorMgr = ErrorManager.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.GenericTradingPlugin.class);
        NO_VALID_LICENSE = m_errorMgr.defineErrorInfo("XB_NO_VALID_LICENSE", "No valid license for : xblocks.gtl");
    }
}
