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
filename	WEB3AsynIfoOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �敨OP������t�T�[�r�X�����N���X(WEB3AsynIfoOrderAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : �����ŗ����F
                    2004/06/15 羉s (���u) �V�K�쐬
                   �񓯊��Ή��ŗ����F
                    2005/03/11 ���u���@@�񓯊����s�Ή��i�V�K�N���X�j
*/


package webbroker3.ifo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.ifo.data.HostFotypeOrderAcceptRow;
import webbroker3.ifo.message.WEB3IfoOrderAcceptRequest;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 * �i�񓯊��Ή��敨OP������t�T�[�r�X�����N���X�j�B
 * @@author  : ���u���i���{���u�j
 * @@version : 1.0
 */
public class WEB3AsynIfoOrderAcceptServiceImpl
    implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynIfoOrderAcceptServiceImpl.class);

    /**
     * ������t���N�G�X�g
     */
    private WEB3IfoOrderAcceptRequest request;


    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3AsynIfoOrderAcceptServiceImpl(WEB3IfoOrderAcceptRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynIfoOrderAcceptServiceImpl�Frun()";
        log.entering(STR_METHOD_NAME);
        
        try
        {    
            try
            {
                WEB3IfoOrderAcceptRequest l_ifoOrderAcceptRequest =
                    (WEB3IfoOrderAcceptRequest) request;
                    
                log.debug("Enter the try panth");
                //�N�G���v���Z�b�T�̃C���X�^���X���擾����
                //�X���[ DataNetworkException, DataFindException
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //�敨OP������tTransactionCallback�I�u�W�F�N�g�𐶐�
                WEB3IfoOrderAcceptTransactionCallback l_ifoOrderAcceptTransactionCallback =
                    new WEB3IfoOrderAcceptTransactionCallback();
                    
                
                //set���ʃR�[�h�v���t�B�N�X�ꗗ()
                l_ifoOrderAcceptTransactionCallback.setOrderRequestNumberPrefixGroup(l_ifoOrderAcceptRequest.
                    orderRequestNumberPrefixGroup);

                l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_ifoOrderAcceptTransactionCallback);
                log.debug("Exit the try path.");
            }
            catch (DataQueryException l_dqex)
            {
                //DB�A�N�Z�X�����s�̏ꍇ
                log.error( getClass().getName() + STR_METHOD_NAME ,l_dqex);
                //��O���X���[����
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataNetworkException l_dnex)
            {
                //DB�A�N�Z�X�����s�̏ꍇ
                log.error( getClass().getName() + STR_METHOD_NAME ,l_dnex);
                //��O���X���[����
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
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
     * �敨OP������tTransactionCallback<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    private class WEB3IfoOrderAcceptTransactionCallback implements TransactionCallback
    {

        /**
         * (�敨OP������tTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^<BR>
         * @@return webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptServiceImpl.WEB3IfoOrderAcceptTransactionCallback
         * @@roseuid 408359FA002B
         */
        public WEB3IfoOrderAcceptTransactionCallback()
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
         * �u�i�敨OP������t�jprocess�v�Q�ƁB<BR>
         * <BR>
         * �P�j �����Ώۃ��R�[�h�擾<BR>
         * ������t�L���[�e�[�u�����A�s���b�N�iselect for update�j�ɂēǂݍ��ށB<BR>
         * <BR>
         * �@@[��������]<BR>
         * �@@�敨OP������t�L���[�e�[�u��.�f�[�^�R�[�h == <BR>
         *   �h�I�v�V����������t�h�iEI80A�j<BR>
         *   �܂��́A�h�敨������t�h�iEI80C�j<BR>
         * �@@�敨OP������t�L���[�e�[�u��.�����敪 == �h�������h<BR>
         * <BR>
         * �ȍ~�̏����́A�������ʂ̒�����t�L���[�e�[�u���̊e�s�ɑ΂��Ď��{����B<BR>
         * <BR>
         * �Q�j �C���^�Z�v�^�Z�b�g<BR>
         * �敨OP������t�C���^�Z�v�^�I�u�W�F�N�g�𐶐����A<BR>
         * setThreadLocalPersistenceEventInterceptor()�ɂāA<BR>
         * �����}�l�[�W����ThreadLocal�ɃZ�b�g����B<BR>
         * <BR>
         * [�R���X�g���N�^�̈���]<BR>
         * �G���[�R�[�h�F�@@�敨OP������t�L���[�e�[�u��.�G���[���b�Z�[�W<BR>
         * <BR>
         * �R�j �L���[�ɊY�����钍���P�ʎ擾<BR>
         * �����}�l�[�W��.get�����P��()���R�[�����A<BR>
         * �����ΏۃL���[�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�،���ЃR�[�h�@@�F������t�L���[�e�[�u��.�،���ЃR�[�h<BR>
         * �@@���X�R�[�h�@@�F������t�L���[�e�[�u��.���X�R�[�h<BR>
         * �@@���i�^�C�v�@@�F�h�敨�I�v�V�����h<BR>
         * �@@���ʃR�[�h�@@�F������t�L���[�e�[�u��.���ʃR�[�h<BR>
         * <BR>
         * �S�j �R�[���o�b�N�T�[�r�X�i�����X�V�����j���s<BR>
         * �����ΏۃL���[�̓��e�𔻒肵�A�ȉ��̏��������{����B<BR>
         * <BR>
         * �E������t�L���[�e�[�u��.������t���� == "������t����"�ł���΁A<BR>
         * �@@DefaultNewOrderAcceptedMarketResponseMessage�𐶐�����B<BR>
         * <BR>
         * �@@IfoMarketResponseReceiverCallbackService�N���X���擾���A<BR>
         * �@@process���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�i���������p�����[�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �@@�����T�[�o�ɒʒm�inotify()�j���s���B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�����P��.getOrderId<BR>
         * <BR>
         * �E������t�L���[�e�[�u��.������t���� == "�G���["�ł���΁A<BR>
         * �@@DefaultNewOrderRejectMarketResponseMessage�𐶐�����B<BR>
         * <BR>
         * �@@IfoMarketResponseReceiverCallbackService�N���X���擾���A<BR>
         * �@@process���\�b�h���R�[������B<BR>
         * <BR>
         * �@@[����]<BR>
         * �@@�i���������p�����[�^�I�u�W�F�N�g�j<BR>
         * <BR>
         * �T�j �L���[�e�[�u���ɏ����ς��X�V<BR>
         * �ȉ��̒ʂ�A�����ΏۃL���[���R�[�h���X�V����B<BR>
         * <BR>
         * �@@[�X�V���e]<BR>
         * �@@�i�e�s���̏����ŗ�O�����������ꍇ�j<BR>
         * �@@�敨OP������t�L���[�e�[�u��.�����敪 == �h�G���[�h<BR>
         * <BR>
         * �@@�i�ȊO�̏ꍇ�j<BR>
         * �@@�敨OP������t�L���[�e�[�u��.�����敪 == "������"<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4083597C01D1
         */
        public java.lang.Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "execute(l_request)";
            log.entering( STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //[��������]
            //�敨OP������t�L���[�e�[�u��.�f�[�^�R�[�h == �h�I�v�V����������t�h�iEI80A�j�܂��́A�h�敨������t�h�iEI80C�j
            //�敨OP������t�L���[�e�[�u��.�����敪 == �h�������h
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" (request_code = ? "); //�f�[�^�R�[�h == �h�I�v�V����������t�h
            l_sbWhere.append(" or request_code = ?) "); //�f�[�^�R�[�h ==�h�敨������t�h
            l_sbWhere.append(" and status = ? "); //�����敪 == �h�������h

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
            
            Object[] l_objIfoOrderUnitWhere = new Object[l_intPrefixGroupLength + 3];
            
            l_objIfoOrderUnitWhere[0] = WEB3HostRequestCodeDef.OPTION_ORDER_ACCEPT;
            l_objIfoOrderUnitWhere[1] = WEB3HostRequestCodeDef.FUTURES_ORDER_ACCEPT;
            l_objIfoOrderUnitWhere[2] = WEB3StatusDef.NOT_DEAL;
            for (int i = 0; i < l_intPrefixGroupLength; i++)
            {
                l_objIfoOrderUnitWhere[i + 3] = orderRequestNumberPrefixGroup[i] + "%";
            }

            //�X���[ DataNetworkException, DataFindException
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            //������t�L���[�e�[�u���̌��ʃ��X�g
            List l_lisSearchResult = null;

            //�X���[ DataQueryExeption,DataNetworkException,
            //doFindAllQuery(String tableName, String where, String orderBy, String conditions, Object bindVars[])
            l_lisSearchResult = l_QueryProcessor.doFindAllQuery(
                HostFotypeOrderAcceptRow.TYPE,
                l_sbWhere.toString(),
                null,
                null,
                l_objIfoOrderUnitWhere
                );
            log.debug("Get the number of the records." + l_lisSearchResult);
            if (l_lisSearchResult == null)
            {
                log.error(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }

            //�敨OP������t�L���[�e�[�u��ROW�I�u�W�F�N�g
            HostFotypeOrderAcceptParams  l_hostFotypeOrderAcceptParams = null;

            //�����P�ʃI�u�W�F�N�g
            OrderUnit l_orderUnit = null;

            int i = 0;
            int l_intLen = 0;
            l_intLen = l_lisSearchResult.size();
            log.debug("Output the number of the records"  + l_intLen);

            for ( i = 0; i < l_intLen; i ++)
            { 
                l_hostFotypeOrderAcceptParams = new HostFotypeOrderAcceptParams((HostFotypeOrderAcceptRow)l_lisSearchResult.get(i));               
                try
                {                            
                    // TransactionCallback����
                    WEB3IfoOrderAcceptNormalTransactionCallback l_transactionCallback =
                        new WEB3IfoOrderAcceptNormalTransactionCallback(l_hostFotypeOrderAcceptParams);

                    // doTransaction()
                    l_QueryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch (Exception l_exp)
                {
                    //--------------------
                    //�����ΏۃL���[UPDATE�@@(�G���[��)
                    //--------------------
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
                    Map l_mapChanges = new HashMap();
                    l_mapChanges.put("status",WEB3StatusDef.DATA_ERROR);
                    l_mapChanges.put("last_updated_timestamp",GtlUtils.getSystemTimestamp());
                    l_QueryProcessor.doUpdateQuery(l_hostFotypeOrderAcceptParams.getPrimaryKey(), l_mapChanges);
                }
            }
            log.exiting( STR_METHOD_NAME);
            return null;

        }
    }
}
        
        
        
        
        @
