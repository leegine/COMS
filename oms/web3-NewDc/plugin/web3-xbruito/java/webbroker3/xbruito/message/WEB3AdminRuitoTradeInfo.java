head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投銘柄別売買情報(WEB3AdminRuitoTradeInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (累投銘柄別売買情報)<BR>
 * 累投銘柄別売買情報クラス
 */
public class WEB3AdminRuitoTradeInfo extends Message
{
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String ruitoProductCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名
     */
    public String ruitoProductName;
    
    /**
     * (買付開始日)<BR>
     * 買付開始日
     */
    public Date buyStartDate;

    /**
     * (買付終了日)<BR>
     * 買付終了日
     */
    public Date buyEndDate;
    
    /**
     * (解約開始日)<BR>
     * 解約開始日
     */
    public Date sellStartDate;
    
    /**
     * (解約終了日)<BR>
     * 解約終了日
     */
    public Date sellEndDate;
    
    /**
     * (買付可能区分)<BR>
     * 買付可能区分（当日）<BR>
     * 0：停止でない、1：停止中
     */
    public String curBuyPosDiv;
    
    /**
     * (解約可能区分)<BR>
     * 解約可能区分（当日）<BR>
     * 0：停止でない、1：停止中
     */
    public String curSellPosDiv;
    
    /**
     * (買付可能区分（翌日))<BR>
     * 買付可能区分（翌日）<BR>
     * 0：停止でない、1：停止中
     */
    public String nextBuyPosDiv;
    
    /**
     * (解約可能区分（翌日）)<BR>
     * 解約可能区分（翌日）<BR>
     * 0：停止でない、1：停止中
     */
    public String nextSellPosDiv;
    
    /**
     * (累投銘柄別売買情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922BD901A5
     */
    public WEB3AdminRuitoTradeInfo()
    {

    }    
}@
