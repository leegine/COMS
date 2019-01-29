head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3AdminEquityAttentionInfoNotifyHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/08 ������(���u) �V�K�쐬 ���f��No.219
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import test.util.TestDBUtility;

import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyRow;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest.class);

    WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl l_impl =
        new WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl();
    
    public WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest(String arg0)
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

    /*�P�j�@@�@@�L���[�ɕK�v�ȏ�񂪊i�[����Ă��Ȃ��ꍇ�A�u2�F�������X�V�v��return����B 
 �@@�@@�@@[����] 
 �@@�@@�@@�@@���ӏ��ʒm�L���[�e�[�u��.��l��null */
    public void testNotifyLimitRangeInfo_C001()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C001";
        log.entering(STR_METHOD_NAME);

        try
        {
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);
            l_ProductParams.setLastUpdater("XXXX");
            l_ProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(3304148080000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setProductId(3304148080000L);
//            l_tradedProductParams.setMarketId(3303L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, l_ProductParams);
            assertEquals("2", l_strUpdateFlag);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�Q�j�@@�،���Ѓv���t�@@�����X�e�[�u�����擾����B 
    //�@@�@@�@@[��������] 
    //�@@�@@�@@�@@�،���Ђh�c�F�@@�،����.�،���Ђh�c 
    //�@@�@@�@@�@@�v���t�@@�����X���F�@@"attention.info.comp.taking.div" 
    //�@@�@@�@@�@@�v���t�@@�����X���̘A�ԁF�@@"1"�i�Œ�j 
    //�R�j�@@�Q�j�ŏ،���Ѓv���t�@@�����X�e�[�u�����擾�o���Ȃ������ꍇ
    //�@@�@@�@@�u2�F�������X�V�v��return����B 
    public void testNotifyLimitRangeInfo_C002()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C002";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(1000);
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
            l_hostAttentionInfoNotifyParams.setRowid("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2086L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("price_range_unit_type");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("51");
//            l_eqtypeProductConditionParams.setDataToday("511");
//            l_eqtypeProductConditionParams.setDataNextDay("511");
//            l_eqtypeProductConditionParams.setDataPlan("511");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);
            l_ProductParams.setLastUpdater("XXXX");
            l_ProductParams.setProductId(2601133200000L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, l_ProductParams);
            assertEquals("2", l_strUpdateFlag);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�Q�j�@@�،���Ѓv���t�@@�����X�e�[�u�����擾����B 
    //�@@�@@�@@[��������] 
    //�@@�@@�@@�@@�،���Ђh�c�F�@@�،����.�،���Ђh�c 
    //�@@�@@�@@�@@�v���t�@@�����X���F�@@"attention.info.comp.taking.div" 
    //�@@�@@�@@�@@�v���t�@@�����X���̘A�ԁF�@@"1"�i�Œ�j 
    //�R�j�@@�،���Ѓv���t�@@�����X�e�[�u��.�v���t�@@�����X�̒l��"�捞�܂Ȃ�"�̏ꍇ
    //�@@�@@�@@�u2�F�������X�V�v��return����B 
    public void testNotifyLimitRangeInfo_C003()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C003";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);
            l_ProductParams.setLastUpdater("XXXX");
            l_ProductParams.setProductId(2601133200000L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(1000);
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
            l_hostAttentionInfoNotifyParams.setRowid("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("0");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
 
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, l_ProductParams);
            assertEquals("2", l_strUpdateFlag);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //����.�D��s��ID��null�̏ꍇ & �����������UPDQ���擾���Ă���ꍇ
    //�������X�V����
    // �]���P�� = ���ӏ��ʒm�L���[.��l(100)
    // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
    //��������������X�V��
    // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
    // �l���`�F�b�N�敪 = "1:�l���`�F�b�N����"
    // �l���敪 = �w�����l���i����l�j�x�w�����l���i�����l�j�x���X�V�������ʁA�ǂ���̍��ڂ�null�łȂ��ꍇ�A"1�F�~"
    // ���ӏ��ʒm�L���[.�����l�����(200) ���ӏ��ʒm�L���[.�����l������(10)
    // �����l���i����l�j= ���ӏ��ʒm�L���[.�����l�����-���ӏ��ʒm�L���[.��l(�v�Z���ʂ�100�̏ꍇ)
    // �����l���i�����l�j= ���ӏ��ʒm�L���[.��l-���ӏ��ʒm�L���[.�����l������(�v�Z���ʂ�90�̏ꍇ)
    // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
    // ��l = ���ӏ��ʒm�L���[.��l(100)
    //�����������UPDQ���X�V����
    // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
    // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
    // ��l = ���ӏ��ʒm�L���[.��l(100)
    public void testNotifyLimitRangeInfo_C004()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C004";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(200);
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);//�]���P��
            l_ProductParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_ProductParams.setProductId(2601133200000L);
            l_ProductParams.setPrimaryMarketId(null);//����.�D��s��ID��null
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setLastClosingPrice(50);//��l�i�I�l�j
            l_eqtypeTradedProductParams.setPriceRangeType("0");//�l���`�F�b�N�敪
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");//�l���敪
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(99);//�����l���i����l�j
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(1);//�����l���i�����l�j
            l_eqtypeTradedProductParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_eqtypeTradedProductParams.setBasePrice(50);//��l
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(2601133200000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(2601L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//��l�i�I�l�j
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//��l
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, 
                    l_eqtypeTradedProductUpdqParams, l_ProductParams);
            assertEquals("1", l_strUpdateFlag);
            
            //�������X�V����
            ProductRow l_product = ProductDao.findRowByPk(2601133200000L);
            // �]���P�� = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_product.getEstimationPrice(), 0);
            // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
            assertEquals("AXRY1", l_product.getLastUpdater());
            
            //��������������X�V��
            EqtypeTradedProductRow l_listEqtypeTradedProduct =
                EqtypeTradedProductDao.findRowByPk(1006160060005L);
            // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProduct.getLastClosingPrice(), 0);
            // �l���`�F�b�N�敪 = "1:�l���`�F�b�N����"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeType());
            // �l���敪 = �w�����l���i����l�j�x�w�����l���i�����l�j�x���X�V�������ʁA�ǂ���̍��ڂ�null�łȂ��ꍇ�A"1�F�~"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeUnitType());
            // ���ӏ��ʒm�L���[.�����l�����(200) ���ӏ��ʒm�L���[.�����l������(10)
            // �����l���i����l�j= ���ӏ��ʒm�L���[.�����l�����-���ӏ��ʒm�L���[.��l(�v�Z���ʂ�0�ȏ�̏ꍇ)
            assertEquals(100, l_listEqtypeTradedProduct.getHighCompulsivePriceRange(), 0);
            // �����l���i�����l�j= ���ӏ��ʒm�L���[.��l-���ӏ��ʒm�L���[.�����l������(�v�Z���ʂ�0�ȏ�̏ꍇ)
            assertEquals(90, l_listEqtypeTradedProduct.getLowCompulsivePriceRange(), 0);
            // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProduct.getLastUpdater());
            // ��l = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProduct.getBasePrice(), 0);
            
            //�����������UPDQ���X�V����
            EqtypeTradedProductUpdqRow l_listEqtypeTradedProductUpdq =
                EqtypeTradedProductUpdqDao.findRowByPk(1006160060005L, "20040917");
            // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProductUpdq.getLastClosingPrice(), 0);
            // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProductUpdq.getLastUpdater());
            // ��l = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProductUpdq.getBasePrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //����.�D��s��ID�ƒ��ӏ��ʒm�L���[�e�[�u��.�s��R�[�h�iSONAR�j�� 
    //�@@�@@�Y������s��ID���������ꍇ & �����������UPDQ���擾���Ă���ꍇ
    //�������X�V����
    // �]���P�� = ���ӏ��ʒm�L���[.��l(100)
    // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
    //��������������X�V��
    // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
    // �l���`�F�b�N�敪 = "1:�l���`�F�b�N����"
    // �l���敪 = �����l"0"
    // ���ӏ��ʒm�L���[.�����l����� = null ���ӏ��ʒm�L���[.�����l������(10)
    // �����l���i����l�j= null
    // �����l���i�����l�j= ���ӏ��ʒm�L���[.��l-���ӏ��ʒm�L���[.�����l������(�v�Z���ʂ�90�̏ꍇ)
    // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
    // ��l = ���ӏ��ʒm�L���[.��l(100)
    //�����������UPDQ���X�V����
    // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
    // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
    // ��l = ���ӏ��ʒm�L���[.��l(100)
    public void testNotifyLimitRangeInfo_C005()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C005";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(null);//�����l�����
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("1");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);//�]���P��
            l_ProductParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_ProductParams.setProductId(2601133200000L);
            l_ProductParams.setPrimaryMarketId(2601L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setLastClosingPrice(50);//��l�i�I�l�j
            l_eqtypeTradedProductParams.setPriceRangeType("0");//�l���`�F�b�N�敪
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");//�l���敪
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(99);//�����l���i����l�j
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(1);//�����l���i�����l�j
            l_eqtypeTradedProductParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_eqtypeTradedProductParams.setBasePrice(50);//��l
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(2601133200000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(2601L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//��l�i�I�l�j
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//��l
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("2");
            l_MarketParams.setSonarMarketCode("1");
            l_MarketParams.setMarketId(2601L);
            TestDBUtility.insertWithDel(l_MarketParams);
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, 
                        l_eqtypeTradedProductUpdqParams, l_ProductParams);
            assertEquals("1", l_strUpdateFlag);
               
            //�������X�V����
            ProductRow l_product = ProductDao.findRowByPk(2601133200000L);
            // �]���P�� = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_product.getEstimationPrice(), 0);
            // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
            assertEquals("AXRY1", l_product.getLastUpdater());
                
            //��������������X�V��
            EqtypeTradedProductRow l_listEqtypeTradedProduct =
                EqtypeTradedProductDao.findRowByPk(1006160060005L);
            // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProduct.getLastClosingPrice(), 0);
            // �l���`�F�b�N�敪 = "1:�l���`�F�b�N����"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeType());
            // �l���敪 = �����l "0"
            assertEquals("0", l_listEqtypeTradedProduct.getPriceRangeUnitType());
            // ���ӏ��ʒm�L���[.�����l����� = null ���ӏ��ʒm�L���[.�����l������(10)
            // �����l���i����l�j= null
            assertTrue(l_listEqtypeTradedProduct.getHighCompulsivePriceRangeIsNull());
            // �����l���i�����l�j= ���ӏ��ʒm�L���[.��l-���ӏ��ʒm�L���[.�����l������(�v�Z���ʂ�90�̏ꍇ)
            assertEquals(90, l_listEqtypeTradedProduct.getLowCompulsivePriceRange(), 0);
            // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProduct.getLastUpdater());
            // ��l = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProduct.getBasePrice(), 0);
                
            //�����������UPDQ���X�V����
            EqtypeTradedProductUpdqRow l_listEqtypeTradedProductUpdq =
                EqtypeTradedProductUpdqDao.findRowByPk(1006160060005L, "20040917");
            // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProductUpdq.getLastClosingPrice(), 0);
            // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProductUpdq.getLastUpdater());
            // ��l = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProductUpdq.getBasePrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //����.�D��s��ID�s����null�I�ꍇ ����
    //����.�D��s��ID�a�o���ӏ��ʒm�L���[�e�[�u��.�s��R�[�h�iSONAR�j�������I�s��ID�s�����I�ꍇ�A�����s�X�V
    //�����������UPDQ���L�擾 �����������UPDQ ���L�X�V
    //��������������X�V��
    // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
    // �l���`�F�b�N�敪 = "1:�l���`�F�b�N����"
    // �l���敪 = �w�����l���i����l�j�x�w�����l���i�����l�j�x���X�V�������ʁA�ǂ���̍��ڂ�null�łȂ��ꍇ�A"1�F�~"
    // ���ӏ��ʒm�L���[.�����l�����(200) ���ӏ��ʒm�L���[.�����l������(110)
    // �����l���i����l�j= ���ӏ��ʒm�L���[.�����l�����-���ӏ��ʒm�L���[.��l(�v�Z���ʂ�0�ȏ�̏ꍇ)
    // �����l���i�����l�j= ���ӏ��ʒm�L���[.��l-���ӏ��ʒm�L���[.�����l������(�v�Z���ʂ�0�ȉ��̏ꍇ -10) null
    // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
    // ��l = ���ӏ��ʒm�L���[.��l(100)
    public void testNotifyLimitRangeInfo_C006()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C006";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(200);
            l_hostAttentionInfoNotifyParams.setLowPriceRange(110);//�����l������
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("1");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);//�]���P��
            l_ProductParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_ProductParams.setProductId(2601133200000L);
            l_ProductParams.setPrimaryMarketId(2222L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setLastClosingPrice(50);//��l�i�I�l�j
            l_eqtypeTradedProductParams.setPriceRangeType("0");//�l���`�F�b�N�敪
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");//�l���敪
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(99);//�����l���i����l�j
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(1);//�����l���i�����l�j
            l_eqtypeTradedProductParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_eqtypeTradedProductParams.setBasePrice(50);//��l
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(2601133200000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(2601L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//��l�i�I�l�j
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//��l
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("1");
            l_MarketParams.setSonarMarketCode("1");
            l_MarketParams.setMarketId(2601L);
            TestDBUtility.insertWithDel(l_MarketParams);
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, 
                    null, l_ProductParams);
            assertEquals("1", l_strUpdateFlag);
               
            //�����s�X�V
            ProductRow l_product = ProductDao.findRowByPk(2601133200000L);
            // �]���P�� = �i�����l�j
            assertEquals(50, l_product.getEstimationPrice(), 0);
            // �X�V�҃R�[�h = �i�����l�j
            assertEquals("XXXX", l_product.getLastUpdater());
                
            //��������������X�V��
            EqtypeTradedProductRow l_listEqtypeTradedProduct =
                EqtypeTradedProductDao.findRowByPk(1006160060005L);
            // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProduct.getLastClosingPrice(), 0);
            // �l���`�F�b�N�敪 = "1:�l���`�F�b�N����"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeType());
            // �l���敪 = �����l "0"
            assertEquals("0", l_listEqtypeTradedProduct.getPriceRangeUnitType());
            // ���ӏ��ʒm�L���[.�����l�����(200) ���ӏ��ʒm�L���[.�����l������(110)
            // �����l���i����l�j= ���ӏ��ʒm�L���[.�����l�����-���ӏ��ʒm�L���[.��l(�v�Z���ʂ�0�ȏ�̏ꍇ)
            assertEquals(100, l_listEqtypeTradedProduct.getHighCompulsivePriceRange(), 0);
            // �����l���i�����l�j= null
            assertTrue(l_listEqtypeTradedProduct.getLowCompulsivePriceRangeIsNull());
            // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProduct.getLastUpdater());
            // ��l = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProduct.getBasePrice(), 0);
                
            //�����������UPDQ�s�X�V
            EqtypeTradedProductUpdqRow l_listEqtypeTradedProductUpdq =
                EqtypeTradedProductUpdqDao.findRowByPk(1006160060005L, "20040917");
            // ��l�i�I�l�j = �i�����l�j
            assertEquals(50, l_listEqtypeTradedProductUpdq.getLastClosingPrice(), 0);
            // �X�V�҃R�[�h = �i�����l�j
            assertEquals("XXXX", l_listEqtypeTradedProductUpdq.getLastUpdater());
            // ��l = �i�����l�j
            assertEquals(50, l_listEqtypeTradedProductUpdq.getBasePrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //����.�D��s��ID�s����null�I�ꍇ ����
    //����.�D��s��ID�a�o���ӏ��ʒm�L���[�e�[�u��.�s��R�[�h�iSONAR�j�������I�s��ID�s�����I�ꍇ�A�����s�X�V
    //�����������UPDQ���L�擾 �����������UPDQ ���L�X�V
    //��������������X�V��
    // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
    // �l���`�F�b�N�敪 = "1:�l���`�F�b�N����"
    // �l���敪 = �����l"0"
    // ���ӏ��ʒm�L���[.�����l�����(90) ���ӏ��ʒm�L���[.�����l������(null)
    // �����l���i����l�j= ���ӏ��ʒm�L���[.�����l�����-���ӏ��ʒm�L���[.��l(�v�Z���ʂ�0�ȏ�̏ꍇ)
    // �����l���i�����l�j= ���ӏ��ʒm�L���[.��l-���ӏ��ʒm�L���[.�����l������(�v�Z���ʂ�0�ȉ��̏ꍇ -10) null
    // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
    // ��l = ���ӏ��ʒm�L���[.��l(100)
    public void testNotifyLimitRangeInfo_C007()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C007";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(90);//�����l�����
            l_hostAttentionInfoNotifyParams.setLowPriceRange(null);//�����l������
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("1");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);//�]���P��
            l_ProductParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_ProductParams.setProductId(2601133200000L);
            l_ProductParams.setPrimaryMarketId(2222L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setLastClosingPrice(50);//��l�i�I�l�j
            l_eqtypeTradedProductParams.setPriceRangeType("0");//�l���`�F�b�N�敪
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");//�l���敪
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(99);//�����l���i����l�j
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(1);//�����l���i�����l�j
            l_eqtypeTradedProductParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_eqtypeTradedProductParams.setBasePrice(50);//��l
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(2601133200000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(2601L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//��l�i�I�l�j
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//�X�V�҃R�[�h
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//��l
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("2");
            l_MarketParams.setSonarMarketCode("1");
            l_MarketParams.setMarketId(2601L);
            TestDBUtility.insertWithDel(l_MarketParams);
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, 
                    null, l_ProductParams);
            assertEquals("1", l_strUpdateFlag);
               
            //�����s�X�V
            ProductRow l_product = ProductDao.findRowByPk(2601133200000L);
            // �]���P�� = �i�����l�j
            assertEquals(50, l_product.getEstimationPrice(), 0);
            // �X�V�҃R�[�h = �i�����l�j
            assertEquals("XXXX", l_product.getLastUpdater());
                
            //��������������X�V��
            EqtypeTradedProductRow l_listEqtypeTradedProduct =
                EqtypeTradedProductDao.findRowByPk(1006160060005L);
            // ��l�i�I�l�j = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProduct.getLastClosingPrice(), 0);
            // �l���`�F�b�N�敪 = "1:�l���`�F�b�N����"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeType());
            // �l���敪 = �����l "0"
            assertEquals("0", l_listEqtypeTradedProduct.getPriceRangeUnitType());
            // ���ӏ��ʒm�L���[.�����l�����(90) ���ӏ��ʒm�L���[.�����l������(null)
            // �����l���i����l�j= null
            assertTrue(l_listEqtypeTradedProduct.getHighCompulsivePriceRangeIsNull());
            // �����l���i�����l�j= null
            assertTrue(l_listEqtypeTradedProduct.getLowCompulsivePriceRangeIsNull());
            // �X�V�҃R�[�h = ���ӏ��ʒm�L���[.�f�[�^�R�[�h(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProduct.getLastUpdater());
            // ��l = ���ӏ��ʒm�L���[.��l(100)
            assertEquals(100, l_listEqtypeTradedProduct.getBasePrice(), 0);
                
            //�����������UPDQ�s�X�V
            EqtypeTradedProductUpdqRow l_listEqtypeTradedProductUpdq =
                EqtypeTradedProductUpdqDao.findRowByPk(1006160060005L, "20040917");
            // ��l�i�I�l�j = �i�����l�j
            assertEquals(50, l_listEqtypeTradedProductUpdq.getLastClosingPrice(), 0);
            // �X�V�҃R�[�h = �i�����l�j
            assertEquals("XXXX", l_listEqtypeTradedProductUpdq.getLastUpdater());
            // ��l = �i�����l�j
            assertEquals(50, l_listEqtypeTradedProductUpdq.getBasePrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

//    //�Ǘ��ҍX�V�ςݍ���List�̗v�f����2���̏ꍇ�A
//    //�A�Ǘ��ҍX�V�ςݍ���List�Ɋ܂܂�Ă��Ȃ��v�f�̍��ڂɑ΂��X�V���s�Ȃ�
//    //�@@�@@�@@�@@�@@�Ehigh_compulsive_price_range �܂܂�
//    //          �Elow_compulsive_price_range�܂܂�
//    public void testNotifyLimitRangeInfo_C007()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C007";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(1000);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams1 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams1.setEqtypeProductConditionId(2088L);
//            l_eqtypeProductConditionParams1.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams1.setProductCode("13320");
//            l_eqtypeProductConditionParams1.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams1.setMarketCode("1");
//            l_eqtypeProductConditionParams1.setMarketId(2601L);
//            l_eqtypeProductConditionParams1.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams1.setColumnName("low_compulsive_price_range");
//            l_eqtypeProductConditionParams1.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams1.setSmallItemDiv("52");
//            l_eqtypeProductConditionParams1.setDataToday("521");
//            l_eqtypeProductConditionParams1.setDataNextDay("521");
//            l_eqtypeProductConditionParams1.setDataPlan("521");
//            l_eqtypeProductConditionParams1.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams1.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams1.setDeleteFlg("0");
//            l_eqtypeProductConditionParams1.setLastUpdater("dir");
//            l_eqtypeProductConditionParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams1);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams2 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams2.setEqtypeProductConditionId(2090);
//            l_eqtypeProductConditionParams2.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams2.setProductCode("13320");
//            l_eqtypeProductConditionParams2.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams2.setMarketCode("1");
//            l_eqtypeProductConditionParams2.setMarketId(2601L);
//            l_eqtypeProductConditionParams2.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams2.setColumnName("high_compulsive_price_range");
//            l_eqtypeProductConditionParams2.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams2.setSmallItemDiv("53");
//            l_eqtypeProductConditionParams2.setDataToday("531");
//            l_eqtypeProductConditionParams2.setDataNextDay("531");
//            l_eqtypeProductConditionParams2.setDataPlan("531");
//            l_eqtypeProductConditionParams2.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams2.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams2.setDeleteFlg("0");
//            l_eqtypeProductConditionParams2.setLastUpdater("dir");
//            l_eqtypeProductConditionParams2.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams2.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams2);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(100, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(100, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //�Ǘ��ҍX�V�ςݍ���List�̗v�f����2���̏ꍇ�A
//    //�Ǘ��ҍX�V�ςݍ���List�Ɋ܂܂�Ă��Ȃ��v�f�̍��ڂɑ΂��X�V���s�Ȃ�
//    //�@@�@@�@@�@@�@@�Ehigh_compulsive_price_range �܂܂�Ă��Ȃ�
//    //          �Elow_compulsive_price_range�܂܂�Ă��Ȃ�
//    //���ӏ��ʒm�L���[.�����l����� - ���ӏ��ʒm�L���[.��l > 0
//    //���ӏ��ʒm�L���[.��l - ���ӏ��ʒm�L���[.�����l������ > 0
//    public void testNotifyLimitRangeInfo_C008()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C008";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(220);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(50);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                
//                assertEquals(1000.0, l_listEqtypeTradedProductRow.getLastClosingPrice(), 1);
//                assertEquals(120, l_listEqtypeTradedProductRow.getHighCompulsivePriceRange(), 0);
//                assertEquals(50, l_listEqtypeTradedProductRow.getLowCompulsivePriceRange(), 0);
//                assertEquals("1", l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10.0, l_listEqtypeTradedProductRow.getBasePrice(), 1);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //�Ǘ��ҍX�V�ςݍ���List�̗v�f����2���̏ꍇ�A
//    //�Ǘ��ҍX�V�ςݍ���List�Ɋ܂܂�Ă��Ȃ��v�f�̍��ڂɑ΂��X�V���s�Ȃ�
//    //�@@�@@�@@�@@�@@�Ehigh_compulsive_price_range �܂܂�Ă��Ȃ�
//    //          �Elow_compulsive_price_range�܂܂�Ă��Ȃ�
//    //���ӏ��ʒm�L���[.�����l����� - ���ӏ��ʒm�L���[.��l < 0
//    //���ӏ��ʒm�L���[.��l - ���ӏ��ʒm�L���[.�����l������ < 0
//    public void testNotifyLimitRangeInfo_C009()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C009";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(90);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(110);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(1000, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //�Ǘ��ҍX�V�ςݍ���List�̗v�f����2���̏ꍇ�A
//    //�Ǘ��ҍX�V�ςݍ���List�Ɋ܂܂�Ă��Ȃ��v�f�̍��ڂɑ΂��X�V���s�Ȃ�
//    //�@@�@@�@@�@@�@@�Ehigh_compulsive_price_range �܂܂�Ă��Ȃ�
//    //          �Elow_compulsive_price_range�܂܂�Ă��Ȃ�
//    //���ӏ��ʒm�L���[.�����l����� - ���ӏ��ʒm�L���[.��l = 0
//    //���ӏ��ʒm�L���[.��l - ���ӏ��ʒm�L���[.�����l������ = 0
//    public void testNotifyLimitRangeInfo_C010()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C010";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(100);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(100);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(1000, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //�Ǘ��ҍX�V�ςݍ���List�̗v�f����2���̏ꍇ
//    //�Ǘ��ҍX�V�ςݍ���List�Ɋ܂܂�Ă��Ȃ��v�f�̍��ڂɑ΂��X�V���s�Ȃ�
//    //�@@�@@�@@�@@�@@�Ehigh_compulsive_price_range �܂܂�Ă��Ȃ�
//    //          �Elow_compulsive_price_range�܂܂�Ă��Ȃ�
//    //���ӏ��ʒm�L���[.�����l����� - ���ӏ��ʒm�L���[.��l > 0
//    //���ӏ��ʒm�L���[.��l - ���ӏ��ʒm�L���[.�����l������ < 0
//    public void testNotifyLimitRangeInfo_C011()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C011";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100.012);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(150.03);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(110.045);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(1000, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertEquals(50.018, l_listEqtypeTradedProductRow.getHighCompulsivePriceRange(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //�Ǘ��ҍX�V�ςݍ���List�̗v�f����2���̏ꍇ�A
//    //�Ǘ��ҍX�V�ςݍ���List�Ɋ܂܂�Ă��Ȃ��v�f�̍��ڂɑ΂��X�V���s�Ȃ�
//    //�@@�@@�@@�@@�@@�Ehigh_compulsive_price_range �܂܂�Ă��Ȃ�
//    //          �Elow_compulsive_price_range�܂܂�Ă��Ȃ�
//    //���ӏ��ʒm�L���[.�����l����� - ���ӏ��ʒm�L���[.��l < 0
//    //���ӏ��ʒm�L���[.��l - ���ӏ��ʒm�L���[.�����l������ > 0
//    public void testNotifyLimitRangeInfo_C012()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C012";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100.012);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(90.03);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(90.045);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(1000, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertEquals(9.967, l_listEqtypeTradedProductRow.getLowCompulsivePriceRange(), 0);
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //  �Ǘ��ҍX�V�ςݍ���List�̗v�f����2���̏ꍇ�A
//    //�Ǘ��ҍX�V�ςݍ���List�Ɋ܂܂�Ă��Ȃ��v�f�̍��ڂɑ΂��X�V���s�Ȃ�
//    //�@@�@@�@@�@@�@@�Ehigh_compulsive_price_range �܂܂�Ă��Ȃ�
//    //          �Elow_compulsive_price_range�܂܂�Ă��Ȃ�
//    //���ӏ��ʒm�L���[.�����l����� - ���ӏ��ʒm�L���[.��l > 0
//    //���ӏ��ʒm�L���[.��l - ���ӏ��ʒm�L���[.�����l������ > 0
//    public void testNotifyLimitRangeInfo_C0013()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C0013";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                
//                assertEquals(1000.0, l_listEqtypeTradedProductRow.getLastClosingPrice(), 1);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10.0, l_listEqtypeTradedProductRow.getBasePrice(), 1);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //�Ǘ��ҍX�V�ςݍ���List�̗v�f����0���̏ꍇ�A�Y������S�Ă̍��ڂɑ΂��X�V���s�Ȃ�
//    //���ӏ��ʒm�L���[.�����l����� - ���ӏ��ʒm�L���[.��l > 0
//    //���ӏ��ʒm�L���[.��l - ���ӏ��ʒm�L���[.�����l������ > 0
//    public void testNotifyLimitRangeInfo_C0014()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C0014";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//            EqtypeTradedProductRow l_listEqtypeTradedProductRow = EqtypeTradedProductDao.findRowByPk(1006160060005L);
//            assertEquals(100, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//            assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//            assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//            assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//            assertEquals(100, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//            assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //  �Ǘ��ҍX�V�ςݍ���List�̗v�f����2���̏ꍇ�A
//    //�Ǘ��ҍX�V�ςݍ���List�Ɋ܂܂�Ă��Ȃ��v�f�̍��ڂɑ΂��X�V���s�Ȃ�
//    //�@@�@@�@@�@@�@@�Ehigh_compulsive_price_range �܂܂�Ă��Ȃ�
//    //          �Elow_compulsive_price_range�܂܂�Ă��Ȃ�
//    //���ӏ��ʒm�L���[.�����l����� - ���ӏ��ʒm�L���[.��l > 0
//    //���ӏ��ʒm�L���[.��l - ���ӏ��ʒm�L���[.�����l������ > 0
//    public void testNotifyLimitRangeInfo_C0015()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C0013";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(50);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                
//                assertEquals(1000.0, l_listEqtypeTradedProductRow.getLastClosingPrice(), 1);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertEquals(50, l_listEqtypeTradedProductRow.getLowCompulsivePriceRange(), 0);
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10.0, l_listEqtypeTradedProductRow.getBasePrice(), 1);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //�Ǘ��ҍX�V�ςݍ���List�̗v�f����0���̏ꍇ�A�Y������S�Ă̍��ڂɑ΂��X�V���s�Ȃ�
//    //���ӏ��ʒm�L���[.�����l����� - ���ӏ��ʒm�L���[.��l > 0
//    //���ӏ��ʒm�L���[.��l - ���ӏ��ʒm�L���[.�����l������ > 0
//    public void testNotifyLimitRangeInfo_C0016()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C0014";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(250);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//            EqtypeTradedProductRow l_listEqtypeTradedProductRow = EqtypeTradedProductDao.findRowByPk(1006160060005L);
//            assertEquals(100, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//            assertEquals(150, l_listEqtypeTradedProductRow.getHighCompulsivePriceRange(), 0);
//            assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//            assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//            assertEquals(100, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//            assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
}
@
