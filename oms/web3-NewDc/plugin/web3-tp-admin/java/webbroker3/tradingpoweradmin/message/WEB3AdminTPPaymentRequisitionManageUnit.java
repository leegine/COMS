head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ǘ����j�b�g�N���X(WEB3AdminTPPaymentRequisitionManageUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 �{�{ �ē�(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * ���������Ǘ����j�b�g
 */
public class WEB3AdminTPPaymentRequisitionManageUnit extends Message 
{
    
    /**
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * �ڋq��
     */
    public String accountName;
    
    /**
     * ���҃R�[�h
     */
    public String traderCode;
    
    /**
     * ����
     * 
     * 1:�O�� 2:�ؕ] 3:�M�p
     */
    public String attribute;
    
    /**
     * �����~
     */
    public String[] tradingPowerStop;

    /**
     * 20%���ꔭ����
     */
    public Date break20Day;
    
    /**
     * 20%����o�ߓ���
     */
    public String break20ElapsedDays;
    
    /**
     * 30%���ꔭ����
     */
    public Date break30Day;
    
    /**
     * 30%����o�ߓ���
     */
    public String break30ElapsedDays;
    
    public WEB3AdminTPPaymentRequisitionManageDetailUnit[] manageDetails;
    
    /**
     * @@roseuid 4412A9CB0238
     */
    public WEB3AdminTPPaymentRequisitionManageUnit() 
    {
     
    }

    /**
     * toString
     *
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPPaymentRequisitionManageUnit[");
        l_sb.append("branchCode=" + this.branchCode);
        l_sb.append("accountCode=" + this.accountCode);
        l_sb.append("accountName=" + this.accountName);
        l_sb.append("traderCode=" + this.traderCode);
        l_sb.append("attribute=" + this.attribute);
        l_sb.append("break20Day=" + this.break20Day);
        l_sb.append("break20ElapsedDays=" + this.break20ElapsedDays);
        l_sb.append("break30Day=" + this.break30Day);
        l_sb.append("break30ElapsedDays=" + this.break30ElapsedDays);
        l_sb.append("]");
        
        return l_sb.toString();
    }

}
@
