head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.42.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSVのテストクラス(WEB3AdminSrvRegiOtherOrgIdDownloadCsvTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/20 周墨洋 (中訊) 新規作成
*/
package webbroker3.srvregi;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSVのテストクラス)<BR>
 * 外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSVのテストクラス<BR>
 * <BR>
 * @@author 周墨洋<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadCsvTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdDownloadCsvTest.class);

    /**
     * 外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV
     */
    private WEB3AdminSrvRegiOtherOrgIdDownloadCsv adminSrvRegiOtherOrgIdDownloadCsv;

    /**
     * @@param arg0
     * arg0
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadCsvTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    public void testSetParameterCase0001()
    {
        final String STR_METHOD_NAME = "testSetParameterCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.adminSrvRegiOtherOrgIdDownloadCsv =
                new WEB3AdminSrvRegiOtherOrgIdDownloadCsv();

            this.adminSrvRegiOtherOrgIdDownloadCsv.addRow("123,900,9,0D,381,251224,20070320,20070320");

            int l_intExpectedColumnNumber = 0;
            WEB3GentradeCsvColumnModel l_gentradeCsvColumnMode0 =
                this.adminSrvRegiOtherOrgIdDownloadCsv.getColumnModel(
                    WEB3AdminSrvRegiOtherOrgIdDownloadCsv.SEQUENCE_NUMBER_LABEL);
            assertEquals(l_intExpectedColumnNumber, l_gentradeCsvColumnMode0.getColumnNumber());

            l_intExpectedColumnNumber = ++l_intExpectedColumnNumber;
            WEB3GentradeCsvColumnModel l_gentradeCsvColumnMode1 =
                this.adminSrvRegiOtherOrgIdDownloadCsv.getColumnModel(
                    WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ID_LABEL);
            assertEquals(l_intExpectedColumnNumber, l_gentradeCsvColumnMode1.getColumnNumber());

            l_intExpectedColumnNumber = ++l_intExpectedColumnNumber;
            WEB3GentradeCsvColumnModel l_gentradeCsvColumnMode2 =
                this.adminSrvRegiOtherOrgIdDownloadCsv.getColumnModel(
                    WEB3AdminSrvRegiOtherOrgIdDownloadCsv.STATUS_LABEL);
            assertEquals(l_intExpectedColumnNumber, l_gentradeCsvColumnMode2.getColumnNumber());

            l_intExpectedColumnNumber = ++l_intExpectedColumnNumber;
            WEB3GentradeCsvColumnModel l_gentradeCsvColumnMode3 =
                this.adminSrvRegiOtherOrgIdDownloadCsv.getColumnModel(
                    WEB3AdminSrvRegiOtherOrgIdDownloadCsv.INSTITUTION_CODE_LABEL);
            assertEquals(l_intExpectedColumnNumber, l_gentradeCsvColumnMode3.getColumnNumber());

            l_intExpectedColumnNumber = ++l_intExpectedColumnNumber;
            WEB3GentradeCsvColumnModel l_gentradeCsvColumnMode4 =
                this.adminSrvRegiOtherOrgIdDownloadCsv.getColumnModel(
                    WEB3AdminSrvRegiOtherOrgIdDownloadCsv.BRANCH_CODE_LABEL);
            assertEquals(l_intExpectedColumnNumber, l_gentradeCsvColumnMode4.getColumnNumber());

            l_intExpectedColumnNumber = ++l_intExpectedColumnNumber;
            WEB3GentradeCsvColumnModel l_gentradeCsvColumnMode5 =
                this.adminSrvRegiOtherOrgIdDownloadCsv.getColumnModel(
                    WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ACCOUNT_CODE_LABEL);
            assertEquals(l_intExpectedColumnNumber, l_gentradeCsvColumnMode5.getColumnNumber());

            l_intExpectedColumnNumber = ++l_intExpectedColumnNumber;
            WEB3GentradeCsvColumnModel l_gentradeCsvColumnMode6 =
                this.adminSrvRegiOtherOrgIdDownloadCsv.getColumnModel(
                    WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_START_DATE_LABEL);
            assertEquals(l_intExpectedColumnNumber, l_gentradeCsvColumnMode6.getColumnNumber());

            l_intExpectedColumnNumber = ++l_intExpectedColumnNumber;
            WEB3GentradeCsvColumnModel l_gentradeCsvColumnMode7 =
                this.adminSrvRegiOtherOrgIdDownloadCsv.getColumnModel(
                    WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_END_DATE_LABEL);
            assertEquals(l_intExpectedColumnNumber, l_gentradeCsvColumnMode7.getColumnNumber());

            assertEquals(1, this.adminSrvRegiOtherOrgIdDownloadCsv.getRowCount());

            assertEquals(
                3,
                this.adminSrvRegiOtherOrgIdDownloadCsv.getCsvFileLines().length);
            Timestamp l_tsSysDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            String l_strCurrentTime = WEB3DateUtility.formatDate(l_tsSysDate, "yyyy/MM/dd HH:mm:ss");
            assertEquals(
                l_strCurrentTime,
                this.adminSrvRegiOtherOrgIdDownloadCsv.getCsvFileLines()[0]);
            assertEquals(
                "通番,ID,ステータス,証券会社コード,部店コード,口座コード,適用期間From,適用期間To",
                this.adminSrvRegiOtherOrgIdDownloadCsv.getCsvFileLines()[1]);
            assertEquals(
                "123,900,9,0D,381,251224,20070320,20070320",
                this.adminSrvRegiOtherOrgIdDownloadCsv.getCsvFileLines()[2]);
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME, l_exE);
            fail();
        }
        finally
        {
            log.entering(TEST_END + STR_METHOD_NAME);
        }
    }

}
@
