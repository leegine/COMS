head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenContractRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�V�K���������N�G�X�g�A�_�v�^(WEB3FuturesOpenContractRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 �����F (���u) �V�K�쐬 ���f�� 832 861
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨�V�K���������N�G�X�g�A�_�v�^)<BR>
 * �����w���敨�V�K���������N�G�X�g�A�_�v�^�N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FuturesOpenContractRequestAdapter
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOpenContractRequestAdapter.class);

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
     * (�R���X�g���N�^)�B
     */
    protected WEB3FuturesOpenContractRequestAdapter()
    {

    }

    /**
     * �敨�V�K���������N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�P�j �{�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�j ���������C���X�^���X�ɁA�ȉ��̍��ڂ�ݒ肷��B<BR>
     * <BR>
     * �@@�@@�@@���N�G�X�g�f�[�^ �F ����.���N�G�X�g<BR>
     * <BR>
     * �@@�R�j �C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3FuturesOpenContractRequestAdapter
     * @@throws WEB3BaseException
     */
    public static WEB3FuturesOpenContractRequestAdapter create(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j �{�C���X�^���X�𐶐�����B
        WEB3FuturesOpenContractRequestAdapter l_openContractRequestAdapter =
            new WEB3FuturesOpenContractRequestAdapter();

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
     * �@@���N�G�X�g�f�[�^.�����P���敪 == "�w�l"�̏ꍇ<BR>
     * �@@�@@���N�G�X�g�f�[�^.�����P����ԋp����B<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.�����P���敪 == "���s"�̏ꍇ<BR>
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
        if (this.request instanceof WEB3FuturesOpenMarginConfirmRequest)
        {
            l_strOrderPriceDiv = ((WEB3FuturesOpenMarginConfirmRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3FuturesOpenMarginConfirmRequest)this.request).limitPrice;
        }
        else if (this.request instanceof WEB3FuturesOpenMarginCompleteRequest)
        {
            l_strOrderPriceDiv = ((WEB3FuturesOpenMarginCompleteRequest)this.request).orderPriceDiv;
            l_strLimitPrice = ((WEB3FuturesOpenMarginCompleteRequest)this.request).limitPrice;
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
