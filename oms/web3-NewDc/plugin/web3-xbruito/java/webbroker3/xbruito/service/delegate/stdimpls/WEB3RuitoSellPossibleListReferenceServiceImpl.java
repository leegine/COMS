head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellPossibleListReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ����\�ꗗ�Ɖ�T�[�r�X�����N���X(WEB3RuitoSellPossibleListReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 ���u�� (���u) �V�K�쐬
                   2004/12/06 ��O�� (���u) �c�Ή�
*/

package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoAsset;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3StopDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.xbruito.WEB3RuitoAssetDetail;
import webbroker3.xbruito.WEB3RuitoClientRequestService;
import webbroker3.xbruito.WEB3RuitoOrderManagerReusableValidationsCheck;
import webbroker3.xbruito.WEB3RuitoPositionManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.WEB3RuitoSellOrderDetail;
import webbroker3.xbruito.define.WEB3RuitoSellPossibleDivDef;
import webbroker3.xbruito.message.WEB3RuitoAssetGroup;
import webbroker3.xbruito.message.WEB3RuitoSellListRequest;
import webbroker3.xbruito.message.WEB3RuitoSellListResponse;
import webbroker3.xbruito.message.WEB3RuitoSellOrderUnit;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellPossibleListReferenceService;

/**
 * �ݓ����\�ꗗ�Ɖ�T�[�r�X�����N���X<BR>
 */
public class WEB3RuitoSellPossibleListReferenceServiceImpl
    extends WEB3RuitoClientRequestService
    implements WEB3RuitoSellPossibleListReferenceService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellPossibleListReferenceServiceImpl.class);
    /**
     * ���͉�ʕ\������<BR>
     * �ݐϓ����̉��\�ꗗ�Ɖ��ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�ݓ��j���\�ꗗ�Ɖ�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406A91D10387
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
		log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

		WEB3RuitoSellListRequest l_web3RuitoSellListRequest = null;
		
		if (l_request instanceof WEB3RuitoSellListRequest)
		{
			l_web3RuitoSellListRequest = (WEB3RuitoSellListRequest) l_request;
		}
		else
		{
			log.debug(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80018,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}			

        // 1.1 �⏕�����擾 
        SubAccount l_subAccount = getSubAccount();
        
		log.debug("SubAccount Status = " + l_subAccount.getSubAccountStatus());
		log.debug("MainAccount Status = " + l_subAccount.getMainAccount().getAccountStatus());

		// 1.2 �ڋq�ʎ�������`�F�b�N 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // 1.3 ����\�ڋq�`�F�b�N�����{����B 
        OrderValidationResult l_orderValidationResult = 
            l_finApp.getCommonOrderValidator().validateSubAccountForTrading(l_subAccount);
            
        if (l_orderValidationResult.getProcessingResult().isFailedResult()) 
        {
			log.debug("�����~�ڋq�G���[");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00275,
				this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.4 �ݓ���������J�݃`�F�b�N 
        WEB3RuitoOrderManagerReusableValidationsCheck l_ruitoOrderManagerReusableValidationsCheck =
            (WEB3RuitoOrderManagerReusableValidationsCheck) 
                WEB3RuitoOrderManagerReusableValidationsCheck.getInstance();

        try
        {
            l_ruitoOrderManagerReusableValidationsCheck
                .validateRuitoTradedAccountEstablish(
                l_subAccount);
        }
        catch (OrderValidationException l_ex)
        {
        	log.debug("__OrderValidationException__");
			log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getValidationResult().toString(),
                l_ex);
        }
        
        boolean l_blnMidiumFundValidFlag = true;
		boolean l_blnMMFValidFlag = true;
		int l_intFundError = 0;
        int l_intMmfError = 0;
                
        // ��t���ԃ`�F�b�N�A�V�X�e�������~�`���b�N 
        //�������t�@@���h�̒�����t�\�`�F�b�N���s��
        try
        {           
            //�|�������t�@@���h�̒�����t�\�`�F�b�N���s���B 
            log.debug("�������t�@@���h�̒�����t�\�`�F�b�N���s���B");
			WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch(WEB3BaseException l_ex)
        {
        	log.debug("�������t�@@���h ��t���ԃ`�F�b�N�A�V�X�e�������~�G���[");
			l_blnMidiumFundValidFlag = false;
            
            if (WEB3ErrorCatalog.BUSINESS_ERROR_00011.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 1;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00012.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 2;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00013.equals(l_ex.getErrorInfo()))
            {
                l_intFundError = 3;
            }
        }        
        
        //�|������ԊǗ�.reset��t���ԋ敪()���R�[����
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(
            WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
        
        //�|��t�����A���t���[�����Z�b�g����B 
        WEB3GentradeTradingTimeManagement.setTimestamp();
        try
        {
            //�|MMF�̒�����t�\�`�F�b�N���s���B 
            log.debug("MMF�̒�����t�\�`�F�b�N���s���B ");
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch (WEB3BaseException l_ex)
        {
			log.debug("MMF ��t���ԃ`�F�b�N�A�V�X�e�������~��t���ԃG���[");
			l_blnMMFValidFlag = false;
            
            if (WEB3ErrorCatalog.BUSINESS_ERROR_00011.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 1;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00012.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 2;
            }
            else if (WEB3ErrorCatalog.BUSINESS_ERROR_00013.equals(l_ex.getErrorInfo()))
            {
                l_intMmfError = 3;
            }
        }
        
        String l_strChuukokuFundSellPossdiv = null;
        String l_strMmfSellPossdiv = null;
        
        //�|�������t�@@���h��MMF�̗����A�܂��͂����ꂩ�ŗ�O���Ԃ��ꂽ�ꍇ�A�ȉ����s���B 
        if (!l_blnMidiumFundValidFlag || !l_blnMMFValidFlag)
        {
            //(1)�������t�@@���h�̒����̃`�F�b�N�̏ꍇ 
            if (!l_blnMidiumFundValidFlag)                    
            {
                log.debug("�������t�@@���h�̒����̃`�F�b�N�̏ꍇ ");
                // �E�u�o�b�`�������v�̗�O���Ԃ��ꂽ�ꍇ 
                //  �u�ً}��~���v�̗�O���Ԃ��ꂽ�ꍇ
                // * �ϐ�.�����t�@@���h���\�敪�Ɂh�V�X�e�������~���h���Z�b�g����B 
                if (l_intFundError == 1 || l_intFundError == 2)
                {
                    log.debug("�ϐ�.�����t�@@���h���\�敪�Ɂh�V�X�e�������~���h���Z�b�g����B");
                    l_strChuukokuFundSellPossdiv = 
                        WEB3RuitoSellPossibleDivDef.SYSTEM_TRADING_STOP_ERROR;
                }  
                //�u��t�s���ԃG���[�v�̗�O���Ԃ��ꂽ�ꍇ
                //*  �ϐ�.�����t�@@���h���\�敪�Ɂh��t���ԃG���[�h���Z�b�g����B 
                else if (l_intFundError == 3)
                {
                    log.debug("�ϐ�.�����t�@@���h���\�敪�Ɂh��t���ԃG���[�h���Z�b�g����B");
                    l_strChuukokuFundSellPossdiv = 
                        WEB3RuitoSellPossibleDivDef.ACCEPTED_TIME_ERROR;
                }
                //�E��O���Ԃ���Ȃ������ꍇ�A�ϐ�.�����t�@@���h���\�敪��NULL���Z�b�g����B 
                else
                {
                    log.debug("�ϐ�.�����t�@@���h���\�敪��NULL���Z�b�g����B");
                    l_strChuukokuFundSellPossdiv = null;
                }
            }
            //(2)MMF�̒����̃`�F�b�N�̏ꍇ 
            if (!l_blnMMFValidFlag)                    
            {
                log.debug("MMF�̒����̃`�F�b�N�̏ꍇ");
                // �E�u�o�b�`�������v�̗�O���Ԃ��ꂽ�ꍇ 
                //  �u�ً}��~���v�̗�O���Ԃ��ꂽ�ꍇ
                // * �ϐ�.�����t�@@���h���\�敪�Ɂh�V�X�e�������~���h���Z�b�g����B 
                if (l_intMmfError == 1 || l_intMmfError == 2)
                {                    
                    log.debug("�ϐ�.�����t�@@���h���\�敪�Ɂh�V�X�e�������~���h���Z�b�g����B");
                    l_strMmfSellPossdiv = 
                        WEB3RuitoSellPossibleDivDef.SYSTEM_TRADING_STOP_ERROR;
                }  
                //�u��t�s���ԃG���[�v�̗�O���Ԃ��ꂽ�ꍇ
                //*  �ϐ�.�����t�@@���h���\�敪�Ɂh��t���ԃG���[�h���Z�b�g����B 
                else if (l_intMmfError == 3)
                {
                    log.debug("�ϐ�.�����t�@@���h���\�敪�Ɂh��t���ԃG���[�h���Z�b�g����B");
                    l_strMmfSellPossdiv = 
                        WEB3RuitoSellPossibleDivDef.ACCEPTED_TIME_ERROR;
                }
                //�E��O���Ԃ���Ȃ������ꍇ�A�ϐ�.MMF���\�敪��NULL���Z�b�g����B
                else
                {
                    log.debug("�ϐ�.�����t�@@���h���\�敪��NULL���Z�b�g����B");
                    l_strMmfSellPossdiv = null;
                }
            }            
        } 

        // 1.11 �ڋq���ۗL����ۗL���Y�I�u�W�F�N�g��S���擾����B
        WEB3RuitoPositionManager l_web3RuitoPositionManager =
            (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getPositionManager();

        List l_lisAssetList =
            l_web3RuitoPositionManager.getAssets(
                l_subAccount,
                new DefaultSortKeySpec(" product_id asc "),
                ProductTypeEnum.RUITO);
                 
		log.debug("l_lisAssetList.size() = " + l_lisAssetList.size());
		int l_intRuitoAssetListSize = l_lisAssetList.size();

        // �ݓ����\�ꗗ�Ɖ�X�|���X�I�u�W�F�N�g����
        WEB3RuitoSellListResponse l_response =
            (WEB3RuitoSellListResponse) l_web3RuitoSellListRequest.createResponse();
        WEB3RuitoAssetGroup[] l_web3RuitoAssetGroup =
            new WEB3RuitoAssetGroup[l_intRuitoAssetListSize];

		log.debug("l_web3RuitoAssetGroup.length = " + l_web3RuitoAssetGroup.length);

        // 1.12 �擾�����ۗL���Y�̖��������A�J��Ԃ�����������
        for (int i = 0; i < l_intRuitoAssetListSize; i++)
        {
            RuitoAsset l_ruitoAsset = (RuitoAsset) l_lisAssetList.get(i);
			WEB3RuitoProduct l_ruitoProduct = (WEB3RuitoProduct) l_ruitoAsset.getProduct();

            AssetRow l_ruitoAssetRow =
                (AssetRow) l_ruitoAsset.getDataSourceObject();
            double l_countBeforePenalty = l_ruitoAssetRow.getCountBeforePenalty();
            
			log.debug("30�������o�ߎc�� = " + l_countBeforePenalty);
			log.debug("�ݓ�����.get����ID() = " + l_ruitoProduct.getProductId());
			log.debug("�ۗL���Y.getQuantity() = " + l_ruitoAsset.getQuantity());
			log.debug("�ݓ�����.getRuitoType() = " + l_ruitoProduct.getRuitoType());

            //1.12.6  �ݓ��ۗL���Y���׎擾 
            WEB3RuitoAssetDetail l_ruitoAssetDetail =
                new WEB3RuitoAssetDetail(
                    l_ruitoProduct.getProductId(),
                    l_ruitoAsset.getQuantity(),
                    l_countBeforePenalty,
                    l_ruitoProduct.getRuitoType());

            //1.12.7  �ݓ��ۗL���Y���ׂɒl��ݒ肷��B 
            l_web3RuitoPositionManager.getRuitoAssetGroup(
                l_subAccount,
                l_ruitoAssetDetail);
			
			l_web3RuitoAssetGroup[i] = new WEB3RuitoAssetGroup();

            l_web3RuitoAssetGroup[i].ruitoProductCode = l_ruitoProduct.getProductCode();
            l_web3RuitoAssetGroup[i].ruitoProductName = l_ruitoProduct.getProductName();

            l_web3RuitoAssetGroup[i].ruitoSellPossBalance =
                WEB3StringTypeUtility.formatNumber(l_ruitoAssetDetail.getSellPossibleBalance());
            l_web3RuitoAssetGroup[i].ruitoBalance = 
                WEB3StringTypeUtility.formatNumber(l_ruitoAsset.getQuantity());
            l_web3RuitoAssetGroup[i].ruito30DayPassBal =
                WEB3StringTypeUtility.formatNumber(l_ruitoAssetDetail.getCountAfterPenalty());
            l_web3RuitoAssetGroup[i].ruito30DayNotPassBal =
                WEB3StringTypeUtility.formatNumber(l_ruitoAssetDetail.getCountBeforePenalty());
            l_web3RuitoAssetGroup[i].estateReserve =
                WEB3StringTypeUtility.formatNumber(l_ruitoAssetDetail.getTrustFortunePenaltyPrice());
            //���i���t�j�\�敪�@@�@@= (*) 
            String l_strSellPossDiv = null;
            
            //�|������ԊǗ�.setTimestamp( )���R�[���B
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //�|�g���ݓ������}�l�[�W��.get�ݓ��������( )���R�[�����A�ݓ���������I�u�W�F�N�g���擾����B
            WEB3RuitoProductManager l_ruitoProductManager =   
               (WEB3RuitoProductManager) l_finApp.getTradingModule(
                   ProductTypeEnum.RUITO).getProductManager(); 
            RuitoTradedProduct l_ruitoTradedProduct = null;
            try
            {
                log.debug("l_web3RuitoAssetGroup[" + i + "].ruitoProductCode" + 
                        l_web3RuitoAssetGroup[i].ruitoProductCode);
                
                l_ruitoTradedProduct = 
                    l_ruitoProductManager.getRuitoTradedProduct(
                        l_subAccount.getInstitution(),
                        l_web3RuitoAssetGroup[i].ruitoProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�g���ݓ�����������Ȃ�");                
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00250,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            RuitoTradedProductRow l_ruitoTradedProductRow = 
                (RuitoTradedProductRow)l_ruitoTradedProduct.getDataSourceObject();
           
            if (RuitoTypeEnum.MMF.equals(l_ruitoAssetDetail.getRuitoType()))
            {
                log.debug("�ݓ��ۗL���Y����.�ݓ��^�C�v=�hMMF�h�̏ꍇ");
                //�|�ݓ��ۗL���Y����.�ݓ��^�C�v=�hMMF�h�̏ꍇ��
                //�hMMF�i�ݒ�j�h�������ɁAreset������t���i( )���R�[���B 
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3TradingTimeTypeDef.MMF_SET);
                
                if (l_ruitoAssetDetail.getSellPossibleBalance() == 0)
                {
                    log.debug("�ݓ��ۗL���Y����.���\�c����0�̏ꍇ�A�h�S����񒆁h���Z�b�g����B");
                    //(1)�S����񒆂̃`�F�b�N 
                    //�|�ݓ��ۗL���Y����.���\�c����0�̏ꍇ�A�h�S����񒆁h���Z�b�g����B
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.ALL_SELLING;
                }
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradedProductRow.getSellStop()))
                {
                    log.debug("�擾�����ݓ���������I�u�W�F�N�g.get����~( )���h��~���h�̏ꍇ�A" +  
                            "�h�ً}��~���h���Z�b�g����B");
                    //(2)�ً}��~���̃`�F�b�N 
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.SCRAM_STOPING;
                }               
                if (!l_ruitoProduct.isSellPossible(
                        WEB3GentradeTradingTimeManagement.getOrderBizDate()))
                {
                    log.debug("�g���ݓ�����.is���\( )=false�̏ꍇ�A�h�����~���h���Z�b�g����B");
                    //�g���ݓ�����.is���\( )=false�̏ꍇ�A�h�����~���h���Z�b�g����B 
                    //(3)�g���ݓ�����.is���\( )=false�̏ꍇ�A�h�����~���h���Z�b�g����B 
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.TRADING_STOPING;
                }              
                if (l_strMmfSellPossdiv != null)
                {
                    log.debug("�����t�@@���h���\�敪!=NULL�̏ꍇ");
                    //(4)��t���ԃG���[�A�V�X�e����~���G���[�̃`�F�b�N 
                    //  �ϐ�.MMF���\�敪!=NULL�̏ꍇ�A 
                    //  �ϐ�.MMF���\�敪�i�S�̏����Őݒ�j���Z�b�g����B 
                    l_strSellPossDiv = l_strMmfSellPossdiv;
                }
                log.debug("���i���t�j�\�敪 = " + l_strSellPossDiv);
            }
            else if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoAssetDetail.getRuitoType()))
            {
                log.debug("�ݓ��ۗL���Y����.�ݓ��^�C�v=�h����F�h�̏ꍇ");
                //�ݓ��ۗL���Y����.�ݓ��^�C�v=�h����F�h�� �ꍇ��
                //�h����F�h�������ɁAreset������t���i( )���R�[���B
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(
                        WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
                
                if (l_ruitoAssetDetail.getSellPossibleBalance() == 0)
                {
                    log.debug("�ݓ��ۗL���Y����.���\�c����0�̏ꍇ�A�h�S����񒆁h���Z�b�g����B");
                    //(1)�S����񒆂̃`�F�b�N 
                    //�|�ݓ��ۗL���Y����.���\�c����0�̏ꍇ�A�h�S����񒆁h���Z�b�g����B
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.ALL_SELLING;
                }                
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradedProductRow.getSellStop()))
                {
                    log.debug("�擾�����ݓ���������I�u�W�F�N�g.get����~( )���h��~���h�̏ꍇ�A" +  
                        "�h�ً}��~���h���Z�b�g����B");
                    //(2)�ً}��~���̃`�F�b�N 
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.SCRAM_STOPING;
                }
                if (!l_ruitoProduct.isSellPossible(
                        WEB3GentradeTradingTimeManagement.getOrderBizDate()))
                {
                    log.debug("�g���ݓ�����.is���\( )=false�̏ꍇ�A�h�����~���h���Z�b�g����B");
                    //(3)�g���ݓ�����.is���\( )=false�̏ꍇ�A�h�����~���h���Z�b�g����B 
                    l_strSellPossDiv = WEB3RuitoSellPossibleDivDef.TRADING_STOPING;
                }
                if (l_strChuukokuFundSellPossdiv != null)
                {
                    log.debug("�����t�@@���h���\�敪!=NULL�̏ꍇ");
                    //(4)��t���ԃG���[�A�V�X�e����~���G���[�̃`�F�b�N 
                    // �ϐ�.�����t�@@���h���\�敪!=NULL�̏ꍇ�A 
                    // �ϐ�.�����t�@@���h���\�敪�i�S�̏����Őݒ�j���Z�b�g����B 
                    l_strSellPossDiv = l_strChuukokuFundSellPossdiv;
                }
                log.debug("���i���t�j�\�敪 = " + l_strSellPossDiv);
            }
            
            l_web3RuitoAssetGroup[i].sellPossDiv = l_strSellPossDiv;
            log.debug("l_web3RuitoAssetGroup[" + i + "].sellPossDiv = " + 
                    l_web3RuitoAssetGroup[i].sellPossDiv);

            WEB3RuitoSellOrderDetail[] l_web3RuitoSellOrderDetail =
                l_ruitoAssetDetail.getSellOrderDetail();

			WEB3RuitoSellOrderUnit[] l_ruitoSellOrderUnits = null;

            if (l_web3RuitoSellOrderDetail != null)
            {
				l_ruitoSellOrderUnits =
					new WEB3RuitoSellOrderUnit[l_web3RuitoSellOrderDetail.length];
					
				log.debug("l_web3RuitoSellOrderDetail.length = " 
				    + l_web3RuitoSellOrderDetail.length);

				for (int j = 0; j < l_web3RuitoSellOrderDetail.length; j++)
				{
					l_ruitoSellOrderUnits[j] = new WEB3RuitoSellOrderUnit();
					
					l_ruitoSellOrderUnits[j].orderDate =
						l_web3RuitoSellOrderDetail[j].getOrderTime();
					l_ruitoSellOrderUnits[j].orderState =
						l_web3RuitoSellOrderDetail[j].getOrderStatusDiv();
                    
					log.debug("OrderQuantityType = "
						+ l_web3RuitoSellOrderDetail[j].getOrderQuantityType());
				
					if ((QuantityTypeEnum.QUANTITY.intValue() + "")
						.equals(l_web3RuitoSellOrderDetail[j].getOrderQuantityType()))
					{
						l_ruitoSellOrderUnits[j].ruitoOrderQuantityType =
							WEB3SellDivDef.COUNT_DESIGNATE;
					}
					else if (
					    (QuantityTypeEnum.AMOUNT.intValue() + "").equals(
						l_web3RuitoSellOrderDetail[j].getOrderQuantityType()))
					{
						l_ruitoSellOrderUnits[j].ruitoOrderQuantityType =
							WEB3SellDivDef.MONEY_DESIGNATE;
					}
					
					l_ruitoSellOrderUnits[j].ruitoOrderQuantity =

                    WEB3StringTypeUtility.formatNumber(l_web3RuitoSellOrderDetail[j].getOrderQuantity()) + "";
                    
					log.debug("OrderQuantity = "
						+ l_web3RuitoSellOrderDetail[j].getOrderQuantity());
				}
            }

            l_web3RuitoAssetGroup[i].ruitoSellOrderUnits = l_ruitoSellOrderUnits;
        }
        
		// �ۗL���Y�I�u�W�F�N�g���O���̏ꍇnull���Z�b�g����B 
        if(l_intRuitoAssetListSize ==0)
        {
			l_response.ruitoAssetGroups = null;    
        }
        else
        {
			l_response.ruitoAssetGroups = l_web3RuitoAssetGroup;
        }
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
