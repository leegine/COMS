head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.21.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeCsvDataModelTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.text.SimpleDateFormat;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeCsvDataModelTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeCsvDataModelTest.class);

    public WEB3GentradeCsvDataModelTest(String arg0)
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

    public class WEB3GentradeCsvDataModelTest1 extends WEB3GentradeCsvDataModel
    {
        public int addRow()
        {
            return 0;
        }

        public int addRow(String l_str)
        {
            return 1;
        }
    }

    public void testAddRow()
    {
        final String STR_METHOD_NAME = "testAddRow()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeCsvDataModelTest1 l_model = new WEB3GentradeCsvDataModelTest1();
            assertEquals(1, l_model.addRow("1", "0"));
            assertEquals(-1, l_model.addRow("", "1"));

            l_model.columnHeader = new WEB3GentradeCsvColumnModel[4];
            l_model.addRow("1,1", "1");
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01993);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAddRow1()
    {
        final String STR_METHOD_NAME = "testAddRow1()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeCsvDataModel l_model = new WEB3GentradeCsvDataModel();

            WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[8];
            l_models[0] = new WEB3GentradeCsvColumnModel(
                "123",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

            l_models[1] = new WEB3GentradeCsvColumnModel(
                "20071224",
                1,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

            l_models[2] = new WEB3GentradeCsvColumnModel(
                "1.1",
                2,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

            l_models[3] = new WEB3GentradeCsvColumnModel(
                "11",
                3,
                WEB3GentradeCsvColumnModel.INTEGERTYPE,
                null);

            l_models[4] = new WEB3GentradeCsvColumnModel(
                "11",
                4,
                WEB3GentradeCsvColumnModel.LONGTYPE,
                null);

            l_models[5] = new WEB3GentradeCsvColumnModel(
                "20071224094356",
                5,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD +
                    WEB3GentradeTimeDef.TIME_FORMAT_HMS));

            l_models[6] = new WEB3GentradeCsvColumnModel(
                "11",
                4,
                WEB3GentradeCsvColumnModel.LONGTYPE,
                null);

            l_models[7] = new WEB3GentradeCsvColumnModel(
                "11",
                4,
                9,
                null);

            l_model.setColumnHeader(l_models);
            l_model.addRow("123,20071224,1.1,11,11,20071224094356,,11", "1");
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80022);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAddRow2()
    {
        final String STR_METHOD_NAME = "testAddRow2()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeCsvDataModel l_model = new WEB3GentradeCsvDataModel();

            WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[2];
            l_models[0] = new WEB3GentradeCsvColumnModel(
                "123",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

            l_models[1] = new WEB3GentradeCsvColumnModel(
                "20071224",
                1,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

            l_model.setColumnHeader(l_models);
            assertEquals(0, l_model.addRow("1,", "1"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAddRow3()
    {
        final String STR_METHOD_NAME = "testAddRow3()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeCsvDataModel l_model = new WEB3GentradeCsvDataModel();

            WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[2];
            l_models[0] = new WEB3GentradeCsvColumnModel(
                "123",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

            l_models[1] = new WEB3GentradeCsvColumnModel(
                "20071224",
                1,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

            l_model.setColumnHeader(l_models);
            l_model.addRow("1,2007121a", "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02994);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAddRow4()
    {
        final String STR_METHOD_NAME = "testAddRow4()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeCsvDataModel l_model = new WEB3GentradeCsvDataModel();

            WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[2];
            l_models[0] = new WEB3GentradeCsvColumnModel(
                "123",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

            l_models[1] = new WEB3GentradeCsvColumnModel(
                "20071224094356",
                5,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD +
                    WEB3GentradeTimeDef.TIME_FORMAT_HMS));

            l_model.setColumnHeader(l_models);
            l_model.addRow("1,2007122409435a", "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02994);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAddRow5()
    {
        final String STR_METHOD_NAME = "testAddRow5()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeCsvDataModel l_model = new WEB3GentradeCsvDataModel();

            WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[2];
            l_models[0] = new WEB3GentradeCsvColumnModel(
                "123",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

            l_models[1] = new WEB3GentradeCsvColumnModel(
                "20071224",
                1,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

            l_model.setColumnHeader(l_models);
            l_model.addRow("1,200712102", "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02994);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAddRow6()
    {
        final String STR_METHOD_NAME = "testAddRow6()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeCsvDataModel l_model = new WEB3GentradeCsvDataModel();

            WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[2];
            l_models[0] = new WEB3GentradeCsvColumnModel(
                "123",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

            l_models[1] = new WEB3GentradeCsvColumnModel(
                "20071224094356",
                5,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD +
                    WEB3GentradeTimeDef.TIME_FORMAT_HMS));

            l_model.setColumnHeader(l_models);
            l_model.addRow("1,200712240943502", "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02994);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAddRow7()
    {
        final String STR_METHOD_NAME = "testAddRow7()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeCsvDataModel l_model = new WEB3GentradeCsvDataModel();

            WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[2];
            l_models[0] = new WEB3GentradeCsvColumnModel(
                "123",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

            l_models[1] = new WEB3GentradeCsvColumnModel(
                "11",
                3,
                WEB3GentradeCsvColumnModel.INTEGERTYPE,
                null);

            l_model.setColumnHeader(l_models);
            l_model.addRow("1,2a", "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03002);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testAddRow8()
    {
        final String STR_METHOD_NAME = "testAddRow8()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeCsvDataModel l_model = new WEB3GentradeCsvDataModel();

            WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[2];
            l_models[0] = new WEB3GentradeCsvColumnModel(
                "123",
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

            l_models[1] = new WEB3GentradeCsvColumnModel(
                "20071224",
                1,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

            l_model.setColumnHeader(l_models);
            l_model.addRow("1,20071321", "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02994);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
