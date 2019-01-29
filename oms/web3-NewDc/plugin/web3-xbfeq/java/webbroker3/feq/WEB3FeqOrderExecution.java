head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderExecution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式約定(WEB3FeqOrderExecution.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  張玲(中訊) 新規作成
                 : 2005/07/28 呉艶飛(中訊) レビュー
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderExecutionImpl;


/**
 * (外国株式約定)<BR>
 * 外国株式約定<BR>
 * 
 * @@ author 張玲 
 * @@ version 1.0 
 */
public class WEB3FeqOrderExecution extends FeqOrderExecutionImpl 
{
    /**
     * コンストラクタ<BR>
     * 
     */
    public WEB3FeqOrderExecution(long order_execution_id)
        throws DataQueryException, DataNetworkException
    {
        super(order_execution_id);
    }
    
    /**
     * コンストラクタ<BR>
     * 
     */
    public WEB3FeqOrderExecution(FeqOrderExecutionRow row)
    {
       super(row);
    }

    /**
     * (getOrderId)<BR>
     * （getOrderId）<BR>
     * 注文ＩＤを取得する。<BR>
     * <BR>
     * this.約定行.注文ＩＤを返却する。<BR>
     * @@return long
     * @@roseuid 4295CBBE01AC
     */
    public long getOrderId() 
    {
        return this.m_row.getOrderId();
    }
    
    /**
     * (is削除済)<BR>
     * 削除済かを判定する。<BR>
     * <BR>
     * this.約定行.削除フラグを返却する。<BR>
     * @@return boolean
     * @@roseuid 42BF9F5F0203
     */
    public boolean isDeleted() 
    {
        return BooleanEnum.TRUE.equals(this.m_row.getDeleteFlag()) ? true : false;
    }
}
@
