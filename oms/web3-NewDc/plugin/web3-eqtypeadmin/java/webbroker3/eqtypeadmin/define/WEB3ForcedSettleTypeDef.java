head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForcedSettleTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済処理区分定数定義インタフェース(WEB3ForcedSettleTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/25 齊珂 (中訊) 新規作成
*/

package webbroker3.eqtypeadmin.define;

/**
 * (強制決済処理区分)<BR>
 * 強制決済処理区分クラス<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public interface WEB3ForcedSettleTypeDef
{
    /**
     * オンライン開始前
     */
    public final static String BEFORE_ONLINE = "1";

    /**
     * 休憩時間帯
     */
    public final static String REST_TIMEZONE = "2";
}
@
