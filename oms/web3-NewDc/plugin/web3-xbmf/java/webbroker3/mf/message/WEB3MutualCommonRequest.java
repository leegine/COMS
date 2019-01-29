head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託信託共通リクエストクラス(WEB3MutualCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 韋念瓊 (中訊) 新規作成
                   2004/08/24 黄建 (中訊) レビュー  
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 投資信託信託共通リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */
public class WEB3MutualCommonRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_common";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408170937L;
    
    /**
     * (ID)<BR>
     * 投信資産ID <BR>
     * <BR>
     * 解約、乗換時のみ使用。買付時はNull<BR>
     */
    public String id;

    /**
     * 投信銘柄コード<BR>
     * <BR>
     * (画面では非表示)<BR>
     */
    public String mutualProductCode;
    
    /**
     * 指定方法@<BR>
     * <BR>
     * 2:全部　@3:金額　@4:口数<BR>
     * (買付の場合、”2:全部”は有りえない)<BR>
     */
    public String specifyDiv;
    
    /**
     * (買付・解約・買取・乗換)数量
     */
    public String mutualOrderQuantity;
    
    /**
     * 発注日<BR>
     * <BR>
     * 入力レスポンスの処理で使用した値を格納する。<BR>
     * （画面では非表示）<BR>
     */
    public Date orderedDate;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A9AB66001B
     */
    public WEB3MutualCommonRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41065112008C
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
