head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������c���Ɖ�T�[�r�XImpl(WEB3FeqBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[     
Revesion History : 2007/11/20 �đo�g(���u) ���f��No.363
Revesion History : 2008/01/14 �đo�g(���u) ���f��No.370�ANo.375�ANo.382�ANo.383
                                           No.384�ANo.385�ANo.386�ANo.387
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2008/02/02 �đo�g(���u) ���f��No.396
Revesion History : 2008/03/03 �g�C��(���u) ���f��No.410�A���f��No.411
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqBalanceReferenceComparator;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqProductQuote;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3FeqBalanceReferenceDetailUnit;
import webbroker3.feq.message.WEB3FeqBalanceReferenceRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceResponse;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalResponse;
import webbroker3.feq.message.WEB3FeqProductCodeNameUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3FeqBalanceReferenceService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�O�������c���Ɖ�T�[�r�XImpl)<BR>
 * �O�������c���Ɖ�T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqBalanceReferenceServiceImpl extends WEB3FeqClientRequestService 
    implements WEB3FeqBalanceReferenceService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBalanceReferenceServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F502BF
     */
    public WEB3FeqBalanceReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �O�������c���Ɖ�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���A<BR>
     * [�O�������c���Ɖ�N�G�X�g�̏ꍇ]<BR>
     * �@@this.get�c���Ɖ�()���R�[������B<BR>
     * <BR>
     * [�O�������c�����v���N�G�X�g�̏ꍇ]<BR>
     * �@@this.get�c�����v()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A8056C01D1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B 
        // ���N�G�X�g�f�[�^���A[�O�������c���Ɖ�N�G�X�g�̏ꍇ] 
        //�@@this.get�c���Ɖ�()���R�[������B
        if (l_request instanceof WEB3FeqBalanceReferenceRequest)
        {
            l_response = 
                this.getBalanceReference(
                    (WEB3FeqBalanceReferenceRequest)l_request);   
        }        
        //[�O�������c�����v���N�G�X�g�̏ꍇ] 
        //this.get�c�����v()���R�[������B
        else if (l_request instanceof WEB3FeqBalanceReferenceTotalRequest)
        {
            l_response =
                this.getBalanceTotal(
                    (WEB3FeqBalanceReferenceTotalRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get�c���Ɖ�)<BR>
     * �O�������c���Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O���c���Ɖ�jget�c���Ɖ�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������c���Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqBalanceReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A805AC01B2
     */
    protected WEB3FeqBalanceReferenceResponse getBalanceReference(
            WEB3FeqBalanceReferenceRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getBalanceReference(WEB3FeqBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);        
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 validate������t�\( )(������ԊǗ�::validate������t�\)
        //"�Ɖ�"���o�b�`���E�V�X�e���ً}��~���ł��邩�ǂ����`�F�b�N����B

        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 �⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.4 create�������ꗗ(�⏕����)
        //�ڋq�̕ێ����鎑�Y����������̈ꗗ���쐬����B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l
        WEB3FeqProductCodeNameUnit[] l_productCodeNameUnit = 
            this.createProductInformationList(l_subAccount);
        
        //1.5 get������戵�\�s��(���X, ProductTypeEnum)
        //������̎戵�\�s����擾����B 

        //[����] 
        //���X�F�@@get�⏕����()�̖߂�l.get����X() 
        //�����^�C�v�F�@@ProductTypeEnum.�O������
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch)
            l_subAccount.getMainAccount().getBranch();
        
        String[] l_strMarketList = 
            WEB3GentradeFeqBranchMarketDealtCond.getTradingHandlingPossibleMarket(
                l_branch, 
                ProductTypeEnum.FOREIGN_EQUITY);

        //1.6 create���׈ꗗ(�⏕����, �O�������c���Ɖ�N�G�X�g)
        //�c���Ɖ�ׂ̈ꗗ���쐬����B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //���N�G�X�g�f�[�^�F�@@���N�G�X�g�f�[�^ 
        WEB3FeqBalanceReferenceDetailUnit[] l_balanceReferenceDetails = 
            this.createDetailList(l_subAccount, l_request);
        
        //1.7 sort�c���Ɖ��(�O�������c���Ɖ��[], �O�������\�[�g�L�[[])
        //�c���Ɖ�ׂ��\�[�g����B 
        //[����] 
        //�c���Ɖ�ׁF�@@create���׈ꗗ()�̖߂�l 
        //�\�[�g�L�[�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
        this.sortBalanceReferenceDetailUnit(
            l_balanceReferenceDetails, 
            l_request.sortKeys);

        //1.8 WEB3PageIndexInfo()
        //WEB3PageIndexInfo�C���X�^���X�𐶐�����B 
        //[����] 
        //arg0�F�@@sort�c���Ɖ��()�̖߂�l
        //arg1�F�@@�v���y�[�W�ԍ� 
        //arg2�F�@@�y�[�W���\���s��
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_balanceReferenceDetails, 
            Integer.parseInt(l_request.pageIndex), 
            Integer.parseInt(l_request.pageSize));
        
        //1.9 getArrayReturned(arg0 : Class)
        //�\���Ώۍs�̊O�������c���Ɖ�ׂ̔z��𐶐�����B 
        //[����] 
        //arg0�F�@@�O�������c���Ɖ��.class
        l_balanceReferenceDetails = (WEB3FeqBalanceReferenceDetailUnit[])
            l_pageIndexInfo.getArrayReturned(
                WEB3FeqBalanceReferenceDetailUnit.class);
        
        //1.10 �\���y�[�W�ԍ����擾����B
        String l_strPageIndex = Integer.toString(l_pageIndexInfo.getPageIndex());
        
        //1.11 ���y�[�W�����擾����B
        String l_strTotalPages = Integer.toString(l_pageIndexInfo.getTotalPages());
        
        //1.12 �����R�[�h�����擾����B
        String l_strTotalRecords = Integer.toString(l_pageIndexInfo.getTotalRecords());
        
        //1.13 ���X�|���X�f�[�^�𐶐�����B 
        WEB3FeqBalanceReferenceResponse l_response = 
            (WEB3FeqBalanceReferenceResponse)l_request.createResponse();
        
        //1.14 (*)�v���p�e�B�Z�b�g
        //�����ꗗ = create�������ꗗ()�̖߂�l
        l_response.productCodeNames = l_productCodeNameUnit;
        
        //�s��R�[�h�ꗗ = get������戵�\�s��()�̖߂�l
        l_response.marketList = l_strMarketList;
        
        //���׈ꗗ = getArrayReturned()�̖߂�l
        l_response.foreignBalanceReferenceDetail = l_balanceReferenceDetails;
        
        //���X�|���X.�\���y�[�W�ԍ� = getPageIndex()�̖߂�l
        l_response.pageIndex = l_strPageIndex;
        
        //���X�|���X.���y�[�W�� = getTotalPages()�̖߂�l
        l_response.totalPages = l_strTotalPages;
         
        //���X�|���X.�����R�[�h�� = getTotalRecords()�̖߂�l
        l_response.totalRecords = l_strTotalRecords;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�c�����v)<BR>
     * �O�������c�����v�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O���c���Ɖ�jget�c�����v�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������c�����v���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FeqBalanceReferenceTotalResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A805AC01D1
     */
    protected WEB3FeqBalanceReferenceTotalResponse getBalanceTotal(
            WEB3FeqBalanceReferenceTotalRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceTotal(" +
            "WEB3FeqBalanceReferenceTotalRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate������t�\( )(������ԊǗ�::validate������t�\)
        //"�Ɖ�"���o�b�`���E�V�X�e���ً}��~���ł��邩�ǂ����`�F�b�N����B

        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.2 �⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount();
        
        //1.3 get�ۗL���Y�ꗗ()
        //�ۗL���Y�ꗗ���擾����B 
        //[����] 
        //�⏕�����F�@@get�⏕����()�̖߂�l 
        //�����^�C�v�F�@@ProductTypeEnum.�O������ 
        //��������������F�@@null 
        //���������f�[�^�R���e�i�F�@@null 
        //�\�[�g�����F�@@null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_feqPositionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
    
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        
        List l_lisAssetRows = 
            l_feqPositionManager.getAssetList(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                null,
                null,
                null);     
        
        //1.4 createResponse( )
        WEB3FeqBalanceReferenceTotalResponse l_response = 
            (WEB3FeqBalanceReferenceTotalResponse)l_request.createResponse();

        if (l_lisAssetRows == null)
        {
            return l_response;
        }
        log.debug("get�ۗL���Y�ꗗ.size = " + l_lisAssetRows.size());
        
        //1.5 ���������i�[����HashMap�𐶐�����B
        HashMap l_hashMap = new HashMap();
        
        double l_dblNormalTotal = 0.0D;
        double l_dblNormalTotalProfitLoss = 0.0D;
        double l_dblCapitalTotal = 0.0D;
        double l_dblCapitalTotalProfitLoss = 0.0D;        
        
        //1.6 (*)get�ۗL���Y�ꗗ()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_lisAssetRows.size(); i++)
        {
            //1.6.1 �O�������������擾����B 
            //arg0�F�@@�ۗL���Y.����ID            
            AssetRow l_assetRow = (AssetRow)l_lisAssetRows.get(i);
            log.debug("�ۗL���YRow = " + l_assetRow);
            WEB3FeqProduct l_feqProduct = null;
            try
            {
                l_feqProduct = (WEB3FeqProduct)l_feqProductManager.getProduct(
                    l_assetRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.debug("__NotFoundException__", l_ex);
                //��O���X���[����
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }   
            
            if (l_feqProduct == null)
            {
                log.debug("Error in �O�������������擾����");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.6.2 get�������(�⏕����, �O����������, HashMap)
            //���������擾����B 
            //[����] 
            //�⏕�����F�@@get�⏕����()�̖߂�l 
            //�O�������F�@@getProduct()�̖߂�l 
            //�������i�[���X�g�F�@@��������HashMap
            WEB3FeqProductQuote l_productQuote = 
                this.getEquityProductQuote(
                    l_subAccount, l_feqProduct, l_hashMap);
            
            //1.6.3 �ʉ݂��擾����B
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            
            if (l_productQuote != null)
            {
                //1.6.4 calc�]���z(double, double, �ʉ�)
                //�]���z�i�~�݁j���Z�o����B 
                //[����] 
                //�]���P���F�@@get�������()�̖߂�l.���� 
                //���ʁF�@@�ۗL���Y.���� + �ۗL���Y.���t�s�\���� 
                //�ʉ݁F�@@�ʉ�
                double l_dblEstimatedValue = 
                    this.calcEstimatedValue(
                        l_productQuote.getCurrentPrice(), 
                        l_assetRow.getQuantity() + l_assetRow.getQuantityCannotSell(), 
                        l_currency);
                
                log.debug("calc�]���z�̖߂�l = " + l_dblEstimatedValue);
                
                //1.6.5 calc�T�Z�뉿�P��(double, double)
                //�T�Z�뉿�P���i�~�݁j���Z�o����B 
                //[����] 
                //�뉿�F�@@�ۗL���Y.�뉿�i�뉿�P���v�Z�p�j 
                //���ʁF�@@�ۗL���Y.���ʁi�뉿�P���v�Z�p�j
                WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
                    (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
                
                BigDecimal l_bdBookValuePrice = 
                    l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                        l_assetRow.getBookValue(), 
                        l_assetRow.getQuantityForBookValue());
    
                log.debug("calc�T�Z�뉿�P���̖߂�l = " + l_bdBookValuePrice);

                //1.6.7 calc�]�����v(double, double, double, �ʉ�)
                //�]�����v�i�~�݁j���Z�o����B 
                //[����] 
                //�]���P���F�@@get�������()�̖߂�l.���� 
                //�뉿�F�@@calc�T�Z�뉿�P��()�̖߂�l
                //���ʁF�@@�ۗL���Y.���� + �ۗL���Y.���t�s�\���� 
                //�ʉ݁F�@@�ʉ� 
                double l_dblProFitLoss = 
                    this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(),
                        l_bdBookValuePrice,
                        l_assetRow.getQuantity() + l_assetRow.getQuantityCannotSell(), 
                        l_currency);
    
                log.debug("calc�]�����v�̖߂�l = " + l_dblProFitLoss);
                
                //1.6.8 (*)�ۗL���Y.�ŋ敪 == "���"�̏ꍇ
                if (TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
                {
                    log.debug("�ۗL���Y.�ŋ敪 == '���'�̏ꍇ");
                    //1.6.8.1 (*)��ʌ����]���z���v�A��ʌ����]�����v���v�ɉ��Z����
                    l_dblNormalTotal += l_dblEstimatedValue;
                    
                    l_response.normalAccountTotalAsset = 
                        WEB3StringTypeUtility.formatNumber(l_dblNormalTotal);
                    
					// ���e�T�Z�]�����v�z�́A�T�Z�뉿�P���i�~�݁E�����_6�����l�̌ܓ��j
					//    != 0�̏ꍇ�̂݃Z�b�g�B
                    if ((l_bdBookValuePrice.setScale(5, BigDecimal.ROUND_HALF_UP)).doubleValue() != 0)
                    {
                        l_dblNormalTotalProfitLoss += l_dblProFitLoss;
						l_response.normalAccountTotalAppraisalProfitLoss = 
							WEB3StringTypeUtility.formatNumber(l_dblNormalTotalProfitLoss);	
                    }
                }
                //1.6.9 (*)�ۗL���Y.�ŋ敪 == "����"�̏ꍇ
                else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
                {
                    log.debug("�ۗL���Y.�ŋ敪 == '����'�̏ꍇ");
                    //1.6.9.1 (*)��������]���z���v�A��������]�����v���v�ɉ��Z����
                    l_dblCapitalTotal += l_dblEstimatedValue;
                    
                    l_response.capitalGainTotalAsset = 
                        WEB3StringTypeUtility.formatNumber(l_dblCapitalTotal);

                    l_dblCapitalTotalProfitLoss += l_dblProFitLoss;
                    l_response.capitalGainTotalAppraisalProfitLoss = 
                        WEB3StringTypeUtility.formatNumber(l_dblCapitalTotalProfitLoss);
                }
            }
        }
               
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create�������ꗗ)<BR>
     * �����̕⏕�����ɊY������ۗL���Y����<BR>
     * �������̈ꗗ���쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�ۗL���Y�擾<BR>
     * �@@�O�������|�W�V�����}�l�[�W��.get�ۗL���Y�ꗗ()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get�ۗL���Y�ꗗ()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�����^�C�v�F�@@ProductTypeEnum.�O������<BR>
     * �@@�@@��������������F�@@null<BR>
     * �@@�@@���������f�[�^�R���e�i�F�@@null<BR>
     * �@@�@@�\�[�g�����F�@@"product_id asc"<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�jArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�P�j�̖߂�l�̗v�f(=�ۗL���Y�I�u�W�F�N�g)�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�����Ώۂۗ̕L���Y.getProduct()�ɂ�<BR>
     * �@@�@@�O�������������擾����B<BR>
     * <BR>
     * �@@�R�|�Q�j�����R�[�h�d���`�F�b�N<BR>
     * �@@�@@ArrayList�ɁA�擾�����O����������.�����R�[�h�Ɠ���<BR>
     * �@@�@@�����R�[�h��ێ�����v�f�����݂���ꍇ�A<BR>
     * �@@�@@���̗v�f�֏������ڍs����B(continue;)<BR>
     * <BR>
     * �@@�R�|�R�j�O�������������C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�R�|�S�j���������C���X�^���X�ɁA�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�����R�[�h = �擾�����O����������.�����R�[�h<BR>
     * �@@�@@������ = �擾�����O����������.get�\��������()�̖߂�l<BR>
     * <BR>
     * �@@�R�|�T�j��������ArrayList�Ƀv���p�e�B�Z�b�g����<BR>
     * �@@�@@�C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �S�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@return WEB3FeqProductCodeNameUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A80881027D
     */
    protected WEB3FeqProductCodeNameUnit[] createProductInformationList(
        WEB3GentradeSubAccount l_subAccount) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductInformationList(" +
            "WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�ۗL���Y�擾 
        //�O�������|�W�V�����}�l�[�W��.get�ۗL���Y�ꗗ()���\�b�h���R�[������B 

        //[get�ۗL���Y�ꗗ()�ɃZ�b�g����p�����[�^] 
        //�@@�⏕�����F�@@�p�����[�^.�⏕���� 
        //�@@�����^�C�v�F�@@ProductTypeEnum.�O������ 
        //�@@��������������F�@@null 
        //�@@���������f�[�^�R���e�i�F�@@null 
        //�@@�\�[�g�����F�@@"product_id asc" 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_feqPositionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
    
        List l_lisAssetRows = 
            l_feqPositionManager.getAssetList(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                null,
                null,
                "product_id asc");        
        
        //�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B 
        if (l_lisAssetRows == null || l_lisAssetRows.size() == 0)
        {
            return null;
        }
        
        //�Q�jArrayList�𐶐�����B 
        List l_lisProductCodeNames = new ArrayList();
        
        //�R�j�P�j�̖߂�l�̗v�f(=�ۗL���Y�I�u�W�F�N�g)�����A 
        //  �ȉ��̏������J��Ԃ��B                
        List l_lisProductCode = new ArrayList();
        
        for (int i = 0; i < l_lisAssetRows.size(); i++)
        {
            //�R�|�P�j�����Ώۂۗ̕L���Y.getProduct()�ɂ� 
            //�@@ �O�������������擾����B 
            AssetRow l_assetRow = (AssetRow)l_lisAssetRows.get(i);
            log.debug("�ۗL���YRow = " + l_assetRow);
            Asset l_asset = null;
            try
            {
                l_asset = 
                    l_feqPositionManager.getAsset(l_assetRow.getAssetId());
            }
            catch (NotFoundException l_ex)
            {
                log.debug("__NotFoundException__", l_ex);
                //��O���X���[����
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            FeqProduct l_feqProduct = (FeqProduct)l_asset.getProduct();
            
            //�R�|�Q�j�����R�[�h�d���`�F�b�N 
            //ArrayList�ɁA�擾�����O����������.�����R�[�h�Ɠ��� 
            //�����R�[�h��ێ�����v�f�����݂���ꍇ�A 
            //���̗v�f�֏������ڍs����B(continue;)

            log.debug("�����R�[�h = " + l_feqProduct.getProductCode());
            
            if (WEB3Toolkit.contain(l_lisProductCode, l_feqProduct.getProductCode()))
            {
                continue;
            }
            
            l_lisProductCode.add(l_feqProduct.getProductCode());
            
            //�R�|�R�j�O�������������C���X�^���X�𐶐�����B
            WEB3FeqProductCodeNameUnit l_productCodeNameUnit = new 
                WEB3FeqProductCodeNameUnit();
            
            //�R�|�S�j���������C���X�^���X�ɁA�ȉ��̃v���p�e�B���Z�b�g����B 
            //�����R�[�h = �擾�����O����������.�����R�[�h 
            //������ = �擾�����O����������.get�\��������()�̖߂�l
            l_productCodeNameUnit.productCode = l_feqProduct.getProductCode();
            l_productCodeNameUnit.productName = l_feqProduct.getStandardName();
            
            //�R�|�T�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B 
            l_lisProductCodeNames.add(l_productCodeNameUnit);
        }       
        
        //�S�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3FeqProductCodeNameUnit[] l_feqProductCodeNameUnits = new 
            WEB3FeqProductCodeNameUnit[l_lisProductCodeNames.size()];
                
        l_lisProductCodeNames.toArray(l_feqProductCodeNameUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqProductCodeNameUnits;
    }
    
    /**
     * (create��������������)<BR>
     * (createQueryString) <BR>
     * ���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * �P�j�p�����[�^.�����R�[�h == null�̏ꍇ�A<BR>
     * �@@null��ԋp���ďI������B<BR>
     * <BR>
     * �Q�j�����R�[�h�ɊY���������ID�̌���������������쐬����B<BR>
     * <BR>
     * �@@�������������� = " and product_id = ? "<BR>
     * <BR>
     * �R�j�쐬�������������������ԋp����B<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@return String
     * @@roseuid 42A80C63000C
     */
    protected String createQueryString(String l_strProductCode) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(" +
            "WEB3FeqBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���������iwhere�ȉ��w��̕�����j���쐬����B 

        //�P�j�p�����[�^.�����R�[�h == null�̏ꍇ�A 
        //�@@null��ԋp���ďI������B 
        if (l_strProductCode == null)
        {
            return null;
        }
        
        //�Q�j�����R�[�h�ɊY���������ID�̌���������������쐬����B 
        //�@@ �������������� = " and product_id = ? " 

        String l_strQueryString = " and product_id = ? ";
        
        //�R�j�쐬�������������������ԋp����B        
        log.exiting(STR_METHOD_NAME);
        
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * (createQueryDataContainer)<BR>
     * ���������iwhere�ȉ��w��̕�����j�̃p�����[�^�̕�����z����쐬����B<BR>
     * <BR>
     * �P�j�p�����[�^.�����R�[�h == null�̏ꍇ�A<BR>
     * �@@null��ԋp���ďI������B<BR>
     * <BR>
     * �Q�j�p�����[�^.�����R�[�h�ɊY���������ID��<BR>
     * �@@�v�f�Ƃ��镶����z��𐶐�����B<BR>
     * <BR>
     * �@@������z�� = ����ID(*1)<BR>
     * <BR>
     * �R�j�쐬����������z���ԋp����B<BR>
     * <BR>
     * (*1)�O�������v���_�N�g�}�l�[�W��.getFeqProduct(�p�����[�^.�،����,<BR>
     *  �p�����[�^.�����R�[�h).����ID<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 42A80C63003B
     */
    protected String[] createQueryDataContainer(
        WEB3GentradeInstitution l_institution, 
        String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(" +
            "WEB3GentradeInstitution l_institution, String l_strProductCode)";
        log.entering(STR_METHOD_NAME);
    
        if (l_institution == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�p�����[�^.�����R�[�h == null�̏ꍇ�A 
        //null��ԋp���ďI������B 
        if (l_strProductCode == null)
        {
            log.debug("�p�����[�^.�����R�[�h == null�̏ꍇ");
            return null;
        }

        //�Q�j�p�����[�^.�����R�[�h�ɊY���������ID�� 
        // �v�f�Ƃ��镶����z��𐶐�����B 
        List l_lisQueryContainer = new ArrayList();
        
        // ������z�� = ����ID(*1) 
        //(*1)�O�������v���_�N�g�}�l�[�W��.getFeqProduct(
        //                           �p�����[�^.�،����, �p�����[�^.�����R�[�h).����ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
                
        FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = 
                l_feqProductManager.getFeqProduct(l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFound �O����������__", l_ex);
            //��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        String l_strProductId = l_feqProduct.getProductId() + "";         
        l_lisQueryContainer.add(l_strProductId);
        
       //�R�j�쐬����������z���ԋp����B
        String[] l_strQueryContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }
    
    /**
     * (create���׈ꗗ)<BR>
     * �ڋq�̕ێ����鎑�Y����c���Ɖ�ׂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O���c���Ɖ�jcreate���׈ꗗ�v<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������c���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3FeqBalanceReferenceDetailUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A80E820348
     */
    protected WEB3FeqBalanceReferenceDetailUnit[] createDetailList(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqBalanceReferenceRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createDetailList(" +
            "WEB3GentradeSubAccount l_subAccount, " +
            "WEB3FeqBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 create��������������(String)
        //����������������쐬����B 
        //[����] 
        //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
        String l_strQueryString = 
            this.createQueryString(l_request.productCode);
        
        //1.2 create���������f�[�^�R���e�i(�،����, String)
        //���������f�[�^�R���e�i���쐬����B 
        //[����] 
        //�،���ЁF�@@�⏕����.getInstitution()
        //�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h
        String[] l_strQueryDatas = 
            this.createQueryDataContainer(
                (WEB3GentradeInstitution)l_subAccount.getInstitution(), 
                l_request.productCode);
        
        //1.3 get�ۗL���Y�ꗗ(�⏕����, ProductTypeEnum, String, String[], String)
        //�����ɊY������ۗL���Y�̈ꗗ���擾����B 
        //[����] 
        //�⏕�����F�@@�p�����[�^.�⏕���� 
        //�����^�C�v�F�@@ProductTypeEnum.�O������ 
        //��������������F�@@create��������������()�̖߂�l 
        //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
        //�\�[�g�����F�@@null
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_feqPositionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();    
        
        List l_lisAssetRows = 
            l_feqPositionManager.getAssetList(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_strQueryString,
                l_strQueryDatas,
                null);        
        
        //1.4 (*)�Y���f�[�^�Ȃ�(get�ۗL���Y�ꗗ()�̖߂�l == null)�̏ꍇ
        if (l_lisAssetRows == null)
        {
            //null��ԋp���ďI������B
            return null;
        }
        log.debug("l_lisAssetRows.size() = " + l_lisAssetRows.size());
        
        //1.5 getOrderValidator( ) �����`�F�b�N�I�u�W�F�N�g���擾����B        
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 

        //1.6 validate����\�ڋq(SubAccount)(�����`�F�b�N::validate����\�ڋq)
        //����\�ڋq���ǂ����`�F�b�N����B 
        //[����] 
        //�⏕�����F�@@�p�����[�^.�⏕����
        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        boolean l_blnIsFailedResult = false;
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            l_blnIsFailedResult = true;
        }        
        //1.7 ArrayList�𐶐�����B
        List l_lisBalanceReferenceDetail = new ArrayList();

        //1.8 ���������i�[����HashMap�𐶐�����B
        HashMap l_hashMap = new HashMap();
        
        WEB3FeqProductManager l_feqProductManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3FeqProduct l_feqProduct = null;
        
        //1.9  (*)get�ۗL���Y�ꗗ()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_lisAssetRows.size(); i++)
        {
            //1.9.1 getProduct()�O�����������I�u�W�F�N�g���擾����B 
            //arg0�F�@@�����Ώۂۗ̕L���Y.����ID
            AssetRow l_assetRow = (AssetRow)l_lisAssetRows.get(i); 
            log.debug("�ۗL���YRow = " + l_assetRow);
            
            try
            {
                l_feqProduct = (WEB3FeqProduct)l_feqProductManager.getProduct(
                    l_assetRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.debug("__NotFoundException__", l_ex);
                //��O���X���[����
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }   
            
            if (l_feqProduct == null)
            {
                log.debug("Error in �O�������������擾����");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //1.9.2 �s��I�u�W�F�N�g���擾����B
            WEB3GentradeMarket l_market = l_feqProduct.getMarket();
            
            //1.9.3 (*)���N�G�X�g�f�[�^.�s��R�[�h != null�̏ꍇ
            if (l_request.marketCode != null)
            {
                //1.9.3.1 (*)�w��s��`�F�b�N
                //(*)�w��s��`�F�b�N
                //���N�G�X�g�f�[�^.�s��R�[�h != get�s��()�̖߂�l.get�s��R�[�h()�̏ꍇ�A
                //���̗v�f�֏������ڍs����B(continue)                
                if (!l_request.marketCode.equals(l_market.getMarketCode()))
                {
                    log.debug("���N�G�X�g�f�[�^.�s��R�[�h != get�s��()�̖߂�l.get�s��R�[�h()�̏ꍇ");
                    continue;
                }
            }
            //1.9.4 reset�s��R�[�h(String)(������ԊǗ�::reset�s��R�[�h)
            //�s��R�[�h���ăZ�b�g����B 
            //[����] 
            //�s��R�[�h�F�@@get�s��()�̖߂�l
            WEB3GentradeTradingTimeManagement.resetMarketCode(
                l_market.getMarketCode());            

            //1.9.5 getQuantity( ) ���ʂ��擾����B            
            double l_dblQuantity = l_assetRow.getQuantity();
            log.debug("getQuantity( ) ���� = " + l_dblQuantity);
            
            //1.9.6 getLockedQuantity( ) ���b�N�����ʂ��擾����B
            Asset l_asset = null;
            try
            {
                l_asset = l_feqPositionManager.getAsset(l_assetRow.getAssetId());
            }
            catch (NotFoundException l_ex)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            double l_dblLockedQuantity = l_asset.getLockedQuantity();
            log.debug("getLockedQuantity( ) ���b�N������ = " + l_dblLockedQuantity);
            
            //1.9.7 �O�������c���Ɖ��( )(�O�������c���Ɖ��::�O�������c���Ɖ��)
            //�O�������c���Ɖ�׃C���X�^���X�𐶐�����B
            //(*)�C���X�^���X������Ɉȉ��̃v���p�e�B�Z�b�g���s���B
            //���t�\�t���O�@@���@@getQuantity() > getLockedQuantity()�̏ꍇ�A
            //�@@�@@�@@�@@�@@true���Z�b�g�B�ȊO�Afalse���Z�b�g�B
            //���t�\�t���O�@@���@@true���Z�b�g�B
            //��validate����\�ڋq()���`�F�b�NNG�̏ꍇ�A���t�^���t�\�t���O
            //�����Ƃ�false���Z�b�g����B
            WEB3FeqBalanceReferenceDetailUnit l_balanceReferenceDetail = 
                new WEB3FeqBalanceReferenceDetailUnit();
            
            boolean l_blnSellPossFlag = false;
            if (l_dblQuantity > l_dblLockedQuantity)
            {
                log.debug("getQuantity() > getLockedQuantity()�̏ꍇ");
                l_blnSellPossFlag = true;
            }
            else
            {
                l_blnSellPossFlag = false;
            }
            boolean l_blnBuyPossFlag = true;
            
            if (l_blnIsFailedResult)
            {
                log.debug("validate����\�ڋq()���`�F�b�NNG�̏ꍇ");
                l_blnSellPossFlag = false;
                l_blnBuyPossFlag = false;
            }
            
            WEB3FeqOrderManager l_feqOrderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            
            //1.9.8 (*)�c���Ɖ��.���t�\�t���O == true�̏ꍇ            
            if (l_blnBuyPossFlag)
            {
                log.debug("�c���Ɖ��.���t�\�t���O == true�̏ꍇ");
                
                //1.9.8.1 validate�������(�O����������, �s��, boolean)
                //�����̔����K�����`�F�b�N����B 
                //[����] 
                //�O�����������F�@@getProduct()�̖߂�l 
                //�s��F�@@get�s��()�̖߂�l 
                //is�������F�@@true�i�������j
                try
                {
                    l_feqOrderManager.validateTradedProduct(
                        l_feqProduct, l_market, true);
                }
                catch(WEB3BaseException l_ex)
                {
                    //��O���X���[���ꂽ�ꍇ�A
                    //�c���Ɖ��.���t�\�t���O��false���Z�b�g����B
                    log.debug("error in validate���������O���X���[���ꂽ�ꍇ", l_ex);
                    l_blnBuyPossFlag = false;
                }
            }
            //1.9.9 (*)�c���Ɖ��.���t�\�t���O == true�̏ꍇ
            if (l_blnSellPossFlag)
            {
                log.debug("�c���Ɖ��.���t�\�t���O == true�̏ꍇ");
                
                //1.9.9.1 validate�������(�O����������, �s��, boolean)
                //�����̔����K�����`�F�b�N����B 
                //[����] 
                //�O�����������F�@@getProduct()�̖߂�l 
                //�s��F�@@get�s��()�̖߂�l 
                //is�������F�@@false�i�������j
                try
                {
                    l_feqOrderManager.validateTradedProduct(
                        l_feqProduct, l_market, false);
                }
                catch(WEB3BaseException l_ex)
                {
                    //��O���X���[���ꂽ�ꍇ�A
                    //�c���Ɖ��.���t�\�t���O��false���Z�b�g����B
                    l_blnSellPossFlag = false;
                }
            }
            l_balanceReferenceDetail.sellPossFlag = l_blnSellPossFlag;
            l_balanceReferenceDetail.buyPossFlag = l_blnBuyPossFlag;
            
            //1.9.10 get�������(�⏕����, �O����������, HashMap)
            //���������擾����B 
            //[����] 
            //�⏕�����F�@@�p�����[�^.�⏕���� 
            //�O�������F�@@getProduct()�̖߂�l 
            //�������i�[���X�g�F�@@��������HashMap
            WEB3FeqProductQuote l_productQuote = 
                this.getEquityProductQuote(
                    l_subAccount, l_feqProduct, l_hashMap);

            //1.9.11 calc�T�Z�뉿�P��(double, double)
            //�T�Z�뉿�P���i�~�݁j���Z�o����B 
            //[����] 
            //�뉿�F�@@�ۗL���Y.�뉿�i�뉿�P���v�Z�p�j 
            //���ʁF�@@�ۗL���Y.���ʁi�뉿�P���v�Z�p�j
            WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            BigDecimal l_bdBookValuePrice = 
                l_feqBizLogicProvider.calcEstimatedBookValuePrice(
                    l_assetRow.getBookValue(), 
                    l_assetRow.getQuantityForBookValue());

            log.debug("calc�T�Z�뉿�P�� = " + l_bdBookValuePrice);
            
            //1.9.12 �ʉ݂��擾����B
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            
            //1.9.13 calc�O�݊��Z(BigDecimal, double, int, String)
            //�T�Z�뉿�P���i�O�݁j���Z�o����B 
            //[����] 
            //���z�i�~�݁j�Fcalc�T�Z�뉿�P��()�̖߂�l.�����_6�ʂ��l�̌ܓ������l
            //���[�g�F�@@�ʉ�.get���t��בփ��[�g() 
            //�����������F �ʉ�.get����������()�̖߂�l 
            //�O�݊��Z�ۂߕ����F �ʉ�.get�O�݊��Z�ۂߕ���()�̖߂�l 
            
            BigDecimal l_bdForeignCCYAmount = 
                l_feqBizLogicProvider.calcForeignCCYAmount(
                    l_bdBookValuePrice.setScale(5, BigDecimal.ROUND_HALF_UP),
                    l_currency.getSellBaseRate(), 
                    l_currency.getScale(), 
                    l_currency.getChangeFCcyRoundDiv());

            log.debug("calc�O�݊��Z = " + l_bdForeignCCYAmount);
            
            //1.9.14 (*)�O�������c���Ɖ�ׂɈȉ��̃v���p�e�B���Z�b�g����B

            //�ۗL���YID      ���@@�ۗL���Y.�ۗL���YID
            l_balanceReferenceDetail.assetId = l_assetRow.getAssetId() + "";
            //�s��R�[�h           ���@@getProduct()�̖߂�l.get�s��R�[�h
            l_balanceReferenceDetail.marketCode = l_feqProduct.getMarketCode();
            
            //�����R�[�h           ���@@getProduct()�̖߂�l.�����R�[�h
            l_balanceReferenceDetail.productCode = l_feqProduct.getProductCode();
            
            //���n�����R�[�h     ���@@getProduct()�̖߂�l.���n�����R�[�h
            l_balanceReferenceDetail.localProductCode = 
                l_feqProduct.getOffshoreProductCode();
            
            //������         ���@@getProduct()�̖߂�l.get�\��������()�̖߂�l
            l_balanceReferenceDetail.productName = 
                l_feqProduct.getDisplayProductName();
            
            //��������敪      ���@@�ۗL���Y.�ŋ敪 == "���"�̏ꍇ�A"���"���Z�b�g�B
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"����"�̏ꍇ�A"����"���Z�b�g�B
            if (TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
            {
                l_balanceReferenceDetail.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
            {
                l_balanceReferenceDetail.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }
            
            //�ʉ݃R�[�h           ���@@getProduct()�̖߂�l.�ʉ݃R�[�h
            l_balanceReferenceDetail.currencyCode= 
                l_feqProduct.getCurrencyCode();
            
            //���t�\����      ���@@getQuantity()�̖߂�l - getLockedQuantity()�̖߂�l
            double l_dblSellPossQuantity = l_dblQuantity - l_dblLockedQuantity;
            
            l_balanceReferenceDetail.sellPossQuantity = 
                WEB3StringTypeUtility.formatNumber(l_dblSellPossQuantity);
            
            //����������       ���@@getLockedQuantity()�̖߂�l
            l_balanceReferenceDetail.orderedQuantity = 
                WEB3StringTypeUtility.formatNumber(l_dblLockedQuantity);
                      
            //�c������            ���@@getQuantity()�̖߂�l + �ۗL���Y.���t�s�\����
            double l_dblBalanceQuantity = 
                l_dblQuantity + l_assetRow.getQuantityCannotSell();
            
            l_balanceReferenceDetail.balanceQuantity = 
                WEB3StringTypeUtility.formatNumber(l_dblBalanceQuantity);
            
            //���t�s�\����      ���@@�ۗL���Y.���t�s�\����
            l_balanceReferenceDetail.sellImpossQuantity = 
                WEB3StringTypeUtility.formatNumber(
                    l_assetRow.getQuantityCannotSell());
            
            //�T�Z�뉿�P���i�~�݁j  ���@@calc�T�Z�뉿�P��()�̖߂�l
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������_6�ʂ��l�̌ܓ�����B
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���߂�l��0�̏ꍇ�́Anull���Z�b�g�B
            log.debug("l_dblBookValuePrice = " + l_bdBookValuePrice);
            if (l_bdBookValuePrice.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue() != 0)
            {
                l_balanceReferenceDetail.estimatedBookPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        (l_bdBookValuePrice.setScale(5, BigDecimal.ROUND_HALF_UP)).doubleValue());
            }
            else
            {
                log.debug("calc�T�Z�뉿�P��()�̖߂�l��0�̏ꍇ");
                l_balanceReferenceDetail.estimatedBookPrice = null;
            }
            
            //�T�Z�뉿�P���i�O�݁j  ���@@calc�O�݊��Z()�̖߂�l
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���߂�l��0�̏ꍇ�́Anull���Z�b�g�B
            if (l_bdForeignCCYAmount.doubleValue() != 0)
            {
                l_balanceReferenceDetail.foreignEstimatedBookPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bdForeignCCYAmount.doubleValue());
            }
            else
            {
                l_balanceReferenceDetail.foreignEstimatedBookPrice = null;
            }
            
            //�뉿�P�����͍σt���O  ���@@�ۗL���Y.���͕뉿�P�� != null�̏ꍇ�Atrue�B
            //                     �ȊO�Afalse���Z�b�g�B
            if (!l_assetRow.getInputBookValueIsNull())
            {
                log.debug("�ۗL���Y.���͕뉿�P�� != null�̏ꍇ");
                l_balanceReferenceDetail.estimatedBookPriceInputFlag = true;
            }
            else
            {
                log.debug("�ۗL���Y.���͕뉿�P�� == null�̏ꍇ");
                l_balanceReferenceDetail.estimatedBookPriceInputFlag = false;
            }
            
            //(*1)get�������()�̖߂�l != null�̏ꍇ�̂݃Z�b�g�B
            //���e�T�Z�]���z�A�T�Z�]�����v�́Aget�������()�̖߂�l != null�̏ꍇ�̂݃Z�b�g�B
            //���e�T�Z�]���z�́A�T�Z�뉿�P���i�~�݁j != null�̏ꍇ�̂݃Z�b�g�B
            
            if (l_productQuote != null)
            {
                log.debug("get�������()�̖߂�l != null�̏ꍇ");
                
                //����(*1)          ���@@get�������()�̖߂�l.����
                l_balanceReferenceDetail.currentPrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_productQuote.getCurrentPrice());
                
                log.debug("���� = " + l_balanceReferenceDetail.currentPrice);                
                
                //�O����(*1)     ���@@get�������()�̖߂�l.�O����
                l_balanceReferenceDetail.comparedPreviousDay = 
                    WEB3StringTypeUtility.formatNumber(
                        l_productQuote.getComparedPreviousDay());
                
                log.debug("�O���� = " + l_balanceReferenceDetail.comparedPreviousDay);
                
                //�����擾����(*1)      ���@@get�������()�̖߂�l.�������\����
                //��yyyy/MM/dd�`���Ƀt�H�[�}�b�g����B
                l_balanceReferenceDetail.currentPriceTime = 
                    WEB3DateUtility.formatDate(
                        l_productQuote.getCurrentPricePublicTime(), WEB3GentradeTimeDef.DATE_SPLIT_YMD);
                        
                log.debug("�����擾���� = " + l_balanceReferenceDetail.currentPriceTime);

                log.debug("�T�Z�뉿�P���i�~�݁j != null�̏ꍇ");
                
                //�T�Z�]���z(�c������) ���@@this.calc�]���z(����, �c������, �ʉ�)            
                double l_dblEstBalanceQuantity = this.calcEstimatedValue(
                    l_productQuote.getCurrentPrice(), 
                    l_dblBalanceQuantity, 
                    l_currency);
                
                l_balanceReferenceDetail.estimatedAssetBalanceQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_dblEstBalanceQuantity);
                
                log.debug("�T�Z�]���z(�c������) = " + 
                    l_balanceReferenceDetail.estimatedAssetBalanceQuantity);
                
                //�T�Z�]���z(���t�\����)   ���@@this.calc�]���z(����, ���t�\����, �ʉ�)
                double l_dblEstSellPossQuantity = this.calcEstimatedValue(
                    l_productQuote.getCurrentPrice(), 
                    l_dblSellPossQuantity, 
                    l_currency);
                
                l_balanceReferenceDetail.estimatedAssetSellPossQuantity = 
                    WEB3StringTypeUtility.formatNumber(
                        l_dblEstSellPossQuantity);                       
                
                log.debug("�T�Z�]���z(���t�\����) = " + 
                    l_balanceReferenceDetail.estimatedAssetSellPossQuantity);
                
                //�T�Z�]���z(����������)    ���@@this.calc�]���z(����, ����������, �ʉ�)
                double l_dblEstOrderedQuantity = this.calcEstimatedValue(
                    l_productQuote.getCurrentPrice(), 
                    Double.parseDouble(l_balanceReferenceDetail.orderedQuantity), 
                    l_currency);
                
                l_balanceReferenceDetail.estimatedAssetOrderedQuantity = 
                    WEB3StringTypeUtility.formatNumber(
                        l_dblEstOrderedQuantity);
                
                log.debug("�T�Z�]���z(����������) = " + 
                    l_balanceReferenceDetail.estimatedAssetOrderedQuantity);
                
                //�T�Z�]���z(���t�s�\����)   ���@@this.calc�]���z(����, ���t�s�\����, �ʉ�)
                double l_dblEstSellImpossQuantity = this.calcEstimatedValue(
                    l_productQuote.getCurrentPrice(), 
                    Double.parseDouble(l_balanceReferenceDetail.sellImpossQuantity), 
                    l_currency);
                
                l_balanceReferenceDetail.estimatedAssetSellImpossQuantity = 
                    WEB3StringTypeUtility.formatNumber(
                        l_dblEstSellImpossQuantity);
                
                log.debug("�T�Z�]���z(���t�s�\����) = " + 
                    l_balanceReferenceDetail.estimatedAssetSellImpossQuantity);

                //�ۗL���Y.�ŋ敪=="���"�̏ꍇ �jget�������()�̖߂�l != null
                //      ���@@�T�Z�뉿�P���i�~�݁j != null�̏ꍇ
                if (TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
                {
                    if (l_balanceReferenceDetail.estimatedBookPrice != null)
                    {
                        //�T�Z�]�����v(�c������)    ��
                        //  this.calc�]�����v(����, calc�T�Z�뉿�P��()�̖߂�l, �c������, �ʉ�)
                        double l_dblProLossBalanceQuantity = this.calcProfitLoss(
                            l_productQuote.getCurrentPrice(),
                            l_bdBookValuePrice,
                            l_dblBalanceQuantity,
                            l_currency);

                        l_balanceReferenceDetail.estimatedAppraisalProfitLossBalanceQuantity =
                            WEB3StringTypeUtility.formatNumber(
                                l_dblProLossBalanceQuantity);

                        log.debug("�T�Z�]�����v(�c������) = " +
                            l_balanceReferenceDetail.estimatedAppraisalProfitLossBalanceQuantity);

                        //�T�Z�]�����v(���t�\����)  ��
                        //  this.calc�]�����v(����, calc�T�Z�뉿�P��()�̖߂�l, ���t�\����, �ʉ�)
                        double l_dblProLossSellPossQuantity = this.calcProfitLoss(
                            l_productQuote.getCurrentPrice(),
                            l_bdBookValuePrice,
                            l_dblSellPossQuantity,
                            l_currency);

                        l_balanceReferenceDetail.estimatedAppraisalProfitLossSellPossQuantity =
                            WEB3StringTypeUtility.formatNumber(
                                l_dblProLossSellPossQuantity);

                        log.debug("�T�Z�]�����v(���t�\����) = " +
                            l_balanceReferenceDetail.estimatedAppraisalProfitLossSellPossQuantity);

                        //�T�Z�]�����v(����������)   ��
                        //  this.calc�]�����v(����, calc�T�Z�뉿�P��()�̖߂�l, ����������, �ʉ�)
                        double l_dblProLossOrderedQuantity = this.calcProfitLoss(
                            l_productQuote.getCurrentPrice(),
                            l_bdBookValuePrice,
                            Double.parseDouble(l_balanceReferenceDetail.orderedQuantity),
                            l_currency);

                        l_balanceReferenceDetail.estimatedAppraisalProfitLossOrderedQuantity =
                            WEB3StringTypeUtility.formatNumber(
                                l_dblProLossOrderedQuantity);

                        log.debug("�T�Z�]�����v(����������) = " +
                            l_balanceReferenceDetail.estimatedAppraisalProfitLossOrderedQuantity);

                        //�T�Z�]�����v(���t�s�\����)  ��
                        //  this.calc�]�����v(����, calc�T�Z�뉿�P��()�̖߂�l, ���t�s�\����, �ʉ�)
                        double l_dblProLossSellImpossQuantity = this.calcProfitLoss(
                            l_productQuote.getCurrentPrice(),
                            l_bdBookValuePrice,
                            Double.parseDouble(l_balanceReferenceDetail.sellImpossQuantity),
                            l_currency);

                        l_balanceReferenceDetail.estimatedAppraisalProfitLossSellImpossQuantity =
                            WEB3StringTypeUtility.formatNumber(
                                l_dblProLossSellImpossQuantity);

                        log.debug("�T�Z�]�����v(���t�s�\����) = " +
                            l_balanceReferenceDetail.estimatedAppraisalProfitLossSellImpossQuantity);
                    }
                }
                //�ۗL���Y.�ŋ敪=="����"�̏ꍇ �jget�������()�̖߂�l != null�@@�̏ꍇ
                else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
                {
                    //�T�Z�]�����v(�c������)    ���@@
                    //  this.calc�]�����v(����, calc�T�Z�뉿�P��()�̖߂�l, �c������, �ʉ�)
                    double l_dblProLossBalanceQuantity = this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(), 
                        l_bdBookValuePrice,
                        l_dblBalanceQuantity, 
                        l_currency);                
                    
                    l_balanceReferenceDetail.estimatedAppraisalProfitLossBalanceQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_dblProLossBalanceQuantity);
                    
                    log.debug("�T�Z�]�����v(�c������) = " + 
                        l_balanceReferenceDetail.estimatedAppraisalProfitLossBalanceQuantity);
                                
                    //�T�Z�]�����v(���t�\����)  ���@@
                    //  this.calc�]�����v(����, calc�T�Z�뉿�P��()�̖߂�l, ���t�\����, �ʉ�)
                    double l_dblProLossSellPossQuantity = this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(), 
                        l_bdBookValuePrice,
                        l_dblSellPossQuantity, 
                        l_currency);
                    
                    l_balanceReferenceDetail.estimatedAppraisalProfitLossSellPossQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_dblProLossSellPossQuantity);
                    
                    log.debug("�T�Z�]�����v(���t�\����) = " + 
                        l_balanceReferenceDetail.estimatedAppraisalProfitLossSellPossQuantity);
                    
                    //�T�Z�]�����v(����������)   ���@@
                    //  this.calc�]�����v(����, calc�T�Z�뉿�P��()�̖߂�l, ����������, �ʉ�)
                    double l_dblProLossOrderedQuantity = this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(), 
                        l_bdBookValuePrice,
                        Double.parseDouble(l_balanceReferenceDetail.orderedQuantity), 
                        l_currency);
                    
                    l_balanceReferenceDetail.estimatedAppraisalProfitLossOrderedQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_dblProLossOrderedQuantity);
                    
                    log.debug("�T�Z�]�����v(����������) = " + 
                        l_balanceReferenceDetail.estimatedAppraisalProfitLossOrderedQuantity);
                    
                    //�T�Z�]�����v(���t�s�\����)  ���@@
                    //  this.calc�]�����v(����, calc�T�Z�뉿�P��()�̖߂�l, ���t�s�\����, �ʉ�)
                    double l_dblProLossSellImpossQuantity = this.calcProfitLoss(
                        l_productQuote.getCurrentPrice(), 
                        l_bdBookValuePrice,
                        Double.parseDouble(l_balanceReferenceDetail.sellImpossQuantity), 
                        l_currency);
                    
                    l_balanceReferenceDetail.estimatedAppraisalProfitLossSellImpossQuantity = 
                        WEB3StringTypeUtility.formatNumber(
                            l_dblProLossSellImpossQuantity);
                    
                    log.debug("�T�Z�]�����v(���t�s�\����) = " + 
                        l_balanceReferenceDetail.estimatedAppraisalProfitLossSellImpossQuantity);
                }
            }
            //1.9.15 ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B 
            //[����] 
            //arg0�F�@@�v���p�e�B�Z�b�g�����O�������c���Ɖ��
            l_lisBalanceReferenceDetail.add(l_balanceReferenceDetail);
        }
        
        //1.10 toArray()
        //�O�������c���Ɖ�ׂ̔z��𐶐�����B
        WEB3FeqBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnits = 
            new WEB3FeqBalanceReferenceDetailUnit[l_lisBalanceReferenceDetail.size()];
        
        l_lisBalanceReferenceDetail.toArray(l_balanceReferenceDetailUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_balanceReferenceDetailUnits;
    }
    
    /**
     * (get�������)<BR>
     * ���������擾����B<BR>
     * <BR>
     * �P�j������񌟍�<BR>
     * �@@�p�����[�^.�������i�[���X�g.get()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[get()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@key�F�@@�p�����[�^.�O������.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@+ �p�����[�^.�O������.get�s��R�[�h()<BR>
     * <BR>
     * �Q�j�������ԋp<BR>
     * �@@�P�j�̖߂�l != null�̏ꍇ�A�擾������������ԋp����B<BR>
     * <BR>
     * �R�j�������擾<BR>
     * �@@�P�j�̖߂�l == null�̏ꍇ�A�ȉ��̎菇�Ŏ��������擾����B<BR>
     * �@@�R�|�P�j�p�����[�^.�O������.get�������()���R�[������B<BR>
     * <BR>
     * �@@�R�|�Q�j�O�������v���_�N�g�}�l�[�W���Dget���������R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@[ ���� ]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������<BR>
     * �@@�@@�@@�@@�@@�@@�@@���A���敪�F���l<BR>
     * <BR>
     * �@@�R�|�R�j�p�����[�^.�������i�[���X�g.put()���\�b�h���R�[�����A<BR>
     * �@@�@@�擾�������������i�[����B<BR>
     * <BR>
     * �@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@key�F�@@�p�����[�^.�O������.�����R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@+ �p�����[�^.�O������.get�s��R�[�h()<BR>
     * �@@�@@�@@value�F�@@�擾�����������<BR>
     * <BR>
     * �@@�R�|�S�j�擾������������ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_feqProduct - (�O������)<BR>
     * �O�����������I�u�W�F�N�g<BR>
     * @@param l_hmProductQuote - (�������i�[���X�g)<BR>
     * �ȑO�̏����Ŏ擾�ς݂̎��������i�[���郊�X�g
     * @@return WEB3FeqProductQuote
     * @@throws WEB3BaseException
     * @@roseuid 42A8313B026E
     */
    protected WEB3FeqProductQuote getEquityProductQuote(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3FeqProduct l_feqProduct, 
        HashMap l_hmProductQuote) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEquityProductQuote(" +
            "WEB3GentradeSubAccount l_subAccount, " +
            "WEB3FeqProduct l_feqProduct, HashMap l_hmProductQuote)";

        log.entering(STR_METHOD_NAME);
    
        if (l_subAccount == null || l_feqProduct == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //���������擾����B 
        //�P�j������񌟍� 
        //�p�����[�^.�������i�[���X�g.get()���\�b�h���R�[������B 
        //[get()�ɃZ�b�g����p�����[�^] 
        //�@@key�F�@@�p�����[�^.�O������.�����R�[�h 
        //�@@�@@�@@�@@+ �p�����[�^.�O������.get�s��R�[�h() 
        WEB3FeqProductQuote l_feqProductQuote = (WEB3FeqProductQuote)
            l_hmProductQuote.get(l_feqProduct.getProductCode() + 
                l_feqProduct.getMarketCode());
        
        //�Q�j�������ԋp 
        //�P�j�̖߂�l != null�̏ꍇ�A�擾������������ԋp����B 
        if (l_feqProductQuote != null)
        {
            log.debug("�������̖߂�l != null�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            return l_feqProductQuote;
        }        
        //�R�j�������擾 
        //�P�j�̖߂�l == null�̏ꍇ�A�ȉ��̎菇�Ŏ��������擾����B 
        else
        {
            log.debug("�������̖߂�l == null�̏ꍇ");
            //�R�|�P�j�p�����[�^.�O������.get�������()���R�[������B 
            WEB3FeqTradedProduct l_feqTradedProduct = 
                l_feqProduct.getFeqTradedProduct();

            //�R�|�Q�j�O�������v���_�N�g�}�l�[�W���Dget���������R�[������B
            //�@@�@@�@@�@@�@@�@@[ ���� ]
            //�@@�@@�@@�@@�@@�@@�@@�������
            //�@@�@@�@@�@@�@@�@@�@@���A���敪�F���l
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqProductManager l_productMgr =
                (WEB3FeqProductManager)l_tradingModule.getProductManager();
            l_feqProductQuote = l_productMgr.getCurrentPriceUnit(
                l_feqTradedProduct,
                RealType.CLOSING_PRICE);

            //�R�|�R�j�p�����[�^.�������i�[���X�g.put()���\�b�h���R�[�����A 
            //�@@�擾�������������i�[����B     
            //�@@[put()�ɃZ�b�g����p�����[�^] 
            //�@@�@@key�F�@@�p�����[�^.�O������.�����R�[�h 
            //�@@�@@�@@�@@�@@+ �p�����[�^.�O������.get�s��R�[�h() 
            //�@@�@@value�F�@@�擾����������� 
            l_hmProductQuote.put(
                l_feqProduct.getProductCode() + l_feqProduct.getMarketCode(), 
                l_feqProductQuote);     
            
            //�@@�R�|�S�j�擾������������ԋp����B 
            log.exiting(STR_METHOD_NAME);
            return l_feqProductQuote;
        }       
    }
    
    /**
     * (calc�]���z)<BR>
     * �]���z�i�~�݁j���Z�o���A�ԋp����B<BR>
     * ���萔���͊܂܂Ȃ��B<BR>
     * <BR>
     * �P�j�@@�]���z�i�O�݁j�̎Z�o<BR>
     * �@@�ȉ��̌v�Z���ɂĎZ�o����B<BR>
     * <BR>
     * �@@�]���z�i�O�݁j = �p�����[�^.�]���P�� * �p�����[�^.����<BR>
     * <BR>
     * �Q�j�@@�~�݊��Z<BR>
     * �@@�O�������v�Z�T�[�r�X.calc�~�݊��Z()�ɂĉ~�݊��Z����<BR>
     * �@@�]���z��ԋp����B<BR>
     * <BR>
     * �@@[calc�~�݊��Z()�Ɏw�肷�����]<BR>
     * �@@�@@���z�i�O�݁j�F�@@�P�j�̌v�Z����<BR>
     * �@@�@@���[�g�F�@@�p�����[�^.�ʉ�.get���t���בփ��[�g<BR>
     * �@@�@@�~�݊��Z�ۂߕ����F�@@�p�����[�^.�ʉ�.get�~�݊��Z�ۂߕ���()<BR>
     * @@param l_dblPrice - (�]���P��)<BR>
     * �]���P��
     * @@param l_dblQuantity - (����)<BR>
     * ����
     * @@param l_currency - (�ʉ�)<BR>
     * �i���ʁj�ʉ݃I�u�W�F�N�g
     * @@return double
     * @@roseuid 42A8430A03E5
     */
    protected double calcEstimatedValue(
        double l_dblPrice, 
        double l_dblQuantity, 
        WEB3GentradeCurrency l_currency) 
    {
        final String STR_METHOD_NAME = "calcEstimatedValue(" +
            "double l_dblPrice, double l_dblQuantity, " +
            "WEB3GentradeCurrency l_currency)" ;
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�]���z�i�O�݁j�̎Z�o 
        //�ȉ��̌v�Z���ɂĎZ�o����B 
        //�]���z�i�O�݁j = �p�����[�^.�]���P�� * �p�����[�^.����
        double l_dblEstimatedValueFc = l_dblPrice * l_dblQuantity;
        
        //�Q�j�@@�~�݊��Z 
        //�O�������v�Z�T�[�r�X.calc�~�݊��Z()�ɂĉ~�݊��Z�����]���z��ԋp����B 
        //[calc�~�݊��Z()�Ɏw�肷�����] 
        //���z�i�O�݁j�F�@@�P�j�̌v�Z���� 
        //���[�g�F�@@�p�����[�^.�ʉ�.get���t���בփ��[�g
        //�~�݊��Z�ۂߕ����F�@@�p�����[�^.�ʉ�.get�~�݊��Z�ۂߕ���() 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        double l_dblJpyAmout = 
            l_feqBizLogicProvider.calcJPYAmount(
                l_dblEstimatedValueFc, 
                l_currency.getSellExecRate(),
                l_currency.getChangeJpyRoundDiv());
        
        log.debug("�]���z = " + l_dblJpyAmout);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblJpyAmout;
    }
    
    /**
     * (calc�]�����v)<BR>
     * �]�����v�i�~�݁j���Z�o���A�ԋp����B<BR>
     * ���萔���͊܂܂Ȃ��B<BR>
     * <BR>
     * �P�j�@@�]���z�i�]���P���E�~�݁j�̎Z�o<BR>
     * <BR>
     * �@@this.calc�]���z���R�[������B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�p�����[�^�D�]���P��<BR>
     * �@@�@@�@@�p�����[�^�D����<BR>
     * �@@�@@�@@�p�����[�^�D�ʉ�<BR>
     * <BR>
     * �Q�j�@@�]���z�i�T�Z�뉿�P���E�~�݁j�̎Z�o<BR>
     * �@@�@@�ȉ��̌v�Z�������Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@�p�����[�^�D�T�Z�뉿�P���@@*�@@�p�����[�^�D���ʁ@@�i�~�����؎̂āj<BR>
     * <BR>
     * �R�j�@@�]�����v�̌v�Z<BR>
     * �@@�ȉ��̌v�Z���ɂČv�Z�������ʂ�ԋp����B<BR>
     * <BR>
     * �@@�P�j�̖߂�l�@@�|�@@�Q�j�̌v�Z����<BR>
     * <BR>
     * @@param l_dblPrice - (�]���P��)<BR>
     * �]���P��
     * @@param l_bdBookValue - (�T�Z�뉿�P��)<BR>
     * calc�T�Z�뉿�P��()�̖߂�l
     * 
     * @@param l_dblQuantity - <����><BR>
     * ����
     * @@param l_currency - (�ʉ�)<BR>
     * �ʉ݃I�u�W�F�N�g
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 42A843430339
     */
    protected double calcProfitLoss(
        double l_dblPrice, BigDecimal l_bdBookValue, 
        double l_dblQuantity, WEB3GentradeCurrency l_currency)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcProfitLoss(double, " +
            "BigDecimal, double, WEB3GentradeCurrency";
        log.entering(STR_METHOD_NAME);

        //�]���z�i�]���P���E�~�݁j�̎Z�o
        //�@@this.calc�]���z���R�[������B
        //�@@�@@[����]
        //�@@�@@�@@�p�����[�^�D�]���P��
        //�@@�@@�@@�p�����[�^�D����
        //�@@�@@�@@�p�����[�^�D�ʉ�
        double l_dblEstimatedValue = this.calcEstimatedValue(
            l_dblPrice,
            l_dblQuantity,
            l_currency);

        //�]���z�i�T�Z�뉿�P���E�~�݁j�̎Z�o
        //�p�����[�^�D�T�Z�뉿�P���@@*�@@�p�����[�^�D���ʁ@@�i�~�����؎̂āj
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));
        BigDecimal l_bdEstimatedValue =
            (l_bdBookValue.multiply(l_bdQuantity)).setScale(0, BigDecimal.ROUND_DOWN);

        //�]�����v�̌v�Z
        //�P�j�̖߂�l�@@�|�@@�Q�j�̌v�Z����
        BigDecimal l_bdResultValue =
            (new BigDecimal(String.valueOf(l_dblEstimatedValue))).subtract(
                l_bdEstimatedValue);

        log.exiting(STR_METHOD_NAME);
        return l_bdResultValue.doubleValue();
    }
    
    /**
     * (sort�c���Ɖ��)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Ďc���Ɖ�ׂ̃\�[�g���s���B<BR>
     * <BR>
     * �P�j�p�����[�^.�c���Ɖ�� == null�̏ꍇ�A<BR>
     * �@@�������I������B<BR>
     * <BR>
     * �Q�jArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�Comparator�𐶐����A<BR>
     * �@@�@@�@@ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�O�������c���Ɖ�Comparator�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~��<BR>
     * �@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[����<BR>
     * <BR>
     * �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B<BR>
     * <BR>
     * �S�jWEB3ArraysUtility.sort()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[sort()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@obj�F�@@�p�����[�^.�c���Ɖ��<BR>
     * �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l<BR>
     * @@param l_balanceReferenceDetailUnits - (�c���Ɖ��)<BR>
     * �O�������c���Ɖ�ׂ̔z��
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �O�������\�[�g�L�[�̔z��
     * @@roseuid 42A844210368
     */
    protected void sortBalanceReferenceDetailUnit(
        WEB3FeqBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnits, 
        WEB3ForeignSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = "sortBalanceReferenceDetailUnit(" +
            "WEB3FeqBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnits, " +
            "WEB3ForeignSortKey[] l_sortKeys)" ;
        log.entering(STR_METHOD_NAME);
        
        //�P�j�p�����[�^.�c���Ɖ�� == null�̏ꍇ�A 
        //�������I������B 
        if (l_balanceReferenceDetailUnits == null)
        {
            log.debug("�p�����[�^.�c���Ɖ�� == null�̏ꍇ");
             return;
        }

        //�Q�jArrayList�𐶐�����B 
        List l_lisComparator = new ArrayList();
        
        //�R�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B 
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //�@@�R�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�Comparator�𐶐����A 
            //�@@�@@�@@ArrayList�ɒǉ�����B             
            //�@@�@@�@@(1)�O�������c���Ɖ�Comparator�𐶐�����B             
            //�@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^] 
            //�@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~�� 
            //�@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[���� 
            WEB3FeqBalanceReferenceComparator l_banlanceReferenceComparator = 
                new WEB3FeqBalanceReferenceComparator(
                    l_sortKeys[i].ascDesc, 
                    l_sortKeys[i].keyItem);
            //�@@�@@�@@(2)ArrayList�ɐ�������Comparator��ǉ�����B 
            l_lisComparator.add(l_banlanceReferenceComparator);
        }
        
        //�S�jWEB3ArraysUtility.sort()���\�b�h���R�[������B 
        //[sort()�ɃZ�b�g����p�����[�^] 
        //�@@obj�F�@@�p�����[�^.�c���Ɖ�� 
        //�@@com�F�@@��������ArrayList.toArray()�̖߂�l 
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);
        
        WEB3ArraysUtility.sort(
            l_balanceReferenceDetailUnits, 
            l_comparators);        
        
        log.entering(STR_METHOD_NAME);
    }
}
@
