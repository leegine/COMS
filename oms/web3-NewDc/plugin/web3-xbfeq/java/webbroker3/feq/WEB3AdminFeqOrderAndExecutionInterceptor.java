head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAndExecutionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�����������̓T�[�r�X�C���^�Z�v�^(WEB3AdminFeqOrderAndExecutionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ��O��(���u) ���r���[   
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҊO�����������̓T�[�r�X�C���^�Z�v�^)<BR>
 * �Ǘ��ҊO�����������̓T�[�r�X�C���^�Z�v�^<BR>
 *
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionInterceptor implements Interceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqOrderAndExecutionInterceptor.class);
    
    /**
     * @@roseuid 42D0CED300AB
     */
    public WEB3AdminFeqOrderAndExecutionInterceptor() 
    {
     
    }
    
    /**
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR> 
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR> 
     * �@@�|���O�C���Z�b�V������胍�O�C���h�c���擾�C���O�C���h�c�ɊY������Ǘ��҂�<BR>
     * �����A<BR>
     * �@@�@@�ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h<BR> 
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.���X�R�[�h<BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<BR>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h10�F�O�������h<BR> 
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT<BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h<BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<BR> 
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * ������ԃR���e�L�X�g���Z�b�g����B<BR> 
     * �@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR> 
     * <BR>
     * �Q�j�@@���t���[�����Z�b�g����B<BR> 
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR> 
     * <BR>
     * ���ȉ��A�T�[�r�X���\�b�h���u�Ǘ��ҊO�����������͊������N�G�X�g�v<BR>
     * �̏ꍇ�̂ݎ��{�B<BR>
     * �R�j�@@���������b�N����B<BR> 
     * <BR>
     * �@@�R�|�P�j�@@�������b�N<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h,<BR>
     *  �����R�[�h)���R�[������B<BR> 
     *       ���N�G�X�g�f�[�^.�����͏����ҏW�B<BR> 
     *       �i���N�G�X�g�f�[�^.�����͏��.�ڋq�R�[�h����ڋq���擾���A�����R�[�h���擾����j<BR> 
     *        (�،���ЃR�[�h�́A�Ǘ���.�،���ЃR�[�h���g�p)<BR> 
     * <BR>
     * �@@�R�|�Q�j�@@���o�H�敪���Z�b�g����B<BR>
     * �@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA<BR>
     * ���o�H�敪.9�F�����͂��Z�b�g����B<BR> 
     * �@@�@@�@@�ݒ�L�[�F �O���������o�H�敪<BR>
     * <BR>
     * �@@(*) ���o�H�敪<BR>
     * �@@0�F�o���ʒm�iDefault�j�@@1�F�o�����́@@2�F��茋�ʈꊇ���́@@9�F������<BR>
     *<BR>
     * @@param l_method - (�T�[�r�X���\�b�h)<BR>
     * �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * 
     * @@param l_serviceParams - (�T�[�r�X�̈���)<BR>
     * �T�[�r�X�̈����z��<BR>
     * @@return Object
     * @@roseuid 42B6944200AB
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�[�����w��(null)�ł��B");
        }

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
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            //�،���ЃR�[�h
            String l_strInstitutionCode = l_administrator.getInstitutionCode();
            //���X�R�[�h
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
 
            //���ȉ��A�T�[�r�X���\�b�h���u�Ǘ��ҊO�����������͊������N�G�X�g�v�̏ꍇ�̂ݎ��{�B       
            if (l_serviceParams[0] instanceof WEB3AdminFeqOrderAndExecutionCompleteRequest)
            {
                //�R�j�@@���������b�N����B 
                WEB3AdminFeqOrderAndExecutionCompleteRequest l_request = 
                    (WEB3AdminFeqOrderAndExecutionCompleteRequest)l_serviceParams[0];
                    
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                if (l_finApp == null)
                {
                    log.debug("FinApp�����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "FinApp�����݂��Ȃ��B");
                }
                
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);                
                if (l_tradingModule == null)
                {
                    log.debug("TradingModule�����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "TradingModule�����݂��Ȃ��B");
                }
                
                WEB3FeqOrderManager l_orderMgr = 
                    (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
                if (l_orderMgr == null)
                {
                    log.debug("�O�����������}�l�[�W�������݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�O�����������}�l�[�W�������݂��Ȃ��B");
                }                
                
                if (l_request.orderAndExecutionUnit == null)
                {
                    log.debug("�����͏�񂪑��݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�����͏�񂪑��݂��Ȃ��B");
                }

                //�R�|�P�j�@@�������b�N
                //�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
                WEB3GentradeAccountManager l_accountMgr = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                if (l_accountMgr == null)
                {
                    log.debug("�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
                }
                
                String l_strAccountCodeForLock = null;
                String l_strBranchCodeForLock = null;
                String l_strInstitutionCodeForLock = null;

                //���N�G�X�g�f�[�^.�����͏��.�ڋq�R�[�h����ڋq���擾���A�����R�[�h���擾����
                String l_6bytesAccCode = l_request.orderAndExecutionUnit.accountCode;
                MainAccount l_MainAcc = 
                    l_accountMgr.getMainAccount(
                        l_strInstitutionCode,
                        l_request.orderAndExecutionUnit.branchCode,
                        l_6bytesAccCode);
                    
                if (l_MainAcc == null)
                {
                    log.debug("�ڋq�����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�ڋq�����݂��Ȃ��B");
                }
                    
                //�ڋq����ڋq�R�[�h���擾����
                l_strAccountCodeForLock = l_MainAcc.getAccountCode();
                l_strBranchCodeForLock = l_request.orderAndExecutionUnit.branchCode;
                    
                //�،���ЃR�[�h�͊Ǘ���.�،���ЃR�[�h���g�p
                l_strInstitutionCodeForLock = l_strInstitutionCode;
                
                l_accountMgr.lockAccount(
                    l_strInstitutionCodeForLock,
                    l_strBranchCodeForLock,
                    l_strAccountCodeForLock);//WEB3BaseException
                 
                //�R�|�Q�j�@@���o�H�敪���Z�b�g����B 
                //  �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA���o�H�敪.9�F�����͂��Z�b�g����B  
                //    �ݒ�L�[�F �O���������o�H�敪
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV, 
                    WEB3FeqOrderExecRouteDivDef.ORDER_AND_EXEC_INPUT);
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
    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR> 
     * ����J�����_�R���e�L�X�g�N���A�����B<BR> 
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR> 
     * ������ԊǗ�.OFFSET_TAG<BR> 
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR> 
     * �O���������o�H�敪<BR>
     * @@param l_context - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_returnValue - (�T�[�r�X���\�b�h�ԋp�l)<BR>
     * �T�[�r�X���\�b�h�ԋp�l<BR>
     * @@roseuid 42B6944200AE
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

        //�O���������o�H�敪
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
            null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR> 
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR> 
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR> 
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR> 
     * �O���������o�H�敪<BR>
     * @@param l_obj - (onCall�ԋp�l)<BR>
     * onCall�ԋp�l<BR>
     * @@param l_throwable - (��O)<BR>
     * ��O�I�u�W�F�N�g<BR>
     * @@roseuid 42B6944200BB
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

        //�O���������o�H�敪
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
