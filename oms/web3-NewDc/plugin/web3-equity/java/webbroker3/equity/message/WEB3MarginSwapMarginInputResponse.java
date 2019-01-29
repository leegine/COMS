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
filename	WEB3MarginSwapMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�������̓��X�|���X(WEB3MarginSwapMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
Revesion History : 2004/12/13 �K�� (SRA) �C��
                 : 2007/01/11 ��іQ (���u) ���f�� No.1082
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����������n�������̓��X�|���X�j�B<br>
 * <br>
 * �M�p����������n�������̓��X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginInputResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (�����\�z)<BR>
     * ����敪���u7�F���������v�̎��ݒ�
     */
    public String swapLongTradingPower;
    
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
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����
     */
    public String taxType;
    
    /**
     * (����敪)<BR>
     * 7�F���������@@8�F���n����<BR>
     * �iOrderTypeEnum�ɂĒ�`�j<BR>
     */
    public String tradingType;
    
    /**
     * �ٍ�<BR>
     * �i�M�p����ٍρj<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (�����挻�n�������敪�ꗗ)<BR>
     * 0�F��ʁ@@1�F����<BR>
     * <BR>
     * �����̎��͌���������敪<BR>
     * ���n�̎��͌��n�������敪<BR>
     */
    public String[] swapTaxTypeList;
    
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
     * (����(���ݒl))<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     * <BR>
     * �����w��̏ꍇ�g�p�B<BR>
     */
    public String currentPrice;
    
    /**
     * (�O����)<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     * <BR>
     * �����w��̏ꍇ�g�p�B<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (�������(�������\����))<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     * <BR>
     * �����w��̏ꍇ�g�p�B<BR>
     */
    public Date currentPriceTime;

    /**
     * (���ݒl)<BR>
     */
    public String boardCurrentPrice;

    /**
     * (���ݒl����)<BR>
     */
    public Date boardCurrentPriceTime;

    /**
     * (���ݒl�敪)<BR>
     */
    public String boardCurrentPriceDiv;

    /**
     * (���ݒl�O����)<BR>
     */
    public String boardChange;

    /**
     * (�o����)<BR>
     */
    public String volume;

    /**
     * (�o��������)<BR>
     */
    public Date volumeTime;

    /**
     * (���C�z�l�^�C�g���敪)<BR>
     */
    public String askPriceTitle;

    /**
     * (���C�z�l)<BR>
     */
    public String askPrice;

    /**
     * (���C�z�l����)<BR>
     */
    public Date askPriceTime;

    /**
     * (���C�z�l�^�C�g���敪)<BR>
     */
    public String bidPriceTitle;

    /**
     * (���C�z�l)<BR>
     */
    public String bidPrice;

    /**
     * (���C�z�l����)<BR>
     */
    public Date bidPriceTime;

    /**
     * (��l�i)<BR>
     */
    public String basePrice;

    /**
     * (�������׈ꗗ)
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ
     */
    public String[] messageSuspension;
    
	/**
	 * (�C���T�C�_�[�x���\���t���O)<BR>
	 * true�F�x���\���v�@@�@@�@@false�F�x���\���s�v
	 */
	public boolean insiderWarningFlag;
	
    /**
     * @@roseuid 414044F002C2
     */
    public WEB3MarginSwapMarginInputResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginSwapMarginInputResponse(WEB3MarginSwapMarginInputRequest l_request)
    {
        super(l_request);
    }
}
@
