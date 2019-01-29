head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransForeignServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���i�O�݁j�T�[�r�XImpl(WEB3AioSonarCashTransForeignServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

import webbroker3.aio.data.HostForeignCashTransferParams;
import webbroker3.aio.data.HostForeignCashTransferRow;
import webbroker3.aio.message.WEB3AioSonarCashTransForeignResponse;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR���o���i�O�݁j�T�[�r�XImpl)<BR>
 * SONAR���o���i�O�݁j�T�[�r�X�����N���X<BR> 
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransForeignServiceImpl 
    implements WEB3AioSonarCashTransForeignService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransForeignServiceImpl.class);
    
    /**
     * SONAR���o���i�O�݁j�T�[�r�X�������s���B<BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�iSONAR���o���i�O�݁j�jexecute�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //1.1 SONAR���o���i�O�݁jTransactionCallback( )
            //SONAR���o���i�O�݁j�g�����U�N�V�����R�[���o�b�N�C���X�^���X�𐶐�����B
            WEB3AioSonarCashTransForeignTransactionCallback 
                l_aioSonarCashTransForeignTransactionCallback = 
                    new WEB3AioSonarCashTransForeignTransactionCallback();
            
            //1.2 getDefaultProcessor( )
            //�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.3 doTransaction(�g�����U�N�V�������� : int, 
            //�g�����U�N�V�����R�[���o�b�N : TransactionCallback)
            //DB�g�����U�N�V�������������{����B 
            //[doTransaction()�Ɏw�肷�����] 
            //�g�����U�N�V���������F�@@TX_CREATE_NEW 
            //�g�����U�N�V�����R�[���o�b�N�F�@@SONAR���o���i�O�݁jTransactionCallback�C���X�^���X
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW, 
                l_aioSonarCashTransForeignTransactionCallback);
            
            //1.4 createResponse( )
            WEB3AioSonarCashTransForeignResponse
                l_aioSonarCashTransForeignResponse =
                    (WEB3AioSonarCashTransForeignResponse)l_request.createResponse();
            
            //�|��������SONAR���o���i�O�݁j���X�|���X��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_aioSonarCashTransForeignResponse;
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (SONAR���o���i�O�݁jTransactionCallback )<BR>
     * SONAR���o���i�O�݁j�g�����U�N�V�����R�[���o�b�N�N���X<BR>
     */
    public class WEB3AioSonarCashTransForeignTransactionCallback
        implements TransactionCallback
    {
        /**
         * ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(
                WEB3AioSonarCashTransForeignTransactionCallback.class);

        /**
         * (SONAR���o���i�O�݁jTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^<BR>
         */
        public WEB3AioSonarCashTransForeignTransactionCallback()
        {

        }

        /**
         * SONAR���o���i�O�݁j�������s���B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�iSONAR���o���i�O�݁j�jprocess�v �Q��<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process() ";
            m_log.entering(STR_METHOD_NAME);
            
            //�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.1 (*1) �O�ݓ��o���e�[�u���Ǎ�
            //    (*1) �ȉ��̏����ŁA�O�ݓ��o���e�[�u������
            // �@@�@@�@@�s���b�N�I�v�V�����ɂă��R�[�h���擾����B
            // [��������]
            // �f�[�^�R�[�h = "GI853"
            // �����敪 = "0"�i�������j
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");
            l_sbWhere.append(" and status = ? ");

            Object[] l_objValues = {
                WEB3HostRequestCodeDef.FOREIGN_CASH_TRANSFER,
                WEB3StatusDef.NOT_DEAL};

            /*����*/
            List l_lisForeignHostCashTransferRows =
                l_queryProcessor.doFindAllQuery(
                    HostForeignCashTransferRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objValues);
            
            int l_intSize = 0;
            if (l_lisForeignHostCashTransferRows != null && 
                !l_lisForeignHostCashTransferRows.isEmpty())
            {
                l_intSize = l_lisForeignHostCashTransferRows.size();
            }

            //1.2 �擾�������R�[�h���̃��[�v����
            for (int i = 0; i < l_intSize; i ++)
            {
                //�O�ݓ��o��Params���擾����
                HostForeignCashTransferRow l_hostForeignCashTransferRow =
                   (HostForeignCashTransferRow)l_lisForeignHostCashTransferRows.get(i);
                HostForeignCashTransferParams l_hostForeignCashTransferParams = 
                    new HostForeignCashTransferParams(l_hostForeignCashTransferRow);
                
                //==========�O�ݓ��o���e�[�u���̏����敪�̍X�V�ݒ�============
                
                boolean l_blnIsFail = false;
                //�����敪��ݒ�
                HashMap l_map = new HashMap();
                
                //�O�ݓ��o���e�[�u���̏����敪�̍X�V�����ݒ�
                
                String l_strUpdateWhere = " rowid = ? ";
                
                //�O�ݓ��o���e�[�u���̏����敪�̍X�V�l�ݒ�
                String[] l_strUpdateParams = {l_hostForeignCashTransferParams.getRowid()};

                //==========================End==========================
                
                try
                {
                    WEB3AioSonarCashTransForeignNormalTransactionCallback l_transactionCallback =
                        new WEB3AioSonarCashTransForeignNormalTransactionCallback(
                            l_hostForeignCashTransferRow);

                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch(Exception l_ex)
                {
                    l_blnIsFail = true;
                    m_log.debug("__an Exception__ ", l_ex);
                }
                if (l_blnIsFail)
                {
                    //1.2.2.3 (*2) �O�ݓ��o���e�[�u���̏����敪�̍X�V
                    //(*2) �O�ݓ��o���e�[�u��.�����敪�Ɉȉ��̒l���Z�b�g���čX�V����B
                    //"9"�i�G���[�j�F ��L�����iLoop���̏����j�ŗ�O�����������ꍇ
                    l_map.put("status", WEB3StatusDef.DATA_ERROR);
                    l_queryProcessor.doUpdateAllQuery(
                        HostForeignCashTransferRow.TYPE,
                        l_strUpdateWhere,
                        l_strUpdateParams,
                        l_map);
                }
            }
            
            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
