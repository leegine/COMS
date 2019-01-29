head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP���������tUnitService�C���^�Z�v�^�N���X(WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 䈋� (���u) �V�K�쐬  
*/
package webbroker3.ifo;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

/**
 * (�敨OP���������tUnitService�C���^�Z�v�^)<BR>
 * �敨OP���������tUnitService�C���^�Z�v�^�N���X<BR>
 * @@author  䈋�
 * @@version 1.0
 */
public class WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor
    implements Interceptor
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor.class);

    /**
     * @@roseuid 41AD62EB0242
     */
    public WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor()
    {

    }

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B
     * 
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j
     * 
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
     * �@@�|����.�T�[�r�X�̈���[0]��敨OP������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B
     * �@@�|���N�G�X�g�f�[�^�̓��e�ƁAOpLoginSecurityService�̓��e��������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
     * 
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = �敨OP������t�L���[Params.�،���ЃR�[�h
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = �敨OP������t�L���[Params.���X�R�[�h
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h
     * �@@����J�����_�R���e�L�X�g.���i�R�[�h = null
     * �@@����J�����_�R���e�L�X�g.������t���i = null
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
     * 
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
     *   �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
     * �Q�j���t���[�����Z�b�g����B<BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * �R�j�@@�����ꒆ�̊�l�擾�̂��߂̑������Z�b�g����B <BR>
     * �@@�|������ԊǗ�.set�������v�Z�p�����( )�ɂ�<BR>
     * �@@�@@�@@�������v�Z�̊�����ɁA�����P��.�������{HHMMSS�Ƃ���"000000"���Z�b�g����B <BR>
     * <BR>
     * �R�j�@@���������b�N����B<BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR>
     *      �������͐敨OP������t�L���[Params���ҏW�B<BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParams - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 414AC3CF0086
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        if (l_serviceParams[0] == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
                
        //����J�����_�R���e�L�X�g�𐶐�����
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();
            
        //����.�T�[�r�X�̈���[0]��敨OP������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����
        if (!(l_serviceParams[0] instanceof HostFotypeOrderAcceptParams))
        {
            log.error("�p�����[�^�^�C�v�s��");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�T�[�r�X�̈���[0]������������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B
        HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams =
            (HostFotypeOrderAcceptParams)l_serviceParams[0];
            
        String l_strInstitutionCode = l_hostFotypeOrderAcceptParams.getInstitutionCode();
        String l_strBranchCode = l_hostFotypeOrderAcceptParams.getBranchCode();
        String l_strAccountCode = l_hostFotypeOrderAcceptParams.getAccountCode();
        
        //����J�����_�R���e�L�X�g.�،���ЃR�[�h = �敨OP������t�L���[Params.�،���ЃR�[�h
        l_context.setInstitutionCode(l_strInstitutionCode);
        //����J�����_�R���e�L�X�g.���X�R�[�h = �敨OP������t�L���[Params.���X�R�[�h
        l_context.setBranchCode(l_strBranchCode);
        //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        //����J�����_�R���e�L�X�g.��t���ԋ敪 = �h11�F�����w���敨OP�h<BR>
        l_context.setTradingTimeType(
            WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        //����J�����_�R���e�L�X�g.���i�R�[�h = null
        l_context.setProductCode(null);   
        //����J�����_�R���e�L�X�g.������t���i = null
        l_context.setOrderAcceptProduct(null);
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null
        l_context.setOrderAcceptTransaction(null);
        
        //�@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
        //�ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
        //�Q�j���t���[�����Z�b�g����
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�����P�ʂ��甭�������擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tm.getOrderManager();
        IfoOrderUnit l_orderUnit = null;
        try
        {
	        l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(
	            l_strInstitutionCode,
	            l_strBranchCode,
	            ProductTypeEnum.IFO,
	            l_hostFotypeOrderAcceptParams.getOrderRequestNumber());
        }
        catch(WEB3BaseException l_wbe)
        {
            log.debug(STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.getMessage(),
                l_wbe);
        }
        
        IfoOrderUnitRow l_ifoOrderUnitRow = 
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
        Date l_bizDate = 
            WEB3DateUtility.getDate(
            l_ifoOrderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN, 
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
                
        //������ԊǗ�.set�������v�Z�p�����()�̃R�[�� 
        WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
            new Timestamp(l_bizDate.getTime()));
          
        // �R�j�@@���������b�N����
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        try
        {
            l_accountManager.lockAccount(l_strInstitutionCode,l_strBranchCode,l_strAccountCode);
        }
        catch(WEB3BaseException l_wbe)
        {
            log.debug(STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.getMessage(),
                l_wbe);
        }

        log.exiting(STR_METHOD_NAME);
        return null;

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
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B<BR>
     * @@param l_context - onCall���^�[���l
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 414AC3CF00A5
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {

        final String STR_METHOD_NAME = "OnReturn";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
        log.exiting(STR_METHOD_NAME);
    

    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * ������ԊǗ�.TIMESTAMP_TAG<BR>
     * ������ԊǗ�.OFFSET_TAG<BR>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<BR>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B<BR>
     * @@param l_obj - onCall���^�[���l
     * @@param l_throwable - ��O�I�u�W�F�N�g
     * @@roseuid 414AC3CF00D4
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "OnReturn";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
        log.exiting(STR_METHOD_NAME);
    }
}
@
