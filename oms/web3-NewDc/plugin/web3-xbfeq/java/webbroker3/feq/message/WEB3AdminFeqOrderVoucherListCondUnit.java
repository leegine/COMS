head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListCondUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式当日注文伝票一覧条件(WEB3AdminFeqOrderVoucherListCondUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (外国株式当日注文伝票一覧条件)<BR>
 * 外国株式当日注文伝票一覧条件クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListCondUnit extends Message 
{
    
    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;
    
    /**
     * (発注日（自）)<BR>
     * 画面にて入力された発注日（自）
     */
    public Date orderBizDateFrom;
    
    /**
     * (発注日（至）)<BR>
     * 画面にて入力された発注日（至）
     */
    public Date orderBizDateTo;
    
    /**
     * (外国株式当日注文伝票一覧条件)<BR>
     * コンストラクタ
     * @@roseuid 420214FC000E
     */
    public WEB3AdminFeqOrderVoucherListCondUnit() 
    {
     
    }
}
@
