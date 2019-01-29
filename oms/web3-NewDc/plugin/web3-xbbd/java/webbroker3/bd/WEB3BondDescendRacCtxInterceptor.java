head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����菈��RAC�R���e�L�X�g�C���^�Z�v�^(WEB3BondDescendRacCtxInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/28 ꎉ� (���u) �V�K�쐬
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����菈��RAC�R���e�L�X�g�C���^�Z�v�^)<BR>
 * �����菈��RAC�R���e�L�X�g�C���^�Z�v�^�N���X
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3BondDescendRacCtxInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondDescendRacCtxInterceptor.class);
    
    /**
     * (VID)<BR>
     * VID
     */
    private static long VID = -1;
    
    /**
     * RAC�R���e�L�X�g��ݒ肷��B <BR>
     *  <BR>
     * �P�j����ID��錾���A����������B <BR> 
     * �@@�@@����ID = VID  <BR>
     *      <BR>
     * �Q�j�T�[�r�X�̈����𔻒肵�A����ID���Z�b�g <BR> 
     *�@@�@@�Q�|�P�j�T�[�r�X�̈���.length > 0 ���� �T�[�r�X�̈���[0] instanceof OrderUnit�̏ꍇ <BR> 
     *�@@�@@�@@�Q�|�P�|�P�j�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B  <BR>
     *�@@�@@�@@�Q�|�P�|�Q�j����ID = �����P��.get����ID()  <BR>
     *  <BR>
     * �R�jRAC Context�̐ݒ� <BR> 
     *�@@�@@�R�|�P�j����ID��0���傫���ꍇ <BR> 
     *�@@�@@�@@�R�|�P�|�P�jRAC Context �T�[�r�X(WEB3DescendRacCtxService)���擾����B <BR> 
     *�@@�@@�@@�R�|�P�|�Q�j�擾�����T�[�r�X��null�łȂ��ꍇ�A�擾�����T�[�r�X.setAccountIdCtx(����ID)���R�[������ <BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@[����]  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@����ID������ID <BR> 
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h<BR>
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����<BR>
     * @@return Object                                 
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j����ID��錾���A����������B 
        //����ID = VID 
        long l_lngAccountId = this.VID;
        
        //�Q�j�T�[�r�X�̈����𔻒肵�A����ID���Z�b�g 
        //�Q�|�P�j�T�[�r�X�̈���.length > 0 ���� �T�[�r�X�̈���[0] instanceof OrderUnit�̏ꍇ
        if (l_serviceParam.length > 0 && l_serviceParam[0] instanceof OrderUnit)
        {
        	//�Q�|�P�|�P�j�T�[�r�X�̈���[0]�𒍕��P�ʃI�u�W�F�N�g�ɃL���X�g����B
        	OrderUnit l_orderUnit = (OrderUnit)l_serviceParam[0];
        	
        	//�Q�|�P�|�Q�j����ID = �����P��.get����ID() 
        	l_lngAccountId = l_orderUnit.getAccountId();
        }
        
        //�R�jRAC Context�̐ݒ� 
        WEB3DescendRacCtxService l_descendRacCtxService = null;
        //�R�|�P�j����ID��0���傫���ꍇ 
        if (l_lngAccountId > 0)
        {
            //�@@�R�|�P�|�P�jRAC Context �T�[�r�X(WEB3DescendRacCtxService)���擾����B 
        	l_descendRacCtxService = 
        		(WEB3DescendRacCtxService) Services.getService(WEB3DescendRacCtxService.class);
        	
            if (l_descendRacCtxService != null)
            {
                //�R�|�P�|�Q�j�擾�����T�[�r�X��null�łȂ��ꍇ�A
            	// �擾�����T�[�r�X.setAccountIdCtx(����ID)���R�[������ 
                //�@@[����] 
                //  ����ID������ID 
            	l_descendRacCtxService.setAccountIdCtx(l_lngAccountId);
            }
        }
               
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * �A�C�e���̒�`<BR>
     * RAC�R���e�L�X�g���N���A����B<BR> 
     * <BR>
     * �@@�P�jRAC Context �T�[�r�X(WEB3DescendRacCtxService)���擾����B<BR> 
     *   �Q�j�擾�����T�[�r�X��null�łȂ��ꍇ�A�擾�����T�[�r�X.clearAccountIdCtx()���Ă�<BR> 
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jRAC Context �T�[�r�X(WEB3DescendRacCtxService)���擾����B 
    	WEB3DescendRacCtxService l_descendRacCtxService = 
    		(WEB3DescendRacCtxService) Services.getService(WEB3DescendRacCtxService.class);
    	
    	//�Q�j�擾�����T�[�r�X��null�łȂ��ꍇ�A�擾�����T�[�r�X.clearAccountIdCtx()���Ă�
    	if (l_descendRacCtxService != null)
    	{
        	l_descendRacCtxService.clearAccountIdCtx();
    	}
    	
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * RAC�R���e�L�X�g���N���A����B<BR> 
     * <BR>
     * �@@�P�jRAC Context �T�[�r�X(WEB3DescendRacCtxService)���擾����B<BR> 
     * �@@�Q�j�擾�����T�[�r�X��null�łȂ��ꍇ�A�擾�����T�[�r�X.clearAccountIdCtx()���Ă�<BR> 
     * @@param l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l<BR>
     * @@param l_throwable - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jRAC Context �T�[�r�X(WEB3DescendRacCtxService)���擾����B 
    	WEB3DescendRacCtxService l_descendRacCtxService = 
    		(WEB3DescendRacCtxService) Services.getService(WEB3DescendRacCtxService.class);
    	
    	//�Q�j�擾�����T�[�r�X��null�łȂ��ꍇ�A�擾�����T�[�r�X.clearAccountIdCtx()���Ă�
    	if (l_descendRacCtxService != null)
    	{
    	    l_descendRacCtxService.clearAccountIdCtx();
    	}
    	
        log.exiting(STR_METHOD_NAME);
    }

}
@
