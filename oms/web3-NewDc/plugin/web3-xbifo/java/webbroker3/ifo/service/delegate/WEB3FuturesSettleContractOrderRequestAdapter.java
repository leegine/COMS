head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesSettleContractOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�ԍϒ������N�G�X�g�A�_�v�^(WEB3FuturesSettleContractOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 �����F (���u) �V�K�쐬 ���f�� 840 861
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨�ԍϒ������N�G�X�g�A�_�v�^)<BR>
 * �敨�ԍϒ������N�G�X�g�A�_�v�^�N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FuturesSettleContractOrderRequestAdapter
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesSettleContractOrderRequestAdapter.class);

    /**
     * (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     */
    public WEB3GenRequest request;

    /**
     * (�R���X�g���N�^)�B
     */
    protected WEB3FuturesSettleContractOrderRequestAdapter()
    {

    }

    /**
     * �敨�ԍϒ������N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�����B<BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃��N�G�X�g�f�[�^���Z�b�g����B<BR>
     * �R�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3FuturesSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    public static WEB3FuturesSettleContractOrderRequestAdapter create(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j �{�C���X�^���X�𐶐�����B
        WEB3FuturesSettleContractOrderRequestAdapter l_settleContractOrderResquestAdapter =
            new WEB3FuturesSettleContractOrderRequestAdapter();

        //�Q�j ���������C���X�^���X�ɁA�ȉ��̍��ڂ�ݒ肷��B
        //���N�G�X�g�f�[�^ �F ����.���N�G�X�g
        l_settleContractOrderResquestAdapter.request = l_request;

        //�R�j �C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderResquestAdapter;
    }

    /**
     * (get����)<BR>
     * ���ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * ���N�G�X�g.�ԍό���[0].ID�ɊY�����錚�ʂ�<BR>
     * �擾���A�ԋp����B<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     */
    public WEB3IfoContractImpl getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract()";
        log.entering(STR_METHOD_NAME);

        WEB3IfoContractImpl l_ifoContract = null;
        String l_strID = null;

        if (this.request instanceof WEB3FuturesCloseMarginConfirmRequest)
        {
            l_strID = ((WEB3FuturesCloseMarginConfirmRequest)this.request).closeMarginContractUnits[0].id;
        }
        else if (this.request instanceof WEB3FuturesCloseMarginCompleteRequest)
        {
            l_strID = ((WEB3FuturesCloseMarginCompleteRequest)this.request).closeMarginContractUnits[0].id;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        try
        {
            //���N�G�X�g.�ԍό���[0].ID�ɊY�����錚��
            l_ifoContract = (WEB3IfoContractImpl)l_positionManager.getContract(Long.parseLong(l_strID));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoContract;
    }

    /**
     * (get��������)<BR>
     * ���N�G�X�g�f�[�^.���Ϗ����A����у��N�G�X�g�f�[�^.�������ʂ��A<BR>
     * AP�w�Ŏg�p���钍�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.���Ϗ������h�����_���h�̏ꍇ�A0��ԋp����B<BR>
     * <BR>
     * �Q�j�@@���N�G�X�g�f�[�^.���Ϗ������h�����_���h�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@���N�G�X�g�f�[�^.�������ʂ�ԋp����B<BR>
     * @@return double
     */
    public double getOrderQuantity()
    {
        final String STR_METHOD_NAME = "getOrderQuantity()";
        log.entering(STR_METHOD_NAME);

        //��������
        double l_dblOrderQuantity = 0.0D;
        String l_strOrderQuantity = null;
        //���Ϗ���
        String l_strOlosingOrder = null;

        if (this.request instanceof WEB3FuturesCloseMarginConfirmRequest)
        {
            l_strOlosingOrder = ((WEB3FuturesCloseMarginConfirmRequest)this.request).closingOrder;
            l_strOrderQuantity = ((WEB3FuturesCloseMarginConfirmRequest)this.request).futOrderQuantity;
        }
        else if (this.request instanceof WEB3FuturesCloseMarginCompleteRequest)
        {
            l_strOlosingOrder = ((WEB3FuturesCloseMarginCompleteRequest)this.request).closingOrder;
            l_strOrderQuantity = ((WEB3FuturesCloseMarginCompleteRequest)this.request).futOrderQuantity;
        }

        //���N�G�X�g�f�[�^.���Ϗ������h�����_���h�ȊO�̏ꍇ���N�G�X�g�f�[�^.�������ʂ�ԋp����
        if (!WEB3ClosingOrderDef.RANDOM.equals(l_strOlosingOrder))
        {
            if (l_strOrderQuantity != null)
            {
                l_dblOrderQuantity = Double.parseDouble(l_strOrderQuantity);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblOrderQuantity;
    }

    /**
     * (get�P��)<BR>
     * �P�������N�G�X�g�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�����P���敪=="�w�l"�̏ꍇ�́A<BR>
     * ���N�G�X�g�f�[�^.�����P����ԋp����B <BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�����P���敪=="���s"�̏ꍇ�́A<BR>
     * 0��ԋp����B <BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrice()";
        log.entering(STR_METHOD_NAME);

        String l_strOrderPriceDiv = null;
        double l_dblPrice = 0.0D;
        String l_strLimitPrice = null;

        if (this.request instanceof WEB3FuturesCloseMarginConfirmRequest)
        {
            l_strOrderPriceDiv = ((WEB3FuturesCloseMarginConfirmRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3FuturesCloseMarginConfirmRequest)this.request).limitPrice;
        }
        else if (this.request instanceof WEB3FuturesCloseMarginCompleteRequest)
        {
            l_strOrderPriceDiv = ((WEB3FuturesCloseMarginCompleteRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3FuturesCloseMarginCompleteRequest)this.request).limitPrice;
        }

        //���N�G�X�g�f�[�^.�����P���敪 == "�w�l"�̏ꍇ���N�G�X�g�f�[�^.�����P����ԋp����B
        //���N�G�X�g�f�[�^.�����P���敪 == "���s"�̏ꍇ0��ԋp����B
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrderPriceDiv))
        {
            if (l_strLimitPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_strLimitPrice);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }
}
@
