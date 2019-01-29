head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���b�N�o�^������t�P���T�[�r�X�����N���X(WEB3AccInfoLockRegistRelaxationAcceptUnitImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
                   2006/01/16 ������(�k�����u) �d�l�ύX�E���f��082,�c�a�X�V�d�l020
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.List;


import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.data.HostLockRegiAcceptParams;
import webbroker3.accountinfo.data.HostLockRegistParams;
import webbroker3.accountinfo.data.HostLockRegistRow;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptUnitService;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3AccountLockErrorCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (���b�N�o�^������t�P���T�[�r�X�����N���X)<BR>
 * ���b�N�o�^������t�P���T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl implements WEB3AccInfoLockRegistReleaseAcceptUnitService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl.class);
    
    /**
     * ���b�N�o�^������t�P�����������{���A�������ʁi�����ρ^�G���[�j��ԋp����B<BR> 
     * <BR>
     * �P�j�@@���b�N�qY�q�o�^�����L���[�f�[�^�擾 <BR>
     * �@@���b�N�qY�q�o�^������t�L���[.�f�[�^�R�[�h�ɑΉ����郍�b�N�qY�q�o�^�����L���[�f�[�^���ȉ��̏����Ō�������B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@���b�N�qY�q�o�^�����L���[�e�[�u��.�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h(*1) And <BR>
     * �@@���b�N�qY�q�o�^�����L���[�e�[�u��.�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And <BR>
     * �@@���b�N�qY�q�o�^�����L���[�e�[�u��.���X�R�[�h = ��t�L���[.���X�R�[�h And <BR>
     * �@@���b�N�qY�q�o�^�����L���[�e�[�u��.�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h And <BR>
     *   ���b�N�qY�q�o�^�����L���[�e�[�u��.���ʃR�[�h = ��t�L���[.���ʃR�[�h <BR>
     * <BR>
     * �@@�擾�ł��Ȃ������ꍇ�́A�����敪.9�F�G���[ ��ԋp����B <BR>
     * 
     *   (*1)��t�L���[.�f�[�^�R�[�h == GI84G�F(WEB3HostRequestCodeDef)�̏ꍇ�A�hGI847�F(WEB3HostRequestCodeDef)�h���Z�b�g����B<BR>
     * �@@�@@�@@ ��t�L���[.�f�[�^�R�[�h == GI84F�F(WEB3HostRequestCodeDef)�̏ꍇ�A�hGI846�F(WEB3HostRequestCodeDef)�h���Z�b�g����B<BR>
     * <BR>
     * �Q�j�@@�G���[�̏ꍇ�i���j�A�ȉ��̏��������{����B<BR>  
     * �@@�i���j�@@�G���[�̏ꍇ <BR>
     * �@@���b�N�qY�q�o�^������t�L���[�e�[�u��.��t�ʒm�敪 == 2�F�G���[ ���� <BR>
     * �@@���b�N�qY�q�o�^������t�L���[�e�[�u��.�G���[�R�[�h�����L�̃G���[�R�[�h�ɓ��Ă͂܂�Ȃ��ꍇ�B <BR>
     * �@@[�G���[�R�[�h]  <BR>
     * �@@�@@1900�F��d�o�^�G���[ <BR>
     * �@@�@@FF51�F�����σG���[ <BR>
     * �@@�@@FF72�F���b�N�G���[ <BR>
     * �@@�@@FF70�F�����G���[ <BR>
     * �@@�@@FF73�F�F�G���[ <BR> 
     *     1E00�F����敪�G���[ <BR>
     *     2700�F�Y������ <BR>
     * 
     *�Q�|�P�j �擾�������b�N�qY�q�o�^�����L���[�f�[�^�X�V�iDB-update�j�B<BR>  
     *�@@�@@�@@�@@�@@ �擾�������b�N�qY�q�o�^�����L���[�f�[�^���ADB�X�V�d�l�̒ʂ�X�V�iupdate)����B<BR>  
     *<BR>
     *�@@�@@�@@�@@�@@�X�V���e�́ADB�X�V�d�l <BR>
     *�@@�@@�@@�@@�@@�u���b�N�o�^������t_���b�N�qY�q�o�^�����L���[�e�[�u��.xls�v �Q�ƁB<BR>  
     *<BR>
     *�P�j�`�Q�j�̏����ŁA�G���[�����������ꍇ�A9�F�����敪.�G���[ ��ԋp����B <BR> 
     *�ȊO�A�����敪.1�F�����ρ@@��ԋp����B  <BR>
     * @@param l_params
     * @@return String
     */
    public String notifyLockRegistReleaseAccep(HostLockRegiAcceptParams l_params) 
    {
        final String STR_METHOD_NAME = " notifyLockRegistReleaseAccep(HostLockRegiAcceptParams l_params) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_params == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_params] = " + l_params
                );
        }
        /*
         * �P�j�@@���b�N�qY�q�o�^�����L���[�f�[�^�擾 
         * �@@���b�N�qY�q�o�^������t�L���[.�f�[�^�R�[�h�ɑΉ����郍�b�N�qY�q�o�^�����L���[�f�[�^���ȉ��̏����Ō�������B 
         * 
         * �@@[��������] 
         * �@@���b�N�qY�q�o�^�����L���[�e�[�u��.�f�[�^�R�[�h = ��t�L���[.�f�[�^�R�[�h(*1) And 
         * �@@���b�N�qY�q�o�^�����L���[�e�[�u��.�،���ЃR�[�h = ��t�L���[.�،���ЃR�[�h And 
         * �@@���b�N�qY�q�o�^�����L���[�e�[�u��.���X�R�[�h = ��t�L���[.���X�R�[�h And 
         * �@@���b�N�qY�q�o�^�����L���[�e�[�u��.�ڋq�R�[�h = ��t�L���[.�ڋq�R�[�h And 
         *   ���b�N�qY�q�o�^�����L���[�e�[�u��.���ʃR�[�h = ��t�L���[.���ʃR�[�h 
         */
        String l_strStatus = null;
        List l_lisRecords = null;
        
        String l_strStitutionCode = l_params.getInstitutionCode();
        String l_strBranchCode = l_params.getBranchCode();
        String l_strAccountCode = l_params.getAccountCode();

        try
        {
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("request_code = ?");
            l_sbQueryString.append(" and institution_code = ?");
            l_sbQueryString.append(" and branch_code = ?");
            l_sbQueryString.append(" and account_code = ?");
            l_sbQueryString.append(" and order_request_number = ?");
            
            String l_strRequestCode = null;
            
            if (WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL_ACCEPT.equals(l_params.getRequestCode()))
            {
                l_strRequestCode = WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL;
            }
            else if (WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL_ACCEPT.equals(l_params.getRequestCode()))
            {
                l_strRequestCode = WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL;
            }

            
            Object[] l_queryDataContainer = new Object[] {
                    l_strRequestCode,
                    l_strStitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_params.getOrderRequestNumber()};
            

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostLockRegistRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainer);
            
            //�擾�ł��Ȃ������ꍇ�́A�����敪.9�F�G���[ ��ԋp����B
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                l_strStatus = WEB3StatusDef.DATA_ERROR;
                return l_strStatus;
            }
            
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            return l_strStatus;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            return l_strStatus;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            return l_strStatus;
        }
        
        HostLockRegistRow l_row = (HostLockRegistRow)l_lisRecords.get(0);
        
        HostLockRegistParams l_lockRegistParams = new HostLockRegistParams(l_row);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); 

            String l_errorCode = l_params.getErrorCode();
            //�Q�j�@@�G���[�̏ꍇ�i���j�A�ȉ��̏��������{����B

            // ���b�N�qY�q�o�^������t�L���[�e�[�u��.��t�ʒm�敪 == 2�F�G���[ ���� 
            // ���b�N�qY�q�o�^������t�L���[�e�[�u��.�G���[�R�[�h�����L�̃G���[�R�[�h�ɓ��Ă͂܂�Ȃ��ꍇ�B
            // �@@[�G���[�R�[�h]  
            //  �@@�@@1900�F��d�o�^�G���[ 
            //  �@@�@@FF51�F�����σG���[ 
            //  �@@�@@FF72�F���b�N�G���[ 
            //  �@@�@@FF70�F�����G���[ 
            //  �@@�@@FF73�F�F�G���[ 
            //  �@@�@@1E00�F����敪�G���[
            //      2700�F�Y������
            if (WEB3AcceptDivDef.ERROR.equals(l_params.getAcceptStatus()) 
                && !WEB3AccountLockErrorCodeDef.DOUBLE_REGI_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.ERASED_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.LOCK_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.LOCK_OFF_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.PERMISSION_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.CANCEL_DIV_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.NO_DATA_ERROR.endsWith(l_errorCode))
            {                                
                
                 // �Q�|�P�j �擾�������b�N�qY�q�o�^�����L���[�f�[�^�X�V�iDB-update�j�B 
                 //�@@�@@�@@�@@�@@ �擾�������b�N�qY�q�o�^�����L���[�f�[�^���ADB�X�V�d�l�̒ʂ�X�V�iupdate)����B 
                 // 
                 // �@@�@@�@@�@@�@@�X�V���e�́ADB�X�V�d�l
                 // �@@�@@�@@�@@�@@�u���b�N�o�^������t_���b�N�qY�q�o�^�����L���[�e�[�u��.xls�v �Q�ƁB 

                l_lockRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_lockRegistParams.setStatus(WEB3StatusDef.DATA_ERROR); 
                
                l_queryProcessor.doUpdateQuery(l_lockRegistParams);                                 
            }
            
            //�P�j�`�Q�j�̏����ŁA�G���[�����������ꍇ�A9�F�����敪.�G���[ ��ԋp����B �ȊO�A�����敪.1�F�����ρ@@��ԋp����B  
            l_strStatus = WEB3StatusDef.DEALT;           
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            l_lockRegistParams.setStatus(WEB3StatusDef.DATA_ERROR);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            l_lockRegistParams.setStatus(WEB3StatusDef.DATA_ERROR);
        }         
        log.exiting(STR_METHOD_NAME);
        return l_strStatus;
    }
}
@
