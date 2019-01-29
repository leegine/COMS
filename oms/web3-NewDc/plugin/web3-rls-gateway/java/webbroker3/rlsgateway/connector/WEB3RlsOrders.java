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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文一覧(WEB3RlsOrders.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 齋藤 栄三(FLJ) 新規作成
*/
package webbroker3.rlsgateway.connector;

import webbroker3.rlsgateway.data.RlsCondOrderRow;
import webbroker3.rlsgateway.data.RlsOmsOrderRow;
import webbroker3.rlsgateway.data.RlsPriceCondRow;


/**
 *
 * 注文一覧
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsOrders
{

    /**
     * 親注文
     */
    private RlsCondOrderRow parentRlsCondOrderRow;

    /**
     * 子注文
     */
    private RlsCondOrderRow rlsCondOrderRow;
    
    /**
     * 逆指値条件
     */
    private RlsPriceCondRow rlsPriceCondRow;

    /**
     * シンプル注文内容
     */
    private RlsOmsOrderRow rlsOmsOrderRow;
    
    /**
     * old親注文
     */
    private RlsCondOrderRow oldParentRlsCondOrderRow;

    /**
     * old子注文
     */
    private RlsCondOrderRow oldRlsCondOrderRow;
    
    /**
     * old逆指値条件
     */
    private RlsPriceCondRow oldRlsPriceCondRow;

    /**
     * oldシンプル注文内容
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
     * @@return oldParentRlsCondOrderRow を戻します。
     */
    public RlsCondOrderRow getOldParentRlsCondOrderRow()
    {
        return oldParentRlsCondOrderRow;
    }
    /**
     * @@param oldParentRlsCondOrderRow oldParentRlsCondOrderRow を設定。
     */
    public void setOldParentRlsCondOrderRow(
            RlsCondOrderRow oldParentRlsCondOrderRow)
    {
        this.oldParentRlsCondOrderRow = oldParentRlsCondOrderRow;
    }
    /**
     * @@return oldRlsCondOrderRow を戻します。
     */
    public RlsCondOrderRow getOldRlsCondOrderRow()
    {
        return oldRlsCondOrderRow;
    }
    /**
     * @@param oldRlsCondOrderRow oldRlsCondOrderRow を設定。
     */
    public void setOldRlsCondOrderRow(RlsCondOrderRow oldRlsCondOrderRow)
    {
        this.oldRlsCondOrderRow = oldRlsCondOrderRow;
    }
    /**
     * @@return oldRlsOmsOrderRow を戻します。
     */
    public RlsOmsOrderRow getOldRlsOmsOrderRow()
    {
        return oldRlsOmsOrderRow;
    }
    /**
     * @@param oldRlsOmsOrderRow oldRlsOmsOrderRow を設定。
     */
    public void setOldRlsOmsOrderRow(RlsOmsOrderRow oldRlsOmsOrderRow)
    {
        this.oldRlsOmsOrderRow = oldRlsOmsOrderRow;
    }
    /**
     * @@return oldRlsPriceCondRow を戻します。
     */
    public RlsPriceCondRow getOldRlsPriceCondRow()
    {
        return oldRlsPriceCondRow;
    }
    /**
     * @@param oldRlsPriceCondRow oldRlsPriceCondRow を設定。
     */
    public void setOldRlsPriceCondRow(RlsPriceCondRow oldRlsPriceCondRow)
    {
        this.oldRlsPriceCondRow = oldRlsPriceCondRow;
    }
    /**
     * @@return parentRlsCondOrderRow を戻します。
     */
    public RlsCondOrderRow getParentRlsCondOrderRow()
    {
        return parentRlsCondOrderRow;
    }
    /**
     * @@param parentRlsCondOrderRow parentRlsCondOrderRow を設定。
     */
    public void setParentRlsCondOrderRow(RlsCondOrderRow parentRlsCondOrderRow)
    {
        this.parentRlsCondOrderRow = parentRlsCondOrderRow;
    }
    /**
     * @@return rlsCondOrderRow を戻します。
     */
    public RlsCondOrderRow getRlsCondOrderRow()
    {
        return rlsCondOrderRow;
    }
    /**
     * @@param rlsCondOrderRow rlsCondOrderRow を設定。
     */
    public void setRlsCondOrderRow(RlsCondOrderRow rlsCondOrderRow)
    {
        this.rlsCondOrderRow = rlsCondOrderRow;
    }
    /**
     * @@return rlsOmsOrderRow を戻します。
     */
    public RlsOmsOrderRow getRlsOmsOrderRow()
    {
        return rlsOmsOrderRow;
    }
    /**
     * @@param rlsOmsOrderRow rlsOmsOrderRow を設定。
     */
    public void setRlsOmsOrderRow(RlsOmsOrderRow rlsOmsOrderRow)
    {
        this.rlsOmsOrderRow = rlsOmsOrderRow;
    }
    /**
     * @@return rlsPriceCondRow を戻します。
     */
    public RlsPriceCondRow getRlsPriceCondRow()
    {
        return rlsPriceCondRow;
    }
    /**
     * @@param rlsPriceCondRow rlsPriceCondRow を設定。
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
