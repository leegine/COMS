head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�X�V�C�x���g�C���^�Z�v�^(WEB3FeqOrderAcceptUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���U (���u) �V�K�쐬
                   2005/07/27 䈋��@@(���u) ���r���[
                   2006/10/17 �����(���u) �c�a�X�V�d�l068
                   2006/11/21 �����(���u) ��Q�Ή�K00010
                   2006/12/19 ���G��(���u) �c�a�X�V�d�l077
*/

package webbroker3.feq;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.feq.define.WEB3FeqChangeCancelDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O������������t�X�V�C�x���g�C���^�Z�v�^)<BR>
 * �O������������t�X�V�C�x���g�C���^�Z�v�^<BR>
 *
 * @@author ���U(���u)
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptUpdateInterceptor extends WEB3FeqUpdateInterceptor
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    protected static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptUpdateInterceptor.class);


    /**
     * (�s�ꃌ�X�|���X���b�Z�[�W)<BR>
     * �s�ꃌ�X�|���X���b�Z�[�W<BR>
     * <BR>
     * ����ۃN���X�͈ȉ��̉��ꂩ<BR>
     * �@@�EDefaultNewOrderAcceptedMarketResponseMessage�i������t�ρj<BR>
     * �@@�EDefaultNewOrderRejectedMarketResponseMessage�i������t�G���[�j<BR>
     * �@@�EDefaultNewOrderSentMarketResponseMessage<BR>
     *    �i������t�ώ���G��t�ς̎���j<BR>
     * �@@�EDefaultChangeOrderSentMarketResponseMessage<BR>
     *    �i������t�ώ���G�����ς̎���j<BR>
     * �@@�EDefaultCancelOrderSentMarketResponseMessage<BR>
     *    �i������t�ώ���G����ς̎���j<BR>
     * �@@�EDefaultChangeOrderAcceptedMarketResponseMessage�i�����ρj<BR>
     * �@@�EDefaultChangeOrderRejectedMarketResponseMessage�i�����G���[�j<BR>
     * �@@�EDefaultCancelOrderAcceptedMarketResponseMessage�i����ρj<BR>
     * �@@�EDefaultCancelOrderRejectedMarketResponseMessage�i����G���[�j<BR>
     * �@@�EDefaultOrderInvalidatedMarketResponseMessage�i�o�����j<BR>
     */
    private MarketResponseMessage marketResponseMessage;

    /**
     * @@roseuid 42D0D7C700FA
     */
    public WEB3FeqOrderAcceptUpdateInterceptor()
    {

    }

    /**
     * (�O������������t�X�V�C�x���g�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �s�ꃌ�X�|���X���b�Z�[�W���v���p�e�B�ɃZ�b�g���C���X�^���X��������B<BR>
     * @@param l_marketResponseMessage - (�s�ꃌ�X�|���X���b�Z�[�W)<BR>
     * �s�ꃌ�X�|���X���b�Z�[�W<BR>
     * <BR>
     * ����ۃN���X�͈ȉ��̉��ꂩ<BR>
     * �@@�EDefaultNewOrderAcceptedMarketResponseMessage�i������t�ρj<BR>
     * �@@�EDefaultNewOrderRejectedMarketResponseMessage�i������t�G���[�j<BR>
     * �@@�EDefaultNewOrderSentMarketResponseMessage<BR>
     *     �i������t�ώ���G��t�ς̎���j<BR>
     * �@@�EDefaultChangeOrderSentMarketResponseMessage<BR>
     *     �i������t�ώ���G�����ς̎���j<BR>
     * �@@�EDefaultCancelOrderSentMarketResponseMessage<BR>
     *     �i������t�ώ���G����ς̎���j<BR>
     * �@@�EDefaultChangeOrderAcceptedMarketResponseMessage�i�����ρj<BR>
     * �@@�EDefaultChangeOrderRejectedMarketResponseMessage�i�����G���[�j<BR>
     * �@@�EDefaultCancelOrderAcceptedMarketResponseMessage�i����ρj<BR>
     * �@@�EDefaultCancelOrderRejectedMarketResponseMessage�i����G���[�j<BR>
     * �@@�EDefaultOrderInvalidatedMarketResponseMessage�i�o�����j<BR>
     * @@roseuid 42A93E36015D
     */
    public WEB3FeqOrderAcceptUpdateInterceptor(
        MarketResponseMessage l_marketResponseMessage)
    {
        this.marketResponseMessage = l_marketResponseMessage;
    }

    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * <BR>
     * �P�j�@@�����iͯ�ށj�e�[�u���X�V<BR>
     * �@@super.mutate(OrderManagerPersistenceType,<BR>
     * �@@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * �@@���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u���X�V�l�ݒ�<BR>
     * �@@�����P��Params�Ɋg������<BR>
     * �@@�ixTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځj��ݒ肵�ԋp����B <BR>
     * �@@���ڐݒ���e�́A<BR>
     * �@@�yxTrade�z�⑫����.DB�X�V\\15.<BR>
     * �@@�i�ǁj������t����F��\\�u�O����t_�O�������P�ʎd�l.xls�v<BR>
     * �@@�t�@@�C���̑Ή����郏�[�N�V�[�g�Q�ƁB<BR>
     * �@@�Ή����郏�[�N�V�[�g�͈ȉ��̒ʂ�B<BR>
     * <BR>
     * �@@�@@������t�ς̏ꍇ�ithis.�s�ꃌ�X�|���X���b�Z�[�Winstanceof<BR>
     * �@@DefaultNewOrderAcceptedMarketResponseMessage�j<BR>
     * �@@�ˊO����t_�O�������P�� DB�X�V�i������t�ρj<BR>
     * <BR>
     * �A�@@������t�G���[�̏ꍇ<BR>�ithis.�s�ꃌ�X�|���X���b�Z�[�W instanceof<BR>
     * �@@DefaultNewOrderRejectedMarketResponseMessage�j<BR>
     * �@@�ˊO����t_�O�������P�� DB�X�V�i������t�G���[�j<BR>
     * <BR>
     * �B�@@��t�ς̎���̏ꍇ<BR>�ithis.�s�ꃌ�X�|���X���b�Z�[�Winstanceof<BR>
     * �@@DefaultNewOrderSentMarketResponseMessage�j<BR>
     * �C�@@�����ς̎���̏ꍇ<BR>�ithis.�s�ꃌ�X�|���X���b�Z�[�Winstanceof<BR>
     * �@@DefaultChangeOrderSentMarketResponseMessage�j<BR>
     *<BR>
     * �D�@@����ς̎���̏ꍇ�ithis.�s�ꃌ�X�|���X���b�Z�[�Winstanceof<BR>
     * �@@DefaultCancelOrderSentMarketResponseMessage�j<BR>
     * �@@�ˊO����t_�O�������P�� DB�X�V�i��t�ώ���j<BR>
     * <BR>
     * �@@���B�`�D�ŁA�����t�̏ꍇ<BR>
     * �@@�i�����P�ʍs.������� == �h�O������h�j�A���b�N����<BR>
     * �@@���Y�ڍ׃e�[�u���̑Ώۍs(*1)���X�V�iDB-Update�j����B�X�V���e�́A<BR>
     * �@@�@@�u�O����t_���b�N���̎��Y�ڍ׃e�[�u��.xls<BR>
     * �@@�@@���O����t_���b�N���̎��Y�ڍ�DB�X�V�i������t�ώ���j�v�Q�ƁB<BR>
     * <BR>
     * �@@�@@(*1) ���b�N���̎��Y�ڍ׃e�[�u���Ώۍs�擾<BR>
     * �@@�@@�|�W�V�����}�l�[�W��.get���b�N���̎��Y�ڍ�()�ɂĎ擾����B<BR>
     * <BR>
     * �@@�@@[get���b�N���̎��Y�ڍ�()�Ɏw�肷�����]<BR>
     * �@@�@@�����h�c�F�@@�����P�ʍs.�����h�c<BR>
     * �@@�@@�⏕�����h�c�F�@@�����P�ʍs.�⏕�����h�c <BR>
     * �@@�@@�����h�c�F�@@�����P�ʍs.�����h�c <BR>
     * �@@�@@�ŋ敪�F�@@�����P�ʍs.�ŋ敪 <BR>
     * <BR>
     * �E�@@�����ς̏ꍇ<BR>�ithis.�s�ꃌ�X�|���X���b�Z�[�Winstanceof<BR>
     * �@@DefaultChangeOrderAcceptedMarketResponseMessage�j<BR>
     * �@@�ˊO����t_�O�������P�� DB�X�V�i�����ρj<BR>
     *
     * �F�@@�����G���[�̏ꍇ<BR>�ithis.�s�ꃌ�X�|���X���b�Z�[�Winstanceof<BR>
     * �@@DefaultChangeOrderRejectedMarketResponseMessage�j<BR>
     * �@@�ˊO����t_�O�������P�� DB�X�V�i�����G���[�j<BR>
     * <BR>
     * �G�@@����ς̏ꍇ<BR>�ithis.�s�ꃌ�X�|���X���b�Z�[�Winstanceof<BR>
     * �@@DefaultCancelOrderAcceptedMarketResponseMessage�j<BR>
     * �@@�ˊO����t_�O�������P�� DB�X�V�i����ρj<BR>
     * <BR>
     * �H�@@����G���[�̏ꍇ<BR>�ithis.�s�ꃌ�X�|���X���b�Z�[�Winstanceof<BR>
     * �@@DefaultCancelOrderRejectedMarketResponseMessage�j<BR>
     * �@@�ˊO����t_�O�������P�� DB�X�V�i����G���[�j<BR>
     * <BR>
     * �I�@@�o�����i�����j�̏ꍇ<BR>�ithis.�s�ꃌ�X�|���X���b�Z�[�Winstanceof<BR>
     * �@@DefaultOrderInvalidatedMarketResponseMessage�j<BR>
     * �@@�ˊO����t_�O�������P�� DB�X�V�i�o�����j<BR>
     * <BR>
     * �J�@@�o�����i�����j�̎���̏ꍇ<BR>�ithis.�s�ꃌ�X�|���X���b�Z�[�W<BR>
     * �@@instanceofDefaultUndoOrderInvalidatedMarketResponseMessage�j<BR>
     * �@@�ˊO����t_�O�������P�� DB�X�V�i��t�ώ���i��������j�j<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v<BR>
     * @@param l_context - (����)<BR>
     * ����<BR>
     * @@param l_orderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j<BR>
     * @@return FeqOrderUnitParams
     * @@roseuid 42A93E360159
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, "
            + "FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        // �P�j �����iͯ�ށj�e�[�u���X�V
        l_orderUnitParams = super.mutate(l_updateType, l_context, l_orderUnitParams);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        FeqOrderManager l_orderManager = (FeqOrderManager)l_tradingModule.getOrderManager();

        // �����I�u�W�F�N�g�擾
        FeqOrderRow l_orderRow = null;
        try
        {
            l_orderRow = (FeqOrderRow) l_orderManager.getOrder(
                l_orderUnitParams.getOrderId()).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_orderRow == null)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // �X�V�҃R�[�h
        String l_strLastUpdater = l_orderRow.getLastUpdater();
        log.debug("�X�V�҃R�[�h = " + l_strLastUpdater);

        FeqOrderUnit l_orderUnit = (FeqOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);

        // �Q�j�@@�����P�ʃe�[�u���X�V�l�ݒ�
        // �@@�@@������t�ς̏ꍇ
        if (this.marketResponseMessage instanceof DefaultNewOrderAcceptedMarketResponseMessage)
        {
            // �s�ꂩ��m�F�ς݂̒����P��
            l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.price);

            // �s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.estimated_price);

            // �s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
            l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderUnitParams.f_estimated_price);

            // �s�ꂩ��m�F�ς݂̎��s����
            l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());

            // �X�V�҃R�[�h
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // �A�@@������t�G���[�̏ꍇ
        else if (this.marketResponseMessage instanceof DefaultNewOrderRejectedMarketResponseMessage)
        {
            // �X�V�҃R�[�h
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // �B�@@��t�ς̎���̏ꍇ
        // �C�@@�����ς̎���̏ꍇ
        // �D�@@����ς̎���̏ꍇ
        else if (this.marketResponseMessage instanceof DefaultNewOrderSentMarketResponseMessage ||
            this.marketResponseMessage instanceof DefaultChangeOrderSentMarketResponseMessage ||
            this.marketResponseMessage instanceof DefaultCancelOrderSentMarketResponseMessage)
        {
            FeqOrderAction l_orderActionMax = null;
            if (this.marketResponseMessage instanceof DefaultNewOrderSentMarketResponseMessage)
            {
                //�s�ꂩ��m�F�ς݂̐���
                l_orderUnitParams.setConfirmedQuantity(null);

                //�s�ꂩ��m�F�ς݂̎w�l
                l_orderUnitParams.setConfirmedPrice(null);

                //�s�ꂩ��m�F�ς݂̒����P��
                l_orderUnitParams.setConfirmedOrderPrice(null);

                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                l_orderUnitParams.setConfirmedEstimatedPrice(null);

                //�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
                l_orderUnitParams.setConfirmedFEstimatedPrice(null);

                //�s�ꂩ��m�F�ς݂̎��s����
                l_orderUnitParams.setConfirmedExecConditionType(null);
            }
            if (this.marketResponseMessage instanceof DefaultChangeOrderSentMarketResponseMessage)
            {
                //(*1) �����O���ʁF�@@
                //�@@�����P��.getOrderActions()�̖߂�l�z����A
                //�@@�����C�x���g�^�C�v==�h2�F�ύX�����h&&�������==�h7�F��t�ρh�j�ɓ��Ă͂܂���̂̂����A
                //�@@��������ԍ����ő�̂��̂��擾����B
                OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
                int l_intOrdActSerialNoMax = 0;
                boolean l_blnIsMaxAction = true;
                for (int i = 0; i < l_orderActions.length; i ++)
                {
                    FeqOrderAction l_orderAction = (FeqOrderAction)l_orderActions[i];
                    if (i == 0)
                    {
                        l_intOrdActSerialNoMax = l_orderAction.getOrderActionSerialNo();
                    }
                    if (OrderEventTypeEnum.CHANGE.equals(l_orderAction.getOrderEventType()) &&
                        OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderAction.getOrderStatus()))
                    {
                        if (l_blnIsMaxAction)
                        {
                            l_blnIsMaxAction = false;
                            l_orderActionMax = l_orderAction;
                        }
                        int l_intSerialNo = l_orderAction.getOrderActionSerialNo();
                        if (l_intSerialNo > l_intOrdActSerialNoMax)
                        {
                            l_intOrdActSerialNoMax = l_intSerialNo;
                            l_orderActionMax = l_orderAction;
                        }
                    }
                }
                if (l_orderActionMax == null)
                {
                    log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                        + "�f�[�^�s�����G���[:���������������擾�Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                FeqOrderActionRow l_orderActionMaxRow = (FeqOrderActionRow)l_orderActionMax.getDataSourceObject();
                
                //�s�ꂩ��m�F�ς݂̐���:
                //�擾������������.�s��m�F�ς݂̐��ʂ�����O���ʂƂ��Ďg�p����B
                if (l_orderActionMaxRow.getConfirmedQuantityIsNull())
                {
                    l_orderUnitParams.setConfirmedQuantity(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedQuantity(l_orderActionMaxRow.getConfirmedQuantity());
                }

                //�s�ꂩ��m�F�ς݂̎w�l
                //�擾������������.�s��m�F�ς݂̎w�l������O�w�l�Ƃ��Ďg�p����B
                if (l_orderActionMaxRow.getConfirmedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedPrice(l_orderActionMaxRow.getConfirmedPrice());
                }

                //���������E����敪 = �i�����ς̎���j�̏ꍇ�A�T�i�������j
                l_orderUnitParams.setModifyCancelType(WEB3FeqChangeCancelDivDef.CHANGING);

                //�s�ꂩ��m�F�ς݂̒����P��
                //�擾������������.�s�ꂩ��m�F�ς݂̒����P��������O�w�l�Ƃ��Ďg�p����B
                if (l_orderActionMaxRow.getConfirmedOrderPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderActionMaxRow.getConfirmedOrderPrice());
                }

                //�s�ꂩ��m�F�ς݂̊T�Z��n���
                //�擾������������.�s�ꂩ��m�F�ς݂̊T�Z��n���������O�T�Z��n����Ƃ��Ďg�p����B
                if (l_orderActionMaxRow.getConfirmedEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderActionMaxRow.getConfirmedEstimatedPrice());
                }

                //�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
                //�@@�擾������������.�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j������O�T�Z��n����i�O�݁j�Ƃ��Ďg�p����B
                if (l_orderActionMaxRow.getConfirmedFEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedFEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderActionMaxRow.getConfirmedFEstimatedPrice());
                }

                //�s�ꂩ��m�F�ς݂̎��s����
                //�擾������������.�s�ꂩ��m�F�ς݂̎��s����������O���s�����Ƃ��Ďg�p����B
                l_orderUnitParams.setConfirmedExecConditionType(l_orderActionMaxRow.getConfirmedExecConditionType());
                
            }

            //�i����ς̎���j�̏ꍇ�A1:�I�[�v���GOPEN
            if (this.marketResponseMessage instanceof DefaultCancelOrderSentMarketResponseMessage)
            {
                l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //���������E����敪 = �i����ς̎���j�̏ꍇ�A�P�i������j
                l_orderUnitParams.setModifyCancelType(WEB3FeqChangeCancelDivDef.CANCELING);
            }

            //�X�V�҃R�[�h
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
            
            // ���B�`�D�ŁA�����t�̏ꍇ�i�����P�ʍs.������� == �h�O������h�j�A
            // ���b�N���̎��Y�ڍ׃e�[�u���̑Ώۍs(*1)���X�V�iDB-Update�j����B
            if (OrderTypeEnum.FEQ_SELL.equals(l_orderUnitParams.getOrderType()))
            {
                // (*1) ���b�N���̎��Y�ڍ׃e�[�u���Ώۍs�擾
                // �|�W�V�����}�l�[�W��.get���b�N���̎��Y�ڍ�()�ɂĎ擾����B
                WEB3FeqPositionManager l_positionManager =
                    (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
                LockedAssetDetailsParams l_lockedAssertDetailsParams = null;
                try
                {
                    l_lockedAssertDetailsParams = l_positionManager.getLockedAssetDetails(
                        l_orderUnitParams.getAccountId(),
                        l_orderUnitParams.getSubAccountId(),
                        l_orderUnitParams.getProductId(),
                        l_orderUnitParams.getTaxType());
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getErrorMessage(),
                        l_ex);
                }
                if (l_lockedAssertDetailsParams == null)
                {
                    log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                        + "�f�[�^�s�����G���[:���Y�ڍׂ��擾�Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                // ���b�N������
                // �i�����l�j �{ ����������
                double l_dblLockedQuanity = l_lockedAssertDetailsParams.getLockedQuantity();

                //���������ʁF
                //�@@���i��t�ς̎���j�̏ꍇ�A0�B
                double l_dblAdjustQuanity = 0D;

                //�@@���i�����ς̎���j�̏ꍇ�A
                if (this.marketResponseMessage instanceof DefaultChangeOrderSentMarketResponseMessage)
                {
                    //(*1) �����O���ʁF�@@
                    //�@@�����P��.getOrderActions()�̖߂�l�z����A
                    //�@@�i�����C�x���g�^�C�v==�h2�F�ύX�����h&&�������==�h7�F��t�ρh�j�ɓ��Ă͂܂���̂̂����A
                    //�@@��������ԍ����ő�̂��̂��擾����B
                    //�@@�擾������������.�s��m�F�ς݂̐��ʂ�����O���ʂƂ��Ďg�p����B
                    if (l_orderActionMax == null)
                    {
                        log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                            + "�f�[�^�s�����G���[:���������������擾�Ȃ��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                    }
                    FeqOrderActionRow l_l_orderActionMaxRow = (FeqOrderActionRow)l_orderActionMax.getDataSourceObject();
                    double l_dblBeforeQuanity = l_l_orderActionMaxRow.getConfirmedQuantity();

                    //�@@�����O����(*1)�|�i������j�����P��.�s�ꂩ��m�F�ςݐ���
                    l_dblAdjustQuanity = l_dblBeforeQuanity - l_orderUnitParams.getConfirmedQuantity();
                }

                //�@@���i����ς̎���j�̏ꍇ�A
                else if (this.marketResponseMessage instanceof DefaultCancelOrderSentMarketResponseMessage)
                {
                    //�@@�@@(�������ʁ|��萔��)
                    l_dblAdjustQuanity = l_orderUnitParams.getQuantity() - l_orderUnitParams.getExecutedQuantity();
                }
                l_lockedAssertDetailsParams.setLockedQuantity(l_dblLockedQuanity + l_dblAdjustQuanity);

                // �X�V�iDB-Update�j����B
                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_lockedAssertDetailsParams);
                }
                catch (DataFindException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            //end if ���B�`�D�ŁA�����t�̏ꍇ�i�����P�ʍs.������� == �h�O������h�j    
            }
        }

        // �E�@@�����ς̏ꍇ
        else if (this.marketResponseMessage instanceof DefaultChangeOrderAcceptedMarketResponseMessage)
        {
            // ���������E����敪
            l_orderUnitParams.setModifyCancelType(
                ((l_orderUnitParams.getExecutedQuantity() == Double.NaN) || (l_orderUnitParams.getExecutedQuantity() == 0))
                ? WEB3ModifyCancelTypeDef.CHANGED : WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);

            // �s�ꂩ��m�F�ς݂̒����P��
            l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.price);

            // �s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.estimated_price);
            
            //�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
            l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderUnitParams.f_estimated_price);

            // �s�ꂩ��m�F�ς݂̎��s����
            l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());

            // �X�V�҃R�[�h
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // �F�@@�����G���[�̏ꍇ
        else if (this.marketResponseMessage instanceof DefaultChangeOrderRejectedMarketResponseMessage)
        {
            //���s����:�s�ꂩ��m�F�ς݂̎��s����
            l_orderUnitParams.setExecutionConditionType(l_orderUnitParams.getConfirmedExecConditionType());
            
            //�����P��:�s�ꂩ��m�F�ς݂̒����P��
            l_orderUnitParams.setPrice(l_orderUnitParams.confirmed_order_price);
            
            //�T�Z��n���:�s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.confirmed_estimated_price);
            
            //�T�Z��n����i�O�݁j:�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
            l_orderUnitParams.setFEstimatedPrice(l_orderUnitParams.confirmed_f_estimated_price);
            
            // ���������E����敪
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGE_ERROR);

            // �X�V�҃R�[�h
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // �G�@@����ς̏ꍇ
        else if (this.marketResponseMessage instanceof DefaultCancelOrderAcceptedMarketResponseMessage)
        {
            // ���������E����敪
            l_orderUnitParams.setModifyCancelType(
			((l_orderUnitParams.getExecutedQuantity() == Double.NaN) || (l_orderUnitParams.getExecutedQuantity() == 0))
                ? WEB3ModifyCancelTypeDef.CANCELED : WEB3ModifyCancelTypeDef.PART_CANCELED);

            // �X�V�҃R�[�h
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // �H�@@����G���[�̏ꍇ
        else if (this.marketResponseMessage instanceof DefaultCancelOrderRejectedMarketResponseMessage)
        {
            // �T�Z��n���
            l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
            
            //�T�Z��n����i�O�݁j
            l_orderUnitParams.setFEstimatedPrice(l_orderUnitParams.getConfirmedFEstimatedPrice());
            
            // ���������E����敪
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCEL_ERROR);

            // �X�V�҃R�[�h
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);

        }

        // �I�@@�o�����i�����j�̏ꍇ
        else if (this.marketResponseMessage instanceof DefaultOrderInvalidatedMarketResponseMessage)
        {
            if (!l_orderUnit.isUnexecuted())
            {
                double l_dblExtimatedPrice = 0.0D;
                double l_dblFExtimatedPrice = 0.0D;
                WEB3FeqFinTransactionManager l_finTransactionManager =
                    (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
                try
                {
                    // �g�����U�N�V�����}�l�[�W��.get��n������v(�����P��)�̖߂�l�B
                    l_dblExtimatedPrice = l_finTransactionManager.getNetAmount(l_orderUnit);
                    // �g�����U�N�V�����}�l�[�W��.get��n������v�i�O�݁j(�����P��)�̖߂�l�B
                    l_dblFExtimatedPrice = l_finTransactionManager.getNetAmountFc(l_orderUnit);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                if (l_dblExtimatedPrice < 0.0D)
                {
                    l_dblExtimatedPrice *= -1D;
                }
                if (l_dblFExtimatedPrice < 0.0D)
                {
                    l_dblFExtimatedPrice =
                        new BigDecimal(String.valueOf(l_dblFExtimatedPrice)).multiply(new BigDecimal("-1")).doubleValue();
                }
                // �T�Z��n���
                l_orderUnitParams.setEstimatedPrice(l_dblExtimatedPrice);
    
                // �T�Z��n����i�O�݁j
                l_orderUnitParams.setFEstimatedPrice(l_dblFExtimatedPrice);
            }

            // �s�ꂩ��m�F�ς݂̊T�Z��n���:�T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.estimated_price);

            // �s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j:�T�Z��n����i�O�݁j
            l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderUnitParams.f_estimated_price);

            // �X�V�҃R�[�h
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);

        }

        // �J�@@�o�����i�����j�̎���̏ꍇ:
        else if (this.marketResponseMessage instanceof DefaultUndoOrderInvalidatedMarketResponseMessage)
        {
            // (*1) �����O��������
            // 1) �����������擾����B
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            // �i�����C�x���g�^�C�v==�h92�F�����ρh�j && ��������ԍ����ő�̗������擾�B
            FeqOrderAction l_orderActionMax = null;
            int l_intOrdActSerialNoMax = 0;
            boolean l_blnIsMaxAction = true;
            for (int i = 0; i < l_orderActions.length; i ++)
            {
                FeqOrderAction l_orderAction = (FeqOrderAction)l_orderActions[i];
                if (i == 0)
                {
                    l_intOrdActSerialNoMax = l_orderAction.getOrderActionSerialNo();
                }
                if (OrderEventTypeEnum.EXPIRE.equals(l_orderAction.getOrderEventType()))
                {
                    if (l_blnIsMaxAction)
                    {
                        l_blnIsMaxAction = false;
                        l_orderActionMax = l_orderAction;
                    }
                    int l_intSerialNo = l_orderAction.getOrderActionSerialNo();
                    if (l_intSerialNo > l_intOrdActSerialNoMax)
                    {
                        l_intOrdActSerialNoMax = l_intSerialNo;
                        l_orderActionMax = l_orderAction;
                    }
                }
            }
            if (l_orderActionMax == null)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                    + "�f�[�^�s�����G���[:���������������擾�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 2) ���������̒��O�i��������.��������ԍ� - 1�j�ł��钍��������
            // �����O���������Ƃ���B
            FeqOrderAction l_orderActionBeforeMax = null;
            for (int i = l_orderActions.length - 1; i >= 0 ; i --)
            {
                FeqOrderAction l_orderAction = (FeqOrderAction)l_orderActions[i];
                int l_intSerialNo = l_orderAction.getOrderActionSerialNo();
                if (l_intSerialNo == l_intOrdActSerialNoMax - 1)
                {
                    l_orderActionBeforeMax = l_orderAction;
                    break;
                }
            }
            if (l_orderActionBeforeMax == null)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                    + "�f�[�^�s�����G���[:�����O�����������擾�Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            FeqOrderActionRow l_actionRow = (FeqOrderActionRow)l_orderActionBeforeMax.getDataSourceObject();

            // �T�Z��n���
            if (l_actionRow.getEstimatedPriceIsNull())
            {
                l_orderUnitParams.setEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setEstimatedPrice(l_actionRow.getEstimatedPrice());
            }

            // �T�Z��n����i�O�݁j
            if (l_actionRow.getFEstimatedPriceIsNull())
            {
                l_orderUnitParams.setFEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setFEstimatedPrice(l_actionRow.getFEstimatedPrice());
            }

            // �s�ꂩ��m�F�ς݂̊T�Z��n���
            if (l_actionRow.getConfirmedEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(l_actionRow.getConfirmedEstimatedPrice());
            }
            
            // �s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j:�T�Z��n����i�O�݁j
            if (l_actionRow.getConfirmedFEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedFEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedFEstimatedPrice(l_actionRow.getConfirmedFEstimatedPrice());
            }

            // �X�V�҃R�[�h
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (�i���������j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X�̓����\�b�h���{�B<BR>
     * �@@-super.mutate()�����{����B<BR>
     * <BR>
     * �Q�j�@@�������s�̏ꍇ�i�p�����[�^.��������Params.�������=="�������s�i�ύX�����j"�̏ꍇ�̂݁A<BR>
     * �@@�@@xTrade�W�����ڂ̍X�V�d�l���J�X�^�}�C�Y����B<BR>
     * �@@�@@��xTrade�W�������ł́A�������s���ɂ�<BR>
     * �@@�@@���������s�������s���O�̒����P�ʂ̒l���ݒ肳��Ă��܂����߁B<BR>
     * <BR>
     * �@@�@@�����P���iprice�j�F�@@�����P��.�w�l ���Z�b�g�B<BR>
     * �@@�@@�������ʁiquantity�j�F�@@�����P�ʂ̓����ڂ̒l���Z�b�g�B<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v
     * @@param l_context - (����)<BR>
     * ����
     * @@param l_feqOrderUnitParams - (���������s)<BR>
     * ���������s�i�F��������Params�j
     * @@return FeqOrderActionParams
     */
    public FeqOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderActionParams l_feqOrderActionParams)
    {
        final String STR_METHOD_NAME =
              "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, "
            + "FeqOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        FeqOrderManager l_orderManager = (FeqOrderManager)l_tradingMod.getOrderManager();               
        FeqOrderUnit l_orderUnit = null;
        try 
        {
            l_orderUnit =
                (FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderActionParams.getOrderUnitId());
        } 
        catch (NotFoundException l_nfe) 
        {
            log.error(l_nfe.getMessage(), l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        FeqOrderUnitRow l_orderUnitRow =
            (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        l_feqOrderActionParams =
            super.mutate(
                l_updateType,
                l_context,
                l_feqOrderActionParams);
        
        if (OrderStatusEnum.NOT_MODIFIED.equals(l_feqOrderActionParams.getOrderStatus()))
        {
            l_feqOrderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
            l_feqOrderActionParams.setQuantity(l_orderUnitRow.getQuantity());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }
}
@
