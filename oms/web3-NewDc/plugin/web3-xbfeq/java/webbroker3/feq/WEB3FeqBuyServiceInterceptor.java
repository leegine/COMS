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
filename	WEB3FeqBuyServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t�T�[�r�X�C���^�Z�v�^(WEB3FeqBuyServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.message.WEB3FeqBuyCompleteRequest;
import webbroker3.feq.message.WEB3FeqBuyConfirmRequest;
import webbroker3.feq.message.WEB3FeqBuyInputRequest;
import webbroker3.feq.message.WEB3FeqBuyProductSelectRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���������t�T�[�r�X�C���^�Z�v�^)<BR>
 * �O���������t�T�[�r�X�C���^�Z�v�^�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBuyServiceInterceptor implements Interceptor 
{

    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBuyServiceInterceptor.class);
    
    /**
     * @@roseuid 42D0CF7E02CE
     */
    public WEB3FeqBuyServiceInterceptor() 
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
     * �@@�@@�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     *   ����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     *   ����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     *   ����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     *   ����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<BR>
     *   ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�O�������h<BR>
     *   ����J�����_�R���e�L�X�g.������t���i = �h�O�����h<BR>
     *   ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h���t�h<BR>
     * <BR>
     *   �|ThreadLocalSystemAttributesRegistry.setAttribute( )��<BR>
     * �Ď�����ԃR���e�L�X�g���Z�b�g����B<BR>
     *      �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     *   (*)�s��R�[�h�̃Z�b�g���@@ <BR>
     *�E���N�G�X�g�f�[�^�̌^ == �h�O���������t���̓��N�G�X�g�h <BR>
     *   or ���N�G�X�g�f�[�^�̌^ == �h�O���������t�m�F���N�G�X�g�h <BR>
     *   or ���N�G�X�g�f�[�^�̌^ == �h�O�����������I�����N�G�X�g�h �̏ꍇ <BR>
     *    ����J�����_�R���e�L�X�g.�s��R�[�h = null <BR>
     *<BR>
     *   ���N�G�X�g�f�[�^�̌^ == �h�O���������t�������N�G�X�g�h or <BR>
     *   �h�O���������t�������N�G�X�g�h �̏ꍇ <BR>
     *    a) OpLoginSecurityService���،���ЃI�u�W�F�N�g���擾����B <BR>
     *    b) a)�Ŏ擾�����،���ЃI�u�W�F�N�g�ƃ�<BR>
     *       �N�G�X�g�f�[�^.�����R�[�h��������I�u�W�F�N�g���擾����B <BR>
     *    c) ����J�����_�R���e�L�X�g.�s��R�[�h = <BR>
     *     �����I�u�W�F�N�g.get�s��R�[�h()�̖߂�l <BR>
     *<BR>
     * �Q�j��t�����A���t���[�����Z�b�g����B<BR>
     *   �|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j���������b�N����B<BR>
     *    ���N�G�X�g�f�[�^�̌^ == �h�O���������t�������N�G�X�g�h �̏ꍇ�A���{����B<BR>
     * <BR>
     *    �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)��<BR>
     * �R�[������B<BR>
     *    ��������OpLoginSecurityService���ҏW�B<BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 428AF59103A2
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�[�����w��(null)�ł��B");
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
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(l_lngAccountId);
            
            //�،���ЃR�[�h���擾����
            Institution l_institution = l_mainAccount.getInstitution();
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            
            //���X�R�[�h���擾����
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            
            //�����R�[�h
            String l_accountCode = l_mainAccount.getAccountCode();
            
            //����J�����_�R���e�L�X�g.set�،���ЃR�[�h
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.set���X�R�[�h
            l_context.setBranchCode(l_strBranchCode);

            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�O�������h 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
            //����J�����_�R���e�L�X�g.������t���i = �h�O�����h 
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h���t�h
            l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
            //������ԃR���e�L�X�g���Z�b�g���� 
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH 
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
                
            //(*)�s��R�[�h�̃Z�b�g���@@ 
            String l_strMarketCode = null;
            WEB3FeqProductManager l_feqProductManager =
                (WEB3FeqProductManager) GtlUtils.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
                    
            //(*)�s��R�[�h�̃Z�b�g���@@ 
            //�E���N�G�X�g�f�[�^�̌^ == �h�O���������t���̓��N�G�X�g�h  
            //  or �h�O���������t�m�F���N�G�X�g�h
            //  or �h�O�����������I�����N�G�X�g�h �̏ꍇ 
            //  ����J�����_�R���e�L�X�g.�s��R�[�h = null 
            if (l_serviceParams[0] instanceof WEB3FeqBuyInputRequest || 
                l_serviceParams[0] instanceof WEB3FeqBuyConfirmRequest ||
                l_serviceParams[0] instanceof WEB3FeqBuyProductSelectRequest)
            {
                l_strMarketCode = null;
            }
            
            //�E���N�G�X�g�f�[�^�̌^ == �h�O���������t�������N�G�X�g�h �̏ꍇ 
            //a) OpLoginSecurityService���،���ЃI�u�W�F�N�g���擾����B 
            //b) a)�Ŏ擾�����،���ЃI�u�W�F�N�g�ƃ��N�G�X�g�f�[�^.�����R�[�h��������I�u�W�F�N�g���擾����B 
            //c) ����J�����_�R���e�L�X�g.�s��R�[�h = �����I�u�W�F�N�g.get�s��R�[�h()�̖߂�l
            else if(l_serviceParams[0] instanceof WEB3FeqBuyCompleteRequest)
            {
                WEB3FeqBuyCompleteRequest l_request = 
                    (WEB3FeqBuyCompleteRequest)l_serviceParams[0];
                WEB3FeqProduct l_feqProduct = 
                    (WEB3FeqProduct)l_feqProductManager.getFeqProduct(
                        l_institution, 
                        l_request.productCode);
                l_strMarketCode = l_feqProduct.getMarketCode();
            }
            else
            {
                log.debug(
                    "���N�G�X�g�f�[�^��"
                        + " WEB3FeqBuyInputRequest "
                        + "�� WEB3FeqBuyConfirmRequest "
                        + "�� WEB3FeqBuyCompleteRequest "
                        + "�� WEB3FeqBuyProductSelectRequest�ȊO�ł���, but is "
                        + this.getClass().getName());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //����J�����_�R���e�L�X�g.�s��R�[�h = (*) 
            l_context.setMarketCode(l_strMarketCode);
            
            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������  
            WEB3GentradeTradingTimeManagement.setTimestamp();   
            
            //�R�j���������b�N����B 
            //���N�G�X�g�f�[�^�̌^ == �h�O���������t�������N�G�X�g�h �̏ꍇ�A���{����B 
            //�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //��������OpLoginSecurityService���ҏW�B 
            if (l_serviceParams[0] instanceof WEB3FeqBuyCompleteRequest)
            {
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
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
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 428AF59103B2
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
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 428AF59103D1
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
        
        log.exiting(STR_METHOD_NAME);   
    }
}
@
