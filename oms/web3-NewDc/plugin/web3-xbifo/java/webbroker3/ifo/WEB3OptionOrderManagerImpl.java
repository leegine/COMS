head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           :OP�����}�l�[�W��(WEB3OptionOrderManagerImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 ���Ō� (Sinocom) �V�K�쐬
Revesion History : 2004/07/31 ���Ō� (Sinocom) �Ή�����: WEB3-���� �Ŗ{�FV1.4
Revesion History : 2004/08/09 ���Ō� (Sinocom) �Ή�����:�yWEB3-XBIFO-A-CD-0082�z
Revesion History : 2004/08/14 ���Ō� (Sinocom) �Ή��o�O  BUG140
Revesion History : 2004/08/14 ���Ō� (Sinocom) �Ή�����  �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)20040802.xls
Revesion History : 2006/07/12 �юu�� (���u) �d�l�ύX ���f��455,460,465,466,469,475,476,479,482,494,497,512,520
                                           �g���K�[�����Ǘ��҂̂c�a�X�V�d�l010(���f��475)
Revesion History : 2006/07/28 �юu�� (���u) �d�l�ύX ���f��530
Revesion History : 2006/08/16 �юu�� (���u) �d�l�ύX ���f��546
Revesion History : 2006/09/22 �s�p (���u) �d�l�ύX ���f��548
Revesion History : 2006/09/28 �s�p (���u) �d�l�ύX ���f��564
Revesion History : 2006/10/09 �юu�� (���u) �d�l�ύX ���f��555
Revesion History : 2006/10/24 ������ (���u) �g���K�[�����c�a�X�V�d�l021,023
Revesion History : 2006/11/28 ���� (���u) �d�l�ύX ���f��574�A581
Revesion History : 2006/11/30 ����� (���u) �g���K�[�����c�a�X�V�d�l034
Revesion History : 2006/12/08 ���� (���u) �X�V�d�l025
Revesion History : 2007/01/25 ������ (���u) �d�l�ύX ���f��No.589,No.593,No.599,No.601,No,604,No.610
Revesion History : 2007/06/08 ��іQ (���u) ���f��No.642�C648�C650�C660,699�C695�C693�C684�C677,�c�a�X�V�d�lNo.168
Revesion History : 2007/06/14 ��іQ (���u) ���f��No.689�C728, 733�C732
Revesion History : 2007/06/21 ��іQ (���u) ���f��No.738,744,�c�a�X�V�d�lNo186
Revesion History : 2007/06/28 ��іQ (���u) ���f��No.759
Revesion History : 2007/06/28 �Ј��� (���u) ���f��No.761
Revesion History : 2007/07/02 ��іQ (���u) ���f��No.757,760
Revesion History : 2007/11/19 �И��� (���u) ���f��No.801,805,816,817,818,819
Revesion History : 2007/11/27 �И��� (���u) Java�\�[�X�i��{�݌v�ƍ����Ă��Ȃ������jNo.004,006
Revesion History : 2008/03/13 ����(���u) ���f�� 823,843
Revesion History : 2008/03/19 ����(���u) ���f�� 860
Revesion History : 2008/04/11 �����F(���u) ���f�� 850
Revesion History : 2008/05/08 �����F(���u) DB�X�V�d�l045
Revesion History : 2008/08/18 ���z(���u) IFO�����_�Ή�
Revesion History : 2008/09/03 ���z(���u) IFO�����_�Ή�
*/
package webbroker3.ifo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.common.define.WEB3ContractCheckDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderDateDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ReserveOrderExistFlagDef;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TradeauditCodeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.define.WEB3CarryoverProcessTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.rlsgateway.define.WEB3RlsNotifyOrderTypeDef;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (OP�����}�l�[�W��)<BR>
 * �I�v�V���������}�l�[�W���N���X<BR>
 * @@author  ���Ō�
 * @@version 1.0
 */
public class WEB3OptionOrderManagerImpl extends IfoOrderManagerImpl
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionOrderManagerImpl.class);

    /**
     * (validate�V�K������)<BR>
     * OP�V�K�������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�����jvalidate�V�K�������v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_openContractOrderSpec - (�敨OP�V�K���������e)
     * @@param l_ifoOrderUnit - �����P��
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 404D67DD0090
     */
    public NewOrderValidationResult validateOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec,
        IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME =
            "validateOpenContractOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractOrderSpec, IfoOrderUnit)";

        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_openContractOrderSpec == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }
        try
        {
            ProcessingResult l_processingResult = null;

            //�V�K�������R�����ʃI�u�W�F�N�g
            NewOrderValidationResult l_newOrderValidationResult = null;

            //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                 (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //�������ʃ`�F�b�N�����{����
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            log.debug("�������ʂ��`�F�b�N���܂�");

            //�s��R�[�h���擾
            String l_strMarketCode = null;
            l_strMarketCode = l_openContractOrderSpec.getMarketCode();
            log.debug("�s��R�[�h���擾 = " + l_strMarketCode);

            //�،���ЃR�[�h���擾
            String l_strInstitutionCode = null;
            l_strInstitutionCode = l_openContractOrderSpec.getInstitutionCode();
            log.debug("�،���ЃR�[�h���擾 = " + l_strInstitutionCode);

            //�s��I�u�W�F�N�g���擾
            Market l_market = null;

            //�s��R�[�h�̃`�F�b�N�����{����
            l_market = l_ifoOrderManagerReusableValidations.validateMarket(l_strMarketCode, l_strInstitutionCode);
            log.debug("�s��R�[�h�̃`�F�b�N�����{����");
            log.debug("�擾�����s��̎s��ID = " + l_market.getMarketId());

            //�����R�[�h���擾����
            String l_strProductCode = null;
            l_strProductCode = l_openContractOrderSpec.getProductCode();
            log.debug("�����R�[�h���擾 = " + l_strProductCode);

            //�����̃`�F�b�N���s���A�����I�u�W�F�N�g��ԋp����
            WEB3IfoProductImpl l_ifoProductImpl = null;
            l_ifoProductImpl =
                    l_ifoOrderManagerReusableValidations.validateProductCode(
                            l_strProductCode,
                            l_strInstitutionCode);
            log.debug("�������`�F�b�N���܂�");
            log.debug("�擾���������̖���ID = " + l_ifoProductImpl.getProductId());

            //�������𔻒肷��
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_openContractOrderSpec.isBuyToOpenOrder();
            log.debug("is���� = " + l_blnIsBuyContract);

            //�戵�\�ȐV�K���������ǂ����̃`�F�b�N���s���B
            l_ifoOrderManagerReusableValidations.validateHandlingOpenContractOrder(l_subAccount,l_blnIsBuyContract);

            //��������̃`�F�b�N���s���A�敨OP��������I�u�W�F�N�g��ԋp����
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl =
                    l_ifoOrderManagerReusableValidations.validateTradedProduct(
                            l_ifoProductImpl,
                            (WEB3GentradeMarket)l_market,
                            l_blnIsBuyContract,
                            true);
            log.debug("����������`�F�b�N���܂�");
            log.debug("�擾������������̎������ID = " + l_ifoTradedProductImpl.getTradedProductId());

            //���X�R�[�h���擾
            String l_strBranchCode = null;
            l_strBranchCode = l_subAccount.getWeb3GenBranch().getBranchCode();
            log.debug("���X�R�[�h���擾 = " + l_strBranchCode);

            //���͎w�����戵�\���𔻒肷��
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                    l_strBranchCode,
                    l_ifoTradedProductImpl);
            log.debug("���͎w�����戵�\���𔻒肵�܂�");
            
            //���s���ǂ����𔻒肷��
            boolean l_blnIsMatketOrder = false;
            l_blnIsMatketOrder = l_openContractOrderSpec.isMarketOrder();
            log.debug("���s�𔻒肵�܂� ���ʂ�True�̏ꍇ");

            //�������������擾����
            Date l_datOrderExpDate = null;
            l_datOrderExpDate = l_openContractOrderSpec.getOrderExpDate();
            log.debug("�������������擾���܂� = " + l_datOrderExpDate);

            //�����������擾����
            String l_strOrderCond = null;
            l_strOrderCond = l_openContractOrderSpec.getOrderCond();
            log.debug("�����������擾���܂� = " + l_strOrderCond);

            //���s�������擾����
            IfoOrderExecutionConditionType l_executionConditionType = null;
            l_executionConditionType = l_openContractOrderSpec.getExecutionConditionType();
            log.debug("���s�������擾���܂� = " + l_executionConditionType);

            //���������敪���擾����
            String l_strExpirationDateType = null;
            l_strExpirationDateType = l_openContractOrderSpec.getExpirationDateType();
            log.debug("���������敪���擾���܂� = " + l_strExpirationDateType);

            //���������̃`�F�b�N���s��
            Date l_datFirstOrderBizDate = null;
            //�����J�z�̏ꍇ
            if((l_openContractOrderSpec.getFirstOrderUnitId() != null) &&
                (l_openContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
            {
                IfoOrderUnit l_orderUnit = null;
                try
                {
                    l_orderUnit = (IfoOrderUnit)getOrderUnit(
                    l_openContractOrderSpec.getFirstOrderUnitId().longValue());
                }
                catch (NotFoundException l_nfe)
                {
                    return new NewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));     
                }
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
            }

            //validate��������
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                0,
                l_blnIsMatketOrder,
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyContract,
                l_datFirstOrderBizDate,
                l_datOrderExpDate,
                l_strOrderCond,
                l_executionConditionType,
                l_strExpirationDateType,
                l_openContractOrderSpec.getFirstOrderUnitId());
                
            log.debug("�����������`�F�b�N���܂�");

            if (l_ifoOrderUnit == null)
            {
                //���ʂ��擾
                double l_dblQuantity = 0D;
                l_dblQuantity = l_openContractOrderSpec.getQuantity();
                log.debug("���ʂ��擾���܂�" + l_dblQuantity);

                //���ʂ̃`�F�b�N���s��
                l_ifoOrderManagerReusableValidations.validateQuantity(
                        l_subAccount,
                        l_ifoTradedProductImpl,
                        l_dblQuantity,
                        l_blnIsBuyContract,
                        true);
                log.debug("���ʂ��`�F�b�N���܂�");
            }

            //�w�l���擾����
            double l_dblLimitPrice = 0D;
            l_dblLimitPrice = l_openContractOrderSpec.getLimitPrice();
            log.debug("�w�l���`�F�b�N���܂�" + l_dblLimitPrice);

            //�w�l�̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                    l_dblLimitPrice,
                    l_ifoTradedProductImpl,
                    l_subAccount);
            log.debug("�w�l���`�F�b�N���܂�");

            //�t�w�l��l���擾����B
            double l_dblStopOrderPrice = l_openContractOrderSpec.getStopOrderPrice();            
            
            //W�w�l�̒����w�l���擾����
            double l_dblWLimitPriceChange = 0D;
            l_dblWLimitPriceChange = l_openContractOrderSpec.getWLimitPriceChange();
            log.debug("W�w�l�̒����w�l���擾���܂� = " + l_dblWLimitPriceChange);

            //�iW�w�l�j���s�������擾����
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                l_openContractOrderSpec.getWLimitExecCondType();

            //validateW�w�l����()
            String l_strWLimitPriceChange = 
                WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                0,
                l_dblLimitPrice,
                l_strOrderCond,
                l_dblStopOrderPrice,
                l_strWLimitPriceChange,
                l_wLimitExecCondType,
                null,
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyContract);
            
            l_processingResult = ProcessingResult.newSuccessResultInstance();

            l_newOrderValidationResult = new NewOrderValidationResult(l_processingResult);

            log.exiting(STR_METHOD_NAME);

            return l_newOrderValidationResult;
        }
        catch (WEB3BaseException l_webx)
        {
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
    }

    /**
     * (validate�ԍϒ���)<BR>
     * OP�ԍϒ��������R�����s���B<BR>
     * �ivalidateOptionSettleContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * this.validate�ԍϒ���()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [����]<BR>
     * �@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e<BR>
     * �@@���ʁF�@@null<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_settleContractOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec)
    {
        return this.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec, null);
    }

    /**
     * (validate�ԍϒ���)<BR>
     * �ivalidateSettleContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * OP�ԍϒ��������R��<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�����jvalidate�ԍϒ����v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_settleContractOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@param l_ifoContractImpl - (�敨OP����)<BR>
     * �敨OP����<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 404D8EFC0051
     */
    public NewOrderValidationResult validateSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3IfoContractImpl l_ifoContractImpl)
    {
        final String STR_METHOD_NAME =
            "validateSettleContractOrder(WEB3GentradeSubAccount, " +
            "WEB3IfoSettleContractOrderSpec, WEB3IfoContractImpl)";

        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_settleContractOrderSpec == null)
        {
            //��O���X���[����
            log.error(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }

        ProcessingResult l_processingResult = null;

        //�V�K�������R�����ʃI�u�W�F�N�g
        NewOrderValidationResult l_newOrderValidationResult = null;

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
            WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
            l_ifoOrderManagerReusableValidations =
                 (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

            //�������ʃ`�F�b�N�����{����
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            log.debug("�������ʃ`�F�b�N�����{���܂�");

            // ���Ύ���ȊO�i�p�����[�^.���� == null�j�̏ꍇ
            if (l_ifoContractImpl == null)
            {
                //�ԍϒ������e�Ɋ֘A����ԍό��ʃG���g���̔z����擾����
                SettleContractEntry l_settleContractEntry = null;
                l_settleContractEntry = l_settleContractOrderSpec.getSettleContractEntries()[0];

                //����ID���擾
                long l_lngContractID = 0L;
                l_lngContractID = l_settleContractEntry.getContractId();

                //����ID�ɊY�����錚�ʃI�u�W�F�N�g���擾����
                l_ifoContractImpl = new WEB3IfoContractImpl(l_lngContractID);
            }

            //�s��ID���擾
            long l_lngMarketId = 0L;
            l_lngMarketId = l_ifoContractImpl.getMarketId();
            log.debug("�s��ID���擾 = " + l_lngMarketId);

            //�s��I�u�W�F�N�g���擾
            Market l_market = null;
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);//throw NotFoundException

            //�،���ЃR�[�h���擾
            String l_strInstitutionCode = null;
            l_strInstitutionCode = l_settleContractOrderSpec.getInstitutionCode();
            log.debug("�،���ЃR�[�h���擾 = " + l_strInstitutionCode);

            //�s��R�[�h�̃`�F�b�N�����{����
            l_market =
                    l_ifoOrderManagerReusableValidations.validateMarket(l_market.getMarketCode(), l_strInstitutionCode);
            log.debug("�s��R�[�h�̃`�F�b�N�����{���܂�");

            //�敨OP�����I�u�W�F�N�g���擾����
            WEB3IfoProductImpl l_ifoProductImpl = null;
            l_ifoProductImpl = (WEB3IfoProductImpl)l_ifoContractImpl.getProduct();

            //�����R�[�h���擾����
            String l_strProductCode = null;
            l_strProductCode = l_ifoProductImpl.getProductCode();
            log.debug("�����R�[�h���擾����" + l_strProductCode);

            //�����̃`�F�b�N���s���A�����I�u�W�F�N�g��ԋp����
            l_ifoProductImpl = l_ifoOrderManagerReusableValidations.validateProductCode(
                    l_strProductCode,
                    l_strInstitutionCode);

            //�������𔻒肷��
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_ifoContractImpl.isLong();
            log.debug("�������𔻒肷�� = " + l_blnIsBuyContract);

            //��������̃`�F�b�N���s���A�敨OP��������I�u�W�F�N�g��ԋp����
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl =
                    l_ifoOrderManagerReusableValidations.validateTradedProduct(
                            l_ifoProductImpl,
                            (WEB3GentradeMarket)l_market,
                            l_blnIsBuyContract,
                            false);

            //���X�R�[�h���擾
            String l_strBranchCode = null;
            l_strBranchCode = l_subAccount.getWeb3GenBranch().getBranchCode();
            log.debug("���X�R�[�h���擾 = " + l_strBranchCode);

            //���͎w�����戵�\���𔻒肷��
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                    l_strBranchCode,
                    l_ifoTradedProductImpl);
            log.debug("���͎w�����戵�\���𔻒肵�܂�");
            
            //���s���ǂ����𔻒肷��
            boolean l_blnIsMatketOrder = false;
            l_blnIsMatketOrder = l_settleContractOrderSpec.isMarketOrder();
            log.debug("���s���ǂ����𔻒肷�� = " + l_blnIsMatketOrder);

            //�������������擾����
            Date l_datOrderExpDate = null;
            l_datOrderExpDate = l_settleContractOrderSpec.getOrderExpDate();
            log.debug("�������������擾���� = " + l_datOrderExpDate);

            //�����������擾����
            String l_strOrderCond = null;
            l_strOrderCond = l_settleContractOrderSpec.getOrderCond();
            log.debug("�����������擾���� = " + l_strOrderCond);

            //���s�������擾����
            IfoOrderExecutionConditionType l_executionConditionType = null;
            l_executionConditionType = l_settleContractOrderSpec.getExecutionConditionType();
            log.debug("���s�������擾���� = " + l_executionConditionType);

            //���������敪���擾����
            String l_strExpirationDateType = null;
            l_strExpirationDateType = l_settleContractOrderSpec.getExpirationDateType();
            log.debug("���������敪���擾���܂� = " + l_strExpirationDateType);

            Date l_datFirstOrderBizDate = null;
            //�����J�z�̏ꍇ
            if((l_settleContractOrderSpec.getFirstOrderUnitId() != null) &&
                (l_settleContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
            {
                IfoOrderUnit l_orderUnit = null;
                l_orderUnit = (IfoOrderUnit)getOrderUnit(
                    l_settleContractOrderSpec.getFirstOrderUnitId().longValue());
                IfoOrderUnitRow l_orderUnitRow =
                    (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
            }

            //���������̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                    l_subAccount,
                    0,
                    l_blnIsMatketOrder,
                    l_ifoTradedProductImpl,
                    false,
                    l_blnIsBuyContract,
                    l_datFirstOrderBizDate,
                    l_datOrderExpDate,
                    l_strOrderCond,
                    l_executionConditionType,
                    l_strExpirationDateType,
                    l_settleContractOrderSpec.getFirstOrderUnitId());
            log.debug("���������̃`�F�b�N���s��");

            //���ʂ��擾
            double l_dblTotalQuantity = 0D;
            l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();
            log.debug("���ʂ��擾 = " + l_dblTotalQuantity);

            //���ʂ̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateQuantity(
                    l_subAccount,
                    l_ifoTradedProductImpl,
                    l_dblTotalQuantity,
                    l_blnIsBuyContract,
                    false);
            log.debug("���ʂ̃`�F�b�N���s�܂�");

            //�w�l���擾����
            double l_dblLimitPrice = 0D;
            l_dblLimitPrice = l_settleContractOrderSpec.getLimitPrice();
            log.debug("�w�l���擾���� = " + l_dblLimitPrice);

            //�w�l�̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                   l_dblLimitPrice,
                   l_ifoTradedProductImpl,
                   l_subAccount);
            log.debug("�w�l�̃`�F�b�N���s��");

            //W�w�l�̒����w�l���擾����
            double l_dblWLimitPriceChange = 0D;
            l_dblWLimitPriceChange = l_settleContractOrderSpec.getWLimitPriceChange();
            log.debug("W�w�l�̒����w�l���擾���� = " + l_dblWLimitPriceChange);

            //�t�w�l��l���擾����B
            double l_dblStopOrderPrice = l_settleContractOrderSpec.getStopOrderPrice();
            
            String l_strWLimitPriceChange = WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
            
            // W�w�l�j���s�������擾����
			IfoOrderExecutionConditionType l_wLimitExecCondType = 
				l_settleContractOrderSpec.getWLimitExecCondType();

            // validateW�w�l����()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                0,
                l_dblLimitPrice,
                l_strOrderCond,
                l_dblStopOrderPrice,
                l_strWLimitPriceChange,
				l_wLimitExecCondType,
                null,
                l_ifoTradedProductImpl,
                false,
                l_ifoContractImpl.isShort());          

            l_processingResult = ProcessingResult.newSuccessResultInstance();

            l_newOrderValidationResult = new NewOrderValidationResult(l_processingResult);
        }
        catch (DataQueryException l_dqex)
        {
             log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
             return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (DataNetworkException l_dnex)
        {
             log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
             return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (WEB3BaseException l_webx)
        {
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);

        return l_newOrderValidationResult;
    }

    /**
     * (calc�T�Z��n���)<BR>
     * �T�Z��n���z���Z�o���ĕԋp����B<BR>
     * �V�[�P���X�}�u�iOP�����jcalc�T�Z��n����v�Q�ƁB<BR>
     * �P�j�@@�߂�l�N���X����<BR>
     * �@@�T�Z��n����v�Z���ʃC���X�^���X�𐶐�����B<BR>
     * �Q�j is�w�l�Z�b�g<BR>
     * �@@����.�萔���I�u�W�F�N�g�Ɏw�l���ǂ����̃t���O���Z�b�g����B<BR>
     * �@@[setIs�w�l()�Ɏw�肷�����]<BR>
     * �@@is�w�l�F<BR>
     * �@@�@@����.�v�Z�P����0�̏ꍇ�Afalse�B<BR>
     * �@@�@@�ȊO�Atrue�B<BR>
     * <BR>
     * �R�j�@@�v�Z�P���擾<BR>
     * �@@���s�i�萔��.is�w�l() == false�j�̏ꍇ�A<BR>
     * �@@�����R���ʃ`�F�b�N.get���s���v�Z�P��()���R�[�����������擾����B<BR>
     * �@@�擾�����������v�Z�P���Ƃ���B<BR>
     * <BR>
     * �@@[get���s���v�Z�P��()�Ɏw�肷�����]<BR>
     * �@@�敨OP��������F�@@�敨OP�������<BR>
     * �@@���X�F�@@�i�萔��.get���XID()�ɊY�����镔�X�I�u�W�F�N�g�j<BR>
     * <BR>
     * �R�j�@@����p�v�Z�p������擾����B<BR>
     * �|�V�K���̏ꍇ�iis�ԍϒ��� == false�j<BR>
     * �@@�v�Z�T�[�r�X.calc�S���������()�ɂčS������������v�Z����B<BR>
     * <BR>
     * �@@[calc�S��������� ����]<BR>
     * �@@���ʁF�@@����<BR>
     * �@@�v�Z�P���F�@@�v�Z�P��<BR>
     * �@@���X�h�c�F�@@�萔��.get���XID()<BR>
     * �@@�萔�����i�R�[�h�F�@@�萔��.get�萔�����i�R�[�h()<BR>
     * �@@is�w�l�F�@@�i�萔��.is�w�l==true�̏ꍇtrue�A�ȊOfalse�j<BR>
     * �@@�敨OP�����F�@@�敨OP�������<BR>
     * <BR>
     * �@@�T�Z��n����v�Z���ʂ̔����S������Ɍv�Z���ʂ̍S������������Z�b�g����B<BR>
     * <BR>
     * �|�ԍς̏ꍇ�iis�ԍϒ��� == true�j<BR>
     * �@@�v�Z�T�[�r�X.calc�������()�ɂĔ���������v�Z����B<BR>
     * <BR>
     * �@@[calc������� ����]<BR>
     * �@@���ʁF�@@����<BR>
     * �@@�v�Z�P���F�@@�v�Z�P��<BR>
     * �@@�敨OP�����F�@@�敨OP�������<BR>
     * <BR>
     * �@@�T�Z��n����v�Z���ʂ̔����S�������0���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@������z�l�`�F�b�N<BR>
     * �@@�|�iisSkip���z�`�F�b�N == false�j�̏ꍇ�̂݁B<BR>
     * �@@�����R���ʃ`�F�b�N.validate����\������z()�ɂ�<BR>
     * ����p�v�Z�p����̋��z�`�F�b�N���s���B<BR>
     * <BR>
     * �@@[validate����\������z ����]<BR>
     * �@@���X�F�@@�⏕����.get����X()<BR>
     * �@@�s��F�@@�������.get�s��()<BR>
     * �@@��������F�@@�R�j�̌v�Z���ʁi�ԍρF��������A�V�K���F�����S������j<BR>
     * �@@�����^�C�v�F�@@�⏕����.get�ڋq().�����^�C�v<BR>
     * <BR>
     * �T�j�@@�萔�����擾����B<BR>
     * <BR>
     * �@@�v�Z�T�[�r�X.calc�ϑ��萔��()�ɂĎ萔�����Z�o����B<BR>
     * <BR>
     * �@@[calc�萔�� ����]<BR>
     * �@@�萔���F�@@�萔��<BR>
     * �@@�⏕�����F�@@�⏕����<BR>
     * <BR>
     * �U�j�@@�萔���ɂ��������ł��擾����B<BR>
     * <BR>
     * �@@�v�Z�T�[�r�X.calc�����()�ɂĎ萔�����Z�o����B<BR>
     * <BR>
     * �@@[calc�萔�� ����]<BR>
     * �@@���z�F�@@�萔�����z�icalc�ϑ��萔��()�̖߂�l�j<BR>
     * �@@����F�@@�萔��.������<BR>
     * �@@�⏕�����F�@@�⏕����<BR>
     * <BR>
     * �V�j�@@�T�Z��n������Z�o����B<BR>
     * <BR>
     * �@@�v�Z�T�[�r�X.calc��n����ɂĊT�Z��n������Z�o����B<BR>
     * <BR>
     * �@@[calc��n��� ����]<BR>
     * �@@�����F�@@����<BR>
     * �@@����p�v�Z�p����F�@@�R�j�Ŏ擾��������p�v�Z�p���<BR>
     * �@@�ϑ��萔���F�@@�T�j�Ŏ擾�����萔��<BR>
     * �@@�ϑ��萔������ŁF�@@�U�j�Ŏ擾���������<BR>
     * <BR>
     * �@@�T�Z��n����v�Z����.set�T�Z��n���()�ɂČv�Z���ʂ��Z�b�g����B<BR>
     * <BR>
     * �@@�T�Z��n����v�Z���ʃI�u�W�F�N�g��ԋp����B<BR>
     * @@param l_commission - (�萔��)
     * @@param l_dblLimitPrice - �w�l<BR>
     * <BR>
     * 0���w�肳�ꂽ�ꍇ�́A�������v�Z�P���Ƃ��ė��p����B<BR>
     *
     * @@param l_subAccount - (�⏕����)
     * @@param l_tradeProduct - �敨OP�������
     * @@param l_dblQuantity - ����
     * @@param l_dealing - ����
     * �@@SideEnum.BUY�i���j
     * �@@SideEnum.SELL�i���j
     *
     * @@param l_blnIsClosingContractOrder - (is�ԍϒ���)<BR>
     * �ԍϒ����̏ꍇ��true�A�V�K�������̏ꍇ��false���w�肷��B<BR>
     * @@param l_blnIsSkipPriceCheck - (isSkip���z�`�F�b�N)<BR>
     * �Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j<BR>
     * �ꍇ��true���w�肷��B<BR>
     *
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 405939770399
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcEstimateDeliveryAmount(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        SubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_tradeProduct,
        double l_dblQuantity,
        SideEnum l_dealing,
        boolean l_blnIsClosingContractOrder,
        boolean l_blnIsSkipPriceCheck)
            throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "calcEstimateDeliveryAmount(l_commission,l_dblLimitPrice,l_subAccount,l_tradeProduct," +
            "l_dblQuantity,l_dealing,l_blnIsClosingContractOrder,l_blnIsSkipPriceCheck)";

        log.entering(STR_METHOD_NAME);

        if (l_commission == null || l_subAccount == null || l_tradeProduct ==null || l_dealing == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("l_commission.getCommissionProductCode() = " + l_commission.getCommissionProductCode());

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�T�Z��n����v�Z���ʃC���X�^���X�𐶐�����
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //���XID���擾����
        long l_lngBranchID = 0L;
        l_lngBranchID = l_commission.getBranchId();
        log.debug("���XID���擾���� = " + l_lngBranchID);

        WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_mainAccount.getBranch();
        //���s���v�Z�P��
        double l_dblmakeOrderCalcUnitPrice = 0D;

        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        if (l_dblLimitPrice == 0D)
        {
            //���s���v�Z�P�����擾����
            if (SideEnum.BUY.equals(l_dealing) && !l_blnIsClosingContractOrder)
            {
                l_dblmakeOrderCalcUnitPrice =
                    l_ifoOrderManagerReusableValidations.getMakeOrderCalcUnitPrice(l_tradeProduct, l_branch);
            }
            else if (SideEnum.SELL.equals(l_dealing) || l_blnIsClosingContractOrder)
            {
                l_dblmakeOrderCalcUnitPrice = l_productManager.getCurrentPrice(l_tradeProduct);
            }

            //�v�Z�P�������s���v�Z�P����ݒ�
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblmakeOrderCalcUnitPrice);
        }
        else
        {

            l_dblmakeOrderCalcUnitPrice = l_dblLimitPrice;
            //�v�Z�P�����w�l��ݒ�
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
        }

        //�v�Z�T�[�r�X�I�u�W�F�N�g���쐬����
        WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
        l_ifoBizLogicProvider =
                (WEB3IfoBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();

        //�S���������
        double l_dblRestraintTurnOver = 0D;
        //�������
        double l_dblTurnOver = 0D;
        //���o��v�Z�p���
        double l_dblExpensesCalcAmount = 0D;

        if (l_blnIsClosingContractOrder)
        {
            //�ԍς̏ꍇ
            log.debug("�ԍς̏ꍇ");
            l_dblTurnOver =
                l_ifoBizLogicProvider.calcTurnOver(l_dblQuantity, l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(), l_tradeProduct);
            log.debug("����������擾���܂� = " + l_dblTurnOver);

            //�T�Z��n����v�Z���ʂ̔����S�������"0"��ݒ�
            log.debug("�T�Z��n����v�Z���ʂ̔����S�������\"0\"��ݒ�");

            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(0);
            l_dblExpensesCalcAmount = l_dblTurnOver;
        }
        else
        {
            //�V�K���̏ꍇ
            log.debug("�V�K���̏ꍇ");
            //�S������������擾����
            log.debug("l_commission.getCommissionProductCode() = " + l_commission.getCommissionProductCode());
            l_dblRestraintTurnOver = l_ifoBizLogicProvider.calcRestraintTurnOver(
                    l_dblQuantity,
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_lngBranchID,
                    l_commission.getCommissionProductCode(),
                    l_commission.isLimitPrice(),
                    l_tradeProduct);

            log.debug("�S������������擾���� = " + l_dblRestraintTurnOver);

            //�T�Z��n����v�Z���ʂ̔����S�������ݒ�
            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblRestraintTurnOver);

            l_dblExpensesCalcAmount = l_dblRestraintTurnOver;
        }

        log.debug("�萔���I�u�W�F�N�g�ɏ��o��v�Z�p��� = " + l_dblExpensesCalcAmount);

        //�萔���I�u�W�F�N�g�ɏ��o��v�Z�p������Z�b�g����
        l_commission.setExpensesCalcAmount(l_dblExpensesCalcAmount);
        log.debug("�萔���I�u�W�F�N�g�ɏ��o��v�Z�p������Z�b�g����");

        //�����I�u�W�F�N�g
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        if (l_blnIsSkipPriceCheck == false)
        {
            //isSkip���z�`�F�b�N == false�̏ꍇ
            log.debug("isSkip���z�`�F�b�N == false�̏ꍇ");
            //�����R���ʃ`�F�b�N.validate����\������z()�ɂď���p�v�Z�p����̋��z�`�F�b�N���s���B
            l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                l_branch,
                l_commission.getExpensesCalcAmount(),
                l_mainAccountRow.getAccountType(),
                WEB3FuturesOptionDivDef.OPTION);
            log.debug("�����R���ʃ`�F�b�N.validate����\������z()�ɂď���p�v�Z�p����̋��z�`�F�b�N���s���B");
        }

        //�ϑ��萔�����擾����
        l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);
        double l_dblCommission = 0D;
        l_dblCommission = l_commission.getCommission();

        log.debug("�ϑ��萔�����擾���� = " + l_dblCommission);

        //�萔���ɂ��������ł��擾����
        double l_dblSalesTax = 0D;
        l_dblSalesTax =
            l_ifoBizLogicProvider.calcSalesTax(
                l_dblCommission,
                l_commission.getOrderBizDate(),
                l_subAccount);

        log.debug("�萔���ɂ��������ł��擾���� = " + l_dblSalesTax);


        //�T�Z��n������Z�o����
        double l_dblDeliveryAmount = 0D;
        l_dblDeliveryAmount = l_ifoBizLogicProvider.calcDeliveryAmount(
                    l_dealing, l_dblExpensesCalcAmount, l_dblCommission, l_dblSalesTax);

        log.debug("�T�Z��n������Z�o���� = " + l_dblDeliveryAmount);

        //�T�Z��n����v�Z����.set�T�Z��n���()�ɂČv�Z���ʂ��Z�b�g����
        l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(l_dblDeliveryAmount);

        //�萔���R�[�X���Z�b�g����B
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());

        //�萔�����Z�b�g����B
        l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());

        //�萔������ł��Z�b�g����B
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblSalesTax);
        log.exiting(STR_METHOD_NAME);

        //�T�Z��n����v�Z���ʃI�u�W�F�N�g��ԋp����
        return l_ifoEstimateDeliveryAmountCalcResult;
    }

    /**
     * (validate�ԍϒ�������)<BR>
     *�ivalidateChangeSettleContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * this.validate�ԍϒ�������()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate�ԍϒ�������()�Ɏw�肷�����] <BR>
     * �⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e<BR>
     * isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j<BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_ifoChangeSettleContractOrderSpec - (�ԍϒ������e) <BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateChangeSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoChangeSettleContractOrderSpec l_settleContractChangeSpec)
    {
        final String STR_METHOD_NAME =
            " validateChangeSettleContractOrder(WEB3GentradeSubAccount, WEB3IfoChangeSettleContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        //this.validate�ԍϒ�������()�ɏ������Ϗ��idelegate�j����B
        //[validate�ԍϒ�������()�Ɏw�肷�����]
        //�⏕�����F�@@�p�����[�^.�⏕����
        //�ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e
        //isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j
        OrderValidationResult l_orderValidationResult =
            this.validateChangeSettleContractOrder(
                l_subAccount,
                l_settleContractChangeSpec,
                false);

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }
    /**
     * (validate�ԍϒ�������)<BR>
     * �ivalidateChangeSettleContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * -------------<BR>
     * �V�[�P���X�} <BR>
     * �u�iOP�����jvalidate�ԍϒ��������v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_settleContractChangeSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * �x���󋵃`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B<BR>
     * @@throws WEB3BaseException
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 40679287025B
     */
    public OrderValidationResult validateChangeSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoChangeSettleContractOrderSpec l_settleContractChangeSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME =
            "validateChangeSettleContractOrder(l_subAccount,l_settleContractChangeSpec,l_blnIsSkipDelayStatusCheck)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_settleContractChangeSpec == null)
        {
            //��O���X���[����
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }

        //�����`�F�b�N���ʃI�u�W�F�N�g
        OrderValidationResult l_orderValidationResult = null;

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //�������ʃ`�F�b�N�����{����
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

            //����ID���擾
            long l_lngOrderID = 0L;
            l_lngOrderID = l_settleContractChangeSpec.getOrderId();

            //�����I�u�W�F�N�g���擾
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID);//throw NotFoundException

            //�������̃X�e�C�^�X�������\�ȏ�Ԃ��𔻒肷��
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);

            //�����P��ID���擾
            long l_lngOrderUnitID = 0L;
            l_lngOrderUnitID = l_settleContractChangeSpec.getOrderUnitId();

            //�����P�ʃI�u�W�F�N�g���擾
            OrderUnit l_orderUnit = null;
            l_orderUnit = this.getOrderUnit(l_lngOrderUnitID);//throw NotFoundException

            //�����P��Row�I�u�W�F�N�g���擾
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //�敨OP�����I�u�W�F�N�g
            WEB3IfoProductImpl l_ifoProductImpl = null;

            //�����̃`�F�b�N���s���A�����I�u�W�F�N�g��ԋp����
            l_ifoProductImpl =
                l_ifoOrderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            //�s��I�u�W�F�N�g
            WEB3GentradeMarket l_market = null;
            //�s��̃`�F�b�N
            l_market =
                (WEB3GentradeMarket)l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            //�ԍϒ��������ʃG���g���Ɋ֘A����ԍό��ʃG���g���̔z����擾����
            SettleContractEntry l_settleContractEntry = null;

            if (l_settleContractChangeSpec.getAfterChangeSettleContractEntries() == null || l_settleContractChangeSpec.getAfterChangeSettleContractEntries().length == 0)
            {
                return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));
            }
            l_settleContractEntry = l_settleContractChangeSpec.getAfterChangeSettleContractEntries()[0];

            //����ID���擾
            long l_lngContractID = 0L;
            l_lngContractID = l_settleContractEntry.getContractId();

            //����ID�ɊY�����錚�ʃI�u�W�F�N�g���擾����
            IfoContractImpl l_ifoContractImpl = null;
            l_ifoContractImpl = new IfoContractImpl(l_lngContractID);

            //�������̋敪���擾
            boolean l_blnIsBuyContract = false;
            l_blnIsBuyContract = l_ifoContractImpl.isLong();

            //��������̃`�F�b�N���s���A�敨OP��������I�u�W�F�N�g��ԋp����
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
            l_ifoTradedProductImpl = l_ifoOrderManagerReusableValidations.validateTradedProduct(
                l_ifoProductImpl,
                l_market,
                l_blnIsBuyContract,
                false);

            //���͎w�����戵�\���𔻒肷��
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                l_subAccount.getWeb3GenBranch().getBranchCode(),
                l_ifoTradedProductImpl);

            //is���s���擾����
            boolean l_blnIsAfterChangePriceMarket = false;
            l_blnIsAfterChangePriceMarket = l_settleContractChangeSpec.isAfterChangePriceMarket();

            //�������������擾����
            Date l_datChangeExpirationDate = l_settleContractChangeSpec.getChangeExpirationDate();

            //�����������擾����
            String l_strOrderCond = l_settleContractChangeSpec.getOrderCond();

            //�������s�������擾����
            IfoOrderExecutionConditionType l_ChangeExecCondType = l_settleContractChangeSpec.getChangeExecCondType();

            //�����㒍�������敪���擾����
            String l_strExpirationDateType = l_settleContractChangeSpec.getExpirationDateType();
            
            //OP�����}�l�[�W��.get���񒍕��̒����P��(�����P��).���������擾����
            IfoOrderUnitRow l_firstOrderUnitRow =
                (IfoOrderUnitRow)this.getFirstOrderUnit((IfoOrderUnit)l_orderUnit).getDataSourceObject();
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
            
            //���������̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                l_lngOrderUnitID,
                l_blnIsAfterChangePriceMarket,
                l_ifoTradedProductImpl,
                false,
                l_blnIsBuyContract,
                l_bizDate,
                l_datChangeExpirationDate,
                l_strOrderCond,
                l_ChangeExecCondType,
                l_strExpirationDateType,
                new Long(l_firstOrderUnitRow.getOrderUnitId()));

            //������̐��ʂ��擾����
            double l_dblAfterChangeTotalQuantity = l_settleContractChangeSpec.getAfterChangeTotalQuantity();

            //���ʂ�0�܂��́A�}�C�i�X�l�łȂ����Ƃ̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateQuantity(l_dblAfterChangeTotalQuantity);

            //������w�l���擾����
            double l_dblAfterChangePrice = l_settleContractChangeSpec.getAfterChangePrice();

            //�w�l�̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                l_dblAfterChangePrice,
                l_ifoTradedProductImpl,
                l_subAccount);

            //validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum,
            //�����^�C�v�F�@@�����P��.�����^�C�v
            //�敨�^�I�v�V�����敪�F�@@�����P��.�敨�^�I�v�V�����敪
            //���X�F�@@�⏕����.get�戵�X()
            //����敪�F�@@�����P��.����敪
            //�������F�@@�����P��.������
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            //������̔����������Z�q���擾����
            String l_strOrderOperatorAfterChange =
                l_settleContractChangeSpec.getOrderCondOperator();

            //������̋t�w�l��l�^�C�v���擾����
            String l_strStopOrderBasePriceTypeAfterChange =
                l_settleContractChangeSpec.getStopOrderBasePriceType();

            //������̋t�w�l��l���擾����
            double l_dblStopOrderBasePriceAfterChange =
                l_settleContractChangeSpec.getStopOrderBasePrice();

            //(W�w�l)�����w�l���擾����B
            double l_dblWLimitPriceChange = l_settleContractChangeSpec.getWLimitPriceChange();
            
            //������̎��������擾����
            Date l_datExpirationDateAfterChange = l_settleContractChangeSpec.getChangeExpirationDate();

            //�iW�w�l�j���s�������擾����
            IfoOrderExecutionConditionType l_wLimitExecCondType = l_settleContractChangeSpec.getWLimitExecCondType();
            
            //������̔��������敪���擾����
            String l_strAfterExpirationDateType = l_settleContractChangeSpec.getExpirationDateType();

            //������̓��󐔗ʂ��擾����B
            SettleContractEntry[] l_modifiedSettleContractEntries=l_settleContractChangeSpec.getAfterChangeSettleContractEntries();

            String l_strWStopPrice = WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
           
            //validateW�w�l����
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                l_lngOrderUnitID,
                l_dblAfterChangePrice,
                l_strOrderCond,
                l_dblStopOrderBasePriceAfterChange,
                l_strWStopPrice,
                l_wLimitExecCondType,
                l_settleContractChangeSpec.getWLimitEnableStatusDiv(),
                l_ifoTradedProductImpl,
                false,
                l_ifoContractImpl.isShort());
            
            //�������͒l���Ó��ł��邩�`�F�b�N����
            l_ifoOrderManagerReusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblAfterChangeTotalQuantity,
                l_dblAfterChangePrice,
                l_ChangeExecCondType,
                l_strOrderCond,
                l_strOrderOperatorAfterChange,
                l_strStopOrderBasePriceTypeAfterChange,
                l_dblStopOrderBasePriceAfterChange,
                l_dblWLimitPriceChange,
                l_wLimitExecCondType,
                l_datExpirationDateAfterChange,
                l_strAfterExpirationDateType,
                l_modifiedSettleContractEntries);

            //validate����������Rev���(IfoOrderUnit, double, double, IfoOrderExecutionConditionType)
            //�������ɒ���Rev������l�𒴂��Ă��Ȃ����ǂ������`�F�b�N����B
            //[validate����������Rev���()�Ɏw�肷�����]
            //�����P�ʁF�@@�������i�������j�̒����P�ʃI�u�W�F�N�g
            //�������ʁF�@@getAfterChangeTotalQuantity()
            //�����w�l�F�@@getAfterChangePrice()
            //�������s�����F�@@get�������s����()
            l_ifoOrderManagerReusableValidations.validateChangeOrderRevLimit(
                (IfoOrderUnit)l_orderUnit,
                l_dblAfterChangeTotalQuantity,
                l_dblAfterChangePrice,
                l_ChangeExecCondType);

            l_orderValidationResult = new OrderValidationResult(
                ProcessingResult.newSuccessResultInstance());
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch (DataQueryException l_dqex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (DataNetworkException l_dnex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003));
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult();
        }
        catch (WEB3BaseException l_webx)
        {
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_webx.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (validate�V�K����������)<BR>
     *�ivalidateChangeOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * this.validate�V�K����������()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate�V�K����������()�Ɏw�肷�����]<BR>
     * �⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �V�K���������e�F�@@�p�����[�^.�V�K���������e<BR>
     * isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j<BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_changeOrderSpec - (�V�K���������e) <BR>
     * �V�K���������e�I�u�W�F�N�g<BR>
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateChangeOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec)
    {
        final String STR_METHOD_NAME =
            " validateChangeOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractChangeSpec)";
        log.entering(STR_METHOD_NAME);
        
        //this.validate�V�K����������()�ɏ������Ϗ��idelegate�j����B
        OrderValidationResult l_orderValidationResult =
            this.validateChangeOrder(
                l_subAccount,
                l_ifoOpenContractChangeSpec,
                false);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }
    /**
     * (validate�V�K����������)<BR>
     * �ivalidateChangeOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iOP�����jvalidate�V�K�����������v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_openContractChangeSpec - �V�K���������e�I�u�W�F�N�g<BR>
     * �V�K���������e�I�u�W�F�N�g<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * �x���󋵃`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B <BR>
     * @@throws WEB3BaseException
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 406792CB026A
     */
    public OrderValidationResult validateChangeOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME =
            "validateChangeOrder(l_subAccount,l_openContractChangeSpec,l_blnIsSkipDelayStatusCheck)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_ifoOpenContractChangeSpec == null)
        {
            //��O���X���[����
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017));
        }

        //�����`�F�b�N���ʃI�u�W�F�N�g
        OrderValidationResult l_orderValidationResult = null;

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g�𐶐�
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();
        try
        {
            //�����`�F�b�N���s��
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);

            //�����h�c���擾
            long l_lngOrderID = l_ifoOpenContractChangeSpec.getOrderId();

            //�����I�u�W�F�N�g���擾
            Order l_order = this.getOrder(l_lngOrderID);

            //�������̃X�e�C�^�X�������\�ȏ�Ԃ��𔻒肷��B
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);

            //�����P�ʂh�c���擾
            long l_lngOrderUnitID = l_ifoOpenContractChangeSpec.getOrderUnitId();

            //�����P�ʃI�u�W�F�N�g���擾
            OrderUnit l_orderUnit = this.getOrderUnit(l_lngOrderUnitID);
            
            //�����P��Row�I�u�W�F�N�g���擾
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            //�敨OP�����I�u�W�F�N�g
            WEB3IfoProductImpl l_ifoProductImpl =
                l_ifoOrderManagerReusableValidations.validateProductID(l_ifoOrderUnitRow.getProductId());

            //�s��I�u�W�F�N�g
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            IfoContractOpenOrderUnitImpl l_ifoContractOpenOrderUnitImpl =
                (IfoContractOpenOrderUnitImpl)l_orderUnit;

            //�����敪���擾
            boolean l_blnIsBuyContract = false;
            if (SideEnum.BUY.equals(l_ifoContractOpenOrderUnitImpl.getSide()))
            {
                l_blnIsBuyContract = true;
            }
            else
            {
                l_blnIsBuyContract = false;
            }

            //��������I�u�W�F�N�g���擾
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
                l_ifoOrderManagerReusableValidations.validateTradedProduct(
                    l_ifoProductImpl,
                    l_market,
                    l_blnIsBuyContract,
                    true);

            //���͎w�����戵�\���𔻒肷��
            l_ifoOrderManagerReusableValidations.validateHandlingIndex(
                l_subAccount.getWeb3GenBranch().getBranchCode(),
                l_ifoTradedProductImpl);

            //���݂̕⏕�����Ŏ戵�\�Ȓ����ǂ����̃`�F�b�N���s���B
            l_ifoOrderManagerReusableValidations.validateHandlingOpenContractOrder(l_subAccount,l_blnIsBuyContract);

            //is���s���擾����
            boolean l_blnIsAfterChangePriceMarket = false;
            l_blnIsAfterChangePriceMarket = l_ifoOpenContractChangeSpec.isAfterChangePriceMarket();

            //�������������擾����
            Date l_datChangeExpirationDate = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            //�����������擾����
            String l_strOrderCond = l_ifoOpenContractChangeSpec.getOrderCond();

            //�������s�������擾����
            IfoOrderExecutionConditionType l_ChangeExecCondType =
                l_ifoOpenContractChangeSpec.getChangeExecCondType();

            //�������������敪���擾����
            String l_strExpirationDateType = l_ifoOpenContractChangeSpec.getExpirationDateType();
            
            //OP�����}�l�[�W��.get���񒍕��̒����P��(�����P��).���������擾����
            IfoOrderUnitRow l_firstOrderUnitRow =
                (IfoOrderUnitRow)this.getFirstOrderUnit((IfoOrderUnit)l_orderUnit).getDataSourceObject();
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
            
            //���������̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderCond(
                l_subAccount,
                l_lngOrderUnitID,
                l_blnIsAfterChangePriceMarket,
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyContract,
                l_bizDate,
                l_datChangeExpirationDate,
                l_strOrderCond,
                l_ChangeExecCondType,
                l_strExpirationDateType,
                new Long(l_firstOrderUnitRow.getOrderUnitId()));

            //�����㐔�ʂ��擾����
            double l_dblAfterChangeOriginalQuantity = l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity();

            //���ʂ�0�܂��́A�}�C�i�X�l�łȂ����Ƃ̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateQuantity(l_dblAfterChangeOriginalQuantity);

            //������w�l���擾����
            double l_dblAfterChangePrice = l_ifoOpenContractChangeSpec.getAfterChangePrice();

            //�w�l�̃`�F�b�N���s��
            l_ifoOrderManagerReusableValidations.validateOrderUnitPrice(
                l_dblAfterChangePrice,
                l_ifoTradedProductImpl,
                l_subAccount);            

            //validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum,
            //�����^�C�v�F�@@�����P��.�����^�C�v
            //�敨�^�I�v�V�����敪�F�@@�����P��.�敨�^�I�v�V�����敪
            //���X�F�@@�⏕����.get�戵�X()
            //����敪�F�@@�����P��.����敪
            //�������F�@@�����P��.������
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            //������iW�w�l�j�����w�l���擾����
            double l_dblWLimitPriceChange = l_ifoOpenContractChangeSpec.getWLimitPriceChange();

            //������̔����������Z�q���擾����
            String l_strOrderOperatorAfterChange = l_ifoOpenContractChangeSpec.getOrderCondOperator();

            //������̋t�w�l��l�^�C�v���擾����
            String l_strStopOrderBasePriceTypeAfterChange = l_ifoOpenContractChangeSpec.getStopOrderBasePriceType();

            //������̋t�w�l��l���擾����
            double l_dblStopOrderBasePriceAfterChange = l_ifoOpenContractChangeSpec.getStopOrderBasePrice();

            //�iW�w�l�j���s�������擾����
            IfoOrderExecutionConditionType l_wLimitExecCondType = l_ifoOpenContractChangeSpec.getWLimitExecCondType();
            
            //������̎��������擾����
            Date l_datExpirationDateAfterChange = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            //������̒��������敪���擾����
            String l_strAfterExpirationDateType = l_ifoOpenContractChangeSpec.getExpirationDateType();

            //is����
            boolean l_blnIsBuyToOpenOrder = false;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_blnIsBuyToOpenOrder = true;
            }
            
            String l_strWStopPrice = WEB3StringTypeUtility.formatNumber(l_dblWLimitPriceChange);
            
            //validateW�w�l����()
            l_ifoOrderManagerReusableValidations.validateWLimitPriceOrder(
                l_subAccount,
                l_lngOrderUnitID,
                l_dblAfterChangePrice,
                l_strOrderCond,
                l_dblStopOrderBasePriceAfterChange,
                l_strWStopPrice,
                l_wLimitExecCondType,
                l_ifoOpenContractChangeSpec.getWLimitEnableStatusDiv(),
                l_ifoTradedProductImpl,
                true,
                l_blnIsBuyToOpenOrder);
            
            //�������͒l���Ó��ł��邩�`�F�b�N����
            l_ifoOrderManagerReusableValidations.validateOrderChangeSpec(
                l_orderUnit,
                l_dblAfterChangeOriginalQuantity,
                l_dblAfterChangePrice,
                l_ChangeExecCondType,
                l_strOrderCond,
                l_strOrderOperatorAfterChange,
                l_strStopOrderBasePriceTypeAfterChange,
                l_dblStopOrderBasePriceAfterChange,
                l_dblWLimitPriceChange,
                l_wLimitExecCondType,
                l_datExpirationDateAfterChange,
                l_strAfterExpirationDateType,
                null);


            //validate����������Rev���(IfoOrderUnit, double, double, IfoOrderExecutionConditionType)
            //�������ɒ���Rev������l�𒴂��Ă��Ȃ����ǂ������`�F�b�N����B
            //[validate����������Rev���()�Ɏw�肷�����]
            //�����P�ʁF�@@�������i�������j�̒����P�ʃI�u�W�F�N�g
            //�������ʁF�@@getAfterChangeOriginalQuantity()
            //�����w�l�F�@@getAfterChangePrice()
            //�������s�����F�@@get�������s����()
            l_ifoOrderManagerReusableValidations.validateChangeOrderRevLimit(
                (IfoOrderUnit)l_orderUnit,
                l_dblAfterChangeOriginalQuantity,
                l_dblAfterChangePrice,
                l_ChangeExecCondType);

            l_orderValidationResult = new OrderValidationResult(
                ProcessingResult.newSuccessResultInstance());
        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult();
        }
        catch (WEB3BaseException l_webx)
        {
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_webx.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (calc�������T�Z��n���)<BR>
     *�����������̊T�Z��n���z���Z�o���ĕԋp����B<BR>
     *�V�[�P���X�}�u�iOP�����jcalc�������T�Z��n����v�Q�ƁB<BR>
     * @@param l_commission - (�萔��)
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * 0���w�肳�ꂽ�ꍇ�́A�������v�Z�P���Ƃ��ė��p����B<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_futuresOptionTradedProduct - �敨OP�������<BR>
     * @@param l_dblQuantity - ������������<BR>
     * @@param l_dealing - ����<BR>
     * �@@SideEnum.BUY�i���j<BR>
     * �@@SideEnum.SELL�i���j<BR>
     * @@param l_blnIsClosingContractOrder - is�ԍϒ���<BR>
     * �ԍϒ����̏ꍇ��true�A<BR>
     * �V�K�������̏ꍇ��false���w�肷��B<BR>
     * @@param l_dblExecQuantity - �����P��.��萔��<BR>
     * @@param l_dblSumTransferredAssetBookValue - �����P��.���v�����z<BR>
     * @@param l_blnIsSkipPriceCheck - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ<BR>
     * ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B<BR>
     * @@return webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 4069228D0203
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult calcChangeEstimateDeliveryAmount(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_futuresOptionTradedProduct,
        double l_dblQuantity,
        SideEnum l_dealing,
        boolean l_blnIsClosingContractOrder,
        double l_dblExecQuantity,
        double l_dblSumTransferredAssetBookValue,
        boolean l_blnIsSkipPriceCheck)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcChangeEstimateDeliveryAmount";

        BigDecimal l_bdSumTransferredAssetBookValue = new BigDecimal(l_dblSumTransferredAssetBookValue + "");

        //calc�T�Z��n������R�[��
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
        //�v�Z�T�[�r�X�I�u�W�F�N�g���쐬����
        WEB3IfoBizLogicProvider l_ifoBizLogicProvider = null;
        l_ifoBizLogicProvider = (WEB3IfoBizLogicProvider) l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();
        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        //���X�h�c���擾����B
        long l_lngBranchId = l_commission.getBranchId();

        WEB3GentradeBranch l_GentradeBranch = null;
        try
        {
            l_GentradeBranch = new WEB3GentradeBranch(l_lngBranchId);
        }
        catch (DataFindException l_nfex)
        {
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_nfex)
        {
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_nfex)
        {
            //��O���X���[����
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME);
        }
        double l_dblCalcUnitPrice = 0;
        //�i����.�w�l() == 0�j�̏ꍇ�̂ݎ��{����B
        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        log.debug("����.���� = " + l_dealing);
        log.debug("is�ԍϒ��� = " + l_blnIsClosingContractOrder);
        if (l_dblLimitPrice == 0)
        {
            //����.���� == �h���h and����.is�ԍϒ��� == false �̏ꍇ�A���{�B
            if (SideEnum.BUY.equals(l_dealing) && !l_blnIsClosingContractOrder)
            {
                //���s���v�Z�P�����擾����B
                l_dblCalcUnitPrice = l_ifoOrderManagerReusableValidations.getMakeOrderCalcUnitPrice(l_futuresOptionTradedProduct,l_GentradeBranch);
                log.debug("���s���v�Z�P�� = " + l_dblCalcUnitPrice);
            }
            //����.���� == �h���h or����.is�ԍϒ��� == true �̏ꍇ�A���{�B
            else if (SideEnum.SELL.equals(l_dealing) || l_blnIsClosingContractOrder)
            {
                //�v�Z�P���i�����j���擾����B
                l_dblCalcUnitPrice = l_productMgr.getCurrentPrice(l_futuresOptionTradedProduct);
                log.debug("�v�Z�P���i�����j = " + l_dblCalcUnitPrice);
            }

            //�v�Z�P�����Z�b�g����B
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblCalcUnitPrice);
        }
        else
        {
            //�v�Z�P�����Z�b�g����B
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(l_dblLimitPrice);
        }

        //�萔�����i�R�[�h���擾����B
        String l_strCommissionProductCode = l_commission.getCommissionProductCode();
        //�S���������
        double l_dblRestraintTurnOver = 0;
        //�������
        double l_dblTurnOver = 0;
        //�S������������v�Z����B���@@�V�K���iis�ԍϒ���==false�j�̏ꍇ�̂ݎ��{�B
        if (!l_blnIsClosingContractOrder)
        {
            l_dblRestraintTurnOver = l_ifoBizLogicProvider.calcRestraintTurnOver((l_dblQuantity - l_dblExecQuantity),
                        l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                        l_lngBranchId,
                        l_strCommissionProductCode,
                        l_commission.isLimitPrice(),
                        l_futuresOptionTradedProduct);
            BigDecimal l_bdRestraintTurnOver = new BigDecimal(l_dblRestraintTurnOver + "");

            //�߂�l�I�u�W�F�N�g�ɍS������������Z�b�g����B
            //�V�K���̏ꍇ�iis�ԍϒ���==false�j�A�S����������B
            log.debug("�S��������� = " + l_dblRestraintTurnOver);
            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(l_dblRestraintTurnOver);
            //���o��v�Z�p����F�@@�|�V�K���̏ꍇ�iis�ԍϒ���==false�j�A�S��������� + ����.���v�����z
            log.debug("���o��v�Z�p��� �|�V�K���̏ꍇ= " +
                l_bdRestraintTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());
            l_commission.setExpensesCalcAmount(
                l_bdRestraintTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());
        }
        else
        {
            //�ԍς̏ꍇ�iis�ԍϒ���==true�j�A0�B
            l_ifoEstimateDeliveryAmountCalcResult.setRestraintTurnover(0);

            //����������v�Z����B���@@�ԍρiis�ԍϒ���==true�j�̏ꍇ�̂ݎ��{�B
            l_dblTurnOver = l_ifoBizLogicProvider.calcTurnOver((l_dblQuantity - l_dblExecQuantity),
                    l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice(),
                    l_futuresOptionTradedProduct);
            BigDecimal l_bdTurnOver = new BigDecimal(l_dblTurnOver + "");

            log.debug("������� = " + l_dblTurnOver);
            //���o��v�Z�p��� -�ԍς̏ꍇ�iis�ԍϒ���==true�j�A������� + ����.���v�����z
            log.debug("���o��v�Z�p��� �|�ԍς̏ꍇ= " + l_bdTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());
            l_commission.setExpensesCalcAmount(
                l_bdTurnOver.add(l_bdSumTransferredAssetBookValue).doubleValue());
        }

        //�����R���ʃ`�F�b�N.validate����\������z()�ɂď���p�v�Z�p����̋��z�`�F�b�N���s���B���@@�iisSkip���z�`�F�b�N == false�j�̏ꍇ�̂݁B
        if (!l_blnIsSkipPriceCheck)
        {
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
            l_ifoOrderManagerReusableValidations.validateOrderMaxAmount(
                    l_subAccount.getWeb3GenBranch(),
                    l_commission.getExpensesCalcAmount(),
                    l_mainAccountRow.getAccountType(),
                    WEB3FuturesOptionDivDef.OPTION);
        }

        //�ϑ��萔�����Z�o����B
        l_ifoBizLogicProvider.calcCommission(l_commission,l_subAccount);
        //�ϑ��萔���ɂ��������ł��Z�o����B
        double l_dblSalesTax = l_ifoBizLogicProvider.calcSalesTax(l_commission.getCommission(),l_commission.getOrderBizDate(),l_subAccount);
        log.debug("�ϑ��萔���ɂ��������� = " + l_dblSalesTax);
        //�T�Z��n������Z�o����B
        double l_dblDeliveryAmount = l_ifoBizLogicProvider.calcDeliveryAmount(
                l_dealing,
                l_commission.getExpensesCalcAmount(),
                l_commission.getCommission(),
                l_dblSalesTax);
        log.debug("�T�Z��n��� = " + l_dblDeliveryAmount);
        //�߂�l�I�u�W�F�N�g�ɊT�Z��n������Z�b�g����B
        l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(l_dblDeliveryAmount);
        //�萔���R�[�X���Z�b�g����B
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse(l_commission.getCommissionCourseDiv());
        //�萔�����Z�b�g����B
        log.debug("�萔�����z = " + l_commission.getCommission());
        l_ifoEstimateDeliveryAmountCalcResult.setCommission(l_commission.getCommission());
        //�萔������ł��Z�b�g����B
        l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(l_dblSalesTax);
        //�T�Z��n����v�Z���ʃI�u�W�F�N�g��ԋp����B
        return l_ifoEstimateDeliveryAmountCalcResult;
    }

    /**
     * (validate�������)<BR>
     * �ivalidateCancelOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ��������R�����s���B<BR>
     * <BR>
     * -------------<BR>
     * �V�[�P���X�} <BR>
     * �u�iOP�����jvalidate��������v�Q�ƁB<BR>
     *
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_cancelOrderSpec - ����������e
     * @@throws WEB3BaseException
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 406B87F900B4
     */
    public OrderValidationResult validateCancelOrder(
        WEB3GentradeSubAccount l_subAccount,
        CancelOrderSpec l_cancelOrderSpec)
    {
        final String STR_METHOD_NAME = "validateCancelOrder(l_subAccount,l_cancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80017));

        }
        //�����`�F�b�N���ʃI�u�W�F�N�g
        OrderValidationResult l_orderValidationResult = null;

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g�𐶐�
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //�����`�F�b�N���s��
            this.validateOrder(l_subAccount, WEB3FuturesOptionDivDef.OPTION);
            log.debug("�����`�F�b�N���s��");

            //����ID���擾
            long l_lngOrderID = 0L;
            l_lngOrderID = l_cancelOrderSpec.getOrderId();
            log.debug("����ID���擾 = " + l_lngOrderID);

            //�����I�u�W�F�N�g���擾
            Order l_order = null;
            l_order = this.getOrder(l_lngOrderID);

            //��������\��Ԃ��`�F�b�N
            l_ifoOrderManagerReusableValidations.validateOrderForCancellation(l_order);
            log.debug("��������\��Ԃ��`�F�b�N");

            //�����P�ʃI�u�W�F�N�g
            OrderUnit l_orderUnit = null;
            l_orderUnit = l_order.getOrderUnits()[0];

            //�����P��ROW�I�u�W�F�N�g
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //�s��̃`�F�b�N���s���A�s��I�u�W�F�N�g��ԋp����B
            l_ifoOrderManagerReusableValidations.validateMarketID(l_ifoOrderUnitRow.getMarketId());

            //validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum,
            //�敨�^�I�v�V�����敪 : String, ���X : ���X, ����敪 : String, ������ : Date)
            //�����^�C�v�F�@@�����P��.�����^�C�v
            //�敨�^�I�v�V�����敪�F�@@�����P��.�敨�^�I�v�V�����敪
            //���X�F�@@����.�⏕����.get�戵�X()
            //����敪�F�@@�����P��.����敪
            //�������F�@@�����P��.������
            Date l_datBizDate = WEB3DateUtility.getDate(
                l_ifoOrderUnitRow.getBizDate(), "yyyyMMdd");

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                l_ifoOrderUnitRow.getProductType(),
                l_ifoOrderUnitRow.getFutureOptionDiv(),
                l_subAccount.getWeb3GenBranch(),
                l_ifoOrderUnitRow.getSessionType(),
                l_datBizDate);

            l_orderValidationResult = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());

        }
        catch (NotFoundException l_nfex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfex);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ovex);
            return l_ovex.getValidationResult();
        }
        catch (WEB3BaseException l_webx)
        {
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_webx.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);

        return l_orderValidationResult;

    }

    /**
     * (validate����)<BR>
     * �������ʃ`�F�b�N�����{����B<BR>
     * <BR>
     * �ȉ��̃`�F�b�N���s���B<BR>
     * �@@�|�V�X�e����~���`�F�b�N<BR>
     * �@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j<BR>
     * �@@�|�敨OP�����J�݃`�F�b�N<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�����jvalidate�����v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_strFuturesOptionDivision - �敨�^�I�v�V�����敪
     * �@@1�F�敨 2�F�I�v�V����
     * @@throws WEB3BaseException
     * @@roseuid 4076560302BE
     */
    public void validateOrder(
        SubAccount l_subAccount,
        String l_strFuturesOptionDivision)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(l_subAccount,l_strFuturesOptionDivision)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g�𐶐�
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;

        l_ifoOrderManagerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();

        try
        {
            //��t���ԃ`�F�b�N�^�V�X�e��������~�`�F�b�N
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("��t���ԃ`�F�b�N�^�V�X�e��������~�`�F�b�N");

            l_ifoOrderManagerReusableValidations.commonFirstValidationsForAllOperations(l_subAccount);
            log.debug("commonFirstValidationsForAllOperations");

            //�ڋq���敨�I�v�V�����������J�݂��Ă��邩���`�F�b�N����
            l_ifoOrderManagerReusableValidations.validateFuturesOptionAccountOpen(l_subAccount, l_strFuturesOptionDivision);
            log.debug("�ڋq���敨�I�v�V�����������J�݂��Ă��邩���`�F�b�N����");
        }
        catch (OrderValidationException l_ovex)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_ovex);

            throw new WEB3BaseException(
                l_ovex.getValidationResult().getProcessingResult().getErrorInfo(),
                getClass().getName() + STR_METHOD_NAME);
        }


        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (create�ԍό��ʃG���g��)<BR>
     * �������ʂ�D�揇�ʂɏ]���Ċe���ʂɔz�����A�ԍό��ʃG���g���̔z�����<BR>������B
     * <BR>
     * <BR>
     * (*)
     * ��ʂ��u����сv�w�肳�ꂽ�ꍇ�i��������==0�j��
     * �I�[�o�[���[�h���\�b�h�ɈϏ�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�����jcreate�ԍό��ʃG���g���v�Q�ƁB<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �Ώے����P�ʂh�c�B<BR>
     * �i���������̏ꍇ�̂ݎd�l�j<BR>
     *
     * @@param l_dblOrderQuantity - ��������
     * @@param l_settleContractEntry - (�ԍό���)<BR>
     * �ԍό��ʃI�u�W�F�N�g�̔z��<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 407B711D01E3
     */
    public SettleContractEntry[] createSettleContractEntry(
        long l_lngOrderUnitId,
        double l_dblOrderQuantity,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSettleContractEntry(l_lngOrderUnitId,l_dblOrderQuantity," +
            "l_futuresOptionsCloseMarginContractUnits)";
        log.entering(STR_METHOD_NAME);

        if (l_futuresOptionsCloseMarginContractUnits == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }
        else if (l_futuresOptionsCloseMarginContractUnits.length == 0)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�敨OP�|�W�V�����}�l�[�W��
        WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
            (WEB3IfoPositionManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

        //�ԍό��ʃG���g���̔z��
        SettleContractEntry[] l_settleContractOrderEntrys = null;
        //�ԍό��ʃG���g���̔z�񃊃X�g
        ArrayList l_lisSettleContractOrderEntrys = new ArrayList();
        //�ԍό��ʃG���g���I�u�W�F�N�g
        SettleContractEntry l_settleContractOrderEntry = null;
        //�ԍό���ID
        long l_lngContractID = 0L;
        //�ԍό��ʐ���
        double l_dblContractQuantity = 0D;
        //�敨OP���ʃI�u�W�F�N�g
        WEB3IfoContractImpl l_ifoContractimpl = null;

        //�������ʁi�c���j�FBigDecimal�𐶐�����
        BigDecimal l_bdOrderQuantity = new BigDecimal(l_dblOrderQuantity + "");
        log.debug("�������ʁi�c���j�FBigDecimal�𐶐����� = " + l_bdOrderQuantity);

        //�����_���w��̏ꍇ�i��������==0�j�A�I�[�o�[���[�h���\�b�h�ɈϏ�
        if (l_dblOrderQuantity == 0)
        {
            l_settleContractOrderEntrys =
                this.createSettleContractEntry(l_lngOrderUnitId, l_futuresOptionsCloseMarginContractUnits);
        }
        else
        {
            int i = 0;
            int l_intLen = 0;
            l_intLen = l_futuresOptionsCloseMarginContractUnits.length;

            try
            {
                //(*)���������i�����P��ID > 0�j�̏ꍇ
                OrderUnit l_orderUnit = null;
                boolean l_isPartiallyExecuted = false;
                BigDecimal l_bdTotalClosingExecuteQuantity = new BigDecimal("0");
                if (l_lngOrderUnitId > 0)
                {
                    l_orderUnit = getOrderUnit(l_lngOrderUnitId);
                    
                    l_isPartiallyExecuted = l_orderUnit.isPartiallyExecuted();
                    
                    if (l_isPartiallyExecuted)
                    {
                        //(*1)���v�ԍϖ�萔��
                        //�@@�����̒����P��ID�ɊY�����錚���ԍώw����̈ꗗ�̓��A
                        //�@@�����Ώۂ̌��Ϗ��ʁiindex + 1�j�`�ŉ��ʂ܂ł̌����ԍώw����.�ԍϖ�萔�ʂ�SUM�l
                        int l_intIndex = 0;
                        
                        IfoContractSettleOrderUnitImpl l_contractSettleOrderUnit =
                            (IfoContractSettleOrderUnitImpl) l_orderUnit;
                        
                        IfoClosingContractSpec[] l_closingContractSpecs =
                            l_contractSettleOrderUnit.getContractsToClose();
                        
                        if (l_closingContractSpecs != null)
                        {
                            l_intIndex = l_closingContractSpecs.length;
                        }
                        
                        for (int j = 0; j < l_intIndex; j++)
                        {
                            l_bdTotalClosingExecuteQuantity =
                                l_bdTotalClosingExecuteQuantity.add(
                                    new BigDecimal(
                                        String.valueOf(
                                            l_closingContractSpecs[j].getExecutedQuantity())));
                        }
                    }
                }

                for (i = 0; i < l_intLen; i++)
                {
                    //�ԍό���ID���擾
                    l_lngContractID =  Long.parseLong(l_futuresOptionsCloseMarginContractUnits[i].id);
                    log.debug("�ԍό���ID���擾 = " + l_lngContractID);

                    //�敨OP���ʃI�u�W�F�N�g�𐶐�
                    l_ifoContractimpl =
                        (WEB3IfoContractImpl)l_ifoPositionManagerImpl.getContract(l_lngContractID);

                    //�ԍό��ʐ��ʂ��擾
                    l_dblContractQuantity = l_ifoContractimpl.getQuantity();
                    log.debug("�ԍό��ʐ��ʂ��擾 = " + l_dblContractQuantity);
                    log.debug("���Y�����ԍϖ��ϐ��� = " + l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId));
                    //�ԍω\���ʁFgetQuantity() - getLockedQuantity() + get���b�N������(�����P�ʂh�c)�@@+�@@get�ԍϖ��ϐ���(�����P��ID)
                    BigDecimal l_bdQuantity = new BigDecimal(l_ifoContractimpl.getQuantity() + "");
                    BigDecimal l_bdLockedQuantity = new BigDecimal(l_ifoContractimpl.getLockedQuantity() + "");
                    BigDecimal l_bdLockedQuantityOrderUnitId =
                        new BigDecimal(l_ifoContractimpl.getLockedQuantity(l_lngOrderUnitId) + "");
                    BigDecimal l_bdClosingExecuteContract =
                        new BigDecimal(l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId) + "");
                    BigDecimal l_bdClosingContractAvailableQuantity =
                        l_bdQuantity.subtract(l_bdLockedQuantity).add(
                            l_bdLockedQuantityOrderUnitId).add(l_bdClosingExecuteContract);

                    log.debug("�ԍω\�c������ = " + l_bdClosingContractAvailableQuantity);
                    log.debug("�����P��ID = " + l_lngOrderUnitId);

                    //�V�K�����i�����P��ID==0�j�̏ꍇ
                    if (l_lngOrderUnitId == 0)
                    {
                        log.debug("�V�K�����i�����P��ID==0�j�̏ꍇ");

                        //��������0�̕ԍό��ʃG���g���𐶐����Ȃ�
                        if (l_dblContractQuantity == 0)
                        {
                            log.debug("��������0�̕ԍό��ʃG���g���𐶐����Ȃ�");
                            continue;
                        }
                        //�������ʂ�0�ȊO�ꍇ
                        else
                        {
                            log.debug("�������ʂ�0�ȊO�ꍇ");
                            log.debug("�������ʁi�c���j = " + l_bdOrderQuantity);
                            //�ԍό��ʃG���g���I�u�W�F�N�g�𐶐�
                            if (l_bdOrderQuantity.compareTo(l_bdClosingContractAvailableQuantity) >= 0)
                            {
                                l_settleContractOrderEntry =
                                    new SettleContractEntry(
                                            l_lngContractID,
                                            l_bdClosingContractAvailableQuantity.doubleValue());
                            }
                            else
                            {
                                l_settleContractOrderEntry =
                                    new SettleContractEntry(
                                            l_lngContractID,
                                            l_bdOrderQuantity.doubleValue());

                            }

                            log.debug("�ԍό��ʃG���g���I�u�W�F�N�g�����X�g�ɒǉ�");

                            //�ԍό��ʃG���g���I�u�W�F�N�g�����X�g�ɒǉ�
                            l_lisSettleContractOrderEntrys.add(l_settleContractOrderEntry);

                            l_bdOrderQuantity = l_bdOrderQuantity.subtract(l_bdClosingContractAvailableQuantity);

                            if (l_bdOrderQuantity.doubleValue() <= 0D)
                            {
                                break;
                            }
                        }
                    }
                    else
                    {
                        log.debug("�V�K�����i�����P��ID !=0�j�̏ꍇ");
                        log.debug("�������ʁi�c���j = " + l_bdOrderQuantity);

                        //�ԍό��ʃG���g���I�u�W�F�N�g�𐶐�
                        BigDecimal l_bdClosingQuantity = null;
                        if (l_bdOrderQuantity.compareTo(l_bdClosingContractAvailableQuantity) >= 0)
                        {
                            l_bdClosingQuantity = 
                                new BigDecimal(
                                    String.valueOf(
                                        l_bdClosingContractAvailableQuantity.doubleValue()));
                        }
                        else
                        {
                            l_bdClosingQuantity = 
                                new BigDecimal(
                                    String.valueOf(
                                        l_bdOrderQuantity.doubleValue()));
                        }
                        
                        //(*)�ꕔ���̒��������i�����P��ID != 0 && isPartiallyExecuted() == true�j�̏ꍇ�A
                        //���ʕԍώw����̖�萔�ʂ̍l�����s��
                        if (l_isPartiallyExecuted)
                        {
                            //(*)���ʂ̕ԍώw����̖�萔�ʂ̍l��
                            //�@@�@@����萔�ʁi�c���j���Z�o����B
                            //�@@�@@����萔�ʁi�c���j = ���������i�c���j - ���v�ԍϖ�萔��(*1)
                            BigDecimal l_bdUnExecutedQuantity =
                                l_bdOrderQuantity.subtract(l_bdTotalClosingExecuteQuantity);
                            
                            //�@@�A����萔�ʁi�c���j + get�ԍϖ��ϐ���()�̖߂�l < ��L�t���[�ɂČ��肵���y�ԍϒ������ʁz�̏ꍇ�A
                            //�@@�@@��L�t���[�ɂČ��肵���y�ԍϒ������ʁz = ����萔�ʁi�c���j + get�ԍϖ��ϐ���()�̖߂�l�@@�Ƃ���B
                            BigDecimal l_bdClosingExecuteContractCnt =
                                new BigDecimal(l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId) + "");
                            if ((l_bdUnExecutedQuantity.add(l_bdClosingExecuteContractCnt)).compareTo(l_bdClosingQuantity) < 0)
                            {
                                l_bdClosingQuantity =
                                    l_bdUnExecutedQuantity.add(l_bdClosingExecuteContractCnt);
                            }
                            
                            //���v�ԍϖ�萔��(*1)���Aget�ԍϖ�萔��()�̖߂�l�����Z����B
                            l_bdTotalClosingExecuteQuantity =
                                l_bdTotalClosingExecuteQuantity.subtract(l_bdClosingExecuteContractCnt);
                        }
                        
                        l_settleContractOrderEntry =
                            new SettleContractEntry(
                                    l_lngContractID,
                                    l_bdClosingQuantity.doubleValue());
                        
                        //�ԍό��ʃG���g���I�u�W�F�N�g�����X�g�ɒǉ�
                        l_lisSettleContractOrderEntrys.add(l_settleContractOrderEntry);
                        log.debug("�ԍό��ʃG���g���I�u�W�F�N�g�����X�g�ɒǉ�");

                        l_bdOrderQuantity = l_bdOrderQuantity.subtract(l_bdClosingQuantity);
                        //���Z�̌��ʃ}�C�i�X�l�ɂȂ����ꍇ�͎c��0�Ƃ���B
                        if (l_bdOrderQuantity.longValue() < 0)
                        {
                            l_bdOrderQuantity = new BigDecimal(0);
                        }

                    }

                }

                //�ԍω\���ʃ`�F�b�N(�������ʁi�c���j��0�ȏ�̏ꍇ)
                if (l_bdOrderQuantity.longValue() > 0)
                {
                    log.error(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01055);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01055,
                        getClass().getName() + STR_METHOD_NAME);
                }

                l_settleContractOrderEntrys = (SettleContractEntry[])l_lisSettleContractOrderEntrys.toArray(new SettleContractEntry[l_lisSettleContractOrderEntrys.size()]);

            }
            catch (NotFoundException l_dfex)
            {
                log.error(this.getClass().getName() + STR_METHOD_NAME, l_dfex);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntrys;
    }

    /**
     * (create�ԍό��ʃG���g��)<BR>
     * ���Ϗ��ʁ^�ԍω\���ʂ��`�F�b�N���A�ԍό��ʃG���g���̔z����쐬����B<BR>
     * <BR>
     * (*)<BR>
     * ��ʂ��u����сv�w�肳�ꂽ�ꍇ�ɃI�[�o�[���[�h���\�b�h���Ϗ������B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�����jcreate�ԍό��ʃG���g���i����юw��j�v�Q�ƁB<BR>
     * @@param l_lngOrderUnitId - �Ώے����P�ʂh�c�B
     * �i���������̏ꍇ�̂ݎd�l�j
     *
     * @@param l_settleContractEntry - (�ԍό���)
     * �ԍό��ʃI�u�W�F�N�g�̔z��
     * �i���N�G�X�g�f�[�^�j
     * @@throws WEB3BaseException
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry[]
     * @@roseuid 407B994403E7
     */
    public SettleContractEntry[] createSettleContractEntry(
        long l_lngOrderUnitId,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSettleContractEntry(l_lngOrderUnitId,l_futuresOptionsCloseMarginContractUnits)";
        log.entering(STR_METHOD_NAME);

        if (l_futuresOptionsCloseMarginContractUnits == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }
        else if (l_futuresOptionsCloseMarginContractUnits.length == 0)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //�敨OP�|�W�V�����}�l�[�W��
        WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
            (WEB3IfoPositionManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

        //�ԍό��ʃG���g����TreeMap
        TreeMap l_tmSettleContractOrderEntry = new TreeMap();
        //�ԍό��ʃG���g���I�u�W�F�N�g
        SettleContractEntry[] l_contractOrderEntry = null;
        SettleContractEntry l_settleContractOrderEntry = null;
        //�ԍό���ID
        long l_lngContractID = 0L;
        //�ԍό��ʐ���
        String l_strContractQuantity = null;
        double l_dblContractQuantity = 0D;
        //���Ϗ���
        String l_strSettlePriority = null;
        Integer l_intSettlePriority =null;
        //�ԍϖ��ϐ���
        double l_dblClosingExecuteContractCnt = 0D;
        //�敨OP���ʃI�u�W�F�N�g
        WEB3IfoContractImpl l_ifoContractimpl = null;

        int l_intLen = l_futuresOptionsCloseMarginContractUnits.length;
        try
        {
            //�p�����[�^�̕ԍό���[]�v�f����Loop����
            for (int i = 0; i < l_intLen; i++)
            {
                //�ԍό���ID���擾
                l_lngContractID = Long.parseLong(l_futuresOptionsCloseMarginContractUnits[i].id);
                //�ԍό��ʐ��ʂ��擾
                l_strContractQuantity = l_futuresOptionsCloseMarginContractUnits[i].contractOrderQuantity;
                //���Ϗ��ʂ��擾
                l_strSettlePriority = l_futuresOptionsCloseMarginContractUnits[i].settlePriority;

                log.debug("�ԍό���[" + i + "].ID = " + l_lngContractID);
                log.debug("�ԍό���[" + i + "].���� = " + l_strContractQuantity);
                log.debug("�ԍό���[" + i + "].���Ϗ��� = " + l_strSettlePriority);

                //�V�K�����i�����P��ID==0�j�̏ꍇ
                if ((l_lngOrderUnitId == 0)
                    && ((l_strContractQuantity == null) || l_strContractQuantity.equals("0")))
                {
                    //���ʐ��ʂ�null��������0�̕ԍό��ʃG���g���͐������Ȃ�
                    continue;
                }
                else
                {
                    if (l_strContractQuantity == null)
                    {
                        l_dblContractQuantity = 0D;
                    }
                    else
                    {
                        l_dblContractQuantity = Double.parseDouble(l_strContractQuantity);
                    }
                }

                //�敨OP���ʃI�u�W�F�N�g�𐶐�
                l_ifoContractimpl  =
                    (WEB3IfoContractImpl)l_ifoPositionManagerImpl.getContract(l_lngContractID);
                //�ԍϖ��ϐ��ʂ��擾
                l_dblClosingExecuteContractCnt = l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId);
                log.debug("�ԍϖ��ϐ��ʂ��擾 = " + l_dblClosingExecuteContractCnt);

                //��萔�ʂ��������ʂ�葽���ꍇ
                if (l_dblContractQuantity < l_dblClosingExecuteContractCnt)
                {
                    log.error(this.getClass().getName() + STR_METHOD_NAME,
                            new WEB3BusinessLayerException(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00300,
                                    getClass().getName() + STR_METHOD_NAME));

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00300,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //�ԍό���.���Ϗ��ʂ�null��������0�̏ꍇ
                if (l_strSettlePriority == null || l_strSettlePriority.equals("0"))
                {
                    log.debug("�ԍό���.���Ϗ��ʂ�null��������0");
                    continue;
                }
                l_intSettlePriority = new Integer(l_strSettlePriority);

                //get�̖߂�l��null�łȂ��ꍇ�A���Ϗ��ʔԍ����d�����Ă���Ɣ���
                if (l_tmSettleContractOrderEntry.get(l_intSettlePriority) != null)
                {
                   log.error(this.getClass().getName() + STR_METHOD_NAME,
                        new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00182,
                                getClass().getName() + STR_METHOD_NAME));
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_00182,
                       getClass().getName() + STR_METHOD_NAME);
                }

                //�i�ԍω\���ʃ`�F�b�N�j
                //�ԍω\�c�����ʁF���ʐ��ʁ|���b�N���S���ʁ{���Y�������b�N�����ʁ{���Y�����ԍϖ�萔��()
                log.debug("���ʐ��� = " + l_ifoContractimpl.getQuantity());
                log.debug("���b�N���S���� = " + l_ifoContractimpl.getLockedQuantity());
                log.debug("���Y�������b�N������ = " + l_ifoContractimpl.getLockedQuantity(l_lngOrderUnitId));
                log.debug("���Y�����ԍϖ��ϐ��� = " + l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId));

                double l_dblClosingContractAvailableQuantity = 0D;
                l_dblClosingContractAvailableQuantity =
                    l_ifoContractimpl.getQuantity() -
                    l_ifoContractimpl.getLockedQuantity() +
                    l_ifoContractimpl.getLockedQuantity(l_lngOrderUnitId) +
                    l_ifoContractimpl.getClosingExecuteContractCnt(l_lngOrderUnitId);

                log.debug("�ԍω\�c������ = " + l_dblClosingContractAvailableQuantity);
                log.debug("�ԍό��ʐ��� = " + l_dblContractQuantity);

                //�p�����[�^.���ʒP�ʂ̒������ʂ��ԍω\�c�����ʂ𒴂��Ă���ꍇ
                if (l_dblContractQuantity > l_dblClosingContractAvailableQuantity)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //�ԍό��ʃG���g���𐶐�����
                l_settleContractOrderEntry =
                    new SettleContractEntry(l_lngContractID,l_dblContractQuantity);
                l_tmSettleContractOrderEntry.put(l_intSettlePriority, l_settleContractOrderEntry);
            }

            l_contractOrderEntry = new SettleContractEntry[l_tmSettleContractOrderEntry.size()];
            l_tmSettleContractOrderEntry.values().toArray(l_contractOrderEntry);

        }
        catch (NotFoundException l_nfex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractOrderEntry;
    }

    /**
     * (is����\�ڋq)<BR>
     * �ڋq�̃`�F�b�N(Y�q�A�Ǘ����b�N��)���s���B<BR>
     * �ڋq�`�F�b�N�ŃG���[�ƂȂ����ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * (1)�����`�F�b�N�I�u�W�F�N�g�̎擾<BR>
     * getOrderValidator�ɂ��A�����`�F�b�N�I�u�W�F�N�g���擾����<BR>
     * <BR>
     * (2)�ڋq�`�F�b�N<BR>
     * �����`�F�b�N.validate����\�ڋq(�p�����[�^.�⏕����)���R�[������<BR>
     * <BR>
     * validate����\�ڋq�ŗ�O��throw���ꂽ�ꍇ�ɂ�false���A<BR>
     * ����Ȃ������ꍇ�ɂ�true��ԋp����B<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 407E534202FD
     */
    public boolean isTradedPossibleCustomer(
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTradedPossibleCustomer(l_subAccount)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }
        boolean l_isTradedPossibleCustomer = false;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        log.debug("�⏕����ID = " + l_subAccount.getSubAccountId());

        //�����`�F�b�N�I�u�W�F�N�g�̎擾
        CommonOrderValidator l_commonOrderValidator = null;
        l_commonOrderValidator = l_finApp.getCommonOrderValidator();

        log.debug("Ordervalidater = " + this.getOrderValidator().getClass().getName());

        //�����`�F�b�N���ʃI�u�W�F�N�g�𐶐�
        OrderValidationResult l_orderValidateResult = null;

        log.debug("�⏕����Status = " + l_subAccount.getSubAccountStatus());

        l_orderValidateResult = l_commonOrderValidator.validateSubAccountForTrading(l_subAccount);

        log.debug("�����`�F�b�N���� = " + l_orderValidateResult.getProcessingResult().toString());

        if (OrderValidationResult.VALIDATION_OK_RESULT.equals(l_orderValidateResult))
        {
            //�`�F�b�N���ʂ��n�j�̏ꍇ
            l_isTradedPossibleCustomer = true;
        }
        else
        {
            ////�`�F�b�N���ʂ��m�f�̏ꍇ
            l_isTradedPossibleCustomer = false;
        }

        log.exiting(STR_METHOD_NAME);

        return l_isTradedPossibleCustomer;

    }

    /**
     * (create���ʖ���ByOrder)<BR>
     * �����Ɋ֘A�������ʖ��ׁi�Ɖ�p�j��z��Ŏ擾����B<BR>
     * �w�蒍�����V�K�������̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�����jcreate���ʖ���ByOrder�v�Q�ƁB<BR>
     * @@param l_lngOrderId - �����h�c
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsContractUnit
     * @@throws WEB3BaseException
     * @@roseuid 407F87C602DD
     */
    public WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(
        long l_lngOrderId)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createContractUnitByOrder(l_lngOrderId)";
        log.entering(STR_METHOD_NAME);

        log.debug("����ID = " + l_lngOrderId);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //���ʖ��ׂ̔z��
        WEB3FuturesOptionsContractUnit[] l_futuresOptionsContractUnits = null;

        //���ʖ��ׂ̃��X�g
        ArrayList l_lisFuturesOptionsContractUnits = null;
        l_lisFuturesOptionsContractUnits = new ArrayList();

        //�敨OP�v���_�N�g�}�l�[�W���I�u�W�F�N�g
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = null;
        l_ifoProductManagerImpl =
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).
                    getProductManager();

        //�敨OP�����I�u�W�F�N�g
        WEB3IfoProductImpl l_ifoProductImpl = null;

        try
        {

            OrderUnit[] l_orderUnits = null;
            l_orderUnits = this.getOrderUnits(l_lngOrderId);

            log.debug("�����P�ʔz��I�u�W�F�N�g���쐬����");

            if (l_orderUnits == null || l_orderUnits.length == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //�����P�ʃI�u�W�F�N�g���쐬����
            OrderUnit l_orderUnit = null;
            l_orderUnit = l_orderUnits[0];

            //�����P��Row�I�u�W�F�N�g���쐬����
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //�����P��ID
            long l_lngOrderUnitId = l_ifoOrderUnitRow.getOrderUnitId();

            l_ifoProductImpl = (WEB3IfoProductImpl)l_ifoProductManagerImpl.getProduct(l_ifoOrderUnitRow.getProductId());
            log.debug("�敨OP�����I�u�W�F�N�g = " + l_ifoProductImpl.getProductId());

            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;

            l_ifoTradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoProductManagerImpl.getTradedProduct(l_ifoOrderUnitRow.getProductId(), l_ifoOrderUnitRow.getMarketId());
            log.debug("�敨OP��������I�u�W�F�N�g = " + l_ifoTradedProductImpl.getTradedProductId());

            //���������擾����
            WEB3IfoProductQuote l_currentInfo = l_ifoTradedProductImpl.getCurrentInfo(null);

            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg()) ||
                OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
            {
                //�V�K�������̏ꍇ
                log.debug("�V�K�������̏ꍇ");
                return null;
            }
            else
            {
                log.debug("�V�K�������ȊO�̏ꍇ");

                //�����Y�����R�[�h���擾����
                String l_strUnderlyingProduct = null;
                l_strUnderlyingProduct =
                    l_ifoProductImpl.getUnderlyingProductCode();
                log.debug("�����Y�����R�[�h���擾���� = " + l_strUnderlyingProduct);

                //����J�����_�R���e�L�X�g���̌����Y�����R�[�h�����Z�b�g����
                WEB3GentradeTradingTimeManagement.resetProductCode(l_strUnderlyingProduct);

                IfoContractSettleOrderUnitImpl l_ifoContractSettleOrderUnitImpl = (IfoContractSettleOrderUnitImpl)l_orderUnit;

                //�ԍώw����̔z����擾����
                IfoClosingContractSpec[] l_ifoClosingContractSpecs = null;
                l_ifoClosingContractSpecs =
                    l_ifoContractSettleOrderUnitImpl.getContractsToClose();

                if (l_ifoClosingContractSpecs == null || l_ifoClosingContractSpecs.length == 0)
                {
                    //��O���X���[����
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //�ԍώw����I�u�W�F�N�g
                IfoClosingContractSpec l_ifoClosingContractSpec = null;
                //�ԍώw����ROW�I�u�W�F�N�g
                IfoClosingContractSpecRow l_ifoClosingContractSpecRow = null;
                //�敨OP����
                WEB3IfoContractImpl l_ifoContractImpl = null;
                //����ID
                long l_lngContractID = 0L;
                //���ʐ���
                double l_dblContractQuantity = 0D;
                //�ԍϒ�������
                double l_dblSettleQuantity = 0D;

                int i = 0;
                int l_intLen = 0;
                l_intLen = l_ifoClosingContractSpecs.length;
                log.debug("�ԍώw����z��̒��� = " + l_intLen);

                //���ʖ��ׂ̃I�u�W�F�N�g
                WEB3FuturesOptionsContractUnit l_futuresOptionsContractUnit = null;

                for (i = 0; i < l_intLen; i++)
                {
                    l_futuresOptionsContractUnit = new WEB3FuturesOptionsContractUnit();

                    l_ifoClosingContractSpec = l_ifoClosingContractSpecs[i];
                    l_ifoClosingContractSpecRow =
                        (IfoClosingContractSpecRow)l_ifoClosingContractSpec.getDataSourceObject();

                    //����ID���擾
                    l_lngContractID = l_ifoClosingContractSpecRow.getContractId();
                    
                    //�ԍϒ������ʂ��擾
                    l_dblSettleQuantity = l_ifoClosingContractSpecRow.getQuantity();

                    //���ʃI�u�W�F�N�g���擾
                    l_ifoContractImpl = new WEB3IfoContractImpl(l_lngContractID);

                    //���ʐ��ʂ��擾
                    l_dblContractQuantity = l_ifoContractImpl.getQuantity();

                    //����ID
                    l_futuresOptionsContractUnit.id = String.valueOf(l_lngContractID);
                    log.debug("����ID��ݒ� = " + l_lngContractID);

                    //����
                    l_futuresOptionsContractUnit.openDate = WEB3DateUtility.toDay(l_ifoContractImpl.getOpenDate());
                    log.debug("������ݒ� = " + l_ifoContractImpl.getOpenDate());

                    //���ʐ�
                    l_futuresOptionsContractUnit.contractQuantity =
                        WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
                    log.debug("���ʐ���ݒ� = " + l_dblContractQuantity);

                    //���P��
                    l_futuresOptionsContractUnit.contractPrice =
                        WEB3StringTypeUtility.formatNumber(l_ifoContractImpl.getContractPrice());
                    log.debug("���P����ݒ� = " + l_ifoContractImpl.getContractPrice());
                    
                    //���萔��
                    BigDecimal l_bdContractCommission =
                        new BigDecimal(
                            l_ifoContractImpl.getContractCommission(l_dblSettleQuantity, l_lngOrderUnitId) + "");
                    BigDecimal l_bdContractCommissionConsumptionTax =
                        new BigDecimal(
                            l_ifoContractImpl.getContractCommissionConsumptionTax(l_dblSettleQuantity, l_lngOrderUnitId) + "");
                    double l_dblContractCommission =
                        l_bdContractCommission.add(l_bdContractCommissionConsumptionTax).doubleValue();
                    l_futuresOptionsContractUnit.contractCommission = 
                        WEB3StringTypeUtility.formatNumber(l_dblContractCommission);
                    log.debug("���萔����ݒ� = " + l_dblContractCommission);

                    //�������擾����
                    double l_dblPrice = 0D;
                    if(l_currentInfo != null)
                    {
                        l_dblPrice = l_currentInfo.getCurrentPrice();
                    }

                    //���v
                    double l_dblIncome;
                    l_dblIncome= 
                        l_ifoContractImpl.getEvaluateIncome(
                            l_dblPrice,
                            l_dblSettleQuantity);

                    l_futuresOptionsContractUnit.income =
                            WEB3StringTypeUtility.formatNumber(l_dblIncome);
                    log.debug("���v��ݒ� = " + l_futuresOptionsContractUnit.income);
                    
                    //���v�i���o��j
                    BigDecimal l_bdIncome = new BigDecimal("" + l_dblIncome);
                    BigDecimal l_bdContractCommission1 = new BigDecimal("" + l_dblContractCommission);
                    l_futuresOptionsContractUnit.incomeCost =
                        WEB3StringTypeUtility.formatNumber(l_bdIncome.subtract(l_bdContractCommission1).doubleValue());
                    log.debug("���v�i���o��j��ݒ� = " + (l_futuresOptionsContractUnit.incomeCost));
                    
                    //�ԍϐ���
                    l_futuresOptionsContractUnit.contractOrderQuantity =
                        WEB3StringTypeUtility.formatNumber(l_dblSettleQuantity);
                    log.debug("�ԍϐ��ʂ�ݒ� = " + l_futuresOptionsContractUnit.contractOrderQuantity);

                    //�������z
                    l_futuresOptionsContractUnit.contractExecPrice = 
                        WEB3StringTypeUtility.formatNumber(l_ifoContractImpl.getContractExecutedAmount(l_dblSettleQuantity));
                    log.debug("�������z��ݒ� = " + l_futuresOptionsContractUnit.contractExecPrice);

                    //�ԍϖ�萔��
                    l_futuresOptionsContractUnit.contractExecQuantity = WEB3StringTypeUtility.formatNumber(l_ifoClosingContractSpec.getExecutedQuantity());
                    log.debug("�ԍϖ�萔�ʂ�ݒ� = " + l_futuresOptionsContractUnit.contractExecQuantity);

                    //���Ϗ���
                    if (l_ifoClosingContractSpecRow.getClosingSerialNoIsSet())
                    {
                        l_futuresOptionsContractUnit.settlePriority =
                            String.valueOf(l_ifoClosingContractSpecRow.getClosingSerialNo());
                    }
                    else
                    {
                        l_futuresOptionsContractUnit.settlePriority = "";
                    }
                    log.debug("���Ϗ��ʂ�ݒ� = " + l_futuresOptionsContractUnit.settlePriority);

                    //����敪
                    IfoContractRow l_ifoContractRow =
                        (IfoContractRow)l_ifoContractImpl.getDataSourceObject();
                    l_futuresOptionsContractUnit.sessionType = l_ifoContractRow.getSessionType();

                    l_lisFuturesOptionsContractUnits.add(l_futuresOptionsContractUnit);
                    log.debug("���ʃ��X�g��ǉ�");
                }
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_dqex);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dnex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_dnex);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }

        l_futuresOptionsContractUnits =
            (WEB3FuturesOptionsContractUnit[]) l_lisFuturesOptionsContractUnits.toArray(new WEB3FuturesOptionsContractUnit[l_lisFuturesOptionsContractUnits.size()]);
        log.exiting(STR_METHOD_NAME);

        return l_futuresOptionsContractUnits;
    }

    /**
     * (get�����P��)<BR>
     * �w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * �iSONAR����̃��N�G�X�g�L���[�ɊY������s���擾����ꍇ�ɗ��p�j<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h���،���Ђh�c���擾����B<BR>
     * �Q�j�@@���X�R�[�h��蕔�X�h�c���擾����B<BR>
     * �R�j�@@�ȉ��̏����Œ����P�ʃe�[�u�����������A<BR>
     * �@@��v�����s�Œ����P�ʃI�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * [��������]<BR>
     * �@@�����P��.�،����ID == �i�擾�����،����ID�j<BR>
     * �@@�����P��.���XID == �i�擾�������XID�j<BR>
     * �@@�����P��.���i�^�C�v == �p�����[�^.���i�^�C�v<BR>
     * �@@�����P��.���ʃR�[�h == �p�����[�^.���ʃR�[�h<BR>
     * <BR>
     * �Y���s�����݂��Ȃ������ꍇ�A�����s��v�����ꍇ��<BR>
     * ��O���X���[����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_productType - (���i�^�C�v)M<BR>
     * �iProductTypeEnum�ɂĒ�`�j<BR>
     * @@param l_strDiscriminationCode - ���ʃR�[�h
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 40836B1C0386
     */
    public OrderUnit getOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        ProductTypeEnum l_productType,
        String l_strDiscriminationCode)
            throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
                    "getOrderUnit(l_strSecutieCompanyCode,l_strBranchCode," +
                    "l_productType,l_strDiscriminationCode)";

        log.entering(STR_METHOD_NAME);

        log.debug("�،���ЃR�[�h = " + l_strInstitutionCode);
        log.debug("���X�R�[�h = " + l_strBranchCode);
        log.debug("���i�^�C�v = " + l_productType);
        log.debug("���� = " + l_strDiscriminationCode);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //���ʂ̒����P�ʃ��X�g
        List l_lisSearchResultOrderUnits = null;
        l_lisSearchResultOrderUnits = new ArrayList();

        //�����P�ʃI�u�W�F�N�g
        OrderUnit l_orderUnit = null;
        //���X�I�u�W�F�N�g
        Branch l_branch = null;
        //�،����ID
        Long l_lngInstitutionID = null;
        //���XID
        Long l_lngBranchID = null;

        try
        {
            //���X�I�u�W�F�N�g���擾
            //Throw NotFoundException
            l_branch =
                l_finApp.getAccountManager().getBranch(
                        l_finApp.getAccountManager().getInstitution(l_strInstitutionCode),
                        l_strBranchCode);
            //�،����ID���擾
            l_lngInstitutionID = new Long(l_finApp.getAccountManager().getInstitution(l_strInstitutionCode).getInstitutionId());
            log.debug("�،����ID���擾 = " + l_lngInstitutionID);
            //���XID���擾
            l_lngBranchID= new Long(l_branch.getBranchId());
            log.debug("���XID���擾 = " + l_lngBranchID);

            //����������ǉ�����B
            //�،����ID = ?�@@and�@@���XID = ? and ���i�^�C�v = ? and ���ʃR�[�h = ?
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" branch_id = ? "); //���XID = ?
            l_sbWhere.append(" and product_type = ? "); //���i�^�C�v = ?
            l_sbWhere.append(" and order_request_number = ? "); //���ʃR�[�h = ?

            Object[] l_objIfoOrderUnitWhere = {
                    l_lngBranchID,
                    new Integer(l_productType.intValue()),
                    l_strDiscriminationCode};

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            l_lisSearchResultOrderUnits = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                null,
                null,
                l_objIfoOrderUnitWhere
                );

            log.debug("���ʒ����P�ʃ��X�g = " + l_lisSearchResultOrderUnits.getClass().getName());
            log.debug("���ʒ����P�ʃ��X�g.���� = " + l_lisSearchResultOrderUnits.size());

            //���ʒ����P�ʃ��X�g���`�F�b�N����
            if (l_lisSearchResultOrderUnits == null || l_lisSearchResultOrderUnits.size() == 0)
            {
                //��O���X���[����
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    getClass().getName() + STR_METHOD_NAME);
            }
            if (l_lisSearchResultOrderUnits.size() > 1)
            {
                //�Y���s�����݂����ꍇ�A�����s��v�����ꍇ��O���X���[����
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    getClass().getName() + STR_METHOD_NAME);
            }
            else
            {
                l_orderUnit = super.toOrderUnit((IfoOrderUnitRow)l_lisSearchResultOrderUnits.get(0));
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME, l_nfex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_dnex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_dnex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataFindException l_dfex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_dfex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_dqex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }


        return l_orderUnit;
    }

    /**
     * get�����L�������P��<BR>
     * �igetTodayOpenOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�͈͂̌ڋq���瓖���̗L��������S�Ď擾����B<BR>
     * <BR>
     * �P�j�@@�L���������擾����B<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̏����ƃ\�[�g���ŁA�����P�ʃe�[�u�����Y�����钍���P�ʍs���擾����B <BR>
     * <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@ ���XID in ����.�،����.getBranches()�̖߂�l�̂����ꂩ<BR>
     * �@@�@@�@@���@@�敨�^�I�v�V�����敪 == ����.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@���@@�����L����� == �h�I�[�v���h<BR>
     * �@@�@@�@@���@@����.From����ID <= ����ID <BR>
     * �@@�@@�@@���@@����ID <= ����.To����ID <BR>
     * �@@�@@�@@���@@������ == �Ɩ����t(YYYYMMDD)(*1) <BR>
     * <BR>
     * �@@�@@�@@[order by]<BR>
     * �@@�@@�@@�󒍓����@@����<BR>
     * <BR>
     * �@@�@@�@@(*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * �@@�@@�@@�@@ �����̏ꒆ�ɔ������������݂̂��擾���邽�߂̏���<BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �Q�j�@@�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@this.toOrderUnit()�ɂ��<BR>
     * �@@�@@�擾���������P�ʍs�I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�@@�擾���������P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �@@�@@���Y���f�[�^�Ȃ��̏ꍇ��null��Ԃ��B <BR>
     * @@param l_strFuturesOptionDivision - �敨�^�I�v�V�����敪<BR>
     * <BR>
     * 1�F�@@�敨<BR>
     * 2�F�@@�I�v�V����<BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@param l_lngRangeFrom - (From����ID)<BR>
     * From����ID
     * @@param l_lngRangeTo - (To����ID)<BR>
     * To����ID
     * @@return OrderUnit[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 408E0DE30368
     */
    public OrderUnit[] getTodayOpenOrderUnits(
        String l_strFuturesOptionDivision,
        Institution l_institution,
        long l_lngRangeFrom,
        long l_lngRangeTo)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTodayOpenOrderUnits(String, Institution, long, long)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_strFuturesOptionDivision == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        Branch[] l_branchs = l_institution.getBranches();
        //�߂�̒����P�ʔz��
        OrderUnit[] l_returnOrderUnits = null;
        //�߂�̒����P�ʃ��X�g
        ArrayList l_lisReturnOrderUnits = new ArrayList();
        //�������ʂ̒����P�ʃ��X�g
        List l_lisSearchResultOrderUnits = null;

        //�P�j�@@�L���������擾����B
        //����������ǉ�����B
        StringBuffer l_sbWhere = new StringBuffer();
        //���ArrayList�𐶐�����B
        List l_lisQueryContainer = new ArrayList();

        l_sbWhere.append(" branch_id in (");
        int l_intCount = l_branchs.length;
        for (int i = 0; i < l_intCount; i++)
        {
            if (i == l_intCount - 1)
            {
                l_sbWhere.append(" ? ");
                l_lisQueryContainer.add(Long.toString(l_branchs[i].getBranchId()));
            }
            else
            {
                l_sbWhere.append(" ?, ");
                l_lisQueryContainer.add(Long.toString(l_branchs[i].getBranchId()));
            }
        }

        l_sbWhere.append(")");

        l_sbWhere.append(" and future_option_div = ? "); //�敨�^�I�v�V�����敪 = ?
        l_sbWhere.append(" and order_open_status = ? "); //�����L����� == �h�I�[�v��
        l_sbWhere.append(" and account_id >= ? ");//����.From����ID <= ����ID
        l_sbWhere.append(" and account_id <= ? ");//����ID <= ����.To����ID
        l_sbWhere.append(" and biz_date = ? "); //������ == �Ɩ����t(YYYYMMDD)

        //[order by] �󒍓���
        String l_strOrderby = "received_date_time asc";

        //������ == �Ɩ����t(YYYYMMDD)(*)
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        l_lisQueryContainer.add(l_strFuturesOptionDivision);
        l_lisQueryContainer.add(OrderOpenStatusEnum.OPEN);
        l_lisQueryContainer.add(new Long(l_lngRangeFrom));
        l_lisQueryContainer.add(new Long(l_lngRangeTo));
        l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));

        Object[] l_queryContainer = new Object[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_queryContainer);
        try
        {
            //�X���[ DataNetworkException, DataFindException
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            //�X���[ DataQueryExeption,DataNetworkException,
            l_lisSearchResultOrderUnits = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderby,
                null,
                l_queryContainer
                );
        }
        catch (DataNetworkException l_dnex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_dnex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataFindException l_dfex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_dfex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_dqex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }

        if (l_lisReturnOrderUnits != null)
        {
            int l_intOrderUnitLength = l_lisSearchResultOrderUnits.size();
            List l_lstOrderUnit  = new ArrayList();
            for (int i = 0; i < l_intOrderUnitLength; i++)
            {
                OrderUnit l_orderUint = this.toOrderUnit((IfoOrderUnitRow)l_lisSearchResultOrderUnits.get(i));
                l_lstOrderUnit.add(l_orderUint);
            }
            l_returnOrderUnits = new OrderUnit[l_lstOrderUnit.size()];
            l_lstOrderUnit.toArray(l_returnOrderUnits);
        }
        log.exiting(STR_METHOD_NAME);
        return l_returnOrderUnits;
    }

    /**
     * get�����L�������ڋq�ꗗ<BR>
     * (getTodayOpenOrderMainAcounts)<BR>
     * <BR>
     * �w��،���Ђ̓����̗L�����������ڋq�̈ꗗ���擾����B<BR>
     * (������From����ID�`To����ID�͈̔�)<BR>
     * �P�j�@@�L���������擾����B<BR>
     * �@@�@@�@@�ȉ��̏����ƃ\�[�g���ŁA�����P�ʃe�[�u�����Y�����钍���P�ʍs���擾����B <BR>
     * <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@ ���XID in ����.�،����.getBranches()�̖߂�l�̂����ꂩ <BR>
     * �@@�@@�@@���@@�敨�^�I�v�V�����敪 == ����.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@���@@�����L����� == "�I�[�v��" <BR>
     * �@@�@@�@@���@@����.From����ID <= ����ID <BR>
     * �@@�@@�@@���@@����ID <= ����.To����ID <BR>
     * �@@�@@�@@���@@������ == �Ɩ����t(YYYYMMDD) (*1)<BR>
     * �@@�@@�@@���@@����敪 is null (*2) <BR>
     * <BR>
     * �@@�@@�@@[order by]<BR>
     *       ����ID ����<BR>
     * <BR>
     * �@@�@@�@@(*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * �@@�@@�@@�@@ �����̏ꒆ�ɔ������������݂̂��擾���邽�߂̏���<BR>
     * <BR>
     * �@@�@@�@@(*2)����.�����J�z�����敪 == "�[��O�����J�z"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@����������ǉ�����B<BR>
     * �@@�@@�@@�@@�@@���[�ꎞ�ԑтɌJ�z�������N�������ꍇ�ɁA<BR>
     * �@@�@@�@@�@@�@@�@@�[��ɓ��͂��ꂽ�������J�z�ΏۂƂ��Ȃ��l���B<BR>
     * <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �Q�j�@@�ڋq�I�u�W�F�N�g�̈ꗗ(�d���Ȃ�)���쐬����B <BR>
     * <BR>
     * �@@�@@�Q�|�P�j�@@�ԋp�p�̌ڋq�I�u�W�F�N�g���i�[���郊�X�g���쐬����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�j�@@�����P�ʍs���Ƃ�Loop����<BR>
     * �@@�@@�@@�@@�@@���X�g�ɖ��ǉ��̌ڋq(�����P�ʍs.����ID�����ǉ�)�̏ꍇ�̂݁A <BR>
     * �@@�@@�@@�@@�@@����ID�ɊY������ڋq�I�u�W�F�N�g���擾���A�ԋp�p�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@�쐬�����ڋq�I�u�W�F�N�g�̈ꗗ��ԋp����B <BR>
     *       ���X�g����ڋq�I�u�W�F�N�g�̔z����擾���A�ԋp����B<BR>
     * <BR>
     * �@@�@@�@@���Y���f�[�^�Ȃ�����null��ԋp����B <BR>
     * @@param l_strFuturesOptionDivision -�敨�^�I�v�V�����敪<BR>
     * <BR>
     * 1�F�@@�敨<BR>
     * 2�F�@@�I�v�V����<BR>
     * @@param l_institution - �،����<BR>
     * @@param l_lngRangeFrom - From����ID<BR>
     * @@param l_lngRangeTo - To����ID<BR>
     * @@param l_strCarryoverProcessType - (�����J�z�����敪)<BR>
     * �����J�z�����敪<BR>
     * @@return MainAccount[]<BR>
     * @@throws WEB3BaseException
     */
    public MainAccount[] getTodayOpenOrderMainAcounts(
        String l_strFuturesOptionDivision,
        Institution l_institution,
        long l_lngRangeFrom,
        long l_lngRangeTo,
        String l_strCarryoverProcessType)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "getTodayOpenOrderMainAcounts(String, Institution, long, long, String)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null || l_strFuturesOptionDivision == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //�������ʂ̒����P�ʃ��X�g
        List l_lisSearchResultOrderUnits = null;
        //�߂�l�̌ڋq�I�u�W�F�N�g
        WEB3GentradeMainAccount[] l_mainAccounts = null;

        //�P�j�L���������擾����B
        //����������ǉ�����B
        Branch[] l_branches = l_institution.getBranches();
        StringBuffer l_sbWhere = new StringBuffer();
        if (l_branches !=null)
        {
            if (l_branches.length == 1)
            {
                l_sbWhere.append("branch_id = ?");
            }

            if (l_branches.length > 1)
            {
                l_sbWhere.append("(branch_id = ?");

                for (int i = 1; i < l_branches.length; i++)
                {
                    l_sbWhere.append(" or branch_id = ?");
                }

                l_sbWhere.append(")");
            }

            l_sbWhere.append(" and future_option_div = ? "); //�敨�^�I�v�V�����敪 = ����.�敨�^�I�v�V�����敪
            l_sbWhere.append(" and order_open_status = ? "); //�����L����� == �h�I�[�v��
            l_sbWhere.append(" and account_id >= ? "); //����.From����ID <= ����ID
            l_sbWhere.append(" and account_id <= ? ");//����ID <= ����.To����ID
            l_sbWhere.append(" and biz_date = ? "); //������ == �Ɩ����t(YYYYMMDD)

            //����.�����J�z�����敪 == "�[��O�����J�z"�̏ꍇ�̂݁A����������ǉ�����B
            //����敪 is null
            if (WEB3CarryoverProcessTypeDef.BEFORE_EVENING_SESSION_ORDER_CARRY_OVER.equals(
                l_strCarryoverProcessType))
            {
                l_sbWhere.append(" and session_type is null ");
            }
        }

        //[order by] ����ID ����
        String l_strOrderby = "account_id asc";

        //������ == �Ɩ����t(YYYYMMDD)(*)
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //�P�j���ArrayList�𐶐�����B<BR>
        List l_lstQueryContainer = new ArrayList();
        if (l_branches != null)
        {
            if (l_branches.length > 0)
            {
                for (int i = 0; i < l_branches.length; i++)
                {
                    l_lstQueryContainer.add(Long.toString(l_branches[i].getBranchId()));
                }
            }

            l_lstQueryContainer.add(l_strFuturesOptionDivision);
            l_lstQueryContainer.add(OrderOpenStatusEnum.OPEN);
            l_lstQueryContainer.add(new Long(l_lngRangeFrom));
            l_lstQueryContainer.add(new Long(l_lngRangeTo));
            l_lstQueryContainer.add(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
        }

        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            l_lisSearchResultOrderUnits = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderby,
                null,
                l_queryContainer
                );

            if (l_lisSearchResultOrderUnits != null && !l_lisSearchResultOrderUnits.isEmpty())
            {
                //�Q�j�@@�ڋq�I�u�W�F�N�g�̈ꗗ(�d���Ȃ�)���쐬����B
                long l_accountId = 0L;
                //�Q�|�P�j�@@�ԋp�p�̌ڋq�I�u�W�F�N�g���i�[���郊�X�g���쐬����B
                List l_lisAccount = new ArrayList();
                //�Q�|�Q�j�@@�����P�ʍs���Ƃ�Loop����
                int l_intSize = l_lisSearchResultOrderUnits.size();
                for (int i = 0;i < l_intSize;i++)
                {
                    IfoOrderUnitRow l_orderUnitRow =
                        (IfoOrderUnitRow)l_lisSearchResultOrderUnits.get(i);
                    if (l_accountId != l_orderUnitRow.getAccountId())
                    {
                        l_accountId = l_orderUnitRow.getAccountId();
                        WEB3GentradeMainAccount l_mainAccount =
                        new WEB3GentradeMainAccount(l_accountId);
                        l_lisAccount.add(l_mainAccount);
                    }
                }
                l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccount.size()];
                l_lisAccount.toArray(l_mainAccounts);
            }
        }
        catch (DataException l_dex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        log.exiting(STR_METHOD_NAME);
        //�쐬�����ڋq�I�u�W�F�N�g�̈ꗗ��ԋp����B
        return l_mainAccounts;
    }

    /**
     * get�����L�������P��<BR>
     * �igetTodayOpenOrderUnits�j<BR>
     * <BR>
     * �w��ڋq�̓����̗L��������S�Ď擾����B<BR>
     * <BR>
     * �P�j�@@�L���������擾����B<BR>
     * �@@�@@�@@�ȉ��̏����ƃ\�[�g���ŁA�����P�ʃe�[�u�����Y�����钍���P�ʍs���擾����B <BR>
     * <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@ ����ID == ����.�ڋq.����ID<BR>
     * �@@�@@�@@���@@�敨�^�I�v�V�����敪 == ����.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@���@@�����L����� == �h�I�[�v���h<BR>
     * �@@�@@�@@���@@������ == �Ɩ����t(YYYYMMDD)(*1) <BR>
     * �@@�@@�@@���@@����敪 is null (*2)<BR>
     * <BR>
     * �@@�@@�@@[order by]<BR>
     * �@@�@@�@@�󒍓����@@����<BR>
     * �@@�@@�@@(*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * �@@�@@�@@�@@ �����̏ꒆ�ɔ������������݂̂��擾���邽�߂̏���<BR>
     * <BR>
     * �@@�@@�@@(*2)����.�����J�z�����敪 == "�[��O�����J�z"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@����������ǉ�����B<BR>
     * �@@�@@�@@�@@�@@���[�ꎞ�ԑтɌJ�z�������N�������ꍇ�ɁA<BR>
     * �@@�@@�@@�@@�@@�@@�[��ɓ��͂��ꂽ�������J�z�ΏۂƂ��Ȃ��l���B<BR>
     * <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �Q�j�@@�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@this.toOrderUnit()�ɂ��<BR>
     * �@@�@@�擾���������P�ʍs�I�u�W�F�N�g���A�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�@@�擾���������P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �@@�@@���Y���f�[�^�Ȃ��̏ꍇ��null��Ԃ��B <BR>
     * @@param l_mainAccount<BR>
     * @@param l_strFuturesOptionDivision<BR>
     * @@param l_strCarryoverProcessType - (�����J�z�����敪)<BR>
     * �����J�z�����敪<BR>
     * @@return OrderUnit[]<BR>
     * @@throws WEB3BaseException
     */
    public OrderUnit[] getTodayOpenOrderUnits(
        String l_strFuturesOptionDivision,
        MainAccount l_mainAccount,
        String l_strCarryoverProcessType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTodayOpenOrderUnits(String, MainAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_strFuturesOptionDivision == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //�߂�l�̒����P�ʔz��
        OrderUnit[] l_returnOrderUnits = null;
        //�߂�l�̒����P�ʃ��X�g
        ArrayList l_lisReturnOrderUnits = new ArrayList();
        //�������ʂ̒����P�ʃ��X�g
        List l_lisSearchResultOrderUnits = null;

        //�P�j�@@�L���������擾����B
        //����������ǉ�����B
        StringBuffer l_sbWhere = new StringBuffer();

        l_sbWhere.append("account_id = ? ");//����ID == ����.�ڋq.����ID
        l_sbWhere.append(" and future_option_div = ? "); //�敨�^�I�v�V�����敪 = ����.�敨�^�I�v�V�����敪
        l_sbWhere.append(" and order_open_status = ? "); //�����L����� == �h�I�[�v��
        l_sbWhere.append(" and biz_date = ? "); //������ == �Ɩ����t(YYYYMMDD)
        //����.�����J�z�����敪 == "�[��O�����J�z"�̏ꍇ�̂݁A����������ǉ�����B
        //����敪 is null
        if (WEB3CarryoverProcessTypeDef.BEFORE_EVENING_SESSION_ORDER_CARRY_OVER.equals(
            l_strCarryoverProcessType))
        {
            l_sbWhere.append(" and session_type is null ");
        }

        //[order by] �󒍓���
        String l_strOrderby = "received_date_time asc";

        //������ == �Ɩ����t(YYYYMMDD)(*)
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //�P�j���ArrayList�𐶐�����B<BR>
        List l_lstQueryContainer = new ArrayList();

        l_lstQueryContainer.add(new Long(l_mainAccount.getAccountId()));
        l_lstQueryContainer.add(l_strFuturesOptionDivision);
        l_lstQueryContainer.add(OrderOpenStatusEnum.OPEN);
        l_lstQueryContainer.add(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));


        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            l_lisSearchResultOrderUnits = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderby,
                null,
                l_queryContainer
                );
        }
        catch (DataException l_dex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        if (l_lisReturnOrderUnits != null)
        {
            int l_intOrderUnitLength = l_lisSearchResultOrderUnits.size();
            List l_lstOrderUnit  = new ArrayList();
            for (int i = 0; i < l_intOrderUnitLength; i++)
            {
                OrderUnit l_orderUint = this.toOrderUnit((IfoOrderUnitRow)l_lisSearchResultOrderUnits.get(i));
                l_lstOrderUnit.add(l_orderUint);
            }
            l_returnOrderUnits = new OrderUnit[l_lstOrderUnit.size()];
            l_lstOrderUnit.toArray(l_returnOrderUnits);
        }
        log.exiting(STR_METHOD_NAME);
        //�R�j�@@�擾���������P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B
        return l_returnOrderUnits;
    }

    /**
     * (get�����P�ʈꗗ)<BR>
     * �igetOrderUnits�̃I�[�o�[���[�h�j<BR>
     * �w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ��NULL��ԋp����B<BR>
     * <BR>
     * (1)�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * (2)����������ǉ�����B<BR>
     * �@@�@@(2-1�j�p�����[�^.��������������ɁA����ID�A�⏕����ID�A�����^�C�v��<BR>
     * �ǉ���������������������쐬<BR>
     * <BR>
     * �@@�@@�������������� ��<BR>
     * �@@�@@"account_id = ? and sub_account_id = �H and product_type = ?"<BR>
     *     �{ �p�����[�^.��������������<BR>
     * <BR>
     * <BR>
     * (2-2)������z��𐶐����A����ID�A�⏕����ID�A�����^�C�v�A<BR>
     * �p�����[�^���������f�[�^�R���e�i�̏��ɃZ�b�g����B<BR>
     * <BR>
     * ������ID�A�⏕����ID�̓p�����[�^�̕⏕�����I�u�W�F�N�g���擾�A<BR>
     * �����^�C�v�̓p�����[�^.�����^�C�v���ݒ肷��B<BR>
     * <BR>
     * (3)QueryProcessor.doFindAllQuery()�ɂ��A<BR>
     * �����P�ʃI�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(,�����P��Row.TYPE<BR>
     *                                      �Q�|�P�j�̌�������������,<BR>
     *                                      �p�����[�^.�\�[�g����,<BR>
     *                                      null,<BR>
     *                                      �Q�|�Q�j�̌��������f�[�^�R���e�i)<BR>
     * <BR>
     *(4)ArrayList�𐶐�����B<BR>
     *(5)�擾���������P�ʃI�u�W�F�N�g��List�̗v�f�����ȉ��̏�����Loop����B<BR>
     *    (5-1)�@@OP�����}�l�[�W��.toOrderUnit((*)�����P��ROW)���\�b�h���R�[������B<BR>
     *     (5-2)�@@(5-1)�̖߂�l��ArrayList�ɒǉ�����B <BR>
     * (*)�����P��ROW�E�E�E�擾���������P�ʃI�u�W�F�N�g��IfoOrderUnitRow�ɃL���X�g����B<BR>
     *(6)ArrayList��ԋp����B <BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_productType - �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j
     * @@param l_strSearchCondition - �������� ������
     * @@param l_searchCondContainers - ��������
     * @@param l_strSortCondition - �\�[�g����
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 40A09816033B
     */
    public List getOrderUnits(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        String l_strSearchCondition,
        String[] l_searchCondContainers,
        String l_strSortCondition)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderUnits(l_subAccount,l_productType,l_strSearchCondition,l_searchConditions,l_strSortCondition)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_searchCondContainers == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        if (l_searchCondContainers.length == 0)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        List l_lisRecords = null;

        ArrayList l_lisResult = new ArrayList();

        try
        {
            //����ID���擾����B
            Long l_lngAccountId = new Long(l_subAccount.getAccountId());
            //�⏕����ID���擾����B
            Long l_lngSubAccountId = new Long(l_subAccount.getSubAccountId());

            //�Q�j�@@����������ǉ�����B
            //�Q�|�P�j�@@�p�����[�^.��������������ɁA����ID�A�⏕����ID�A�����^�C�v��ǉ���������������������쐬
            //"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ?"��t������B
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? "); //����ID = ?
            l_sbWhere.append(" and sub_account_id = ? "); //�⏕����ID = ?
            l_sbWhere.append(" and product_type = ? "); //�����^�C�v = ?

            //�������̕⏕�����I�u�W�F�N�g�A�y�ш����̖����^�C�v���ݒ肷��B
            Object[] l_objOrderUnitWhere = {
                l_lngAccountId, //����ID
                l_lngSubAccountId, //�⏕����ID
                new Integer(l_productType.intValue())  //�����^�C�v
                };

            //�Q�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA
            //��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B
            int l_size = l_objOrderUnitWhere.length + l_searchCondContainers.length;
            Object[] l_objWhere = new Object[l_size];
            for (int l_iLoop = 0; l_iLoop < l_objOrderUnitWhere.length; l_iLoop++)
            {
                l_objWhere[l_iLoop] = l_objOrderUnitWhere[l_iLoop];
            }

            for (int l_jLoop = 0; l_jLoop < l_searchCondContainers.length; l_jLoop++)
            {
                l_objWhere[l_objOrderUnitWhere.length + l_jLoop ] = l_searchCondContainers[l_jLoop];
            }

            //Throw DataNetworkException,DatafindException
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            log.debug("�������� = " + l_sbWhere.toString() + l_strSearchCondition);

            for (int l_iLoop = 0; l_iLoop < l_objWhere.length; l_iLoop ++)
            {
                log.debug("l_objWhere" + l_iLoop + " = " + l_objWhere[l_iLoop]);
            }
            log.debug("l_strSortCondition = " + l_strSortCondition);

            //Throw DataQuetyException,DataFoundException
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString() + l_strSearchCondition,
                l_strSortCondition,
                null,
                l_objWhere
                );

            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                return null;
            }

            for (int l_iLoop = 0; l_iLoop < l_lisRecords.size(); l_iLoop ++)
            {
                l_lisResult.add(super.toOrderUnit((IfoOrderUnitRow)l_lisRecords.get(l_iLoop)));
            }
        }
        catch (DataNetworkException l_dnex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            //��O���X���[����
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dnex);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataFindException l_dfex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dfex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_dqex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_dqex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_lisResult;
    }

    /**
     * (get���������ꗗ)<BR>
     * �����Ŏw�肳�ꂽ�����P��ID��������������S�Ď擾���A
     * ��������ԍ��ŏ����Ƀ\�[�g���ĕԂ��B<BR>
     * <BR>
     * (1)�p�����[�^.�����P��.getOrderActions()�ŁA�w��̒����P�ʂ�<BR>
     * ���������I�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * (2)�擾�������������I�u�W�F�N�g�̔z����A��������ԍ���
     * �����Ƀ\�[�g���ĕԂ���B<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g�B
     * @@return IfoOrderAction[]
     * @@throws WEB3BaseException
     * @@roseuid 40A46A3102ED
     */
    public OrderAction[] getOrderActions(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEffectiveOrderUnits(l_strInstitutionCode,l_strFuturesOptionDivision)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //��������z��I�u�W�F�N�g
        OrderAction[] l_orderActions = null;
        l_orderActions = l_orderUnit.getOrderActions();

        if (l_orderActions == null || l_orderActions.length == 0)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.debug("��������z��I�u�W�F�N�g���擾 = ");
        log.debug("��������z��I�u�W�F�N�g�̒��� = " + l_orderActions.length);

        int i = 0;
        int j = 0;
        int l_intLen = 0;
        l_intLen = l_orderActions.length;
        long l_lngIOrderActionId = 0L;
        long l_lngJOrderActionId = 0L;
        OrderAction l_TmpOrderAction = null;

        //��������ԍ��ŏ����Ƀ\�[�g
        for (i = 0; i < l_intLen - 1; i++)
        {
            for (j = i + 1; j < l_intLen; j++)
            {
                l_lngIOrderActionId = ((OrderAction)l_orderActions[i]).getOrderActionId();
                l_lngJOrderActionId = ((OrderAction)l_orderActions[j]).getOrderActionId();
                if (l_lngIOrderActionId > l_lngJOrderActionId)
                {
                    l_TmpOrderAction = l_orderActions[i];
                    l_orderActions[i] = l_orderActions[j];
                    l_orderActions[j] = l_TmpOrderAction;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return (OrderAction[])l_orderActions;
    }

    /**
     * (get���s�����ꗗ)<BR>
     * ���s�����ꗗ��ԋp����B<BR>
     * �i���s�s��Ђ̏ꍇ�A�h�s�o���������s�h�͎��s�����ꗗ���폜����j<BR>
     * 
     * �P�j�����̒����P���敪�ꗗ���������݂��Ă���ꍇ�i���s�\��Ёj<BR>
     * �����̎��s�����ꗗ�����̂܂ܕԋp����B<BR>
     * �Q�j�����̒����P���敪�ꗗ���P�̂ݑ��݂��Ă���ꍇ�i���s�s�\��Ёj<BR>
     *�@@�Q�|�P�j�����̎��s�����ꗗ�ɕs�o���������s�����݂��Ă���ꍇ��<BR>
     * �߂�l�̎��s�����ꗗ����폜����B<BR>
     *�@@�Q�|�Q�j�����̎��s�����ꗗ�ɕs�o���������s�����݂��Ă��Ȃ��ꍇ��<BR>
     *  �����̎��s�����ꗗ�����̂܂ܕԋp����B<BR>
     * @@param l_strHandlingPossibleOrderPriceDivs - �����P���敪�ꗗ
     * @@param l_strHandlingPossibleExecConds - ���s�����ꗗ
     * @@return l_strReHandlingPossibleExecConds - ���s�����ꗗ
     * @@throws
     * @@roseuid 40A459EC0365
     */
    public String[] getHandlingPossibleExecConds(String[] l_strHandlingPossibleOrderPriceDivs,String[] l_strHandlingPossibleExecConds)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleExecConds(l_strHandlingPossibleOrderPriceDivs,l_strHandlingPossibleExecConds)";
         log.entering(STR_METHOD_NAME);

        ArrayList l_lisHandlingPossibleExecConds = new ArrayList();
        
        if(l_strHandlingPossibleOrderPriceDivs.length > 1)
        {
            log.debug("�擾�������s�����ꗗ��ԋp");
            return l_strHandlingPossibleExecConds;
        }
        else
        {
            for(int i=0;i<l_strHandlingPossibleExecConds.length;i++)
            {
                if(l_strHandlingPossibleExecConds[i].equals(WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED))
                {
                    log.debug("�擾�������s�����ꗗ�̕s�o���������s���폜");
                }
                else
                {   
                    l_lisHandlingPossibleExecConds.add(l_strHandlingPossibleExecConds[i]);
                }
                
            }
            String[] l_strReHandlingPossibleExecConds = new String[l_lisHandlingPossibleExecConds.size()];
            l_lisHandlingPossibleExecConds.toArray(l_strReHandlingPossibleExecConds);
            return l_strReHandlingPossibleExecConds;
        }
    }   
    
     /**
     * (validate���������\���)<BR>
     * �����̒������\�Ȓ�����Ԃł��邩�ǂ������`�F�b�N����B<BR>
     * �i* �敨OP�����R���ʃ`�F�b�N.validate���������\���()�ɈϏ�����B�j<BR>
     * @@param l_order - ����
     * @@throws WEB3BaseException
     * @@roseuid 40A332B2030C
     */
    public void validateOrderChangePossibleStatus(Order l_order)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderChangePossibleStatus(l_order)";

        log.entering(STR_METHOD_NAME);

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();
        //�敨OP�����R���ʃ`�F�b�N.validate���������\���()�ɈϏ�����B
        try
        {
            l_ifoOrderManagerReusableValidations.validateOrderForChangeability(l_order);
        }
        catch (OrderValidationException l_ove)
        {
            ProcessingResult l_processingResult = l_ove.getValidationResult().getProcessingResult();
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate��������\���)<BR>
     * �����̎�����\�Ȓ�����Ԃł��邩�ǂ������`�F�b�N����B<BR>
     * �i* �敨OP�����R���ʃ`�F�b�N.validate��������\���()�ɈϏ�����B�j<BR>
     * @@param l_order - ����
     * @@throws WEB3BaseException
     * @@roseuid 40A332B202ED
     */
    public void validateOrderCancelPossibleStatus(Order l_order)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateOrderCancelPossibleStatus(l_order)";

        log.entering(STR_METHOD_NAME);

        //�敨OP�����R���ʃ`�F�b�N�I�u�W�F�N�g���쐬����
        WEB3IfoOrderManagerReusableValidations l_ifoOrderManagerReusableValidations = null;
        l_ifoOrderManagerReusableValidations =
             (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();
        //�敨OP�����R���ʃ`�F�b�N.validate��������\���()�ɈϏ�����
        try
        {
            l_ifoOrderManagerReusableValidations.validateOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ove)
        {
            ProcessingResult l_processingResult = l_ove.getValidationResult().getProcessingResult();
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());

        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (is���e�ʒm�ϒ���)<BR>
     * �����ϒ����ł��邩�𔻒肷��B<BR>
     * �����̒����P�ʂ��ȉ��̏����ɓ��Ă͂܂�ꍇtrue�A<BR>
     * �ȊO��false��ԋp���B<BR>
     *  [�����ϒ����̏���]<BR>
     * �@@�����P��.���s���� == �����P��.�s�ꂩ��m�F�ς̎��s����<BR>
     * �@@�����P��.�w�l == �����P��.�s�ꂩ��m�F�ς̎w�l<BR>
     * �@@�����P��.�������� == �����P��.�s�ꂩ��m�F�ς̐���<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@return boolean
     * @@throws
     * @@roseuid 40A459EC0365
     */
    public boolean isNotifyEndOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNotifyEndOrder(l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        //�����P��ROW�I�u�W�F�N�g
        IfoOrderUnitRow l_orderUnitRow = null;
        l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        boolean l_blnIsNotifyEndOrder = false;

        if (l_orderUnitRow.getExecutionConditionType() == null ||
                l_orderUnitRow.getLimitPriceIsNull() == true ||
                l_orderUnitRow.getQuantityIsSet() == false)
        {
            //���s�����A�w�l�A�������ʂ�NULL�̏ꍇ
            log.debug("���s�����A�w�l�A�������ʂ�NULL�̏ꍇ");
            l_blnIsNotifyEndOrder = false;
        }
        else if (l_orderUnitRow.getExecutionConditionType().equals(l_orderUnitRow.getConfirmedExecConditionType()) &&
                l_orderUnitRow.getLimitPrice() == l_orderUnitRow.getConfirmedPrice() &&
                l_orderUnitRow.getQuantity() == l_orderUnitRow.getConfirmedQuantity())
        {
            //�����P��.���s���� == �����P��.�s�ꂩ��m�F�ς̎��s����
            //�����P��.�w�l == �����P��.�s�ꂩ��m�F�ς̎w�l
            //�����P��.�������� == �����P��.�s�ꂩ��m�F�ς̐���
            //�ȏ�̏ꍇ
            log.debug("�����ϒ����̏ꍇ");
            l_blnIsNotifyEndOrder = true;
        }
        else
        {
            //���̏ꍇ
            log.debug("�����ϒ����ȊO�̏ꍇ");
            l_blnIsNotifyEndOrder = false;
        }

        log.exiting(STR_METHOD_NAME);

        return l_blnIsNotifyEndOrder;
    }

    /**
     * (update�T�Z��n���)<BR>
     * �I�v�V�����̊T�Z��n������X�V����B<BR>
     * �P�j�@@�����P�ʃe�[�u���̉��L���ڂ��X�V����B<BR>
     *  �E�T�Z��n���<BR>
     *  �E�����P��<BR>
     *  �E�s�ꂩ��m�F�ς݂̊T�Z��n���<BR>
     *  �E�s�ꂩ��m�F�ς݂̒����P��<BR>
     * �Q�j�@@���������e�[�u���̊T�Z��n������X�V����B<BR>
     *  �V�[�P���X�}<BR>
     *  �u�iOP�����jupdate�T�Z��n����v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@return void
     * @@throws
     * @@roseuid 40A459EC0365
     */
    public void updateEstimateDeliveryAmount(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateEstimateDeliveryAmount(l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            // �T�Z��n���
            double l_dblEstimateDeliveryAmount = 0D;
            // �����P��
            double l_dblPrice = 0D;

            log.debug("�����P��.getOrderOpenStatus() = " + l_orderUnit.getOrderOpenStatus());
            log.debug("�����P��.isFullyExecuted() = " + l_orderUnit.isFullyExecuted());
            log.debug("�����P��.�����敪 = " + l_orderUnit.getExpirationStatus());

            //(*1)�S���o���A�܂��́A�����ς̏ꍇ(����.�����P��.isFullyExecuted() == true || ����.�����P��.�����敪 == "�}�[�P�b�g����")
            if ((l_orderUnit.isFullyExecuted()) || 
                OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
            {
                log.debug("�S���o���A�܂��́A�����ς̏ꍇ");
                //1.1.1 get��n���z���v(�����P�� : IfoOrderUnit)
                // �X�V�l�̐ݒ�
                //   �T�Z��n��� = get��n���z���v()
                //   �����P�� = �����P��.�����P���i�����l�̂܂܁j
                l_dblEstimateDeliveryAmount = this.getNetAmount((IfoOrderUnit)l_orderUnit);
                l_dblPrice = l_orderUnitRow.getPrice();

                log.debug("l_dblPrice   = " + l_dblPrice);
                log.debug("l_dblEstimateDeliveryAmount   = " + l_dblEstimateDeliveryAmount);
            }
            //(*2)�S���o���A�܂��́A�����ψȊO�̏ꍇ((*1)�ȊO)
            else
            {
                log.debug("�S���o���A�܂��́A�����ψȊO�̏ꍇ");
                //1.2.1 create�萔��(�����P��ID : long)
                // �萔���I�u�W�F�N�g�𐶐�����B
                // [�����̐ݒ�]
                // �����P��ID�F�@@����.�����P��.�����P��ID
                WEB3IfoBizLogicProvider l_bizLogicProvider = (WEB3IfoBizLogicProvider)l_tradingMod.getBizLogicProvider();
                WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(l_orderUnit.getOrderUnitId());
                WEB3GentradeCommission l_cloneCommission = this.copyCommission(l_commission);
                
                //1.2.2 calc�������T�Z��n������Čv�Z����B
                //[����]
                //  �萔���F create�萔��()�̖߂�l
                //  �w�l�F �����P��.�w�l
                //  �⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g
                //  �敨OP��������F �����P��.getTradedProduct()�̖߂�l
                //  ���ʁF �����P��.����
                //  �����F �����P��.getSide()
                //  is�ԍϒ����F
                //    (�����P��.�����J�e�S�� == "OP�V�K������")�̏ꍇ�Afalse
                //    (�����P��.�����J�e�S�� == "OP�ԍϒ���")�̏ꍇ�Atrue
                //  ��萔�ʁF �����P��.��萔��
                //  ���v�����z�F �����P��.���v�����z
                //  isSkip���z�`�F�b�N�F true(�X�L�b�v����)
                boolean l_blnIsClosingContractOrder = false;
                if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
                {
                    l_blnIsClosingContractOrder = false;
                }
                else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()))
                {
                    l_blnIsClosingContractOrder = true;
                }
                WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());

                WEB3IfoEstimateDeliveryAmountCalcResult l_limitPriceResult = calcChangeEstimateDeliveryAmount(
                    l_commission,
                    l_orderUnitRow.getLimitPrice(),
                    l_subAccount,
                    (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct(),
                    l_orderUnitRow.getQuantity(),
                    l_orderUnit.getSide(),
                    l_blnIsClosingContractOrder,
                    l_orderUnitRow.getExecutedQuantity(),
                    l_orderUnitRow.getExecutedAmount(),
                    true);

                //�X�V�l�̐ݒ�
                //�T�Z��n��� = calc�������T�Z��n���()�̖߂�l�̊T�Z��n����v�Z����.�T�Z��n���
                //�����P�� = calc�������T�Z��n���()�̖߂�l�̊T�Z��n����v�Z����.�v�Z�P��
                l_dblEstimateDeliveryAmount = l_limitPriceResult.getEstimateDeliveryAmount();
                l_dblPrice = l_limitPriceResult.getCalcUnitPrice();
                log.debug("l_dblPrice   = " + l_dblPrice);
                log.debug("l_dblEstimateDeliveryAmount   = " + l_dblEstimateDeliveryAmount);

                //1.2.3  get�v�w�l�p�L����ԋ敪(�����P�� : IfoOrderUnit)
                String l_strWLimitEnableStatusDiv = WEB3IfoDataAdapter.getWLimitEnableStatusDiv((IfoOrderUnit)l_orderUnit);
                
                //1.2.4 (*)����&&W�w�l���~�b�g�����L���̏ꍇ
                //(�����P��.������� == "OP�V�K��������" && get�v�w�l�p�L����ԋ敪()�̖߂�l == "���~�b�g�����L��")
                //�iW�w�l�j�����w�l�ɂ��������T�Z��n������擾����
                if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderUnit.getOrderType())
                    && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
                {
                    //1.2.4.1 setIs�w�l(is�w�l : boolean)
                    //  [�����̐ݒ�]
                    //  is�w�l�F�@@�����P��.(W�w�l)�����w�l == 0�̏ꍇ�Afalse�B�ȊO�Atrue�B
                    if (l_orderUnitRow.getWLimitPrice() == 0)
                    {
                        l_cloneCommission.setIsLimitPrice(false);
                    }
                    else
                    {
                        l_cloneCommission.setIsLimitPrice(true);
                    }

                    //1.2.4.2 calc�������T�Z��n���(�萔��, double, SubAccount, �敨OP�������, double, SideEnum, boolean, double, double, boolean)
                    //[����]
                    //  �萔���F W�w�l�p�萔���I�u�W�F�N�g
                    //  �w�l�F �����P��.(W�w�l)�����w�l
                    //  �⏕�����F �����P��.�⏕����ID����擾�����⏕�����I�u�W�F�N�g
                    //  �敨OP��������F �����P��.getTradedProduct()�̖߂�l
                    //  ���ʁF �����P��.����
                    //  �����F �����P��.getSide()
                    //  is�ԍϒ����F
                    //    (�����P��.�����J�e�S�� == "OP�V�K������")�̏ꍇ�Afalse
                    //    (�����P��.�����J�e�S�� == "OP�ԍϒ���")�̏ꍇ�Atrue
                    //  ��萔�ʁF �����P��.��萔��
                    //  ���v�����z�F �����P��.���v�����z
                    //  isSkip���z�`�F�b�N�F true(�X�L�b�v����)
                    WEB3IfoEstimateDeliveryAmountCalcResult l_wLimitPriceResult = calcChangeEstimateDeliveryAmount(
                        l_cloneCommission,
                        l_orderUnitRow.getWLimitPrice(),
                        l_subAccount,
                        (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct(),
                        l_orderUnitRow.getQuantity(),
                        l_orderUnit.getSide(),
                        l_blnIsClosingContractOrder,
                        l_orderUnitRow.getExecutedQuantity(),
                        l_orderUnitRow.getExecutedAmount(),
                        true);

                    log.debug("(**)�߂�l(*1) = " + l_limitPriceResult.getRestraintTurnover());
                    log.debug("(**)�߂�l(*2) = " + l_wLimitPriceResult.getRestraintTurnover());
                    //�X�V�l�̐ݒ�
                    //�T�Z��n��� = calc�������T�Z��n���()�̖߂�l�̊T�Z��n����v�Z����(**).�T�Z��n���
                    //�����P�� = calc�������T�Z��n���()�̖߂�l�̊T�Z��n����v�Z����(**).�v�Z�P��
                    //(**)�߂�l(*1)�Ɩ߂�l(*2)�̍S������������r���āA
                    //��r���ʂ������ق��̖߂�l�̊T�Z��n����v�Z���ʃI�u�W�F�N�g���g�p����
                    if (l_limitPriceResult.getRestraintTurnover() < l_wLimitPriceResult.getRestraintTurnover())
                    {
                        l_dblEstimateDeliveryAmount = l_wLimitPriceResult.getEstimateDeliveryAmount();
                        l_dblPrice = l_wLimitPriceResult.getCalcUnitPrice();
                    }
                    log.debug("l_dblPrice   = " + l_dblPrice);
                    log.debug("l_dblEstimateDeliveryAmount   = " + l_dblEstimateDeliveryAmount);
                }
            }

            //1.3 (*)�����P�ʃe�[�u���̍X�V
            //�ȉ��̏����Œ����P�ʃe�[�u�����������A�擾�����s�����L�X�V���e�ōX�V����B
            //[����]
            //�����P�ʃe�[�u��.�����P��ID =�@@�����P��.�����P��ID
            //[�X�V���e]
            //�����P�ʃe�[�u��.�����P�� = ��L�Őݒ肵�������P��
            //�����P�ʃe�[�u��.�s�ꂩ��m�F�ς݂̒����P�� = ��L�Őݒ肵�������P��
            //�����P�ʃe�[�u��.�T�Z��n��� = ��L�Őݒ肵���T�Z��n���
            //�����P�ʃe�[�u��.�s�ꂩ��m�F�ς݂̊T�Z��n��� = ��L�Őݒ肵���T�Z��n���
            OrderUnit l_orderUnitSch = getOrderUnit(l_orderUnit.getOrderUnitId());
            IfoOrderUnitRow l_orderUnitRowSch = (IfoOrderUnitRow)l_orderUnitSch.getDataSourceObject();
            IfoOrderUnitParams l_orderUnitParam = new IfoOrderUnitParams(l_orderUnitRowSch);

            l_orderUnitParam.setPrice(l_dblPrice);
            l_orderUnitParam.setConfirmedOrderPrice(l_dblPrice);
            l_orderUnitParam.setEstimatedPrice(l_dblEstimateDeliveryAmount);
            l_orderUnitParam.setConfirmedEstimatedPrice(l_dblEstimateDeliveryAmount);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderUnitParam);

            log.debug("�����P�ʃe�[�u���̍X�V" + l_orderUnit.getOrderUnitId());

            //1.4 (*)���������e�[�u���̍X�V
            //�ȉ��̏����Œ��������e�[�u�����������A�擾�����s�����L�X�V���e�ōX�V����B
            //[����]
            //���������e�[�u��.�����P��ID�@@    =�@@�����P��.�����P��ID and
            //���������e�[�u��.��������ԍ��@@=�@@�����P��.���������ŏI�ʔ�
            //[�X�V���e]
            //���������e�[�u��.�T�Z��n����@@= ��L�Őݒ肵���T�Z��n���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");
            l_sbWhere.append(" and order_action_serial_no = ? ");

            Object[] l_objWhere = {
                String.valueOf(l_orderUnit.getOrderUnitId()),
                String.valueOf(l_orderUnitRow.getLastOrderActionSerialNo())};

            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                IfoOrderActionRow.TYPE,
                l_sbWhere.toString(),
                null,
                "FOR UPDATE",
                l_objWhere);
            if (l_lisRecords.size() > 0)
            {
                IfoOrderActionRow l_actionRow = (IfoOrderActionRow)l_lisRecords.get(0);
                IfoOrderActionParams l_actionParams = new IfoOrderActionParams(l_actionRow);
                l_actionParams.setEstimatedPrice(l_dblEstimateDeliveryAmount);

                l_queryProcessor.doUpdateQuery(l_actionParams);
                log.debug("���������e�[�u���̍X�V" + l_actionRow.getOrderActionId());
            }
        }
        catch (DataQueryException l_dqe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify���[���G���W���T�[�o)<BR>
     * <BR>
     * �inotifyRLS�j<BR>
     * �����t�����̎��s�A�o�^�A�����A�����<BR>
     * ���[���G���W���T�[�o�ɒʒm����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�����jnotify���[���G���W���T�[�o�v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P��<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@param l_context - ����<BR>
     * �����B<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@throws WEB3BaseException
     */
    public void notifyRLS(
        IfoOrderUnit l_orderUnit,
        OrderManagerPersistenceContext l_context)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "notifyRLS(IfoOrderUnit, OrderManagerPersistenceContext)";
        log.entering(STR_METHOD_NAME);

        if (OrderManagerPersistenceContext.FILL_ORDER.equals(l_context))
        {
            // notify�e�����S�����
            this.notifyParentOrderFullyExecuted(l_orderUnit);
        }
        else
        {
            // notify�A������
            this.notifyToSuccOrder(l_orderUnit);
        }

        //�p�����[�^.�����P��.getDataSourceObject()���敨OP�\�񒍕��P��Row�̏ꍇ
        //�������I������
        Object l_objDataSourceObject = l_orderUnit.getDataSourceObject();
        if (l_objDataSourceObject instanceof RsvIfoOrderUnitRow)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        IfoOrderUnitRow l_ifoRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        //get��������
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoRow.getOrderConditionType(), 
                l_ifoRow.getOrgOrderConditionType());

        //(*)�t�w�l�����iget��������()�̖߂�l == "�t�w�l"�j�̏ꍇ
        //1.1 notify�t�w�l����()
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            this.notifyStopOrder(l_orderUnit);
        }
        //(*)W�w�l�����iget��������()�̖߂�l == "W�w�l"�j�̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //1.2 notifyW�w�l����()
            this.notifyWLimitOrder(l_orderUnit, l_context);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify�t�w�l����)<BR>
     * <BR>
     * �inotifyStopOrder�j<BR>
     * �t�w�l�����̓o�^�A�����A�����<BR>
     * ���[���G���W���T�[�o�ɒʒm����B<BR>
     * <BR>
     * �P�j�@@�敨OP�����P�ʂ��ǂ����̔���@@<BR>
     * �@@�����̒����P��.getDataSourceObject()���R�[������B�@@<BR>
     * <BR>
     * �@@���\�b�h�̖߂�l�̌^���A<BR>
     * �@@�敨OP�����P��Row�łȂ��ꍇ�A<BR>
     * �@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j<BR>
     * �@@���\�񒍕��P�ʂɑ΂��āA�t�w�l�͐ݒ�s�B<BR>
     * <BR>
     * �Q�j�@@���[���G���W���T�[�o�ւ̒ʒm�v�ۃ`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�������̋t�w�l�������ǂ����̔���@@<BR>
     * �@@�P�j�̖߂�l.�������� != "�t�w�l" �܂���<BR>
     * �@@�P�j�̖߂�l.���N�G�X�g�^�C�v != "DEFAULT"�̏ꍇ�A<BR>
     * �@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�������̔����x���������ǂ����̔��� <BR>
     * �@@�@@OP�����}�l�[�W��.is�������x������() == true�̏ꍇ�A<BR>
     * �@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j<BR>
     * <BR>
     * �@@�@@[is�������x������()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�̖߂�l <BR>
     * <BR>
     * �R�j�@@�⏕�������擾����B<BR>
     * �@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B<BR>
     * <BR>
     * �@@[getSubAccount()�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�P�j�̖߂�l.����ID<BR>
     * �@@�@@arg1�F�@@�P�j�̖߂�l.�⏕����ID<BR>
     * <BR>
     * �S�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j<BR>
     * �@@���擾����B<BR>
     * <BR>
     * �T�j�@@���[���G���W���T�[�o�ɒʒm���s���B<BR>
     * �@@�����̒����P��.������Ԃɂ���ĉ��L����������s���B<BR>
     * <BR>
     * �@@�T�|�P�j�@@��������i�����L�����="�N���[�Y"�j�̏ꍇ<BR>
     * �@@�@@�擾�����T�[�r�X.sendCancelConOrderMessage()���\�b�h��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[sendCancelConOrderMessage()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕����<BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"�t�w�l"<BR>
     * �@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����"<BR>
     * �@@�@@�@@����ID�F�@@�P�j�̖߂�l.����ID<BR>
     * <BR>
     * �@@�T�|�Q�j�@@���������i�������="�����ρi�ύX�����j"�j�̏ꍇ<BR>
     * �@@�@@�擾�����T�[�r�X.sendModifyConOrdersMessage()���\�b�h��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[sendModifyConOrdersMessage()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕����<BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"�t�w�l"<BR>
     * �@@�@@�@@�e�����̖����^�C�v�F�@@"�敨�I�v�V����"<BR>
     * �@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID<BR>
     * �@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null<BR>
     * �@@�@@�@@�q�����̒���ID�ꗗ�F�@@null<BR>
     * <BR>
     * �@@�T�|�R�j�@@�V�K�o�^�i�������="��t�ρi�V�K�����j"�j�̏ꍇ<BR>
     * �@@�@@�擾�����T�[�r�X.sendRegisterConOrdersMessage()���\�b�h��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[sendRegisterConOrdersMessage()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕����<BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"�t�w�l"<BR>
     * �@@�@@�@@�e�����̖����^�C�v�F�@@"�敨�I�v�V����"<BR>
     * �@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID<BR>
     * �@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null<BR>
     * �@@�@@�@@�q�����̒���ID�ꗗ�F�@@null<BR>
     * @@param l_orderUnit - �����P��<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    private void notifyStopOrder(
        IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyStopOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        Object l_row = l_orderUnit.getDataSourceObject();
        if (!(l_row instanceof IfoOrderUnitRow))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_row;
        try
        {
	        l_orderUnitRow = (IfoOrderUnitRow)getOrderUnit(l_orderUnitRow.getOrderUnitId()).getDataSourceObject();
	    } 
	    catch (NotFoundException e) 
	    {
	        log.error("�e�[�u���ɊY������f�[�^������܂���B");
	        throw new WEB3SystemLayerException(
	            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
	            this.getClass().getName() + "." + STR_METHOD_NAME,
	            e.getMessage(),
	            e);
	    }
	    
        if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()) ||
            !WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        if (this.isNotOrderedDelay(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);

        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        OrderOpenStatusEnum l_orderOpenStatus = l_orderUnitRow.getOrderOpenStatus();
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus))
        {
            l_rlsRequestSenderService.sendCancelConOrderMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT,
                ProductTypeEnum.IFO,
                new Long(l_orderUnitRow.getOrderId()));
        }
        else if (OrderStatusEnum.MODIFIED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendModifyConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT,
                ProductTypeEnum.IFO,
                new Long(l_orderUnitRow.getOrderId()),
                null,
                null);
        }
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendRegisterConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT,
                ProductTypeEnum.IFO,
                new Long(l_orderUnitRow.getOrderId()),
                null,
                null);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�����f�[�^)<BR>
     * <BR>
     * �iupdateOrderData�j<BR>
     * �w�肳�ꂽ�����P�ʃI�u�W�F�N�g���g�p���AQueryProcessor�ɂ�蒍���f�[�^�ނ̍X�V���s���B<BR>
     * �|�����i�w�b�_�j�e�[�u��.�X�V���t��update<BR>
     * �|�����P�ʃe�[�u�����A�����̒����P�ʃI�u�W�F�N�g�̓��e��update<BR>
     * �@@���������쐬����ꍇ�́A�����P��.���������ŏI�ʔԂ��Ăяo������<BR>
     * �@@�@@�@@�J�E���g�A�b�v���邱�ƁB<BR>
     * �|���������e�[�u���Ƀ��R�[�h��insert�i����.is�����쐬==true�̏ꍇ�̂݁j<BR>
     * <BR>
     * �P�j�@@�����i�w�b�_�j�e�[�u����update����B<BR>
     * <BR>
     * �@@�@@����ID==�����̒����P��.����ID �ɊY�����钍���i�w�b�_�j���R�[�h��update����B<BR>
     * <BR>
     * �@@�@@�X�V���t�ɁA�����̒����P�ʃI�u�W�F�N�g�̓����ڂ̓��e���Z�b�g����update����B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u����update����B<BR>
     * <BR>
     * �@@�@@�����̒����P�ʃI�u�W�F�N�g�̓��e��update����B<BR>
     * <BR>
     * �R�j�@@������is�����쐬==true�̏ꍇ�̂݁A�����̒����P�ʃI�u�W�F�N�g���g�p��<BR>
     * �@@�@@���������e�[�u���ւP���R�[�hinsert����B<BR>
     * <BR>
     * �@@�@@�R�|�P�j�@@����OK<BR>
     * �@@�@@[�����̒����P��.������� == "�������i�V�K�����j" ����<BR>
     * �@@�@@�@@�����̒����P��.���N�G�X�g�^�C�v == "�����T�[�o"�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�@@DB�X�V�d�l<BR>
     * �@@�@@�@@�u�t�w�l��������(OK)_�敨OP���������e�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �@@�@@�R�|�Q�j�@@�Ǘ��Ҏ蓮���� <BR>
     * �@@�@@�@@[�����̒����P��.���N�G�X�g�^�C�v == "����" ����<BR>
     * �@@�@@�@@�@@�����̒����P��.�����G���[���R�R�[�h ==<BR>
     * �@@�@@�@@�@@�@@"�g���K�[�����Ǘ��Ҏ蓮������"�̏ꍇ] <BR>
     * <BR>
     * �@@�@@�@@DB�X�V�d�l <BR>
     * �@@�@@�@@�u�蓮����_�敨OP���������e�[�u��.xls�v�Q�ƁB <BR>
     * <BR>
     * �@@�@@�R�|�R�j�@@�ؑ�NG <BR>
     * �@@�@@�@@[�����̒����P��.������� == "�������s�i�ύX�����j" ���� <BR>
     * �@@�@@�@@�@@�����̒����P��.���N�G�X�g�^�C�v == "����"����<BR>
     * �@@�@@�@@�@@�����̒����P��.�����G���[���R�R�[�h !=<BR>
     * �@@�@@�@@�@@�@@"�g���K�[�����Ǘ��Ҏ蓮������"�̏ꍇ] <BR>
     * �@@�@@�@@ <BR>
     * �@@�@@�@@DB�X�V�d�l <BR>
     * �@@�@@�@@�uW�w�l�����ؑցiNG�j_���������e�[�u���d�l.xls�v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P��<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@param l_blnIsCreateOrderAction - is�����쐬<BR>
     * ���������e�[�u���Ƀf�[�^��o�^���邩�ǂ����̃t���O�B<BR>
     * �itrue�F�o�^����Afalse�F�o�^���Ȃ��j
     * @@throws WEB3BaseException
     */
    public void updateOrderData(
        IfoOrderUnit l_orderUnit,
        boolean l_blnIsCreateOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderData(IfoOrderUnit, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "order_id=?";
            Object[] l_objWhere =
            {
                new Long(l_orderUnitRow.getOrderId())
            };
            HashMap l_map = new HashMap();
            l_map.put("last_updated_timestamp", l_orderUnitRow.getLastUpdatedTimestamp());
            l_processor.doUpdateAllQuery(
                IfoOrderRow.TYPE,
                l_strWhere,
                l_objWhere,
                l_map);

            l_processor.doUpdateQuery(l_orderUnitRow);

            if (l_blnIsCreateOrderAction)
            {
                //�R�|�P�j�@@����OK
                boolean l_blnOrderOK =
                    OrderStatusEnum.ORDERING.equals(l_orderUnitRow.getOrderStatus()) &&
                    WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType());

                //�R�|�Q�j�@@�Ǘ��Ҏ蓮����
                boolean l_blnInvalidate =
                    WEB3RequestTypeDef.INVALIDATE.equals(l_orderUnitRow.getRequestType())
                        && WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED.equals(
                            l_orderUnitRow.getErrorReasonCode());

                //�R�|�R�j�@@�ؑ�NG
                boolean l_blnTransferNG =
                    OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitRow.getOrderStatus())
                        && !WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED.equals(
                            l_orderUnitRow.getErrorReasonCode())
                        && WEB3RequestTypeDef.INVALIDATE.equals(l_orderUnitRow.getRequestType());

                if (l_blnOrderOK || l_blnInvalidate || l_blnTransferNG)
                {
                    IfoOrderActionParams l_orderActionParams =
                        new IfoOrderActionParams();
                    long l_lngOrderActionId = IfoOrderActionDao.newPkValue();
                    Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();
                    l_orderActionParams.setOrderActionId(l_lngOrderActionId);
                    l_orderActionParams.setAccountId(l_orderUnitRow.getAccountId());
                    l_orderActionParams.setSubAccountId(l_orderUnitRow.getSubAccountId());
                    l_orderActionParams.setOrderId(l_orderUnitRow.getOrderId());
                    l_orderActionParams.setOrderUnitId(l_orderUnitRow.getOrderUnitId());
                    if (!l_orderUnitRow.getMarketIdIsNull())
                    {
                        l_orderActionParams.setMarketId(l_orderUnitRow.getMarketId());
                    }
                    l_orderActionParams.setOrderType(l_orderUnitRow.getOrderType());
                    if (l_blnOrderOK)
                    {
                        l_orderActionParams.setOrderEventType(OrderEventTypeEnum.SEND_TO_MKT);
                    }
                    else if (l_blnInvalidate)
                    {
                        l_orderActionParams.setOrderEventType(OrderEventTypeEnum.EXPIRE);
                    }
                    else if (l_blnTransferNG)
                    {
                        l_orderActionParams.setOrderEventType(OrderEventTypeEnum.MARKER_REFUSAL);
                    }
                    if (!l_orderUnitRow.getLimitPriceIsNull())
                    {
                        l_orderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
                    }
                    l_orderActionParams.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType());
                    l_orderActionParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());
                    l_orderActionParams.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());
                    l_orderActionParams.setStopPriceType(l_orderUnitRow.getStopPriceType());
                    if (!l_orderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_orderActionParams.setStopOrderPrice(l_orderUnitRow.getStopOrderPrice());
                    }
                    if (!l_orderUnitRow.getWLimitPriceIsNull())
                    {
                        l_orderActionParams.setWLimitPrice(l_orderUnitRow.getWLimitPrice());
                    }
                    l_orderActionParams.setExpirationDate(l_orderUnitRow.getExpirationDate());
                    l_orderActionParams.setQuantity(l_orderUnitRow.getQuantity());
                    if (!l_orderUnitRow.getConfirmedPriceIsNull())
                    {
                        l_orderActionParams.setConfirmedPrice(l_orderUnitRow.getConfirmedPrice());
                    }
                    if (!l_orderUnitRow.getConfirmedQuantityIsNull())
                    {
                        l_orderActionParams.setConfirmedQuantity(l_orderUnitRow.getConfirmedQuantity());
                    }
                    l_orderActionParams.setExecutedQuantity(null);
                    l_orderActionParams.setOrderStatus(l_orderUnitRow.getOrderStatus());
                    l_orderActionParams.setExpirationStatus(l_orderUnitRow.getExpirationStatus());
                    l_orderActionParams.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
                    l_orderActionParams.setExecutedPrice(null);
                    l_orderActionParams.setProductType(l_orderUnitRow.getProductType());
                    l_orderActionParams.setProductId(l_orderUnitRow.getProductId());
                    if (!l_orderUnitRow.getEstimatedPriceIsNull())
                    {
                        l_orderActionParams.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
                    }
                    l_orderActionParams.setModifyCancelType(l_orderUnitRow.getModifyCancelType());
                    l_orderActionParams.setClosingOrder(l_orderUnitRow.getClosingOrder());
                    if (l_blnOrderOK || l_blnInvalidate)
                    {
                        l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
                    }
                    else if (l_blnTransferNG)
                    {
                        l_orderActionParams.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());
                    }
                    l_orderActionParams.setRequestType(l_orderUnitRow.getRequestType());
                    if (!l_orderUnitRow.getTraderIdIsNull())
                    {
                        l_orderActionParams.setTraderId(l_orderUnitRow.getTraderId());
                    }
                    l_orderActionParams.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());
                    l_orderActionParams.setCreatedTimestamp(l_tsSysTime);
                    l_orderActionParams.setLastUpdatedTimestamp(l_tsSysTime);

                    if (l_blnInvalidate || l_blnTransferNG)
                    {
                        l_orderActionParams.setOrgOrderConditionType(
                            l_orderUnitRow.getOrgOrderConditionType());
                        l_orderActionParams.setOrgOrderCondOperator(
                            l_orderUnitRow.getOrgOrderCondOperator());
                        l_orderActionParams.setOrgStopPriceType(
                            l_orderUnitRow.getOrgStopPriceType());
                        if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
                        {
                            l_orderActionParams.setOrgStopOrderPrice(
                                l_orderUnitRow.getOrgStopOrderPrice());
                        }
                        if (!l_orderUnitRow.getOrgWLimitPriceIsNull())
                        {
                            l_orderActionParams.setOrgWLimitPrice(
                                l_orderUnitRow.getOrgWLimitPrice());
                        }
                        l_orderActionParams.setOrgWLimitExecCondType(
                            l_orderUnitRow.getOrgWLimitExecCondType());
                        l_orderActionParams.setWLimitExecCondType(
                            l_orderUnitRow.getWLimitExecCondType());
                        if (!l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
                        {
                            l_orderActionParams.setWLimitBeforeLimitPrice(
                                l_orderUnitRow.getWLimitBeforeLimitPrice());
                        }
                        l_orderActionParams.setWLimitBeforeExecCondType(
                            l_orderUnitRow.getWLimitBeforeExecCondType());
                    }

                    l_orderActionParams.setConfirmedExecConditionType(
                        l_orderUnitRow.getConfirmedExecConditionType());

                    l_orderActionParams.setExpirationDateType(
                        l_orderUnitRow.getExpirationDateType());

                    l_processor.doInsertQuery(l_orderActionParams);
                }
            }
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�V�K�������L���[)<BR>
     * <BR>
     * �iinsertOpenContractHostOrder�j<BR>
     * �敨OP��������L���[�e�[�u���ɐV�K�������̃f�[�^��o�^����B<BR>
     * <BR>
     * �P�j�@@this.getOrderUnits(����ID)�ɂĒ����P�ʂ��擾����B<BR>
     * �@@���߂�l��0�Ԗڂ̗v�f�𒍕��P�ʂƂ��Ďg�p����B<BR>
     * <BR>
     * �Q�j�@@�敨OP��������L���[�e�[�u���Ƀf�[�^��o�^����B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�I�v�V�����̏ꍇ�i�����P��.�敨�^�I�v�V�����敪="�I�v�V����"�j<BR>
     * �@@�@@�X�V����s�̓��e�́ADB�X�V�d�l<BR>
     * �@@�@@�uOP�V�K��_�敨OP��������L���[�e�[�u��.xls�v�Q��<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�敨�̏ꍇ�i�����P��.�敨�^�I�v�V�����敪="�敨"�j<BR>
     * �@@�@@�X�V����s�̓��e�́ADB�X�V�d�l<BR>
     * �@@�@@�u�敨�V�K��_�敨OP��������L���[�e�[�u��.xls�v �Q�ƁB<BR>
     * @@param l_lngOrderId - ����ID<BR>
     * ����ID�B
     * @@throws WEB3BaseException
     */
    public void insertOpenContractHostOrder(
        long l_lngOrderId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "insertOpenContractHostOrder(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.OPTION_ORDER;// ���N�G�X�g�f�[�^�R�[�h 
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//�����敪 "0�F������"
            String l_strInstitutionCode = null;//�،���ЃR�[�h
            String l_strBranchCode = null;//���X�R�[�h
            String l_strAccountCode = null;//�ڋq�R�[�h
            String l_strTraderCode = null;//���҃R�[�h
            String l_strReceivedDateTimeDiv = null;//�󒍓��敪
            String l_strOrderRequestNumber = null;//���ʃR�[�h
            String l_strMarketCode = null;//�s��
            String l_strProductCode = null;//�����R�[�h
            String l_strTargetProductCode = null;//�����Y�����R�[�h
            String l_strDeliveryMonthYYYY = null;//�����i�N�j
            String l_strDeliveryMonthMM = null;//�����i���j
            IfoDerivativeTypeEnum l_futureOptionProductType = null;//�敨�I�v�V�������i
            double l_dblStrikePrice = 0;//�s�g���i
            String l_strSplitType = null;//����
            double l_dblSellOrderQuantity = 0;//���t����
            double l_dblBuyOrderQuantity = 0;//���t����
            double l_dblLimitPrice = 0;//�w�l
            String l_strExecutionCondition = null;//���s����
            String l_strTransactionType = null;//����敪
            String l_strTicketNumber = null;//�`�[��
            String l_strContractCheck = null;//���ʃ`�F�b�N
            Timestamp l_tsReceivedDateTime = null;//�󒍓���
            String l_strOrderChannel = null;//�����`���l��
            String l_strCommisionNumber = null;//�萔����
            String l_strCommisionBranchNumber = null;//�萔�����}��
            String l_strCommisionProductCode = null;//�萔�����i�R�[�h
            String l_strFuturesOptionDiv = null;
            int l_intOrder_action_serial_no = 0;//��������ԍ�
            
            IfoOrderImpl l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;

            AccountManager l_accountManager =GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //�����擾
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //�敨�I�v�V�����敪���擾
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();

            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                    l_ifoOrderUnitRow.getProductId());

            //�Ώۃf�[�^���擾����----------------------------[START]
            //throw NotFoundException
            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());

            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();

            //���X�R�[�h���擾����
            l_strBranchCode = l_banch.getBranchCode();

            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager.getMainAccount(
            l_ifoOrderUnitRow.getAccountId()).getAccountCode();

            //���҃R�[�h���擾����
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();

            //�󒍓������擾����
            l_tsReceivedDateTime = l_ifoOrderUnitRow.getReceivedDateTime();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsReceivedDateTime);

            //�󒍓��敪���擾����
            //�����P��.������==�󒍓��i�󒍓����̓��t�����j�̏ꍇ�́@@0�F�����B�ȊO�A1�F�O���B
            l_strReceivedDateTimeDiv = WEB3OrderDateDivDef.YESTERDAY;

            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strReceivedDateTimeDiv = WEB3OrderDateDivDef.TODAY;
            }

            //���ʃR�[�h���擾����
            l_strOrderRequestNumber = l_ifoOrderUnitRow.getOrderRequestNumber();

            //�s��R�[�h���擾����
            l_strMarketCode = l_ifoOrderUnitRow.getSonarMarketCode();

            //�����R�[�h���擾����
            l_strProductCode = l_ifoProductImpl.getProductCode();

            //�����Y�����R�[�h���擾����
            l_strTargetProductCode =  l_ifoProductImpl.getUnderlyingProductCode();

            //����(�N)���擾����
            l_strDeliveryMonthYYYY = l_ifoProductImpl.getMonthOfDelivery().substring(0,4);

            //����(��)���擾����
            l_strDeliveryMonthMM =  l_ifoProductImpl.getMonthOfDelivery().substring(4);

            //�敨�I�v�V�������i���擾����
            l_futureOptionProductType = l_ifoProductImpl.getDerivativeType();

            //�s�g���i���擾����
            l_dblStrikePrice = l_ifoProductImpl.getStrikePrice();

            //�������擾����
            l_strSplitType = ((IfoProductRow) l_ifoProductImpl.getDataSourceObject()).getSplitType();

            //���t/���t���ʂ��擾����
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType))
                //605�FOP�V�K��������/601�F�敨�V�K��������
            {   //���t�̏ꍇ
                l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getQuantity();
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
                //606�FOP�V�K��������/602�F�敨�V�K��������
            {
                //���t�̏ꍇ
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getQuantity();
            }

            //�w�l���擾����
            l_dblLimitPrice = l_ifoOrderUnitRow.getLimitPrice();

            //���s�������擾����
            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getExecutionConditionType();

            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //��t
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //����
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //�o�����Έ���(�s��)
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // �o����܂Œ���(�����P��.���񒍕��̒����P��ID��null)�̏ꍇ�A�Q�F�o���B
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //������
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            //����敪���擾����
            l_strTransactionType = l_ifoOrderUnitRow.getSonarTradedCode();

            //�`�[�����擾����
            l_strTicketNumber = l_ifoOrderUnitRow.getVoucherNo();

            //�����`���l�����擾����
            l_strOrderChannel = l_ifoOrderUnitRow.getOrderChanel();

            //�萔�������擾����
            l_strCommisionNumber = l_ifoOrderUnitRow.getCommTblNo();

            //�萔�����}�Ԃ��擾����
            l_strCommisionBranchNumber = l_ifoOrderUnitRow.getCommTblSubNo();

            //�萔�����i�R�[�h���擾����
            l_strCommisionProductCode =
                    l_ifoOrderUnitRow.getCommProductCode();

            //���������ŏI�ʔԂ��擾����
            l_intOrder_action_serial_no =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();
            
            //�f�[�^�R�[�h���擾����
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER;
            }
            //�Ώۃf�[�^���擾����--------------------------------[END]

            //�L���[�e�[�u���փf�[�^����
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
                new HostFotypeOrderAllParams();

            //set�f�[�^�R�[�h
            l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);

            //set�����h�c
            //�����P��.����ID
            l_hostFotypeOrderAllParams.setAccountId(l_ifoOrderUnitRow.getAccountId());

            //set�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
            //set���X�R�[�h
            l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
            //set�ڋq�R�[�h
            l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
            //set���҃R�[�h
            l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
            //set�s��R�[�h
            l_hostFotypeOrderAllParams.setSonarMarketCode(l_strMarketCode);
            //set�󒍓��敪
            l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strReceivedDateTimeDiv);
            //set���ʃR�[�h
            l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
            //set�����R�[�h
            l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);

            //set�����o�H�敪
            //�����P��.�����o�H�敪
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(
                l_ifoOrderUnitRow.getSubmitOrderRouteDiv());

            //set�����Y�����R�[�h
            l_hostFotypeOrderAllParams.setTargetProductCode(l_strTargetProductCode);
            //set�����i�N�j
            //�����P��.�����h�c�ɊY���������.�����̔N�����iYYYY�j
            l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(l_strDeliveryMonthYYYY);
            //set�����i���j
            //�����P��.�����h�c�ɊY���������.�����̌������iMM�j
            l_hostFotypeOrderAllParams.setDeliveryMonthMm(l_strDeliveryMonthMM);
            //set�敨�I�v�V�������i
            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.CALL_OPTIONS);    
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.PUT_OPTIONS);
            }
            else if (IfoDerivativeTypeEnum.FUTURES.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.FUTURES);
            }
            
            //set�s�g���i
            l_hostFotypeOrderAllParams.setStrikePrice(l_dblStrikePrice);
            //set����
            l_hostFotypeOrderAllParams.setSplitType(l_strSplitType);
            //set���t����
            l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
            //set���t����
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
            //set�w�l
            l_hostFotypeOrderAllParams.setLimitPrice(l_dblLimitPrice);
            //set���s����
            l_hostFotypeOrderAllParams.setExecutionCondition(l_strExecutionCondition);

            //�t�w�l��l
            //NULL
            l_hostFotypeOrderAllParams.setStopOrderPrice(null);

            //�iW�w�l�j�����w�l
            //NULL
            l_hostFotypeOrderAllParams.setWLimitPrice(null);

            //set����敪
            l_hostFotypeOrderAllParams.setTransactionType(l_strTransactionType);
            //set�`�[��
            l_hostFotypeOrderAllParams.setTicketNumber(l_strTicketNumber);
            //set���ʃ`�F�b�N
            l_hostFotypeOrderAllParams.setContractCheck(l_strContractCheck);
            //set�󒍓���
            l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsReceivedDateTime);
            //set�����`���l��
            l_hostFotypeOrderAllParams.setOrderChanel(l_strOrderChannel);
            //set�萔����
            l_hostFotypeOrderAllParams.setCommisionNumber(l_strCommisionNumber);
            //set�萔�����}��
            l_hostFotypeOrderAllParams.setCommisionBranchNumber(l_strCommisionBranchNumber);
            //set�萔�����i�R�[�h
            l_hostFotypeOrderAllParams.setCommisionProductCode(l_strCommisionProductCode);

            //��������     change_quantity
            //null
            l_hostFotypeOrderAllParams.setChangeQuantity(null);

            //�����w�l    change_limit_price
            //null
            l_hostFotypeOrderAllParams.setChangeLimitPrice(null);

            //�������s����     change_execution_condition
            //null
            l_hostFotypeOrderAllParams.setChangeExecutionCondition(null);

            //�����t�w�l��l        change_stop_order_price
            //null
            l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);

            //�����iW�w�l�j�����w�l        change_w_limit_price
            //null
            l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);

            //����敪        cancel_div
            //0�F����ȊO
            l_hostFotypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);

            //�t�����g����������敪�R�[�h     front_order_exchange_code
            //�敨OP�����T�[�r�X.get�t�����g����������敪�R�[�h()�̖߂�l
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_objectManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_ifoOrderUnitRow.getMarketId());

            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                l_ifoOrderService.getFrontOrderExchangeCode(l_market.getMarketCode()));
            
            //�t�����g�����V�X�e���敪       front_order_system_code
            //�敨OP�����T�[�r�X.get�t�����g�����V�X�e���敪()�̖߂�l
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                l_ifoOrderService.getFrontOrderSystemCode(l_market.getMarketCode()));

            //�t�����g��������敪�R�[�h      front_order_trade_code
            //1�F��������
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);

            //���Ȉϑ��敪      tradeaudit_code
            //0�F�ϑ�
            l_hostFotypeOrderAllParams.setTradeauditCode(
                WEB3TradeauditCodeDef.COMMISSION);

            //�Г���������      corp_code
            //�敨OP�����T�[�r�X.get�Г���������()�̖߂�l
            l_hostFotypeOrderAllParams.setCorpCode(
                l_ifoOrderService.getCorpCode((IfoOrderUnit)l_orderUnit));

            //�i������j�Г���������     org_corp_code
            //null
            l_hostFotypeOrderAllParams.setOrgCorpCode(null);

            //���z�T�[�oNo.�iJSOES�j     virtual_server_number_jsoes
            //null
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);

            //�s�ꔭ��No.       market_order_number
            //null
            l_hostFotypeOrderAllParams.setMarketOrderNumber(null);

            //AMG���M����        amg_send_time
            //null
            l_hostFotypeOrderAllParams.setAmgSendTime(null);

            //AMG���͕ۏ؎�M����      amg_ack_time
            //null
            l_hostFotypeOrderAllParams.setAmgAckTime(null);

            //�s����͕ۏ؎�M����      market_ack_time
            //null
            l_hostFotypeOrderAllParams.setMarketAckTime(null);

            //�S���������敪          all_order_change_div
            //0�F�S�����ȊO
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
            
            //set��������ԍ�
            l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrder_action_serial_no);
            //set�����敪
            l_hostFotypeOrderAllParams.setStatus(l_strStatus);
            

            log.debug("l_hostFotypeOrderAllParams" + l_hostFotypeOrderAllParams);

            //(*2) �L���[�e�[�u���ɍs��}������B
            //�}������s�̓��e�́ADB�X�V�d�l[OP�V�K��_�敨OP�����L���[�e�[�u��.xls]�Q�ƁB
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
        }
        catch (DataException l_de)
        {
            log.error("__an unexpected error__", l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__an unexpected error__", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (insert�ԍϒ����L���[)<BR>
     * <BR>
     * �iinsertSettleContractHostOrder�j<BR>
     * �敨OP��������L���[�e�[�u���ɕԍϒ����̃f�[�^��o�^����B<BR>
     * <BR>
     * �P�j�@@this.getOrderUnits(����ID)�ɂĒ����P�ʂ��擾����B<BR>
     * �@@���߂�l��0�Ԗڂ̗v�f�𒍕��P�ʂƂ��Ďg�p����B<BR>
     * <BR>
     * �Q�j�@@�敨OP��������L���[�e�[�u���Ƀf�[�^��o�^����B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�I�v�V�����̏ꍇ�i�����P��.�敨�^�I�v�V�����敪="�I�v�V����"�j<BR>
     * �@@�@@�}������s�̓��e�́ADB�X�V�d�l<BR>
     * �@@�@@�uOP�ԍ�_�敨OP��������L���[�e�[�u��.xls�v�Q��<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�敨�̏ꍇ�i�����P��.�敨�^�I�v�V�����敪="�敨"�j<BR>
     * �@@�@@�X�V����s�̓��e�́ADB�X�V�d�l<BR>
     * �@@�@@�u�敨�ԍ�_�敨OP��������L���[�e�[�u��.xls�v �Q�ƁB<BR>
     * @@param l_lngOrderId - ����ID<BR>
     * ����ID�B
     * @@throws WEB3BaseException
     */
    public void insertSettleContractHostOrder(
        long l_lngOrderId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "insertSettleContractHostOrder(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.OPTION_ORDER;// ���N�G�X�g�f�[�^�R�[�h
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//�����敪 "0�F������"
            String l_strInstitutionCode = null;//�،���ЃR�[�h
            String l_strBranchCode = null;//���X�R�[�h
            String l_strAccountCode = null;//�ڋq�R�[�h
            String l_strTraderCode = null;//���҃R�[�h
            String l_strReceivedDateTimeDiv = null;//�󒍓��敪
            String l_strOrderRequestNumber = null;//���ʃR�[�h
            String l_strMarketCode = null;//�s��
            String l_strProductCode = null;//�����R�[�h
            String l_strTargetProductCode = null;//�����Y�����R�[�h
            String l_strDeliveryMonthYYYY = null;//�����i�N�j
            String l_strDeliveryMonthMM = null;//�����i���j
            IfoDerivativeTypeEnum l_futureOptionProductType = null;//�敨�I�v�V�������i
            double l_dblStrikePrice = 0;//�s�g���i
            String l_strSplitType = null;//����
            double l_dblSellOrderQuantity = 0;//���t����
            double l_dblBuyOrderQuantity = 0;//���t����
            double l_dblLimitPrice = 0;//�w�l
            String l_strExecutionCondition = null;//���s����
            String l_strTransactionType = null;//����敪
            String l_strTicketNumber = null;//�`�[��
            String l_strContractCheck = null;//���ʃ`�F�b�N
            Timestamp l_tsReceivedDateTime = null;//�󒍓���
            String l_strOrderChannel = null;//�����`���l��
            String l_strCommisionNumber = null;//�萔����
            String l_strCommisionBranchNumber = null;//�萔�����}��
            String l_strCommisionProductCode = null;//�萔�����i�R�[�h
            String l_strFuturesOptionDiv = null;
            int l_intOrder_action_serial_no = 0;//��������ԍ�

            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;

            IfoProductImpl l_ifoProductImpl = null;

            AccountManager l_accountManager = GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            //�����擾
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //�敨�I�v�V�����敪���擾
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();

            //�Ώۃf�[�^���擾����----------------------------[START]
            //throw NotFoundException
            //�����I�u�W�F�N�g���擾����
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                                    l_ifoOrderUnitRow.getProductId());

            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());

            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();

            //���X�R�[�h���擾����
            l_strBranchCode = l_banch.getBranchCode();

            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_ifoOrderUnitRow.getAccountId()).getAccountCode();

            //���҃R�[�h���擾����
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();

            //�󒍓������擾����
            l_tsReceivedDateTime = l_ifoOrderUnitRow.getReceivedDateTime();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsReceivedDateTime);

            //�󒍓��敪���擾����
            l_strReceivedDateTimeDiv = WEB3OrderDateDivDef.YESTERDAY;
            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strReceivedDateTimeDiv = WEB3OrderDateDivDef.TODAY;
            }
            //���ʃR�[�h���擾����
            l_strOrderRequestNumber = l_ifoOrderUnitRow.getOrderRequestNumber();

            //�s��R�[�h���擾����
            l_strMarketCode = l_ifoOrderUnitRow.getSonarMarketCode();

            //�����R�[�h���擾����
            l_strProductCode = l_ifoProductImpl.getProductCode();

            //�����Y�����R�[�h���擾����
            l_strTargetProductCode =  l_ifoProductImpl.getUnderlyingProductCode();

            //����(�N)���擾����
            l_strDeliveryMonthYYYY = l_ifoProductImpl.getMonthOfDelivery().substring(0,4);

            //����(��)���擾����
            l_strDeliveryMonthMM =  l_ifoProductImpl.getMonthOfDelivery().substring(4);

            //�敨�I�v�V�������i���擾����
            l_futureOptionProductType =l_ifoProductImpl.getDerivativeType();

            //�s�g���i���擾����
            l_dblStrikePrice = l_ifoProductImpl.getStrikePrice();

            //�������擾����
            l_strSplitType = ((IfoProductRow) l_ifoProductImpl.getDataSourceObject()).getSplitType();

            //���t/���t���ʂ��擾����
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();            
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType) ||
            OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
                //607�FOP�����ԍϒ����i���ԍρj/603�F�敨�����ԍϒ����i���ԍρj
            {   //���t�̏ꍇ
                l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getQuantity();
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType) || 
            OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
                //608�FOP�����ԍϒ����i���ԍρj/604�F�敨�����ԍϒ����i���ԍρj
            {
                //���t�̏ꍇ
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getQuantity();
            }

            //�w�l���擾
            l_dblLimitPrice = l_ifoOrderUnitRow.getLimitPrice();

            //���s�������擾����
            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getExecutionConditionType();

            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //��t
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //����
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //�o�����Έ���(�s��)
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // �o����܂Œ���(�����P��.���񒍕��̒����P��ID��null)�̏ꍇ�A�Q�F�o���B
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //������
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            //����敪���擾����
            l_strTransactionType = l_ifoOrderUnitRow.getSonarTradedCode();

            //�`�[�����擾����
            l_strTicketNumber = l_ifoOrderUnitRow.getVoucherNo();

            //���ʃ`�F�b�N���擾����
            //�敨OP�f�[�^�A�_�v�^.get���v��敪(�����P��.���v��敪)�̖߂�l
            l_strContractCheck = WEB3IfoDataAdapter.getDayTradeType(
                l_ifoOrderUnitRow.getDayTradeType());

            //�����`���l�����擾����
            l_strOrderChannel = l_ifoOrderUnitRow.getOrderChanel();

            //�萔�������擾����
            l_strCommisionNumber = l_ifoOrderUnitRow.getCommTblNo();

            //�萔�����}�Ԃ��擾����
            l_strCommisionBranchNumber = l_ifoOrderUnitRow.getCommTblSubNo();

            //�萔�����i�R�[�h���擾����
            l_strCommisionProductCode =
                    l_ifoOrderUnitRow.getCommProductCode();

            //���������ŏI�ʔԂ��擾����
            l_intOrder_action_serial_no =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();

            //�f�[�^�R�[�h���擾����
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER;
            }
            //�Ώۃf�[�^���擾����-----------------------------------[END]            

            //�L���[�e�[�u���փf�[�^����
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
                new HostFotypeOrderAllParams();

            //set�f�[�^�R�[�h
            l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);

            //set�����h�c
            //�����P��.����ID
            l_hostFotypeOrderAllParams.setAccountId(l_ifoOrderUnitRow.getAccountId());

            //set�،���ЃR�[�h
            l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
            //set���X�R�[�h
            l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
            //set�ڋq�R�[�h
            l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
            //set���҃R�[�h
            l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
            //set�󒍓���
            l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsReceivedDateTime);
            //set�󒍓��敪
            l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strReceivedDateTimeDiv);
            //set���ʃR�[�h
            l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
            //set�s��R�[�h
            l_hostFotypeOrderAllParams.setSonarMarketCode(l_strMarketCode);
            //set�����R�[�h
            l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);

            //set�����o�H�敪
            //�����P��.�����o�H�敪
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(
                l_ifoOrderUnitRow.getSubmitOrderRouteDiv());

            //set�����Y�����R�[�h
            l_hostFotypeOrderAllParams.setTargetProductCode(l_strTargetProductCode);
            //set�����i�N�j
            l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(l_strDeliveryMonthYYYY);
            //set�����i���j
            l_hostFotypeOrderAllParams.setDeliveryMonthMm(l_strDeliveryMonthMM);
            //set�敨�I�v�V�������i

            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.CALL_OPTIONS);    
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.PUT_OPTIONS);
            }
            else if (IfoDerivativeTypeEnum.FUTURES.equals(l_futureOptionProductType))
            {
                l_hostFotypeOrderAllParams.setFutureOptionProductType(WEB3IfoProductTypeDef.FUTURES);
            }
                        
            //set�s�g���i
            l_hostFotypeOrderAllParams.setStrikePrice(l_dblStrikePrice);
            //set����
            l_hostFotypeOrderAllParams.setSplitType(l_strSplitType);
            //set���t����
            l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
            //set���t����
            l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
            //set�w�l
            l_hostFotypeOrderAllParams.setLimitPrice(l_dblLimitPrice);
            //set���s����
            l_hostFotypeOrderAllParams.setExecutionCondition(l_strExecutionCondition);

            //�t�w�l��l          stop_order_price
            //NULL
            l_hostFotypeOrderAllParams.setStopOrderPrice(null);

            //�iW�w�l�j�����w�l         w_limit_price
            //NULL
            l_hostFotypeOrderAllParams.setWLimitPrice(null);

            //set����敪
            l_hostFotypeOrderAllParams.setTransactionType(l_strTransactionType);
            //set�`�[��
            l_hostFotypeOrderAllParams.setTicketNumber(l_strTicketNumber);
            //set���ʃ`�F�b�N
            l_hostFotypeOrderAllParams.setContractCheck(l_strContractCheck);
            //set�����`���l��
            l_hostFotypeOrderAllParams.setOrderChanel(l_strOrderChannel);
            //set�萔����
            l_hostFotypeOrderAllParams.setCommisionNumber(l_strCommisionNumber);
            //set�萔�����}��
            l_hostFotypeOrderAllParams.setCommisionBranchNumber(l_strCommisionBranchNumber);
            //set�萔�����i�R�[�h
            l_hostFotypeOrderAllParams.setCommisionProductCode(l_strCommisionProductCode);

            //��������     change_quantity
            //null
            l_hostFotypeOrderAllParams.setChangeQuantity(null);

            //�����w�l    change_limit_price
            //null
            l_hostFotypeOrderAllParams.setChangeLimitPrice(null);

            //�������s����     change_execution_condition
            //null
            l_hostFotypeOrderAllParams.setChangeExecutionCondition(null);

            //�����t�w�l��l        change_stop_order_price
            //null
            l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);

            //�����iW�w�l�j�����w�l        change_w_limit_price
            //null
            l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);

            //����敪        cancel_div
            //0�F����ȊO
            l_hostFotypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);

            //�t�����g����������敪�R�[�h     front_order_exchange_code
            //�敨OP�����T�[�r�X.get�t�����g����������敪�R�[�h()�̖߂�l
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_objectManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market = l_objectManager.getMarket(l_ifoOrderUnitRow.getMarketId());

            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                l_ifoOrderService.getFrontOrderExchangeCode(l_market.getMarketCode()));

            //�t�����g�����V�X�e���敪       front_order_system_code
            //�敨OP�����T�[�r�X.get�t�����g�����V�X�e���敪()�̖߂�l
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                l_ifoOrderService.getFrontOrderSystemCode(l_market.getMarketCode()));

            //�t�����g��������敪�R�[�h      front_order_trade_code
            //1�F��������
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);

            //���Ȉϑ��敪      tradeaudit_code
            //0�F�ϑ�
            l_hostFotypeOrderAllParams.setTradeauditCode(
                WEB3TradeauditCodeDef.COMMISSION);

            //�Г���������      corp_code
            //�敨OP�����T�[�r�X.get�Г���������()�̖߂�l
            l_hostFotypeOrderAllParams.setCorpCode(
                l_ifoOrderService.getCorpCode((IfoOrderUnit)l_orderUnit));

            //�i������j�Г���������     org_corp_code
            //null
            l_hostFotypeOrderAllParams.setOrgCorpCode(null);

            //���z�T�[�oNo.�iJSOES�j     virtual_server_number_jsoes
            //null
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);

            //�s�ꔭ��No.       market_order_number
            //null
            l_hostFotypeOrderAllParams.setMarketOrderNumber(null);

            //AMG���M����        amg_send_time
            //null
            l_hostFotypeOrderAllParams.setAmgSendTime(null);

            //AMG���͕ۏ؎�M����      amg_ack_time
            //null
            l_hostFotypeOrderAllParams.setAmgAckTime(null);

            //�s����͕ۏ؎�M����      market_ack_time
            //null
            l_hostFotypeOrderAllParams.setMarketAckTime(null);

            //�S���������敪          all_order_change_div
            //0�F�S�����ȊO
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);

            //set��������ԍ�
            l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrder_action_serial_no);
             //set�����敪
            l_hostFotypeOrderAllParams.setStatus(l_strStatus);

            log.debug("l_hostFotypeOrderAllParams =" +l_hostFotypeOrderAllParams);

            //(*2) OP�ԍ�_�敨OP�����L���[�e�[�u���ɍs��}������B
            //�}������s�̓��e�́ADB�X�V�d�l�Q�ƁB
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("__an unexpected error__", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (DataException l_de)
        {
            log.error("__an unexpected error__", l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�V�K������)
     * �ivalidateOpenContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * OP�V�K�������R�����s���B<BR>
     * <BR>
     * this.validate�V�K������()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate�V�K������()�Ɏw�肷�����]<BR>
     * �@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�M�p�V�K���������e�F�@@�p�����[�^.�V�K���������e<BR>
     * �@@�����P�ʁF�@@null<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_openContractOrderSpec - �敨OP�V�K���������e
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_openContractOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateOpenContractOrder(WEB3GentradeSubAccount, WEB3IfoOpenContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        NewOrderValidationResult l_result =
            this.validateOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (remove�J�z�������P��)<BR>
     * �����̒����P�ʃI�u�W�F�N�g�̃��X�g����A�J�z���̒����P�ʃI�u�W�F�N�g���������A������̃��X�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����P��Row�̎擾<BR>
     * <BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �@@�p�����[�^.�����P�ʈꗗ�v�f������Loop�������s���A<BR>
     * �@@�����P��.getDataSourceObject()�ɂ�<BR>
     * �@@�����P��Row���擾���AArrayList�ɒǉ�����B<BR>
     * <BR>
     * �Q�j�@@this.remove�J�z�������P��()�ɏ������Ϗ�����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�����P��Params�ꗗ�F�@@�P�j�ɂĐ�������ArrayList.toArray()<BR>
     * @@param l_orderUnitList - �����P�ʃI�u�W�F�N�g�̈ꗗ<BR>
     * @@return IfoOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 40A9A5A9018F
     */
    public IfoOrderUnit[] removeCarryOverOriginalOrderUnit(IfoOrderUnit[] l_orderUnitList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "removeCarryOverOriginalOrderUnit(IfoOrderUnit[])";

        log.entering(STR_METHOD_NAME);

        List  l_orderUnitListNew = new ArrayList();
        for (int i = 0; i < l_orderUnitList.length; i++)
        {
            l_orderUnitListNew.add(l_orderUnitList[i].getDataSourceObject());            
        }       
        IfoOrderUnitParams[] l_ifoOrderUnitParamsList =
            this.removeCarryOverOriginalOrderUnit((IfoOrderUnitParams[])l_orderUnitListNew.toArray(new IfoOrderUnitParams[0]));
        
        if (l_ifoOrderUnitParamsList == null)
        {
	        log.exiting(STR_METHOD_NAME);
	        return null;
	    }
        else
        {
            ArrayList l_orderUnitListReturn = new ArrayList();
            for (int i = 0; i < l_ifoOrderUnitParamsList.length; i++)
            {
                l_orderUnitListReturn.add(this.toOrderUnit(l_ifoOrderUnitParamsList[i]));
            }
            log.exiting(STR_METHOD_NAME);
            return (IfoOrderUnit[])l_orderUnitListReturn.toArray(new IfoOrderUnit[0]);
        }
    }
    
    /**
     * (remove�J�z�������P��)
     *�����̒����P�ʃI�u�W�F�N�g�̃��X�g����A�J�z���̒����P�ʃI�u�W�F�N�g���������A<BR>
     *������̃��X�g��ԋp����B<BR>
     *<BR>
     *�P�j�@@�p�����[�^.�����P�ʈꗗ == null�̏ꍇ�A<BR>
     *�@@�@@null��ԋp���ďI������B<BR>
     *<BR>
     *�Q�j�@@�����Ώۂ̔��� <BR>
     *<BR>
     *�@@�@@�@@�ȉ��A�p�����[�^.�����P�ʈꗗ�̗v�f������Loop�����B <BR>
     *<BR>
     * �@@�@@�@@[�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)��"��������"�̏ꍇ ]<BR>
     *�@@�@@�@@�@@�@@���X�g�ɂ��̂܂܎c���B <BR>
     *<BR>
     * �@@�@@�@@[�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)��"��������"�ȊO�̏ꍇ ]<BR>
     *<BR>
     *�@@�@@�@@�@@[���񒍕��̏ꍇ] <BR>
     * �@@�@@�@@�@@(�����P��.���񒍕��̒����P��ID == 0 || �����P��.���񒍕��̒����P��ID == null�̏ꍇ)<BR>
     *�@@�@@�@@�@@�@@���X�g��(�p�����[�^.�����P�ʈꗗ)���������A <BR>
     *�@@�@@�@@�@@�@@�@@�@@�����P��.�����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID <BR>
     *�@@�@@�@@�@@�@@�ƂȂ�f�[�^�����݂����ꍇ�́A���g�������ΏۂƂ���B <BR>
     *�@@�@@�@@�@@�@@���J�z��̒��������݂���ׁB <BR>
     *<BR>
     *�@@�@@�@@�@@[�J�z�ϒ����̏ꍇ] <BR>
     *�@@�@@�@@�@@(�����P��.���񒍕��̒����P��ID != 0�̏ꍇ) <BR>
     *�@@�@@�@@�@@�@@���X�g��(�p�����[�^.�����P�ʈꗗ)���������A <BR>
     *�@@�@@�@@�@@�@@�@@�@@�����P��.���񒍕��̒����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID <BR>
     *�@@�@@�@@�@@�@@�ƂȂ�f�[�^�����݂����ꍇ�́A�쐬�������r���A<BR>
     *�@@�@@�@@�@@�@@�ŐV�̒����P�ʈȊO��S�ď����ΏۂƂ���B<BR> 
     *�@@�@@�@@�@@�@@���ŐV�̌J�z�����݂̂�\������ׁB <BR>
     *<BR>
     *�R�j�@@���X�g����̏����ΏۂƔ��肳�ꂽ�J�z���̒����P�ʃI�u�W�F�N�g���A<BR>
     *�@@�@@�����P�ʈꗗ����S�ď�������B<BR> 
     *�@@�@@���p�����[�^.�����P�ʃI�u�W�F�N�g�̕��я��͌ڋq�w��̃\�[�g�����ɂ�邽�߁A<BR>
     *�@@�@@�@@�@@�����͍Ō�ɓZ�߂čs���K�v������B<BR> 
     *<BR>
     *�S�j�@@�����ς̒����P�ʈꗗ��ԋp����B<BR>
     *�@@�@@�������P�ʈꗗ�̗v�f����0�ɂȂ����ꍇ��NULL��ԋp����B<BR>
     * @@param l_orderUnitList - �敨OP�����P��Params�̔z��<BR>
     * @@return IfoOrderUnitParams[]
     * @@throws WEB3BaseException
     * @@roseuid 40A9A5A9018F
     */
    public IfoOrderUnitParams[] removeCarryOverOriginalOrderUnit(IfoOrderUnitParams[] l_orderUnitList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "removeCarryOverOriginalOrderUnit(IfoOrderUnitParams[])";

        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�����P�ʈꗗ == null�̏ꍇ�A
        //null��ԋp���ďI������B
        if (l_orderUnitList == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        List  l_orderUnitListNew = new ArrayList();
        for (int i = 0; i < l_orderUnitList.length; i++)
        {
            l_orderUnitListNew.add(l_orderUnitList[i]);            
        }       
        ListIterator l_iterator = l_orderUnitListNew.listIterator();
        while (l_iterator.hasNext())
        {
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_iterator.next();
            //[�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)��"��������"�̏ꍇ ]
            //���X�g�ɂ��̂܂܎c���B
            //[�敨OP�f�[�^�A�_�v�^.get���������敪(�����P��)��"��������"�ȊO�̏ꍇ ]
            IfoOrderUnit l_ifoOrderUnit =
                (IfoOrderUnit)this.toOrderUnit(l_orderUnitRow);
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);

            if (!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType))
            {
                //[���񒍕��̏ꍇ]<BR> 
                //(�����P��.���񒍕��̒����P��ID == 0 || �����P��.���񒍕��̒����P��ID == null�̏ꍇ)
                //���X�g��(�p�����[�^.�����P�ʈꗗ)���������A<BR> 
                //�����P��.�����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID<BR> 
                //�ƂȂ�f�[�^�����݂����ꍇ�́A���g�������ΏۂƂ���B<BR> 
                // ���J�z��̒��������݂���ׁB<BR>
                if (l_orderUnitRow.getFirstOrderUnitIdIsNull()
                    || l_orderUnitRow.getFirstOrderUnitId() == 0)
                {
                    for (int j =0; j < l_orderUnitList.length; j++)
                    {
                        IfoOrderUnitRow l_orderUnitRowSource =
                                        (IfoOrderUnitRow)l_orderUnitList[j];
                        if (l_orderUnitRow.getOrderUnitId() == l_orderUnitRowSource.getFirstOrderUnitId())
                        {
                            l_iterator.remove();
                            break;                                                    
                        }
                    }
                }
                else
                {
                    //[�J�z�ϒ����̏ꍇ] <BR>
                    //(�����P��.���񒍕��̒����P��ID != 0�̏ꍇ) <BR>
                    //���X�g��(�p�����[�^.�����P�ʈꗗ)���������A <BR>
                    //�����P��.���񒍕��̒����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID <BR>
                    //�ƂȂ�f�[�^�����݂����ꍇ�́A�쐬�������r���A�ŐV�̒����P�ʈȊO��S�ď����ΏۂƂ���B
                    //���ŐV�̌J�z�����݂̂�\������ׁB<BR> 
 
                    for (int j =0; j < l_orderUnitList.length; j++)
                    {
                        IfoOrderUnitRow l_orderUnitRowSource =
                                        (IfoOrderUnitRow)l_orderUnitList[j];
                        if (l_orderUnitRow.getFirstOrderUnitId() == l_orderUnitRowSource.getFirstOrderUnitId())
                        {
                            if (WEB3DateUtility.compareToSecond(l_orderUnitRowSource.getCreatedTimestamp(), l_orderUnitRow.getCreatedTimestamp()) > 0)
                            {
                                l_iterator.remove();
                                break;  
                            }                                                  
                        }
                    }                   
                }
            }
        }
        if (l_orderUnitListNew.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return (IfoOrderUnitParams[])l_orderUnitListNew.toArray(new IfoOrderUnitParams[0]);
        }
    }

   /**
     * (throw�]�̓G���[�ڍ׏��)<BR>
     * �����Őݒ肳�ꂽ����]�͌��ʁE�⏕�����ɉ����āA<BR>
     * �Y������G���[�R�[�h��ݒ肵�ė�O��throw������B<BR>
     * <BR>
     * �P�j�@@����]�͌���.����t���O��true�̏ꍇ�A<BR>
     * �@@���̂܂�return����B�i�]�̓`�F�b�NOK�j<BR>
     * �Q�j�@@����]�͌���.����t���O��false�̏ꍇ<BR>
     * �@@�Q�|�P�j�@@�؋��������̏ꍇ<BR>
     * �@@�@@�⏕����.�⏕�����^�C�v��<BR>
     * �@@�@@"�����I�v�V������������i�敨�؋����j"�ł���΁A<BR>
     * �@@�@@�u����]�̓`�F�b�N�G���[�v�̗�O��throw����B<BR>
     * �@@�Q�|�Q�j�@@�I�v�V�������������̏ꍇ<BR>
     * �@@�@@�⏕����.�⏕�����^�C�v��<BR>
     * �@@�@@"�����I�v�V������������i�敨�؋����j"�ł���΁A<BR>
     * �@@�@@�u�V�K���a������s���v�̗�O��throw����B<BR>
     * @@param l_tpResult - (����]�͌���)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@throws WEB3BaseException
     */
   public void throwTpErrorInfo(
        WEB3TPTradingPowerResult l_tpResult,
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
   {
        String STR_METHOD_NAME =
            "throwTpErrorInfo(WEB3TPTradingPowerResult, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_tpResult == null || l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80017);

            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + STR_METHOD_NAME);
         }

        //����]�͌���.����t���O��true�̏ꍇ
        if (l_tpResult.isResultFlg())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);

            //�⏕����.�⏕�����^�C�v=="�����I�v�V������������i�敨�؋����j"
            if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01935,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
    }

   /**
     * (get�����G���[���R�R�[�h)<BR>
     * �����̃G���[�R�[�h���璍���G���[���R�R�[�h�ւ̕ϊ����s���B<BR>
     * �i�����G���[���R�R�[�h�̃R�[�h�̌n�́A�敨OP�����P�ʃe�[�u����DB���C�A�E�g���Q�Ɓj<BR>
     * <BR>
     * �ϊ��d�l�͈ȉ��̒ʂ�B<BR>
     * ---------------------------------------------------------------------<BR>
     * "����"<BR>
     * �����̃G���[�R�[�h==null<BR>
     * <BR>
     *"�l���E���ݒl�G���["<BR>
     * �iBUSINESS_ERROR_00148 or<BR>
     * �@@BUSINESS_ERROR_00031�j<BR>
     * <BR>
     * "�a����s���G���["�i���I�v�V�������������̏ꍇ�j<BR>
     * �@@BUSINESS_ERROR_01935<BR>
     * <BR>
     * "�����w���敨�I�v�V�����c���s���G���["<BR>
     * �@@BUSINESS_ERROR_01306<BR>
     * <BR>
     * "���ʎc���s���G���["<BR>
     * �@@BUSINESS_ERROR_00299<BR>
     * <BR>
     * "������~�����G���["<BR>
     * �@@BUSINESS_ERROR_00004<BR>
     * <BR>
     * "�s��ύX�����G���["<BR>
     * �iBUSINESS_ERROR_00003 or<BR>
     * �@@BUSINESS_ERROR_00735�j<BR>
     * <BR>
     *"�����J�z�X�L�b�v�����G���["<BR>
     * �@@BUSINESS_ERROR_00684<BR>
     * <BR>
     * "�������`�F�b�N�G���["<BR>
     * �@@BUSINESS_ERROR_00205<BR>
     * <BR>
     * "���̑��G���["<BR>
     * ��L�ȊO<BR>
     * @@param l_strErrorCode - (�G���[�R�[�h)<BR>
     * WEB3ErrorCatalog�ɒ�`����Ă���G���[�R�[�h�B<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException
     */
    public String getErrorReasonCode(
        String l_strErrorCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getErrorReasonCode(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strErrorCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            //"0000"�i����j
            return WEB3ErrorReasonCodeDef.NORMAL;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00148.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00031.getErrorCode().equals(l_strErrorCode))
        {
            //"0001"�i�l���E���ݒl�G���[�j
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01935.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0002""�a����s���G���["�i���I�v�V�������������̏ꍇ�j
            return WEB3ErrorReasonCodeDef.DEPOSIT_MONEY_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01306.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0003""�����w���敨�I�v�V�����c���s���G���[" 
            return WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00299.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0005"�i���ʎc���s���G���[" �j
            return WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00004.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0006"�i������~�����G���[�j
            return WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
        }                                
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00003.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00735.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0007"�i�s��ύX�����G���[�j
            return WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
        } 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00684.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0011"�i�����J�z�X�L�b�v�����G���[�j
            return WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00205.getErrorCode().equals(l_strErrorCode))
        {
            //"0013"�i�������`�F�b�N�G���[�j
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorReasonCodeDef.BIZ_DATE_ERROR;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            //��L�ȊO�̔����R���ŃG���[�����������ꍇ�́A"9001"�i���̑��G���[�j��ݒ肷��B
            return WEB3ErrorReasonCodeDef.OTHRE_ERROR;
        }
    }

   /**
     * (update�����x��)<BR>
     * <BR>
     * �iupdateOrderDelay�j<BR>
     * �w�肳�ꂽ����ID�ɕR�t�������f�[�^�ނ𔭒��x���̃X�e�[�^�X�ɍX�V����B<BR>
     * �P�j�@@�p�����[�^.����ID�ɕR�t�������P�ʂ��擾����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̒����P�ʂ�clone���쐬����B<BR>
     * <BR>
     * �R�j�@@�Q�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B<BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�t�w�l���������i�����x���j_�敨OP�����P�ʃe�[�u��.xls�v��<BR>
     * �@@�@@�i�t�w�l�����j[�����x��]�敨OP�����P�ʃe�[�u���v�Q�ƁB<BR>
     * �S�j�@@�����f�[�^��update����B<BR>
     * �@@this.update�����f�[�^()���R�[������B<BR>
     * �@@[update�����f�[�^()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�R�j�ɂč쐬���������P��<BR>
     * �@@�@@is�����쐬�F�@@false�i�쐬���Ȃ��j<BR>
     * <BR>
     * �T�j�@@�R�j�ɂč쐬���������P�ʂ��g�p�� <BR>
     * �@@�@@���������e�[�u���ւP���R�[�hinsert����B <BR>
     * �@@�@@�X�V���e�́ADB�X�V�d�l <BR>
     * �@@�@@�u�t�w�l��������(�����x��)_�敨OP���������e�[�u��.xls�v�Q�ƁB<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_lngOrderID - (����ID)<BR>
     * �����x���̃X�e�[�^�X�ɍX�V���s������ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateOrderDelay(
        long l_lngOrderID)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderDelay(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�p�����[�^.����ID�ɕR�t�������P�ʂ��擾����B
        OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderID);
        if (l_orderUnits.length == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        IfoOrderUnitParams l_ifoOrderUnitParams =
            new IfoOrderUnitParams((IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject());

        //�Q�j�@@�P�j�̒����P�ʂ�clone���쐬����B
        IfoOrderUnit l_cloneIfoOrderUnit =
            (IfoOrderUnit)super.toOrderUnit(l_ifoOrderUnitParams);

        //�R�j�@@�Q�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(l_ifoOrderUnitParams.getLastOrderActionSerialNo() + 1);

        l_ifoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);

        // �S�j�@@�����f�[�^��update����B
        this.updateOrderData(l_cloneIfoOrderUnit, false);

        //�T�j�@@�R�j�ɂč쐬���������P�ʂ��g�p��
        IfoOrderActionParams l_ifoOrderActionParams = new IfoOrderActionParams();

        //���������h�c
        long l_lngOrderActionId = 0;
        try
        {
            l_lngOrderActionId = IfoOrderActionDao.newPkValue();
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

        l_ifoOrderActionParams.setOrderActionId(l_lngOrderActionId);

        //�����h�c
        l_ifoOrderActionParams.setAccountId(l_ifoOrderUnitParams.getAccountId());

        //�⏕�����h�c
        l_ifoOrderActionParams.setSubAccountId(l_ifoOrderUnitParams.getSubAccountId());

        //�����h�c
        l_ifoOrderActionParams.setOrderId(l_ifoOrderUnitParams.getOrderId());

        //�����P�ʂh�c
        l_ifoOrderActionParams.setOrderUnitId(l_ifoOrderUnitParams.getOrderUnitId());

        //�s��h�c
        if (!l_ifoOrderUnitParams.getMarketIdIsNull())
        {
            l_ifoOrderActionParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
        }

        //�������
        l_ifoOrderActionParams.setOrderType(l_ifoOrderUnitParams.getOrderType());

        //�����C�x���g�^�C�v
        l_ifoOrderActionParams.setOrderEventType(OrderEventTypeEnum.ORDER_DELAY);

        //�����P��
        if (!l_ifoOrderUnitParams.getLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setPrice(l_ifoOrderUnitParams.getLimitPrice());
        }

        //���s����
        l_ifoOrderActionParams.setExecutionConditionType(
            l_ifoOrderUnitParams.getExecutionConditionType());

        //��������
        l_ifoOrderActionParams.setOrderConditionType(
            l_ifoOrderUnitParams.getOrderConditionType());

        //�����������Z�q
        l_ifoOrderActionParams.setOrderCondOperator(
            l_ifoOrderUnitParams.getOrderCondOperator());

        //�t�w�l��l�^�C�v
        l_ifoOrderActionParams.setStopPriceType(l_ifoOrderUnitParams.getStopPriceType());

        //�t�w�l��l
        if (!l_ifoOrderUnitParams.getStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setStopOrderPrice(
                l_ifoOrderUnitParams.getStopOrderPrice());
        }

        //�iW�w�l�j�����w�l
        if (!l_ifoOrderUnitParams.getWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitPrice(l_ifoOrderUnitParams.getWLimitPrice());
        }

        //�����������t
        l_ifoOrderActionParams.setExpirationDate(l_ifoOrderUnitParams.getExpirationDate());

        //��������
        l_ifoOrderActionParams.setQuantity(l_ifoOrderUnitParams.getQuantity());

        //�s��Ɗm�F�ς݂̎w�l
        if (!l_ifoOrderUnitParams.getConfirmedPriceIsNull())
        {
            l_ifoOrderActionParams.setConfirmedPrice(l_ifoOrderUnitParams.getConfirmedPrice());
        }

        //�s��Ɗm�F�ς݂̐���
        if (!l_ifoOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_ifoOrderActionParams.setConfirmedQuantity(
                l_ifoOrderUnitParams.getConfirmedQuantity());
        }

        //��萔��
        l_ifoOrderActionParams.setExecutedQuantity(null);

        //�������
        l_ifoOrderActionParams.setOrderStatus(l_ifoOrderUnitParams.getOrderStatus());

        //���������X�e�[�^�X
        l_ifoOrderActionParams.setExpirationStatus(l_ifoOrderUnitParams.getExpirationStatus());

        //��������ԍ�
        l_ifoOrderActionParams.setOrderActionSerialNo(l_ifoOrderUnitParams.getLastOrderActionSerialNo());

        //���P��
        l_ifoOrderActionParams.setExecutedPrice(null);

        //�����^�C�v
        l_ifoOrderActionParams.setProductType(l_ifoOrderUnitParams.getProductType());

        //�����h�c
        l_ifoOrderActionParams.setProductId(l_ifoOrderUnitParams.getProductId());

        //�T�Z��n���
        if (!l_ifoOrderUnitParams.getEstimatedPriceIsNull())
        {
            l_ifoOrderActionParams.setEstimatedPrice(
                l_ifoOrderUnitParams.getEstimatedPrice());
        }

        //���������E����敪
        l_ifoOrderActionParams.setModifyCancelType(
            l_ifoOrderUnitParams.getModifyCancelType());

        //���Ϗ���
        l_ifoOrderActionParams.setClosingOrder(l_ifoOrderUnitParams.getClosingOrder());

        //�����G���[���R�R�[�h
        l_ifoOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        //���N�G�X�g�^�C�v
        l_ifoOrderActionParams.setRequestType(l_ifoOrderUnitParams.getRequestType());

        //�����ID
        if (!l_ifoOrderUnitParams.getTraderIdIsNull())
        {
            l_ifoOrderActionParams.setTraderId(l_ifoOrderUnitParams.getTraderId());
        }

        //�����o�H�敪
        l_ifoOrderActionParams.setOrderRootDiv(l_ifoOrderUnitParams.getOrderRootDiv());

        //�쐬���t
        l_ifoOrderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //�X�V���t
        l_ifoOrderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


        //����������
        l_ifoOrderActionParams.setOrgOrderConditionType(
            l_ifoOrderUnitParams.getOrgOrderConditionType());

        //�������������Z�q
        l_ifoOrderActionParams.setOrgOrderCondOperator(
            l_ifoOrderUnitParams.getOrgOrderCondOperator());

        //���t�w�l��l�^�C�v
        l_ifoOrderActionParams.setOrgStopPriceType(l_ifoOrderUnitParams.getOrgStopPriceType());

        //���t�w�l��l
        if (!l_ifoOrderUnitParams.getOrgStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgStopOrderPrice(l_ifoOrderUnitParams.getOrgStopOrderPrice());
        }

        //���iW�w�l�j�����w�l
        if (!l_ifoOrderUnitParams.getOrgWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgWLimitPrice(l_ifoOrderUnitParams.getOrgWLimitPrice());
        }

        //���iW�w�l�j���s����
        l_ifoOrderActionParams.setOrgWLimitExecCondType(l_ifoOrderUnitParams.getOrgWLimitExecCondType());

        //�iW�w�l�j���s����
        l_ifoOrderActionParams.setWLimitExecCondType(l_ifoOrderUnitParams.getWLimitExecCondType());

        //�iW�w�l�j�֑ؑO�w�l
        if (!l_ifoOrderUnitParams.getWLimitBeforeLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitBeforeLimitPrice(
                l_ifoOrderUnitParams.getWLimitBeforeLimitPrice());
        }

        //�iW�w�l�j�֑ؑO���s����
        l_ifoOrderActionParams.setWLimitBeforeExecCondType(
            l_ifoOrderUnitParams.getWLimitBeforeExecCondType());

        //�s�ꂩ��m�F�ς݂̎��s����
        l_ifoOrderActionParams.setConfirmedExecConditionType(
            l_ifoOrderUnitParams.getConfirmedExecConditionType());

        //���������敪
        l_ifoOrderActionParams.setExpirationDateType(
            l_ifoOrderUnitParams.getExpirationDateType());
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_ifoOrderActionParams);
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
      * �iget��n���z���v�j<BR>
      * <BR>
      * �����Ŏw�肳�ꂽ�����P�ʂɑ΂��������薾��.��n����̍��v�l���擾���A
      * �����P�ʃe�[�u���̊T�Z��n����ւ̐ݒ�p���z��Ԃ��B
      *  
      * �P�j�@@�����̒����P��.�����J�e�S�� == "�敨�V�K������"�̏ꍇ�́A 
      * �����P��.���v�����z��Ԃ��B 
      * �����̒����P��.�����J�e�S�� != "�敨�V�K������"�̏ꍇ�́A�ȉ��̏������s���B
      *  
      * �Q�j�@@�敨OP�g�����U�N�V�����}�l�[�W��.get��n���z���v(�����̒����P��)�ɂ��A
      * ��n���z���v���擾����B
      *  
      * �R�j�@@�����P�ʃe�[�u���̊T�Z��n����ւ̐ݒ�p���z���Z�o����B
      *  
      * �R�|�P�j�@@�����̒����P��.������ʂ��ȉ��ɊY������ꍇ�́A
      * �@@�@@�@@�@@�@@�Q�j�Ŏ擾�����l�̕����𔽓]�����l��Ԃ��B
      * �@@�@@�@@�@@�@@�@@�EOP�V�K���������iIDX_OPTIONS_BUY_TO_OPEN�j
      * �@@�@@�@@�@@�@@�@@�EOP�������ԍϒ����iIDX_OPTIONS_BUY_TO_CLOSE�j
      * 
      * �R�|�Q�j�@@�R�|�P�j�ȊO�̏ꍇ�́A
      * �@@�@@�@@�@@�@@�Q�j�Ŏ擾�����l�����̂܂ܕԂ��B<BR>
      * <BR>
      * @@param l_ifoOrderUnit �i�����P�ʁj<BR>
      * �@@�@@�@@�����P�ʃI�u�W�F�N�g�B<BR>
      * @@return double<BR>
      * @@throws WEB3BaseException<BR>
      */
    public double getNetAmount(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNetAmount(l_ifoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // �����P��.�����J�e�S�� == "�敨�V�K������"�̏ꍇ
        if (l_ifoOrderUnit.getOrderCateg().equals(OrderCategEnum.IDX_FUTURES_OPEN)== true)
        {
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            return l_ifoOrderUnitRow.getExecutedAmount();
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoFinTransactionManagerImpl l_finTransactionMgr
            = (WEB3IfoFinTransactionManagerImpl)l_tradingModule.getFinTransactionManager();

        //��n���z�̍��v�l���擾
        double l_dblDeliveryAmount = l_finTransactionMgr.getNetAmount(l_ifoOrderUnit);

        //�����P��.������� == "OP�V�K��������" or "OP�������ԍϒ���"�̏ꍇ
        if (l_ifoOrderUnit.getOrderType().equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN) ||
            l_ifoOrderUnit.getOrderType().equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE))
        {
            //��n���z���v�l�̕����𔽓]������
            l_dblDeliveryAmount =
                new BigDecimal(l_dblDeliveryAmount + "").negate().doubleValue();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblDeliveryAmount;
    }
     
    /**
     * �iget���񒍕��̒����P�ʁj�B<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�����P�ʃI�u�W�F�N�g�́A���񒍕��̒����P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.���񒍕��̒����P��ID���inull �܂��� 0�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�����̒����P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@this.getOrderUnit(�����̒����P��.���񒍕��̒����P��ID)�Ŏ擾����<BR>
     * �@@�@@�����P�ʃI�u�W�F�N�g��ԋp����B
     * @@param l_orderUnit �����P�ʃI�u�W�F�N�g�B
     * @@return OrderUnit
     * @@throws WEB3BaseException
     */
    public IfoOrderUnit getFirstOrderUnit(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getFirstOrderUnit(IfoOrderUnit l_orderUnit)";
        
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        
            if (l_orderUnitRow.getFirstOrderUnitIdIsNull() || l_orderUnitRow.getFirstOrderUnitId() == 0)
            {
                //�����̒����P��.���񒍕��̒����P��ID���inull �܂��� 0�j�̏ꍇ
                //�����̒����P�ʃI�u�W�F�N�g��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_orderUnit;
            }
            else
            {
                //��L�ȊO�̏ꍇ
                log.exiting(STR_METHOD_NAME);
                return (IfoOrderUnit)this.getOrderUnit(l_orderUnitRow.getFirstOrderUnitId());
            }            
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }

        
    }

    /**
     * (notifyW�w�l����)<BR>
     * �inotifyWLimitOrder�j <BR>
     * W�w�l�����i�X�g�b�v�����j�̓o�^�A�����A����� <BR>
     * ���[���G���W���T�[�o�ɒʒm����B <BR>
     * <BR>
     * �P�j�@@�敨OP�����P�ʂ��ǂ����̔���@@ <BR>
     * �@@�����̒����P��.getDataSourceObject()���R�[������B<BR>
     * <BR>
     * �@@[���\�b�h�̖߂�l�̌^���敨OP�����P��Row�łȂ��ꍇ] <BR>
     * �@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j <BR>
     * �@@�@@���\�񒍕��P�ʂɑ΂��āAW�w�l�͐ݒ�s�B<BR>
     * <BR>
     * �@@[�敨OP�����P��Row�ł���ꍇ] <BR>
     * �@@�@@getOrderUnit().getDataSourceObject()���R�[�����A<BR>
     * �@@�@@�����P�ʂ��擾�������B <BR>
     * <BR>
     * �Q�j�@@���[���G���W���T�[�o�ւ̒ʒm�v�ۃ`�F�b�N <BR>
     * �@@�Q�|�P�j�@@�蓮���������A������s��������R�[�����ꂽ�ꍇ�A <BR>
     * �@@�@@�i�P)�̖߂�l.���N�G�X�g�^�C�v == "����" ���� <BR>
     * �@@�@@�@@�p�����[�^.���� == ("ORDER_EXPIRED",  <BR>
     * �@@�@@�@@�@@"CANCEL_ORDER_REJECTED_BY_MKT")�j <BR>
     * <BR>
     * �@@�ȍ~�̃`�F�b�N�͎��{���Ȃ��B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�@@�֖ؑ��ς�W�w�l�������ǂ����̔��� 
     * �@@�@@�֖ؑ��ς�W�w�l�����łȂ��ꍇ�A 
     * �@@�@@�i(�P�j�̖߂�l.�������� == "W�w�l" ���� 
     * �@@�@@�P�j�̖߂�l.���N�G�X�g�^�C�v == "DEFAULT")�ȊO�j 
     * �@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j 
     * <BR>
     * �@@�Q�|�R�j�@@�������̔����x���������ǂ����̔���<BR>
     * �@@�@@OP�����}�l�[�W��.is�������x������() == true�̏ꍇ�A<BR>
     * �@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j <BR>
     * <BR>
     * �@@�@@[is�������x������()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�����P�ʁF�@@�P�j�̖߂�l<BR>
     * <BR>
     * �R�j�@@�⏕�������擾����B <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B <BR>
     * <BR>
     * �@@[getSubAccount()�Ɏw�肷�����] <BR>
     * �@@�@@arg0�F�@@�P�j�̖߂�l.����ID <BR>
     * �@@�@@arg1�F�@@�P�j�̖߂�l.�⏕����ID <BR>
     * <BR>
     * �S�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j <BR>
     * �@@���擾����B <BR>
     * <BR>
     * �T�j�@@���[���G���W���T�[�o�ɒʒm���s���B <BR>
     * �@@�����̏����A�����P��.������Ԃɂ���ĉ��L����������s���B <BR>
     * <BR>
     * �@@�T�|�P�j�@@��� <BR>
     * �@@�@@�����̏��� == "ORDER_EXPIRED"�i�X�g�b�v���������j�A <BR>
     * �@@�@@"ORDER_INVALIDATED_BY_MKT"�i�����j�A <BR>
     * �@@�@@"CANCEL_ORDER_REJECTED_BY_MKT"�i������s�j�A <BR>
     * �@@�@@�܂��͎����t�ρi�������="��t�ρi��������j"�j�̏ꍇ�A <BR>
     * �@@�@@�܂��͎���m��A�S�����A������t�G���[�i�����L�����="�N���[�Y"�j�̏ꍇ�A <BR>
     * �@@�@@�擾�����T�[�r�X.sendCancelConOrderMessage()���\�b�h�� <BR>
     * �@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@[sendCancelConOrderMessage()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕���� <BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"W�w�l" <BR>
     * �@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����" <BR>
     * �@@�@@�@@����ID�F�@@�P�j�̖߂�l.����ID <BR>
     * <BR>
     * �@@�T�|�Q�j�@@�V�K�o�^�i�������="��t�ρi�V�K�����j"�j�̏ꍇ <BR>
     * �@@�@@�擾�����T�[�r�X.sendRegisterConOrdersMessage()���\�b�h�� <BR>
     * �@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@[sendRegisterConOrdersMessage()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕���� <BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"W�w�l" <BR>
     * �@@�@@�@@�e�����̖����^�C�v�F�@@"�敨�I�v�V����" <BR>
     * �@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID <BR>
     * �@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null <BR>
     * �@@�@@�@@�q�����̒���ID�ꗗ�F�@@null <BR>
     * <BR>
     * �@@�T�|�R�j�@@�������� <BR>
     * �@@�@@�i�������="��t�ρi�ύX�����j" or "�����ρi�ύX�����j"�j�̏ꍇ <BR>
     * �@@�@@�擾�����T�[�r�X.sendModifyConOrdersMessage()���\�b�h�� <BR>
     * �@@�@@�R�[������B <BR>
     * �@@�@@���s�ꑗ�M�ϒ����^�s�ꖢ���M�����̒������l���B <BR>
     * <BR>
     * �@@�@@[sendModifyConOrdersMessage()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕���� <BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"W�w�l" <BR>
     * �@@�@@�@@�e�����̖����^�C�v�F�@@"�敨�I�v�V����" <BR>
     * �@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID <BR>
     * �@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null <BR>
     * �@@�@@�@@�q�����̒���ID�ꗗ�F�@@null <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_context - (����)<BR>
     * �����B<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@throws WEB3BaseException
     */
    private void notifyWLimitOrder(
        IfoOrderUnit l_ifoOrderUnit, OrderManagerPersistenceContext l_context) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "notifyWLimitOrder(IfoOrderUnit, OrderManagerPersistenceContext)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�敨OP�����P�ʂ��ǂ����̔���@@ 
        //�@@�����̒����P��.getDataSourceObject()���R�[������B�@@ 
        //�@@[���\�b�h�̖߂�l�̌^���敨OP�����P��Row�łȂ��ꍇ] 
        //�@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j 
        //�@@�@@���\�񒍕��P�ʂɑ΂��āAW�w�l�͐ݒ�s�B 
        if (l_ifoOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        Object l_objRow = l_ifoOrderUnit.getDataSourceObject();
        if (!(l_objRow instanceof IfoOrderUnitRow))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�@@[�敨OP�����P��Row�ł���ꍇ] 
        //�@@�@@getOrderUnit().getDataSourceObject()���R�[�����A 
        //�@@�@@�����P�ʂ��擾�������B
        IfoOrderUnitRow l_orderUnitRow = null;
        try 
        {
			l_orderUnitRow = 
			    (IfoOrderUnitRow)getOrderUnit(
			        l_ifoOrderUnit.getOrderUnitId()).getDataSourceObject();
		} 
        catch (NotFoundException e) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
		}
        
        String l_strRequestType = l_orderUnitRow.getRequestType();
        Long l_orderId = new Long(l_orderUnitRow.getOrderId());
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        OrderOpenStatusEnum l_orderOpenStatus = l_orderUnitRow.getOrderOpenStatus();
        
        //�Q�j�@@���[���G���W���T�[�o�ւ̒ʒm�v�ۃ`�F�b�N
        //�Q�|�P�j�@@�蓮���������A������s��������R�[�����ꂽ�ꍇ�A
        //�i�P)�̖߂�l.���N�G�X�g�^�C�v == "����" ����
        //�p�����[�^.���� == ("ORDER_EXPIRED", 
        //"CANCEL_ORDER_REJECTED_BY_MKT")�j
        //�@@�ȍ~�̃`�F�b�N�͎��{���Ȃ��B
        if ((OrderManagerPersistenceContext.ORDER_EXPIRED.equals(l_context)
            ||OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT.equals(l_context))
            && WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            // ��������
        }
        else
        {
            //�Q�|�Q�j�@@�@@�֖ؑ��ς�W�w�l�������ǂ����̔���
            //�֖ؑ��ς�W�w�l�����łȂ��ꍇ�A
            //�i(�P�j�̖߂�l.�������� == "W�w�l" ����
            //�P�j�̖߂�l.���N�G�X�g�^�C�v == "DEFAULT")�ȊO�j
            //�����ΏۊO�ł���ׁA�������I������B�ireturn�j
            if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType())
                || (!WEB3RequestTypeDef.DEFAULT.equals(l_strRequestType)))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }

            //�@@�Q�|�R�j�@@�������̔����x���������ǂ����̔���
            //�@@�@@OP�����}�l�[�W��.is�������x������() == true�̏ꍇ�A
            //�@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn)
            if (this.isNotOrderedDelay(l_ifoOrderUnit))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
        }

        //�R�j�@@�⏕�������擾����B
        //�@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //�S�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j 
        //�@@���擾����B 
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        
        //�T�j�@@���[���G���W���T�[�o�ɒʒm���s���B 
        //�@@�����̏����A�����P��.������Ԃɂ���ĉ��L����������s���B 
        //�T�|�P�j�@@���
        //�����̏��� == "ORDER_EXPIRED"�i�X�g�b�v���������j�A
        //"ORDER_INVALIDATED_BY_MKT"�i�����j�A
        //"CANCEL_ORDER_REJECTED_BY_MKT"�i������s�j�A
        //�܂��͎����t�ρi�������="��t�ρi��������j"�j�̏ꍇ�A
        //�܂��͎���m��A�S�����A������t�G���[�i�����L�����="�N���[�Y"�j�̏ꍇ�A 
        //�擾�����T�[�r�X.sendCancelConOrderMessage()���\�b�h��
        //�R�[������B
        if (OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT.equals(l_context)
            || OrderManagerPersistenceContext.ORDER_EXPIRED.equals(l_context)
            || OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT.equals(l_context)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus))
        {
            //�@@�@@�擾�����T�[�r�X.sendCancelConOrderMessage()���\�b�h��
            //�@@�@@�R�[������B
            //�@@�@@[sendCancelConOrderMessage()�Ɏw�肷�����]
            //�@@�@@�@@�⏕�����F�@@�擾�����⏕����
            //�@@�@@�@@�����t�����^�C�v�F�@@"W�w�l"
            //�@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����"
            //�@@�@@�@@����ID�F�@@�P�j�̖߂�l.����ID
            l_rlsRequestSenderService.sendCancelConOrderMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.IFO,
                l_orderId);
        }
        //�T�|�Q�j�@@�V�K�o�^�i�������="��t�ρi�V�K�����j"�j�̏ꍇ
        //�@@�擾�����T�[�r�X.sendRegisterConOrdersMessage()���\�b�h��
        //�@@�R�[������B
        //�@@[sendRegisterConOrdersMessage()�Ɏw�肷�����]
        //�@@�@@�⏕�����F�@@�擾�����⏕����
        //�@@�@@�����t�����^�C�v�F�@@"W�w�l"
        //�@@�@@�e�����̖����^�C�v�F�@@"�敨�I�v�V����"
        //�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID
        //�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null
        //�@@�@@�q�����̒���ID�ꗗ�F�@@null
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendRegisterConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.IFO,
                l_orderId,
                null,
                null);
        }
        //�T�|�R�j�@@��������
        //�@@�i�������="��t�ρi�ύX�����j" or "�����ρi�ύX�����j"�j�̏ꍇ
        //�@@�擾�����T�[�r�X.sendModifyConOrdersMessage()���\�b�h��
        //�@@�R�[������B
        //�@@���s�ꑗ�M�ϒ����^�s�ꖢ���M�����̒������l���B
        //�@@�@@[sendModifyConOrdersMessage()�Ɏw�肷�����]
        //�@@�@@�@@�⏕�����F�@@�擾�����⏕����
        //�@@�@@�@@�����t�����^�C�v�F�@@"W�w�l"
        //�@@�@@�@@�����^�C�v�F�@@"�敨�I�v�V����"
        //�@@�@@�@@����ID�F�@@�P�j�̖߂�l.����ID
        //�@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null 
        //�@@�@@�@@�q�����̒���ID�ꗗ�F�@@null
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFIED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendModifyConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.IFO,
                l_orderId,
                null,
                null);
        }

        log.exiting(STR_METHOD_NAME);
    }
     
    /**
     * (is�X�g�b�v�����L��)<BR>
     * W�w�l�����̃X�g�b�v�������L�����ǂ������ʂ���B <BR>
     * <BR>
     * this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B <BR>
     * <BR>
     * [is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@���������F�@@��������(*1) <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v <BR>
     * <BR>
     * (*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����B<BR> 
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderValid(IfoOrderUnit l_ifoOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderValid(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        IfoOrderUnitRow l_ifoOrderUnitRow = 
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        
        //(*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����B
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderUnitRow.getOrderConditionType(),
                l_ifoOrderUnitRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderOpen = 
            this.isStopOrderValid(
                l_strOrderConditionType, l_ifoOrderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderOpen;
    }
     
    /**
     * (is�X�g�b�v�����L��)<BR>
     * W�w�l�����̃X�g�b�v�������L�����ǂ������ʂ���B <BR>
     * <BR>
     * this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B <BR>
     * <BR>
     * [is�X�g�b�v�����L��()�Ɏw�肷�����] <BR>
     * �@@���������F�@@��������(*1) <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.��������.���N�G�X�g�^�C�v <BR>
     * <BR>
     * (*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����B<BR> 
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.����������<BR>
     * @@param l_ifoOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderValid(IfoOrderAction l_ifoOrderAction) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderValid(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        IfoOrderActionRow l_ifoOrderActionRow = 
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        
        // (*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderActionRow.getOrderConditionType(),
                l_ifoOrderActionRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderOpen = 
            this.isStopOrderValid(
                l_strOrderConditionType, l_ifoOrderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderOpen;
    }
     
    /**
     * (is�X�g�b�v�����L��)<BR>
     * W�w�l�����̃X�g�b�v�������L�����ǂ������ʂ���B<BR> 
     * ���������� == "W�w�l"�łȂ��ꍇ�́A <BR>
     * �@@�ꗥfalse��ԋp����B <BR>
     * <BR>
     * �P�j�@@W�w�l���ǂ����̃`�F�b�N <BR>
     * �@@�p�����[�^.�������� != "W�w�l"�̏ꍇ�A <BR>
     * �@@false��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�X�g�b�v�������L�����ǂ����̃`�F�b�N <BR>
     * �@@�p�����[�^.���N�G�X�g�^�C�v == "�ؑ֊���"�̏ꍇ�A<BR> 
     * �@@true��ԋp����B <BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * @@param l_strOrderCondition - (��������)<BR>
     * ��������<BR>
     * @@param l_strRequestType - (���N�G�X�g�^�C�v)<BR>
     * ���N�G�X�g�^�C�v<BR>
     * @@return boolean 
     */
    private boolean isStopOrderValid(
        String l_strOrderCondition, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderValid(String, String)";
        log.entering(STR_METHOD_NAME);        
        
        //�P�j�@@W�w�l���ǂ����̃`�F�b�N
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�X�g�b�v�������L�����ǂ����̃`�F�b�N 
        if (WEB3RequestTypeDef.TRANSFERED.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
     
    /**
     * (is�X�g�b�v�����ؑ֒�)<BR>
     * W�w�l�������X�g�b�v�����ɐؑ֒����ǂ����𔻕ʂ���B<BR> 
     * <BR>
     * this.is�X�g�b�v�����ؑ֒�()�ɏ������Ϗ�����B<BR> 
     * <BR>
     * [is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] <BR>
     * �@@���������F�@@��������(*1) <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v <BR>
     * <BR>
     * (*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderSwitching(IfoOrderUnit l_ifoOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        IfoOrderUnitRow l_ifoOrderUnitRow = 
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        
        //(*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����B
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderUnitRow.getOrderConditionType(),
                l_ifoOrderUnitRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderChanging = 
            this.isStopOrderSwitching(
                l_strOrderConditionType, l_ifoOrderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderChanging;
    }
     
    /**
     * (is�X�g�b�v�����ؑ֒�)<BR>
     * W�w�l�������X�g�b�v�����ɐؑ֒����ǂ����𔻕ʂ���B <BR>
     * <BR>
     * this.is�X�g�b�v�����ؑ֒�()�ɏ������Ϗ�����B <BR>
     * <BR>
     * [is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����] <BR>
     * �@@���������F�@@��������(*1) <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.��������.���N�G�X�g�^�C�v <BR>
     * <BR>
     * (*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����] <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.�������� <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.����������<BR>
     * @@param l_ifoOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return boolean 
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderSwitching(IfoOrderAction l_ifoOrderAction) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        IfoOrderActionRow l_ifoOrderActionRow = 
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        
        // (*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderActionRow.getOrderConditionType(),
                l_ifoOrderActionRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderChanging = 
            this.isStopOrderSwitching(
                l_strOrderConditionType, l_ifoOrderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderChanging;
    }
     
    /**
     * (is�X�g�b�v�����ؑ֒�)<BR>
     * W�w�l�������X�g�b�v�����ɐؑ֒����ǂ����𔻕ʂ���B <BR>
     * ���������� == "W�w�l"�łȂ��ꍇ�́A <BR>
     * �@@�ꗥfalse��ԋp����B <BR>
     * <BR>
     * �P�j�@@W�w�l���ǂ����̃`�F�b�N <BR>
     * �@@�p�����[�^.�������� != "W�w�l"�̏ꍇ�A <BR>
     * �@@false��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�X�g�b�v�����ɐؑ֒����ǂ����̃`�F�b�N <BR>
     * �@@�p�����[�^.���N�G�X�g�^�C�v == "�����T�[�o"�̏ꍇ�A <BR>
     * �@@true��ԋp����B <BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * @@param l_strOrderCondition - (��������)<BR>
     * ��������<BR>
     * @@param l_strRequestType - (���N�G�X�g�^�C�v)<BR>
     * ���N�G�X�g�^�C�v<BR>
     * @@return boolean 
     */
    private boolean isStopOrderSwitching(
        String l_strOrderCondition, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@W�w�l���ǂ����̃`�F�b�N
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�X�g�b�v�������L�����ǂ����̃`�F�b�N 
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
     
    /**
     * (is�X�g�b�v����������)<BR>
     * W�w�l�����̃X�g�b�v�����������ς��ǂ������ʂ���B<BR> 
     * <BR>
     * this.is�X�g�b�v����������()�ɏ������Ϗ�����B<BR> 
     * <BR>
     * [is�X�g�b�v����������()�Ɏw�肷�����] <BR>
     * �@@���������F�@@��������(*1) <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v <BR>
     * <BR>
     * (*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����B<BR> 
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR> 
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������<BR> 
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return boolean  
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderExpired(IfoOrderUnit l_ifoOrderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        IfoOrderUnitRow l_ifoOrderUnitRow = 
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        
        //(*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����B
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderUnitRow.getOrderConditionType(),
                l_ifoOrderUnitRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderExpired = 
            this.isStopOrderExpired(
                l_strOrderConditionType, l_ifoOrderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderExpired;
    }
     
    /**
     * (is�X�g�b�v����������)<BR>
     * W�w�l�����̃X�g�b�v�����������ς��ǂ������ʂ���B <BR>
     * <BR>
     * this.is�X�g�b�v����������()�ɏ������Ϗ�����B <BR>
     * <BR>
     * [is�X�g�b�v����������()�Ɏw�肷�����] <BR>
     * �@@���������F�@@��������(*1) <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.��������.���N�G�X�g�^�C�v <BR>
     * <BR>
     * (*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����B<BR> 
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR> 
     * �@@�@@���������F�@@�p�����[�^.��������.��������<BR> 
     * �@@�@@�����������F�@@�p�����[�^.��������.����������<BR>
     * @@param l_ifoOrderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return boolean  
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderExpired(IfoOrderAction l_ifoOrderAction) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(IfoOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ifoOrderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        IfoOrderActionRow l_ifoOrderActionRow = 
            (IfoOrderActionRow)l_ifoOrderAction.getDataSourceObject();
        
        // (*1)���������́A�敨OP�f�[�^�A�_�v�^.get��������()�ɂ��擾����
        String l_strOrderConditionType =
            WEB3IfoDataAdapter.getOrderConditionType(
                l_ifoOrderActionRow.getOrderConditionType(),
                l_ifoOrderActionRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderExpired = 
            this.isStopOrderExpired(
                l_strOrderConditionType, l_ifoOrderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderExpired;
    } 
     
    /**
     * (is�X�g�b�v����������)<BR>
     * W�w�l�����̃X�g�b�v�����������ς��ǂ������ʂ���B<BR> 
     * ���������� == "W�w�l"�łȂ��ꍇ�́A <BR>
     * �@@�ꗥfalse��ԋp����B <BR>
     * <BR>
     * �P�j�@@W�w�l���ǂ����̃`�F�b�N <BR>
     * �@@�p�����[�^.�������� != "W�w�l"�̏ꍇ�A <BR>
     * �@@false��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�X�g�b�v�����������ς��ǂ����̃`�F�b�N <BR>
     * �@@�p�����[�^.���N�G�X�g�^�C�v == "����"�̏ꍇ�A<BR> 
     * �@@true��ԋp����B <BR>
     * �@@�ȊO�Afalse��ԋp����B<BR> 
     * @@param l_strOrderCondition - (��������)<BR>
     * ��������<BR>
     * @@param l_strRequestType - (���N�G�X�g�^�C�v)<BR>
     * ���N�G�X�g�^�C�v<BR>
     * @@return boolean 
     */
    private boolean isStopOrderExpired(
        String l_strOrderCondition, String l_strRequestType) 
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@W�w�l���ǂ����̃`�F�b�N
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCondition))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�X�g�b�v�������L�����ǂ����̃`�F�b�N 
        if (WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
     
    /**
     * (is�蓮�����\)<BR>
     * �������蓮�����\�ł��邩�𔻕ʂ���B<BR>
     * �����������w��Ȃ��̏ꍇ�́A�ꗥfalse��ԋp����B<BR>
     * <BR>
     * �P�j�g���K�[�������ǂ����̃`�F�b�N<BR>
     * <BR>
     * �@@�p�����[�^.�����P��.�������� == "DEFAULT(�����w��Ȃ�)"�̏ꍇ�A<BR>
     * �@@false��ԋp����B<BR>
     * �@@(�蓮�����ΏۊO)<BR>
     * <BR>
     * �Q�j�t�w�l�����̏ꍇ(�p�����[�^.�����P��.�������� == "�t�w�l")<BR>
     * <BR>
     * �@@�y�����҂��^�����x���z<BR>
     * �@@�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v == "DEFAULT"�A���A<BR>
     * �@@�@@�p�����[�^.�����P��.�����L����� == "�I�[�v��"�A���A <BR>
     * �@@�@@������ԊǗ�.is����ԑ�() == true�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �@@�������x���i�����x�������łȂ���Ёj�̏ꍇ���A��L��ԂɊY������B<BR>
     * �@@�@@�����x�������̉�Ђ͔����x�����ɂ͎蓮�����s�B <BR>
     * �@@�@@(�����ςł����) <BR>
     * <BR>
     * �R�jW�w�l�����̏ꍇ(�p�����[�^.�����P��.�������� == "W�w�l")<BR>
     * <BR>
     * �y�ؑ֒x���z <BR>
     * �@@this.is�������x������(�p�����[�^.�����P��) == true�A���A<BR>
     * �@@�p�����[�^.�����P��.�����L����� == "�I�[�v��"�A���A <BR>
     * �@@�p�����[�^.�����P��.������� != <BR>
     * �@@�@@�i"��t�ρi��������j"�A"�������i��������j")�A���A <BR>
     * �@@�@@������ԊǗ�.is����ԑ�() == true�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isManualOrderPossible(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isManualOrderPossible(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }

        //�P�j�g���K�[�������ǂ����̃`�F�b�N
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        String l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();
        String l_strRequestType = l_ifoOrderUnitRow.getRequestType();
        OrderOpenStatusEnum l_orderOpenStatus = l_ifoOrderUnitRow.getOrderOpenStatus();
        OrderStatusEnum l_orderStatus = l_ifoOrderUnitRow.getOrderStatus();

        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Q�j�t�w�l�����̏ꍇ(�p�����[�^.�����P��.�������� == "�t�w�l")
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //   �����҂�/�����x��
            //�@@�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v == "DEFAULT"�A���A
            //�@@�@@�p�����[�^.�����P��.�����P��.�����L����� == "�I�[�v��"�A���A
            //  ������ԊǗ�.is����ԑ�() == true�̏ꍇ�Atrue��ԋp����B
            if (WEB3RequestTypeDef.DEFAULT.equals(l_strRequestType)
                && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatus)
                && WEB3GentradeTradingTimeManagement.isSessionTimeZone())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //�R�jW�w�l�����̏ꍇ(�p�����[�^.�����P��.�������� == "W�w�l")
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {

            //�@@this.is�������x������(�p�����[�^.�����P��) == true�A���A
            //�@@�p�����[�^.�����P��.�����P��.�����L����� == "�I�[�v��"�A���A
            //�@@�p�����[�^.�����P��.������� != �i"��t�ρi��������j"�A"�������i��������j")�A���A
            //������ԊǗ�.is����ԑ�() == true�̏ꍇ�Atrue��ԋp����B
            if (this.isNotOrderedDelay(l_ifoOrderUnit)
                && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatus)
                && !OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
                && !OrderStatusEnum.CANCELLING.equals(l_orderStatus)
                && WEB3GentradeTradingTimeManagement.isSessionTimeZone())
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //�ȊO�Afasle��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (update�ؑ֒x��)<BR>
     * �iupdateSwitchDelay�j <BR>
     * �w�肳�ꂽ����ID�ɕR�t�������f�[�^�ނ�ؑ֒x���̃X�e�[�^�X�ɍX�V����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.����ID�ɕR�t�������P�ʂ��擾����B<BR>
     * <BR>
     * �Q�j�@@������ԃ`�F�b�N <BR>
     * �@@�����P��.���N�G�X�g�^�C�v == "DEFAULT" ����<BR>
     * �@@�i�s�ꖢ���M(*)�̏ꍇ�A�܂��́A<BR>
     * �@@�擾���������P��.������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�j�A<BR>
     * �@@�u��t���^�������^������̒����͐ؑ֏����s�v�̗�O���X���[����B<BR>
     * �@@�@@�E"��t�ρi�ύX�����j" <BR>
     * �@@�@@�E"�������i�ύX�����j" <BR>
     * �@@�@@�E"��t�ρi��������j" <BR>
     * �@@�@@�E"�������i��������j" <BR>
     * �@@�@@class:WEB3BusinessLayerException<BR>
     * �@@�@@tag  :BUSINESS_ERROR_02521<BR>
     * <BR>
     * �@@�@@(*)�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�s�ꖢ���M�̒����Ɣ��肷��B<BR>
     * <BR>
     * �R�j�@@�P�j�̒����P�ʂ�clone���쐬����B  <BR>
     * <BR>
     * �S�j�@@�R�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B  <BR>
     * �@@DB�X�V�d�l  <BR>
     * �@@�uW�w�l�����ؑցi�ؑ֒x���j_�����P�ʃe�[�u��.xls�v��
     * �@@�@@�iW�w�l�����j[�ؑ֒x��]�����P��ð��فv�Q�ƁB<BR>
     * <BR>
     * �T�j�@@�����f�[�^��update����B  <BR>
     * �@@this.update�����f�[�^()���R�[������B <BR>
     * <BR>
     * �@@[update�����f�[�^()�Ɏw�肷�����]  <BR>
     * �@@�@@�����P�ʁF�@@�S�j�ɂč쐬���������P��  <BR>
     * �@@�@@is�����쐬�F�@@false�i�쐬���Ȃ��j <BR>
     * <BR>
     * �U�j�@@�S�j�ɂč쐬���������P�ʂ��g�p�� <BR>
     * �@@�@@���������e�[�u���ւP���R�[�hinsert����B <BR>
     * �@@�@@�X�V���e�́ADB�X�V�d�l <BR>
     * �@@�@@�uW�w�l�����ؑցi�ؑ֒x���j_���������e�[�u���d�l.xls�v�Q�ƁB<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateSwitchDelay(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSwitchDelay(long)";
        log.entering(STR_METHOD_NAME);
         
        OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);
        if (l_orderUnits.length == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        IfoOrderUnit l_ifoOrderUnit =
            (IfoOrderUnit)this.getOrderUnits(l_lngOrderId)[0];
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        //�Q�j�@@������ԃ`�F�b�N
        OrderStatusEnum l_orderStatus = l_ifoOrderUnit.getOrderStatus();

        if ((WEB3RequestTypeDef.DEFAULT.equals(l_ifoOrderUnitRow.getRequestType())
            && (l_ifoOrderUnitRow.getConfirmedQuantityIsNull()
            || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatus)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatus))))
        {
            log.debug("��t���^�������^������̒����͐ؑ֏����s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02521,
                getClass().getName() + STR_METHOD_NAME,
                "��t���^�������^������̒����͐ؑ֏����s�B");
        }

        //�S�j�@@�R�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams(l_ifoOrderUnitRow);

        Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();

        l_ifoOrderUnitParams.setLastOrderActionSerialNo(
            l_ifoOrderUnitRow.getLastOrderActionSerialNo() + 1);
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(l_tsSysTime);
        l_ifoOrderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);

        IfoOrderUnit l_ifoOrderUnitClone =
            (IfoOrderUnit)this.toOrderUnit(l_ifoOrderUnitParams);

        //�T�j�@@�����f�[�^��update����B
        this.updateOrderData(l_ifoOrderUnitClone, false);

        //�U�j�@@�S�j�ɂč쐬���������P�ʂ��g�p��
        IfoOrderActionParams l_ifoOrderActionParams = new IfoOrderActionParams();

        //���������h�c
        long l_lngOrderActionId = 0;
        try
        {
            l_lngOrderActionId = IfoOrderActionDao.newPkValue();
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

        l_ifoOrderActionParams.setOrderActionId(l_lngOrderActionId);

        //�����h�c
        l_ifoOrderActionParams.setAccountId(l_ifoOrderUnitParams.getAccountId());

        //�⏕�����h�c
        l_ifoOrderActionParams.setSubAccountId(l_ifoOrderUnitParams.getSubAccountId());

        //�����h�c
        l_ifoOrderActionParams.setOrderId(l_ifoOrderUnitParams.getOrderId());

        //�����P�ʂh�c
        l_ifoOrderActionParams.setOrderUnitId(l_ifoOrderUnitParams.getOrderUnitId());

        //�s��h�c
        if (!l_ifoOrderUnitParams.getMarketIdIsNull())
        {
            l_ifoOrderActionParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
        }

        //�������
        l_ifoOrderActionParams.setOrderType(l_ifoOrderUnitParams.getOrderType());

        //�����C�x���g�^�C�v
        l_ifoOrderActionParams.setOrderEventType(OrderEventTypeEnum.SWITCH_DELAY);

        //�����P��
        if (!l_ifoOrderUnitParams.getLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setPrice(l_ifoOrderUnitParams.getLimitPrice());
        }

        //���s����
        l_ifoOrderActionParams.setExecutionConditionType(
            l_ifoOrderUnitParams.getExecutionConditionType());

        //��������
        l_ifoOrderActionParams.setOrderConditionType(
            l_ifoOrderUnitParams.getOrderConditionType());

        //�����������Z�q
        l_ifoOrderActionParams.setOrderCondOperator(
            l_ifoOrderUnitParams.getOrderCondOperator());

        //�t�w�l��l�^�C�v
        l_ifoOrderActionParams.setStopPriceType(l_ifoOrderUnitParams.getStopPriceType());

        //�t�w�l��l
        if (!l_ifoOrderUnitParams.getStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setStopOrderPrice(
                l_ifoOrderUnitParams.getStopOrderPrice());
        }

        //�iW�w�l�j�����w�l
        if (!l_ifoOrderUnitParams.getWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitPrice(l_ifoOrderUnitParams.getWLimitPrice());
        }

        //�����������t
        l_ifoOrderActionParams.setExpirationDate(l_ifoOrderUnitParams.getExpirationDate());

        //��������
        l_ifoOrderActionParams.setQuantity(l_ifoOrderUnitParams.getQuantity());

        //�s��Ɗm�F�ς݂̎w�l
        if (!l_ifoOrderUnitParams.getConfirmedPriceIsNull())
        {
            l_ifoOrderActionParams.setConfirmedPrice(l_ifoOrderUnitParams.getConfirmedPrice());
        }

        //�s��Ɗm�F�ς݂̐���
        if (!l_ifoOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_ifoOrderActionParams.setConfirmedQuantity(
                l_ifoOrderUnitParams.getConfirmedQuantity());
        }

        //��萔��
        l_ifoOrderActionParams.setExecutedQuantity(null);

        //�������
        l_ifoOrderActionParams.setOrderStatus(l_ifoOrderUnitParams.getOrderStatus());

        //���������X�e�[�^�X
        l_ifoOrderActionParams.setExpirationStatus(l_ifoOrderUnitParams.getExpirationStatus());

        //��������ԍ�
        l_ifoOrderActionParams.setOrderActionSerialNo(l_ifoOrderUnitParams.getLastOrderActionSerialNo());

        //���P��
        l_ifoOrderActionParams.setExecutedPrice(null);

        //�����^�C�v
        l_ifoOrderActionParams.setProductType(l_ifoOrderUnitParams.getProductType());

        //�����h�c
        l_ifoOrderActionParams.setProductId(l_ifoOrderUnitParams.getProductId());

        //�T�Z��n���
        if (!l_ifoOrderUnitParams.getEstimatedPriceIsNull())
        {
            l_ifoOrderActionParams.setEstimatedPrice(
                l_ifoOrderUnitParams.getEstimatedPrice());
        }

        //���������E����敪
        l_ifoOrderActionParams.setModifyCancelType(
            l_ifoOrderUnitParams.getModifyCancelType());

        //���Ϗ���
        l_ifoOrderActionParams.setClosingOrder(l_ifoOrderUnitParams.getClosingOrder());

        //�����G���[���R�R�[�h
        l_ifoOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //���N�G�X�g�^�C�v
        l_ifoOrderActionParams.setRequestType(l_ifoOrderUnitParams.getRequestType());

        //�����ID
        if (!l_ifoOrderUnitParams.getTraderIdIsNull())
        {
            l_ifoOrderActionParams.setTraderId(l_ifoOrderUnitParams.getTraderId());
        }

        //�����o�H�敪
        l_ifoOrderActionParams.setOrderRootDiv(l_ifoOrderUnitParams.getOrderRootDiv());

        //�쐬���t
        l_ifoOrderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //�X�V���t
        l_ifoOrderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


        //����������
        l_ifoOrderActionParams.setOrgOrderConditionType(
            l_ifoOrderUnitParams.getOrgOrderConditionType());

        //�������������Z�q
        l_ifoOrderActionParams.setOrgOrderCondOperator(
            l_ifoOrderUnitParams.getOrgOrderCondOperator());

        //���t�w�l��l�^�C�v
        l_ifoOrderActionParams.setOrgStopPriceType(l_ifoOrderUnitParams.getOrgStopPriceType());

        //���t�w�l��l
        if (!l_ifoOrderUnitParams.getOrgStopOrderPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgStopOrderPrice(l_ifoOrderUnitParams.getOrgStopOrderPrice());
        }

        //���iW�w�l�j�����w�l
        if (!l_ifoOrderUnitParams.getOrgWLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setOrgWLimitPrice(l_ifoOrderUnitParams.getOrgWLimitPrice());
        }

        //���iW�w�l�j���s����
        l_ifoOrderActionParams.setOrgWLimitExecCondType(l_ifoOrderUnitParams.getOrgWLimitExecCondType());

        //�iW�w�l�j���s����
        l_ifoOrderActionParams.setWLimitExecCondType(l_ifoOrderUnitParams.getWLimitExecCondType());

        //�iW�w�l�j�֑ؑO�w�l
        if (!l_ifoOrderUnitParams.getWLimitBeforeLimitPriceIsNull())
        {
            l_ifoOrderActionParams.setWLimitBeforeLimitPrice(
                l_ifoOrderUnitParams.getWLimitBeforeLimitPrice());
        }

        //�iW�w�l�j�֑ؑO���s����
        l_ifoOrderActionParams.setWLimitBeforeExecCondType(
            l_ifoOrderUnitParams.getWLimitBeforeExecCondType());

        //�s�ꂩ��m�F�ς݂̎��s����
        l_ifoOrderActionParams.setConfirmedExecConditionType(
            l_ifoOrderUnitParams.getConfirmedExecConditionType());

        //���������敪
        l_ifoOrderActionParams.setExpirationDateType(
            l_ifoOrderUnitParams.getExpirationDateType());
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_ifoOrderActionParams);
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
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (isSONAR���)<BR>
     * SONAR����̎���ʒm���ǂ������ʂ���B<BR> 
     * <BR>
     * SONAR����̎���ʒm�̏ꍇ��true���A <BR>
     * �ȊO�Afalse��ԋp����B <BR>
     * <BR>
     * �p�����[�^.�����P��.������Ԃ��ȉ��̂����ꂩ�ɊY������ <BR>
     * �ꍇ�Afalse��ԋp����B <BR>
     * �@@�E"��t�ρi��������j" <BR>
     * �@@�E"�������i��������j" <BR>
     * <BR>
     * ��L�ȊO�Atrue��ԋp����B <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isSONARCancel(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSONARCancel(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
         
        if (l_ifoOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
         
        IfoOrderUnitRow l_ifoOrderUnitRow = 
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
         
        //"��t�ρi��������j"  "�������i��������j"
        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_ifoOrderUnitRow.getOrderStatus())
            || OrderStatusEnum.CANCELLING.equals(l_ifoOrderUnitRow.getOrderStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
         
        //��L�ȊO�Atrue��ԋp����
        log.exiting(STR_METHOD_NAME);
        return true;
    }
     
    /**
     * Commission�̃N���[��
     * @@param l_commission
     * @@return WEB3GentradeCommission
     */
    private WEB3GentradeCommission copyCommission(WEB3GentradeCommission l_commission)
    {
        WEB3GentradeCommission l_cloneCommission = new WEB3GentradeCommission();
        l_cloneCommission.setBranchId(l_commission.getBranchId());
        l_cloneCommission.setCommission(l_commission.getCommission());
        l_cloneCommission.setCommissionCourseDiv(l_commission.getCommissionCourseDiv());
        l_cloneCommission.setCommissionNo(l_commission.getCommissionNo());
        l_cloneCommission.setCommissionProductCode(l_commission.getCommissionProductCode());
        l_cloneCommission.setCommissionRevNo(l_commission.getCommissionRevNo());
        l_cloneCommission.setCommitionPerUnit(l_commission.getCommitionPerUnit());
        l_cloneCommission.setDayTradeType(l_commission.getDayTradeType());
        l_cloneCommission.setExpensesCalcAmount(l_commission.getExpensesCalcAmount());
        l_cloneCommission.setInstitutionCode(l_commission.getInstitutionCode());
        l_cloneCommission.setIsLimitPrice(l_commission.isLimitPrice());
        l_cloneCommission.setMinCommission(l_commission.getMinCommission());
        l_cloneCommission.setOrderBizDate(l_commission.getOrderBizDate());
        l_cloneCommission.setOrderChannel(l_commission.getOrderChannel());
        l_cloneCommission.setOrgCommissionNo(l_commission.getOrgCommissionNo());
        l_cloneCommission.setOrgCommissionRevNo(l_commission.getOrgCommissionRevNo());
        l_cloneCommission.setOrgOrderChannel(l_commission.getOrgOrderChannel());
        l_cloneCommission.setPayType(l_commission.getPayType());
        l_cloneCommission.setQuantity(l_commission.getQuantity());
        l_cloneCommission.setSonarMarketCode(l_commission.getSonarMarketCode());
        l_cloneCommission.setSonarTradedCode(l_commission.getSonarTradedCode());
        l_cloneCommission.setUnderlyingProductCode(l_cloneCommission.getUnderlyingProductCode());
     
        return l_cloneCommission;        
    }

	/**
	 * (is�o����܂Œ����P��) <BR>
	 * (is�o����܂Œ����P��(�����P��)�̃I�[�o�[���[�h���\�b�h) <BR>
	 * <BR>
	 * �u�o����܂Œ����v�̒������ǂ����𔻒肷��B<BR>
	 * �u�o����܂Œ����v�̏ꍇ��true���A�u�o����܂Œ����v�ł͂Ȃ��ꍇ��false���A<BR>
	 * ���ꂼ��ԋp����B <BR>
	 * <BR>
	 * �P�j�@@this.getOrderUnit(����.�����P��ID)�ŁA�����P�ʃI�u�W�F�N�g���擾����B<BR>
	 * <BR>
	 * �Q�j�@@this.is�o����܂Œ����P��(�����P��)��delegate����B<BR>
	 * <BR>   
	 * @@param l_lngOrderUnitId - (�����P��ID) <BR>
	 * �����P�ʃI�u�W�F�N�g.�����P��ID�B<BR>
	 * @@return boolean
	 * @@throws WEB3BaseException
	 */
	public boolean isCarriedOrderUnit(long l_lngOrderUnitId)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isCarriedOrderUnit(long l_lngOrderUnitId)";
		log.entering(STR_METHOD_NAME);
        
		IfoOrderUnit l_orderUnit;
		try
		{
			l_orderUnit = (IfoOrderUnit)getOrderUnit(l_lngOrderUnitId);
		}
		catch (NotFoundException nfe)
		{
			log.error(STR_METHOD_NAME,nfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				nfe.getMessage(),
				nfe);
		}
        
		log.exiting(STR_METHOD_NAME);
        
		return this.isCarriedOrderUnit(l_orderUnit);
	}

	/**
	 * (is�o����܂Œ����P��) <BR>
	 * <BR>
	 * �u�o����܂Œ����v�̒������ǂ����𔻒肷��B<BR>
	 * �u�o����܂Œ����v�̏ꍇ��true���A�u�o����܂Œ����v�ł͂Ȃ��ꍇ��false���A<BR>
	 * ���ꂼ��ԋp����B<BR>
	 * <BR>
	 * �P�j�@@�����̒����P��.���񒍕��̒����P��ID��null�@@���A<BR>
     * �@@�@@�@@OP�����}�l�[�W��.is�[��܂Œ���(�����̒����P��)��false�̏ꍇ�́Atrue��Ԃ��B<BR>
     * <BR>
	 * �Q�j�@@�����̒����P��.���񒍕��̒����P��ID��null�̏ꍇ�́Afalse��Ԃ��B<BR>
	 * <BR>
	 * @@param l_ifoOrderUnit - (�����P�ʃI�u�W�F�N�g) <BR>
	 * �����P�ʃI�u�W�F�N�g�B <BR>
	 * @@return boolean
	 * @@throws WEB3BaseException
	 */
	public boolean isCarriedOrderUnit(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isCarriedOrderUnit(IfoOrderUnit l_ifoOrderUnit)";
		log.entering(STR_METHOD_NAME);
        
		IfoOrderUnitRow l_orderUnitRow = 
			(IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        
		boolean l_blnResult = false;
		if (l_orderUnitRow.getFirstOrderUnitIdIsNull())
		{
			l_blnResult = false;
		}
		else if (!l_orderUnitRow.getFirstOrderUnitIdIsNull()
            && !this.isEveningSessionOrder(l_ifoOrderUnit))
		{
			l_blnResult = true;
		}
        
		log.exiting(STR_METHOD_NAME);

		return l_blnResult;
	}

    /**
     * (is�x������)<BR>
     * �x���������ǂ������ʂ���B<BR>
     * <BR>
     * true�F�@@�����x������<BR>
     * false�F�@@�����x���Ȃ�<BR>
     * <BR>
     * �p�����[�^.�����P��.�����x���t���O == "�x������"�̏ꍇ�A<BR>
     * true��ԋp����B�ȊO�Afalse��ԋp����B�@@<BR>
     * @@param l_ifoOrderUnit - (�����P��) <BR>
     * @@return boolean
     */
    public boolean isDelayOrder(IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME = "isDelayOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        if (BooleanEnum.TRUE.equals(l_ifoOrderUnitRow.getSubmitOrderDelayFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�������x������)<BR>
     * �������i�t�w�l���������^W�w�l�ؑ֏������ρj��<BR>
     * �����x���������ǂ������ʂ���B<BR>
     * <BR>
     * true�F�@@�������̔����x������<BR>
     * false�F�@@�������̔����x�������ȊO<BR>
     * <BR>
     * �p�����[�^.�����P��.���N�G�X�g�^�C�v == "DEFAULT"�@@����<BR>
     * OP�����}�l�[�W��.is�x������(�p�����[�^.�����P��) == true<BR>
     * �̏ꍇ�Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@param l_ifoOrderUnit - (�����P��) <BR>
     * @@return boolean
     */
    public boolean isNotOrderedDelay(IfoOrderUnit l_ifoOrderUnit)
    {
        final String STR_METHOD_NAME = "isNotOrderedDelay(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        if (WEB3RequestTypeDef.DEFAULT.equals(l_ifoOrderUnitRow.getRequestType())
            && this.isDelayOrder(l_ifoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (get�X�g�b�v�����������T�Z����v�Z����)<BR>
     * �X�g�b�v�����������̊T�Z��n������Čv�Z����B <BR>
     * �i���~�b�g�����̒����P���ɂ��v�Z���s���j <BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P��.������� != "OP�V�K��������"�̏ꍇ�A<BR>
     * �@@null��ԋp����B <BR>
     * <BR>
     * �Q�j�@@��L�ȊO�A�ȉ��̏������s���B <BR>
     * �@@�Q�|�P�j�@@�敨OP�v�Z�T�[�r�X.create�萔��()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[create�萔��()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�����P��ID�F�@@�p�����[�^.�����P��.�����P��ID <BR>
     * �@@�@@�@@���ʁF�@@�p�����[�^.�����P��.�������� <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����P��Row���擾����B <BR>
     * �@@�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B <BR>
     * <BR>
     * �@@�Q�|�R�j�@@�萔��.setIs�w�l()���\�b�h���R�[������ <BR>
     * <BR>
     * �@@�@@[setIs�w�l()�Ɏw�肷�����] <BR>
     * �@@�@@�@@is�w�l�F <BR>
     * �@@�@@�@@�@@[�����P��Row.�s�ꂩ��m�F�ς݂̎w�l == null�̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@�����P��.isMarketOrder()�̖߂�l <BR>
     * �@@�@@�@@�@@[�����P��Row.�s�ꂩ��m�F�ς݂̎w�l == 0�̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@false�i���s�j <BR>
     * �@@�@@�@@�@@[��L�ȊO] <BR>
     * �@@�@@�@@�@@�@@true�i�w�l�j <BR>
     * <BR>
     * �@@�Q�|�S�j�@@this.calc�������T�Z��n���()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@[calc�������T�Z��n���()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�萔���F�@@�Q�|�P�j�ɂč쐬�����萔���I�u�W�F�N�g <BR>
     * �@@�@@�@@�w�l�F <BR>
     * �@@�@@�@@�@@[�����P��Row.�s�ꂩ��m�F�ς݂̎w�l == null�̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@�����P��Row.�w�l <BR>
     * �@@�@@�@@�@@[��L�ȊO] <BR>
     * �@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎w�l <BR>
     * �@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �@@�@@�@@�敨OP��������F�@@�����P��.getTradedProduct() <BR>
     * �@@�@@�@@���ʁF�@@�����P��Row.�������� <BR>
     * �@@�@@�@@�����F�@@�����P��.getSide() <BR>
     * �@@�@@�@@is�ԍϒ����F�@@false�i�Œ�j <BR>
     * �@@�@@�@@��萔�ʁF�@@�����P��Row.��萔�� <BR>
     * �@@�@@�@@���v�����z�F�@@�����P��Row.���v�����z <BR>
     * �@@�@@�@@isSkip���z�`�F�b�N�F�@@true�i�Œ�j <BR>
     * <BR>
     * �@@�Q�|�T�j�@@�Q�|�S�j�̖߂�l��ԋp����B <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@return WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException 
     */
    public WEB3IfoEstimateDeliveryAmountCalcResult getStopOrderExpireEstimatedPrice(
        IfoOrderUnit l_ifoOrderUnit, WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopOrderExpireEstimatedPrice(IfoOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //�P�j�@@�p�����[�^.�����P��.������� != "OP�V�K��������"�̏ꍇ�A
        //null��ԋp����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3IfoBizLogicProvider l_logicProvider =
            (WEB3IfoBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.IFO).getBizLogicProvider();

        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult;

        if (!OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_ifoOrderUnit.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�Q�j�@@��L�ȊO�A�ȉ��̏������s���B
        //�Q�|�P�j�@@�敨OP�v�Z�T�[�r�X.create�萔��()���\�b�h���R�[������B
        else
        {
            WEB3GentradeCommission l_genCommission =
                l_logicProvider.createCommission(
                    l_ifoOrderUnit.getOrderUnitId(),
                    l_ifoOrderUnit.getQuantity());

            //�Q�|�Q�j�@@�����P��Row���擾����B
            //�p�����[�^.�����P��.getDataSourceObject()���R�[������B
            IfoOrderUnitRow l_ifoRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

            //�@@�Q�|�R�j�@@�萔��.setIs�w�l()���\�b�h���R�[������
            boolean l_blnIsLimitPrice = true;
            //[�����P��Row.�s�ꂩ��m�F�ς݂̎w�l == null�̏ꍇ]
            // �����P��.isMarketOrder()�̖߂�l
            if (l_ifoRow.getConfirmedPriceIsNull())
            {
                l_blnIsLimitPrice = l_ifoOrderUnit.isMarketOrder();
            }
            else if (l_ifoRow.getConfirmedPrice() == 0)
            {
                l_blnIsLimitPrice = false;
            }
            l_genCommission.setIsLimitPrice(l_blnIsLimitPrice);

            //�Q�|�S�j�@@this.calc�������T�Z��n���()���\�b�h���R�[������B
            //�@@�@@[calc�������T�Z��n���()�Ɏw�肷�����]
            //�@@�@@�@@�萔���F�@@�Q�|�P�j�ɂč쐬�����萔���I�u�W�F�N�g
            //�@@�@@�@@�w�l�F
            //�@@�@@�@@�@@[�����P��Row.�s�ꂩ��m�F�ς݂̎w�l == null�̏ꍇ]
            //�@@�@@�@@�@@�@@�����P��Row.�w�l
            //�@@�@@�@@�@@[��L�ȊO]
            //�@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎w�l
            //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
            //�@@�@@�@@�敨OP��������F�@@�����P��.getTradedProduct()
            //�@@�@@�@@���ʁF�@@�����P��Row.��������
            //�@@�@@�@@�����F�@@�����P��.getSide()
            //�@@�@@�@@is�ԍϒ����F�@@false�i�Œ�j
            //�@@�@@�@@��萔�ʁF�@@�����P��Row.��萔��
            //�@@�@@�@@���v�����z�F�@@�����P��Row.���v�����z
            //�@@�@@�@@isSkip���z�`�F�b�N�F�@@true�i�Œ�j
            //�Q�|�T�j�@@�Q�|�S�j�̖߂�l��ԋp����B
            double l_dblLimitPrice = 0.0D;
            if (l_ifoRow.getConfirmedPriceIsNull())
            {
                l_dblLimitPrice = l_ifoRow.getLimitPrice();
            }
            else
            {
                l_dblLimitPrice = l_ifoRow.getConfirmedPrice();
            }

            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct =
                (WEB3IfoTradedProductImpl)l_ifoOrderUnit.getTradedProduct();

            l_estimateDeliveryAmountCalcResult =
                this.calcChangeEstimateDeliveryAmount(
                    l_genCommission,
                    l_dblLimitPrice,
                    l_subAccount,
                    l_futuresOptionTradedProduct,
                    l_ifoRow.getQuantity(),
                    l_ifoOrderUnit.getSide(),
                    false,
                    l_ifoRow.getExecutedQuantity(),
                    l_ifoRow.getExecutedAmount(),
                    true);
        }

        log.exiting(STR_METHOD_NAME);
        return l_estimateDeliveryAmountCalcResult;
    }

    /**
     * (is���v��)<BR>
     * ���ʂ����v��ł��邩���肷��B<BR>
     * <BR>
     * �P�j �����̕ԍό��ʃG���g��[0].ID���猚�ʃI�u�W�F�N�g�𐶐�����(*) <BR>
     * <BR>
     * �Q�j �P�j�Ŏ擾�������ʂ���A����.getTradedProduct()�ɂĐ敨OP����������擾����B <BR>
     * <BR>
     * �R�j �Q�j�Ŏ擾�����敨OP�����������A�敨OP�������.get��n��()�ɂĎ�n�����擾����B <BR>
     * <BR>
     * �S�j ������ԊǗ�.get������()���甭�������擾����B <BR>
     * <BR>
     * �T�j ���ʂ����v��ł��邩�̔���́A�ȉ��̂Ƃ���Ƃ���B <BR>
     * <BR>
     * �@@�@@�m����.getOpenDate() == �S�j�Ŏ擾���������� and ����.getDeliveryDate() == �R�j�Ŏ擾������n���n<BR>
     * <BR>
     * �@@�@@�@@�@@true��ԋp����B <BR>
     * <BR>
     * �@@�@@�m��L�ȊO�n<BR>
     * <BR>
     * �@@�@@�@@�@@false��ԋp����B <BR>
     * <BR>
     * (*) ����.�ԍό��ʃG���g���́A���Ϗ��ʁi�����j�Ń\�[�g����Ă��邱�Ƃ�O������Ƃ���B<BR>
     * @@param l_settleContractEntrys - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g��<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isDayTrade(SettleContractEntry[] l_settleContractEntrys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDayTrade(SettleContractEntry[])";
        log.entering(STR_METHOD_NAME);

        if (l_settleContractEntrys == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�����̕ԍό��ʃG���g��[0].ID���猚�ʃI�u�W�F�N�g�𐶐�����(*)
        long l_lngContractId = l_settleContractEntrys[0].getContractId();

        try
        {
            WEB3IfoContractImpl l_ifoContractImpl =
                new WEB3IfoContractImpl(l_lngContractId);

            //�Q�j�P�j�Ŏ擾�������ʂ���A����.getTradedProduct()�ɂĐ敨OP����������擾����B
            WEB3IfoTradedProductImpl l_ifoTradedProduct =
                (WEB3IfoTradedProductImpl)l_ifoContractImpl.getTradedProduct();

            //�R�j�Q�j�Ŏ擾�����敨OP�����������A�敨OP�������.get��n��()�ɂĎ�n�����擾����B
            Date l_datDeliveryDate = l_ifoTradedProduct.getDailyDeliveryDate();

            //�S�j ������ԊǗ�.get������()���甭�������擾����B
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //�m����.getOpenDate() == �S�j�Ŏ擾���������� and ����.getDeliveryDate() == �R�j�Ŏ擾������n���n
            //true��ԋp����B
            IfoContractRow l_ifoContractRow = (IfoContractRow)l_ifoContractImpl.getDataSourceObject();
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_ifoContractImpl.getOpenDate()) == 0
                && WEB3DateUtility.compareToDay(l_datDeliveryDate, l_ifoContractRow.getDeliveryDate()) == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
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

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get���v��敪)<BR>
     * ���ʂ����v��ł���΁A�u���v��敪�F���v��v��ԋp����(*1) <BR>
     * <BR>
     * �@@this.is���v��()���R�[������(*2) <BR>
     * <BR>
     * �@@�mthis.is���v��() == true�̏ꍇ�n<BR>
     * <BR>
     * �@@�@@�u���v��v��ԋp����B <BR>
     * <BR>
     * �@@�mthis.is���v��() == false�̏ꍇ�n<BR>
     * <BR>
     * �@@�@@null��ԋp����B <BR>
     * <BR>
     * (*1) ���v��敪�́A�u�萔��`�C���^�t�F�C�X�FWEB3ContractCheckDef�v���Q��<BR>
     * (*2) �����́A�����\�b�h�̈���.�ԍό��ʃG���g�� <BR>
     * @@param l_settleContractEntrys - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g��<BR>
     * @@throws WEB3BaseException
     * @@return String
     */
    public String getDayTradeType(SettleContractEntry[] l_settleContractEntrys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDayTradeType(SettleContractEntry[])";
        log.entering(STR_METHOD_NAME);

        //�mthis.is���v��() == true�̏ꍇ�n
        //�u���v��v��ԋp����B
        if (this.isDayTrade(l_settleContractEntrys))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ContractCheckDef.DAY_TRADE;
        }

        //this.is���v��() == false�̏ꍇ�n
        //null��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (is�[��܂Œ���)<BR>
     * �u�[��܂Œ����v�̒������ǂ����𔻒肷��B<BR>
     * �u�[��܂Œ����v�̏ꍇ��true���A�u�[��܂Œ����v�ł͂Ȃ��ꍇ��false���A<BR>
     * ���ꂼ��ԋp����B<BR>
     * <BR>
     * �P�j�@@OP�����}�l�[�W��.get���񒍕��̒����P��()���R�[������B<BR>
     * <BR>
     * �@@[get���񒍕��̒����P��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �Q�j�@@�[��܂Œ������ǂ������肷��B <BR>
     * �@@�P�j�ɂĎ擾���������P��.���񒍕��̒����P��ID == null ���� <BR>
     * �@@�@@�P�j�ɂĎ擾���������P��.�[��O�J�z�Ώۃt���O == "�[��O�J�z����"�̏ꍇ�A<BR>
     * �@@true�i�[��܂Œ����j��ԋp����B <BR>
     * �@@�ȊO�Afalse�i�[��܂Œ����łȂ��j��ԋp����B<BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isEveningSessionOrder(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isEveningSessionOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@OP�����}�l�[�W��.get���񒍕��̒����P��()���R�[������B
        //   �@@[get���񒍕��̒����P��()�Ɏw�肷�����]
        //   �@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        IfoOrderUnitRow l_firstOrderUnitRow =
            (IfoOrderUnitRow)this.getFirstOrderUnit(l_ifoOrderUnit).getDataSourceObject();

        //�Q�j�@@�[��܂Œ������ǂ������肷��B
        //�P�j�ɂĎ擾���������P��.���񒍕��̒����P��ID == null ����
        //�P�j�ɂĎ擾���������P��.�[��O�J�z�Ώۃt���O == "�[��O�J�z����"�̏ꍇ�A
        //true�i�[��܂Œ����j��ԋp����B

        if (l_firstOrderUnitRow.getFirstOrderUnitIdIsNull()
            && BooleanEnum.TRUE.equals(l_firstOrderUnitRow.getEveningSessionCarryoverFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ȊO�Afalse�i�[��܂Œ����łȂ��j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is�J�z�Ώے���)<BR>
     * �J�z�Ώے����ł��邩���肷��B <BR>
     * �P�j������ԊǗ�.get����敪()���R�[������B <BR>
     * <BR>
     * �@@�@@[get����敪()�Ɏw�肷�����]<BR>
     * �@@�@@�������� <BR>
     * <BR>
     * �Q�j�[��O�����J�z�iget����敪() == �h�[��h�j�̏ꍇ�A<BR> 
     * �@@�@@�����o�^�����������蒍���i*1�j�͌J�z�ΏۊO�����Ɣ��f���邽�߁A <BR>
     * �@@�@@false��ԋp����B <BR>
     * <BR>
     * �@@�i*1�j�����o�^�����������蒍�� <BR>
     * �@@�@@�@@�敨�f�[�^�A�_�v�^.get���������敪() == �h��������h���A <BR>
     * �@@�@@�@@����.�����P��.����敪 == null <BR>
     * <BR>
     * �@@�@@�@@[get���������敪()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�����P�ʁF�@@����.�����P�� <BR>
     * <BR>
     * �R�j�����J�z�iget����敪() != �h�[��h�j�̏ꍇ�A <BR>
     * �@@�@@����.�����P��.���������� <= �Ɩ����t�i*2�j �ɊY�����钍���́A <BR>
     * �@@�@@�J�z�ΏۊO�����Ɣ��f���邽�߁A false ��ԋp����B<BR> 
     * <BR>
     * �@@�i*2�j �Ɩ����t�́AGtlUtils.getTradingSystem().getBizDate()�Ŏ擾�B <BR>
     * <BR>
     * �S�j��L�̏����ɊY�����Ȃ��ꍇ�Atrue�i=�J�z�Ώے����j��ԋp����B<BR> 
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isCarryoverOrder(IfoOrderUnit l_ifoOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCarryoverOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //������ԊǗ�.get����敪()���R�[������
        String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();

        //���������敪
        String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);

        //�����P��
        IfoOrderUnitRow l_firstOrderUnitRow =
            (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        //�����P��.����������
        //(�Ɩ����t�� GtlUtils.getTradingSystem().getBizDate()�Ŏ擾)
        Timestamp l_tsExpirationTimestamp = l_ifoOrderUnit.getExpirationTimestamp();
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //�[��O�����J�z�iget����敪() == �h�[��h�j�̏ꍇ
        if (WEB3SessionTypeDef.EVENING_SESSION.equals(l_strSessionType))
        {
            //�敨�f�[�^�A�_�v�^.get���������敪() == �h��������h����
            //����.�����P��.����敪 == null
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strExpirationDateType)
                && l_firstOrderUnitRow.getSessionType() == null)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        //�����J�z�iget����敪() != �h�[��h�j�̏ꍇ
        else
        {
            //����.�����P��.���������� <= �Ɩ����t
            if (WEB3DateUtility.compareToDay(l_tsExpirationTimestamp, l_datBizDate) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (is���v��Ώ�)<BR>
     * ���ʂ����v��Ώۂł��邩���肷��B <BR>
     * �@@�Etrue�i���v��ԍςł���j<BR>
     * �@@�Efalse�i���v��ԍςłȂ��j<BR>
     * <BR>
     * �P�j�p�����[�^.���� == �������i������ԊǗ�.get������()�j<BR>
     * �@@�@@���A����敪 == ������ԊǗ�.get����敪()�̏ꍇ�A <BR>
     * �@@�@@true��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�̏����ɊY�����Ȃ��ꍇ�A<BR> 
     * �@@�@@false��ԋp����B <BR>
     * <BR> 
     * @@param l_contractOpenDate - (����)<BR>
     * @@param l_contractSessionType - (����敪)<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isDayTrade(Date l_contractOpenDate, String l_contractSessionType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDayTrade(Date l_contractOpenDate, String l_contractSessionType)";
        log.entering(STR_METHOD_NAME);

        if (WEB3DateUtility.compareToDay(l_contractOpenDate,
            WEB3GentradeTradingTimeManagement.getOrderBizDate()) == 0
            && WEB3Toolkit.isEquals(l_contractSessionType,
            WEB3GentradeTradingTimeManagement.getSessionType()))
        {
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�����L������)<BR>
     * �����L�������ŏI���̂ݎw���Ђ̏ꍇ�A<BR>
     * �p�����[�^.�����L�������Ɛ敨OP�������.�����ŏI�����r����<BR>
     * ���������̒l��ԋp����B<BR>
     * �i�����L�������ŏI����蔄���ŏI�����������ꍇ�A<BR>
     * �����ŏI���𒍕��L�������ŏI���Ƃ��Ďg�p����ׁj<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����L��������null�̏ꍇ�A<BR>
     * �@@�@@�ȉ��Q�j�`�S�j�̏����͍s�킸�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�敨OP��������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@[get�������()�ɐݒ肷�����]<BR>
     * �@@�،���ЁF  ���O�C���Z�b�V�������擾�����،����<BR>
     * �@@�����R�[�h�F �p�����[�^.�����R�[�h<BR>
     * �@@�s��R�[�h�F �p�����[�^.�s��R�[�h<BR>
     * <BR>
     * �R�j�@@�戵�\���������I�u�W�F�N�g�𐶐�����B<BR>
     * �@@[�R���X�g���N�^�ɐݒ肷�����]<BR>
     * �@@�،���ЃR�[�h�F        ���O�C���Z�b�V�������擾�����،���ЃR�[�h<BR>
     * �@@�����^�C�v�F              �h�敨�I�v�V�����h<BR>
     * �@@�敨�^�I�v�V�����敪�F �p�����[�^.�敨�^�I�v�V����<BR>
     * �@@�M�p����敪�F        �hDEFAULT�h<BR>
     * <BR>
     * �S�j�@@�����L��������ԋp����B<BR>
     * <BR>
     * �@@�S�|�P�j�@@�戵�\��������.get�o����܂Œ����������w��()�̖߂�l��<BR>
     * �@@�@@�@@�@@�@@�@@�w�ŏI���̂ݎw��x�̏ꍇ<BR>
     * <BR>
     * �@@�@@�S�|�P�|�P�j�@@�敨OP�������.�����ŏI���ƃp�����[�^.�����L��������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��r���ď��������̒l��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�S�|�P�|�P�|�P�j�@@�敨OP�������.�����ŏI�������p�����[�^.�����L�������̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�敨OP�������.�����ŏI����ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�S�|�P�|�P�|�Q�j�@@�敨OP�������.�����ŏI�����p�����[�^.�����L�������̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����L��������ԋp����B<BR>
     * <BR>
     * �@@�S�|�Q�j�@@��L�ȊO�̏ꍇ�A�p�����[�^.�����L��������ԋp����B<BR>
     * <BR>
     * @@param l_datExpirationDate - �����L������<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * @@param l_strFutureOptionDiv - �敨�^�I�v�V����<BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExpirationDate(
        Date l_datExpirationDate,
        String l_strProductCode,
        String l_strMarketCode,
        String l_strFutureOptionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate(Date, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�p�����[�^.�����L��������null�̏ꍇ
        //null��ԋp����
        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl =
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        //�敨OP��������I�u�W�F�N�g�𐶐�����B
        //[get�������()�ɐݒ肷�����]
        //�،���ЁF  ���O�C���Z�b�V�������擾�����،����
        //�����R�[�h�F �p�����[�^.�����R�[�h
        //�s��R�[�h�F �p�����[�^.�s��R�[�h
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        //�����}�l�[�W�����擾����
        WEB3GentradeAccountManager l_accountMananger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        long l_lngAccountId = l_opLoginSec.getAccountId();

        WEB3GentradeHandlingOrderCond l_handlingOrderCond = null;
        //�����ŏI��
        Date l_datLastTrading = null;
        try
        {
            //�������擾����
            MainAccount l_mainAccount = l_accountMananger.getMainAccount(l_lngAccountId);
            //�،����
            Institution l_institution = l_mainAccount.getInstitution();

            WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
                l_ifoProductManagerImpl.getIfoTradedProduct(l_institution, l_strProductCode, l_strMarketCode);

            //�敨OP�������.�����ŏI��
            IfoTradedProductRow l_ifoTradedProductRow =
                (IfoTradedProductRow)l_ifoTradedProductImpl.getDataSourceObject();
            l_datLastTrading = l_ifoTradedProductRow.getLastTradingDate();

            //�戵�\���������I�u�W�F�N�g�𐶐�����B
            l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                l_institution.getInstitutionCode(), //�،���ЃR�[�h
                ProductTypeEnum.IFO, //�h�敨�I�v�V�����h
                l_strFutureOptionDiv,//�p�����[�^.�敨�^�I�v�V����
                WEB3MarginTradingDivDef.DEFAULT); //�M�p����敪
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        //�戵�\��������.get�o����܂Œ����������w��()
        String l_strCarriedOrderLapseDateSpec = l_handlingOrderCond.getOrderUntilDeadLineExpDay();

        //�w�ŏI���̂ݎw��x�̏ꍇ
        if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
        {
            //�敨OP�������.�����ŏI�������p�����[�^.�����L�������̏ꍇ�A
            if (WEB3DateUtility.compareToDay(l_datLastTrading, l_datExpirationDate) <= 0)
            {
                //�敨OP�������.�����ŏI����ԋp����B
                return l_datLastTrading;
            }
            //�敨OP�������.�����ŏI�����p�����[�^.�����L�������̏ꍇ�A
            else
            {
                //�p�����[�^.�����L��������ԋp����B
                return l_datExpirationDate;
            }
        }

        //�ȊO�̏ꍇ�A�p�����[�^.�����L��������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_datExpirationDate;
    }

    /**
     * (notify�e�����S�����)<BR>
     * �����Ŏw�肳�ꂽ�������S����肵�Ă���A���A<BR>
     * �L���ȗ\�񒍕����ݒ肳��Ă���e�����ł���ꍇ�ɁA<BR>
     * ���[���G���W���T�[�o�ɐe�����̑S������ʒm����B<BR>
     * ���S����肩�ǂ����͌Ăяo�����Ŕ��ʂ��邱�ƁB<BR>
     * <BR>
     * �P�j�@@�\�񒍕��L���̔��� <BR>
     * �����Ŏw�肳�ꂽ�����ɗL���ȗ\�񒍕����ݒ肳��Ă��邩 <BR>
     *�i���e�����ł��邩�j�𔻒肷��B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�\�񒍕��m�F�v�ۂ̔���<BR>
     * �@@�@@this.is�\�񒍕��m�F�v()���R�[������B<BR>
     * <BR>
     * �@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�����P�ʁF�@@����.�����P��<BR>
     * <BR>
     * �߂�l��false�̏ꍇ�A�\�񒍕����ݒ肳��Ă��Ȃ����ߏ������I������ireturn)<BR>
     * �Q�j�@@�S����肩�ǂ����̔���<BR>
     * �@@�����̒����P��.isFullyExecuted()���R�[������B<BR>
     * <BR>
     * �@@�߂�l��false�̏ꍇ�A�S�����łȂ����ߏ������I������ireturn)<BR>
     * �R�j�@@�L���\�񒍕��̊m�F <BR>
     * �@@�L���ȗ\�񒍕��̈ꗗ���擾����B<BR>
     * <BR>
     * �敨OP�\�񒍕��X�V�T�[�r�XImpl.get�L���\�񒍕��P�ʈꗗ()���R�[������B<BR>
     * <BR>
     * �@@[�����̐ݒ�]�e�����̒���ID�F�@@����.�����P��.����ID <BR>
     * <BR>
     * �@@�߂�l��null�̏ꍇ�A�L���ȗ\�񒍕������݂��Ȃ����ߏ������I������(return) <BR>
     * <BR>
     * �S�j�@@�S�����̒ʒm<BR>
     * ���[���G���W���T�[�o�ɐe�����̑S������ʒm����B<BR>
     * <BR>
     * WEB3RlsRequestSenderService.sendConOrderExecuteMessage()���R�[������B<BR>
     * <BR>
     * [�����̐ݒ�]<BR>
     * �⏕�����F�@@����.�����P��.����ID�A�⏕����ID�ɊY������⏕����<BR>
     * �e�����̒���ID�F�@@����.�����P��.����ID<BR>
     * �����^�C�v�F�@@"�敨�I�v�V����"<BR>
     * �e�����̎��ʃR�[�h�F�@@����.�����P��.���ʃR�[�h<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    private void notifyParentOrderFullyExecuted(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyParentOrderFullyExecuted(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�\�񒍕��L���̔���
        // �P�|�P�j�@@�\�񒍕��m�F�v�ۂ̔���
        // �߂�l��false�̏ꍇ�A�\�񒍕����ݒ肳��Ă��Ȃ����ߏ������I������ireturn)
        if (!this.isReserveOrderExist(l_ifoOrderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // �Q�j�@@�S����肩�ǂ����̔���
        // �����̒����P��.isFullyExecuted()���R�[������B
        // �߂�l��false�̏ꍇ�A�S�����łȂ����ߏ������I������ireturn)
        if (!l_ifoOrderUnit.isFullyExecuted())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // �R�j�@@�L���\�񒍕��̊m�F
        // �L���ȗ\�񒍕��̈ꗗ���擾����B
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        WEB3ToSuccReservationIfoOrderUpdateService l_orderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        List l_lisOpenReserveIfoOrderUnits = l_orderUpdateService.getOpenReserveIfoOrderUnits(
            l_ifoOrderUnit.getOrderId());
        // �߂�l��null�̏ꍇ�A�L���ȗ\�񒍕������݂��Ȃ����ߏ������I������(return)
        if (l_lisOpenReserveIfoOrderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        // �S�j�@@�S�����̒ʒm
        // ���[���G���W���T�[�o�ɐe�����̑S������ʒm����B
        // WEB3RlsRequestSenderService.sendConOrderExecuteMessage()���R�[������B
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(
                WEB3RlsRequestSenderService.class);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accMgr.getSubAccount(l_ifoOrderUnit.getAccountId(), l_ifoOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // [�����̐ݒ�]
        // �⏕�����F�@@����.�����P��.����ID�A�⏕����ID�ɊY������⏕����
        // �e�����̒���ID�F�@@����.�����P��.����ID
        // �����^�C�v�F�@@"�敨�I�v�V����"
        // �e�����̎��ʃR�[�h�F�@@����.�����P��.���ʃR�[�h
        l_rlsRequestSenderService.sendConOrderExecuteMessage(
            l_subAccount,
            new Long(l_ifoOrderUnit.getOrderId()),
            ProductTypeEnum.IFO,
            l_ifoOrderUnitRow.getOrderRequestNumber());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify�A������)<BR>
     * �A�������̓o�^�����[���G���W���T�[�o�ɒʒm����B<BR>
     * <BR>
     * �P�j�@@�\�񒍕��P�ʂ��ǂ����̔���<BR>
     * �@@�����̒����P��.getDataSourceObject()���R�[������B<BR>
     * ���\�b�h�̖߂�l�̌^���A<BR>
     * �敨OP�\�񒍕��P��Row�łȂ��ꍇ�A<BR>
     * �����ΏۊO�ł���ׁA�������I������B�ireturn�j<BR>
     * <BR>
     * �Q�j�@@�⏕�������擾����B<BR>
     * �@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B<BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�P�j�̖߂�l.����ID<BR>
     * �@@�P�j�̖߂�l.�⏕����ID<BR>
     * <BR>
     * �R�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j���擾����B<BR>
     * <BR>
     * �S�j���[���G���W���T�[�o�ɒʒm���s���B<BR>
     * [�V�K�o�^�i�P�j�̖߂�l.������� == "��t�ρi�V�K�����j"�j�̏ꍇ]<BR>
     * �@@�R�j�Ŏ擾�����T�[�r�X.sendRegisterConOrdersMessage()���\�b�h���R�[������B<BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�@@�⏕�����F�@@�Q�j�Ŏ擾�����⏕����<BR>
     * �@@�@@�����t�����^�C�v�F�@@"�A������"<BR>
     * �@@�@@�e�����̖����^�C�v�F�@@"�敨�I�v�V����"<BR>
     * �@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.�e�����̒���ID<BR>
     * �@@�@@�q�����̖����^�C�v�ꗗ�F�@@�P�j�̖߂�l.�����^�C�v�݂̂�v�f�Ƃ���z��<BR>
     * �@@�@@�q�����̒���ID�ꗗ�F�@@�P�j�̖߂�l.����ID�݂̂�v�f�Ƃ���z��<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    private void notifyToSuccOrder(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyToSuccOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�\�񒍕��P�ʂ��ǂ����̔���
        RsvIfoOrderUnitRow l_rsvOrderUnitRow = null;
        if (!(l_ifoOrderUnit.getDataSourceObject() instanceof RsvIfoOrderUnitRow))
        {
            log.exiting(STR_METHOD_NAME);
            return;    
        }
        l_rsvOrderUnitRow = (RsvIfoOrderUnitRow) l_ifoOrderUnit.getDataSourceObject();

        // �Q�j�@@�⏕�������擾����B
       //�@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B
       //�@@[�����̐ݒ�]
       //�@@�@@arg0�F�@@�P�j�̖߂�l.����ID
       //�@@�@@arg1�F�@@�P�j�̖߂�l.�⏕����ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accMgr.getSubAccount(
                l_rsvOrderUnitRow.getAccountId(), l_rsvOrderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�R�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j���擾����B
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(
                WEB3RlsRequestSenderService.class);

        // ������� == "��t�ρi�V�K�����j"�j�̏ꍇ
        if (OrderStatusEnum.ACCEPTED.equals(l_rsvOrderUnitRow.getOrderStatus()))
        {
            //[�����̐ݒ�]
            //�@@�⏕�����F�@@�Q�j�Ŏ擾�����⏕����
            //�@@�����t�����^�C�v�F�@@"�A������"
            //�@@�e�����̖����^�C�v�F�@@"�敨�I�v�V����"
            //�@@�e�����̒���ID�F�@@�P�j�̖߂�l.�e�����̒���ID
            //�@@�q�����̖����^�C�v�ꗗ�F�@@�P�j�̖߂�l.�����^�C�v�݂̂�v�f�Ƃ���z��
            //�@@�q�����̒���ID�ꗗ�F�@@�P�j�̖߂�l.����ID�݂̂�v�f�Ƃ���z��
            l_rlsRequestSenderService.sendRegisterConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.EXECUTE,
                ProductTypeEnum.IFO,
                new Long(l_rsvOrderUnitRow.getParentOrderId()),
                new ProductTypeEnum[]{l_rsvOrderUnitRow.getProductType()},
                new Long[]{new Long(l_rsvOrderUnitRow.getOrderId())});
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�\�񒍕��m�F�v)<BR>
     * �����Ŏw�肳�ꂽ�����ɗ\�񒍕����ݒ肳��Ă���\�������邩 <BR>
     *�i���e�����̉\�������邩�j�𔻒肷��B<BR>
     * <BR>
     * �\�񒍕����ݒ肳��Ă���\��������ꍇtrue���A<BR>
     * �Ȃ��ꍇfalse���A���ꂼ��ԋp����B<BR>
     * <BR>
     * �P�j�@@�\�񒍕��m�F�v�ۂ̔��� <BR>
     * �����̒����P��.�\�񒍕��ݒ�t���O �� "1�F�ݒ�̉\������"�̏ꍇ�́A<BR>
     * �\�񒍕��̐ݒ�Ȃ��̂��߁Afalse��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isReserveOrderExist(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isReserveOrderExist(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();

        // �����̒����P��.�\�񒍕��ݒ�t���O �� "1�F�ݒ�̉\������"�̏ꍇ
        // �\�񒍕��̐ݒ�Ȃ��̂��߁Afalse��ԋp����B
        if (!WEB3ReserveOrderExistFlagDef.SET_POSSIBLE.equals(l_ifoOrderUnitRow.getReserveOrderExistFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        // �ȊO�Atrue��ԋp����B
        return true;
    }
}
@
