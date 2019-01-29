head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransToFXInputServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֓��̓T�[�r�X�C���^�Z�v�^(WEB3FXTransToFXInputServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/1/20 ���z (���u) �V�K�쐬   
Revesion History : 2008/09/23 �g�C�� (���u) �d�l�ύX�E���f��No.996�A1024�A1079
Revesion History : 2009/03/12 ���u�� (���u) �d�l�ύX�E���f��No.1112
*/

package webbroker3.aio;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.message.WEB3FXTransToFXInputRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX�ւ̐U�֓��̓T�[�r�X�C���^�Z�v�^) <BR>
 * FX�ւ̐U�֓��̓T�[�r�X�C���^�Z�v�^�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputServiceInterceptor implements Interceptor
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXInputServiceInterceptor.class);
    
    /**
     * @@roseuid 41E780B1036B
     */
    public WEB3FXTransToFXInputServiceInterceptor()
    {
    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B <BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j <BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^���擾����B<BR>
     * �@@�@@�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B<BR>
     *<BR>
     * �Q�j�@@FX�V�X�e���敪���擾����B <BR>
     * �@@�|��Е�FX�V�X�e�������e�[�u��(comp_fx_condition)���ȉ��̏����Ō�������B<BR>
     * �@@�@@���������͂P�j�Ŏ擾�������N�G�X�g�f�[�^�ƁAOpLoginSecurityService�̓��e���擾���邱�ƁB<BR>
     * <BR>
     *     �Q�|�P�j���N�G�X�g�f�[�^.FX�V�X�e���R�[�h ��null�łȂ��ꍇ<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@�@@���X�R�[�h = OpLoginSecurityService���ҏW  <BR>
     * �@@�@@FX�V�X�e���R�[�h = �P�j�Ŏ擾�������N�G�X�g�f�[�^.FX�V�X�e���R�[�h <BR>
     * <BR>
     *     �Q�|�Q�j���N�G�X�g�f�[�^.FX�V�X�e���R�[�h ��null�̏ꍇ<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = OpLoginSecurityService���ҏW <BR>
     * �@@�@@���X�R�[�h = OpLoginSecurityService���ҏW  <BR>
     * <BR>
     * �@@�@@�����R�[�h���擾�o���Ȃ������ꍇ�܂��́A�������擾�����ꍇ�͗�O��throw����B<BR>
     *<BR>
     * �R�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�@@�P�j�Ŏ擾�������N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���<BR>
     * �@@�@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     *<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h��Е�FX�V�X�e������.��t���ԋ敪�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���i�R�[�h = �h02�F�U�ցi�o���j�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h��Е�FX�V�X�e������.��t���ԋ敪�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh<BR>
     *<BR>
     * �@@�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     *<BR>
     * �S�j�@@��t�����A���t���[�����Z�b�g����B<BR>
     * �@@�@@������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h
     * @@param l_serviceParam - �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 41BCEFE600E3
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String l_strMethodName = "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(l_strMethodName);

        //�P�j�@@���N�G�X�g�f�[�^���擾����B
        //�|�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����
        if (l_serviceParam == null || l_serviceParam.length == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName,
                "�p�����[�^�l�s���B");
        }

        String l_strFxSystemCode = null;
        if (l_serviceParam[0] instanceof WEB3FXTransToFXInputRequest)
        {
            WEB3FXTransToFXInputRequest l_fxTransToFXInputRequest =
                (WEB3FXTransToFXInputRequest)l_serviceParam[0];
            l_strFxSystemCode = l_fxTransToFXInputRequest.fxSystemCode;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();

        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

        long l_lngAccountId = l_opLoginSecurityService.getAccountId();

        MainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@FX�V�X�e���敪���擾����B
        //��Е�FX�V�X�e�������e�[�u��(comp_fx_condition)���ȉ��̏����Ō�������B
        //���������͂P�j�Ŏ擾�������N�G�X�g�f�[�^�ƁAOpLoginSecurityService�̓��e���擾���邱�ƁB
        //[����]
        //  �،���ЃR�[�h = OpLoginSecurityService���ҏW
        //  ���X�R�[�h = OpLoginSecurityService���ҏW
        //  FX�V�X�e���R�[�h = �P�j�Ŏ擾�������N�G�X�g�f�[�^.FX�V�X�e���R�[�h

        //�،���ЃR�[�h = OpLoginSecurityService���ҏW
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h = OpLoginSecurityService���ҏW
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();

        String l_strQuery = " institution_code = ? and branch_code = ? ";

        List l_lisQuery = new ArrayList();
        l_lisQuery.add(l_strInstitutionCode);
        l_lisQuery.add(l_strBranchCode);

        if (l_strFxSystemCode != null)
        {
            l_strQuery += " and fx_system_code = ? ";
            l_lisQuery.add(l_strFxSystemCode);
        }

        Object[] l_objValues = new Object[l_lisQuery.size()];
        l_lisQuery.toArray(l_objValues);

        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                CompFxConditionRow.TYPE,
                l_strQuery,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //�����R�[�h���擾�o���Ȃ������ꍇ�܂��́A�������擾�����ꍇ�͗�O��throw����B
        if (l_lisRows.size() == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        if (l_lisRows.size() > 1)
        {
            log.debug("�f�[�^�s�����G���[�B");
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + l_strMethodName,
                "�f�[�^�s�����G���[�B");
        }

        CompFxConditionRow l_compFxConditionRow = (CompFxConditionRow)l_lisRows.get(0);

        //�R�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        //�@@�P�j�Ŏ擾�������N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e���
        //������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = OpLoginSecurityService���ҏW
        l_context.setInstitutionCode(l_strInstitutionCode);
        //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
        l_context.setBranchCode(l_strBranchCode);
        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //���J�����_�R���e�L�X�g.��t���ԋ敪 = �h��Е�FX�V�X�e������.��t���ԋ敪�h
        l_context.setTradingTimeType(l_compFxConditionRow.getTradingTimeType());
        //����J�����_�R���e�L�X�g.���i�R�[�h = �h02�F�U�ցi�o���j�h
        l_context.setProductCode(WEB3ProductCodeDef.TRANSFER_PAYMENT);
        //����J�����_�R���e�L�X�g.������t���i = �h��Е�FX�V�X�e������.��t���ԋ敪�h
        l_context.setOrderAcceptProduct(l_compFxConditionRow.getTradingTimeType());
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �h09�F�U�ցh
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.TRANSFER);

        try
        {
            //ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g����
            //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

            //�Q�j��t�����A���t���[�����Z�b�g����
            //������ԊǗ�.setTimestamp()���R�[������
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("error in setTimestamp", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(l_strMethodName);

        return null;
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
     * 
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41BCEFE60112
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        String l_strMethodName = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(l_strMethodName);
        
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
        
        log.exiting(l_strMethodName);
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
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * 
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 41BCEFE60122
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        String l_strMethodName = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(l_strMethodName);
        
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
        
        log.exiting(l_strMethodName);
    }
}@