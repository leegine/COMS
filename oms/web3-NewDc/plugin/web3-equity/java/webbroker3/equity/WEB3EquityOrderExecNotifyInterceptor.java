head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNotifyInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����o���ʒm�C���^�Z�v�^(WEB3EquityOrderExecNotifyInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 SRA���� �V�K�쐬
                 : 2006/11/03 �đo�g (���u) �c�a�X�V�d�lNo.181,���f��No.1040,���f��No.1026
                 : 2006/11/29 ������ (���u) ���f��No.1071,�c�a�X�V�d�lNo.189
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����o���ʒm�C���^�Z�v�^�j�B<BR>
 * <BR>
 * ���������o���ʒm���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR> 
 *�iEqTypeOrderManagerPersistenceEventInterceptor�̎����j
 * @@author  : SRA����
 * @@version : 1.0
 */
public class WEB3EquityOrderExecNotifyInterceptor extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyInterceptor.class);
    
    /**
     * (������)<BR>
     * �������B<BR>
     */
    private Date execTimestamp;
    
    /**
     * (���P��)<BR>
     * ���P���B<BR>
     */
    private double executedPrice;
    
    /**
     * (��芔��)<BR>
     * ��芔���B<BR>
     */
    private double execQuantity;
    
    /**
     * (�����o���ʒm�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * @@param l_dateExecutedTimestamp �i�������j<BR>
     * �y�����o���ʒm�L���[�e�[�u���z�������B<BR>
     * @@param l_dblExecutedPrice ���P��<BR>
     * ���P���B<BR>
     * @@param l_dblExecQuantity ��芔��<BR>
     * ��芔���B<BR>
     * @@return WEB3EquityOrderExecNotifyInterceptor
     */
    public WEB3EquityOrderExecNotifyInterceptor(
        Date l_dateExecutedTimestamp,
        double l_dblExecutedPrice, 
        double l_dblExecQuantity) 
    {
        this.execTimestamp  = l_dateExecutedTimestamp;
        this.executedPrice = l_dblExecutedPrice;
        this.execQuantity = l_dblExecQuantity;
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �������Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * �P�j�@@������i�����̏�����EqTypeOrderManagerPersistenceContext.UNDO_EXECUTION�j <BR>
     * �@@�@@�@@�̏ꍇ�́A���������ɏ������I������B <BR>
     * <BR>
     * �Q�j�@@�J�X�^�}�C�Y�ł̒l�Z�b�g���s���B <BR>
     * <BR>
     * �Q�|�P�j�@@���Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@�@@�����̊������Params.�����P��ID�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�|�Q�j�@@�������Params�̃v���p�e�B�ݒ�d�l���J�X�^�}�C�Y����B <BR>
     * <BR>
     * �@@�@@�@@���N���X�̃v���p�e�B�A�y�тQ�|�P�j�Ŏ擾���������P�ʃI�u�W�F�N�g���A <BR>
     * �@@�@@�@@�������Params�̃v���p�e�B��ݒ肷��B <BR>
     * <BR>
     * �@@�@@�@@�X�V���e��DB�ݒ�_���u�i���j�o���ʒm_���e�[�u��.xls�v�Q�ƁB <BR>
     * <BR>
     * �R�j�@@�����̊������Params��ԋp����B <BR>
     *      * <BR>
     * <BR>
     * @@param l_updateType - INSERT�܂��́AUPDATE�B <BR>
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B <BR>
     * <BR> 
     * @@param l_process - ����<BR>
     * �iEqTypeOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_orderExecParams - �������Params<BR>
     * ������肪�ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderExecutionParams
     */
    public EqtypeOrderExecutionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderExecutionParams l_orderExecParams) 
    {
        final String STR_METHOD_NAME = "WEB3EquityOrderExecNotifyInterceptor:mutate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@������i�����̏�����OrderManagerPersistenceContext.UNDO_EXECUTION�j
        //�@@�@@�@@�̏ꍇ�́A���������ɏ������I������B
        if (OrderManagerPersistenceContext.UNDO_EXECUTION.equals(l_process))
        {
            log.exiting(STR_METHOD_NAME);
            return l_orderExecParams;
        }
        
        //�Q�j�@@�J�X�^�}�C�Y�ł̒l�Z�b�g���s��
        //�Q�|�P�j�@@���Ώۂ̒����P�ʃI�u�W�F�N�g���擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderManager = (EqTypeOrderManager) l_tradingMod.getOrderManager();               

        OrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                l_orderExecParams.getOrderUnitId());
            l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {   
            log.error(STR_METHOD_NAME, l_nfe);   
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }

        //�Q�|�Q�j�@@�������Params�̃v���p�e�B�ݒ�d�l���J�X�^�}�C�Y����
        //��n�����Z�b�g
        l_orderExecParams.setDeliveryDate(l_orderUnitRow.getDeliveryDate());
        
        //���������Z�b�g
        l_orderExecParams.setExecTimestamp(this.execTimestamp);
        
        //���������Z�b�g
        l_orderExecParams.setBizDate(l_orderUnitRow.getBizDate());
        
        //���ʃR�[�h���Z�b�g
        l_orderExecParams.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());

        log.exiting(STR_METHOD_NAME);
        return l_orderExecParams;
    }    
    
    
    /**
     * (�X�V�l�ݒ�) <BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * ���T�Z��n����́A�����\�b�h���ōČv�Z�����l���Z�b�g����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j�@@�g�����ڃZ�b�g<BR>
     * <BR>
     * �@@�e�v���p�e�B����A<BR>
     * �@@�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�X�V���e�́A�ȉ���DB�ݒ�_�����Q�ƁB<BR>
     * �@@�E�u�i���j�o���ʒm_���������P�ʃe�[�u��.xls�v<BR>
     * �@@�E�u�i������j�o���ʒm_���������P�ʃe�[�u��.xls�v<BR>
     * <BR>
     * �Q�j�@@�T�Z��n������Čv�Z���A�p�����[�^.�����P��Params�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�|�P�j�ăI�[�v��(��)�������f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪��"���~�b�g�����L��" �̏ꍇ<BR>
     *�@@�@@�i���X�V�O.�����L�����=="�N���[�Y" ���X�V��.�����L�����=="�I�[�v��"�̏ꍇ�A�ăI�[�v���j<BR>
     * <BR>
     *�@@�@@�T�Z��n������Čv�Z����B<BR>
     * <BR>
     *�@@�@@�Q�|�P�|�P�j�⏕�������擾����B<BR>
     *�@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�⏕�������R�[�����A�⏕�������擾����B<BR>
     *�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�����̒����P��Params.����ID<BR>
     *�@@�@@�@@�@@�����̒����P��Params.�⏕����ID<BR>
     * <BR>
     *�@@�@@�Q�|�P�|�Q�j�X�g�b�v�����������̊T�Z��n������Čv�Z����B<BR>
     *�@@�@@�@@�g�����������}�l�[�W��.get�X�g�b�v�����������T�Z����v�Z����()���R�[������B<BR>
     *�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�����P��<BR>
     *�@@�@@�@@�@@�Q�|�P�|�P�j�Ŏ擾�����⏕����<BR>
     * <BR>
     * �Q�|�Q�jget�X�g�b�v�����������T�Z����v�Z����()�I��null�̏ꍇ<BR>
     * <BR>
     *�@@�@@�@@�@@�@@�ȉ��̒l�𒍕��P��Params�ɃZ�b�g���A�����P��Param��ԋp����B<BR>
     * <BR>
     *�@@�@@�@@�@@�@@�����P��Params.{�T�Z��n���,�s�ꂩ��m�F�ς݂̊T�Z��n���}�ɁA<BR>
     *�@@�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.get�T�Z��n������Z�b�g����B<BR>
     * <BR>
     *�@@�@@�@@�@@�@@�����P��Params.�o�����P���A�s�ꂩ��m�F�ς̒����P���p�ɁA<BR>
     *�@@�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.get�v�Z�P��()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * �Q�|�R�j�Q�|�Q�j�ȊO�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�Q�|�R�|�P�j�@@�萔���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�����v�Z�T�[�r�X.create�萔��(�����̒����P��Params.����ID)���R�[������B<BR>
     * <BR>
     * �ȉ��A�����ɃZ�b�g���钍���P��Params�Ƃ��āA<BR>
     * �u�P�j�@@�g�����ڃZ�b�g�v��̒����P��Params���g�p����B<BR>
     * <BR>
     * �@@�Q�|�R�|2�j�@@�T�Z��n������Čv�Z���A�����P��Params�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�g�����������}�l�[�W��.calc�T�Z��n���()���R�[������B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc�T�Z��n���()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g<BR>
     * �@@�@@�@@�w�l�F�@@�����P��Params.�w�l<BR>
     * �@@�@@�@@�iW�w�l)�����w�l�F�@@�����P��Params.�iW�w�l�j�����w�l<BR>
     * �@@�@@�@@�t�w�l��l�F�@@�����P��Params.�t�w�l��l <BR>
     * �@@�@@�@@���s�����F�@@�����P��Params.���s����<BR>
     * �@@�@@�@@�iW�w�l�j���s�����F�@@�����P��Params.�iW�w�l�j���s����<BR>
     * �@@�@@�@@�l�i�����F�@@�����P��Params.�l�i����<BR>
     * �@@�@@�@@���������F�@@�����P��Params.��������<BR>
     * �@@�@@�@@�m�F���擾�����F�@@null<BR>
     * �@@�@@�@@is�X�g�b�v�����L���F�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��(�����P��)<BR>
     * �@@�@@�@@�⏕�����F�@@�����P��Params.�o����ID, �⏕����ID�p�ɊY������⏕���� <BR>
     * �@@�@@�@@��������F�@@�����P��.getTradedProduct() <BR>
     * �@@�@@�@@�����F�@@�����P��Params.�s�ꂩ��m�F�ς݂̐���<BR>
     * �@@�@@�@@is�������F�@@�g�����������}�l�[�W��.is������(�����P��)<BR>
     * �@@�@@�@@��萔�ʁF�@@�����P��Params.��萔��<BR>
     * �@@�@@�@@���v�����z�F�@@�����P��Params.���v�����z<BR>
     * �@@�@@�@@isSkip���z�`�F�b�N�F�@@true�i������z�`�F�b�N�Ȃ��j<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�����P��Params.�o�T�Z��n����A�s�ꂩ��m�F�ς̊T�Z��n����p�ɁA<BR>
     * �@@�@@calc�T�Z��n���()�̖߂�l�I�u�W�F�N�g.get�T�Z��n���()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�����P��Params.�o�����P���A�s�ꂩ��m�F�ς̒����P���p�ɁA<BR>
     * �@@�@@calc�T�Z��n���()�̖߂�l�I�u�W�F�N�g.get�v�Z�P��()�̖߂�l���Z�b�g����B<BR>
     * �@@�@@�i�������P��/�s�ꂩ��m�F�ς݂̒����P���Z�b�g�́A�S�����ȊO�i�����^�ꕔ���j�̂ݎ��s�B�j<BR>
     * <BR>
     * �R�j�@@�v���p�e�B���Z�b�g���������P��Params��ԋp����B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - INSERT�܂��́AUPDATE�B <BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����) <BR>
     *�iOrderManagerPersistenceContext�ɂĒ萔��`�j <BR>
     * <BR>
     * @@param l_eqtypeOrderUnitParams - (�����P��Params) <BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B <BR>
     * @@return EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //------------------------
        // �p�����[�^null�`�F�b�N
        //------------------------
        if (l_eqtypeOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �����P�ʎ擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderManager =
            (EqTypeOrderManager)l_tradingMod.getOrderManager();
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_eqtypeOrderUnitParams);
        WEB3EquityOrderManager l_eqtypeOrderManager = (WEB3EquityOrderManager)l_orderManager;
        //is�X�g�b�v�����L��
        boolean l_blnStopOrderValid = false;
        //is������
        boolean l_blnSellOrder = false;
        try
        {
            l_blnStopOrderValid = l_eqtypeOrderManager.isStopOrderValid(l_orderUnit);
            l_blnSellOrder = l_eqtypeOrderManager.isSellOrder(l_orderUnit);
        }
        catch (WEB3BaseException l_exc)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }

        //�X�V��.�����L�����
        OrderOpenStatusEnum l_orderOpenStatusAftUpdate =
            l_eqtypeOrderUnitParams.getOrderOpenStatus();
        //�X�V�O�̒����P��
        EqTypeOrderUnit l_orderUnitBefUpdate = null;
        try
        {
            l_orderUnitBefUpdate =
                (EqTypeOrderUnit)l_eqtypeOrderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        //�X�V�O.�����L�����
        OrderOpenStatusEnum l_orderOpenStatusBefUpdate =
            l_orderUnitBefUpdate.getOrderOpenStatus();
        //�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()
        String l_strWLimitEnableStatusDiv = null;
        try
        {
            l_strWLimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnitBefUpdate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // ������̏ꍇ
        if (OrderManagerPersistenceContext.UNDO_EXECUTION.equals(l_orderManagerPersistenceContext))
        {            
            //---------------------------
            //�w�l�^�s�ꂩ��m�F�ς݂̎w�l
            //---------------------------
            //�l�i������"���s�c���w�l" ����
            //��萔��==0�i���Ȃ��ƂȂ������j�̏ꍇ�F0
            //��L�ȊO�̏ꍇ�F�@@�i�����l�j
            if (
                WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER
                    .equals(l_eqtypeOrderUnitParams.getPriceConditionType()) &&
                l_eqtypeOrderUnitParams.getExecutedQuantity() == 0)
            {
                // �w�l
                l_eqtypeOrderUnitParams.setLimitPrice(0.0D);
                // �s�ꂩ��̊m�F�ς݂̎w�l
                l_eqtypeOrderUnitParams.setConfirmedPrice(0.0D);
            }

            //�@@(*1)�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()��"���~�b�g�����L��"�̏ꍇ�A���~�b�g�����L���B
            //�@@�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
            //�@@�iEqtypeOrderUnitParams���g�����������}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
            //�@@(*2)�X�V�O.�����L�����=="�N���[�Y" ���X�V��.�����L�����=="�I�[�v��"�̏ꍇ�A�ăI�[�v���B
            //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv)
                && OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusBefUpdate)
                && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusAftUpdate))
            {
                //����������     org_order_condition_type
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F �X�V�O�̔�������
                //�ȊO�̏ꍇ�F�i�����l�j
                l_eqtypeOrderUnitParams.setOrgOrderConditionType(
                    l_eqtypeOrderUnitParams.getOrderConditionType());

                //�������������Z�q      org_order_cond_operator
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F �X�V�O�̔����������Z�q
                //�ȊO�̏ꍇ�F�i�����l�j
                l_eqtypeOrderUnitParams.setOrgOrderCondOperator(
                    l_eqtypeOrderUnitParams.getOrderCondOperator());

                //���t�w�l��l       org_stop_order_price
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F �X�V�O�̋t�w�l��l
                //�ȊO�̏ꍇ�F�i�����l�j
                if (l_eqtypeOrderUnitParams.getStopOrderPriceIsNull())
                {
                    l_eqtypeOrderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setOrgStopOrderPrice(
                        l_eqtypeOrderUnitParams.getStopOrderPrice());
                }

                //���iW�w�l�j�����w�l        org_w_limit_price
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F �X�V�O�́iW�w�l�j�����w�l
                //�ȊO�̏ꍇ�F�i�����l�j
                if (l_eqtypeOrderUnitParams.getWLimitPriceIsNull())
                {
                    l_eqtypeOrderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setOrgWLimitPrice(
                        l_eqtypeOrderUnitParams.getWLimitPrice());
                }

                //���iW�w�l�j���s����    org_w_limit_exec_cond_type
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F �X�V�O�́iW�w�l�j���s����
                //�ȊO�̏ꍇ�F�i�����l�j
                l_eqtypeOrderUnitParams.setOrgWLimitExecCondType(
                    l_eqtypeOrderUnitParams.getWLimitExecCondType());

                //�iW�w�l�j���s����        w_limit_exec_cond_type
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F null
                //�ȊO�̏ꍇ�F�i�����l�j
                l_eqtypeOrderUnitParams.setWLimitExecCondType(null);

                //��������   order_condition_type
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F�@@0:DEFAULT
                //�ȊO�̏ꍇ�F�i�����l�j
                l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //�����������Z�q   order_cond_operator
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F�@@null
                //�ȊO�̏ꍇ�F�i�����l�j
                l_eqtypeOrderUnitParams.setOrderCondOperator(null);

                //�t�w�l��l       stop_order_price
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F�@@null
                //�ȊO�̏ꍇ�F�i�����l�j
                l_eqtypeOrderUnitParams.setStopOrderPrice(null);

                //�iW�w�l�j�����w�l      w_limit_price
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F�@@null
                //�ȊO�̏ꍇ�F�i�����l�j
                l_eqtypeOrderUnitParams.setWLimitPrice(null);

                //���N�G�X�g�^�C�v        request_type
                //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ�F�@@5:����
                //�ȊO�̏ꍇ�F�i�����l�j
                l_eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }
        }
        // ���̏ꍇ
        else
        {
            //---------------------------
            //�w�l�^�s�ꂩ��m�F�ς݂̎w�l
            //---------------------------
            //�l�i������"���s�c���w�l"
            //���� ������i��萔��==�C���^�Z�v�^�̃v���p�e�B.��芔���j
            //���� �ꕔ�o���i�����P��.isPartiallyExecuted( )==true�j�̏ꍇ�F
            //�@@�C���^�Z�v�^�̃v���p�e�B.���P���B
            //��L�ȊO�̏ꍇ�F�@@�i�����l�j
            if (WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(l_eqtypeOrderUnitParams.getPriceConditionType()) &&
                l_orderUnit.isMarketOrder() &&
                l_orderUnit.isPartiallyExecuted())
            { 
                // �w�l
                l_eqtypeOrderUnitParams.setLimitPrice(this.executedPrice);
                // �s�ꂩ��m�F�ς݂̎w�l
                l_eqtypeOrderUnitParams.setConfirmedPrice(this.executedPrice);
            }

            // ���������E����敪
            l_eqtypeOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        }

        WEB3EquityEstimatedPrice l_stopOrderExpireEstimatedPrice = null;
        //�@@�Q�|�P�j�ăI�[�v��(��)�������f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪��"���~�b�g�����L��" �̏ꍇ
        //�@@�@@�i���X�V�O.�����L�����=="�N���[�Y" ���X�V��.�����L�����=="�I�[�v��"�̏ꍇ�A�ăI�[�v���j
        //
        //�@@�@@�T�Z��n������Čv�Z����B
        if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv)
            && OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusBefUpdate)
            && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusAftUpdate))
        {
            //�@@�@@�Q�|�P�|�P�j�⏕�������擾����B
            //�@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�⏕�������R�[�����A�⏕�������擾����B
            //�@@�@@�@@[����]
            //�@@�@@�@@�@@�����̒����P��Params.����ID
            //�@@�@@�@@�@@�����̒����P��Params.�⏕����ID
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount =
                    l_accountManager.getSubAccount(
                        l_eqtypeOrderUnitParams.getAccountId(),
                        l_eqtypeOrderUnitParams.getSubAccountId());
            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            //�@@�@@�Q�|�P�|�Q�j�X�g�b�v�����������̊T�Z��n������Čv�Z����B
            //�@@�@@�@@�g�����������}�l�[�W��.get�X�g�b�v�����������T�Z����v�Z����()���R�[������B
            //�@@�@@�@@[����]
            //�@@�@@�@@�@@�����P��
            //�@@�@@�@@�@@�Q�|�P�|�P�j�Ŏ擾�����⏕����
            try
            {
                l_stopOrderExpireEstimatedPrice =
                    l_eqtypeOrderManager.getStopOrderExpireEstimatedPrice(
                        l_orderUnit,
                        l_subAccount);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //�@@�Q�|�Q�jget�X�g�b�v�����������T�Z����v�Z����()�I��null�̏ꍇ
        //
        //�@@�@@�@@�@@�@@�ȉ��̒l�𒍕��P��Params�ɃZ�b�g���A�����P��Param��ԋp����B
        //
        //�@@�@@�@@�@@�@@�����P��Params.{�T�Z��n���,�s�ꂩ��m�F�ς݂̊T�Z��n���}�ɁA
        //�@@�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.get�T�Z��n������Z�b�g����B
        //
        //�@@�@@�@@�@@�@@�����P��Params.�o�����P���A�s�ꂩ��m�F�ς̒����P���p�ɁA
        //�@@�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.get�v�Z�P��()�̖߂�l���Z�b�g����B
        if (l_stopOrderExpireEstimatedPrice != null)
        {
            l_eqtypeOrderUnitParams.setEstimatedPrice(
                l_stopOrderExpireEstimatedPrice.getEstimateDeliveryAmount());
            l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(
                l_stopOrderExpireEstimatedPrice.getEstimateDeliveryAmount());
            l_eqtypeOrderUnitParams.setPrice(
                l_stopOrderExpireEstimatedPrice.getCalcUnitPrice());
            l_eqtypeOrderUnitParams.setConfirmedOrderPrice(
                l_stopOrderExpireEstimatedPrice.getCalcUnitPrice());

            log.exiting(STR_METHOD_NAME);
            return l_eqtypeOrderUnitParams;
        }

        //�@@�Q�|�R�j�Q�|�Q�j�ȊO�̏ꍇ�A�ȉ��̏������s���B
        // �T�Z��n����̍Čv�Z���s��
        try
        {
	        WEB3EquityBizLogicProvider l_bizLogic =
	            (WEB3EquityBizLogicProvider)l_tradingMod.getBizLogicProvider();
	        // �萔���I�u�W�F�N�g�̍쐬
	        WEB3GentradeCommission l_commission =
	            l_bizLogic.createCommission(l_eqtypeOrderUnitParams.getOrderId());
	        
            // �⏕�����̎擾
            WEB3GentradeAccountManager l_accManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accManager.getSubAccount(
                    l_eqtypeOrderUnitParams.getAccountId(),
                    l_eqtypeOrderUnitParams.getSubAccountId());

            // �T�Z��n����̍Čv�Z
            WEB3EquityEstimatedDeliveryPrice l_calcResult =
                l_eqtypeOrderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_eqtypeOrderUnitParams.getLimitPrice(),
                    l_eqtypeOrderUnitParams.getWLimitPrice(),
                    l_eqtypeOrderUnitParams.getStopOrderPrice(),
                    l_eqtypeOrderUnitParams.getExecutionConditionType(),
                    l_eqtypeOrderUnitParams.getWLimitExecCondType(),
                    l_eqtypeOrderUnitParams.getPriceConditionType(),
                    l_eqtypeOrderUnitParams.getOrderConditionType(),
                    null,
                    l_blnStopOrderValid,
                    l_subAccount,
                    (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct(),
                    l_eqtypeOrderUnitParams.getConfirmedQuantity(),
                    l_blnSellOrder,
                    l_eqtypeOrderUnitParams.getExecutedQuantity(),
                    l_eqtypeOrderUnitParams.getExecutedAmount(),
                    true);

            // �T�Z��n����A�s�ꂩ��m�F�ς̊T�Z��n����̃Z�b�g
            l_eqtypeOrderUnitParams.setEstimatedPrice(l_calcResult.getEstimateDeliveryAmount());
            l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(l_calcResult.getEstimateDeliveryAmount());

            if (!l_orderUnit.isFullyExecuted())
            {
                //�����P���A�s�ꂩ��m�F�ς̒����P��
                l_eqtypeOrderUnitParams.setPrice(l_calcResult.getCalcUnitPrice());
                l_eqtypeOrderUnitParams.setConfirmedOrderPrice(l_calcResult.getCalcUnitPrice());
            }
        }
        catch (WEB3BaseException l_be)
        {
            throw new WEB3BaseRuntimeException(
                l_be.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_be);
        }
        catch (Exception l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }
}@
