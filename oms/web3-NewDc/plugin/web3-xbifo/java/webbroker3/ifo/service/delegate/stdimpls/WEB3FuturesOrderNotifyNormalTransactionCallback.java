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
filename	WEB3FuturesOrderNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�����ʒm���폈���ꌏTransactionCallback(WEB3FuturesOrderNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/23 ���u��(���{���u) �V�K�쐬
*/


package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.ifo.data.HostFotypeOrderReceiptRow;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�敨�����ʒm���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3FuturesOrderNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderNotifyNormalTransactionCallback.class);

    /**
      * �敨OP�����ʒm�L���[Row�I�u�W�F�N�g�B<BR>
      */
    private HostFotypeOrderReceiptRow hostFotypeOrderReceiptRow;

    /**
      * �敨OP�����ʒm�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostFotypeOrderReceiptParams hostFotypeOrderReceiptParams;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostFotypeOrderReceiptRow - (�敨OP�����ʒm�L���[Row)
     * @@params l_hostFotypeOrderReceiptParams - (�敨OP�����ʒm�L���[Params)
     */
    public WEB3FuturesOrderNotifyNormalTransactionCallback(
        HostFotypeOrderReceiptRow l_hostFotypeOrderReceiptRow,
        HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams)
    {
        hostFotypeOrderReceiptRow = l_hostFotypeOrderReceiptRow;
        hostFotypeOrderReceiptParams = l_hostFotypeOrderReceiptParams;
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

        HostFotypeOrderReceiptRow l_receiptRow = hostFotypeOrderReceiptRow;
        HostFotypeOrderReceiptParams l_notifyParams = hostFotypeOrderReceiptParams;

        //1.2.1 getInstitution(�،���ЃR�[�h : �_���r���[::java::lang::String)
        //  [getInstitution()�Ɏw�肷�����]
        //  �،���ЃR�[�h�F�@@�敨OP�����ʒm�L���[���R�[�h.�،���ЃR�[�h
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        try
        {
            Institution l_institution = l_accountManager.getInstitution(
                l_receiptRow.getInstitutionCode());

            //1.2.2 getMainAccount(�،����ID : long, ���X�R�[�h : �_���r���[::java::lang::String, �����R�[�h : �_���r���[::java::lang::String)
            //  [getMainAccount()�Ɏw�肷�����]
            //  �،����ID�F�@@getInstitution()�̖߂�l.getInstitutionId()
            //  ���X�R�[�h�F�@@�敨OP�����ʒm�L���[���R�[�h.���X�R�[�h
            //  �����R�[�h�F�@@�敨OP�����ʒm�L���[���R�[�h.�ڋq�R�[�h
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_institution.getInstitutionId(),
                l_receiptRow.getBranchCode(),
                l_receiptRow.getAccountCode());

            //1.2.3 getSubAccount(�⏕�����^�C�v : SubAccountTypeEnum)
            //  [getSubAccount()�Ɏw�肷�����]
            //  �⏕�����^�C�v�F�@@�ڋq.getOP��������^�C�v()�̖߂�l
            SubAccount l_subAccount = l_mainAccount.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);

            //  �敨�����ʒmUnitServiceImpl
            WEB3FuturesOrderNotifyUnitService l_orderNotifyUnitService =
                (WEB3FuturesOrderNotifyUnitService)Services.getService(WEB3FuturesOrderNotifyUnitService.class);

            //1.2.4(*2)����t���[
            //  (*2)����t���[
            //  �V�K���̏ꍇ(�����Ώې敨OP�����ʒm�L���[���R�[�h.����R�[�h(SONAR) == "�敨OP��")�A�������{�B
            if (WEB3TransactionTypeSONARDef.OPEN_CONTRACT.equals(l_receiptRow.getSonarTradedCode()))
            {
                //1.2.4.1 notify�V�K������(�敨OP�����ʒm�L���[Params, �⏕����)(�敨�����ʒmUnitServiceImpl::notify�V�K������)
                //  [notify�V�K������()�Ɏw�肷�����]
                //  �敨OP�����ʒm�L���[Params�F�@@�擾���������Ώې敨OP�����ʒm�L���[�e�[�u����1���R�[�h
                //  �⏕�����F�@@�ڋq.getSubAccount()�̖߂�l
                l_orderNotifyUnitService.notifyOpenContractOrder(
                    l_notifyParams,
                    l_subAccount);
            }
            //1.2.5(*3)����t���[
            //  (*3)����t���[
            //  �ԍς̏ꍇ(�����Ώې敨OP�����ʒm�L���[���R�[�h.����R�[�h(SONAR) == "�敨OP��")�A�������{�B
            else if (WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(l_receiptRow.getSonarTradedCode()))
            {
                //1.2.5.1 notify�ԍϒ���(�敨OP�����ʒm�L���[Params, �⏕����)(�敨�����ʒmUnitServiceImpl::notify�ԍϒ���)
                //  [notify�ԍϒ���()�Ɏw�肷�����]
                //  �敨OP�����ʒm�L���[Params�F�@@�擾���������Ώې敨OP�����ʒm�L���[�e�[�u����1���R�[�h
                //  �⏕�����F�@@�ڋq.getSubAccount()�̖߂�l
                l_orderNotifyUnitService.notifySettleContractOrder(
                    l_notifyParams,
                    l_subAccount);
            }
        }
        catch (NotFoundException l_exp)
        {
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getMessage(),
                    new WEB3SystemLayerException(
                    l_errorInfo,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_exp.getMessage(),
                    l_exp));
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_exp);
        }

        //1.2.6(*4)�L���[�e�[�u���ɏ����敪�Z�b�g
        //(*4)�����Ώې敨OP�����ʒm�L���[���R�[�h.�����敪���ȉ��̒ʂ�Z�b�g���X�V����B
        //�@@[�X�V���e]
        //  �h�����ρh
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
        l_notifyParams.setStatus(WEB3StatusDef.DEALT);
        l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        l_QueryProcessor.doUpdateQuery(l_notifyParams);

        log.exiting(STR_METHOD_NAME);

        return null;
    }
}

@
