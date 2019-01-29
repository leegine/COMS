head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���b�N�o�^������t�T�[�r�X�����N���X(WEB3AccInfoLockRegistReleaseAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.data.HostLockRegiAcceptParams;
import webbroker3.accountinfo.data.HostLockRegiAcceptRow;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (���b�N�o�^������t�T�[�r�X�����N���X)<BR>
 * ���b�N�o�^������t�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockRegistReleaseAcceptServiceImpl implements WEB3AccInfoLockRegistReleaseAcceptService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoLockRegistReleaseAcceptServiceImpl.class);
    
    /**
     * ���b�N�o�^������t���������{����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���b�N�o�^������texecute�v�Q�ƁB<BR>
     *@@param l_request
     *@@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1.1getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //���b�N�o�^������tTransactionCallback�𐶐�����B 
            WEB3AccInfoLockRegistReleaseAcceptTransactionCallback l_callback = 
                new WEB3AccInfoLockRegistReleaseAcceptTransactionCallback();
            
            /*
             * DB�g�����U�N�V�������������{����B 
             * [doTransaction()�Ɏw�肷�����] 
             * �g�����U�N�V���������F�@@TX_JOIN_EXISTING 
             * �g�����U�N�V�����R�[���o�b�N�F�@@���b�N�o�^������tTransactionCallback�C���X�^���X 
             */
            l_queryProcessor.doTransaction(TransactionalInterceptor.TX_JOIN_EXISTING, l_callback);
            
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //���X�|���X�f�[�^�𐶐�����B
        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }
    
    public class WEB3AccInfoLockRegistReleaseAcceptTransactionCallback implements TransactionCallback
    {
        /**
         * (�R���X�g���N�^)
         *�R���X�g���N�^
         */
        public WEB3AccInfoLockRegistReleaseAcceptTransactionCallback()
        {
            
        }
        
        /**
         * �g�����U�N�V�������������{����B<BR>  
         * <BR>
         * �V�[�P���X�} <BR>
         * �u���b�N�o�^������tprocess�v�Q�ƁB<BR>
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);
            
            //�������̃��b�N�qY�q�o�^������t�L���[�f�[�^���擾����B
            try
            {
                HostLockRegiAcceptParams[] l_params = this.getAcceptQueue(); 
            
                
                WEB3AccInfoLockRegistReleaseAcceptUnitService l_service =
                    (WEB3AccInfoLockRegistReleaseAcceptUnitService)Services.getService(WEB3AccInfoLockRegistReleaseAcceptUnitService.class);
                if (l_params != null)
                {
                    for (int i = 0; i < l_params.length; i++)
                    {
                        HostLockRegiAcceptParams l_acceptParams = l_params[i];
        
                        //���b�N�o�^������t�P�����������{���A�������ʂ�ԋp����B
                        String l_strStatus = l_service.notifyLockRegistReleaseAccep(l_acceptParams);
                        
                        /*
                         * ���b�N�o�^������t�L���[���X�V����B 
                         * 
                         * [update��t�L���[�i�j�Ɏw�肷������n
                         * ���b�N�o�^������t�L���[�F get��t�L���[�i�j�̖߂�l�̊e�v�f
                         * �����敪�F noti�������b�N�o�^������t�i�j�̖߂�l
                         */
                        this.updatQueue(l_acceptParams, l_strStatus);        
                    }
                }
            }
            catch (WEB3BaseException l_ex)
            {
                ErrorInfo l_errorInfo = l_ex.getErrorInfo();
                l_errorInfo.setErrorClass(l_ex.getClass().getName());
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_errorInfo);
            }
            return null;
        }
        
        /**
         *(get��t�L���[)
         * get��t�L���[
         * ���b�N�qY�q�o�^������t�L���[�f�[�^���擾����B <BR>
         * <BR>
         * �P�j�@@���b�N�qY�q�o�^������t�L���[�e�[�u������ <BR>
         * �@@�ȉ���[��������]�ŁA���b�N�qY�q�o�^������t�L���[�e�[�u�����s���b�N�I�v�V�����iselect for update�j�ɂČ�������B<BR> 
         * <BR>
         * �@@[��������] <BR>
         * �@@���b�N�qY�q�o�^������t�L���[.�����敪 = 0:������ And <BR>
         * �@@���b�N�qY�q�o�^������t�L���[.�f�[�^�R�[�h = GI84G�FY�q�o�^��t or GI84F�F���b�N�q�o�^������t<BR>
         * �@@���@@�������̃��b�N�qY�q�o�^������t�f�[�^ <BR>
         * �Q�j�@@�������ʂ�z��ɂ��ĕԋp����B <BR>
         * @@throws WEB3BaseException
         */
        public HostLockRegiAcceptParams[] getAcceptQueue() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " getAcceptQueue()";
            log.entering(STR_METHOD_NAME);
            
            //�P�jArrayList�𐶐�����B 
            List l_lisRecords = null;

            try
            {

                /*
                 * ���b�N�qY�q�o�^������t�L���[�f�[�^���擾����B 
                 * 
                 * �P�j�@@���b�N�qY�q�o�^������t�L���[�e�[�u������ 
                 * �@@�ȉ���[��������]�ŁA���b�N�qY�q�o�^������t�L���[�e�[�u�����s���b�N�I�v�V�����iselect for update�j�ɂČ�������B
                 * 
                 * �@@[��������] 
                 * �@@���b�N�qY�q�o�^������t�L���[.�����敪 = 0:������ And <BR>
                 * �@@���b�N�qY�q�o�^������t�L���[.�f�[�^�R�[�h = GI84G�FY�q�o�^��t or GI84F�F���b�N�q�o�^������t
                 * 
                 * �@@���@@�������̃��b�N�qY�q�o�^������t�f�[�^ 
                 * 
                 * �Q�j�@@�������ʂ�z��ɂ��ĕԋp����B
                 */
                StringBuffer l_sbQueryString = new StringBuffer();
                l_sbQueryString.append("status = ?");
                l_sbQueryString.append(" and (request_code = ? or request_code = ?)");
                
                Object[] l_queryDataContainer = new Object[] {
                        WEB3StatusDef.NOT_DEAL,
                        WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL_ACCEPT,
                        WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL_ACCEPT};
                //�Q�j�ڋq�s�I�u�W�F�N�g��List���擾����B

                /*
                  * �P�j�@@���b�N�qY�q�o�^������t�L���[�e�[�u������ 
                 * �@@�ȉ���[��������]�ŁA���b�N�qY�q�o�^������t�L���[�e�[�u�����s���b�N�I�v�V�����iselect for update�j�ɂČ�������B 
                 * 
                 * �@@[��������] 
                 * �@@���b�N�qY�q�o�^������t�L���[.�����敪 = 0:������ And 
                 * �@@���b�N�qY�q�o�^������t�L���[.�f�[�^�R�[�h = GI84G�FY�q�o�^��t or GI84F�F���b�N�q�o�^������t
                 */
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    HostLockRegiAcceptRow.TYPE,
                    l_sbQueryString.toString(),
                    " for update",
                    l_queryDataContainer);
            }
            catch (DataFindException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            log.exiting(STR_METHOD_NAME);
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                return null;
            }
            //�Q�j�@@�������ʂ�z��ɂ��ĕԋp����B
            HostLockRegiAcceptParams[] l_params = new HostLockRegiAcceptParams[l_lisRecords.size()];
            l_lisRecords.toArray(l_params);
            return l_params;
        }
        
        /**
         * (��������������t�L���[)<BR>
         * ��������������t�L���[<BR>
         * ���b�N�o�^������t�L���[���X�V(update)����B <BR>
         * �|�����̃��b�N�o�^������t�L���[�Ɉȉ��̒ʂ�l���Z�b�g���čX�V�iupdate)����B <BR>
         * <BR>
         * �@@�@@���b�N�o�^������t�L���[.�����敪 = �����敪 <BR>
         * <BR>
         *     ��DB�X�V�d�l<BR>
         *    �@@�@@�@@�u���b�N�o�^������t_���b�N�qY�q�o�^������t�L���[�e�[�u��.xls�v�Q�� <BR>
         * @@param l_params
         * @@param l_strStatus
         * @@throws WEB3BaseException
         */
        public void updatQueue(HostLockRegiAcceptParams l_params, String l_strStatus) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " updatQueue(HostLockRegiAcceptParams l_params, String l_strStatus)";
            log.entering(STR_METHOD_NAME);
            
            /*
             *���b�N�o�^������t�L���[���X�V(update)����B 
             *   �|�����̃��b�N�o�^������t�L���[�Ɉȉ��̒ʂ�l���Z�b�g���čX�V�iupdate)����B 
             *   
             *   ���b�N�o�^������t�L���[.�����敪 = �����敪 
             *   
             *   ��DB�X�V�d�l
             *   �u���b�N�o�^������t_���b�N�qY�q�o�^������t�L���[�e�[�u��.xls�v�Q�� 
             */
                        
            l_params.setStatus(l_strStatus);
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_params);
            }
            catch (DataFindException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
    }
}
@
