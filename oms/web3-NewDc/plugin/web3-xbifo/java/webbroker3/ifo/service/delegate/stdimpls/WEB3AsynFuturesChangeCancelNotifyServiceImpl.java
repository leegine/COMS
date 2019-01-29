head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AsynFuturesChangeCancelNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨��������ʒm�T�[�r�X�����N���X(WEB3AsynFuturesChangeCancelNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : �����ŗ����F
Revesion History : 2004/7/21 Ḗ@@�� (���u) �V�K�쐬
Revesion History : �񓯊��Ή��ŗ����F
Revesion History : 2005/03/11 ���u���@@�񓯊����s�Ή��i�V�K�N���X�j
Revesion History : 2007/01/30 �����q (���u) �d�l�ύX No.623
Revesion History : 2007/04/24 �Ј���(���u) �d�l�ύX���f��No.637
Revesion History : 2008/04/11 �����F(���u) Java�\�[�X�i��{�݌v�ƍ����Ă��Ȃ������jNo.012
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataRollbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow;
import webbroker3.ifo.define.WEB3IfoCanmodReceiptTypeDef;
import webbroker3.ifo.message.WEB3FuturesChangeCancelNotifyRequest;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�񓯊��Ή������w���敨��������ʒm�T�[�r�X�����N���X�j�B
 * @@author  : ���u���i���{���u�j
 * @@version : 1.0
 */
public class WEB3AsynFuturesChangeCancelNotifyServiceImpl
    implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynFuturesChangeCancelNotifyServiceImpl.class);

    /**
     * �����w���敨��������ʒm���N�G�X�g
     */
    private WEB3FuturesChangeCancelNotifyRequest request;


    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3AsynFuturesChangeCancelNotifyServiceImpl(WEB3FuturesChangeCancelNotifyRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynFuturesChangeCancelNotifyServiceImpl�Frun()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FuturesChangeCancelNotifyRequest l_futuresChangeCancelNotifyRequest =
                (WEB3FuturesChangeCancelNotifyRequest) request;

            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();

            }
            catch (DataFindException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //3: ������t�g�����U�N�V�����R�[���o�b�N�T�[�r�X�𐶐�����
            WEB3FuturesChangeCancelNotifyTransactionCallback l_callback = new WEB3FuturesChangeCancelNotifyTransactionCallback();

            //set���ʃR�[�h�v���t�B�N�X�ꗗ()
            l_callback.setOrderRequestNumberPrefixGroup(
                l_futuresChangeCancelNotifyRequest.orderRequestNumberPrefixGroup);

            //5: DB�g�����U�N�V�������������{����
            try
            {
                l_queryProcessor.doTransaction(TransactionalInterceptor.TX_CREATE_NEW, l_callback);
            }
            catch (DataCallbackException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataRollbackException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3SystemLayerException
                    (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }

        //�X���b�h�J��
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(
                request.threadNo.longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }

        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (�敨��������ʒmTransactionCallback)<BR>
     * �敨��������ʒmTransactionCallback<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3FuturesChangeCancelNotifyTransactionCallback implements TransactionCallback
    {

        /**
         * (�敨��������ʒmTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@return
         * webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyServiceImpl.WEB3FuturesChangeCancelNotifyTransactionCallback
         * @@roseuid 40A89E78021B
         */
        public WEB3FuturesChangeCancelNotifyTransactionCallback()
        {

        }

        /**
         * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (set�����̎��ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B<BR>
         * @@params String[] - ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
         */
        public void setOrderRequestNumberPrefixGroup(String[]
            l_orderRequestNumberPrefixGroup)
        {
            orderRequestNumberPrefixGroup = l_orderRequestNumberPrefixGroup;
        }

        /**
         * (get�����̎��ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B<BR>
         * @@return String[]<BR>
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return orderRequestNumberPrefixGroup;
        }


        /**
         * ������t���������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�敨��������ʒm�jprocess�v�Q�ƁB<BR>
         * <BR>
         * �P�j �����Ώۃ��R�[�h�擾<BR>
         * ��������ʒm�L���[�e�[�u�����A�s���b�N�iselect for update�j�ɂēǂݍ��ށB<BR>
         * <BR>
         * �@@[��������]<BR>
         * �@@�敨OP��������ʒm�L���[�e�[�u��.�f�[�^�R�[�h == �h�敨��������ʒm�h�iEI816�j<BR>
         * �@@�敨OP��������ʒm�L���[�e�[�u��.�����敪 == �h�������h<BR>
         * <BR>
         * �ȍ~�̏����́A�������ʂ̒�����t�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����B<BR>
         * <BR>
         * �Q�j �C���^�Z�v�^�Z�b�g<BR>
         * �敨��������ʒm�C���^�Z�v�^�I�u�W�F�N�g�𐶐����A�ȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
         * <BR>
         * �@@�C���^�Z�v�^.�����㐔�� = �敨OP��������ʒm�L���[.�����㐔��<BR>
         * �@@�C���^�Z�v�^.������w�l = �敨OP��������ʒm�L���[.������w�l<BR>
         * �@@�C���^�Z�v�^.�����㎷�s���� = �敨OP��������ʒm�L���[.�����㎷�s����<BR>
         * �@@�C���^�Z�v�^.����������ʃR�[�h = �敨OP��������ʒm�L���[.����������ʃR�[�h<BR>
         * �@@�C���^�Z�v�^.��������ʒm�敪 = �敨OP��������ʒm�L���[.��������ʒm�敪<BR>
         * <BR>
         * setThreadLocalPersistenceEventInterceptor()�ɂāA<BR>�����}�l�[�W����ThreadLocal�ɃZ�b�g����B<BR>
         * <BR>
         * �R�j �L���[�ɊY�����钍���P�ʂ��擾<BR>
         * �����}�l�[�W��.get�����P��()���R�[�����A<BR>
         * �����ΏۃL���[�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�،���ЃR�[�h�@@�F�敨OP��������ʒm�L���[�e�[�u��.�،���ЃR�[�h<BR>
         * �@@���X�R�[�h�@@�F�敨OP��������ʒm�L���[�e�[�u��.���X�R�[�h<BR>
         * �@@���i�^�C�v�@@�F�h�敨�I�v�V�����h<BR>
         * �@@���ʃR�[�h�@@�F�敨OP��������ʒm�L���[�e�[�u��.���ʃR�[�h<BR>
         * <BR>
         * �S�j �����ʒm�A����ʒm�𒍕��ɍX�V����B<BR>
         * �����ΏۃL���[�̓��e�𔻒肵�A�ȉ��̏��������{����B<BR>
         * <BR>
         * �S�|�P�j�@@�����ʒm<BR>
         * �@@�i�敨OP��������ʒm�L���[�e�[�u��.������t���� == "��������"�܂��́A<BR>
         * �@@�敨OP��������ʒm�L���[�e�[�u��.������t���� == "�����G���["�̏ꍇ�j<BR>
         * <BR>
         * �@@�敨��������ʒmUnitService���擾���Anotify����()���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�����P�ʁF�@@�iget�����P��()�߂�l�j<BR>
         * �@@�C���^�Z�v�^�F�@@�i���������敨��������ʒm�C���^�Z�v�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �S�|�Q�j�@@����ʒm<BR>
         * �@@�i�敨OP��������ʒm�L���[�e�[�u��.������t���� == "�������"�܂��́A<BR>
         * �@@�敨OP��������ʒm�L���[�e�[�u��.������t���� == "����G���["�̏ꍇ�j<BR>
         * <BR>
         * �@@�敨��������ʒmUnitService���擾���Anotify���()���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�����P�ʁF�@@�iget�����P��()�߂�l�j<BR>
         * �@@�C���^�Z�v�^�F�@@�i���������敨��������ʒm�C���^�Z�v�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �T�j �L���[�e�[�u���ɏ����ς��X�V<BR>
         * �����Ώے�������ʒm�L���[���R�[�h.�����敪���ȉ��̒ʂ�Z�b�g���X�V����B<BR>
         * <BR>
         * �@@[�X�V���e]<BR>
         * �@@�h�G���[�h�F��L�����ŃG���[�����������ꍇ<BR>
         * �@@�h�����ρh�F�ȊO�̏ꍇ<BR>
         * <BR>
         * �U�j null��ԋp����B<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40A89E7801EC
         */
        public Object process() throws DataQueryException, DataNetworkException, IllegalStateException
        {
            final String STR_METHOD_NAME = "Object process";
            log.entering(STR_METHOD_NAME);

            //
            WEB3FuturesChangeCancelNotifyUnitService l_futuresChangeCancelNotifyUnitService =
                (WEB3FuturesChangeCancelNotifyUnitService) Services.getService(WEB3FuturesChangeCancelNotifyUnitService.class);
            //2: �敨OP��������ʒm�L���[�e�[�u���Ǎ�
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("request_code = ? ");
            l_sbWhere.append("and status= ?");

            int l_intPrefixGroupLength = 0;
            if (orderRequestNumberPrefixGroup != null)
            {
                l_intPrefixGroupLength = orderRequestNumberPrefixGroup.length;
            }
            if (l_intPrefixGroupLength > 0)
            {
                l_sbWhere.append(" and (");
                for (int i = 0; i < l_intPrefixGroupLength; i++)
                {
                    if (i > 0)
                    {
                        l_sbWhere.append(" or ");
                    }
                    l_sbWhere.append("order_request_number like ?");
                }
                l_sbWhere.append(")");
            }

            Object[] l_objWhere = new Object[l_intPrefixGroupLength + 2];
            l_objWhere[0] = WEB3HostRequestCodeDef.FUTURES_CHANGE_CANCEL_NOTICE;
            l_objWhere[1] = WEB3StatusDef.NOT_DEAL;

            for (int i = 0; i < l_intPrefixGroupLength; i++)
            {
                l_objWhere[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
            }

            List l_listRecords = null;

            QueryProcessor l_queryProcessor = null;

            l_queryProcessor = Processors.getDefaultProcessor();

            l_listRecords = l_queryProcessor.doFindAllQuery
                (HostFotypeOrderClmdNotifyRow.TYPE, l_sbWhere.toString(), null, null, l_objWhere);

            int l_intLength;
            l_intLength = l_listRecords.size();
            log.debug("Get the size of the records" + l_intLength);
            //3:�敨OP��������ʒm�L���[�e�[�u���������ʃ��R�[�h�e�s���Ƃ�LOOP
            for (int i = 0; i < l_intLength; i++)
            {
                HostFotypeOrderClmdNotifyRow l_orderChangeCancelNotifyRow =
                    (HostFotypeOrderClmdNotifyRow) l_listRecords.get(i);
                try
                {
                    //�،���ЃR�[�h
                    String l_strInstitutionCode = l_orderChangeCancelNotifyRow.getInstitutionCode();
                    log.debug("�،���ЃR�[�h" + l_strInstitutionCode);
                    //���X�R�[�h
                    String l_strBranchCode = l_orderChangeCancelNotifyRow.getBranchCode();
                    log.debug("���X�R�[�h:" + l_strBranchCode);
                    //���ʃR�[�h
                    String l_strOrderRequestNumber = l_orderChangeCancelNotifyRow.getOrderRequestNumber();
                    log.debug("���ʃR�[�h:" + l_strOrderRequestNumber);

                    //5: �敨OP��������ʒm�X�V�C���^�Z�v�^����
                    WEB3IfoChangeCancelNotifyUpdateInterceptor l_ChangeCancelNotifyUpdateInterceptor = new WEB3IfoChangeCancelNotifyUpdateInterceptor();
                    //6: �����㐔�ʂ��Z�b�g����
                    double l_dblQuantity = l_orderChangeCancelNotifyRow.getModifiedQuantity();

                    l_ChangeCancelNotifyUpdateInterceptor.setChangedQuantity(l_dblQuantity);
                    log.debug("�����㐔��:" + l_orderChangeCancelNotifyRow.getModifiedQuantity());
                    //7: set������w�l
                    l_ChangeCancelNotifyUpdateInterceptor.setChangedLimitPrice(l_orderChangeCancelNotifyRow.getModifiedLimitPrice());
                    log.debug("������w�l:" + l_orderChangeCancelNotifyRow.getModifiedLimitPrice());
                    //8: set�����㎷�s����
                    String l_strModifiedExecutionType = l_orderChangeCancelNotifyRow.getModifiedExecutionType();

                    log.debug("�����㎷�s����: " + l_strModifiedExecutionType);
                    //setChangedExecCondType
                    IfoOrderExecutionConditionType l_conditionType = null;
                    if (WEB3ExecutionConditionDef.NO_CONDITION.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.NONE;
                    }
                    if (WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
                    }
                    if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
                    }
                    if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
                    }
                    l_ChangeCancelNotifyUpdateInterceptor.setChangedExecCondType(l_conditionType);
                    log.debug("ChangedExecCondType is :" + l_conditionType);
                    //9: set����������ʃR�[�h
                    l_ChangeCancelNotifyUpdateInterceptor.setChangeCancelResultCode
                        (l_orderChangeCancelNotifyRow.getModifiedResult());
                    log.debug("����������ʃR�[�h:" + l_orderChangeCancelNotifyRow.getModifiedResult());
                    //10: set��������ʒm�敪
                    l_ChangeCancelNotifyUpdateInterceptor.setChangeCancelNotifyDivision
                        (l_orderChangeCancelNotifyRow.getCanmodReceiptType());
                    log.debug("��������ʒm�敪:" + l_orderChangeCancelNotifyRow.getCanmodReceiptType());

                    // set�����㔭���o�H�敪(String)
                    // �C���^�Z�v�^�ɒ����㔭���o�H�敪���Z�b�g����B
                    // �����㔭���o�H�敪�F�@@�敨OP��������ʒm�L���[.�����㔭���o�H�敪
                    l_ChangeCancelNotifyUpdateInterceptor.setModSubmitOrderRouteDiv(
                        l_orderChangeCancelNotifyRow.getModSubmitOrderRouteDiv());

                    // set�����㒍��Rev.(String)
                    // �C���^�Z�v�^�ɒ����㒍��Rev.���Z�b�g����B
                    // �����㒍��Rev.�F�@@�敨OP��������ʒm�L���[.�����㒍��Rev.
                    l_ChangeCancelNotifyUpdateInterceptor.setModifiedOrderRev(
                        l_orderChangeCancelNotifyRow.getModifiedOrderRev());

                    //13: ��������ʒm�敪
                    log.debug("canmod_receipt_type: " + l_orderChangeCancelNotifyRow.getCanmodReceiptType());
                    if ((WEB3IfoCanmodReceiptTypeDef.CHANGED_COMPLETE.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType()))
                        || (WEB3IfoCanmodReceiptTypeDef.CHANGED_FAILED.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType())))
                    {
                        // TransactionCallback����
                        WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback l_transactionCallback =
                            new WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback(
                                l_ChangeCancelNotifyUpdateInterceptor,
                                l_orderChangeCancelNotifyRow);

                        // doTransaction()
                        l_queryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }

                    //15: ����ʒm
                    if ((WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType()))
                        || (WEB3IfoCanmodReceiptTypeDef.CANCELED_FAILED.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType())))
                    {
                        // TransactionCallback����
                        WEB3FuturesChangeCancelNotifyNotifyCancelTransactionCallback l_transactionCallback =
                            new WEB3FuturesChangeCancelNotifyNotifyCancelTransactionCallback(
                                l_ChangeCancelNotifyUpdateInterceptor,
                                l_orderChangeCancelNotifyRow);

                        // doTransaction()
                        l_queryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }
                }
                catch (Exception l_exp)
                {
                    //�����ΏۃL���[UPDATE�@@(�G���[��)
                    if (l_exp instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_exp;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                        {
                            log.debug("�������b�N���s�F");
                            continue;
                        }
                    }

                    //�G���[������ȊO�̏ꍇ�@@(=>�G���[)
                    log.debug("�ꌏ�����ɂăG���[�����F", l_exp);
                    Map l_changesMap1 = new HashMap();
                    l_changesMap1.put("status", WEB3StatusDef.DATA_ERROR);
                    l_changesMap1.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_orderChangeCancelNotifyRow.getPrimaryKey(),l_changesMap1);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}

@
