head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : AIOソートキー(WEB3AioSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 艾興 (中訊) 新規作成
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (AIOソートキー)<BR>
 * AIOソートキークラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3AioSortKeyUnit extends Message
{

    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名をキー項目とする。 <BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;

    /**
     * (昇順/降順)<BR>
     * A： 昇順<BR>
     * D： 降順
     */
    public String ascDesc;

    /**
     * @@roseuid 41EC84F90186
     */
    public WEB3AioSortKeyUnit()
    {

    }
}
@
