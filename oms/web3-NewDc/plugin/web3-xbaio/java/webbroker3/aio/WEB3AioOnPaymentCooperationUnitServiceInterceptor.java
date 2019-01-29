head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOnPaymentCooperationUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���A�gUnitService�C���^�Z�v�^(WEB3AioOnPaymentCooperationUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���A�gUnitService�C���^�Z�v�^) <BR>
 * �o���A�gUnitService�C���^�Z�v�^�N���X <BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationUnitServiceInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationUnitServiceInterceptor.class);
    
    /**
     * @@roseuid 41E77F4C0203
     */
    public WEB3AioOnPaymentCooperationUnitServiceInterceptor()
    {
    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B  <BR> 
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j  <BR> 
     *  <BR> 
     * �P�j�@@���������b�N����B  <BR> 
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(  <BR> 
     *      �،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     *   �������͒����P�ʂ��ҏW�B<BR> 
     * <BR> 
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 41BCF2EB0056
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);        
       
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("array is null OR the array's length is 0");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        AioOrderUnit[] l_aioOrderUnits = null;
        log.debug("l_serviceParams[0] = " + l_serviceParams[0]);
        if(l_serviceParams[0] instanceof AioOrderUnit[])
        {
            l_aioOrderUnits = (AioOrderUnit[]) (l_serviceParams[0]);            
        }
        else
        {
            log.debug(" �p�����[�^Error ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }               
       
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        try
        { 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);  
            
            long l_lngAccountId = l_aioOrderUnits[0].getAccountId();
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
            l_strBranchCode = l_acc.getBranch().getBranchCode(); 
            l_strAccountCode = l_acc.getAccountCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__ ",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�P�j�@@���������b�N����B 
        //�@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B  
        // �������͒����P�ʂ��ҏW�B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
        try
        {
            l_accountManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__error in lockAccount__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);        
        return null;
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * �����Ƃ��ẮA�������Ȃ��B<BR>
     * 
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41BCF2EB0076
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        
    }

    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * �����Ƃ��ẮA�������Ȃ��B<BR>
     * 
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41BCF2EB0095
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        
    }
}@
