head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍϓ��͉�ʃ��X�|���X�N���X(WEB3OptionsCloseMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ������ �V�K�쐬
*/
package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;

/**
 * (�����w���I�v�V�����ԍϓ��͉�ʃ��X�|���X)<BR>
 * �����w���I�v�V�����ԍϓ��͉�ʃ��X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginInputResponse extends WEB3OptionsCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406112110L;
        
    /**
     * (����敪)<BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj<BR>
     */
    public String tradingType;
    
    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;
    
    /**
    * (�w�����)<BR>
    * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
    */
    public String targetProductCode;
    
    /**
    * (����)<BR>
    * YYYYMM�`��<BR>
    */
    public String delivaryMonth;
    
    /**
    * (�I�v�V�������i�敪)<BR>
    * P�F�v�b�g�I�v�V���� C�F�R�[���I�v�V����<BR>
    */
    public String opProductType;
    
    /**
    * �s�g���i
    */
    public String strikePrice;
    
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
    * ���ʖ���
    */
    public WEB3FuturesOptionsContractUnit[] contractUnits;
    
    /**
    * (����I���x������)<BR>
    * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
    */
    public String[] messageSuspension;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsCloseMarginInputResponse()
    {
        
    }
    
    /**
    * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
    * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    *<BR>
    * @@param l_request ���N�G�X�g�I�u�W�F�N�g
    */
    protected WEB3OptionsCloseMarginInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
