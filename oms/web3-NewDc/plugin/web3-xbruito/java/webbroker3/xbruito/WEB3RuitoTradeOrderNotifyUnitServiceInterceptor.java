head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ����������ʒmUnitService�C���^�Z�v�^�N���X(WEB3RuitoTradeOrderNotifyUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 �m�X (���u) �V�K�쐬
                   2004/11/30 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.define.WEB3RuitoOrderDivTypeDef;

/**
 * �ݓ����������ʒmUnitService�C���^�Z�v�^�N���X<BR>
 */
public class WEB3RuitoTradeOrderNotifyUnitServiceInterceptor implements Interceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyUnitServiceInterceptor.class);

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]��ݓ������ʒm�L���[Params�ɃL���X�g����B<BR>
     * <BR>
     * �@@�|�،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�A�J�E���g�}�l�[�W��.getInstitution()���R�[�����āA<BR>
     *             �،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@[getInstitution�ɓn���p�����^] <BR>
     * �@@�@@�@@�،���ЃR�[�h�F �ݓ������ʒm�L���[Params.get�،���ЃR�[�h <BR>
     * <BR>
     * �@@�|�ݓ������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�g���ݓ�����.get�ݓ�����(with�R�[�Xand�v����)���R�[�����āA<BR>
     *            �g���ݓ������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[get�ݓ������ɓn���p�����^] <BR>
     * �@@�@@�@@�،���ЁF�@@�擾�����،���ЃI�u�W�F�N�g <BR>
     * �@@�@@�@@�R�[�X�F �ݓ������ʒm�L���[Params.get�R�[�X()�̖߂�l <BR>
     * �@@�@@�@@�v�����F �ݓ������ʒm�L���[Params.get�v����()�̖߂�l<BR>
     * <BR>
     * �@@�|������t�g�����U�N�V�������擾����B<BR>
     * �@@�@@(*)�ݓ������ʒm�L���[Params.get�������()�̒l���h2�F���t�h�̏ꍇ�A<BR>
     * ������t�g�����U�N�V�����̒l�́h01�F���t�i�V�K�����j�h<BR>
     * �@@�@@(*)�ݓ������ʒm�L���[Params.get�������()�̒l���h2�F���t�h�łȂ��ꍇ�A<BR>
     * ������t�g�����U�N�V�����̒l�́h02�F���t�i�V�K�����j�h <BR>
     * <BR>
     * �@@�|��t���ԋ敪���擾����B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l�� <BR>
     *               RuitoTypeEnum.�������t�@@���h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.����F�B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l��RuitoTypeEnum.MMF <BR>
     *                
     * �Œ�����t�g�����U�N�V�����̒l���h01�F���t�i�V�K�����j�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.MMF�i�ݒ�j�B<BR>
     * �@@�@@(*) �ݓ������P��Params.get�ݓ��^�C�v()�̒l�� <BR>
     *          RuitoTypeEnum.MMF�Œ�����t�g�����U�N�V�����̒l�� <BR>
     *          �h02�F���t�i�V�K�����j�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@ ��t���ԋ敪�̒l��WEB3TradingTimeTypeDef.MMF�i�ݒ���j�B<BR>
     * <BR>
     * �@@�|������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h =  <BR>
     *                �ݓ������ʒm�L���[Params.get�،���ЃR�[�h()�̖߂�l <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h =  <BR>
     *                �ݓ������ʒm�L���[Params.get���X�R�[�h()�̖߂�l <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = �擾������t���ԋ敪 <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = <BR>
     *                �擾����������t�g�����U�N�V���� <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( ) <BR>
     *              �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@ �msetAttribute�ɓn���p�����^�n <BR>
     * �@@�@@�@@�ݒ�L�[�F ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * �@@�@@�@@�ݒ�l�F ������ԃR���e�L�X�g <BR>
     * <BR>
     * <BR>
     * �Q�j�@@��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B <BR>
     * <BR>
     * �R�j�@@��t������SONAR���ł̔�������(����������(biz_datetime)) ���Z�b�g����B<BR> 
     * �@@�@@��t�����Ƃ��āASONAR���ł̔�������(����������(biz_datetime))�� <BR>
     * �@@�@@�g�p����B�i�����@@���Q�Ɓj <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂāA<BR>
     *      �ݓ������ʒm�L���[Params.�������� ���Z�b�g����B <BR>
     * �@@�@@�ݒ�L�[�F"xblocks.gtl.attributes.system_timestamp" <BR>
     * <BR>
     * �� ���� ��<BR>
     * SONAR�������̂����󒍓���(create_datetime)�� <BR>
     * �o�^�҂ɂ�����͍��ځiSONAR�����̓`�F�b�N�قƂ�ǖ����j�ł��邽�߁A<BR>
     * ��������擾��system_timestamp�Ƃ��Ă͎g�p�ł��Ȃ��B<BR>
     * �������ASONAR�������̂�����������(biz_datetime)�� <BR>
     * SONAR�V�X�e�����������Ɏ����Z�b�g���鍀�ڂł���A<BR>
     * YYYYMMDD�ɂ͓������t���A<BR>
     * HHMMSS�ɂ͓����̊J�ǑO�`�J�ǎ��ԑт̎��Ԃ������Ă��邽�߁A<BR>
     * ���̃T�[�r�X�ɂ����Ă͔�������(biz_datetime)�� <BR>
     * ��������擾��system_timestamp�Ƃ��Ďg�p����B<BR>
     * �S�j�@@���������b�N����B <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * �������͗ݓ������ʒm�L���[Params���ҏW�B <BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g <BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h���� <BR>
     * @@return Object
     * @@roseuid 405A4E4500BA
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParam)"; 
        log.entering(STR_METHOD_NAME);
        if (l_serviceParam == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");            
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("�p�����[�^Size�͂O�ł��Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^Size�͂O�ł��Ȃ�");
        }
        try
        {
            HostRuitoOrderNotifyParams l_hostOrderNotifyParams =
                (HostRuitoOrderNotifyParams) l_serviceParam[0];
    
            //�،���ЃI�u�W�F�N�g���擾����
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            String l_strInstitutionCode = 
                l_hostOrderNotifyParams.getInstitutionCode();
            Institution l_institution =
                l_finApp.getAccountManager().getInstitution
                (l_strInstitutionCode);
            log.debug("�،���ЃI�u�W�F�N�g���擾���� = " + 
                l_institution.getInstitutionCode());    
           
            //�ݓ������I�u�W�F�N�g���擾����
            WEB3RuitoProductManager l_ruitoProductManager =
                (WEB3RuitoProductManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getProductManager();
    
            String l_course = l_hostOrderNotifyParams.getCourse();
            String l_plan = l_hostOrderNotifyParams.getPlan();
            RuitoProduct l_ruitoProduct = null;
            l_ruitoProduct = 
                (RuitoProduct)l_ruitoProductManager.getRuitoProductWithCoursePlan(
                    l_institution, l_course, l_plan);

            RuitoProductParams l_RuitoProductParams = 
                (RuitoProductParams)l_ruitoProduct.getDataSourceObject();        
            log.debug("�ݓ������I�u�W�F�N�g���擾���� = " + 
                l_RuitoProductParams.getProductCode());        
    
            //�|������t�g�����U�N�V�������擾����B
            String l_strOrderAcceptTransaction = null;
            if (WEB3RuitoOrderDivTypeDef.BUY.equals(
                    l_hostOrderNotifyParams.getOrderDiv()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }
            else
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }
            log.debug("l_orderNotifyParams.getOrderDiv() = " + 
                    l_hostOrderNotifyParams.getOrderDiv());
            log.debug("������t�g�����U�N�V�������擾���� = " + 
                l_strOrderAcceptTransaction);    
    
            //�|��t���ԋ敪���擾����B
    
            String l_strAcceptTimeDiv = null;
    
            if (l_RuitoProductParams.getRuitoType().equals( 
                RuitoTypeEnum.CHUUKOKU_FUND))
            {
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
            }
            else if ( l_RuitoProductParams.getRuitoType().equals( 
                    RuitoTypeEnum.MMF) && 
                    WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN.equals
                    (l_strOrderAcceptTransaction))
            {
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MMF_SET;
            }
            else if ( l_RuitoProductParams.getRuitoType().equals( 
                    RuitoTypeEnum.MMF) && 
                    WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN.equals
                    (l_strOrderAcceptTransaction))
            {
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MMF_SET_CANCEL;
            }
            log.debug("RuitoType = " + l_RuitoProductParams.getRuitoType());
            log.debug("l_strAcceptTimeDiv = " + l_strAcceptTimeDiv);
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = 
            //�ݓ������ʒm�L���[Params.get�،���ЃR�[�h()�̖߂�l 
            l_context.setInstitutionCode
                (l_hostOrderNotifyParams.getInstitutionCode());
    
            //����J�����_�R���e�L�X�g.���X�R�[�h = 
            //�ݓ������ʒm�L���[Params.get���X�R�[�h()�̖߂�l
            l_context.setBranchCode(l_hostOrderNotifyParams.getBranchCode());
    
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
    
            //����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
    
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = �擾������t���ԋ敪
            l_context.setTradingTimeType(l_strAcceptTimeDiv);
    
            //����J�����_�R���e�L�X�g.������t���i = �h08�F�ݐϓ����h
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
    
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = 
            //�擾����������t�g�����U�N�V����
            l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);
            
            log.debug("�،���ЃR�[�h = " + l_context.getInstitutionCode());
            log.debug("���X�R�[�h=" + l_context.getBranchCode());
            log.debug("�s��R�[�h=" + l_context.getMarketCode());
            log.debug("�����R�[�h=" + l_context.getProductCode());
            log.debug("��t���ԋ敪=" + l_context.getTradingTimeType());
            log.debug("������t���i=" + l_context.getOrderAcceptProduct());
            log.debug("������t�g�����U�N�V����=" + 
                l_context.getOrderAcceptTransaction());
              
            //ThreadLocalSystemAttributesRegistry.setAttribute( )
            //�ɂĎ�����ԃR���e�L�X�g���Z�b�g����
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            //�Q�j�@@��t�����A���t���[�����Z�b�g����B
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //�R�j�@@��t������SONAR���ł̔�������(����������(biz_datetime)) ���Z�b�g����B
            // ������ԃR���e�L�X�g���Z�b�g����            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.system_timestamp",
                l_hostOrderNotifyParams.getOrderDate());
            
            //�S�j�@@���������b�N����B 
            //�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B 
            //�������͗ݓ������ʒm�L���[Params���ҏW�B 
            WEB3GentradeAccountManager l_gentradeAccMgr = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();       
            l_gentradeAccMgr.lockAccount(
                    l_hostOrderNotifyParams.getInstitutionCode(),
                    l_hostOrderNotifyParams.getBranchCode(),
                    l_hostOrderNotifyParams.getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("_NotFoundException_", l_ex);
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
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * @@param l_context - onCall���^�[���l <BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l <BR>
     * @@roseuid 405A4E4500D9
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME =
            "onReturn(Object l_context, Object l_returnValue)";
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
        log.exiting(STR_METHOD_NAME);    
    }

    /**
     * �T�[�r�X���\�b�h����O���X���[�����ꍇ�ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG <BR>
     * �@@������ԊǗ�.OFFSET_TAG <BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG <BR>
     * @@param l_obj - onCall���^�[���l <BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g <BR>
     * @@roseuid 405A4E4500E9
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME =
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
        log.exiting(STR_METHOD_NAME);    

    }
}
@
