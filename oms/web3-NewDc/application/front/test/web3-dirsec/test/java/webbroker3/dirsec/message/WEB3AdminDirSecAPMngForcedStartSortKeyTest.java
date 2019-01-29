head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAPMngForcedStartSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AdminDirSecAPMngForcedStartSortKeyTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/23 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAPMngForcedStartSortKeyTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAPMngForcedStartSortKeyTest.class);
    
    WEB3AdminDirSecAPMngForcedStartSortKey l_key = null;
    public WEB3AdminDirSecAPMngForcedStartSortKeyTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_key = new WEB3AdminDirSecAPMngForcedStartSortKey();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //１）　@this.キー項目＝nullの場合、
    //「ソートキー.キー項目がnull」の例外をスローする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_key.keyItem = null;
            l_key.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //２）　@this.昇順／降順＝nullの場合、
    //「ソートキー.昇順／降順がnull」の例外をスローする。
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_key.keyItem = "001";
            l_key.ascDesc = null;
            l_key.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３）　@this.昇順／降順が下記の項目以外の場合、
    //　@　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。
    //　@　@　@　@・”A：昇順”
    //　@　@　@　@・”D：降順”
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_key.keyItem = "001";
            l_key.ascDesc = "1111";
            l_key.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_key.keyItem = "001";
            l_key.ascDesc = WEB3AscDescDef.ASC;
            l_key.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
