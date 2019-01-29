head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP������t�ꌏTransactionCallback(WEB3IfoOrderAcceptNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/11 ���u��(���{���u) �V�K�쐬
Revesion History : 2007/01/25 ���� (���u) �d�l�ύX ���f��605
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�敨OP������t�ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3IfoOrderAcceptNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderAcceptNormalTransactionCallback.class);

    /**
      * �敨OP������t�L���[Params�I�u�W�F�N�g�B<BR>
      */
    private HostFotypeOrderAcceptParams hostFotypeOrderAcceptParams;
        
    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostFotypeOrderAcceptParams - (�敨OP������t�L���[Params)
     */
    public WEB3IfoOrderAcceptNormalTransactionCallback(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
    {
        hostFotypeOrderAcceptParams = l_hostFotypeOrderAcceptParams;
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
                         
        QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

        WEB3IfoOrderAcceptUnitService l_orderAcceptUnitService =
            (WEB3IfoOrderAcceptUnitService)Services.getService(
                WEB3IfoOrderAcceptUnitService.class);

        String l_strAcceptStatus = this.hostFotypeOrderAcceptParams.getAcceptStatus();

        try
        {
            //�i�@@����t���[�F�L���[.������t���ʋ敪�@@==�@@�h������t�����h
            //�@@�܂��́@@�h�G���[�h�@@�j�@@�̏ꍇ
            if (WEB3AcceptStatusDef.OVER.equals(l_strAcceptStatus)
                || WEB3AcceptStatusDef.ERROR.equals(l_strAcceptStatus))
            {
                //notify������t(�敨OP������t�L���[Params)
                l_orderAcceptUnitService.notifyOrderAccept(this.hostFotypeOrderAcceptParams);
            }
            //�i�@@����t���[�F�L���[.������t���ʋ敪�@@==�@@�h�O���t���ԊO�G���[�h�@@�j�@@�̏ꍇ
            else if (WEB3AcceptStatusDef.MORN_SESS_ACCEPT_OVERTIME_ERROR.equals(
                l_strAcceptStatus))
            {
                //notify��t���ԊO(�敨OP������t�L���[Params)
                l_orderAcceptUnitService.notifyOrderAcceptOvertime(this.hostFotypeOrderAcceptParams);
            }

            //1.2.2 (*2) �L���[�e�[�u���ɏ����敪�X�V
            // �|�����Ώے�����t�L���[���R�[�h.�����敪�Ɂh�����ρh���Z�b�g��DB�X�V����B
            hostFotypeOrderAcceptParams.setStatus(WEB3StatusDef.DEALT);
            hostFotypeOrderAcceptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_QueryProcessor.doUpdateQuery(hostFotypeOrderAcceptParams);
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }
                    
        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
