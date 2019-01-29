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
filename	WEB3MarginExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����������Ɖ���P�ʃN���X(WEB3MarginExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
Revesion History : 2006/07/05 �юu�� (���u) �d�l�ύX941
Revesion History : 2006/11/02 �����F(���u) ���f�� 948,999
Revesion History : 2007/06/05 �����q (���u) �d�l�ύX�E���f��1164
Revesion History : 2007/07/27 �����q(���u) ���f�� No.1184
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p����������Ɖ���P�ʁj�B<br>
 * <br>
 * �M�p����������Ɖ���P�ʃN���X
 * @@version 1.0
 */
public class WEB3MarginExecuteGroup extends Message  
{

    
    /**
     * (�����h�c)<BR>
     */
    public String id;
    
    /**
     * (�����R�[�h)<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     */
    public String productName;
    
    /**
     * (�s��R�[�h)<BR>
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����<BR>
     */
    public String taxType;
    
    /**
     * (����敪)<BR>
     * 3�F�V�K���������@@4�F�V�K��������<BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj<BR>
     * 7�F���������@@8�F���n����<BR>
     */
    public String tradingType;
    
    /**
     * (�ٍ�)<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (����)<BR>
     */
    public Date openDate;
    
    /**
     * (���P��)<BR>
     */
    public String contractPrice;
    
    /**
     * (�l�i����)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@ 7:���s�c�����
     */
    public String priceCondType;
        
    /**
     * (���s����)<BR>
     * 1�F�������@@3�F��t�@@4�F�����@@7�F�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * �i�t�w�l�p���������P���j<BR>
     * ���������敪�u1�F�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * �i�t�w�l�p�����������Z�q�j<BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * ���������敪�u1�F�t�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * �i�v�w�l�p���������P���j<BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * �i�v�w�l�p�����������Z�q�j<BR>
     * 1�F�ȉ��@@2�F�ȏ�<BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * �i�v�w�l�p�����P���敪�j<BR>
     * 0�F���s�@@1�F�w�l <BR>
     * ���������敪�u2�FW�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * �i�v�w�l�p�����P���j<BR>
     * �v�w�l�p�����P���敪�u1�F�w�l�v�̏ꍇ�ݒ�<BR>
     */
    public String wLimitPrice;

    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳��� <BR>
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
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳��� <BR>
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
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ݒ肳���<BR>
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
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     */
    public String limitPrice;
    
    /**
     * (��芔��)<BR>
     */
    public String execQuantity;
    
    /**
     * (���P��)<BR>
     */
    public String execPrice;
    
    /**
     * (�T�Z��n���)<BR>
     * �V�K���@@�@@ �F�@@�T�Z�����<BR>
     * �ԍρ@@�@@�@@�@@�F�@@�T�Z���ϑ��v���<BR>
     * �������n�@@�F�@@�T�Z��n���<BR>
     */
    public String estimatedPrice;
    
    /**
     * (��n���)<BR>
     * ��n���<BR>
     */
    public String deliveryPrice;
    
    /**
     * (��������)<BR>
     */
    public Date orderDate;
    
    /**
     * (������)
     * ������
     */
    public Date orderBizDate;
    
    /**
     * (�����L������)<BR>
     */
    public Date expirationDate;
    
    /**
     * (������)<BR>
     * 000�F��t����/�����/��������Ȃ�<BR>
     * 003�F��t����/�����/�S���������<BR>
     * 007�F��t����/�����/�S����������<BR>
     * 100�F��t��/�����/��������Ȃ�<BR>
     * 101�F��t��/�����/�����<BR>
     * 103�F��t��/�����/�S���������<BR>
     * 104�F��t��/�����/������s<BR>
     * 105�F��t��/�����/������<BR>
     * 107�F��t��/�����/�S����������<BR>
     * 108�F��t��/�����/�������s<BR>
     * 109�F��t��/�����/��������G���[<BR>
     * 110�F��t��/�ꕔ���/��������Ȃ�<BR>
     * 111�F��t��/�ꕔ���/�����<BR>
     * 114�F��t��/�ꕔ���/������s<BR>
     * 115�F��t��/�ꕔ���/������<BR>
     * 116�F��t��/�ꕔ���/�ꕔ��������<BR>
     * 118�F��t��/�ꕔ���/�������s<BR>
     * 119�F��t��/�ꕔ���/��������G���[<BR>
     * 120�F��t��/�S�����/��������Ȃ�<BR>
     * 124�F��t��/�S�����/������s<BR>
     * 126�F��t��/�S�����/�ꕔ��������<BR>
     * 127�F��t��/�S�����/�S����������<BR>
     * 128�F��t��/�S�����/�������s<BR>
     * 129�F��t��/�S�����/��������G���[<BR>
     * 130�F��t��/����/��������Ȃ�<BR>
     * 134�F��t��/����/������s<BR>
     * 136�F��t��/����/�ꕔ��������<BR>
     * 137�F��t��/����/�S����������<BR>
     * 138�F��t��/����/�������s<BR>
     * 139�F��t��/����/��������G���[<BR>
     * 140�F��t��/�ꕔ����/��������Ȃ�<BR>
     * 144�F��t��/�ꕔ����/������s<BR>
     * 146�F��t��/�ꕔ����/�ꕔ��������<BR>
     * 148�F��t��/�ꕔ����/�������s<BR>
     * 149�F��t��/�ꕔ����/��������G���[<BR>
     * 200�F��t�G���[/�����/��������Ȃ�<BR>
     * <BR>
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
     * <BR>
     * �������n�ɂ��Ă͒����t���O�Ɣ񓯊��Őݒ肳���B<BR>
     */
    public boolean cancelFlag;
    
    /**
     * (�����`���l��)<BR>
     * 0�F�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�@@3�F���o�C��<BR>
     * <BR>
     * ���O�C�����[�U���R�[���Z���^�[�̏ꍇ�ݒ�<BR>
     */
    public String orderChannel;
    
    /**
     * (�����o�H�敪)<BR>
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g�@@4�Fi-mode�@@<BR>
     * 5�FVodafone�@@6�FAU�@@7�F�X�����O�V���b�g�i�����j�@@9�FHOST<BR>
     * A�F�Ǘ��ҁ@@B�F�ۏ؋������U�փo�b�`�@@C�F���b�`�N���C�A���g<BR>
     * F�FIVR�i���������d�b�j�@@G�F��������<BR>
     * <BR>
     * ���O�C�����[�U���R�[���Z���^�[�̏ꍇ�ݒ�<BR>
     */
    public String orderRootDiv;
    
    /**
     * (�I�y���[�^�R�[�h)<BR>
     * ���O�C�����[�U���R�[���Z���^�[�̏ꍇ�ݒ�<BR>
     */
    public String operatorCode;
    
    /**
     * (�������ꗗ)<BR>
     * �M�p����������<BR>
     */
    public WEB3MarginExecuteUnit[] executeUnits;

    /**
     * (�x���敪)<BR>
     * 0�F����@@1�F�x��<BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String delayDiv;

    /**
     * (�蓮�����\�t���O)<BR>
     * true�F�蓮�����\�@@�@@false�F�蓮�����s�� <BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�܂��́u2�FW�w�l�v�ŁA<BR>
     * �蓮�������\�ł���ꍇ�Atrue���ݒ肳���B<BR>
     */
    public boolean manualFlag;

    /**
     * (�������ϗ��R)<BR>
     * �������ϗ��R<BR>
     * <BR>
     * 0�F�@@���ϊ�������<BR>
     * 1�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j<BR>
     * 2�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j<BR>
     * 3�F�@@�ۏ؋��ێ������i��ԁj<BR>
     * 9�F�@@�蓮��������<BR>
     * <BR>
     * ���������ϒ����łȂ��ꍇ��null���Z�b�g�����B<BR>
     */
    public String forcedSettleReason = null;

    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     * <BR>
     * 0�F�@@�I�[�v��<BR>
     * 1�F�@@����������<BR>
     */
    public String forcedLapseDiv;

    /**
     * @@roseuid 414048CA0067
     */
    public WEB3MarginExecuteGroup() 
    {
     
    }
}
@
