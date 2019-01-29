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
filename	WEB3AdminFeqExecutionInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҊO�������o�����̓T�[�r�X�C���^�Z�v�^(WEB3AdminFeqExecutionInputServiceInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/18 �Ջ`�g(���u) �V�K�쐬
                    2005/08/01 ��O��(���u) ���r���[
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
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
import webbroker3.feq.message.WEB3AdminFeqExecutionCompleteRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO�������o�����̓T�[�r�X�C���^�Z�v�^) <BR>
 * �Ǘ��ҊO�������o�����̓T�[�r�X�C���^�Z�v�^ <BR>
 * 
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionInputServiceInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminFeqExecutionInputServiceInterceptor.class);

    /**
     * @@roseuid 42D0CED2030D
     */
    public WEB3AdminFeqExecutionInputServiceInterceptor()
    {

    }

    /**
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B <BR>
     * �|���O�C���Z�b�V������胍�O�C���h�c���擾�C���O�C���h�c�ɊY������Ǘ��҂̏�� ���A <BR>
     * �ȉ��̒ʂ������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * ����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h <BR>
     * ����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.���X�R�[�h <BR>
     * ����J�����_�R���e�L�X�g.�s��R�[�h = null <BR>
     * ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h10�F�O�������h <BR>
     * ����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT <BR>
     * ����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h <BR>
     * ����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null <BR>
     * <BR>
     * �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ� <BR>
     * ������ԃR���e�L�X�g���Z�b�g����B <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * �Q�j ���t���[�����Z�b�g����B <BR>
     * �|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * ���ȉ��A�T�[�r�X���\�b�h���u�Ǘ��ҊO�������o�����͊������N�G�X�g�v <BR>
     * �̏ꍇ�̂ݎ��{�B <BR>
     * �R�j ���������b�N����B <BR>
     * �R�|�P�j �T�[�r�X�̈���[0]�i�F�����h�c�j�ɂāA�Y�����钍���I�u�W�F�N�g�� <BR>
     * �擾����B�i�O�����������}�l�[�W��.getOrder()���g�p�j <BR>
     * <BR>
     * �R�|�Q�j �������b�N <BR>
     * �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h) <BR>
     * ���R�[������B <BR>�� �����͒����P�ʂ��ҏW�B <BR>
     * <BR>
     * �R�|�R�j ���o�H�敪���Z�b�g����B <BR>
     * �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA���o�H�敪. <BR>
     * 1�F�o�����͂��Z�b�g����B <BR>
     * �ݒ�L�[�F �O���������o�H�敪 <BR>
     * <BR>
     * (*) ���o�H�敪 <BR>
     * 0�F�o���ʒm�iDefault�j 1�F�o������ 2�F��茋�ʈꊇ���� 9�F������ <BR>
     * 
     * @@param l_method -
     *            (�T�[�r�X���\�b�h) <BR>
     *            �T�[�r�X���\�b�h�I�u�W�F�N�g <BR>
     * @@param l_serviceParams -
     *            (�T�[�r�X�̈���) <BR>
     *            �T�[�r�X�̈����z�� <BR>
     * @@return Object
     * @@roseuid 428C4267005C
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�Ǘ���
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
            if (l_administrator == null)
            {
                log.debug("�Ǘ��҂����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��҂����݂��Ȃ��B");
            }

            //�،���ЃR�[�h
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            // ���X�R�[�h
            String l_strBranchCode = l_administrator.getBranchCode();

            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            
            //�P�j ����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �Ǘ���.�،���ЃR�[�h 
            l_context.setInstitutionCode(l_strInstitutionCode);

            //����J�����_�R���e�L�X�g.���X�R�[�h = �Ǘ���.���X�R�[�h 
            l_context.setBranchCode(l_strBranchCode);

            //����J�����_�R���e�L�X�g.�s��R�[�h = null
            l_context.setMarketCode(null);

            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h10�F�O�������h 
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);

            //����J�����_�R���e�L�X�g.���i�R�[�h = 0�FDEFAULT
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);

            //����J�����_�R���e�L�X�g.������t���i = �h04�F�O�����h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);

            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null 
            l_context.setOrderAcceptTransaction(null);

            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�
            //������ԃR���e�L�X�g���Z�b�g����
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������
            WEB3GentradeTradingTimeManagement.setTimestamp();

            //�ȉ��A�T�[�r�X���\�b�h���u�Ǘ��ҊO�������o�����͊������N�G�X�g�v
            if (l_serviceParams != null && l_serviceParams[0] instanceof WEB3AdminFeqExecutionCompleteRequest)
            {
                //�R�j ���������b�N����B 
                // �@@�R�|�P�j �T�[�r�X�̈���[0]�i�F�����h�c�j�ɂāA�Y�����钍���I�u�W�F�N�g��
                // �擾����B�i�O�����������}�l�[�W��.getOrder()���g�p�j
                WEB3AdminFeqExecutionCompleteRequest l_request = 
                    (WEB3AdminFeqExecutionCompleteRequest)l_serviceParams[0];
                
                long l_lngOrderId = 0;     
                if (WEB3StringTypeUtility.isNumber(l_request.orderId))
                {
                    l_lngOrderId = Long.parseLong(l_request.orderId); 
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        this.getClass().getName() + STR_METHOD_NAME, 
                        "�p�����[�^�^�C�v�s���B");
                }
                
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

                TradingModule l_tradingModule = 
                    l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
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
            
                //get�����P�ʃI�u�W�F�N�g
                OrderUnit l_orderUnit = 
                    l_orderManager.getOrderUnitByOrderId(l_lngOrderId);//NotFoundException
                if (l_orderUnit == null)
                {
                    String l_strMessage = "�����P�ʃI�u�W�F�N�g�����݂��Ȃ��B";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }
    
                // �R�|�Q�j �������b�N
                // �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h,
                // �����R�[�h)
                // ���R�[������B 
                // �@@�@@�� �����͒����P�ʂ��ҏW�B 
                long l_lngBranchId = l_orderUnit.getBranchId();
                long l_lngAccountId = l_orderUnit.getAccountId();
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
    
                // �R�|�R�j ���o�H�敪���Z�b�g����B
                // �|ThreadLocalSystemAttributesRegistry.setAttribute(
                // )�ɂāA���o�H�敪.
                // 1�F�o�����͂��Z�b�g����B 
                // �ݒ�L�[�F �O���������o�H�敪
                // (*) ���o�H�敪
                // 0�F�o���ʒm�iDefault�j 1�F�o������ 2�F��茋�ʈꊇ���� 9�F������
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
                    WEB3FeqOrderExecRouteDivDef.EXEC_INPUT);
            }
            log.exiting(STR_METHOD_NAME);
            
            return  l_context;
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
     * �O���������o�H�敪 <BR>
     * 
     * @@param l_context -
     *            (onCall�ԋp�l) <BR>
     *            onCall�ԋp�l <BR>
     * @@param l_returnValue -
     *            (�T�[�r�X���\�b�h�ԋp�l) <BR>
     *            �T�[�r�X���\�b�h�ԋp�l <BR>
     * @@roseuid 428C42670136
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
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
        
        //�O���������o�H�敪
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV, null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B <BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG <BR>
     * ������ԊǗ�.OFFSET_TAG <BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * �O���������o�H�敪 <BR>
     * 
     * @@param l_obj -
     *            (onCall�ԋp�l) <BR>
     *            onCall�ԋp�l <BR>
     * @@param l_throwable -
     *            (��O) <BR>
     *            ��O�I�u�W�F�N�g <BR>
     * @@roseuid 428C426701F2
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
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
            
        //�O���������o�H�敪
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV, null);

        log.exiting(STR_METHOD_NAME);
    }
}@
