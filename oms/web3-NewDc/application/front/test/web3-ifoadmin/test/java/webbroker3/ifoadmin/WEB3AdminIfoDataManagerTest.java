head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoDataManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 管理者・先物OPデータマネージャ(WEB3AdminIfoDataManager.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
Revision History : 2010/08/25　@趙天月(中訊) モデル029
Revision History : 2010/11/02　@趙天月(中訊) モデル038
*/
package webbroker3.ifoadmin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.VirtualServerInformationParams;
import webbroker3.gentrade.data.VirtualServerInformationRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifoadmin.data.IfoCsvFileFormatParams;
import webbroker3.ifoadmin.data.IfoCsvFileFormatRow;
import webbroker3.ifoadmin.define.WEB3AdminIfoOrderTypeDef;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （管理者・先物OPデータマネージャ）<BR>
 * <BR>
 * 管理者・先物OPデータマネージャ<BR>
 * 商品管理(株式)のデータ作成などを管理するクラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminIfoDataManagerTest extends TestBaseForMock
{
    /** Log Variable.<BR> */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDataManagerTest.class);

    public WEB3AdminIfoDataManagerTest(String arg0)
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

    /**
     * 証券会社プリファ@レンステーブルのダウンロード上限件数を取得し返却する
     * 検索結果からプリファ@レンスの値を返却する
     */
    public void testGetDownloadMaxCountCase1()
    {
        String STR__METHOD_NAME = ".testGetDownloadMaxCountCase1()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.IFO_ORDER_DOWNLOAD_MAXCOUNT);
            TestDBUtility.insertWithDelAndCommit(l_institutionPreferencesParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            
            int count = WEB3AdminIfoDataManager.getDownloadMaxCount(l_institution);
            
            assertEquals(1,count);
        }
        catch(Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(InstitutionPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(STR__METHOD_NAME , l_ex);
            }
            
            log.exiting(TEST_END + STR__METHOD_NAME);
        }
        
    }
    
    /**
     * 証券会社プリファ@レンステーブルのダウンロード上限件数を取得し返却する
     * 対象がなかった場合は、デフォルト値として100を返却する
     */
    public void testGetDownloadMaxCountCase2()
    {
        String STR__METHOD_NAME = ".testGetDownloadMaxCountCase2()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            TestDBUtility.insertWithDelAndCommit(l_institutionPreferencesParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            
            int count = WEB3AdminIfoDataManager.getDownloadMaxCount(l_institution);
            
            assertEquals(100,count);
        }
        catch(Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(InstitutionPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(STR__METHOD_NAME , l_ex);
            }
            
            log.exiting(TEST_END + STR__METHOD_NAME);
        }
        
    }

    /**
     * 先物OPCSVファ@イルフォーマットテーブルを以下の条件で検索し、 行を返却する。
     */
    public void testGetDownloadFormatCase1()
    {
        String STR__METHOD_NAME = "testGetDownloadFormatCase1()";
        log.entering(TEST_START + STR__METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatParams.TYPE);
            IfoCsvFileFormatParams l_IfoCsvFileFormatParams = new IfoCsvFileFormatParams();
            l_IfoCsvFileFormatParams.setInstitutionCode("0D");
            l_IfoCsvFileFormatParams.setColumnNumber(1);
            l_IfoCsvFileFormatParams.setColumnLabel("name");
            //作成日付
            l_IfoCsvFileFormatParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            //更新日付
            l_IfoCsvFileFormatParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_IfoCsvFileFormatParams);
            
            IfoCsvFileFormatRow[] l_rows = WEB3AdminIfoDataManager.getDownloadFormat("0D");
            assertEquals(1,l_rows.length);
        }
        catch(Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR__METHOD_NAME);
    }
    
    /**
     * 先物OPCSVファ@イルフォーマットテーブルを以下の条件で検索し、 行を返却する。
     */
    public void testGetDownloadFormatCase2()
    {
        String STR__METHOD_NAME = "testGetDownloadFormatCase2()";
        log.entering(TEST_START + STR__METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatParams.TYPE);
            IfoCsvFileFormatParams l_IfoCsvFileFormatParams = new IfoCsvFileFormatParams();
            l_IfoCsvFileFormatParams.setInstitutionCode("0D");
            l_IfoCsvFileFormatParams.setColumnNumber(1);
            l_IfoCsvFileFormatParams.setColumnLabel("name");
            //作成日付
            l_IfoCsvFileFormatParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            //更新日付
            l_IfoCsvFileFormatParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_IfoCsvFileFormatParams);
            
            IfoCsvFileFormatParams l_IfoCsvFileFormatParams1 = new IfoCsvFileFormatParams();
            l_IfoCsvFileFormatParams1.setInstitutionCode("0D");
            l_IfoCsvFileFormatParams1.setColumnNumber(2);
            l_IfoCsvFileFormatParams1.setColumnLabel("name");
            //作成日付
            l_IfoCsvFileFormatParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            //更新日付
            l_IfoCsvFileFormatParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_IfoCsvFileFormatParams1);
            
            IfoCsvFileFormatParams l_IfoCsvFileFormatParams2 = new IfoCsvFileFormatParams();
            l_IfoCsvFileFormatParams2.setInstitutionCode("0D");
            l_IfoCsvFileFormatParams2.setColumnNumber(3);
            l_IfoCsvFileFormatParams2.setColumnLabel("name");
            //作成日付
            l_IfoCsvFileFormatParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            //更新日付
            l_IfoCsvFileFormatParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_IfoCsvFileFormatParams2);
            
            IfoCsvFileFormatRow[] l_rows = WEB3AdminIfoDataManager.getDownloadFormat("0D");
            assertEquals(3,l_rows.length);
        }
        catch(Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR__METHOD_NAME);
    }
    
    /**
     * 該当行が存在しない場合は「フォーマットが登録されていません」の例外をスローする。
     */
    public void testGetDownloadFormatCase3()
    {
        String STR__METHOD_NAME = "testGetDownloadFormatCase3()";
        log.entering(TEST_START + STR__METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoCsvFileFormatParams.TYPE);
            WEB3AdminIfoDataManager.getDownloadFormat("0D");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03204, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getProduct
     */
    public void testGetProduct_0001() 
    {
        String STR__METHOD_NAME = ".testGetProduct_0001()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        
        //証券会社
        WEB3GentradeInstitution l_institution =  null;
        
        //先物／オプション区
        String l_strFuturesOptionDivision = null;
        
        //指数種別
        String l_strTargetProductCode = null;
        
        //限月
        String l_strDelivaryMonth =  null;
        
        //行使価格
        String l_strStrikePrice = null;
        
        //オプション商品区分
        String l_strOpProductType =  null;
        
        try
        {
            WEB3IfoProductImpl l_ifoProduct = WEB3AdminIfoDataManager.getProduct(
                l_institution,l_strFuturesOptionDivision,l_strTargetProductCode,
                l_strDelivaryMonth,l_strStrikePrice,l_strOpProductType);
            assertNull(l_ifoProduct);
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getProduct
     */
    public void testGetProduct_0002()
    {
        String STR__METHOD_NAME = ".testGetProduct_0002()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        //証券会社
        WEB3GentradeInstitution l_institution = null;
        
        //先物／オプション区
        String l_strFuturesOptionDivision = "1";
        
        //指数種別
        String l_strTargetProductCode = null;
        
        //限月
        String l_strDelivaryMonth =  null;
        
        //行使価格
        String l_strStrikePrice = null;
        
        //オプション商品区分
        String l_strOpProductType =  null;
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3IfoProductImpl l_ifoPr = new WEB3IfoProductImpl(1006160060005L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct",
                new Class[] {Institution.class,
                    String.class,
                    String.class,
                    IfoDerivativeTypeEnum.class,
                    double.class, String.class, String.class},
                    l_ifoPr);
            
            WEB3IfoProductImpl l_ifoProduct = WEB3AdminIfoDataManager.getProduct(
                l_institution,l_strFuturesOptionDivision,l_strTargetProductCode,
                l_strDelivaryMonth,l_strStrikePrice,l_strOpProductType);
            assertEquals("160030005",l_ifoProduct.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getProduct
     */
    public void testGetProduct_0003()
    {
        String STR__METHOD_NAME = ".testGetProduct_0003()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        //証券会社
        WEB3GentradeInstitution l_institution = null;
        
        //先物／オプション区
        String l_strFuturesOptionDivision = "2";
        
        //指数種別
        String l_strTargetProductCode = null;
        
        //限月
        String l_strDelivaryMonth =  null;
        
        //行使価格
        String l_strStrikePrice = "1";
        
        //オプション商品区分
        String l_strOpProductType =  null;
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3IfoProductImpl l_ifoPr = new WEB3IfoProductImpl(1006160060005L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct",
                new Class[] {Institution.class,
                    String.class,
                    String.class,
                    IfoDerivativeTypeEnum.class,
                    double.class, String.class, String.class},
                    l_ifoPr);
            
            WEB3IfoProductImpl l_ifoProduct = WEB3AdminIfoDataManager.getProduct(
                l_institution,l_strFuturesOptionDivision,l_strTargetProductCode,
                l_strDelivaryMonth,l_strStrikePrice,l_strOpProductType);
            assertEquals("160030005",l_ifoProduct.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getProduct
     */
    public void testGetProduct_0004()
    {
        String STR__METHOD_NAME = ".testGetProduct_0004()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        //証券会社
        WEB3GentradeInstitution l_institution = null;
        
        //先物／オプション区
        String l_strFuturesOptionDivision = "2";
        
        //指数種別
        String l_strTargetProductCode = "5";
        
        //限月
        String l_strDelivaryMonth =  "3";
        
        //行使価格
        String l_strStrikePrice = "4";
        
        //オプション商品区分
        String l_strOpProductType =  "P";
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3IfoProductImpl l_ifoPr = new WEB3IfoProductImpl(1006160060005L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct",
                new Class[] {Institution.class,
                    String.class,
                    String.class,
                    IfoDerivativeTypeEnum.class,
                    double.class, String.class, String.class},
                    l_ifoPr);
            
            WEB3IfoProductImpl l_ifoProduct = WEB3AdminIfoDataManager.getProduct(
                l_institution,l_strFuturesOptionDivision,l_strTargetProductCode,
                l_strDelivaryMonth,l_strStrikePrice,l_strOpProductType);
            
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct",
                new Class[] {Institution.class,
                    String.class,
                    String.class,
                    IfoDerivativeTypeEnum.class,
                    double.class, String.class, String.class});
            
            assertNull(l_value.getFirstCalled()[0]);
            assertEquals("5",l_value.getFirstCalled()[1]);
            assertEquals("3",l_value.getFirstCalled()[2]);
            assertEquals(IfoDerivativeTypeEnum.PUT_OPTIONS,l_value.getFirstCalled()[3]);
            
            assertEquals("160030005",l_ifoProduct.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    
    /**
     * getProduct
     */
    public void testGetProduct_0005()
    {
        String STR__METHOD_NAME = ".testGetProduct_0005()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        //証券会社
        WEB3GentradeInstitution l_institution = null;
        
        //先物／オプション区
        String l_strFuturesOptionDivision = "1";
        
        //指数種別
        String l_strTargetProductCode = "5";
        
        //限月
        String l_strDelivaryMonth =  "3";
        
        //行使価格
        String l_strStrikePrice = "4";
        
        //オプション商品区分
        String l_strOpProductType =  "9";
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3IfoProductImpl l_ifoPr = new WEB3IfoProductImpl(1006160060005L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct",
                new Class[] {Institution.class,
                    String.class,
                    String.class,
                    IfoDerivativeTypeEnum.class,
                    double.class, String.class, String.class},
                    l_ifoPr);
            
            WEB3IfoProductImpl l_ifoProduct = WEB3AdminIfoDataManager.getProduct(
                l_institution,l_strFuturesOptionDivision,l_strTargetProductCode,
                l_strDelivaryMonth,l_strStrikePrice,l_strOpProductType);
            
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct",
                new Class[] {Institution.class,
                    String.class,
                    String.class,
                    IfoDerivativeTypeEnum.class,
                    double.class, String.class, String.class});
            
            assertNull(l_value.getFirstCalled()[0]);
            assertEquals("5",l_value.getFirstCalled()[1]);
            assertEquals("3",l_value.getFirstCalled()[2]);
            assertEquals(IfoDerivativeTypeEnum.FUTURES,l_value.getFirstCalled()[3]);
            
            assertEquals(WEB3DivisionTypeDef.DIVISION_DEFAULT,l_value.getFirstCalled()[5]);
            assertNull(l_value.getFirstCalled()[6]);
            
            assertEquals("160030005",l_ifoProduct.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getProduct
     */
    public void testGetProduct_0006()
    {
        String STR__METHOD_NAME = ".testGetProduct_0006()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        //証券会社
        WEB3GentradeInstitution l_institution = null;
        
        //先物／オプション区
        String l_strFuturesOptionDivision = "2";
        
        //指数種別
        String l_strTargetProductCode = "a";
        
        //限月
        String l_strDelivaryMonth =  "a";
        
        //行使価格
        String l_strStrikePrice = "1";
        
        //オプション商品区分
        String l_strOpProductType =  "P";
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3IfoProductImpl l_ifoPr = new WEB3IfoProductImpl(1006160060005L);
                       
            WEB3IfoProductImpl l_ifoProduct = WEB3AdminIfoDataManager.getProduct(
                l_institution,l_strFuturesOptionDivision,l_strTargetProductCode,
                l_strDelivaryMonth,l_strStrikePrice,l_strOpProductType);
            
            assertNull(l_ifoProduct);
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getAccountList
     */
    public void testGetAccountList_0001()
    {
        String STR__METHOD_NAME = ".testGetAccountList_0001()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        //拡張アカウントマネージャ
        WEB3GentradeAccountManager l_accountMananger = new WEB3GentradeAccountManager();
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            
            WEB3IfoProductImpl l_ifoPr = new WEB3IfoProductImpl(1006160060005L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {String.class, String.class, String.class},
                l_accountMananger.getMainAccount(333812512246L));
            
            String[] str = {"",""};
            
            WEB3GentradeMainAccount[] l_mainAccount = WEB3AdminIfoDataManager.getAccountList(
                "0D",str,"2512246");
            
            assertEquals("2512246",l_mainAccount[0].getAccountCode());
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getAccountList
     */
    public void testGetAccountList_0002()
    {
        String STR__METHOD_NAME = ".testGetAccountList_0002()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        //拡張アカウントマネージャ
        WEB3GentradeAccountManager l_accountMananger = new WEB3GentradeAccountManager();
        
        l_accountMananger = null;
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            
            try
            {
                WEB3IfoProductImpl l_ifoPr = new WEB3IfoProductImpl(1006160060005L);
            } catch (DataFindException l_ex)
            {
                log.error(STR__METHOD_NAME , l_ex);
                log.exiting(TEST_END + STR__METHOD_NAME);
                fail();
            } catch (DataQueryException l_ex)
            {
                log.error(STR__METHOD_NAME , l_ex);
                log.exiting(TEST_END + STR__METHOD_NAME);
                fail();
            } catch (DataNetworkException l_ex)
            {
                log.error(STR__METHOD_NAME , l_ex);
                log.exiting(TEST_END + STR__METHOD_NAME);
                fail();
            }
            
            String[] str = {"",""};
            
            WEB3GentradeMainAccount[] l_mainAccount = WEB3AdminIfoDataManager.getAccountList(
                "0D",str,"1");
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037,l_ex.getErrorInfo());
        }
    }
    
    /**
     * getAccountList
     */
    public void testGetAccountList_0003()
    {
        String STR__METHOD_NAME = ".testGetAccountList_0003()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        
        //拡張アカウントマネージャ
        WEB3GentradeAccountManager l_accountMananger = new WEB3GentradeAccountManager();
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            
            MainAccountParams l_main = TestDBUtility.getMainAccountRow();
            
            l_main.setAccountId(321313L);
            l_main.setBranchCode("123");
            l_main.setInstitutionId(12454);
            
            TestDBUtility.insertWithDel(l_main);
            
            MainAccountParams l_main1 = TestDBUtility.getMainAccountRow();
            
            l_main1.setBranchCode("321");
            l_main1.setAccountId(3213132222L);
            
            l_main1.setInstitutionId(454);
            
            TestDBUtility.insertWithDel(l_main1);
            
            WEB3IfoProductImpl l_ifoPr = new WEB3IfoProductImpl(1006160060005L);
            
            String[] str = {"123","321"};
            WEB3GentradeMainAccount[] l_mainAccount = WEB3AdminIfoDataManager.getAccountList(
                "0D",str,"2512246");
            assertEquals("2512246",l_mainAccount[0].getAccountCode());
            assertEquals("2512246",l_mainAccount[1].getAccountCode());
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    
    /**
     * resetTradingCalContext
     */
    public void testResetTradingCalContext_0001()
    {
        String STR__METHOD_NAME = ".testResetTradingCalContext_0001()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        //拡張アカウントマネージャ
        WEB3GentradeAccountManager l_accountMananger = new WEB3GentradeAccountManager();
    
        WEB3GentradeTradingClendarContext l_context = null;
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("123");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0005");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
            String l_BizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime);
            l_tradingTimeParams.setBizDateType(l_BizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

            WEB3AdminIfoDataManager.resetTradingCalContext("123",new Long(33381L),new Long(1006160060005L));
            
            WEB3GentradeTradingClendarContext l_context1 = 
               (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("381",l_context1.getBranchCode());
            
            assertEquals("0",l_context1.getMarketCode());
            
            assertEquals("0005",l_context1.getProductCode());
            
            assertEquals("123",l_context1.getInstitutionCode());
            
            assertEquals("11",l_context1.getTradingTimeType());
            
            log.exiting(STR__METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    public void testResetTradingCalContext_0006()
    {
        String STR__METHOD_NAME = ".testResetTradingCalContext_0006()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        //拡張アカウントマネージャ
        WEB3GentradeAccountManager l_accountMananger = new WEB3GentradeAccountManager();
    
        WEB3GentradeTradingClendarContext l_context = null;
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("123");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
            String l_BizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime);
            l_tradingTimeParams.setBizDateType(l_BizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

            WEB3AdminIfoDataManager.resetTradingCalContext("123",new Long(33381L), null);
            
            WEB3GentradeTradingClendarContext l_context1 = 
               (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            
            assertEquals("0",l_context1.getProductCode());
            
            
            log.exiting(STR__METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    /**
     * resetTradingCalContext
     */
    public void testResetTradingCalContext_0002()
    {
        String STR__METHOD_NAME = ".testResetTradingCalContext_0002()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        //拡張アカウントマネージャ
        WEB3GentradeAccountManager l_accountMananger = new WEB3GentradeAccountManager();
    
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        l_context.setBranchCode("22");
        
        l_context.setMarketCode("45");
        
        l_context.setProductCode("89");
        
        l_context.setInstitutionCode("78");
        
        l_context.setTradingTimeType("7");
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("123");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0005");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
            String l_BizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime);
            l_tradingTimeParams.setBizDateType(l_BizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

            WEB3AdminIfoDataManager.resetTradingCalContext("123",new Long(33381L),new Long(1006160060005L));
            
            WEB3GentradeTradingClendarContext l_context1 = 
               (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("381",l_context1.getBranchCode());
            
            assertEquals("0",l_context1.getMarketCode());
            
            assertEquals("0005",l_context1.getProductCode());
            
            assertEquals("123",l_context1.getInstitutionCode());
            
            assertEquals("11",l_context1.getTradingTimeType());
            
        }
        catch (Exception l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            fail();
        }
    }
    
    /**
     * resetTradingCalContext
     */
    public void testResetTradingCalContext_0003()
    {
        String STR__METHOD_NAME = ".testResetTradingCalContext_0003()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        //拡張アカウントマネージャ
        WEB3GentradeAccountManager l_accountMananger = new WEB3GentradeAccountManager();
    
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        l_context.setBranchCode("22");
        
        l_context.setMarketCode("45");
        
        l_context.setProductCode("89");
        
        l_context.setTradingTimeType("7");
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("123");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0005");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
            String l_BizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime);
            l_tradingTimeParams.setBizDateType(l_BizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());

            WEB3AdminIfoDataManager.resetTradingCalContext(null,new Long(33381L),new Long(1006160060005L));
            
            WEB3GentradeTradingClendarContext l_context1 = 
               (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006,l_ex.getErrorInfo());
        }
    }
    
    /**
     * resetTradingCalContext
     */
    public void testResetTradingCalContext_0004()
    {
        String STR__METHOD_NAME = ".testResetTradingCalContext_0004()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        //拡張アカウントマネージャ
        WEB3GentradeAccountManager l_accountMananger = new WEB3GentradeAccountManager();
    
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        l_context.setBranchCode("22");
        
        l_context.setMarketCode("45");
        
        l_context.setProductCode("89");
        
        l_context.setTradingTimeType("7");
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("123");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0005");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
            String l_BizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime);
            l_tradingTimeParams.setBizDateType(l_BizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            WEB3AdminIfoDataManager.resetTradingCalContext(null,new Long(33231L),new Long(1006160060005L));
            
            WEB3GentradeTradingClendarContext l_context1 = 
               (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
    }
    
    /**
     * resetTradingCalContext

     */
    public void testResetTradingCalContext_0005()
    {
        String STR__METHOD_NAME = ".testResetTradingCalContext_0005()";
        log.entering(TEST_START + STR__METHOD_NAME);
        
        //拡張アカウントマネージャ
        WEB3GentradeAccountManager l_accountMananger = new WEB3GentradeAccountManager();
    
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        l_context.setBranchCode("22");
        
        l_context.setMarketCode("45");
        
        l_context.setProductCode("89");
        
        l_context.setTradingTimeType("7");
        
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            TestDBUtility.insertWithDel(l_productParams);
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("123");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0005");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
            String l_BizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime);
            l_tradingTimeParams.setBizDateType(l_BizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            WEB3AdminIfoDataManager.resetTradingCalContext(null,new Long(33381L),new Long(10061605L));
            
            WEB3GentradeTradingClendarContext l_context1 = 
               (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR__METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR__METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
    }
    
    public void testGetManualExpireOrderUnits_0001()
    {
        String STR_METHOD_NAME = "testGetManualExpireOrderUnits_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        // mainAccount InstitutionCode = "0D"|BranchCode = "381"|AccountCode = "2512246"
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setAccountId(333812512246L);
        l_ifoOrderUnitParams.setBranchId(33381L);

        IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams1.setOrderUnitId(1002L);
        l_ifoOrderUnitParams1.setAccountId(333812512255L);
        l_ifoOrderUnitParams1.setBranchId(33381L);
        l_ifoOrderUnitParams1.setOrderRequestNumber("000003007");

        IfoOrderUnitParams l_ifoOrderUnitParams_setBranchId = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setBranchId.setOrderUnitId(1003L);
        l_ifoOrderUnitParams_setBranchId.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setBranchId.setBranchId(33382L);
        l_ifoOrderUnitParams_setBranchId.setOrderRequestNumber("000003008");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setProductid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setProductid.setOrderUnitId(1004L);
        l_ifoOrderUnitParams_setProductid.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setProductid.setBranchId(33381L);
        l_ifoOrderUnitParams_setProductid.setProductId(1006169090019L);
        l_ifoOrderUnitParams_setProductid.setOrderRequestNumber("000003009");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setOrdertype = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setOrdertype.setOrderUnitId(1005L);
        l_ifoOrderUnitParams_setOrdertype.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setOrdertype.setBranchId(33381L);
        l_ifoOrderUnitParams_setOrdertype.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams_setOrdertype.setOrderRequestNumber("000003040");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setAccountid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setAccountid.setOrderUnitId(1006L);
        l_ifoOrderUnitParams_setAccountid.setBranchId(33381L);
        l_ifoOrderUnitParams_setAccountid.setAccountId(333812512247L);
        l_ifoOrderUnitParams_setAccountid.setOrderRequestNumber("000003041");
        
        /* Table HostFotypeOrderAllParams  */
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        
        try
        {
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(l_ifoOrderUnitParams.getRowType());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setBranchId);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setProductid);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setOrdertype);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setAccountid);
            
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(l_hostFotypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("0D");
            String[] l_strBranchCodes = {"381"};
            String l_strProductID = "1006169090018";
            String[] l_strTradingTypeList = {"601"};
            String l_strAccountCode = "2512246";
            Long l_accountIdFrom = new Long(333812512246L);
            Long l_accountIdTo = new Long(333812512248L);
            
            IfoOrderUnitRow[] l_ifoOrderUnitRows = null;
            
            l_ifoOrderUnitRows = WEB3AdminIfoDataManager.getManualExpireOrderUnits(
                l_institution , 
                l_strBranchCodes , 
                l_strProductID,
                l_strTradingTypeList,
                l_strAccountCode,
                l_accountIdFrom,
                l_accountIdTo
                );
            
            assertNotNull(l_ifoOrderUnitRows);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetManualExpireOrderUnits_0002()
    {
        String STR_METHOD_NAME = "testGetManualExpireOrderUnits_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("0D");
            String[] l_strBranchCodes = {"400"};
            String l_strProductID = "1006169090018";
            String[] l_strTradingTypeList = {"601"};
            String l_strAccountCode = "2512246";
            Long l_accountIdFrom = new Long(333812512246L);
            Long l_accountIdTo = new Long(333812512248L);
            
            IfoOrderUnitRow[] l_ifoOrderUnitRows = null;
            
            l_ifoOrderUnitRows = WEB3AdminIfoDataManager.getManualExpireOrderUnits(
                l_institution , 
                l_strBranchCodes , 
                l_strProductID,
                l_strTradingTypeList,
                l_strAccountCode,
                l_accountIdFrom,
                l_accountIdTo
                );
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037 , l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetManualExpireOrderUnits_0003()
    {
        String STR_METHOD_NAME = "testGetManualExpireOrderUnits_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        // mainAccount InstitutionCode = "0D"|BranchCode = "381"|AccountCode = "2512246"
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setAccountId(333812512246L);
        l_ifoOrderUnitParams.setBranchId(33381L);

        IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams1.setOrderUnitId(1002L);
        l_ifoOrderUnitParams1.setAccountId(333812512255L);
        l_ifoOrderUnitParams1.setBranchId(33381L);
        l_ifoOrderUnitParams1.setOrderRequestNumber("000003007");

        IfoOrderUnitParams l_ifoOrderUnitParams_setBranchId = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setBranchId.setOrderUnitId(1003L);
        l_ifoOrderUnitParams_setBranchId.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setBranchId.setBranchId(33382L);
        l_ifoOrderUnitParams_setBranchId.setOrderRequestNumber("000003008");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setProductid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setProductid.setOrderUnitId(1004L);
        l_ifoOrderUnitParams_setProductid.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setProductid.setBranchId(33381L);
        l_ifoOrderUnitParams_setProductid.setProductId(1006169090019L);
        l_ifoOrderUnitParams_setProductid.setOrderRequestNumber("000003009");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setOrdertype = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setOrdertype.setOrderUnitId(1005L);
        l_ifoOrderUnitParams_setOrdertype.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setOrdertype.setBranchId(33381L);
        l_ifoOrderUnitParams_setOrdertype.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams_setOrdertype.setOrderRequestNumber("000003040");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setAccountid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setAccountid.setOrderUnitId(1006L);
        l_ifoOrderUnitParams_setAccountid.setBranchId(33381L);
        l_ifoOrderUnitParams_setAccountid.setAccountId(333812512247L);
        l_ifoOrderUnitParams_setAccountid.setOrderRequestNumber("000003041");
        
        /* Table HostFotypeOrderAllParams  */
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        
        try
        {
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(l_ifoOrderUnitParams.getRowType());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setBranchId);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setProductid);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setOrdertype);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setAccountid);
            
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(l_hostFotypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("0D");
            String[] l_strBranchCodes = {"381"};
            String l_strProductID = "1006169090018";
            String[] l_strTradingTypeList = {"666"};
            String l_strAccountCode = "2512246";
            Long l_accountIdFrom = new Long(333812512246L);
            Long l_accountIdTo = new Long(333812512248L);
            
            IfoOrderUnitRow[] l_ifoOrderUnitRows = null;
            
            l_ifoOrderUnitRows = WEB3AdminIfoDataManager.getManualExpireOrderUnits(
                l_institution , 
                l_strBranchCodes , 
                l_strProductID,
                l_strTradingTypeList,
                l_strAccountCode,
                l_accountIdFrom,
                l_accountIdTo
                );
            
            assertNull(l_ifoOrderUnitRows);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetManualExpireOrderUnits_0004()
    {
        String STR_METHOD_NAME = "testGetManualExpireOrderUnits_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        // mainAccount InstitutionCode = "0D"|BranchCode = "381"|AccountCode = "2512246"
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setAccountId(333812512246L);
        l_ifoOrderUnitParams.setBranchId(33381L);

        IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams1.setOrderUnitId(1002L);
        l_ifoOrderUnitParams1.setAccountId(333812512255L);
        l_ifoOrderUnitParams1.setBranchId(33381L);
        l_ifoOrderUnitParams1.setOrderRequestNumber("000003007");

        IfoOrderUnitParams l_ifoOrderUnitParams_setBranchId = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setBranchId.setOrderUnitId(1003L);
        l_ifoOrderUnitParams_setBranchId.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setBranchId.setBranchId(33382L);
        l_ifoOrderUnitParams_setBranchId.setOrderRequestNumber("000003008");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setProductid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setProductid.setOrderUnitId(1004L);
        l_ifoOrderUnitParams_setProductid.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setProductid.setBranchId(33381L);
        l_ifoOrderUnitParams_setProductid.setProductId(1006169090019L);
        l_ifoOrderUnitParams_setProductid.setOrderRequestNumber("000003009");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setOrdertype = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setOrdertype.setOrderUnitId(1005L);
        l_ifoOrderUnitParams_setOrdertype.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setOrdertype.setBranchId(33381L);
        l_ifoOrderUnitParams_setOrdertype.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams_setOrdertype.setOrderRequestNumber("000003040");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setAccountid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setAccountid.setOrderUnitId(1006L);
        l_ifoOrderUnitParams_setAccountid.setBranchId(33381L);
        l_ifoOrderUnitParams_setAccountid.setAccountId(333812512247L);
        l_ifoOrderUnitParams_setAccountid.setOrderRequestNumber("000003041");
        
        /* Table HostFotypeOrderAllParams  */
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams.setStatus("1");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        
        try
        {
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(l_ifoOrderUnitParams.getRowType());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setBranchId);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setProductid);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setOrdertype);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setAccountid);
            
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(l_hostFotypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("0D");
            String[] l_strBranchCodes = {"381"};
            String l_strProductID = "1006169090018";
            String[] l_strTradingTypeList = {"601"};
            String l_strAccountCode = "2512246";
            Long l_accountIdFrom = new Long(333812512246L);
            Long l_accountIdTo = new Long(333812512248L);
            
            IfoOrderUnitRow[] l_ifoOrderUnitRows = null;
            
            l_ifoOrderUnitRows = WEB3AdminIfoDataManager.getManualExpireOrderUnits(
                l_institution , 
                l_strBranchCodes , 
                l_strProductID,
                l_strTradingTypeList,
                l_strAccountCode,
                l_accountIdFrom,
                l_accountIdTo
                );
            
            assertNull(l_ifoOrderUnitRows);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //返回0条数据
    public void testGetDepositInfoList_0001()
    {
        String STR_METHOD_NAME = "testGetDepositInfoList_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(IfoDepositParams.TYPE);
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setBranchCode("101");
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            l_ifoDepositParams.setAccountCode("1234567");
            //TestDBUtility.insertWithDel(l_ifoDepositParams);

            WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
            
            l_request.searchDate = WEB3DateUtility.getDate("20090310", "yyyyMMdd");
            String[] l_branchCodeList = new String[1];
            l_branchCodeList[0] = "101";
            l_request.branchCode = l_branchCodeList;
            l_request.accountCode = "123456";
            l_request.pageIndex = "1";
            l_request.pageSize = "10";
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            l_request.sortKeys = l_sortKeys;

            Institution l_institution = new InstitutionImpl(33);

            IfoDepositRow[] l_ifoDepositRows =
                WEB3AdminIfoDataManager.getDepositInfoList(l_institution, l_request);

            assertEquals(0, l_ifoDepositRows.length);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //返回1条数据
    public void testGetDepositInfoList_0002()
    {
        String STR_METHOD_NAME = "testGetDepositInfoList_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(IfoDepositParams.TYPE);
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setBranchCode("101");
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            l_ifoDepositParams.setAccountCode("1234567");
            IfoDepositParams l_ifoDepositParams2 = new IfoDepositParams();
            l_ifoDepositParams2.setBranchCode("102");
            l_ifoDepositParams2.setInstitutionCode("0D");
            l_ifoDepositParams2.setCalcDate(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            l_ifoDepositParams2.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_ifoDepositParams);
            TestDBUtility.insertWithDel(l_ifoDepositParams2);

            WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
            
            l_request.searchDate = WEB3DateUtility.getDate("20090310", "yyyyMMdd");
            String[] l_branchCodeList = new String[2];
            l_branchCodeList[0] = "101";
            l_branchCodeList[1] = "103";
            l_request.branchCode = l_branchCodeList;
            l_request.pageIndex = "1";
            l_request.pageSize = "10";
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            l_request.sortKeys = l_sortKeys;

            Institution l_institution = new InstitutionImpl(33);

            IfoDepositRow[] l_ifoDepositRows =
                WEB3AdminIfoDataManager.getDepositInfoList(l_institution, l_request);

            assertEquals(1, l_ifoDepositRows.length);
            assertEquals("101", l_ifoDepositRows[0].getBranchCode());
            assertEquals("1234567", l_ifoDepositRows[0].getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //返回2条数据
    public void testGetDepositInfoList_0003()
    {
        String STR_METHOD_NAME = "testGetDepositInfoList_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            l_institutionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(IfoDepositParams.TYPE);
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setBranchCode("101");
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            l_ifoDepositParams.setAccountCode("1234567");
            IfoDepositParams l_ifoDepositParams2 = new IfoDepositParams();
            l_ifoDepositParams2.setBranchCode("102");
            l_ifoDepositParams2.setInstitutionCode("0D");
            l_ifoDepositParams2.setCalcDate(WEB3DateUtility.getDate("20090310", "yyyyMMdd"));
            l_ifoDepositParams2.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_ifoDepositParams);
            TestDBUtility.insertWithDel(l_ifoDepositParams2);

            WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
            
            l_request.searchDate = WEB3DateUtility.getDate("20090310", "yyyyMMdd");
            String[] l_branchCodeList = new String[3];
            l_branchCodeList[0] = "101";
            l_branchCodeList[1] = "102";
            l_branchCodeList[2] = "103";
            l_request.branchCode = l_branchCodeList;
            l_request.accountCode = "123456";
            l_request.pageIndex = "1";
            l_request.pageSize = "10";
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "D";
            l_request.sortKeys = l_sortKeys;

            Institution l_institution = new InstitutionImpl(33);

            IfoDepositRow[] l_ifoDepositRows =
                WEB3AdminIfoDataManager.getDepositInfoList(l_institution, l_request);

            assertEquals(2, l_ifoDepositRows.length);
            assertEquals("102", l_ifoDepositRows[0].getBranchCode());
            assertEquals("101", l_ifoDepositRows[1].getBranchCode());
            assertEquals("1234567", l_ifoDepositRows[1].getAccountCode());
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //返回true
    public void testIsIfoDepositMailFlagAndIsQuickReportReceived_0001()
    {
        String STR_METHOD_NAME = "testIsIfoDepositMailFlagAndIsQuickReportReceived_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_processManagentParams = new ProcessManagementParams();
            l_processManagentParams.setProcessId("0001");
            l_processManagentParams.setInstitutionCode("0D");
            l_processManagentParams.setBranchCode("101");
            ProcessManagementParams l_processManagentParams2 = new ProcessManagementParams();
            l_processManagentParams2.setProcessId("0008");
            l_processManagentParams2.setInstitutionCode("0D");
            l_processManagentParams2.setBranchCode("102");
            TestDBUtility.insertWithDel(l_processManagentParams);
            TestDBUtility.insertWithDel(l_processManagentParams2);

            String l_institution = "0D";
            String l_branch1 = "101";
            String l_branch2 = "102";
            boolean l_blnIsIfoDepositMailFlag =
                WEB3AdminIfoDataManager.isIfoDepositMailFlag(l_institution, l_branch1);

            boolean l_blnIsQuickReportReceived =
                WEB3AdminIfoDataManager.isQuickReportReceived(l_institution, l_branch2);

            assertEquals(true, l_blnIsIfoDepositMailFlag);
            assertEquals(true, l_blnIsQuickReportReceived);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //返回false
    public void testIsIfoDepositMailFlagAndIsQuickReportReceived_0002()
    {
        String STR_METHOD_NAME = "testIsIfoDepositMailFlagAndIsQuickReportReceived_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProcessManagementParams.TYPE);
            ProcessManagementParams l_processManagentParams = new ProcessManagementParams();
            l_processManagentParams.setProcessId("0001");
            l_processManagentParams.setInstitutionCode("0D");
            l_processManagentParams.setBranchCode("101");
            ProcessManagementParams l_processManagentParams2 = new ProcessManagementParams();
            l_processManagentParams2.setProcessId("0008");
            l_processManagentParams2.setInstitutionCode("0D");
            l_processManagentParams2.setBranchCode("102");
            TestDBUtility.insertWithDel(l_processManagentParams);
            TestDBUtility.insertWithDel(l_processManagentParams2);

            String l_institution = "0D";
            String l_branch1 = "101";
            String l_branch2 = "102";
            boolean l_blnIsIfoDepositMailFlag =
                WEB3AdminIfoDataManager.isIfoDepositMailFlag(l_institution, l_branch2);

            boolean l_blnIsQuickReportReceived =
                WEB3AdminIfoDataManager.isQuickReportReceived(l_institution, l_branch1);

            assertEquals(false, l_blnIsIfoDepositMailFlag);
            assertEquals(false, l_blnIsQuickReportReceived);      
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    // update市場未発注注文ステータス
    /**
     * (1)処理区分 = 5：ダウンロード済みグレー注文
     * (2)更新日付 = 現在日時
     */
    public void testUpdateMarketUnOrderStatus_case001()
    {
        final String STR_METHOD_NAME = "testUpdateMarketUnOrderStatus_case001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 障害時市場未発注注文ダウンロード_先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lstHostFotypeOrderAllRow = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE);
            
            WEB3AdminIfoDataManager.updateMarketUnOrderStatus((HostFotypeOrderAllRow)l_lstHostFotypeOrderAllRow.get(0));
            
            List l_lstReturnHostFotypeOrderAllRow = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllRow l_returnHostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstReturnHostFotypeOrderAllRow.get(0);
            
            String l_strStatus = l_returnHostFotypeOrderAllRow.getStatus();
            Timestamp l_datLastUpdatedTimestamp = l_returnHostFotypeOrderAllRow.getLastUpdatedTimestamp();
            
            String l_strLastUpdatedTimestamp = WEB3DateUtility.formatDate(
                    l_datLastUpdatedTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            
            Date l_datCurrentSystemTime = new Date();
            String l_strCurrentSystemDate = 
                WEB3DateUtility.formatDate(l_datCurrentSystemTime, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            
            //
            assertEquals(WEB3FrontOrderStatusDef.DOWNLOAD_GRAY_ORDER, l_strStatus);
            assertEquals(l_strCurrentSystemDate, l_strLastUpdatedTimestamp);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    
    // getOrderTypeEachMarketNotOrder
    /**
     * (1)引数の注文種別　@== ’3:取消’の場合
     * (2)DBを検索し、検索結果が取得できない場合、 nullを返却する。 
     * 
     * ・証券会社コード = 引数の証券会社の証券会社コード 
     * ・受注日時　@>= 引数の基準時間
     * ・データコード in ”EI804”,”EI802”
     * ・取消区分 = ”1”
     * ・処理区分 in "0"."1"."2"
     */
    public void testGetOrderTypeEachMarketNotOrder_case0001()
    {
        String STR_METHOD_NAME = "testGetOrderTypeEachMarketNotOrder_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            
            // 引数の基準時間
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            long l_lngBaseTimeMillis = l_lngCurrentTimeMillis - 5000;
            Date l_datBaseTime = new Timestamp(l_lngBaseTimeMillis);
            
            
            // 先物OP注文取引キュー
            
            // data1:wrong institution_code
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0E"); // wrong institution_code
            l_hostFotypeOrderAllParams1.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams1.setRequestCode("EI802");
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            l_hostFotypeOrderAllParams1.setStatus("0");
            l_hostFotypeOrderAllParams1.setCorpCode("institution_code"); //
            
            // data2:wrong received_date_time
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams2.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 50000)); // wrong received_date_time
            l_hostFotypeOrderAllParams2.setRequestCode("EI804");
            l_hostFotypeOrderAllParams2.setCancelDiv("1");
            l_hostFotypeOrderAllParams2.setStatus("1");
            l_hostFotypeOrderAllParams2.setCorpCode("received_date_time"); //
            
            // data3:wrong request_code
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams3.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams3.setRequestCode("EI801"); // wrong request_code
            l_hostFotypeOrderAllParams3.setCancelDiv("1");
            l_hostFotypeOrderAllParams3.setStatus("2");
            l_hostFotypeOrderAllParams3.setCorpCode("request_code"); //
            
            // data4:wrong cancel_div
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams4.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams4.setRequestCode("EI802"); 
            l_hostFotypeOrderAllParams4.setCancelDiv("0"); // wrong cancel_div
            l_hostFotypeOrderAllParams4.setStatus("2");
            l_hostFotypeOrderAllParams4.setCorpCode("cancel_div"); //
            
            // data5: wrong status
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams5.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams5.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams5.setRequestCode("EI804"); 
            l_hostFotypeOrderAllParams5.setCancelDiv("1"); 
            l_hostFotypeOrderAllParams5.setStatus("5"); // wrong status
            l_hostFotypeOrderAllParams5.setCorpCode("status"); //
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
//            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams5);
            
            
            HostFotypeOrderAllRow[] l_returnHostFotypeOrderAllRow =
                WEB3AdminIfoDataManager.getOrderTypeEachMarketNotOrder(l_institution, WEB3AdminIfoOrderTypeDef.CANCEL);
            
            assertEquals(null, l_returnHostFotypeOrderAllRow);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    /**
     * (1)引数の注文種別　@== ’3:取消’の場合
     * (2)DBを検索し、検索結果取得できる場合、検索結果を返却用listを先物OP注文取引キューrow[]にキャストして返却する。
     * 
     * ・証券会社コード = 引数の証券会社の証券会社コード 
     * ・受注日時　@>= 引数の基準時間
     * ・データコード in ”EI804”,”EI802”
     * ・取消区分 = ”1”
     * ・処理区分 in "0"."1"."2"
     */
    public void testGetOrderTypeEachMarketNotOrder_case0002()
    {
        String STR_METHOD_NAME = "testGetOrderTypeEachMarketNotOrder_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            
            // 引数の基準時間
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            long l_lngBaseTimeMillis = l_lngCurrentTimeMillis - 5000;
            Date l_datBaseTime = new Timestamp(l_lngBaseTimeMillis);
            
            
            // 先物OP注文取引キュー
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams1.setRequestCode("EI802");
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            l_hostFotypeOrderAllParams1.setStatus("0");
            l_hostFotypeOrderAllParams1.setCorpCode("EI802,0"); //
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams2.setReceivedDateTime(new Date(l_lngCurrentTimeMillis)); 
            l_hostFotypeOrderAllParams2.setRequestCode("EI804");
            l_hostFotypeOrderAllParams2.setCancelDiv("1");
            l_hostFotypeOrderAllParams2.setStatus("1");
            l_hostFotypeOrderAllParams2.setCorpCode("EI804,1"); //
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams3.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams3.setRequestCode("EI802"); 
            l_hostFotypeOrderAllParams3.setCancelDiv("1");
            l_hostFotypeOrderAllParams3.setStatus("2");
            l_hostFotypeOrderAllParams3.setCorpCode("EI802,2"); //

            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            
            
            HostFotypeOrderAllRow[] l_returnHostFotypeOrderAllRow =
                WEB3AdminIfoDataManager.getOrderTypeEachMarketNotOrder(l_institution, WEB3AdminIfoOrderTypeDef.CANCEL);
            
            assertEquals(3, l_returnHostFotypeOrderAllRow.length);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    /**
     * (1)引数の注文種別　@== ’1:新規’の場合
     * (2)DBを検索し、検索結果取得できる場合、発注中の逆指値注文　@或いは、切替中のＷ指値注文　@の削除処理
     *    要素数分のLoop処理を開始する。 
     *    Loop要素の「口座ＩＤ」と「識別コード」によって、注文単位を取得する。
     *    取得した注文単位の発注条件が「1：逆指値」の場合、Loop処理のcontinue。 
     *    以外の場合、 該当要素を返却用listに追加して格納する。
     *    Loop終了、返却用listを先物OP注文取引キューrow[]にキャストする。 キャストした返却用listを返却する。 
     * 
     * ・証券会社コード = 引数の証券会社の証券会社コード 
     * ・受注日時　@>= 引数の基準時間
     * ・データコード in ”EI801”,”EI803”
     * ・取消区分 = ”0”
     * ・処理区分 in "0"."1"."2"
     */
    public void testGetOrderTypeEachMarketNotOrder_case0003()
    {
        String STR_METHOD_NAME = "testGetOrderTypeEachMarketNotOrder_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            
            // 引数の基準時間
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            long l_lngBaseTimeMillis = l_lngCurrentTimeMillis - 5000;
            Date l_datBaseTime = new Timestamp(l_lngBaseTimeMillis);
            
            
            // 先物OP注文取引キュー
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 1000));
            l_hostFotypeOrderAllParams1.setRequestCode("EI801");
            l_hostFotypeOrderAllParams1.setCancelDiv("0");
            l_hostFotypeOrderAllParams1.setStatus("0");
            l_hostFotypeOrderAllParams1.setCorpCode("EI801,0,0");            
            l_hostFotypeOrderAllParams1.setAccountId(111111); // 取得した注文単位の発注条件が「1：逆指値」
            l_hostFotypeOrderAllParams1.setOrderRequestNumber("EI801,0,0"); //
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams2.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 2000)); 
            l_hostFotypeOrderAllParams2.setRequestCode("EI803");
            l_hostFotypeOrderAllParams2.setCancelDiv("0");
            l_hostFotypeOrderAllParams2.setStatus("1");
            l_hostFotypeOrderAllParams2.setCorpCode("EI803,0,1");
            l_hostFotypeOrderAllParams2.setAccountId(222222); // 取得した注文単位の発注条件が「1：逆指値」ではない
            l_hostFotypeOrderAllParams2.setOrderRequestNumber("EI803,0,1"); //
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams3.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 4000));
            l_hostFotypeOrderAllParams3.setRequestCode("EI803"); 
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setStatus("2");
            l_hostFotypeOrderAllParams3.setCorpCode("EI803,0,2");
            l_hostFotypeOrderAllParams3.setAccountId(333333); // 取得した注文単位の発注条件が「1：逆指値」ではない
            l_hostFotypeOrderAllParams3.setOrderRequestNumber("EI803,0,2"); //

            // wrong data
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams4.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams4.setRequestCode("EI802"); 
            l_hostFotypeOrderAllParams4.setCancelDiv("0");
            l_hostFotypeOrderAllParams4.setStatus("1");
            l_hostFotypeOrderAllParams4.setCorpCode("EI802,0,1");
            l_hostFotypeOrderAllParams4.setAccountId(444444); // 取得した注文単位の発注条件が「1：逆指値」ではない
            l_hostFotypeOrderAllParams4.setOrderRequestNumber("EI802,0,1"); //
            // wrong data
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams5.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams5.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams5.setRequestCode("EI804"); 
            l_hostFotypeOrderAllParams5.setCancelDiv("0");
            l_hostFotypeOrderAllParams5.setStatus("2");
            l_hostFotypeOrderAllParams5.setCorpCode("EI804,0,2");
            l_hostFotypeOrderAllParams5.setAccountId(555555); // 取得した注文単位の発注条件が「1：逆指値」ではない
            l_hostFotypeOrderAllParams5.setOrderRequestNumber("EI804,0,2"); //
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams5);
            
            
            // 注文単位テーブル
            IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams1.setOrderUnitId(1001);
            l_ifoOrderUnitParams1.setAccountId(111111);
            l_ifoOrderUnitParams1.setOrderRequestNumber("EI801,0,0");
            l_ifoOrderUnitParams1.setOrderConditionType("1"); // 発注条件が「1：逆指値」
            
            IfoOrderUnitParams l_ifoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams2.setOrderUnitId(1002);
            l_ifoOrderUnitParams2.setAccountId(222222);
            l_ifoOrderUnitParams2.setOrderRequestNumber("EI803,0,1");
            l_ifoOrderUnitParams2.setOrderConditionType("0"); // 発注条件が「1：逆指値」ではない
            
            IfoOrderUnitParams l_ifoOrderUnitParams3 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams3.setOrderUnitId(1003);
            l_ifoOrderUnitParams3.setAccountId(333333);
            l_ifoOrderUnitParams3.setOrderRequestNumber("EI803,0,2");
            l_ifoOrderUnitParams3.setOrderConditionType("2"); // 発注条件が「1：逆指値」ではない
            
            // wrong data
            IfoOrderUnitParams l_ifoOrderUnitParams4 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams4.setOrderUnitId(1004);
            l_ifoOrderUnitParams4.setAccountId(444444);
            l_ifoOrderUnitParams4.setOrderRequestNumber("EI802,0,1");
            l_ifoOrderUnitParams4.setOrderConditionType("2"); // 発注条件が「1：逆指値」ではない
            
            IfoOrderUnitParams l_ifoOrderUnitParams5 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams5.setOrderUnitId(1005);
            l_ifoOrderUnitParams5.setAccountId(555555);
            l_ifoOrderUnitParams5.setOrderRequestNumber("EI804,0,2");
            l_ifoOrderUnitParams5.setOrderConditionType("0"); // 発注条件が「1：逆指値」ではない
            
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams2);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams3);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams4);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams5);
            
            
            HostFotypeOrderAllRow[] l_returnHostFotypeOrderAllRow =
                WEB3AdminIfoDataManager.getOrderTypeEachMarketNotOrder(l_institution, WEB3AdminIfoOrderTypeDef.CREATE_NEW);
            
            assertEquals(2, l_returnHostFotypeOrderAllRow.length);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    /**
     * (1)引数の注文種別　@== ’2:訂正’の場合
     * (2)DBを検索し、検索結果取得できる場合、発注中の逆指値注文　@或いは、切替中のＷ指値注文　@の削除処理
     *    要素数分のLoop処理を開始する。 
     *    Loop要素の「口座ＩＤ」と「識別コード」によって、注文単位を取得する。
     *    取得した注文単位の発注条件が「2：W指値」かつ、取得した注文単位のリクエストタイプが「時価サーバ」の場合、Loop処理のcontinue。 
     *    以外の場合、 該当要素を返却用listに追加して格納する。
     *    Loop終了、返却用listを先物OP注文取引キューrow[]にキャストする。 
     *    受注日時をキーに昇順にソートする。 （asc：昇順） 
     *    処理したものを先物OP注文取引キューrow[]にキャストして返却する。
     * 
     * ・証券会社コード = 引数の証券会社の証券会社コード 
     * ・受注日時　@>= 引数の基準時間
     * ・データコード in ”EI804”,”EI802”
     * ・取消区分 = ”0”
     * ・処理区分 in "0"."1"."2"
     */
    public void testGetOrderTypeEachMarketNotOrder_case0004()
    {
        String STR_METHOD_NAME = "testGetOrderTypeEachMarketNotOrder_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            Institution l_institution = new InstitutionImpl(l_institutionParams);
            
            // 引数の基準時間
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            long l_lngBaseTimeMillis = l_lngCurrentTimeMillis - 5000;
            Date l_datBaseTime = new Timestamp(l_lngBaseTimeMillis);
            
            
            // 先物OP注文取引キュー
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 1000));
            l_hostFotypeOrderAllParams1.setRequestCode("EI802");
            l_hostFotypeOrderAllParams1.setCancelDiv("0");
            l_hostFotypeOrderAllParams1.setStatus("0");
            l_hostFotypeOrderAllParams1.setCorpCode("EI802,0,0"); 
            // 取得した注文単位の発注条件が「2：W指値」かつ、取得した注文単位のリクエストタイプが「時価サーバ」
            l_hostFotypeOrderAllParams1.setAccountId(111111);
            l_hostFotypeOrderAllParams1.setOrderRequestNumber("EI802,0,0"); //
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams2.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 2000)); 
            l_hostFotypeOrderAllParams2.setRequestCode("EI804");
            l_hostFotypeOrderAllParams2.setCancelDiv("0");
            l_hostFotypeOrderAllParams2.setStatus("1");
            l_hostFotypeOrderAllParams2.setCorpCode("EI804,0,1");
            // 取得した注文単位の発注条件が「2：W指値」ではない、
            // または、取得した注文単位のリクエストタイプが「時価サーバ」ではない
            l_hostFotypeOrderAllParams2.setAccountId(222222); 
            l_hostFotypeOrderAllParams2.setOrderRequestNumber("EI804,0,1"); //
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams3.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 4000));
            l_hostFotypeOrderAllParams3.setRequestCode("EI802"); 
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setStatus("2");
            l_hostFotypeOrderAllParams3.setCorpCode("EI802,0,2");
            // 取得した注文単位の発注条件が「2：W指値」ではない、
            // または、取得した注文単位のリクエストタイプが「時価サーバ」ではない
            l_hostFotypeOrderAllParams3.setAccountId(333333); 
            l_hostFotypeOrderAllParams3.setOrderRequestNumber("EI802,0,2"); //

            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams4.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804"); 
            l_hostFotypeOrderAllParams4.setCancelDiv("0");
            l_hostFotypeOrderAllParams4.setStatus("0");
            l_hostFotypeOrderAllParams4.setCorpCode("EI804,0,0");
            // 取得した注文単位の発注条件が「2：W指値」ではない、
            // または、取得した注文単位のリクエストタイプが「時価サーバ」ではない
            l_hostFotypeOrderAllParams4.setAccountId(444444); 
            l_hostFotypeOrderAllParams4.setOrderRequestNumber("EI804,0,0"); //
            
            
            // wrong data
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams5.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams5.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams5.setRequestCode("EI804"); 
            l_hostFotypeOrderAllParams5.setCancelDiv("1");
            l_hostFotypeOrderAllParams5.setStatus("2");
            l_hostFotypeOrderAllParams5.setCorpCode("EI804,1,2");
            l_hostFotypeOrderAllParams5.setAccountId(555555); 
            l_hostFotypeOrderAllParams5.setOrderRequestNumber("EI804,1,2");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams6.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams6.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams6.setRequestCode("EI801"); 
            l_hostFotypeOrderAllParams6.setCancelDiv("0");
            l_hostFotypeOrderAllParams6.setStatus("0");
            l_hostFotypeOrderAllParams6.setCorpCode("EI801,0,0");
            l_hostFotypeOrderAllParams6.setAccountId(666666); 
            l_hostFotypeOrderAllParams6.setOrderRequestNumber("EI801,0,0");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams7 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams7.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams7.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams7.setRequestCode("EI803"); 
            l_hostFotypeOrderAllParams7.setCancelDiv("0");
            l_hostFotypeOrderAllParams7.setStatus("2");
            l_hostFotypeOrderAllParams7.setCorpCode("EI803,0,2");
            l_hostFotypeOrderAllParams7.setAccountId(777777); 
            l_hostFotypeOrderAllParams7.setOrderRequestNumber("EI803,0,2");
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams5);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams6);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams7);
            
            
            // 注文単位テーブル
            IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams1.setOrderUnitId(1001);
            l_ifoOrderUnitParams1.setAccountId(111111);
            l_ifoOrderUnitParams1.setOrderRequestNumber("EI802,0,0");
            l_ifoOrderUnitParams1.setOrderConditionType("2"); // 発注条件が「2：W指値」
            l_ifoOrderUnitParams1.setRequestType("1"); // かつ、リクエストタイプが「時価サーバ」
            
            IfoOrderUnitParams l_ifoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams2.setOrderUnitId(1002);
            l_ifoOrderUnitParams2.setAccountId(222222);
            l_ifoOrderUnitParams2.setOrderRequestNumber("EI804,0,1");
            l_ifoOrderUnitParams2.setOrderConditionType("0"); // 発注条件が「2：W指値」ではない
            l_ifoOrderUnitParams2.setRequestType("1"); // リクエストタイプが「時価サーバ」
            
            IfoOrderUnitParams l_ifoOrderUnitParams3 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams3.setOrderUnitId(1003);
            l_ifoOrderUnitParams3.setAccountId(333333);
            l_ifoOrderUnitParams3.setOrderRequestNumber("EI802,0,2");
            l_ifoOrderUnitParams3.setOrderConditionType("2"); // 発注条件が「2：W指値」
            l_ifoOrderUnitParams3.setRequestType("0"); // リクエストタイプが「時価サーバ」ではない
            
            IfoOrderUnitParams l_ifoOrderUnitParams4 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams4.setOrderUnitId(1004);
            l_ifoOrderUnitParams4.setAccountId(444444);
            l_ifoOrderUnitParams4.setOrderRequestNumber("EI804,0,0");
            l_ifoOrderUnitParams4.setOrderConditionType("1"); // 発注条件が「2：W指値」ではない
            l_ifoOrderUnitParams4.setRequestType("2"); // リクエストタイプが「時価サーバ」ではない
            
            // wrong data
            IfoOrderUnitParams l_ifoOrderUnitParams5 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams5.setOrderUnitId(1005);
            l_ifoOrderUnitParams5.setAccountId(555555);
            l_ifoOrderUnitParams5.setOrderRequestNumber("EI804,1,2");
            l_ifoOrderUnitParams5.setOrderConditionType("1"); 
            l_ifoOrderUnitParams5.setRequestType("2");
            
            IfoOrderUnitParams l_ifoOrderUnitParams6 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams6.setOrderUnitId(1006);
            l_ifoOrderUnitParams6.setAccountId(666666);
            l_ifoOrderUnitParams6.setOrderRequestNumber("EI801,0,0");
            l_ifoOrderUnitParams6.setOrderConditionType("1"); 
            l_ifoOrderUnitParams6.setRequestType("2");
            
            IfoOrderUnitParams l_ifoOrderUnitParams7 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams7.setOrderUnitId(1007);
            l_ifoOrderUnitParams7.setAccountId(777777);
            l_ifoOrderUnitParams7.setOrderRequestNumber("EI803,0,2");
            l_ifoOrderUnitParams7.setOrderConditionType("1"); 
            l_ifoOrderUnitParams7.setRequestType("2");            
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams2);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams3);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams4);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams5);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams6);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams7);
            
            HostFotypeOrderAllRow[] l_returnHostFotypeOrderAllRow =
                WEB3AdminIfoDataManager.getOrderTypeEachMarketNotOrder(l_institution, WEB3AdminIfoOrderTypeDef.MODIFY);
            
            assertEquals(3, l_returnHostFotypeOrderAllRow.length);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    
    // getBaseTime
    /**
     * (1)引数の注文種別　@== ’1:新規’
     * (2)先物OP注文取引キューテーブルを検索し、検索結果が取得できない場合、nullを返却する。 
     */
    public void testGetBaseTime_case0001()
    {
        String STR_METHOD_NAME = "testGetBaseTime_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams1.setRequestCode("EI803");
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            l_hostFotypeOrderAllParams1.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams2.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 1000));
            l_hostFotypeOrderAllParams2.setRequestCode("EI801");
            l_hostFotypeOrderAllParams2.setCancelDiv("1");
            l_hostFotypeOrderAllParams2.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams3.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 2000));
            l_hostFotypeOrderAllParams3.setRequestCode("EI802");
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams4.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 3000));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804");
            l_hostFotypeOrderAllParams4.setCancelDiv("1");
            l_hostFotypeOrderAllParams4.setCorpCode("aaa");
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            
            Date l_datBaseTime = WEB3AdminIfoDataManager.getBaseTime(l_institution, "aaa", WEB3AdminIfoOrderTypeDef.CREATE_NEW);
            assertEquals(null, l_datBaseTime);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    /**
     * (1)引数の注文種別　@== ’2:訂正’
     * (2)先物OP注文取引キューテーブルを検索し、検索結果の0番目の要素を取得して、該当要素の受注日時を基準時間として返却する。 
     */
    public void testGetBaseTime_case0002()
    {
        String STR_METHOD_NAME = "testGetBaseTime_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams1.setRequestCode("EI803");
            l_hostFotypeOrderAllParams1.setCancelDiv("0");
            l_hostFotypeOrderAllParams1.setCorpCode("aaa");
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("1");//
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams2.setReceivedDateTime(new Timestamp(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams2.setRequestCode("EI802");
            l_hostFotypeOrderAllParams2.setCancelDiv("0");
            l_hostFotypeOrderAllParams2.setCorpCode("aaa");
            l_hostFotypeOrderAllParams2.setAllOrderChangeDiv("1");//
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams3.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 2000));
            l_hostFotypeOrderAllParams3.setRequestCode("EI804");
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setCorpCode("aaa");
            l_hostFotypeOrderAllParams3.setAllOrderChangeDiv("0");//
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams4.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 3000));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804");
            l_hostFotypeOrderAllParams4.setCancelDiv("1");
            l_hostFotypeOrderAllParams4.setCorpCode("aaa");
            l_hostFotypeOrderAllParams4.setAllOrderChangeDiv("1");//
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            
            Date l_datBaseTime = WEB3AdminIfoDataManager.getBaseTime(l_institution, "aaa", WEB3AdminIfoOrderTypeDef.MODIFY);
            int l_intCompare = WEB3DateUtility.compareToSecond(l_datBaseTime, new Timestamp(l_lngCurrentTimeMillis));
            assertEquals(0, l_intCompare);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    /**
     * (1)引数の注文種別　@== ’3:取消’
     * (2)先物OP注文取引キューテーブルを検索し、検索結果の0番目の要素を取得して、該当要素の受注日時を基準時間として返却する。 
     */
    public void testGetBaseTime_case0003()
    {
        String STR_METHOD_NAME = "testGetBaseTime_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams1.setRequestCode("EI803");
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            l_hostFotypeOrderAllParams1.setCorpCode("aaa");
            l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("1");//
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams2.setReceivedDateTime(new Timestamp(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams2.setRequestCode("EI802");
            l_hostFotypeOrderAllParams2.setCancelDiv("1");
            l_hostFotypeOrderAllParams2.setCorpCode("aaa");
            l_hostFotypeOrderAllParams2.setAllOrderChangeDiv("1");//
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams3.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams3.setRequestCode("EI804");
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setCorpCode("aaa");
            l_hostFotypeOrderAllParams3.setAllOrderChangeDiv("0");//
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams4.setReceivedDateTime(new Date(l_lngCurrentTimeMillis + 3000));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804");
            l_hostFotypeOrderAllParams4.setCancelDiv("1");
            l_hostFotypeOrderAllParams4.setCorpCode("aaa");
            l_hostFotypeOrderAllParams4.setAllOrderChangeDiv("0");//
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            
            Date l_datBaseTime = WEB3AdminIfoDataManager.getBaseTime(l_institution, "aaa", WEB3AdminIfoOrderTypeDef.CANCEL);
            int l_intCompare = WEB3DateUtility.compareToSecond(l_datBaseTime, new Timestamp(l_lngCurrentTimeMillis + 3000)); 
            assertEquals(0, l_intCompare);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    
//    // getCorpCodes
//    /**
//     * 引数の先物OP注文取引キュー一覧の要素数分のLoop処理し、
//     * 社内処理項目ストリング　@＝　@社内処理項目ストリング　@＋　@"、"　@＋　@（２）のLoop要素の社内処理項目 
//     * Loop終了。 社内処理項目ストリングを返却する。
//     */
//    public void testGetCorpCodes_case0001()
//    {
//        String STR_METHOD_NAME = "testGetCorpCodes_case0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            Date l_datBaseTime =
//                WEB3DateUtility.getDate("2010-11-3 11:05:38", "yyyy-MM-dd HH:mm:ss");
//            
//            // 先物OP注文取引キューテーブル
//            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
//            l_hostFotypeOrderAllParams1.setReceivedDateTime(
//                WEB3DateUtility.getDate("2010-11-3 11:05:38", "yyyy-MM-dd HH:mm:ss"));
//            l_hostFotypeOrderAllParams1.setCorpCode("right1");
//            
//            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
//            l_hostFotypeOrderAllParams2.setReceivedDateTime(
//                WEB3DateUtility.getDate("2010-11-3 11:05:38", "yyyy-MM-dd HH:mm:ss"));
//            l_hostFotypeOrderAllParams2.setCorpCode("right2");
//            
//            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
//            l_hostFotypeOrderAllParams3.setReceivedDateTime(
//                WEB3DateUtility.getDate("2010-11-3 11:08:38", "yyyy-MM-dd HH:mm:ss"));
//            l_hostFotypeOrderAllParams3.setCorpCode("wrong1");
//            
//            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
//            l_hostFotypeOrderAllParams4.setReceivedDateTime(
//                WEB3DateUtility.getDate("2010-11-3 11:05:38", "yyyy-MM-dd HH:mm:ss"));
//            l_hostFotypeOrderAllParams4.setCorpCode("right3");
//            
//            HostFotypeOrderAllParams l_hostFotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
//            l_hostFotypeOrderAllParams5.setReceivedDateTime(
//                WEB3DateUtility.getDate("2010-11-3 12:05:38", "yyyy-MM-dd HH:mm:ss"));
//            l_hostFotypeOrderAllParams5.setCorpCode("wrong2");
//            
//            List l_lstHostFotypeOrderAllParams = new ArrayList();
//            l_lstHostFotypeOrderAllParams.add(l_hostFotypeOrderAllParams1);
//            l_lstHostFotypeOrderAllParams.add(l_hostFotypeOrderAllParams2);
//            l_lstHostFotypeOrderAllParams.add(l_hostFotypeOrderAllParams3);
//            l_lstHostFotypeOrderAllParams.add(l_hostFotypeOrderAllParams4);
//            l_lstHostFotypeOrderAllParams.add(l_hostFotypeOrderAllParams5);
//            
//            HostFotypeOrderAllParams[] l_arrayHostFotypeOrderAllParams = new HostFotypeOrderAllParams[5];
//            l_lstHostFotypeOrderAllParams.toArray(l_arrayHostFotypeOrderAllParams);
//            
//            String l_returnCorpCodes = WEB3AdminIfoDataManager.getCorpCodes(l_datBaseTime, l_arrayHostFotypeOrderAllParams, "" ,null);
//           
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals("right1、right2、right3", l_returnCorpCodes);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }
//    
//    /**
//     * 引数の先物OP注文取引キュー一覧の要素数分のLoop処理し、
//     * Loop要素の「受注日時」　@！＝　@引数の基準時間の場合、nullを返却する。
//     */
//    public void testGetCorpCodes_case0002()
//    {
//        String STR_METHOD_NAME = "testGetCorpCodes_case0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            Date l_datBaseTime =
//                WEB3DateUtility.getDate("2012-11-5 11:04:50", "yyyy-MM-dd HH:mm:ss");
//            
//            // 先物OP注文取引キューテーブル
//            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
//            l_hostFotypeOrderAllParams1.setReceivedDateTime(
//                WEB3DateUtility.getDate("2010-11-3 11:05:38", "yyyy-MM-dd HH:mm:ss"));
//            l_hostFotypeOrderAllParams1.setCorpCode("wrong1");
//            
//            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
//            l_hostFotypeOrderAllParams2.setReceivedDateTime(
//                WEB3DateUtility.getDate("2010-11-3 11:05:38", "yyyy-MM-dd HH:mm:ss"));
//            l_hostFotypeOrderAllParams2.setCorpCode("wrong2");
//            
//            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
//            l_hostFotypeOrderAllParams3.setReceivedDateTime(
//                WEB3DateUtility.getDate("2010-11-3 11:08:38", "yyyy-MM-dd HH:mm:ss"));
//            l_hostFotypeOrderAllParams3.setCorpCode("wrong3");
//            
//            List l_lstHostFotypeOrderAllParams = new ArrayList();
//            l_lstHostFotypeOrderAllParams.add(l_hostFotypeOrderAllParams1);
//            l_lstHostFotypeOrderAllParams.add(l_hostFotypeOrderAllParams2);
//            l_lstHostFotypeOrderAllParams.add(l_hostFotypeOrderAllParams3);
//            
//            HostFotypeOrderAllParams[] l_arrayHostFotypeOrderAllParams = new HostFotypeOrderAllParams[3];
//            l_lstHostFotypeOrderAllParams.toArray(l_arrayHostFotypeOrderAllParams);
//            
//            String l_returnCorpCodes = WEB3AdminIfoDataManager.getCorpCodes(l_datBaseTime, l_arrayHostFotypeOrderAllParams,"", null);
//           
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            assertEquals(null, l_returnCorpCodes);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }
//    
    
    // getVirtualServerInfo
    /**
     * (1)仮想サーバ情報テーブルを検索し、
     * (2)検索結果が取得できない場合、 nullを返却する。
     */
    public void testGetVirtualServerInfo_case0001()
    {
        String STR_METHOD_NAME = "testGetVirtualServerInfo_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            VirtualServerInformationParams l_virtualServerInformationParams1 = new VirtualServerInformationParams();
            l_virtualServerInformationParams1.setVirtualServerNumberJsoes("0000001");
            l_virtualServerInformationParams1.setVirtualServerNumberMarket("0000001");
            l_virtualServerInformationParams1.setInstitutionCode("0E");
            l_virtualServerInformationParams1.setFrontOrderExchangeCode("2");
            l_virtualServerInformationParams1.setProductType(ProductTypeEnum.IFO);
            
            VirtualServerInformationParams l_virtualServerInformationParams2 = new VirtualServerInformationParams();
            l_virtualServerInformationParams2.setVirtualServerNumberJsoes("0000002");
            l_virtualServerInformationParams2.setVirtualServerNumberMarket("0000002");
            l_virtualServerInformationParams2.setInstitutionCode("0D");
            l_virtualServerInformationParams2.setFrontOrderExchangeCode("2");
            l_virtualServerInformationParams2.setProductType(ProductTypeEnum.AIO);
            
            VirtualServerInformationParams l_virtualServerInformationParams3 = new VirtualServerInformationParams();
            l_virtualServerInformationParams3.setVirtualServerNumberJsoes("0000003");
            l_virtualServerInformationParams3.setVirtualServerNumberMarket("0000003");
            l_virtualServerInformationParams3.setInstitutionCode("0D");
            l_virtualServerInformationParams3.setFrontOrderExchangeCode("1");
            l_virtualServerInformationParams3.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.deleteAllAndCommit(VirtualServerInformationParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_virtualServerInformationParams1);
            TestDBUtility.insertWithDelAndCommit(l_virtualServerInformationParams2);
            TestDBUtility.insertWithDelAndCommit(l_virtualServerInformationParams3);
            
            VirtualServerInformationRow l_virtualServerInformationRow =
                WEB3AdminIfoDataManager.getVirtualServerInfo(l_institution);
            
            assertEquals(null, l_virtualServerInformationRow);            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(VirtualServerInformationParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    
    // getVirtualServerInfo
    /**
     * (1)仮想サーバ情報テーブルを検索し、
     * (2)検索結果取得できる場合、検索結果の0番目の要素を取得して、仮想サーバ情報Rowにキャストして返却する。 
     */
    public void testGetVirtualServerInfo_case0002()
    {
        String STR_METHOD_NAME = "testGetVirtualServerInfo_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            VirtualServerInformationParams l_virtualServerInformationParams1 = new VirtualServerInformationParams();
            l_virtualServerInformationParams1.setVirtualServerNumberJsoes("0000001");
            l_virtualServerInformationParams1.setVirtualServerNumberMarket("0000001");
            l_virtualServerInformationParams1.setInstitutionCode("0D");
            l_virtualServerInformationParams1.setFrontOrderExchangeCode("2");
            l_virtualServerInformationParams1.setProductType(ProductTypeEnum.IFO);
            
            VirtualServerInformationParams l_virtualServerInformationParams2 = new VirtualServerInformationParams();
            l_virtualServerInformationParams2.setVirtualServerNumberJsoes("0000002");
            l_virtualServerInformationParams2.setVirtualServerNumberMarket("0000002");
            l_virtualServerInformationParams2.setInstitutionCode("0D");
            l_virtualServerInformationParams2.setFrontOrderExchangeCode("2");
            l_virtualServerInformationParams2.setProductType(ProductTypeEnum.IFO);
            
            VirtualServerInformationParams l_virtualServerInformationParams3 = new VirtualServerInformationParams();
            l_virtualServerInformationParams3.setVirtualServerNumberJsoes("0000003");
            l_virtualServerInformationParams3.setVirtualServerNumberMarket("0000003");
            l_virtualServerInformationParams3.setInstitutionCode("0D");
            l_virtualServerInformationParams3.setFrontOrderExchangeCode("2");
            l_virtualServerInformationParams3.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.deleteAllAndCommit(VirtualServerInformationParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_virtualServerInformationParams1);
            TestDBUtility.insertWithDelAndCommit(l_virtualServerInformationParams2);
            TestDBUtility.insertWithDelAndCommit(l_virtualServerInformationParams3);
            
            VirtualServerInformationRow l_virtualServerInformationRow =
                WEB3AdminIfoDataManager.getVirtualServerInfo(l_institution);
            
            assertEquals("0000001", l_virtualServerInformationRow.getVirtualServerNumberJsoes());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(VirtualServerInformationParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    
    // getOrderType
    /**
     * 引数の先物OP注文取引キューのデータコードin「"EI803", "EI801"」の場合、「1：新規」を返却する。 
     */
    public void testGetOrderType_case0001()
    {
        String STR_METHOD_NAME = "testGetOrderType_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        //
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams1.setRequestCode("EI803");
        l_hostFotypeOrderAllParams1.setCancelDiv("0");
        
        String l_strReturnOrderType1 = WEB3AdminIfoDataManager.getOrderType(l_hostFotypeOrderAllParams1);
        assertEquals("1", l_strReturnOrderType1);
        
        //
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams2.setRequestCode("EI801");
        l_hostFotypeOrderAllParams2.setCancelDiv("1");
        
        String l_strReturnOrderType2 = WEB3AdminIfoDataManager.getOrderType(l_hostFotypeOrderAllParams2);
        assertEquals("1", l_strReturnOrderType2);
        
        //
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams3.setRequestCode("EI808");
        l_hostFotypeOrderAllParams3.setCancelDiv("0");
        
        String l_strReturnOrderType3 = WEB3AdminIfoDataManager.getOrderType(l_hostFotypeOrderAllParams3);
        assertEquals(null, l_strReturnOrderType3);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 引数の先物OP注文取引キューのデータコードin「"EI804", "EI802"」　@かつ
     * 引数の先物OP注文取引キューの取消区分が「0：取消以外」の場合、「2：訂正」を返却する。
     */
    public void testGetOrderType_case0002()
    {
        String STR_METHOD_NAME = "testGetOrderType_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        //
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams1.setRequestCode("EI804");
        l_hostFotypeOrderAllParams1.setCancelDiv("0");
        
        String l_strReturnOrderType1 = WEB3AdminIfoDataManager.getOrderType(l_hostFotypeOrderAllParams1);
        assertEquals("2", l_strReturnOrderType1);
        
        //
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams2.setRequestCode("EI802");
        l_hostFotypeOrderAllParams2.setCancelDiv("1");
        
        String l_strReturnOrderType2 = WEB3AdminIfoDataManager.getOrderType(l_hostFotypeOrderAllParams2);
        assertEquals("3", l_strReturnOrderType2);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 引数の先物OP注文取引キューのデータコードin「"EI804", "EI802"」　@かつ
     * 引数の先物OP注文取引キューの取消区分が「1：取消」の場合、「3：取消」を返却する。
     */
    public void testGetOrderType_case0003()
    {
        String STR_METHOD_NAME = "testGetOrderType_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        //
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams1.setRequestCode("EI804");
        l_hostFotypeOrderAllParams1.setCancelDiv("1");
        
        String l_strReturnOrderType1 = WEB3AdminIfoDataManager.getOrderType(l_hostFotypeOrderAllParams1);
        assertEquals("3", l_strReturnOrderType1);
        
        //
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams2.setRequestCode("EI802");
        l_hostFotypeOrderAllParams2.setCancelDiv("0");
        
        String l_strReturnOrderType2 = WEB3AdminIfoDataManager.getOrderType(l_hostFotypeOrderAllParams2);
        assertEquals("2", l_strReturnOrderType2);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getManageUser
     * 証券会社プリファ@レンステーブルの管理ユーザ利用を取得し返却する。
     * 「0：利用しない」、「1：利用する」
     * [条件] 
     * 証券会社ID ＝　@引数の証券会社の証券会社ID 
     * プリファ@レンス項目名 = プリファ@レンス名.管理ユーザ利用 
     * 検索結果からプリファ@レンスの値を返却する。 
     */
    public void testGetManageUsercase0001()
    {
        String STR_METHOD_NAME = "testGetManageUsercase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("ifo.order.download.manager.div");
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            String l_strManageUser = WEB3AdminIfoDataManager.getManageUser(l_institution);
            assertEquals("1", l_strManageUser);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getManageUser
     * 証券会社プリファ@レンステーブルの管理ユーザ利用を取得し返却する。
     * 「0：利用しない」、「1：利用する」
     * [条件] 
     * 証券会社ID ＝　@引数の証券会社の証券会社ID 
     * プリファ@レンス項目名 = プリファ@レンス名.管理ユーザ利用 
     * 「0：利用しない」を返却する。 
     */
    public void testGetManageUsercase0002()
    {
        String STR_METHOD_NAME = "testGetManageUsercase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(111111);
            l_institutionPreferencesParams.setName("ifo.order.download.manager.div");
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            String l_strManageUser = WEB3AdminIfoDataManager.getManageUser(l_institution);
            assertEquals("0", l_strManageUser);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getMarketFirstOpenTime
     *
     */
    public void testGetMarketFirstOpenTimecase0001()
    {
        String STR_METHOD_NAME = "testGetMarketFirstOpenTimecase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setMarketCode("0");
            l_TradingTimeParams.setTradingTimeType("11");
            l_TradingTimeParams.setSubmitMarketTrigger("1");
            l_TradingTimeParams.setStartTime("164105");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("system.bizdate");
            l_systemPreferencesParams.setValue("20101126");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            String l_strManageUser = WEB3AdminIfoDataManager.getMarketFirstOpenTime(l_institution);
            assertEquals("20101126164105", l_strManageUser);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetMarketFirstOpenTimecase0002()
    {
        String STR_METHOD_NAME = "testGetMarketFirstOpenTimecase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("1D");
            l_TradingTimeParams.setMarketCode("0");
            l_TradingTimeParams.setTradingTimeType("11");
            l_TradingTimeParams.setSubmitMarketTrigger("1");
            l_TradingTimeParams.setStartTime("164105");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("system.bizdate");
            l_systemPreferencesParams.setValue("20101126");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            String l_strManageUser = WEB3AdminIfoDataManager.getMarketFirstOpenTime(l_institution);
            assertEquals("20101126075900", l_strManageUser);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * getAMGSendBaseTime
     *
     */
    public void testGetAMGSendBaseTimecase0001()
    {
        String STR_METHOD_NAME = "testGetAMGSendBaseTimecase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("20101010", "yyyyMMdd"));
            l_hostFotypeOrderAllParams1.setRequestCode("EI803");
            l_hostFotypeOrderAllParams1.setCancelDiv("0");
            l_hostFotypeOrderAllParams1.setCorpCode("aaa");
            l_hostFotypeOrderAllParams1.setAmgSendTime(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            l_hostFotypeOrderAllParams2.setRequestCode("EI801");
            l_hostFotypeOrderAllParams2.setCancelDiv("0");
            l_hostFotypeOrderAllParams2.setCorpCode("aaa");
            l_hostFotypeOrderAllParams2.setAmgSendTime(WEB3DateUtility.getDate("20101211", "yyyyMMdd"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams3.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 2000));
            l_hostFotypeOrderAllParams3.setRequestCode("EI802");
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams4.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 3000));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804");
            l_hostFotypeOrderAllParams4.setCancelDiv("1");
            l_hostFotypeOrderAllParams4.setCorpCode("aaa");
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            
            Date l_datAMGSendBaseTime = WEB3AdminIfoDataManager.getAMGSendBaseTime(l_institution, "aaa", "1");
            assertEquals(0,
            WEB3DateUtility.compare(l_datAMGSendBaseTime, WEB3DateUtility.getDate("20101211", "yyyyMMdd")));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    public void testGetAMGSendBaseTimecase0002()
    {
        String STR_METHOD_NAME = "testGetAMGSendBaseTimecase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("20101010", "yyyyMMdd"));
            l_hostFotypeOrderAllParams1.setRequestCode("EI803");
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            l_hostFotypeOrderAllParams1.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            l_hostFotypeOrderAllParams2.setRequestCode("EI801");
            l_hostFotypeOrderAllParams2.setCancelDiv("1");
            l_hostFotypeOrderAllParams2.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("20101015", "yyyyMMdd"));
            l_hostFotypeOrderAllParams3.setRequestCode("EI802");
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setCorpCode("aaa");
            l_hostFotypeOrderAllParams3.setAmgSendTime(WEB3DateUtility.getDate("20101212", "yyyyMMdd"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("20101111", "yyyyMMdd"));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804");
            l_hostFotypeOrderAllParams4.setCancelDiv("1");
            l_hostFotypeOrderAllParams4.setCorpCode("aaa");
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            
            Date l_datAMGSendBaseTime = WEB3AdminIfoDataManager.getAMGSendBaseTime(l_institution, "aaa", "2");
            assertEquals(0,
            WEB3DateUtility.compare(l_datAMGSendBaseTime, WEB3DateUtility.getDate("20101212", "yyyyMMdd")));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    public void testGetAMGSendBaseTimecase0003()
    {
        String STR_METHOD_NAME = "testGetAMGSendBaseTimecase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("20101010", "yyyyMMdd"));
            l_hostFotypeOrderAllParams1.setRequestCode("EI803");
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            l_hostFotypeOrderAllParams1.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            l_hostFotypeOrderAllParams2.setRequestCode("EI801");
            l_hostFotypeOrderAllParams2.setCancelDiv("1");
            l_hostFotypeOrderAllParams2.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("20101015", "yyyyMMdd"));
            l_hostFotypeOrderAllParams3.setRequestCode("EI802");
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("20101111", "yyyyMMdd"));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804");
            l_hostFotypeOrderAllParams4.setCancelDiv("1");
            l_hostFotypeOrderAllParams4.setCorpCode("aaa");
            l_hostFotypeOrderAllParams4.setAmgSendTime(WEB3DateUtility.getDate("20101214", "yyyyMMdd"));
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            
            Date l_datAMGSendBaseTime = WEB3AdminIfoDataManager.getAMGSendBaseTime(l_institution, "aaa", "3");
            assertEquals(0,
            WEB3DateUtility.compare(l_datAMGSendBaseTime, WEB3DateUtility.getDate("20101214", "yyyyMMdd")));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    public void testGetAMGSendBaseTimecase0004()
    {
        String STR_METHOD_NAME = "testGetAMGSendBaseTimecase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            // 引数の証券会社
            // InstitutionCode("0D");
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            long l_lngCurrentTimeMillis = System.currentTimeMillis();
            
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(new Date(l_lngCurrentTimeMillis));
            l_hostFotypeOrderAllParams1.setRequestCode("EI803");
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            l_hostFotypeOrderAllParams1.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams2.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 1000));
            l_hostFotypeOrderAllParams2.setRequestCode("EI801");
            l_hostFotypeOrderAllParams2.setCancelDiv("1");
            l_hostFotypeOrderAllParams2.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams3.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 2000));
            l_hostFotypeOrderAllParams3.setRequestCode("EI802");
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams4.setReceivedDateTime(new Date(l_lngCurrentTimeMillis - 3000));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804");
            l_hostFotypeOrderAllParams4.setCancelDiv("1");
            l_hostFotypeOrderAllParams4.setCorpCode("aaa");
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams2);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams3);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams4);
            
            Date l_datBaseTime = WEB3AdminIfoDataManager.getAMGSendBaseTime(l_institution, "aaa", "1");
            assertEquals(null, l_datBaseTime);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
    }
    
    public void testGetCorpCodescase0001()
    {
        String STR_METHOD_NAME = "testGetCorpCodescase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("20101214", "yyyyMMdd"));
            l_hostFotypeOrderAllParams1.setRequestCode("EI803");
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            l_hostFotypeOrderAllParams1.setCorpCode("000001");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("20101214", "yyyyMMdd"));
            l_hostFotypeOrderAllParams2.setRequestCode("EI801");
            l_hostFotypeOrderAllParams2.setCancelDiv("1");
            l_hostFotypeOrderAllParams2.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("20101214", "yyyyMMdd"));
            l_hostFotypeOrderAllParams3.setRequestCode("EI802");
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setCorpCode("aaa");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("20101214", "yyyyMMdd"));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804");
            l_hostFotypeOrderAllParams4.setCancelDiv("1");
            l_hostFotypeOrderAllParams4.setCorpCode("aaa");
            
            HostFotypeOrderAllParams[] l_params = new HostFotypeOrderAllParams[1];
            l_params[0] = l_hostFotypeOrderAllParams1;
            String l_strCorpCodes = WEB3AdminIfoDataManager.getCorpCodes(
                    WEB3DateUtility.getDate("20101216", "yyyyMMdd"), l_params,
                    "20101129112233",
                    WEB3DateUtility.getDate("20101214", "yyyyMMdd"));
            assertEquals("000001", l_strCorpCodes);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetCorpCodescase0002()
    {
        String STR_METHOD_NAME = "testGetCorpCodescase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams1.setRequestCode("EI803");
            l_hostFotypeOrderAllParams1.setCancelDiv("1");
            l_hostFotypeOrderAllParams1.setCorpCode("000001");
            l_hostFotypeOrderAllParams1.setStatus("1");
            l_hostFotypeOrderAllParams1.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("20101216111111", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams2.setRequestCode("EI801");
            l_hostFotypeOrderAllParams2.setCancelDiv("1");
            l_hostFotypeOrderAllParams2.setCorpCode("000002");
            l_hostFotypeOrderAllParams2.setStatus("2");
            l_hostFotypeOrderAllParams2.setAmgSendTime(WEB3DateUtility.getDate("20101214111110", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams3.setRequestCode("EI802");
            l_hostFotypeOrderAllParams3.setCancelDiv("0");
            l_hostFotypeOrderAllParams3.setCorpCode("000003");
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setInstitutionCode("0D"); 
            l_hostFotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams4.setRequestCode("EI804");
            l_hostFotypeOrderAllParams4.setCancelDiv("1");
            l_hostFotypeOrderAllParams4.setCorpCode("000004");
            
            HostFotypeOrderAllParams[] l_params = new HostFotypeOrderAllParams[3];
            l_params[0] = l_hostFotypeOrderAllParams1;
            l_params[1] = l_hostFotypeOrderAllParams2;
            l_params[2] = l_hostFotypeOrderAllParams3;
            String l_strCorpCodes = WEB3AdminIfoDataManager.getCorpCodes(
                    WEB3DateUtility.getDate("20101216111111", "yyyyMMddhhmmss"), l_params,
                    "20101129112233",
                    WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            assertEquals("000001、000002、000003", l_strCorpCodes);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetCorpCodescase0003()
    {
        String STR_METHOD_NAME = "testGetCorpCodescase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("20101216111110", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams1.setStatus("1");
            l_hostFotypeOrderAllParams1.setCorpCode("000001");
            l_hostFotypeOrderAllParams1.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("20101216111113", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams2.setCorpCode("000002");
            l_hostFotypeOrderAllParams2.setStatus("2");
            l_hostFotypeOrderAllParams2.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams3.setCorpCode("000003");
            l_hostFotypeOrderAllParams3.setStatus("2");
            l_hostFotypeOrderAllParams3.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams4.setCorpCode("000004");
            l_hostFotypeOrderAllParams4.setStatus("2");
            l_hostFotypeOrderAllParams4.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams5.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams5.setCorpCode("000005");
            l_hostFotypeOrderAllParams5.setStatus("9");
            l_hostFotypeOrderAllParams5.setAmgSendTime(WEB3DateUtility.getDate("20101214111110", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams6.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams6.setCorpCode("000006");
            l_hostFotypeOrderAllParams6.setStatus("2");
            l_hostFotypeOrderAllParams6.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams7 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams7.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams7.setCorpCode("000007");
            l_hostFotypeOrderAllParams7.setStatus("2");
            l_hostFotypeOrderAllParams7.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams8 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams8.setReceivedDateTime(WEB3DateUtility.getDate("20101216111109", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams8.setCorpCode("000008");
            l_hostFotypeOrderAllParams8.setStatus("2");
            l_hostFotypeOrderAllParams8.setAmgSendTime(WEB3DateUtility.getDate("20101214111110", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams9 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams9.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams9.setCorpCode("000009");
            l_hostFotypeOrderAllParams9.setStatus("2");
            l_hostFotypeOrderAllParams9.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams10 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams10.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams10.setCorpCode("000010");
            l_hostFotypeOrderAllParams10.setStatus("2");
            l_hostFotypeOrderAllParams10.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams11 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams11.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams11.setCorpCode("000011");
            l_hostFotypeOrderAllParams11.setStatus("2");
            l_hostFotypeOrderAllParams11.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams12 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams12.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams12.setCorpCode("000012");
            l_hostFotypeOrderAllParams12.setStatus("2");
            l_hostFotypeOrderAllParams12.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams13 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams13.setReceivedDateTime(WEB3DateUtility.getDate("20101216111113", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams13.setCorpCode("000013");
            l_hostFotypeOrderAllParams13.setStatus("2");
            l_hostFotypeOrderAllParams13.setAmgSendTime(WEB3DateUtility.getDate("20101214111109", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams14 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams14.setReceivedDateTime(WEB3DateUtility.getDate("20101216111113", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams14.setCorpCode("000014");
            l_hostFotypeOrderAllParams14.setStatus("2");
            l_hostFotypeOrderAllParams14.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams15 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams15.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams15.setCorpCode("000015");
            l_hostFotypeOrderAllParams15.setStatus("2");
            l_hostFotypeOrderAllParams15.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams[] l_params = new HostFotypeOrderAllParams[16];
            l_params[0] = l_hostFotypeOrderAllParams1;//Loop要素の「受注日時」　@＜　@引数の基準時間
            l_params[1] = l_hostFotypeOrderAllParams2;
            l_params[2] = l_hostFotypeOrderAllParams3;
            l_params[3] = l_hostFotypeOrderAllParams4;
            l_params[4] = l_hostFotypeOrderAllParams5;//（Ａ-１）以外の場合、（２）のLoop処理のcontinue
            l_params[5] = l_hostFotypeOrderAllParams6;
            l_params[6] = l_hostFotypeOrderAllParams7;
            l_params[7] = l_hostFotypeOrderAllParams8;//Loop要素の「受注日時」　@＜　@引数の基準時間
            l_params[8] = l_hostFotypeOrderAllParams9;
            l_params[9] = l_hostFotypeOrderAllParams10;
            l_params[10] = l_hostFotypeOrderAllParams11;
            l_params[11] = l_hostFotypeOrderAllParams12;
            l_params[12] = l_hostFotypeOrderAllParams13;//Ａ-１-１）以外の場合、（２）のLoop処理のcontinue
            l_params[13] = l_hostFotypeOrderAllParams14;
            l_params[14] = l_hostFotypeOrderAllParams15;
            l_params[15] = l_hostFotypeOrderAllParams15;
            String l_strCorpCodes = WEB3AdminIfoDataManager.getCorpCodes(
                    WEB3DateUtility.getDate("20101216111111", "yyyyMMddhhmmss"), l_params,
                    "20101229112233",
                    WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            assertEquals("000001、000002、000003、000004、000006、000007、000008、" +
                    "000009、000010、000011、000012、000014",
                    l_strCorpCodes);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetCorpCodescase0004()
    {
        String STR_METHOD_NAME = "testGetCorpCodescase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            // 先物OP注文取引キューテーブル
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("20101216111110", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams1.setStatus("1");
            l_hostFotypeOrderAllParams1.setCorpCode("000001");
            l_hostFotypeOrderAllParams1.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams2.setCorpCode("000002");
            l_hostFotypeOrderAllParams2.setStatus("2");
            l_hostFotypeOrderAllParams2.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams3.setCorpCode("000003");
            l_hostFotypeOrderAllParams3.setStatus("2");
            l_hostFotypeOrderAllParams3.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams4.setCorpCode("000004");
            l_hostFotypeOrderAllParams4.setStatus("2");
            l_hostFotypeOrderAllParams4.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams5.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams5.setCorpCode("000005");
            l_hostFotypeOrderAllParams5.setStatus("9");
            l_hostFotypeOrderAllParams5.setAmgSendTime(WEB3DateUtility.getDate("20101214111110", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams6.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams6.setCorpCode("000006");
            l_hostFotypeOrderAllParams6.setStatus("2");
            l_hostFotypeOrderAllParams6.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams7 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams7.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams7.setCorpCode("000007");
            l_hostFotypeOrderAllParams7.setStatus("2");
            l_hostFotypeOrderAllParams7.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams8 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams8.setReceivedDateTime(WEB3DateUtility.getDate("20101216111109", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams8.setCorpCode("000008");
            l_hostFotypeOrderAllParams8.setStatus("2");
            l_hostFotypeOrderAllParams8.setAmgSendTime(WEB3DateUtility.getDate("20101214111110", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams9 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams9.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams9.setCorpCode("000009");
            l_hostFotypeOrderAllParams9.setStatus("2");
            l_hostFotypeOrderAllParams9.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams10 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams10.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams10.setCorpCode("000010");
            l_hostFotypeOrderAllParams10.setStatus("2");
            l_hostFotypeOrderAllParams10.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams11 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams11.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams11.setCorpCode("000011");
            l_hostFotypeOrderAllParams11.setStatus("2");
            l_hostFotypeOrderAllParams11.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams12 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams12.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams12.setCorpCode("000012");
            l_hostFotypeOrderAllParams12.setStatus("2");
            l_hostFotypeOrderAllParams12.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams13 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams13.setReceivedDateTime(WEB3DateUtility.getDate("20101216111113", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams13.setCorpCode("000013");
            l_hostFotypeOrderAllParams13.setStatus("2");
            l_hostFotypeOrderAllParams13.setAmgSendTime(WEB3DateUtility.getDate("20101214111109", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams14 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams14.setReceivedDateTime(WEB3DateUtility.getDate("20101216111111", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams14.setCorpCode("000014");
            l_hostFotypeOrderAllParams14.setStatus("2");
            l_hostFotypeOrderAllParams14.setAmgSendTime(WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams15 = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams15.setReceivedDateTime(WEB3DateUtility.getDate("20101216111112", "yyyyMMddhhmmss"));
            l_hostFotypeOrderAllParams15.setCorpCode("000015");
            l_hostFotypeOrderAllParams15.setStatus("2");
            l_hostFotypeOrderAllParams15.setAmgSendTime(WEB3DateUtility.getDate("20101214111112", "yyyyMMddhhmmss"));
            
            HostFotypeOrderAllParams[] l_params = new HostFotypeOrderAllParams[15];
            l_params[0] = l_hostFotypeOrderAllParams1;//Loop要素の「受注日時」　@＜　@引数の基準時間
            l_params[1] = l_hostFotypeOrderAllParams2;
            l_params[2] = l_hostFotypeOrderAllParams3;
            l_params[3] = l_hostFotypeOrderAllParams4;
            l_params[4] = l_hostFotypeOrderAllParams5;//（Ａ-１）以外の場合、（２）のLoop処理のcontinue
            l_params[5] = l_hostFotypeOrderAllParams6;
            l_params[6] = l_hostFotypeOrderAllParams7;
            l_params[7] = l_hostFotypeOrderAllParams8;//Loop要素の「受注日時」　@＜　@引数の基準時間
            l_params[8] = l_hostFotypeOrderAllParams9;
            l_params[9] = l_hostFotypeOrderAllParams10;
            l_params[10] = l_hostFotypeOrderAllParams11;
            l_params[11] = l_hostFotypeOrderAllParams12;
            l_params[12] = l_hostFotypeOrderAllParams13;//Ａ-１-１）以外の場合、（２）のLoop処理のcontinue
            l_params[13] = l_hostFotypeOrderAllParams14;
            l_params[14] = l_hostFotypeOrderAllParams15;
            String l_strCorpCodes = WEB3AdminIfoDataManager.getCorpCodes(
                    WEB3DateUtility.getDate("20101216111111", "yyyyMMddhhmmss"), l_params,
                    "20101209112233",
                    WEB3DateUtility.getDate("20101214111111", "yyyyMMddhhmmss"));
            assertEquals("000001、000002、000003、000004、000006、000007、000008、" +
                    "000009、000010、000011、000012、000014",
                    l_strCorpCodes);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
