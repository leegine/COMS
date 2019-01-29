head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXSimplexTransferAbleAmtConnectionSystemTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3FXSimplexTransferAbleAmtConnectionSystemTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/22 武波 (中訊) 新規作成
*/
package webbroker3.aio;

import webbroker3.aio.message.WEB3FXSimplexAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXSimplexTransferAbleAmtConnectionSystemTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSimplexTransferAbleAmtConnectionSystemTest.class);

    WEB3FXSimplexTransferAbleAmtConnectionSystem l_simplexTransferAbleAmtConnectionSystem =
        new WEB3FXSimplexTransferAbleAmtConnectionSystem();
    public WEB3FXSimplexTransferAbleAmtConnectionSystemTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 正常的情況下
     *
     */
    public void testCreateHash_Case001()
    {
        final String STR_METHOD_NAME = "testCreateHash_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit =
                new WEB3FXSimplexAskingTelegramUnit();
            l_simplexAskingTelegramUnit.oseFxLoginId = "123";
            l_simplexAskingTelegramUnit.mail1 = "wu-bo@@sinocom.cn";
            l_simplexAskingTelegramUnit.timeStamp = "1";
            WEB3FXSimplexAskingTelegramUnit l_unit =
                l_simplexTransferAbleAmtConnectionSystem.createHash(l_simplexAskingTelegramUnit, "1");
            log.debug(l_unit.hashValue);
            assertEquals("6512bd43d9caa6e02c990b0a82652dca", l_unit.hashValue);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 異常的情況下
     *
     */
    public void testCreateHash_Case002()
    {
        final String STR_METHOD_NAME = "testCreateHash_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_simplexTransferAbleAmtConnectionSystem.createHash(null, "1");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 正常的情況下
     *
     */
    public void testCreateURL_Case001()
    {
        final String STR_METHOD_NAME = "testCreateURL_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit =
                new WEB3FXSimplexAskingTelegramUnit();
            l_simplexAskingTelegramUnit.fullNameKana = "1";
            l_simplexAskingTelegramUnit.fullName = "1";
            l_simplexAskingTelegramUnit.genderType = "1";
            l_simplexAskingTelegramUnit.zipCode = "1";
            l_simplexAskingTelegramUnit.address1 = "1";
            l_simplexAskingTelegramUnit.address2 = "1";
            l_simplexAskingTelegramUnit.address3 = "1";
            l_simplexAskingTelegramUnit.initialLoginPassword = "1";
            l_simplexAskingTelegramUnit.initialTradePassword = "1";
            l_simplexAskingTelegramUnit.initialOsePassword = "1";
            l_simplexAskingTelegramUnit.hashValue = "1";
            l_simplexAskingTelegramUnit.oseFxLoginId = "123";
            l_simplexAskingTelegramUnit.mail1 = "wu-bo@@sinocom.cn";
            l_simplexAskingTelegramUnit.timeStamp = "1";
            String l_strURL =
                l_simplexTransferAbleAmtConnectionSystem.createURL("1", l_simplexAskingTelegramUnit);
            log.debug(l_strURL);
            assertEquals("1GetWithdrawLimit.do?oseFxLoginId=123" +
                    "&tie-upAccountId=&tie-upSystemId=" +
                    "&timeStamp=1&hashValue=1", l_strURL);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 異常的情況下
     *
     */
    public void testCreateURL_Case002()
    {
        final String STR_METHOD_NAME = "testCreateURL_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_simplexTransferAbleAmtConnectionSystem.createURL("1", null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
