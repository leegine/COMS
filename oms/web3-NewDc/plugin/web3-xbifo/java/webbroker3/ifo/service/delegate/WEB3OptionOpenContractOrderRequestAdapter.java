head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�V�K���������N�G�X�g�A�_�v�^(WEB3OptionOpenContractOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 �����F (���u) �V�K�쐬 ���f�� 848
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�V�K���������N�G�X�g�A�_�v�^)<BR>
 * �����w���I�v�V�����V�K���������N�G�X�g�A�_�v�^�N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3OptionOpenContractOrderRequestAdapter
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOpenContractOrderRequestAdapter.class);

    /**
     * (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�I�u�W�F�N�g�B<BR>
     */
    public WEB3GenRequest request;

    /**
     * (�P��)<BR>
     * �P���B<BR>
     * <BR>
     * �i�w�l�^0�i�����s�j�^���s�P���i�}�w�l�j�j<BR>
     */
    public double price;

    /**
     * (�R���X�g���N�^)�B<BR>
     */
    protected WEB3OptionOpenContractOrderRequestAdapter()
    {

    }

    /**
     * �I�v�V�����V�K���������N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �@@�P�j�@@�{�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �@@�Q�j�@@���������C���X�^���X�ɁA�ȉ��̍��ڂ�ݒ肷��B <BR>
     * <BR>
     * �@@�@@�@@�@@���N�G�X�g�f�[�^�@@�F�@@�������N�G�X�g <BR>
     * <BR>
     * �@@�R�j�@@�C���X�^���X��ԋp����B <BR>
     * <BR>
     * �i�{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3OptionOpenContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    public static WEB3OptionOpenContractOrderRequestAdapter create(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j �{�C���X�^���X�𐶐�����B
        WEB3OptionOpenContractOrderRequestAdapter l_openContractRequestAdapter =
            new WEB3OptionOpenContractOrderRequestAdapter();

        //�Q�j ���������C���X�^���X�ɁA�ȉ��̍��ڂ�ݒ肷��B
        //���N�G�X�g�f�[�^ �F ����.���N�G�X�g
        l_openContractRequestAdapter.request = l_request;

        //�R�j �C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_openContractRequestAdapter;
    }

    /**
     * (get�P��)<BR>
     * �P����ԋp����B <BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.�����P���敪 == "�w�l"�̏ꍇ <BR>
     * �@@�@@���N�G�X�g�f�[�^.�����P����ԋp����B <BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.�����P���敪 == "���s"�̏ꍇ <BR>
     * �@@�@@0��ԋp����B<BR>
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
        if (this.request instanceof WEB3OptionsOpenMarginConfirmRequest)
        {
            l_strOrderPriceDiv = ((WEB3OptionsOpenMarginConfirmRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3OptionsOpenMarginConfirmRequest)this.request).limitPrice;
        }
        else if (this.request instanceof WEB3OptionsOpenMarginCompleteRequest)
        {
            l_strOrderPriceDiv = ((WEB3OptionsOpenMarginCompleteRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3OptionsOpenMarginCompleteRequest)this.request).limitPrice;
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
