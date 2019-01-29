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
filename	WEB3EquityCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������������m�F���X�|���X(WEB3EquityCancelOrderConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 ���_�� (���u) �V�K�쐬
Revesion History : 2004/12/08 �����iSAR�j�c�Č��Ή� �m��.�O�T�X���m��.�O�U�O
Revesion History : 2004/12/14 �K�� (SRA) �c�Č��Ή��@@No.385
                   2006/08/29 �����F(���u) ���f�� 972
                   2006/11/02 �����F(���u) ���f�� 948
                   2006/12/14 �đo�g(���u) ���f��1082
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.equity.message.WEB3EquityConfirmCommonResponse;

/**
 * �i����������������m�F���X�|���X�j�B<BR>
 * <BR>
 * ����������������m�F�����@@���X�|���X�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityCancelConfirmResponse extends WEB3EquityConfirmCommonResponse
{

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
     * <BR>
     * 0�F��ʁ@@1�F����@@5�F�X�g�b�N�I�v�V����<BR>
     */
    public String taxType;

    /**
     * (����敪)<BR>
     * <BR>
     * 1�F�����������@@2�F�����������@@99�F����O����<BR>
     */
    public String tradingType;

    /**
     * (��������)<BR>
     */
    public String orderQuantity;

    /**
     * (���o������)<BR>
     */
    public String partContQuantity;

    /**
     * (�����P���敪)<BR>
     * <BR>
     * 0:���s�@@1:�w�l<BR>
     */
    public String orderPriceDiv;

    /**
     * (�����P��)<BR>
     * <BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String limitPrice;

    /**
     * (�T�Z�뉿�P��)<BR>
     */
    public String estimatedBookPrice;

    /**
     * (�l�i����)<BR>
     * <BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@7:���s�c�����<BR>
     */
    public String priceCondType;

    /**
     * (���s����)<BR>
     * <BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String execCondType;

    /**
     * (���������敪)<BR>
     * <BR>
     * 1�F��������@@2�F�o����܂Œ���<BR>
     */
    public String expirationDateType;

    /**
     * (�����L������)<BR>
     * <BR>
     * ���������敪���u�o����܂Œ����v�̏ꍇ�ɐݒ�<BR>
     */
    public Date expirationDate;

    /**
     * (���������敪)<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;

    /**
     * (�t�w�l�p���������P��)<BR>
     * <BR>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * <BR>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (W�w�l�p���������P��)<BR>
     * <BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (W�w�l�p�����������Z�q)<BR>
     * <BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (W�w�l�p�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W�w�l�p�����P��)<BR>
     * <BR>
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
     * (�����敪)<BR>
     * 1:���ݒl�@@�@@2:���C�z�l�@@�@@3:���C�z�l�@@�@@4:�O���I�l<BR>
     */
    public String currentPriceDiv;
    
    /**
     * (����(���ݒl))<BR>
     */
    public String currentPrice;

    /**
     * (�O����)
     */
    public String comparedPreviousDay;

    /**
     * (�������(�������\����))<BR>
     */
    public Date currentPriceTime;

    /**
     * (���ݒl)
     */
    public String boardCurrentPrice;

    /**
     * (���ݒl����)
     */
    public Date boardCurrentPriceTime;

    /**
     * (���ݒl�敪)
     */
    public String boardCurrentPriceDiv;

    /**
     * (���ݒl�O����)
     */
    public String boardChange;

    /**
     * (�o����)
     */
    public String volume;

    /**
     * (�o��������)
     */
    public Date volumeTime;

    /**
     * (���C�z�l�^�C�g���敪)
     */
    public String askPriceTitle;

    /**
     * (���C�z�l)
     */
    public String askPrice;

    /**
     * (���C�z�l����)
     */
    public Date askPriceTime;

    /**
     * (���C�z�l�^�C�g���敪)
     */
    public String bidPriceTitle;

    /**
     * (���C�z�l)
     */
    public String bidPrice;

    /**
     * (���C�z�l����)
     */
    public Date bidPriceTime;

    /**
     * (��l�i)
     */
    public String basePrice;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_cancel_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405161153L;

    /**
     * @@roseuid 40AC537602DD
     */
    public WEB3EquityCancelConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 405023760250
     */
    public WEB3EquityCancelConfirmResponse(WEB3EquityCancelConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
