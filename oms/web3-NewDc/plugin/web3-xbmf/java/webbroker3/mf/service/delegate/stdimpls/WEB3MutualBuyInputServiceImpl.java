head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�������̓T�[�r�X�����N���X(WEB3MutualBuyInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���E (���u) �V�K�쐬
Revesion History : 2004/08/23 ���� (���u) ���r���[
Revesion History : 2004/12/10 ���� (���u) �c�Ή�
Revesion History : 2005/10/18 ���� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/05/15 �юu��(���u) �d�l�ύX�i���f��) :411,415
Revesion History : 2006/10/11 �ęԍg(���u) �d�l�ύX ���f�� No.500
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��524
Revesion History : 2007/04/06 ������ (���u) ����005, ���f��558, 560
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualBuyInputRequest;
import webbroker3.mf.message.WEB3MutualBuyInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualBuyInputService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�����t�������̓T�[�r�X�����N���X<BR>
 *
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyInputServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3MutualBuyInputService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyInputServiceImpl.class);
    /**
     * (execute)<BR>
     *  �V�[�P���X�} <BR>
     *�u(���M)���t�������́v�Q�ƁB <BR>
     *<BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���t�������́v: <BR>
     *         17(( is���t�����L��( )��false�̏ꍇfalse)�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00363<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}�u(���M)���t�������́v: <BR>
     *         is�O��MMF == false ����<BR>
     *         ( is�O�����M() == true�i�O�������j�@@���́AisFWF() == true) �ŁA<BR>
     *         is�O���،������J��() == false�i�O���،��������J�݁j<BR>
     *         �̏ꍇ�A�u�O���،��������J�݃G���[�v�Ƃ��ė�O���X���[<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01341<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B150C601AA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualBuyInputRequest l_mutualBuyInputRequest = null;
        if (l_request instanceof WEB3MutualBuyInputRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u���M���t�������̓��N�G�X�g�v�̏ꍇ
            l_mutualBuyInputRequest = (WEB3MutualBuyInputRequest) l_request;
        }
        else
        {
            log.debug(
                "the parameter of method isn't WEB3MutualBuyInputRequest type");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1�j�@@���͓��e�`�F�b�N
        l_mutualBuyInputRequest.validate();

        //1.2�j�@@�⏕�����擾
        SubAccount l_subAccount = this.getSubAccount();

        //1.3�j�@@�g�����M�����擾
        //�g�����M�����}�l�[�W�����擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualBuyInputResponse l_response = null;
        try
        {
            WEB3MutualFundProduct l_product =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getProduct(
                    Long.parseLong(l_mutualBuyInputRequest.id));
            MutualFundProductRow l_mfProductRow =
                (MutualFundProductRow) l_product.getDataSourceObject();
            if (l_mfProductRow == null)
            {
                log.debug("ProductRow is null");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�g�����M�����擾�ł��Ȃ��ꍇ�G���[");
            }
            //1.4�j�g�����M�������擾����
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
                l_mfProductRow);

            //1.5�jis�O�����M( )
            boolean l_blnIsForeignFund = l_mutualFundProduct.isForeignFund();

            //1.6�jisFWF( )
            boolean l_blnIsFWF = l_mutualFundProduct.isFWF();

            //1.7) is�ē�������( )
            boolean l_blnIsPlowbackProduct = l_mutualFundProduct.isPlowbackProduct();

            //�ڋq
            WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

            //is�O��MMF( )
            boolean l_blnIsFrgnMmf = l_mutualFundProduct.isFrgnMmf();

            //1.8�jif is�O�����M() == true�@@���́@@isFWF() == true ���� is�O��MMF() == true
            if (l_blnIsForeignFund || l_blnIsFWF || l_blnIsFrgnMmf)
            {
                //1.8.1�jis�O���،������J��( )
                //is�O���،������J��( )==false�i�O���،��������J�݁j�̏ꍇ
                //�u�O���،��������J�݃G���[�v�Ƃ��ė�O���X���[����
                if (!l_genMainAccount.isForeignAccountOpen())
                {
                    log.debug("���Y�ڋq�͊O���،������J�݂Ȃ�");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���Y�ڋq�͊O���،������J�݂Ȃ��B");
                }
            }

            // 1.9) if is�ē�������() == true
            if (l_blnIsPlowbackProduct)
            {
                log.debug("is�ē�������() == true");
                // 1.9.1) is�ݓ������J��()
                if (!l_genMainAccount.isRuitoAccountOpen())
                {
                    log.error(" __�ݓ��������J�݃G���[__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " __�ݓ��������J�݃G���[__");
                }
            }

            //1.10) ���N�G�X�g.�d�q���`�F�b�N�t���O == True �̏ꍇ�A�ژ_�����{���`�F�b�N�����{����
            WEB3GentradeProspectusResult l_validateBataResult = null;
            if(l_mutualBuyInputRequest.batoCheckFlag)
            {
                // 1.10.1) validate�ژ_�����{��(String, String)
                //[����]
                //  ��ʃR�[�h�F���N�G�X�g.��ʃR�[�h
                //  �����R�[�h�F�擾�����g�����M����.getProductCode()�̖߂�l
                WEB3GentradeBatoClientService l_bataService =
                    (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);

                l_validateBataResult =
                    l_bataService.validateProspectus(
                        l_mutualBuyInputRequest.typeCode,
                        l_mutualFundProduct.getProductCode());
            }

            //1.11�j�����`�F�b�N�I�u�W�F�N�g���擾����  �@@
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            //1.12�j���݂̎��������M�̎�t���ԋ敪�^�C�v�̎�����ԂɊY�����邩�A
            //�܂��ً}��~����Ă��Ȃ����A���邢�̓o�b�`�������łȂ����`�F�b�N����B
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            //1.13�j������Ԃ̍Đݒ�
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_mutualFundProduct.getProductCode());

            //1.14�jsetTimestamp( )
            WEB3MutualFundTradingTimeManagement.setTimestamp();

            //1.15�j get���M������( )
            //������� �F OrderTypeEnum�D�����M��������
            //�ꊇ�敪 �F �擾�����g�����M�����Dis������������()�̖߂�l
            //[is�������������̈���]
            //������� �F OrderTypeEnum�D�����M��������
            Date l_datOrderBizDate =
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(
                    OrderTypeEnum.MF_BUY,
                    l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY));
            log.debug("l_datOrderBizDate = " + l_datOrderBizDate);

            //=============�ڋq�ʎ����~�����`�F�b�N==================start
            //1.16�j�|�����`�F�b�N.validate����\�ڋq()���R�[������B
            OrderValidationResult l_validationResult =
                l_orderValidator.validateAccountForTrading(
                    l_genMainAccount,
                    new Timestamp(l_datOrderBizDate.getTime()));
            if (l_validationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_validationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //=============�ڋq�ʎ����~�����`�F�b�N==================End

            //1.17�j�戵�\�����`�F�b�N
            if (!l_mutualFundProduct.isSystemHandling())
            {
                log.debug(" __�戵�s�����G���[__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "__�戵�s�����G���[__");
            }

            //1.18�j is���t�\(Date)
            boolean l_blnSellSwitchingPossible =
                l_mutualFundProduct.isAcquiredPossible(l_datOrderBizDate);
            if (!l_blnSellSwitchingPossible)
            {
                log.debug("is���t�\()�� false ��Ԃ��ꍇ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����s�����G���[");
            }
            //1.19�jis���t�����L��( )
            boolean l_blnAcquiredDeregExistence =
                l_mutualFundProduct.isAcquiredDeregExistence();
            if (l_blnAcquiredDeregExistence)
            {
                log.debug("is���t�����L��()�� true��Ԃ��ꍇ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����s�����G���[");
            }
            //1.20�jvalidate�ً}��~(�g�����M����, String)
            WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
                (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
            try
            {
                l_validationsCheck.validateEmergencyStop(
                                l_mutualFundProduct,
                                WEB3ProcessDivDef.BUY);
            }
            catch(WEB3BaseException l_ex)
            {
                log.error("�ً}��~�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ً}��~�G���[");
            }

            //1.21�jvalidate������t�\( )
            WEB3MutualFundTradingTimeManagement.validateOrderAccept();

            //validate�O��MMF��d����(�⏕����, �g�����M����, Date)
            //�mvalidate�O��MMF��d�����ɓn���p�����^�n 
            // �⏕����       �F �擾�����⏕���� 
            //�@@�@@�@@�g�����M���� �F �擾�����g�����M���� 
            //      ������          �F �擾�������M������
            l_validationsCheck.validateFrgnMmfDoubleOrder(
                l_subAccount,
                l_mutualFundProduct,
                l_datOrderBizDate);

            //1.22�j�����擾
            //�mget�����ɓn���p�����^�n
            //�،���ЁF �擾�����⏕����.getInstitution()�̖߂�l
            //�����R�[�h�F �擾�����g�����M����.getProductCode()�̖߂�l
            //������    �F�擾�������M������
            Timestamp l_tsExecutedDate =
                l_mutualFundProductManager.getExecutedDate(
                    l_subAccount.getInstitution(),
                    l_mutualFundProduct.getProductCode(),
                    l_datOrderBizDate);

            //1.23�j�@@��n���擾
            //�mget��n���ɓn���p�����^�n
            //�،���ЁF �擾�����⏕����.getInstitution()�̖߂�l
            //�����R�[�h�F �擾�����g�����M����.getProductCode()�̖߂�l
            //is���t�F true
            //�����@@�F �擾��������

            Timestamp l_tsDeliveryDate =
                l_mutualFundProductManager.getDeliveryDate(
                    l_subAccount.getInstitution(),
                    l_mutualFundProduct.getProductCode(),
                    true,
                    l_tsExecutedDate);

            // 1.24�jget�����M�����t�\�z
            //���t�\�z���擾����B
            //�mget�����M�����t�\�z�ɓn���p�����^�n
            //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
            //��n���F�@@�擾������n��
            //������ʁF�@@201�F�����M��������

            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            double l_buyPossibleAmount =
                l_tpTradingPowerService.getMutualFundBuyTradingPower(
                    l_gentradeSubAccount,
                    l_tsDeliveryDate,
                    OrderTypeEnum.MF_BUY);

            //1.25�j���M���t�������̓��X�|���X�I�u�W�F�N�g�𐶐����A���^�[������
            l_response =  (WEB3MutualBuyInputResponse) l_request.createResponse();

            //1.26�j���M���t�������̓��X�|���X�I�u�W�F�N�g�ɂ́A�ȉ��̒l��ݒ肷��
            l_response.tradingPower = WEB3StringTypeUtility.formatNumber(l_buyPossibleAmount);
            l_response.mutualProductCode = l_mutualFundProduct.getProductCode();
            l_response.mutualProductName =
                l_mutualFundProduct.getMutualProductName();
            l_response.constantValueCurrencyCode =
                l_mutualFundProduct.getCurrencyCode();
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            if (!l_mutualFundProductRow.getBuyConstantValueIsNull())
            {
                l_response.constantValue =
                    WEB3StringTypeUtility.formatNumber(l_mutualFundProduct.getConstantValue());
            }
            else
            {
                l_response.constantValue = null;
            }
            l_response.constantValueAppDate =
                l_mutualFundProduct.getConstantValueAppDate();
            //�g���A�J�E���g�}�l�[�W���擾����
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //�|�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����
            WEB3GentradeMainAccount l_gentradeMainAccount =
                (WEB3GentradeMainAccount) l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());

            //    1) �g�����M����.is�O��MMF = true�̏ꍇ 
            //    �@@�@@ 2:���̑���ݒ肷��B 
            // �@@ 2) �g�����M����.is�O��MMF = false�̏ꍇ 
            //     �@@�|�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����B 
            //     �@@�@@�mget�ڋq�ɓn���p�����^�n 
            // �@@    �@@�@@����ID�F �擾�����⏕����.getAccountId()�̖߂�l 
            //     �@@�|�擾�����ڋq�I�u�W�F�N�g.is��������J��()���R�[������B 
            // �@@    �@@�mis��������J�݂ɓn���p�����^�n 
            // �@@�@@    �@@�⏕�����F �擾�����⏕�����I�u�W�F�N�g 
            // �@@�@@�@@    ��n���F �擾������n�� 
            //     �@@�|is��������J��()�� true ���� is�����^()��true��Ԃ��ꍇ�́A 
            // �@@    �@@�@@�h0�F��ʁh�Ɓh1�F����h��ݒ肷��B 
            //   �@@  �|is��������J��()�� true ���� is�����^()�� false��Ԃ��ꍇ�A���邢�� 
            //   �@@  �@@ is��������J��()�� false ��Ԃ��ꍇ�́A�h0�F��ʁh��ݒ肷��B
            boolean l_specialAccountEstablished =
                l_gentradeMainAccount.isSpecialAccountEstablished(
                    l_tsDeliveryDate,
                    l_subAccount);
            if (l_blnIsFrgnMmf)
            {
                String l_arrAccountDivDefs[] = new String[1];
                l_arrAccountDivDefs[0] = WEB3MFAccountDivDef.OTHER;
                l_response.taxTypeList = l_arrAccountDivDefs;
            }
            else
            {
                if (l_specialAccountEstablished == true)
                {
                    if(l_mutualFundProduct.isStockType() == true)
                    {
                        String l_arrAccountDivDefs[] = new String[2];
                        l_arrAccountDivDefs[0] = WEB3MFAccountDivDef.SPECIAL;
                        l_arrAccountDivDefs[1] = WEB3MFAccountDivDef.NORMAL;
                        l_response.taxTypeList = l_arrAccountDivDefs;
                    }
                    else
                    {
                        String l_arrAccountDivDefs[] = new String[1];
                        l_arrAccountDivDefs[0] = WEB3MFAccountDivDef.NORMAL;
                        l_response.taxTypeList = l_arrAccountDivDefs;
                    }
                }
                else
                {
                    if(l_specialAccountEstablished == false)
                    {
                        String l_arrAccountDivDefs[] = new String[1];
                        l_arrAccountDivDefs[0] = WEB3MFAccountDivDef.NORMAL;
                        l_response.taxTypeList = l_arrAccountDivDefs;
                    }
                }
            }

            //�w����@@�ꗗ�ݒ�
            if (WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(
                l_mutualFundProduct.getBuySelectable()))
            {
                l_response.specifyDivList =
                    new String[] {
                        WEB3SellDivDef.COUNT_DESIGNATE,
                        WEB3SellDivDef.MONEY_DESIGNATE };
            }
            else if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(
                    l_mutualFundProduct.getBuySelectable()))
            {
                l_response.specifyDivList =
                    new String[] { WEB3SellDivDef.MONEY_DESIGNATE };
            }
            else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(
                    l_mutualFundProduct.getBuySelectable()))
            {
                l_response.specifyDivList =
                    new String[] { WEB3SellDivDef.COUNT_DESIGNATE };
            }
            //���ϕ��@@�ꗗ�ݒ�
            if (WEB3BuySellSettlementDivDef.SELECT_DESIGNATE.equals(
                l_mutualFundProduct.getAcquiredSettlement()))
            {
                l_response.settleDivList =
                    new String[] {
                        WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY,
                        WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY };
            }
            else if (WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(
                    l_mutualFundProduct.getAcquiredSettlement()))
            {
                l_response.settleDivList =
                    new String[] { WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY };
            }
            else if (WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY.equals(
                    l_mutualFundProduct.getAcquiredSettlement()))
            {
                l_response.settleDivList =
                    new String[] { WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY };
            }

            try
            {
                // �ۗL���Y�e�[�u���������B
                List l_lisAssets = new ArrayList();

                String l_strWhere =
                    "account_id = ? and sub_account_id = ? "
                    + "and product_id = ? ";

                Object[] l_objWhereValues = {
                    new Long(l_subAccount.getAccountId()),
                    new Long(l_subAccount.getSubAccountId()),
                    new Long(l_mutualFundProduct.getProductId()),
                };
                // -�ۗL���Y�e�[�u�����������A�ۗL���YParams��List���擾����B
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisAssets = l_queryProcessor.doFindAllQuery(
                    AssetRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);

                // 1�����Ȃ��ꍇ�A�V�K���t�Ƃ���B
                if(l_lisAssets.size() <= 0)
                {
                    //���t���P�ʌ���,�V�K���t�̏ꍇ
                    l_response.buyUnitQty =
                        Long.toString(l_mutualFundProduct.getNewBuyUnitQty());
                    //���t���Œ����,�V�K���t�̏ꍇ
                    l_response.buyMinQty =
                       Long.toString(l_mutualFundProduct.getNewBuyMinQty());
                    //���t���P�ʋ��z,�V�K���t�̏ꍇ
                    l_response.buyUnitAmt =
                        Long.toString(l_mutualFundProduct.getNewBuyUnitAmt());
                    //���t���Œ���z,�V�K���t�̏ꍇ
                    l_response.buyMinAmt =
                        Long.toString(l_mutualFundProduct.getNewBuyMinAmt());

                    //�E���t���O�ݒP�ʋ��z�F
                    //�@@(*) �V�K���t�̏ꍇ�A�擾�����g�����M����.get�O�ݒP�ʋ��z�i�V�K���t�j()��
                    //�@@�@@�@@�߂�l��ݒ肷��B
                    if (!l_mutualFundProductRow.getFrgnNewBuyUnitAmtIsNull())
                    {
                        l_response.buyFrgnUnitAmt =
                            l_mutualFundProduct.getFrgnNewBuyUnitAmt() + "";
                    }

                    //�E���t���O�ݍŒ���z�F
                    //�@@(*) �V�K���t�̏ꍇ�A�擾�����g�����M����.get�O�ݍŒ���z�i�V�K���t�j()��
                    //�@@�@@�@@�߂�l��ݒ肷��B
                    if (!l_mutualFundProductRow.getFrgnNewBuyMinAmtIsNull())
                    {
                        l_response.buyFrgnMinAmt =
                            l_mutualFundProduct.getFrgnNewBuyMinAmt() + "";
                    }
                }
                // 1���ȏ゠��ꍇ�A�ǉ����t�Ƃ���B
                else if (l_lisAssets.size() > 0)
                {
                    //���t���P�ʌ���,�ǉ����t�̏ꍇ
                    l_response.buyUnitQty =
                        Long.toString(l_mutualFundProduct.getAddBuyUnitQty());
                    //���t���Œ����,�ǉ����t�̏ꍇ
                    l_response.buyMinQty =
                        Long.toString(l_mutualFundProduct.getAddBuyMinQty());
                    //���t���P�ʋ��z,�ǉ����t�̏ꍇ
                    l_response.buyUnitAmt =
                        Long.toString(l_mutualFundProduct.getAddBuyUnitAmt());
                    //���t���Œ���z,�ǉ����t�̏ꍇ
                    l_response.buyMinAmt =
                        Long.toString(l_mutualFundProduct.getAddBuyMinAmt());

                    //�E���t���O�ݒP�ʋ��z�F
                    //�@@(*) �ǉ����t�̏ꍇ�A�擾�����g�����M����.get�O�ݒP�ʋ��z�i�ǉ����t�j()��
                    //�@@�@@�@@�߂�l��ݒ肷��B
                    if (!l_mutualFundProductRow.getFrgnAddBuyUnitAmtIsNull())
                    {
                        l_response.buyFrgnUnitAmt =
                            l_mutualFundProduct.getFrgnAddBuyUnitAmt() + "";
                    }

                    //�E���t���O�ݍŒ���z�F
                    //�@@(*) �ǉ����t�̏ꍇ�A�擾�����g�����M����.get�O�ݍŒ���z�i�ǉ����t�j()��
                    //�@@�@@�@@�߂�l��ݒ肷��B
                    if (!l_mutualFundProductRow.getFrgnAddBuyMinAmtIsNull())
                    {
                        l_response.buyFrgnMinAmt =
                            l_mutualFundProduct.getFrgnAddBuyMinAmt() + "";
                    }
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In �ۗL���Y�e�[�u���������� ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            catch (DataQueryException l_ex)
            {
                log.error("Error In �ۗL���Y�e�[�u���������� ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            //�������ݒ�
            l_response.orderBizDate = l_datOrderBizDate;
            //�����ݒ�
            l_response.executionTimestamp = l_tsExecutedDate;
            //��n���ݒ�
            l_response.deliveryDate = l_tsDeliveryDate;

            //�ژ_�����{���`�F�b�N���ʁF
            //�@@���N�G�X�g.�d�q���`�F�b�N�t���O==false�̏ꍇ�́Anull�Z�b�g
            //�@@���N�G�X�g.�d�q���`�F�b�N�t���O==true�̏ꍇ�Avalidate�ژ_�����{��()�̌��ʃI�u�W�F�N�g���Z�b�g����B
            l_response.prospectusResult = l_validateBataResult;

            //�E�~�]����z 
            //�@@�@@1�j���M����.get�ʉ݃R�[�h( )��T0�@@�܂���
            //�@@�@@�@@�g�����M����.is�O��MMF = true�@@�̏ꍇ
            //�@@�@@�@@�@@null���Z�b�g����B
            //�@@�@@2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ
            //�@@�@@�@@�@@�g�����M����.get�~�]����z(WEB3MFProcessDivDef.���t)���Z�b�g����B
            if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mutualFundProduct.getCurrencyCode())
                || l_blnIsFrgnMmf)
            {
                l_response.yenConstantValue = null;
            }
            else
            {
                l_response.yenConstantValue =
                    l_mutualFundProduct.getYenConstantValue(WEB3MFProcessDivDef.BUY);
            }
            
            // �E�Q�l���[�g
            //     1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ
            //        null���Z�b�g����B
            //     2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ
            //        2-1) ���M����.is�O��MMF �� true�̏ꍇ
            //               �g�����M����.get�O��MMF�בփ��[�g()�̖߂�l�O��MMF�בփ��[�gParams��TTS
            //               ���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
            //        2-2) ���M����.is�O��MMF �� false�̏ꍇ
            //               �g�����M����.get�בփ��[�g()�̖߂�l�בփ��[�gParams��TTS / ���בփ��[�g�v�Z�P��
            //               ���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
            // �E�Q�l���[�g�m���
            //     1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ
            //        null���Z�b�g����B
            //     2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ
            //        2-1) ���M����.is�O��MMF �� true�̏ꍇ
            //             �g�����M����.get�O��MMF�בփ��[�g()�̖߂�l
            //             �O��MMF�בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B
            //        2-2) ���M����.is�O��MMF �� false�̏ꍇ
            //             �g�����M����.get�בփ��[�g()�̖߂�l
            //             �בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B
            if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mutualFundProduct.getCurrencyCode()))
            {
                l_response.referenceRate = null;
                l_response.referenceRateFixedDay = null;
            }
            else
            {
                if (l_blnIsFrgnMmf)
                {
                    BigDecimal l_bdTtSellingRate =
                        new BigDecimal(l_mutualFundProduct.getFrgnMmfExchangeRate().getTtSellingRate() + "");
                    l_response.referenceRate =
                        WEB3StringTypeUtility.formatNumber(
                            l_bdTtSellingRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                    l_response.referenceRateFixedDay =
                        l_mutualFundProduct.getFrgnMmfExchangeRate().getExecTimestamp();
                }
                else
                {
                    BigDecimal l_bdTtSellingRate =
                        new BigDecimal(l_mutualFundProduct.getExchangeRate().getTtSellingRate());
                    BigDecimal l_bdExchangeCalcUnit =
                        new BigDecimal(l_mutualFundProduct.getExchangeRate().getExchangeCalcUnit());
                    l_response.referenceRate =
                        WEB3StringTypeUtility.formatNumber(
                            l_bdTtSellingRate.divide(
                                l_bdExchangeCalcUnit,
                                2,
                                BigDecimal.ROUND_HALF_UP).doubleValue());
                    
                    l_response.referenceRateFixedDay =
                        l_mutualFundProduct.getExchangeRate().getExecTimestamp();
                }
            } 
        }
        catch (NotFoundException e)
        {
            log.error("not found Product or MainAccountexception");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^�s�����G���[�B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
