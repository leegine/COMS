head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������t�ꌏ�T�[�r�X�C���^�Z�v�^�N���X(WEB3EquityChangeCancelAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/22 Ḗ@@�� (���u) �V�K�쐬
Revesion History : 2004/12/10 �����a��(SRA) �c�Č��Ή� �m��.�Q�S�X
Revesion History : 2004/12/21 �����a��(SRA) JavaDoc�C��
Revesion History : 2005/01/05 �����a��(SRA) �������b�N�C���Ή�
Revesion History : 2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2007/01/31 �đo�g(���u) ���f��1119�A1121
*/

package webbroker3.equity;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�������������t�ꌏ�T�[�r�X�C���^�Z�v�^�j�B<BR>
 * <BR>
 * ���������t�ꌏ�T�[�r�X�C���^�Z�v�^�N���X
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3EquityChangeCancelAcceptUnitServiceInterceptor implements Interceptor 
{
    
    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityChangeCancelAcceptUnitServiceInterceptor.class);
        
    /**
     * @@roseuid 414567A90250
     */
    public WEB3EquityChangeCancelAcceptUnitServiceInterceptor() 
    {
     
    }
    
    /**
     * <p>�ionCall�j�B</p>
     * <p>����J�����_�����p����R���e�L�X�g�𐶐�����B<br>
     * �ixTrade�J�[�l�����A�T�[�r�X���\�b�h�J�n���ɃR�[�������j<br>
     * <br>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<br>
     * �@@�|�T�[�r�X�̈���[0]������������t�L���[Params�I�u�W�F�N�g�ɃL���X�g����B<br>
     * �@@�|����������t�L���[Params�I�u�W�F�N�g�̓��e���<br>
     * �@@�@@�@@������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<br>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = ����������t�L���[Params�̓�����<br>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = ����������t�L���[Params�̓�����<br>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h = null<br>
     * �@@������ԃR���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h<br>
     * �@@������ԃR���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h<br>
     * �@@����J�����_�R���e�L�X�g.������t���i = null<br>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = null<br>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<br>
     * �ݒ�L�[�F������ԊǗ�.TRADING_CAL_CONTEXT_PATH<br>
     * <br>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B<br>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<br>
     * <br>
     * �R�j�@@�����ꒆ�̊�l�擾�̂��߂̑������Z�b�g����B<br>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�<br>
     * �@@�u���菈���ŕK���ꒆ�����v���Z�b�g����B<br>
     * �@@�@@�ݒ�L�[�F �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<br>
     * �@@�|�g�����������}�l�[�W��.get�����P��()�ɂĒ����P�ʂ��擾����B<br>
     * �@@�@@[����]<br>
     * �@@�@@�@@�،���ЃR�[�h�F�@@����������t�L���[Params�̓�����<br>
     * �@@�@@�@@���X�R�[�h�F�@@����������t�L���[Params�̓�����<br>
     * �@@�@@�@@���i�^�C�v�F�@@ProductTypeEnum."����"�iEQUITY�j<br>
     * �@@�@@�@@���ʃR�[�h�F�@@����������t�L���[Params�̓�����<br>
     * �@@�|������ԊǗ�.set�������v�Z�p�����( )�ɂ�<br>
     * �@@�@@�������v�Z�̊�����ɁA�����P��.�������{HHMMSS�Ƃ���"000000"���Z�b�g����B<br>
     * <br>
     * �S�j�@@���������b�N����B<br>
     * <br>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<br>
     * �@@�@@�@@�������͊���������t�L���[Params���ҏW�B</p>
     * @@param l_method �T�[�r�X���\�b�h�I�u�W�F�N�g
     * @@param l_serviceParams �T�[�r�X���\�b�h����
     * @@return Object
     * @@roseuid 41083F100018
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        if(l_serviceParams[0] == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�T�[�r�X�̈���[0]������������t�L���[Params�I�u�W�F�N�g�ɃL���X�g��
        HostEqtypeOrderAcceptParams l_accParams = (HostEqtypeOrderAcceptParams) l_serviceParams[0];

        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();
            
        String l_strInstitutionCode = null; //�،���ЃR�[�h
        String l_strBranchCode  = null; //���X�R�[�h

        l_strInstitutionCode = l_accParams.getInstitutionCode();
        l_strBranchCode = l_accParams.getBranchCode();
                    
        //����J�����_�R���e�L�X�g.�،���ЃR�[�h
        l_context.setInstitutionCode(l_strInstitutionCode);
            
        //����J�����_�R���e�L�X�g.���X�R�[�h 
        l_context.setBranchCode(l_strBranchCode);
        
        //����J�����_�R���e�L�X�g.�s��R�[�h 
        l_context.setMarketCode(null);
        
        //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        // ����J�����_�R���e�L�X�g.��t���ԋ敪 = �h�����E�M�p�h
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //����J�����_�R���e�L�X�g.������t���i = �h�����h
        l_context.setOrderAcceptProduct(null);
        
        //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = 
        l_context.setOrderAcceptTransaction(null);
            
        //������ԃR���e�L�X�g���Z�b�g����
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);


        //--------------------
        //��t�������Z�b�g����B
        //--------------------
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_wse)
        {
            throw new WEB3BaseRuntimeException(
                l_wse.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wse.getMessage(), l_wse);                  
        }

        //�����ꒆ�̊�l�擾�̂��߂̑������Z�b�g����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        //�g�����������}�l�[�W���𐶐�
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderUnit l_eqTypeOrderUnit = null;
        try
        {
            l_eqTypeOrderUnit =
                l_orderMgr.getOrderUnit(
                    l_accParams.getInstitutionCode(),
                    l_accParams.getBranchCode(),
                    ProductTypeEnum.EQUITY,
                    l_accParams.getOrderRequestNumber());
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            WEB3EquityBackServiceOnlineDef.ONLINE);
        Date l_bizDate =
            WEB3DateUtility.getDate(
            l_orderUnitRow.getBizDate() + WEB3GentradeTimeDef.MIN_RETURN,
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        WEB3GentradeTradingTimeManagement.setBaseTimestampForOrderBizDate(
            new Timestamp(l_bizDate.getTime()));

        //--------------------
        //���������b�N����
        //--------------------
        try
        {
            log.debug("�g���A�J�E���g�}�l�[�W���̌��������b�N���܂��B");
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            // �g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B
            l_accountManager.lockAccount(l_strInstitutionCode, l_strBranchCode, l_accParams.getAccountCode());
        }
        catch (WEB3BaseException l_be)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_be.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_be.getMessage(), l_be);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_context;
    }   
    /**
     * <p>�ionReturn�j�B</p>
     * <p>����J�����_�R���e�L�X�g�N���A�����B<br>
     * ���������t�T�[�r�X�I�����ɃR�[�������B<br>
     * <br>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<br>
     * <br>
     * ������ԊǗ�.TIMESTAMP_TAG <br>
     * ������ԊǗ�.OFFSET_TAG <br>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <br>
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<br>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B<br>
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE<br>
     * @@param l_returnValue onCall���^�[���l
     * @@param l_context �T�[�r�X���\�b�h���^�[���l
     * @@roseuid 41083F10001B
     */
    public void onReturn(Object l_returnValue, Object l_context) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
            null);

        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }
    
    /**
     * <p>�ionThrowable�j�B</p>
     * <p>����J�����_�R���e�L�X�g�N���A�����B<br>
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<br>
     * <br>
     * ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<br>
     * <br>
     * ������ԊǗ�.TIMESTAMP_TAG <br>
     * ������ԊǗ�.OFFSET_TAG <br>
     * ������ԊǗ�.TRADING_CAL_CONTEXT_PATH <br>
     * ������ԊǗ�.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE<br>
     * �@@��������ԊǗ�.clear�������v�Z�p�����( )���R�[���B<br>
     * �ݒ�L�[�萔��`�C���^�t�F�[�X.BACK_SERVICE_IN_ONLINE <br>
     * <br>
     * @@param l_obj onCall���^�[���l
     * @@param l_throwable ��O�I�u�W�F�N�g
     * @@roseuid 41083F100028
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE,
                null);

        WEB3GentradeTradingTimeManagement.clearBaseTimestampForOrderBizDate();
    }
}
@
