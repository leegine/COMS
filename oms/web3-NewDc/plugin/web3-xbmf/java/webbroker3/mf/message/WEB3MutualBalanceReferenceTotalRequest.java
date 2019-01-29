head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceTotalRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会残高合計リクエストクラス(WEB3MutualBalanceReferenceTotalRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 于美麗 (中訊) 新規作成
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル536
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信残高照会残高合計リクエスト)<BR>
 * 投信残高照会残高合計リクエストクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceTotalRequest extends WEB3GenRequest 
{    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_balance_reference_total";
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412281618L;

    /**
     * (投信・外貨MMF表示区分)<BR>
     * 投信･外貨MMF表示区分 <BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分 <BR>
     * <BR>
     * 0:投信のみ <BR>
     * 1:外貨MMFのみ <BR>
     * 2:両方 <BR>
     * <BR>
     * ※nullの場合、「0:投信のみ」とする<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

   /**
    * @@roseuid 41D13CC1029F
    */
   public WEB3MutualBalanceReferenceTotalRequest() 
   {
    
   }
   
   /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    *<BR>
    * @@return レスポンスオブジェクト
    */
   public WEB3GenResponse createResponse() 
   {
       return new WEB3MutualBalanceReferenceTotalResponse(this);
   }
}
@
