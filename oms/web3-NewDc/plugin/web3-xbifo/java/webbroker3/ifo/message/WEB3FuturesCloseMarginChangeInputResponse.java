head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����ԍϓ��͉�ʃ��X�|���X(WEB3FuturesCloseMarginChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ���Q (���u) �V�K�쐬
                   2006/07/28 �����F�@@�d�l�ύX�@@���f��454,457,470,488
Revesion History : 2007/06/11 �Ј���@@�d�l�ύX���f��No.639
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���敨�����ԍϓ��͉�ʃ��X�|���X)<BR>
 * �����w���敨�����ԍϓ��͉�ʃ��X�|���X�N���X<BR>
 * 
 * @@author ���Q
 * @@version 1.0 
 */
public class WEB3FuturesCloseMarginChangeInputResponse extends WEB3FuturesCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_closeMarginChangeInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201051L;
    
    /**
     * (����敪)<BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj<BR>
     * �iOrderTypeEnum�ɂĒ�`�j<BR>
     */
    public String tradingType;
    
    /**
     * (����s��)<BR>
     * �F�����@@2�F���<BR>
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
     * (�����i���ݒl�j)<BR>
     * ���o�C���Ŏg�p�B<BR>
     */
    public String currentPrice;
    
    /**
     * (�O����)<BR>
     * ���o�C���Ŏg�p�B<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (�������(�������\���ԁj)<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     */
    public Date currentPriceTime;
    
    /**
     * (���Ϗ���)<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     * <BR>
     * �ꊇ�ԍς̏ꍇ�ݒ�<BR>
     */
    public String closingOrder;
    
    /**
     * (���ʖ���)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;
    
    /**
     * (��������)<BR>
     * �ꊇ�ԍώ��Ɂu�����_�����[�h�v�̏ꍇ�͐ݒ肳��Ȃ�<BR>
     */
    public String futOrderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * �F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String limitPrice;
    
    /**
     * (���s����)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * 1�F��������@@2�F�o����܂Œ����@@3�F�[��܂Œ���<BR>
     */
    public String expirationDateType;
    
    /**
     * (�����L������)<BR>
     * ���������敪���u�o����܂Œ����v�̏ꍇ�ɐݒ�<BR>
     */
    public Date expirationDate;
    
    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (�t�w�l�p���������P��)<BR>
     * ���������敪���u�t�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * ���������敪���u�t�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (�v�w�l�p���������P��)<BR>
     * ���������敪���uW�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * ���������敪���uW�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (�v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪���uW�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (�v�w�l�p�����P��)<BR>
     * W�w�l�p�����P���敪���u�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String wLimitPrice;
    
    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>
     * 2�F�X�g�b�v����������<BR>
     * <BR>
     * ���������敪�܂��͌����������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitEnableStatusDiv;
    
    /**
     * (�v�w�l�p�֑ؑO�����P��)<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgLimitPrice;
    
    /**
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgExecCondType;
    
    /**
     * (�����������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     * <BR>
     * �������������s��̏ꍇ�ɐݒ�<BR>
     */
    public String orgOrderCondType;
    
    /**
     * (�����������P��)<BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgOrderCondPrice;
    
    /**
     * (�������������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgCondOperator;
    
    /**
     * (���v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWLimitOrderPriceDiv;
    
    /**
     * (���v�w�l�p�����P��)<BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String orgWLimitPrice;
    
    /**
     * (���v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitExecCondType;
    
    /**
     * (�T�Z���ϑ��v)<BR>
     */
    public String estimatedSettleIncome;
    
    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3FuturesCloseMarginChangeInputResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesCloseMarginChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
