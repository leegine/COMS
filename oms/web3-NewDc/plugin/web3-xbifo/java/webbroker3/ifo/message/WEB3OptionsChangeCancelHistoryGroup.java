head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsChangeCancelHistoryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�������������ꗗ�s(WEB3OptionsChangeCancelHistoryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 羉s (���u) �V�K�쐬
              001: 2004/07/28 ���Ō� (���u) �Ή� �ڍא݌v�`�F�b�N�w�E���� (���{��) 
                   com.fitechlabs.xtrade.kernel.message.Message���p���B
              002: 2006/07/12 �����F�@@(���u)�@@�d�l�ύX�@@454,457,470,488,527
Revesion History : 2007/06/08 ���^�](���u) �d�l�ύX���f��No.701
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����w���I�v�V�������������ꗗ�s)<BR>
 * �����w���I�v�V�������������ꗗ�s�N���X<BR>                                                                    
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionsChangeCancelHistoryGroup extends Message
{
    /**
     * (�����m�n)<BR>
     * ��������ID<BR>
     */
    public String orderActionId;

    /**
     * ������t��
     */
    public Date orderDate;

    /**
     * (��������)<BR>
     * ���ȊO�̏ꍇ�ݒ�<BR>
     */
    public String opOrderQuantity;

    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@�@@1�F�w�l<BR>
     * <BR>
     * ���ȊO�̏ꍇ�ݒ�<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String limitPrice;

    /**
     * (�������e�敪)<BR>
     * ���R�[�h�l�̓��b�Z�[�W��`�t�H���_�ȉ���<BR>
     * �uү���ޒ�`��_�����w���I�v�V����(����).xls�v�̒������e�敪��`���Q��<BR>
     */
    public String orderType;

    /**
     * (���s����)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     * <BR>
     * ���������̏ꍇ�ݒ�<BR>
     */
    public String execCondType;

    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     * <BR>
     * ���������̏ꍇ�ݒ�<BR>
     */
    public String orderCondType;

    /**
     * (�t�w�l�p�v���~�A��/�����Y���i)<BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     */
    public String stopPremium_underlyingAssets;

    /**
     * (�t�w�l�p���������P��)<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (W�w�l�p�v���~�A��/�����Y���i)<BR>
     * 0�F�����Y���i�@@1�F�v���~�A��<BR>
     */
    public String wlimitPremium_underlyingAssets;

    /**
     * (W�w�l�p���������P��)<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (W�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (W�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W�w�l�p�����P��)<BR>
     */
    public String wLimitPrice;
    
    /**
     * (W�w�l�p���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���ȊO�A���A���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitExecCondType;     
    
    /**
     * (W�w�l�p�L����ԋ敪)<BR>
     * <BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L�� <BR> 
     * 2�F�X�g�b�v���������� <BR>
     * ���ȊO�A���A<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitEnableStatusDiv; 
    
    /**
     * (W�w�l�p�֑ؑO�����P��)<BR>
     * <BR>
     * ���ȊO�A���A<BR>
     * ���������敪�܂��͌����������敪���u2�FW�w�l�v�A���� <BR>
     * W�w�l�̗L����Ԃ�"�X�g�b�v�����L��"�i���ؑ֊����j�̏ꍇ�A�ݒ肳���<BR>
     */
    public String wlimitBefChgLimitPrice; 
    
    /**
     * (W�w�l�p�֑ؑO���s����)<BR>
     * <BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * <BR>
     * ���ȊO�A���A<BR>
     * ���������敪�܂��͌����������敪���u2�FW�w�l�v�A���� <BR>
     * W�w�l�̗L����Ԃ�"�X�g�b�v�����L��"�i���ؑ֊����j�̏ꍇ�A�ݒ肳���<BR>
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
     * (��萔��)<BR>
     * ���̏ꍇ�ݒ�<BR>
     */
    public String execQuantity;

    /**
     * (���P��)<BR>
     * ���̏ꍇ�ݒ�<BR>
     */
    public String execPrice;

    /**
     * (�����L������)<BR>
     * ���������̏ꍇ�ݒ�<BR>
     * �u��������v�̏ꍇ��null��ݒ�<BR>
     */
    public Date expirationDate;

    /**
     * (���l�i���Ϗ����j)<BR>
     * �ԍό��ʏ��Ԃ�\��<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     * �ꊇ�ԍώ��̏ꍇ�ݒ�<BR>
     */
    public String closingOrder;

    /**
     * (��t���ʋ敪)<BR>
     * ���R�[�h�l�̓��b�Z�[�W��`�t�H���_�ȉ���<BR>
     * �uү���ޒ�`��_�����w���I�v�V����(����).xls�v�̎�t���ʋ敪��`���Q��<BR>
     */
    public String acceptedResultDiv;
    
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
     * @@roseuid 40C075320261
     */
    public WEB3OptionsChangeCancelHistoryGroup()
    {

    }
}
@
