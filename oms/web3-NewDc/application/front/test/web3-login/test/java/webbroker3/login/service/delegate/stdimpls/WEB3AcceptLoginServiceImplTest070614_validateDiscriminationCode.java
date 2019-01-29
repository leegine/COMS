head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AcceptLoginServiceImplTest070614_validateDiscriminationCode.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客ログインサービステスト(WEB3AcceptLoginServiceImplTest070614_validateDiscriminationCode.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/14 武波(中訊) 新規作成
*/
package webbroker3.login.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;

import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PwdCheckFlagDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (顧客ログインサービステスト)<BR>
 * 顧客ログインサービスクラステスト
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AcceptLoginServiceImplTest070614_validateDiscriminationCode
    extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptLoginServiceImplTest070614_validateDiscriminationCode.class);

    public WEB3AcceptLoginServiceImplTest070614_validateDiscriminationCode(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    /**
     * setUp<BR>
     * @@param l_strName
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     * tearDown<BR>
     * @@param l_strName
     */
    protected void tearDown() throws Exception
    {
        super.checkMethodValue();
        super.tearDown();
    }

    /**
     * 
     *
     */
    public void test_validateDiscriminationCode_C0003()
    {
        final String STR_METHOD_NAME = " test_validateDiscriminationCode_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Map l_mapLoginTypeAttributes = new HashMap();
            Map l_mapLoginAttributes = new HashMap();
            l_mapLoginTypeAttributes.put(
                WEB3LoginTypeAttributeKeyDef.DISCRIMINATION_CD_CHECK + WEB3OrderRootDivDef.VODAFONE, WEB3PwdCheckFlagDef.NO_CHECK);
            WEB3AcceptLoginServiceImpl l_acceptLoginServiceImpl = new WEB3AcceptLoginServiceImpl();
            ProcessingResult l_processingResult = l_acceptLoginServiceImpl.validateDiscriminationCode(
                "", WEB3OrderRootDivDef.VODAFONE, l_mapLoginTypeAttributes, l_mapLoginAttributes);
            assertNull(l_processingResult.getErrorInfo());
            assertTrue(l_processingResult.isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     *
     */
    public void test_validateDiscriminationCode_C0004()
    {
        final String STR_METHOD_NAME = " test_validateDiscriminationCode_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Map l_mapLoginTypeAttributes = new HashMap();
            Map l_mapLoginAttributes = new HashMap();
            l_mapLoginTypeAttributes.put(
                WEB3LoginTypeAttributeKeyDef.DISCRIMINATION_CD_CHECK
                + WEB3OrderRootDivDef.VODAFONE, WEB3PwdCheckFlagDef.CHECK);
            l_mapLoginAttributes.put(WEB3LoginAttributeKeyDef.WEB3_ENCRYPTED_DISCRIMINATION_CD, null);
            WEB3AcceptLoginServiceImpl l_acceptLoginServiceImpl = new WEB3AcceptLoginServiceImpl();
            ProcessingResult l_processingResult = l_acceptLoginServiceImpl.validateDiscriminationCode(
                "123", WEB3OrderRootDivDef.VODAFONE, l_mapLoginTypeAttributes, l_mapLoginAttributes);
            ErrorInfo l_ErrorInfo = l_processingResult.getErrorInfo();
            assertFalse(l_processingResult.isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     *
     */
    public void test_validateDiscriminationCode_C0005()
    {
        final String STR_METHOD_NAME = " test_validateDiscriminationCode_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Map l_mapLoginTypeAttributes = new HashMap();
            Map l_mapLoginAttributes = new HashMap();
            l_mapLoginTypeAttributes.put(
                WEB3LoginTypeAttributeKeyDef.DISCRIMINATION_CD_CHECK
                + WEB3OrderRootDivDef.VODAFONE, WEB3PwdCheckFlagDef.CHECK);
            l_mapLoginAttributes.put(WEB3LoginAttributeKeyDef.WEB3_ENCRYPTED_DISCRIMINATION_CD, "123");
            WEB3AcceptLoginServiceImpl l_acceptLoginServiceImpl = new WEB3AcceptLoginServiceImpl();
            ProcessingResult l_processingResult = l_acceptLoginServiceImpl.validateDiscriminationCode(
                "321", WEB3OrderRootDivDef.VODAFONE, l_mapLoginTypeAttributes, l_mapLoginAttributes);
            ErrorInfo l_ErrorInfo = l_processingResult.getErrorInfo();
            assertFalse(l_processingResult.isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     *
     */
    public void test_validateDiscriminationCode_C0006()
    {
        final String STR_METHOD_NAME = " test_validateDiscriminationCode_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Map l_mapLoginTypeAttributes = new HashMap();
            Map l_mapLoginAttributes = new HashMap();
            l_mapLoginTypeAttributes.put(
                WEB3LoginTypeAttributeKeyDef.DISCRIMINATION_CD_CHECK
                + WEB3OrderRootDivDef.VODAFONE, WEB3PwdCheckFlagDef.CHECK);
            WEB3AcceptLoginServiceImpl l_acceptLoginServiceImpl = new WEB3AcceptLoginServiceImpl();
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strEncryptPwd = l_crypt.encrypt("123");
            l_mapLoginAttributes.put(WEB3LoginAttributeKeyDef.WEB3_ENCRYPTED_DISCRIMINATION_CD, l_strEncryptPwd);
            ProcessingResult l_processingResult = l_acceptLoginServiceImpl.validateDiscriminationCode(
                "123", WEB3OrderRootDivDef.VODAFONE, l_mapLoginTypeAttributes, l_mapLoginAttributes);
//            ErrorInfo l_ErrorInfo = l_processingResult.getErrorInfo();
            assertTrue(l_processingResult.isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
