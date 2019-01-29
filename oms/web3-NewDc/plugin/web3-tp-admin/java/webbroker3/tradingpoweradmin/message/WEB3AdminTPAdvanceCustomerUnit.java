head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���֋��ڋq���j�b�g�N���X(WEB3AdminTPAdvanceCustomerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/02/08 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���֋��ڋq���j�b�g)<BR>
 * ���֋��ڋq���j�b�g<BR>
 */
public class WEB3AdminTPAdvanceCustomerUnit extends Message
{

    /**
     * (�]�͌v�Z����ID)
     */
    public String calcResultId;
    
    /**
     * (���X�R�[�h)
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)
     */
    public String accountCode;
    
    /**
     * (�ڋq��)
     */
    public String accountName;
    
    /**
     * (���҃R�[�h)
     */
    public String traderCode;
    
    /**
     * (�a��،��ڋq�敪)
     */
    public String depositDiv;
    
    /**
     * (���֋��ڋq���׈ꗗ)
     */
    public WEB3AdminTPAdvanceCustomerDetailUnit[] advanceCustomerDetailUnits;
    
    /**
     * (�R���X�g���N�^)
     */
    public WEB3AdminTPAdvanceCustomerUnit()
    {
    }
    
    /**
     * toString
     *
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPAdvanceCustomerUnit[");
        l_sb.append("branchCode=" + this.branchCode);
        l_sb.append(",accountCode=" + this.accountCode);
        l_sb.append(",accountName=" + this.accountName);
        l_sb.append(",calcResultId=" + this.calcResultId);
        l_sb.append(",depositDiv=" + this.depositDiv);
        l_sb.append(",traderCode=" + this.traderCode);
        for(int i = 0; i < this.advanceCustomerDetailUnits.length; i++)
        {
            l_sb.append(",advanceCustomerDetailUnits[" + i + "]=" + this.advanceCustomerDetailUnits[i]);            
        }
        l_sb.append("]");
        
        return l_sb.toString();
    }
    
}
@
