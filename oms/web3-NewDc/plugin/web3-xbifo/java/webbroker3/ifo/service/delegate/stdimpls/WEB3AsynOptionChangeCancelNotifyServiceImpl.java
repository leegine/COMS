head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AsynOptionChangeCancelNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP��������ʒm�T�[�r�XImpl(WEB3AsynOptionChangeCancelNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : �����ŗ����F
Revesion History : 2004/6/15 Ḗ@@�� (���u) �V�K�쐬
Revesion History : 2007/01/30 �����q (���u) �d�l�ύX No.612,616
Revesion History : 2007/04/24 �Ј���(���u) �d�l�ύX���f��No.637
**/

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
import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyRequest;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�񓯊��Ή�OP��������ʒm�T�[�r�X�����N���X�j�B
 * @@author  : ���u���i���{���u�j
 * @@version : 1.0
 */
public class WEB3AsynOptionChangeCancelNotifyServiceImpl
    implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynOptionChangeCancelNotifyServiceImpl.class);

    /**
     * �����w���I�v�V������������ʒm���N�G�X�g
     */
    private WEB3OptionsChangeCancelNotifyRequest request;


    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3AsynOptionChangeCancelNotifyServiceImpl(WEB3OptionsChangeCancelNotifyRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynOptionChangeCancelNotifyServiceImpl�Frun()";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;

        WEB3OptionsChangeCancelNotifyRequest l_optionsChangeCancelNotifyRequest =
            (WEB3OptionsChangeCancelNotifyRequest) request;
        try
        {
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
            WEB3OptionChangeCancelNotifyTransactionCallback l_callback = new WEB3OptionChangeCancelNotifyTransactionCallback();

            //set���ʃR�[�h�v���t�B�N�X�ꗗ()
            l_callback.setOrderRequestNumberPrefixGroup(
                l_optionsChangeCancelNotifyRequest.orderRequestNumberPrefixGroup);

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
            new WEB3GentradeDaemonTriggerManager().releaseThread(request.threadNo.
                longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }
        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (OP��������ʒmTransactionCallback)<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3OptionChangeCancelNotifyTransactionCallback implements TransactionCallback
    {

        /**
         * (OP��������ʒmTransactionCallback)
         * �f�t�H���g�R���X�g���N�^
         * @@return
         * webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyServiceImpl.WEB3OptionChangeCancelNotifyTransaction
         * Callback
         * @@roseuid 4084B3CF0365
         */
        public WEB3OptionChangeCancelNotifyTransactionCallback()
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
         * �u�iOP��������ʒm�jprocess�v�Q�ƁB<BR>
         * <BR>
         * �P�j �����Ώۃ��R�[�h�擾<BR>
         * ��������ʒm�L���[�e�[�u�����A<BR>
         * �s���b�N�iselect for update�j�ɂēǂݍ��ށB<BR>
         * <BR>
         * �@@[��������]<BR>
         * �@@�敨OP��������ʒm�L���[�e�[�u��.�f�[�^�R�[�h == <BR>
         * �h�I�v�V������������ʒm�h�iEI812�j<BR>
         * �@@�敨OP��������ʒm�L���[�e�[�u��.�����敪 == �h�������h<BR>
         * <BR>
         * �ȍ~�̏����́A<BR>
         * �����ʂ̒�����t�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����B<BR>
         * <BR>
         * �Q�j �C���^�Z�v�^�Z�b�g<BR>
         * OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g�𐶐����A<BR>
         * �ȉ��̒ʂ�v���p�e�B���Z�b�g����B<BR>
         * <BR>
         * �@@�C���^�Z�v�^.�����㐔�� = <BR>
         * �敨OP��������ʒm�L���[.�����㐔��<BR>
         * �@@�C���^�Z�v�^.������w�l = <BR>
         * �敨OP��������ʒm�L���[.������w�l<BR>
         * �@@�C���^�Z�v�^.�����㎷�s���� = <BR>
         * �敨OP��������ʒm�L���[.�����㎷�s����<BR>
         * �@@�C���^�Z�v�^.����������ʃR�[�h = <BR>
         * �敨OP��������ʒm�L���[.����������ʃR�[�h<BR>
         * �@@�C���^�Z�v�^.��������ʒm�敪 = <BR>
         * �敨OP��������ʒm�L���[.��������ʒm�敪<BR>
         * <BR>
         * setThreadLocalPersistenceEventInterceptor()�ɂāA<BR>
         * �����}�l�[�W����ThreadLocal�ɃZ�b�g����B<BR>
         * <BR>
         * �R�j �L���[�ɊY�����钍���P�ʂ��擾<BR>
         * �����}�l�[�W��.get�����P��()���R�[�����A<BR>
         * �����ΏۃL���[�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�،���ЃR�[�h�@@�F<BR>
         * �敨OP��������ʒm�L���[�e�[�u��.�،���ЃR�[�h<BR>
         * �@@���X�R�[�h�@@�F�敨OP��������ʒm�L���[�e�[�u��.���X�R�[�h<BR>
         * �@@���i�^�C�v�@@�F�h�敨�I�v�V�����h<BR>
         * �@@���ʃR�[�h�@@�F�敨OP��������ʒm�L���[�e�[�u��.���ʃR�[�h<BR>
         * <BR>
         * �S�j �����ʒm�A����ʒm�𒍕��ɍX�V����B<BR>
         * �����ΏۃL���[�̓��e�𔻒肵�A�ȉ��̏��������{����B<BR>
         * <BR>
         * �S�|�P�j�@@�����ʒm<BR>
         * �@@�i�敨OP��������ʒm�L���[�e�[�u��.������t���� == <BR>
         * "��������"�܂��́A<BR>
         * �@@�敨OP��������ʒm�L���[�e�[�u��.������t���� == <BR>
         * "�����G���["�̏ꍇ�j<BR>
         * <BR>
         * �@@OP��������ʒmUnitService���擾���A<BR>
         * notify����()���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�����P�ʁF�@@�iget�����P��()�߂�l�j<BR>
         * �@@�C���^�Z�v�^�F�@@�i��������OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �S�|�Q�j�@@����ʒm<BR>
         * �@@�i�敨OP��������ʒm�L���[�e�[�u��.������t���� == <BR>
         * "�������"�܂��́A<BR>
         * �@@�敨OP��������ʒm�L���[�e�[�u��.������t���� ==<BR>
         *  "����G���["�̏ꍇ�j<BR>
         * <BR>
         * �@@OP��������ʒmUnitService���擾���A<BR>
         * notify���()���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�����P�ʁF�@@�iget�����P��()�߂�l�j<BR>
         * �@@�C���^�Z�v�^�F�@@�i��������OP��������ʒm�C���^�Z�v�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �U�j �L���[�e�[�u���ɏ����ς��X�V<BR>
         * �����Ώے�������ʒm�L���[���R�[�h.�����敪<BR>
         * ���ȉ��̒ʂ�Z�b�g���X�V����B<BR>
         * <BR>
         * �@@[�X�V���e]<BR>
         * �@@�h�G���[�h�F��L�����ŃG���[�����������ꍇ<BR>
         * �@@�h�����ρh�F�ȊO�̏ꍇ<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4084B3CF0364
         */
        public Object process()
            throws DataNetworkException,DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "Object process";
            log.entering(STR_METHOD_NAME);

            //
            WEB3OptionChangeCancelNotifyUnitService l_optionChangeCancelNotifyUnitService =
                (WEB3OptionChangeCancelNotifyUnitService) Services.getService(WEB3OptionChangeCancelNotifyUnitService.class);
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
            l_objWhere[0] = WEB3HostRequestCodeDef.OPTION_CHANGE_CANCEL_NOTICE;
            l_objWhere[1] = WEB3StatusDef.NOT_DEAL;

            for (int i = 0; i < l_intPrefixGroupLength; i++)
            {
                l_objWhere[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
            }

            List l_listRecords = null;

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords =
                l_queryProcessor.doFindAllQuery
                    (HostFotypeOrderClmdNotifyRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    null,
                    l_objWhere);

            int l_intLength;
            l_intLength = l_listRecords.size();
            log.debug("Get the size of the records" + l_intLength);
            HostFotypeOrderClmdNotifyRow l_orderChangeCancelNotifyRow = null;

            for (int i = 0; i < l_intLength; i++)
            {
                try
                {
                    l_orderChangeCancelNotifyRow =
                        (HostFotypeOrderClmdNotifyRow) l_listRecords.get(i);

                    //�،���ЃR�[�h
                    String l_strInstitutionCode = l_orderChangeCancelNotifyRow.getInstitutionCode();
                    log.debug("�،���ЃR�[�h" + l_strInstitutionCode);
                    //���X�R�[�h
                    String l_strBranchCode = l_orderChangeCancelNotifyRow.getBranchCode();
                    log.debug("���X�R�[�h:" + l_strBranchCode);

                    //���ʃR�[�h
                    String l_strOrderRequestNumber = l_orderChangeCancelNotifyRow.getOrderRequestNumber();
                    log.debug("���ʃR�[�h:" + l_strOrderRequestNumber);

                    //4: �敨OP��������ʒm�X�V�C���^�Z�v�^����
                    WEB3IfoChangeCancelNotifyUpdateInterceptor l_ChangeCancelNotifyUpdateInterceptor =
                        new WEB3IfoChangeCancelNotifyUpdateInterceptor();
                    //5: �����㐔�ʂ��Z�b�g����
                    double l_dblModifieQuantity = l_orderChangeCancelNotifyRow.getModifiedQuantity();
                    l_ChangeCancelNotifyUpdateInterceptor.setChangedQuantity(l_dblModifieQuantity);

                    log.debug("�����㐔��:" + l_orderChangeCancelNotifyRow.getModifiedQuantity());
                    //6: set������w�l
                    double l_dblModifiedLimitPrice = l_orderChangeCancelNotifyRow.getModifiedLimitPrice();

                    l_ChangeCancelNotifyUpdateInterceptor.setChangedLimitPrice(l_dblModifiedLimitPrice);
                    log.debug("������w�l:" + l_orderChangeCancelNotifyRow.getModifiedLimitPrice());

                    //7: set�����㎷�s����
                    String l_strModifiedExecutionType = l_orderChangeCancelNotifyRow.getModifiedExecutionType();
                    log.debug("�����㎷�s����: " + l_strModifiedExecutionType);
                    //setChangedExecCondType
                    IfoOrderExecutionConditionType l_conditionType = null;

                    if (WEB3ExecutionConditionDef.NO_CONDITION.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.NONE;
                    }
                    else if (WEB3ExecutionConditionDef.AT_MARKET_OPEN.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_OPEN;
                    }
                    else if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE;
                    }
                    else if (WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_strModifiedExecutionType))
                    {
                        l_conditionType = IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
                    }

                    l_ChangeCancelNotifyUpdateInterceptor.setChangedExecCondType(l_conditionType);
                    log.debug("ChangedExecCondType is :" + l_conditionType);

                    //8: set����������ʃR�[�h
                    l_ChangeCancelNotifyUpdateInterceptor.setChangeCancelResultCode
                        (l_orderChangeCancelNotifyRow.getModifiedResult());
                    log.debug("����������ʃR�[�h:" + l_orderChangeCancelNotifyRow.getModifiedResult());

                    //9: set��������ʒm�敪
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

                    //12: ��������ʒm�敪
                    log.debug("canmod_receipt_type: " + l_orderChangeCancelNotifyRow.getCanmodReceiptType());

                    if ((WEB3IfoCanmodReceiptTypeDef.CHANGED_COMPLETE.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType()))
                        || (WEB3IfoCanmodReceiptTypeDef.CHANGED_FAILED.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType())))
                    {
                        WEB3OptionChangeCancelNotifyNotifyChangeTransactionCallback l_transactionCallback =
                            new WEB3OptionChangeCancelNotifyNotifyChangeTransactionCallback(
                            l_ChangeCancelNotifyUpdateInterceptor,
                            l_orderChangeCancelNotifyRow);

                        // doTransaction()
                        l_queryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }

                    //14: ����ʒm
                    if ((WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType()))
                        || (WEB3IfoCanmodReceiptTypeDef.CANCELED_FAILED.equals(l_orderChangeCancelNotifyRow.getCanmodReceiptType())))
                    {
                        WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallback l_transactionCallback =
                            new WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallback(
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
                    log.debug("�ꌏ�����ɂăG���[�����F");
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
