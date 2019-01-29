head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualRecruitOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M����W�������̓T�[�r�X�����N���X(WEB3MutualRecruitOrderInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/29 ���� (���u) �V�K�쐬
Revesion History : 2006/10/12 ��іQ (���u) �d�l�ύX�E���f��503
Revesion History : 2006/11/13 ���{ (SRA) ��Q�ԍ�:U02944
Revesion History : 2007/04/06 ������ (���u) ����005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualApplyInputRequest;
import webbroker3.mf.message.WEB3MutualApplyInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderInputService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M����W�������̓T�[�r�X�����N���X
 *
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualRecruitOrderInputServiceImpl
        extends WEB3MutualClientRequestService implements WEB3MutualRecruitOrderInputService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRecruitOrderInputServiceImpl.class);

    /**
     * �����M����W�������̓T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���M)��W�������́v�Q�ƁB<BR>
     * <BR>
     *==========================================================<BR>
     * �V�[�P���X�}�u(���M)���t�������́v: <BR>
     *     1.3((getProduct(long) �g�����M�������擾�o���Ȃ��ꍇ�A��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00377<BR>
     *==========================================================<BR>
     * <BR>
     *==========================================================<BR>
     * �V�[�P���X�}�u(���M)���t�������́v: <BR>
     *     1.12((is�V�X�e���戵 �戵�\�����`�F�b�N
     *     is�V�X�e���戵()�̖߂�l��false�̏ꍇ �i�戵�s�����j��O���X�B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00362<BR>
     *==========================================================<BR>
     * <BR>
     *==========================================================<BR>
     * �V�[�P���X�}�u(���M)���t�������́v: <BR>
     *     1.13((iis��W�\(Date) ����\�����`�F�b�N
     *     is��W�\()�̖߂�l��false�̏ꍇ�A��O���X���[�B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00589<BR>
     *==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40566483009E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //���M��W�������̓��N�G�X�g
        WEB3MutualApplyInputRequest l_mutualApplyInputRequest = null;
        if (l_request instanceof WEB3MutualApplyInputRequest)
        {
            //���N�G�X�g�f�[�^�̋�ۃf�[�^�^���u���M��W�������̓��N�G�X�g�v�̏ꍇ
            l_mutualApplyInputRequest = (WEB3MutualApplyInputRequest) l_request;
        }
        else
        {
            log.debug(
                "the parameter of method isn't WEB3MutualApplyInputRequest type");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1 validate( )
        l_mutualApplyInputRequest.validate();

        //1.2 get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.3 getProduct(long)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_product = null;
        try
        {
            l_product =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getProduct(
                    Long.parseLong(l_mutualApplyInputRequest.id));
        }
        //�g�����M�������擾�o���Ȃ��ꍇ�A��O���X���[����
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MutualFundProductRow l_mfProductRow =
            (MutualFundProductRow) l_product.getDataSourceObject();

        //1.4 to����(Row)
        WEB3MutualFundProduct l_mutualFundProduct =
            (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
            l_mfProductRow);

        //1.5.��d�����`�F�b�N
        //��d�����`�F�b�N
        //�P�D��������������擾
        //    �ȉ��̏����Œ����P�ʃe�[�u������������B
        //    [��������]
        StringBuffer l_strWhere = new StringBuffer();
        //    ����ID = ����.�⏕����.getAccountId() and
        l_strWhere.append("account_id = ?");
        //    �⏕����ID = ����.�⏕����.getSubAccountId() and
        l_strWhere.append(" and sub_account_id = ?");
        //    ����ID = ����.�g�����M����.getProductId() and
        l_strWhere.append(" and product_id = ?");
        //    ������� = OrderTypeEnum.�����M����W���� and
        l_strWhere.append(" and order_type = ?");
        //    ( ������� = OrderStatusEnum.��t��(�V�K����)
        //      or
        //      ������� = OrderStatusEnum.������(�V�K����)
        //      or
        //      ������� = OrderStatusEnum.�������s�i��������j )
        l_strWhere.append(" and order_status in (?, ?, ?) ");

        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        long l_lngProductId = l_mutualFundProduct.getProductId();
        long l_lngOrderType = OrderTypeEnum.MF_RECRUIT.intValue();
        long l_lngOrderStatusOne = OrderStatusEnum.ACCEPTED.intValue();
        long l_lngOrderStatusTwo = OrderStatusEnum.ORDERED.intValue();
        long l_lngOrderStatusThree = OrderStatusEnum.NOT_ORDERED.intValue();
        Object[] l_objQuerys =
            new Object[]{
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngProductId),
                new Long(l_lngOrderType),
                new Long(l_lngOrderStatusOne),
                new Long(l_lngOrderStatusTwo),
                new Long(l_lngOrderStatusThree)};
        List l_lisOrderUnitRow = null;
        try
        {
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();
            l_lisOrderUnitRow =
                l_queryProcessor.doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhere.toString(),
                    null,
                    l_objQuerys);

        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�D������������̑��݃`�F�b�N
        //    �P�D�Ŏ擾�������R�[�h������0�ȊO�̏ꍇ�A��O���X���[����B�i��d�����G���[�j
        if (l_lisOrderUnitRow != null && !l_lisOrderUnitRow.isEmpty())
        {
            log.debug("��d�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02648,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��d�����G���[�B");
        }

        //1.6 if�@@���N�G�X�g�f�[�^.�d�q���`�F�b�N�t���O == true
        WEB3GentradeProspectusResult l_validateBataResult = null;
        if(l_mutualApplyInputRequest.batoCheckFlag)
        {
            //1.6.1 validate�ژ_�����{��(String, String)
            WEB3GentradeBatoClientService l_bataService =
                (WEB3GentradeBatoClientService)Services.getService(
                    WEB3GentradeBatoClientService.class);
            l_validateBataResult =
                l_bataService.validateProspectus(
                    l_mutualApplyInputRequest.typeCode,
                    l_mutualFundProduct.getProductCode());
        }

        //1.7 getCommonOrderValidator( )
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.8 validate������t�\( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.9 reset�����R�[�h(String)
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_mutualFundProduct.getProductCode());

        //1.10 setTimestamp( )
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        //1.11�ꊇ�敪�擾
        //    �P�j���X�p�v���t�@@�����X����u���M��W�����ꊇ���M�敪�v���擾����B
        //    [���X�p�v���t�@@�����X�̎擾����]
        //       ���XID �F �擾�����⏕����.getMainAccount().getBranchId()
        //       �v���t�@@�����X�� �F mf.recruit.mq.send.div
        //       �v���t�@@�����X���̘A�� �F 1
        //  �����R�[�h�����́u�ꊇ���M����v
        //
        //�Q�j�ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ���ꊇ�敪�փZ�b�g����B
        //    ���X�p�v���t�@@�����X.���M��W�����ꊇ���M�敪 == �u�ꊇ���M����v
        boolean l_blnMfRecruitMqSendDiv = true;
        BranchPreferencesRow l_row = null;
        try
        {
            l_row = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_subAccount.getMainAccount().getBranch().getBranchId(),
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_row == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_row.getValue()))
        {
            l_blnMfRecruitMqSendDiv = true;
        }
        else
        {
            l_blnMfRecruitMqSendDiv = false;
        }

        // 1.12 get���M������(OrderTypeEnum, boolean)
        //������ʁF�@@OrderTypeEnum.�����M����W����
        //�ꊇ�敪�F�@@�擾�����ꊇ�敪
        Date l_datOrderBizDate =
            WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(
                OrderTypeEnum.MF_RECRUIT,
                l_blnMfRecruitMqSendDiv);

        log.debug("l_datOrderBizDate = " + l_datOrderBizDate);

        //1.13 validate����\�ڋq(�ڋq, Timestamp)
        WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

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
        //1.14 is�V�X�e���戵( )
        //�戵�\�����`�F�b�N  is�V�X�e���戵()�̖߂�l��false�̏ꍇ �i�戵�s�����j��O���X
        if (!l_mutualFundProduct.isSystemHandling())
        {
            log.debug(" __�戵�s�����G���[__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "__�戵�s�����G���[__");
        }

        //1.15 is��W�\(Date)
        //����\�����`�F�b�N is��W�\()�̖߂�l��false�̏ꍇ�A��O���X���[
        boolean l_blnRecruitPossible =
            l_mutualFundProduct.isRecruitPossible(l_datOrderBizDate);
        if (!l_blnRecruitPossible)
        {
            log.debug("is��W�\(Date)�� false ��Ԃ��ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00589,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��W���~�����ł��B");
        }

        //1.16 validate�ً}��~(�g�����M����, String)
        //�g�����M�����F �擾�����g�����M����
        //�����敪�F �h5�F��W�h
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        try
        {
            l_validationsCheck.validateEmergencyStop(
                l_mutualFundProduct,
                WEB3ProcessDivDef.RECRUIT);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("�ً}��~�G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ً}��~�G���[");
        }
        //1.17 validate������t�\( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        // is�ē�������()
        if (l_mutualFundProduct.isPlowbackProduct())
        {
            // is�ݓ������J��()
            if (!l_genMainAccount.isRuitoAccountOpen())
            {
                    log.error(" __�ݓ��������J�݃G���[__");
                    throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            " __�ݓ��������J�݃G���[__");
            }
        }
        //1.20 get�����M�����t�\�z(�⏕����, Date, OrderTypeEnum)
        //�m�����n
        //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
        //��n���F�@@�擾�����������̗��c�Ɠ�
        //������ʁF�@@203�F�����M����W����

        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        //��n���F�@@�擾�����������̗��c�Ɠ�
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        Date l_datDeliveryDate = l_gentradeBizDate.roll(1);

        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)l_subAccount;

        double l_dblBuyPossibleAmount =
            l_tpTradingPowerService.getMutualFundBuyTradingPower(
                l_gentradeSubAccount,
                l_datDeliveryDate,
                OrderTypeEnum.MF_RECRUIT);

        //1.21 get��W�I����( )
        Date l_datRecruitEndDate = l_mutualFundProduct.getRecruitEndDate();

        //1.22 get��W�I�����iSONAR�j( )
        Date l_datApplyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();

        //1.23 get�ڋq()
        //�g���A�J�E���g�}�l�[�W���擾����
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        //�|�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����
        try
        {
        l_genMainAccount =
            (WEB3GentradeMainAccount) l_web3GentradeAccountManager.getMainAccount(
                l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }


        //1.24 is��������J��(Date, �⏕����)
        boolean l_blnIsSpecialAccountEstablished =
            l_genMainAccount.isSpecialAccountEstablished(
                l_datRecruitEndDate,
                l_subAccount);

        //1.25 if is��������J��() == true
        //-�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����A�ڋq�I�u�W�F�N�g���擾����B
        //�mget�ڋq�ɓn���p�����^�n
        //����ID�F �擾�����⏕����.getAccountId()�̖߂�l
        //-�擾�����ڋq�I�u�W�F�N�g.is��������J��()���R�[������B
        //�mis��������J�݂ɓn���p�����^�n
        //�⏕�����F �擾�����⏕�����I�u�W�F�N�g
        //��n���F �擾������W�I����
        String[] l_strArrAccountDivDefs = null;
        boolean l_blnisStockType = l_mutualFundProduct.isStockType();
        if(l_blnIsSpecialAccountEstablished)
        {
            //1.25.1 is�����^( )
            if(l_blnisStockType)
            {
                //-is��������J��()�� true ���� is�����^()��true��Ԃ��ꍇ�́A
                //�h0�F��ʁh�Ɓh1�F����h��ݒ肷��B
                l_strArrAccountDivDefs = new String[2];
                l_strArrAccountDivDefs[0] = WEB3AccountDivDef.NORMAL;
                l_strArrAccountDivDefs[1] = WEB3AccountDivDef.SPECIAL;
            }
            else
            {
                //-is��������J��()�� true ���� is�����^()�� false��Ԃ��ꍇ�A���邢��
                l_strArrAccountDivDefs = new String[1];
                l_strArrAccountDivDefs[0] = WEB3AccountDivDef.NORMAL;
            }
        }
        else
        {
            //is��������J��()�� false ��Ԃ��ꍇ�́A�h0�F��ʁh��ݒ肷��B
            l_strArrAccountDivDefs = new String[1];
            l_strArrAccountDivDefs[0] = WEB3AccountDivDef.NORMAL;
        }

        //1.26 get�ʉ݃R�[�h( )
        String l_strCurrencyCode = l_mutualFundProduct.getCurrencyCode();

        //1.27 get��W���z( )
        double l_dblRecruitConstantValue =
            l_mutualFundProduct.getRecruitConstantValue();

        //1.28 get�w����@@�i��W�j
        String l_strRecruitSpecityDiv = l_mutualFundProduct.getRecruitSpecityDiv();

        //1.29 get�P�ʌ����i��W�j
        String l_strRecruitUnitQty = l_mutualFundProduct.getRecruitUnitQty();

        //1.30 get�Œ�����i��W�j
        String l_strRecruitMinQty = l_mutualFundProduct.getRecruitMinQty();

        //1.31 get�P�ʋ��z�i��W�j
        String l_strRecruitUnitAmt = l_mutualFundProduct.getRecruitUnitAmt();

        //1.32 get�Œ���z�i��W�j
        String l_strRecruitMinAmt = l_mutualFundProduct.getRecruitMinAmt();

        //1.33 get���ρi��W�j
        String l_strRecruitSettlementDiv =
            l_mutualFundProduct.getRecruitSettlementDiv();

        //1.34 createResponse( )
        WEB3MutualApplyInputResponse l_response =
            (WEB3MutualApplyInputResponse)l_mutualApplyInputRequest.createResponse();

        //1.35 �v���p�e�B�Z�b�g
        //[���M��W�������̓��X�|���X�ɐݒ肷��l]

        //���t�\���z�F �擾�������t�\�z
        l_response.tradingPower =
            WEB3StringTypeUtility.formatNumber(l_dblBuyPossibleAmount);

        //�����R�[�h�F �擾�����g�����M����.getProductCode()�̖߂�l
        l_response.mutualProductCode = l_mutualFundProduct.getProductCode();

        //�������F �擾�����g�����M����.get������()�̖߂�l
        l_response.mutualProductName = l_mutualFundProduct.getMutualProductName();

        //��W���z�ʉ݃R�[�h�F �擾�����g�����M����.get�ʉ݃R�[�h()�̖߂�l
        l_response.constantValueCurrencyCode = l_strCurrencyCode;

        //��W���z�F �擾�����g�����M����.get��W���z()�̖߂�l
        l_response.applyConstantValue =
            WEB3StringTypeUtility.formatNumber(l_dblRecruitConstantValue);

        //�����敪�ꗗ�F
        l_response.taxTypeList = l_strArrAccountDivDefs;

        //�w����@@�ꗗ�F
        //(*) �擾�����g�����M����.get�w����@@�i��W�j()�̖߂�l��
        //�h0�F�I���w��h �̏ꍇ�́h3�F���z�h�Ɓh4�F�����h��ݒ肷��B
        if (WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE.equals(l_strRecruitSpecityDiv))
        {
            l_response.specifyDivList =
                new String[] {
                    WEB3SellDivDef.COUNT_DESIGNATE,
                    WEB3SellDivDef.MONEY_DESIGNATE };
        }

        //(*) �擾�����g�����M����.get�w����@@�i��W�j()�̖߂�l��
        //�h3�F���z�w��h �̏ꍇ�́h3�F���z�h��ݒ肷��B
        else if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strRecruitSpecityDiv))
        {
            l_response.specifyDivList =
                new String[] { WEB3SellDivDef.MONEY_DESIGNATE };
        }

        //(*) �擾�����g�����M����.get�w����@@�i��W�j()�̖߂�l��
        //�h4�F�����w��h �̏ꍇ�́h4�F�����h��ݒ肷��B
        else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strRecruitSpecityDiv))
        {
            l_response.specifyDivList =
                new String[] { WEB3SellDivDef.COUNT_DESIGNATE };
        }

        //��W���P�ʌ����F �擾�����g�����M����.get�P�ʌ����i��W�j()�� �߂�l��ݒ肷��B
        l_response.applyUnitQty = l_strRecruitUnitQty;

        //��W���Œ�����F �擾�����g�����M����.get�Œ�����i��W�j()�� �߂�l��ݒ肷��B
        l_response.applyMinQty = l_strRecruitMinQty;

        //��W���P�ʋ��z�F �擾�����g�����M����.get�P�ʋ��z�i��W�j()�� �߂�l��ݒ肷��B
        l_response.applyUnitAmt = l_strRecruitUnitAmt;

        //��W���Œ���z�F �擾�����g�����M����.get�Œ���z�i��W�j()�� �߂�l��ݒ肷��B
        l_response.applyMinAmt = l_strRecruitMinAmt;

        //���ϕ��@@�ꗗ�F
        //(*) �擾�����g�����M����.get���ρi��W�j()�̖߂�l���h0�F�I���w��h �̏ꍇ��
        //�h1�F�~�݁h�Ɓh2�F�O�݁h��ݒ肷��B
        if (WEB3BuySellSettlementDivDef.SELECT_DESIGNATE.equals(l_strRecruitSettlementDiv))
        {
            l_response.settleDivList =
                new String[] {
                    WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY,
                    WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY };
        }

        //(*) �擾�����g�����M����.get���ρi��W�j()�̖߂�l���h1�F�~�݁h �̏ꍇ��
        //�h1�F�~�݁h��ݒ肷��B
        else if (WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(l_strRecruitSettlementDiv))
        {
            l_response.settleDivList =
                new String[] { WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY };
        }

        //(*) �擾�����g�����M����.get���ρi��W�j()�̖߂�l���h2�F�O�݁h �̏ꍇ��
        //�h2�F�O�݁h��ݒ肷��B
        else if (WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY.equals(l_strRecruitSettlementDiv))
        {
            l_response.settleDivList =
                new String[] { WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY };
        }

        //�������F �擾����������
        l_response.orderBizDate = l_datOrderBizDate;

        //�����F �擾����������
        l_response.executionTimestamp = l_datOrderBizDate;

        //��n���F
        //�擾�����ꊇ�敪==true�Ȃ�擾������W�I����(SONAR)�A
        //�擾�����ꊇ�敪==false�Ȃ�擾������W�I����
        if (l_blnMfRecruitMqSendDiv)
        {
            l_response.deliveryDate = l_datApplyAbleEndDateSonar;
        }
        else
        {
            l_response.deliveryDate = l_datRecruitEndDate;
        }

        //�ژ_�����{���`�F�b�N���ʁF
        //���N�G�X�g.�d�q���`�F�b�N�t���O==false�̏ꍇ�́Anull�Z�b�g
        //���N�G�X�g.�d�q���`�F�b�N�t���O==true�̏ꍇ�Avalidate�ژ_�����{��()�̌��ʃI�u�W�F�N�g���Z�b�g����B
        l_response.prospectusResult = l_validateBataResult;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
