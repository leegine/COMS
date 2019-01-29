head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.57.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyConditionCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3MutualFixedBuyConditionCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/18 安陽(中訊) 新規作成
*/
package webbroker3.mf.message;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFixedBuyConditionCompleteRequestTest extends TestBaseForMock
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionCompleteRequestTest.class);
    
    private WEB3MutualFixedBuyConditionCompleteRequest l_request = null;

    public WEB3MutualFixedBuyConditionCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3MutualFixedBuyConditionCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        l_request = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteRequest.validate()'
     */
    
    //１－１－１）銘柄コード == null の場合、例外をスローする。
    //BUSINESS_ERROR_00079
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //１－１－２－１）買付金額（月々）が数字以外の場合、例外をスローする。
    // BUSINESS_ERROR_02476
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = "a";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02476, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //１－１－２－１）買付金額（月々）が数字以外の場合、例外をスローする。
    // BUSINESS_ERROR_02476
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = "!";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02476, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //１－１－２－２）買付金額（月々） ≧ 10桁の場合、例外をスローする。
    //BUSINESS_ERROR_02477
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = "1000000000";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02477, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //1－１－３－１）買付金額（積み増し）が数字以外の場合、例外をスローする。
    //BUSINESS_ERROR_02478
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = "9999999";
            l_request.conditionList[0].increaseBuyAmount = "a";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02478, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //1－１－３－１）買付金額（積み増し）が数字以外の場合、例外をスローする。
    //BUSINESS_ERROR_02478
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = null;
            l_request.conditionList[0].increaseBuyAmount = "!";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02478, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //1－１－３－２）買付金額（積み増し） ≧ 10桁の場合、例外をスローする。
    //BUSINESS_ERROR_02479
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = null;
            l_request.conditionList[0].increaseBuyAmount = "1000000000";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02479, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //１－１－４）変更区分 == null の場合、例外をスローする。
    //BUSINESS_ERROR_03102
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = null;
            l_request.conditionList[0].increaseBuyAmount = "9999999";
            l_request.conditionList[0].changeDiv = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03102, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //１－１－５）適用開始年月 == null の場合、例外をスローする。
    //BUSINESS_ERROR_03103
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = null;
            l_request.conditionList[0].increaseBuyAmount = null;
            l_request.conditionList[0].changeDiv = "1";
            l_request.conditionList[0].validStartDate = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03103, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    //２）投信定時定額買付新規追加内容のチェック
    
    //２－１－１）銘柄コード == null の場合、例外をスローする。
    //BUSINESS_ERROR_00079
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = null;
            l_request.conditionList[0].increaseBuyAmount = null;
            l_request.conditionList[0].changeDiv = "1";
            l_request.conditionList[0].validStartDate = GtlUtils.getSystemTimestamp();
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = null;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２－１－２）買付金額（月々） == null の場合、例外をスローする。
    //BUSINESS_ERROR_02475
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = null;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02475, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２－１－３）買付金額（月々）が数字以外の場合、例外をスローする。
    //BUSINESS_ERROR_02476
    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "a";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02476, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２－１－３）買付金額（月々）が数字以外の場合、例外をスローする。
    //BUSINESS_ERROR_02476
    public void testValidate_C0013()
    {
        final String STR_METHOD_NAME = "testValidate_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "!";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02476, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２－１－４）買付金額（月々） ≧ 10桁の場合、例外をスローする。
    //BUSINESS_ERROR_02477
    public void testValidate_C0014()
    {
        final String STR_METHOD_NAME = "testValidate_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "1000000000";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02477, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２－１－５－１）買付金額（積み増し）が数字以外の場合、例外をスローする。
    //BUSINESS_ERROR_02478
    public void testValidate_C0015()
    {
        final String STR_METHOD_NAME = "testValidate_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "9999999";
            l_request.addConditionList[0].increaseBuyAmount = "a";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02478, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２－１－５－１）買付金額（積み増し）が数字以外の場合、例外をスローする。
    //BUSINESS_ERROR_02478
    public void testValidate_C0016()
    {
        final String STR_METHOD_NAME = "testValidate_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "5000";
            l_request.addConditionList[0].increaseBuyAmount = "!";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02478, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２－１－５－２）買付金額（積み増し） ≧ 10桁の場合、例外をスローする。
    //BUSINESS_ERROR_02479
    public void testValidate_C0017()
    {
        final String STR_METHOD_NAME = "testValidate_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "5000";
            l_request.addConditionList[0].increaseBuyAmount = "1000000000";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02479, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２－１－６）変更区分 == null の場合、例外をスローする。
    //BUSINESS_ERROR_03102
    public void testValidate_C0018()
    {
        final String STR_METHOD_NAME = "testValidate_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "5000";
            l_request.addConditionList[0].increaseBuyAmount = "9999999";
            l_request.addConditionList[0].changeDiv = null;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03102, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２－１－７）適用開始年月 == null の場合、例外をスローする。
    //BUSINESS_ERROR_03103
    public void testValidate_C0019()
    {
        final String STR_METHOD_NAME = "testValidate_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "5000";
            l_request.addConditionList[0].increaseBuyAmount = "5000";
            l_request.addConditionList[0].changeDiv = "1";
            l_request.addConditionList[0].validStartDate = null;
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03103, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //正常終結 空
    public void testValidate_C0020()
    {
        final String STR_METHOD_NAME = "testValidate_C0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = null;
            
            l_request.addConditionList = null;
            
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //正常終結 0個
    public void testValidate_C0021()
    {
        final String STR_METHOD_NAME = "testValidate_C0021()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[0];
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[0];
            
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //正常終結 1個
    public void testValidate_C0022()
    {
        final String STR_METHOD_NAME = "testValidate_C0022()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = null;
            l_request.conditionList[0].increaseBuyAmount = null;
            l_request.conditionList[0].changeDiv = "1";
            l_request.conditionList[0].validStartDate = GtlUtils.getSystemTimestamp();
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[1];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "5000";
            l_request.addConditionList[0].increaseBuyAmount = "5000";
            l_request.addConditionList[0].changeDiv = "1";
            l_request.addConditionList[0].validStartDate = GtlUtils.getSystemTimestamp();
            
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //正常終結 多個（3個）
    public void testValidate_C0023()
    {
        final String STR_METHOD_NAME = "testValidate_C0023()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.conditionList = new WEB3MutualFixedBuyCommonUnit[3];
            l_request.conditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[0].mutualProductCode = "300100";
            l_request.conditionList[0].monthlyBuyAmount = null;
            l_request.conditionList[0].increaseBuyAmount = null;
            l_request.conditionList[0].changeDiv = "1";
            l_request.conditionList[0].validStartDate = GtlUtils.getSystemTimestamp();
            l_request.conditionList[1] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[1].mutualProductCode = "300101";
            l_request.conditionList[1].monthlyBuyAmount = "5000";
            l_request.conditionList[1].increaseBuyAmount = "5000";
            l_request.conditionList[1].changeDiv = "1";
            l_request.conditionList[1].validStartDate = GtlUtils.getSystemTimestamp();
            l_request.conditionList[2] = new WEB3MutualFixedBuyCommonUnit();
            l_request.conditionList[2].mutualProductCode = "300102";
            l_request.conditionList[2].monthlyBuyAmount = "9999999";
            l_request.conditionList[2].increaseBuyAmount = "99999999";
            l_request.conditionList[2].changeDiv = "1";
            l_request.conditionList[2].validStartDate = GtlUtils.getSystemTimestamp();
            
            l_request.addConditionList = new WEB3MutualFixedBuyCommonUnit[3];
            l_request.addConditionList[0] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[0].mutualProductCode = "300100";
            l_request.addConditionList[0].monthlyBuyAmount = "5000";
            l_request.addConditionList[0].increaseBuyAmount = "5000";
            l_request.addConditionList[0].changeDiv = "1";
            l_request.addConditionList[0].validStartDate = GtlUtils.getSystemTimestamp();
            l_request.addConditionList[1] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[1].mutualProductCode = "300100";
            l_request.addConditionList[1].monthlyBuyAmount = "9999999";
            l_request.addConditionList[1].increaseBuyAmount = "99999999";
            l_request.addConditionList[1].changeDiv = "1";
            l_request.addConditionList[1].validStartDate = GtlUtils.getSystemTimestamp();
            l_request.addConditionList[2] = new WEB3MutualFixedBuyCommonUnit();
            l_request.addConditionList[2].mutualProductCode = "300100";
            l_request.addConditionList[2].monthlyBuyAmount = "5000";
            l_request.addConditionList[2].increaseBuyAmount = "5000";
            l_request.addConditionList[2].changeDiv = "1";
            l_request.addConditionList[2].validStartDate = GtlUtils.getSystemTimestamp();
            
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
