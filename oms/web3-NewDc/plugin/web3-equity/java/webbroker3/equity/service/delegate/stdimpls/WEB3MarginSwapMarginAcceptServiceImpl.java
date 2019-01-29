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
filename	WEB3MarginSwapMarginAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n��t�T�[�r�XImpl(WEB3MarginSwapMarginAcceptServiceImpl.java)
Revesion History : 2004/10/8 Ḗ@@��(���u) �V�K�쐬
                   2004/12/13 �����a��(SRA) �c�Č��Ή� �m��.�R�T�T
                   2004/12/21 �����a��(SRA) JavaDoc�C��
                   2005/01/05 �����a��(SRA) �������b�N�C��
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;
import webbroker3.equity.data.HostEqtypeSwapAcceptRow;
import webbroker3.equity.message.WEB3MarginSwapMarginAcceptRequest;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptUnitService;
import webbroker3.gentrade.WEB3GentradeRequestCodesForRead;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������n��t�T�[�r�XImpl�j�B<br>
 * <br>
 * �M�p����������n��t�T�[�r�X�����N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptServiceImpl implements WEB3MarginSwapMarginAcceptService
{

    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginSwapMarginAcceptServiceImpl.class);

    /**
     * <p>�i�M�p����������n��t�T�[�r�XImpl�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3MarginSwapMarginAcceptServiceImpl()
    {
    }

    /**
     * <p>�iexecute�j�B</p>
     * <p>�M�p����������n��t�T�[�r�X���������{����B<br>
     * <br>
     * �V�[�P���X�}<br>
     * �u�i�M�p����������n��t�T�[�r�X�j�������n��t�v�Q�ƁB</p>
     * @@param l_request ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "EXECUTE";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 1.1 validate()
        WEB3MarginSwapMarginAcceptRequest l_marginSwapMarginAcceptRequest = (WEB3MarginSwapMarginAcceptRequest)l_request;
        l_marginSwapMarginAcceptRequest.validate();

        QueryProcessor l_QueryProcessor = null;
        try
        {
            // 1.2 getDefaultProcessor()
            l_QueryProcessor = Processors.getDefaultProcessor();
            // 1.3 �M�p����������n��tTransactionCallback
            WEB3MarginSwapMarginAcceptTransactionCallback l_callback = new WEB3MarginSwapMarginAcceptTransactionCallback();
            // 1.4 set���ʃR�[�h�v���t�B�N�X�ꗗ(���ʃR�[�h�v���t�B�N�X�ꗗ : String[])
            l_callback.setOrderRequestNumberPrefixGroup(l_marginSwapMarginAcceptRequest.orderRequestNumberPrefixGroup);
            // 1.5 doTransaction
            WEB3BaseException l_baseExp = (WEB3BaseException) l_QueryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callback);
            if (l_baseExp != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw l_baseExp;
            }
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
        return l_request.createResponse();
    }

    /**
     * <p>�i�M�p����������n��tTransactionCallback�j�B</p>
     * <p>�M�p����������n��tTransactionCallback�N���X�B</p>
     */
    public class WEB3MarginSwapMarginAcceptTransactionCallback implements TransactionCallback
    {

        /**
         * <p>�i���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
         * <p>���ʃR�[�h�v���t�B�N�X�ꗗ�B</p>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * <p>�i�M�p����������n��tTransactionCallback�j�B</p>
         * <p>�R���X�g���N�^�B</p>
         */
        public WEB3MarginSwapMarginAcceptTransactionCallback()
        {
        }

        /**
         * <p>�iprocess�j�B</p>
         * <p>�g�����U�N�V�������������{����B<br>
         * <br>
         * �V�[�P���X�}<br>
         * �u�i�M�p����������n��t�T�[�r�X�jprocess�v�Q�ƁB</p>
         * @@return Object
         */
        public Object process() throws DataQueryException, DataNetworkException, IllegalStateException
        {

            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1
            //get���ʃR�[�h�v���t�B�N�X�ꗗ()
            String[] l_orderRequestNumberPrefixGroup = this.getOrderRequestNumberPrefixGroup();
            
            // �����Ώۃf�[�^�R�[�h�e�[�u���Ǎ�
            String[] l_requestCodesForReadList = null;
            try
            {
                l_requestCodesForReadList =
                    WEB3GentradeRequestCodesForRead.getRequestCodesForReadList(
                    WEB3MarginSwapMarginAcceptRequest.PTYPE);
            }
            catch (WEB3BaseException l_wbe)
            {
                throw new DataCallbackException(l_wbe.getMessage(), l_wbe);
            }
            
            //1.2
            //[��������]
            //�y�������n��t�L���[�e�[�u���z���Aselect for update�ɂ��ȉ��̏������w�肵�ēǂݍ��ށB
            log.debug("1.2 �������n��t�L���[�e�[�u���ǂݍ���");
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" status = ? "); //�����敪 == �h�������h

            int l_intRequestNumberPrefixGroupCnt = l_orderRequestNumberPrefixGroup.length;

            l_sbWhere.append(" and (");
            for(int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
            {
                if(i == 0)
                {
                    l_sbWhere.append(" substr(order_request_number , 1 , 2) = ?");
                } else
                {
                    l_sbWhere.append(" or substr(order_request_number , 1 , 2) = ?");
                }
            }
            l_sbWhere.append(" )");
            
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
            
            Object[] l_objBindValues = new Object[l_intRequestNumberPrefixGroupCnt + l_intRequestCodesForReadListCnt + 1];
            l_objBindValues[0] = WEB3StatusDef.NOT_DEAL;
            int l_intSize = 1;

            for(int i = 0 ; i < l_intRequestNumberPrefixGroupCnt ; i++)
            {
                l_objBindValues[l_intSize++] = l_orderRequestNumberPrefixGroup[i];
                log.debug("���ʃR�[�h�v���t�B�N�X�ꗗ�F" + l_orderRequestNumberPrefixGroup[i]);
            }
            for (int i = 0; i < l_intRequestCodesForReadListCnt; i++)
            {
                l_objBindValues[l_intSize++] = l_requestCodesForReadList[i];
                log.debug("�����Ώۃf�[�^�R�[�h�ꗗ[ " + i + "]�F" + l_requestCodesForReadList[i]);
            }

            //�X���[ DataNetworkException, DataFindException
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            //������t�L���[�e�[�u���̌��ʃ��X�g
            List l_lisSearchResult = null;

            //l_lisSearchResult = l_QueryProcessor.doFindAllQuery(HostEqtypeSwapAcceptRow.TYPE, l_sbWhere.toString(), null, "FOR UPDATE", l_objIfoOrderUnitWhere);
            l_lisSearchResult = l_QueryProcessor.doFindAllQuery(HostEqtypeSwapAcceptRow.TYPE, l_sbWhere.toString(), null, null, l_objBindValues);
            int l_intNum = 0;
            if (l_lisSearchResult != null )
            {
                l_intNum = l_lisSearchResult.size();
            }

            //1.3(�����L���[�e�[�u���̃��R�[�h�����ALoop�����j
            log.debug("1.3 �擾�����L���[�e�[�u���̃��R�[�h�����ALoop����");
            WEB3MarginSwapMarginAcceptUnitService l_accept = (WEB3MarginSwapMarginAcceptUnitService) Services.getService(WEB3MarginSwapMarginAcceptUnitService.class);

            for (int i = 0; i < l_intNum; i++)
            {
                HostEqtypeSwapAcceptParams l_params = new HostEqtypeSwapAcceptParams((HostEqtypeSwapAcceptRow)l_lisSearchResult.get(i));

                try
                {
                    //1.3.1 notify�������n��t(�������n��t�L���[Params : �������n��t�L���[Params)
                    l_accept.notifySwapMarginAccept(l_params);

                    //1.3.6 �����ΏۃL���[���R�[�h�G���[��update����
                    //�ꌏ���������ֈڂ�.start
                    //log.debug("1.3.2 �����ΏۃL���[���R�[�h��update����");
                    //l_mapChanges.put("status", WEB3StatusDef.DEALT);
                    //l_QueryProcessor.doUpdateQuery(l_params.getPrimaryKey(), l_mapChanges);
                    //.end
                }
                catch (Exception l_ex)
                {
                    //1.3.6 �����ΏۃL���[���R�[�h�G���[��update����
                    if (l_ex instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
                        if (l_wre.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
                        {
                            log.debug("�������b�N���s�F" + l_params.toString());
                            continue;
                        }
                    }
                    log.error("�ꌏ�����ɂăG���[����", l_ex);
                    l_params.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_QueryProcessor.doUpdateQuery(l_params);
                }
            }

            log.entering(STR_METHOD_NAME);
            return null;
        }


        /**
         * <p>�iget���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
         * <p>���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B</p>
         * @@return ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return this.orderRequestNumberPrefixGroup;
        }

        /**
         * <p>�iset���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
         * <p>�����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B</p>
         * @@param orderRequestNumberPrefixGroup ���ʃR�[�h�v���t�B�N�X�ꗗ
         */
        public void setOrderRequestNumberPrefixGroup(String[] orderRequestNumberPrefixGroup)
        {
            this.orderRequestNumberPrefixGroup = orderRequestNumberPrefixGroup;
        }

    }
}
@
