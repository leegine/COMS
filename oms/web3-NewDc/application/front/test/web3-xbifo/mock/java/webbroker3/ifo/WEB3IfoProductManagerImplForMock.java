head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoProductManagerImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP�v���_�N�g�}�l�[�W���N���XForMock(WEB3IfoProductManagerImplForMock.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/02 ���G�� (���u) �V�K�쐬
 Revesion History : 2007/04/27 ���� �ugetTickValue�v��ǉ�����
 Revesion History : 2010/07/26 �����C (���u) �uisIfoBasePriceMail�v��ǉ�����
 */
package webbroker3.ifo;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �敨OP�v���_�N�g�}�l�[�W���N���XForMock
 * 
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3IfoProductManagerImplForMock extends WEB3IfoProductManagerImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoProductManagerImplForMock.class);

    /**
     * (get����(Mock))<BR>
     * �igetProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �敨OP�����I�u�W�F�N�g���擾����B<BR>
     * 
     * @@param l_institution -
     *            �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strUnderlyingProductCode -
     *            (�����Y�����R�[�h)<BR>
     * @@param l_strMonthOfDelivery -
     *            �����i�hYYYYMMDD�h�j<BR>
     * @@param l_ifoDerivativeType -
     *            (�敨�I�v�V�������i)<BR>
     * @@param l_dblStrikePrice -
     *            (�s�g���i)<BR>
     * @@param l_strSplitType -
     *            (����)<BR>
     * @@param l_strMarketCode -
     *            (�Ώێs��R�[�h)<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     */
    public WEB3IfoProductImpl getIfoProduct(Institution l_institution, String l_strUnderlyingProductCode,
            String l_strMonthOfDelivery, IfoDerivativeTypeEnum l_ifoDerivativeType, double l_dblStrikePrice,
            String l_strSplitType, String l_strMarketCode) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = "getIfoProduct()-->ForMock";
        log.entering(STR_METHOD_NAME);

        // 1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoProduct",
                new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class, double.class,
                        String.class, String.class }, new Object[] { l_institution, l_strUnderlyingProductCode,
                        l_strMonthOfDelivery, l_ifoDerivativeType, new Double(l_dblStrikePrice), l_strSplitType,
                        l_strMarketCode });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoProduct",
                new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class, double.class,
                        String.class, String.class }))
        {
            // 2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                            double.class, String.class, String.class }).asWEB3BaseException();

            // 3)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3IfoProductImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                            double.class, String.class, String.class }).asObject();

        }

        log.exiting(STR_METHOD_NAME);
        return super.getIfoProduct(l_institution, l_strUnderlyingProductCode, l_strMonthOfDelivery,
                l_ifoDerivativeType, l_dblStrikePrice, l_strSplitType, l_strMarketCode);
    }

    /**
     * (get����)<BR>
     * �igetProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �����R�[�h�ɊY������������擾����B<BR>
     * 
     * @@param l_institution -
     *            �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode -
     *            (�����R�[�h)<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     */
    public WEB3IfoProductImpl getIfoProduct(Institution l_institution, String l_strProductCode)
            throws NotFoundException

    {
        final String STR_METHOD_NAME = "getIfoProduct(Institution�CString)-->ForMock";
        log.entering(STR_METHOD_NAME);

        // 1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoProduct",
                new Class[] { Institution.class, String.class }, new Object[] { l_institution, l_strProductCode });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoProduct",
                new Class[] { Institution.class, String.class }))
        {
            // 2�jMockFor --�r asNotFoundException

            // 3)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3IfoProductImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoProduct",
                    new Class[] { Institution.class, String.class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getIfoProduct(l_institution, l_strProductCode);

    }

    /**
     * (get�������)<BR>
     * �igetTradedProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �敨OP��������I�u�W�F�N�g���擾����B<BR>
     * 
     * @@param l_institution -
     *            �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strMarketCode -
     *            (�s��R�[�h)<BR>
     * @@param l_strUnderlyingProductCode -
     *            (�����Y�����R�[�h)<BR>
     * @@param l_strMonthOfDelivery -
     *            �����i�hYYYYMMDD�h�j<BR>
     * @@param l_strDerivativeType -
     *            (�敨�I�v�V�������i)<BR>
     * @@param l_dblStrikePrice -
     *            �s�g���i
     * @@param l_strDivision -
     *            (����)<BR>
     * @@param l_strObjectMarketCode -
     *            (�Ώێs��R�[�h)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.WEB3IfoTradedProductImpl
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     */
    public WEB3IfoTradedProductImpl getIfoTradedProduct(Institution l_institution, String l_strMarketCode,
            String l_strUnderlyingProductCode, String l_strMonthOfDelivery, IfoDerivativeTypeEnum l_ifoDerivativeType,
            double l_dblStrikePrice, String l_strSplitType, String l_strObjectMarketCode) throws WEB3BaseException,
            NotFoundException
    {
        final String STR_METHOD_NAME = "getIfoTradedProduct(Institution�CString, String,"
                + "String, IfoDerivativeTypeEnum, double, String, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        // 1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct", new Class[] { Institution.class, String.class, String.class, String.class,
                        IfoDerivativeTypeEnum.class, double.class, String.class, String.class }, new Object[] {
                        l_institution, l_strMarketCode, l_strUnderlyingProductCode, l_strMonthOfDelivery,
                        l_ifoDerivativeType, new Double(l_dblStrikePrice), l_strSplitType, l_strObjectMarketCode });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoTradedProduct",
                new Class[] { Institution.class, String.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                        double.class, String.class, String.class }))
        {
            // 2�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class })
                    .asWEB3BaseException();

            // 3)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3IfoTradedProductImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class }).asObject();

        }

        log.exiting(STR_METHOD_NAME);
        return super.getIfoTradedProduct(l_institution, l_strMarketCode, l_strUnderlyingProductCode,
                l_strMonthOfDelivery, l_ifoDerivativeType, l_dblStrikePrice, l_strSplitType, l_strObjectMarketCode);
    }

    /**
     * (get�������)<BR>
     * �igetTradedProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �����R�[�h�ɊY������敨OP��������I�u�W�F�N�g���擾����B
     * 
     * @@param l_institution -
     *            �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode -
     *            (�����R�[�h)<BR>
     * @@param l_strMarketCode -
     *            (�s��R�[�h)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     */
    public WEB3IfoTradedProductImpl getIfoTradedProduct(Institution l_institution, String l_strProductCode,
            String l_strMarketCode) throws NotFoundException
    {
        final String STR_METHOD_NAME = "getIfoTradedProduct(Institution�CString�CString)-->ForMock";
        log.entering(STR_METHOD_NAME);

        // 1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoTradedProduct", new Class[] { Institution.class, String.class, String.class }, new Object[] {
                        l_institution, l_strProductCode, l_strMarketCode });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoTradedProduct",
                new Class[] { Institution.class, String.class, String.class }))
        {
            // 2�jMockFor --�r asNotFoundException

            // 3)MockFor --�r asObject
            log.exiting(STR_METHOD_NAME);
            return (WEB3IfoTradedProductImpl) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class }).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getIfoTradedProduct(l_institution, l_strProductCode, l_strMarketCode);
    }

    public double getTickValue(WEB3IfoProductImpl l_futuresOptionProduct, double l_dblUnitPrice)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTickValue(WEB3IfoProductImpl l_futuresOptionProduct, double l_dblUnitPrice)-->ForMock";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                "getTickValue",
                new Class[] { WEB3IfoProductImpl.class, double.class }, new Object[] { l_futuresOptionProduct,
                        new Double(l_dblUnitPrice) });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoProductManagerImpl", "getTickValue",
                new Class[] { WEB3IfoProductImpl.class, double.class }))
        {
            
            log.debug("webbroker3.ifo.WEB3IfoProductManagerImplForMock.getTickValue");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getTickValue",
                    new Class[] { WEB3IfoProductImpl.class, double.class }).asWEB3BaseException();
            
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getTickValue",
                    new Class[] { WEB3IfoProductImpl.class, double.class }).asDouble();
        }

        return super.getTickValue(l_futuresOptionProduct, l_dblUnitPrice);
    }

    public Product toProduct(Row l_row)
    {
        Product l_product = super.toProduct(l_row);

        IfoProductRow l_ifoProductRow = (IfoProductRow) l_product.getDataSourceObject();
        try
        {
            return new WEB3IfoProductImplForMock(l_ifoProductRow);
        }
        catch (DataException l_dqe)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + "."
                    + "toProduct(Row)", l_dqe);
        }
    }

    public TradedProduct toTradedProduct(Row l_row)
    {

        TradedProductRow l_tprow = (TradedProductRow) l_row;
        try
        {
            return new WEB3IfoTradedProductImplForMock(l_tprow);
        }
        catch (DataException l_dqe)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + "."
                    + "toTradedProduct(Row)", l_dqe);
        }
    }
    
    public Product getProduct(long l_lngProductId) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl", "getProduct", new Class[]
                { long.class }, new Object[]
                { new Long(l_lngProductId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.WEB3IfoProductManagerImpl", "getProduct", new Class[]
                { long.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoProductManagerImplForMock.getProduct()");
            return (Product) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getProduct", new Class[]
                    { long.class }).asObject();
        }
        return super.getProduct(l_lngProductId);
    }
    
    public TradedProduct getTradedProduct(long l_lngProductId, long l_lngMarketId) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTradedProduct", new Class[]
                { long.class, long.class }, new Object[]
                { new Long(l_lngProductId), new Long(l_lngMarketId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoProductManagerImpl", "getTradedProduct",
                new Class[]
                { long.class, long.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoProductManagerImplForMock.getTradedProduct()");
            return (TradedProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", "getTradedProduct", new Class[]
                    { long.class, long.class }).asObject();
        }
        return super.getTradedProduct(l_lngProductId, l_lngMarketId);
    }
    
    public double getCurrentPrice(WEB3IfoTradedProductImpl l_futuresOptionTradedProduct) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getCurrentPrice", new Class[]
                { WEB3IfoTradedProductImpl.class }, new Object[]
                { l_futuresOptionTradedProduct });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.ifo.WEB3IfoProductManagerImpl", "getCurrentPrice",
                new Class[]
                { WEB3IfoTradedProductImpl.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoProductManagerImplForMock.getCurrentPrice()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getCurrentPrice", new Class[]
                    { WEB3IfoTradedProductImpl.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getCurrentPrice", new Class[]
                    { WEB3IfoTradedProductImpl.class }).asDouble();
        }
        return super.getCurrentPrice(l_futuresOptionTradedProduct);
    }
    
    public boolean isIfoBasePriceMail(String l_strInstitutionCode) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "isIfoBasePriceMail",
                new Class[] { String.class },
                new Object[] { l_strInstitutionCode });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "isIfoBasePriceMail",
                new Class[] { String.class }))
        {
            log.debug("webbroker3.ifo.WEB3IfoProductManagerImplForMock.isIfoBasePriceMail()");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "isIfoBasePriceMail",
                    new Class[] { String.class }).asBoolean();
        }
        return super.isIfoBasePriceMail(l_strInstitutionCode);
    }
}
@
