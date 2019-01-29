head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������������̓��X�|���X(WEB3EquityChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
Revesion History : 2004/12/08 �����a��(SRA) �c�Č��Ή� �m��.�O�T�X
                   2006/08/29 �����F(���u) ���f�� 972
                   2006/11/01 �����F(���u) ���f�� 948
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * �i�������������������̓��X�|���X�j�B<BR>
 * <BR>
 * �������������������͉����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityChangeInputResponse
    extends WEB3EquityInputCommonResponse
{

    /**
     * (���t�\���z)<BR>
     * ���t�����̏ꍇ�̂ݾ�āB<BR>
     */
    public String tradingPower;

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
     * 1:�����@@2:���@@3:���É��@@6:�����@@8:�D�y�@@9:NNM�@@10:JASDAQ<BR>
     */
    public String marketCode;

    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����@@5�F�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;

    /**
     * (�����敪)<BR>
     * 1�F�����@@�@@2�F����<BR>
     */
    public String dealingType;

    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;

    /**
     * (���o������)<BR>
     * ���A�����o������<BR>
     */
    public String partContQuantity;

    /**
     * (�T�Z�뉿�P��)<BR>
     * ���A���T�Z�뉿�P��<BR>
     */
    public String estimatedBookPrice;

    /**
     * (�����P���敪)<BR>
     * 0:���s�@@1:�w�l<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String limitPrice;

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
     * 1�F��������@@2�F�o����܂Œ���<BR>
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
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (�v�w�l�p���������P��)<BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (W�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W�w�l�p�����P��)<BR>
     * �v�w�l�p�����P���敪���A�u1�F�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitPrice;

    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���B<BR>
     */
    public String wlimitExecCondType;

    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>
     * 2�F�X�g�b�v����������<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���B<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * (�v�w�l�p�֑ؑO�����P��)<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���B<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�A�ݒ肳���B<BR>
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
     * (�T�Z��n���)<BR>
     * ��������̏ꍇ�ɕK�v<BR>
     */
    public String estimatedPrice;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004052001L;

    /**
     * @@roseuid 409F417C00A1
     */
    public WEB3EquityChangeInputResponse()
    {

    }
    public WEB3EquityChangeInputResponse(WEB3EquityChangeInputRequest l_request)
    {
        super(l_request);
    }
}
@
