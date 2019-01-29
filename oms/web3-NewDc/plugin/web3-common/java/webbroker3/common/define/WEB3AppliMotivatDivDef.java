head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AppliMotivatDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引動機@区分(WEB3AppliMotivatDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 取引動機@区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3AppliMotivatDivDef
{

    /**
     * 1：新聞
     */
    public final static String NEWSPAPER  = "1";

    /**
     * 2：雑誌
     */
    public final static String MAGAZINE  = "2";

    /**
     * 3：ＴＶ
     */
    public final static String TV  = "3";

    /**
     * 4：セミナー
     */
    public final static String SEMINAR  = "4";

    /**
     * 5：ホームページ
     */
    public final static String HOMEPAGE  = "5";

    /**
     * 9：その他
     */
    public final static String OTHER  = "9";

}@
