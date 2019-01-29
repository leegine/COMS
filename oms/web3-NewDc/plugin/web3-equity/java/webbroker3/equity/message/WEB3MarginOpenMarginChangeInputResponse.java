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
filename	WEB3MarginOpenMarginChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p��������V�K�����̓��X�|���X�N���X(WEB3MarginOpenMarginChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
                   2006/11/03 �����F(���u) ���f�� 948
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * �i�M�p��������V�K�����̓��X�|���X�j�B<br>
 * <br>
 * �M�p��������V�K�����̓��X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginOpenMarginChangeInputResponse extends WEB3MarginInputCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginChangeInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (�V�K���\�z)
     */
    public String marginTradingPower;
    
    /**
     * (�����R�[�h)
     */
    public String productCode;
    
    /**
     * (������)
     */
    public String productName;
    
    /**
     * (�s��R�[�h)
     */
    public String marketCode;
    
    /**
     * (�����敪)
     */
    public String taxType;
    
    /**
     * (����敪)<BR>
     * <BR>
     * 3�F�V�K���������@@4�F�V�K��������<BR>
     * �iOrderTypeEnum�ɂĒ�`�j
     */
    public String tradingType;
    
    /**
     * (�ٍ�)<BR>
     * �i�M�p����ٍρj<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (��������)
     */
    public String orderQuantity;
    
    /**
     * (���o������)<BR>
     */
    public String partContQuantity;
    
    /**
     * (�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@1�F�w�l
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * <BR>
     * �����P���敪���u�w�l�v�̏ꍇ�ɐݒ�
     */
    public String limitPrice;
    
    /**
     * (�l�i����)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@ 7:���s�c�����
     */
    public String priceCondType;
    
    /**
     * (���s����)<BR>
     * <BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * <BR>
     * 1�F��������@@2�F�o����܂Œ���
     */
    public String expirationDateType;
    
    /**
     * (�����L������)<BR>
     * <BR>
     * ���������敪���u�o����܂Œ����v�̏ꍇ�ɐݒ�
     */
    public Date expirationDate;
    
    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l
     */
    public String orderCondType;
    
    /**
     * (�t�w�l�p���������P��)<BR.>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * <BR>
     * ���������敪���u1�F�t�w�l�v�̏ꍇ�ݒ肳���
     */
    public String stopOrderCondOperator;
    
    /**
     * (W�w�l�p���������P��)<BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (W�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * <BR>
     * ���������敪���u2�FW�w�l�v�̏ꍇ�ݒ肳���
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (W�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * ���������P�����A�u2�FW�w�l�v�̏ꍇ�ݒ肳���
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W�w�l�p�����P��)<BR>
     * �v�w�l�p�����P���敪���A�u1�F�w�l�v�̏ꍇ�ݒ肳���
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
     * ���������敪�܂��͌����������敪���u2�FW�w�l�v�̏ꍇ�A�ݒ肳���B<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * <BR>
     * ���������敪�܂��͌����������敪���u2�FW�w�l�v�̏ꍇ�A�ݒ肳���B<BR>
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
     * (�T�Z��n���)<BR>
     * �����O�̊T�Z��n�����ݒ�B
     */
    public String estimatedPrice;
    
    /**
     * (�����i���ݒl�j)<BR>
     * �l�����Ă��Ȃ��Ƃ��͊�l��ݒ�B<BR>
     * <BR>
     * �����w��̏ꍇ�g�p�B
     */
    //�X�[�o�[�N���X�Ɉړ�
    //public String currentPrice;
    
    /**
     * (�O����)<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     * <BR>
     * �����w��̏ꍇ�g�p�B
     */
	//�X�[�o�[�N���X�Ɉړ�
    //public String comparedPreviousDay;
    
    /**
     * (������ԁi�������\���ԁj)<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     * <BR>
     * �����w��̏ꍇ�g�p�B
     */
	//�X�[�o�[�N���X�Ɉړ�
    //public Date currentPriceTime;
    
    /**
     * @@roseuid 4140453A01BA
     */
    public WEB3MarginOpenMarginChangeInputResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginOpenMarginChangeInputResponse(WEB3MarginOpenMarginChangeInputRequest l_request)
    {
        super(l_request);
    }
}
@
