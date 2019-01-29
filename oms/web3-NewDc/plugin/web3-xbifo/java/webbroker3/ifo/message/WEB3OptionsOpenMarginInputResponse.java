head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K���������͉�ʃ��X�|���X(WEB3OptionsOpenMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���o�� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�����V�K���������͉�ʃ��X�|���X)<BR>
 * �����w���I�v�V�����V�K���������͉�ʃ��X�|���X�N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginInputResponse extends WEB3OptionsCommonResponse
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141512L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_openMarginInput";

    /**
     * �V�K���\�z
     */
    public String opTradingPower;

    /**
     * �����R�[�h<BR>
     * �����I����ʂ��J�ڎ��ɐݒ肳���B<BR>
     */
    public String opProductCode;

    /**
     * ����s��ꗗ
     */
    public String[] marketList;

    /**
     * ����s��
     */
    public String marketCode;

    /**
     * �w����ʈꗗ<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String[] targetProductList;

    /**
     * �w�����
     */
    public String targetProductCode;

    /**
     * �����ꗗ
     */
    public String[] delivaryMonthList;

    /**
     * ����
     */
    public String delivaryMonth;

    /**
     * �I�v�V�������i�敪
     */
    public String opProductType;

    /**
     * �s�g���i
     */
    public String strikePrice;

    /**
     * �l�����Ă��Ȃ��Ƃ��͊�l��ݒ�B<BR>
     */
    public String currentPrice;

    /**
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     */
    public String comparedPreviousDay;

    /**
     * �������<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     */
    public Date currentPriceTime;

    /**
     * ����I���x������<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsOpenMarginInputResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsOpenMarginInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
