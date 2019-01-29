head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoProductManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�v���_�N�g�}�l�[�W��(WEB3IfoProductManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 ���� �V�K�쐬
Revesion History : 2007/06/28 �����F(���u) ���f��768
Revesion History : 2008/07/25 ��іQ(���u) ���f��893
*/
package webbroker3.ifo;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.ifo.data.IfoDeliveryMonthMasterRow;
import webbroker3.ifo.data.IfoTickValuesMasterRow;
import webbroker3.ifo.data.IfoLimitPriceRangeMasterRow;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;

/**
 * (�敨OP�v���_�N�g�}�l�[�W��)<BR>
 * �敨OP�v���_�N�g�}�l�[�W���N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3IfoProductManagerImpl extends IfoProductManagerImpl
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3IfoProductManagerImpl.class);
    /**
     * @@roseuid 40C0751201B5
     */
    public WEB3IfoProductManagerImpl()
    {

    }

    /**
     * (get����)<BR>
     * �igetProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �敨OP�����I�u�W�F�N�g���擾����B<BR>
     * @@param l_institution - �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strUnderlyingProductCode - (�����Y�����R�[�h)<BR>
     * @@param l_strMonthOfDelivery - �����i�hYYYYMMDD�h�j<BR>
     * @@param l_ifoDerivativeType - (�敨�I�v�V�������i)<BR>
     * @@param l_dblStrikePrice - (�s�g���i)<BR>
     * @@param l_strSplitType - (����)<BR>
     * @@param l_strMarketCode - (�Ώێs��R�[�h)<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@roseuid 404D4E7C01B9
     */
    public WEB3IfoProductImpl getIfoProduct(
        Institution l_institution,
        String l_strUnderlyingProductCode,
        String l_strMonthOfDelivery,
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        double l_dblStrikePrice,
        String l_strSplitType,
        String l_strMarketCode)
        throws WEB3BaseException,NotFoundException
    {
        String METHOD_NAME = "getIfoProduct()";
        log.entering(METHOD_NAME);

        if (l_institution == null ||
            l_strUnderlyingProductCode == null ||
            l_strMonthOfDelivery == null ||
            l_ifoDerivativeType ==null ||
            l_strSplitType == null)
        {
            log.error("parameter is null!");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);
        }

        try
        {   /*
            //throw NotFoundException
            IfoProduct l_ifoProduct =  super.getIfoProduct(
                    l_institution,
                    l_strUnderlyingProductCode,
                    l_ifoDerivativeType,
                    l_strMonthOfDelivery,
                    l_dblStrikePrice);

            long l_lngProductId = l_ifoProduct.getProductId();

            log.debug("ProductId = " + l_lngProductId);
            */
            IfoProductRow l_productRow =
                IfoProductDao.findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType(
                l_institution.getInstitutionCode(),
                l_strUnderlyingProductCode,
                l_ifoDerivativeType,
                l_dblStrikePrice,
                l_strMonthOfDelivery,
                l_strSplitType);

            if (l_productRow == null)
            {
                log.exiting(METHOD_NAME);
                throw new NotFoundException("�����Y�����R�[�h = " + l_strUnderlyingProductCode +
                    "�،���ЃR�[�h = " + l_institution.getInstitutionCode() + 
                    "�����i�hYYYYMMDD�h�j= " + l_strMonthOfDelivery + 
                    "�敨�I�v�V�������i= " + l_ifoDerivativeType + 
                    "�s�g���i = " + l_dblStrikePrice +
                    "���� = " + l_strSplitType + "�ɊY������������擾�ł��܂���");
            }

            long l_lngProductId = l_productRow.getProductId();

            log.exiting(METHOD_NAME);
            //throw DataFindException, DataNetworkException, DataQueryException
            return new WEB3IfoProductImpl(l_productRow.getProductId());
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(METHOD_NAME);
            throw new NotFoundException("�����Y�����R�[�h = " + l_strUnderlyingProductCode +
                "�،���ЃR�[�h = " + l_institution.getInstitutionCode() + 
                "�����i�hYYYYMMDD�h�j= " + l_strMonthOfDelivery + 
                "�敨�I�v�V�������i= " + l_ifoDerivativeType + 
                "�s�g���i = " + l_dblStrikePrice +
                "���� = " + l_strSplitType + "�ɊY������������擾�ł��܂���");
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }

    }

    /**
     * (get����)<BR>
     * �igetProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �����R�[�h�ɊY������������擾����B<BR>
     * @@param l_institution - �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@roseuid 404D4ECC019A
     */
    public WEB3IfoProductImpl getIfoProduct(
        Institution l_institution,
        String l_strProductCode)
        throws NotFoundException

    {
        String METHOD_NAME = "getIfoProduct()";
        log.entering(METHOD_NAME);

        try
        {
            IfoProductRow l_productRow =
            IfoProductDao.findRowByProductCodeInstitutionCode(
                l_strProductCode,
                l_institution.getInstitutionCode());

            if (l_productRow == null)
            {
                log.exiting(METHOD_NAME);
                throw new NotFoundException("�����R�[�h = " + l_strProductCode +
                    "�،���ЃR�[�h = " + l_institution.getInstitutionCode() + "�ɊY������������擾�ł��܂���");
            }

            long l_lngProductId = l_productRow.getProductId();
            log.debug("ProductId = " + l_lngProductId);

            log.exiting(METHOD_NAME);
            //throw DataFindException, DataNetworkException, DataQueryException
            return new WEB3IfoProductImpl(l_lngProductId);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(METHOD_NAME);
            throw new NotFoundException("�����R�[�h = " + l_strProductCode +
                "�،���ЃR�[�h = " + l_institution.getInstitutionCode() + "�ɊY������������擾�ł��܂���");        
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);
            log.exiting(METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }

    }

    /**
     * (get�������)<BR>
     * �igetTradedProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �敨OP��������I�u�W�F�N�g���擾����B<BR>
     * @@param l_institution - �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@param l_strUnderlyingProductCode - (�����Y�����R�[�h)<BR>
     * @@param l_strMonthOfDelivery - �����i�hYYYYMMDD�h�j<BR>
     * @@param l_strDerivativeType - (�敨�I�v�V�������i)<BR>
     * @@param l_dblStrikePrice - �s�g���i
     *
     * @@param l_strDivision - (����)<BR>
     * @@param l_strObjectMarketCode - (�Ώێs��R�[�h)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.WEB3IfoTradedProductImpl
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 404D4B840003
     */
    public WEB3IfoTradedProductImpl getIfoTradedProduct(
        Institution l_institution,
        String l_strMarketCode,
        String l_strUnderlyingProductCode,
        String l_strMonthOfDelivery,
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        double l_dblStrikePrice,
        String l_strSplitType,
        String l_strObjectMarketCode)
        throws WEB3BaseException,NotFoundException
    {
        String METHOD_NAME = "getIfoTradedProduct()";
        log.entering(METHOD_NAME);      
        
        if (l_institution == null || 
            l_strMarketCode == null ||
            l_strUnderlyingProductCode == null || 
            l_strMonthOfDelivery == null ||
            l_ifoDerivativeType == null ||
            l_strSplitType == null )
        {   
            log.error("parameter is null!");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);                 
        }
        
        try
        {   
            //throw NotFoundException            
            IfoTradedProduct l_ifoTradedProduct =  super.getIfoTradedProduct(
                    l_institution,
                    l_strUnderlyingProductCode,            
                    l_ifoDerivativeType,
                    l_strMonthOfDelivery,
                    l_dblStrikePrice,
                    l_strMarketCode);
                  
            long l_lngTradedProductId = l_ifoTradedProduct.getTradedProductId();         
            log.debug("TradedProductId  = " + l_lngTradedProductId);
            
            log.exiting(METHOD_NAME);      
             
            //throw DataFindException, DataNetworkException, DataQueryException   
            return new WEB3IfoTradedProductImpl(l_lngTradedProductId);
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);  
            log.exiting(METHOD_NAME);                             
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }
    }

    /**
     * (get�������)<BR>
     * �igetIfoTradedProduct(Institution, String, IfoDerivativeTypeEnum, String,<BR>
     * �@@double, String)�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �敨OP��������I�u�W�F�N�g���擾����B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@param l_strUnderlyingProductCode - (�����Y�����R�[�h)<BR>
     * �����Y�����R�[�h
     * @@param l_ifoDerivativeType - (�敨�I�v�V�������i)<BR>
     * �敨�I�v�V�������i
     * @@param l_strMonthOfDelivery - (����)<BR>
     * �����i�hYYYYMMDD�h�j
     * @@param l_dblStrikePrice - (�s�g���i)<BR>
     * �s�g���i
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h
     * @@return IfoTradedProduct
     * @@throws NotFoundException
     */
    public IfoTradedProduct getIfoTradedProduct(
        Institution l_institution,
        String l_strUnderlyingProductCode,
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        String l_strMonthOfDelivery,
        double l_dblStrikePrice,
        String l_strMarketCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getIfoTradedProduct(Institution, String, IfoDerivativeTypeEnum," +
            "String, double, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            IfoTradedProduct l_ifoTradedProduct = super.getIfoTradedProduct(
                l_institution,
                l_strUnderlyingProductCode,
                l_ifoDerivativeType,
                l_strMonthOfDelivery,
                l_dblStrikePrice,
                l_strMarketCode);

            long l_lngTradedProductId = l_ifoTradedProduct.getTradedProductId();
            log.debug("TradedProductId  = " + l_lngTradedProductId);

            log.exiting(STR_METHOD_NAME);
            return new WEB3IfoTradedProductImpl(l_lngTradedProductId);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (get�������)<BR>
     * �igetTradedProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �����R�[�h�ɊY������敨OP��������I�u�W�F�N�g���擾����B
     * @@param l_institution - �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 4059609C0119
     */
    public WEB3IfoTradedProductImpl getIfoTradedProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strMarketCode)
        throws NotFoundException
    {
        String METHOD_NAME = "getIfoTradedProduct()";
        log.entering(METHOD_NAME);      
        
        try
        {                       
            Product l_product = this.getIfoProduct(l_institution, l_strProductCode);
            log.debug("ProductId = " + l_product.getProductId());
            
            Market l_market = 
                GtlUtils.getFinObjectManager().getMarket(l_institution, l_strMarketCode);
            log.debug("MarketId = " + l_market.getMarketId());
                    
            IfoTradedProduct ifoTradedProduct = (IfoTradedProduct)super.getTradedProduct(l_product, l_market);
                   
            long l_lngTradedProductId = ifoTradedProduct.getTradedProductId();
            log.debug("TradedProductId  = " + l_lngTradedProductId);
            
            log.exiting(METHOD_NAME);     
            //throw DataFindException, DataNetworkException, DataQueryException   
            return new WEB3IfoTradedProductImpl(l_lngTradedProductId);
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);  
            log.exiting(METHOD_NAME);                             
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }

    }

    /**
     * (get���ݒl)<BR>
     * <BR>
     * �Ēl�e�[�u������A�Y�����鍏�ݒl���擾����B<BR>
     * <BR>
     * �P�j�@@�Ώۍs�擾<BR>
     * �@@�|�敨�I�v�V�����Ēl�e�[�u�����ȉ��̏����œǂ�<BR>
     * �@@[����]<BR>
     * �@@�敨�I�v�V�����Ēl�e�[�u��.�����Y�����R�[�h = �敨OP����.�����Y�����R�[�h<BR>
     * �@@�敨�I�v�V�����Ēl�e�[�u��.�敨�^�I�v�V�����敪 = �敨OP����.�敨�^�I�v�V�����敪<BR>
     * <BR>
     * �@@�|�����Ɉ�v����s���A<BR>
     * �@@�i�敨�I�v�V�����Ēl�e�[�u��.�����l�@@< ����.�P�� <= �敨�I�v�V�����Ēl�e�[�u��.����l�j<BR>
     * �@@�ɊY������s�̍s�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@���ݒl�ԋp<BR>
     * �@@�擾�����s.���ݒl��ԋp����B<BR>
     * @@param l_futuresOptionProduct - �敨OP�����I�u�W�F�N�g
     * @@param l_dblUnitPrice - (�P��)<BR>
     * @@return double
     * @@roseuid 4067B6F901DE
     */
    public double getTickValue(
        WEB3IfoProductImpl l_futuresOptionProduct,
        double l_dblUnitPrice) throws WEB3BaseException
    {
        String METHOD_NAME =
              "getTickValue(WEB3IfoProductImpl,double)";

        log.entering(METHOD_NAME);

        if (l_futuresOptionProduct == null)
        {
            log.error("parameter is null type");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);
        }

        String l_strUnderlyingProductCode =
            l_futuresOptionProduct.getUnderlyingProductCode();
        String l_strFutureOptionDiv =
            ((IfoProductRow) l_futuresOptionProduct.getDataSourceObject()).getFutureOptionDiv();

        log.debug("l_strUnderlyingProductCode =" + l_strUnderlyingProductCode);
        log.debug("l_strFutureOptionDiv =" + l_strFutureOptionDiv);

        QueryProcessor l_queryProcesser = null;
        double l_dblTickValue = 0;
        List l_lisRows = null;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            String l_strWhere =
                  "target_product_code = ? and "
                     + "future_option_div = ? and "
                     + "(low_price < ?) and (? <= cap_price)";

            Object l_bindVars[] =
            {   new String(l_strUnderlyingProductCode),
                new String(l_strFutureOptionDiv),
                new Double(l_dblUnitPrice),
                new Double(l_dblUnitPrice)};
            //throw DataFindException, DataNetworkException, DataQueryException
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                        IfoTickValuesMasterRow.TYPE,
                        l_strWhere,
                        l_bindVars);
        }
        catch (DataException l_de)
        {
            log.error(l_de.getMessage(), l_de);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        if (l_lisRows.size() == 0)
        {
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME);
        }

        log.debug("Rows count : " + l_lisRows.size());

        l_dblTickValue =
                ((IfoTickValuesMasterRow) l_lisRows.get(0)).getTickValue();

        log.exiting(METHOD_NAME);
        return l_dblTickValue;
    }

    /**
     * (get�����l��)<BR>
     * <BR>
     * �l���e�[�u������A�Y�����鐧���l�����擾����B<BR>
     * <BR>
     * �P�j�@@�Ώۍs�擾<BR>
     * �@@�|�敨�I�v�V�����l���e�[�u�����ȉ��̏����œǂ�<BR>
     * �@@[����]<BR>
     * �@@�敨�I�v�V�����l���e�[�u��.�����Y�����R�[�h = �敨OP����.�����Y�����R�[�h<BR>
     * �@@�敨�I�v�V�����l���e�[�u��.�敨�^�I�v�V�����敪 = �敨OP����.�敨�^�I�v�V�����敪<BR>
     * <BR>
     * �@@�|�����Ɉ�v����s���A<BR>
     * �@@�i�敨�I�v�V�����l���e�[�u��.�����l�@@<= ����.�P�� < �敨�I�v�V�����l���e�[�u��.����l�j<BR>
     * �@@�ɊY������s�̍s�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�����l���ԋp<BR>
     * �@@�擾�����s.�l����ԋp����B<BR>
     * @@param l_futuresOptionProduct - �敨OP�����I�u�W�F�N�g
     * @@param l_dblUnitPrice - �P��
     * @@return double
     * @@roseuid 4067BECC01EE
     */
    public double getDeregulatedPriceRange(
        WEB3IfoProductImpl l_futuresOptionProduct,
        double l_dblUnitPrice) throws WEB3BaseException
    {
        String METHOD_NAME =
            "getDeregulatedPriceRange(WEB3IfoProductImpl,double)";

        log.entering(METHOD_NAME);

        if (l_futuresOptionProduct == null)
        {
            log.error("parameter is null type");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);
        }

        String l_strUnderlyingProductCode =
            l_futuresOptionProduct.getUnderlyingProductCode();
        String l_strFutureOptionDiv =
            ((IfoProductRow) l_futuresOptionProduct.getDataSourceObject()).getFutureOptionDiv();

        log.debug("l_strUnderlyingProductCode =" + l_strUnderlyingProductCode);
        log.debug("l_strFutureOptionDiv =" + l_strFutureOptionDiv);

        QueryProcessor l_queryProcesser = null;
        double l_dblPriceRange = 0;
        try
        {
            l_queryProcesser = Processors.getDefaultProcessor();
            String l_strWhere =
                    "target_product_code = ? and " +
                    "future_option_div = ? and " +
                    "(low_price <= ?) and (? < cap_price)";

            Object l_bindVars[] =
                {   new String(l_strUnderlyingProductCode),
                    new String(l_strFutureOptionDiv),
                    new Double(l_dblUnitPrice),
                    new Double(l_dblUnitPrice)};
            //throw DataFindException, DataNetworkException, DataQueryException
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    IfoLimitPriceRangeMasterRow.TYPE,
                    l_strWhere,
                    l_bindVars);

            if (l_lisRows.size() == 0)
            {
               throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            l_dblPriceRange =
                ((IfoLimitPriceRangeMasterRow) l_lisRows.get(0)).getRange();
        }
        catch (DataException de)
        {
            log.error(de.getMessage(),de);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                de.getMessage(),
                de);
        }
        catch (WEB3BaseException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.toString(),
                l_nfe);
        }

        log.exiting(METHOD_NAME);
        return l_dblPriceRange;
    }

    /**
     * (get����)<BR>
     * <BR>
     * �������擾����B<BR>
     * <BR>
     * �葱���ɂ��ẮA<BR>
     * �u��{�݌v�v�Z����(�����w��OP�j.doc�v�@@5.�����擾<BR>
     * �Q�ƁB<BR>
     * @@param l_futuresOptionTradedProduct - �敨OP��������I�u�W�F�N�g
     * @@return double
     * @@roseuid 4072A7270232
     */
    public double getCurrentPrice(WEB3IfoTradedProductImpl l_futuresOptionTradedProduct)
        throws WEB3BaseException
    {
        String METHOD_NAME =
                "getCurrentPrice(WEB3IfoTradedProductImpl)";

        log.entering(METHOD_NAME);

        if (l_futuresOptionTradedProduct == null)
        {
            log.error("parameter is null type");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                   this.getClass().getName() + "." + METHOD_NAME);
        }

        double l_lngPrice = 0;
        try
        {
            // TradingModule����QuoteDataSupplierService���擾
            //throw NotInstalledException
            FinApp finApp = (FinApp) Services.getService(FinApp.class);
            WEB3QuoteDataSupplierService  l_quoteSupplier =
                (WEB3QuoteDataSupplierService ) finApp
                   .getTradingModule(ProductTypeEnum.IFO)
                   .getQuoteDataSupplierService();
            // TradedProduct���w�肵�Ď��������擾
            //throw NotFoundException
            WEB3IfoQuoteData l_ifoQuoteData =
                (WEB3IfoQuoteData) l_quoteSupplier.getIfoQuote(l_futuresOptionTradedProduct);

            log.debug("l_ifoQuoteData =" + l_ifoQuoteData);
            // �����̎擾
            //�����T�[�r�X�iQuoteDataSupplierService�j���A�������iIfoQuoteData�j���擾����B
            //�ȉ��̗D�揇�ʂŁA�擾�ł���i0�łȂ��j�P���������Ƃ��ĕԋp����B
            //1.  ���ݒl�iIfoQuoteData.getCurrentPrice()�j
            //2.  ���C�z�l�iIfoQuoteData.getBidPrice()�j
            //3.  ���C�z�l�iIfoQuoteData.getAskPrice()�j
            //4.  �敨OP�������.��l�i�I�l�j

            l_lngPrice = l_ifoQuoteData.getCurrentPrice();
            log.debug("getCurrentPrice() = " + l_lngPrice);

            if (l_lngPrice == 0)
            {
                l_lngPrice = l_ifoQuoteData.getBidPrice();
                log.debug("getBidPrice() = " + l_lngPrice);

                if (l_lngPrice == 0)
                {
                    l_lngPrice = l_ifoQuoteData.getAskPrice();
                    log.debug("getAskPrice() = " + l_lngPrice);

                    if ((l_lngPrice == 0)||(Double.isNaN(l_lngPrice)))
                    {
                        l_lngPrice = ((IfoTradedProductRow) l_futuresOptionTradedProduct
                        .getDataSourceObject()).getLastClosingPrice();
                        log.debug("getLastClosingPrice() = " + l_lngPrice);

						if((l_lngPrice == 0)||(Double.isNaN(l_lngPrice)))
						{
							throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_01997, 
							this.getClass().getName() + METHOD_NAME,
							"�w��̖����͐��s�����ɕK�v�Ȏs�ꎞ�����Ȃ����߁A�w�l�����̂ݎ�t�\�ł��B");
						}
                    }
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(),l_nfe);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (NotInstalledException l_nie)
        {
            log.error(l_nie.getMessage(), l_nie);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nie.toString(),
                l_nie);
        }

        log.exiting(METHOD_NAME);
        return l_lngPrice;

    }

    /**
     * (get����)<BR>
     * �敨OP�����}�X�^�e�[�u������A�Y��������������X�g�Ŏ擾����B<BR>
     * �P�j�敨OP�����}�X�^�e�[�u�����ȉ��̏����Ō�������<BR>
     * �@@[����]<BR>
     * �@@�敨OP�����}�X�^�e�[�u��.�����Y�����R�[�h = ����.�����Y�����R�[�h<BR>
     * �@@�敨OP�����}�X�^�e�[�u��.�敨�^�I�v�V�����敪 = ����.�敨�^�I�v�V�����敪<BR>
     * <BR>
     * �Q�j�擾���ʂ̌��������X�g�ŕԋp����<BR>
     * ���Y������f�[�^�����݂��Ȃ��ꍇ�ɂ́u�f�[�^�s�����G���[�v�̗�O���X���[����B<BR>
     *      class:   WEB3SystemLayerException<BR>
     *        tag:   SYSTEM_ERROR_80006<BR>
     * <BR>
     * @@param l_strUnderlyingProductCode - (�����Y�����R�[�h)<BR>
     * �����Y�����R�[�h<BR>
     * @@param l_strFutureOptionDiv - (�敨/�I�v�V�����敪)<BR>
     * �敨/�I�v�V�����敪<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getMonthOfDelivery(String l_strUnderlyingProductCode, String l_strFutureOptionDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMonthOfDelivery(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�敨OP�����}�X�^�e�[�u�����ȉ��̏����Ō�������
        List l_lisRows = new ArrayList();

        //�敨OP�����}�X�^�e�[�u��.�����Y�����R�[�h = ����.�����Y�����R�[�h
        //�敨OP�����}�X�^�e�[�u��.�敨�^�I�v�V�����敪 = ����.�敨�^�I�v�V�����敪
        String l_strWhere = " underlying_product_code = ? and future_option_div = ? ";
        Object[] l_bindVars = {l_strUnderlyingProductCode, l_strFutureOptionDiv};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRows = l_queryProcessor.doFindAllQuery(
                IfoDeliveryMonthMasterRow.TYPE,
                l_strWhere,
                l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Y������f�[�^�����݂��Ȃ��ꍇ�ɂ́u�f�[�^�s�����G���[�v�̗�O���X���[����B
        if (l_lisRows == null || l_lisRows.isEmpty())
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        //�Q�j�擾���ʂ̌��������X�g�ŕԋp����
        List l_lisDeliveryMonths = new ArrayList();
        int l_intLength = l_lisRows.size();
        for (int i = 0; i < l_intLength; i++)
        {
            IfoDeliveryMonthMasterRow l_ifoDeliveryMonthMasterRow = (IfoDeliveryMonthMasterRow)l_lisRows.get(i);
            l_lisDeliveryMonths.add(l_ifoDeliveryMonthMasterRow.getDeliveryMonth());
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisDeliveryMonths;
    }

    /**
     * (get�����ꗗ)<BR>
     * �戵�\�Ȍ����Y�����R�[�h�Ɛ敨/�I�v�V�����敪�Ɉ�v��������̈ꗗ���擾����B<BR>
     * ���擾�����S�Ă̌����ŏd�����Ȃ��l���Z�b�g�B<BR>
     * <BR>
     * �P�j�߂�lList�̃C���X�^���X�𐶐�����B<BR>
     * �Q�j������(���X�w����)�戵�����I�u�W�F�N�g���A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�敨OP�v���_�N�g�}�l�[�W��.get����()���\�b�h�̃R�[���ɂ��A<BR>
     * �@@�@@�@@�@@�@@������List���擾����B<BR>
     * �@@[����]<BR>
     * �@@�@@�����Y�����R�[�h �@@�@@�F�p�����[�^.(���X�w����)�戵�����̗v�f.�����Y�����R�[�h<BR>
     * �@@�@@�敨�^�I�v�V�����敪 �F�p�����[�^.�敨�^�I�v�V�����敪<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�Q�|�P�j�Ŏ擾����������List�����A<BR>
     * �@@�@@�@@�@@�@@�@@�P�j�Ő��������߂�lList�ɁA�Q�|�P�j�Ŏ擾����������ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�������������d�����Ċ܂܂��ꍇ�́A�d�����������B<BR>
     * <BR>
     * �R�j�߂�lList�̌����ꗗ��ԋp����B<BR>
     * <BR>
     * @@param l_gentradeBranchIndexDealtConds - (�i���X�w���ʁj�戵����[])<BR>
     * �i���X�w���ʁj�戵����<BR>
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪)<BR>
     * �敨�^�I�v�V�����敪<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDeliveryMonthList(
        WEB3GentradeBranchIndexDealtCond[] l_gentradeBranchIndexDealtConds,
        String l_strFutureOptionDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryMonthList(WEB3GentradeBranchIndexDealtCond[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_gentradeBranchIndexDealtConds == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�P�j�߂�lList�̃C���X�^���X�𐶐�����B
        List l_lisDeliveryMonths = new ArrayList();

        //�Q�j������(���X�w����)�戵�����I�u�W�F�N�g���A�ȉ��̏������s���B
        int l_intListLength = l_gentradeBranchIndexDealtConds.length;
        for (int i = 0; i < l_intListLength; i++)
        {
            //�Q�|�P�jthis.get����()���\�b�h�̃R�[���ɂ��A������List���擾����B
            List l_lisMonthOfDeliverys =
                this.getMonthOfDelivery(
                    l_gentradeBranchIndexDealtConds[i].getUnderlyingProductCode(),
                    l_strFutureOptionDiv);

            //�Q�|�Q�j�@@�Q�|�P�j�Ŏ擾����������List�����A
            //�P�j�Ő��������߂�lList�ɁA�Q�|�P�j�Ŏ擾����������ǉ�����B
            //�������������d�����Ċ܂܂��ꍇ�́A�d�����������B
            int l_intLength = l_lisMonthOfDeliverys.size();
            for (int j = 0; j < l_intLength; j++)
            {
                //�d�����Ȃ��������擾
                if (!l_lisDeliveryMonths.contains(l_lisMonthOfDeliverys.get(j)))
                {
                    l_lisDeliveryMonths.add(l_lisMonthOfDeliverys.get(j));
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        //�R�j�߂�lList�̌����ꗗ��ԋp����B
        return l_lisDeliveryMonths;
    }

    public Product toProduct(Row l_row)
    {
        ProductRow l_prow = (ProductRow) l_row;
        try
        {
            return new WEB3IfoProductImpl(l_prow);
        }
        catch(DataException l_dex)
        {
            String msg = "Exception when creating IfoProduct for product id: " + l_prow.getProductId();
            log.error(msg, l_dex);
            throw new RuntimeSystemException(msg, l_dex);
        }

    }

    public TradedProduct toTradedProduct(Row l_row)
    {

        TradedProductRow l_tprow = (TradedProductRow) l_row;
        try
        {
            return new WEB3IfoTradedProductImpl(l_tprow);
        }
        catch(DataException l_dex)
        {
            String msg = "Exception when creating IfoTradedProduct for traded product id: " + l_tprow.getTradedProductId();
            log.error(msg, l_dex);
            throw new RuntimeSystemException(msg, l_dex);
        }
    }

    public IfoProduct getIfoProduct(
        Institution l_inst,
        String l_strUnderlyingProductCode,
        IfoDerivativeTypeEnum l_derivativeType,
        String l_strMonthOfDelivery,
        double l_dblStrikePrice) throws NotFoundException
    {
        try
        {

            IfoProductRow l_mRow =
            IfoProductDao.findRowByInstitutionCodeUnderlyingProductCodeDerivativeTypeStrikePriceMonthOfDeliverySplitType
                (l_inst.getInstitutionCode(),
                l_strUnderlyingProductCode,
                l_derivativeType,
                l_dblStrikePrice,
                l_strMonthOfDelivery,
                "000");
            if(l_mRow == null)
            {
                String l_strMsg = "No IfoProduct found with institutionCode," +
                " underlyingProductCode, derivativeType, monthOfDelivery and strikePrice : " +
                l_inst.getInstitutionCode() + "," + l_strUnderlyingProductCode + "," + l_derivativeType + "," +
                l_strMonthOfDelivery + "," + l_dblStrikePrice;
                throw new NotFoundException(l_strMsg);
            }

            return new IfoProductImpl(l_mRow);
        }
        catch(DataFindException l_dfe)
        {
            String l_strMmsg = "No IfoProduct found with institutionCode, " +
            "underlyingProductCode, derivativeType, monthOfDelivery and strikePrice : " +
            l_inst.getInstitutionCode() + "," + l_strUnderlyingProductCode + "," + l_derivativeType + "," +
            l_strMonthOfDelivery + "," + l_dblStrikePrice;
            log.warn(l_strMmsg, l_dfe);
            throw new NotFoundException(l_strMmsg);
        }
        catch(DataException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            throw new RuntimeSystemException(
                "System exception while getting IfoProduct with institutionCode, underlyingProductCode, " +
                "derivativeType, monthOfDelivery and strikePrice : " + l_inst.getInstitutionCode() + "," +
                l_strUnderlyingProductCode + "," + l_derivativeType + "," + l_strMonthOfDelivery + "," + l_dblStrikePrice);
        }
    }
}
@
