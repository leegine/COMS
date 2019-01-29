head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyUnitServiceIntercetor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����������ʒmUnitService�C���^�Z�v�^�N���X(WEB3MutualTradeOrderNotifyUnitServiceIntercetor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
                   2004/08/24 ����� (���u) ���r���[
                   2004/12/10 ���� (���u) �c�Ή�              
*/

package webbroker3.mf;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mf.define.WEB3MFTradeTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�����������ʒmUnitService�C���^�Z�v�^�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualTradeOrderNotifyUnitServiceIntercetor 
    implements Interceptor 
{

    /**
     *  ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualTradeOrderNotifyUnitServiceIntercetor.class);    

    /**
     * �T�[�r�X���\�b�h�J�n���ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B<BR>
     * �ixTrade�J�[�l�����T�[�r�X���s�O�ɌĂяo�����j<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B<BR>
     * �@@�|�T�[�r�X�̈���[0]�𓊐M�����ʒm�L���[Params�ɃL���X�g����B<BR>
     * <BR>
     * �@@�|�،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�g���A�J�E���g�}�l�[�W��.getInstitution()���R�[�����āA�،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@[getInstitution�ɓn���p�����^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F ���M�����ʒm�L���[Params.get��ЃR�[�h()�̖߂�l<BR>
     * <BR>
     * �@@�|�g�����M�����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�g�����M�����}�l�[�W��.get���M����()���R�[�����āA<BR>
     * �g�����M�����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[get���M�����ɓn���p�����^]<BR>
     * �@@�@@�@@�،���ЁF�@@�擾�����،���ЃI�u�W�F�N�g<BR>
     * �@@�@@�@@�����R�[�h�F ���M�����ʒm�L���[Params.get�����R�[�h()�̖߂�l<BR>
     * <BR>
     * �@@�|������t�g�����U�N�V�������擾����B<BR>
     *     (*) ����.���M�����ʒm�L���[Params.get�f�[�^�R�[�h()�̖߂�l���hCI817�F��W�h�̏ꍇ�A<BR>
     * ������t�g�����U�N�V�����̒l�́h11�F��W�h <BR>
     * �@@�@@(*) ���M�����ʒm�L���[Params.get�����敪()�̒l���h1�F���t�h�̏ꍇ�A<BR>
     * ������t�g�����U�N�V�����̒l�́h02�F���t�i�V�K�����j�h<BR>
     * �@@�@@(*) ���M�����ʒm�L���[Params.get�����敪()�̒l���h2�F���t�h�̏ꍇ�A<BR>
     * ������t�g�����U�N�V�����̒l�́h01�F���t�i�V�K�����j�h<BR>
     * <BR>
     * �@@�|������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h<BR>
     *  = ���M�����ʒm�L���[Params.get��ЃR�[�h()�̖߂�l<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.���X�R�[�h = ���M�����ʒm�L���[Params.get���X�R�[�h()�̖߂�l<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.�����R�[�h<BR>
     *  = �擾�����g�����M�����I�u�W�F�N�g.getProductCode()�̖߂�l<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = WEB3TradingTimeTypeDef.�����M��<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t���i = �h07�F�����M���h<BR>
     * �@@�@@�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = �擾����������t�g�����U�N�V����<BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ�����ԃR���e�L�X�g���Z�b�g����B<BR>
     * �@@�@@�msetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݒ�L�[�F ������ԃR���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * �@@�@@�@@�ݒ�l�F ������ԃR���e�L�X�g<BR>
     * <BR>
     * �Q�j��t�����A���t���[�����Z�b�g����B <BR>
     * �@@�@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * �@@<BR>
     * �R�j�@@��t������SONAR���ł̔�������(����������(biz_datetime)) ���Z�b�g����B<BR> �@@
     *       ��t�����Ƃ��āASONAR���ł̔�������(����������(biz_datetime))�� 
     *       �g�p����B�i�����@@���Q�Ɓj 
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂāA<BR>
     * ���M�����ʒm�L���[Params.get��������()�̖߂�l���Z�b�g����B<BR>
     * 
     * �@@�@@�ݒ�L�[�F"xblocks.gtl.attributes.system_timestamp"<BR>
     * <BR>
     * �� ���� ��<BR>
     * SONAR�������̂����󒍓���(create_datetime)��<BR>
     * �o�^�҂ɂ�����͍��ځiSONAR�����̓`�F�b�N�قƂ�ǖ����j�ł��邽�߁A<BR>
     * ��������擾��system_timestamp�Ƃ��Ă͎g�p�ł��Ȃ��B<BR>
     * �������ASONAR�������̂�����������(biz_datetime)��<BR>
     * SONAR�V�X�e�����������Ɏ����Z�b�g���鍀�ڂł���A<BR>
     * YYYYMMDD�ɂ͓������t���A<BR>
     * HHMMSS�ɂ͓����̊J�ǑO�`�J�ǎ��ԑт̎��Ԃ������Ă��邽�߁A<BR>
     * ���̃T�[�r�X�ɂ����Ă͔�������(biz_datetime)��<BR>
     * ��������擾��system_timestamp�Ƃ��Ďg�p����B<BR>
     * �S�j�@@���������b�N����B <BR>
     * �@@�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B<BR> 
     * �@@�@@�������͓��M�����ʒm�L���[����ҏW�B<BR>
     * <BR>
     * @@param l_method - �T�[�r�X���\�b�h�I�u�W�F�N�g<BR>
     * @@param l_serviceParam - �T�[�r�X���\�b�h����<BR>
     * @@return Object
     * @@roseuid 40567DF90009
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParam)";
        log.entering(STR_METHOD_NAME);
  
        if (l_serviceParam == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^��Null�ł���");       
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ��B");        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ��B");      
        }
        
        //�P�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B
        
        //�،����
        Institution l_institution = null;     
        
        // �|�T�[�r�X�̈���[0]�𓊐M�����ʒm�L���[Params�ɃL���X�g����B
        HostXbmfOrderNotifyParams l_orderNotifyParams = 
            (HostXbmfOrderNotifyParams) l_serviceParam[0];
   
        //�g���A�J�E���g�}�l�[�W���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManaer = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager(); 
        
        //�،���ЃR�[�h�擾 
        String l_strInstitutionCode = 
            l_orderNotifyParams.getInstitutionCode();     
        
        try
        {
            //�g���A�J�E���g�}�l�[�W��.getInstitution()���R�[�����āA
            //�|�،���ЃI�u�W�F�N�g���擾����B
            l_institution = 
                l_gentradeAccountManaer.getInstitution(l_strInstitutionCode); 
           
            //�g�����M�����}�l�[�W��.get���M����()���R�[�����āA
            
            //�g�����M�����}�l�[�W���擾����
            WEB3MutualFundProductManager l_mutualFundProductManager =
                (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            
            //�|�g�����M�����I�u�W�F�N�g���擾����
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_orderNotifyParams.getProductCode());
            
            //�|������t�g�����U�N�V�������擾����B
            
            //������t�g�����U�N�V����
            String l_strOrderAcceptTransaction = null;   
            //(*) ����.���M�����ʒm�L���[Params.get�f�[�^�R�[�h()�̖߂�l���hCI817�F��W�h�̏ꍇ�A
            //������t�g�����U�N�V�����̒l�́h11�F��W�h 
            if(WEB3HostRequestCodeDef.MUTUAL_FUND_RECRUIT_ORDER_NOTIFY.equals(l_orderNotifyParams.getRequestCode()))
            {
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.RECRUIT;
            }
            
            //(*) ���M�����ʒm�L���[Params.get�����敪()�̒l���h
             //2�F���t�h�̏ꍇ�A������t�g�����U�N�V�����̒l�́h01�F���t�i�V�K�����h
            else if ((WEB3MFTradeTypeDef.BUY).equals(
                    l_orderNotifyParams.getTradeType()))
            {
                l_strOrderAcceptTransaction = 
                    WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
            }  
            
            //(*) ���M�����ʒm�L���[Params.get�����敪()�̒l���h
            //1�F���t�h�̏ꍇ�A������t�g�����U�N�V�����̒l�́h02�F���t�i�V�K�����j�h
            else if ((WEB3MFTradeTypeDef.SELL).equals(
                l_orderNotifyParams.getTradeType()))
            {
                l_strOrderAcceptTransaction =
                    WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
            }

            //�|������ԃR���e�L�X�g�̃v���p�e�B���Z�b�g����B
            
            //������ԃR���e�L�X�g�擾
            WEB3GentradeTradingClendarContext l_context = 
                new WEB3GentradeTradingClendarContext();     
           
            //����J�����_�R���e�L�X�g.�،���ЃR�[�h = 
             // ���M�����ʒm�L���[Params.get��ЃR�[�h()�̖߂�l
            l_context.setInstitutionCode(
                l_orderNotifyParams.getInstitutionCode());
        
            //����J�����_�R���e�L�X�g.���X�R�[�h = 
            //  ���M�����ʒm�L���[Params.get���X�R�[�h()�̖߂�l
            l_context.setBranchCode(l_orderNotifyParams.getBranchCode());
       
            //����J�����_�R���e�L�X�g.�s��R�[�h = �h0�FDEFAULT�h
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
       
            //����J�����_�R���e�L�X�g.�����R�[�h = 
            //  �擾�����g�����M�����I�u�W�F�N�g.getProductCode()�̖߂�l
            l_context.setProductCode(l_mutualFundProduct.getProductCode());
            
            //����J�����_�R���e�L�X�g.��t���ԋ敪 = 
            //  WEB3TradingTimeTypeDef.�����M��
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            
            //����J�����_�R���e�L�X�g.������t���i = �h07�F�����M���h
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MUTUAL_FUND);
            
            //����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = 
            //  �擾����������t�g�����U�N�V����
            l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);
            
            //�|ThreadLocalSystemAttributesRegistry.setAttribute()
            //  �ɂĎ�����ԃR���e�L�X�g���Z�b�g����B
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
                
            //�Q�j��t�����A���t���[�����Z�b�g����B 
            // �|������ԊǗ�.setTimestamp()���R�[������B
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //�R�j�@@��t������SONAR���ł̔�������(����������(biz_datetime)) ���Z�b�g����B 
            //  ��t�����Ƃ��āASONAR���ł̔�������(����������(biz_datetime))�� 
            //  �g�p����B�i�����@@���Q�Ɓj 
            //�|ThreadLocalSystemAttributesRegistry.setAttribute()�ɂāA
            //���M�����ʒm�L���[Params.get��������()�̖߂�l���Z�b�g����B 
            //  �ݒ�L�[�F"xblocks.gtl.attributes.system_timestamp"  
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                l_orderNotifyParams.getBizDatetime());
                        
            //�S�j�@@���������b�N����B      
            l_gentradeAccountManaer.lockAccount(
                    l_orderNotifyParams.getInstitutionCode(),
                    l_orderNotifyParams.getBranchCode(),
                    l_orderNotifyParams.getAccountCode());                
            
            log.exiting(STR_METHOD_NAME);
            return l_serviceParam;            
        }
        catch (NotFoundException l_ex) 
        {
              log.error("�Y���،����/�����R�[�h�s����  with" +
                " (�،����)l_institution =  " + 
                    l_orderNotifyParams.getInstitutionCode() + 
                " and (�����R�[�h)l_strProductCode = " + 
                    l_orderNotifyParams.getProductCode());
              throw new WEB3BaseRuntimeException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex); 
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("__WEB3GentradeTradingTimeManagement.setTimestamp Error__", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        

    }
    
    /**
     * �T�[�r�X���\�b�h�I�����ɃR�[�������B<BR>
     * <BR>
     * ����J�����_�R���e�L�X�g�N���A�����B<BR>
     * <BR>
     * �P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B<BR>
     * <BR>
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCall���^�[���l<BR>
     * @@param l_returnValue - �T�[�r�X���\�b�h���^�[���l<BR>
     * @@roseuid 40567DF900C5
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //������ԊǗ�.OFFSET_TAG 
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
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
     * �@@������ԊǗ�.TIMESTAMP_TAG<BR>
     * �@@������ԊǗ�.OFFSET_TAG<BR>
     * �@@����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCall���^�[���l<BR>
     * @@param l_throwable - ��O�I�u�W�F�N�g<BR>
     * @@roseuid 40567DF90161
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(STR_METHOD_NAME); 
        
        //�P�j�@@ThreadLocalSystemAttributesRegistry�̈ȉ��̓��e���N���A����B
        
        //������ԊǗ�.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);        
        
        //������ԊǗ�.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        
        //����J�����_�R���e�L�X�g.TRADING_CALENDAR_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);
        
        log.exiting(STR_METHOD_NAME);       
    }
}
@
