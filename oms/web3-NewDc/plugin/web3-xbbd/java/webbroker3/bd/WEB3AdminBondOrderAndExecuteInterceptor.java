head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderAndExecuteInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҐV�K�����̓C���^�Z�v�^(WEB3AdminBondOrderAndExecuteInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3AdminBondExecCompleteRequest;


/**
 * (�Ǘ��ҐV�K�����̓C���^�Z�v�^)<BR>
 * �Ǘ��ҐV�K�����̓T�[�r�X�C���^�Z�v�^�N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondOrderAndExecuteInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B <BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminBondOrderAndExecuteInterceptor.class);
    
    /**
     * @@roseuid 44E3510501B5
     */
    public WEB3AdminBondOrderAndExecuteInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = "0:DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = "0:DEFAULT"<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = "25:��"<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = "28:��"<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "00�FDEFAULT"<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * ���ȉ��A�T�[�r�X���\�b�h���u���Ǘ��ҐV�K��芮�����N�G�X�g�v�̏ꍇ�̂ݎ��{�B <BR>
     * �R�j�@@���������b�N����B <BR>
     * �@@�R�|�P�j�ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)����擾����B<BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h�FOpLoginSecurityService���ҏW <BR>
     * �@@�@@�@@�@@�@@�@@���X�R�[�h�F���N�G�X�g.�ڋq���.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�����R�[�h�F���N�G�X�g.�ڋq���.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�R�|�Q�j�������b�N����B<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�E�،���ЃR�[�h�F�ڋq�I�u�W�F�N�g����擾<BR>
     * �@@�@@�E���X�R�[�h�F�ڋq�I�u�W�F�N�g����擾<BR>
     * �@@�@@�E�����R�[�h�F�ڋq�I�u�W�F�N�g����擾
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h
     * @@param l_serviceParam - (�T�[�r�X���\�b�h����)<BR>
     * �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 44B62391028F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
   
        try 
        {
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo(); 
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            String l_strInstitutionCode = l_admin.getInstitutionCode();
            String l_strBranchCode = l_admin.getBranchCode();
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode);
            //����J�����_�R���e�L�X�g.�s��R�[�h = "0:DEFAULT"
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.�����R�[�h = "0:DEFAULT"
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = "25:��"
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.BOND);
            //����J�����_�R���e�L�X�g.������t���i = "28:��"
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.BOND);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = "00�FDEFAULT"
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.DEFAULT);
            //�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B 
            //�ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_context);
            //�Q�j�@@��t�����A���t���[�����Z�b�g����B 
            //�|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException
            
            //���ȉ��A�T�[�r�X���\�b�h���u���Ǘ��ҐV�K��芮�����N�G�X�g�v�̏ꍇ�̂ݎ��{�B
            Object l_objRequest = l_serviceParam[0];
            if (l_objRequest instanceof WEB3AdminBondExecCompleteRequest)
            {
                WEB3AdminBondExecCompleteRequest l_request = (WEB3AdminBondExecCompleteRequest)l_objRequest;             
            
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();

                //�R�j�@@���������b�N����B 
                //�R�|�P�j�ڋq�I�u�W�F�N�g���擾����B
                //�g���A�J�E���g�}�l�[�W��.get�ڋq(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)����擾����B
                //[����]
                //�،���ЃR�[�h�FOpLoginSecurityService���ҏW 
                //���X�R�[�h�F���N�G�X�g.�ڋq���.���X�R�[�h
                //�����R�[�h�F���N�G�X�g.�ڋq���.�ڋq�R�[�h
                WEB3GentradeMainAccount l_mainAccount = 
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                        l_strInstitutionCode, 
                        l_request.accountInfo.branchCode,
                        l_request.accountInfo.accountCode);
                
                //�R�|�Q�j�������b�N����B
                //�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
                //[����]
                //�E�،���ЃR�[�h�F�ڋq�I�u�W�F�N�g����擾
                //�E���X�R�[�h�F�ڋq�I�u�W�F�N�g����擾
                //�E�����R�[�h�F�ڋq�I�u�W�F�N�g����擾
                 l_accountManager.lockAccount(
                     l_mainAccount.getInstitution().getInstitutionCode(),
                     l_mainAccount.getBranch().getBranchCode(),
                     l_mainAccount.getAccountCode());
            }
            
            log.exiting(STR_METHOD_NAME);
            return null;   
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_context - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * @@param  l_returnValue - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 44B6239102AF
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        
        final String STR_METHOD_NAME = " onReturn(Object, Object) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param  l_obj - (onCall���^�[���l)<BR>
     * onCall���^�[���l
     * @@param  l_throwable - (�T�[�r�X���\�b�h���^�[���l)<BR>
     * �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 44B6239102CE
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable) ";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                null);

        log.exiting(STR_METHOD_NAME);
    }

}
@
