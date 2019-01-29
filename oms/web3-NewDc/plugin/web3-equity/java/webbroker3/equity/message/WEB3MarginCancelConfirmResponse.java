head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p�����������m�F���X�|���X�N���X(WEB3MarginCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
Revesion History : 2004/12/13 �K�� (SRA) �C��
Revesion History : 2006/11/02 �����F(���u) ���f�� 948
Revesion History : 2006/12/14 �đo�g(���u) ���f��1082
Revesion History : 2007/06/05 �����q (���u) �d�l�ύX�E���f��1166
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * �i�M�p�����������m�F���X�|���X�j�B<br>
 * <br>
 * �M�p�����������m�F���X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginCancelConfirmResponse extends WEB3MarginConfirmCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_cancelConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101625L;      
    /**
     * (�����R�[�h)<BR>
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
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����
     */
    public String taxType;
    
    /**
     * (����敪)<BR>
     * <BR>
     * 3�F�V�K���������@@4�F�V�K��������<BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj<BR>
     * 7�F���������@@8�F���n����<BR>
     * <BR>
     * �iOrderTypeEnum�ɂĒ�`�j<BR>
     */
    public String tradingType;
    
    /**
     * (�ٍ�)<BR>
     * �M�p����ٍ�
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (��������)
     */
    public String orderQuantity;
    
    /**
     * (���o������)<BR>
     * <BR>
     * ��肪����ꍇ�ݒ�<BR>
     */
    public String partContQuantity;
    
    /**
     * (�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * <BR>
     * �����P���敪���u1�F�w�l�v�̏ꍇ�ɐݒ�<BR>
     */
    public String limitPrice;
    
    /**
     * (���Ϗ����敪)<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     * <BR>
     * �ԍρA�������n�̏ꍇ�ݒ�<BR>
     */
    public String closingOrder;
    
    /**
     * (�������׈ꗗ)<BR>
     * �M�p����������ׂ̈ꗗ<BR>
     * <BR>
     * �ԍρA�������n�̏ꍇ�ݒ�<BR>
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (�����挻�n�������敪)<BR>
     * 0�F��ʁ@@1�F����<BR>
     * <BR>
     * �������n�̏ꍇ�ݒ�<BR>
     */
    public String swapTaxType;
    
	/**
	 * (�l�i����)<BR>
	 * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@ 7:���s�c�����
	 */
	public String priceCondType;
    
    /**
     * (���s����)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s)<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * 1�F��������@@2�F�o����܂Œ���<BR>
     */
    public String expirationDateType;
    
    /**
     * (�����L������)<BR>
     * ���������敪���u2�F�o����܂Œ����v�̏ꍇ�ɐݒ�<BR>
     */
    public Date expirationDate;
    
    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (�t�w�l�p���������P��)<BR>
     * ���������敪���A�u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * <BR>
     * ���������敪���A�u1�F�t�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (�v�w�l�p���������P��)<BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * <BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (�v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * <BR>
     * ���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (�v�w�l�p�����P��)<BR>
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
     * (�����敪)<BR>
     * �����敪 <BR>
     * �i1�F���ݒl <BR>
     * �@@2�F���C�z�l <BR>
     * �@@3�F���C�z�l <BR>
     * �@@4�F�O���I�l�j<BR>
     */
    public String currentPriceDiv;
    
    /**
     * (�����i���ݒl�j)<BR>
     * ����<BR>
     */
    public String currentPrice;
    
    /**
     * (�O����)
     */
    public String comparedPreviousDay;
    
    /**
     * (������ԁi�������\���ԁj)<BR>
     * �������<BR>
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
     * (���ӕ����\���敪)<BR>
     * null�F���ӕ�����\���@@�@@3�F�a����s�����ӕ����\��<BR>
     */
    public String attentionObjectionType;
    
    /**
     * (�a����s���z)<BR>
     * �]�͂̕s�����z<BR>
     */
    public String accountBalanceInsufficiency;

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
     * @@roseuid 414046A801CB
     */
    public WEB3MarginCancelConfirmResponse() 
    {
     
    }

    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginCancelConfirmResponse(WEB3MarginCancelConfirmRequest l_request)
    {
        super(l_request);
    } 
}
@
