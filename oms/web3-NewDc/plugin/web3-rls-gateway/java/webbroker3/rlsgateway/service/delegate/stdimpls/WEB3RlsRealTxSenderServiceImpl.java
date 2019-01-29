head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsRealTxSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���ۃ��[���G���W���֑��M�g�����U�N�V�����̏����T�[�r�X�����N���X(WEB3RlsRealTxSenderService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/14 ��(FLJ) �V�K�쐬
                  : 2007/07/05 ��(FLJ) �[��Ή� ����/�񓯊��ʒm���fAPI�ύX
 */
package webbroker3.rlsgateway.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.*;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.message.*;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;
import webbroker3.common.*;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.rlsgateway.*;
import webbroker3.rlsgateway.connector.*;
import webbroker3.rlsgateway.data.*;
import webbroker3.rlsgateway.define.*;
import webbroker3.rlsgateway.service.*;
import webbroker3.triggerorder.base.data.*;
import webbroker3.util.*;

/**
 *
 * ���ۃ��[���G���W���֑��M�g�����U�N�V�����̏����T�[�r�X����
 * @@author �� (FLJ)
 * @@version 1.0
 */
public class WEB3RlsRealTxSenderServiceImpl
    implements WEB3RlsRealTxSenderService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsRealTxSenderServiceImpl.class);

    /**
     * �i�A���������ʒm�j<br />
     * <br />
     * ���[���G���W���ɑ΂��āA�A���������ʒm���b�Z�[�W�𑗐M�B<br />
     * <br />
     * @@param l_context - ���M���e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendConOrderExecuteMessage(WEB3RlsConOrderExecuteMessageContext
                                           l_context) throws
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " sendExecuteMessage(SubAccount,Long ,ProductTypeEnum,String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            log.debug(l_context.toString());
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            SubAccount l_subaccount = l_context.getSubAccount();
            Long l_lngConOrderId = l_context.getConOrderId();
            ProductTypeEnum l_productType = l_context.getConOrderProductType();

            OmsConOrderRequestParams l_params = new OmsConOrderRequestParams();
            l_params.setAccountId(l_subaccount.getAccountId());
            l_params.setSubAccountId(l_subaccount.getSubAccountId());
            l_params.setProductType(l_productType);
            l_params.setOrderId(l_lngConOrderId.longValue());
            l_params.setOrderType(WEB3RlsNotifyOrderTypeDef.EXECUTE);
            l_params.setRequestType(WEB3RlsRequestTypeDef.EXECUTE);
            l_params.setStatus(WEB3RlsNotifyStatusDef.NOT_DEAL);
            l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_params.setLastUpdatedTimestamp(l_params.getCreatedTimestamp());
            l_qp.doInsertQuery(l_params);

            String l_mode = GtlUtils.getTradingSystem().getPreference(
                WEB3RlsGatewayPlugin.STR_RLS_SERVER_ORDER_SUBMIT_MODE_KEY);
            try
            {

                if (l_mode == null)
                {
                    //���[�J������
                    submitLocalCondOrder(l_params.getAccountId(),
                                         l_params.getSubAccountId(),
                                         l_params.getOrderId()
                                         );
                }

            }
            catch (WEB3BaseException l_exp)
            {
                ErrorInfo l_errorInfo = l_exp.getErrorInfo();
                l_errorInfo.setErrorClass(l_exp.getClass().getName());
                throw new DataCallbackException(
                    l_exp.getErrorMessage(),
                    l_errorInfo);
            }

        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * ���[���G���W���o�R�����A���[�J�������t���q�����̂𔭒�����B�@@<br />
     * <br />
     * @@param l_lngAccountId - ����ID<br />
     * @@param l_lngSubAccountId - �⏕����ID<br />
     * @@param l_lngConOrderId - ����ID<br />
     * <br />
     */
    private void submitLocalCondOrder(long l_lngAccountId,
                                      long l_lngSubAccountId,
                                      long l_lngConOrderId
                                      ) throws
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " submitLocalRlsCondOrder(long,long,long)";

        log.entering(STR_METHOD_NAME);

        try
        {
            log.debug("l_lngAccountId=" + l_lngAccountId);
            log.debug("l_lngSubAccountId=" + l_lngSubAccountId);
            log.debug("l_lngConOrderId=" + l_lngConOrderId);

            List l_rsvOrderUnitList = getRsvOrderUnitList(l_lngAccountId,
                l_lngSubAccountId, l_lngConOrderId);

            if (l_rsvOrderUnitList == null || l_rsvOrderUnitList.size() == 0)
            {
                log.debug("�\��q���������݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                return;
            }

            for (int i = 0; i < l_rsvOrderUnitList.size(); i++)
            {

                RsvEqOrderUnitRow l_row = (RsvEqOrderUnitRow) l_rsvOrderUnitList.get(i);
                RlsConOrderHitNotifyParams l_hitNotifyParams = new
                    RlsConOrderHitNotifyParams();
                log.debug("�\��q����=" + l_row.getRowType() + ">>>" + l_row);
                l_hitNotifyParams.setAccountId(l_row.getAccountId());
                l_hitNotifyParams.setSubAccountId(l_row.getSubAccountId());
                l_hitNotifyParams.setOrderId(l_row.getOrderId());
                l_hitNotifyParams.setOrderType(WEB3RlsNotifyOrderTypeDef.EXECUTE);
                l_hitNotifyParams.setProductType(l_row.getProductType());
                l_hitNotifyParams.setParentOrderId(l_row.getParentOrderId());
                l_hitNotifyParams.setParentProductType(ProductTypeEnum.EQUITY);
                l_hitNotifyParams.setSerialNoInParent(l_row.getSerialNoInParent());
                l_hitNotifyParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_hitNotifyParams.setLastUpdatedTimestamp(l_hitNotifyParams.
                    getCreatedTimestamp());
                log.debug("loopback���[���G���W���ʒm=" + l_hitNotifyParams.getRowType() + ">>>" +
                          l_hitNotifyParams);
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_hitNotifyParams);
            }

        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�\��q�����P�ʈꗗ)<BR>
     * ����ڋq�A����\��e��������\��q�����̈ꗗ��ԋp����B<BR>
     * @@param l_lngAccountId - ����ID
     * @@param l_lngAccountId - �⏕�����h�c
     * @@param l_lngAccountId - �e�����̒���ID
     * @@return List
     * @@exception WEB3SystemLayerException
     */
    private List getRsvOrderUnitList(long l_lngAccountId, long l_lngSubAccountId,
                                     long l_lngConOrderId) throws
        WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getRsvOrderUnitList(long,long,long)";
        log.entering(STR_METHOD_NAME);

        //�\��q�������擾����B
        //�ȉ��̏����ɂāA�����\�񒍕��P�ʃe�[�u����
        //   ��������B
        //[����]
        //  ����ID = �p�����[�^.����ID
        //  �⏕�����h�c = �p�����[�^.�⏕�����h�c
        //  �e�����̒���ID = �p�����[�^.�e�����̒���ID
        //  [�\�[�g]
        //  �e�������A�� ����
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and sub_account_id = ? ");
        l_sbWhere.append(" and parent_order_id = ? ");

        List l_lstWhere = new ArrayList();
        l_lstWhere.add(new Long(l_lngAccountId));
        l_lstWhere.add(new Long(l_lngSubAccountId));
        l_lstWhere.add(new Long(l_lngConOrderId));

        Object[] l_objWhere = new Object[l_lstWhere.size()];
        l_lstWhere.toArray(l_objWhere);

        String l_strOrderBy = " serial_no_in_parent asc ";
        List l_lstRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                RsvEqOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                + "."
                + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                + "."
                + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                + "."
                + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lstRecords;
    }

    /**
     * �i�����t���������o�^�j<br /> <br />
     * ���[���G���W���ɑ΂��āA�����t�����o�^���b�Z�[�W�𑗐M�B<br />
     * <br />
     * @@param l_context - ���M���e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendRegisterConOrdersMessage(WEB3RlsRegisterConOrdersMessageContext
                                             l_context) throws
        WEB3SystemLayerException, WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " sendRegisterConOrdersMessage(WEB3RlsRegisterConOrdersMessageContext)";
        log.entering(STR_METHOD_NAME);

        log.debug(l_context.toString());
        try
        {

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            SubAccount l_subaccount = l_context.getSubAccount();
            Long l_lngConOrderId = l_context.getConOrderId();
            ProductTypeEnum l_productType = l_context.getConOrderProductType();
            int l_intOrderType = l_context.getConOrderType();
            int l_intRequestType = WEB3RlsRequestTypeDef.NEW;

            Long[] l_SubOrderIds = l_context.getSubOrderIds();
            ProductTypeEnum[] l_subOrderProductTypes = l_context.getSubOrderProductTypes();
            if (l_SubOrderIds != null && l_SubOrderIds.length > 0)
            {
                for (int i = 0; i < l_SubOrderIds.length; i++)
                {

                    OmsConOrderRequestParams l_params = new OmsConOrderRequestParams();
                    //�������
                    l_params.setAccountId(l_subaccount.getAccountId());
                    l_params.setSubAccountId(l_subaccount.getSubAccountId());
                    l_params.setProductType(l_productType);
                    l_params.setOrderId(l_lngConOrderId.longValue());
                    l_params.setOrderType(l_intOrderType);
                    l_params.setRequestType(l_intRequestType);
                    l_params.setStatus(WEB3RlsNotifyStatusDef.NOT_DEAL);
                    l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_params.setLastUpdatedTimestamp(l_params.getCreatedTimestamp());
                    //�q����
                    l_params.setSubOrderId(l_SubOrderIds[i].longValue());
                    l_params.setSubProductType(l_subOrderProductTypes[i]);
                    l_qp.doInsertQuery(l_params);
                    if (forward2Rls(l_params))
                    {
                        l_params.setStatus(WEB3RlsNotifyStatusDef.DEAL);
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_params.setRlsAcceptedTimestamp(l_params.getLastUpdatedTimestamp());
                        l_qp.doUpdateQuery(l_params);
                    }
                }
            }
            else
            {
                OmsConOrderRequestParams l_params = new OmsConOrderRequestParams();
                //�������
                l_params.setAccountId(l_subaccount.getAccountId());
                l_params.setSubAccountId(l_subaccount.getSubAccountId());
                l_params.setProductType(l_productType);
                l_params.setOrderId(l_lngConOrderId.longValue());
                l_params.setOrderType(l_intOrderType);
                l_params.setRequestType(l_intRequestType);
                l_params.setSubOrderId(0);
                l_params.setSubProductType(ProductTypeEnum.OTHER);
                l_params.setStatus(WEB3RlsNotifyStatusDef.DEAL);
                l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_params.setLastUpdatedTimestamp(l_params.getCreatedTimestamp());

                deliver2Rls(l_params);
            }
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �i�����t�������������j<br /> <br />
     * ���[���G���W���ɑ΂��āA�����t�����������b�Z�[�W�𑗐M<br />
     * <br />
     * @@param l_context - ���M���e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendModifyConOrdersMessage(WEB3RlsModifyConOrdersMessageContext
                                           l_context) throws
        WEB3SystemLayerException, WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " sendModifyConOrdersMessage(WEB3RlsModifyConOrdersMessageContext)";
        log.entering(STR_METHOD_NAME);

        log.debug(l_context.toString());
        try
        {

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            SubAccount l_subaccount = l_context.getSubAccount();
            Long l_lngConOrderId = l_context.getConOrderId();
            ProductTypeEnum l_productType = l_context.getConOrderProductType();
            int l_intOrderType = l_context.getConOrderType();
            int l_intRequestType = WEB3RlsRequestTypeDef.MOTIFY;

            Long[] l_SubOrderIds = l_context.getSubOrderIds();
            ProductTypeEnum[] l_subOrderProductTypes = l_context.getSubOrderProductTypes();
            if (l_SubOrderIds != null && l_SubOrderIds.length > 0)
            {
                for (int i = 0; i < l_SubOrderIds.length; i++)
                {

                    OmsConOrderRequestParams l_params = new OmsConOrderRequestParams();
                    //�������
                    l_params.setAccountId(l_subaccount.getAccountId());
                    l_params.setSubAccountId(l_subaccount.getSubAccountId());
                    l_params.setProductType(l_productType);
                    l_params.setOrderId(l_lngConOrderId.longValue());
                    l_params.setOrderType(l_intOrderType);
                    l_params.setRequestType(l_intRequestType);
                    l_params.setStatus(WEB3RlsNotifyStatusDef.NOT_DEAL);
                    l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_params.setLastUpdatedTimestamp(l_params.getCreatedTimestamp());
                    //�q����
                    l_params.setSubOrderId(l_SubOrderIds[i].longValue());
                    l_params.setSubProductType(l_subOrderProductTypes[i]);

                    List list = l_qp.doFindAllQuery(
                        OmsConOrderRequestRow.TYPE,
                        "account_id=? and sub_account_id=? and order_type=? and request_type like ? and order_id=? and product_type=? and sub_order_id=? and sub_product_type=?",
                        null,
                        new Object[]
                        {new Long(l_params.getAccountId()),
                        new Long(l_params.getSubAccountId()),
                        new Long(l_params.getOrderType()),
                        new String(l_params.getRequestType() + "%"),
                        new Long(l_params.getOrderId()), l_params.getProductType(),
                        new Long(l_params.getSubOrderId()),
                        l_params.getSubProductType()});

                    int i_size = list.size();
                    if (i_size > 0)
                    {
                        l_params.setRequestType(
                            Integer.parseInt(Integer.toString(l_intRequestType) +
                                             Integer.toString(i_size)));
                    }
                    l_qp.doInsertQuery(l_params);
                    if (forward2Rls(l_params))
                    {
                        l_params.setStatus(WEB3RlsNotifyStatusDef.DEAL);
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_params.setRlsAcceptedTimestamp(l_params.getLastUpdatedTimestamp());
                        l_qp.doUpdateQuery(l_params);
                    }
                }
            }
            else
            {
                OmsConOrderRequestParams l_params = new OmsConOrderRequestParams();
                //�������
                l_params.setAccountId(l_subaccount.getAccountId());
                l_params.setSubAccountId(l_subaccount.getSubAccountId());
                l_params.setProductType(l_productType);
                l_params.setOrderId(l_lngConOrderId.longValue());
                l_params.setOrderType(l_intOrderType);
                l_params.setRequestType(l_intRequestType);
                l_params.setStatus(WEB3RlsNotifyStatusDef.DEAL);
                l_params.setSubOrderId(0);
                l_params.setSubProductType(ProductTypeEnum.OTHER);
                l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_params.setLastUpdatedTimestamp(l_params.getCreatedTimestamp());

                List list = l_qp.doFindAllQuery(
                    OmsConOrderRequestRow.TYPE,
                    "account_id=? and sub_account_id=? and order_type=? and request_type like ? and order_id=? and product_type=? and sub_order_id=? and sub_product_type=?",
                    null,
                    new Object[]
                    {new Long(l_params.getAccountId()), new Long(l_params.getSubAccountId()),
                    new Long(l_params.getOrderType()),
                    new String(l_params.getRequestType() + "%"),
                    new Long(l_params.getOrderId()), l_params.getProductType(),
                    new Long(l_params.getSubOrderId()),
                    l_params.getSubProductType()});

                int i_size = list.size();
                if (i_size > 0)
                {
                    l_params.setRequestType(
                        Integer.parseInt(Integer.toString(l_intRequestType) +
                                         Integer.toString(i_size)));
                }

                deliver2Rls(l_params);
            }
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �i�����t������������j<br />
     * <br />
     * ���[���G���W���ɑ΂��āA�����t����������b�Z�[�W�𑗐M�B<br />
     * <br />
     * @@param l_context - ���M���e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendCancelConOrderMessage(WEB3RlsCancelConOrdersMessageContext l_context) throws
        WEB3SystemLayerException, WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " sendCancelConOrderMessage(WEB3RlsCancelConOrdersMessageContext)";

        log.entering(STR_METHOD_NAME);

        log.debug(l_context.toString());
        try
        {

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            SubAccount l_subaccount = l_context.getSubAccount();
            Long l_lngConOrderId = l_context.getConOrderId();
            ProductTypeEnum l_productType = l_context.getConOrderProductType();
            int l_intOrderType = l_context.getConOrderType();
            int l_intRequestType = WEB3RlsRequestTypeDef.CANCEL;

            OmsConOrderRequestParams l_params = new OmsConOrderRequestParams();
            //�������
            l_params.setAccountId(l_subaccount.getAccountId());
            l_params.setSubAccountId(l_subaccount.getSubAccountId());
            l_params.setProductType(l_productType);
            l_params.setOrderId(l_lngConOrderId.longValue());
            l_params.setOrderType(l_intOrderType);
            l_params.setRequestType(l_intRequestType);
            l_params.setSubOrderId(0);
            l_params.setSubProductType(ProductTypeEnum.OTHER);
            l_params.setStatus(WEB3RlsNotifyStatusDef.DEAL);
            l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_params.setLastUpdatedTimestamp(l_params.getCreatedTimestamp());

            OmsConOrderRequestRow l_dbExistRow = null;
            try
            {
                //�����̎�����N�G�X�g�����邱�Ƃ�����̂ŁA��Ӑ���G���[�ɂȂ�Ȃ������O�`�F�b�N
                l_dbExistRow= OmsConOrderRequestDao.findRowByPk(l_params.getAccountId(), l_params.getSubAccountId(),
                                                             l_params.getOrderType(), l_params.getRequestType(),
                                                             l_params.getOrderId(), l_params.getProductType(),
                                                             l_params.getSubOrderId(), l_params.getSubProductType());
            }
            catch (DataFindException e)
            {
                log.debug("���̒����ɑ΂��čŏ��̎�����N�G�X�g�F" + l_params);
            }

            //���Ƀ��R�[�h�����݂���ꍇ(��Ӑ���G���[�ɂȂ�ꍇ)
            if(l_dbExistRow != null)
            {
                log.debug("��Ӑ���ɂȂ�̂Ń��[���G���W���Ƀ��N�G�X�g���Ȃ��F" + l_params);
                log.exiting(STR_METHOD_NAME);
                return;
            }

            deliver2Rls(l_params);
        }
        catch (DataException l_ex)
        {
            //��������ł̃G���[�̓��O�ɏo�͂��ă��\�b�h����I��������
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
        }
        catch (Throwable l_t)
        {
            //��������ł̃G���[�̓��O�ɏo�͂��ă��\�b�h����I��������
            log.error(l_t.getMessage(), l_t);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �i�蓮�����t�������������j<br />
     * <br />
     * �蓮�����t�������������B<br />
     * <br />
     * @@param l_context - �����������e�R���e�N�X�g<br />
     * <br />
     * @@throws WEB3SystemLayerException
     * @@throws WEB3BaseException
     */
    public void sendManualSubmitConOrder(WEB3RlsManualSubmitConOrderMessageContext
                                         l_context) throws
        WEB3SystemLayerException, WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " sendManualSubmitConOrder(WEB3RlsForceSubmitConOrdersMessageContext)";
        log.entering(STR_METHOD_NAME);
        log.debug(l_context.toString());
        try
        {

            RlsConOrderHitNotifyParams l_hitNotifyParams = new
                RlsConOrderHitNotifyParams();
            l_hitNotifyParams.setAccountId(l_context.getSubAccount().getAccountId());
            l_hitNotifyParams.setSubAccountId(l_context.getSubAccount().getSubAccountId());
            l_hitNotifyParams.setOrderId(l_context.getConOrderId().longValue());
            l_hitNotifyParams.setOrderType(l_context.getConOrderType());
            l_hitNotifyParams.setProductType(l_context.getConOrderProductType());
            l_hitNotifyParams.setParentOrderId(l_context.getParentOrderId());
            l_hitNotifyParams.setParentProductType(l_context.getParentOrderProductType());
            l_hitNotifyParams.setSerialNoInParent(l_context.getSerialNoInParent());
            l_hitNotifyParams.setNotifyType(l_context.getSubmitNotifyType());
            l_hitNotifyParams.setSubmitterLoginId(l_context.getSubmitterLoginId());
            l_hitNotifyParams.setStatus(WEB3RlsNotifyStatusDef.NOT_DEAL);
            l_hitNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            RlsConOrderHitNotifyRow l_oldRow = (RlsConOrderHitNotifyRow)
                RlsConOrderHitNotifyDao.
                findRowByAccountIdSubAccountIdOrderTypeOrderIdProductType(
                l_hitNotifyParams.getAccountId(), l_hitNotifyParams.getSubAccountId(),
                l_hitNotifyParams.getOrderType(), l_hitNotifyParams.getOrderId(),
                l_hitNotifyParams.getProductType());

            if (l_oldRow == null)
            {
                l_hitNotifyParams.setCreatedTimestamp(l_hitNotifyParams.
                    getLastUpdatedTimestamp());

                RlsManualSubmitHistoryParams l_forceSubmitParams = new
                    RlsManualSubmitHistoryParams();
                l_forceSubmitParams.setHistoryId(RlsManualSubmitHistoryDao.newPkValue());
                l_forceSubmitParams.setAccountId(l_context.getSubAccount().getAccountId());
                l_forceSubmitParams.setSubAccountId(l_context.getSubAccount().
                    getSubAccountId());
                l_forceSubmitParams.setOrderId(l_context.getConOrderId().longValue());
                l_forceSubmitParams.setOrderType(l_context.getConOrderType());
                l_forceSubmitParams.setProductType(l_context.getConOrderProductType());
                l_forceSubmitParams.setParentOrderId(l_context.getParentOrderId());
                l_forceSubmitParams.setParentProductType(l_context.
                    getParentOrderProductType());
                l_forceSubmitParams.setSerialNoInParent(l_context.getSerialNoInParent());

                l_forceSubmitParams.setNotifyType(l_context.getSubmitNotifyType());
                l_forceSubmitParams.setSubmitterLoginId(l_context.getSubmitterLoginId());

                l_forceSubmitParams.setCreatedTimestamp(l_hitNotifyParams.
                    getCreatedTimestamp());
                l_forceSubmitParams.setLastUpdatedTimestamp(l_hitNotifyParams.
                    getLastUpdatedTimestamp());

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                log.debug("�蓮�����ʒm=" + l_hitNotifyParams.getRowType() + ">>>" +
                          l_hitNotifyParams);
                l_queryProcessor.doInsertQuery(l_hitNotifyParams);
                log.debug("�蓮��������=" + l_forceSubmitParams.getRowType() + ">>>" +
                          l_forceSubmitParams);
                l_queryProcessor.doInsertQuery(l_forceSubmitParams);

            }
            else
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                RlsManualSubmitHistoryParams l_forceSubmitParams = new
                    RlsManualSubmitHistoryParams();
                l_forceSubmitParams.setHistoryId(RlsManualSubmitHistoryDao.newPkValue());
                l_forceSubmitParams.setAccountId(l_context.getSubAccount().getAccountId());
                l_forceSubmitParams.setSubAccountId(l_context.getSubAccount().
                    getSubAccountId());
                l_forceSubmitParams.setOrderId(l_context.getConOrderId().longValue());
                l_forceSubmitParams.setOrderType(l_context.getConOrderType());
                l_forceSubmitParams.setProductType(l_context.getConOrderProductType());
                if (!l_oldRow.getParentOrderIdIsNull())
                {
                    l_forceSubmitParams.setParentOrderId(l_oldRow.getParentOrderId());
                }
                l_forceSubmitParams.setParentProductType(l_oldRow.getParentProductType());
                l_forceSubmitParams.setSerialNoInParent(l_oldRow.getSerialNoInParent());
                l_forceSubmitParams.setStatus(l_oldRow.getStatus());
                l_forceSubmitParams.setHitTickTimestamp(l_oldRow.getHitTickTimestamp());
                l_forceSubmitParams.setRlsHitTimestamp(l_oldRow.
                    getRlsHitTimestamp());
                l_forceSubmitParams.setOrderSubmitErrorCode(l_oldRow.
                    getOrderSubmitErrorCode());

                l_forceSubmitParams.setNotifyType(l_oldRow.getNotifyType());

                if (!l_oldRow.getSubmitterLoginIdIsNull())
                {
                    l_forceSubmitParams.setSubmitterLoginId(l_oldRow.getSubmitterLoginId());
                }
                l_forceSubmitParams.setOrderSubmitTimestamp(l_oldRow.
                    getOrderSubmitTimestamp());

                l_forceSubmitParams.setCreatedTimestamp(l_oldRow.getCreatedTimestamp());
                l_forceSubmitParams.setLastUpdatedTimestamp(l_oldRow.
                    getLastUpdatedTimestamp());

                l_hitNotifyParams.setCreatedTimestamp(l_oldRow.getCreatedTimestamp());

                log.debug("�蓮�����ʒm=" + l_hitNotifyParams.getRowType() + ">>>" +
                          l_hitNotifyParams);
                l_queryProcessor.doUpdateQuery(l_hitNotifyParams);
                log.debug("�蓮��������=" + l_forceSubmitParams.getRowType() + ">>>" +
                          l_forceSubmitParams);
                l_queryProcessor.doInsertQuery(l_forceSubmitParams);

            }

        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * forward2Rls
     *
     * @@param l_params OmsConOrderRequestParams
     * @@throws WEB3BaseException
     */
    private boolean forward2Rls(OmsConOrderRequestParams l_params) throws
        WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " forward2Rls(OmsConOrderRequestParams)";

        log.entering(STR_METHOD_NAME);

        boolean l_ret = true;
        if (WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT == l_params.getOrderType()
            || WEB3RlsNotifyOrderTypeDef.W_LlIMIT == l_params.getOrderType())
        {
            if (ProductTypeEnum.EQUITY.equals(l_params.getProductType()))
            {

                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                EqTypeOrderManager l_orderManager =
                    (EqTypeOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).
                    getOrderManager();
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_params.
                    getOrderId());
                EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) l_orderUnits[0];

                if (isRlsIndirectOrder(l_orderUnit))
                {
                    log.debug("isNextDayOrder:" +
                              l_orderUnit.getDataSourceObject().toString());
                    log.exiting(STR_METHOD_NAME);
                    l_ret = false;
                    return l_ret;
                }

                int l_intRequestType = l_params.getRequestType();
                if(l_intRequestType > 9)
                {
                    //���N�G�X�g�^�C�v��1���ڂ��擾
                    String l_strRequestType = String.valueOf(l_intRequestType);
                    l_intRequestType = Integer.valueOf(l_strRequestType.substring(0, 1)).intValue();
                }

                WEB3RlsConnectorService l_rlsService = (WEB3RlsConnectorService) Services.
                    getService(WEB3RlsConnectorService.class);
                if (l_intRequestType == WEB3RlsRequestTypeDef.NEW)
                {
                    l_rlsService.register(l_params, l_orderUnit);
                }
                else if (l_intRequestType == WEB3RlsRequestTypeDef.MOTIFY)
                {
                    l_rlsService.modify(l_params, l_orderUnit);
                }
                else if (l_intRequestType == WEB3RlsRequestTypeDef.CANCEL)
                {
                    l_rlsService.cancel(l_params, l_orderUnit);
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new IllegalArgumentException("No implementation. [RequestType=" +
                        l_params.getRequestType() + "]");
                }
            }
            else if (ProductTypeEnum.IFO.equals(l_params.getProductType()))
            {
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                IfoOrderManager l_orderManager =
                    (IfoOrderManager) l_finApp.getTradingModule(ProductTypeEnum.IFO).
                    getOrderManager();
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_params.
                    getOrderId());
                IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderUnits[0];

                if (isRlsIndirectOrder(l_orderUnit))
                {
                    log.debug("isNextDayOrder:" +
                              l_orderUnit.getDataSourceObject().toString());
                    log.exiting(STR_METHOD_NAME);
                    l_ret = false;
                    return l_ret;
                }

                WEB3RlsConnectorService l_rlsService = (WEB3RlsConnectorService) Services.
                    getService(WEB3RlsConnectorService.class);

                int l_intRequestType = l_params.getRequestType();
                if(l_intRequestType > 9)
                {
                    //���N�G�X�g�^�C�v��1���ڂ��擾
                    String l_strRequestType = String.valueOf(l_intRequestType);
                    l_intRequestType = Integer.valueOf(l_strRequestType.substring(0, 1)).intValue();
                }

                if (l_intRequestType== WEB3RlsRequestTypeDef.NEW)
                {
                    l_rlsService.register(l_params, l_orderUnit);
                }
                else if (l_intRequestType== WEB3RlsRequestTypeDef.MOTIFY)
                {
                    l_rlsService.modify(l_params, l_orderUnit);
                }
                else if (l_intRequestType == WEB3RlsRequestTypeDef.CANCEL)
                {
                    l_rlsService.cancel(l_params, l_orderUnit);
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new IllegalArgumentException("No implementation. [RequestType=" +
                        l_params.getRequestType() + "]");
                }

            }

        }
        log.exiting(STR_METHOD_NAME);
        return l_ret;
    }

    /**
     * isRlsIndirectOrder
     *
     * @@param l_orderUnit OrderUnit
     * @@return boolean
     */
    private boolean isRlsIndirectOrder(OrderUnit l_orderUnit)
    {

        final String STR_METHOD_NAME =
            " isRlsIndirectOrder(OrderUnit)";

        log.entering(STR_METHOD_NAME);

        boolean l_isRlsIndirectOrder = true;

        try
        {
            /* 2007/07/05 �[��Ή� ����/�񓯊��ʒm���fAPI�ύX
            //�s��J�ǎ��ԑт����肷��
            boolean l_isTradeOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();

            //�s��J�ǎ��ԑ�=true�̏ꍇ�A�Ԑڒ����t���O=false, �s��J�ǎ��ԑ�=false�̏ꍇ�A�Ԑڒ����t���O=true
            l_isRlsIndirectOrder = ! l_isTradeOpenTimeZone;
            */

            //����/�񓯊��ʒm���f
            l_isRlsIndirectOrder = WEB3GentradeTradingTimeManagement.isRlsAsyncTreatmentTimeZone();
            log.debug("isRlsAsyncTreatmentTimeZone="+l_isRlsIndirectOrder);

        }
        catch (WEB3SystemLayerException wse)
        {
            log.error(STR_METHOD_NAME,wse);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                wse.getErrorInfo(),
                wse.getErrorMethod(),
                wse.getMessage(),
                wse);
        }

        log.exiting(STR_METHOD_NAME);
        return l_isRlsIndirectOrder;

    }

    /**
     * deliver2Rls
     *
     * @@param l_params OmsConOrderRequestParams
     * @@throws WEB3BaseException
     * @@throws DataException
     */
    private void deliver2Rls(OmsConOrderRequestParams l_params) throws
    WEB3BaseException, DataException
    {

        final String STR_METHOD_NAME =
            " deliver2Rls(OmsConOrderRequestParams)";

        log.entering(STR_METHOD_NAME);

        QueryProcessor l_qp = Processors.getDefaultProcessor();

        //new tx �ŃC���T�[�g
        WEB3RlsSenderInsertTransactionCallback l_requestInsTxCallback = new WEB3RlsSenderInsertTransactionCallback(l_params);
        l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_requestInsTxCallback);
        try
        {
            if (forward2Rls(l_params))
            {
                l_params.setStatus(WEB3RlsNotifyStatusDef.DEAL);
                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_params.setRlsAcceptedTimestamp(l_params.getLastUpdatedTimestamp());
                l_qp.doUpdateQuery(l_params);
            }
            else
            {
                l_params.setStatus(WEB3RlsNotifyStatusDef.NOT_DEAL);
                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_qp.doUpdateQuery(l_params);
            }
        }
        catch (WEB3BaseException l_bex)
        {
            try
            {
                //new tx �Ńf���[�g
                WEB3RlsSenderDeleteTransactionCallback l_requestDelTxCallback = new WEB3RlsSenderDeleteTransactionCallback(l_params);
                l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_requestDelTxCallback);
            }
            catch (DataException l_dex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_dex);
            }
            log.exiting(STR_METHOD_NAME);
            throw l_bex;
        }
    }

    /**
     * ���[���G���W���ւ̃��N�G�X�g�e�[�u���C���T�[�gTransactionCallback<BR>
     * <BR>
     * @@author Eizo Saito
     * @@version 1.0
     */
    public class WEB3RlsSenderInsertTransactionCallback implements TransactionCallback
    {
        private OmsConOrderRequestParams requestParams;

        public WEB3RlsSenderInsertTransactionCallback(OmsConOrderRequestParams l_params)
        {
            requestParams = l_params;
        }

        /**
         * @@see com.fitechlabs.xtrade.kernel.data.TransactionCallback#process()
         */
        public Object process() throws DataNetworkException,
                DataQueryException, DataCallbackException
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_qp.doInsertQuery(requestParams);
            return null;
        }
    }

    /**
     * ���[���G���W���ւ̃��N�G�X�g�e�[�u���폜TransactionCallback<BR>
     * <BR>
     * @@author Eizo Saito
     * @@version 1.0
     */
    public class WEB3RlsSenderDeleteTransactionCallback implements TransactionCallback
    {
        private OmsConOrderRequestParams requestParams;

        public WEB3RlsSenderDeleteTransactionCallback(OmsConOrderRequestParams l_params)
        {
            requestParams = l_params;
        }

        /**
         * @@see com.fitechlabs.xtrade.kernel.data.TransactionCallback#process()
         */
        public Object process() throws DataNetworkException,
                DataQueryException, DataCallbackException
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_qp.doDeleteQuery(requestParams.getPrimaryKey());
            return null;
        }
    }
}
@
