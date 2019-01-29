head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccReservationIfoOrderUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�\�񒍕��X�V�T�[�r�XImpl(WEB3ToSuccReservationIfoOrderUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/11 ��іQ(���u) �V�K�쐬 ���f��No.254, �c�a�X�V�d�lNO.44,49,57,60
*/

package webbroker3.triggerorder.base.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨OP�\�񒍕��X�V�T�[�r�XImpl)<BR>
 * �敨OP�\�񒍕��X�V�T�[�r�X�̎����N���X�B<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3ToSuccReservationIfoOrderUpdateServiceImpl implements WEB3ToSuccReservationIfoOrderUpdateService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccReservationIfoOrderUpdateServiceImpl.class);

    /**
     * @@roseuid 4348D9800280
     */
    public WEB3ToSuccReservationIfoOrderUpdateServiceImpl()
    {

    }

    /**
     * (insert�\�񒍕�����)<BR>
     * �ŐV�̐敨OP�\�񒍕��P�ʃe�[�u���̓��e���A<BR>
     * �敨OP�\�񒍕��������P���R�[�h�쐬���o�^����B<BR>
     * <BR>
     * �P�j�@@�敨OP�\�񒍕��P�ʃe�[�u������Y�����R�[�h���擾����B<BR>
     * <BR>
     * �@@�敨OP�\�񒍕��P�ʃe�[�u�����A�����̒���ID�ɊY�����郌�R�[�h���擾����B<BR>
     * <BR>
     * �Q�j�@@�擾�����敨OP�\�񒍕��P�ʃI�u�W�F�N�g���A<BR>
     * �@@�@@�@@�敨OP�\�񒍕��������P���R�[�h�o�^����B<BR>
     * <BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�i�A���j�����o�^_�敨OP�\�񒍕������e�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�敨OP�\�񒍕��P��.����ID���Z�b�g�j<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43378D0602A2
     */
    public void insertReserveOrderAction(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertReserveOrderAction(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�P�j�@@�敨OP�\�񒍕��P�ʃe�[�u������Y�����R�[�h���擾����B
            //�敨OP�\�񒍕��P�ʃe�[�u�����A�����̒���ID�ɊY�����郌�R�[�h���擾����B
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(l_lngOrderId);

            //�Q�j�@@�擾�����敨OP�\�񒍕��P�ʃI�u�W�F�N�g���A�敨OP�\�񒍕��������P���R�[�h�o�^����B
            RsvIfoOrderActionParams l_rsvIfoOrderActionParams = new RsvIfoOrderActionParams();

            //�����h�c: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setAccountId(l_rsvIfoOrderUnitRow.getAccountId());

            //�⏕�����h�c: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setSubAccountId(l_rsvIfoOrderUnitRow.getSubAccountId());

            //�����h�c: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setOrderId(l_rsvIfoOrderUnitRow.getOrderId());

            //��������: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setQuantity(l_rsvIfoOrderUnitRow.getQuantity());

            //�w�l: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            if (!l_rsvIfoOrderUnitRow.getLimitPriceIsNull())
            {
                l_rsvIfoOrderActionParams.setLimitPrice(l_rsvIfoOrderUnitRow.getLimitPrice());
            }

            //�P�������l: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            if (!l_rsvIfoOrderUnitRow.getPriceAdjustValueIsNull())
            {
                l_rsvIfoOrderActionParams.setPriceAdjustValue(l_rsvIfoOrderUnitRow.getPriceAdjustValue());
            }

            //�����������t: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setExpirationDate(l_rsvIfoOrderUnitRow.getExpirationDate());

            //�������: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setOrderStatus(l_rsvIfoOrderUnitRow.getOrderStatus());

            //�����L�����: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setOrderOpenStatus(l_rsvIfoOrderUnitRow.getOrderOpenStatus());

            //�����敪: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setExpirationStatus(l_rsvIfoOrderUnitRow.getExpirationStatus());

            //��������ԍ�: �敨OP�\�񒍕��P�ʃe�[�u��.���������ŏI�ʔ�
            l_rsvIfoOrderActionParams.setOrderActionSerialNo(l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());

            //�T�Z��n���: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            if (!l_rsvIfoOrderUnitRow.getEstimatedPriceIsNull())
            {
                l_rsvIfoOrderActionParams.setEstimatedPrice(l_rsvIfoOrderUnitRow.getEstimatedPrice());
            }

            //�����o�H�敪: �����\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //����҂h�c: �敨OP�\�񒍕��P�ʃe�[�u���̓�����
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                l_rsvIfoOrderActionParams.setTraderId(l_rsvIfoOrderUnitRow.getTraderId());
            }

            //���҃R�[�h�iSONAR�j:�敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());

            //���������敪 :�敨OP�\�񒍕��P�ʃe�[�u���̓�����
            l_rsvIfoOrderActionParams.setExpirationDateType(l_rsvIfoOrderUnitRow.getExpirationDateType());

            //�쐬���t: ���ݎ����iSystemTimestamp�j
            l_rsvIfoOrderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //�X�V���t: ���ݎ����iSystemTimestamp�j
            l_rsvIfoOrderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_rsvIfoOrderActionParams);

            log.exiting(STR_METHOD_NAME);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
    }

    /**
     * (invalidate�\�񒍕��P��)<BR>
     * �iinvalidateOrderUnit�j<BR>
     * <BR>
     * �敨OP�\�񒍕��P�ʍs������������B<BR>
     * <BR>
     * �P�j�@@�����̐敨OP�\�񒍕��P�ʍs������������B�iupdate����j<BR>
     * <BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�A�����������iNG�j_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�敨OP�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@this.insert�敨OP�\�񒍕�����(�����̐敨OP�\�񒍕��P�ʍs.����ID)��<BR>
     * �@@�R�[������B<BR>
     * @@param l_rsvIfoOrderUnitRow - (�\�񒍕��P�ʍs)<BR>
     * �敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B
     * @@param l_strErrorCode - (�����G���[�R�[�h)<BR>
     * �����G���[�R�[�h�B<BR>
     * �i�G���[�����̓��肪�\��ErrorInfo.error_code���Z�b�g�A<BR>
     * �����G���[�ȊO�Ŏ�������ꍇ�Anull���Z�b�g�j
     * @@throws WEB3BaseException
     * @@roseuid 4337904800BE
     */
    public void invalidateOrderUnit(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow,
        String l_strErrorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "invalidateOrderUnit(RsvIfoOrderUnitRow, String)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnitRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����̐敨OP�\�񒍕��P�ʍs������������B�iupdate����j
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

        //DB�X�V�d�l�u�A�����������iNG�j_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
        //���������ŏI�ʔ�: �i�����l�j�{�P
        int l_intLastOrderActionSerialNo = l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo() + 1;
        l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(l_intLastOrderActionSerialNo);

        //�������:
        //�����̔����G���[�R�[�h���w�肳��Ă���ꍇ�F
        //6:�������s�i�V�K�����j
        // �iOrderStatusEnum�ɂĒ�`�j
        //�����̔����G���[�R�[�h���w�肳��Ă��Ȃ��ꍇ�F�i�����l�j
        if (!WEB3StringTypeUtility.isEmpty(l_strErrorCode))
        {
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
        }

        //�����L�����:
        //2:�N���[�Y�iOrderOpenStatusEnum�ɂĒ�`�j
        l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

        //�����敪:
        //3:�}�[�P�b�g���ہiOrderExpirationStatusEnum�ɂĒ�`�j
        l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);

        //�����G���[�R�[�h:
        //�����̔����G���[�R�[�h���w�肳��Ă���ꍇ�F
        //�����G���[�R�[�h���Z�b�g
        //�i* �G���[�����̓��肪�\��ErrorInfo.error_code���Z�b�g�j
        // �����̔����G���[�R�[�h���w�肳��Ă��Ȃ��ꍇ�F�i�����l�j
        if (!WEB3StringTypeUtility.isEmpty(l_strErrorCode))
        {
            l_rsvIfoOrderUnitParams.setOrderErrorCode(l_strErrorCode);
        }

        //�X�V���t: ���ݓ���
        l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);

            //�Q�j�@@�敨OP�\�񒍕��������쐬����B
            //this.insert�敨OP�\�񒍕�����(�����̐敨OP�\�񒍕��P�ʍs.����ID)���R�[������B
            this.insertReserveOrderAction(l_rsvIfoOrderUnitParams.getOrderId());

            log.exiting(STR_METHOD_NAME);
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
    }

    /**
     * (invalidateAll�\�񒍕��P��)<BR>
     * �iinvalidateAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď���������B<BR>
     * �������������s���Ȃ������i�L���ȗ\�񒍕��Ȃ��j�ꍇ�A<BR>
     * �@@false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �P�j�@@�L���Ȑ敨OP�\�񒍕��P�ʃ��R�[�h���擾����B<BR>
     * �@@�@@�@@this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j�@@this.invalidate�\�񒍕��P��(�����Ώۂ̗v�f, null)���R�[������B<BR>
     * <BR>
     * �R�j�@@true��ԋp����B<BR>
     * @@param l_lngParentOrderId - �i�e�����̒���ID�j<BR>
     * �e�����̒���ID�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4344AA59016F
     */
    public boolean invalidateAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "invalidateAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�L���Ȑ敨OP�\�񒍕��P�ʃ��R�[�h���擾����B
        //this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B
        List l_lisOrderUnitRows = this.getOpenReserveIfoOrderUnits(l_lngParentOrderId);

        //�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B
        //�Q�|�P�j�@@this.invalidate�\�񒍕��P��(�����Ώۂ̗v�f, null)���R�[������B
        int l_intCnt = l_lisOrderUnitRows.size();

        for (int i = 0; i < l_intCnt; i++)
        {
            this.invalidateOrderUnit((RsvIfoOrderUnitRow)l_lisOrderUnitRows.get(i), null);
        }

        //�R�j�@@true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (cancel�\�񒍕��P��)<BR>
     * �icancelOrderUnit�j<BR>
     * �����̐敨OP�\�񒍕��P�ʍs���������B<BR>
     * <BR>
     * �P�j�@@�����̐敨OP�\�񒍕��P�ʍs���������B�iupdate����j<BR>
     * <BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�i�A���j�������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�敨OP�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@this.insert�\�񒍕�����(�����̐敨OP�\�񒍕��P�ʍs.����ID)���R�[������B<BR>
     * @@param l_rsvIfoOrderUnitRow - (�敨OP�\�񒍕��P�ʍs)<BR>
     * �敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A1D6802AF
     */
    public void cancelOrderUnit(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "cancelOrderUnit(RsvIfoOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnitRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����̐敨OP�\�񒍕��P�ʍs���������B�iupdate����j
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

        try
        {
            //DB�X�V�d�l�u�i�A���j�������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            //����҂h�c(trader_id) :
            //���O�C���Z�L�����e�B�T�[�r�X���擾�\�ȏꍇ�F
            //�Z�b�V��������擾�������O�C��ID�ɊY�����鈵��.�����ID
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_loginId = l_opLoginSecurityService.getLoginInfo().getLoginId();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

            try
            {
                Trader l_trader = l_finObjMgr.getTraderByLoginId(l_loginId);
                l_rsvIfoOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            catch (NotFoundException l_ex)
            {
                //���擾�ł��Ȃ������ꍇ�́Anull���Z�b�g
                l_rsvIfoOrderUnitParams.setTraderId(null);
            }

            //�����o�H�敪(order_root_div) :
            //���O�C���Z�L�����e�B�T�[�r�X���擾�\�ȏꍇ�F�Z�b�V��������擾���������o�H�敪
            l_rsvIfoOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
        }
        catch (IllegalSessionStateException l_ex)
        {
            //����҂h�c(trader_id) :
            //���O�C���Z�L�����e�B�T�[�r�X���擾�s�ȏꍇ�F�i�����l�j
            //�����o�H�敪(order_root_div) :
            //���O�C���Z�L�����e�B�T�[�r�X���擾�s�ȏꍇ�F�i�����l�j
        }

        //���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j + 1
        l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo() + 1);

        //�������(order_status) :14:�����ρi��������j
        l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);

        //�����L�����(order_open_status) :2:�N���[�Y
        l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

        //�X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
        l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);

            //�Q�j�@@�敨OP�\�񒍕��������쐬����B
            //�@@this.insert�\�񒍕�����(�����̐敨OP�\�񒍕��P�ʍs.����ID)���R�[������B
            this.insertReserveOrderAction(l_rsvIfoOrderUnitRow.getOrderId());

            log.exiting(STR_METHOD_NAME);
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
    }

    /**
     * (cancelAll�\�񒍕��P��)<BR>
     * �icancelAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď������B<BR>
     * ������������s���Ȃ������i�L���ȗ\�񒍕��Ȃ��j�ꍇ�A<BR>
     * �@@false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �P�j�@@�L���Ȑ敨OP�\�񒍕��P�ʃ��R�[�h���擾����B<BR>
     * �@@�@@�@@this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j�@@this.cancel�\�񒍕��P��(�����Ώۂ̗v�f)���R�[������B<BR>
     * <BR>
     * �R�j�@@true��ԋp����B<BR>
     * @@param l_lngParentOrderId - �i�e�����̒���ID�j<BR>
     * �e�����̒���ID�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 433A18D20109
     */
    public boolean cancelAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "cancelAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�L���Ȑ敨OP�\�񒍕��P�ʃ��R�[�h���擾����B
        //this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B
        List l_lisOrderUnitRows = this.getOpenReserveIfoOrderUnits(l_lngParentOrderId);

        //�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B
        //�Q�|�P�j�@@this.cancel�\�񒍕��P��(�����Ώۂ̗v�f)���R�[������B
        int l_intCnt = l_lisOrderUnitRows.size();

        for (int i = 0; i < l_intCnt; i++)
        {
            this.cancelOrderUnit((RsvIfoOrderUnitRow)l_lisOrderUnitRows.get(i));
        }

        //�R�j�@@true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (expire�\�񒍕��P��)<BR>
     * �iexpireOrderUnit�j<BR>
     * �����̐敨OP�\�񒍕��P�ʍs������������B<BR>
     * <BR>
     * �P�j�@@�����̐敨OP�\�񒍕��P�ʍs������������B�iupdate����j<BR>
     * <BR>
     * �@@�@@DB�X�V�d�l<BR>
     * �@@�@@�u��OP�o���I��_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@�@@���Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�敨OP�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�@@�@@this.insert�\�񒍕�����(�����̐敨OP�\�񒍕��P�ʍs.����ID)���R�[������B<BR>
     * @@param l_rsvIfoOrderUnitRow - (�敨OP�\�񒍕��P�ʍs)<BR>
     * �敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g<BR>
     * <BR>
     * ��DDL��莩�����������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A20720399
     */
    public void expireOrderUnit(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireOrderUnit(RsvIfoOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnitRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        try
        {
            //�P�j�@@�����̐敨OP�\�񒍕��P�ʍs������������B�iupdate����j
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

            //DB�X�V�d�l�u��OP�o���I��_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            //���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j�{�P
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo() + 1);

            //�����L�����(order_open_status) : 2:�N���[�Y�iOrderOpenStatusEnum�ɂĒ�`�j
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

            //�����敪(expiration_status) : 2:�I���iOrderExpirationStatusEnum�ɂĒ�`�j
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);

            //�X�V���t(last_updated_timestamp) : ���ݓ����iGtlUtils.getSystemTimestamp()�j
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);

            //�Q�j�@@�敨OP�\�񒍕��������쐬����B
            //this.insert�\�񒍕�����(�����̐敨OP�\�񒍕��P�ʍs.����ID)���R�[������B
            this.insertReserveOrderAction(l_rsvIfoOrderUnitRow.getOrderId());

            log.exiting(STR_METHOD_NAME);
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
    }

    /**
     * (expireAll�\�񒍕��P��)<BR>
     * �iexpireAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��P�ʂ�<BR>
     * �S�Ď���������B<BR>
     * �������������s���Ȃ������i�L���ȗ\�񒍕��Ȃ��j�ꍇ�A<BR>
     * �@@false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * �P�j�@@�L���Ȑ敨OP�\�񒍕��P�ʃ��R�[�h���擾����B<BR>
     * �@@�@@�@@this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B<BR>
     * �@@�Q�|�P�j�@@this.expire�\�񒍕��P��(�����Ώۂ̗v�f)���R�[������B<BR>
     * <BR>
     * �R�j�@@true��ԋp����B<BR>
     * @@param l_lngParentOrderId - �i�e�����̒���ID�j<BR>
     * �e�����̒���ID�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 433A2072039B
     */
    public boolean expireAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�L���Ȑ敨OP�\�񒍕��P�ʃ��R�[�h���擾����B
        //this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B
        List l_lisOrderUnitRows = this.getOpenReserveIfoOrderUnits(l_lngParentOrderId);

        //�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B
        //�Q�|�P�j�@@this.expire�\�񒍕��P��(�����Ώۂ̗v�f)���R�[������B
        int l_intCnt = l_lisOrderUnitRows.size();

        for (int i = 0; i < l_intCnt; i++)
        {
            this.expireOrderUnit((RsvIfoOrderUnitRow)l_lisOrderUnitRows.get(i));
        }

        //�R�j�@@true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get�L���\�񒍕��P�ʈꗗ)<BR>
     * �igetOpenReserveIfoOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�L���Ȑ敨OP�\�񒍕��P�ʍs�̔z���ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�@@�ȉ��̏������w�肵�āA�y�敨OP�\�񒍕��P�ʃe�[�u���z����������B<BR>
     * <BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@������������<BR>
     * <BR>
     * �@@�@@�@@�@@�e�����̒���ID = ����.�e�����̒���ID<BR>
     * �@@�@@���@@�����L����� = "�I�[�v��"<BR>
     * <BR>
     * �@@�@@���u�e�������A�ԁv�ŏ����\�[�g�w�肷��B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 433A1C640157
     */
    public List getOpenReserveIfoOrderUnits(long l_lngParentOrderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOpenReserveIfoOrderUnits(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�P�j�@@DB����
            //�ȉ��̏������w�肵�āA�y�敨OP�\�񒍕��P�ʃe�[�u���z����������B
            //������������
            //�e�����̒���ID = ����.�e�����̒���ID ���@@�����L����� = "�I�[�v��"
            //���u�e�������A�ԁv�ŏ����\�[�g�w�肷��B
            String l_strWhere = " parent_order_id = ? and order_open_status = ? ";
            Object[] l_objs = {new Long(l_lngParentOrderId), OrderOpenStatusEnum.OPEN};

            String l_strSort = "serial_no_in_parent asc";
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_objs);

            if (l_lisRsvIfoOrderUnitRows != null && !l_lisRsvIfoOrderUnitRows.isEmpty())
            {
                //�Q�j�@@�������ʂ�ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_lisRsvIfoOrderUnitRows;
            }

            //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
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
    }

    /**
     * (deleteAll�\�񒍕��P��)<BR>
     * �ideleteAllOrderUnit�j<BR>
     * �����́i�e�����j����ID�ɊY������\�񒍕��f�[�^��<BR>
     * �S�č폜����B<BR>
     * ���폜�������s���Ȃ������i�\�񒍕��Ȃ��j�ꍇ�A<BR>
     * �@@false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * ���f�[�^�x�[�Xdelete�́A�S�ăN�G���v���Z�b�T���g�p���A<BR>
     * �@@SQL���𔭍s���邱�Ƃōs���B<BR>
     * <BR>
     * �P�j�@@�敨OP�\�񒍕��P�ʃ��R�[�h���擾����B<BR>
     * �@@�@@�@@this.get�\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�Y���\�񒍕����������ɑ΂���ԍϒ����̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�i�����Ώۂ̗v�f.�A����������敪=="�敨�ԍρi�����c�j"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�܂��́A�����Ώۂ̗v�f.�A����������敪=="OP�ԍρi�����c�j"�j�A<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�\�񌚋ʕԍώw����f�[�^��<BR>
     * �@@�@@�@@�@@�@@�@@�y�敨OP�\�񌚋ʕԍώw����e�[�u���z���delete����B<BR>
     * �@@�@@�@@�@@�@@�@@�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�\�񒍕������f�[�^��<BR>
     * �@@�@@�@@�@@�@@�@@�y�敨OP�\�񒍕������e�[�u���z���delete����B<BR>
     * �@@�@@�@@�@@�@@�@@�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�\�񒍕��P�ʂ̃f�[�^��<BR>
     * �@@�@@�@@�@@�@@�@@�y�敨OP�\�񒍕��P�ʃe�[�u���z���delete����B<BR>
     * �@@�@@�@@�@@�@@�@@�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B<BR>
     * <BR>
     * �R�j�@@true��ԋp����B<BR>
     * @@param l_lngParentOrderId - �i�e�����̒���ID�j<BR>
     * �e�����̒���ID�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4355E2000186
     */
    public boolean deleteAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�敨OP�\�񒍕��P�ʃ��R�[�h���擾����B
        //this.get�\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B
        List l_lisOrderUnitRows = this.getReserveIfoOrderUnits(l_lngParentOrderId);

        //�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        try
        {
            //�Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B
            int l_intCnt = l_lisOrderUnitRows.size();

            for (int i = 0; i < l_intCnt; i++)
            {
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_lisOrderUnitRows.get(i);

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                String l_strWhere = " order_id = ? ";
                Object[] l_objs = {new Long(l_rsvIfoOrderUnitRow.getOrderId())};

                //�Q�|�P�j�@@�Y���\�񒍕����������ɑ΂���ԍϒ����̏ꍇ
                //�i�����Ώۂ̗v�f.�A����������敪=="�敨�ԍρi�����c�j"
                //�܂��́A�����Ώۂ̗v�f.�A����������敪=="OP�ԍρi�����c�j"�j�A
                if (WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(
                        l_rsvIfoOrderUnitRow.getReserveOrderTradingType())
                    || WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
                        l_rsvIfoOrderUnitRow.getReserveOrderTradingType()))
                {
                    //�\�񌚋ʕԍώw����f�[�^��
                    //�y�敨OP�\�񌚋ʕԍώw����e�[�u���z���delete����B
                    //�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B
                    l_queryProcessor.doDeleteAllQuery(
                        RsvIfoClosingContractSpecRow.TYPE,
                        l_strWhere,
                        l_objs);
                }

                //�Q�|�Q�j�@@�\�񒍕������f�[�^��
                //�y�敨OP�\�񒍕������e�[�u���z���delete����B
                //�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B
                l_queryProcessor.doDeleteAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    l_strWhere,
                    l_objs);

                //�Q�|�R�j�@@�\�񒍕��P�ʂ̃f�[�^��
                //�y�敨OP�\�񒍕��P�ʃe�[�u���z���delete����B
                //�폜�L�[�ɂ́A�����Ώۂ̗v�f.����ID ���w�肷��B
                l_queryProcessor.doDeleteAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objs);
            }

            //�R�j�@@true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
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
    }

    /**
     * (get�\�񒍕��P�ʈꗗ)<BR>
     * �igetReserveIfoOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�敨OP�\�񒍕��P�ʍs�̔z���ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�@@�ȉ��̏������w�肵�āA�y�敨OP�\�񒍕��P�ʃe�[�u���z����������B<BR>
     * <BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@������������<BR>
     * <BR>
     * �@@�@@�@@�@@�e�����̒���ID = ����.�e�����̒���ID<BR>
     * <BR>
     * �@@�@@���u�e�������A�ԁv�ŏ����\�[�g�w�肷��B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getReserveIfoOrderUnits(long l_lngParentOrderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getReserveIfoOrderUnits(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�P�j�@@DB����
            //�ȉ��̏������w�肵�āA�y�敨OP�\�񒍕��P�ʃe�[�u���z����������B
            //������������
            //�e�����̒���ID = ����.�e�����̒���ID
            //���u�e�������A�ԁv�ŏ����\�[�g�w�肷��B
            String l_strWhere = " parent_order_id = ?";
            Object[] l_objs = {new Long(l_lngParentOrderId)};
            String l_strSort = "serial_no_in_parent asc";

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_objs);

            if (l_lisRsvIfoOrderUnitRows != null && !l_lisRsvIfoOrderUnitRows.isEmpty())
            {
                //�Q�j�@@�������ʂ�ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_lisRsvIfoOrderUnitRows;
            }

            //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
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
    }

    /**
     * (update�\�񒍕��f�[�^)<BR>
     * �w�肳�ꂽ�����I�u�W�F�N�g���g�p���A<BR>
     * QueryProcessor�ɂ��\�񒍕��f�[�^�ނ̍X�V���s���B<BR>
     * <BR>
     * �P�j�@@�����̐敨OP�\�񒍕��P��Row�I�u�W�F�N�g�̓��e��<BR>
     * �@@�敨OP�\�񒍕��P�ʃe�[�u����update����B<BR>
     * <BR>
     * �Q�j�@@�����̐敨OP�\�񒍕�����Row��"null"�łȂ��ꍇ�̂݁A<BR>
     * �@@�����̐敨OP�\�񒍕�����Row�I�u�W�F�N�g�̓��e��<BR>
     * �@@�敨OP�\�񒍕������e�[�u����insert����B<BR>
     * <BR>
     * @@param l_rsvIfoOrderUnitRow - (�敨OP�\�񒍕��P�ʍs)<BR>
     * �敨OP�\�񒍕��P�ʍs<BR>
     * @@param l_rsvIfoOrderActionRow - (�敨OP�\�񒍕������s)<BR>
     * �敨OP�\�񒍕������s<BR>
     * @@throws WEB3BaseException
     */
    public void updateReserveOrderData(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow,
        RsvIfoOrderActionRow l_rsvIfoOrderActionRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateReserveOrderData(RsvIfoOrderUnitRow, RsvIfoOrderActionRow)";
        log.entering(STR_METHOD_NAME);
        try
        {
            // �f�[�^���X�V����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // �P�j�@@�����̐敨OP�\�񒍕��P��Row�I�u�W�F�N�g�̓��e��
            //�敨OP�\�񒍕��P�ʃe�[�u����update����B
            String l_strWhere = "order_id = ? ";
            Object[] l_objUpdWhere = {new Long(l_rsvIfoOrderUnitRow.getOrderId())};

            l_queryProcessor.doUpdateQuery(
                l_rsvIfoOrderUnitRow,
                l_strWhere,
                l_objUpdWhere);

            // �Q�j�@@�����̐敨OP�\�񒍕�����Row��"null"�łȂ��ꍇ�̂݁A
            //�����̐敨OP�\�񒍕�����Row�I�u�W�F�N�g�̓��e��
            //�敨OP�\�񒍕������e�[�u����insert����B
            if (l_rsvIfoOrderActionRow != null)
            {
                l_queryProcessor.doInsertQuery(l_rsvIfoOrderActionRow);
            }
            log.exiting(STR_METHOD_NAME);
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
    }
}
@
