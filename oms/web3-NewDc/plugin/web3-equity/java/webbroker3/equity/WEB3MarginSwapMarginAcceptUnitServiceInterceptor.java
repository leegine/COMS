head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n��t�ꌏ�T�[�r�X�C���^�Z�v�^(WEB3MarginSwapMarginAcceptUnitServiceInterceptor.java)
Author Name      : 2004/10/08 Ḗ@@��(���u) �V�K�쐬
                   2004/12/10 �����a��(SRA) �c�Č��Ή� �m��.�Q�T�X
                   2004/12/21 �����a��(SRA) JavaDoc�C��
                   2004/12/29 �����a��(SRA) onCall���\�b�h�̕ύX�Ή�
*/

package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeSwapAcceptRow;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������n��t�ꌏ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �M�p����������n��t�ꌏ�T�[�r�X�C���^�Z�v�^�N���X
 * @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginSwapMarginAcceptUnitServiceInterceptor.class);

    /**
     * <p>�i�M�p����������n��t�ꌏ�T�[�r�X�C���^�Z�v�^�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3MarginSwapMarginAcceptUnitServiceInterceptor() 
    {
    }
    
    /**
     * <p>�ionCall�j�B</p>
     * <p>����J�����_�����p����R���e�L�X�g�𐶐�����B<br>
     * �ixTrade�J�[�l�����A�T�[�r�X���\�b�h�J�n���ɃR�[�������j<br>
     * <br>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<br>
     * �@@�|�T�[�r�X�̈���[0]���������n��t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B<br>
     * �@@�|�������n��t�L���[Params�I�u�W�F�N�g�̓��e���A<br>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<br>
     * <br>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <br>
     *       �������n��t�L���[Params�̓�����<br>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �������n��t�L���[Params�̓�����<br>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<br>
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h19�F�����E���n�h <br>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<br>
     * �@@����J�����_�R���e�L�X�g.������t���i = null<br>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<br>
     * <br>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<br>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B<br>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH <br>
     * <br>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<br>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<br>
     * <br>
     * �R�j�@@���������b�N����B<br>
     * <br>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<br>
     * �@@�@@�@@�������͌������n��t�L���[Params���ҏW�B</p>
     * @@param l_method �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParams �T�[�r�X���\�b�h����
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        if (l_serviceParams[0] == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }               
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        //�T�[�r�X�̈���[0]�����N�G�X�g�f�[�^�I�u�W�F�N�g�ɃL���X�g����B
        try
        {      
            HostEqtypeSwapAcceptRow l_eqtypeSwapAcceptRow = (HostEqtypeSwapAcceptRow) l_serviceParams[0];
            log.debug("OnCall�̈����^�C�v���`�F�b�N_start");              
            // �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService��<BR>
            // ���e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>

            String l_strInstitutionCode = null; //�،���ЃR�[�h
            String l_strBranchCode  = null; //���X�R�[�h

            l_strInstitutionCode = l_eqtypeSwapAcceptRow.getInstitutionCode();
            l_strBranchCode = l_eqtypeSwapAcceptRow.getBranchCode();

            log.debug("����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����");
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �������n��t�L���[Params�̓�����
            l_context.setInstitutionCode(l_strInstitutionCode);
            //����J�����_�R���e�L�X�g.���X�R�[�h = OpLoginSecurityService���ҏW
            l_context.setBranchCode(l_strBranchCode); 
            //����J�����_�R���e�L�X�g.�s��R�[�h = null
            l_context.setMarketCode(null);
            //����J�����_�R���e�L�X�g.��t���ԋ敪 =   �h19�F�����E���n�h �j�h
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP); 
            //����J�����_�R���e�L�X�g.���i�R�[�h = �h0�FDEFAULT�h 
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);        
            //����J�����_�R���e�L�X�g.������t���i = null
            l_context.setOrderAcceptProduct(null);         
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� =null
            l_context.setOrderAcceptTransaction(null);           
            //������ԃR���e�L�X�g���Z�b�g����B
            log.debug("������ԃR���e�L�X�g���Z�b�g����");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);                     
            //2�j�@@��t�����A���t���[�����Z�b�g����B
            log.debug("��t�����A���t���[�����Z�b�g����");
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //3�j�@@���������b�N����B
            log.debug("�g���A�J�E���g�}�l�[�W���̌��������b�N���܂��B");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            // �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
            l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_eqtypeSwapAcceptRow.getAccountCode());
            
            log.exiting(STR_METHOD_NAME);
            return l_context;
             
        }
        catch (WEB3BaseException l_ex)
        {   
            log.error( STR_METHOD_NAME + STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() +"." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);      
        }        
    }
    
    /**
     * <p>�ionReturn�j�B</p>
     * <p>����J�����_�R���e�L�X�g�N���A�����B<br>
     * ������t�T�[�r�X�I�����ɃR�[�������B<br>
     * <br>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<br>
     * <br>
     * ������ԊǗ�.TIMESTAMP_TAG <br>
     * ������ԊǗ�.OFFSET_TAG <br>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH</p>
     * @@param l_context onCall���^�[���l
     * @@param l_returnValue �T�[�r�X���\�b�h���^�[���l
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object,Object)";
            
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);           
    }
    
    /**
     * <p>�ionThrowable�j�B</p>
     * <p>����J�����_�R���e�L�X�g�N���A�����B<br>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<br>
     * <br>
     * ������ԊǗ�.TIMESTAMP_TAG<br>
     * ������ԊǗ�.OFFSET_TAG<br>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH</p>
     * @@param l_obj onCall ���^�[���l
     * @@param l_throwable ��O�I�u�W�F�N�g
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object,Throwable)";
            
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);                    
    }
}
@
