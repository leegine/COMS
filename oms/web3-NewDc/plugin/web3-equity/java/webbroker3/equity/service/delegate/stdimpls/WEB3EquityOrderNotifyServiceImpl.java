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
filename	WEB3EquityOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�T�[�r�XImpl(WEB3EquityOrderNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/26 �R�w��(���u) �쐬
Revesion History : 2004/12/13 �����a��(SAR) �c�Č��Ή� �m��.�O�S�T���m��.�O�S�V���m��.�O�S�W���m��.�O�T�P���m��.�O�U�Q
                                                     ���m��.�P�S�V���m��.�P�T�R���m��.�Q�R�Q���m��.�Q�R�X���m��.�Q�V�X
                                                     ���m��.�R�O�S���m��.�R�T�S
Revesion History : 2005/01/05 �����a��(SRA) �������b�N�̑Ή�
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2007/04/17 ��іQ (���u) ���f�� 1139
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Iterator;
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

import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.message.WEB3EquityOrderNotifyRequest;
import webbroker3.equity.service.delegate.WEB3EquityOrderInputNotifyAdapter;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeRequestCodesForRead;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �i���������ʒm�T�[�r�XImpl�j �B<BR>
 * <BR>
 * ���������ʒm���[�X�P�[�X�̃G���g���|�C���g�B<BR>
 * �L���[�e�[�u������̃f�[�^�擾�A�y�� <BR>
 * ���������p�ƐM�p����p�� <BR>
 * �o�^�������s���T�u�T�[�r�X�̌Ăѕ������s���B
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyServiceImpl implements WEB3EquityOrderNotifyService
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderNotifyServiceImpl.class);

    /**
     * @@roseuid 40B3EE9800AB
     */
    public WEB3EquityOrderNotifyServiceImpl()
    {

    }

    /**
     * �iexecute�j�B<BR>
     * <BR>
     * �����ŗ^����ꂽ���N�G�X�g����ɋƖ��������s���A<BR>
     * �������ʂ����X�|���X�ɐݒ肵�Ă�Ԃ��B
     * @@param l_request ���N�G�X�g
     * @@return �������ʂ��ݒ肳�ꂽ���X�|���X
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4021F50103E3
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderNotifyRequest l_eqOrderAcceptRequest =
            (WEB3EquityOrderNotifyRequest)l_request;

        // 1.1 validate( )
        l_eqOrderAcceptRequest.validate();

        try
        {
            // 1.2 getDefaultProcessor()
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            // 1.3 ���������ʒmTransactionCallback()
            WEB3EquityOrderNotifyServiceTransactionCallback l_orderNotifyServiceTransactionCallback =
                new WEB3EquityOrderNotifyServiceTransactionCallback();

            // 1.4 set���ʃR�[�h�v���t�B�N�X�ꗗ()
            l_orderNotifyServiceTransactionCallback.setOrderRequestNumberPrefixGroup(
                l_eqOrderAcceptRequest.orderRequestNumberPrefixGroup);

            // 1.5 doTransaction()
            l_qp.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_orderNotifyServiceTransactionCallback);
        }
        catch (DataCallbackException l_dce)
        {
            WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
            throw l_wbe;
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_eqOrderAcceptRequest.createResponse();
    }

    /**
     * �i���������ʒmTransactionCallback�j�B<BR>
     * <BR>
     * ���������ʒmTransactionCallback<BR>
     * �g�����U�N�V�������������{��������N���X
     * @@author �����a��(SRA)
     * @@version 1.0
     */
    public class WEB3EquityOrderNotifyServiceTransactionCallback
        implements TransactionCallback
    {

        /**
         * ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * @@roseuid 40B3F2C600AB
         */
        public WEB3EquityOrderNotifyServiceTransactionCallback()
        {

        }

        /**
         * �iprocess�j�B<BR>
         * <br>
         * �����ʒm���������{����B<BR>
         * <br>
         * �V�[�P���X�}<br>
         * �u�i���������ʒm�T�[�r�X�jprocess�v�Q��<BR>
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40B3F1530148
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";

            log.entering(STR_METHOD_NAME);

            // 1.1 get���͒ʒm�L���[( )
            List l_lisHostEqtypeOrderNotify = getHostEqtypeOrderReceipt();


            if (l_lisHostEqtypeOrderNotify != null)
            {
                Iterator l_iterator = l_lisHostEqtypeOrderNotify.iterator();

                // 1.2 �擾�����L���[�e�[�u���̃��R�[�h����Loop����
                while (l_iterator.hasNext())
                {
                    HostEqtypeOrderReceiptParams l_params =
                        (HostEqtypeOrderReceiptParams)l_iterator.next();
                    QueryProcessor l_qp = Processors.getDefaultProcessor();

                    //1.2.1get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
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
                        l_qp.doUpdateQuery(l_params);
                        continue;
                    }

                    // 1.2.1 create(�����������͒ʒm�L���[Params)
                    // �����������͒ʒm�f�[�^�A�_�v�^��create����B
                    // �����͈ȉ��̒ʂ�ɐݒ肷��B
                    // �����������͒ʒm�L���[Params�F�@@�����Ώہy�����������͒ʒm�L���[�e�[�u���z�P���R�[�h
                    WEB3EquityOrderInputNotifyAdapter l_adapter =
                        WEB3EquityOrderInputNotifyAdapter.create(l_params);

                    try
                    {
                        // 1.2.2 ���b�Z�[�W �ʒm�ꌏ����(�����������͒ʒm�f�[�^�A�_�v�^ : �����������͒ʒm�f�[�^�A�_�v�^)
                        WEB3EquityOrderNotifyUnitService l_service =
                            (WEB3EquityOrderNotifyUnitService)Services.getService(
                                WEB3EquityOrderNotifyUnitService.class);
                        l_service.notifyPartProcess(l_adapter);

                        // �����ΏۃL���[���R�[�h�̏����敪��update
                        //�ꌏ���������ֈڂ�.start
                        //log.debug("1.3.2 �����������͒ʒm�L���[�e�[�u��.�����敪��update����");
                        //l_params.setStatus(WEB3StatusDef.DEALT);
                        //l_qp.doUpdateQuery(l_params);
                        //.end
                    }
                    catch (Exception l_exp)
                    {
                        log.error("�ꌏ�����ɂăG���[�����F", l_exp);
                        l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_qp.doUpdateQuery(l_params);
                    }
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (get���͒ʒm�L���[)<BR>
         * <BR>
         * �P�j�@@�y�����������͒ʒm�L���[�e�[�u���z����A<BR>
         * �@@�@@�ȉ��̏����ɍ��v����S�f�[�^�� SELECT FOR UPDATE�i�s���b�N�j�Ŏ擾���A<BR>
         * �@@�@@�u�����������͒ʒm�L���[�e�[�u��Params�v�̃��X�g���쐬����B<BR>
         * <BR>
         * �@@�@@�@@�@@�@@�f�[�^�R�[�h��"AI821"(�����������͒ʒm)"<BR>
         * �@@�@@���@@����R�[�h�iSONAR�j���i"�ʏ����i�������j"�A"����O����"�j <BR>
         * �@@�@@���@@���ʃR�[�h�̐擪�Q�����Aget���ʃR�[�h�v���t�B�N�X�ꗗ( )�̖߂�l�z��̂����ꂩ�ƈ�v<BR>
         * �@@�@@���@@�����敪��"������"<BR>
         * <BR>
         * �Q�j�@@�u�����������͒ʒm�L���[�e�[�u��Params�v��List��Ԃ��B<BR>
         * @@return List
         * @@throws DataQueryException
         * @@throws DataNetworkException
         * @@throws DataCallbackException
         * @@roseuid 402711F301E2
         */
        public List getHostEqtypeOrderReceipt() throws DataQueryException, DataNetworkException, DataCallbackException
        {
            final String STR_METHOD_NAME = "getHostEqtypeOrderReceipt()";
            log.entering(STR_METHOD_NAME);

            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
            
            String[] l_requestCodesForReadList = null;
            try
            {
                l_requestCodesForReadList =
                    WEB3GentradeRequestCodesForRead.getRequestCodesForReadList(
                    WEB3EquityOrderNotifyRequest.PTYPE);
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
            log.debug("��������������:[" + l_sbWhere.toString() + "]");
            Object[] l_objWhere = new Object[l_intRequestNumberPrefixGroupCnt + l_intRequestCodesForReadListCnt + 3];
            l_objWhere[0] = WEB3TransactionTypeSONARDef.MARKET_TRADING;
            l_objWhere[1] = WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET;
            l_objWhere[2] = WEB3StatusDef.NOT_DEAL;
            int l_intSize = 3;
            for (int i = 0; i < l_intRequestNumberPrefixGroupCnt; i++)
            {
                l_objWhere[l_intSize++] = l_orderRequestNumberPrefixGroup[i] + "%";
                log.debug("���ʃR�[�h�v���t�B�N�X�ꗗ[" + i + "]�F[" + l_orderRequestNumberPrefixGroup[i] + "%]");
            }
            for (int i = 0; i < l_intRequestCodesForReadListCnt; i++)
            {
                l_objWhere[l_intSize++] = l_requestCodesForReadList[i];
                log.debug("�����Ώۃf�[�^�R�[�h�ꗗ[" + i + "]�F[" + l_requestCodesForReadList[i] + "]");
            }
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisHostEqtypeOrderAccept =
                l_qp.doFindAllQuery(
                    HostEqtypeOrderReceiptParams.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);

            log.exiting(STR_METHOD_NAME);
            return l_lisHostEqtypeOrderAccept;
        }

        /**
         * (get���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B
         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * (set���ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B
         * @@param orderRequestNumberPrefixGroup ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup)
        {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }
    }
}
@
