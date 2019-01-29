head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�����������T�[�r�X�C���^�Z�v�^(WEB3ToSuccOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 ������(���u) �V�K�쐬
Revesion History : 2008/05/05 ����(���u) �d�l�ύX�@@���f��No.314
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A�����������T�[�r�X�C���^�Z�v�^)<BR>
 * �A�����������T�[�r�X�C���^�Z�v�^�B
 * <BR>
 * @@author ������ <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccOrderServiceInterceptor implements Interceptor 
{
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccOrderServiceInterceptor.class);
    
    /**
     * @@roseuid 4348DAA80232
     */
    public WEB3ToSuccOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * �|���������b�N����B  <BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.lock����(<BR>
     * �@@�@@�@@�@@�ڋq.�،���ЃR�[�h, �ڋq.���X�R�[�h, �ڋq.�����R�[�h)���R�[������B<BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 43216E380084
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams[0] == null)
        {   
            log.error("�p�����[�^�l�s���B");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        try
        {
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)l_serviceParams[0];
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //�����}�l�[�W�����擾����
            WEB3GentradeAccountManager l_accountMananger =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAcc = l_subAccount.getMainAccount();

            //�|���������b�N����B
            //  �g���A�J�E���g�}�l�[�W��.lock����(
            //      �ڋq.�،���ЃR�[�h, �ڋq.���X�R�[�h, �ڋq.�����R�[�h)���R�[������B
            l_accountMananger.lockAccount(
                l_mainAcc.getInstitution().getInstitutionCode(),
                l_mainAcc.getBranch().getBranchCode(),
                l_mainAcc.getAccountCode());

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * �����ɉ������Ȃ��B<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 43216E380094
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {

    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * �����ɉ������Ȃ��B<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 43216E3800B3
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {

    }
}
@
