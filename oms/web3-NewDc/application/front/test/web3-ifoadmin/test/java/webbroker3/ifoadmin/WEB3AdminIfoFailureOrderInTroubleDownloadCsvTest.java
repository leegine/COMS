head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoFailureOrderInTroubleDownloadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : 先物OP市場送信グレー注文ダウンロードCSV(WEB3AdminIfoGrayOrderDownloadCsv.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/08/03 劉レイ(北京中訊) 新規作成
 */
package webbroker3.ifoadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.data.VirtualServerInformationParams;
import webbroker3.gentrade.data.VirtualServerInformationRow;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifoadmin.data.IfoCsvFileFormatParams;
import webbroker3.ifoadmin.data.IfoCsvFileFormatRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoFailureOrderInTroubleDownloadCsvTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoFailureOrderInTroubleDownloadCsvTest.class);
    
    private String[] CsvColumnModel =
        {"市場", "銘柄コード", "注文種別", "注文識別", "売/買", "元注文値段", "値段", 
        "数量", "執条", "信用", "空売", "自/委", "安/裁", "数条", "数約",
        "ｻﾎﾟｰﾄﾒﾝﾊﾞｰ", "任意設定項目", "社内処理番号", "取引ID", "ストップ銘柄",
        "トリガー値段", "ストップ条件", "残注文数量", "被管理サブ参加者コード",
        "被管理ユーザID", "管理サブ参加者コード", "管理ユーザID"};
    
    private String CsvColumnModelString ="市場,銘柄コード,注文種別,注文識別," +
        "売/買,元注文値段,値段,数量,執条,信用,空売,自/委,安/裁,数条,数約," +
        "ｻﾎﾟｰﾄﾒﾝﾊﾞｰ,任意設定項目,社内処理番号,取引ID,ストップ銘柄,トリガー値段," +
        "ストップ条件,残注文数量,被管理サブ参加者コード,被管理ユーザID," +
        "管理サブ参加者コード,管理ユーザID";

    public WEB3AdminIfoFailureOrderInTroubleDownloadCsvTest(String name)
    {
        super(name);
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
     * 先物OPCSVファ@イルフォーマットテーブル
     */
    public IfoCsvFileFormatParams getIfoCsvFileFormatRow()
    {
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            new IfoCsvFileFormatParams();
        
        //証券会社コード
        l_ifoCsvFileFormatParams.setInstitutionCode("0D");
        
        //カラム番号
        l_ifoCsvFileFormatParams.setColumnNumber(0);
        
        //カラムラベル
        l_ifoCsvFileFormatParams.setColumnLabel("市場");
        
        //作成日付
        l_ifoCsvFileFormatParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //更新日付
        l_ifoCsvFileFormatParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_ifoCsvFileFormatParams;
    }

    /**
     * 仮想サーバ情報テーブル
     */
    public VirtualServerInformationParams getVirtualServerInformationRow()
    {
        VirtualServerInformationParams l_VirtualServerInformationParams =
            new VirtualServerInformationParams();
        
        //仮想サーバNo.（JSOES）
        l_VirtualServerInformationParams.setVirtualServerNumberJsoes("1111111");
        
        //証券会社コード
        l_VirtualServerInformationParams.setInstitutionCode("0D");
        
        //フロント発注取引所区分コード
        l_VirtualServerInformationParams.setFrontOrderExchangeCode("2");
        
        //フロント発注システム区分
        l_VirtualServerInformationParams.setFrontOrderSystemCode("2");
        
        //フロント発注取引区分コード
        l_VirtualServerInformationParams.setFrontOrderTradeCode("1");
        
        //仮想サーバNo.（市場）
        l_VirtualServerInformationParams.setVirtualServerNumberMarket("11111");
        
        //クライアントNo.
        l_VirtualServerInformationParams.setClientNumber("101");
        
        //通知ファ@イルNo.
        l_VirtualServerInformationParams.setNoticeFileNumber("201");
        
        //作成日付
        l_VirtualServerInformationParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //更新日付
        l_VirtualServerInformationParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //銘柄タイプ
        l_VirtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
        
        //ユーザーID（大証）
        l_VirtualServerInformationParams.setUserIdOsaka("33333");
        
        return l_VirtualServerInformationParams;
    }
    
    public void testWEB3AdminIfoFailureOrderInTroubleDownloadCsvCase0001()
    {
        final String STR_METHOD_NAME = "testWEB3AdminIfoFailureOrderInTroubleDownloadCsvCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        String[] l_strKeyHeaders = new String[1];
        String[] l_strColumnHeaders = new String[1];
        
        //件数
        Double l_count = new Double(20);
        
        //MAX件数
        Double l_maxCount = new Double(30);
        
        //社内処理項目ストリング
        String l_strCorpCodeString = null;
        
        l_strKeyHeaders[0] = "追加ダウンロードが必要な件数は0件です。";
        
        l_strColumnHeaders[0] = l_ifoCsvFileFormatParams.getColumnLabel();
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", l_count, l_maxCount, l_strCorpCodeString);
            
            assertEquals(l_strKeyHeaders[0], l_downloadCsv.getCsvFileLines()[0]);
            
            assertEquals(l_strColumnHeaders[0], l_downloadCsv.getCsvFileLines()[1]);
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testWEB3AdminIfoFailureOrderInTroubleDownloadCsvCase0002()
    {
        final String STR_METHOD_NAME = "testWEB3AdminIfoFailureOrderInTroubleDownloadCsvCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        String[] l_strKeyHeaders = new String[1];
        String[] l_strColumnHeaders = new String[1];
        
        //件数
        Double l_count = new Double(30);
        
        //MAX件数
        Double l_maxCount = new Double(20);
        
        //社内処理項目ストリング
        String l_strCorpCodeString = null;
        
        l_strKeyHeaders[0] = "追加ダウンロードが必要な件数は10件です。";
        
        l_strColumnHeaders[0] = l_ifoCsvFileFormatParams.getColumnLabel();
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", l_count, l_maxCount, l_strCorpCodeString);
            
            assertEquals(l_strKeyHeaders[0], l_downloadCsv.getCsvFileLines()[0]);
            
            assertEquals(l_strColumnHeaders[0], l_downloadCsv.getCsvFileLines()[1]);
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testWEB3AdminIfoFailureOrderInTroubleDownloadCsvCase0003()
    {
        final String STR_METHOD_NAME = "testWEB3AdminIfoFailureOrderInTroubleDownloadCsvCase0003()";
        log.entering(STR_METHOD_NAME);

        String[] l_strKeyHeaders = new String[1];
        
        //件数
        Double l_count = new Double(30);
        
        //MAX件数
        Double l_maxCount = new Double(20);
        
        //社内処理項目ストリング
        String l_strCorpCodeString = "社内処理項目";
        
        l_strKeyHeaders[0] = "追加ダウンロードが必要な件数は10件です。以下の注文は、市場に既に発注されている可能性があります。"
            + "市場端末より市場にて有効であるかを確認し、無効の場合のみ再発注を行ってください。"
            + "社内処理項目："
            + l_strCorpCodeString;
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            for (int i = 0; i < this.CsvColumnModel.length; i++)
            {
                IfoCsvFileFormatParams l_ifoCsvFileFormatParams = new IfoCsvFileFormatParams();
                
                l_ifoCsvFileFormatParams = this.getIfoCsvFileFormatRow();
                l_ifoCsvFileFormatParams.setColumnNumber(i);
                l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[i]);
                
                TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            }
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", l_count, l_maxCount, l_strCorpCodeString);
            
            assertEquals(l_strKeyHeaders[0], l_downloadCsv.getCsvFileLines()[0]);
            
            assertEquals(this.CsvColumnModelString, l_downloadCsv.getCsvFileLines()[1]);
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetMarketCodeCase0001()
    {
        final String STR_METHOD_NAME = "testSetMarketCodeCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //1：東京
        l_hostFotypeOrderAllParams.setSonarMarketCode("1");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow};
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setMarketCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("1", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetMarketCodeCase0002()
    {
        final String STR_METHOD_NAME = "testSetMarketCodeCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //2：大阪
        l_hostFotypeOrderAllParams.setSonarMarketCode("2");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow};
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setMarketCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("2", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetMarketCodeCase0003()
    {
        final String STR_METHOD_NAME = "testSetMarketCodeCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //3：名古屋
        l_hostFotypeOrderAllParams.setSonarMarketCode("3");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow};
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setMarketCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("3", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetMarketCodeCase0004()
    {
        final String STR_METHOD_NAME = "testSetMarketCodeCase0004()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //5：NNM
        l_hostFotypeOrderAllParams.setSonarMarketCode("5");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow};
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setMarketCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("5", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetMarketCodeCase0005()
    {
        final String STR_METHOD_NAME = "testSetMarketCodeCase0005()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //4：札幌
        l_hostFotypeOrderAllParams.setSonarMarketCode("4");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow};
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setMarketCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("8", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetMarketCodeCase0006()
    {
        final String STR_METHOD_NAME = "testSetMarketCodeCase0006()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //9：福岡
        l_hostFotypeOrderAllParams.setSonarMarketCode("9");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow};
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setMarketCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("6", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetMarketCodeCase0007()
    {
        final String STR_METHOD_NAME = "testSetMarketCodeCase0007()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //6：JASDAQ
        l_hostFotypeOrderAllParams.setSonarMarketCode("6");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow};
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setMarketCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("9", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetMarketCodeCase0008()
    {
        final String STR_METHOD_NAME = "testSetMarketCodeCase0008()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //x
        l_hostFotypeOrderAllParams.setSonarMarketCode("x");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow};
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setMarketCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals(" ", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetProductCodeCase0001()
    {
        final String STR_METHOD_NAME = "testSetProductCodeCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[1]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //銘柄コード
        l_hostFotypeOrderAllParams.setProductCode("100001");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow};
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setProductCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("100001", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetOrderTypeCase0001()
    {
        final String STR_METHOD_NAME = "testSetOrderTypeCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[2]);

        //注文種別が「1:新規」の場合
        Object[] l_object = new Object[]{ new Integer(0), new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setOrderType",
                    new Class[] { int.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("112", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetOrderTypeCase0002()
    {
        final String STR_METHOD_NAME = "testSetOrderTypeCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[2]);

        //注文種別が「2:訂正」の場合
        Object[] l_object = new Object[]{ new Integer(0), new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setOrderType",
                    new Class[] { int.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("131", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetOrderTypeCase0003()
    {
        final String STR_METHOD_NAME = "testSetOrderTypeCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[2]);
        
        //注文種別が「3:取消」の場合
        Object[] l_object = new Object[]{ new Integer(0), new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setOrderType",
                    new Class[] { int.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("121", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testSetAcceptNumberCase0001()
    {
        final String STR_METHOD_NAME = "testSetAcceptNumberCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[3]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //注文受付番号
        l_hostFotypeOrderAllParams.setAcceptNumber("100001");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setAcceptNumber",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetAcceptNumberCase0002()
    {
        final String STR_METHOD_NAME = "testSetAcceptNumberCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[3]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //注文受付番号
        l_hostFotypeOrderAllParams.setAcceptNumber("100001");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「2:訂正」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setAcceptNumber",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("100001", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetAcceptNumberCase0003()
    {
        final String STR_METHOD_NAME = "testSetAcceptNumberCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[3]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //注文受付番号
        l_hostFotypeOrderAllParams.setAcceptNumber("100002");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「3:取消」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setAcceptNumber",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("100002", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetSellBuyCase0001()
    {
        final String STR_METHOD_NAME = "testSetSellBuyCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[4]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //売買区分
        l_hostFotypeOrderAllParams.setBuySellDiv("1");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setSellBuy",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("1", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetSellBuyCase0002()
    {
        final String STR_METHOD_NAME = "testSetSellBuyCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[4]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //売買区分
        l_hostFotypeOrderAllParams.setBuySellDiv("2");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setSellBuy",
                    new Class[] { int.class, HostFotypeOrderAllRow.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("3", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetOrgOrderPriceRangeCase0001()
    {
        final String STR_METHOD_NAME = "testSetOrgOrderPriceRangeCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[5]);
        
        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setOrgOrderPriceRange",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetPriceRangeCase0001()
    {
        final String STR_METHOD_NAME = "testSetPriceRangeCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[6]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //指値
        l_hostFotypeOrderAllParams.setLimitPrice(20);
        
        //訂正指値
        l_hostFotypeOrderAllParams.setChangeLimitPrice(30);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setPriceRange",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("20", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetPriceRangeCase0002()
    {
        final String STR_METHOD_NAME = "testSetPriceRangeCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[6]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //指値
        l_hostFotypeOrderAllParams.setLimitPrice(20);
        
        //訂正指値
        l_hostFotypeOrderAllParams.setChangeLimitPrice(0);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「2:訂正」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setPriceRange",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetPriceRangeCase0003()
    {
        final String STR_METHOD_NAME = "testSetPriceRangeCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[6]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //指値
        l_hostFotypeOrderAllParams.setLimitPrice(20);
        
        //訂正指値
        l_hostFotypeOrderAllParams.setChangeLimitPrice(30);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「2:訂正」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setPriceRange",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("30", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetPriceRangeCase0004()
    {
        final String STR_METHOD_NAME = "testSetPriceRangeCase0004()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[6]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //指値
        l_hostFotypeOrderAllParams.setLimitPrice(20);
        
        //訂正指値
        l_hostFotypeOrderAllParams.setChangeLimitPrice(30);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「3:取消」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setPriceRange",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetQuantityCase0001()
    {
        final String STR_METHOD_NAME = "testSetQuantityCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[7]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //売買区分が「1:売（新規売建、買建売返済）」
        l_hostFotypeOrderAllParams.setBuySellDiv("1");
        
        //売付数量
        l_hostFotypeOrderAllParams.setSellOrderQuantity(500);
        
        //買付数量
        l_hostFotypeOrderAllParams.setBuyOrderQuantity(400);
        
        //訂正数量
        l_hostFotypeOrderAllParams.setChangeQuantity(200);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setQuantity",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("500", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetQuantityCase0002()
    {
        final String STR_METHOD_NAME = "testSetQuantityCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[7]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //売買区分が「2:買（新規買建、売建買返済）」
        l_hostFotypeOrderAllParams.setBuySellDiv("2");
        
        //売付数量
        l_hostFotypeOrderAllParams.setSellOrderQuantity(500);
        
        //買付数量
        l_hostFotypeOrderAllParams.setBuyOrderQuantity(400);
        
        //訂正数量
        l_hostFotypeOrderAllParams.setChangeQuantity(200);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setQuantity",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("400", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetQuantityCase0003()
    {
        final String STR_METHOD_NAME = "testSetQuantityCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[7]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //売買区分が「2:買（新規買建、売建買返済）」
        l_hostFotypeOrderAllParams.setBuySellDiv("2");
        
        //売付数量
        l_hostFotypeOrderAllParams.setSellOrderQuantity(500);
        
        //買付数量
        l_hostFotypeOrderAllParams.setBuyOrderQuantity(400);
        
        //訂正数量
        l_hostFotypeOrderAllParams.setChangeQuantity(0);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「2:訂正」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setQuantity",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("0", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetQuantityCase0004()
    {
        final String STR_METHOD_NAME = "testSetQuantityCase0004()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[7]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //売買区分が「1:売（新規売建、買建売返済）」
        l_hostFotypeOrderAllParams.setBuySellDiv("1");
        
        //売付数量
        l_hostFotypeOrderAllParams.setSellOrderQuantity(500);
        
        //買付数量
        l_hostFotypeOrderAllParams.setBuyOrderQuantity(400);
        
        //訂正数量
        l_hostFotypeOrderAllParams.setChangeQuantity(200);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「2:訂正」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setQuantity",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("300", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetQuantityCase0005()
    {
        final String STR_METHOD_NAME = "testSetQuantityCase0005()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[7]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //売買区分が「2:買（新規買建、売建買返済）」
        l_hostFotypeOrderAllParams.setBuySellDiv("2");
        
        //売付数量
        l_hostFotypeOrderAllParams.setSellOrderQuantity(500);
        
        //買付数量
        l_hostFotypeOrderAllParams.setBuyOrderQuantity(400);
        
        //訂正数量
        l_hostFotypeOrderAllParams.setChangeQuantity(200);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「2:訂正」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setQuantity",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("200", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetQuantityCase0006()
    {
        final String STR_METHOD_NAME = "testSetQuantityCase0006()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[7]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //売買区分が「2:買（新規買建、売建買返済）」
        l_hostFotypeOrderAllParams.setBuySellDiv("2");
        
        //売付数量
        l_hostFotypeOrderAllParams.setSellOrderQuantity(500);
        
        //買付数量
        l_hostFotypeOrderAllParams.setBuyOrderQuantity(400);
        
        //訂正数量
        l_hostFotypeOrderAllParams.setChangeQuantity(200);
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「3:取消」の場合
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setQuantity",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetExecutionConditionTypeCase0001()
    {
        final String STR_METHOD_NAME = "testSetExecutionConditionTypeCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[8]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setExecutionConditionType",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetMarginCase0001()
    {
        final String STR_METHOD_NAME = "testSetMarginCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[9]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setMargin",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetShortSellCase0001()
    {
        final String STR_METHOD_NAME = "testSetShortSellCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[10]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setShortSell",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetTradeauditCase0001()
    {
        final String STR_METHOD_NAME = "testSetTradeauditCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[11]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //自己委託区分
        l_hostFotypeOrderAllParams.setTradeauditCode("0");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setTradeaudit",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("0", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetTradeauditCase0002()
    {
        final String STR_METHOD_NAME = "testSetTradeauditCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[11]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //自己委託区分
        l_hostFotypeOrderAllParams.setTradeauditCode("9");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「2:訂正」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setTradeaudit",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetTradeauditCase0003()
    {
        final String STR_METHOD_NAME = "testSetTradeauditCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[11]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //自己委託区分
        l_hostFotypeOrderAllParams.setTradeauditCode("9");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「3:取消」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setTradeaudit",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetAnnSaiCase0001()
    {
        final String STR_METHOD_NAME = "testSetAnnSaiCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[12]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setAnnSai",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetExecutionConditionCase0001()
    {
        final String STR_METHOD_NAME = "testSetExecutionConditionCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[13]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //執行条件
        l_hostFotypeOrderAllParams.setExecutionCondition("A");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setExecutionCondition",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("G", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetExecutionConditionCase0002()
    {
        final String STR_METHOD_NAME = "testSetExecutionConditionCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[13]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //執行条件
        l_hostFotypeOrderAllParams.setExecutionCondition("E");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setExecutionCondition",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("O", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetExecutionConditionCase0003()
    {
        final String STR_METHOD_NAME = "testSetExecutionConditionCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[13]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //執行条件
        l_hostFotypeOrderAllParams.setExecutionCondition("D");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setExecutionCondition",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("A", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetExecutionConditionCase0004()
    {
        final String STR_METHOD_NAME = "testSetExecutionConditionCase0004()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[13]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //執行条件
        l_hostFotypeOrderAllParams.setExecutionCondition("B");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setExecutionCondition",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetExecutionConditionCase0005()
    {
        final String STR_METHOD_NAME = "testSetExecutionConditionCase0005()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[13]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //執行条件
        l_hostFotypeOrderAllParams.setExecutionCondition("A");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「2:訂正」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setExecutionCondition",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetExecutionConditionCase0006()
    {
        final String STR_METHOD_NAME = "testSetExecutionConditionCase0006()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[13]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //執行条件
        l_hostFotypeOrderAllParams.setExecutionCondition("A");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「3:取消」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setExecutionCondition",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetExecutedQuantityCase0001()
    {
        final String STR_METHOD_NAME = "testSetExecutedQuantityCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[14]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setExecutedQuantity",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetSupportMemberCase0001()
    {
        final String STR_METHOD_NAME = "testSetSupportMemberCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[15]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setSupportMember",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetArbitrarySetItemCase0001()
    {
        final String STR_METHOD_NAME = "testSetArbitrarySetItemCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[16]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setArbitrarySetItem",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetCorpCodeCase0001()
    {
        final String STR_METHOD_NAME = "testSetCorpCodeCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[17]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //社内処理項目
        l_hostFotypeOrderAllParams.setCorpCode("100001");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setCorpCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("100001", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetCorpCodeCase0002()
    {
        final String STR_METHOD_NAME = "testSetCorpCodeCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[17]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //社内処理項目
        l_hostFotypeOrderAllParams.setCorpCode("100002");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「2:訂正」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setCorpCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("100002", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetCorpCodeCase0003()
    {
        final String STR_METHOD_NAME = "testSetCorpCodeCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[17]);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
            TestDBUtility.getHostFotypeOrderAllRow();
        
        //社内処理項目
        l_hostFotypeOrderAllParams.setCorpCode("100001");
        
        HostFotypeOrderAllRow l_hostFotypeOrderAllRow = l_hostFotypeOrderAllParams;
        
        //注文種別が「3:取消」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_hostFotypeOrderAllRow, new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setCorpCode",
                    new Class[] { int.class, HostFotypeOrderAllRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetExClientCase0001()
    {
        final String STR_METHOD_NAME = "testSetExClientCase0001()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[18]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setExClient",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetStopProductCase0001()
    {
        final String STR_METHOD_NAME = "testSetStopProductCase0001()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[19]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setStopProduct",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetTriggerPriceRangeCase0001()
    {
        final String STR_METHOD_NAME = "testSetTriggerPriceRangeCase0001()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[20]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setTriggerPriceRange",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetStopConditionCase0001()
    {
        final String STR_METHOD_NAME = "testSetStopConditionCase0001()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[21]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setStopCondition",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetBalanceQuantityCase0001()
    {
        final String STR_METHOD_NAME = "testSetBalanceQuantityCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[22]);
        
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{ new Integer(0), new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setBalanceQuantity",
                    new Class[] { int.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetBalanceQuantityCase0002()
    {
        final String STR_METHOD_NAME = "testSetBalanceQuantityCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[22]);
        
        //注文種別が「2:訂正」の場合、
        Object[] l_object = new Object[]{ new Integer(0), new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setBalanceQuantity",
                    new Class[] { int.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("0", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetBalanceQuantityCase0003()
    {
        final String STR_METHOD_NAME = "testSetBalanceQuantityCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[22]);
        
        //注文種別が「3:取消」の場合、
        Object[] l_object = new Object[]{ new Integer(0), new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setBalanceQuantity",
                    new Class[] { int.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManagedSubParticipantCodeCase0001()
    {
        final String STR_METHOD_NAME = "testManagedSubParticipantCodeCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[23]);
        
        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //仮想サーバNo.（市場）
        l_virtualServerInformationParams.setVirtualServerNumberMarket("1111111");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_virtualServerInformationRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManagedSubParticipantCode",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testManagedSubParticipantCodeCase0002()
    {
        final String STR_METHOD_NAME = "testManagedSubParticipantCodeCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[23]);
        
        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //仮想サーバNo.（市場）
        l_virtualServerInformationParams.setVirtualServerNumberMarket("1111111");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「2:訂正」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_virtualServerInformationRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManagedSubParticipantCode",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
 
    public void testManagedSubParticipantCodeCase0003()
    {
        final String STR_METHOD_NAME = "testManagedSubParticipantCodeCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[23]);
        
        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //仮想サーバNo.（市場）
        l_virtualServerInformationParams.setVirtualServerNumberMarket("2222222");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「3:取消」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_virtualServerInformationRow, new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManagedSubParticipantCode",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("2222222", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManagedUserIdCase0001()
    {
        final String STR_METHOD_NAME = "testManagedUserIdCase0001()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[24]);
        
        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //ユーザーID（大証）
        l_virtualServerInformationParams.setUserIdOsaka("11111");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_virtualServerInformationRow, new String("1") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManagedUserId",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManagedUserIdCase0002()
    {
        final String STR_METHOD_NAME = "testManagedUserIdCase0002()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[24]);
        
        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //ユーザーID（大証）
        l_virtualServerInformationParams.setUserIdOsaka("11111");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「2:訂正」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_virtualServerInformationRow, new String("2") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManagedUserId",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManagedUserIdCase0003()
    {
        final String STR_METHOD_NAME = "testManagedUserIdCase0003()";
        log.entering(STR_METHOD_NAME);
       
        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[24]);
        
        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //ユーザーID（大証）
        l_virtualServerInformationParams.setUserIdOsaka("22222");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「3:取消」の場合、
        Object[] l_object = new Object[]{ new Integer(0), l_virtualServerInformationRow, new String("3") };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManagedUserId",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("22222", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManageSubParticipantCodeCase0001()
    {
        final String STR_METHOD_NAME = "testManageSubParticipantCodeCase0001()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[25]);

        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //サブ参加者コード
//        l_virtualServerInformationParams.setSubParticipantCode("11111");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「1:新規」の場合、
        Object[] l_object = new Object[]{
            new Integer(0),
            l_virtualServerInformationRow,
            new String("1"),
            new String("1")//管理ユーザ利用が「1：利用する」の場合
            };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManageSubParticipantCode",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class, String.class});
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManageSubParticipantCodeCase0002()
    {
        final String STR_METHOD_NAME = "testManageSubParticipantCodeCase0002()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[25]);

        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //サブ参加者コード
        l_virtualServerInformationParams.setVirtualServerNumberMarket("12345");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「2:訂正」の場合、
        Object[] l_object = new Object[]{
            new Integer(0),
            l_virtualServerInformationRow,
            new String("2"),
            new String("1")//管理ユーザ利用が「1：利用する」の場合
            };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManageSubParticipantCode",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class, String.class});
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("12345", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManageSubParticipantCodeCase0003()
    {
        final String STR_METHOD_NAME = "testManageSubParticipantCodeCase0003()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[25]);

        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //サブ参加者コード
//        l_virtualServerInformationParams.setSubParticipantCode("11111");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「2:訂正」の場合、
        Object[] l_object = new Object[]{
            new Integer(0),
            l_virtualServerInformationRow,
            new String("2"),
            new String("0")//管理ユーザ利用が「0：利用しない」の場合
            };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManageSubParticipantCode",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class, String.class});
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManageSubParticipantCodeCase0004()
    {
        final String STR_METHOD_NAME = "testManageSubParticipantCodeCase0004()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[25]);

        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //サブ参加者コード
//        l_virtualServerInformationParams.setSubParticipantCode("11111");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「3:取消」の場合、
        Object[] l_object = new Object[]{
            new Integer(0),
            l_virtualServerInformationRow,
            new String("3"),
            new String("1")//管理ユーザ利用が「1：利用する」の場合
            };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManageSubParticipantCode",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class, String.class});
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("11111", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManageSubParticipantCodeCase0005()
    {
        final String STR_METHOD_NAME = "testManageSubParticipantCodeCase0005()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[25]);

        VirtualServerInformationParams l_virtualServerInformationParams =
            this.getVirtualServerInformationRow();
        
        //サブ参加者コード
//        l_virtualServerInformationParams.setSubParticipantCode("11111");
        
        VirtualServerInformationRow l_virtualServerInformationRow =
            l_virtualServerInformationParams;
                
        //注文種別が「3:取消」の場合、
        Object[] l_object = new Object[]{
            new Integer(0),
            l_virtualServerInformationRow,
            new String("3"),
            new String("0")//管理ユーザ利用が「0：利用しない」の場合
            };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManageSubParticipantCode",
                    new Class[] { int.class, VirtualServerInformationRow.class, String.class, String.class});
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testManageUserIdCase0001()
    {
        final String STR_METHOD_NAME = "testManageUserIdCase0001()";
        log.entering(STR_METHOD_NAME);

        IfoCsvFileFormatParams l_ifoCsvFileFormatParams =
            this.getIfoCsvFileFormatRow();
        l_ifoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[26]);

        Object[] l_object = new Object[]{ new Integer(0) };
        
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoCsvFileFormatParams);
            
            WEB3AdminIfoFailureOrderInTroubleDownloadCsv l_downloadCsv =
                new WEB3AdminIfoFailureOrderInTroubleDownloadCsv("0D", new Double(10), new Double(20), null);
            
            l_downloadCsv.addRow();
            
            WEB3GentradeCsvColumnModel l_csvColumnModel =
                l_downloadCsv.getColumnModel(l_ifoCsvFileFormatParams.getColumnLabel());
            
            Method l_method =
                l_downloadCsv.getClass().getDeclaredMethod(
                    "setManageUserId",
                    new Class[] { int.class });
            
            l_method.setAccessible(true);
            
            l_method.invoke(l_downloadCsv, l_object);
            
            assertEquals("", l_downloadCsv.getStringValue(0, l_csvColumnModel));
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
