head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellSwtListInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����抷�ꗗ�Ɖ�T�[�r�XImpl(WEB3MutualSellSwtListInquiryServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ��O�� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
                   2004/12/13 ������ (���u) �c�Ή�
                   2005/10/23 ���� (���u) �t�B�f���e�B�Ή�   
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.define.WEB3RemarkDivDef;
import webbroker3.mf.message.WEB3MutualAppraisalProfitLossComparator;
import webbroker3.mf.message.WEB3MutualMarketValueComparator;
import webbroker3.mf.message.WEB3MutualSellSwitchingProductGroup;
import webbroker3.mf.message.WEB3MutualSellSwtListRequest;
import webbroker3.mf.message.WEB3MutualSellSwtListResponse;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mf.message.WEB3MutualOrderCloseTimeComparator;
import webbroker3.mf.message.WEB3MutualTaxTypeComparator;
import webbroker3.mf.service.delegate.WEB3MutualSellSwtListInquiryService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�����抷�ꗗ�Ɖ�T�[�r�XImpl
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualSellSwtListInquiryServiceImpl extends 
    WEB3MutualClientRequestService implements WEB3MutualSellSwtListInquiryService 
{    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellSwtListInquiryServiceImpl.class);
        
    /**
     * �����M�����抷�ꗗ�Ɖ�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���M�j���抷�ꗗ�Ɖ�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2ED11023F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3MutualSellSwtListRequest l_sellSwtListRequest = null;
        
        if (l_request instanceof WEB3MutualSellSwtListRequest)
        {
            l_sellSwtListRequest = (WEB3MutualSellSwtListRequest) l_request;
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        //1.1�j���͓��e�`�F�b�N 
        l_sellSwtListRequest.validate();

        // --�ڋq�ʎ����~�����`�F�b�N 
        //1.2)�@@this.get�⏕����( )���R�[�����A�⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3) FinApp�N���X��getCommonOrderValidator()
        //  �����`�F�b�N�I�u�W�F�N�g���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);     
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4) get���M�������i�j       
        Timestamp l_datBizDate = 
            new Timestamp(
                    WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate().getTime());
            
        //1.5) �����`�F�b�N.validate����\�ڋq( )���R�[������O���Ԃ��ꂽ�ꍇ�A 
        //    ��O���X���[����B 
        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        OrderValidationResult l_validationResult =  
            l_orderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�����~�ڋq�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00275,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����~�ڋq�G���[");
        }
                
        // --��t���ԃ`�F�b�N�E�V�X�e�������~�`�F�b�N
        // 1.6) validate������t�\
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.7) �ۗL���Y�����ꗗ�������� 
        WEB3MutualFundPositionManager l_mfPositionManager =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        log.debug("l_subAccount.getAccountId() = " + l_subAccount.getAccountId());   
        List l_lisAsset = new Vector();
        l_lisAsset = l_mfPositionManager.getAssets(
            l_subAccount, null, ProductTypeEnum.MUTUAL_FUND);
        log.debug("l_lisAsset.size() = "+ l_lisAsset.size());
        
        // --���ׂ̍쐬
        //1.8) getAssets()�̖߂�l�̌������J��Ԃ��A
        //    ���M���抷�����ꗗ�s�I�u�W�F�N�g�̔z��̍쐬
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        WEB3MutualFundProduct l_web3MfProduct = null;
                        
        //�ȉ��̏�����1.7)�̏����Ŏ擾�����ۗL�����̌������s���B
        List l_lisSellSwtProductGroup = new Vector();
        for (int i = 0; i < l_lisAsset.size(); i++)
        {
            try
            {
                MutualFundAsset l_mfAsset = (MutualFundAsset) l_lisAsset.get(i);
                AssetRow l_assetRow = (AssetRow)l_mfAsset.getDataSourceObject();
                // ���M�����̎擾
                // ���M�g�������}�l�[�W��.getProduct( )���R�[�����A 
                // �擾���������I�u�W�F�N�g.getDataSourceObject( )���擾
                log.debug("l_assetRow.getProductId() = " + l_assetRow.getProductId());
                //NotFoundException
                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow) l_mfProductManager.getProduct(
                        l_assetRow.getProductId()).getDataSourceObject();
                        
                //�g�����M�����}�l�[�W��.to���M����( )���R�[������        
                l_web3MfProduct = (WEB3MutualFundProduct)
                    l_mfProductManager.toProduct(l_mutualFundProductRow);
                                               
                //������ԃR���e�L�X�g�X�V
                WEB3MutualFundTradingTimeManagement.resetProductCode(
                    l_web3MfProduct.getProductCode());
                WEB3MutualFundTradingTimeManagement.setTimestamp();
                
                log.debug("l_web3MfProduct.getProductCode() = " + l_web3MfProduct.getProductCode());
                //�\���������ׂ̍쐬
                WEB3MutualSellSwitchingProductGroup l_sellSwtProductGroup =
                    new WEB3MutualSellSwitchingProductGroup();
                
                //1.8.7) ���v���p�e�B�E�Z�b�g��       
                l_sellSwtProductGroup.id = l_assetRow.getAssetId() + "";
                l_sellSwtProductGroup.mutualProductCode = l_web3MfProduct.getProductCode();
                log.debug("��������.mutualProductCode = " + l_web3MfProduct.getProductCode());
                l_sellSwtProductGroup.mutualProductName = 
                l_web3MfProduct.getMutualProductName();
                
                //�����敪
                TaxTypeEnum l_taxType = null;
                l_taxType = l_assetRow.getTaxType();
                if (TaxTypeEnum.NORMAL.equals(l_taxType))
                {
                    l_sellSwtProductGroup.taxType = WEB3MFAccountDivDef.NORMAL;
                }
                else if (TaxTypeEnum.SPECIAL.equals(l_taxType) ||
                            TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
                {
                    l_sellSwtProductGroup.taxType = WEB3MFAccountDivDef.SPECIAL;
                }
                log.debug("��������.�����敪 = " + l_sellSwtProductGroup.taxType);
                
                //���\���� = �g�����M�|�W�V�����}�l�[�W��.calc���\�c������( ) 
                // 1.8.8.1.1) calc���\�c������(�⏕����, �g�����M����, ���YID)
                // �mcalc���\�c�������ɓn���p�����^�n
                // �@@ �⏕�����F �擾�����⏕�����I�u�W�F�N�g 
                //�@@�@@�g�����M�����F �擾�����g�����M�����I�u�W�F�N�g 
                //�@@�@@���YID�F�@@�擾�����ۗL���Y�e�[�u��Params.get���YID
                
                double l_dblSellPossiblePositionQty = l_mfPositionManager.calcSellPossiblePositionQty(
                    l_subAccount, l_web3MfProduct, l_mfAsset.getAssetId() + "");
                log.debug("�g�����M�|�W�V�����}�l�[�W��.calc���\�c������( ) = " + 
                    l_dblSellPossiblePositionQty);
                
                //1.8.8.1.2)�g�����M�|�W�V�����}�l�[�W��.calc���\�c������( )�̖߂�l��0�̏ꍇ
                if ( l_dblSellPossiblePositionQty == 0)
                {
                    //1.8.8.1.2.1) ���g�S����񒆁h�̃Z�b�g��
					//���M���抷�����ꗗ�s�I�u�W�F�N�g�̈ȉ��̃v���p�e�B�Ɂh�S����񒆁h���Z�b�g����B 
					//���\�敪 
					//����\�敪 
					//�抷�\�敪
                    l_sellSwtProductGroup.sellPossType =WEB3RemarkDivDef.All_SELLING;
                    l_sellSwtProductGroup.buyPossType =WEB3RemarkDivDef.All_SELLING;
                    l_sellSwtProductGroup.switchingPossType =WEB3RemarkDivDef.All_SELLING;
                }
                
                l_sellSwtProductGroup.sellableQty = 
                    WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);
                
                log.debug("��������.���\���� = " + l_sellSwtProductGroup.sellableQty);
                
                //��񒆊T�Z���� = �g�����M�|�W�V�����}�l�[�W��.calc��񒆊T�Z����( )    
                l_sellSwtProductGroup.sellingEstimatedQty = 
                    WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);
                
                log.debug("��������.��񒆊T�Z���� = " + l_sellSwtProductGroup.sellingEstimatedQty);
                
                //�Q�l����z�ʉ݃R�[�h= ���M����.get�ʉ݃R�[�h( )
                l_sellSwtProductGroup.constantValueCurrencyCode = 
                    l_web3MfProduct.getCurrencyCode();
                
                log.debug("��������.�Q�l����z�ʉ݃R�[�h = " + l_sellSwtProductGroup.constantValueCurrencyCode);
                
                //�Q�l����z = ���M����.get��񉿊z( )
                l_sellSwtProductGroup.constantValue = 
                    WEB3StringTypeUtility.formatNumber(l_web3MfProduct.getSellValue());
                
                log.debug("��������.�Q�l����z = " + l_sellSwtProductGroup.constantValue);
                
                //����z�K�p��= ���M����.get����z�K�p��( ) 
                l_sellSwtProductGroup.constantValueAppDate = WEB3DateUtility.toDay(
                    l_web3MfProduct.getConstantValueAppDate());
                
                log.debug("��������.����z�K�p�� = " + l_sellSwtProductGroup.constantValueAppDate);
                
                //�ʌ��{ = �ۗL���Y�e�[�u��Params.getBookValue( )
                l_sellSwtProductGroup.indivPrincipal = 
                    WEB3StringTypeUtility.formatNumber(l_assetRow.getBookValue());
                
                log.debug("��������.�ʌ��{ = " + l_sellSwtProductGroup.indivPrincipal);
                
                log.debug("���M����.get���M�^�C�v( )= " + l_web3MfProduct.getMutualFundType());

                //�]���z = �g�����M�|�W�V�����}�l�[�W��.calc�]���z( )
                l_sellSwtProductGroup.marketValue = 
                    WEB3StringTypeUtility.formatNumber(
                        l_mfPositionManager.calcMarketValue(
                        l_subAccount, l_web3MfProduct, l_mfAsset.getAssetId() + ""));
                            
                log.debug("��������.�]���z = " + l_sellSwtProductGroup.marketValue);
                //���M����.get���M�^�C�v( )= "���O"�ł���A����L�����敪��"���"�ƂȂ�ꍇ 
                //�ȊO�A�ȉ����s���B
                if ((MutualFundTypeEnum.FOREIGN.equals(l_web3MfProduct.getMutualFundType()) &&
                    WEB3MFAccountDivDef.NORMAL.equals(l_sellSwtProductGroup.taxType)))
                {
                    //(�����ɍ��v��������̕]�����v�ɂ́Anull���Z�b�g)
                    l_sellSwtProductGroup.appraisalProfitLoss = null;
                }
                else
                {                  
                    //�]�����v = �g�����M�|�W�V�����}�l�[�W��.calc�]�����v( ) 
                    l_sellSwtProductGroup.appraisalProfitLoss = 
                        WEB3StringTypeUtility.formatNumber(
                            l_mfPositionManager.calcAppraisalProfitLoss(
                            l_subAccount, l_web3MfProduct, l_mfAsset.getAssetId() + ""));
                            
                    log.debug("��������.�]�����v = " + l_sellSwtProductGroup.appraisalProfitLoss);
                }

                //��񎞒P�ʌ��� = ���M����.get�P�ʌ���(���)( ) 
                l_sellSwtProductGroup.sellUnitQty = l_web3MfProduct.getSellUnitQty() + "";
                log.debug("��������.��񎞒P�ʌ��� = " + l_sellSwtProductGroup.sellUnitQty);
                
                //��񎞍Œ���� = ���M����.get�Œ����(���)( )
                l_sellSwtProductGroup.sellMinQty = l_web3MfProduct.getSellMinQty() + "";
                log.debug("��������.��񎞍Œ���� = " + l_sellSwtProductGroup.sellMinQty);
                
                //��񎞒P�ʋ��z = ���M����.get�P�ʋ��z(���)( ) 
                l_sellSwtProductGroup.sellUnitAmt = l_web3MfProduct.getSellUnitAmt() + "";
                log.debug("��������.��񎞒P�ʋ��z = " + l_sellSwtProductGroup.sellUnitAmt);
                
                //��񎞍Œ���z = ���M����.get�Œ���z(���)( ) 
                l_sellSwtProductGroup.sellMinAmt = l_web3MfProduct.getSellMinAmt() + "";
                log.debug("��������.��񎞍Œ���z = " + l_sellSwtProductGroup.sellMinAmt);
                
                //�抷���P�ʌ��� = ���M����.get�P�ʌ���(�抷)( )
                l_sellSwtProductGroup.switchingUnitQty = 
                    l_web3MfProduct.getSwitchingUnitQty() + "";
                log.debug("��������.�抷���P�ʌ��� = " + l_sellSwtProductGroup.switchingUnitQty);
                
                //�抷���Œ���� = ���M����.get�Œ����(�抷)( )                
                l_sellSwtProductGroup.switchingMinQty = 
                    l_web3MfProduct.getSwitchingMinQty() + "";
                log.debug("��������.�抷���Œ���� = " + l_sellSwtProductGroup.switchingMinQty);
                
                //�抷���P�ʋ��z = ���M����.get�P�ʋ��z(�抷)( )
                l_sellSwtProductGroup.switchingUnitAmt = 
                    l_web3MfProduct.getSwitchingUnitAmt() + "";
                log.debug("��������.�抷���P�ʋ��z = " + l_sellSwtProductGroup.switchingUnitAmt);
                
                //�抷���Œ���z = ���M����.get�Œ���z(�抷)( ) 
                l_sellSwtProductGroup.switchingMinAmt = 
                    l_web3MfProduct.getSwitchingMinAmt() + "";
                log.debug("��������.�抷���Œ���z = " + l_sellSwtProductGroup.switchingMinAmt);
                
                //������t���؎��� = ���M������ԊǗ�.get������t���؎���( )���R�[�����A 
                //�߂��ꂽ�l�̂P�b��̎���"HHMMSS"��"HH:MM"�ɕҏW���ăZ�b�g����B
                String l_strOrderCloseTime = 
                    WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
                Date l_datOrderCloseTime = 
                    WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
                l_strOrderCloseTime = 
                    WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss"); 
                l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
                    + WEB3GentradeTimeDef.STR_COLON
                    + l_strOrderCloseTime.substring(2, 4);
                l_sellSwtProductGroup.orderCloseTime = 
                    l_strOrderCloseTime;
                
                //1.8.8) �����E����E�抷�\�敪�̃Z�b�g��  
                //-start--------------------------------------------- 
                //a6) �ȊO�̏ꍇnull���Z�b�g
                l_sellSwtProductGroup.sellPossType = null;
                l_sellSwtProductGroup.buyPossType = null;
                l_sellSwtProductGroup.switchingPossType = null;
                
                //1.8.8.2) �ً}��~���`�F�b�N 
                WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
                    (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
                try
                {
                    //1.8.8.2.1) validate�ً}��~(�g�����M����, String)
                    l_validationsCheck.validateEmergencyStop(
                        l_web3MfProduct, WEB3ProcessDivDef.SELL);
                }
                catch (WEB3BaseException l_ex)
                {
                    //1.8.8.2.2) ��validate�ً}��~()�����O���X���[���ꂽ�ꍇ��            
                    //1.8.8.2.2.1) ���g�ً}��~���h�̃Z�b�g��
                    l_sellSwtProductGroup.sellPossType = WEB3RemarkDivDef.EMERGENCY_STOP; 
                    //����\�敪��"�ً}��~��"���Z�b�g����
                    l_sellSwtProductGroup.buyPossType = WEB3RemarkDivDef.EMERGENCY_STOP;                               
                }
                //1.8.8.2.3) validate�ً}��~(�g�����M����, String)
                try
                {
                    l_validationsCheck.validateEmergencyStop(
                        l_web3MfProduct, WEB3ProcessDivDef.SWITCHING);
                }
                catch (WEB3BaseException l_ex)
                {
                    //1.8.8.2.4)��validate�ً}��~()�����O���X���[���ꂽ�ꍇ��
                    //1.8.8.2.4.1)���g�ً}��~���h�̃Z�b�g��
                    l_sellSwtProductGroup.switchingPossType = WEB3RemarkDivDef.EMERGENCY_STOP;                
                }
                
                //1.8.8.3) ��������ԊO�`�F�b�N��
                try
                {                    
                    //1.8.8.3.1) ���M������ԊǗ�.validate������t�\( )���R�[����
                    WEB3MutualFundTradingTimeManagement.validateOrderAccept();
                }
                catch (WEB3BaseException l_ex)
                {
                    //1.8.8.3.2) ��validate������t�\()�����O���X���[���ꂽ�ꍇ��
                    //1.8.8.3.2.1) ���g������ԊO�h�̃Z�b�g��
                    //���\�敪 
                    l_sellSwtProductGroup.sellPossType = 
                        WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
                    //����\�敪
                    l_sellSwtProductGroup.buyPossType = 
                        WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP; 
                    //�抷�\�敪
                    l_sellSwtProductGroup.switchingPossType = 
                        WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;                       
                }
                //1.8.8.4) ������s�`�F�b�N��
                
                //1.8.8.4.1) get���M������( )
                Date l_datOrderBizDate =
                    WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
                
                //1.8.8.4.2) is���抷�\(Date)
                boolean l_blnSellSwtPossible = false;   //���抷�\
                
                l_blnSellSwtPossible = 
                    l_web3MfProduct.isSellSwitchingPossible(l_datOrderBizDate);
                
                //1.8.8.4.4)��is���抷�\()��false �̏ꍇ��
                if (!l_blnSellSwtPossible)
                {
                    //1.8.8.4.4.1)���g����s�h�̃Z�b�g��
                    l_sellSwtProductGroup.sellPossType = 
                        WEB3RemarkDivDef.HANDLING_DISABLE;
                    l_sellSwtProductGroup.buyPossType = 
                        WEB3RemarkDivDef.HANDLING_DISABLE;
                    l_sellSwtProductGroup.switchingPossType = 
                        WEB3RemarkDivDef.HANDLING_DISABLE;
                }
                //1.8.8.4.3) ��is���抷�\()��true �̏ꍇ��    
                //1.8.8.4.3.1) is���搿���\(Date, SubAccount, �g�����M����)
                boolean l_blnBuyingRequestPossible = false; //���搿���\
                
                l_blnBuyingRequestPossible = l_validationsCheck.isBuyingRequestPossible(
                    WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(),
                    l_subAccount,
                    l_web3MfProduct);
                    
                log.debug("���M����.is���抷�\( ) = " + l_blnSellSwtPossible);
                log.debug("���M����.is���搿���\( ) = " + l_blnBuyingRequestPossible);
                
                //1.8.8.4.3.2)��is���搿���\()��false �̏ꍇ��
                if (l_blnSellSwtPossible && !l_blnBuyingRequestPossible)                
                {
                    //1.8.8.4.3.2.1) ���g����s�h�̃Z�b�g��
                    l_sellSwtProductGroup.buyPossType = 
                        WEB3RemarkDivDef.HANDLING_DISABLE;
                }
                
                //1.8.8.4.5) is�抷�\( )
                //1.8.8.4.6)   ��is�抷�\()��false �̏ꍇ��
                // �抷�\�敪��"�戵�s��"���Z�b�g����B
                log.debug("���M����.is�抷�\(�@@) = " + l_web3MfProduct.isSwitchingAble());
                if(!l_web3MfProduct.isSwitchingAble())
                {
                    //1.8.8.4.6.1) ���g�戵�s�h�̃Z�b�g��
                    l_sellSwtProductGroup.switchingPossType = 
                        WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
                }
                
                //1.8.8.5)���戵�s�`�F�b�N��
                //1.8.8.5.1) is�V�X�e���戵( )
                //1.8.8.5.2) ��is�V�X�e���戵()��false �̏ꍇ��
                log.debug("���M����.is�V�X�e���戵( ) = " + l_web3MfProduct.isSystemHandling());
                if (!l_web3MfProduct.isSystemHandling())
                {
                    //1.8.8.5.2.1) ���g�戵�s�h�̃Z�b�g��
                    l_sellSwtProductGroup.sellPossType = 
                        WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
                    l_sellSwtProductGroup.buyPossType = 
                        WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
                    l_sellSwtProductGroup.switchingPossType = 
                        WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
                }
                log.debug("��������.���\�敪 = " + l_sellSwtProductGroup.sellPossType);
                log.debug("��������.����\�敪 = " + l_sellSwtProductGroup.buyPossType);
                log.debug("��������.�抷�\�敪 = " + l_sellSwtProductGroup.switchingPossType);
                l_lisSellSwtProductGroup.add(l_sellSwtProductGroup);
                //-end-----------------------------------------------                
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }   //for end
        WEB3MutualSellSwitchingProductGroup[] l_sellSwtProductGroups = 
            new WEB3MutualSellSwitchingProductGroup[l_lisSellSwtProductGroup.size()];
        l_lisSellSwtProductGroup.toArray(l_sellSwtProductGroups);
        
        //1.9) �\�[�g����
        //�@@this.sort���抷�ꗗ����( )
        this.sortSellSwitchingListDetails(
            l_sellSwtProductGroups, l_sellSwtListRequest.sortKeys);


        //1.10)���M���抷�ꗗ�Ɖ�N�G�X�g.create���X�|���X( ) 
        //     ���M���抷�ꗗ�Ɖ�X�|���X�𐶐� 
        WEB3MutualSellSwtListResponse l_sellSwtListresponse =
            (WEB3MutualSellSwtListResponse) l_sellSwtListRequest.createResponse();
          
        //1.11) �y�[�W���O����  
        //���M���抷�ꗗ�Ɖ�X�|���X�̈ȉ��̍��ڂ�ݒ肷�� 
        /*-start--------------------------------------------- 
         * �����M���抷�ꗗ�Ɖ�X�|���X.���y�[�W��: 
         * �U)�Ŋm�肵�����ׂ̗v�f�������M���抷�ꗗ�Ɖ�N�G�X�g.�y�[�W���\���s�� 
         * ���]�肪�o��ꍇ�́A�{�P�����l��ݒ�B
         * ���U)�Ŋm�肵�����ׂ̗v�f����0(�\���Ώۃf�[�^�Ȃ�)�̏ꍇ�A0���Z�b�g�B
         * -end----------------------------------------------- 
         */
         
        //�����M���抷�ꗗ�Ɖ�X�|���X.���y�[�W��: 
        ///*-start--------------------------------------------- 
        int l_intRequestPageSize = 0;  //�y�[�W���\���s��
        
        l_intRequestPageSize = Integer.parseInt(l_sellSwtListRequest.pageSize);
        
        //���U)�Ŋm�肵�����ׂ̗v�f����0(�\���Ώۃf�[�^�Ȃ�)�̏ꍇ�A0���Z�b�g
        if (l_sellSwtProductGroups.length == 0)
        {
            l_sellSwtListresponse.totalPages = "0";
        }
        //���U)�Ŋm�肵�����ׂ̗v�f�������M���抷�ꗗ�Ɖ�N�G�X�g.�y�[�W���\���s��
        else if (l_sellSwtProductGroups.length % l_intRequestPageSize == 0) 
        {
            l_sellSwtListresponse.totalPages = 
                l_sellSwtProductGroups.length / l_intRequestPageSize + "";            
        }
        //���]�肪�o��ꍇ�́A�{�P�����l��ݒ�
        else
        {
            l_sellSwtListresponse.totalPages = 
                l_sellSwtProductGroups.length / l_intRequestPageSize + 1 + "";
        }
        //-end----------------------------------------------- 
        log.debug("���M���抷�ꗗ�Ɖ�X�|���X.���y�[�W��: " + l_sellSwtListresponse.totalPages);
        
        //�����M���抷�ꗗ�Ɖ�X�|���X.�����R�[�h��:�@@�U)�Ŋm�肵�����ׂ̗v�f�� 
        l_sellSwtListresponse.totalRecords = l_sellSwtProductGroups.length + "";
        log.debug("���M���抷�ꗗ�Ɖ�X�|���X.�����R�[�h��: " + l_sellSwtListresponse.totalRecords);
        
        //�����M���抷�ꗗ�Ɖ�X�|���X.�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩): 
        /*-start--------------------------------------------- 
         * �U)�Ŋm�肵�����ׂ̗v�f�� > (���M���抷�ꗗ�Ɖ�N�G�X�g.�y�[�W���\���s���~
         * (���M���抷�ꗗ�Ɖ�N�G�X�g.�v���y�[�W�ԍ� - 1) )�ł���΁A
         * ���M���抷�ꗗ�Ɖ�N�G�X�g.�v���y�[�W�ԍ��B 
         * ��L�ȊO�̏ꍇ�́A���M���抷�ꗗ�Ɖ�X�|���X.���y�[�W�� �����̂܂ܐݒ�B 
         * ���������ʂ�PR�w����̗v���y�[�W�ԍ��ɖ����Ȃ��ꍇ�́A�ŏI�y�[�W�ɊY������f�[�^�� 
         * ���M���抷�ꗗ�Ɖ�X�|���X�ɐݒ肷��B 
         * -end----------------------------------------------- 
         */

        //���M���抷�ꗗ�Ɖ�N�G�X�g.�v���y�[�W�ԍ�
        int l_intPageIndex = Integer.parseInt(l_sellSwtListRequest.pageIndex);
        
        if (l_sellSwtProductGroups.length > (l_intRequestPageSize * l_intPageIndex - 1))
        {
            l_sellSwtListresponse.pageIndex = l_sellSwtListRequest.pageIndex;
        }
        else
        {
            l_sellSwtListresponse.pageIndex = l_sellSwtListresponse.totalPages;
        }
        log.debug("���M���抷�ꗗ�Ɖ�X�|���X.�v���y�[�W�ԍ�: " + 
            l_sellSwtListresponse.pageIndex);
        
        // �ݒ��A���M���抷�ꗗ�Ɖ�X�|���X.���y�[�W�� = 0 �̏ꍇ
        // ���X�|���X�f�[�^.���抷�����ꗗ(���M���抷�����ꗗ�s[ ])�� 
        // null�������Ƃ��A���X�|���X��return����B
        if (Integer.parseInt(l_sellSwtListresponse.totalPages) == 0)
        {
            l_sellSwtListresponse.sellSwitchingProductGroups = null;
            return l_sellSwtListresponse;
        }
        
        /* (���M���抷�ꗗ�Ɖ�N�G�X�g.�y�[�W���\���s���~( 
         * ���M���抷�ꗗ�Ɖ�X�|���X.�\���y�[�W�ԍ� - 1)�����A�U)�Ŋm�肵��
         * ���M���抷�ꗗ�Ɖ�X�|���X���׃f�[�^�ꗗ�̗v�f���X�L�b�v����B
         */
        int l_intBeginRecordNumber =  
            l_intRequestPageSize * (Integer.parseInt(l_sellSwtListresponse.pageIndex) - 1);
        int l_intEndRecordNumber =  
            l_intRequestPageSize * Integer.parseInt(l_sellSwtListresponse.pageIndex);
        if (l_intEndRecordNumber > l_sellSwtProductGroups.length)
        {
            l_intEndRecordNumber = l_sellSwtProductGroups.length;
        }

       /* ��L�Ō��肵�����M���抷�ꗗ�Ɖ�X�|���X���׃f�[�^�ꗗ�̗v�f�ԍ��`
        * (���M���抷�ꗗ�Ɖ�X�|���X���׃f�[�^�ꗗ�̗v�f�ԍ��{���M���抷�ꗗ�Ɖ� 
        *  ���N�G�X�g.�y�[�W���\���s��)�܂łɊY�����铊�M���抷�ꗗ�Ɖ�X�|���X
        * ���׃f�[�^���A���M���抷�ꗗ�Ɖ�X�|���X�f�[�^.���抷�����ꗗ�Ƃ��� 
        *  �Z�b�g����B 
        */
        List l_lisProducts = new Vector(); 
        for (int i = l_intBeginRecordNumber; i < l_intEndRecordNumber; i++)
        {
            l_lisProducts.add(l_sellSwtProductGroups[i]);
        }
        WEB3MutualSellSwitchingProductGroup[] l_products = 
            new WEB3MutualSellSwitchingProductGroup[l_lisProducts.size()];
        l_lisProducts.toArray(l_products);
        
        l_sellSwtListresponse.sellSwitchingProductGroups = l_products;
       
        return l_sellSwtListresponse;
    }
    
    /**
     * (sort���抷�ꗗ����)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A<BR>
     * ���~���Ɋ�Â��ē��M���抷�������ׂ̃\�[�g���s���B <BR>
     * <BR>
     * �P)�@@ArrayList���쐬 <BR>
     * <BR>
     * �Q)�@@����:�\�[�g�L�[�̔z�񐔕�Loop���� <BR>
     * <BR>
     * �@@�Q�|�P)�@@����:�\�[�g�L�[.�L�[���ڂ��擾 <BR>
     * <BR>
     * �@@�Q�|�Q)�@@����:�\�[�g�L�[.����/�~�����擾 <BR>
     * <BR>
     * �@@�Q�|�R)�@@�L�[���ڂɂ�镪�򏈗� <BR>
     * �@@�@@�L�[���ڂ��]���z�ł������ꍇ�A�]���zComparator�𐶐� <BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@�L�[���ڂ������敪�ł������ꍇ�A�����敪Comparator�𐶐� <BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@�L�[���ڂ��]�����v�ł������ꍇ�A�]�����vComparator�𐶐� <BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@�L�[���ڂ�������t���؎��Ԃł������ꍇ�A<BR>
     * ������t���؎���Comparator�𐶐� <BR>
     * �@@�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�Q�|�S)�@@�Q�|�R)�ɂč쐬����Comparator��ArrayList�ɒǉ� <BR>
     * <BR>
     * �R)�@@ArrayList����Comparator�̔z����쐬 <BR>
     * <BR>
     * �S)�@@Comparator�̔z�񏇂̃\�[�g���� <BR>
     * (web3-common)WEB3ArraysUtility.sort(����:���M���抷�������ׁA<BR>
     * Comparator[]) <BR>
     * <BR>
     * �T)�@@�\�[�g���ꂽ���M�����Ɖ�ׂ̔z���ԋp<BR>
     * <BR>
     * @@param l_sellSwitchingProductDetails - ���M���抷��������
     * 
     * @@param l_sortKey - �\�[�g�L�[
     * @@roseuid 40BDAA350378
     */
    public void sortSellSwitchingListDetails(
        WEB3MutualSellSwitchingProductGroup[] l_sellSwitchingProductDetails, 
        WEB3MutualSortKey[] l_sortKey)
    {
        String STR_METHOD_NAME = "sortSellSwitchingListDetails()";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKey == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P)ArrayList���쐬
        ArrayList l_lisComparator = new ArrayList();
        
        for (int i = 0; i < l_sortKey.length; i++)
        {
            Comparator l_comparator = null;
            
            //�Q�|�P)�@@����:�\�[�g�L�[.�L�[���ڂ��擾
            String l_strKeyItem = l_sortKey[i].keyItem;
            //�Q�|�Q)�@@����:�\�[�g�L�[.����/�~�����擾
            String l_strAscDesc = l_sortKey[i].ascDesc;      
                  
            //�Q�|�R)�@@�L�[���ڂɂ�镪�򏈗�
            //�L�[���ڂ��]���z�ł������ꍇ�A�]���zComparator�𐶐� 
            if (WEB3MFSortkeyItemDef.MARKET_VALUE.equals(l_strKeyItem))
            {
                WEB3MutualMarketValueComparator l_marketValueComparator = 
                    new WEB3MutualMarketValueComparator(l_strAscDesc);
                l_comparator = l_marketValueComparator;
            }
            //�L�[���ڂ������敪�ł������ꍇ�A�����敪Comparator�𐶐�
            else if (WEB3MFSortkeyItemDef.TAX_TYPE.equals(l_strKeyItem))
            {
                WEB3MutualTaxTypeComparator l_taxTypeComparator = 
                    new WEB3MutualTaxTypeComparator(l_strAscDesc);
                l_comparator = l_taxTypeComparator;                           
            }
            //�L�[���ڂ��]�����v�ł������ꍇ�A�]�����vComparator�𐶐�
            else if (WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS.equals(l_strKeyItem))
            {
                WEB3MutualAppraisalProfitLossComparator l_appraisalProfitLossComparator = 
                    new WEB3MutualAppraisalProfitLossComparator(l_strAscDesc); 
                l_comparator = l_appraisalProfitLossComparator;                                          
            }
            
            //�L�[���ڂ�������t���؎��Ԃł������ꍇ�A������t���؎���Comparator�𐶐�
            else if (WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME.equals(l_strKeyItem))
            {
                WEB3MutualOrderCloseTimeComparator l_orderCloseTimeComparator = 
                    new WEB3MutualOrderCloseTimeComparator(l_strAscDesc);  
                l_comparator = l_orderCloseTimeComparator;                                                        
            }
            //�Q�|�S)�@@�Q�|�R)�ɂč쐬����Comparator��ArrayList�ɒǉ� 
            l_lisComparator.add(l_comparator);
        }
        //�R)�@@ArrayList����Comparator�̔z����쐬
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);
        
        //�S)�@@Comparator�̔z�񏇂̃\�[�g����
        //(web3-common)WEB3ArraysUtility.sort(����:���M���抷�������ׁAComparator[]) 
        WEB3ArraysUtility.sort(l_sellSwitchingProductDetails, l_comparators);

        log.exiting(STR_METHOD_NAME);        
    }
}
@
