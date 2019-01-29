head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsOrders.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ꗗ(WEB3RlsOrders.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 �V�� �h�O(FLJ) �V�K�쐬
*/
package webbroker3.rlsgateway.connector;

import webbroker3.rlsgateway.data.RlsCondOrderRow;
import webbroker3.rlsgateway.data.RlsOmsOrderRow;
import webbroker3.rlsgateway.data.RlsPriceCondRow;


/**
 *
 * �����ꗗ
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsOrders
{

    /**
     * �e����
     */
    private RlsCondOrderRow parentRlsCondOrderRow;

    /**
     * �q����
     */
    private RlsCondOrderRow rlsCondOrderRow;
    
    /**
     * �t�w�l����
     */
    private RlsPriceCondRow rlsPriceCondRow;

    /**
     * �V���v���������e
     */
    private RlsOmsOrderRow rlsOmsOrderRow;
    
    /**
     * old�e����
     */
    private RlsCondOrderRow oldParentRlsCondOrderRow;

    /**
     * old�q����
     */
    private RlsCondOrderRow oldRlsCondOrderRow;
    
    /**
     * old�t�w�l����
     */
    private RlsPriceCondRow oldRlsPriceCondRow;

    /**
     * old�V���v���������e
     */
    private RlsOmsOrderRow oldRlsOmsOrderRow;

    
    /**
     * 
     */
    public WEB3RlsOrders()
    {
        super();
    }
    
    /**
     * @@return oldParentRlsCondOrderRow ��߂��܂��B
     */
    public RlsCondOrderRow getOldParentRlsCondOrderRow()
    {
        return oldParentRlsCondOrderRow;
    }
    /**
     * @@param oldParentRlsCondOrderRow oldParentRlsCondOrderRow ��ݒ�B
     */
    public void setOldParentRlsCondOrderRow(
            RlsCondOrderRow oldParentRlsCondOrderRow)
    {
        this.oldParentRlsCondOrderRow = oldParentRlsCondOrderRow;
    }
    /**
     * @@return oldRlsCondOrderRow ��߂��܂��B
     */
    public RlsCondOrderRow getOldRlsCondOrderRow()
    {
        return oldRlsCondOrderRow;
    }
    /**
     * @@param oldRlsCondOrderRow oldRlsCondOrderRow ��ݒ�B
     */
    public void setOldRlsCondOrderRow(RlsCondOrderRow oldRlsCondOrderRow)
    {
        this.oldRlsCondOrderRow = oldRlsCondOrderRow;
    }
    /**
     * @@return oldRlsOmsOrderRow ��߂��܂��B
     */
    public RlsOmsOrderRow getOldRlsOmsOrderRow()
    {
        return oldRlsOmsOrderRow;
    }
    /**
     * @@param oldRlsOmsOrderRow oldRlsOmsOrderRow ��ݒ�B
     */
    public void setOldRlsOmsOrderRow(RlsOmsOrderRow oldRlsOmsOrderRow)
    {
        this.oldRlsOmsOrderRow = oldRlsOmsOrderRow;
    }
    /**
     * @@return oldRlsPriceCondRow ��߂��܂��B
     */
    public RlsPriceCondRow getOldRlsPriceCondRow()
    {
        return oldRlsPriceCondRow;
    }
    /**
     * @@param oldRlsPriceCondRow oldRlsPriceCondRow ��ݒ�B
     */
    public void setOldRlsPriceCondRow(RlsPriceCondRow oldRlsPriceCondRow)
    {
        this.oldRlsPriceCondRow = oldRlsPriceCondRow;
    }
    /**
     * @@return parentRlsCondOrderRow ��߂��܂��B
     */
    public RlsCondOrderRow getParentRlsCondOrderRow()
    {
        return parentRlsCondOrderRow;
    }
    /**
     * @@param parentRlsCondOrderRow parentRlsCondOrderRow ��ݒ�B
     */
    public void setParentRlsCondOrderRow(RlsCondOrderRow parentRlsCondOrderRow)
    {
        this.parentRlsCondOrderRow = parentRlsCondOrderRow;
    }
    /**
     * @@return rlsCondOrderRow ��߂��܂��B
     */
    public RlsCondOrderRow getRlsCondOrderRow()
    {
        return rlsCondOrderRow;
    }
    /**
     * @@param rlsCondOrderRow rlsCondOrderRow ��ݒ�B
     */
    public void setRlsCondOrderRow(RlsCondOrderRow rlsCondOrderRow)
    {
        this.rlsCondOrderRow = rlsCondOrderRow;
    }
    /**
     * @@return rlsOmsOrderRow ��߂��܂��B
     */
    public RlsOmsOrderRow getRlsOmsOrderRow()
    {
        return rlsOmsOrderRow;
    }
    /**
     * @@param rlsOmsOrderRow rlsOmsOrderRow ��ݒ�B
     */
    public void setRlsOmsOrderRow(RlsOmsOrderRow rlsOmsOrderRow)
    {
        this.rlsOmsOrderRow = rlsOmsOrderRow;
    }
    /**
     * @@return rlsPriceCondRow ��߂��܂��B
     */
    public RlsPriceCondRow getRlsPriceCondRow()
    {
        return rlsPriceCondRow;
    }
    /**
     * @@param rlsPriceCondRow rlsPriceCondRow ��ݒ�B
     */
    public void setRlsPriceCondRow(RlsPriceCondRow rlsPriceCondRow)
    {
        this.rlsPriceCondRow = rlsPriceCondRow;
    }
    
    
    /**
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("WEB3RlsOrders[");
        sb.append("rlsCondOrderRow=").append(this.getRlsCondOrderRow());
        sb.append(", rlsOmsOrderRow=").append(this.getRlsOmsOrderRow());
        sb.append(", parentRlsCondOrderRow=").append(this.getParentRlsCondOrderRow());
        sb.append(", rlsPriceCondRow=").append(this.getRlsPriceCondRow());
        sb.append(", oldRlsCondOrderRow=").append(this.getOldRlsCondOrderRow());
        sb.append(", oldRlsOmsOrderRow=").append(this.getOldRlsOmsOrderRow());
        sb.append(", oldParentRlsCondOrderRow=").append(this.getOldParentRlsCondOrderRow());
        sb.append(", oldRlsPriceCondRow=").append(this.getOldRlsPriceCondRow());
        sb.append("]");
        return sb.toString();
    }
}
@
