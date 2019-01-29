head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginCompleteRequestTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済完了リクエストテストクラス(WEB3OptionsCloseMarginCompleteRequestTestzj.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/06/12 周捷 新規作成                          
*/

package webbroker3.ifo.message;

import java.util.Date;

import test.util.JunitTestBase;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginCompleteRequestTest_OT extends JunitTestBase
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginCompleteRequestTest_OT.class);
    
    WEB3OptionsCloseMarginCompleteRequest l_request = null;

    public WEB3OptionsCloseMarginCompleteRequestTest_OT(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3OptionsCloseMarginCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * １）　@スーパークラスのvalidateメソッドを呼び出す
     */
    public void test_validate_case1()
    {
        final String STR_METHOD_NAME = "test_validate_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.返済建玉=null の場合
     */
    public void test_validate_case2()
    {
        final String STR_METHOD_NAME = "test_validate_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.返済建玉の要素数=0 の場合
     */
    public void test_validate_case3()
    {
        final String STR_METHOD_NAME = "test_validate_case3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序≠null and this.決済順序≠（以下の値） の場合
　@　@ *　@・”0：ランダム” 
　@　@ *　@・”1：単価益順” 
　@　@ *　@・”2：単価損順” 
　@　@ *　@・”3：建日順” 
     */
    public void test_validate_case4()
    {
        final String STR_METHOD_NAME = "test_validate_case4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "5";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00179);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.返済建玉の要素数>1and this.決済順序==nullの場合
     */
    public void test_validate_case5()
    {
        final String STR_METHOD_NAME = "test_validate_case5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] =
                {l_closeMarginContractUnit1, l_closeMarginContractUnit2};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02304);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=null and this.注文数量=null の場合
     */
    public void test_validate_case6()
    {
        final String STR_METHOD_NAME = "test_validate_case6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=1：単価益順and this.注文数量=null の場合
     */
    public void test_validate_case7()
    {
        final String STR_METHOD_NAME = "test_validate_case7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=2：単価益順and this.注文数量=null の場合
     */
    public void test_validate_case8()
    {
        final String STR_METHOD_NAME = "test_validate_case8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=3：単価益順and this.注文数量=null の場合
     */
    public void test_validate_case9()
    {
        final String STR_METHOD_NAME = "test_validate_case9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.注文数量≠null and this.注文数量≠数字 の場合
     */
    public void test_validate_case10()
    {
        final String STR_METHOD_NAME = "test_validate_case10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "a";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00075);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.注文数量≠null and this.注文数量<0 の場合
     */
    public void test_validate_case11()
    {
        final String STR_METHOD_NAME = "test_validate_case11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "-1";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.注文数量≠null and this.注文数量=0 の場合
     */
    public void test_validate_case12()
    {
        final String STR_METHOD_NAME = "test_validate_case12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "0";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=”0：ランダム” の場合、返済建玉の要素数分下記のチェックを繰り返して行う。 
     *   返済建玉のvalidate()メソッドを呼び出す。 
     */
    public void test_validate_case13()
    {
        final String STR_METHOD_NAME = "test_validate_case13()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=”0：ランダム” の場合返済建玉.決済順位=null  
     */
    public void test_validate_case14()
    {
        final String STR_METHOD_NAME = "test_validate_case14()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00180);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=”0：ランダム” の場合返済建玉.決済順位= 0 
     */
    public void test_validate_case15()
    {
        final String STR_METHOD_NAME = "test_validate_case15()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "001";
            l_closeMarginContractUnit.settlePriority = "0";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00180);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=”0：ランダム” の場合返済建玉の全要素について、
     * 返済建玉.決済順位>0 and 返済建玉.数量>0 となる 
     * 組み合わせが存在しない場合
     */
    public void test_validate_case16()
    {
        final String STR_METHOD_NAME = "test_validate_case16()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "001";
            l_closeMarginContractUnit.settlePriority = "1";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00180);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=nullの場合,返済建玉.決済順位≠null 
     */
    public void test_validate_case17()
    {
        final String STR_METHOD_NAME = "test_validate_case17()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "001";
            l_closeMarginContractUnit.settlePriority = "1";
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = null;
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00183);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=nullの場合,返済建玉のvalidate()メソッドを呼び出す
     */
    public void test_validate_case18()
    {
        final String STR_METHOD_NAME = "test_validate_case18()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = null;
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=1：単価益順の場合,返済建玉.決済順位≠null 
     */
    public void test_validate_case19()
    {
        final String STR_METHOD_NAME = "test_validate_case19()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = "1";
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00183);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=1：単価益順の場合,返済建玉のvalidate()メソッドを呼び出す
     */
    public void test_validate_case20()
    {
        final String STR_METHOD_NAME = "test_validate_case20()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=2：単価損順の場合,返済建玉.決済順位≠null 
     */
    public void test_validate_case21()
    {
        final String STR_METHOD_NAME = "test_validate_case21()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = "1";
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00183);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=2：単価損順の場合,返済建玉のvalidate()メソッドを呼び出す
     */
    public void test_validate_case22()
    {
        final String STR_METHOD_NAME = "test_validate_case22()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=3：建日順の場合,返済建玉.決済順位≠null 
     */
    public void test_validate_case23()
    {
        final String STR_METHOD_NAME = "test_validate_case23()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = "1";
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00183);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=3：建日順の場合,返済建玉のvalidate()メソッドを呼び出す
     */
    public void test_validate_case24()
    {
        final String STR_METHOD_NAME = "test_validate_case24()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.注文ID≠nullの場合,this.確認時単価が初期値（未入力）の値であれば
     */
    public void test_validate_case25()
    {
        final String STR_METHOD_NAME = "test_validate_case25()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "10";
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.orderId = "0001";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00206);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.注文ID≠nullの場合,this.確認時発注日が初期値（未入力）の値であれば
     */
    public void test_validate_case26()
    {
        final String STR_METHOD_NAME = "test_validate_case26()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "10";
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.orderId = "0001";
            l_request.checkPrice = "100";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00078);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * 以上check正常通過
     */
    public void test_validate_case27()
    {
        final String STR_METHOD_NAME = "test_validate_case27()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequestPass();
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "10";
            l_closeMarginContractUnit.settlePriority = null;
            l_closeMarginContractUnit.contractOrderQuantity = "20";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {l_closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "2";
            l_request.orderId = "0001";
            l_request.checkPrice = "100";
            l_request.checkDate = new Date("2004/07/16");
            l_request.validate();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * １）　@スーパークラスのvalidateメソッドを呼び出す
     */
    public void test_validateATReserveOrder_case1()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.注文ID＝nullの場合
     */
    public void test_validateATReserveOrder_case2()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00600);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.返済建玉=null の場合
     */
    public void test_validateATReserveOrder_case3()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.返済建玉の要素数=0 
     */
    public void test_validateATReserveOrder_case4()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序≠null and 
     * this.決済順序≠（以下の値） の場合
　@　@ *　@・”0：ランダム” 
　@　@ *　@・”1：単価益順” 
　@　@ *　@・”2：単価損順” 
　@　@ *　@・”3：建日順” 
     */
    public void test_validateATReserveOrder_case5()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "5";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00179);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=null  and this.注文数量=null の場合
     */
    public void test_validateATReserveOrder_case6()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = null;
            l_request.opOrderQuantity = null;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=1：単価益順and this.注文数量=null の場合
     */
    public void test_validateATReserveOrder_case7()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = null;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=2：単価益順and this.注文数量=null の場合
     */
    public void test_validateATReserveOrder_case8()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.opOrderQuantity = null;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.決済順序=3：単価益順and this.注文数量=null の場合
     */
    public void test_validateATReserveOrder_case9()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = null;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.注文数量≠null and this.注文数量≠数字 の場合
     */
    public void test_validateATReserveOrder_case10()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "a";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00075);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.注文数量≠null and this.注文数量<0 の場合
     */
    public void test_validateATReserveOrder_case11()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "0";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.注文数量≠null and this.注文数量=0 の場合
     */
    public void test_validateATReserveOrder_case12()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "-1";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * 決済順序＝”0：ランダム”の場合返済建玉.注文数量 がnull 
     */
    public void test_validateATReserveOrder_case13()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case13()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03060);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * 決済順序＝”0：ランダム”の場合返済建玉.注文数量 が数字以外 
     */
    public void test_validateATReserveOrder_case14()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case14()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "a";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03060);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * 決済順序＝”0：ランダム”の場合返済建玉.注文数量 が０以下の数字
     */
    public void test_validateATReserveOrder_case15()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case15()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "-2";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03060);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * 決済順序＝”0：ランダム”の場合返済建玉.注文数量 が・８桁を超える数字
     */
    public void test_validateATReserveOrder_case16()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case16()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "100000000";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03060);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.確認時単価＝nullであった場合
     */
    public void test_validateATReserveOrder_case17()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case17()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00206);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * this.確認時発注日＝nullであった場合
     */
    public void test_validateATReserveOrder_case18()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case18()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.checkPrice = "100";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00078);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /**
     * 以上check正常通過
     */
    public void test_validateATReserveOrder_case19()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case19()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            commonRequestPass();
            l_request.orderId = "0001";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            closeMarginContractUnit.contractOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit closeMarginContractUnits[] = {closeMarginContractUnit};
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "10";
            l_request.checkPrice = "100";
            l_request.checkDate = new Date("2004/07/16");
            l_request.validateATReserveOrder();
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }

    private void commonRequestPass()
    {
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "5";
        l_request.execCondType = "1";
        l_request.expirationDateType = "2";
        l_request.expirationDate = WEB3DateUtility.getDate("20080606", "yyyyMMdd");
        l_request.orderCondType = "1";
        l_request.stopPremium_underlyingAssets = "0";
        l_request.stopOrderCondPrice = "5";
        l_request.stopOrderCondOperator = "1";
    }  
}
@
