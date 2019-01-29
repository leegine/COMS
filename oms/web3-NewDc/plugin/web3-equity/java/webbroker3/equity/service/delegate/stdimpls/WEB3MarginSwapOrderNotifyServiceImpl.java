head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����ʒm�T�[�r�XImpl(WEB3MarginSwapOrderNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/27 �X��   (SRA)  �c�Č��Ή�
Revesion History : 2005/01/06 ����   (SRA)  JavaDoc�C��
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
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.data.HostEqtypeSwapReceiptParams;
import webbroker3.equity.data.HostEqtypeSwapReceiptRow;
import webbroker3.equity.message.WEB3MarginSwapOrderNotifyRequest;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyDataAdapter;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeRequestCodesForRead;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�M�p����������n�����ʒm�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����������n�����ʒm�T�[�r�X�����N���X�B
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyServiceImpl
    implements WEB3MarginSwapOrderNotifyService
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyServiceImpl.class);


    /**
     * (�R���X�g���N�^)�B<BR>
     */
    public WEB3MarginSwapOrderNotifyServiceImpl()
    {
    }


    /**
     * (execute)�B<BR>
     * <BR>
     * �p����������n�����ʒm�T�[�r�X���������{����B <BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p����������n�����ʒm�T�[�r�X�j�������n�����ʒm�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);


        //--------------------
        //���X�|���X�̐���
        //--------------------
        WEB3MarginSwapOrderNotifyRequest l_request1 = (WEB3MarginSwapOrderNotifyRequest)l_request;
        WEB3BackResponse l_response = l_request1.createResponse();


        //--------------------
        //validate
        //--------------------
        l_request1.validate();


        try
        {
            //--------------------
            //getDefaultProcessor
            //--------------------
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();


            //--------------------
            //�M�p����������n�����ʒmTransactionCallback
            //--------------------
            WEB3MarginSwapOrderNotifyTransactionCallback l_transactionCallBack =
                new WEB3MarginSwapOrderNotifyTransactionCallback();


            //--------------------
            //set���ʃR�[�h�v���t�B�N�X�ꗗ
            //--------------------
            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_request1.orderRequestNumberPrefixGroup);


            //--------------------
            //doTransaction(�g�����U�N�V��������(=TX_CREATE_NEW)
            //--------------------
            l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallBack);


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
        return l_response;
    }


    /**
     * (�M�p����������n�����ʒmTransactionCallback)�B<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3MarginSwapOrderNotifyTransactionCallback
        implements TransactionCallback
    {

        /**
         * (���ʃR�[�h�v���t�B�N�X�ꗗ)�B<BR>
         */
        private String[] orderRequestNumberPrefixGroup;


        /**
         * (get���ʃR�[�h�v���t�B�N�X�ꗗ)�B<BR>
         * <BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B<BR>
         * <BR>
         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public String[] getOrderRequestNumberPrefixGroup() {
            return this.orderRequestNumberPrefixGroup;
        }


        /**
         * (set���ʃR�[�h�v���t�B�N�X�ꗗ)�B<BR>
         * <BR>
         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B<BR>
         * <BR>
         * @@param orderRequestNumberPrefixGroup ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup) {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }


        /**
         * (�M�p����������n�����ʒmTransactionCallback)�B<BR>
         * <BR>
         * �R���X�g���N�^<BR>
         * @@return WEB3MarginSwapOrderNotifyTransactionCallback
         */
        public WEB3MarginSwapOrderNotifyTransactionCallback()
        {
        }


        /**
         * (process)�B<BR>
         * <BR>
         * �M�p����������n�����ʒm���������{����B <BR>
         * <BR>
         * �V�[�P���X�}�u�i�M�p����������n�����ʒm�T�[�r�X�jprocess�v�Q�ƁB<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);


            //--------------------
            //get���ʃR�[�h�v���t�B�N�X�ꗗ
            //--------------------
            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
            int l_orderRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;

            //--------------------
            // �����Ώۃf�[�^�R�[�h�e�[�u���Ǎ�
            //--------------------
            String[] l_requestCodesForReadList = null;
            try
            {
                l_requestCodesForReadList =
                    WEB3GentradeRequestCodesForRead.getRequestCodesForReadList(
                    WEB3MarginSwapOrderNotifyRequest.PTYPE);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            
            //--------------------
            //�������n���͒ʒm�L���[�e�[�u���Ǎ�
            //--------------------
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" status = ? ");

            Object[] l_objParams = new Object[l_orderRequestNumberPrefixGroupCnt + l_requestCodesForReadList.length + 1];
            l_objParams[0] = WEB3StatusDef.NOT_DEAL;

            log.debug("l_objParams[0] = [" + l_objParams[0].toString() + "]");
            int l_intSize = 1;

            // ���ʃR�[�h�̐擪�Q����get���ʃR�[�h�v���t�B�N�X�ꗗ()�̖߂�l�z��̂����ꂩ�ƈ�v
            l_sbWhere.append("and (");
            for(int i = 0 ; i < l_orderRequestNumberPrefixGroupCnt; i++)
            {
                if (i == 0)
                {
                    l_sbWhere.append("order_request_number like ?");
                }
                else
                {
                    l_sbWhere.append(" or order_request_number like ?");
                }
                l_objParams[i + l_intSize] = l_orderRequestNumberPrefixGroup[i] + "%";
                log.debug("l_objParams[" + Integer.toString(i + l_intSize) + "] = [" + l_objParams[i + l_intSize].toString() + "]");
            }
            l_sbWhere.append(")");

            l_intSize = l_intSize + l_orderRequestNumberPrefixGroupCnt;
            int l_intRequestCodesForReadListCnt = 0;
            if (l_requestCodesForReadList != null)
            {
                l_intRequestCodesForReadListCnt = l_requestCodesForReadList.length;
            }
            if (l_intRequestCodesForReadListCnt > 0)
            {
                l_sbWhere.append(" and (request_code = ?");
                l_objParams[l_intSize++] = l_requestCodesForReadList[0];
                log.debug("l_objParams[" + Integer.toString(l_intSize - 1) + "] = [" + l_objParams[l_intSize - 1].toString() + "]");
                for (int i = 1; i < l_intRequestCodesForReadListCnt; i++)
                {
                    l_sbWhere.append(" or request_code = ?");
	                l_objParams[l_intSize++] = l_requestCodesForReadList[i];
	                log.debug("l_objParams[" + Integer.toString(l_intSize - 1) + "] = [" + l_objParams[l_intSize - 1].toString() + "]");
                }
                l_sbWhere.append(")");
            }
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            log.debug("l_sbWhere = [" +l_sbWhere.toString() + "]");
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostEqtypeSwapReceiptRow.TYPE, l_sbWhere.toString(), null, l_objParams);
            int l_iSize = l_lisRecords.size();


            //--------------------
            //�擾�����L���[�e�[�u���̃��R�[�h�����ALoop����
            //--------------------
            WEB3MarginSwapOrderNotifyUnitService l_swapOrderNotifyUnitService =
                (WEB3MarginSwapOrderNotifyUnitService) Services.getService(WEB3MarginSwapOrderNotifyUnitService.class);

            for (int i = 0; i < l_iSize; i++)
            {
                HostEqtypeSwapReceiptParams l_params
                    = new HostEqtypeSwapReceiptParams((HostEqtypeSwapReceiptRow) l_lisRecords.get(i));

                // get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                //�،���ЃR�[�h�F�@@�������n���͒ʒm�L���[Params.�،���ЃR�[�h  
                //���X�R�[�h�F�@@�@@�@@�@@�������n���͒ʒm�L���[Params.���X�R�[�h 
                //�����R�[�h�F�@@�@@�@@�@@�������n���͒ʒm�L���[Params�����R�[

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

                WEB3MarginSwapOrderNotifyDataAdapter l_adapter =
                    WEB3MarginSwapOrderNotifyDataAdapter.create(l_params);

                try
                {
                    //--------------------
                    //(����敪������̏ꍇ)
                    //notify�������n�������
                    //--------------------
                    if (WEB3CancelDivDef.CANCEL.equals(l_params.cancel_div))
                    {
                        l_swapOrderNotifyUnitService.notifyCancelSwapOrder(l_adapter);
                    }
                    //--------------------
                    //(����敪������̏ꍇ)
                    //notify�������n����
                    //--------------------
                    else
                    {
                        l_swapOrderNotifyUnitService.notifySwapOrder(l_adapter);
                    }

                    //--------------------
                    //�����ΏۃL���[UPDATE�@@(=>������)
                    //--------------------
                    //�ꌏ���������ֈڂ�.start
                    //l_params.setStatus(WEB3StatusDef.DEALT);
                    //l_queryProcessor.doUpdateQuery(l_params);
                    //.end

                }
                catch (Exception l_e)
                {
                    //--------------------
                    //�����ΏۃL���[UPDATE�@@(�G���[��)
                    //--------------------
                    log.error("�ꌏ�����ɂăG���[�����F" + l_params.toString());
                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_queryProcessor.doUpdateQuery(l_params);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
