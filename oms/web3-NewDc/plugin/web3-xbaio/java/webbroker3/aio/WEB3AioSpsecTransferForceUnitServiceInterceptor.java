head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.30.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSpsecTransferForceUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������U�֋���UnitService�C���^�Z�v�^(WEB3AioSpsecTransferForceUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/06 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (��������U�֋���UnitService�C���^�Z�v�^)<BR>
 * ��������U�֋���UnitService�C���^�Z�v�^�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceUnitServiceInterceptor implements Interceptor 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSpsecTransferForceUnitServiceInterceptor.class);
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     *�ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     *�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     *�@@�|�T�[�r�X�̈���[0]�������������U�փL���[Params�I�u�W�F�N�g�ɃL���X�g����B<BR>
     *�@@�|�����P�ʂ̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     *�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     *		������������U�փL���[Params.�،���ЃR�[�h <BR>
     *�@@����J�����_�R���e�L�X�g.���X�R�[�h = ������������U�փL���[Params.���X�R�[�h <BR>
     *�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     *�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h20�F�،��U�ցh <BR>
     *�@@����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h <BR>
     *�@@����J�����_�R���e�L�X�g.������t���i = �h11�F�،��U�ցh <BR>
     *�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh <BR>
     * <BR>
     *�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( ) <BR>
     *	  �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B <BR>
     *	�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     *�Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     *�@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     *�R�j�@@���������b�N����B <BR>
     *�@@�|�g���A�J�E���g�}�l�[�W��.lock����( <BR>
     *		�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B <BR>
     *  �������͓�����������U�փL���[Params���ҏW�B <BR>
     *  <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * @@param l_serviceParams - (�T�[�r�X���\�b�h����)<BR>
     * @@return Object
     * @@roseuid 415797AA00CC
     */
    public Object onCall(Method l_method, Object[] l_obj) 
    {
        String STR_METHOD_NAME = "onCall(Method l_method, Object[] l_obj)";
        log.entering(STR_METHOD_NAME);
        
        if (l_obj == null || l_obj.length == 0)
        {
            log.debug("�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);         
        }
        
        
        HostSpsecTransNotifyParams l_hostSpsecTransNotifyParams = null;
        if (l_obj[0] instanceof HostSpsecTransNotifyParams)
        {
            l_hostSpsecTransNotifyParams =
                (HostSpsecTransNotifyParams)l_obj[0];               
        }
        else 
        {
            log.debug("error in get necessory request");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        //�����P�ʂ̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
            
        //�،���ЃR�[�h = ������������U�փL���[Params.�،���ЃR�[�h
        String l_strInstitutionCode = l_hostSpsecTransNotifyParams.getInstitutionCode();
        
        //���X�R�[�h = ������������U�փL���[Params.���X�R�[�h
        String l_strBranchCode = l_hostSpsecTransNotifyParams.getBranchCode();
            
        //����J�����_�R���e�L�X�g.set�،���ЃR�[�h
        l_context.setInstitutionCode(l_strInstitutionCode);
        //����J�����_�R���e�L�X�g.set���X�R�[�h
        l_context.setBranchCode(l_strBranchCode);
        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h 
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h20�F�،��U�ցh 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SECURITY_TRANSFER);
        //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        //����J�����_�R���e�L�X�g.������t���i = �h11�F�،��U�ցh 
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.TRASUT_SUBSTITUTION_CHANGE);
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);
    
        //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g���� 
        //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
        //�Q�j��t�����A���t���[�����Z�b�g����
        //������ԊǗ�.setTimestamp()���R�[������ 
        //throw SystemLayerException  
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("__error in setTimestamp__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   
        
        //�R�j���������b�N����B 
        //�@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
        //  �������͓�����������U�փL���[Params���ҏW�B
        
        //a> FinApp, GenTradeAccountManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
        //b> �����R�[�h
        String l_strAccountCode = l_hostSpsecTransNotifyParams.getAccountCode();
            
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR> 
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * @@param l_objOnCall
     * @@param l_object
     * @@roseuid 415797AA00CF
     */
    public void onReturn(Object l_objOnCall, Object l_object) 
    {
        String STR_METHOD_NAME = "onReturn(Object l_objOnCall, Object l_obj)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);        
        
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
     * @@param l_objOnCall
     * @@param l_throwable
     * @@roseuid 415797AA00D2
     */
    public void onThrowable(Object l_objOnCall, Throwable l_throwable) 
    {
        String STR_METHOD_NAME = "onThrowable(" +
                "Object l_objOnCall, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);    

        log.exiting(STR_METHOD_NAME);       
    }
}
@
