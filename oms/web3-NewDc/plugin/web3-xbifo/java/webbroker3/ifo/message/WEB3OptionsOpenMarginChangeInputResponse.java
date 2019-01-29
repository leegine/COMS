head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������V�K�����͉�ʃ��X�|���X(WEB3OptionsOpenMarginChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 羉s (���u) �V�K�쐬
              001: 2006/07/12 �����F�@@(���u)�@@�d�l�ύX�@@454,457,470,488,527
Revesion History : 2007/06/08 ��іQ (���u) ���f��No.639
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;

import webbroker3.ifo.message.WEB3OptionsCommonResponse;

/**
 * (�����w���I�v�V���������V�K�����͉�ʃ��X�|���X)<BR>
 * �����w���I�v�V���������V�K�����͉�ʃ��X�|���X�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginChangeInputResponse extends WEB3OptionsCommonResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_openMarginChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141551L;

    /**
     * (����敪)<BR>
     * 3�F�V�K���������@@4�F�V�K��������
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
     * (�I�v�V�������i�敪)<BR>
     * P�F�v�b�g�I�v�V���� C�F�R�[���I�v�V����
     */
    public String opProductType;

    /**
     * (�s�g���i)<BR>
     */
    public String strikePrice;

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
    public java.util.Date currentPriceTime;

    /**
     * (��������)<BR>
     * �ꊇ�ԍώ��Ɂu�����_�����[�h�v�̏ꍇ�͐ݒ肳��Ȃ�
     */
    public String opOrderQuantity;

    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * �����P���敪���u1�F�w�l�v�̏ꍇ�ɐݒ�
     */
    public String limitPrice;

    /**
     * (���s����)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s
     */
    public String execCondType;

    /**
     * (���������敪)<BR>
     * 1�F��������@@2�F�o����܂Œ����@@3�F�[��܂Œ���
     */
    public String expirationDateType;

    /**
     * (�����L������)<BR>
     * ���������敪���u2�F�o����܂Œ����v�̏ꍇ�ɐݒ�
     */
    public java.util.Date expirationDate;

    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l
     */
    public String orderCondType;

    /**
     * (�t�w�l�p�v���~�A���^�����Y���i)<BR>
     * 0�FDEFAULT�i�����j�i*�j�敨�n�o�̏ꍇ�͌����Y����<BR>
     * 1�F�v���~�A��<BR>
     * ���������敪���A�u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopPremium_underlyingAssets;

    /**
     * (�t�w�l�p���������P��)<BR>
     * ���������敪���A�u1�F�t�w�l�v�̏ꍇ�ݒ肳���
     */
    public String stopOrderCondPrice;

    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * ���������敪���A�u1�F�t�w�l�v�̏ꍇ�ݒ肳���
     */
    public String stopOrderCondOperator;

    /**
     * (�v�w�l�p�v���~�A���^�����Y���i)<BR>
     * 0�FDEFAULT�i�����j�i*�j�敨�n�o�̏ꍇ�͌����Y����<BR>
     * 1�F�v���~�A��<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���
     */
    public String wlimitPremium_underlyingAssets;

    /**
     * (�v�w�l�p���������P��)<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���
     */
    public String wlimitOrderCondPrice;

    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���
     */
    public String wlimitOrderCondOperator;

    /**
     * (�v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���
     */
    public String wLimitOrderPriceDiv;

    /**
     * (�v�w�l�p�����P��)<BR>
     * �v�w�l�p�����P���敪���A�u1�F�w�l�v�̏ꍇ�ݒ肳���
     */
    public String wLimitPrice;
    
    /**
     * (W�w�l�p���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitExecCondType;     
    
    /**
     * (W�w�l�p�L����ԋ敪)<BR>
     * <BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��  <BR>
     * 2�F�X�g�b�v���������� <BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitEnableStatusDiv; 
    
    /**
     * (W�w�l�p�֑ؑO�����P��)<BR>
     * <BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgLimitPrice; 
    
    /**
     * (W�w�l�p�֑ؑO���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgExecCondType; 
    
    /**
     * (�����������敪)<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l <BR>
     * <BR>
     * �������������s��̏ꍇ�ɐݒ�<BR>
     */
    public String orgOrderCondType; 
    
    /**
     * (���v���~�A��/�����Y���i)<BR>
     * <BR>
     * 0�F�v���~�A���@@1�F�����Y���i <BR>
     * <BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgPremium_underlyingAssets; 
    
    /**
     * (�����������P��)<BR>
     * <BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgOrderCondPrice; 
    
    /**
     * (�������������Z�q)<BR>
     * <BR>
     * 1�F�ȏ�@@2�F�ȉ� <BR>
     *�������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgCondOperator; 
    
    /**
     * (��W�w�l�p�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l <BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWLimitOrderPriceDiv; 
    
    /**
     * (��W�w�l�p�����P��)<BR>
     * <BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String orgWLimitPrice; 
    
    /**
     * (��W�w�l�p���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s <BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitExecCondType;
    
    /**
     * (�T�Z��n���)<BR>
     */
    public String estimatedPrice;

    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[
     */
    public String[] messageSuspension;

    /**
     * (����萔��)<BR>
     * �������ʂ̂����A��肵�Ă��鐔��<BR>
     */
    public String partExecQuantity;
        
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsOpenMarginChangeInputResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsOpenMarginChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
