head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������Ɖ���P��(WEB3EquityExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �����F (���u) �V�K�쐬
Revesion History : 2004/12/07 �����iSAR�j�c�Č��Ή� �m��.�O�T�X���m��.�R�V�T
Revesion History : 2004/12/13 �K�� (SRA) �c�Č��Ή� No.385
Revesion History : 2006/07/05 �юu�� (���u) �d�l�ύX941
Revesion History : 2006/07/15 �h�C �y�����z�d�l�ύX�Ǘ��䒠�E���f��952��Ή�
                   2006/08/29 �����F(���u) ���f�� 972
                   2006/11/01 �����F(���u) ���f�� 948,989
Revesion History : 2007/07/27 �����q(���u) ���f�� No.1184
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i���������������Ɖ���P�ʁj�B<BR>
 * <BR>
 * ���������������Ɖ� �����P�ʁ@@�f�[�^�N���X<BR>
 * <BR>
 * ���������F�u�������Ɖ�v<BR>
 * ���������F�u��������ꗗ�v�̗���ʂŎg�p����B
 * @@version 1.0
 */
public class WEB3EquityExecuteGroup extends Message
{

    /**
     * (ID)<BR>
     * ��ʔ�\������<BR>
     * �u���������ꗗ�v��ʂւ̑J�ڂɕK�v<BR>
     */
    public String id;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;

    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����@@5�F�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;

    /**
     * (����敪)<BR>
     * 1�F�����������@@2�F�����������@@99�F����O����<BR>
     */
    public String tradingType;

    /**
     * (�l�i����)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@7:���s�c�����<BR>
     */
    public String priceCondType;

    /**
     * (���s����)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String execCondType;

    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * �i�t�w�l�p���������P���j<BR>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * �i�t�w�l�p�����������Z�q�j<BR>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳��� <BR>
     * 1�F�ȏ�@@2�F�ȉ�
     */
    public String stopOrderCondOperator;
    
    /**
     * �iW�w�l�p���������P���j<BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * �iW�w�l�p�����������Z�q�j<BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     * 1�F�ȏ�@@2�F�ȉ�  <BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * �iW�w�l�p�����P���敪�j<BR>
     * 0�F���s�@@1�F�w�l <BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * �iW�w�l�p�����P���j<BR>
     * �v�w�l�p�����P���敪���A�u1�F�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitPrice;

    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s <BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitExecCondType;

    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>
     * 2�F�X�g�b�v����������<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
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
     */
    public String orgOrderCondType;
    
    /**
     * (�����������P��)<BR>
     * �����������P��<BR>
     */
    public String orgOrderCondPrice;

    /**
     * (�������������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ� <BR>
     */
    public String orgOrderCondOperator;

    /**
     * (���v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitOrderPriceDiv;

    /**
     * (���v�w�l�p�����P��)<BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ݒ肳���B<BR>
     */
    public String orgWlimitPrice;

    /**
     * (���v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitExecCondType;

    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;

    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    public String limitPrice;

    /**
     * (��芔��)<BR>
     * ���v��芔��<BR>
     */
    public String execQuantity;

    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String execPrice;

    /**
     * (�T�Z��n���)<BR>
     * �T�Z��n���<BR>
     */
    public String estimatedPrice;
    
    /**
     * (��n���)<BR>
     * ��n���<BR>
     */
    public String deliveryPrice;
    
    /**
     * (�T�Z���v)<BR>
     * �T�Z���v<BR>
     */
    public String estimatedProfitLoss;

    /**
     * (��������)<BR>
     * ������t����<BR>
     */
    public Date orderDate;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate;

    /**
     * (�����L������)<BR>
     * �����L������<BR>
     */
    public Date expirationDate;

    /**
     * (������)<BR>
     * �R�[�h��`�F�iU:\10.�v���_�N�V����\01.�v����`\02.�@@�\��`��\96.�W���v���j<BR>
     * �uNEW�������Ɖ�g�����\.xls�v�V�[�g�y�������Ɖ�@@�����󋵁z�Q�ƁB<BR>
     */
    public String transactionStateType;

    /**
     * (�����\�t���O)<BR>
     * true�F�����\�@@�@@false�F�����s��<BR>
     */
    public boolean changeFlag;

    /**
     * (����\�t���O)<BR>
     * true�F����\�@@�@@false�F����s��<BR>
     */
    public boolean cancelFlag;

    /**
     * (�����`���l��)<BR>
     * �����`���l��<BR>
     * <BR>
     * 0�F�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�@@3�F���o�C��<BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */
    public String orderChannel;

    /**
     * (�����o�H�敪)<BR>
     * �R�[�h��`��<BR>
     * <BR>
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g<BR>
     * 4�Fi-mode�@@5�FVodafone�@@6�FAU�@@7�F�X�����O�V���b�g�i�����j�@@9�FHOST<BR>
     * A�F�Ǘ��ҁ@@B�F�ۏ؋������U�փo�b�`�@@C�F���b�`�N���C�A���g<BR>
     * F�FIVR�i���������d�b�j�@@G�F��������<BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */
    public String orderRootDiv;

    /**
     * (�I�y���[�^�R�[�h)<BR>
     * �戵�҃R�[�h
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */
    public String operatorCode;

    /**
     * (��薾�׈ꗗ)<BR>
     * �������ɕR�t���������̈ꗗ<BR>
     * �i���������������Ɖ���̔z��j<BR>
     */
    public webbroker3.equity.message.WEB3EquityExecuteUnit[] executeUnits;

    /**
     * (�x���敪)<BR>
     * 0�F����@@1�F�x��<BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String delayDiv;

    /**
     * (�蓮�����\�t���O)<BR>
     * true�F�蓮�����\�@@�@@false�F�蓮�����s��<BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�ŁA<BR>
     * �蓮�������\�ł���ꍇ�Atrue���ݒ肳���B<BR>
     */
    public boolean manualFlag;

    /**
     * (���������������Ɖ���P��)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 407CC8090353
     */
    public WEB3EquityExecuteGroup()
    {

    }
}
@
