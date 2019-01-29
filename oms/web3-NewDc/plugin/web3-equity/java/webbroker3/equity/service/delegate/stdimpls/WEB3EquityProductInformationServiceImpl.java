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
filename	WEB3EquityProductInformationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������\���T�[�r�XImpl(WEB3EquityProductInformationServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 ���(SRA) �V�K�쐬
Revesion History : 2007/12/19 ��іQ (���u) ���f�� No.1238,1245,1266
Revesion History : 2009/09/21 �Ԑi (���u) ���f�� No.1334,No.1335,No.1341,No.1349
Revesion History : 2009/10/14 �����F (���u) ���f�� No.1351
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3MarginSysProductTypeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MarketOrderDesignateStopDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.define.WEB3EquityMarginAttributeDef;
import webbroker3.equity.define.WEB3EquityRegulationTypeDef;
import webbroker3.equity.message.WEB3EquityProductInformationRequest;
import webbroker3.equity.message.WEB3EquityProductInformationResponse;
import webbroker3.equity.service.delegate.WEB3EquityProductInformationService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

/**
 * �i�����������\���T�[�r�XImpl�j�B<BR>
 * <BR>
 * �����������\���T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityProductInformationServiceImpl
	extends WEB3EquityClientRequestService
	implements WEB3EquityProductInformationService 
{

	/**
	 * (���O�o�̓��[�e�B���e�B�B)
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3EquityProductInformationServiceImpl.class);
	/**
	 * @@roseuid XXXXXXXXXXX
	 */
	public WEB3EquityProductInformationServiceImpl()
	{

	}

	/**
	 * �����������\���T�[�r�X���������{����B<BR>
	 * <BR>
	 * �V�[�P���X�}�u�i�����������\���T�[�r�X�jexecute�v�Q�ƁB<BR>
	 * @@param l_request - ���N�G�X�g�f�[�^
	 * @@return webbroker3.common.message.WEB3GenResponse
	 * @@throws webbroker3.common.WEB3BaseException
	 * @@roseuid XXXXXXXXXXXX
	 */
	public WEB3GenResponse execute(WEB3GenRequest l_request)
		throws WEB3BaseException 
	{
		final String STR_METHOD_NAME =
			"WEB3EquityProductInformationServiceImpl.execute()";
		log.entering(STR_METHOD_NAME);
		            
		boolean executeFlg = true ; // �������I��������ꍇfalse���Z�b�g���āA�������X�L�b�v������

		EqtypeTradedProductRow l_row = new EqtypeTradedProductParams();
        BranchRow l_rowBranch = new BranchParams();
        WEB3EquityProductInformationRequest l_prodInfoRequest = null; 
        WEB3EquityProductInformationResponse l_prodInfoResponse =null;                   
               
		if (l_request instanceof WEB3EquityProductInformationRequest)
		{
			l_prodInfoRequest =  (WEB3EquityProductInformationRequest) l_request;
		}
		else
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"INPUT ���N�G�X�g NOT �����������\�����N�G�X�g");
		}

		String l_strProductCode = l_prodInfoRequest.productCode;
		String l_strMarketCode = l_prodInfoRequest.marketCode;
		String l_strOrderCommodityCode = l_prodInfoRequest.orderCommodityCode;

		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
		WEB3EquityOrderManager l_orderMgr =
		    (WEB3EquityOrderManager) l_tradingModule.getOrderManager();	
		WEB3GentradeFinObjectManager l_gentradeFinObjectManager = 
		    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

		try
        {
            //1.1.validate
            l_prodInfoRequest.validate();
        }
        //1.2.��O���X���[���ꂽ�ꍇ�͏������I������
        catch (WEB3BaseException b_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, b_ex);
            executeFlg = false;
        }
        
        //�����R�[�h���w�莞�͑S����null�̃��X�|���X��Ԃ�
        if (l_strProductCode == null)
        {
            return l_prodInfoRequest.createResponse();
        }
        
        if (executeFlg)
        {
			if (l_strOrderCommodityCode == null)
			{
                boolean l_blStockBatch = false; 
                boolean l_blMiniBatch = false; 
                boolean l_blMarginBatch = false; 
                boolean l_blStockSysStop = false; 
                boolean l_blMiniSysStop = false; 
                boolean l_blMarginSysStop = false;
                boolean l_blStockEx = false; 
                boolean l_blMiniEx = false; 
                boolean l_blMarginEx = false;        
        		//1.3.���򏈗��F���N�G�X�g.������t���i==null�̏ꍇ
        		////1.3.1.reset������t���i(01�F����)
				WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);

                try
                {
                    ////1.3.2.validate������t�\
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException b_ex)
                {
                    if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00011))
                    {
                        l_blStockBatch = true;
                    }
                    else if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00012))
                    {
                        l_blStockSysStop = true;
                    }
                    else
                    {
                        l_blStockEx = true;
                    }
                }
		
				////1.3.3.reset������t���i(02�F�����~�j����)
				WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.MINI_STOCK);

                try
                {
                    ////1.3.4.validate������t�\
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException b_ex)
                {
                    if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00011))
                    {
                        l_blMiniBatch = true;
                    }
                    else if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00012))
                    {
                        l_blMiniSysStop = true;
                    }
                    else
                    {
                        l_blMiniEx = true;
                    }
                }
		
				////1.3.5.reset������t���i(03�F�M�p���)
				WEB3GentradeTradingTimeManagement.resetOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);

                try
                {
                    ////1.3.6.validate������t�\
                    WEB3GentradeTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException b_ex)
                {
                    if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00011))
                    {
                        l_blMarginBatch = true;
                    }
                    else if (b_ex.getErrorInfo().equals(WEB3ErrorCatalog.BUSINESS_ERROR_00012))
                    {
                        l_blMarginSysStop = true;
                    }
                    else
                    {
                        l_blMarginEx = true;
                    }
                }

                //1.4.�S�ċً}��~�A�܂��͂����ꂩ�o�b�`�������̏ꍇ�������I��������
                if ((l_blStockBatch) || (l_blMiniBatch) || (l_blMarginBatch))
                {
                    executeFlg = false;
                }
                else if ((l_blStockSysStop) && (l_blMiniSysStop) && (l_blMarginSysStop))
                {
                    executeFlg = false;
                }
                else if ((l_blStockEx) || (l_blMiniEx) || (l_blMarginEx))
                {
                    executeFlg = false;
                }               
            }
        }
        
		if (executeFlg)
		{
        	//1.5.���򏈗��F���N�G�X�g.������t���i!=null�̏ꍇ
        	if (l_strOrderCommodityCode != null)
        	{
				try
				{
					//1.5.1.validate������t�\
					WEB3GentradeTradingTimeManagement.validateOrderAccept();
				}
                //1.6.�ً}��~���A�܂��̓o�b�`�������̏ꍇ�������I��������
				catch (WEB3BaseException b_ex)
				{
                    log.error(this.getClass().getName() + "." + STR_METHOD_NAME, b_ex);
					executeFlg = false; 
				}
        	}
		}
        
        WEB3GentradeSubAccount l_subAccount = null;
        Institution l_institution = null;
        String l_strInstitutionCode = null;
        WEB3GentradeBranch l_branch = null;
        List l_arryListedMarketCodes = new ArrayList();
        WEB3EquityProduct l_equityProduct = null;
        Market[] l_markets = null;
		if (executeFlg)
		{
			//1.7.get�⏕����
			l_subAccount = this.getSubAccount();

			l_institution = l_subAccount.getInstitution();
			l_strInstitutionCode = l_institution.getInstitutionCode();
            try
            {
    			l_branch =
    				(WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
    			//1.9.getProduct
	       		l_equityProduct = 
                    (WEB3EquityProduct)l_equityProductManager.getProduct(l_institution,l_strProductCode);
                //get����s��ꗗ()
                l_markets = l_equityProduct.getTradedMarkets();
            }
            catch (NotFoundException nf_ex)
            {
                executeFlg = false;
            }
        }

        WEB3GentradeMarket l_primMarket = null;
        if (executeFlg)
        {
			//1.10.Loop�����F��������.get����s��ꗗ()�̖߂�l�v�f���A�J�Ԃ��B
			int l_lngLength;
			if (l_markets == null)
			{
				l_lngLength = 0;
			}
			else
			{
				l_lngLength = l_markets.length;
			}

			int i = 0;
            Market l_market = null;
            boolean l_blisListing = true;
			for (int j = 0; j < l_lngLength; j++)
			{
                try
                {
				    //1.10.1.reset�s��R�[�h(�s��R�[�h:String)
				    WEB3GentradeTradingTimeManagement.resetMarketCode(l_markets[j].getMarketCode());

					//1.10.2.validate�������()
					WEB3EquityTradedProduct l_tradedProduct = 
						(WEB3EquityTradedProduct)l_orderMgr.validateTradedProduct(l_equityProduct, l_markets[j]);
					if (l_tradedProduct == null)
					{
                        if (l_markets[j].getMarketCode().equals(l_strMarketCode))
                        {
                            l_blisListing = false;                      
                        }
						continue;
					}
                }
				catch (WEB3BaseException l_ex)
				{	
                    if(l_markets[j].getMarketCode().equals(l_strMarketCode))
                    {
                        l_blisListing = false;
                    }
					continue;
				}
				//����������擾�ł��A���敪������łȂ��A��O���������Ȃ��Ƃ��́A
				//�L���Ȏs��R�[�h�ꗗ�Ƃ��ăZ�b�g����
                l_arryListedMarketCodes.add(l_markets[j].getMarketCode()) ;
			}
            
            //1.11.���򏈗��F�D��s����擾����            				
            if (l_strMarketCode == null || (l_strMarketCode != null && !l_blisListing))
            {
                //1.11.1.get�D��s��()
                l_primMarket = (WEB3GentradeMarket)l_equityProduct.getPrimaryMarket();

                if (l_primMarket == null)
                {
                    //1.12.�D��s�ꂪ�擾�ł��Ȃ��ꍇ�͖������̂݃Z�b�g���ď������I��������
                    executeFlg = false;
                }
            }
		}
		
        WEB3EquityTradedProduct l_eqtradedProduct = null;
		if (executeFlg)
		{
            try
            {
			    //1.13.reset�s��R�[�h
                //�擾�������N�G�X�g�̎s��R�[�h�Ŏ�������������B
                if (l_strMarketCode != null && l_primMarket == null)
                {
                    WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        
                    //1.14.get�������()
                    l_eqtradedProduct = 
                        (WEB3EquityTradedProduct) l_equityProductManager.getTradedProduct(
                                    l_institution,
                                    l_strProductCode,
                                    l_strMarketCode);
                }
                //�擾�����D��s��̎s��R�[�h�Ŏ�������������B
                else
                {
                    WEB3GentradeTradingTimeManagement.resetMarketCode(l_primMarket.getMarketCode());

                    //1.14.get�������()
                    l_eqtradedProduct = 
                        (WEB3EquityTradedProduct) l_equityProductManager.getTradedProduct(
                                    l_institution,
                                    l_strProductCode,
                                    l_primMarket.getMarketCode());
                }                   
            }
            catch (NotFoundException nf_ex) //1.15.����������擾�ł��Ȃ��ꍇ�͏������I��������  
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, nf_ex);
                executeFlg = false;
            }
		}
		
        String l_strstopHighPrice = null;
        String l_strstopLowPrice = null;  
        String[] l_tradeRegInfos = null;
        boolean l_blmarketMakeFlg = false;
        boolean l_isAdditionalCollateralRegulateBuyContract = false;
        boolean l_isAdditionalCollateralRegulateSellContract = false;
		if (executeFlg)
		{
            try
            {
                l_isAdditionalCollateralRegulateBuyContract =
                    l_equityProductManager.isAdditionalCollateralRegulateProduct(
                        l_subAccount,
                        l_eqtradedProduct,
                        Boolean.TRUE);
                
                l_isAdditionalCollateralRegulateSellContract =
                    l_equityProductManager.isAdditionalCollateralRegulateProduct(
                        l_subAccount,
                        l_eqtradedProduct,
                        Boolean.FALSE);

                //���N�G�X�g.�s��R�[�h �� null �̏ꍇ
                boolean l_blnIsPTSMarket = false;
                if (l_strMarketCode != null)
                {
                    //�g�����Z�I�u�W�F�N�g�}�l�[�W��
                    WEB3GentradeFinObjectManager l_finObjectManager =
                        (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                    //get�s��(�،���ЃR�[�h : , �s��R�[�h : )
                    //�،���ЁF�@@�،����.getInstitutionCode()�̖߂�l
                    //�s��R�[�h�F�@@���N�G�X�g.�s��R�[�h
                    WEB3GentradeMarket l_market = null;
                    try
                    {
                        l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                            l_strInstitutionCode,
                            l_strMarketCode);
                    }
                    catch(NotFoundException l_ex)
                    {
                        log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    //isPTS�s��( )
                    l_blnIsPTSMarket = l_market.isPTSMarket();
                }

                ////�������Row�I�u�W�F�N�g�̎擾
                l_row = (EqtypeTradedProductRow)l_eqtradedProduct.getDataSourceObject();
                l_rowBranch = (BranchRow)l_branch.getDataSourceObject();
                String l_strHandlingMM = String.valueOf(l_rowBranch.getHandlingMarketMake());

                //��������Row�̎擾
                EqtypeProductRow l_prodRow =
                    (EqtypeProductRow)l_eqtradedProduct.getProduct().getDataSourceObject();
                Timestamp l_tsDevidendRightDate = l_prodRow.getYearlyBooksClosingDate();

                //���N�G�X�g.�s��R�[�h == null�A�܂��́@@PTS�s��łȂ��ꍇ�iisPTS�s��() == false�j�j
                boolean l_blnIsDevRightDate = false;
                if (l_strMarketCode == null || !l_blnIsPTSMarket)
                {
                    //get������()
                    Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                    Timestamp l_tsOrderBizDate = new Timestamp(l_datOrderBizDate.getTime());                

                    //is����������()
                    l_blnIsDevRightDate =
                        l_orderMgr.isDevidendRightDate(l_tsOrderBizDate,l_tsDevidendRightDate);
                }

                WEB3EquityPTSOrderManager l_equityPTSOrderManager =
                    (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
                //PTS�s��̏ꍇ�iisPTS�s��() == true�j�j
                if (l_blnIsPTSMarket)
                {
                    //get������( )
                    Date l_datPTSOrderBizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
                    Timestamp l_tsPTSOrderBizDate = new Timestamp(l_datPTSOrderBizDate.getTime());         

                    // is�����������iPTS�j(Timestamp, Timestamp)
                    //�������F�@@PTS������ԊǗ�.get������()
                    //�����m����F�@@��������.���Z���i�������.getProduct()�Ŏ擾�ł��銔�������I�u�W�F�N�g���j
                    l_blnIsDevRightDate =
                        l_equityPTSOrderManager.isPTSDevidendRightDate(
                            l_tsPTSOrderBizDate, l_tsDevidendRightDate);
                }

			    //1.18.���򏈗��F�l���`�F�b�N�ΏۊO�����̏ꍇ
                boolean l_isRightDate = false;

                //�������i�i���́j�������.�L�����j
                Date l_datOrderBizDate = WEB3DateUtility.getDate(
                    l_row.getValidUntilBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                //�i*6�j�ȉ��̏����ɖ������Ă���̂ł���΁A�����������ԑт̏ꍇ�Ɣ��f����B
                // �E�@@������(�i���́j�������.�L����)����t�����̗��c�Ɠ��ł���
                //  �E�@@��t�����͉c�Ɠ��ł���B
                Timestamp l_orderTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
                Date l_datGentradeBizDate =
                    new WEB3GentradeBizDate(l_orderTime).roll(1);
                String l_strBizDateType = 
                    WEB3GentradeTradingTimeManagement.getBizDateType(l_orderTime);

                if (!(l_blnIsDevRightDate
                    && WEB3DateUtility.compare(WEB3DateUtility.toDay(l_datOrderBizDate),
                        WEB3DateUtility.toDay(l_datGentradeBizDate)) == 0
                    && !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType)
                    && !l_equityProductManager.isNextDayBasePriceMail(l_row.getInstitutionCode())))
                {
                    l_isRightDate = true;
                }
                boolean l_isJasdaq;
                if (l_strMarketCode != null)
                {
                    l_isJasdaq = WEB3MarketCodeDef.JASDAQ.equals(l_strMarketCode);
                }
                else
                {
                    l_isJasdaq = WEB3MarketCodeDef.JASDAQ.equals(l_primMarket.getMarketCode());
                }
                
                String l_strOpenOtcDiv = null;
                if (l_row.getOpenOtcDiv() != null)
                {
                    l_strOpenOtcDiv = l_row.getOpenOtcDiv();
                }
                else
                {
                    l_strOpenOtcDiv = WEB3OpenOtcDivDef.DEFAULT;
                }                
                
                if (l_isJasdaq && l_strOpenOtcDiv.equals(WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT))
                {
                    if (l_strHandlingMM.equals(WEB3DealtDef.CAN_DEALT))
                    {
                        l_blmarketMakeFlg = true;
                    }
                    else
                    {
                        executeFlg = false;
                    }
                }
                
                if (executeFlg)
                {
                    if (l_row.getPriceRangeType() != null
                         && !(l_row.getPriceRangeType().equals(WEB3PriceRangeTypeDef.NO_CHECK)) 
                         && l_isRightDate)
                    {
                        //PTS�s��łȂ��ꍇ(isPTS�s��() == false))
                        if (!l_blnIsPTSMarket)
                        {
                            ////1.18.1.get�l������l()
                            l_strstopHighPrice = WEB3StringTypeUtility.formatNumber(
                                l_orderMgr.getStopHighPrice(l_eqtradedProduct));

                            ////1.18.2.get�l�������l()
                            l_strstopLowPrice = WEB3StringTypeUtility.formatNumber(
                                l_orderMgr.getStopLowPrice(l_eqtradedProduct));
                        }
                    }
                    //�i���򏈗��F�l���`�F�b�N�Ώۖ����̏ꍇ�j
                    if (l_row.getPriceRangeType() != null
                        && !(l_row.getPriceRangeType().equals(WEB3PriceRangeTypeDef.NO_CHECK))
                        && !(l_blnIsDevRightDate))
                    {
                        //PTS�s��̏ꍇ
                        if (l_blnIsPTSMarket)
                        {
                            //get�l������l�iPTS�j(�������)
                            l_strstopHighPrice = WEB3StringTypeUtility.formatNumber(
                                l_equityPTSOrderManager.getPTSStopHighPrice(l_eqtradedProduct));
                            
                            //get�l�������l�iPTS�j(�������)
                            l_strstopLowPrice = WEB3StringTypeUtility.formatNumber(
                                l_equityPTSOrderManager.getPTSStopLowPrice(l_eqtradedProduct));
                        }
                    }
                    //1.19.get����K�����               
                    l_tradeRegInfos = this.getTradeRegulationInfo(l_eqtradedProduct);        
                }
            }
            catch(WEB3BaseException b_ex)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME, b_ex);
                executeFlg = false;
            }
		}

        try
        {  		
    		//1.20.createResponse
    		 l_prodInfoResponse = (WEB3EquityProductInformationResponse) l_prodInfoRequest.createResponse();
          
    		if (executeFlg) //����I��(executeFlg==true)���̃��X�|���X�Z�b�g
    		{
    			////������
                EqtypeProductRow l_productRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
    			l_prodInfoResponse.productName = l_productRow.getStandardName();

    			////�s��R�[�h�i�\���p�j
    			if (l_strMarketCode != null && l_primMarket == null)
    			{
    				l_prodInfoResponse.marketCodePriority = l_strMarketCode;
    			}
    			else
    			{
    				l_prodInfoResponse.marketCodePriority = l_primMarket.getMarketCode();
    			}

    			////�s��R�[�h�ꗗ
    			l_prodInfoResponse.marketList = (String[])l_arryListedMarketCodes.toArray(new String[0]);

    			////�}�[�P�b�g���C�N�����t���O(Boolean�^)
    			l_prodInfoResponse.marketMakeFlag = Boolean.valueOf(l_blmarketMakeFlg);

    			////�����P��
                l_prodInfoResponse.dealingUnit =
                    WEB3StringTypeUtility.formatNumber(l_row.getLotSize());  			

    			////�l������l
    			l_prodInfoResponse.upperPriceRange = l_strstopHighPrice;
    			
    			////�l�������l
    			l_prodInfoResponse.lowerPriceRange = l_strstopLowPrice;
    			
    			////�M�p����
                if (l_row.getMarginSysProductType() != null)
                {
                    if (l_row.getMarginSysProductType().equals(
                        WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_LOAN))
                    {
                        l_prodInfoResponse.marginAttribute =
                            WEB3EquityMarginAttributeDef.MARGIN_ATTRIBUTE_PRODUCT_LOAN;
                    }
                    else if (l_row.getMarginSysProductType().equals(
                        WEB3MarginSysProductTypeDef.MARGIN_SYS_PRODUCT_NO_LOAN))
                    {
                        l_prodInfoResponse.marginAttribute =
                            WEB3EquityMarginAttributeDef.MARGIN_ATTRIBUTE_PRODUCT_NOLOAN;
                    }
                    else
                    {
                        l_prodInfoResponse.marginAttribute =
                            WEB3EquityMarginAttributeDef.MARGIN_ATTRIBUTE_PRODUCT_OTHER;
                    }
                }
                else
                {
                    l_prodInfoResponse.marginAttribute =
                        WEB3EquityMarginAttributeDef.MARGIN_ATTRIBUTE_PRODUCT_OTHER;
                }
    			
    			////����K��
    			l_prodInfoResponse.tradingRegulation = l_tradeRegInfos;

    			////���ʒl�����������t���O(Boolean�^)
                if (l_row.getPriceRangeType() != null)
                {
                    if (l_row.getPriceRangeType().equals(WEB3PriceRangeTypeDef.NO_CHECK))
                    {
                        l_prodInfoResponse.specialPriceRangeFlag = Boolean.valueOf(true);
                    }
                    else
                    {
                        l_prodInfoResponse.specialPriceRangeFlag = Boolean.valueOf(false);
                    }
                }
                else
                {
                    l_prodInfoResponse.specialPriceRangeFlag = Boolean.valueOf(false);
                }

    			////����������������t���O(Boolean�^)
                if (l_row.getTodayDepFundReg() != null)
                {
                    if (l_row.getTodayDepFundReg().equals(BooleanEnum.TRUE))
                    {
                        l_prodInfoResponse.sameDayCollectionFlag = Boolean.valueOf(true);
                    }
                    else
                    {
                        l_prodInfoResponse.sameDayCollectionFlag = Boolean.valueOf(false);
                    }
                }
                else
                {
                    l_prodInfoResponse.sameDayCollectionFlag = Boolean.valueOf(false);
                }

    			////��p�|��
    			l_prodInfoResponse.marginRatio = WEB3StringTypeUtility.formatNumber(l_equityProduct.getMarginRatio());
                
                if (l_isAdditionalCollateralRegulateBuyContract ||
                    l_isAdditionalCollateralRegulateSellContract)
                {
                    l_prodInfoResponse.additionalCollateralRegulateFlag = Boolean.TRUE;
                    if (l_isAdditionalCollateralRegulateBuyContract)
                    {
                        l_prodInfoResponse.buyMarginDepositRate =
                            WEB3StringTypeUtility.formatNumber(l_row.getLongMarginDepositRate());
                        l_prodInfoResponse.buyCashMarginDepositRate =
                            WEB3StringTypeUtility.formatNumber(l_row.getLongCashMarginDepositRate());
                    }
                    if (l_isAdditionalCollateralRegulateSellContract)
                    {
                        l_prodInfoResponse.sellMarginDepositRate =
                            WEB3StringTypeUtility.formatNumber(l_row.getShortMarginDepositRate());
                        l_prodInfoResponse.sellCashMarginDepositRate =
                            WEB3StringTypeUtility.formatNumber(l_row.getShortCashMarginDepositRate());
                    }
                }
                else
                {
                    l_prodInfoResponse.additionalCollateralRegulateFlag = Boolean.FALSE;
                }
    		}
    		else  //�������s(executeFlg==false)���̃��X�|���X�Z�b�g
    		{
    			//�S�Ă̍��ڂ�null���Z�b�g����
                //�������A�����������Ă���ꍇ�͖������̂݃Z�b�g�B
                if (l_equityProduct != null)
                {
                    EqtypeProductRow l_productRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
                    l_prodInfoResponse.productName = l_productRow.getStandardName();
                }
                else
                {
                    l_prodInfoResponse.productName = null; 
                }
                
    			l_prodInfoResponse.marketCodePriority = null;
    			l_prodInfoResponse.marketList = null;
    			l_prodInfoResponse.marketMakeFlag = null;
    			l_prodInfoResponse.dealingUnit = null;
    			l_prodInfoResponse.upperPriceRange = null;
    			l_prodInfoResponse.lowerPriceRange = null;
    			l_prodInfoResponse.marginAttribute = null;
    			l_prodInfoResponse.tradingRegulation = null;
    			l_prodInfoResponse.specialPriceRangeFlag = null;
    			l_prodInfoResponse.sameDayCollectionFlag = null;
    			l_prodInfoResponse.marginRatio = null;
                l_prodInfoResponse.additionalCollateralRegulateFlag = null;
                l_prodInfoResponse.buyMarginDepositRate = null;
                l_prodInfoResponse.buyCashMarginDepositRate = null;
                l_prodInfoResponse.sellMarginDepositRate = null;
                l_prodInfoResponse.sellCashMarginDepositRate = null;
    		}
        }
        catch (Exception ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, ex);
            //�S�Ă̍��ڂ�null���Z�b�g����
            //�������A�����������Ă���ꍇ�͖������̂݃Z�b�g�B
            if (l_equityProduct != null)
            {
                EqtypeProductRow l_productRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();
                l_prodInfoResponse.productName = l_productRow.getStandardName();
            }
            else
            {
                l_prodInfoResponse.productName = null; 
            }
            l_prodInfoResponse.marketCodePriority = null;
            l_prodInfoResponse.marketList = null;
            l_prodInfoResponse.marketMakeFlag = null;
            l_prodInfoResponse.dealingUnit = null;
            l_prodInfoResponse.upperPriceRange = null;
            l_prodInfoResponse.lowerPriceRange = null;
            l_prodInfoResponse.marginAttribute = null;
            l_prodInfoResponse.tradingRegulation = null;
            l_prodInfoResponse.specialPriceRangeFlag = null;
            l_prodInfoResponse.sameDayCollectionFlag = null;
            l_prodInfoResponse.marginRatio = null;
            l_prodInfoResponse.additionalCollateralRegulateFlag = null;
            l_prodInfoResponse.buyMarginDepositRate = null;
            l_prodInfoResponse.buyCashMarginDepositRate = null;
            l_prodInfoResponse.sellMarginDepositRate = null;
            l_prodInfoResponse.sellCashMarginDepositRate = null;
        }

		log.exiting(STR_METHOD_NAME);
		return l_prodInfoResponse;
    }
    

	/**
	 * (get����K�����)<BR>
	 * �������������K�������擾����B<BR>
	 * �i���X�|���X.����K��[]�z��֐ݒ肷��B�j<BR>
	 * �P�j�@@�����̎�������I�u�W�F�N�g���<BR>
	 * �@@���������I�u�W�F�N�g���擾����B<BR>
	 * �@@�g���v���_�N�g�}�l�[�W��.getProduct(�،���ЃI�u�W�F�N�g�C<BR>
	 * �@@�����̎������.����ID�ɊY����������R�[�h�j<BR>
	 * <BR>
	 * �Q�j�@@�������n�̎���K�����擾�p�̊�����������I�u�W�F�N�g���擾����B<BR>
	 * <BR>
	 * �Q?�P�j�@@����J�����_�R���e�L�X�g.��t���ԋ敪 ���ăZ�b�g����B <BR>
	 * <BR>
	 * �@@��t���ԋ敪�F�@@"�������n"���Z�b�g�B <BR>
	 * <BR>
	 * �Q?�Q�j�@@�������n�̔������x�[�X�̊�����������I�u�W�F�N�g���擾����B <BR>
	 * <BR>
	 * �@@�g���v���_�N�g�}�l�[�W��.getTradedProduct(�����̎������.�������ID)���R�[������B <BR>
	 * <BR>
	 * �R�j�@@�~�j���̎���K�����擾�p�̊�����������I�u�W�F�N�g���擾����B <BR>
	 * <BR>
	 * �R?�P�j�@@����J�����_�R���e�L�X�g.��t���ԋ敪�A�s��R�[�h ���ăZ�b�g����B <BR>
	 * <BR>
	 * �@@��t���ԋ敪�F�@@"�����~�j����"���Z�b�g�B <BR>
	 * �@@�s��R�[�h�F�@@"DEFAULT"���Z�b�g�B <BR>
	 * <BR>
	 * �R?�Q�j�@@�~�j���̔������x�[�X�́A�~�j���戵�s��ɂ����銔����������I�u�W�F�N�g���擾����B <BR>
	 * <BR>
	 * �@@�g���v���_�N�g�}�l�[�W��.get�~�j���戵�������(�P�j�Ŏ擾�������������I�u�W�F�N�g)�� <BR>
	 * �@@�R�[������B <BR>
	 * �@@���߂�l��null�i==�~�j���戵�Ȃ��̖����j�܂��͗�O��throw���ꂽ�ꍇ�A<BR>
	 * �@@���~�j���̎���K���̓`�F�b�N���Ȃ��B <BR>
	 * <BR>
	 * �S�j�@@�P)�Ŏ擾�������������I�u�W�F�N�g�A�����̎�������I�u�W�F�N�g�A<BR>
	 * �@@�Q�j�y�тR�j�Ŏ擾����������������I�u�W�F�N�g������K�������擾����B<BR>
	 * �����������A��������̎Q�ƍ��ڂɂ��Ă͈ȉ��L�q�B<BR>
	 * �i�\�̍��ڐ����Q�ƁB�j <BR>
	 * ������K�����擾���@@�ɂ��Ă�<BR>
	 * �w�@@�\��`���i2.1.��������ʕ\�������j<BR>
	 * 8)����K�������擾����B�x�̕\���Q�ƁB<BR>
     * <BR>
     * �����\�b�h���ŗ�O�����������ꍇ�Anull��Ԃ��B<BR>
	 * <BR>
	 * @@param l_equityTradedProduct- �������
     * @@return java.lang.String[]
	 * @@roseuid XXXXXXXXXXX
	 */
	protected String[] getTradeRegulationInfo(WEB3EquityTradedProduct l_equityTradedProduct)
	{
		final String STR_METHOD_NAME =
			"WEB3EquityProductInformationServiceImpl.getTradeRegulationInfo()";
		log.entering(STR_METHOD_NAME);

		List l_arryTrdRegInfolist = new ArrayList(); //����K�����i�[�p���X�g
        String[] l_strTrdRegInfolist = new String[0];

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityTradingModule l_tradingModule
                       = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
               
            ////�g���v���_�N�g�}�l�[�W�����擾 
            WEB3EquityProductManager l_EqProdMgr
                = (WEB3EquityProductManager) l_tradingModule.getProductManager();

            //�����R�[�h���擾
            String l_strProdCd = l_equityTradedProduct.getProductCode(); 
            
            //�⏕��������،���ЃI�u�W�F�N�g���擾
            SubAccount l_subAccount = this.getSubAccount();
            Institution l_instInstitution = l_subAccount.getInstitution();
            
			//��������Row�I�u�W�F�N�g�擾
			WEB3EquityProduct l_equityProduct = 
				(WEB3EquityProduct) l_EqProdMgr.getProduct(l_instInstitution,l_strProdCd);
			EqtypeProductRow l_rowEqProd =
				(EqtypeProductRow) l_equityTradedProduct.getProduct().getDataSourceObject();
			
            //�������Row�I�u�W�F�N�g�擾
			EqtypeTradedProductRow l_rowEqTrdProd =
			    (EqtypeTradedProductRow) l_equityTradedProduct.getDataSourceObject();
            
            //�������n�̎���K�����擾�p�̊�����������I�u�W�F�N�g�擾
			WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
			EqTypeTradedProduct l_swapEqTrdProd =
			    (EqTypeTradedProduct)l_EqProdMgr.getTradedProduct(l_rowEqTrdProd.getTradedProductId());
			EqtypeTradedProductRow l_rowSwapEqTrdProd =
			    (EqtypeTradedProductRow) l_swapEqTrdProd.getDataSourceObject();

			//�~�j���̎���K�����擾�p�̊�����������I�u�W�F�N�g�擾
			WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MINI_STOCK);
			WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);
			WEB3EquityTradedProduct l_miniEqTrdProd =
				l_EqProdMgr.getMiniStockTradedProduct(l_equityProduct);
            EqtypeTradedProductRow l_rowMiniEqTrdProd = null;
            if (l_miniEqTrdProd != null)
            {
			    l_rowMiniEqTrdProd =
				    (EqtypeTradedProductRow) l_miniEqTrdProd.getDataSourceObject();
            }
            
            //�@@�������~
            String l_strStockTrdStop = String.valueOf(l_rowEqProd.getTradeStop());//��������������~
            String l_strMarSysTrdStop = String.valueOf(l_rowEqProd.getMarginSysTradeStop());//���x�M�p���������~
            String l_strMarGenTrdStop = String.valueOf(l_rowEqProd.getMarginGenTradeStop());//��ʐM�p���������~                                           
            //�A���������~
            String l_strBuyCashStop = String.valueOf(l_rowEqTrdProd.getBuyCashStop());//��������~
            String l_strSellCashStop = String.valueOf(l_rowEqTrdProd.getSellCashStop());//��������~          
            //�B�~�j�������~
			String l_strBuyMiniStockStop = WEB3TradeStopDef.ACTIVE;
			String l_strSellMiniStockStop = WEB3TradeStopDef.ACTIVE;
            if (l_miniEqTrdProd != null)
            {
				l_strBuyMiniStockStop = String.valueOf(l_rowMiniEqTrdProd.getBuyMiniStockStop());//���~�j����~
				l_strSellMiniStockStop = String.valueOf(l_rowMiniEqTrdProd.getSellMiniStockStop());//���~�j����~
            }
            //�C�M�p�V�K����~
            String l_strLongMarginSysStop = String.valueOf(l_rowEqTrdProd.getLongMarginSysStop());//�����x�M�p��~
            String l_strShortMarginSysStop = String.valueOf(l_rowEqTrdProd.getShortMarginSysStop());//�����x�M�p��~
            String l_strLongMarginGenStop = String.valueOf(l_rowEqTrdProd.getLongMarginGenStop());//����ʐM�p��~
            String l_strShortMarginGenStop = String.valueOf(l_rowEqTrdProd.getShortMarginGenStop());//����ʐM�p��~
            //�D�M�p�ԍϒ�~
            String l_strLongCloseMarginSysStop = String.valueOf(l_rowEqTrdProd.getLongCloseMarginSysStop());//�����ԍϐ��x�M�p��~
            String l_strShortCloseMarginSysStop = String.valueOf(l_rowEqTrdProd.getShortCloseMarginSysStop());//�����ԍϐ��x�M�p��~
            String l_strLongCloseMarginGenStop = String.valueOf(l_rowEqTrdProd.getLongCloseMarginGenStop());//�����ԍψ�ʐM�p��~
            String l_strShortCloseMarginGenStop = String.valueOf(l_rowEqTrdProd.getShortCloseMarginGenStop());//�����ԍψ�ʐM�p��~
            //�E�M�p�������n��~
            String l_strLongSwapMarginSysStop = String.valueOf(l_rowSwapEqTrdProd.getLongSwapMarginSysStop());//�������x�M�p��~
            String l_strShortSwapMarginSysStop = String.valueOf(l_rowSwapEqTrdProd.getShortSwapMarginSysStop());//���n���x�M�p��~
            String l_strLongSwapMarginGenStop = String.valueOf(l_rowSwapEqTrdProd.getLongSwapMarginGenStop());//������ʐM�p��~
            String l_strShortSwapMarginGenStop = String.valueOf(l_rowSwapEqTrdProd.getShortSwapMarginGenStop());//���n��ʐM�p��~
            //�F���s���������~
            String l_strBuySpotMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getBuySpotMarketOrdDesStop());//���������s�w���~
            String l_strSellSpotMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getSellSpotMarketOrdDesStop());//���������s�w���~
            //�G���s�M�p�V�K����~
            String l_strLongMsMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getLongMsMarketOrdDesStop());//�����x�M�p���s�w���~
            String l_strShortMsMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getShortMsMarketOrdDesStop());//�����x�M�p���s�w���~
            String l_strLongMgMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getLongMgMarketOrdDesStop());//����ʐM�p���s�w���~
            String l_strShortMgMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getShortMgMarketOrdDesStop());//����ʐM�p���s�w���~
            //�H���s�M�p�ԍϒ�~
            String l_strLongCmsMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getLongCmsMarketOrdDesStop());//�����ԍϐ��x�M�p���s�w���~
            String l_strShortCmsMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getShortCmsMarketOrdDesStop());//�����ԍϐ��x�M�p���s�w���~
            String l_strLongCmgMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getLongCmgMarketOrdDesStop());//�����ԍψ�ʐM�p���s�w���~
            String l_strShortCmgMarketOrdDesStop = String.valueOf(l_rowEqTrdProd.getShortCmgMarketOrdDesStop());//�����ԍψ�ʐM�p���s�w���~
        
            //�e�T�[�r�X�̎���K����ԇ@@�`�H���Q�����z��Ɋi�[
            String[][] l_strTrdReglists = 
            {
                {l_strStockTrdStop,l_strMarSysTrdStop,l_strMarGenTrdStop},
                {l_strBuyCashStop,l_strSellCashStop},
                {l_strBuyMiniStockStop,l_strSellMiniStockStop},
                {l_strLongMarginSysStop,l_strShortMarginSysStop,l_strLongMarginGenStop,l_strShortMarginGenStop},
                {l_strLongCloseMarginSysStop,l_strShortCloseMarginSysStop,l_strLongCloseMarginGenStop,l_strShortCloseMarginGenStop},
                {l_strLongSwapMarginSysStop,l_strShortSwapMarginSysStop,l_strLongSwapMarginGenStop,l_strShortSwapMarginGenStop},
                {l_strBuySpotMarketOrdDesStop,l_strSellSpotMarketOrdDesStop},
                {l_strLongMsMarketOrdDesStop,l_strShortMsMarketOrdDesStop,l_strLongMgMarketOrdDesStop,l_strShortMgMarketOrdDesStop},
                {l_strLongCmsMarketOrdDesStop,l_strShortCmsMarketOrdDesStop,l_strLongCmgMarketOrdDesStop,l_strShortCmgMarketOrdDesStop}                      
            };
            
            int i = 0;
            //���������~(i==0)
            if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
               && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
               && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
            {
                //������~��
                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP);   
            }
            else
            {
                if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])))
                {
                    //���������~��
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP);
                }
            
                if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                         && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
                {
                    //�M�p�����~��
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARTRDSTOP);
                }
            
                if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                         && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                {
                    //���x�M�p�����~��
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP);
                }
            
                if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                         && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
                {
                    //��ʐM�p�����~��
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP);
                } 
            }
            
            //�����������~(i==1)�E�~�j�������~(i==2)
            if(!l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP))
            {
                for(i = 1 ; i<3 ; i++)
                {
                    if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])) 
                        && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                    {
                        //�����~��
                        if(i == 1 &&
                           !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
                        {
                            //����
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP);
                        }
                        else if(i == 2 &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
                        {
                            //�~�j��
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MINISTOCKSTOP);
                        }
                    }
                    else if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                             && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                    {
                        //���t��~��
                        if(i == 1 &&
                           !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
                        {
                            //����
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_BUYCASHSTOP);
                        }
                        else if(i == 2 &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP) &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_BUYCASHSTOP))
                        {
                            //�~�j��
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_BUYMINISTOCKSTOP);
                        }                    
                    }
                    else if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                             && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                    {
                        //���t��~��
                        if(i == 1 &&
                           !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
                        {
                            //����
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SELLCASHSTOP);
                        }
                        else if(i == 2 &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP) &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SELLCASHSTOP))
                        {
                            //�~�j��
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SELLMINISTOCKSTOP);
                        }                    
                    }
                }                
            }

       
            //���M�p�V�K����~(i==3)�E�M�p�ԍϒ�~(i==4)�E�M�p�������n��~(i==5)
            if(!l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP) &&
               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARTRDSTOP))
            {
                for(i = 3 ; i<6 ; i++)
                {
                    if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                        && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                        && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                        && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                    {
                        //�M�p������~��
                        if(i == 3)
                        {
                            //�V�K��
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP);
                        }
                        else if(i == 4)
                        {
                            //�ԍ�
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP);
                        }
                        else if(i == 5)
                        {
                            //�������n
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SWAPMARGINSTOP);                  
                        }
                    }
                    else
                    {
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
                        {
                            //�M�p��������~��
                            if(i == 3)
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSTOP);
                            }
                            else if(i == 4)
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSTOP);
                            }
                            else if(i == 5)
                            {
                                //�������n
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGSWAPMARGINSTOP);                   
                            }                    
                        }
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //�M�p��������~��
                            if(i == 3)
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSTOP);
                            }
                            else if(i == 4)
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSTOP);
                            }
                            else if(i == 5)
                            {
                                //�������n
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTSWAPMARGINSTOP);               
                            }                    
                        }
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                        {
                            //���x�M�p������~��
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSYSSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSYSSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //�������n
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SWAPMARGINSYSSTOP);                  
                            }                    
                        }
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //��ʐM�p������~��
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINGENSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINGENSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //�������n
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SWAPMARGINGENSTOP);                    
                            }                    
                        }
                        if(!(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                        {
                            //���x�M�p��������~��
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSYSSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSYSSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //�������n
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGSWAPMARGINSYSSTOP);                    
                            }                    
                        }
                        if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3]))
                        {
                            //���x�M�p��������~��
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSYSSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSYSSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP))
                            {
                                //�������n
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTSWAPMARGINSYSSTOP);                   
                            }
                        }
                        if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3]))
                        {
                            //��ʐM�p��������~��
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINGENSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINGENSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //�������n
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGSWAPMARGINGENSTOP);                    
                            }                    
                        }
                        if(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                                  && WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][2])
                                  && !(WEB3TradeStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //��ʐM�p��������~��
                            if(i == 3 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINGENSTOP);
                            }
                            else if(i == 4 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINGENSTOP);
                            }
                            else if(i == 5 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP))
                            {
                                //�������n
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTSWAPMARGINGENSTOP);                   
                            }                    
                        }
                    }                                       
                }                          
            }

            i = 6;
            //�����s���������~(i==6)
            if(!l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP) &&
               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_STOCKTRDSTOP))
            {
                if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0])) 
                    && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                {
                    //���s���������~��
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SPOTMARKETORDDESSTOP);
                }
                else if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                         && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                         && !(l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_BUYCASHSTOP)))
                {
                    //���s�������t��~��
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_BUYSPOTMARKETORDDESSTOP);
                }
                else if(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                         && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                         && !(l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SELLCASHSTOP)))
                {
                    //���s�������t��~��
                    l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SELLSPOTMARKETORDDESSTOP);
                }                 
            }
       
        
            //�����s�M�p�V�K����~(i==7)�E���s�M�p�ԍϒ�~(i==8)
            if(!l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_TRDSTOP) &&
               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARTRDSTOP))
            {
                for(i = 7 ; i<9 ; i++)
                {
                    if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                        && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                        && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                        && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                    {
                        //���s�M�p������~��
                        if(i == 7 &&
                           !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP))
                        {
                            //�V�K��
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MMARKETORDDESSTOP);
                        }
                        else if(i == 8 &&
                                 !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP))
                        {
                            //�ԍ�
                            l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CMMARKETORDDESSTOP);
                        }
                    }
                    else
                    {
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2])))
                        {
                            //���s�M�p��������~��
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCMMARKETORDDESSTOP);
                            }
                        }
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //���s�M�p��������~��
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCMMARKETORDDESSTOP);
                            }
                        }
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])))
                        {
                            //���s���x�M�p������~��
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSYSSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MSMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSYSSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CMSMARKETORDDESSTOP);
                            }
                        }
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //���s��ʐM�p������~��
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINGENSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_MGMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINGENSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_CMGMARKETORDDESSTOP);
                            }
                        }
                        if(!(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0]))
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                        {
                            //���s���x�M�p��������~��
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSYSSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMSMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSYSSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCMSMARKETORDDESSTOP);
                            }
                        }
                        if(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1]))
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3]))
                        {
                            //���s���x�M�p��������~��
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSYSSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMSMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARSYSTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSYSSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCMSMARKETORDDESSTOP);
                            }
                        }
                        if(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][0])
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2]))
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3]))
                        {
                            //���s��ʐM�p��������~��
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINGENSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGMGMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINGENSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_LONGCMGMARKETORDDESSTOP);
                            }
                        }
                        if(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][1])
                                  && WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][2])
                                  && !(WEB3MarketOrderDesignateStopDef.ACTIVE.equals(l_strTrdReglists[i][3])))
                        {
                            //���s��ʐM�p��������~��
                            if(i == 7 &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMARGINSTOP) &&
                               !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGINGENSTOP))
                            {
                                //�V�K��
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTMGMARKETORDDESSTOP);
                            }
                            else if(i == 8 &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_MARGENTRDSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCLOSEMARGINSTOP) &&
                                     !l_arryTrdRegInfolist.contains(WEB3EquityRegulationTypeDef.REG_TYPE_CLOSEMARGINGENSTOP))
                            {
                                //�ԍ�
                                l_arryTrdRegInfolist.add(WEB3EquityRegulationTypeDef.REG_TYPE_SHORTCMGMARKETORDDESSTOP);
                            }
                        }
                    }
                }
            }
        }
        catch(WEB3BaseException b_ex)
        {
            log.error(this.getClass().getName() , b_ex);
            log.error("����K�����̎擾�Ɏ��s���܂���");
			log.exiting(STR_METHOD_NAME);
            return null;                      
        }
        catch(NotFoundException nf_ex)
        {
            log.error(this.getClass().getName() , nf_ex);
            log.error("����K�����̎擾�Ɏ��s���܂���");
			log.exiting(STR_METHOD_NAME);
            return null; 
        }        
		log.exiting(STR_METHOD_NAME);           
        return (String[]) l_arryTrdRegInfolist.toArray(l_strTrdRegInfolist);
    }
}
@
