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
filename	WEB3EquityChangeOrderUnitEntry.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������l�ڍ�(WEB3EquityChangeOrderUnitEntry.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/19 ����� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/01 ��іQ (���u) ���f�� No.995
                   2006/11/21 �đo�g (���u) ���f�� No.1068
*/
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderRequestAdapter;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������l�ڍׁj�B<BR>
 * <BR>
 * �������͓��e�̏ڍׁiEqTypeChangeOrderUnitEntry�j��\������B
 * @@version 1.0
 */
public class WEB3EquityChangeOrderUnitEntry extends EqTypeChangeOrderUnitEntry
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderUnitEntry.class);

    /**
     * (�����㎷�s����)<BR>
     * �iafterChangeExecutionConditionType�j<BR>
     * ������̎��s�����B<BR>
     */
    private EqTypeExecutionConditionType afterChangeExecutionConditionType;

    /**
     * (�����P��)<BR>
     * �����Ώۂ̒����P�ʃI�u�W�F�N�g<BR>
     */
    private OrderUnit orderUnit;

    /**
     * (������is�o����܂Œ���)<BR>
     * �������͓��e���A�o����܂Œ����ł��邩�ǂ����̃t���O�B<BR>
     * �o����܂Œ����ł����true�A�ȊO��false�B<BR>
     */
    private boolean changeAfterIsOrderUntilDeadLine;

    /**
     * (������l�i����)<BR>
     * �������͂̒l�i�����B<BR>
     */
    private String changeAfterPriceConditionType;

    /**
     * (�����㔭������)<BR>
     * �������͂̔��������B<BR>
     */
    private String changeAfterOrderCondType;

    /**
     * (�����㔭���������Z�q)<BR>
     * �������͂̔����������Z�q�B<BR>
     */
    private String changeAfterOrderCondOperator;

    /**
     * (������t�w�l��l)<BR>
     * �������͂̋t�w�l��l�B<BR>
     */
    private double changeAfterStopOrderCondPriceBasePrice;

    /**
     * (������iW�w�l�j�����w�l)<BR>
     * �������͂́iW�w�l�j�����w�l�B<BR>
     */
    private double changeAfterWlimitOrderCondPrice;

    /**
     * (�����㒍��������)<BR>
     * ������̒����������i�����L�������j�B<BR>
     */
    private Date modifiedExpirationDate;

    /**
     * (������iW�w�l�j���s����)<BR>
     * ������iW�w�l�j���s����<BR>
     */
    private EqTypeExecutionConditionType modifiedWlimitExecCondType;

    /**
     * (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪<BR>
     */
    private String wlimitEnableStatusDiv;

    /**
     * @@roseuid 40A9D29D0053<BR>
     */
    public WEB3EquityChangeOrderUnitEntry(
        long l_lngOrderUnitId, 
        double l_dblAfterChangeOrigQuantity, 
        double l_dblAfterChangePrice)
    {
        super(l_lngOrderUnitId, l_dblAfterChangeOrigQuantity, l_dblAfterChangePrice);
    }

    /**
     * (get�����㎷�s����)<BR>
     * �igetAfterChangeExecutionConditionType�j<BR>
     * ������̎��s�������擾����B<BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@roseuid 4018ED550233<BR>
     */
    public EqTypeExecutionConditionType getAfterChangeExecutionConditionType()
    {
        return this.afterChangeExecutionConditionType;
    }

    /**
     * (create�������������l�ڍ�)<BR>
     * ���������l�ڍ׃I�u�W�F�N�g�𐶐����A<BR>
     * ���N�G�X�g�A�_�v�^�̓��e���v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�����Ώے����P�ʎ擾<BR>
     * �@@���N�G�X�g�A�_�v�^.get����ID()�ɂāA�����������̒���ID���擾����B<BR>
     * �@@�擾��������ID�ɊY�����钍���I�u�W�F�N�g���擾����B<BR>
     * �@@�擾���������I�u�W�F�N�g.getOrderUnits()�ɂĎ擾���������P�ʃI�u�W�F�N�g�̂����A<BR>
     * �@@0�Ԗڂ̗v�f���擾����B<BR>
     * <BR>
     * �Q�j�@@�C���X�^���X����<BR>
     * �@@�X�[�p�[�N���X�̃R���X�g���N�^�iEqTypeChangeOrderUnitEntry�j���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@orderUnitId�F�@@�P�j�Ŏ擾���������I�u�W�F�N�g.�����P��ID�B<BR>
     * �@@afterChangeOrigQuantity�F�@@���N�G�X�g�A�_�v�^.get�����㊔��<BR>
     * �@@afterChangePrice�F�@@���N�G�X�g�A�_�v�^.get������P��<BR>
     * <BR>
     * �R�j�@@�g�����ڃZ�b�g<BR>
     * �@@���g�̃C���X�^���X�Ɉȉ��̒ʂ�Z�b�g����B<BR>
     * <BR>
     * �@@�|�����㎷�s�����F�@@���N�G�X�g�A�_�v�^.get���s����( )<BR>
     * �@@�|�����P�ʁF�@@�P�j�Ŏ擾���������P�ʃI�u�W�F�N�g<BR>
     * �@@�|������is�o����܂Œ����F�@@���N�G�X�g�A�_�v�^.is�o����܂Œ���( )<BR>
     * �@@�|������l�i�����F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�l�i����<BR>
     * �@@�|�����㔭�������F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���������敪<BR>
     * �@@�|�����㒍���������F�@@���N�G�X�g�A�_�v�^.get�����㒍��������( )<BR>
     * <BR>
     * �@@���R�[�h�ǂݑւ������K�v�ȍ��ڂ́A���N�G�X�g�A�_�v�^��getter���g�p���ăZ�b�g�B<BR>
     * �@@����L���K�v�Ȃ����ڂ́A���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^�̓����ڂ����̂܂܃Z�b�g�B<BR>
     * <BR>
     * �ȉ��́A���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.���������敪 �ɂ��ݒ�d�l���قȂ�B<BR>
     * (1)���������敪���h�w��Ȃ��h�̏ꍇ<BR>
     * �@@�|�����㔭���������Z�q�F�@@null<BR>
     * �@@�|������t�w�l��l�F�@@0<BR>
     * �@@�|������iW�w�l�j�����w�l�F�@@0<BR>
     * �@@�|������iW�w�l�j���s�����F�@@null <BR>
     * �@@�|�iW�w�l�j�L����ԋ敪�F�@@null<BR>
     * <BR>
     * (2)���������敪���h�t�w�l�h�̏ꍇ<BR>
     * �@@�|�����㔭���������Z�q�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�t�w�l�p�����������Z�q<BR>
     * �@@�|������t�w�l��l�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�t�w�l�p���������P�� ��double�ɕϊ������l<BR>
     * �@@�|������iW�w�l�j�����w�l�F�@@0<BR>
     * �@@�|������iW�w�l�j���s�����F�@@null<BR>
     * �@@�|�iW�w�l�j�L����ԋ敪�F�@@null <BR>
     * <BR>
     * (3)���������敪���hW�w�l�h�̏ꍇ<BR>
     * �@@�|�����㔭���������Z�q�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.W�w�l�p�����������Z�q<BR>
     * �@@�|������t�w�l��l�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.W�w�l�p���������P�� ��double�ɕϊ������l<BR>
     * �@@�|������iW�w�l�j�����w�l�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.W�w�l�p�����P�� ��double�ɕϊ������l�B<BR>
     * �@@�@@�@@�����N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.W�w�l�p�����P���敪=="���s"�̏ꍇ�́A0���Z�b�g�B<BR>
     * �@@�|������iW�w�l�j���s�����F�@@���N�G�X�g�A�_�v�^.get�iW�w�l�j���s����() <BR>
     * �@@�|�iW�w�l�j�L����ԋ敪�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪<BR>
     * <BR>�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@
     * @@param l_requestAdaptor - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return WEB3EquityChangeOrderUnitEntry<BR>
     * @@roseuid 4021DBBD01C1<BR>
     */
    public static WEB3EquityChangeOrderUnitEntry createChangeOrderUnitEntry(
        WEB3EquityChangeOrderRequestAdapter l_requestAdaptor) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createChangeOrderUnitEntry(WEB3EquityChangeOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        //�����Ώے���ID�擾
        long l_lngOrderId = l_requestAdaptor.getRequestOrderId();

        //�擾��������ID�ɊY�����钍���I�u�W�F�N�g���擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        Order l_order = null;
        try
        {
            l_order = l_tradingMod.getOrderManager().getOrder(l_lngOrderId);
        }
        catch (NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityChangeOrderUnitEntry.class + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }

        //get�����P��
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        OrderUnit l_orderUnit = l_orderUnits[0];

        //�C���X�^���X����
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            new WEB3EquityChangeOrderUnitEntry(
                l_orderUnit.getOrderUnitId(), 
                l_requestAdaptor.getRequestOrderQuantity(), 
                l_requestAdaptor.getRequestLimitPrice());

        //set�����㎷�s����
        l_changeOrderUnitEntry.afterChangeExecutionConditionType = l_requestAdaptor.getExecCondType();

        //set�����P��
        l_changeOrderUnitEntry.orderUnit = l_orderUnit;

        //set������is�o����܂Œ���
        l_changeOrderUnitEntry.changeAfterIsOrderUntilDeadLine = 
            l_requestAdaptor.isOrderUntilDeadLine();    

        //set������l�i����
        //set�����㔭������
        String l_strPriceConditionType = null;
        String l_orderCondType = null;
        if(l_requestAdaptor.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            WEB3EquityChangeCompleteRequest l_equityChangeCompleteRequest = 
                (WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData;
            l_strPriceConditionType = l_equityChangeCompleteRequest.priceCondType;
            l_orderCondType = l_equityChangeCompleteRequest.orderCondType;
        }
        else if(l_requestAdaptor.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            WEB3EquityChangeConfirmRequest l_equityChangeComfirmRequest = 
                (WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData;
            l_strPriceConditionType = l_equityChangeComfirmRequest.priceCondType;
            l_orderCondType = l_equityChangeComfirmRequest.orderCondType;
        }
        else
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                WEB3EquityChangeOrderUnitEntry.class + "." + STR_METHOD_NAME);
        }
        l_changeOrderUnitEntry.changeAfterPriceConditionType = l_strPriceConditionType;
        l_changeOrderUnitEntry.changeAfterOrderCondType = l_orderCondType;
        
        // set�����㒍��������
        l_changeOrderUnitEntry.modifiedExpirationDate = l_requestAdaptor.getExpirationDate();

        //���������敪���h�w��Ȃ��h�̏ꍇ
        //   �@@�|�����㔭���������Z�q�F�@@null
        //   �@@�|������t�w�l��l�F�@@0
        //   �@@�|������iW�w�l�j�����w�l�F�@@0
        //   �@@�|������iW�w�l�j���s�����F�@@null
        //   �@@�|�iW�w�l�j�L����ԋ敪�F�@@null
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderCondType))
        {
            l_changeOrderUnitEntry.changeAfterOrderCondOperator = null;
            l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice = 0.0D;
            l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice = 0.0D;
            l_changeOrderUnitEntry.modifiedWlimitExecCondType = null;
            l_changeOrderUnitEntry.wlimitEnableStatusDiv = null;
        }

        //���������敪���h�t�w�l�h�̏ꍇ
        //�|�����㔭���������Z�q�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�t�w�l�p�����������Z�q
        //�|������t�w�l��l�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�t�w�l�p���������P�� ��double�ɕϊ������l
        //�|������iW�w�l�j�����w�l�F�@@0
        //�|������iW�w�l�j���s�����F�@@null
        //�|�iW�w�l�j�L����ԋ敪�F�@@null
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderCondType))
        {
            if (l_requestAdaptor.requestData instanceof WEB3EquityChangeConfirmRequest)
            {
                l_changeOrderUnitEntry.changeAfterOrderCondOperator =
                    ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).stopOrderCondOperator;

                l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice =
                    (Double.parseDouble(
                        ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).stopOrderCondPrice));
            }
            else
            {
                l_changeOrderUnitEntry.changeAfterOrderCondOperator =
                    ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).stopOrderCondOperator;

                l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice =
                    (Double.parseDouble(
                        ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).stopOrderCondPrice));
            }

            l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice = 0.0D;

            l_changeOrderUnitEntry.modifiedWlimitExecCondType = null;

            l_changeOrderUnitEntry.wlimitEnableStatusDiv = null;
        }

        //���������敪���hW�w�l�h�̏ꍇ
        //�|�����㔭���������Z�q�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.W�w�l�p�����������Z�q
        //�|������t�w�l��l�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.W�w�l�p���������P�� ��double�ɕϊ������l
        //�|������iW�w�l�j�����w�l�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.W�w�l�p�����P�� ��double�ɕϊ������l�B
        //�����N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.W�w�l�p�����P���敪=="���s"�̏ꍇ�́A0���Z�b�g�B
        //�|������iW�w�l�j���s�����F�@@���N�G�X�g�A�_�v�^.get�iW�w�l�j���s����()
        //�|�iW�w�l�j�L����ԋ敪�F�@@���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�v�w�l�p�L����ԋ敪
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderCondType))
        {
            if (l_requestAdaptor.requestData instanceof WEB3EquityChangeConfirmRequest)
            {
                l_changeOrderUnitEntry.changeAfterOrderCondOperator =
                    ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wlimitOrderCondOperator;

                l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice =
                    Double.parseDouble(
                        ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wlimitOrderCondPrice);

                if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(
                    ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wLimitOrderPriceDiv))
                {
                    l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice = 0.0D;
                }
                else
                {
                    l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice =
                        Double.parseDouble(
                            ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wLimitPrice);
                }

                l_changeOrderUnitEntry.modifiedWlimitExecCondType =
                    l_requestAdaptor.getWLimitExecCondType();

                l_changeOrderUnitEntry.wlimitEnableStatusDiv =
                    ((WEB3EquityChangeConfirmRequest)l_requestAdaptor.requestData).wlimitEnableStatusDiv;

            }
            else
            {
                l_changeOrderUnitEntry.changeAfterOrderCondOperator =
                    ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wlimitOrderCondOperator;

                l_changeOrderUnitEntry.changeAfterStopOrderCondPriceBasePrice =
                    Double.parseDouble(
                        ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wlimitOrderCondPrice);

                if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(
                    ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wLimitOrderPriceDiv))
                {
                    l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice = 0.0D;
                }
                else
                {
                    l_changeOrderUnitEntry.changeAfterWlimitOrderCondPrice =
                        Double.parseDouble(
                            ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wLimitPrice);
                }

                l_changeOrderUnitEntry.modifiedWlimitExecCondType =
                    l_requestAdaptor.getWLimitExecCondType();

                l_changeOrderUnitEntry.wlimitEnableStatusDiv =
                    ((WEB3EquityChangeCompleteRequest)l_requestAdaptor.requestData).wlimitEnableStatusDiv;

            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_changeOrderUnitEntry;
    }

    /**
     * (get�����P��)<BR>
     * �����Ώے����P�ʃI�u�W�F�N�g���擾����B<BR>
     * @@return OrderUnit<BR>
     * @@roseuid 4021E0A60173<BR>
     */
    public OrderUnit getOrderUnit()
    {
        return this.orderUnit;
    }

    /**
     * (get������is�o����܂Œ���)<BR>
     * �����オ�u�o����܂Œ����v�ł��邩�ǂ�����Ԃ��B<BR>
     * @@return boolean<BR>
     * @@roseuid 4074D20800BB<BR>
     */
    public boolean getChangeAfterIsOrderUntilDeadLine()
    {
        return this.changeAfterIsOrderUntilDeadLine;
    }

    /**
     * (get������l�i����)<BR>
     * ������̒l�i�������擾����B<BR>
     * @@return String
     */
    public String getChangeAfterPriceConditionType() 
    {
        return this.changeAfterPriceConditionType;
    }

    /**
     * (get�����㔭������)<BR>
     * ������̔����������擾����B<BR>
     * @@return String<BR>
     * @@roseuid 4074D188033B<BR>
     */
    public String getChangeAfterOrderCondType()
    {
        return this.changeAfterOrderCondType;
    }

    /**
     * (get�����㔭���������Z�q)<BR>
     * ������̔����������Z�q���擾����B<BR>
     * @@return String<BR>
     * @@roseuid 4074DDA3001E<BR>
     */
    public String getChangeAfterOrderCondOperator()
    {
        return this.changeAfterOrderCondOperator;
    }

    /**
     * (get������t�w�l��l)<BR>
     * ������̋t�w�l��l���擾����B<BR>
     * @@return double<BR>
     * @@roseuid 4074DDE1000F<BR>
     */
    public double getChangeAfterStopOrderCondPriceBasePrice()
    {
        return this.changeAfterStopOrderCondPriceBasePrice;
    }

    /**
     * (get������iW�w�l�j�����w�l)<BR>
     * ������́iW�w�l�j�����w�l���擾����B<BR>
     * @@return double<BR>
     * @@roseuid 4074DE0202ED<BR>
     */
    public double getChangeAfterWlimitOrderCondPrice()
    {
        return this.changeAfterWlimitOrderCondPrice;
    }

    /**
     * (get�����㒍��������)<BR>
     * ������̒������������擾����B<BR>
     * @@return Date
     */
    public Date getModifiedExpirationDate() 
    {
        return this.modifiedExpirationDate;
    }

    /**
     * (get������iW�w�l�j���s����)<BR>
     * ������́iW�w�l�j���s�������擾����B<BR>
     * @@return EqTypeExecutionConditionType
     */
    public EqTypeExecutionConditionType getModifiedWlimitExecCondType()
    {
        return this.modifiedWlimitExecCondType;
    }

    /**
     * (get�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪���擾����B<BR>
     * @@return String
     */
    public String getWlimitEnableStatusDiv()
    {
        return this.wlimitEnableStatusDiv;
    }

    /**
     * (is�X�g�b�v�����L��)<BR>
     * this.�iW�w�l�j�L����ԋ敪 == "�X�g�b�v�����L��"�̏ꍇ�Atrue�A<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isStopOrderEnable()
    {
        if (WEB3EquityWlimitEnableStatusDivDef.STOP_ENABLE.equals(
            this.wlimitEnableStatusDiv))
        {
            return true;
        }
        return false;
    }

    /**
     * (�������������l�ڍ�)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * �@@�X�[�p�[�N���X�̃R���X�g���N�^�iEqTypeChangeOrderUnitEntry�j���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@orderUnitId�F�@@�p�����[�^.�����P��.�����P��ID<BR>
     * �@@�@@afterChangeOrigQuantity�F�@@�p�����[�^.�����P��.��������<BR>
     * �@@�@@afterChangePrice�F�@@�p�����[�^.�����P��.�iW�w�l�j�����w�l<BR>
     * <BR>
     * �Q�j�@@�g���v���p�e�B���Z�b�g����<BR>
     * <BR>
     * �@@�|�����㎷�s�����F�@@�p�����[�^.�����㎷�s����<BR>
     * �@@�|�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * �@@�|������is�o����܂Œ����F�@@�p�����[�^.������is�o����܂Œ���<BR>
     * �@@�|������l�i�����F�@@�p�����[�^.������l�i����<BR>
     * �@@�|�����㔭�������F�@@�p�����[�^.�����㔭������<BR>
     * �@@�|�����㔭���������Z�q�F�@@�p�����[�^.�����㔭���������Z�q<BR>
     * �@@�|������t�w�l��l�F�@@�p�����[�^.������t�w�l��l<BR>
     * �@@�|������iW�w�l�j�����w�l�F�@@�p�����[�^.������iW�w�l�j�����w�l<BR>
     * �@@�|�����㒍���������F�@@�p�����[�^.�����㒍��������<BR>
     * �@@�|������iW�w�l�j���s�����F�@@�p�����[�^.������iW�w�l�j���s����<BR>
     * �@@�|�iW�w�l�j�L����ԋ敪�F�@@�p�����[�^.�iW�w�l�j�L����ԋ敪<BR>
     * @@param l_afterChangeExecutionConditionType - (�����㎷�s����)<BR>
     * �����㎷�s����<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_blnAfterChangeIsOrderUntilDeadLine - (������is�o����܂Œ���)<BR>
     * ������is�o����܂Œ���<BR>
     * @@param l_strAfterChangePriceConditionType - (������l�i����)<BR>
     * ������l�i����<BR>
     * @@param l_strAfterChangeOrderCondType - (�����㔭������)<BR>
     * �����㔭������<BR>
     * @@param l_strAfterChangeOrderCondOperator - (�����㔭���������Z�q)<BR>
     * �����㔭���������Z�q<BR>
     * @@param l_dblAfterChangeStopOrderCondPriceBasePrice - (������t�w�l��l)<BR>
     * ������t�w�l��l<BR>
     * @@param l_dblAfterChangeWlimitOrderCondPrice - (������iW�w�l�j�����w�l)<BR>
     * ������iW�w�l�j�����w�l<BR>
     * @@param l_afterChangeExpirationDate - (�����㒍��������)<BR>
     * �����㒍��������<BR>
     * @@param l_afterChangeWlimitExecCondType - (������iW�w�l�j���s����)<BR>
     * ������iW�w�l�j���s����<BR>
     * @@param l_strWlimitEnableStatusDiv - (�iW�w�l�j�L����ԋ敪)<BR>
     * �iW�w�l�j�L����ԋ敪
     */
    public WEB3EquityChangeOrderUnitEntry(
        EqTypeExecutionConditionType l_afterChangeExecutionConditionType,
        OrderUnit l_orderUnit,
        boolean l_blnAfterChangeIsOrderUntilDeadLine,
        String l_strAfterChangePriceConditionType,
        String l_strAfterChangeOrderCondType,
        String l_strAfterChangeOrderCondOperator,
        double l_dblAfterChangeStopOrderCondPriceBasePrice,
        double l_dblAfterChangeWlimitOrderCondPrice,
        Date l_afterChangeExpirationDate,
        EqTypeExecutionConditionType l_afterChangeWlimitExecCondType,
        String l_strWlimitEnableStatusDiv)
    {
        //�P�j�@@�C���X�^���X����
        super(l_orderUnit.getOrderUnitId(),
            l_orderUnit.getQuantity(),
            ((EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject()).getWLimitPrice());
        //�����㎷�s����
        this.afterChangeExecutionConditionType = l_afterChangeExecutionConditionType;
        //�����P��
        this.orderUnit = l_orderUnit;
        //������is�o����܂Œ���
        this.changeAfterIsOrderUntilDeadLine = l_blnAfterChangeIsOrderUntilDeadLine;
        //������l�i����
        this.changeAfterPriceConditionType = l_strAfterChangePriceConditionType;
        //�����㔭������
        this.changeAfterOrderCondType = l_strAfterChangeOrderCondType;
        //�����㔭���������Z�q
        this.changeAfterOrderCondOperator = l_strAfterChangeOrderCondOperator;
        //������t�w�l��l
        this.changeAfterStopOrderCondPriceBasePrice = l_dblAfterChangeStopOrderCondPriceBasePrice;
        //������iW�w�l�j�����w�l
        this.changeAfterWlimitOrderCondPrice = l_dblAfterChangeWlimitOrderCondPrice;
        //�����㒍��������
        this.modifiedExpirationDate = l_afterChangeExpirationDate;
        //������iW�w�l�j���s����
        this.modifiedWlimitExecCondType = l_afterChangeWlimitExecCondType;
        //�iW�w�l�j�L����ԋ敪
        this.wlimitEnableStatusDiv = l_strWlimitEnableStatusDiv;
    }
}
@
