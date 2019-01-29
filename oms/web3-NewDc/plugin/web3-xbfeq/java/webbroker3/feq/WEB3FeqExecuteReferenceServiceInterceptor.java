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
filename	WEB3FeqExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������Ɖ�T�[�r�X�C���^�Z�v�^(WEB3FeqExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬 
                 : 2005/08/03 �A�C��(���u) ���r���[       
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.message.WEB3FeqExecuteDetailsRequest;
import webbroker3.feq.message.WEB3FeqExecuteReferenceRequest;
import webbroker3.feq.message.WEB3FeqOrderHistoryRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O�������������Ɖ�T�[�r�X�C���^�Z�v�^)<BR>
 * �O�������������Ɖ�T�[�r�X�C���^�Z�v�^
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqExecuteReferenceServiceInterceptor implements Interceptor 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceServiceInterceptor.class);
    
    /**
     * @@roseuid 42D0D2D402FD
     */
    public WEB3FeqExecuteReferenceServiceInterceptor() 
    {
     
    }
    
    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     *   �|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g��<BR>
     * �@@�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     *   ����J�����_�R���e�L�X�g.�،���ЃR�[�h = (*1)<BR>
     *   ����J�����_�R���e�L�X�g.���X�R�[�h = (*1)<BR>
     *   ����J�����_�R���e�L�X�g.�s��R�[�h = (*2)<BR>
     *   ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     *   ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�O�������h<BR>
     *   ����J�����_�R���e�L�X�g.������t���i = �h�O�����h<BR>
     *   ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�Ɖ�h<BR>
     * <BR>
     *   �|ThreadLocalSystemAttributesRegistry.setAttribute( )��<BR>
     * �Ď�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *      �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �Q�j��t�����A���t���[�����Z�b�g����B<BR>
     *   �|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * (*1)�@@OpLoginSecurityService������ID���擾����B<BR>
     *     �A�Ǘ��҃Z�b�V����(OpLoginSecurityService���擾��������ID == <BR>
     * 0)�̏ꍇ�A<BR>
     *       instanceof�ɂă��N�G�X�g�f�[�^�̌^�𔻕ʂ��A�ȉ��̌^�ɃL���X�g����B<BR>
     * �@@�@@�@@�@@�E�O�������������ڍ׃��N�G�X�g<BR>
     * �@@�@@�@@�@@�E�O������������藚�����N�G�X�g<BR>
     * <BR>
     *       �L���X�g�������N�G�X�g.����ID�ɊY�����钍��.����ID��<BR>
     * ������ԃR���e�L�X�g�ɃZ�b�g����B <BR>
     *         �ݒ�L�[�F�@@ACCOUNT_ID <BR>
     *         ���ݒ�L�[�FACCOUNT_ID�͕K���萔�N���X���쐬���A<BR>
     * ���̒萔���Q�Ƃ��邱�ƁB<BR>
     * <BR>
     *     �B�擾��������ID(�A�̏ꍇ�́A<BR>
     * ����.����ID)�ɊY������ڋq�I�u�W�F�N�g���<BR>
     *       �،���ЃR�[�h�A���X�R�[�h���Z�b�g����B<BR>
     * <BR>
     * (*2)[�p�����[�^.�T�[�r�X���\�b�h��"get�������Ɖ�()"�̏ꍇ]<BR>
     *       �@@ �p�����[�^.�T�[�r�X���\�b�h����[0]��<BR>
     * �O�������������Ɖ�N�G�X�g�^�ɃL���X�g����B<BR>
     *       �A �L���X�g�������N�G�X�g.�s��R�[�h���Z�b�g����B<BR>
     *          ���L���X�g�������N�G�X�g.�s��R�[�h == null�̏ꍇ�A<BR>
     * �h0�FDEFAULT�h���Z�b�g����B<BR>
     *     [��L�ȊO]<BR>
     *       �h0�FDEFAULT�h���Z�b�g����B<BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 429EB2EC0115
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);         
        }
        
        //�P�j����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B 
        //�|OpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B 
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
            OpLoginSecurityService.class);
        
        long l_lngAccountId = l_opLoginSecurityService.getAccountId();
        
        try
        {
            //(*1)(1)OpLoginSecurityService������ID���擾����B 
            //(2)�Ǘ��҃Z�b�V����(OpLoginSecurityService���擾��������ID == 0)�̏ꍇ�A 
            
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            
            WEB3FeqOrderManager l_feqOrderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
            long l_lngOrderId = 0L;
            
            //(2)�Ǘ��҃Z�b�V����(OpLoginSecurityService���擾��������ID == 0)�̏ꍇ�A 
            if (l_lngAccountId == 0L)
            {
                //instanceof�ɂă��N�G�X�g�f�[�^�̌^�𔻕ʂ��A�ȉ��̌^�ɃL���X�g����B 
                //�E�O�������������ڍ׃��N�G�X�g 
                //�E�O������������藚�����N�G�X�g 
                if (l_serviceParams[0] instanceof WEB3FeqExecuteDetailsRequest)
                {
                    WEB3FeqExecuteDetailsRequest l_detailsRequest = 
                        (WEB3FeqExecuteDetailsRequest)l_serviceParams[0];
                    
                    l_lngOrderId = Long.parseLong(l_detailsRequest.orderId);
                }        
                else if (l_serviceParams[0] instanceof WEB3FeqOrderHistoryRequest)
                {
                    
                    WEB3FeqOrderHistoryRequest l_historyRequest = 
                        (WEB3FeqOrderHistoryRequest)l_serviceParams[0];
                    
                    l_lngOrderId = Long.parseLong(l_historyRequest.orderId);                   
                }
                
                //�L���X�g�������N�G�X�g.����ID�ɊY�����钍��.����ID��������ԃR���e�L�X�g�ɃZ�b�g����B  
                //  �ݒ�L�[�F�@@ACCOUNT_ID  
                //  ���ݒ�L�[�FACCOUNT_ID�͕K���萔�N���X���쐬���A���̒萔���Q�Ƃ��邱�ƁB
                FeqOrderUnit l_feqOrderUnit = 
                    l_feqOrderManager.getOrderUnitByOrderId(l_lngOrderId);

                FeqOrderUnitParams l_feqOrderUnitParams = 
                    new FeqOrderUnitParams(
                        (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
                
                long l_lngOrderAccountId = l_feqOrderUnitParams.getAccountId();
                
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID, 
                    new Long(l_lngOrderAccountId));
                
                l_lngAccountId = l_lngOrderAccountId;
            }            

            // (3)�擾��������ID((2)�̏ꍇ�́A����.����ID)�ɊY������ڋq�I�u�W�F�N�g��� 
            //�،���ЃR�[�h�A���X�R�[�h���Z�b�g����B 
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(l_lngAccountId);            
            
            //(*2)[�p�����[�^.�T�[�r�X���\�b�h��"get�������Ɖ�()"�̏ꍇ] 
            String l_strMarketCode = null;
            final String METHOD_GETORDEREXCUTEREFERENCE = "getOrderExecuteReference";
            
            if (METHOD_GETORDEREXCUTEREFERENCE.equals(l_method.getName()))
            {
                //(1) �p�����[�^.�T�[�r�X���\�b�h����[0]���O�������������Ɖ�N�G�X�g�^�ɃL���X�g����B 
                if (l_serviceParams[0] instanceof WEB3FeqExecuteReferenceRequest)
                {
                    WEB3FeqExecuteReferenceRequest l_detailsRequest = 
                        (WEB3FeqExecuteReferenceRequest)l_serviceParams[0];
                    
                    //(2) �L���X�g�������N�G�X�g.�s��R�[�h���Z�b�g����B 

                    if (l_detailsRequest.marketCode != null)
                    {
                        l_strMarketCode = l_detailsRequest.marketCode;
                    }
                    //   ���L���X�g�������N�G�X�g.�s��R�[�h == null�̏ꍇ�A
                    //    �h0�FDEFAULT�h���Z�b�g����B 
                    else
                    {
                        l_strMarketCode = WEB3MarketCodeDef.DEFAULT;
                    }
                }               
            }
            //[��L�ȊO] 
            //�h0�FDEFAULT�h���Z�b�g����B 
            else
            {
                l_strMarketCode = WEB3MarketCodeDef.DEFAULT;
            }
            
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode = 
                l_mainAccount.getInstitution().getInstitutionCode();
            
            //���X�R�[�h���擾����
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = (*1) 
            l_context.setInstitutionCode(l_strInstitutionCode);
            
            //����J�����_�R���e�L�X�g.���X�R�[�h = (*1)
            l_context.setBranchCode(l_strBranchCode);
            
            //����J�����_�R���e�L�X�g.�s��R�[�h = (*2) 
            l_context.setMarketCode(l_strMarketCode);
            
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�O�������h 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            
            //����J�����_�R���e�L�X�g.������t���i = �h�O�����h 
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.FOREIGN_STOCK);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h�Ɖ�h
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.REFERENCE);
    
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
            //������ԃR���e�L�X�g���Z�b�g���� 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������  
            WEB3GentradeTradingTimeManagement.setTimestamp();   
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getMainAccount", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return null;
    }
    
    /**
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * �T�[�r�X�I�����ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ACCOUNT_ID<BR>
     * <BR>
     * ���ݒ�L�[�FACCOUNT_ID�͕K���萔�N���X���쐬���A<BR>
     * ���̒萔���Q�Ƃ��邱�ƁB<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 429EB2EC0134
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
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

        //ACCOUNT_ID
        //���ݒ�L�[�FACCOUNT_ID�͕K���萔�N���X���쐬���A���̒萔���Q�Ƃ��邱�ƁB
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID, null);
        
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * ��O�������ɃR�[�������B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ACCOUNT_ID<BR>
     * <BR>
     * ���ݒ�L�[�FACCOUNT_ID�͕K���萔�N���X���쐬���A<BR>
     * ���̒萔���Q�Ƃ��邱�ƁB<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 429EB2EC0144
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
        
        //ACCOUNT_ID
        //���ݒ�L�[�FACCOUNT_ID�͕K���萔�N���X���쐬���A���̒萔���Q�Ƃ��邱�ƁB
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.ACCOUNT_ID, null);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
