head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadCsvTest_validateDispensableItem.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AdminSrvRegiAccountDataUploadCsvTest_validateDispensableItem.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/08 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadCsvTest_validateDispensableItem extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadCsvTest_validateDispensableItem.class);

    WEB3AdminSrvRegiAccountDataUploadCsv csv = new WEB3AdminSrvRegiAccountDataUploadCsvForTest();

    public WEB3AdminSrvRegiAccountDataUploadCsvTest_validateDispensableItem(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateDispensableItem01()
    {
        final String STR_METHOD_NAME = "testValidateDispensableItem01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            csv.validateDispensableItem(1);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01020, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

//    public void testValidateDispensableItem02()
//    {
//        final String STR_METHOD_NAME = "testValidateDispensableItem02()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            csv.validateDispensableItem(2);
//            assertTrue(true);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

//    public void testValidateDispensableItem03()
//    {
//        final String STR_METHOD_NAME = "testValidateDispensableItem03()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            csv.validateDispensableItem(3);
//            fail();
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00832, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

//    public void testValidateDispensableItem04()
//    {
//        final String STR_METHOD_NAME = "testValidateDispensableItem04()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            csv.validateDispensableItem(4);
//            fail();
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00841, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

//    public void testValidateDispensableItem05()
//    {
//        final String STR_METHOD_NAME = "testValidateDispensableItem05()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            csv.validateDispensableItem(5);
//            fail();
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01030, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    class WEB3AdminSrvRegiAccountDataUploadCsvForTest extends WEB3AdminSrvRegiAccountDataUploadCsv
    {
        public Object getValue(int l_intLineNumber, WEB3GentradeCsvColumnModel l_csvColumnModel)
        {
            if (l_intLineNumber == 1)
            {
                return null;                
            }
            else if (l_intLineNumber == 2)
            {
                String l_strLabelName = l_csvColumnModel.getColumnLabel();
                if ("アップロード区分".equals(l_strLabelName))
                {
                    return "3";
                }
                else if ("申込登録ID".equals(l_strLabelName))
                {
                    return null;
                }
                else
                {
                    return "other";
                }
            }
            else if (l_intLineNumber == 3)
            {
                String l_strLabelName = l_csvColumnModel.getColumnLabel();
                if ("アップロード区分".equals(l_strLabelName))
                {
                    return "1";
                }
                else if ("申込登録ID".equals(l_strLabelName))
                {
                    return null;
                }
                else
                {
                    return "other";
                }
            }
            else if (l_intLineNumber == 4)
            {
                String l_strLabelName = l_csvColumnModel.getColumnLabel();
                if ("アップロード区分".equals(l_strLabelName))
                {
                    return "1";
                }
                else if ("登録区分".equals(l_strLabelName))
                {
                    return null;
                }
                else
                {
                    return "other";
                }
            }
            else if (l_intLineNumber == 5)
            {
                String l_strLabelName = l_csvColumnModel.getColumnLabel();
                if ("アップロード区分".equals(l_strLabelName))
                {
                    return "1";
                }
                else if ("登録区分".equals(l_strLabelName))
                {
                    return "0";
                }
                else if ("利用料金".equals(l_strLabelName))
                {
                    return null;
                }
                else
                {
                    return "other";
                }
            }
            return null;
        }

        public WEB3GentradeCsvColumnModel getColumnModel(String l_strColumnLabel)
        {
            WEB3GentradeCsvColumnModel l_columnModel;
            if ("アップロード区分".equals(l_strColumnLabel))
            {
                l_columnModel = new WEB3GentradeCsvColumnModel("アップロード区分", 0, 0, null);
                return l_columnModel;
            }
            if ("申込登録ID".equals(l_strColumnLabel))
            {
                l_columnModel = new WEB3GentradeCsvColumnModel("申込登録ID", 0, 0, null);
                return l_columnModel;
            }
            if ("登録区分".equals(l_strColumnLabel))
            {
                l_columnModel = new WEB3GentradeCsvColumnModel("登録区分", 0, 0, null);
                return l_columnModel;
            }
            if ("利用料金".equals(l_strColumnLabel))
            {
                l_columnModel = new WEB3GentradeCsvColumnModel("利用料金", 0, 0, null);
                return l_columnModel;
            }
            return new WEB3GentradeCsvColumnModel();
        }
    }
}
@
