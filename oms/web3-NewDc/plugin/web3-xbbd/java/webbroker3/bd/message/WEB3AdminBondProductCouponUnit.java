head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.55.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductCouponUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������(WEB3AdminBondProductCouponUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (����������)<BR>
 * �����������N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductCouponUnit extends Message
{
    
    /**
     * (������)<BR>
     * ������
     */
    public Date interestPaymentDay;
    
    /**
     * (����)<BR>
     * ����
     */
    public String coupon;
    
    /**
     * @@roseuid 44E3363A029F
     */
    public WEB3AdminBondProductCouponUnit() 
    {
     
    }
}
@
