head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderDLCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 姜丹(中訊) 仕様変更モデルNo.211
*/
package webbroker3.eqtypeadmin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleOrderDLCsvTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderDLCsvTest.class);
    
    public WEB3AdminEquityForcedSettleOrderDLCsvTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        
        
        
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        
    }

    /**
     * createキーヘッダ 
     *
     */
    public void test_createKeyHeader_0001()
    {
        final String STR_METHOD_NAME = "test_createKeyHeader_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        String[] l_strKeyHeader = l_csv.getKeyHeader();
        assertEquals("1", "" + l_strKeyHeader.length);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * createカラムヘッダ
     *
     */
    public void test_createColumnHeader_0001()
    {
        final String STR_METHOD_NAME = "test_createColumnHeader_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        WEB3GentradeCsvColumnModel[] l_columnModel = l_csv.getColumnHeader();
        
        //部店コード
        //項目ラベル
        assertEquals("部店", "" + l_columnModel[0].getColumnLabel());
        //カラム番号
        assertEquals("0", "" + l_columnModel[0].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[0].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[0].getDateFormat());
        
        //顧客コード 
        //項目ラベル
        assertEquals("顧客", "" + l_columnModel[1].getColumnLabel());
        //カラム番号
        assertEquals("1", "" + l_columnModel[1].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[1].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[1].getDateFormat());
        
        //顧客名
        //項目ラベル
        assertEquals("顧客名", "" + l_columnModel[2].getColumnLabel());
        //カラム番号
        assertEquals("2", "" + l_columnModel[2].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[2].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[2].getDateFormat());
        
        //強制決済理由
        //項目ラベル
        assertEquals("強制決済理由", "" + l_columnModel[3].getColumnLabel());
        //カラム番号
        assertEquals("3", "" + l_columnModel[3].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[3].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[3].getDateFormat());
        
        //市場名
        //項目ラベル
        assertEquals("市場", "" + l_columnModel[4].getColumnLabel());
        //カラム番号
        assertEquals("4", "" + l_columnModel[4].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[4].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[4].getDateFormat());
        
        //銘柄コード
        //項目ラベル
        assertEquals("銘柄", "" + l_columnModel[5].getColumnLabel());
        //カラム番号
        assertEquals("5", "" + l_columnModel[5].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[5].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[5].getDateFormat());
        
        //銘柄名
        //項目ラベル
        assertEquals("銘柄名", "" + l_columnModel[6].getColumnLabel());
        //カラム番号
        assertEquals("6", "" + l_columnModel[6].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[6].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[6].getDateFormat());
        
        //税区分
        //項目ラベル
        assertEquals("口座", "" + l_columnModel[7].getColumnLabel());
        //カラム番号
        assertEquals("7", "" + l_columnModel[7].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[7].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[7].getDateFormat());
        
        //建区分
        //項目ラベル
        assertEquals("建区分", "" + l_columnModel[8].getColumnLabel());
        //カラム番号
        assertEquals("8", "" + l_columnModel[8].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[8].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[8].getDateFormat());
        
        //弁済区分
        //項目ラベル
        assertEquals("弁済", "" + l_columnModel[9].getColumnLabel());
        //カラム番号
        assertEquals("9", "" + l_columnModel[9].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[9].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[9].getDateFormat());
        
        //建日
        //項目ラベル
        assertEquals("建日", "" + l_columnModel[10].getColumnLabel());
        //カラム番号
        assertEquals("10", "" + l_columnModel[10].getColumnNumber());
        //項目型
        assertEquals("21", "" + l_columnModel[10].getColumnType());
        //日付フォーマット
        assertEquals("yyyy/M/d", ((SimpleDateFormat)l_columnModel[10].getDateFormat()).toPattern());
        
        //決済期日
        //項目ラベル
        assertEquals("決済期日", "" + l_columnModel[11].getColumnLabel());
        //カラム番号
        assertEquals("11", "" + l_columnModel[11].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[11].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[11].getDateFormat());
        
        //建株数
        //項目ラベル
        assertEquals("建株数", "" + l_columnModel[12].getColumnLabel());
        //カラム番号
        assertEquals("12", "" + l_columnModel[12].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[12].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[12].getDateFormat());
        
        //建単価
        //項目ラベル
        assertEquals("建単価", "" + l_columnModel[13].getColumnLabel());
        //カラム番号
        assertEquals("13", "" + l_columnModel[13].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[13].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[13].getDateFormat());
        
        //建代金
        //項目ラベル
        assertEquals("建代金", "" + l_columnModel[14].getColumnLabel());
        //カラム番号
        assertEquals("14", "" + l_columnModel[14].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[14].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[14].getDateFormat());
        
        //保証金率
        //項目ラベル
        assertEquals("保証金率 (%)", "" + l_columnModel[15].getColumnLabel());
        //カラム番号
        assertEquals("15", "" + l_columnModel[15].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[15].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[15].getDateFormat());
        
        //追証発生日
        //項目ラベル
        assertEquals("追証発生日", "" + l_columnModel[16].getColumnLabel());
        //カラム番号
        assertEquals("16", "" + l_columnModel[16].getColumnNumber());
        //項目型
        assertEquals("21", "" + l_columnModel[16].getColumnType());
        //日付フォーマット
        assertEquals("yyyy/M/d", ((SimpleDateFormat)l_columnModel[16].getDateFormat()).toPattern());
        
        //経過日数
        //項目ラベル
        assertEquals("経過日数(日)", "" + l_columnModel[17].getColumnLabel());
        //カラム番号
        assertEquals("17", "" + l_columnModel[17].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[17].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[17].getDateFormat());
        
        //作成日時
        //項目ラベル
        assertEquals("作成日時", "" + l_columnModel[18].getColumnLabel());
        //カラム番号
        assertEquals("18", "" + l_columnModel[18].getColumnNumber());
        //項目型
        assertEquals("21", "" + l_columnModel[18].getColumnType());
        //日付フォーマット
        assertEquals("yyyy/M/d H:mm", ((SimpleDateFormat)l_columnModel[18].getDateFormat()).toPattern());
        
        //処理日時
        //項目ラベル
        assertEquals("処理日時", "" + l_columnModel[19].getColumnLabel());
        //カラム番号
        assertEquals("19", "" + l_columnModel[19].getColumnNumber());
        //項目型
        assertEquals("21", "" + l_columnModel[19].getColumnType());
        //日付フォーマット
        assertEquals("yyyy/M/d H:mm", ((SimpleDateFormat)l_columnModel[19].getDateFormat()).toPattern());
        
        //承認状態
        //項目ラベル
        assertEquals("承認状態", "" + l_columnModel[20].getColumnLabel());
        //カラム番号
        assertEquals("20", "" + l_columnModel[20].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[20].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[20].getDateFormat());
        
        //承認者
        //項目ラベル
        assertEquals("承認者", "" + l_columnModel[21].getColumnLabel());
        //カラム番号
        assertEquals("21", "" + l_columnModel[21].getColumnNumber());
        //項目型
        assertEquals("0", "" + l_columnModel[21].getColumnType());
        //日付フォーマット
        assertNull(l_columnModel[21].getDateFormat());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set部店コード 
     *
     */
    public void test_setBranchCode_0001()
    {
        final String STR_METHOD_NAME = "test_setBranchCode_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_csv.setBranchCode(0, 33381);
            
            List list = (List)l_csv.l_rowValues.get(0);
            assertEquals("0", "" + ((Integer)list.get(0)).intValue());
            assertEquals("部店", "" + ((WEB3GentradeCsvColumnModel)list.get(1)).getColumnLabel());
            assertEquals("381", "" + (Object)list.get(2));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * set顧客  
     *
     */
    public void test_setAccount_0001()
    {
        final String STR_METHOD_NAME = "test_setAccount_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_csv.setAccount(1, "23", 333812512246L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("1", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("顧客", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("23", "" + (Object)list0.get(2));
            
            
            List list1 = (List)l_csv.l_rowValues.get(1);
            assertEquals("1", "" + ((Integer)list1.get(0)).intValue());
            assertEquals("顧客名", "" + ((WEB3GentradeCsvColumnModel)list1.get(1)).getColumnLabel());
            assertEquals("内藤　@四郎", "" + (Object)list1.get(2));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set顧客  
     * 引数.口座ＩＤに該当する顧客.get顧客表示名()
     * 取得できない場合は、nullをセットする。  
     */
    public void test_setAccount_0002()
    {
        final String STR_METHOD_NAME = "test_setAccount_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_csv.setAccount(1, "23", 333812512246L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("1", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("顧客", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("23", "" + (Object)list0.get(2));
            
            
            List list1 = (List)l_csv.l_rowValues.get(1);
            assertEquals("1", "" + ((Integer)list1.get(0)).intValue());
            assertEquals("顧客名", "" + ((WEB3GentradeCsvColumnModel)list1.get(1)).getColumnLabel());
            assertNull((Object)list1.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set強制決済理由 
     * 引数.強制決済理由区分＝"期日到来"の場合
     * "決済期日到来"
     */
    public void test_setForcedSettleReason_0001()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "0");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("強制決済理由", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("決済期日到来", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set強制決済理由 
     * 引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・軽度）"の場合
     * 30%割れ7日以上
     */
    public void test_setForcedSettleReason_0002()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "1");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("強制決済理由", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("30%割れ7日以上", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set強制決済理由 
     * 引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・重度）"の場合
     * 30%割れ7日以上
     */
    public void test_setForcedSettleReason_0003()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "2");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("強制決済理由", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("30%割れ7日以上", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set強制決済理由 
     * 引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・法@定）"の場合
     * 20%割れ期日超過
     */
    public void test_setForcedSettleReason_0004()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "4");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("強制決済理由", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("20%割れ期日超過", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set強制決済理由 
     * 引数.強制決済理由区分＝"保証金維持率割れ（場間）"の場合
     * 20%割れ期日超過
     */
    public void test_setForcedSettleReason_0005()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "3");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("強制決済理由", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("20%割れ期日超過", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set強制決済理由 
     * 引数.強制決済理由区分＝"手動強制決済"の場合
     * 手動強制決済
     */
    public void test_setForcedSettleReason_0006()
    {
        final String STR_METHOD_NAME = "test_setForcedSettleReason_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setForcedSettleReason(2, "9");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("2", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("強制決済理由", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("手動強制決済", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set市場名  
     *
     */
    public void test_setMarketName_0001()
    {
        final String STR_METHOD_NAME = "test_setMarketName_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            l_csv.setMarketName(3, 3303L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("3", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("市場", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("シンガポール", "" + (Object)list0.get(2));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set銘柄 
     *
     */
    public void test_setProduct_0001()
    {
        final String STR_METHOD_NAME = "test_setProduct_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setStandardName("111111111");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            l_csv.setProduct(4, "dadsadsa", 3304148080000L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("4", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("銘柄", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("dads", "" + (Object)list0.get(2));
            
            
            List list1 = (List)l_csv.l_rowValues.get(1);
            assertEquals("4", "" + ((Integer)list1.get(0)).intValue());
            assertEquals("銘柄名", "" + ((WEB3GentradeCsvColumnModel)list1.get(1)).getColumnLabel());
            assertEquals("111111111", "" + (Object)list1.get(2));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set銘柄 
     * 引数.銘柄IDに該当する株式銘柄.銘柄名
     * 取得できない場合は、nullをセットする。 
     */
    public void test_setProduct_0002()
    {
        final String STR_METHOD_NAME = "test_setProduct_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            
            l_csv.setProduct(4, "dadsadsa", 3304148080000L);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("4", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("銘柄", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("dads", "" + (Object)list0.get(2));
            
            
            List list1 = (List)l_csv.l_rowValues.get(1);
            assertEquals("4", "" + ((Integer)list1.get(0)).intValue());
            assertEquals("銘柄名", "" + ((WEB3GentradeCsvColumnModel)list1.get(1)).getColumnLabel());
            assertNull((Object)list1.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * set税区分 
     * 引数.税区分＝TaxTypeEnum."一般"の場合
     * "一般"
     */
    public void test_setTaxType_0001()
    {
        final String STR_METHOD_NAME = "test_setTaxType_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setTaxType(5, TaxTypeEnum.NORMAL);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("5", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("口座", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("一般", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set税区分 
     * 引数.税区分＝TaxTypeEnum."特定"の場合
     * "特定"
     */
    public void test_setTaxType_0002()
    {
        final String STR_METHOD_NAME = "test_setTaxType_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setTaxType(5, TaxTypeEnum.SPECIAL);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("5", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("口座", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("特定", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set税区分 
     * 引数.税区分＝TaxTypeEnum."特定口座かつ源泉徴収"の場合
     * "特定"
     */
    public void test_setTaxType_0003()
    {
        final String STR_METHOD_NAME = "test_setTaxType_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setTaxType(5, TaxTypeEnum.SPECIAL_WITHHOLD);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("5", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("口座", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("特定", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set税区分 
     * [以外の場合]
     * nullをセットする
     */
    public void test_setTaxType_0004()
    {
        final String STR_METHOD_NAME = "test_setTaxType_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setTaxType(5, TaxTypeEnum.STOCK_OPTION);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("5", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("口座", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertNull((Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set建区分 
     * 引数.建区分＝ContractTypeEnum."買建"の場合
     * "新規買"
     */
    public void test_setContractType_0001()
    {
        final String STR_METHOD_NAME = "test_setContractType_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractType(6, ContractTypeEnum.LONG);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("6", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("建区分", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("新規買", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set建区分 
     * 引数.建区分＝ContractTypeEnum."売建"の場合
     * "新規売"
     */
    public void test_setContractType_0002()
    {
        final String STR_METHOD_NAME = "test_setContractType_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractType(6, ContractTypeEnum.SHORT);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("6", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("建区分", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("新規売", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set弁済区分 
     * 引数.弁済区分＝"1：制度信用"の場合
     * "制度信用"
     */
    public void test_setRepaymentDiv_0001()
    {
        final String STR_METHOD_NAME = "test_setRepaymentDiv_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setRepaymentDiv(7, "1");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("7", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("弁済", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("制度信用", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set弁済区分 
     * 引数.弁済区分＝"2：一般信用"の場合
     * "一般信用"
     */
    public void test_setRepaymentDiv_0002()
    {
        final String STR_METHOD_NAME = "test_setRepaymentDiv_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setRepaymentDiv(7, "2");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("7", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("弁済", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("一般信用", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set建日 
     * 
     */
    public void test_setOpenDate_0001()
    {
        final String STR_METHOD_NAME = "test_setOpenDate_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setOpenDate(8, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("8", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("建日", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), 
                    (Date)list0.get(2)));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set決済期日 
     * 引数.決済期日が"9999/12/31"の場合
     * "無期"
     */
    public void test_setCloseDate_0001()
    {
        final String STR_METHOD_NAME = "test_setCloseDate_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setCloseDate(9, WEB3DateUtility.getDate("9999/12/31", "yyyy/MM/dd"));
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("9", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("決済期日", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("無期", "" + (Object)list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set決済期日 
     * 以外の場合
     * "yyyy/M/d"
     */
    public void test_setCloseDate_0002()
    {
        final String STR_METHOD_NAME = "test_setCloseDate_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setCloseDate(9, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("9", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("決済期日", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(),"yyyy/M/d"), (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set建株数 
     */
    public void test_setContractQuantity_0001()
    {
        final String STR_METHOD_NAME = "test_setContractQuantity_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractQuantity(10, "123");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("10", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("建株数", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("123", (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set建単価  
     */
    public void test_setContractPrice_0001()
    {
        final String STR_METHOD_NAME = "test_setContractPrice_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractPrice(11, "255");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("11", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("建単価", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("255", (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set建代金
     */
    public void test_setContractExecPrice_0001()
    {
        final String STR_METHOD_NAME = "test_setContractExecPrice_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setContractExecPrice(12, "12313");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("12", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("建代金", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("12313", (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set保証金率
     */
    public void test_setMarginDepositRate_0001()
    {
        final String STR_METHOD_NAME = "test_setMarginDepositRate_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setMarginDepositRate(13, "258");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("13", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("保証金率 (%)", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("258", (String)list0.get(2));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set追証発生日  
     * 
     */
    public void test_setAdditionalMarginDate_0001()
    {
        final String STR_METHOD_NAME = "test_setAdditionalMarginDate_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setAdditionalMarginDate(14, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("14", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("追証発生日", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), 
                    (Date)list0.get(2)));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set経過日数  
     * 引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・法@定）"の場合
     * "追証未入"
     */
    public void test_setMarginAccruedDays_0001()
    {
        final String STR_METHOD_NAME = "test_setMarginAccruedDays_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setMarginAccruedDays(15, "11232", "4");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("経過日数(日)", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("追証未入", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set経過日数  
     * 引数.強制決済理由区分＝"保証金維持率割れ（場間）"の場合
     * "追証未入"
     */
    public void test_setMarginAccruedDays_0002()
    {
        final String STR_METHOD_NAME = "test_setMarginAccruedDays_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setMarginAccruedDays(15, "11232", "3");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("経過日数(日)", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("追証未入", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set経過日数  
     * 以外の場合
     * 引数.経過日数 
     */
    public void test_setMarginAccruedDays_0003()
    {
        final String STR_METHOD_NAME = "test_setMarginAccruedDays_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setMarginAccruedDays(15, "11232", "0");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("経過日数(日)", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("11232", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set作成日時
     *
     */
    public void test_setCreatedTimestamp_0001()
    {
        final String STR_METHOD_NAME = "test_setCreatedTimestamp_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setCreatedTimestamp(16, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("16", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("作成日時", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), 
                    (Date)list0.get(2)));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set処理日時
     *
     */
    public void test_setProcessTime_0001()
    {
        final String STR_METHOD_NAME = "test_setProcessTime_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setProcessTime(17, Calendar.getInstance().getTime());
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("17", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("処理日時", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), 
                    (Date)list0.get(2)));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set承認状態 
     * 引数.承認状態区分＝"0：未承認"の場合
     * "未承認" 
     */
    public void test_setApproveStatus_0001()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "0", "4", null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認状態", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("未承認", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set承認状態 
     * 引数.承認状態区分＝"1：承認済"の場合
     * "承認済" 
     */
    public void test_setApproveStatus_0002()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "1", "4",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認状態", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("承認済", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set承認状態 
     * 引数.承認状態区分＝"2：非承認"の場合
     * "否認済" 
     */
    public void test_setApproveStatus_0003()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "2", "4",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認状態", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("否認済", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set承認状態 
     * 引数.承認状態区分＝"9：エラー"の場合
     * 引数.注文エラー理由コード＝"建株残高不足エラー"の場合
     * "建株残高不足エラー"  
     */
    public void test_setApproveStatus_0004()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "0005",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認状態", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("建株残高不足エラー", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set承認状態 
     * 引数.承認状態区分＝"9：エラー"の場合
     * 引数.注文エラー理由コード＝"売買停止銘柄エラー"の場合
     * "売買停止銘柄エラー"  
     */
    public void test_setApproveStatus_0005()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "0006",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認状態", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("売買停止銘柄エラー", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set承認状態 
     * 引数.承認状態区分＝"9：エラー"の場合
     * 引数.注文エラー理由コード＝"決済期日到来済エラー"の場合
     * "決済期日到来済エラー"  
     */
    public void test_setApproveStatus_0006()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "0016",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認状態", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("決済期日到来済エラー", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set承認状態 
     * 引数.承認状態区分＝"9：エラー"の場合
     * 引数.注文エラー理由コード＝"現引・現渡注文登録済エラー"の場合
     * "現引・現渡注文登録済エラー"  
     */
    public void test_setApproveStatus_0007()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0007()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "0017",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認状態", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("現引・現渡注文登録済エラー", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set承認状態 
     * 引数.承認状態区分＝"9：エラー"の場合
     * 引数.注文エラー理由コード＝"その他エラー"の場合
     * "その他エラー"  
     */
    public void test_setApproveStatus_0008()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0008()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "9", "9001",null);
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認状態", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("その他エラー", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * set承認状態 
     * 引数.強制決済理由区分＝"手動強制決済"の場合
     * "手動承認済" 
     */
    public void test_setApproveStatus_0009()
    {
        final String STR_METHOD_NAME = "test_setApproveStatus_0009()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApproveStatus(15, "1", "4","9");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("15", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認状態", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("手動承認済", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * set承認者 
     * 引数.承認状態区分＝"0：未承認"の場合
     * "未承認" 
     */
    public void test_setApprover_0001()
    {
        final String STR_METHOD_NAME = "test_setApprover_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleOrderDLCsvForTest l_csv = new WEB3AdminEquityForcedSettleOrderDLCsvForTest();
        
        try
        {
            l_csv.setApprover(20, "256");
            
            List list0 = (List)l_csv.l_rowValues.get(0);
            assertEquals("20", "" + ((Integer)list0.get(0)).intValue());
            assertEquals("承認者", "" + ((WEB3GentradeCsvColumnModel)list0.get(1)).getColumnLabel());
            assertEquals("256", "" + list0.get(2));
        }
        catch (Exception e)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3AdminEquityForcedSettleOrderDLCsvForTest extends WEB3AdminEquityForcedSettleOrderDLCsv
    {        
        public List l_rowValues = new ArrayList();
        
        public String[] getKeyHeader()
        {
            return this.keyHeader;
        }

        public WEB3GentradeCsvColumnModel[] getColumnHeader()
        {
            return this.columnHeader;
        }
        
        public void setValue(
            int l_intLineNumber,
            WEB3GentradeCsvColumnModel l_csvColumnModel,
            Object l_objValue)
        {         
            List list = new ArrayList();
            list.add(new Integer(l_intLineNumber));
            list.add(l_csvColumnModel);
            list.add(l_objValue);
            
            l_rowValues.add(list);
        }
    }
}
@
