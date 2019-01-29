head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.34.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLProductRegistControlServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録制御サービスImpl(WEB3AdminAioSLProductRegistControlServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/27 趙林鵬(中訊) 新規作成
*/

package webbroker3.aio;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.aio.data.SecurityProductDao;
import webbroker3.aio.data.SecurityProductPK;
import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLProductSearchConditions;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3AdminAioSLProductRegistControlServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductRegistControlServiceImplTest.class);

    public WEB3AdminAioSLProductRegistControlServiceImplTest(String arg0)
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
     * 引数.担保銘柄情報 == null
     * 抛異常。
     */
    public void testValidateSecurityProductSameTermCase0001()
    {
        
        final String STR_METHOD_NAME = "testValidateSecurityProductSameTermCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            List l_lisSecurityProductInfos = null;
            Date l_datTargetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            Date l_datTargetPeriodTo = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.validateSecurityProductSameTerm(
                    l_lisSecurityProductInfos,l_datTargetPeriodFrom,l_datTargetPeriodTo);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

    /**
     * 引数.適用期間to がnull
     * 担保銘柄行.get適用期間from <= 引数.適用期間from <= 9999/12/31 の場合、例外をスローする。
     * 抛異常。
     */
    public void testValidateSecurityProductSameTermCase0002()
    {
        
        final String STR_METHOD_NAME = "testValidateSecurityProductSameTermCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            String l_strValue = "product_id = ? and apply_term_from = ?";
            Object[] l_objValue = new Object[]{
                   new Long(1006169090018L), 
                   WEB3DateUtility.getDate("20071028", "yyyyMMdd")};

            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermTo(null);
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20071028", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSecurityProductInfos = l_queryProcessor.doFindAllQuery(
                    SecurityProductRow.TYPE,
                    l_strValue, l_objValue);

            Date l_datTargetPeriodFrom = WEB3DateUtility.getDate("20071029", "yyyyMMdd");
            Date l_datTargetPeriodTo = null;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.validateSecurityProductSameTerm(
                    l_lisSecurityProductInfos,l_datTargetPeriodFrom,l_datTargetPeriodTo);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02927);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * 引数.適用期間to がnull
     * 担保銘柄行.get適用期間from <= 引数.適用期間from <= 担保銘柄行.get適用期間toの場合、例外をスローする。
     * 抛異常。
     */
    public void testValidateSecurityProductSameTermCase0003()
    {
        
        final String STR_METHOD_NAME = "testValidateSecurityProductSameTermCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            String l_strValue = "product_id = ? and apply_term_from = ?";
            Object[] l_objValue = new Object[]{
                   new Long(1006169090018L), 
                   WEB3DateUtility.getDate("20071028", "yyyyMMdd")};

            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20071128", "yyyyMMdd"));
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20071028", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            SecurityProductParams l_securityProductParams1 = TestDBUtility.getSecurityProductRow();
            l_securityProductParams1.setApplyTermTo(WEB3DateUtility.getDate("20071128", "yyyyMMdd"));
            l_securityProductParams1.setApplyTermFrom(WEB3DateUtility.getDate("20071027", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams1);
            
            SecurityProductParams l_securityProductParams2 = TestDBUtility.getSecurityProductRow();
            l_securityProductParams2.setApplyTermTo(WEB3DateUtility.getDate("20071128", "yyyyMMdd"));
            l_securityProductParams2.setApplyTermFrom(WEB3DateUtility.getDate("20071026", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams2);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSecurityProductInfos = l_queryProcessor.doFindAllQuery(
                    SecurityProductRow.TYPE,
                    l_strValue, l_objValue);

            log.debug("=========================" + l_lisSecurityProductInfos.size());
            Date l_datTargetPeriodFrom = WEB3DateUtility.getDate("20071029", "yyyyMMdd");
            Date l_datTargetPeriodTo = null;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.validateSecurityProductSameTerm(
                    l_lisSecurityProductInfos,l_datTargetPeriodFrom,l_datTargetPeriodTo);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02927);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * 引数.適用期間to がnull
     * 引数.適用期間from <= 担保銘柄行.get適用期間from <= 引数.適用期間to の場合、例外をスローする。 
     * 抛異常。
     */
    public void testValidateSecurityProductSameTermCase0004()
    {
        
        final String STR_METHOD_NAME = "testValidateSecurityProductSameTermCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            String l_strValue = "product_id = ? and apply_term_from = ?";
            Object[] l_objValue = new Object[]{
                   new Long(1006169090018L), 
                   WEB3DateUtility.getDate("20071028", "yyyyMMdd")};

            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20071128", "yyyyMMdd"));
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20071028", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSecurityProductInfos = l_queryProcessor.doFindAllQuery(
                    SecurityProductRow.TYPE,
                    l_strValue, l_objValue);

            Date l_datTargetPeriodFrom = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
            Date l_datTargetPeriodTo = WEB3DateUtility.getDate("20071129", "yyyyMMdd");;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.validateSecurityProductSameTerm(
                    l_lisSecurityProductInfos,l_datTargetPeriodFrom,l_datTargetPeriodTo);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02927);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * 引数.適用期間to がnull
     * 正常通過
     */
    public void testValidateSecurityProductSameTermCase0005()
    {
        
        final String STR_METHOD_NAME = "testValidateSecurityProductSameTermCase0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            String l_strValue = "product_id = ? and apply_term_from = ?";
            Object[] l_objValue = new Object[]{
                   new Long(1006169090018L), 
                   WEB3DateUtility.getDate("20071028", "yyyyMMdd")};

            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20071128", "yyyyMMdd"));
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20071028", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSecurityProductInfos = l_queryProcessor.doFindAllQuery(
                    SecurityProductRow.TYPE,
                    l_strValue, l_objValue);

            Date l_datTargetPeriodFrom = WEB3DateUtility.getDate("20071010", "yyyyMMdd");
            Date l_datTargetPeriodTo = WEB3DateUtility.getDate("20071025", "yyyyMMdd");;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.validateSecurityProductSameTerm(
                    l_lisSecurityProductInfos,l_datTargetPeriodFrom,l_datTargetPeriodTo);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }

    /**
     * insert担保銘柄情報
     * 引数.銘柄登録情報 == null
     * 抛異常。
     */
    public void testInsertSecurityProductInfoCase0001()
    {
        
        final String STR_METHOD_NAME = "testInsertSecurityProductInfoCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            String l_strInstitutionCode = "0D";
            WEB3SLProductInfoUnit l_stockLoanProductInfo = null;
            String l_strAdministratorCode = "A111";
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.insertSecurityProductInfo(
                    l_strInstitutionCode,l_stockLoanProductInfo,l_strAdministratorCode);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * 担保銘柄テーブルに担保銘柄情報をinsrtする。
     * DB更新仕様「担保銘柄登録_担保銘柄テーブル.xls」
     * 銘柄登録情報.掛目 != null;
     */
    public void testInsertSecurityProductInfoCase0002()
    {
        
        final String STR_METHOD_NAME = "testInsertSecurityProductInfoCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            WEB3SLProductInfoUnit l_stockLoanProductInfo = new WEB3SLProductInfoUnit();
            l_stockLoanProductInfo.productId = 1010101010101L;
            l_stockLoanProductInfo.productCode = "B123";
            l_stockLoanProductInfo.productType = "2";
            l_stockLoanProductInfo.productName = "ABC";
            l_stockLoanProductInfo.qualifiedDiv = "0";
            l_stockLoanProductInfo.weight = "1234";
            l_stockLoanProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            l_stockLoanProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20071007", "yyyyMMdd");
            l_stockLoanProductInfo.reason = "ABCDEFG";
            
            String l_strAdministratorCode = "A111";
            String l_strInstitutionCode = "0D";
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.insertSecurityProductInfo(
                    l_strInstitutionCode,l_stockLoanProductInfo,l_strAdministratorCode);

            SecurityProductRow l_row = SecurityProductDao.findRowByPk(
                1010101010101L, new Timestamp(WEB3DateUtility.getDate("20070927", "yyyyMMdd").getTime()));
            
            assertEquals(l_row.getProductId(), 1010101010101L);
            assertEquals(l_row.getProductCode(), "B123");
            assertEquals(l_row.getInstitutionCode(), "0D");
            assertEquals(l_row.getProductType().intValue() + "", "2");
            assertEquals(WEB3StringTypeUtility.formatNumber(l_row.getEstimationRatio()), "1234");
            assertEquals(l_row.getFitFlg(), "0");
            assertEquals(WEB3DateUtility.formatDate(l_row.getApplyTermFrom(), "yyyyMMdd"),"20070927");
            assertEquals(WEB3DateUtility.formatDate(l_row.getApplyTermTo(), "yyyyMMdd"), "20071007");
            assertEquals(l_row.getReason(), "ABCDEFG");
            assertEquals(l_row.getLastUpdater(), "A111");
            assertEquals(WEB3DateUtility.formatDate(l_row.getCreatedTimestamp(), "yyyyMMdd"), 
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd"));
            assertEquals(WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(), "yyyyMMdd"), 
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * 担保銘柄テーブルに担保銘柄情報をinsrtする。
     * DB更新仕様「担保銘柄登録_担保銘柄テーブル.xls」
     * 銘柄登録情報.掛目 == null;
     */
    public void testInsertSecurityProductInfoCase0003()
    {
        
        final String STR_METHOD_NAME = "testInsertSecurityProductInfoCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            WEB3SLProductInfoUnit l_stockLoanProductInfo = new WEB3SLProductInfoUnit();
            l_stockLoanProductInfo.productId = 1010101010101L;
            l_stockLoanProductInfo.productCode = "B123";
            l_stockLoanProductInfo.productType = "2";
            l_stockLoanProductInfo.productName = "ABC";
            l_stockLoanProductInfo.qualifiedDiv = "0";
            l_stockLoanProductInfo.weight = null;
            l_stockLoanProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
            l_stockLoanProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20071007", "yyyyMMdd");
            l_stockLoanProductInfo.reason = "ABCDEFG";
            
            String l_strAdministratorCode = "A111";
            String l_strInstitutionCode = "0D";
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.insertSecurityProductInfo(
                    l_strInstitutionCode,l_stockLoanProductInfo,l_strAdministratorCode);

            SecurityProductRow l_row = SecurityProductDao.findRowByPk(
                1010101010101L, new Timestamp(WEB3DateUtility.getDate("20070927", "yyyyMMdd").getTime()));
            
            assertEquals(l_row.getProductId(), 1010101010101L);
            assertEquals(l_row.getProductCode(), "B123");
            assertEquals(l_row.getInstitutionCode(), "0D");
            assertEquals(l_row.getProductType().intValue() + "", "2");
            assertEquals(WEB3StringTypeUtility.formatNumber(l_row.getEstimationRatio()), "0");
            assertEquals(l_row.getFitFlg(), "0");
            assertEquals(WEB3DateUtility.formatDate(l_row.getApplyTermFrom(), "yyyyMMdd"),"20070927");
            assertEquals(WEB3DateUtility.formatDate(l_row.getApplyTermTo(), "yyyyMMdd"), "20071007");
            assertEquals(l_row.getReason(), "ABCDEFG");
            assertEquals(l_row.getLastUpdater(), "A111");
            assertEquals(WEB3DateUtility.formatDate(l_row.getCreatedTimestamp(), "yyyyMMdd"), 
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd"));
            assertEquals(WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(), "yyyyMMdd"), 
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * get担保銘柄行
     * 引数.適用期間from == null
     * 抛異常。
     */
    public void testGetSecurityProductRowCase0001()
    {
        
        final String STR_METHOD_NAME = "testGetSecurityProductRowCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            long l_lngProductId = 1010101010101L;
            Date l_datTargetPeriodFrom = null;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.getSecurityProductRow(
                    l_lngProductId,l_datTargetPeriodFrom);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * get担保銘柄行
     * 担保銘柄行を得不到。
     */
    public void testGetSecurityProductRowCase0002() 
    {
        final String STR_METHOD_NAME = "testGetSecurityProductRowCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
    
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductId(1010101010101L);
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            long l_lngProductId = 111111111111L;
            Date l_datTargetPeriodFrom = WEB3DateUtility.getDate("20070917","yyyyMMdd");
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.getSecurityProductRow(
                    l_lngProductId,l_datTargetPeriodFrom);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        
        
        /**
         * get担保銘柄行
         * 担保銘柄行を得到。
         */
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
    
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductId(1010101010101L);
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            long l_lngProductId = 1010101010101L;
            Date l_datTargetPeriodFrom = WEB3DateUtility.getDate("20070917","yyyyMMdd");
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            SecurityProductRow l_row = l_impl.getSecurityProductRow(
                l_lngProductId,l_datTargetPeriodFrom);
            
            assertEquals(l_row.getProductId(), 1010101010101L);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

    }
    
    
    /**
     * compare変更情報
     * 引数.変更前担保銘柄情報 == null
     * 抛異常。
     */
    public void testCompareChangeInfoCase0001()
    {
        
        final String STR_METHOD_NAME = "testCompareChangeInfoCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            SecurityProductRow l_changeBeforeSecurityProductInfo = null;
            WEB3SLProductInfoUnit l_changeAfterSecurityProductInfo = new WEB3SLProductInfoUnit();
            l_changeAfterSecurityProductInfo.productId = 1111111111111L;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.compareChangeInfo(
                l_changeBeforeSecurityProductInfo,l_changeAfterSecurityProductInfo);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * compare変更情報
     * 引数.変更後担保銘柄情報 == null
     * 抛異常。
     */
    public void testCompareChangeInfoCase0002()
    {
        
        final String STR_METHOD_NAME = "testCompareChangeInfoCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductRow l_changeBeforeSecurityProductInfo = TestDBUtility.getSecurityProductRow();
            TestDBUtility.insertWithDel(l_changeBeforeSecurityProductInfo);
            
            WEB3SLProductInfoUnit l_changeAfterSecurityProductInfo = null;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.compareChangeInfo(
                l_changeBeforeSecurityProductInfo,l_changeAfterSecurityProductInfo);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * compare変更情報
     * 項目全て、this.is項目変更（）をコールし、全て差異がない場合,１を返却。
     */
    public void testCompareChangeInfoCase0003()
    {
        
        final String STR_METHOD_NAME = "testCompareChangeInfoCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_changeBeforeSecurityProductInfo = TestDBUtility.getSecurityProductRow();
            l_changeBeforeSecurityProductInfo.setFitFlg("0");
            l_changeBeforeSecurityProductInfo.setEstimationRatio(123);
            l_changeBeforeSecurityProductInfo.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            l_changeBeforeSecurityProductInfo.setApplyTermTo(WEB3DateUtility.getDate("20071007","yyyyMMdd"));
            l_changeBeforeSecurityProductInfo.setReason("abcdef");
            TestDBUtility.insertWithDel(l_changeBeforeSecurityProductInfo);
            
            WEB3SLProductInfoUnit l_changeAfterSecurityProductInfo = new WEB3SLProductInfoUnit();
            l_changeAfterSecurityProductInfo.qualifiedDiv = "0";
            l_changeAfterSecurityProductInfo.weight = "123";
            l_changeAfterSecurityProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070917","yyyyMMdd");
            l_changeAfterSecurityProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20071007","yyyyMMdd");
            l_changeAfterSecurityProductInfo.reason = ("abcdef");

            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            int l_int = l_impl.compareChangeInfo(
                l_changeBeforeSecurityProductInfo,l_changeAfterSecurityProductInfo);

            assertEquals(1, l_int);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * compare変更情報
     * 項目全て、this.is項目変更（）をコールし、1つでも差異が存在する場合は0を返却する。。
     */
    public void testCompareChangeInfoCase0004()
    {
        
        final String STR_METHOD_NAME = "testCompareChangeInfoCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_changeBeforeSecurityProductInfo = TestDBUtility.getSecurityProductRow();
            l_changeBeforeSecurityProductInfo.setFitFlg("0");
            l_changeBeforeSecurityProductInfo.setEstimationRatio(123);
            l_changeBeforeSecurityProductInfo.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            l_changeBeforeSecurityProductInfo.setApplyTermTo(WEB3DateUtility.getDate("20071007","yyyyMMdd"));
            l_changeBeforeSecurityProductInfo.setReason("abcdef");
            TestDBUtility.insertWithDel(l_changeBeforeSecurityProductInfo);
            
            WEB3SLProductInfoUnit l_changeAfterSecurityProductInfo = new WEB3SLProductInfoUnit();
            l_changeAfterSecurityProductInfo.qualifiedDiv = "1";
            l_changeAfterSecurityProductInfo.weight = "123";
            l_changeAfterSecurityProductInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070917","yyyyMMdd");
            l_changeAfterSecurityProductInfo.targetPeriodTo = WEB3DateUtility.getDate("20071007","yyyyMMdd");
            l_changeAfterSecurityProductInfo.reason = ("abcdef");

            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            int l_int = l_impl.compareChangeInfo(
                l_changeBeforeSecurityProductInfo,l_changeAfterSecurityProductInfo);

            assertEquals(0, l_int);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * update担保銘柄情報
     * 引数.検索キー情報 == null
     * 抛異常。
     */
    public void testUpdateSecurityProductInfoCase0001()
    {        
        final String STR_METHOD_NAME = "testUpdateSecurityProductInfoCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SLProductSearchConditions l_searchKeyConditions = null;

            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            SecurityProductParams l_securityProductRow = new SecurityProductParams();
            l_impl.updateSecurityProductInfo(
                l_searchKeyConditions, l_securityProductRow);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * update担保銘柄情報
     * 主キーを更新条件に担保銘柄テーブルのレコードを更新する
     */
    public void testUpdateSecurityProductInfoCase0002()
    {        
        final String STR_METHOD_NAME = "testUpdateSecurityProductInfoCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams1 = TestDBUtility.getSecurityProductRow();
            l_securityProductParams1.setProductId(123456789L);
            l_securityProductParams1.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            l_securityProductParams1.setProductCode("123456789");
            l_securityProductParams1.setFitFlg("0");
            l_securityProductParams1.setReason("bbbb");
            // estimation_ratio
            l_securityProductParams1.setEstimationRatio(256.38);
            // apply_term_to
            l_securityProductParams1.setApplyTermTo(WEB3DateUtility.getDate("20070918","yyyyMMdd"));
            // last_updater
            l_securityProductParams1.setLastUpdater("12345678");
            l_securityProductParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_securityProductParams1);
            
            
            WEB3SLProductSearchConditions l_searchKeyConditions = new WEB3SLProductSearchConditions();
            l_searchKeyConditions.productId = 123456789L;
            l_searchKeyConditions.targetPeriodFrom = WEB3DateUtility.getDate("20070917","yyyyMMdd");
            
            SecurityProductParams l_securityProductRow = new SecurityProductParams();
            l_securityProductRow.setProductId(123456789L);
            l_securityProductRow.setFitFlg("1");
            l_securityProductRow.setReason("reason");
            l_securityProductRow.setInstitutionCode("0D");
            l_securityProductRow.setProductCode("123456789");
            l_securityProductRow.setProductType(ProductTypeEnum.AIO);
            l_securityProductRow.setEstimationRatio(260.30);
            
            // apply_term_from
            Calendar l_calendarApplyTermForm = Calendar.getInstance();
            l_calendarApplyTermForm.set(Calendar.YEAR,2007);
            l_calendarApplyTermForm.set(Calendar.MONTH,9);
            l_calendarApplyTermForm.set(Calendar.DAY_OF_MONTH,23);
            
            l_securityProductRow.setApplyTermFrom(WEB3DateUtility.getDate("20071023","yyyyMMdd"));
            
            // apply_term_to
            Calendar l_calendarApplyTermTo = Calendar.getInstance();
            l_calendarApplyTermTo.set(Calendar.YEAR,2007);
            l_calendarApplyTermTo.set(Calendar.MONTH,9);
            l_calendarApplyTermTo.set(Calendar.DAY_OF_MONTH,24);
            
            l_securityProductRow.setApplyTermTo(WEB3DateUtility.getDate("20071024","yyyyMMdd"));
            
            //last_updater
            l_securityProductRow.setLastUpdater("87654321");
            
            
            Calendar l_calendarCreatedTimestamp = Calendar.getInstance();
            l_calendarCreatedTimestamp.set(Calendar.YEAR,2007);
            l_calendarCreatedTimestamp.set(Calendar.MONTH,9);
            l_calendarCreatedTimestamp.set(Calendar.DAY_OF_MONTH,23);
            // last_updated_timestamp
            l_securityProductRow.setLastUpdatedTimestamp(l_calendarCreatedTimestamp.getTime());
            
            l_securityProductRow.setCreatedTimestamp(l_calendarCreatedTimestamp.getTime());
            
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.updateSecurityProductInfo(
                l_searchKeyConditions, l_securityProductRow);
                        
            String l_strWhere = "product_id = ? and apply_term_from = ?";
            Object[] l_whereValues = new Object[]{
                new Long(123456789L),WEB3DateUtility.getDate("20071023","yyyyMMdd")};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResults = l_queryProcessor.doFindAllQuery(SecurityProductRow.TYPE,l_strWhere,l_whereValues);
            
            assertEquals(1,l_lisResults.size());
            
            assertEquals("1",((SecurityProductRow)l_lisResults.get(0)).getFitFlg());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex); 
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * delete担保銘柄情報
     * 削除対象キー == null
     * 抛異常。
     */
    public void testDeleteSecurityProductInfoCase0001()
    {        
        final String STR_METHOD_NAME = "testDeleteSecurityProductInfoCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SLProductSearchConditions l_deleteObjectKey = null;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();
            
            l_impl.deleteSecurityProductInfo( l_deleteObjectKey);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * delete担保銘柄情報
     * 主キーを対象に担保銘柄テーブルのレコードを削除する
     */
    public void testDeleteSecurityProductInfoCase0002()
    {        
        final String STR_METHOD_NAME = "testDeleteSecurityProductInfoCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductId(101010101010L);
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);

            WEB3SLProductSearchConditions l_deleteObjectKey = new WEB3SLProductSearchConditions();
            l_deleteObjectKey.productId = 101010101010L;
            l_deleteObjectKey.targetPeriodFrom = WEB3DateUtility.getDate("20070917","yyyyMMdd");
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();

            String l_strValue = "product_id = ? and apply_term_from = ?";
            Object[] l_objValue = new Object[]{new Long(101010101010L),
                WEB3DateUtility.getDate("20070917","yyyyMMdd")};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSecurityProductInfos = null;
            
            l_lisSecurityProductInfos = l_queryProcessor.doFindAllQuery(
                    SecurityProductRow.TYPE,
                    l_strValue, l_objValue);

            assertEquals(1,l_lisSecurityProductInfos.size());
            
            l_impl.deleteSecurityProductInfo(l_deleteObjectKey);

            l_lisSecurityProductInfos = l_queryProcessor.doFindAllQuery(
                SecurityProductRow.TYPE,
                l_strValue, l_objValue);

            assertEquals(0,l_lisSecurityProductInfos.size());

        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * get担保銘柄情報
     * 銘柄IDをキーに担保銘柄テーブルのレコードを取不到。
     */
    public void testGetSecurityProductInfoCase0001()
    {        
        final String STR_METHOD_NAME = "testGetSecurityProductInfoCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductId(101010101010L);
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);

            long l_lngProductId = 111111111111L;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();

            List l_lis = l_impl.getSecurityProductInfo(l_lngProductId);
            assertEquals(0, l_lis.size());
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * get担保銘柄情報
     * 銘柄IDをキーに担保銘柄テーブルのレコードを取到1條。
     */
    public void testGetSecurityProductInfoCase0002()
    {        
        final String STR_METHOD_NAME = "testGetSecurityProductInfoCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductId(101010101010L);
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);

            long l_lngProductId = 101010101010L;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();

            List l_lis = l_impl.getSecurityProductInfo(l_lngProductId);
            assertEquals(1, l_lis.size());
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * get担保銘柄情報
     * 銘柄IDをキーに担保銘柄テーブルのレコードを取到3條。
     */
    public void testGetSecurityProductInfoCase0003()
    {        
        final String STR_METHOD_NAME = "testGetSecurityProductInfoCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductId(101010101010L);
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            SecurityProductParams l_securityProductParams1 = TestDBUtility.getSecurityProductRow();
            l_securityProductParams1.setProductId(101010101010L);
            l_securityProductParams1.setApplyTermFrom(WEB3DateUtility.getDate("20070918","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams1);
            
            SecurityProductParams l_securityProductParams2 = TestDBUtility.getSecurityProductRow();
            l_securityProductParams2.setProductId(101010101010L);
            l_securityProductParams2.setApplyTermFrom(WEB3DateUtility.getDate("20070919","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_securityProductParams2);
            

            long l_lngProductId = 101010101010L;
            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();

            List l_lis = l_impl.getSecurityProductInfo(l_lngProductId);
            assertEquals(3, l_lis.size());
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * is項目変更
     * 変更前項目と変更後項目を比較し、値が変更されている場合true
     */
    public void testIsItemChangeCase0001()
    {        
        final String STR_METHOD_NAME = "testIsItemChangeCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            String l_strChangeBeforeItem = "ABC";
            String l_strChangeAfterItem = "ABCD";

            
            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();

            boolean l_blnIsItemChange = l_impl.isItemChange(l_strChangeBeforeItem, l_strChangeAfterItem);
            assertTrue(l_blnIsItemChange);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * is項目変更
     * 変更前項目と変更後項目を比較し、変更無の場合はfalseを返却する。
     */
    public void testIsItemChangeCase0002()
    {        
        final String STR_METHOD_NAME = "testIsItemChangeCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            String l_strChangeBeforeItem = "ABC";
            String l_strChangeAfterItem = "ABC";

            WEB3AdminAioSLProductRegistControlServiceImpl l_impl = 
                new WEB3AdminAioSLProductRegistControlServiceImpl();

            boolean l_blnIsItemChange = l_impl.isItemChange(l_strChangeBeforeItem, l_strChangeAfterItem);
            assertFalse(l_blnIsItemChange);
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
}
@
