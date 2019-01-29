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
filename	WEB3RuitoSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投ソートキークラス(WEB3RuitoSortKey)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * 累投ソートキー<BR>
 */
public class WEB3RuitoSortKey extends Message
{

    /**
     * 当クラスを利用したリクエスト対してのレスポンスクラス中の<BR>
     * シンボル名をキー項目とする。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;

    /**
     * A：昇順　@D：降順<BR>
     */
    public String ascDesc;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922BD901A5
     */
    public WEB3RuitoSortKey()
    {

    }
}
@
