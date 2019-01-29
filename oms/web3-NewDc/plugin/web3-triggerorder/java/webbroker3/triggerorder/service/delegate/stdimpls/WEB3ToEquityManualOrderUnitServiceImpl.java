head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToEquityManualOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����蓮����UnitServiceImpl(WEB3ToEquityManualOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/3/6 ��O��(���u) �V�K�쐬
                   2006/08/30 �ęԍg(���u) �d�l�ύX���f��165
                   2007/01/11 ��іQ (���u) ���f�� No.206
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToEquityManualOrderUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����蓮����UnitServiceImpl)<BR>
 * ���ۃN���X�iabstract�j<BR>
 * <BR>
 * @@author ��O�� <BR>
 * @@version 1.0<BR>
 */
public abstract class WEB3ToEquityManualOrderUnitServiceImpl implements WEB3ToEquityManualOrderUnitService 
{
    
    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    	WEB3ToEquityManualOrderUnitServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToEquityManualOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * ����1�����Ƃ̎蓮�����m�F�������s���B <BR> 
     * <BR>
     * �V�[�P���X�} <BR>
     * �w(�����蓮����Unit�T�[�r�X)validate�x�Q�ƁB<BR>
     * <BR>
     * @@param l_strOrderId - (����ID)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    public WEB3EquityManualUnit validate(String l_strOrderId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate(String)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityManualUnit l_web3EquityManualUnit = null; 
            
        //1.1 get�����f�[�^(String)
        //�����͈ȉ��̒ʂ�ɐݒ肷��B  
        //����ID�F�@@�p�����[�^.����ID
        EqTypeOrderUnit l_eqTypeOrderUnit = 
            this.getOrderData(l_strOrderId);
        
        //1.2 �i����t���[�F get�����f�[�^()�̖߂�l == null�̏ꍇ�j
        if (l_eqTypeOrderUnit == null)
        {
            //1.2.1 get�G���[Unit���X�|���X(String)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  

            //�蓮�����G���[�R�[�h�F�@@"�Y���Ȃ�" 
            l_web3EquityManualUnit = 
                this.getErrorUnitResponse(WEB3ToManualOrderErrorCodeDef.NOT_AVAILABLE);
            
            //1.2.2
            return l_web3EquityManualUnit;
        }
        
        //1.3 set����J�����_�R���e�L�X�g(EqTypeOrderUnit)

        //�����͈ȉ��̒ʂ�ɐݒ肷��B  
        //�����f�[�^�F�@@this.get�����f�[�^()�̖߂�l 
        this.setTradingClendarContext(l_eqTypeOrderUnit);
        
        //1.4 validate( )
        try
        {
            this.validate();
        
            //1.5 getUnit���X�|���X(EqTypeOrderUnit)
    
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //�����f�[�^�F�@@this.get�����f�[�^()�̖߂�l 
            l_web3EquityManualUnit = 
                this.getUnitResponse(l_eqTypeOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            //1.6 (*)�������ɗ�O�����������ꍇ
            //get�G���[Unit���X�|���X(String)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  

            //�蓮�����G���[�R�[�h�F�@@"���̑��G���[" 
            l_web3EquityManualUnit = 
                this.getErrorUnitResponse(WEB3ToManualOrderErrorCodeDef.OTHER);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_web3EquityManualUnit;
    }
    
    /**
     * ����1�����Ƃ̎蓮���������������s���B  <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �w(�����蓮����Unit�T�[�r�X)submit�x�Q�ƁB<BR>
     * <BR>
     * @@param l_strOrderId - (����ID)<BR>
     * @@param l_submitterLoginId - (�����҃��O�C��ID)<BR>
     * @@param l_strNotifyType - (�ʒm�o�H)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    public WEB3EquityManualUnit submit(
        String l_strOrderId, 
        Long l_submitterLoginId, 
        String l_strNotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submit(String, Long, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualUnit l_web3EquityManualUnit = null; 

        //1.1 get�����f�[�^(String)
        
        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //����ID�F�@@�p�����[�^.����ID 
        
        EqTypeOrderUnit l_eqTypeOrderUnit = 
            this.getOrderData(l_strOrderId);
        
        //1.2 �i����t���[�F get�����f�[�^()�̖߂�l == null�̏ꍇ�j
        if (l_eqTypeOrderUnit == null)
        {
            //1.2.1 get�G���[Unit���X�|���X(String)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B

            //�蓮�����G���[�R�[�h�F�@@"�Y���Ȃ�" 
            l_web3EquityManualUnit = 
                this.getErrorUnitResponse(WEB3ToManualOrderErrorCodeDef.NOT_AVAILABLE);
            
            //1.2.2
            return l_web3EquityManualUnit;
        }
        
        //1.3 set����J�����_�R���e�L�X�g(EqTypeOrderUnit)

        //�����͈ȉ��̒ʂ�ɐݒ肷��B  
        //�����f�[�^�F�@@this.get�����f�[�^()�̖߂�l 
        this.setTradingClendarContext(l_eqTypeOrderUnit);
        
        try
        {
            //1.4 validate()
            this.validate();
   
            //1.5 getUnit���X�|���X(EqTypeOrderUnit)
    
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  
            //�����f�[�^�F�@@this.get�����f�[�^()�̖߂�l 
            l_web3EquityManualUnit = 
                this.getUnitResponse(l_eqTypeOrderUnit);
            
            //���[���G���W������̒ʒm�e�[�u����INSERT����B
    
            //�ȉ������ɊY������ꍇ�̂݁A���{����B
            //�@@�E�����蓮����Unit.�蓮�����G���[�R�[�h��"����"
            if (WEB3ToManualOrderErrorCodeDef.NORMAL.equals(
                    l_web3EquityManualUnit.manualOrderErrorCode))
            {    
                //1.6 sendRLSRequest(EqTypeOrderUnit, Long, String)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B  
        
                //�����f�[�^�F�@@this.get�����f�[�^()�̖߂�l 
                //�����҃��O�C��ID�F�@@�p�����[�^.�����҃��O�C��ID 
                //�ʒm�o�H�F�@@�p�����[�^.�ʒm�o�H
                
                this.sendRLSRequest(
                    l_eqTypeOrderUnit,
                    l_submitterLoginId, 
                    l_strNotifyType);
            }
        }
        catch(WEB3BaseException l_ex)
        {
            ////1.7 (*)�������ɗ�O�����������ꍇ
            //get�G���[Unit���X�|���X(String)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B  

            //�蓮�����G���[�R�[�h�F�@@"���̑��G���[" 
            l_web3EquityManualUnit = 
                this.getErrorUnitResponse(WEB3ToManualOrderErrorCodeDef.OTHER);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_web3EquityManualUnit;
    }
    
    /**
     * �����蓮�����̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@������t�\�`�F�b�N���s���B <BR>
     * �@@�@@�@@������ԊǗ�.validate������t()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�s��J�ǃ`�F�b�N���s���B<BR>
     * �@@�@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�()���R�[������B<BR>
     * <BR>
     * �@@�@@�Q�|�P�j�@@is�s��J�ǎ��ԑ�()�̖߂�l��"false"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u�V�X�e������t�\���ԊO�v�̗�O��throw����B<BR>
     * <BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@������t�\�`�F�b�N���s���B
        //������ԊǗ�.validate������t()���R�[������B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //�Q�j�@@�s��J�ǃ`�F�b�N���s���B
        boolean l_blTradeOpenTime =
                      WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();

        //�Q�|�P�j�@@is�s��J�ǎ��ԑ�()�̖߂�l��false�̏ꍇ
        if (!l_blTradeOpenTime)
        {
            log.debug(STR_METHOD_NAME + "�F��t�\���ԊO");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�����f�[�^)
     * <BR>
     * �p�����[�^�D����ID�ɊY�����钍���P�ʂ��擾����B <BR>
     * <BR>
     * �P�j�@@�����P�ʂ��擾����B <BR>
     * �@@�@@�@@�g�����������}�l�[�W��.getOrderUnits()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[getOrderUnits()�Ɏw�肷�����] <BR>
     * �@@�@@�@@arg0�F�@@�p�����[�^.����ID <BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾���������P�ʂ� �v�f�� == 0 �̏ꍇ <BR>
     * �@@�@@�@@�@@�@@null��Ԃ��B <BR>
     * <BR>
     * �@@�@@�@@�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�P�j�Ŏ擾���������P�ʂ�0�Ԗڂ̗v�f��Ԃ��B<BR>
     * <BR>
     * @@param l_strOrderId - (����ID) <BR>
     * @@return EqTypeOrderUnit <BR>
     * @@roseuid 432175DD01A0
     */
    protected EqTypeOrderUnit getOrderData(String l_strOrderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderData(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����P�ʂ��擾����B 
        //�@@�g�����������}�l�[�W��.getOrderUnits()���R�[������B
        //  [getOrderUnits()�Ɏw�肷�����] 
        //�@@ arg0�F�@@�p�����[�^.����ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager(); 
        
        OrderUnit[] l_orderUnits = 
            l_orderManager.getOrderUnits(Long.parseLong(l_strOrderId));
        
        //�Q�j�@@�P�j�Ŏ擾���������P�ʂ� �v�f�� == 0 �̏ꍇ 
        //�@@�@@null��Ԃ��B 

        //�@@�ȊO�̏ꍇ�A 
        //�@@�@@�@@�P�j�Ŏ擾���������P�ʂ�0�Ԗڂ̗v�f��Ԃ��B
        if (l_orderUnits.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeOrderUnit;
        }
    }
    
    /**
     * (set����J�����_�R���e�L�X�g)
     * <BR>
     * ����J�����_�����p����R���e�L�X�g�𐶐�����B <BR>
     * <BR>
     * �P�j�@@���X�I�u�W�F�N�g���擾�B <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[getBranch()�ɐݒ肷�����] <BR>
     * �@@�@@�@@arg0�F�@@�����f�[�^.getBranchId()�̖߂�l <BR>
     * <BR>
     * �Q�j�@@�،���ЃI�u�W�F�N�g���擾�B <BR>
     * �@@�@@�@@getBranch()�̖߂�l.getInstitution()���R�[������B <BR>
     * <BR>
     * �R�j�@@�s��I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�R�|�P�j�@@�����P��Row�𐶐�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����f�[�^.getDataSourceObject()���R�[������B<BR>
     * <BR>
     * �@@�@@�R�|�Q�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()���R�[������B<BR>
     * <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@[getMarket()�ɐݒ肷�����]<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.getMarketId()<BR>
     * <BR>
     * �S�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B    <BR>
     * �@@�|�p�����[�^.�����f�[�^�̓��e��������ԃR���e�L�X�g�� <BR>
     * �@@�@@�@@�v���p�e�B���Z�b�g����B  <BR>
     * <BR>
     * �@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = <BR>
     * �@@�@@�@@getInstitution()�̖߂�l.getInstitutionCode() <BR>
     * �@@����J�����_�R���e�L�X�g.���X�R�[�h = <BR>
     * �@@�@@�@@getBranch()�̖߂�l.getBranchCode() <BR>
     * �@@����J�����_�R���e�L�X�g.�s��R�[�h =  <BR>
     *       getMarket()�̖߂�l.getMarketCode()<BR> 
     * �@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1) <BR>
     * �@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h <BR>
     * �@@����J�����_�R���e�L�X�g.������t���i = (*2) <BR>
     * �@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*3) <BR>
     * <BR>
     * �@@(*1)��t���ԋ敪 <BR>
     * �@@�@@�@@�E�����J�e�S��(�����f�[�^.getOrderCateg())���h�����E���n�����h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�h�����E���n�h���Z�b�g����B <BR>
     * �@@�@@�@@�E�����J�e�S������L�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�h�����E�M�p�h���Z�b�g����B <BR>
     * <BR>
     * �@@(*2)������t���i <BR>
     * �@@�@@�@@�E�����J�e�S���i�����f�[�^.getOrderCateg()�j���h���������h�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�@@�h�����h���Z�b�g����B <BR>
     * �@@�@@�@@�E�����J�e�S������L�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�h�M�p�h���Z�b�g����B <BR>
     * <BR>
     * �@@(*3)������t�g�����U�N�V���� <BR>
     * �@@�@@�@@�E������� == �i�h�����������h�܂��́h�V�K���������h�j�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�h���t�i�V�K�����j�h���Z�b�g����B <BR>
     * �@@�@@�@@�E������� == �i�h�����������h�܂��́h�V�K���������h�j�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�h���t�i�V�K�����j�h���Z�b�g����B <BR>
     * �@@�@@�@@�E�����J�e�S�����h�ԍρh�̏ꍇ�A"�ԍ�"���Z�b�g����B  <BR>
     * �@@�@@�@@�E�����J�e�S�����h�������n�h�̏ꍇ�A"�������n"���Z�b�g����B  <BR>
     * <BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�    <BR>
     * �@@�@@�@@������ԃR���e�L�X�g���Z�b�g����B    <BR>
     * �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH    <BR>
     * <BR>
     * �T�j�@@��t�����A���t���[�����Z�b�g����B    <BR>
     * �@@�|������ԊǗ�.setTimestamp()���R�[������B<BR>
     * <BR>
     * @@param l_orderData - (�����f�[�^)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_orderData) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderData == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        } 

        //�P�j�@@���X�I�u�W�F�N�g���擾�B 
        //�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B
        //�@@[getBranch()�ɐݒ肷�����] 
        //�@@arg0�F�@@�����f�[�^.getBranchId()�̖߂�l 
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        Branch l_branch = null;
        
        try
        {
            l_branch = l_accountManager.getBranch(l_orderData.getBranchId());           
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�،���ЃI�u�W�F�N�g���擾�B 
        //�@@�@@getBranch()�̖߂�l.getInstitution()���R�[������B 
        Institution l_institution = l_branch.getInstitution();
        
        //�s��I�u�W�F�N�g���擾����B
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = 
            (EqtypeOrderUnitRow)l_orderData.getDataSourceObject();
            
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
             
        long l_lngMarketId = l_eqtypeOrderUnitRow.getMarketId();        
        WEB3GentradeMarket l_market = null;
        
        try
        {
            l_market = 
                (WEB3GentradeMarket)l_finObjManager.getMarket(l_lngMarketId);           
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�S�j�@@����J�����_�R���e�L�X�g�ɓ��e���Z�b�g����B    
        //�|�p�����[�^.�����f�[�^�̓��e��������ԃR���e�L�X�g�� 
        //�@@�@@�v���p�e�B���Z�b�g����B  
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        //�@@����J�����_�R���e�L�X�g.�،���ЃR�[�h = getInstitution()�̖߂�l.getInstitutionCode() 
        l_context.setInstitutionCode(l_institution.getInstitutionCode());
        
        //�@@����J�����_�R���e�L�X�g.���X�R�[�h = getBranch()�̖߂�l.getBranchCode()
        l_context.setBranchCode(l_branch.getBranchCode());
        
        //�@@����J�����_�R���e�L�X�g.�s��R�[�h = getMarket()�̖߂�l.getMarketCode() 
        l_context.setMarketCode(l_market.getMarketCode());
        
        //�@@����J�����_�R���e�L�X�g.��t���ԋ敪 = (*1) 
        //�@@(*1)��t���ԋ敪 
        //�@@�E�����J�e�S��(�����f�[�^.getOrderCateg())���h�����E���n�����h�̏ꍇ�A 
        //�@@�@@�@@�h�����E���n�h���Z�b�g����B 
        
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
        }
        //  �E�����J�e�S������L�ȊO�̏ꍇ�A 
        //�@@�@@�@@�h�����E�M�p�h���Z�b�g����B
        else
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        }      
        
        //�@@����J�����_�R���e�L�X�g.�����R�[�h = �h0�FDEFAULT�h 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //�@@����J�����_�R���e�L�X�g.������t���i = (*2) 
        //�@@(*2)������t���i 
        //�@@�@@�E�����J�e�S���i�����f�[�^.getOrderCateg()�j���h���������h�̏ꍇ�A 
        //�@@�@@�@@�@@�h�����h���Z�b�g����B 

        if (OrderCategEnum.ASSET.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        //�@@  �E�����J�e�S������L�ȊO�̏ꍇ�A 
        //�@@�@@�@@�@@�h�M�p�h���Z�b�g����B
        else
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        }        
        
        //�@@����J�����_�R���e�L�X�g.������t�g�����U�N�V���� = (*3) 
        
        //�@@(*3)������t�g�����U�N�V���� 
        //�@@�E������� == �i�h�����������h�܂��́h�V�K���������h�j�̏ꍇ�A         
        //�@@�@@    �h���t�i�V�K�����j�h���Z�b�g����B 
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderData.getOrderType()) ||
            OrderTypeEnum.MARGIN_LONG.equals(l_orderData.getOrderType()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        }
        
        //  �E������� == �i�h�����������h�܂��́h�V�K���������h�j�̏ꍇ�A 
        //�@@�@@    �h���t�i�V�K�����j�h���Z�b�g����B 
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderData.getOrderType()) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_orderData.getOrderType()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        }
        
        //  �E�����J�e�S�����h�ԍρh�̏ꍇ�A"�ԍ�"���Z�b�g����B  
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CLOSE_MARGIN);
        }
        
        //  �E�����J�e�S�����h�������n�h�̏ꍇ�A"�������n"���Z�b�g����B 
        else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.SWAP_MARGIN);
        }
        
        //  �|ThreadLocalSystemAttributesRegistry.setAttribute( )�ɂ�    
        //�@@�@@������ԃR���e�L�X�g���Z�b�g����B    
        //    �ݒ�L�[�F ������ԊǗ�.TRADING_CAL_CONTEXT_PATH    

        //�T�j�@@��t�����A���t���[�����Z�b�g����B    
        //�@@�|������ԊǗ�.setTimestamp()���R�[������B  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
    
        //������ԊǗ�
        WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�蓮�����萔�����)
     * <BR>
     * �蓮�����萔�������擾����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �w(�����蓮����Unit�T�[�r�X)create�蓮�����萔���x <BR>
     * �Q�ƁB<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)
     * @@param l_blnIsLimitPrice - (is�w�l)
     * @@return WEB3ManualCommissionInfoUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3ManualCommissionInfoUnit createManualCommissionInfoUnit(
        EqTypeOrderUnit l_orderUnit,
        boolean l_blnIsLimitPrice
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createManualCommissionInfoUnit(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�@@create�萔��(�����P��)

        //�����͈ȉ��̒ʂ�ɐݒ肷��B  
        //����ID�F�@@�p�����[�^.�����P��.����ID
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
            (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
        
        EqTypeOrderUnitImpl l_orderUnitImpl = 
            (EqTypeOrderUnitImpl)l_orderUnit;        
        
        WEB3GentradeCommission l_commission = 
            l_eqBizLogicProvider.createCommission(l_orderUnitImpl);
        
        //�@@�萔��.is�w�l�Ƀp�����[�^.is�w�l��ݒ肷��B
        l_commission.setIsLimitPrice(l_blnIsLimitPrice);
        
        //�@@calc���o��v�Z�p���(�����P��)

        //�����͈ȉ��̒ʂ�ɐݒ肷��B

        //�����P�ʁF�@@�p�����[�^.�����P��
        //is�w�l�F�@@�p�����[�^.is�w�l 
        double l_dblExpensesCalcAmount = 
            this.calcExpensesCalcAmount(l_orderUnit, l_blnIsLimitPrice);
        
        //�@@set���o��v�Z�p���(���o��v�Z�p��� : double)

        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //���o��v�Z�p����F�@@calc���o��v�Z�p���()�̖߂�l
        l_commission.setExpensesCalcAmount(l_dblExpensesCalcAmount);

        //�@@get�⏕����(����ID : , �⏕����ID : )

        //�����͈ȉ��̒ʂ�ɐݒ肷��B

        //����ID�F�@@�p�����[�^�D�����P��.getAccountId()
        //�⏕����ID�F�@@�p�����[�^.�����P��.getSubAccountId()        
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�@@calc�ϑ��萔��(�萔�� : �萔��, �⏕���� : SubAccount)

        //�����͈ȉ��̒ʂ�ɐݒ肷��B
        //�萔���F�@@�萔�� 
        //�⏕�����F�@@get�⏕����()�̖߂�l
        l_eqBizLogicProvider.calcCommission(
            l_commission, 
            l_subAccount);
        
        //�@@calc�����(���z : double, ��� : Timestamp, �⏕���� : �⏕����)

        //�����͈ȉ��̒ʂ�ɐݒ肷��B  

        //���z�F�@@�萔��.�萔�����z   
        //����F�@@�萔��.������  
        //�⏕�����F�@@get�⏕����()�̖߂�l
        double l_dblSalesTax = 
            l_eqBizLogicProvider.calcSalesTax(
                l_commission.getCommission(),
                l_commission.getOrderBizDate(),
                l_subAccount);
        
        //�@@�蓮�����萔�����I�u�W�F�N�g�𐶐�����B
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit = 
            new WEB3ManualCommissionInfoUnit();

        //�@@(*)�Z�b�g�v���p�e�B
        //�萔���R�[�X�F�@@�萔��.get�萔���R�[�X�R�[�h()�̖߂�l
        l_manualCommissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
        
        //�萔���F�@@�萔��.get�萔�����z()�̖߂�l
        l_manualCommissionInfoUnit.commission = WEB3StringTypeUtility.formatNumber(l_commission.getCommission());
        
        //�萔������ŁF�@@calc�����()�̖߂�l
        l_manualCommissionInfoUnit.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblSalesTax);
        
        log.exiting(STR_METHOD_NAME);
        return l_manualCommissionInfoUnit;
    }
    
    /**
     * (calc���o��v�Z�p���)
     * <BR>
     * �萔���v�Z�ׂ̈̏��o��v�Z�p������擾����B <BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ɂ�蕪�򂵁A�Ή����郁�\�b�h���R�[������B   <BR>
     * <BR>
     * �@@�@@�@@�p�����[�^.�����P��.������ʂ��ȉ��̂����ꂩ�ɊY������ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�@@�E"����������" <BR>
     * �@@�@@�@@�@@�@@�@@�E"�V�K��������" <BR>
     * �@@�@@�@@�@@�@@�@@�E"�V�K��������" <BR>
     * �@@�@@�@@�����v�Z�T�[�r�X.calc�S���������()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[calc�S���������()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����F�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�@@�@@�v�Z�P���F�@@�p�����[�^.�����P��.�����P�� <BR>
     * �@@�@@�@@�@@���X�h�c�F�@@�p�����[�^.�����P��.���XID <BR>
     * �@@�@@�@@�@@�萔�����i�R�[�h�F�@@"��ꊔ��" <BR>
     * �@@�@@�@@�@@is�w�l�F�@@�p�����[�^.is�w�l<BR>
     * <BR>
     * <BR>
     * �@@�@@�@@�p�����[�^.�����P��.������ʂ��ȉ��̂����ꂩ�ɊY������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�E"����������" <BR>
     * �@@�@@�@@�@@�@@�@@�E"�����ԍϒ���(���ԍ�)" <BR>
     * �@@�@@�@@�@@�@@�@@�E"�����ԍϒ���(���ԍ�)" <BR>
     * �@@�@@�@@�����v�Z�T�[�r�X.calc�������()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[calc�������()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@���ʁF�@@�p�����[�^.�����P��.�������� <BR>
     * �@@�@@�@@�@@�v�Z�P���F�@@�p�����[�^.�����P��.�����P�� <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l��ԋp����B <BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)
     * @@param l_blnIsLimitPrice - (is�w�l)
     * @@throws WEB3BaseException
     * @@return double
     * @@roseuid 432175DD01A0
     */
    protected double calcExpensesCalcAmount(
        EqTypeOrderUnit l_orderUnit, boolean l_blnIsLimitPrice) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcExpensesCalcAmount(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�P�j�@@�ȉ��̏����ɂ�蕪�򂵁A�Ή����郁�\�b�h���R�[������B   

        //�@@�@@�p�����[�^.�����P��.������ʂ��ȉ��̂����ꂩ�ɊY������ꍇ�A 
        //�@@�@@�@@�@@�@@�E"����������" 
        //�@@�@@�@@�@@�@@�E"�V�K��������" 
        //�@@�@@�@@�@@�@@�E"�V�K��������"
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        double l_dblExpensesCalcAmount = 0.0;
        
        if (OrderTypeEnum.EQUITY_BUY.equals(l_eqtypeOrderUnitRow.getOrderType()) ||
            OrderTypeEnum.MARGIN_LONG.equals(l_eqtypeOrderUnitRow.getOrderType()) || 
            OrderTypeEnum.MARGIN_SHORT.equals(l_eqtypeOrderUnitRow.getOrderType()))
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
                (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            //�����v�Z�T�[�r�X.calc�S���������()���R�[������B 
            //�@@�@@�@@[calc�S���������()�ɐݒ肷�����] 
            //�@@�@@�@@�����F�@@�p�����[�^.�����P��.�������� 
            //�@@�@@�@@�v�Z�P���F�@@�p�����[�^.�����P��.�����P�� 
            //�@@�@@�@@���X�h�c�F�@@�p�����[�^.�����P��.���XID 
            //�@@�@@�@@�萔�����i�R�[�h�F�@@"��ꊔ��"
            //�@@�@@�@@is�w�l�F�@@�p�����[�^.is�w�l 
            
            l_dblExpensesCalcAmount = 
                l_eqBizLogicProvider.calcRestraintTurnover(
                    l_eqtypeOrderUnitRow.getQuantity(),
                    l_eqtypeOrderUnitRow.getPrice(), 
                    l_eqtypeOrderUnitRow.getBranchId(), 
                    WEB3CommisionProductCodeDef.LISTING_STOCK, 
                    l_blnIsLimitPrice);                
        }        

        //�@@�@@�p�����[�^.�����P��.������ʂ��ȉ��̂����ꂩ�ɊY������ꍇ�A 
        //�@@�@@�@@�@@�@@�E"����������" 
        //�@@�@@�@@�@@�@@�E"�����ԍϒ���(���ԍ�)" 
        //�@@�@@�@@�@@�@@�E"�����ԍϒ���(���ԍ�)" 
        if (OrderTypeEnum.EQUITY_SELL.equals(l_eqtypeOrderUnitRow.getOrderType()) ||
            OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_eqtypeOrderUnitRow.getOrderType()) || 
            OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_eqtypeOrderUnitRow.getOrderType()))
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
                (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            //�@@�@@�����v�Z�T�[�r�X.calc�������()���R�[������B 

            //�@@�@@�@@[calc�������()�ɐݒ肷�����] 
            //�@@�@@�@@���ʁF�@@�p�����[�^.�����P��.�������� 
            //�@@�@@�@@�v�Z�P���F�@@�p�����[�^.�����P��.�����P��           
            
            l_dblExpensesCalcAmount = 
                l_eqBizLogicProvider.calcTurnover(
                    l_eqtypeOrderUnitRow.getQuantity(), 
                    l_eqtypeOrderUnitRow.getPrice());
        }

        //�Q�j�@@�P�j�̖߂�l��ԋp����B 

        log.exiting(STR_METHOD_NAME);
        return l_dblExpensesCalcAmount;
    }
    
    /**
     * (getUnit���X�|���X)
     * <BR>
     * �����蓮����Unit�Ƀv���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�����f�[�^��"���������P�ʌ^"�ɃL���X�g����B <BR>
     * �@@�@@�@@�i���ȉ��A�L���X�g�����I�u�W�F�N�g��"�����P��"�ƕ\�L����B�j <BR>
     * <BR>
     * �Q�j�@@this.createUnit���X�|���X()���R�[������B<BR>
     * <BR>
     * �R�j�@@���X�I�u�W�F�N�g���擾����B <BR>
     * �@@�@@  �g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[getBranch()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@arg0�F�@@�����P��.getBranchId() <BR>
     * <BR>
     * �S�j�@@�ڋq�I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get�ڋq()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@arg0�F�@@�����P��.getAccountId() <BR>
     * <BR>
     * �T�j�@@�⏕�������擾����B <BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�⏕����()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get�⏕����()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@arg0�F�@@�����P��.getAccountId() <BR>
     * �@@�@@�@@�@@arg1�F�@@�����P��.getSubAccountId() <BR>
     * <BR>
     * �U�j�@@�s����擾����B <BR>
     * �@@�@@�U�|�P�j�@@�����P��Row�𐶐�����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����P��.getDataSourceObject()���R�[������B <BR>
     * <BR>
     * �@@�@@�U�|�Q�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@[get�s��()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.getMarketId() <BR>
     * <BR>
     * �V�j�@@�����������擾����B <BR>
     * �@@�@@�@@�����P��.getProduct()���R�[������B <BR>
     * <BR>
     * �W�j�@@����������擾����B <BR>
     * �@@�@@�@@�����P��.getTradedProduct()���R�[������B <BR>
     * <BR>
     * �X�j�@@��������Row�𐶐�����B <BR>
     * �@@�@@�@@getProduct()�̖߂�l.getDataSourceObject()�R�[������B <BR>
     * <BR>
     * �P�O�j���i�敪���擾����B <BR>
     * �@@�@@  �����f�[�^�A�_�v�^.get���i�敪()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get���i�敪()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �P�P�j ����敪���擾����B <BR>
     * �@@�@@�@@�����f�[�^�A�_�v�^.get����敪()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get����敪()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@������ʁF�@@�����P��.������� <BR>
     * <BR>
     * �P�Q�j ���s�������擾����B <BR>
     * �@@�@@�@@�g�����������}�l�[�W��.get���s����(SONAR)()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get���s����(SONAR)()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@���s�����F�@@�����P��.���s���� <BR>
     * <BR>
     * �P�R�j �o����܂Œ��������肷��B <BR>
     * �@@�@@�@@�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B<BR> 
     * <BR>
     * �@@�@@�@@�@@[is�o����܂Œ����P��()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �P�S�j ������ԋ敪���擾����B <BR>
     * �@@�@@�@@�����f�[�^�A�_�v�^.get������ԋ敪()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get������ԋ敪()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �P�T�j ����ԋ敪���擾����B <BR>
     * �@@�@@�@@�����f�[�^�A�_�v�^.get����ԋ敪()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get����ԋ敪()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �P�U�j �������擾����B <BR>
     * �@@�@@�@@�g���v���_�N�g�}�l�[�W��.get�\���p�������()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get�\���p�������()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@��������F�@@getTradedProduct() <BR>
     * �@@�@@�@@�@@�⏕�����F�@@get�⏕����() <BR>
     * <BR>
     * �P�V�j �����敪���擾����B <BR>
     * �@@�@@�@@�����f�[�^�A�_�v�^.get�����敪()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get�����敪()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�ŋ敪�F�@@�����P��.�ŋ敪 <BR>
     * <BR>
     * �P�W�j �����󋵋敪���擾����B <BR>
     * �@@�@@�@@�����f�[�^�A�_�v�^.get�����󋵋敪()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[get�����󋵋敪()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�����P�� <BR>
     * <BR>
     * �P�X�j�@@�����P��.������ʂ�"�������t����"�̏ꍇ�̂�<BR>
     * �@@�@@�@@�T�Z�뉿�P�����擾����B<BR>
     * <BR>
     * �@@�@@�@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��()���R�[������B<BR>
     * �@@�@@�@@����O���X���[���ꂽ�ꍇ�A"null"���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@�@@[calc�T�Z�뉿�P��()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@����ID�F�@@�����P��Row.����ID<BR>
     * �@@�@@�@@�@@�⏕�����F�@@get�⏕����()�̖߂�l<BR>
     * �@@�@@�@@�@@�ŋ敪�F�@@�����P��.�ŋ敪<BR>
     * <BR>
     * �Q�O�j�@@�����P��.������ʂ�"�V�K��������"�܂���"�V�K��������"�̏ꍇ�̂�<BR>
     * �@@�@@�@@�i���X�s��ٍϕʁj�戵�������擾����B<BR>
     * <BR>
     * �@@�@@�@@�i���X�s��ٍϕʁj�戵����()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[�i���X�s��ٍϕʁj�戵����()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F�@@get�⏕����().�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@���X�R�[�h�F�@@getBranch()�̖߂�l.get���X�R�[�h<BR>
     * �@@�@@�@@�@@�s��R�[�h�F�@@get�s��()�̖߂�l.get�s��R�[�h<BR>
     * �@@�@@�@@�@@�ٍϋ敪�F�@@�����P��Row.�ٍϋ敪<BR>
     * �@@�@@�@@�@@�ٍϊ����l�F�@@�����P��Row.�ٍϊ����l<BR>
     * <BR>
     * �Q�P�j�@@�����P��.�����J�e�S����"��������"�ȊO�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�M�p����ٍσI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@�@@���ȊO��"null"���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@�@@�M�p����ٍ�.�ٍϋ敪�F�@@�����P��Row.�ٍϋ敪<BR>
     * �@@�@@�@@�@@�M�p����ٍ�.�ٍϊ����l�F�@@�����P��Row.�ٍϊ����l<BR>
     * <BR>
     * �Q�Q�j�@@createUnit���X�|���X()�Ŏ擾�����A <BR>
     * �@@�@@�@@�@@�����蓮����Unit�̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�y���i���ʃv���p�e�B�z <BR>
     * �@@ID�F�@@�����P��.����ID <BR>
     * �@@���X�R�[�h�F�@@getBranch()�̖߂�l.get���X�R�[�h() <BR>
     * �@@�ڋq�R�[�h�F�@@get�ڋq()�̖߂�l.get�ڋq�R�[�h() <BR>
     * �@@�s��R�[�h�F�@@get�s��()�̖߂�l.get�s��R�[�h() <BR>
     *   �����R�[�h�F�@@��������Row.get�����R�[�h() <BR>
     * �@@�������F�@@��������Row.get������() <BR>
     * �@@���i�敪�F�@@get���i�敪()�̖߂�l <BR>
     * �@@����敪�F�@@get����敪()�̖߂�l <BR>
     * �@@���s�����F�@@get���s����(SONAR)()�̖߂�l <BR>
     * �@@���������敪�F�@@is�o����܂Œ����P��()�̖߂�l��ture�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�o����܂Œ���"���Z�b�g�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO"��������"�A���Z�b�g�B <BR>
     * �@@�����L�������F�@@is�o����܂Œ����P��()�̖߂�l��ture�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��.�����������t���Z�b�g�B <BR>
     * �@@�������ʁF�@@�����P�ʁD�������� <BR>
     * �@@�����P���敪�F�@@�����P��.isMarketOrder()�̖߂�l��true�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"���s"���Z�b�g�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false�̏ꍇ��"�w�l"���Z�b�g�B <BR>
     * �@@�����P���F�@@�����P���敪��"�w�l"�̏ꍇ�A�����P��.�w�l���Z�b�g�B <BR>
     * �@@������ԋ敪�F�@@get������ԋ敪()�̖߂�l <BR>
     * �@@����ԋ敪�F�@@get����ԋ敪()�̖߂�l <BR>
     * �@@��������敪�F�@@�����P��.���������E����敪 <BR>
     * �@@�������ԁF�@@�����P��.�󒍓��� <BR>
     * �@@�������F�@@�����P��.������ <BR>
     * �@@��n���F�@@�����P��.��n�� <BR>
     * �@@�T�Z��n����F�@@�����P��.�T�Z��n��� <BR>
     * �@@���Ϗ����F�@@�����P��.���Ϗ����敪 <BR>
     * �@@�����敪�F�@@get�\���p�������̖߂�l.get�����敪() <BR>
     * �@@�����i���ݒl�j�F�@@get�\���p�������̖߂�l.get����() <BR>
     * �@@�O����F�@@get�\���p�������̖߂�l.get�O����() <BR>
     * �@@������ԁi�������\���ԁj�F�@@get�\���p�������()�̖߂�l.get�������\����() <BR>
     * <BR>
     * �@@��񍀖ڂƂ��Ĉȉ���ݒ肷��<BR>
     * �@@�@@���ݒl�F�@@�擾�������������������.get���ݒl() <BR>
     * �@@�@@���ݒl�����F�@@�擾�������������������.get���ݒl����() <BR>
     * �@@�@@���ݒl�敪�F�@@�擾�������������������.get���ݒl�敪() <BR>
     * �@@�@@���ݒl�O����F�@@�擾�������������������.get���ݒl�O����() <BR>
     * �@@�@@�o�����F�@@�擾�������������������.get�o����()�̖߂�l���Z�b�g <BR>
     * �@@�@@�o���������F�@@�擾�������������������.get�o��������() <BR>
     * �@@�@@���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪() <BR>
     * �@@�@@���C�z�l�F�@@�擾�������������������.get���C�z�l() <BR>
     * �@@�@@���C�z�l�����F�@@�擾�������������������.get���C�z�l����() <BR>
     * �@@�@@���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪() <BR>
     * �@@�@@���C�z�l�F�@@�擾�������������������.get���C�z�l() <BR>
     * �@@�@@���C�z�l�����F�@@�擾�������������������.get���C�z�l����() <BR>
     * �@@�@@��l�i�F�@@�擾�������������������.get��l�i()<BR>
     * <BR>
     *   �P�������l�F�@@null���Z�b�g�B<BR>
     * <BR>
     * �@@�����敪�F�@@get�����敪()�̖߂�l <BR>
     * �@@�l�i�����F�@@�����P��.�l�i���� <BR>
     * �@@�����󋵋敪�F�@@get�����󋵋敪()�̖߂�l <BR> 
     * �@@�T�Z�뉿�P���F�@@�i*1�jcalc�T�Z�뉿�P��()�̖߂�l<BR>
     * �@@�����F�@@�����P��.������ʂ�"�V�K��������"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�i���X�s��ٍϕʁj�戵����.�����������Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�����P��.������ʂ�"�V�K��������"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�i���X�s��ٍϕʁj�戵����.�����������Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�ȊO�Anull���Z�b�g�B<BR>
     * �@@���Z���ԁF�@@�i*2�j�i���X�s��ٍϕʁj�戵����.�������o��Z����<BR>
     * �@@�ٍρF�@@�M�p����ٍσI�u�W�F�N�g���Z�b�g�B<BR>
     * <BR>
     * �i*1�j�����P��.������ʂ�"����������"�̏ꍇ�̂݃Z�b�g�B<BR>
     * �@@�@@�@@�ȊO�Anull���Z�b�g�B<BR>
     * <BR>
     * �i*2�j�ȉ������ɊY������ꍇ�̂݃Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�E�����P��.������ʂ�"�V�K��������"�܂���"�V�K��������"<BR>
     * �@@�@@�@@�@@�@@�E�����P��Row.�ٍϋ敪��"��ʐM�p"<BR>
     * �@@�@@�@@�@@�@@�E�����P��Row.�ٍϊ����l��"9999999"�i��ALL9�j<BR>
     *<BR>
     * �Q�R�j�@@�v���p�e�B���Z�b�g���������蓮����Unit��Ԃ��B <BR>
     * <BR>
     * @@param l_orderData - (�����f�[�^)
     * @@return WEB3EquityManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit getUnitResponse(
        EqTypeOrderUnit l_orderData) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnitResponse(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderData == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�P�j�@@�����f�[�^��"���������P�ʌ^"�ɃL���X�g����B 
        //�@@    �i���ȉ��A�L���X�g�����I�u�W�F�N�g��"�����P��"�ƕ\�L����B�j 
        
        EqTypeOrderUnit l_eqTypeOrderUnit = l_orderData;
        
        //�Q�j�@@this.createUnit���X�|���X()���R�[������B
        WEB3EquityManualUnit l_equityManualUnit = this.createUnitResponse();

        //�R�j�@@���X�I�u�W�F�N�g���擾����B 
        //�@@�@@�@@�g���A�J�E���g�}�l�[�W��.getBranch()���R�[������B 

        //�@@�@@�@@�@@[getBranch()�ɐݒ肷�����] 
        //�@@�@@�@@�@@arg0�F�@@�����P��.getBranchId() 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        Branch l_branch = null;
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        WEB3GentradeMarket l_market = null;
        try
        {
            //NotFoundException
            l_branch = l_accountManager.getBranch(l_eqTypeOrderUnit.getBranchId());
        
            //�S�j�@@�ڋq�I�u�W�F�N�g���擾����B 
            //�@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B 
            //�@@�@@[get�ڋq()�ɐݒ肷�����] 
            //�@@�@@arg0�F�@@�����P��.getAccountId() 
            l_mainAccount = 
                l_accountManager.getMainAccount(l_eqTypeOrderUnit.getAccountId());
    
            //�T�j�@@�⏕�������擾����B 
            //�@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�⏕����()���R�[������B
            //�@@�@@[get�⏕����()�ɐݒ肷�����] 
            //�@@�@@�@@�@@arg0�F�@@�����P��.getAccountId() 
            //�@@�@@    arg1�F�@@�����P��.getSubAccountId() 
            l_subAccount = l_accountManager.getSubAccount(
                l_eqTypeOrderUnit.getAccountId(),
                l_eqTypeOrderUnit.getSubAccountId());

        
            //�U�j�@@�s����擾����B 
            //�@@�U�|�P�j�@@�����P��Row�𐶐�����B 
            //�@@�@@�@@�@@�����P��.getDataSourceObject()���R�[������B 
            l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)
                l_eqTypeOrderUnit.getDataSourceObject();        
    
            
            // �U�|�Q�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B 

            //�@@�@@�@@�@@�@@�@@�@@[get�s��()�ɐݒ肷�����] 
            //�@@�@@�@@�@@�@@�@@�@@�����P��Row.getMarketId()         
            
            WEB3GentradeFinObjectManager l_finObjManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
            long l_lngMarketId = l_eqtypeOrderUnitRow.getMarketId();
            
            l_market =
                (WEB3GentradeMarket)l_finObjManager.getMarket(
                    l_lngMarketId);    //NotFoundException
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�V�j�@@�����������擾����B 
        //�@@�����P��.getProduct()���R�[������B 
        EqTypeProduct l_eqtypeProduct = (EqTypeProduct)
            l_eqTypeOrderUnit.getProduct();

        //�W�j�@@����������擾����B 
        //�@@�����P��.getTradedProduct()���R�[������B 
        EqTypeTradedProduct l_eqTypeTradedProduct = (EqTypeTradedProduct)
            l_eqTypeOrderUnit.getTradedProduct();

        //�X�j�@@��������Row�𐶐�����B 
        //�@@�@@�@@getProduct()�̖߂�l.getDataSourceObject()�R�[������B 
        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)
            l_eqtypeProduct.getDataSourceObject();

        //�P�O�j�@@���i�敪���擾����B 
        //�@@�@@�@@�����f�[�^�A�_�v�^.get���i�敪()���R�[������B 

        //�@@�@@�@@�@@[get���i�敪()�ɐݒ肷�����] 
        //�@@�@@�@@�@@�����P�ʁF�@@�����P��
        String l_strProductType = WEB3EquityDataAdapter.getProductType(l_eqTypeOrderUnit);
        
        //�P�P�j�@@����敪���擾����B 
        //�@@�����f�[�^�A�_�v�^.get����敪()���R�[������B 

        //�@@�@@[get����敪()�ɐݒ肷�����] 
        //�@@�@@������ʁF�@@�����P��.������� 
        String l_strTradingType = WEB3EquityDataAdapter.getTradingType(l_eqtypeOrderUnitRow.getOrderType());
        
        //�P�Q�j�@@���s�������擾����B 
        //�@@�g�����������}�l�[�W��.get���s����(SONAR)()���R�[������B 

        //�@@�@@[get���s����(SONAR)()�ɐݒ肷�����] 
        //�@@�@@���s�����F�@@�����P��.���s���� 
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        String l_strExecutionConditionTypeSonar = 
            l_orderManager.getExecutionConditionTypeSonar(
                l_eqtypeOrderUnitRow.getExecutionConditionType());

        //�P�R�j�@@�o����܂Œ��������肷��B 
        //�@@�@@�g�����������}�l�[�W��.is�o����܂Œ����P��()���R�[������B         

        //�@@�@@[is�o����܂Œ����P��()�ɐݒ肷�����] 
        //�@@�@@�����P�ʁF�@@�����P�� 
        boolean l_blnIsCarriedOrderUnit = 
            l_orderManager.isCarriedOrderUnit(l_eqTypeOrderUnit);
        
        //�P�S�j�@@������ԋ敪���擾����B 
        //�@@�@@�@@�����f�[�^�A�_�v�^.get������ԋ敪()���R�[������B 

        //�@@�@@[get������ԋ敪()�ɐݒ肷�����] 
        //�@@�@@�����P�ʁF�@@�����P�� 
        String l_strOrderState =  WEB3EquityDataAdapter.getOrderState(l_eqTypeOrderUnit);
       
        //�P�T�j�@@����ԋ敪���擾����B 
        //�@@�����f�[�^�A�_�v�^.get����ԋ敪()���R�[������B 
 
        //[get����ԋ敪()�ɐݒ肷�����] 
        //�@@�����P�ʁF�@@�����P��
        String l_strExecType = WEB3EquityDataAdapter.getExecType(l_eqTypeOrderUnit);

        //�P�U�j�@@�������擾����B 
        //�g���v���_�N�g�}�l�[�W��.get�\���p�������()���R�[������B 
 
        //�@@[get�\���p�������()�ɐݒ肷�����] 
        //�@@��������F�@@getTradedProduct() 
        //�@@�⏕�����F�@@get�⏕����()
        WEB3EquityProductManager l_productManager =  
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
       
        WEB3EquityProductQuote l_equityProductQuote = 
        l_productManager.getDisplayEquityProductQuote(
                l_eqTypeTradedProduct,(WEB3GentradeSubAccount)l_subAccount);         
       
        //�P�V�j�@@�����敪���擾����B 
        //�����f�[�^�A�_�v�^.get�����敪()���R�[������B 

       //�@@[get�����敪()�ɐݒ肷�����] 
       //�@@�ŋ敪�F�@@�����P��.�ŋ敪 
         String l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_eqTypeOrderUnit.getTaxType());
       
        //�P�W�j�@@�����󋵋敪���擾����B 
        //�����f�[�^�A�_�v�^.get�����󋵋敪()���R�[������B 

        //�@@[get�����󋵋敪()�ɐݒ肷�����] 
        //�@@�����P�ʁF�@@�����P�� 
        String l_strTransactionStatusType =  
        WEB3EquityDataAdapter.getTransactionStatusType(l_eqTypeOrderUnit);

        //�P�X�j�@@�����P��.������ʂ�"�������t����"�̏ꍇ�̂�
        //�@@�@@�@@�T�Z�뉿�P�����擾����B
        String l_estimatedBookPrice = null;
        
        if (OrderTypeEnum.EQUITY_SELL.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            //�@@�@@�@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��()���R�[������B
            //�@@�@@�@@����O���X���[���ꂽ�ꍇ�A"null"���Z�b�g�B
            WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
                (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            try
            {
                //�@@�@@�@@�@@[calc�T�Z�뉿�P��()�ɐݒ肷�����]
                l_estimatedBookPrice =
                    WEB3StringTypeUtility.formatNumber(l_eqBizLogicProvider.calcEstimatedBookPrice(
                        l_eqtypeOrderUnitRow.getProductId(),//�����P��Row.����ID
                        l_subAccount,                       //get�⏕����()�̖߂�l
                        l_eqTypeOrderUnit.getTaxType()));   //�����P��.�ŋ敪
            }
            catch(WEB3BaseException l_ex)
            { 
                l_estimatedBookPrice = null;
            }
            
        }

        //�Q�O�j�@@�����P��.������ʂ�"�V�K��������"�܂���"�V�K��������"�̏ꍇ�̂�
        //�@@�@@�@@�i���X�s��ٍϕʁj�戵�������擾����B
        BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow = null;
        String l_interestRates = null;
        if (OrderTypeEnum.MARGIN_LONG.equals(l_eqTypeOrderUnit.getOrderType()) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            //�@@�@@�@@�i���X�s��ٍϕʁj�戵����()���R�[������B
            //�@@�@@�@@�@@[�i���X�s��ٍϕʁj�戵����()�ɐݒ肷�����]
            WEB3GentradeBranchMarketRepayDealtCond l_genBranchMarketRepayDealtCond = 
                new WEB3GentradeBranchMarketRepayDealtCond(
                    l_subAccount.getInstitution().getInstitutionCode(),//get�⏕����().�،���ЃR�[�h
                    l_branch.getBranchCode(),                          //getBranch()�̖߂�l.get���X�R�[�h
                    l_market.getMarketCode(),                          //get�s��()�̖߂�l.get�s��R�[�h
                    l_eqtypeOrderUnitRow.getRepaymentType(),           //�����P��Row.�ٍϋ敪
                    l_eqtypeOrderUnitRow.getRepaymentNum()             //�����P��Row.�ٍϊ����l
                );
               
            l_branchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)l_genBranchMarketRepayDealtCond.getDataSourceObject();
        }
        
        //�Q�P�j�@@�����P��.�����J�e�S����"��������"�ȊO�̏ꍇ�̂݁A
        //�@@�@@�@@�@@�M�p����ٍσI�u�W�F�N�g�𐶐�����B
        //�@@�@@�@@�@@���ȊO��"null"���Z�b�g�B
        //�@@�@@�@@�@@�M�p����ٍ�.�ٍϋ敪�F�@@�����P��Row.�ٍϋ敪
        //�@@�@@�@@�@@�M�p����ٍ�.�ٍϊ����l�F�@@�����P��Row.�ٍϊ����l
        WEB3MarginRepaymentUnit l_repayment = null;
        if (!OrderCategEnum.ASSET.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = l_eqtypeOrderUnitRow.getRepaymentType();
            l_repayment.repaymentTimeLimit = String.valueOf(l_eqtypeOrderUnitRow.getRepaymentNum());
        }
        
        //�Q�Q�j�@@createUnit���X�|���X()�Ŏ擾�����A
        //�@@�@@�@@�@@�����蓮����Unit�̃v���p�e�B���Z�b�g����B  
        //      �@@�y���i���ʃv���p�e�B�z 
        //      �@@ID�F�@@�����P��.����ID 
        l_equityManualUnit.id = l_eqTypeOrderUnit.getOrderId() + "";
        
        //    ���X�R�[�h�F�@@getBranch()�̖߂�l.get���X�R�[�h() 
        l_equityManualUnit.branchCode = l_branch.getBranchCode();
            
        //    �ڋq�R�[�h�F�@@get�ڋq()�̖߂�l.get�ڋq�R�[�h() 
        l_equityManualUnit.accountCode = 
            l_mainAccount.getAccountCode().substring(0, 6);
        
        //    �s��R�[�h�F�@@get�s��()�̖߂�l.get�s��R�[�h() 
        l_equityManualUnit.marketCode = l_market.getMarketCode();
        
        //    �����R�[�h�F�@@��������Row.get�����R�[�h()
        l_equityManualUnit.productCode = l_eqtypeProductRow.getProductCode();
        
        //    �������F�@@��������Row.get������() 
        l_equityManualUnit.productName = l_eqtypeProductRow.getStandardName();
        
        //    ���i�敪�F�@@get���i�敪()�̖߂�l 
        l_equityManualUnit.productDiv = l_strProductType;
        
        //    ����敪�F�@@get����敪()�̖߂�l
        l_equityManualUnit.tradingType = l_strTradingType;
        
        //    ���s�����F�@@get���s����(SONAR)()�̖߂�l 
        l_equityManualUnit.execCondType = l_strExecutionConditionTypeSonar;
        
        //    ���������敪�F�@@is�o����܂Œ����P��()�̖߂�l��ture�̏ꍇ�A
        //        "�o����܂Œ���"���Z�b�g�B 
        //        �ȊO"��������"�A���Z�b�g�B
        l_equityManualUnit.expirationDateType = l_blnIsCarriedOrderUnit ? 
            WEB3OrderExpirationDateTypeDef.CARRIED_ORDER :
            WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        
        //    �����L�������F�@@is�o����܂Œ����P��()�̖߂�l��ture�̏ꍇ�A
        //        �����P��.�����������t���Z�b�g�B
        if (l_blnIsCarriedOrderUnit == true)
        {
            l_equityManualUnit.expirationDate = l_eqTypeOrderUnit.getExpirationTimestamp();
        }
        
        //   �������ʁF�@@�����P�ʁD�������� 
        l_equityManualUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(
            l_eqTypeOrderUnit.getQuantity());
        
        //   �����P���敪�F�@@�����P��.isMarketOrder()�̖߂�l��true�̏ꍇ�A"���s"���Z�b�g�B 
        //      �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false�̏ꍇ��"�w�l"���Z�b�g�B 
        l_equityManualUnit.orderPriceDiv = l_eqTypeOrderUnit.isMarketOrder() ?
            WEB3OrderPriceDivDef.MARKET_PRICE :
            WEB3OrderPriceDivDef.LIMIT_PRICE;
        
        //   �����P���F�@@�����P���敪��"�w�l"�̏ꍇ�A�����P��.�w�l���Z�b�g�B
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_equityManualUnit.orderPriceDiv))
        {
            l_equityManualUnit.limitPrice = WEB3StringTypeUtility.formatNumber(
                l_eqTypeOrderUnit.getLimitPrice());
        }
        
        //   ������ԋ敪�F�@@get������ԋ敪()�̖߂�l
        l_equityManualUnit.orderState = l_strOrderState;
        
        //   ����ԋ敪�F�@@get����ԋ敪()�̖߂�l 
        l_equityManualUnit.execType = l_strExecType;
        
        //   ��������敪�F�@@�����P��.���������E����敪
        l_equityManualUnit.changeCancelDiv = 
            l_eqtypeOrderUnitRow.getModifyCancelType();
        
        //   �������ԁF�@@�����P��.�󒍓���
        l_equityManualUnit.orderDate = 
            l_eqtypeOrderUnitRow.getReceivedDateTime();
        
        //   �������F�@@�����P��.������ 
        l_equityManualUnit.orderBizDate =
            WEB3DateUtility.getDate(l_eqtypeOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        //   ��n���F�@@�����P��.��n�� 
        l_equityManualUnit.deliveryDate =
            l_eqtypeOrderUnitRow.getDeliveryDate();
        
        //   �T�Z��n����F�@@�����P��.�T�Z��n��� 
        l_equityManualUnit.estimatedPrice = WEB3StringTypeUtility.formatNumber(
            l_eqtypeOrderUnitRow.getEstimatedPrice());
        
        //   ���Ϗ����F�@@�����P��.���Ϗ����敪 
        l_equityManualUnit.closingOrder = l_eqtypeOrderUnitRow.getClosingOrderType();
        
        //   �����敪�F�@@get�\���p�������̖߂�l.get�����敪()
        l_equityManualUnit.currentPriceDiv = l_equityProductQuote.getQuoteTypeDiv();
        
        //   �����i���ݒl�j�F�@@get�\���p�������̖߂�l.get����() 
        l_equityManualUnit.currentPrice = WEB3StringTypeUtility.formatNumber(
            l_equityProductQuote.getQuote());
        
        //   �O����F�@@get�\���p�������̖߂�l.get�O����() 
        l_equityManualUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(
            l_equityProductQuote.getComparedPreviousDay());
        
        //   ������ԁi�������\���ԁj�F�@@get�\���p�������()�̖߂�l.get�������\����() 
        l_equityManualUnit.currentPriceTime = l_equityProductQuote.getQuoteTime();

        //��񍀖ڂƂ��Ĉȉ���ݒ肷��
        //���ݒl�F�@@�擾�������������������.get���ݒl()
        l_equityManualUnit.boardCurrentPrice = l_equityProductQuote.getBoardCurrentPrice();

        //���ݒl�����F�@@�擾�������������������.get���ݒl����()
        l_equityManualUnit.boardCurrentPriceTime = l_equityProductQuote.getBoardCurrentPriceTime();

        //���ݒl�敪�F�@@�擾�������������������.get���ݒl�敪()
        l_equityManualUnit.boardCurrentPriceDiv = l_equityProductQuote.getBoardCurrentPriceDiv();

        //���ݒl�O����F�@@�擾�������������������.get���ݒl�O����()
        l_equityManualUnit.boardChange = l_equityProductQuote.getBoardChange();

        //�o�����F�@@�擾�������������������.get�o����()�̖߂�l���Z�b�g
        l_equityManualUnit.volume = l_equityProductQuote.getVolume();

        //�o���������F�@@�擾�������������������.get�o��������()
        l_equityManualUnit.volumeTime = l_equityProductQuote.getVolumeTime();

        //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()
        l_equityManualUnit.askPriceTitle = l_equityProductQuote.getAskPriceTitle();

        //���C�z�l�F�@@�擾�������������������.get���C�z�l()
        l_equityManualUnit.askPrice = l_equityProductQuote.getAskPrice();

        //���C�z�l�����F�@@�擾�������������������.get���C�z�l����()
        l_equityManualUnit.askPriceTime = l_equityProductQuote.getAskPriceTime();

        //���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪()
        l_equityManualUnit.bidPriceTitle = l_equityProductQuote.getBidPriceTitle();
 
        //���C�z�l�F�@@�擾�������������������.get���C�z�l()
        l_equityManualUnit.bidPrice = l_equityProductQuote.getBidPrice();

        //���C�z�l�����F�@@�擾�������������������.get���C�z�l����()
        l_equityManualUnit.bidPriceTime = l_equityProductQuote.getBidPriceTime();

        //��l�i�F�@@�擾�������������������.get��l�i()
        l_equityManualUnit.basePrice = l_equityProductQuote.getBasePrice();

        //�@@�@@�P�������l�F�@@null���Z�b�g�B
        l_equityManualUnit.priceAdjustmentValue = null;
        
        //   �y�����ŗL�̃v���p�e�B�z 
        //    �����敪�F�@@get�����敪()�̖߂�l 
        l_equityManualUnit.taxType = l_strTaxType;
        
        //    �l�i�����F�@@�����P��.�l�i���� 
        l_equityManualUnit.priceCondType = l_eqtypeOrderUnitRow.getPriceConditionType();
        
        //    �����󋵋敪�F�@@get�����󋵋敪()�̖߂�l 
        l_equityManualUnit.transactionStateType = l_strTransactionStatusType;
        
        //    �T�Z�뉿�P���F�@@�i*1�jcalc�T�Z�뉿�P��()�̖߂�l 
        l_equityManualUnit.estimatedBookPrice = l_estimatedBookPrice;
        
        //    �����F�@@�����P��.������ʂ�"�V�K��������"�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�i���X�s��ٍϕʁj�戵����.�����������Z�b�g�B
        if (OrderTypeEnum.MARGIN_LONG.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            l_equityManualUnit.interestRates =
                WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getBuyInterestRate());
        }
        //    �@@�@@�@@�@@�����P��.������ʂ�"�V�K��������"�̏ꍇ�A
        //�@@�@@�@@�@@�@@�@@�i���X�s��ٍϕʁj�戵����.�����������Z�b�g�B
        else if (OrderTypeEnum.MARGIN_SHORT.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            l_equityManualUnit.interestRates =
                WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getSellInterestRate());
        }
        
        //    ���Z���ԁF�@@�i*2�j�i���X�s��ٍϕʁj�戵����.�������o��Z����
        //    �@@�ȉ��̏����ɊY������ꍇ�̂݃Z�b�g�B
        //�@@�@@�@@�@@�@@�@@�E�����P��.������ʂ�"�V�K��������"�܂���"�V�K��������"
        //�@@�@@�@@�@@�@@�@@�E�����P��Row.�ٍϋ敪��"��ʐM�p"
        //�@@�@@�@@�@@�@@�@@�E�����P��Row.�ٍϊ����l��"9999999"�i��ALL9�j
        if ((OrderTypeEnum.MARGIN_LONG.equals(l_eqTypeOrderUnit.getOrderType()) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_eqTypeOrderUnit.getOrderType())) &&
            WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_eqtypeOrderUnitRow.getRepaymentType()) &&
            l_eqtypeOrderUnitRow.getRepaymentNum() == 9999999)
        {
            l_equityManualUnit.clearUpTerm =
                String.valueOf(l_branchMarketRepayDealtCondRow.getContLiquidateTerm());
        }
        
        //    �ٍρF�@@�M�p����ٍσI�u�W�F�N�g���Z�b�g�B
        l_equityManualUnit.repayment = l_repayment;
        
        //�Q�R�j�@@�v���p�e�B���Z�b�g���������蓮����Unit��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_equityManualUnit;
    }
    
    /**
     * (createUnit���X�|���X)
     * <BR>
     * �����蓮����Unit�𐶐����ĕԂ��B<BR>
     * @@return WEB3EquityManualUnit
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit createUnitResponse()
    {
        final String STR_METHOD_NAME = "createUnitResponse()";
        log.entering(STR_METHOD_NAME);
        
        //�����蓮����Unit�𐶐����ĕԂ��B
        WEB3EquityManualUnit l_equityManualUnit = new WEB3EquityManualUnit();

        log.exiting(STR_METHOD_NAME);
        return l_equityManualUnit;
    }

    /**
     * (create��������ByOrder)
     * <BR>
     * �����̒����P�ʂɊ֘A����M�p����������ׂ�  <BR>
     * �ꗗ���쐬����B  <BR>
     * <BR>
     * �g�����������}�l�[�W��.create��������ByOrder(�����P�ʁD�����P��ID)��  <BR>
     * delegate����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)
     * @@return WEB3MarginContractUnit[]
     * @@roseuid 432175DD01A0
     */
    protected WEB3MarginContractUnit[] createContractUnitByOrder(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnitByOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }
        
        //�g�����������}�l�[�W��.create����ByOrder(�����P�ʁD�����P��ID)��  
        //delegate����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager(); 
        
        WEB3MarginContractUnit[] l_marginContractUnits = 
            l_orderManager.createContractUnitByOrder(l_orderUnit.getOrderUnitId());

        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }
    
    /**
     * (get�G���[Unit���X�|���X)
     * <BR>
     * �����̃G���[�R�[�h�������蓮����Unit�ɃZ�b�g���A�Ԃ��B <BR>
     * <BR>
     * �P�j�@@�����蓮����Unit�𐶐�����B <BR>
     * �@@�@@�@@this.createUnit���X�|���X()���R�[������B <BR>
     * <BR>
     * �Q�j�@@�P�j�Ő��������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�@@�@@�蓮�����G���[�R�[�h �F�@@�p�����[�^.�蓮�����G���[�R�[�h <BR>
     * <BR>
     * �@@�@@�@@�@@������ȊO�̍��ځF�@@null<BR>
     * <BR>
     * @@param l_strManualErrorCode - (�蓮�����G���[�R�[�h)
     * @@return WEB3EquityManualUnit
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit getErrorUnitResponse(String l_strManualErrorCode)
    {
        final String STR_METHOD_NAME = "getErrorUnitResponse(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����蓮����Unit�𐶐�����B 
        //this.createUnit���X�|���X()���R�[������B
        WEB3EquityManualUnit l_equityManualUnit = this.createUnitResponse();

        //�Q�j�@@�P�j�Ő��������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B 

        //�@@�@@�@@�@@�蓮�����G���[�R�[�h �F�@@�p�����[�^.�蓮�����G���[�R�[�h 
        //�@@�@@�@@�@@������ȊO�̍��ځF�@@null
        l_equityManualUnit.manualOrderErrorCode = l_strManualErrorCode;        
        
        log.exiting(STR_METHOD_NAME);
        return l_equityManualUnit;
    }
    
    /**
     * (sendRLSRequest)
     * ���ۃ��\�b�h�iabstract�j
     * <BR>
     * @@param l_orderData - (�����f�[�^)
     * @@param l_submitterLoginId - (�����҃��O�C��ID)
     * @@param l_strNotifyType - (�ʒm�o�H)
     * @@roseuid 432175DD01A0
     */
    protected abstract void sendRLSRequest(
        EqTypeOrderUnit l_orderData, 
        Long l_submitterLoginId, 
        String l_strNotifyType) throws WEB3BaseException;
}
@
