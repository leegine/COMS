head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���֋��ڋq���׃��j�b�g�N���X(WEB3AdminTPAdvanceCustomerDetailUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/02/08 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���֋��ڋq���׃��j�b�g)<BR>
 * ���֋��ڋq���׃��j�b�g<BR>
 */
public class WEB3AdminTPAdvanceCustomerDetailUnit extends Message
{

    /**
     * (�����q���c)
     */
    public String realAccountBalance;

    /**
     * (�a��،��]���z)
     */
    public String trustSecurityAsset;

    /**
     * (����ۏ؋�)
     */
    public String receiptMarginDeposit;
    
    /**
     * (�ۏ؋��ێ��K�v�z)
     */
    public String marginMaintenanceAmount;
    
    /**
     * (�ۏ؋��a����)
     */
    public String marginDepositRate;
    
    /**
     * (���֋�)
     */
    public String debitAmount;
    
    /**
     * (���ʗ��֋�)
     */
    public String specialDebitAmount;
    
    /**
     * (�ۏ؋������z)
     */
    public String marginClaimedAmount;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3AdminTPAdvanceCustomerDetailUnit()
    {
    }

    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPAdvanceCustomerDetailUnit[");
        l_sb.append("debitAmount=" + this.debitAmount);
        l_sb.append(",specialDebitAmount=" + this.specialDebitAmount);
        l_sb.append(",receiptMarginDeposit=" + this.receiptMarginDeposit);
        l_sb.append(",marginMaintenanceAmount=" + this.marginMaintenanceAmount);
        l_sb.append(",marginDepositRate=" + this.marginDepositRate);
        l_sb.append(",trustSecurityAsset=" + this.trustSecurityAsset);
        l_sb.append(",marginClaimedAmount=" + this.marginClaimedAmount);
        l_sb.append(",realAccountBalance=" + this.realAccountBalance);
        l_sb.append("]");
        
        return l_sb.toString();
    }
    
    
}
@
