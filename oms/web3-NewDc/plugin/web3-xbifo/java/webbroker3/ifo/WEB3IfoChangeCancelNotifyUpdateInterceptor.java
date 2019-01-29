head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP��������ʒm�X�V�C���^�Z�v�^(WEB3IfoChangeCancelNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/11 Ḗ@@�� (���u) �V�K�쐬
Revesion History : 2004/07/23 ���Ō� (���u) WEB3ModifyCancelTypeDef��WEB3IfoModifyCancelTypeDef�������ւ���
Revesion History : 2006/07/12 �s�p (���u) DB�X�V�d�lNo.085, 099
Revesion History : 2006/11/29 ���� (���u) DB�X�V�d�lNo.124�A125�A126�A127
Revesion History : 2007/01/25 �����F (���u) ���f��No.611 DB�X�V�d�lNo.145�A153
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.define.WEB3IfoCanmodReceiptTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;

/**
 * (�敨OP��������ʒm�X�V�C���^�Z�v�^)<BR>
 * �敨OP��������ʒm�X�V�C���^�Z�v�^�N���X<BR>
 * @@author  Ḗ@@��
 * @@version 1.0
 */
public class WEB3IfoChangeCancelNotifyUpdateInterceptor extends WEB3IfoUpdateInterceptor
{
    /**
      * ���O�o�̓��[�e�B���e�B�B
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelNotifyUpdateInterceptor.class);
    /**
     * �����㐔��<BR>
     */
    private double changedQuantity;

    /**
     * ������w�l<BR>
     */
    private double changedLimitPrice;

    /**
     * �������s����<BR>
     */
    private IfoOrderExecutionConditionType changeExecCondType;

    /**
     * ����������ʃR�[�h<BR>
     * <BR>
     * DB���C�A�E�g<BR>
     * �u�敨OP��������ʒm�L���[�e�[�u���v�Q�ƁB<BR>
     */
    private String changeCancelledResultCode;

    /**
     * ��������ʒm�敪<BR>
     * <BR>
     * �@@1�F��������<BR>
     * �@@2�F�������s<BR>
     * �@@3�F�������<BR>
     * �@@4�F������s<BR>
     * �@@5�F�G���[<BR>
     *
     * DB���C�A�E�g
     * �u�敨OP��������ʒm�L���[�e�[�u���v�Q�ƁB
     */
    private String changeCancelNotifyDivision;

    /**
     * �v�Z�P��<BR>
     */
    private double computerUnitPrice;

    /**
     * �T�Z��n���<BR>
     */
    private double estimateDeliveryAmount;

    /**
     * (�����㔭���o�H�敪)<BR>
     * �����㔭���o�H�敪<BR>
     */
    private String modSubmitOrderRouteDiv;

    /**
     * (�����㒍��Rev.)<RB>
     * �����㒍��Rev.<BR>
     */
    private String modifiedOrderRev;

    /**
     * (����ʒm�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor
     * @@roseuid 4084C0EE025A
     */
    public WEB3IfoChangeCancelNotifyUpdateInterceptor()
    {

    }

    /**
     * (set�����㐔��)<BR>
     * �����㐔�ʂ��Z�b�g����B<BR>
     * @@param l_dblChangedQuantity - �����㐔��
     * @@roseuid 4084C10C03B2
     */
    public void setChangedQuantity(double l_dblChangedQuantity)
    {
        this.changedQuantity = l_dblChangedQuantity;
    }

    /**
     * (set������w�l)<BR>
     * ������w�l���Z�b�g����B<BR>
     * @@param l_dblChangedLimitPrice - ������w�l
     * @@roseuid 4084C14203E1
     */
    public void setChangedLimitPrice(double l_dblChangedLimitPrice)
    {
        this.changedLimitPrice = l_dblChangedLimitPrice;
    }

    /**
     * (set�����㎷�s����)<BR>
     * �����㎷�s�������Z�b�g����B<BR>
     * @@param l_execCondType - ���s����
     * @@roseuid 4084C16000C4
     */
    public void setChangedExecCondType(IfoOrderExecutionConditionType l_execCondType)
    {
        this.changeExecCondType = l_execCondType;
    }

    /**
     * (set����������ʃR�[�h)<BR>
     * ����������ʃR�[�h���Z�b�g����B<BR>
     * @@param l_strChangeCancelResultCode - ����������ʃR�[�h
     * @@roseuid 4084C195024A
     */
    public void setChangeCancelResultCode(String l_strChangeCancelResultCode)
    {
        this.changeCancelledResultCode = l_strChangeCancelResultCode;
    }

    /**
     * (set��������ʒm�敪)<BR>
     * ��������ʒm�敪���Z�b�g����B<BR>
     * @@param l_strChangeCancelNotifyDivision - ��������ʒm�敪
     * @@roseuid 4084CA6B0018
     */
    public void setChangeCancelNotifyDivision(String l_strChangeCancelNotifyDivision)
    {
        this.changeCancelNotifyDivision = l_strChangeCancelNotifyDivision;
    }

    /**
     * (set�v�Z�P��)<BR>
     * �v�Z�P�����Z�b�g����B<BR>
     * @@param l_dblComputerUnitPrice - �v�Z�P��
     * @@roseuid 4085F98901DB
     */
    public void setCalcUnitPrice(double l_dblComputerUnitPrice)
    {
        this.computerUnitPrice = l_dblComputerUnitPrice;
    }

    /**
     * (set�T�Z��n���)<BR>
     * �T�Z��n������Z�b�g����B<BR>
     * @@param l_dblEstimateDeliveryAmount - �T�Z��n���
     * @@roseuid 4085F9910036
     */
    public void setEstimateDeliveryAmount(double l_dblEstimateDeliveryAmount)
    {
        this.estimateDeliveryAmount = l_dblEstimateDeliveryAmount;
    }

    /**
     * (set�����㔭���o�H�敪)<BR>
     * �����㔭���o�H�敪���Z�b�g����B<BR>
     * @@param l_strModSubmitOrderRouteDiv - (�����㔭���o�H�敪)<BR>
     * �����㔭���o�H�敪<BR>
     */
    public void setModSubmitOrderRouteDiv(String l_strModSubmitOrderRouteDiv)
    {
        this.modSubmitOrderRouteDiv = l_strModSubmitOrderRouteDiv;
    }

    /**
     * (set�����㒍��Rev.)<BR>
     * �����㒍��Rev.���Z�b�g����B<BR>
     * @@param l_strModifiedOrderRev - (�����㒍��Rev.)<BR>
     * �����㒍��Rev.<BR>
     */
    public void setModifiedOrderRev(String l_strModifiedOrderRev)
    {
        this.modifiedOrderRev = l_strModifiedOrderRev;
    }

    /**
     * (get�����㐔��)<BR>
     * �����㐔�ʂ��擾����B<BR>
     * @@return double
     * @@roseuid 4084E05500A5
     */
    public double getChangedQuantity()
    {
        return this.changedQuantity;
    }

    /**
     * (get������w�l)<BR>
     * ������w�l���擾����B<BR>
     * @@return double
     * @@roseuid 4084E05500B4
     */
    public double getChangedLimitPrice()
    {
        return this.changedLimitPrice;
    }

    /**
     * (get�����㎷�s����)<BR>
     * �����㎷�s�������擾����B<BR>
     * @@return IfoOrderExecutionConditionType
     * @@roseuid 4084E05500B6
     */
    public IfoOrderExecutionConditionType getChangedExecCondType()
    {
        return this.changeExecCondType;
    }

    /**
     * (get����������ʃR�[�h)<BR>
     * ����������ʃR�[�h���擾����B<BR>
     * @@return double
     * @@roseuid 4084E05500B8
     */
    public String getChangeCancelResultCode()
    {
        return this.changeCancelledResultCode;
    }

    /**
     * (get��������ʒm�敪)<BR>
     * ��������ʒm�敪���擾����B<BR>
     * @@return double
     * @@roseuid 4084E05500E3
     */
    public String getChangeCancelNotifyDivision()
    {
        return this.changeCancelNotifyDivision;
    }

    /**
     * (get�����㔭���o�H�敪)<BR>
     * �����㔭���o�H�敪���擾����B<BR>
     * @@return String
     */
    public String getModSubmitOrderRouteDiv()
    {
        return this.modSubmitOrderRouteDiv;
    }

    /**
     * (get�����㒍��Rev.)<BR>
     * �����㒍��Rev.���擾����B<BR>
     * @@return String
     */
    public String getModifiedOrderRev()
    {
        return this.modifiedOrderRev;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     *�imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �����P��.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ<BR>
     * �@@�X�V���e�́A�uOP��������ʒm_�����P�ʃe�[�u��.xls�v��<BR>
     * �@@�uOP��������ʒm_�����P��ð��� DB�X�V[����]�v�V�[�g�A<BR>
     * �@@�uOP��������ʒm_�����P��ð��� DB�X�V[���]�v�V�[�g�Q�ƁB<BR>
     * �����P��.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ<BR>
     * �@@�X�V���e�́A�u�敨��������ʒm_�����P�ʃe�[�u��.xls�v��<BR>
     * �@@�u�敨��������ʒm_�����P��ð��� DB�X�V[����]�v�V�[�g�A<BR>
     * �@@�u�敨��������ʒm_�����P��ð��� DB�X�V[���]�v�V�[�g�Q�ƁB<BR>
     * @@param l_updateType
     * @@param l_dealing
     * @@param l_orderUnitParams
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40C9957D008C
     */
    public IfoOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_dealing,
        IfoOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            ".mutate(OrderManagerPersistenceType l_updateType," +
            " OrderManagerPersistenceContext l_dealing, " +
            "IfoOrderUnitParams l_orderUnitParams) ";

        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.debug("Enter the if path that l_orderUnitParams is null.");
            log.error(STR_METHOD_NAME,new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ STR_METHOD_NAME ));
            log.debug("Exit the if path that l_orderUnitParams is null.");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        try
        {
            //�iIfoOrderUnitParams��OP�����}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_opOrderMgr = (WEB3OptionOrderManagerImpl)
                l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit = 
                (IfoOrderUnit)l_opOrderMgr.getOrderUnit(l_orderUnitParams.getOrderUnitId());
            IfoOrderUnitRow l_orderUnitRow = 
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            IfoOrderUnitParams l_beforeOrderUnitParams = new IfoOrderUnitParams(l_orderUnitRow);

            String l_strChangeCancelNotifyDivision = getChangeCancelNotifyDivision();
            if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision)
                || WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strChangeCancelNotifyDivision))
            {
                log.debug("Enter the try path that l_orderUnitParams is not null.");
                log.entering(STR_METHOD_NAME);

                //(*1)OP�����}�l�[�W��.is�X�g�b�v�����ؑ֒�()��true�̏ꍇ�A�X�g�b�v�����ؑ֒��B
                //�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
                boolean l_blnIsStopOrderChanging = l_opOrderMgr.isStopOrderSwitching(l_orderUnit);

                //(*2)�X�g�b�v�����ؑ֒�(*1)�A���A
                //�敨OP��������ʒm�L���[�e�[�u��.��������ʒm�敪 == �h���������h�̏ꍇ�A
                //�X�g�b�v�����ؑ�OK�B
                boolean l_blnIsStopOrderChgOk = false;

                if (l_blnIsStopOrderChanging && 
                    WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
                {    
                    l_blnIsStopOrderChgOk = true;
                }

                //��������
                l_orderUnitParams.setQuantity(this.changedQuantity);                

                //�w�l
                l_orderUnitParams.setLimitPrice(this.changedLimitPrice);

                //���s����
                l_orderUnitParams.setExecutionConditionType(this.changeExecCondType);                                    

                //�s�ꂩ��m�F�ς݂̐���
                l_orderUnitParams.setConfirmedQuantity(this.changedQuantity);

                //�s�ꂩ��m�F�ς݂̎w�l
                l_orderUnitParams.setConfirmedPrice(this.changedLimitPrice);

                //�����P��
                l_orderUnitParams.setPrice(this.computerUnitPrice);

                //�T�Z��n���
                l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmount);

                // ���������E����敪
                if (WEB3ModifiedResultDef.ALL_CHANGED_NO_EXECUTED.equals(this.changeCancelledResultCode)
                    || WEB3ModifiedResultDef.ALL_CHANGED_PARTIALLY_EXECUTED.equals(this.changeCancelledResultCode))
                {
                    if (l_blnIsStopOrderChanging)
                    {
                        //�X�g�b�v�����ؑ֒�(*1)�ł���΁A�hC�FW�w�l�����S���ؑ֊����h
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED);
                    }
                    else
                    {
                        //�ȊO�A�h7�F�S�����������h
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
                    }
                }
                //�i����������ʺ��� == 05�F�ꕔ�����h�܂��́A
                // 08�F�ꕔ�����s�\�i���o���Ȃ��j�h�܂��́A
                // 09�F�ꕔ�����s�\�i���o������j�h�j�̏ꍇ
                else if ((WEB3ModifiedResultDef.PARTIALLY_CHANGED.equals(this.changeCancelledResultCode))
                    || (WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_NO_EXECUTED.equals(this.changeCancelledResultCode))
                    || (WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_PARTIALLY_EXECUTED.equals(this.changeCancelledResultCode)))
                {
                    if (l_blnIsStopOrderChanging)
                    {
                        //�X�g�b�v�����ؑ֒�(*1)�ł���΁A�hB�FW�w�l�����ꕔ�ؑ֊����h
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED);
                    }
                    else
                    {
                        //�ȊO�A�h6�F�ꕔ���������h
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
                    }
                }
                //��L�ȊO�̏ꍇ
                else
                {
                    log.debug("Enter the else path:��L�ȊO�̏ꍇ");
                    if (l_blnIsStopOrderChanging)
                    {
                        //�X�g�b�v�����ؑ֒�(*1)�ł���΁A�hD�FW�w�l�����ؑ֎��s�h
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);
                    }
                    else
                    {
                        // �ȊO�A�h8�F�������s�h
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGE_ERROR);
                    }                        

                    log.exiting("Exit the else path:��L�ȊO�̏ꍇ");
                }

                if (l_orderUnitParams.getPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                }

                // �s�ꂩ��m�F�ς݂̊T�Z��n���
                if (l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());

                //�敨OP��������ʒm�L���[�e�[�u��.��������ʒm�敪 == �h���������h�̏ꍇ
                if (WEB3IfoCanmodReceiptTypeDef.CHANGED_COMPLETE.equals(this.changeCancelNotifyDivision))
                {
                     //0000�F����
                    l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
                }

                //���N�G�X�g�^�C�v
                //�X�g�b�v�����ؑ֒�(*1)�̏ꍇ�A
                if (l_blnIsStopOrderChanging)
                {
                    // �|�敨OP��������ʒm�L���[�e�[�u��.��������ʒm�敪 == �h���������h�ł���΁A
                    if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
                    {
                        //�h2�F�ؑ֊����h
                        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.TRANSFERED);
                    }
                    // �|�敨OP��������ʒm�L���[�e�[�u��.��������ʒm�敪 == �h�������s�h�ł���΁A
                    else if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strChangeCancelNotifyDivision))
                    {
                        //�h5�F�����h
                        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
                    }

                    //��������
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�A0�FDEFAULT�i�����w��Ȃ��j
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                    //�����������Z�q
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrderCondOperator(null);

                    //�t�w�l��l�^�C�v
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setStopPriceType(null);

                    //�t�w�l��l
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setStopOrderPrice(null);

                    //�iW�w�l�j�����w�l
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setWLimitPrice(null);

                    //����������
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�A�X�V�O�̔�������
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrgOrderConditionType(
                        l_beforeOrderUnitParams.getOrderConditionType());

                    //�������������Z�q
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�A�X�V�O�̔����������Z�q
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrgOrderCondOperator(
                        l_beforeOrderUnitParams.getOrderCondOperator());

                    //���t�w�l��l�^�C�v
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�A�X�V�O�̋t�w�l��l�^�C�v
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrgStopPriceType(
                        l_beforeOrderUnitParams.getStopPriceType());

                    //���t�w�l��l
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�A�X�V�O�̋t�w�l��l
                    //�ȊO�A�i�����l�j
                    if (l_beforeOrderUnitParams.getStopOrderPriceIsNull())
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(
                            l_beforeOrderUnitParams.getStopOrderPrice());
                    }

                    //���iW�w�l�j�����w�l
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�A�X�V�O�́iW�w�l�j�����w�l
                    //�ȊO�A�i�����l�j
                    if (l_beforeOrderUnitParams.getWLimitPriceIsNull())
                    {
                        l_orderUnitParams.setOrgWLimitPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgWLimitPrice(
                            l_beforeOrderUnitParams.getWLimitPrice());
                    }

                    //���iW�w�l�j���s����
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�A�X�V�O�́iW�w�l�j���s����
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrgWLimitExecCondType(
                        l_beforeOrderUnitParams.getWLimitExecCondType());

                    //�iW�w�l�j���s����
                    //�X�g�b�v�����ؑ�OK(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setWLimitExecCondType(null);
                }
                //�ȊO�̏ꍇ�A�i�����l�j

				//�����L�����
				//�s��m�F�ςݐ��ʁI�������P��.��萔�ʂ̏ꍇ�A�I�[�v���ɐݒ�B
				if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
				{
					if(this.changedQuantity == l_orderUnitParams.getExecutedQuantity())
					{
						l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
					}else
					{
						l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
					}
					
				}

                if (l_blnIsStopOrderChgOk)
                {       
                    //�iW�w�l�j�֑ؑO�w�l
                    //�X�g�b�v�����ؑ�OK(*2�j�̏ꍇ�A�X�V�O�̎s�ꂩ��m�F�ς݂̎w�l
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setWLimitBeforeLimitPrice(
                        l_beforeOrderUnitParams.getConfirmedPrice());
                    
                    //�iW�w�l�j�֑ؑO���s����
                    //�X�g�b�v�����ؑ�OK(*2�j�̏ꍇ�A�X�V�O�̎s�ꂩ��m�F�ς݂̎��s����
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setWLimitBeforeExecCondType(
                        l_beforeOrderUnitParams.getConfirmedExecConditionType());
                }

                //�����o�H�敪
                //�敨OP��������ʒm�L���[�e�[�u��.�����㔭���o�H�敪��null�̏ꍇ�F
                //�@@�敨OP��������ʒm�L���[�e�[�u��.�����㔭���o�H�敪���Z�b�g�B
                //��L�ȊO�̏ꍇ�F�i�����l�j
                if (getModSubmitOrderRouteDiv() != null)
                {
                    l_orderUnitParams.setSubmitOrderRouteDiv(getModSubmitOrderRouteDiv());
                }

                //�s�ꂩ��m�F�ς݂̒���Rev.
                //�敨OP��������ʒm�L���[�e�[�u��.�����㒍��Rev.
                l_orderUnitParams.setConfirmedOrderRev(getModifiedOrderRev());
            }

            // ��������܂��͎�����s
            if (WEB3CanmodReceiptTypeDef.CANCEL.equals(l_strChangeCancelNotifyDivision)
                || WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_strChangeCancelNotifyDivision))
            {

                //�w�l
                l_orderUnitParams.setLimitPrice(this.changedLimitPrice);

                //�s�ꂩ��m�F�ς݂̐���
                l_orderUnitParams.setConfirmedQuantity(l_orderUnitParams.getQuantity());

                //���s����
                l_orderUnitParams.setExecutionConditionType(this.changeExecCondType);

                //�s�ꂩ��m�F�ς݂̎w�l
                if (l_orderUnitParams.getLimitPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedPrice(l_orderUnitParams.getLimitPrice());
                }

                if (WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(this.changeCancelledResultCode))
                {
                    //�T�Z��n���
                    l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmount);
                }

                //���������E����敪
                //����������ʺ���==�h01�F�S������h�̏ꍇ
                if (WEB3ModifiedResultDef.ALL_CANCEL.equals(this.changeCancelledResultCode))
                {
                    //�S���������
                    l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);

                }
                //�i����������ʺ��� == 04�F�ꕔ����h�܂��́A
                //06�F�ꕔ����s�\�i���o���Ȃ��j�h�܂��́A
                //07�F�ꕔ����s�\�i���o������j�h�j�̏ꍇ
                else if ((WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(this.changeCancelledResultCode))
                    || (WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_NO_EXECUTED.equals(this.changeCancelledResultCode))
                    || (WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_EXECUTED.equals(this.changeCancelledResultCode)))
                {
                    l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
                }
                //��L�ȊO�̏ꍇ
                else
                {
                    l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCEL_ERROR);
                }

                //�s�ꂩ��m�F�ς݂̎��s����
                l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());
                //�敨OP��������ʒm�L���[�e�[�u��.��������ʒm�敪 == ��������̏ꍇ

                if (WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(this.changeCancelNotifyDivision))
                {
                    //0000�F����
                    l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
                }

                String l_strWLimitEnableStatusDiv = 
                    WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
                //(*1)
                //�E�ʒm�L���[.��������ʒm�敪="������s"�̏ꍇ�A������s�B
                //�E�敨OP�f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()��"���~�b�g�����L��"�̏ꍇ�A
                //���~�b�g�����L���B
                //�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
                if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_strChangeCancelNotifyDivision)
                    && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
                {
                    //��������
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�A0�FDEFAULT�i�����w��Ȃ��j
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                    //�����������Z�q
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrderCondOperator(null);

                    //�t�w�l��l�^�C�v
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setStopPriceType(null);

                    //�t�w�l��l
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setStopOrderPrice(null);

                    //�iW�w�l�j�����w�l
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setWLimitPrice(null);

                    //����������
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�̔�������
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrgOrderConditionType(
                        l_beforeOrderUnitParams.getOrderConditionType());

                    //�������������Z�q
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�̔����������Z�q
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrgOrderCondOperator(
                        l_beforeOrderUnitParams.getOrderCondOperator());

                    //���t�w�l��l�^�C�v
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�̋t�w�l��l�^�C�v
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrgStopPriceType(
                        l_beforeOrderUnitParams.getStopPriceType());

                    //���t�w�l��l
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�̋t�w�l��l
                    //�ȊO�A�i�����l�j
                    if (l_beforeOrderUnitParams.getStopOrderPriceIsNull())
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(
                            l_beforeOrderUnitParams.getStopOrderPrice());
                    }

                    //���iW�w�l�j�����w�l
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�́iW�w�l�j�����w�l
                    //�ȊO�A�i�����l�j
                    if (l_beforeOrderUnitParams.getWLimitPriceIsNull())
                    {
                        l_orderUnitParams.setOrgWLimitPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgWLimitPrice(
                            l_beforeOrderUnitParams.getWLimitPrice());
                    }

                    //���iW�w�l�j���s����
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�́iW�w�l�j���s����
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setOrgWLimitExecCondType(
                        l_beforeOrderUnitParams.getWLimitExecCondType());

                    //�iW�w�l�j���s����
                    //������s�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                    //�ȊO�A�i�����l�j
                    l_orderUnitParams.setWLimitExecCondType(null);

                    if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitParams.getFutureOptionDiv()))
                    {
                        //�����P��
                        l_orderUnitParams.setPrice(this.computerUnitPrice);

                        //�T�Z��n���
                        l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmount);
                    }

                    //���N�G�X�g�^�C�v
                    l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

                }
                
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitParams.getFutureOptionDiv()))
                {
                    if (l_orderUnitParams.getPriceIsNull())
                    {
                        l_orderUnitParams.setConfirmedOrderPrice(null);
                    }
                    else
                    {
                        //�s�ꂩ��m�F�ς݂̒����P��
                        l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                    }
                }

                // �s�ꂩ��m�F�ς݂̊T�Z��n���
                if (l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                //�����o�H�敪
                //�敨OP��������ʒm�L���[�e�[�u��.�����㔭���o�H�敪��null�̏ꍇ�F
                //�敨OP��������ʒm�L���[�e�[�u��.�����㔭���o�H�敪���Z�b�g�B
                //��L�ȊO�̏ꍇ�F�i�����l�j
                if (getModSubmitOrderRouteDiv() != null)
                {
                    l_orderUnitParams.setSubmitOrderRouteDiv(getModSubmitOrderRouteDiv());
                }
            }
        }
        catch(Exception l_ex)
        {
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }

	/**                             
	 * (�X�V�l�ݒ�)<BR>                              
	 *�imutate���\�b�h�̎����j<BR>                               
	 * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>                               
	 * <BR>                             
	 * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>                              
	 * <BR>                             
	 * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
	 * �����̒����P��Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B                               
	 * <BR>
	 * 
	 * �Q�j�@@super.mutate(IfoOrderActionParams)���R�[������B
	 * 
	 * �R�j�@@xTrade�W�����ڂ̍X�V�d�l���J�X�^�}�C�Y����B
	 * �@@�@@��xTrade�W�������ł́A 
	 * �@@�@@���������s���ɂ͒�������ʒm���s���O�̒����P�ʂ̒l���ݒ肳��Ă��܂����߁B
	 *  
     * @@param l_updateType
     * @@param l_dealing
     * @@param l_orderActionParams
     * @@return webbroker3.ifo.data.l_orderActionParams
     * @@roseuid 40C9957D008C
     */
    public IfoOrderActionParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_dealing,
      IfoOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME =
            ".mutate(OrderManagerPersistenceType l_updateType," +
            " OrderManagerPersistenceContext l_dealing, " +
            "IfoOrderActionParams l_orderActionParams) ";

        log.entering(STR_METHOD_NAME);

        if (l_orderActionParams == null)
        {
            log.debug("Enter the if path that l_orderActionParams is null.");
            log.error(STR_METHOD_NAME,new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ STR_METHOD_NAME ));
            log.debug("Exit the if path that l_orderActionParams is null.");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

      long l_orderUnitID;
      l_orderUnitID = l_orderActionParams.getOrderUnitId();
      FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
      OrderUnit l_ifoOrderUnit = null;
      IfoOrderUnitParams l_params = null;
      try
      {
          l_ifoOrderUnit = l_finApp.getTradingModule(
              ProductTypeEnum.IFO).getOrderManager().getOrderUnit(l_orderUnitID);
          l_params = (IfoOrderUnitParams)l_ifoOrderUnit.getDataSourceObject();
      }
      catch (NotFoundException l_nfe)
      {
          log.error(STR_METHOD_NAME, l_nfe);
      }

      super.mutate(l_updateType, l_dealing, l_orderActionParams);

      l_orderActionParams.setPrice(l_params.getLimitPrice());
      l_orderActionParams.setQuantity(l_params.getQuantity());
      l_orderActionParams.setConfirmedQuantity(l_params.getConfirmedQuantity());
      l_orderActionParams.setConfirmedPrice(l_params.getConfirmedPrice());

      log.exiting(STR_METHOD_NAME);
      return l_orderActionParams;
    }
}
@
