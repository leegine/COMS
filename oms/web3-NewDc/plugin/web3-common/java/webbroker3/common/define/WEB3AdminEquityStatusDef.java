head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理区分定数定義インタフェイス(WEB3AdminEquityStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 劉剣（中訊）新規作成
Revision History : 2009/02/13 劉剣（中訊）ＤＢ更新仕様No.025
*/
package webbroker3.common.define;

/**
 * 処理区分定数定義インタフェイス<BR>
 * (注意情報通知キューテーブルの処理区分の參照)<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public interface WEB3AdminEquityStatusDef
{
    /**
     * 0：未処理<BR>
     */
    public final static String NOT_DEAL = "0";

    /**
     * 1：処理済<BR>
     */
    public final static String DEALT = "1";

    /**
     * 3：重複データ（処理対象外）<BR>
     */
    public final static String REPEAT_ERROR = "3";

    /**
     * 9：エラー<BR>
     */
    public final static String ERROR = "9";
}
@
