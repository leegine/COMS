head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�������(WEB3BondApplyBuyProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (������/���t�������)<BR>
 * ������/���t�������<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondApplyBuyProductInfo extends Message
{
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String productId;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h<BR>
     */
    public String bondCategCode;
    
    /**
     * (S&P)<BR>
     * S&P<BR>
     */
    public String sAndP;
    
    /**
     * (Moody's)<BR>
     * Moody's<BR>
     */
    public String moodys;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String coupon;
    
    /**
     * (�N�ԗ�����)<BR>
     * �N�ԗ�����<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (������1)<BR>
     * ������1<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (������2)<BR>
     * ������2<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (�\���P��)<BR>
     * �\���P��<BR>
     */
    public String tradeUnit;
    
    /**
     * (�Œ�\������)<BR>
     * �Œ�\������<BR>
     */
    public String minOrderQuantity;
    
    /**
     * (�ō��\������)<BR>
     * �ō��\������<BR>
     */
    public String maxOrderQuantity;
    
    /**
     * (����J�n��)<BR>
     * ����J�n��<BR>
     */
    public Date recruitStartDate;
    
    /**
     * (����I����)<BR>
     * ����I����<BR>
     */
    public Date recruitEndDate;
    
    /**
     * (���t�P��)<BR>
     * ���t�P��<BR>
     */
    public String buyPrice;
    
    /**
     * (���s��)<BR>
     * ���s��<BR>
     */
    public Date issueDate;
    
    /**
     * (���ғ�)<BR>
     * ���ғ�<BR>
     */
    public Date maturityDate;
    
    /**
     * (����\�敪)<BR>
     * ����\�敪<BR>
     * <BR>
     * 1�F����<BR>
     * 2�F���t<BR>
     */
    public String posibleDiv;
    
    /**
     * (������/���t�������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44BDBBAE0176
     */
    public WEB3BondApplyBuyProductInfo() 
    {
     
    }
}
@
