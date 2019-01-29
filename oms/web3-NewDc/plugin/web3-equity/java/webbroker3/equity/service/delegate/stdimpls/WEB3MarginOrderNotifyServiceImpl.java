head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������ʒm�T�[�r�XImpl(WEB3MarginOrderNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 䈋� (���u) �V�K�쐬
Revesion History : 2004/12/16 ���� (SRA) �c�Č��Ή��̂��ߏC��
Revesion History : 2005/01/06 ���� (SRA) JavaDoc�C��
Revesion History : 2007/04/17 ��іQ (���u) ���f�� 1139
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.data.HostEqtypeOrderReceiptRow;
import webbroker3.equity.message.WEB3MarginOrderNotifyRequest;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeRequestCodesForRead;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������ʒm�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p��������ʒm�T�[�r�X�����N���X�B
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyServiceImpl
    implements WEB3MarginOrderNotifyService
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOrderNotifyServiceImpl.class);
    /**
     * @@roseuid 4140066F029C
     */
    public WEB3MarginOrderNotifyServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * �M�p��������ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p��������ʒm�T�[�r�X�j�����ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057B16700AE
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginOrderNotifyRequest l_marginOrderNotifyRequest =
            (WEB3MarginOrderNotifyRequest)l_request;

        //1.1.validate()
        l_marginOrderNotifyRequest.validate();

        try
        {
            //1.2.getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //1.3.�M�p��������ʒmTransactionCallback()
            WEB3MarginOrderNotifyTransactionCallback l_transactionCallBack =
                new WEB3MarginOrderNotifyTransactionCallback();
            //1.4.set���ʃR�[�h�v���t�B�N�X�ꗗ�i)
            l_transactionCallBack.setOrderRequestNumberPrefixGroup(
                l_marginOrderNotifyRequest.orderRequestNumberPrefixGroup);
            //1.5.doTransaction()
            l_queryProcessor.doTransaction
                (QueryProcessor.TX_CREATE_NEW,
                l_transactionCallBack);
        }
        catch (DataCallbackException l_dce)
        {
            WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
            throw l_wbe;
        }
        catch (DataException l_de)
        {
            log.debug(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_marginOrderNotifyRequest.createResponse();
    }

    /**
     * (�M�p��������ʒmTransactionCallback)<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3MarginOrderNotifyTransactionCallback
        implements TransactionCallback
    {

        /**
         * (���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (�M�p��������ʒmTransactionCallback)<BR>
         * �R���X�g���N�^�B
         * @@return WEB3MarginOrderNotifyServiceImpl.WEB3MarginOrderNotifyTransactionCallback
         * @@roseuid 40EA59D20304
         */
        public WEB3MarginOrderNotifyTransactionCallback()
        {

        }

        /**
         * �M�p��������ʒm���������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�M�p��������ʒm�T�[�r�X�jprocess�v�Q�ƁB<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40EA59D202E5
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1. get���ʃR�[�h�v���t�B�N�X�ꗗ()
            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();

            //1.2. ���������ʒm���̓L���[�e�[�u���Ǎ�
            String[] l_requestCodesForReadList = null;
            try
            {
                l_requestCodesForReadList =
                    WEB3GentradeRequestCodesForRead.getRequestCodesForReadList(
                    WEB3MarginOrderNotifyRequest.PTYPE);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("(sonar_traded_code = ? or sonar_traded_code = ?)");
            l_sbWhere.append(" and status = ?");
            int l_intRequestNumberPrefixGroupCnt = 0;
            if (l_orderRequestNumberPrefixGroup != null)
            {
                l_intRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;
            }
            if (l_intRequestNumberPrefixGroupCnt > 0)
            {
                l_sbWhere.append(" and (order_request_number like ?");
                for (int i = 1; i < l_intRequestNumberPrefixGroupCnt ; i++)
                {
                    l_sbWhere.append(" or order_request_number like ?");
                }
                l_sbWhere.append(")");
            }
            int l_intRequestCodesForReadListCnt = 0;
            if (l_requestCodesForReadList != null)
            {
                l_intRequestCodesForReadListCnt = l_requestCodesForReadList.length;
            }
            if (l_intRequestCodesForReadListCnt > 0)
            {
                l_sbWhere.append(" and (request_code = ?");
                for (int i = 1; i < l_intRequestCodesForReadListCnt; i++)
                {
                    l_sbWhere.append(" or request_code = ?");
                }
                l_sbWhere.append(")");
            }
            Object[] l_objParams = new Object[l_intRequestNumberPrefixGroupCnt + l_intRequestCodesForReadListCnt + 3];
            l_objParams[0] = WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
            l_objParams[1] = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
            l_objParams[2] = WEB3StatusDef.NOT_DEAL;
            int l_intSize = 3;
            for (int i = 0; i < l_intRequestNumberPrefixGroupCnt; i++)
            {
                l_objParams[l_intSize++] = l_orderRequestNumberPrefixGroup[i] + "%";
                log.debug("���ʃR�[�h�v���t�B�N�X�ꗗ[ " + i + "]�F" + l_orderRequestNumberPrefixGroup[i]);
            }
            for (int i = 0; i < l_intRequestCodesForReadListCnt; i++)
            {
                l_objParams[l_intSize++] = l_requestCodesForReadList[i];
                log.debug("�����Ώۃf�[�^�R�[�h�ꗗ[ " + i + "]�F" + l_requestCodesForReadList[i]);
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    HostEqtypeOrderReceiptRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objParams);

            //1.3. �擾�����L���[�e�[�u���̃��R�[�h�����ALoop����
            int l_size = 0;
            if (l_lisRecords != null)
            {
                l_size = l_lisRecords.size();
            }
            WEB3MarginOrderNotifyUnitService l_orderNotifyUnitService =
                (WEB3MarginOrderNotifyUnitService)
                Services.getService(WEB3MarginOrderNotifyUnitService.class);
            for (int i = 0; i < l_size; i++)
            {
                HostEqtypeOrderReceiptParams l_params = new
                    HostEqtypeOrderReceiptParams((HostEqtypeOrderReceiptRow)l_lisRecords.get(i));
                
                //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                //�،���ЃR�[�h�F�@@�����������͒ʒm�L���[Params.�،���ЃR�[�h
                //���X�R�[�h�F�@@�@@�@@�@@�����������͒ʒm�L���[Params.���X�R�[�h
                //�����R�[�h�F�@@�@@�@@�@@�����������͒ʒm�L���[Params�����R�[
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountMgr =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                try
                {
                    l_accountMgr.getMainAccount(
                        l_params.getInstitutionCode(),
                        l_params.getBranchCode(),
                        l_params.getAccountCode());
                }
                catch(WEB3BaseException l_ex)
                {
                    log.debug(l_ex.getMessage(), l_ex);
                    l_params.setStatus(WEB3StatusDef.DEALT);
                    l_params.setLastUpdatedTimestamp(
                        GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                    continue;
                }

                //1.3.1. create(�����������͒ʒm�L���[Params)(
                WEB3MarginOrderNotifyDataAdapter l_adapter =
                    WEB3MarginOrderNotifyDataAdapter.create(l_params);
                //1.3.2. is�V�K������()
                boolean l_isOpenOrder = l_adapter.isOpenMarginOrder();
                //1.3.3. is�ԍϒ���()
                boolean l_isCloseOrder = l_adapter.isCloseMarginOrder();

                try
                {
                    //1.3.4. is�V�K���Ē���()�̖߂�l��true�̏ꍇ
                    if (l_isOpenOrder)
                    {
                        l_orderNotifyUnitService.notifyOpenMarginOrder(l_adapter);
                    }
                    //1.3.5. is�ԍϒ���()�̖߂�l��true�̏ꍇ
                    else if (l_isCloseOrder)
                    {
                        l_orderNotifyUnitService.notifyCloseMarginOrder(l_adapter);
                    }
                    //�ꌏ���������ֈڂ�.start
                    //--------------------
                    //�����敪�X�V�l �ݒ�@@(���펞)
                    //--------------------
                    //l_params.setStatus(WEB3StatusDef.DEALT);
                    //.end
                }
                catch (Exception l_exp)
                {
                    log.error("�ꌏ�����ɂăG���[�����F", l_exp);
                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
		            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (get���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B
         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public String[] getOrderRequestNumberPrefixGroup() {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * (set���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B
         * @@param orderRequestNumberPrefixGroup ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup) {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }
    }
}
@
