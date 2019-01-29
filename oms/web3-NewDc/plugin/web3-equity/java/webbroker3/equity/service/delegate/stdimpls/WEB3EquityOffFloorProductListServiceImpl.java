head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOffFloorProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����O���������ꗗ�T�[�r�X(WEB3EquityOffFloorProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 ���(SRA) �V�K�쐬
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.data.OffFloorOrderProductRow;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3EquitySortKey;
import webbroker3.equity.message.WEB3EquityOffFloorProductGroup;
import webbroker3.equity.message.WEB3EquityOffFloorProductListRequest;
import webbroker3.equity.message.WEB3EquityOffFloorProductListResponse;
import webbroker3.equity.service.delegate.WEB3EquityOffFloorProductListService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * �i����O���������ꗗ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ����O���������ꗗ�T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductListServiceImpl
	extends WEB3EquityClientRequestService
	implements WEB3EquityOffFloorProductListService 
{

	/**
	 * (���O�o�̓��[�e�B���e�B�B)
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3EquityOffFloorProductListServiceImpl.class);
	/**
	 * @@roseuid XXXXXXXXXXX
	 */
	public WEB3EquityOffFloorProductListServiceImpl()
	{

	}

    /**
     * ����O���������ꗗ�T�[�r�X���������{����B<BR>
     * <BR>
     * get�����ꗗ()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.common.message.WEB3GenResponse<BR>
     * @@throws webbroker3.common.WEB3BaseException<BR>
     * @@roseuid XXXXXXXXXXXX
     */ 
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String l_strMethodName =
            getClass().getName() + ".execute(WEB3GenRequest)";

        if (l_request == null)
        {
			log.error("*******���N�G�X�g�̒l��Null�ł�*******");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_strMethodName);
        }
        else if (l_request instanceof WEB3EquityOffFloorProductListRequest)
        {
            return this.getProductList((WEB3EquityOffFloorProductListRequest) l_request);
        }
        else
        {
            log.error("*******���N�G�X�g�̌^���s���ł�*******");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_strMethodName);
        }
    }

    /**
     * (get�����ꗗ)<BR>
     * �����ꗗ<BR>
     * �̏������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i����O���������ꗗ�jget�����ꗗ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ����O���������ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteReferenceResponse<BR>
     * @@throws webbroker3.common.WEB3BaseException<BR>
     * @@roseuid XXXXXXXXXXX
     */
    protected WEB3EquityOffFloorProductListResponse getProductList(WEB3EquityOffFloorProductListRequest l_request) throws WEB3BaseException 
	{
		final String STR_METHOD_NAME = "getProductList()";
		log.entering(STR_METHOD_NAME);
   
        WEB3EquityOffFloorProductListResponse l_response = null;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule
                   = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);            
        WEB3EquityOrderManager l_eqOrderMgr =
                     (WEB3EquityOrderManager) l_tradingModule.getOrderManager();    
                            
        WEB3EquityProductManager l_eqProdMgr
                = (WEB3EquityProductManager) l_tradingModule.getProductManager();

        l_request.validate();
    
        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        SubAccount l_subAccount = this.getSubAccount(); 
    
        //validate����\�ڋq(�⏕����)
        OrderValidationResult l_validationResult = null;
    
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
             (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        l_validationResult = l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
    
        if (l_validationResult != OrderValidationResult.VALIDATION_OK_RESULT)
        {
            log.error("*******����\�ڋq�ł͂���܂���*******");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 l_validationResult.getProcessingResult().getErrorInfo(),
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GentradeBranch l_branch = null;
        l_branch =
             (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
       
        Institution l_institution = null;
        l_institution = l_subAccount.getInstitution();   
         
		l_response = (WEB3EquityOffFloorProductListResponse) l_request.createResponse();
		
		//get����O���������ꗗ(�\�[�g�L�[�@@���������\�[�g�L�[[])
		WEB3EquitySortKey[] l_sortKeys = l_request.sortKeys;
		OffFloorOrderProductRow[] l_strOffFloorProdRow = this.getOffFloorProductList(l_sortKeys);
		if (l_strOffFloorProdRow == null)
		{
			l_response.messageSuspension = null;
			l_response.productList =  null;
			return l_response;
		}        

		//get�s��ǌx���s��
		String[] l_TradeCloseMarket = null;
		l_TradeCloseMarket = 
			WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
					l_branch,
					ProductTypeEnum.EQUITY,
					"0");

        //�����ꗗ�i�[�p  
        ArrayList l_lstProductList = new ArrayList();

         //(�擾��������O���������I�u�W�F�N�g����Loop)        
		for (int i = 0;i<l_strOffFloorProdRow.length;i++)
		{
			Date l_datBizdate = GtlUtils.getTradingSystem().getBizDate();
            //��t�I�����iYYYYMMDD�j���߂��Ă���ꍇ�́A�ꗗ�̕\���ΏۊO�Ƃ���B
            if (WEB3DateUtility.compareToDay(l_strOffFloorProdRow[i].getOrderEndDatetime(), l_datBizdate) < 0)
            {
                continue;
            }
             
            WEB3EquityOffFloorProductGroup l_product = new WEB3EquityOffFloorProductGroup();

            EqTypeProduct l_eqProd;
            WEB3EquityTradedProduct l_eqTrdProd;
			WEB3GentradeTradingTimeManagement.resetMarketCode(l_strOffFloorProdRow[i].getMarketCode());
            try
            {
                l_eqProd = l_eqProdMgr.getProduct(l_institution,l_strOffFloorProdRow[i].getProductCode());
                l_eqTrdProd = 
					(WEB3EquityTradedProduct) l_eqProdMgr.getTradedProduct(
					l_institution, l_strOffFloorProdRow[i].getProductCode(), l_strOffFloorProdRow[i].getMarketCode());
            }
            catch (NotFoundException e)
            {
                continue;
            }

            //�v���p�e�B���Z�b�g����
            l_product.productCode = l_strOffFloorProdRow[i].getProductCode();
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_eqProd.getDataSourceObject();
            l_product.productName = l_productRow.getStandardName();
            l_product.marketCode  = l_strOffFloorProdRow[i].getMarketCode();
            l_product.orderStartDatetime = l_strOffFloorProdRow[i].getOrderStartDatetime();
            l_product.orderEndDatetime   = l_strOffFloorProdRow[i].getOrderEndDatetime();
            if (l_strOffFloorProdRow[i].getOffFloorOrderPriceIsNull() == false)
            {
				l_product.offFloorOrderPrice
				= WEB3StringTypeUtility.formatNumber(l_strOffFloorProdRow[i].getOffFloorOrderPrice());
            }
            if (l_strOffFloorProdRow[i].getMaxApplyQuantityIsNull() == false)
            {
				l_product.maxApplyQuantity
				= WEB3StringTypeUtility.formatNumber(l_strOffFloorProdRow[i].getMaxApplyQuantity());
            }
            l_product.applyUnit = WEB3StringTypeUtility.formatNumber(l_eqTrdProd.getLotSize());

            //���{���O�c�Ɠ��I�l���擾���A�����v���p�e�B�ɃZ�b�g����
            double l_dbllastClosingPrice = 0.0D;
			Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            if (WEB3DateUtility.compareToDay(l_strOffFloorProdRow[i].getOrderEndDatetime(),l_orderBizDate) <= 0)
            {
                if (l_strOffFloorProdRow[i].getLastClosingPriceIsNull())
                {
                    //����(*1)�̎擾
                    //get����(��������@@EqTypeTradedProduct)
					l_dbllastClosingPrice = l_eqProdMgr.getCurrentPrice(l_eqTrdProd);
                    l_product.lastClosingPrice = WEB3StringTypeUtility.formatNumber(l_dbllastClosingPrice);
                }
                else if (l_strOffFloorProdRow[i].getLastClosingPrice() == 0)
                {
                    l_product.lastClosingPrice = null;
                }
                else
                {
					l_dbllastClosingPrice = l_strOffFloorProdRow[i].getLastClosingPrice();
                    l_product.lastClosingPrice = WEB3StringTypeUtility.formatNumber(l_dbllastClosingPrice);
                }
            }
            else
            {
                l_product.lastClosingPrice = null; 
            }
            if (l_dbllastClosingPrice == 0)
            {
				l_product.lastClosingPrice = null;            
            }
            
            //���������v�Z���A�����̃v���p�e�B�ɃZ�b�g����
            if ((l_strOffFloorProdRow[i].getOffFloorOrderPriceIsNull() == false)
                 && (l_product.lastClosingPrice != null))
            {
				double l_dbldiscountRate = 
                     (1 - (l_strOffFloorProdRow[i].getOffFloorOrderPrice() / l_dbllastClosingPrice)) * 100;
				//���ʂ͏����_��Q�ʖ�����؂�̂Ă�B�}�C�i�X�l�̏ꍇ��0�ɋ߂Â���B
				BigDecimal l_bddiscountRate = new BigDecimal(l_dbldiscountRate);
				l_dbldiscountRate
				    = l_bddiscountRate.divide(new BigDecimal(1.0D), 2, BigDecimal.ROUND_DOWN).doubleValue();
				l_product.discountRate = WEB3StringTypeUtility.formatNumber(l_dbldiscountRate);
            }
            else
            {
                l_product.discountRate = null;
            }
        
            //���t�\���ǂ����`�F�b�N����
            //is�戵�\�s��(�s��R�[�h�@@�����^�C�v)
            boolean l_blisPossibleMarket = 
                 l_branch.isHandlingPossibleMarket(l_strOffFloorProdRow[i].getMarketCode(),
                                                    ProductTypeEnum.EQUITY);
        
            boolean l_blInsiderOrderStop = true;
            try
            {
                //validate�C���T�C�_�[(�⏕�����@@��������)
                l_eqOrderMgr.validateInsider(l_subAccount,l_eqProd);
            
                //validate�ڋq�����ʎ����~(�⏕�����@@����ID�@@�������)
                l_eqOrderMgr.validateAccountProductOrderStop(l_subAccount,
                                                              l_eqProd.getProductId(),
                                                              OrderTypeEnum.EQUITY_BUY);
            }
            catch(WEB3BaseException b_ex)
            {
                l_blInsiderOrderStop = false;
            }
        
            //�v���p�e�B�u���t�\�t���O�v���Z�b�g
            if(!(l_strOffFloorProdRow[i].getOffFloorOrderPriceIsNull())
                && !(l_strOffFloorProdRow[i].getMaxApplyQuantityIsNull())
                && (l_blisPossibleMarket)
                && (l_blInsiderOrderStop)
                && (WEB3DateUtility.compareToSecond(
                     l_strOffFloorProdRow[i].getOrderStartDatetime(),GtlUtils.getSystemTimestamp()) <= 0)
                && (WEB3DateUtility.compareToSecond(l_strOffFloorProdRow[i].getOrderEndDatetime(), 
                     GtlUtils.getSystemTimestamp()) > 0))
            {
                l_product.buyPossFlag = true; 
            }
            else
			{
                l_product.buyPossFlag = false;
			}
			//(�����E�v���p�e�B���Z�b�g��������O�����������ׂ̃C���X�^���X���A���X�|���X.�����ꗗ��add����)
			l_lstProductList.add(l_product);
		}
		//���X�|���X�f�[�^(�v���p�e�B)�̃Z�b�g
		l_response.messageSuspension = l_TradeCloseMarket;
		WEB3EquityOffFloorProductGroup[] l_pList = new WEB3EquityOffFloorProductGroup[l_lstProductList.size()];
		l_lstProductList.toArray(l_pList);
		l_response.productList = l_pList;

		log.exiting(STR_METHOD_NAME);
		return l_response;
    }
    

    /**
     * (get����O���������ꗗ)<BR>
     * �y����O���������e�[�u���z���A���������̈ꗗ���擾����B<BR>
     * �P�j�@@DB����<BR>
     * ����O���������e�[�u�����ȉ��̏����Ō�������B<BR>
     * [����] <BR>
     * �،���ЃR�[�h = this.get�⏕����( ).�،���ЃR�[�h<BR>
     * <BR>
     * ���\�[�g�����ɁA�����̃\�[�g�L�[�z��̓��e���w�肷��B <BR>
     * <BR>
     * (*1)���ݓ����FGtlUtils.getSystemTimestamp()�Ŏ擾�B<BR>
     * <BR>
     * �Q�j�@@�擾�����S���R�[�h�̔z���Ԃ��B<BR>
     * <BR>
     * <BR>
     * @@param l_equitySortKeys- ���������\�[�g�L�[�̔z��
     * @@return java.lang.String[]
     * @@roseuid XXXXXXXXXXX
     */
    protected OffFloorOrderProductRow[] getOffFloorProductList(WEB3EquitySortKey[] l_equitySortKeys) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3EquityOffFloorProductListServiceImpl.getOffFloorProductList";
        log.entering(STR_METHOD_NAME);
	
        OffFloorOrderProductRow[] l_rows = null;
        
        SubAccount l_subAccount = this.getSubAccount();
        Institution l_institution = null;
        l_institution = l_subAccount.getInstitution();           
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        
        String l_strWhere =
            " institution_code = ?";
                      
        String l_strOrderBy = " ";
        for (int i = 0; i < l_equitySortKeys.length; i++)
        {
            l_strOrderBy = l_strOrderBy + " ";
            String l_strKeyItem = l_equitySortKeys[i].keyItem;
            if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                //�����R�[�h 
                l_strOrderBy = l_strOrderBy + "product_code";
            }
            else if(WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strKeyItem))
            {
                //�s��R�[�h
                l_strOrderBy = l_strOrderBy + "to_number(market_code)";
            }
            else if(WEB3EquityKeyItemDef.ORDER_START_DATE_TIME.equals(l_strKeyItem))
            {
                //��t�J�n����
                l_strOrderBy = l_strOrderBy + "order_start_datetime";
            }
            else if(WEB3EquityKeyItemDef.ORDER_END_DATE_TIME.equals(l_strKeyItem))
            {
                //��t�I������
                l_strOrderBy = l_strOrderBy + "order_end_datetime";
            }
            
            l_strOrderBy = l_strOrderBy + " ";
            if (WEB3AscDescDef.ASC.equals(l_equitySortKeys[i].ascDesc)) 
            {
                l_strOrderBy = l_strOrderBy + "asc";
            } 
            else if (WEB3AscDescDef.DESC.equals(l_equitySortKeys[i].ascDesc)) 
            {
                l_strOrderBy = l_strOrderBy + "desc";
            }

            if (i != (l_equitySortKeys.length -1))
            {
                l_strOrderBy = l_strOrderBy + ", ";
            }
        }        
        log.debug("�\�[�g���� = " + l_strOrderBy);

        Object l_bindVars[] =
        {
            l_strInstitutionCode
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    OffFloorOrderProductRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_bindVars);
                    
            int l_intSize = l_lisRows.size();
            if (l_intSize == 0)
            {
                return null;
            }
            else
            {
                l_rows = (OffFloorOrderProductRow[])l_lisRows.toArray(new OffFloorOrderProductRow[0]);
            }

        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        log.exiting(STR_METHOD_NAME);
        return l_rows;
    }
}@
