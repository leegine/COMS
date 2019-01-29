head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍϓ��͉�ʃ��X�|���X(WEB3FuturesCloseMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���敨�ԍϓ��͉�ʃ��X�|���X)<BR>
 * �����w���敨�ԍϓ��͉�ʃ��X�|���X�N���X
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginInputResponse extends WEB3FuturesCommonResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191543L;

    /**
     * (����敪)<BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj
     */
    public String tradingType;

    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���
     */
    public String marketCode;

    /**
     * (�w�����)<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225
     */
    public String targetProductCode;

    /**
     * (����)<BR>
     * YYYYMM�`��
     */
    public String delivaryMonth;

    /**
     * (���ݒl)<BR>
     * �l�����Ă��Ȃ��Ƃ��͊�l��ݒ�B
     */
    public String currentPrice;

    /**
     * (�O����)<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B
     */
    public String comparedPreviousDay;

    /**
     * (�������)<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B
     */
    public Date currentPriceTime;

    /**
     * (���ʖ���)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;

    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[
     */
    public String[] messageSuspension;

    /**
     * @@roseuid 40F7AE160280
     */
    public WEB3FuturesCloseMarginInputResponse()
    {

    }

    /**
    * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
    * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    *<BR>
    * @@param l_request ���N�G�X�g�I�u�W�F�N�g
    */
    protected WEB3FuturesCloseMarginInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
