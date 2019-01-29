head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�o���ʒm���폈���ꌏTransactionCallback(WEB3FuturesOrderExecNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ���u��(���{���u) �V�K�쐬
Revesion History : 2007/04/25 �����Q (���u) �d�l�ύX���f��No.634
Revesion History : 2008/04/28 ���z (���u) �d�l�ύX���f��No.881
Revesion History : 2008/08/28 ���z (���u) �d�l�ύX�c�a���C�A�E�g090
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow;
import webbroker3.ifo.data.HostOptionOrderExecNotifyParams;
import webbroker3.ifo.data.HostOptionOrderExecNotifyRow;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����w���敨�o���ʒm���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3FuturesOrderExecNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderExecNotifyNormalTransactionCallback.class);

    /**
      * �敨OP�o���ʒm�L���[Row�I�u�W�F�N�g�B<BR>
      */
    private HostOptionOrderExecNotifyRow hostOptionOrderExecNotifyRow;

    /**
      * �敨OP�o���ʒm�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostOptionOrderExecNotifyParams hostOptionOrderExecNotifyParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostOptionOrderExecNotifyRow - (�敨OP�o���ʒm�L���[Row)
     * @@params l_hostOptionOrderExecNotifyParams - (�敨OP�o���ʒm�L���[Params)
     */
    public WEB3FuturesOrderExecNotifyNormalTransactionCallback(
        HostOptionOrderExecNotifyRow l_hostOptionOrderExecNotifyRow,
        HostOptionOrderExecNotifyParams l_hostOptionOrderExecNotifyParams)
    {
        hostOptionOrderExecNotifyRow = l_hostOptionOrderExecNotifyRow;
        hostOptionOrderExecNotifyParams = l_hostOptionOrderExecNotifyParams;
    }

    /**
     * �g�����U�N�V�������������{����B<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);


        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        HostFotypeCloseOrderNotifyParams l_closeParams = null;
        HostFotypeOrderClmdNotifyParams l_cancelParams = null;
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

        HostOptionOrderExecNotifyRow l_notifyRow = hostOptionOrderExecNotifyRow;
        HostOptionOrderExecNotifyParams l_notifyParams = hostOptionOrderExecNotifyParams;

        try
        {
            String l_strInstitutionCode = l_notifyRow.getInstitutionCode();
            String l_strBranchCode = l_notifyRow.getBranchCode();

            //1.2.1 get�����P��(String, String, ProductTypeEnum, String)
            String l_orderRequestNumber =  l_notifyRow.getOrderRequestNumber();
            OrderUnit l_orderUnit = null;
            
            try
            {
                l_orderUnit = l_orderManager.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    ProductTypeEnum.IFO,
                    l_orderRequestNumber);
            }
            catch (WEB3BaseException l_ex)
            {
                if (l_ex instanceof WEB3SystemLayerException)
                {
                    WEB3SystemLayerException l_wre = (WEB3SystemLayerException)l_ex;
                    if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80017 &&
                        l_wre.getException() == null)
                    {
                        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
                        String l_strPreference = l_tradingSystem.getPreference(
                            WEB3SystemPreferencesNameDef.EXEC_NOTIFY_WAIT_SECONDS);
                        if (WEB3StringTypeUtility.isEmpty(l_strPreference))
                        {
                            l_strPreference = "0";
                        }
                      
                        //�����ʒm��҂����肷��
                        long l_lngPreference = Long.parseLong(l_strPreference);
                        Date l_datAddDate = WEB3DateUtility.addSecond(l_notifyRow.getCreatedTimestamp(),
                            l_lngPreference);
                        Date l_datCurrentDate = GtlUtils.getSystemTimestamp();
                        if (WEB3DateUtility.compareToSecond(l_datCurrentDate, l_datAddDate) > 0)
                        {
                            log.debug("�Y������f�[�^�����݂��܂���B");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "�Y���f�[�^�Ȃ�");
                        }
                        else
                        {
                            //�ʒm�L���[.�X�V���t���X�V���A���L�������X�L�b�v����B
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02752,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "�����P�ʂ��擾�o���Ȃ��B");
                        }
                    }
                }
                throw new WEB3BaseException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage());
            }

            //�������F�@@�o���ʒm�L���[.������
            Timestamp l_tsExecTimestamp = l_notifyRow.getExecTimestamp();

            //��萔�ʁF�@@�o���ʒm�L���[.��萔��
            double l_dblExecQuantity = l_notifyRow.getExecQuantity();

            //���P���F�@@�o���ʒm�L���[.���P��
            double l_dblExecPrice = l_notifyRow.getExecPrice();

            //�o���ʒm�敪�F�@@�o���ʒm�L���[.�o���ʒm�敪
            String l_strDealedType = l_notifyRow.getDealedType();

            // �敨�o���ʒmUnitServiceImpl
            WEB3FuturesOrderExecNotifyUnitService l_orderExecNotifyUnitService =
                (WEB3FuturesOrderExecNotifyUnitService) Services.getService(
                    WEB3FuturesOrderExecNotifyUnitService.class);

            // �����敪
            String l_strStatus = null;

            log.debug("�o���ʒm�敪 = " + l_strDealedType);
            //1.2.2 (*1) ���̏ꍇ�����Ώۏo���ʒm�L���[���R�[�h.�o���ʒm�敪 == �h�S�����h
            //  �܂��́h�ꕔ���h�̏ꍇ�������{�B
            if (WEB3DealedTypeDef.FULLY_EXECUTED.equals(l_strDealedType)
                || WEB3DealedTypeDef.PARTIALLY_EXECUTED.equals(l_strDealedType))
            {
                //1.2.2.1  notify���(OrderUnit, Timestamp, double, double, String)
                l_orderExecNotifyUnitService.notifyExecute(
                    l_orderUnit,
                    l_tsExecTimestamp,
                    l_dblExecQuantity,
                    l_dblExecPrice,
                    l_strDealedType);


                l_orderUnit = l_orderManager.getOrderUnit(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    ProductTypeEnum.IFO,
                    hostOptionOrderExecNotifyRow.getOrderRequestNumber());


                //1.2.2.2  isPartiallyExecuted()
                l_orderUnit.isPartiallyExecuted();

                //1.3.3(*2)�����P��.�����敪==�h�������h�̏ꍇ�̂ݎ��{
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                log.debug("�����P��.�����敪 = 4:" + l_orderUnitRow.getExpirationStatus());
                if (OrderExpirationStatusEnum.EXPIRING.equals(l_orderUnitRow.getExpirationStatus()))
                {
                    try
                    {
                        //1.2.2.3.1 (*3)�����ʒm�L���[�e�[�u���Ɂh�������h�����݂���ꍇ
                        //(*3) �ȉ��̏����Ő敨OP�����ʒm�L���[�e�[�u������������B
                        //�s���擾�ł����ꍇ�A�����ʒm�������R�[�����A�����ΏۃL���[�e�[�u���̏����敪���X�V����B
                        //[����]
                        //�敨OP�����ʒm�L���[�e�[�u��.�f�[�^�R�[�h = "EI817"�i�����w���敨�����ʒm�j
                        //�敨OP�����ʒm�L���[�e�[�u��.�،���ЃR�[�h = (*A)
                        //�敨OP�����ʒm�L���[�e�[�u��.���X�R�[�h = (*B)
                        //�敨OP�����ʒm�L���[�e�[�u��.���ʃR�[�h = �����P��.���ʃR�[�h
                        //�敨OP�����ʒm�L���[�e�[�u��.�����ʒm�敪 = "1"�i�����j
                        //�敨OP�����ʒm�L���[�e�[�u��.�����敪 = �h�������h
                        //(*A)�g���A�J�E���g�}�l�[�W��.getBranch(�����P��.���XID).getInstitution().getInstitutionCode()
                        //(*B)�g���A�J�E���g�}�l�[�W��.getBranch(�����P��.���XID).getBranchCode()

                        StringBuffer l_sbWhereClose = new StringBuffer();
                        l_sbWhereClose.append(" request_code = ? ");            // "EI817"�i�����w���敨�����ʒm)
                        l_sbWhereClose.append(" and institution_code = ? ");    // (*A)
                        l_sbWhereClose.append(" and branch_code = ? ");         // (*B)
                        l_sbWhereClose.append(" and order_request_number = ? ");// �����P��.���ʃR�[�h
                        l_sbWhereClose.append(" and close_notify_type = ? ");   // "1"�i�����j
                        l_sbWhereClose.append(" and status = ? ");              // �h�������h

                        Object[] l_objWhereClose = {
                            WEB3HostRequestCodeDef.FUTURES_CLOSE_NOTICE,
                            l_accountManager.getBranch(l_orderUnitRow.getBranchId()).getInstitution().getInstitutionCode(),
                            l_accountManager.getBranch(l_orderUnitRow.getBranchId()).getBranchCode(),
                            l_orderUnitRow.getOrderRequestNumber(),
                            WEB3CloseNotifyTypeDef.CLOSE,
                            WEB3StatusDef.DEALING};

                        List l_lisSearchResultClose = null;
                        l_lisSearchResultClose = l_QueryProcessor.doFindAllQuery(
                            HostFotypeCloseOrderNotifyRow.TYPE,
                            l_sbWhereClose.toString(),
                            null,
                            null,
                            l_objWhereClose);
                        int l_intNumClose = 0;
                        if (l_lisSearchResultClose != null)
                        {
                            l_intNumClose = l_lisSearchResultClose.size();
                        }
                        log.debug("l_intNumClose = " + l_intNumClose);

                        if (l_intNumClose == 1)
                        {
                              //1.2.3.1.1 notify����(OrderUnit, double, String, String)
                              //  (�敨OP�����ʒmUnitServiceImpl::notify����)
                              //  [����]
                              //  �����P�ʁF�@@�����P��
                              //  ��萔�ʁF
                              //  �����ʒm�L���[.�������� ! = NULL �̏ꍇ�A
                              //  �@@�����P��.�s�ꂩ��m�F�ς݂̐���-�����ʒm�L���[.��������
                              //  �����ʒm�L���[.�������� = NULL �̏ꍇ�A
                              //  �@@�敨OP�����ʒm�L���[�e�[�u��.��萔��
                              //  �������R�R�[�h�F�@@�����ʒm�L���[�e�[�u��.�������R�R�[�h
                              WEB3IfoCloseNotifyUnitService l_unitService =
                                  (WEB3IfoCloseNotifyUnitService)Services.getService(
                                      WEB3IfoCloseNotifyUnitService.class);
                              l_closeParams = (HostFotypeCloseOrderNotifyParams)l_lisSearchResultClose.get(0);
                              double l_dblExecutedQuantity = 0.0;
                              if (!l_closeParams.getCloseQuantityIsNull())
                              {
                                  BigDecimal l_bdConfirmedQuantity =
                                      new BigDecimal(String.valueOf(l_orderUnit.getConfirmedQuantity()));
                                  BigDecimal l_bdCloseQuantity =
                                      new BigDecimal(String.valueOf(l_closeParams.getCloseQuantity()));
                                  l_dblExecutedQuantity =
                                      l_bdConfirmedQuantity.subtract(l_bdCloseQuantity).doubleValue();
                              }
                              else
                              {
                                  l_dblExecutedQuantity = l_closeParams.getExecutedQuantity();
                              }
                              l_strStatus = l_unitService.notifyClose(
                                  l_orderUnit,
                                  l_dblExecutedQuantity,
                                  l_closeParams.getReasonCode(),
                                  l_closeParams.getCloseNotifyType());

                              //1.2.3.1.2 �����Ώێ����ʒm�L���[���R�[�h.�����敪���ȉ��̒ʂ�Z�b�g���X�V����B
                              //  [�X�V���e]
                              //  �h�G���[�h�F��L�����ŗ�O�����������ꍇ�B
                              //  notify�����̖߂�l�F�ȊO�̏ꍇ�B
                              l_closeParams.setStatus(l_strStatus);
                              l_closeParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                              l_QueryProcessor.doUpdateQuery(l_closeParams);
                        }
                        else
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                    catch (WEB3BaseException l_bex)
                    {
                        ErrorInfo l_errorInfo = l_bex.getErrorInfo();
                        if (l_errorInfo.getErrorTag().startsWith("BUSINESS_ERROR"))
                        {
                            l_errorInfo=WEB3ErrorCatalog.BUSINESS_ERROR_01961;
                            throw new DataCallbackException(l_errorInfo.getErrorMessage(), l_errorInfo);
                        }
                        else
                        {
                            l_errorInfo=WEB3ErrorCatalog.SYSTEM_ERROR_80077;
                            throw new DataCallbackException(l_errorInfo.getErrorMessage(), l_errorInfo);

                        }
                    }
                    catch (Exception l_e)
                    {
                        log.exiting(STR_METHOD_NAME);
                        log.error("", new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_e.getMessage(),
                            l_e));
                    }

                }

                log.debug("�����P�� ������� = " + l_orderUnitRow.getOrderStatus());
                //1.3.4(*5)�����P��.�������==�h�������i��������j�h�܂��́h��t�ρi��������j�h�̏ꍇ�̂ݎ��{
                if (OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus())
                    || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitRow.getOrderStatus()))
                {
                    log.debug(" �����P��.�������==�h�������i��������j�h�܂��́h��t�ρi��������j�h�̏ꍇ ");
                    try
                    {
                        //1.2.2.3.2(*5)��������L���[�e�[�u����"������"�����݂���ꍇ
                        //(*5) �ȉ��̏����Ő敨OP��������ʒm�L���[�e�[�u������������B
                        //�s���擾�ł����ꍇ�A����ʒm�������R�[�����A�����ΏۃL���[�e�[�u���̏����敪���X�V����B
                        //[����]
                        //�敨OP��������ʒm�L���[�e�[�u��.�f�[�^�R�[�h = �hEI816�h�i�����w���敨��������ʒm�j
                        //�敨OP��������ʒm�L���[�e�[�u��.�،���ЃR�[�h = (*A)
                        //�敨OP��������ʒm�L���[�e�[�u��.���X�R�[�h = (*B)
                        //�敨OP��������ʒm�L���[�e�[�u��.���ʃR�[�h = �����P��.���ʃR�[�h
                        //�敨OP��������ʒm�L���[�e�[�u��.��������ʒm�敪 = "3"�i��������j�܂���"4"�i������s�j
                        //�敨OP��������ʒm�L���[�e�[�u��.�����敪 = �h�������h
                        //(*A)�g���A�J�E���g�}�l�[�W��.getBranch(�����P��.���XID).getInstitution().getInstitutionCode()
                        //(*B)�g���A�J�E���g�}�l�[�W��.getBranch(�����P��.���XID).getBranchCode()

                        StringBuffer l_sbWhereCancel = new StringBuffer();
                        l_sbWhereCancel.append(" request_code = ? ");             // "EI816"�i�����w���敨��������ʒm�j
                        l_sbWhereCancel.append(" and institution_code = ? ");     // (*A)
                        l_sbWhereCancel.append(" and branch_code = ? ");          // (*B)
                        l_sbWhereCancel.append(" and order_request_number = ? "); // �����P��.���ʃR�[�h
                        l_sbWhereCancel.append(" and ( canmod_receipt_type = ? ");// "3"�i��������j
                        l_sbWhereCancel.append(" or canmod_receipt_type = ? ) "); // �܂���"4"�i������s�j
                        l_sbWhereCancel.append(" and status = ? ");               // �h�������h

                        Object[] l_objWhereCancel = {
                            WEB3HostRequestCodeDef.FUTURES_CHANGE_CANCEL_NOTICE,
                            l_accountManager.getBranch(l_orderUnitRow.getBranchId()).getInstitution().getInstitutionCode(),
                            l_accountManager.getBranch(l_orderUnitRow.getBranchId()).getBranchCode(),
                            l_orderUnitRow.getOrderRequestNumber(),
                            WEB3CanmodReceiptTypeDef.CANCEL,
                            WEB3CanmodReceiptTypeDef.CANCEL_FAILED,
                            WEB3StatusDef.DEALING};

                        List l_lisSearchResultCancel = null;
                        l_lisSearchResultCancel = l_QueryProcessor.doFindAllQuery(
                            HostFotypeOrderClmdNotifyRow.TYPE,
                            l_sbWhereCancel.toString(),
                            null,
                            null,
                            l_objWhereCancel);
                        int l_intNumCancel = 0;
                        if (l_lisSearchResultCancel != null)
                        {
                            l_intNumCancel = l_lisSearchResultCancel.size();
                        }
                        log.debug("l_intNumCancel = "+ l_intNumCancel);

                        if (l_intNumCancel == 1)
                        {
                            //1.2.2.3.2.1 �敨OP��������ʒm�X�V�C���^�Z�v�^()
                            WEB3IfoChangeCancelNotifyUpdateInterceptor l_interceptor =
                                new WEB3IfoChangeCancelNotifyUpdateInterceptor();

                            l_cancelParams = (HostFotypeOrderClmdNotifyParams) l_lisSearchResultCancel.get(0);

                            //�敨OP��������ʒm�X�V�C���^�Z�v�^�ɒl��ݒ�B
                            l_interceptor.setChangedQuantity(l_cancelParams.getModifiedQuantity());
                            l_interceptor.setChangedLimitPrice(l_cancelParams.getModifiedLimitPrice());

                            IfoOrderExecutionConditionType l_conditionType = null;

                            if (WEB3ExecutionConditionDef.NO_CONDITION.equals(l_cancelParams.getModifiedExecutionType()))
                            {
                                l_conditionType = IfoOrderExecutionConditionType.NONE;
                            }
                            else if (WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(l_cancelParams.getModifiedExecutionType()))
                            {
                                l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
                            }
                            else if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(l_cancelParams.getModifiedExecutionType()))
                            {
                                l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
                            }
                            else if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_cancelParams.getModifiedExecutionType()))
                            {
                                l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
                            }

                            l_interceptor.setChangedExecCondType(l_conditionType);
                            l_interceptor.setChangeCancelNotifyDivision(l_cancelParams.getCanmodReceiptType());
                            l_interceptor.setChangeCancelResultCode(l_cancelParams.getModifiedResult());

                            //1.2.2.3.2.2  notify���(OrderUnit, �敨OP��������ʒm�X�V�C���^�Z�v�^)(�敨��������ʒmUnitServiceImpl::notify���)
                            //[notify���()�Ɏw�肷�����]
                            //�����P��    �F �����P��
                            //�C���^�Z�v�^�F�i��������OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g�j
                            WEB3FuturesChangeCancelNotifyUnitService l_unitService =
                                (WEB3FuturesChangeCancelNotifyUnitService)Services.getService(
                                    WEB3FuturesChangeCancelNotifyUnitService.class);

                            //1.2.2.3.2.3 �����Ώے�������ʒm�L���[���R�[�h.�����敪��
                            //[�X�V���e]
                            //�h�G���[�h�F��L�����ŗ�O�����������ꍇ�B
                            //notify����̖߂�l�F�ȊO�̏ꍇ�B
                            l_strStatus = l_unitService.notifyCancel(
                                l_orderUnit,
                                l_interceptor);

                            l_cancelParams.setStatus(l_strStatus);
                            l_cancelParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                            l_QueryProcessor.doUpdateQuery(l_cancelParams);

                        }
                    }
                    catch (WEB3BaseException l_bex)
                    {
                        ErrorInfo l_errorInfo = l_bex.getErrorInfo();
                        if (l_errorInfo.getErrorTag().startsWith("BUSINESS_ERROR"))
                        {
                            l_errorInfo=WEB3ErrorCatalog.BUSINESS_ERROR_01962;
                                throw new DataCallbackException(
                                l_errorInfo.getErrorMessage(),
                                l_errorInfo);
                        }
                        else
                        {
                            l_errorInfo=WEB3ErrorCatalog.SYSTEM_ERROR_80078;
                                throw new DataCallbackException(
                                l_errorInfo.getErrorMessage(),
                                l_errorInfo);

                        }
                    }
                    catch (Exception l_e)
                    {
                        log.exiting(STR_METHOD_NAME);
                        log.error("", new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_e.getMessage(),
                            l_e));
                    }

                }
            }
            //1.2.3 (*6) ������̏ꍇ
            else if (WEB3DealedTypeDef.CANCEL.equals(l_notifyRow.getDealedType()))
            {
                //1.2.3.1 notify�����(OrderUnit, Timestamp, double, double)
                //  (�敨�o���ʒmUnitServiceImpl::notify�����)
                //  [notify�����()�Ɏw�肷�����]
                //  �����P�ʁF�@@�����P��
                //  �������F�@@�o���ʒm�L���[.������
                //  ��萔�ʁF�@@�o���ʒm�L���[.��萔��
                //  ���P���F�@@�o���ʒm�L���[.���P��
                l_orderExecNotifyUnitService.notifyExecuteCancel(
                    l_orderUnit,
                    l_tsExecTimestamp,
                    l_dblExecQuantity,
                    l_dblExecPrice);
            }

            //1.2.4 �����Ώۏo���ʒm�L���[���R�[�h.�����敪���ȉ��̒ʂ�Z�b�g���X�V����B
            //[�X�V���e]
            // �h�G���[�h�F��L�����i�L���[�e�[�u���e�s���Ƃ̏����j�ŗ�O�����������ꍇ�B
            // �h�����ρh�F�ȊO�̏ꍇ�B
            l_notifyParams.setStatus(WEB3StatusDef.DEALT);
            l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_QueryProcessor.doUpdateQuery(l_notifyParams);
        }
        catch(WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}
@
