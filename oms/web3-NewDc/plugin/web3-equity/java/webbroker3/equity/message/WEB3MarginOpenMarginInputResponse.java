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
filename	WEB3MarginOpenMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K���������̓��X�|���X(WEB3MarginOpenMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
                   2006/12/25 �����F (���u) ���f�� 1085
*/

package webbroker3.equity.message;

/**
 * �i�M�p����V�K���������̓��X�|���X�j�B<br>
 * <br>
 * �M�p����V�K���������̓��X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputResponse extends WEB3MarginInputCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (�V�K���\�z)<BR>
     * �����̏ꍇ�͐V�K�����\�z�A�����̏ꍇ�͐V�K�����\�z
     */
    public String marginTradingPower;
    
    /**
     * (������)<BR>
     * �����w��̏ꍇ�g�p�B
     */
    public String productName;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * �������w��̏ꍇ�́A�Y��������������s��̂�<BR>
     */
    public String[] marketList;
    
    /**
     * (�����敪�ꗗ)<BR>
     * 0�F��� 1�F����
     */
    public String[] taxTypeList;
    
    /**
     * (�ٍψꗗ)<BR>
     * �M�p����ٍς̈ꗗ�B
     */
    public WEB3MarginRepaymentUnit[] repaymentList;
    
    /**
     * (���������敪�ꗗ)<BR>
     * 1�F��������@@2�F�o����܂Œ���
     */
    public String[] expirationDateTypeList;
    
    /**
     * (���������敪�ꗗ)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l
     */
    public String[] orderCondTypeList;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;

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
     * @@roseuid 4140477E0182
     */
    public WEB3MarginOpenMarginInputResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginOpenMarginInputResponse(WEB3MarginOpenMarginInputRequest l_request)
    {
        super(l_request);
    }
}
@
