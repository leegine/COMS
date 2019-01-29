head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAssetInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���t�ꗗ�T�[�r�XImpl(WEB3EquityAssetInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �C�ї� (���u) �V�K�쐬
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;

import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityAssetUnit;
import webbroker3.equity.message.WEB3EquityProductCodeNameUnit;
import webbroker3.equity.message.WEB3EquitySellListRequest;
import webbroker3.equity.message.WEB3EquitySellListResponse;
import webbroker3.equity.message.WEB3EquitySortKey;
import webbroker3.equity.service.delegate.WEB3EquityAssetInquiryService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

/**
 * �i���t�ꗗ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ���t�ꗗ�T�[�r�X�����N���X
 * @@version 1.0  
 */
public class WEB3EquityAssetInquiryServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityAssetInquiryService 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityAssetInquiryServiceImpl.class);
    /**
     * @@roseuid 409F383D0346
     */
    public WEB3EquityAssetInquiryServiceImpl()
    {

    }

    /**
     * ���t�ꗗ�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���t�ꗗ�n���h���j���N�G�X�g�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406030A90384
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "WEB3EquityAssetInquiryServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);

        WEB3EquitySellListRequest l_inquiryRequest = null;
        String l_strProductCode = null;
        String l_strMarketCode = null;
		String l_strInstitutionCode = null; // �،���ЃR�[�h
		String l_strmarketCode = null;
        
        
        if (l_request instanceof WEB3EquitySellListRequest)
        {
            l_inquiryRequest = (WEB3EquitySellListRequest) l_request;
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT ���N�G�X�g NOT ���t�ꗗ���N�G�X�g");
        }

        l_strProductCode = l_inquiryRequest.productCode;
        l_strMarketCode = l_inquiryRequest.marketCode;

        //1.1.validate
        l_inquiryRequest.validate();
        
        //1.2.validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
		//1.3.get�⏕����
		SubAccount l_subAccount = this.getSubAccount();

        //1.4.getEqTypeOrderValidator
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        //�g���v���_�N�g�}�l�[�W�����擾 
        WEB3EquityProductManager l_equityProductManager
                   = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        
        //�g�����������}�l�[�W���𐶐�
        WEB3EquityOrderManager l_orderMgr =
                    (WEB3EquityOrderManager) l_tradingModule.getOrderManager();

        //1.5.validate����\�ڋq
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult = 
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult != OrderValidationResult.VALIDATION_OK_RESULT)
        {
            log.error("__Error[validate����\�ڋq���`�F�b�N]__");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                "execute");
        }

		//1.6.createResponse
		WEB3EquitySellListResponse l_inquiryResponse =
			(WEB3EquitySellListResponse) l_inquiryRequest.createResponse();

        //1.7.get����X
        WEB3GentradeBranch l_branch =
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();

        //1.8.get�s��ǌx���s��
        String[] l_TradeOpenMarket = null;
        l_TradeOpenMarket =
            WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_branch,
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);

        l_inquiryResponse.messageSuspension = l_TradeOpenMarket;

		try
		{        

        	//1.9.validate�ڋq�����ʎ����~�R�[��
			l_orderMgr.validateAccountProductOrderStop(
							l_subAccount,
							0,
							OrderTypeEnum.EQUITY_SELL
							);

        	//1.10.���N�G�X�g.�����R�[�h���w�肳��Ă���ꍇ
            //�،���ЃR�[�h���擾
			Institution l_institution = l_subAccount.getInstitution(); //�،����
            l_strInstitutionCode = l_institution.getInstitutionCode();
			EqTypeProduct l_eqtypeProduct = null;
            if (l_strProductCode != null && l_strProductCode.length() != 0)
            {
                //1.10.1 validate�����R�[�h
				l_eqtypeProduct = l_orderMgr.validateProductCode(
                    					l_strProductCode,
                    					l_strInstitutionCode);
                    				
				//1.10.2 validate�C���T�C�_�[
				l_orderMgr.validateInsider(
					l_subAccount,
					l_eqtypeProduct);
							
				//1.10.3 validate�ڋq�����ʎ����~�R�[�� 
				l_orderMgr.validateAccountProductOrderStop(
					l_subAccount,
					l_eqtypeProduct.getProductId(),
					OrderTypeEnum.EQUITY_SELL
					);
								
            }
            
        	//1.11.���N�G�X�g.�s��R�[�h���w�肳��Ă���ꍇ
			l_strmarketCode = l_inquiryRequest.marketCode;
       		Market l_market = null;
        	if (l_strmarketCode != null && l_strmarketCode.length() !=0)
        	{
        		//1.11.1.validate�s��R�[�h
        		l_market = l_orderMgr.validateMarket(
								l_strmarketCode,
								l_strInstitutionCode);
					
        	}

			//1.12.���N�G�X�g.�����R�[�h�y�у��N�G�X�g.�s��R�[�h���w�肳��Ă���ꍇ
            log.debug("=====> productCode = " + l_strProductCode);
            log.debug("=====> marketCode = " + l_strmarketCode);
            
            TradedProduct l_tradedProduct = null;
            if (l_strProductCode != null && l_strmarketCode != null
                && l_strProductCode.length() != 0 && l_strmarketCode.length() != 0)
            {
                log.debug(
                    "==> productCode, marketCode not null, and length not 0!");
                
                //1.12.1.validate�������
				l_tradedProduct = l_orderMgr.validateTradedProduct(
										l_subAccount,
										l_eqtypeProduct,
										l_market,
										true					
										);
                
                //1.12.2.validate�戵�\�s��    
                l_orderMgr.validateHandlingMarket(
						l_branch,
						l_tradedProduct);
                    
            }
		}
		catch (WEB3BaseException l_ex)		
		{
			log.error(STR_METHOD_NAME, l_ex);
			log.exiting(STR_METHOD_NAME);
			l_inquiryResponse.errorMessage = l_ex.getErrorInfo().getErrorMessage();
			return l_inquiryResponse;
		}

			//get�ۗL���Y�ꗗ()�̏���
			//1.create��������������
			  String l_strConditionList = createSearchConditionList(l_inquiryRequest);

			//2.create���������f�[�^�R���e�i
			  String[] l_searchConditionDataContainer =
				  createSearchConditionDataContainer(l_inquiryRequest);

			//3.create�\�[�g����(���������\�[�g�L�[[ ])
			  WEB3EquitySortKey[] l_sortKeys = l_inquiryRequest.sortKeys;
			  String l_strSortCondition = createSortCondition(l_sortKeys);		
		
			//4.get�ۗL���Y�ꗗ()		
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            List l_assetList =
                l_positionManager.getSellableAssets(
                    l_subAccount,
					ProductTypeEnum.EQUITY,
					null,
					null,
					l_strSortCondition);

            WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

            /**
             * 1.13.(*1)�����v���_�E���E�s��v���_�E���쐬
             * **/
            l_inquiryResponse =
                pulldown(
		            l_assetList,
					l_subAccount,
                    l_strInstitutionCode,
                    l_branch,
                    l_gentradeFinObjectManager,
                    l_equityProductManager,
                    l_inquiryResponse);

			if(l_inquiryResponse.productCodeNames == null || l_inquiryResponse.marketList == null)
			{
				l_inquiryResponse.equityAssetUnits = null;
				l_inquiryResponse.totalPages   = "0";
				l_inquiryResponse.totalRecords = "0";
				l_inquiryResponse.pageIndex    = "0";
				l_inquiryResponse.messageSuspension = l_TradeOpenMarket;
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01037,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}

            /**
             * 1.14.(*2)���t�\�ꗗ�쐬
             **/
            l_inquiryResponse =  sellList(
                    l_inquiryRequest,
                    l_positionManager,
                    l_subAccount,
                    l_branch,
                    l_strInstitutionCode,
                    l_gentradeFinObjectManager,
                    l_equityProductManager,
                    l_inquiryResponse);
                    
            log.debug(
                "== > l_inquiryResponse.totalRecords "
                    + l_inquiryResponse.totalRecords);

            log.exiting("WEB3EquityAssetInquiryServiceImpl.execute");
            return l_inquiryResponse;
        }
    

    /**
     * 15.(*1)�����v���_�E���E�s��v���_�E���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���t�ꗗ�j�����E�s��v���_�E���쐬�v�Q�ƁB<BR>
     * @@param l_assetList - �ۗL���Y
     * @@param l_subAccount - �⏕����
     * @@param l_strInstitutionCode - �،����
     * @@param l_branch - ����X
     * @@param l_gentradeFinObjectManager - �g�����Z�I�u�W�F�N�g�}�l�[�W��
     * @@param l_productManager - �g���v���_�N�g�}�l�[�W��
     * @@param l_inquiryResponse - �g���v���_�N�g�}�l�[�W��
     * @@return WEB3EquitySellListResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406030A90384
     */
	private WEB3EquitySellListResponse pulldown(
		List l_assetList,
		SubAccount l_subAccount,
		String l_strInstitutionCode,
		WEB3GentradeBranch l_branch,
		WEB3GentradeFinObjectManager l_gentradeFinObjectManager,
		WEB3EquityProductManager l_productManager,
		WEB3EquitySellListResponse l_inquiryResponse)
        throws WEB3BaseException 
    {

        final String STR_METHOD_NAME =
                    "private pulldown(List,String,WEB3GentradeBranch,WEB3GentradeFinObjectManager,WEB3EquityProductManager)";

        long l_lngAssetProductIdTemp;

        WEB3EquityProduct l_stockProductTemp = null;
		WEB3EquityTradedProduct l_tradedProductTemp = null;
        WEB3GentradeMarket l_market = null;
        String l_strProductCodeTemp = null;
		EqTypeProduct l_eqtypeProduct = null;
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        WEB3EquityOrderManager l_orderMgr =
                    (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
					  
		int l_intAssetList = l_assetList.size();

				
        //1.2.get������戵�\�s��(���X, ProductTypeEnum)
        String[] l_strHandlingPossibleMarket =
            WEB3GentradeBranchMarketDealtCond.getTradingHandlingPossibleMarket(
                l_branch,
                ProductTypeEnum.EQUITY
                );

        long l_lngLength = l_strHandlingPossibleMarket.length;

        WEB3EquityProductCodeNameUnit[] l_productCodeNameUnits =
            new WEB3EquityProductCodeNameUnit[l_intAssetList];
        for (int i = 0; i < l_intAssetList; i++)
        {
            l_productCodeNameUnits[i] = new WEB3EquityProductCodeNameUnit();    
        }
        int productIndex = 0;

		//1.3.�擾�����ۗL���Y�I�u�W�F�N�g.����ID����Loop
        for (int i = 0; i < l_intAssetList; i++)
        {
            WEB3EquityAsset l_AssetTmp = (WEB3EquityAsset) l_assetList.get(i);
            l_lngAssetProductIdTemp = l_AssetTmp.getProduct().getProductId();
            l_strProductCodeTemp =
                ((WEB3EquityProduct) l_AssetTmp.getProduct()).getProductCode();
            
             try 
             {             
				//1.3.1.validate�����R�[�h
				l_eqtypeProduct = l_orderMgr.validateProductCode(
										l_strProductCodeTemp,
										l_strInstitutionCode);
                    				
				//1.3.2 validate�C���T�C�_�[
				l_orderMgr.validateInsider(
					l_subAccount,
					l_eqtypeProduct);          
 
             }
             catch (WEB3BusinessLayerException e) 
             {
                continue;                 
             }
               
               try
               {
               		l_stockProductTemp =
                    new WEB3EquityProduct(
                        l_productManager.getProduct(l_lngAssetProductIdTemp).getProductId());
                
//                	boolean isValidate = false;

				//1.3.3.����\�s��̎s�ꐔ��Loop
                for (int j = 0; j < l_lngLength; j++)
                {
                    //1.3.3.1.reset�s��R�[�h(String)
                    WEB3GentradeTradingTimeManagement.resetMarketCode(
                        l_strHandlingPossibleMarket[j]);

                    try 
                    {
                        //get�������
                        l_market =
                            new WEB3GentradeMarket(
                                l_gentradeFinObjectManager.getMarket(
                                    l_strInstitutionCode,
                                    l_strHandlingPossibleMarket[j]).getMarketId());
                        l_tradedProductTemp = (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
                                l_stockProductTemp,
                                l_market);
                    } 
                    catch (NotFoundException nfe) 
                    {
                        continue;
                    }
                    
					if (l_tradedProductTemp != null) {
                        
                        try
                        {
							//1.3.3.2 validate�ڋq�����ʎ����~�R�[�� 
							l_orderMgr.validateAccountProductOrderStop(
								l_subAccount,
								l_eqtypeProduct.getProductId(),
								OrderTypeEnum.EQUITY_SELL
								);   
							
                        	//1.3.3.3.validate�������
							l_orderMgr.validateTradedProduct(
								l_subAccount,
								l_eqtypeProduct,
								l_market,
								true);
                        	
							//1.3.3.4.validateJASDAQ�����戵�\(���X)
                            l_tradedProductTemp.validateJASDAQProductHandling(l_branch);
                            
                        }
                        catch (WEB3BaseException l_ex)
                        {
                            continue;
                        }                       
                    }
                } 
                
                    String l_strTempCode = l_tradedProductTemp.getProductCode();
                    boolean l_tempFlag = false;
                    if (productIndex > 0)
                    {
                    
                        for (int m = 0; m < productIndex; m++)
                        {
                            if (l_strTempCode == l_productCodeNameUnits[m].productCode)
                            {
                                l_tempFlag = true;
                                break;
                            }
                        }
                    }
                    if (l_tempFlag == false)
                    {
						WEB3EquityProduct l_product = (WEB3EquityProduct)l_tradedProductTemp.getProduct();
                    	EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();
                    	                    
                        l_productCodeNameUnits[productIndex].productName =  l_productRow.getStandardName();
                        l_productCodeNameUnits[productIndex].productCode =  l_tradedProductTemp.getProductCode();
                        productIndex = productIndex + 1;
                    }
                
                       
                //1.4.getProduct(����ID : long)
                WEB3EquityProduct l_productTemp =
                    (WEB3EquityProduct) l_productManager.getProduct(l_lngAssetProductIdTemp);                
				EqtypeProductRow l_productTempRow = (EqtypeProductRow)l_productTemp.getDataSourceObject();

                l_productCodeNameUnits[i] = new WEB3EquityProductCodeNameUnit();
				if (l_productTemp != null)
                {
                    l_productCodeNameUnits[i].productCode =
                        l_productTemp.getProductCode();
                    l_productCodeNameUnits[i].productName =
					    l_productTempRow.getStandardName();
                }
                
            }
            catch (NotFoundException nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
            catch (DataException de)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
        }
        //���X�|���X.�����ꗗ �Ɂ@@null ���@@�Z�b�g
        
        if (productIndex == 0) {
            l_inquiryResponse.productCodeNames = null;
        }
        else
        {
            WEB3EquityProductCodeNameUnit[] l_productCodeNameUnitsEnd =
                        new WEB3EquityProductCodeNameUnit[productIndex];
            for (int n = 0; n < productIndex; n++)
            {
                l_productCodeNameUnitsEnd[n] = new WEB3EquityProductCodeNameUnit();
                l_productCodeNameUnitsEnd[n] = l_productCodeNameUnits[n];
            }
            l_inquiryResponse.productCodeNames = l_productCodeNameUnitsEnd;
        }

        //���X�|���X.�s��R�[�h�ꗗ �Ɂ@@null ���@@�Z�b�g
        if (l_lngLength == 0)
        {
            l_inquiryResponse.marketList = null;
        }
        else
        {
            l_inquiryResponse.marketList = l_strHandlingPossibleMarket;
        }

        return l_inquiryResponse;
    }

    /**
     * 16.(*2)���t�\�ꗗ�쐬����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���t�ꗗ�j���t�\�ꗗ�쐬�v�Q�ƁB<BR>
     * @@param l_inquiryRequest - ���N�G�X�g�f�[�^
     * @@param l_positionManager - �����|�W�V�����}�l�[�W�� 
     * @@param l_subAccount - �⏕����
     * @@param l_branch - �،���Ђ̕����i���X�j
     * @@param l_strInstitutionCode - �،����
     * @@param l_gentradeFinObjectManagerTemp - �g�����Z�I�u�W�F�N�g�}�l�[�W��
     * @@param l_productManager - �g���v���_�N�g�}�l�[�W��
     * @@param l_inquiryResponse - �g���v���_�N�g�}�l�[�W��
     * @@return WEB3EquitySellListResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406030A90384
     */
    private WEB3EquitySellListResponse sellList(
        WEB3EquitySellListRequest l_inquiryRequest,
        WEB3EquityPositionManager l_positionManager,
        SubAccount l_subAccount,
        WEB3GentradeBranch l_branch,
        String l_strInstitutionCode,
        WEB3GentradeFinObjectManager l_gentradeFinObjectManagerTemp,
        WEB3EquityProductManager l_productManager,
        WEB3EquitySellListResponse l_inquiryResponse)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
                    "private sellList()";
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        WEB3EquityOrderManager l_orderMgr =
                    (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            
        //1.1.create��������������
        String l_strConditionList = createSearchConditionList(l_inquiryRequest);

        //1.2.create���������f�[�^�R���e�i
        String[] l_searchConditionDataContainer =
            createSearchConditionDataContainer(l_inquiryRequest);

        //1.3.create�\�[�g����(���������\�[�g�L�[[ ])
        WEB3EquitySortKey[] l_sortKeys = l_inquiryRequest.sortKeys;
        String l_strSortCondition = createSortCondition(l_sortKeys);

        //1.4.get���t�\�ۗL���Y�ꗗ
        List l_list = null;
        int l_intList = 0;
        l_list =
            l_positionManager.getSellableAssets(
                l_subAccount,
                ProductTypeEnum.EQUITY,
                l_strConditionList,
                l_searchConditionDataContainer,
                l_strSortCondition);
        l_intList = l_list.size();

        //1.5.get������戵�\�s��(���X, ProductTypeEnum)
        String[] l_strHandlingPossibleMarket =
            WEB3GentradeBranchMarketDealtCond.getTradingHandlingPossibleMarket(
                l_branch,
                ProductTypeEnum.EQUITY
                );

        int l_intLengthTemp = l_strHandlingPossibleMarket.length;

        String l_strMarketCodeTempTemp = null;
        WEB3EquityTradedProduct l_tradedProductTempT = null;

        long l_lngAssetProductIdTempT = 0;
        String l_strAssetProductCodeTemp = null;

		WEB3GentradeMarket  l_marketTemp = null;
		EqTypeProduct l_eqtypeProduct = null;

        WEB3EquityAssetUnit[] l_assetUnits = new WEB3EquityAssetUnit[l_intList];
        for (int i = 0; i < l_intList; i++)
        {
            l_assetUnits[i] = new WEB3EquityAssetUnit();
        }

        try
        {
            int assetUnitIndex = 0;			
            //1.6.�擾�����ۗL���Y�I�u�W�F�N�g.��������Loop
            for (int i = 0; i < l_intList; i++)
            {
                WEB3EquityAsset l_AssetTemp = (WEB3EquityAsset)  l_list.get(i);
                
                double l_lngCount = l_AssetTemp.getQuantity();
                l_lngAssetProductIdTempT = l_AssetTemp.getProduct().getProductId();
                long l_lngAssetIDTemp = l_AssetTemp.getAssetId();
                l_strAssetProductCodeTemp =
                    ((WEB3EquityProduct) l_productManager.getProduct(l_lngAssetProductIdTempT)).getProductCode();

                boolean isValidate = false;
                
                List l_marketCodeListTemp = new ArrayList();
                try
                {
					//1.6.1.validate�����R�[�h
					l_eqtypeProduct = l_orderMgr.validateProductCode(
											l_strAssetProductCodeTemp,
											l_strInstitutionCode);
                    				
					//1.6.2 validate�C���T�C�_�[
					l_orderMgr.validateInsider(
						l_subAccount,
						l_eqtypeProduct);        
 
                }
                catch (WEB3BusinessLayerException e)
                {
                    continue;
                }
              
                //1.6.3.���N�G�X�g.�s��R�[�h�w�肠��̏ꍇ
                if (l_inquiryRequest.marketCode != null)
                {
				 try
				 {
                  	//1.6.3.1.reset�s��R�[�h(String)
                  	WEB3GentradeTradingTimeManagement.resetMarketCode(
                    	  l_inquiryRequest.marketCode);
                    	  
					//1.6.3.2 validate�ڋq�����ʎ����~�R�[�� 
					l_orderMgr.validateAccountProductOrderStop(
						l_subAccount,
						l_eqtypeProduct.getProductId(),
						OrderTypeEnum.EQUITY_SELL
						);

				  	//1.6.3.3.validate�������
				  	l_marketTemp = (WEB3GentradeMarket) l_orderMgr.validateMarket(
				  		l_inquiryRequest.marketCode,
				  		l_strInstitutionCode);
				  		
				  	l_orderMgr.validateTradedProduct(
					  l_subAccount,
					  l_eqtypeProduct,
					  l_marketTemp,
					  true);  
                 
                  	l_tradedProductTempT =
                          (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
								l_eqtypeProduct,
                       		    l_marketTemp);

                  	//1.6.3.4.validateJASDAQ�����戵�\(���X)
                  	l_tradedProductTempT.validateJASDAQProductHandling(l_branch);
                  
                  }
				  catch (NotFoundException nfe) 
				  {
					log.error("not this traded product id" + nfe.getMessage());
				  	  continue;
				  }
                  catch(WEB3BaseException l_ex)
                  {
                      continue;
                  }
                  
				  l_marketCodeListTemp.add(l_inquiryRequest.marketCode); 
				  isValidate = true;
                }
				//1.6.4.���N�G�X�g.�s��R�[�h�w��Ȃ��̏ꍇ
                else
                {
                	//1.6.4.1.�戵�\�s�ꐔ��Loop
                    for (int j = 0; j < l_intLengthTemp; j++)
                    {
                     	try
                    	{
                    	
                        	//1.6.4.1.1.reset�s��R�[�h(String)
                        	WEB3GentradeTradingTimeManagement.resetMarketCode(
                            	l_strHandlingPossibleMarket[j]);
                            
							//1.6.4.1.2 validate�ڋq�����ʎ����~�R�[�� 
							l_orderMgr.validateAccountProductOrderStop(
								l_subAccount,
								l_eqtypeProduct.getProductId(),
								OrderTypeEnum.EQUITY_SELL
								);

							//1.6.4.1.3.validate�������
							l_strMarketCodeTempTemp = l_strHandlingPossibleMarket[j];
							l_marketTemp = (WEB3GentradeMarket) l_orderMgr.validateMarket(
								l_strMarketCodeTempTemp,
								l_strInstitutionCode);
				  		
							l_orderMgr.validateTradedProduct(
								l_subAccount,
								l_eqtypeProduct,
								l_marketTemp,
								true); 

                        	l_tradedProductTempT =
                                (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
									l_eqtypeProduct,
                                    l_marketTemp);
                        

							//1.6.4.1.4.validateJASDAQ�����戵�\(���X)
                            l_tradedProductTempT.validateJASDAQProductHandling(
                                l_branch);                                
						} 
						catch (NotFoundException nfe)
						{
							log.error("not this traded product id" + nfe.getMessage());
							continue;
                        }
                        catch (WEB3BaseException l_ex)
                        {
                            continue;
                        }
                        
                        l_marketCodeListTemp.add(l_strMarketCodeTempTemp);
                        isValidate = true;
                    }
                }

                if (isValidate)
                {
					WEB3EquityProduct l_prouctTemp = (WEB3EquityProduct)l_tradedProductTempT.getProduct();
					EqtypeProductRow l_productTempRow = (EqtypeProductRow)l_prouctTemp.getDataSourceObject();
					
                    l_assetUnits[assetUnitIndex].productCode = l_tradedProductTempT.getProductCode();                  
                    l_assetUnits[assetUnitIndex].productName = l_productTempRow.getStandardName();
                    l_assetUnits[assetUnitIndex].marketList = l_strHandlingPossibleMarket;

					//�擾���Y
					WEB3EquityAsset l_asset = (WEB3EquityAsset)l_positionManager.getAsset(l_lngAssetIDTemp);

					//1.7.getProduct(����ID : long)
					EqTypeProduct l_product = (EqTypeProduct) l_asset.getProduct();
					EqtypeProductRow l_productRow = (EqtypeProductRow)l_product.getDataSourceObject();

                    //1.8.getLockedQuantity
                    double l_lockedQuantity = l_asset.getLockedQuantity();                   

                    //���X�|���X.����.ID
                    l_assetUnits[assetUnitIndex].id = String.valueOf(l_lngAssetIDTemp);

                    //���X�|���X.����.�����R�[�h
                    l_assetUnits[assetUnitIndex].productCode = l_strAssetProductCodeTemp;
                    
					//���X�|���X.����.������
					l_assetUnits[assetUnitIndex].productName = l_productRow.getStandardName();
                    
                    //���X�|���X.����.�����敪
                    WEB3EquityAsset l_web3EquityAsset =  (WEB3EquityAsset)l_list.get(i);
                    AssetParams l_assetParams = new AssetParams((AssetRow)l_web3EquityAsset.getDataSourceObject());
                    if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType()))
                    {
                        l_assetUnits[assetUnitIndex].taxType = "0";
                    }
                    else if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetParams.getTaxType())
                          || TaxTypeEnum.SPECIAL.equals(l_assetParams.getTaxType()))
                    {
                          l_assetUnits[assetUnitIndex].taxType = "1";
                    }
                    
                    //���X�|���X.����.���t�\����
                    double l_dblPossQuantity = l_lngCount - l_lockedQuantity;
                    
                    int l_intPossQuantity = (int)l_dblPossQuantity;
                    int l_intLockedQuantity = (int)l_lockedQuantity;
                    l_assetUnits[assetUnitIndex].sellPossQuantity = "" + l_intPossQuantity;
                    l_assetUnits[assetUnitIndex].orderedQuantity = "" + l_intLockedQuantity;

                    //���X�|���X.����.�s��R�[�h�ꗗ
                    l_assetUnits[assetUnitIndex].marketList = new String[l_marketCodeListTemp.size()];
                    l_marketCodeListTemp.toArray(l_assetUnits[assetUnitIndex].marketList);
                    
                    //���X�|���X.����.���t�\�t���O
                    if (l_lngCount > l_lockedQuantity)
                    {
                        l_assetUnits[assetUnitIndex].sellPossFlag = true;
                    }
                    else
                    {
                        l_assetUnits[assetUnitIndex].sellPossFlag = false;
                    }
                    assetUnitIndex = assetUnitIndex + 1;
                }                
            }

            int l_intPageIndex = Integer.parseInt(l_inquiryRequest.pageIndex);
            int l_intPageSize = Integer.parseInt(l_inquiryRequest.pageSize);

            if (l_intPageIndex > (int)Math.ceil((double)assetUnitIndex / l_intPageSize))
            {
                l_intPageIndex = (int)Math.ceil((double)assetUnitIndex / l_intPageSize);
                l_inquiryResponse.pageIndex = "" + l_intPageIndex;
            }
            else
            {
                l_inquiryResponse.pageIndex = l_inquiryRequest.pageIndex;
            }
           
            int l_intAssertStartIndex = l_intPageSize*(l_intPageIndex -1);
            int l_intAssertEndIndex = l_intPageSize*l_intPageIndex;
            
            if (l_intAssertEndIndex > assetUnitIndex)
            {
                l_intAssertEndIndex = assetUnitIndex;
            }
            
            int l_intAssertSize = l_intAssertEndIndex - l_intAssertStartIndex;
            WEB3EquityAssetUnit[] l_assetUnitsEnd = new WEB3EquityAssetUnit[l_intAssertSize];
            for (int k = 0; k < l_intAssertSize; k++)
            {
                l_assetUnitsEnd[k] = new WEB3EquityAssetUnit();
                l_assetUnitsEnd[k] = l_assetUnits[l_intAssertStartIndex + k];
            }            

            l_inquiryResponse.equityAssetUnits = l_assetUnitsEnd;
            
           int l_lngResult =
               assetUnitIndex / Integer.parseInt(l_inquiryRequest.pageSize);
           int l_lngTemp = assetUnitIndex % Integer.parseInt(l_inquiryRequest.pageSize);
           
           if (l_lngResult == 0)
           {
               l_inquiryResponse.totalPages = "1";
           }
           else
           {
               if (l_lngTemp == 0)
               {
                   l_inquiryResponse.totalPages = String.valueOf(l_lngResult);
               }
               else
               {
                   l_inquiryResponse.totalPages = String.valueOf(l_lngResult + 1);
               }
           }
           //���X�|���X.���y�[�W��
           if (assetUnitIndex == 0)
           {
               l_inquiryResponse.totalPages = "0";
           }
            
         //���X�|���X.�����R�[�h��                   
         l_inquiryResponse.totalRecords = String.valueOf(assetUnitIndex);
         
         //���X�|���X.�\���y�[�W�ԍ�
         if (l_inquiryResponse.totalPages == null)
         {
             l_inquiryResponse.equityAssetUnits = null;
         } 
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        return l_inquiryResponse;
    }

    /**
     * (create��������������)<BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬���Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����̃��N�G�X�g�f�[�^.�����R�[�h��null�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * ����ID�w���ǉ�����B<BR>
     * <BR>
     * �Q�j�@@�쐬�������������������Ԃ��B<BR>
     * �@@�@@�@@�������̃��N�G�X�g�f�[�^.�����R�[�h ���w�肳��Ă��Ȃ��ꍇ�A<BR>
     * null��Ԃ��B<BR>
     * @@param l_requestData - ���N�G�X�g�f�[�^<BR>
     * ���t�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return java.lang.String
     * @@roseuid 4069416D0118
     */
    protected String createSearchConditionList(WEB3EquitySellListRequest l_requestData)
    {
        log.entering(
            "WEB3EquityAssetInquiryServiceImpl.createSearchConditionList");
        if (l_requestData.productCode != null)
        {
            String l_strConditionList = "product_Id = ?";
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSearchConditionList");
            return l_strConditionList;
        }
        else
        {
            log.debug("���N�G�X�g�f�[�^.�����R�[�h ���w�肳��Ă��Ȃ�");
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSearchConditionList");
            return null;
        }
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������iwhere�ȉ��w��̕�����j�̃p�����[�^���X�g���쐬���Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����̃��N�G�X�g�f�[�^.�����R�[�h��null�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * ����ID���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�g���v���_�N�g�}�l�[�W��.getProduct(�،���ЃI�u�W�F�N�g(*1), <BR>
     * �����̃��N�G�X�g�f�[�^.�����R�[�h).����ID���Z�b�g<BR>
     * <BR>
     * �Q�j�@@�쐬�����p�����[�^���X�g��Ԃ��B<BR>
     * �@@�@@�@@�������̃��N�G�X�g�f�[�^.�����R�[�h 
     * ���w�肳��Ă��Ȃ��ꍇ�Anull��Ԃ��B<BR>
     * <BR>
     * (*1)�،���ЃI�u�W�F�N�g�́A�⏕����.getInstitution( )�Ŏ擾���ݒ�<BR>
     * @@param l_request - ���t�ꗗ���N�G�X�g�I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 4069416D0127
     */
    protected String[] createSearchConditionDataContainer(WEB3EquitySellListRequest l_request)
    {
        log.entering(
            "WEB3EquityAssetInquiryServiceImpl.createSearchConditionDataContainer");
        if (l_request.productCode != null)
        {

            //�g���v���_�N�g�}�l�[�W��.getProduct(�،���ЃI�u�W�F�N�g(*1), 
            //   * �����̃��N�G�X�g�f�[�^.�����R�[�h).����ID���Z�b�g<BR>
            String l_strProductCode = l_request.productCode;
            Institution l_institution = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeProduct l_product = null;
            String l_strId = null;
            try
            {
                SubAccount l_subAccount =
                    this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                l_institution = l_subAccount.getInstitution();

                TradingModule l_tradingMod =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager) l_tradingMod.getProductManager();

                l_product =
                    l_productManager.getProduct(
                        l_institution,
                        l_strProductCode);
                //�쐬�����p�����[�^���X�g��Ԃ�
                long l_lngProductId = l_product.getProductId();

                l_strId = String.valueOf(l_lngProductId);
            }
            catch (NotFoundException e)
            {
                log.debug(
                    "createSearchConditionDataContainer__Error[�����I�u�W�F�N�g�擾]__");

            }
            catch (WEB3SystemLayerException e)
            {
                log.debug(
                    "createSearchConditionDataContainer__Error[�����I�u�W�F�N�g�擾]__");

            }

            String[] l_ConditionDataContainer = { l_strId };
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSearchConditionDataContainer");
            return l_ConditionDataContainer;

        }
        else
        {
            log.debug("���N�G�X�g�f�[�^.�����R�[���w�肳��Ă��Ȃ�");
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSearchConditionDataContainer");
            return null;
        }

    }

    /**
     * (create�\�[�g����)<BR>
     * �\�[�g������������쐬���Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����̃\�[�g�L�[.�L�[���ڂ̐����A�Ή�����e�[�u���̗񕨗���<BR>
     * �������^�~���w���t�����Z�b�g����B<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�B<BR>
     * �@@�@@�@@���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q�ƁB<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q�ƁB<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�i�����j�R�[�h�@@�@@�@@�@@�F�ۗL���Y�e�[�u��.����ID<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�E�����@@�@@�@@�@@�@@�@@�@@�@@�@@�F�ۗL���Y�e�[�u��.�ŋ敪<BR>
     * <BR>
     * �@@�@@�E�����^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ肷��B<BR>
     * <BR>
     * �Q�j�@@�쐬�����\�[�g�����������Ԃ��B<BR>
     * �@@�@@�@@�������̃��N�G�X�g�f�[�^.�\�[�g�L�[ 
     * ���w�肳��Ă��Ȃ��ꍇ�Anull��Ԃ��B<BR>
     * @@param l_sortKeys - ���N�G�X�g�f�[�^.�\�[�g�L�[
     * @@return java.lang.String
     * @@roseuid 40710E3100B4
     */
    protected String createSortCondition(WEB3EquitySortKey[] l_sortKeys) {

        log.entering("WEB3EquityAssetInquiryServiceImpl.createSortCondition()");
        if (l_sortKeys.length != 0)
        {
            String l_strSort = "";
            for (int i = 0; i < l_sortKeys.length; i++)
            {
                // ���u�� U00232�̎b��Ή� start
//                  l_strSort = l_strSort + l_sortKeys[i].keyItem;

                if("productCode".equals(l_sortKeys[i].keyItem))
                {
                    //�i�����j�R�[�h �F�����P�ʃe�[�u���D����ID
                    l_strSort = l_strSort + "product_id";
                }
                else if("taxType".equals(l_sortKeys[i].keyItem))
                {
                    //���� �F�����P�ʃe�[�u���D�ŋ敪
                    l_strSort = l_strSort + "tax_type";
                }
                // ���u�� U00232�̎b��Ή� end
                
                l_strSort = l_strSort + " ";
                
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc)) 
                {
                    l_strSort = l_strSort + "Asc";
                } 
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc)) 
                {
                    l_strSort = l_strSort + "Desc";
                }
                
                if (i >= 0 && i < l_sortKeys.length - 1)
                {
                    l_strSort = l_strSort + ",";
                }
            }
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSortCondition()");
            return l_strSort;
        }
        else
        {
            log.error("���N�G�X�g�f�[�^.�\�[�g�L�[  ���w�肳��Ă��Ȃ�");
            log.exiting(
                "WEB3EquityAssetInquiryServiceImpl.createSortCondition()");
            return null;
        }
    }
}
@
