head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����ԍϒ������̓��X�|���X�N���X(WEB3MarginCloseMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 ������ (���u) �V�K�쐬
Revesion History : 2004/12/13 �K�� (SRA) �C��
*/
package webbroker3.equity.message;


/**
 * �i�M�p����ԍϒ������̓��X�|���X�j�B<br>
 * <br>
 * �M�p����ԍϒ������̓��X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginCloseMarginInputResponse extends WEB3MarginInputCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101757L;    
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
     * <BR>
     * 5�F�����ԍϒ����i���ԍρj�@@6�F�����ԍϒ����i���ԍρj<BR>
     */
    public String tradingType;
    
    /**
     * (�ٍ�)<BR>
     * �i�M�p����ٍρj<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (���������敪�ꗗ)<BR>
     * 1�F��������@@2�F�o����܂Œ���<BR>
     */
    public String[] expirationDateTypeList;
    
    /**
     * (���������敪�ꗗ)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String[] orderCondTypeList;
    
    /**
     * (����(���ݒl))<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     * <BR>
     * �����w��̏ꍇ�g�p�B<BR>
     */
    //�X�[�p�[�N���X�Ɉړ�
    //public String currentPrice;
    
    /**
     * (�O����)<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     * <BR>
     * �����w��̏ꍇ�g�p�B<BR>
     */
	//�X�[�p�[�N���X�Ɉړ�
    //public String comparedPreviousDay;
    
    /**
     * (�������(�������\����))<BR>
     * �l�����Ă��Ȃ��Ƃ��͖��ݒ�B<BR>
     * <BR>
     * �����w��̏ꍇ�g�p�B<BR>
     */
	//�X�[�p�[�N���X�Ɉړ�
    //public Date currentPriceTime;
    
    /**
     * (�������׈ꗗ)<BR>
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * @@roseuid 4140485A012E
     */
    public WEB3MarginCloseMarginInputResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginCloseMarginInputResponse(WEB3MarginCloseMarginInputRequest l_request)
    {
        super(l_request);
    }
}
@
