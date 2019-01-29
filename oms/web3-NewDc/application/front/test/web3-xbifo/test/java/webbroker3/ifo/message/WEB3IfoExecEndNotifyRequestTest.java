head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoExecEndNotifyRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoOrderCarryOverMainRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/22 孟亞南 (中訊) 新規作成
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoExecEndNotifyRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3IfoExecEndNotifyRequestTest.class);
    

    public WEB3IfoExecEndNotifyRequestTest(String name)
    {
        super(name);
    }

    /**
     * (証券会社コードチェック)<BR>
     * this.証券会社コード == null の場合、「証券会社コードがnull」の例外をthrowする。<BR>
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //証券会社コード
        l_requset.institutionCode = null;
        try
        {
            l_requset.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02775,e.getErrorInfo());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (先物／オプション区分チェック)<BR>
     * this.先物／オプション区分 != （"1：先物" or "2：オプション"） の場合、<BR>
     * 「先物／オプション区分が不正です」の例外をthrowする。  <BR>
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //証券会社コード
        l_requset.institutionCode = "11";
        //先物／オプション区分
        l_requset.fuOpDiv = "3";
        try
        {
            l_requset.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02953,e.getErrorInfo());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (出来終了区分チェック)<BR>
     * this.出来終了区分 != （"1：夕場前出来終了" or "0：出来終了（最終）"） の場合、<BR>
     * 「出来終了区分が不正です」の例外をthrowする。 <BR>
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //証券会社コード
        l_requset.institutionCode = "11";
        //先物／オプション区分
        l_requset.fuOpDiv = "1";
        //出来終了区分
        l_requset.execEndDiv = "2";
        try
        {
            l_requset.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02954,e.getErrorInfo());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (出来終了区分チェック)<BR>
     * this.出来終了区分 = "1：夕場前出来終了" <BR>
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //証券会社コード
        l_requset.institutionCode = "11";
        //先物／オプション区分
        l_requset.fuOpDiv = "2";
        //出来終了区分
        l_requset.execEndDiv = "1";
        try
        {
            l_requset.validate();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (出来終了区分チェック)<BR>
     * this.出来終了区分 = 0：出来終了（最終）<BR>
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3IfoExecEndNotifyRequest l_requset = new WEB3IfoExecEndNotifyRequest();
        //証券会社コード
        l_requset.institutionCode = "11";
        //先物／オプション区分
        l_requset.fuOpDiv = "2";
        //出来終了区分
        l_requset.execEndDiv = "0";
        try
        {
            l_requset.validate();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
