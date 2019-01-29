head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 解約注文明細クラス(WEB3RuitoSellOrderUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 解約注文明細<BR>
 */
public class WEB3RuitoSellOrderUnit extends Message
{

    /**
     * 注文日時<BR>
     */
    public Date orderDate;

    /**
     *  1:受付済(新規注文)　@       3:発注済(新規注文)<BR>　@　@　@ 
     *  6:発注失敗(新規注文)     12:受付済(取消注文)<BR>
     * 14:発注済(取消注文)　@　@    15:発注失敗(取消注文)<BR>
     * 30:MRF取消エラー　@　@　@      31:受付済(MRF解約有り)<BR>
     * 32:注文済(MRF解約有り)
     */
    public String orderState;

    /**
     * 注文数量区分<BR>
     * <BR>
     * 3: 金額　@4:口数<BR>
     */
    public String ruitoOrderQuantityType;

    /**
     * 注文数量<BR>
     */
    public String ruitoOrderQuantity;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922B610196
     */
    public WEB3RuitoSellOrderUnit()
    {

    }
}
@
