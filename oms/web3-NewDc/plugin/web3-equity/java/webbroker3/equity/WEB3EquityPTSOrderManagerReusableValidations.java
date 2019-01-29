head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����R���ʃ`�F�b�N�iWEB3EquityPTSOrderManagerReusableValidations.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/19 �����Q (���u) �V�K�쐬 ���f��No.1208�A1257 �v�Z����No.020
Revision History : 2007/12/21 �����Q(���u) ���f��No.1261�A1263�A1276 �v�Z����No.021
Revision History : 2008/04/15 ����(���u) ���f��No.1312
Revision History : 2009/09/10 �И���(���u) �v�Z����No.022
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeableTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MaxMinFlagDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS�����R���ʃ`�F�b�N)<BR>
 * PTS�����R���ʃ`�F�b�N�N���X<BR>
 *
 * @@author �����Q
 * @@version 1.0
 */
public class WEB3EquityPTSOrderManagerReusableValidations
    extends WEB3EquityTypeOrderManagerReusableValidations
{
    /**
     * (���O���[�e�B���e�B)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityPTSOrderManagerReusableValidations.class);

    /**
     * @@roseuid 4766165100BC
     */
    public WEB3EquityPTSOrderManagerReusableValidations()
    {

    }

    /**
     * (validatePTS���������\���)<BR>
     * �������\�Ȓ�����Ԃ����`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@�ȉ��A�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f���g�p����B <BR>
     * <BR>
     * �Q�j�@@�����L����ԁiorder_open_status�j�A<BR>
     * �@@�@@�@@�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B <BR>
     * <BR>
     * �@@�Q�|�P�j �����L����Ԃ̃`�F�b�N <BR>
     * �@@�@@�@@�E�����L����Ԃ�OPEN�ȊO�̏ꍇ�͒����s�Ƃ��A <BR>
     * �@@�@@�@@�@@��O��throw����B <BR>
     * �@@�@@�@@�@@class: OrderValidationException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00032<BR>
     * <BR>
     * �@@�Q�|�Q�j ������Ԃ̃`�F�b�N <BR>
     * �@@�@@�s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B <BR>
     * �@@�@@[�s��J�ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ] <BR>
     * <BR>
     * �@@�@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A <BR>
     * �@@�@@�@@�@@��O��throw����B <BR>
     * �@@�@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@�@@ACCEPTED<BR>
     * �@@�@@�@@�@@CANCEL_ACCEPTED <BR>
     * �@@�@@�@@�@@CANCELLING <BR>
     * �@@�@@�@@�@@MODIFY_ACCEPTED <BR>
     * �@@�@@�@@�@@MODIFYING <BR>
     * �@@�@@�@@�@@ORDERING <BR>
     * �@@�@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@�@@class: OrderValidationException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00032<BR>
     * <BR>
     * �@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj<BR>
     * �@@�@@�@@�@@�̏ꍇ�͒����s�Ƃ��A��O��throw����B <BR>
     * �@@�@@�@@�@@class: OrderValidationException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00032<BR>
     * <BR>
     * �@@�@@[�s��ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == �����������j�̏ꍇ] <BR>
     * <BR>
     * �@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A <BR>
     * �@@�@@�@@��O��throw����B <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@CANCEL_ACCEPTED <BR>
     * �@@�@@�@@CANCELLING <BR>
     * �@@�@@�@@MODIFY_ACCEPTED <BR>
     * �@@�@@�@@MODIFYING <BR>
     * �@@�@@�@@ORDERING <BR>
     * �@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@class: OrderValidationException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00032<BR>
     * @@param l_order - (����)<BR>
     * @@roseuid 47344A590343
     */
    public void validatePTSOrderForChangeability(Order l_order)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validatePTSOrderForChangeability(Order)";
        log.entering(STR_METHOD_NAME);

        if (l_order == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }

        //�P�j�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B
        //�ȉ��A�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f���g�p����B
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit = l_orderUnits[0];

        //�Q�j�����L����ԁiorder_open_status�j�A�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B
        //�Q�|�P�j�����L����Ԃ̃`�F�b�N
        //�E�����L����Ԃ�OPEN�ȊO�̏ꍇ�͒����s�Ƃ��A��O��throw����B
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            log.debug("��������t�����Ԃł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
        }

        //�Q�|�Q�j������Ԃ̃`�F�b�N
        //�s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B
        boolean l_blnIsTradeOpenTimeZone;
        try
        {
            l_blnIsTradeOpenTimeZone = WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_exp)
        {
            log.error(l_exp.getErrorInfo().getErrorMessage(), l_exp);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(l_exp.getErrorInfo());
        }

        int l_intStatus = l_orderUnitRow.getOrderStatus().intValue();
        if (l_blnIsTradeOpenTimeZone)
        {
            //�s��J�ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ
            if ((l_intStatus == OrderStatusEnum.IntValues.ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                //�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A��O��throw����B
                //ACCEPTED
                //CANCEL_ACCEPTED
                //CANCELLING
                //MODIFY_ACCEPTED
                //MODIFYING
                //ORDERING
                log.debug("��������t�����Ԃł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }

            if (Double.isNaN(l_orderUnit.getConfirmedQuantity()))
            {
                //�E�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj�̏ꍇ�͒����s�Ƃ��A
                //��O��throw����B
                log.debug("��������t�����Ԃł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        else
        {
            //�s��ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == �����������j�̏ꍇ]

            if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                //�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͒����s�Ƃ��A��O��throw����B
                //CANCEL_ACCEPTED
                //CANCELLING
                //MODIFY_ACCEPTED
                //MODEFYING
                //ORDERING
                log.debug("��������t�����Ԃł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS��������\���)<BR>
     * ������\�Ȓ�����Ԃ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�ȉ��A�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f���g�p����B<BR>
     * <BR>
     * �Q�j�@@�����L����ԁiorder_open_status�j�A<BR>
     * �@@�@@�@@�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�����L����Ԃ̃`�F�b�N<BR>
     * �@@�@@�E�����L����Ԃ�CLOSED�̏ꍇ�͎���s�Ƃ��A��O��throw����B<BR>
     * �@@�@@�@@class: OrderValidationException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00820<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@������Ԃ̃`�F�b�N<BR>
     * �@@�@@�s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B<BR>
     * <BR>
     * =======================================================================<BR>
     * �@@�@@[�s��J�ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ]<BR>
     * �@@�@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class: OrderValidationException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00032<BR>
     * �@@�@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@�@@ACCEPTED<BR>
     * �@@�@@�@@�@@CANCEL_ACCEPTED<BR>
     * �@@�@@�@@�@@CANCELLING<BR>
     * �@@�@@�@@�@@MODIFY_ACCEPTED<BR>
     * �@@�@@�@@�@@MODIFYING<BR>
     * �@@�@@�@@�@@ORDERING<BR>
     * �@@�@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@�E�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj<BR>
     * �@@�@@�@@�@@�̏ꍇ�͎���s�Ƃ��A��O��throw����B<BR>
     * �@@�@@�@@�@@class: OrderValidationException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00032<BR>
     * <BR>
     * =======================================================================<BR>
     * �@@�@@[�s��ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ]<BR>
     * �@@�@@�@@�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A <BR>
     * �@@�@@�@@�@@��O��throw����B <BR>
     * �@@�@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@�@@CANCEL_ACCEPTED <BR>
     * �@@�@@�@@�@@CANCELLING <BR>
     * �@@�@@�@@�@@MODIFY_ACCEPTED <BR>
     * �@@�@@�@@�@@MODIFYING <BR>
     * �@@�@@�@@�@@ORDERING <BR>
     * �@@�@@�@@�@@------------------------------------------------ <BR>
     * �@@�@@�@@�@@class: OrderValidationException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00032<BR>
     * @@param l_order - (����)<BR>
     * @@roseuid 47344C2B01DE
     */
    public void validatePTSOrderForCancellation(Order l_order)
        throws OrderValidationException
    {
        final String STR_METHOD_NAME = "validatePTSOrderForCancellation(Order)";
        log.entering(STR_METHOD_NAME);

        if (l_order == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }

        //�P�j�����̒����I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B
        //�ȉ��A�擾���������P�ʃI�u�W�F�N�g��0�Ԗڂ̗v�f���g�p����B
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnits[0].getDataSourceObject();

        //�Q�j�����L����ԁiorder_open_status�j�A�y�ђ�����ԁiorder_status�j�̃`�F�b�N���s���B
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            //�Q�|�P�j�����L����Ԃ̃`�F�b�N
            //�E�����L����Ԃ�CLOSED�̏ꍇ�͎���s�Ƃ��A��O��throw����B
            log.debug("�N���[�Y���������͓������̌������n�����������Ď���ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00820);
        }

        //�Q�|�Q�j������Ԃ̃`�F�b�N
        //�s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B
        boolean l_blnIsTradeOpenTimeZone;
        try
        {
            l_blnIsTradeOpenTimeZone = WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone();
        }
        catch (WEB3SystemLayerException l_exp)
        {
            log.error(l_exp.getErrorInfo().getErrorMessage(), l_exp);
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(l_exp.getErrorInfo());
        }

        int l_intStatus = l_orderUnitRow.getOrderStatus().intValue();
        if (l_blnIsTradeOpenTimeZone)
        {
            //�s��J�ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j�̏ꍇ

            if ((l_intStatus == OrderStatusEnum.IntValues.ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                //�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A��O��throw����B
                //ACCEPTED
                //CANCEL_ACCEPTED
                //CANCELLING
                //MODIFY_ACCEPTED
                //MODEFYING
                //ORDERING
                log.debug("��������t�����Ԃł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }

            if (Double.isNaN(l_orderUnits[0].getConfirmedQuantity()))
            {
                //�E�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�i���s���t���ρj�̏ꍇ�͎���s�Ƃ��A
                //��O��throw����B
                log.debug("��������t�����Ԃł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }
        else
        {
            //�s��ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ

            if ((l_intStatus == OrderStatusEnum.IntValues.CANCEL_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.CANCELLING)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFY_ACCEPTED)
                || (l_intStatus == OrderStatusEnum.IntValues.MODIFYING)
                || (l_intStatus == OrderStatusEnum.IntValues.ORDERING))
            {
                //�E������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A
                //��O��throw����B
                //CANCEL_ACCEPTED
                //CANCELLING
                //MODIFY_ACCEPTED
                //MODEFYING
                //ORDERING
                log.debug("��������t�����Ԃł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00032);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS�s��ʎ���\������z)<BR>
     * �T�Z���z�l���A��ЁE���X�E�s��ň�x��<BR>
     * �@@����\�ȏ�����z�𒴂��Ă��Ȃ����`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@�،���ЃR�[�h = ���X.getInstitution().getInstitutionCode() <BR>
     * �@@���X�R�[�h = ���X.getBranchCode()<BR>
     * �@@�s��R�[�h = ����.�s��.getMarketCode()<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����i���X�s��ʁEPTS�j�戵����.get����\������z()���R�[������B<BR>
     * <BR>
     * �R�j�@@�i �Q�j�Ŏ擾��������\������z�l�@@���@@�S��������� �j�̏ꍇ<BR>
     * �@@�@@�@@����\����l�𒴉߂��Ă���Ɣ��f���A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02972<BR>
     * @@param l_branch - (���X)<BR>
     * @@param l_market - (�s��)<BR>
     * @@param l_dblRestraintTurnover - (�S���������)<BR>
     * @@roseuid 47344C7101B1
     */
    public void validatePTSMarketMaxHandlingPrice(
        Branch l_branch,
        Market l_market,
        double l_dblRestraintTurnover) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSMarketMaxHandlingPrice(Branch, Market, double)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_market == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g���擾����B
        //[�R���X�g���N�^�̈���]
        //�،���ЃR�[�h = ���X.getInstitution().getInstitutionCode()
        //���X�R�[�h = ���X.getBranchCode()
        //�s��R�[�h = ����.�s��.getMarketCode()
        WEB3GentradeBranchMarketPTSDealtCond l_ptsBranchMarketDealtCond =
            new WEB3GentradeBranchMarketPTSDealtCond(
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                l_market.getMarketCode());

        //�Q�j�P�j�Ŏ擾�����i���X�s��ʁEPTS�j�戵����.get����\������z()���R�[������B
        double l_dblMaxHandlingPrice = l_ptsBranchMarketDealtCond.getMaxHandlingPrice();

        if (l_dblMaxHandlingPrice < l_dblRestraintTurnover)
        {
            //�R�j�i �Q�j�Ŏ擾��������\���z����l���S����������j�̏ꍇ
            //����\����l�𒴉߂��Ă���Ɣ��f���A��O���X���[����B
            log.debug("�w��s��̎���\������z�𒴂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02972,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w��s��̎���\������z�𒴂��Ă��܂��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�戵�\PTS�s��)<BR>
     * ��Е��X�̎戵�\�s�ꂩ���`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@�،���ЃR�[�h �F ����.���X.getInstitution().getInstitutionCode() <BR>
     * �@@���X�R�[�h �F ����.���X.getBranchCode()<BR>
     * �@@�s��R�[�h �F ����.�������.getMarket().getMarketCode()<BR>
     * <BR>
     * �Q�j�@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g.is�戵�\()���R�[������B<BR>
     * <BR>
     * �@@[is�戵�\()�̈���]<BR>
     * �@@�����^�C�v�@@�F�@@�h�����h<BR>
     * <BR>
     * �R�j�@@�戵�s�iis�戵�\ = false�j�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02108<BR>
     * @@param l_branch - (���X)<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * @@roseuid 4741845B02C7
     */
    public void validateHandlingPossiblePTSMarket(
        Branch l_branch,
        WEB3EquityTradedProduct l_tradedProduct)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingPossiblePTSMarket(Branch, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_tradedProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g���擾����B
        //[�R���X�g���N�^�̈���]
        //�،���ЃR�[�h�F����.���X.getInstitution().getInstitutionCode()
        //���X�R�[�h�F����.���X.getBranchCode()
        //�s��R�[�h�F����.�������.getMarket().getMarketCode()
        WEB3GentradeBranchMarketPTSDealtCond l_ptsBranchMarketDealtCond =
            new WEB3GentradeBranchMarketPTSDealtCond(
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                l_tradedProduct.getMarket().getMarketCode());

        //�Q�j�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g.is�戵�\()���R�[������B
        //[is�戵�\()�̈���]
        //�����^�C�v�F�h�����h
        boolean l_blnIsHandlingPossible = l_ptsBranchMarketDealtCond.isHandlingPossible(ProductTypeEnum.EQUITY);

        if (!l_blnIsHandlingPossible)
        {
            //�R�j�戵�s�iis�戵�\ = false�j�̏ꍇ�A��O���X���[����B
            log.debug("���X�̎戵�\�s��ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00158,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�̎戵�\�s��ł͂���܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�����iPTS�j)<BR>
     * �����̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@������0�܂��̓}�C�i�X�l�łȂ����`�F�b�N���s���B<BR>
     * �@@super.validateQuantity()���R�[������B<BR>
     * <BR>
     * �@@[validateQuantity()�̈���]<BR>
     * �@@�����F�@@����.����<BR>
     * <BR>
     * �Q�j�@@�����������P�ʂ̐����{�ł��邩�`�F�b�N���s���B <BR>
     * �@@super.checkLotSize()���R�[������B<BR>
     * <BR>
     * �@@[checkLotSize()�̈���]<BR>
     * �@@�����P�ʁF�@@����.�������.get�����P��()<BR>
     * �@@�����F�@@����.����<BR>
     * <BR>
     * �R�j�@@�����������AHOST����<BR>
     * �@@�@@�@@��t�\�Ȋ�������l�𒴂��Ă��Ȃ����ǂ����̃`�F�b�N���s���B <BR>
     * �@@this.validate�����i�w��\����l�j()���R�[������B<BR>
     * <BR>
     * �@@[validate�����i�w��\����l�j()�̈���]<BR>
     * �@@���X�F�@@����.���X<BR>
     * �@@�����F�@@����.����<BR>
     * <BR>
     * �S�j�@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@�،���ЃR�[�h = ����.���X.getInstitution().getInstitutionCode() <BR>
     * �@@���X�R�[�h = ����.���X.getBranchCode()<BR>
     * �@@�s��R�[�h = ����.�������.getMarket().getMarketCode()<BR>
     * <BR>
     * �T�j�@@�s��̔�������������v�Z����B <BR>
     * <BR>
     * �@@�i�v�Z���j <BR>
     * �@@�������.�����P�ʁ@@�~�@@�������x�P��(*1)�@@���@@�s��̔����������<BR>
     * <BR>
     * �@@(*1) �������x�P�ʁF <BR>
     * �@@�@@�@@�|�������.�������x�P�� != null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������.�������x�P�� ���g�p����B <BR>
     * �@@�@@�@@�|�������.�������x�P�� == null �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�S�j�Ŏ擾�����i���X�s��ʁEPTS�j�戵����.get�������x�P�ʁi�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�̖߂�l���g�p����B<BR>
     * <BR>
     * �T�j�@@�i�s��̔�����������@@< �����j�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00160<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * @@param l_branch - (���X)<BR>
     * @@param l_dblStockQuantity - (����)<BR>
     * @@roseuid 47424A76006B
     */
    public void validatePTSQuantity(
        TradedProduct l_tradedProduct,
        WEB3GentradeBranch l_branch, double l_dblStockQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSQuantity(WEB3EquityTradedProduct, WEB3GentradeBranch, double)";
        log.entering(STR_METHOD_NAME);

        if (l_branch == null || l_tradedProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

        //�P�j������0�܂��̓}�C�i�X�l�łȂ����`�F�b�N���s���B
        //super.validateQuantity()���R�[������B
        //[validateQuantity()�̈���]
        //�����F����.����
        try
        {
            super.validateQuantity(l_dblStockQuantity);
        }
        catch (OrderValidationException l_ove)
        {
            log.debug("�������ʂ�0�ȉ��̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ�0�ȉ��̒l�ł��B");
        }

        //�Q�j�����������P�ʂ̐����{�ł��邩�`�F�b�N���s���B
        //super.checkLotSize()���R�[������B
        //[checkLotSize()�̈���]
        //�����P�ʁF�@@����.�������.get�����P��()
        //�����F�@@����.����
        try
        {
            super.checkLotSize(
                l_tradedProductRow.getLotSize(),
                l_dblStockQuantity);
        }
        catch (OrderValidationException l_ove)
        {
            log.debug("�����������P�ʂ̐����{�ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
              WEB3ErrorCatalog.BUSINESS_ERROR_00708,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              "�����������P�ʂ̐����{�ł͂���܂���B");
        }

        //�R�j�����������AHOST���Ŏ�t�\�Ȋ�������l�𒴂��Ă��Ȃ����ǂ����̃`�F�b�N���s���B
        //this.validate�����i�w��\����l�j()���R�[������B
        //[validate�����i�w��\����l�j()�̈���]
        //���X�F����.���X
        //�����F����.����
        this.validateQuantity(l_branch, l_dblStockQuantity);

        //�S�j�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g���擾����B
        //[�R���X�g���N�^�̈���]
        //�،���ЃR�[�h=����.���X.getInstitution().getInstitutionCode()
        //���X�R�[�h=����.���X.getBranchCode()
        //�s��R�[�h=����.�������.getMarket().getMarketCode()
        WEB3GentradeBranchMarketPTSDealtCond l_ptsBranchMarketDealtCond =
            new WEB3GentradeBranchMarketPTSDealtCond(
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                l_tradedProduct.getMarket().getMarketCode());

        //(*1) �������x�P�ʁF
        double l_dblLimitedUnit = 0.0D;
        if (!l_tradedProductRow.getCompulsiveLimitedUnitIsNull())
        {
            //�|�������.�������x�P�� != null �̏ꍇ�A
            //�������.�������x�P�� ���g�p����B
            l_dblLimitedUnit = l_tradedProductRow.getCompulsiveLimitedUnit();
        }
        else
        {
            //�|�������.�������x�P�� == null�̏ꍇ�A
            //�S�j�Ŏ擾�����i���X�s��ʁEPTS�j�戵����.get�������x�P�ʁi�j�̖߂�l���g�p����B
            l_dblLimitedUnit = l_ptsBranchMarketDealtCond.getLimitedUnit();
        }

        //�T�j�s��̔�������������v�Z����B
        //�i�v�Z���j
        //�������.�����P�ʁ@@�~�@@�������x�P��(*1)�@@���@@�s��̔����������
        BigDecimal l_bdLimitedUnit = new BigDecimal(l_dblLimitedUnit + "");
        BigDecimal l_bdResultHighQuantity =
            l_bdLimitedUnit.multiply(new BigDecimal(l_tradedProductRow.getLotSize() + ""));
        double l_dblResultHighQuantity = l_bdResultHighQuantity.doubleValue();

        if (l_dblResultHighQuantity < l_dblStockQuantity)
        {
            //�T�j�i�s��̔�����������@@< �����j�̏ꍇ�A��O���X���[����B
            log.debug("������������������𒴂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00160,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������������������𒴂��Ă��܂��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�w�l�����iPTS�j)<BR>
     * �w�l�����̂ݎw��B<BR>
     * <BR>
     * �P�j�@@���s�i����.�����������e.isLimitOrder == false�j���w�肳��Ă����ꍇ�A<BR>
     * �@@�@@�@@�uPTS�s��ł́A���s�w��s�B�v�̗�O���X���[����B�@@<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02974<BR>
     * @@param l_equityNewCashBasedOrderSpec - (�����������e)<BR>
     * @@roseuid 474CEABA0074
     */
    public void validatePTSLimitOrder(WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSLimitOrder(WEB3EquityNewCashBasedOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_equityNewCashBasedOrderSpec == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        if (!l_equityNewCashBasedOrderSpec.isLimitOrder())
        {
            //�P�j���s�i����.�����������e.isLimitOrder == false�j���w�肳��Ă����ꍇ�A
            //�uPTS�s��ł́A���s�w��s�B�v�̗�O���X���[����B
            log.debug("PTS�s��ł́A���s�w��s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02974,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PTS�s��ł́A���s�w��s�B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc��l�iPTS�l���`�F�b�N�p�j)<BR>
     * <BR>
     * �P�j�@@����.�������.get��l(�I�l)()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l��ԋp����B<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * @@return double
     * @@roseuid 474D465E01EE
     */
    public double calcBasePriceForPTSPriceRange(WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "calcBasePriceForPTSPriceRange(WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j����.�������.get��l(�I�l)()���R�[������B
        double l_dblBasePrice = l_tradedProduct.getLastClosingPrice();

        //�Q�j�P�j�̖߂�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;
    }

    /**
     * (validate�ڋq�����ʎ����~�iPTS�j)<BR>
     * �ڋq�����ʎ����~�`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�����̕⏕�����I�u�W�F�N�g���A�ڋq�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@�ڋq.isPTS�����~����( )���R�[������B <BR>
     * <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@��isPTS�����~����( )�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@����ID�F�@@�����̖���ID <BR>
     * �@@�@@�@@������ʁF�@@�����̒������ <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �@@�@@�@@�߂�l == true�̏ꍇ�́u�ڋq�͎w������̊Y�������~���v�̗�O��throw����B <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_01357<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID�B <BR>
     * ��������肵�Ȃ��ꍇ�́A0�i�S�����j���Z�b�g�B<BR>
     * @@param l_orderType - (�������)<BR>
     * ������ʁB<BR>
     * @@roseuid 474E0B6E0373
     */
    public void validatePTSAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSAccountProductOrderStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�����̕⏕�����I�u�W�F�N�g���A�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_account =
            new WEB3GentradeMainAccount((MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject());

        //�Q�j�ڋq.isPTS�����~����( )���R�[������B
        //����ID�F�@@�����̖���ID
        //������ʁF�@@�����̒������
        if (l_account.isPTSTradeStopProduct(l_lngProductId, l_orderType))
        {
            //�߂�l == true�̏ꍇ�́u�ڋq�͎w������̊Y�������~���v�̗�O��throw����B
            log.debug("�ڋq�͎w������̊Y�������~���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01357,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�F[" + l_account.getAccountId()
                + "] ����ID�F[" + l_lngProductId
                + "] ������ʁF[" + l_orderType + "]");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�������ځiPTS�j)<BR>
     * ���������ɂāA�������ꂽ���ڂ������������`�F�b�N����B <BR>
     * <BR>
     * �G���[���e�͈ȉ��̒ʂ�B <BR>
     * �@@�|�����������̊��������������������ꍇ�B <BR>
     * �@@�|�������������ꕔ�o���̏�ԂŁA������������萔�ȉ��̏ꍇ�B <BR>
     * �@@�|���������s�̎s��ŁA�����Ɠ��������Ώۍ��ڂ������ɒ�������Ă���ꍇ�B <BR>
     * �@@�|�������������牽�������������Ă��Ȃ��ꍇ�B <BR>
     * <BR>
     * ------------------------------------------------------------------- <BR>
     * �P�j�@@���������`�F�b�N <BR>
     * �@@isChange�����i�����P�ʁA�����㊔���j���R�[�����A <BR>
     * �@@���������l�̃`�F�b�N���s���B <BR>
     * <BR>
     * �Q�j�@@�P���`�F�b�N <BR>
     * �@@isChange�P���i�����P�ʁA������w�l�j���R�[�����A <BR>
     * �@@�P�������l�̃`�F�b�N���s���B <BR>
     * <BR>
     * �R�j�@@���s�����ɒ����������Ă��邩�̃`�F�b�N <BR>
     * �@@isChange���s�����i�����P�ʁA�����㎷�s�����j���R�[������B <BR>
     * <BR>
     * �S�j�@@�����Ɠ��������Ώۍ���(*1)�𓯎��ɒ������Ă���ꍇ�A<BR>
     * �@@�@@�@@���������\�Ȏs�ꂩ�ǂ����̃`�F�b�N <BR>
     * <BR>
     * �@@(*1)���������Ώۍ��� <BR>
     * �@@�@@�@@�E�P���i�w�l�j <BR>
     * �@@�@@�@@�E���s���� <BR>
     * <BR>
     * �@@�@@�����������P�ʂ̒l���g�p���A�u���������v�ł��邩�ǂ����𔻒肷��B <BR>
     * <BR>
     * �@@�@@isChange����( )��true��ԋp�����ꍇ�̂݁A <BR>
     * �@@�@@{isChange�P��( )�AisChange���s����( )}�̂����ꂩ��ł�true��ԋp���Ă���� <BR>
     * �@@�@@�u���������v�Ɣ��肷��B <BR>
     * <BR>
     * �@@�@@�u���������v�ɊY������ꍇ�̂݁A<BR>
     * �@@�@@�g���v���_�N�g�}�l�[�W��.getMarket(�����P��.�s��ID)�ɂ� <BR>
     * �@@�@@�s��I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�s��I�u�W�F�N�g.���������\�敪���h�������ړ��������s�h�̏ꍇ <BR>
     * �@@�@@�́A�u�������ړ��������s�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00687<BR>
     * <BR>
     * �T�j�@@�����������Ă��邩�̃`�F�b�N <BR>
     * �@@isChange����( )�AisChange�P��( )�A <BR>
     * �@@isChange���s����( ) �̂��ׂĂ�false��ԋp�����ꍇ�A <BR>
     * �@@�������������牽����������Ă��Ȃ��Ɣ��f���A <BR>
     * �@@�u�������͂Ȃ��v�̗�O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_02103<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����������i�������j�̒����P�ʃI�u�W�F�N�g<BR>
     * @@param l_dblModifiedQuantity - (�����㊔��)<BR>
     * �����㊔��<BR>
     * @@param l_dblModifiedLimitPrice - (������w�l)<BR>
     * ������w�l<BR>
     * @@param l_eqTypeExecutionConditionType - (�����㎷�s����)<BR>
     * �����㎷�s����<BR>
     * @@param l_strModifiedPriceConditionType - (������l�i����)<BR>
     * ������l�i����<BR>
     * @@param l_strModifiedOrderConditionType - (�����㔭������)<BR>
     * �����㔭������<BR>
     * @@param l_strModifiedOrderCondOperator - (�����㔭���������Z�q)<BR>
     * �����㔭���������Z�q<BR>
     * @@param l_dblModifiedStopOrderPrice - (������t�w�l��l)<BR>
     * ������t�w�l��l<BR>
     * @@param l_dblModifiedWLimitPrice - (������iW�w�l�j�����w�l)<BR>
     * ������iW�w�l�j�����w�l<BR>
     * @@param l_modifiedWLimitExecCondType - (������iW�w�l�j���s����)<BR>
     * ������iW�w�l�j���s����<BR>
     * @@param l_modifiedIsCarriedOrder - (������is�o����܂Œ���)<BR>
     * ������̒������o����܂Œ������ǂ����𔻕ʂ���t���O�B <BR>
     * <BR>
     * true�F�@@�o����܂Œ��� <BR>
     * false�F�@@�������� <BR>
     * @@param l_datModifiedExpirationDate - (�����㒍��������)<BR>
     * �����㒍��������<BR>
     * @@param l_modifiedSettleContractEntries - (�����㌈�ώw��G���g��)<BR>
     * �����㌈�ώw��G���g���̔z��B���ϒ����̏ꍇ�̂݃Z�b�g����B<BR>
     * @@roseuid 474E0DDF02F7
     */
    public void validatePTSChangeItem(
        EqTypeOrderUnit l_orderUnit,
        double l_dblModifiedQuantity,
        double l_dblModifiedLimitPrice,
        EqTypeExecutionConditionType l_eqTypeExecutionConditionType,
        String l_strModifiedPriceConditionType,
        String l_strModifiedOrderConditionType,
        String l_strModifiedOrderCondOperator,
        double l_dblModifiedStopOrderPrice,
        double l_dblModifiedWLimitPrice,
        EqTypeExecutionConditionType l_modifiedWLimitExecCondType,
        boolean l_modifiedIsCarriedOrder,
        Date l_datModifiedExpirationDate,
        EqTypeSettleContractOrderEntry[] l_modifiedSettleContractEntries)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSChangeItem(EqTypeOrderUnit, double, double, " +
            "EqTypeExecutionConditionType, String, String, String, double, double, " +
            "EqTypeExecutionConditionType, boolean, Date, EqTypeSettleContractOrderEntry[])";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //1)���������`�F�b�N
        //isChange�����i�����P�ʁA�����㊔���j���R�[�����A���������l�̃`�F�b�N���s���B
        boolean l_blnIsChangeQuantity = this.isChangeQuantity(l_orderUnit, l_dblModifiedQuantity);

        //2)�P���`�F�b�N
        //isChange�P���i�����P�ʁA������w�l�j���R�[�����A�P�������l�̃`�F�b�N���s���B
        boolean l_blnIsChangePrice = this.isChangePrice(l_orderUnit, l_dblModifiedLimitPrice);

        //3)���s�����ɒ����������Ă��邩�̃`�F�b�N
        //isChange���s�����i�����P�ʁA�����㎷�s�����j���R�[������B
        boolean l_blnIsChangeExecutionCondition =
            this.isChangeExecutionCondition(l_orderUnit, l_eqTypeExecutionConditionType);

        //4)�����Ɠ��������Ώۍ��ڂ𓯎��ɒ������Ă���ꍇ�A���������\�Ȏs�ꂩ�ǂ����̃`�F�b�N
        if (l_blnIsChangeQuantity && (l_blnIsChangePrice || l_blnIsChangeExecutionCondition))
        {
            //isChange����( )��true��ԋp�����ꍇ�̂݁A
            //{isChange�P��( )�AisChange���s����( )}�̂����ꂩ��ł�true��ԋp���Ă���΁u���������v�Ɣ��肷��B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();

            //�g���v���_�N�g�}�l�[�W��.getMarket(�����P��.�s��ID)�ɂ�
            //�s��I�u�W�F�N�g���擾����B
            try
            {
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();

                if (WEB3ChangeableTypeDef.CANNOT_CHANGE.equals(l_marketRow.getChangeableType()))
                {
                    log.debug("�������ړ��������s�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00687,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�������ړ��������s�B");
                }
            }
            catch (NotFoundException l_nfe)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }

        //�T�j�����������Ă��邩�̃`�F�b�N
        if (!l_blnIsChangeQuantity && !l_blnIsChangePrice && !l_blnIsChangeExecutionCondition)
        {
            //isChange����()�AisChange�P��()�A
            //isChange���s����()�̂��ׂĂ�false��ԋp�����ꍇ�A
            //�������������牽����������Ă��Ȃ��Ɣ��f���A
            //�u�������͂Ȃ��v�̗�O���X���[����B
            log.debug("�������͂���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02103,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������͂���Ă��܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate����������Rev����iPTS�j)<BR>
     * �������̒���Rev������𒴂��Ȃ����ǂ������`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�����̒����P�ʃI�u�W�F�N�g��clone�I�u�W�F�N�g�𐶐����A <BR>
     * �@@�@@�@@�����̒�����̒l�����ꂼ��Y�����鍀�ڂɃZ�b�g����B <BR>
     * <BR>
     * �@@�@@�@@�����㊔���F�@@�@@�@@�@@�������� <BR>
     * �@@�@@�@@������w�l�F�@@�@@�@@�@@�w�l <BR>
     * �@@�@@�@@�����㎷�s�����F�@@���s���� <BR>
     * �@@�@@�@@������l�i�����F�@@�l�i���� <BR>
     * <BR>
     * �Q�j�@@���������T�[�r�X.getPTS����������Rev()���R�[������B <BR>
     * �@@�@@�@@�����ɂ́A�P�j�ō쐬���������P�ʃI�u�W�F�N�g���Z�b�g����B <BR>
     * �@@�@@�@@����O�i����񐔃I�[�o�[���j��throw���ꂽ�ꍇ�́A <BR>
     * �@@�@@�@@�����̗�O�����̂܂�throw����B <BR>
     * @@param l_orderUnit - (�����O�����P��)<BR>
     * �����O�̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_dblQuantity - (�����㊔��)<BR>
     * �����㊔��<BR>
     * @@param l_dblLimitPrice - (������w�l)<BR>
     * ������w�l<BR>
     * @@param l_executionConditionType - (�����㎷�s����)<BR>
     * �����㎷�s����<BR>
     * @@param l_strPriceConditionType - (������l�i����)<BR>
     * ������l�i����<BR>
     * @@roseuid 474E2A500296
     */
    public void validatePTSChangeOrderRevUpperLimit(
        EqTypeOrderUnit l_orderUnit,
        double l_dblQuantity,
        double l_dblLimitPrice,
        EqTypeExecutionConditionType l_executionConditionType,
        String l_strPriceConditionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSChangeOrderRevUpperLimit(EqTypeOrderUnit," +
            " double, double, EqTypeExecutionConditionType, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�����̒����P�ʃI�u�W�F�N�g��clone�I�u�W�F�N�g�𐶐����A
        //�����̒�����̒l�����ꂼ��Y�����鍀�ڂɃZ�b�g����B
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_orderUnitParams =
            new EqtypeOrderUnitParams(l_orderUnitRow);

        //�����㊔���F��������
        //������w�l�F�w�l
        //�����㎷�s�����F���s����
        //������l�i�����F�l�i����
        l_orderUnitParams.setQuantity(l_dblQuantity);
        l_orderUnitParams.setLimitPrice(l_dblLimitPrice);
        l_orderUnitParams.setExecutionConditionType(l_executionConditionType);
        l_orderUnitParams.setPriceConditionType(l_strPriceConditionType);

        //�Q�j���������T�[�r�X.getPTS����������Rev()���R�[������B
        //�����ɂ́A�P�j�ō쐬���������P�ʃI�u�W�F�N�g���Z�b�g����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_eqTypeOrderUnit =
            (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);

        //����O�i����񐔃I�[�o�[���j��throw���ꂽ�ꍇ�́A
        //�����̗�O�����̂܂�throw����B
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        l_frontOrderService.getPTSChangeOrderRev(l_eqTypeOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�����������iPTS�j)<BR>
     * �������������������ł��邩�ǂ����̔�����s���B<BR>
     * <BR>
     * �������������������̏ꍇ��true���A<BR>
     * ��L�ȊO�̏ꍇ��false��Ԃ��B<BR>
     * �����\�b�h�̏ڍׂ�<BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v��<BR>
     * �u[������7]PTS����p�E�����������`�F�b�N�v���Q�ƁB<BR>
     * ���@@�c�Ɠ��v�Z�̍ۂɂ́A�c�Ɠ��v�Z�N���X���g�p����B<BR>
     * @@param l_tsOrderBizDate - (������)<BR>
     * @@param l_tsYearlyBooksClosingDate - (�����m���)<BR>
     * �����m������w�肷��B�ʏ�́A�y���������e�[�u���z���Z�����w�肳���B<BR>
     * @@return boolean
     * @@roseuid 4753F6A20321
     */
    public boolean isPTSDevidendRightDate(
        Timestamp l_tsOrderBizDate,
        Timestamp l_tsYearlyBooksClosingDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSDevidendRightDate(Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        if (l_tsOrderBizDate == null || l_tsYearlyBooksClosingDate == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�����������Z�o����
        WEB3GentradeBizDate l_bizDateCalcUtil =
            new WEB3GentradeBizDate(l_tsYearlyBooksClosingDate);

        //�u�����m����v�̂R�c�Ɠ��O�̓��t���Z�o
        Timestamp l_tsDevidendRightDate = l_bizDateCalcUtil.roll(-3);

        if (WEB3DateUtility.compareToDay(l_tsOrderBizDate, l_tsDevidendRightDate) == 0)
        {
            //�Y�������̌����������ł���Ɣ��肵true��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            //��L�ȊO�̏ꍇ��false��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (validate�����P���iPTS�j)<BR>
     * �w�l���K�؂ł��邩�ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �V�[�P���X�}�w�iPTS�����jvalidate�����P���x�Q�ƁB<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@return boolean
     * @@roseuid 4753F6A23381
     */
    public boolean validatePTSPrice(
        double l_dblLimitPrice,
        WEB3EquityTradedProduct l_tradedProduct,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSPrice(double, WEB3EquityTradedProduct, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�Ēl�`�F�b�N�iPTS�j
        if (!this.isPTSTickValueDef((WEB3EquityTradedProduct)l_tradedProduct, l_dblLimitPrice))
        {
            //�Ēl�`�F�b�N�iPTS�j() == false�̏ꍇ�Afalse��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Ēl�`�F�b�N�iPTS�j() == true�̏ꍇ�A�������p�s����B
        //�������Row
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

        //��������Row���擾
        EqtypeProductRow l_productRow =
            (EqtypeProductRow)l_tradedProduct.getProduct().getDataSourceObject();

        //is����������
        Timestamp l_tsOrderBizDate =
            new Timestamp(WEB3DateUtility.getDate(
            l_tradedProductRow.getValidUntilBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());
        boolean l_blnIsPTSDevidendRightDate =
            this.isPTSDevidendRightDate(l_tsOrderBizDate, l_productRow.getYearlyBooksClosingDate());

        //Is�l���`�F�b�N�ΏۊO
        boolean l_blnIsPriceRangeCheck = false;
        if (WEB3PriceRangeTypeDef.CHECK.equals(l_tradedProductRow.getPriceRangeType()))
        {
            l_blnIsPriceRangeCheck = true;
        }

        if (!l_blnIsPTSDevidendRightDate && l_blnIsPriceRangeCheck)
        {
            log.exiting(STR_METHOD_NAME);
            return this.isPTSPriceRange((WEB3EquityTradedProduct)l_tradedProduct, l_dblLimitPrice);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (�Ēl�`�F�b�N�iPTS�j)<BR>
     * �w�l�����ݒl�̐����{���ǂ����i�w�l�����ݒl�Ŋ���؂�邩�ǂ����j���`�F�b�N����B<BR>
     * �����{�̏ꍇ��true���A�񐮐��{�̏ꍇ��false��Ԃ��B<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * @@param l_dblOrderPrice - (�����P��)<BR>
     * @@return boolean
     * @@roseuid 47364A530343
     */
    protected boolean isPTSTickValueDef(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblOrderPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSTickValueDef(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_dblOrderPrice < 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityProductManager l_productManager = null;
        l_productManager =
            (WEB3EquityProductManager)GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        BigDecimal l_bdTickValue = null;
        try
        {
            //���ݒl�擾
            double l_dblTickValue =
                l_productManager.getTickValue(l_tradedProduct, l_dblOrderPrice);
            l_bdTickValue = new BigDecimal(l_dblTickValue);
        }
        catch (WEB3BaseException l_ble)
        {
            log.error("�w�肳�ꂽ�Ēl������������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00030,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_dblOrderPrice % l_bdTickValue.doubleValue() != 0)
        {
            // �Ēl�G���[
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00030,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (�l���`�F�b�N�iPTS�j)<BR>
     * �y�l���e�[�u���z����l�����擾���Ēl������^�l���������Z�o���A<BR>
     * �w�l���l���͈͓̔��ł��邩�ǂ������`�F�b�N����B<BR>
     * �w�l���l���͈͓̔��ł���ꍇ��true���A����ȊO�̏ꍇ��false���A<BR>
     * ���ꂼ��Ԃ��B<BR>
     * <BR>
     * �����\�b�h�̏ڍׂ�<BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u[3]�l���`�F�b�N�v���Q�ƁB<BR>
     * <BR>
     * �V�[�P���X�}�u�iPTS����)validate�����P���iPTS�j�v�Q�ƁB<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * @@param l_dblOrderPrice - (�����P��)<BR>
     * @@return boolean
     * @@roseuid 4731945B05C7
     */
    protected boolean isPTSPriceRange(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblOrderPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSPriceRange(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdBasePrice = null;
        BigDecimal l_bdResultPriceHigh = null;
        BigDecimal l_bdResultPriceLow = null;

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();

        // �����P��
        BigDecimal l_bdOrderPrice = new BigDecimal(l_dblOrderPrice);

        //(1) [������6]��l�Z�o�i�l���`�F�b�N�p�j�@@���s��
        l_bdBasePrice =
            new BigDecimal(this.calcBasePriceForPTSPriceRange(l_tradedProduct));
        log.debug("*** �u��l�v �F  " + l_bdBasePrice.doubleValue());

        //(2) (1)�ŎZ�o������l���g�p���A�l������v�Z����
        // �g�p����l����[������2]�l���Z�o �ɂ��v�Z����B
        BigDecimal l_bdPriceRangeHigh =
            new BigDecimal(
                this.calcPriceRange(
                    l_tradedProduct,
                    l_bdBasePrice.doubleValue(),
                    WEB3MaxMinFlagDef.MAXIMUM));
        log.debug("*** �u�l��(����v�Z��)�v �F  " + l_bdPriceRangeHigh.doubleValue());

        //(3) �i(1)�Ŏ擾������l�{(2)�Ŏ擾�����l���j���u��l�v
        // �Ƃ���[������1]���ݒl�擾�@@���s���A
        // �l������ɑ΂��鍏�ݒl���擾����B
        BigDecimal l_bdTickValue =
            new BigDecimal(
                l_productManager.getTickValue(
                    l_tradedProduct,
                    l_bdBasePrice.add(l_bdPriceRangeHigh).doubleValue()));
        log.debug("*** �u���ݒl(����v�Z��)�v �F  " + l_bdTickValue.doubleValue());

        //(4) (3)�Ŏ擾�������ݒl���w�l�P�ʂƂ��A(2)�Ŏ擾����
        // �l������v�Z���Ɏg�p����l��������[������3]�l������Z�o
        // ���s���A�l��������Z�o����
        l_bdResultPriceHigh =
            new BigDecimal(
                this.calcStopHighPrice(
                    l_bdBasePrice.doubleValue(),
                    l_bdPriceRangeHigh.doubleValue(),
                    l_bdTickValue.doubleValue()));
        log.debug("*** �u�l������v �F  " + l_bdResultPriceHigh.doubleValue());

        //(5) (1)�ŎZ�o������l���g�p���A�l�������v�Z���Ɏg�p����l��
        // ��[������2]�l���Z�o �ɂ��v�Z����
        BigDecimal l_bdPriceRangeLow =
            new BigDecimal(
                this.calcPriceRange(
                    l_tradedProduct,
                    l_bdBasePrice.doubleValue(),
                    WEB3MaxMinFlagDef.MINIMUM));
        log.debug("*** �u�l��(�����v�Z��)�v �F  " + l_bdPriceRangeLow.doubleValue());

        //(6) (4)�Ŏ擾�����l������A�y��(5)�Ŏ擾�����l�������v�Z����
        // �g�p����l�������ɁA�l���������Z�o����B
        //(��l�|�l��)��0 �̏ꍇ�@@�ˁ@@�l��������1�Ƃ���B
        //��L�ȊO�̏ꍇ�@@�ˁ@@�l��������(��l�|�l��)�@@
        // (�����_�ȉ���؂�̂�)�Ƃ���B
        l_bdResultPriceLow = l_bdBasePrice.subtract(l_bdPriceRangeLow);
        if (l_bdResultPriceLow.compareTo(new BigDecimal(0)) <= 0)
        {
            l_bdResultPriceLow = new BigDecimal(1);
        }
        else
        {
            l_bdResultPriceLow = new BigDecimal(l_bdResultPriceLow.longValue());
        }
        log.debug("*** �u�l�������v �F  " + l_bdResultPriceLow.doubleValue());

        //�l���`�F�b�N
        if ((l_bdOrderPrice.compareTo(l_bdResultPriceHigh) > 0)
            || (l_bdOrderPrice.compareTo(l_bdResultPriceLow) < 0))
        {
            String l_strMessage = "(�������=" + l_tradedProduct.getTradedProductId()
                + ")�l������F " + l_bdResultPriceHigh
                + "�A�l�������F" + l_bdResultPriceLow
                + "�A�����P���F" + l_bdOrderPrice;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00031,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                WEB3EquityTypeOrderManagerReusableValidations.ORDER_ERROR_TAG
                + WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR
                );
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate�ŋ敪�iPTS�j)<BR>
     * �ŋ敪�̃`�F�b�N���s���B<BR>
     * ���蒍���ŁA�ŋ敪���X�g�b�N�I�v�V�����̏ꍇ�̓G���[�Ƃ���B<BR>
     * <BR>
     * <BR>
     * �P�j�@@����.is������ �� true ���� <BR>
     * �@@�@@�@@����.�ŋ敪 �� �X�g�b�N�I�v�V�����̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@��O�uPTS�s��ŃX�g�b�N�I�v�V�����������s�B�v���X���[����B<BR>
     * <BR>
     * �@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_03069 <BR>
     * @@param l_blnIsSellOrder - (is������)<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * @@throws WEB3BaseException<BR>
     */
    public void validatePTSTaxType(boolean l_blnIsSellOrder, TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSTaxType(boolean, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@����.is������ �� true ����
        //����.�ŋ敪 �� �X�g�b�N�I�v�V�����̏ꍇ
        if (l_blnIsSellOrder && TaxTypeEnum.STOCK_OPTION.equals(l_taxType))
        {
            log.debug("PTS�s��ŃX�g�b�N�I�v�V�����������s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03069,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PTS�s��ŃX�g�b�N�I�v�V�����������s�B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
