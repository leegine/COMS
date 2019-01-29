head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCancelExecutionServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o��������C���^�Z�v�^(WEB3AdminFeqCancelExecutionServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �A�C��(���u) �V�K�쐬
                 : 2005/08/01 ��O��(���u) ���r���[       
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO�������o��������C���^�Z�v�^)<BR>
 * �Ǘ��ҊO�������o��������C���^�Z�v�^<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqCancelExecutionServiceInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqCancelExecutionServiceInterceptor.class);

    /**
     * @@roseuid 42D0CE42032C
     */
    public WEB3AdminFeqCancelExecutionServiceInterceptor() 
    {
     
    }
    
    /**
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �@@�|���O�C���Z�b�V������胍�O�C���h�c���擾�C���O�C���h�c�ɊY������Ǘ��҂̏��<BR>
     * ���A<BR>
     * �@@�@@�ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.���X�R�[�h <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h10�F�O�������h <BR>
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ������<BR>
     * �R���e�L�X�g���Z�b�g����B <BR>
     * �@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j�@@���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * ���ȉ��A�T�[�r�X���\�b�h���u�Ǘ��ҊO�������o��������������N�G�X�g�v<BR>
     * �̏ꍇ�̂ݎ��{�B<BR>
     * �R�j�@@���������b�N����B <BR>
     * �@@�R�|�P�j�@@�T�[�r�X�̈���[0]�i�F���h�c�j�ɂāA�Y��������I�u�W�F�N�g��<BR>
     * �擾����B<BR>
     * �@@�@@�i�O�����������}�l�[�W��.getOrderExecution()���g�p�j<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�������b�N<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h,<BR> 
     * �����R�[�h)��<BR>
     * �R�[������B <BR>
     * �@@�@@�� �����͖��̏����ҏW�B <BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * 
     * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
     * �T�[�r�X�̈����z��<BR>
     * @@return Object
     * @@roseuid 4295FFDD01AC
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        long l_lngExecId = 0;

        try
        {
            //�Ǘ���
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            if (l_administrator == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�،���ЃR�[�h
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
            // ���X�R�[�h
            String l_strBranchCode = l_administrator.getBranchCode();
        
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
                   
            //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B        
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = null�h
            l_context.setMarketCode(null);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h10�F�O�������h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
            l_context.setOrderAcceptTransaction(null);
            
            // ������ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        
            //������ԊǗ�
            WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

            //���ȉ��A�T�[�r�X���\�b�h���u�Ǘ��ҊO�������o��������������N�G�X�g�v
            // �̏ꍇ�̂ݎ��{
            if (l_serviceParams != null 
                && l_serviceParams[0] instanceof WEB3AdminFeqCancelExecutionCompleteRequest)
            {
                // �R�j�@@���������b�N����B 
                // �@@�R�|�P�j�@@�T�[�r�X�̈���[0]�i�F���h�c�j�ɂāA�Y��������I�u�W�F�N�g���擾����B
                // �@@�@@�i�O�����������}�l�[�W��.getOrderExecution()���g�p�j
                
                //get ���h�c
                WEB3AdminFeqCancelExecutionCompleteRequest l_request = 
                    (WEB3AdminFeqCancelExecutionCompleteRequest)l_serviceParams[0]; 
                if (WEB3StringTypeUtility.isNumber(l_request.execId))
                {
                    l_lngExecId = Long.parseLong(l_request.execId); 
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + STR_METHOD_NAME, 
                        "�p�����[�^�^�C�v�s���B");
                }
            
                // get�O�����������}�l�[�W��
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                if (l_finApp == null)
                {
                    String l_strMessage = "FinApp�����݂��Ȃ��B";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
                if (l_tradingModule == null)
                {
                    String l_strMessage = "TradingModule�����݂��Ȃ��B";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
                WEB3FeqOrderManager l_orderManager = 
                    (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
                if (l_orderManager == null)
                {
                    String l_strMessage = "�O�����������}�l�[�W�������݂��Ȃ��B";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
            
                //get���I�u�W�F�N�g
                OrderExecution l_orderExecution = 
                    l_orderManager.getOrderExecution(l_lngExecId);//NotFoundException
                if (l_orderExecution == null)
                {
                    String l_strMessage = "���I�u�W�F�N�g�����݂��Ȃ��u" + l_lngExecId + "�v�B";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
            
                // �@@�R�|�Q�j�@@�������b�N
                // �@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h,�����R�[�h)���R�[������
                long l_lngBranchId = l_orderExecution.getBranchId();
                long l_lngAccountId = l_orderExecution.getAccountId();
                WEB3GentradeAccountManager l_accountManager = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                if (l_accountManager == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                         this.getClass().getName() + STR_METHOD_NAME,
                        "�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
                }
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);//NotFoundException
                Branch l_branch = l_accountManager.getBranch(l_lngBranchId);//NotFoundException
            
                l_accountManager.lockAccount(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_mainAccount.getAccountCode());//WEB3BaseException
            }

            log.exiting(STR_METHOD_NAME);
            return l_context;        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);                
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h�ԋp�l)<BR>
     * �T�[�r�X���\�b�h�ԋp�l<BR>
     * @@roseuid 4295FFDD01AF
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
            
        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_throwable - (��O)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 4295FFDD01BB
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
     
    }
}
@
