head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�V�K���������͉�ʃ��X�|���X�N���X(WEB3FuturesOpenMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ������ �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (�����w���敨�V�K���������͉�ʃ��X�|���X)<BR>
 * �����w���敨�V�K���������͉�ʃ��X�|���X�N���X<BR>
 * 
 * @@author ������
 * @@version 1.0 
 */
public class WEB3FuturesOpenMarginInputResponse extends WEB3FuturesCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_OpenMarginInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201655L;
    
    /**
     * (�����R�[�h)<BR>
     * �����I����ʂ��J�ڎ��ɐݒ肳���B<BR>
     */
    public String futProductCode;
    
    /**
     * (�V�K���\�z)<BR>
     */
    public String futTradingPower;
    
    /**
     * (����s��ꗗ)<BR>
     */
    public String[] marketList;
    
    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;
    
    /**
     * (�w����ʈꗗ)<BR>
     */
    public String[] targetProductList;
    
    /**
     * (�w�����)<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;
    
    /**
     * (�����ꗗ)<BR>
     */
    public String[] delivaryMonthList;
    
    /**
     * (����)<BR>
     */
    public String delivaryMonth;
    
    /**
     * (���ݒl)<BR>
     * �l�����Ă��Ȃ��Ƃ��͊�l��ݒ�B<BR>
     */
    public String currentPrice;
    
    /**
     * (�O����)<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (�������)<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     */
    public Date currentPriceTime;
    
    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 40F7AE0D01E4
     */
    public WEB3FuturesOpenMarginInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
