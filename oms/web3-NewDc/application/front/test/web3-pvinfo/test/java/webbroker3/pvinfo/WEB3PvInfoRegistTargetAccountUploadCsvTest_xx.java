head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3PvInfoRegistTargetAccountUploadCsvTest_xx.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.AdministratorUploadTempRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3PvInfoRegistTargetAccountUploadCsvTest_xx extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PvInfoRegistTargetAccountUploadCsvTest_xx.class);


    public WEB3PvInfoRegistTargetAccountUploadCsvTest_xx(String arg0)
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
    
    public void testSetDataFromUploadTemp_0001()
    {
        final String STR_METHOD_NAME = " testSetDataFromUploadTemp_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(AdministratorUploadTempRow.TYPE);
            WEB3PvInfoRegistTargetAccountUploadCsv l_csv = new WEB3PvInfoRegistTargetAccountUploadCsv();
            l_csv.setDataFromUploadTemp(123L);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetDataFromUploadTemp_0002()
    {
        final String STR_METHOD_NAME = " testSetDataFromUploadTemp_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            AdministratorUploadTempParams l_upLoadTempParams = TestDBUtility.getAdministratorUploadTempRow();
            TestDBUtility.deleteAll(l_upLoadTempParams.getRowType());
            TestDBUtility.insertWithDel(l_upLoadTempParams);
            
            WEB3PvInfoRegistTargetAccountUploadCsv l_csv = new WEB3PvInfoRegistTargetAccountUploadCsv();
            l_csv.setDataFromUploadTemp(10L);
            
            assertEquals(10L, l_csv.getAdministratorUploadId());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
