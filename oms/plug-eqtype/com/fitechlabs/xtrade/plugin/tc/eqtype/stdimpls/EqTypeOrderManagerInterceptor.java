// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderManagerInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.license.LicenseException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlAbstractMethodLevelInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlComponentLicenseCheckerUtil;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EqTypeOrderManagerInterceptor extends GtlAbstractMethodLevelInterceptor
    implements Interceptor
{

    public EqTypeOrderManagerInterceptor(String methodsToIntercept[], String methodsToIntercept4SerializingOnAccount[])
    {
        super(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager.class, methodsToIntercept);
        m_methodsToIntercept4SerializingAccount = getMethodsTableToIntercept4ValidLicense(methodsToIntercept4SerializingOnAccount);
        m_methodsToIntercept4ValidMarginLicense = getMethodsTableToIntercept4ValidLicense(CHECK_FOR_EQTYPE_MARGIN_LICENSE_ONCALL);
        m_methodsToIntercept4ValidMiniStockLicense = getMethodsTableToIntercept4ValidLicense(CHECK_FOR_EQTYPE_MINI_STOCK_LICENSE_ONCALL);
    }

    private static Map getMethodsTableToIntercept4ValidLicense(String methodsToIntercept[])
    {
        Method methods[] = (com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager.class).getMethods();
        Map validMethodsTable = new HashMap();
        for(int i = 0; i < methods.length; i++)
            validMethodsTable.put(methods[i].getName(), Boolean.TRUE);

        Map retMap = new HashMap();
        for(int i = 0; i < methodsToIntercept.length; i++)
        {
            if(!validMethodsTable.containsKey(methodsToIntercept[i]))
                throw new IllegalArgumentException("method : " + methodsToIntercept[i] + " - is not valid !!!");
            retMap.put(methodsToIntercept[i], Boolean.TRUE);
        }

        return retMap;
    }

    public Object onCall(Method method, Object arguments[])
    {
        Object txiContext;
        if(DBG)
            m_log.debug(method.getName() + " of EqTypeOrderManager invoked.");
        if(DBG)
            m_log.debug("checking license: " + method.getName());
        verifyLicense(method.getName(), arguments);
        if(DBG)
            m_log.debug("finished license checking: " + method.getName());
        if(!isMethodInterceptable(method))
            return null;
        if(DBG)
            m_log.debug("EqTypeOrderManager." + method.getName() + " - being intercepted");
        txiContext = txi.onCall(method, arguments);
        Map contextValues;
        contextValues = new HashMap();
        contextValues.put("txi_context", txiContext);
        if(DBG)
            m_log.debug("Tx started ");
        boolean shouldSerializeAccount = m_methodsToIntercept4SerializingAccount.containsKey(method.getName());
        if(shouldSerializeAccount && arguments != null && arguments.length > 0 && (arguments[0] instanceof SubAccount))
        {
            SubAccount subAccount = (SubAccount)arguments[0];
            if(DBG)
                m_log.debug("Serializing operations of account : " + subAccount.getSubAccountId());
            subAccount.serializeOperationsWithWait();
            if(DBG)
                m_log.debug("Serializing done.");
        }
        return contextValues;
        Throwable t;
        t;
        try
        {
            txi.onThrowable(txiContext, t);
        }
        catch(Throwable tt)
        {
            m_log.error(tt.getMessage(), tt);
        }
        m_log.error(t.getMessage(), t);
        throw new RuntimeSystemException(t.getMessage(), t);
    }

    public void onReturn(Object context, Object returnValue)
    {
        if(context != null)
        {
            boolean commit = false;
            if(returnValue instanceof ProcessingResultHolder)
                commit = ((ProcessingResultHolder)returnValue).getProcessingResult().isSuccessfulResult();
            Object txiContext = ((Map)context).get("txi_context");
            if(commit)
            {
                txi.onReturn(txiContext, returnValue);
            } else
            {
                txi.onThrowable(txiContext, null);
                if(DBG)
                    m_log.debug("**** Rollback transaction ****");
            }
        }
    }

    public void onThrowable(Object context, Throwable thrownObject)
    {
        if(context != null)
        {
            Object txiContext = ((Map)context).get("txi_context");
            txi.onThrowable(txiContext, thrownObject);
        }
    }

    private void verifyLicense(String methodName, Object args[])
    {
        if(m_isMarginAndMiniStockLicenseAvailable)
            return;
        if(!m_licenseChecker4Margin.isValidLicenseAvailable() && m_methodsToIntercept4ValidMarginLicense.containsKey(methodName))
        {
            boolean verifyMarginLicense = true;
            if(methodName.equals("validateNewOrder") || methodName.equals("submitNewOrder"))
            {
                Object orderSpec = args[2];
                verifyMarginLicense = (orderSpec instanceof EqTypeOpenContractOrderSpec) || (orderSpec instanceof EqTypeSettleContractOrderSpec) || (orderSpec instanceof EqTypeSwapContractOrderSpec);
            }
            if(verifyMarginLicense)
            {
                m_log.error(NO_VALID_EQTYPE_MARGIN_LICENSE.toString());
                throw new LicenseException(NO_VALID_EQTYPE_MARGIN_LICENSE.toString());
            }
        }
        if(!m_licenseChecker4Mini.isValidLicenseAvailable() && m_methodsToIntercept4ValidMiniStockLicense.containsKey(methodName))
        {
            boolean verifyMiniLicense = true;
            if(methodName.equals("validateNewOrder") || methodName.equals("submitNewOrder"))
            {
                Object orderSpec = args[2];
                verifyMiniLicense = orderSpec instanceof EqTypeNewMiniStockOrderSpec;
            }
            if(verifyMiniLicense)
            {
                m_log.error(NO_VALID_EQTYPE_MINI_LICENSE.toString());
                throw new LicenseException(NO_VALID_EQTYPE_MINI_LICENSE.toString());
            }
        }
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static final String TXI_CONTEXT_VAR_NAME = "txi_context";
    private static Map m_methodsToIntercept4ValidMarginLicense = null;
    private final TransactionalInterceptor txi = new TransactionalInterceptor(1);
    private static final String CHECK_FOR_EQTYPE_MARGIN_LICENSE_ONCALL[] = {
        "validateOpenContractOrder", "validateSettleContractOrder", "validateSwapContractOrder", "validateNewOrder", "submitOpenContractOrder", "submitSettleContractOrder", "submitSwapContractOrder", "submitNewOrder"
    };
    private static final String EQTYPE_MARGIN_COMPONENT_NAME_FOR_LICENSE_VERIFICATION = "xblocks.eqtype.margin";
    private static final String LICENSE_PUBLIC_KEY_FOR_EQTYPE_MARGIN = "aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405f72804c0add18501fc8f0a65deda4a5ea9ea2280929ed361da92e4ad4d932b5f2d021107412e9fc58ddf2fb5fa6785f7d83a929072dc102a75405d58b4d5c2d78737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fffffffffefffffffe00000001757200025b42acf317f8060854e00200007870000000405f72804c0add18501fc8f0a65deda4a5ea9ea2280929ed361da92e4ad4d932b5f2d021107412e9fc58ddf2fb5fa6785f7d83a929072dc102a75405d58b4d5c2d";
    private static final String CHECK_FOR_EQTYPE_MINI_STOCK_LICENSE_ONCALL[] = {
        "validateNewMiniStockOrder", "validateNewOrder", "submitNewMiniStockOrder", "submitNewOrder"
    };
    private static Map m_methodsToIntercept4ValidMiniStockLicense = null;
    private static Map m_methodsToIntercept4SerializingAccount = null;
    private static final String EQTYPE_MINI_COMPONENT_NAME_FOR_LICENSE_VERIFICATION = "xblocks.eqtype.mini";
    private static final String LICENSE_PUBLIC_KEY_FOR_MINI = "aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4034300024053258e80cd34cc75d287939fc95bffe054f62e9bf094ca5d180e11ff49cbc6ecf687ae970d6e4fa6f6d68e0851a1218ecb3a9e53aa284185b3508ad14be13de578737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fffffffffefffffffe00000001757200025b42acf317f8060854e002000078700000004053258e80cd34cc75d287939fc95bffe054f62e9bf094ca5d180e11ff49cbc6ecf687ae970d6e4fa6f6d68e0851a1218ecb3a9e53aa284185b3508ad14be13de5";
    static final GtlComponentLicenseCheckerUtil m_licenseChecker4Margin;
    static final GtlComponentLicenseCheckerUtil m_licenseChecker4Mini;
    private static ErrorManager m_errorMgr;
    public static final ErrorInfo NO_VALID_EQTYPE_MARGIN_LICENSE;
    public static final ErrorInfo NO_VALID_EQTYPE_MINI_LICENSE;
    static final boolean m_isMarginAndMiniStockLicenseAvailable;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderManagerInterceptor.class);
        DBG = m_log.ison();
        m_licenseChecker4Margin = new GtlComponentLicenseCheckerUtil("xblocks.eqtype.margin", "aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405f72804c0add18501fc8f0a65deda4a5ea9ea2280929ed361da92e4ad4d932b5f2d021107412e9fc58ddf2fb5fa6785f7d83a929072dc102a75405d58b4d5c2d78737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fffffffffefffffffe00000001757200025b42acf317f8060854e00200007870000000405f72804c0add18501fc8f0a65deda4a5ea9ea2280929ed361da92e4ad4d932b5f2d021107412e9fc58ddf2fb5fa6785f7d83a929072dc102a75405d58b4d5c2d");
        m_licenseChecker4Mini = new GtlComponentLicenseCheckerUtil("xblocks.eqtype.mini", "aced00057372002273756e2e73656375726974792e70726f76696465722e4453415075626c69634b6579d6727d0d0419eb7b0200014c0001797400164c6a6176612f6d6174682f426967496e74656765723b7872001973756e2e73656375726974792e783530392e583530394b6579b5a01dbe649a72a60300034c0005616c67696474001f4c73756e2f73656375726974792f783530392f416c676f726974686d49643b5b000a656e636f6465644b65797400025b425b00036b657971007e0004787077f33081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4034300024053258e80cd34cc75d287939fc95bffe054f62e9bf094ca5d180e11ff49cbc6ecf687ae970d6e4fa6f6d68e0851a1218ecb3a9e53aa284185b3508ad14be13de578737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d020006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e697475646571007e0004787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870ffffffff000001fffffffffefffffffe00000001757200025b42acf317f8060854e002000078700000004053258e80cd34cc75d287939fc95bffe054f62e9bf094ca5d180e11ff49cbc6ecf687ae970d6e4fa6f6d68e0851a1218ecb3a9e53aa284185b3508ad14be13de5");
        m_errorMgr = ErrorManager.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradingPlugin.class);
        NO_VALID_EQTYPE_MARGIN_LICENSE = m_errorMgr.defineErrorInfo("XB_NO_VALID_EQTYPE_MARGIN_LICENSE", "No valid license for : xblocks.eqtype.margin");
        NO_VALID_EQTYPE_MINI_LICENSE = m_errorMgr.defineErrorInfo("XB_NO_VALID_EQTYPE_MINI_STOCK_LICENSE", "No valid license for : xblocks.eqtype.mini");
        m_isMarginAndMiniStockLicenseAvailable = m_licenseChecker4Margin.isValidLicenseAvailable() && m_licenseChecker4Mini.isValidLicenseAvailable();
    }
}
