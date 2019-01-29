head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����������Ɖ���P��(WEB3OptionsExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 羉s (���u) �V�K�쐬
              001: 2004/07/28 ���Ō� (���u) �Ή� �ڍא݌v�`�F�b�N�w�E���� (���{��) 
                   com.fitechlabs.xtrade.kernel.message.Message���p���B
              002: 2006/07/12 �����F�@@(���u) �Ή��@@�d�l�ύX�@@454,457,470,488,497,527    
Revesion History : 2007/06/08 ���^�]  (���u) �d�l�ύX���f��No.640
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����w���I�v�V�����������Ɖ���P��)<BR>
 * �����w���I�v�V�����������Ɖ���P�ʃN���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsExecuteGroup extends Message
{
    /**
     * �����h�c
     */
    public String id;

    /**
     * ������
     */
    public String opProductName;

    /**
     * (����s��)<BR>
     * <BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;

    /**
     * ����
     */
    public Date openDate;

    /**
     * ���P��
     */
    public String contractPrice;

    /**
     * (����敪)<BR>
     * <BR>
     * 3�F�V�K���������@@4�F�V�K��������<BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj<BR>
     */
    public String tradingType;

    /**
     * ��������
     */
    public Date orderDate;
    
    /**
     * ������
     */
    public Date orderBizDate;   

    /**
     * ��������
     */
    public String opOrderQuantity;

    /**
     * (�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * <BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String limitPrice;

    /**
     * (���s����)<BR>
     * <BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String execCondType;

    /**
     * (���������敪)<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;

    /**
     * �t�w�l�p�v���~�A���^�����Y���i<BR>
     * <BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     * ���������敪�u�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopPremium_underlyingAssets;

    /**
     * �t�w�l�p���������P��<BR>
     * <BR>
     * ���������敪�u�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopOrderCondPrice;

    /**
     * �t�w�l�p�����������Z�q<BR>
     * <BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * ���������敪�u�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopOrderCondOperator;

    /**
     * W�w�l�p�v���~�A���^�����Y���i<BR>
     * <BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     * ���������敪�uW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitPremium_underlyingAssets;

    /**
     * W�w�l�p���������P��<BR>
     * <BR>
     * ���������敪�uW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * W�w�l�p�����������Z�q<BR>
     * <BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * ���������敪�uW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * W�w�l�p�����P���敪<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪�uW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * W�w�l�p�����P��<BR>
     * <BR>
     * ���������敪�uW�w�l�v�̏ꍇ�ݒ�<BR>
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
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>  
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
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgPremium_underlyingAssets; 
    
    /**
     * (�����������P��)<BR>
     * <BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgOrderCondPrice; 
    
    /**
     * (�������������Z�q)<BR>
     * <BR>
     * 1�F�ȏ�@@2�F�ȉ� <BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgCondOperator; 
    
    /**
     * (��W�w�l�p�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l <BR>
     * �������������敪��2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgWLimitOrderPriceDiv; 
    
    /**
     * (��W�w�l�p�����P��)<BR>
     * <BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgWLimitPrice; 
    
    /**
     * (��W�w�l�p���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s <BR>
     * �������������敪��2�F�v�w�l�̏ꍇ�ɐݒ�<BR>
     */
    public String orgWlimitExecCondType;

    /**
     * ��萔��
     */
    public String execQuantity;

    /**
     * ���P��
     */
    public String execPrice;

    /**
     * �T�Z��n���
     */
    public String estimatedPrice;

    /**
     * ��n���
     */
    public String deliveryPrice;

    /**
     * (������)<BR>
     * ���R�[�h�l�̓��b�Z�[�W��`�t�H���_�ȉ��� <BR>
     * �uү���ޒ�`��_�����w���I�v�V����(����).xls�v�̏����󋵋敪��`���Q��<BR>
     */
    public String transactionStateType;
    
    /**
     * (�x���敪)<BR>
     * <BR>
     * 0�F����@@1�F�x�� <BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String delayDiv;
    
    /**
     * (�蓮�����\�t���O)<BR>
     * <BR>
     * true�F�蓮�����\�@@�@@false�F�蓮�����s�� <BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�ŁA<BR> 
     *�蓮�������\�ł���ꍇ�Atrue���ݒ肳���B<BR>
     */
    public boolean manualFlag;

    /**
     * �����L������
     */
    public Date expirationDate;

    /**
     * (�����`���l��)<BR>
     * <BR>
     * 0�F�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�@@3�F���o�C��<BR>
     * <BR>
     * ���O�C�����[�U���R�[���Z���^�[�̏ꍇ�ݒ�<BR>
     */
    public String orderChannel;

    /**
     * (�����o�H�敪)<BR>
     * <BR>
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g�@@4�Fi-mode�@@5�FVodafone�@@6�FAU�@@9�FHOST<BR>
     * <BR>
     * ���O�C�����[�U���R�[���Z���^�[�̏ꍇ�ݒ�<BR>
     */
    public String orderRootDiv;

    /**
     * (�I�y���[�^�R�[�h)<BR>
     * <BR>
     * ���O�C�����[�U���R�[���Z���^�[�̏ꍇ�ݒ�<BR>
     */
    public String operatorCode;

    /**
     * �������Ɖ���
     */
    public WEB3OptionsExecuteUnit[] opExecuteUnits;

    /**
     * (�����\�t���O)<BR>
     * <BR>
     * true�F�����\�@@�@@false�F�����s��<BR>
     */
    public boolean changeFlag;

    /**
     * (����\�t���O)<BR>
     * <BR>
     * true�F����\�@@�@@false�F����s��<BR>
     */
    public boolean cancelFlag;

    /**
     * �����R�[�h
     */
    public String opProductCode;

    /**
     * �w�����<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;

    /**
     * ����<BR>
     * YYYYMM�`��<BR>
     */
    public String delivaryMonth;

    /**
     * �I�v�V�������i�敪<BR>
     * P�F�v�b�g�I�v�V���� C�F�R�[���I�v�V����<BR>
     */
    public String opProductType;

    /**
     * �s�g���i
     */
    public String strikePrice;

    /**
     * (�[��O�J�z�Ώۃt���O)<BR>
     * false�F�[��O�J�z�Ȃ�<BR>
     * true�F�[��O�J�z����<BR>
     */
    public boolean eveningSessionCarryoverFlag;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40C0754C01D4
     */
    public WEB3OptionsExecuteGroup()
    {

    }
}
@
