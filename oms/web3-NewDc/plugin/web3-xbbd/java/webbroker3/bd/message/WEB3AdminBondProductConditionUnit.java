head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������Ɖ���(WEB3AdminBondProductConditionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�������Ɖ���)<BR>
 * �������s
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductConditionUnit extends Message
{
    
    /**
     * (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)
     */
    public String productCode;
    
    /**
     * (HOST������1)<BR>
     * HOST������1
     */
    public String hostProductName1;
    
    /**
     * (�戵������)<BR>
     * �戵������
     */
    public String handlingProductName;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     */
    public String currencyCode;
    
    /**
     * (�戵�敪)<BR>
     * �戵�敪
     */
    public String tradeHandleDiv;
    
    /**
     * (���s���i)<BR>
     * ���s���i
     */
    public String issuePrice;
    
    /**
     * (����)<BR>
     * ����
     */
    public String coupon;
    
    /**
     * (���s��)<BR>
     * ���s��
     */
    public Date issueDate;
    
    /**
     * (���ғ�)<BR>
     * ���ғ�
     */
    public Date maturityDate;
    
    /**
     * (�N�ԗ�����)<BR>
     * �N�ԗ�����<BR>
     * <BR>
     * �s�莞��99999999
     */
    public String yearlyInterestPayments;
    
    /**
     * (������1)<BR>
     * ������1<BR>
     * <BR>
     * "0000"�̎��͕\�����Ȃ�
     */
    public String interestPaymentDay1;
    
    /**
     * (������2)<BR>
     * ������2<BR>
     * <BR>
     * "0000"�̎��͕\�����Ȃ�
     */
    public String interestPaymentDay2;
    
    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h
     */
    public String bondCategCode;
    
    /**
     * @@roseuid 44E3363B0280
     */
    public WEB3AdminBondProductConditionUnit() 
    {
     
    }
}
@
