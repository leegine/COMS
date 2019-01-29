head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������Ǘ����׃��j�b�g�N���X(WEB3AdminTPAdvanceCustomerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/03/13 �{�{ �ē�(SCS) �V�K�쐬
 */
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * ���������Ǘ����׃��j�b�g
 */
public class WEB3AdminTPPaymentRequisitionManageDetailUnit  extends Message 
{
    
    /**
     * �c�Ɠ�
     */
    public Date bizDate;
    
    /**
     * ���������z
     */
    public String paymentRequisitionAmount;
    
    /**
     * �����敪
     * 
     * 1:���֋� 2:���ʗ��֋� 3:�Ǐ� 4:���֋��E�Ǐ� 5:���ʗ��֋��E�Ǐ�
     */
    public String paymentRequisitionDivision;
    
    /**
     * �a���
     */
    public String accountBalance;
    
    /**
     * SONAR�q��
     */
    public String sonarAccountBalance;
    
    /**
     * ���v��S����
     */
    public String dayTradeRestraint;
    
    /**
     * ���̑��S����
     */
    public String otherRestraint;
    
    /**
     * �ϑ��ۏ؋�
     */
    public String paidMarginDeposit;
    
    /**
     * ����ۏ؋��c
     */
    public String receiptMarginDepositRest;
    
    /**
     * �K�v�ۏ؋�
     */
    public String marginDeposit;
    
    /**
     * �������K�v�ۏ؋�
     */
    public String cashMarginDeposit;
    
    /**
     * ���ʑ��
     */
    public String contractAmount;
    
    /**
     * �ۏ؋��a����
     */
    public String marginDepositRate;
    
    /**
     * @@roseuid 4412A9CC0035
     */
    public WEB3AdminTPPaymentRequisitionManageDetailUnit() 
    {
     
    }

    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPAdvanceDetailUnit=");
        l_sb.append("accountBalance=" + this.accountBalance);
        l_sb.append(",sonarAccountBalance=" + this.sonarAccountBalance);
        l_sb.append(",dayTradeRestraint=" + this.dayTradeRestraint);
        l_sb.append(",otherRestraint=" + this.otherRestraint);
        l_sb.append(",paidMarginDeposit=" + this.paidMarginDeposit);
        l_sb.append(",receiptMarginDepositRest=" + this.receiptMarginDepositRest);
        l_sb.append(",marginDeposit=" + this.marginDeposit);
        l_sb.append(",cashMarginDeposit=" + this.cashMarginDeposit);
        l_sb.append(",contractAmount=" + this.contractAmount);
        l_sb.append(",marginDepositRate=" + this.marginDepositRate);
        return l_sb.toString();
    }
}
@
