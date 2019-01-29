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
filename	WEB3MstkBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����c���Ɖ�T�[�r�XImpl(WEB3MstkBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MiniClientRequestService;
import webbroker3.equity.WEB3MstkBalanceReferenceComparator;
import webbroker3.equity.message.WEB3MstkBalanceReferenceDetailUnit;
import webbroker3.equity.message.WEB3MstkBalanceReferenceRequest;
import webbroker3.equity.message.WEB3MstkBalanceReferenceResponse;
import webbroker3.equity.message.WEB3MstkBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3MstkBalanceReferenceTotalResponse;
import webbroker3.equity.message.WEB3MstkProductCodeNameUnit;
import webbroker3.equity.message.WEB3MstkSortKey;
import webbroker3.equity.service.delegate.WEB3MstkBalanceReferenceService;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����~�j�����c���Ɖ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �����~�j�����c���Ɖ�T�[�r�X�����N���X<BR>
 */
public class WEB3MstkBalanceReferenceServiceImpl
extends WEB3MiniClientRequestService
implements WEB3MstkBalanceReferenceService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceServiceImpl.class);    
        
    /**
     * @@roseuid 4206CCB4002B<BR>
     */
    public WEB3MstkBalanceReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �����~�j�����c���Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �������~�j�����c���Ɖ�c�����v���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�c�����v()���\�b�h���R�[������B<BR>
     * <BR>
     * �������~�j�����c���Ɖ�N�G�X�g�̏ꍇ<BR>
     * �@@this.get�c���Ɖ�()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41C2D24B021A<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "WEB3MstkBalanceReferenceServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.error("�p�����[�^.���N�G�X�g�f�[�^��null�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3MstkBalanceReferenceRequest)
        {
            l_response = 
                this.getBalanceReference((WEB3MstkBalanceReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3MstkBalanceReferenceTotalRequest)
        {
            l_response = 
                this.getBalanceTotal((WEB3MstkBalanceReferenceTotalRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^.���N�G�X�g�f�[�^�̌^���s���ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�c���Ɖ�)<BR>
     * <BR>
     * �����~�j�����c���Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�~�j���c���Ɖ�T�[�r�X�jget�c���Ɖ�v<BR>
     * �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) �����~�j�����c���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MstkBalanceReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2D27201FB<BR>
     */
    protected WEB3MstkBalanceReferenceResponse getBalanceReference(WEB3MstkBalanceReferenceRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3MstkBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        // validate
        l_request.validate();
        
        // get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        // validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // ���X�|���X�f�[�^����
        WEB3MstkBalanceReferenceResponse l_response =
            (WEB3MstkBalanceReferenceResponse)l_request.createResponse();
            
        // create�����R�[�h����
        this.createProductCodeNames(l_subAccount, l_response);
        
        // create�c���Ɖ�׈ꗗ
        this.createBalanceReferenceDetailUnitList(l_response, l_subAccount, l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�c�����v)<BR>
     *<BR>
     * �����~�j�����c�����v�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�~�j���c���Ɖ�T�[�r�X�jget�c�����v�v<BR>
     * �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) �����~�j�����c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MstkBalanceReferenceTotalResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2D272021B<BR>
     */
    protected WEB3MstkBalanceReferenceTotalResponse getBalanceTotal(WEB3MstkBalanceReferenceTotalRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceTotal(WEB3MstkBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME); 
        
        // validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // get�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        // ���X�|���X�f�[�^�𐶐�
        WEB3MstkBalanceReferenceTotalResponse l_response =
            (WEB3MstkBalanceReferenceTotalResponse)l_request.createResponse();
            
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �g���|�W�V�����}�l�[�W���擾
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        // get�~�j���ۗL���Y�ꗗ
        List l_lstMiniStockAssets =
            l_positionManager.getMiniStockAssets(l_subAccount, ProductTypeEnum.EQUITY, null, null, "product_id asc");
        
        if (l_lstMiniStockAssets == null || l_lstMiniStockAssets.size() == 0)
        {
            // ���X�|���X�ɏ����l���Z�b�g���ďI��
            l_response.capitalGainTotalAsset = "0";
            l_response.normalAccountTotalAsset = "0";
            l_response.capitalGainTotalAppraisalProfitLoss = "0";
            l_response.normalAccountTotalAppraisalProfitLoss = "0";
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        HashMap l_hmProductQuote = new HashMap();
        
        double l_dblNormalEstimatedValueTotal = 0.0D;
        double l_dblNormalProfitLossTotal = 0.0D;
        double l_dblSpecialEstimatedValueTotal = 0.0D;
        double l_dblSpecialProfitLossTotal = 0.0D;
        
        // �g���v���_�N�g�}�l�[�W�����擾
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        
        // get�~�j���ۗL���Y�ꗗ�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_lstMiniStockAssets.size(); i++)
        {
            WEB3EquityAsset l_asset = (WEB3EquityAsset)l_lstMiniStockAssets.get(i);
            AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
            
            // �����������擾
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_asset.getProduct();
            // �~�j���s����擾
            Market l_miniStockMarket = l_eqtypeProduct.getMiniStockMarket();
            
            WEB3EquityTradedProduct l_tradedProduct = null;
            WEB3EquityProductQuote l_productQuote = null;
            try
            {
                if (l_miniStockMarket != null)
                {
	                l_tradedProduct =
	                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_eqtypeProduct, l_miniStockMarket);
		            // ���������擾
		            l_productQuote = this.getEquityProductQuote(l_subAccount, l_tradedProduct, l_hmProductQuote);
                }
            } catch (NotFoundException l_ex) {
                log.debug("*** ��������Ȃ� *** ����ID:[" + l_eqtypeProduct.getProductId() +
                    "] �s��R�[�h:[" + l_miniStockMarket + "]�ɊY������L���Ȏ�����������݂��܂���B");
            }
            
            // ������񂪎擾�ł��Ȃ������ꍇ�́A���ۗ̕L���Y�֏������ڍs����B
            if (l_productQuote == null)
            {
                continue;
            }
            // ����
            double l_dblCurrentPrice = l_productQuote.getQuote();
            // �c������
            double l_dblBalanceQuantity = l_asset.getQuantity() + l_assetRow.getQuantityCannotSell();
            // �]���z���Z�o
            double l_dblEstimatedValue = this.calcEstimatedValue(l_dblCurrentPrice, l_dblBalanceQuantity);
            
            // �T�Z�뉿�P��
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblBookValuePrice = l_bizLogicProvider.calcEstimatedBookPrice(
                l_assetRow.getBookValue(),
                l_assetRow.getQuantityForBookValue(),
                0);
            
            // �]�����v���Z�o
            double l_dblProfitLoss = 0.0D;
            if (l_dblBookValuePrice != 0)
            {
                l_dblProfitLoss = this.calcProfitLoss(l_dblCurrentPrice, l_dblBookValuePrice, l_dblBalanceQuantity);
            }
            
            // �ۗL���Y.�ŋ敪 == "���"�̏ꍇ
            if (l_asset.getTaxType().equals(TaxTypeEnum.NORMAL))
            {
                // ��ʌ����]���z���v�A��ʌ����]�����v���v�ɉ��Z����
                l_dblNormalEstimatedValueTotal += l_dblEstimatedValue;
                l_dblNormalProfitLossTotal += l_dblProfitLoss;
            }
            // �ۗL���Y.�ŋ敪 == "����" or "������������򒥎�"�̏ꍇ
            else if (l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL)
                || l_asset.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
            {
                // ��������]���z���v�A��������]�����v���v�ɉ��Z����
               l_dblSpecialEstimatedValueTotal += l_dblEstimatedValue;
               l_dblSpecialProfitLossTotal += l_dblProfitLoss;
            }
        }
        
        l_response.capitalGainTotalAsset = WEB3StringTypeUtility.formatNumber(l_dblSpecialEstimatedValueTotal);
        l_response.capitalGainTotalAppraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblSpecialProfitLossTotal);
        l_response.normalAccountTotalAsset = WEB3StringTypeUtility.formatNumber(l_dblNormalEstimatedValueTotal);
        l_response.normalAccountTotalAppraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblNormalProfitLossTotal);

        return l_response;
    }
    
    /**
     * (create�����R�[�h����)<BR>
     * <BR>
     * �@@�w������̕ێ�����~�j���ۗL���Y�̖����R�[�h�Ɩ������̈ꗗ���쐬���A���X�|���X<BR>
     * �f�[�^�ɃZ�b�g����B <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null���Z�b�g����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j���c���Ɖ�T�[�r�X�jcreate�����R�[�h���́v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g<BR>
     * @@param l_response - (���X�|���X�f�[�^) �����~�j�����c���Ɖ�X�|���X�f�[�^�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2D58A007A<BR>
     */
    protected void createProductCodeNames(WEB3GentradeSubAccount l_subAccount, WEB3MstkBalanceReferenceResponse l_response) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductCodeNames(WEB3GentradeSubAccount, WEB3MstkBalanceReferenceResponse)";
        log.entering(STR_METHOD_NAME); 
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �g���|�W�V�����}�l�[�W���擾
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        // get�~�j���ۗL���Y�ꗗ
        List l_lstMiniStockAssets =
            l_positionManager.getMiniStockAssets(l_subAccount, ProductTypeEnum.EQUITY, null, null, "product_id asc");
        
        if (l_lstMiniStockAssets == null || l_lstMiniStockAssets.size() == 0)
        {
            l_response.productCodeNames = null;
            
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        TreeMap l_tmProductCodeNames = new TreeMap();
        
        // get�~�j���ۗL���Y�ꗗ�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_lstMiniStockAssets.size(); i++)
        {
            WEB3EquityAsset l_asset = (WEB3EquityAsset)l_lstMiniStockAssets.get(i);
            AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
            
            String l_strProductId = String.valueOf(l_assetRow.getProductId());
            
            // ���ǉ��̏ꍇ�̏������{
            if (l_tmProductCodeNames.get(l_strProductId) == null)
            {
                // �����������擾
                WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_asset.getProduct();
                // �����~�j�������R�[�h���̂��쐬���A�v���p�e�B�Z�b�g
                WEB3MstkProductCodeNameUnit l_productCodeNameUnit = new WEB3MstkProductCodeNameUnit();
                l_productCodeNameUnit.productCode = l_eqtypeProduct.getProductCode();
                EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
                l_productCodeNameUnit.productName = l_eqtypeProductRow.getStandardName();
                
                l_tmProductCodeNames.put(l_strProductId, l_productCodeNameUnit);
            }
        }
        // ���X�|���X�f�[�^.�����ꗗ�Ƀv���p�e�B�Z�b�g
        l_response.productCodeNames =
            (WEB3MstkProductCodeNameUnit[])l_tmProductCodeNames.values().toArray(new WEB3MstkProductCodeNameUnit[0]);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�c���Ɖ�׈ꗗ)<BR>
     * <BR>
     * �w������̕ێ�����~�j���ۗL���Y���A<BR>
     * �c���Ɖ�ׂ��쐬���A���X�|���X�f�[�^�ɃZ�b�g����B <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null���Z�b�g����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�~�j���c���Ɖ�T�[�r�X�jcreate�c���Ɖ�׈ꗗ�v�Q�ƁB<BR>
     * <BR>
     * @@param l_response - (���X�|���X�f�[�^) �����~�j�����c���Ɖ�X�|���X�f�[�^�I�u�W�F�N�g<BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) �����~�j�����c���Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2DBCC021A<BR>
     */
    protected void createBalanceReferenceDetailUnitList(WEB3MstkBalanceReferenceResponse l_response, WEB3GentradeSubAccount l_subAccount, WEB3MstkBalanceReferenceRequest l_request) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceReferenceDetailUnitList(WEB3MstkBalanceReferenceResponse, WEB3GentradeSubAccount, WEB3MstkBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME); 
     
        // create��������������
        String l_strProductCode = l_request.productCode;
        String l_strQueryString = this.createQueryString(l_strProductCode);
        
        // create���������f�[�^�R���e�i
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        String[] l_strQueryDataContainer = this.createQueryContainer(l_institution, l_strProductCode);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �g���|�W�V�����}�l�[�W���擾
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            
        // get�~�j���ۗL���Y�ꗗ
        List l_lstMiniStockAssets =
            l_positionManager.getMiniStockAssets(l_subAccount, ProductTypeEnum.EQUITY, l_strQueryString, l_strQueryDataContainer, null);
        
        if (l_lstMiniStockAssets == null || l_lstMiniStockAssets.size() == 0)
        {
            l_response.mstkBalanceReferenceDetail = null;
            l_response.pageIndex = "0";
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        HashMap l_hmProductQuote = new HashMap();
        ArrayList l_lstBalanceReference = new ArrayList();
        
        // �g���v���_�N�g�}�l�[�W���擾
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        // ���������}�l�[�W���擾
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            
        // get�~�j���ۗL���Y�ꗗ�̗v�f����Loop����
        for (int i = 0; i < l_lstMiniStockAssets.size(); i++)
        {
            WEB3EquityAsset l_asset = (WEB3EquityAsset)l_lstMiniStockAssets.get(i);
            AssetRow l_assetRow = (AssetRow)l_asset.getDataSourceObject();
            
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_asset.getProduct();
            
            // �~�j���s����擾����
            Market l_miniStockMarket = l_eqtypeProduct.getMiniStockMarket();
            
            WEB3EquityTradedProduct l_tradedProduct = null;
            try
            {
                if (l_miniStockMarket != null)
                {
	                l_tradedProduct =
	                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_eqtypeProduct, l_miniStockMarket);
                }
            } catch (NotFoundException l_ex) {
                log.debug("*** ��������Ȃ� *** ����ID:[" + l_eqtypeProduct.getProductId() +
                    "] �s��R�[�h:[" + l_miniStockMarket + "]�ɊY������L���Ȏ�����������݂��܂���B");
            }
            
            long l_lngAccountId = l_subAccount.getMainAccount().getAccountId();
            long l_lngSubAccountId = l_subAccount.getSubAccountId();
            long l_lngProductId = l_eqtypeProduct.getProductId();
            // get�~�j������������(������)
            double l_dblBuyOrderingQuantity =
                l_orderManager.getMiniStockOrderingQuantity(l_lngAccountId, l_lngSubAccountId, l_lngProductId, false);
                
            // get�~�j������������(������)
            double l_dblSellOrderingQuantity =
                l_orderManager.getMiniStockOrderingQuantity(l_lngAccountId, l_lngSubAccountId, l_lngProductId, true);
                
            // �������擾
            WEB3EquityProductQuote l_productQuote = null;
            if (l_tradedProduct != null)
            {
                l_productQuote = this.getEquityProductQuote(l_subAccount, l_tradedProduct, l_hmProductQuote);
            }
            
            // �����~�j�����c���Ɖ�ׂ��쐬
            WEB3MstkBalanceReferenceDetailUnit l_balanceReferenceUnit = new WEB3MstkBalanceReferenceDetailUnit();
			// �v���p�e�B�������i���t�\�t���O�j
			l_balanceReferenceUnit.sellPossFlag = true;
			
            // �v���p�e�B�Z�b�g
            // �����R�[�h
            l_balanceReferenceUnit.productCode = l_eqtypeProduct.getProductCode();
            // ������
            EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject(); 
            l_balanceReferenceUnit.productName = l_eqtypeProductRow.getStandardName();
            // �s��R�[�h
            if (l_miniStockMarket != null)
            {
	            l_balanceReferenceUnit.marketCode = l_miniStockMarket.getMarketCode();
            }
            else
            {
	            l_balanceReferenceUnit.marketCode = null;
				l_balanceReferenceUnit.sellPossFlag = false;
            }
            
            // �c������
            double l_dblBalanceQuantity = l_asset.getQuantity() + l_assetRow.getQuantityCannotSell();
            l_balanceReferenceUnit.balanceQuantity = WEB3StringTypeUtility.formatNumber(l_dblBalanceQuantity);
            // ���t����������
            l_balanceReferenceUnit.buyOrderedQuantity = WEB3StringTypeUtility.formatNumber(l_dblBuyOrderingQuantity);
            // ���t����������
            l_balanceReferenceUnit.sellOrderedQuantity = WEB3StringTypeUtility.formatNumber(l_dblSellOrderingQuantity);
            
            double l_dblSellPossQuantity = 0.0D;
            // get�~�j���ۗL����()
            double l_dblMiniBalanceQuantity = l_positionManager.getMiniStockQuantity(l_lngAccountId, l_lngSubAccountId, l_lngProductId);
            
            // ���t�\����
            // validate�~�j���d����������O���X���[�����ꍇ�́A���t�\���� = 0���Z�b�g
            // �ȊO�A�iget�~�j���ۗL����()�̖߂�l - ���t�����������j�̌v�Z����
            try
            {
                if (l_tradedProduct != null)
                {
	                l_orderManager.validateMiniStockDuplicateOrder(l_subAccount, l_tradedProduct);
	                l_dblSellPossQuantity = l_dblMiniBalanceQuantity - l_dblSellOrderingQuantity;
	                
	                // ���t�\�t���O��true���Z�b�g
	                l_balanceReferenceUnit.buyPossFlag = true;
                }
                else
                {
                    // ���t�\�t���O��false�A���t�\���ʂ�0���Z�b�g
                    l_balanceReferenceUnit.buyPossFlag = false;
	                l_dblSellPossQuantity = 0.0D;
                }
            } catch (WEB3BaseException l_wbex) {
                l_dblSellPossQuantity = 0.0D;
                
                // ���t�\�t���O��false���Z�b�g
                l_balanceReferenceUnit.buyPossFlag = false;
            }
            finally
            {
                l_balanceReferenceUnit.sellPossQuantity = WEB3StringTypeUtility.formatNumber(l_dblSellPossQuantity);
            }
            
            // ���t�\�t���O
            if (l_dblSellPossQuantity == 0)
            {
                l_balanceReferenceUnit.sellPossFlag = false;
            }
            
            // �ۗL���YID
            l_balanceReferenceUnit.id = String.valueOf(l_asset.getAssetId());
            // �����敪
            if (l_assetRow.getTaxType().equals(TaxTypeEnum.NORMAL))
            {
                l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.NORMAL;
            }
            else if (l_assetRow.getTaxType().equals(TaxTypeEnum.SPECIAL)
                || l_assetRow.getTaxType().equals(TaxTypeEnum.SPECIAL_WITHHOLD))
            {
                l_balanceReferenceUnit.taxType = WEB3TaxTypeDef.SPECIAL;
            }
            
            // �T�Z�뉿�P��
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblBookValuePrice = l_bizLogicProvider.calcEstimatedBookPrice(l_assetRow.getBookValue(), l_assetRow.getQuantityForBookValue(), 0);
            
            if (l_assetRow.getQuantityForBookValue() == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ۗL���Y.���ʁi�뉿�P���v�Z�p�j��0���ݒ肳��Ă��܂�");
            }
            
            if (l_dblBookValuePrice == 0)
            {
                l_balanceReferenceUnit.estimatedBookPrice = null;
            }
            else
            {
                l_balanceReferenceUnit.estimatedBookPrice =
                    WEB3StringTypeUtility.formatNumber(l_dblBookValuePrice);
            }
            
            // �뉿�P�����͍σt���O
            if (!l_assetRow.getInputBookValueIsNull())
            {
                l_balanceReferenceUnit.estimatedBookPriceInputFlag = true;
            }
            else
            {
                l_balanceReferenceUnit.estimatedBookPriceInputFlag = false;
            }
            
            // ������񂪎擾�ł����ꍇ
            if (l_productQuote != null)
            {
                // ����
                double l_dblCurrentPrice = l_productQuote.getQuote();
                l_balanceReferenceUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
                // �O����
                l_balanceReferenceUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_productQuote.getComparedPreviousDay());
                // �����擾����(HH:MM)
                log.debug("�����擾�敪:[" + l_productQuote.getQuoteFromDiv() + "]");
                Timestamp l_quoteTime = l_productQuote.getQuoteTime();
                if (l_quoteTime != null)
                {
                    l_balanceReferenceUnit.currentPriceTime = WEB3DateUtility.formatDate(l_quoteTime, "HH:mm");
                }
                else
                {
                    l_balanceReferenceUnit.currentPriceTime = null;
                }
                // �T�Z�]���z
                l_balanceReferenceUnit.estimatedAsset =
                    WEB3StringTypeUtility.formatNumber(this.calcEstimatedValue(l_dblCurrentPrice, l_dblBalanceQuantity));
                // �T�Z�]�����v
                if (l_dblBookValuePrice != 0)
                {
	                l_balanceReferenceUnit.estimatedlAppraisalProfitLoss =
	                    WEB3StringTypeUtility.formatNumber(this.calcProfitLoss(l_dblCurrentPrice, l_dblBookValuePrice, l_dblBalanceQuantity));
	            }
            }
            else
            {
                l_balanceReferenceUnit.currentPrice = null;
                l_balanceReferenceUnit.comparedPreviousDay = null;
                l_balanceReferenceUnit.currentPriceTime = null;
                l_balanceReferenceUnit.estimatedAsset = null;
                l_balanceReferenceUnit.estimatedlAppraisalProfitLoss = null;
            }
            
            l_lstBalanceReference.add(l_balanceReferenceUnit);
        }
        
        WEB3MstkBalanceReferenceDetailUnit[] l_balanceReferenceUnits =
            (WEB3MstkBalanceReferenceDetailUnit[])l_lstBalanceReference.toArray(new WEB3MstkBalanceReferenceDetailUnit[0]);
        
        // sort�c���Ɖ��
        this.sortBalanceReferenceDetailUnit(l_balanceReferenceUnits, l_request.sortKeys);
        
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_balanceReferenceUnits, l_intPageIndex, l_intPageSize);
        
        // ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        // �c���Ɖ�׈ꗗ
        l_response.mstkBalanceReferenceDetail =
            (WEB3MstkBalanceReferenceDetailUnit[])l_pageIndexInfo.getArrayReturned(WEB3MstkBalanceReferenceDetailUnit.class);
        // ���y�[�W��
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        // �����R�[�h��
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
        // �\���y�[�W�ԍ�
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create��������������)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�����ƂɁA���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����R�[�h == null�̏ꍇ�Anull��ԋp���ďI������B<BR>
     * <BR>
     * �Q�j�@@�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�����R�[�h != null�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * �@@����ID�w��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * �S�j�@@������C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h) �����R�[�h<BR>
     * @@return String<BR>
     * @@roseuid 41C2DDC803D3<BR>
     */
    protected String createQueryString(String l_strProductCode) 
    {
        final String STR_METHOD_NAME = "createQueryString(String)";
        log.entering(STR_METHOD_NAME);
        
        // �p�����[�^.�����R�[�h == null�̏ꍇ
        if (l_strProductCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        String l_strQueryString = " and product_id = ? ";
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^����A���������iwhere�ȉ��w��̕�����j�̃p�����[�^���X�g���쐬?
<BR>
     * ��B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����R�[�h == null�̏ꍇ�Anull��ԋp���ďI������B<BR>
     * <BR>
     * �Q�j�@@������z����쐬����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�����R�[�h != null�i�����R�[�h�w��j�̏ꍇ�A����ID��<BR>
     * �@@�@@������z��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����ID = �g���v���_�N�g�}�l�[�W��.get����(�p�����[�^.�،����, <BR>
     * �p�����[�^.�����R�[�h).����ID<BR>
     * <BR>
     * �S�j�@@������z���ԋp����B<BR>
     * <BR>
     * @@param l_institution - (�،����) �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h) - �����R�[�h<BR>
     * @@return String[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C2DDC9000A<BR>
     */
    protected String[] createQueryContainer(WEB3GentradeInstitution l_institution, String l_strProductCode) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryContainer(WEB3GentradeInstitution, String)";
        log.entering(STR_METHOD_NAME);
        
        // �p�����[�^.�����R�[�h == null�̏ꍇ
        if (l_strProductCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �g���v���_�N�g�}�l�[�W��
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
            
        EqTypeProduct l_eqtypeProduct = null;
        try
        {
            l_eqtypeProduct =
                l_productManager.getProduct(l_institution, l_strProductCode);
        } catch (NotFoundException l_ex) {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY�����銔���������擾�ł��܂���B");
        }
        
        // ���������f�[�^�R���e�i�쐬
        String[] l_strQueryDataContainer = {String.valueOf(l_eqtypeProduct.getProductId())};
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryDataContainer;
    }
    
    /**
     * (get�������)<BR>
     * <BR>
     * ���������擾����B <BR>
     * <BR>
     * �P�j������񌟍� <BR>
     * �@@�p�����[�^.�������i�[���X�g.get()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@[get()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@key�F�@@�p�����[�^.�������.����ID�ɊY����������R�[�h <BR>
     * �@@�@@�@@�@@�@@+ �p�����[�^.�������.get�s��R�[�h() <BR>
     * <BR>
     * �Q�j�������ԋp <BR>
     * �@@�P�j�̖߂�l != null�̏ꍇ�A�擾������������ԋp����B <BR>
     * <BR>
     * �R�j�������擾 <BR>
     * �@@�P�j�̖߂�l == null�̏ꍇ�A�ȉ��̎菇�Ŏ��������擾����B <BR>
     * �@@�R�|�P�j�g���v���_�N�g�}�l�[�W��.get�������()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@[get�������()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@��������F�@@�p�����[�^.������� <BR>
     * �@@�@@�@@RealType�F�@@"���A��" <BR>
     * �@@�@@�@@is�I�l�e�[�u��������read�F�@@true�i��������read����j<BR>
     * <BR>
     * �@@�R�|�Q�j�p�����[�^.�������i�[���X�g.put()���\�b�h���R�[�����A <BR>
     * �@@�@@�擾�������������i�[����B <BR>
     * <BR>
     * �@@�@@[put()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@key�F�@@�p�����[�^.�������.����ID�ɊY����������R�[�h <BR>
     * �@@�@@�@@�@@�@@+ �p�����[�^.�������.get�s��R�[�h() <BR>
     * �@@�@@�@@value�F�@@�擾����������� <BR>
     * <BR>
     * �@@�R�|�R�j�擾������������ԋp����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g<BR>
     * @@param l_tradedProduct - (�������) - ��������I�u�W�F�N�g<BR>
     * @@param l_hmProductQuote - (�������i�[���X�g) <BR>
     * �����R�[�h+�s��R�[�h���L�[�Ƃ��āA���������i�[���郊�X�g<BR>
     * @@return WEB3EquityProductQuote<BR>
     * @@roseuid 41C65E810057<BR>
     */
    protected WEB3EquityProductQuote getEquityProductQuote(WEB3GentradeSubAccount l_subAccount, WEB3EquityTradedProduct l_tradedProduct, HashMap l_hmProductQuote)
    {
        final String STR_METHOD_NAME = "getEquityProductQuote(WEB3GentradeSubAccount, WEB3EquityProduct, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // ������񌟍�
        String l_strKey = l_tradedProduct.getProductCode() + l_tradedProduct.getMarketCode();
        WEB3EquityProductQuote l_productQuote =
            (WEB3EquityProductQuote)l_hmProductQuote.get(l_strKey);
        
        // ������񂪎擾�ł����ꍇ�́A�ԋp���ďI��
        if (l_productQuote != null)
        {
            log.debug(STR_METHOD_NAME + " �p�����[�^.�������i�[���X�g���玞�����擾");
            log.exiting(STR_METHOD_NAME);
            return l_productQuote;
        }
        else // ������񂪎擾�ł��Ȃ������ꍇ
        {
            log.debug(STR_METHOD_NAME + " �����T�[�o�[���玞�����擾");
            try
            {
	            // ���������擾����B
	            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
	            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                    
	            l_productQuote = l_productManager.getProductQuote(l_tradedProduct, RealType.REAL, true);
	            
	            // �p�����[�^.�������i�[���X�g�Ɏ擾������������ǉ�����
	            l_hmProductQuote.put(l_strKey, l_productQuote);
            } catch (WEB3BaseException l_wbex) {
                l_productQuote = null;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }
    
    /**
     * (calc�]���z)<BR>
     *<BR>
     * �]���z���Z�o���A�ԋp����B<BR>
     * ���萔���͊܂܂Ȃ��B<BR>
     * <BR>
     * �p�����[�^.�]���P�� * �p�����[�^.���ʂ̌��ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_dblPrice - (�]���P��) �]���P��<BR>
     * @@param l_dblQuantity - (����) ����<BR>
     * @@return double<BR>
     * @@roseuid 41C65E810067<BR>
     */
    protected double calcEstimatedValue(double l_dblPrice, double l_dblQuantity) 
    {
        final String STR_METHOD_NAME = "calcEstimatedValue(double, double)";
        log.entering(STR_METHOD_NAME);
        
        // �]���z = �p�����[�^.�]���P�� * �p�����[�^.����
        double l_dblResult = l_dblPrice * l_dblQuantity;
        
        log.exiting(STR_METHOD_NAME);
        return l_dblResult;
    }
    
    /**
     * (calc�]�����v)<BR>
     * <BR>
     * �]�����v���Z�o���A�ԋp����B<BR>
     * ���萔���͊܂܂Ȃ��B<BR>
     * <BR>
     * (�p�����[�^.�]���P�� - �p�����[�^.�뉿) * �p�����[�^.����<BR>
     * �̌��ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_dblPrice - (�]���P��) �]���P��<BR>
     * @@param l_dblBookValue - (�뉿) �뉿<BR>
     * @@param l_dblQuantity - (����) ����<BR>
     * @@return double<BR>
     * @@roseuid 41C65E810096<BR>
     */
    protected double calcProfitLoss(double l_dblPrice, double l_dblBookValue, double l_dblQuantity) 
    {
        final String STR_METHOD_NAME = "calcProfitLoss(double, double, double)";
        log.entering(STR_METHOD_NAME);
        
        // �]�����v = (�p�����[�^.�]���P�� - �p�����[�^.�뉿) * �p�����[�^.����
        double l_dblResult = (l_dblPrice - l_dblBookValue) * l_dblQuantity;
        
        log.exiting(STR_METHOD_NAME);
        return l_dblResult;
    }
    
    /**
     * (sort�c���Ɖ��)<BR>
     * <BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Ďc���Ɖ�ׂ̃\�[�g���s���B<BR>
     * <BR>
     * �P�j�p�����[�^.�c���Ɖ�� == null�̏ꍇ�A<BR>
     * �@@�������I������B<BR>
     * <BR>
     * �Q�jArrayList�𐶐�����B <BR>
     * <BR>
     * �R�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�Comparator�𐶐����A<BR>
     * �@@�@@�@@ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�@@�@@�����~�j�����c���Ɖ�Comparator�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~��<BR>
     * �@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[����<BR>
     * <BR>
     * �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B<BR>
     * <BR>
     * �S�j�����敪���\�[�g����Comparator�̍쐬<BR>
     * �@@�����~�j�����c���Ɖ�Comparator���쐬���A<BR>
     * �@@ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@orderBy�F�@@"����"<BR>
     * �@@�@@��r���ځF�@@�����~�j�����c���Ɖ��.�����敪<BR>
     * <BR>
     * �T�jWEB3ArraysUtility.sort()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@[sort()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@obj�F�@@�p�����[�^.�c���Ɖ��<BR>
     * �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l<BR>
     * <BR>
     * @@param l_balanceReferenceDetailUnit - (�c���Ɖ��) �����~�j�����c���Ɖ�ׂ̔z��<BR>
     * @@param l_sortKey - (�\�[�g�L�[) �����~�j�����\�[�g�L�[�̔z��<BR>
     * @@roseuid 41C664290123<BR>
     */
    protected void sortBalanceReferenceDetailUnit(WEB3MstkBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnit, WEB3MstkSortKey[] l_sortKey) 
    {
        final String STR_METHOD_NAME = "sortBalanceReferenceDetailUnit(WEB3MstkBalanceReferenceDetailUnit, WEB3MstkSortKey)";
        log.entering(STR_METHOD_NAME);
        
        // �p�����[�^.�c���Ɖ�� == null�̏ꍇ�A�����I��
        if (l_balanceReferenceDetailUnit == null || l_balanceReferenceDetailUnit.length == 0)
        {
            return;
        }
        
        ArrayList l_lstComparators = new ArrayList();
        
        // �p�����[�^.�\�[�g�L�[�̗v�f����Loop����
        WEB3MstkBalanceReferenceComparator l_comparator = null;
        String l_strOrderBy = null;
        String l_strKeyItem = null;
        for (int i = 0; i < l_sortKey.length; i++)
        {
            l_strOrderBy = l_sortKey[i].ascDesc;
            l_strKeyItem = l_sortKey[i].keyItem;
            log.debug("��" + (i + 1) + "�\�[�g�L�[�F" + l_strKeyItem + " " + l_strOrderBy);
            
            // ���������c���Ɖ�Comparator�𐶐�
            l_comparator = new WEB3MstkBalanceReferenceComparator(l_strOrderBy, l_strKeyItem);
            // ArrayList��Comparator��ǉ�
            l_lstComparators.add(l_comparator);
        }
            
        // �\�[�g
        WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit,
            (WEB3MstkBalanceReferenceComparator[])l_lstComparators.toArray(new WEB3MstkBalanceReferenceComparator[0]));
        log.exiting(STR_METHOD_NAME);
    }
}
@
