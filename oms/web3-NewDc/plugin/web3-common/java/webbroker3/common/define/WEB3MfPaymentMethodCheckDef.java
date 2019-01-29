head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MfPaymentMethodCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信：受渡方法@チェック実施定数定義インタフェイス(WEB3MfPaymentMethodCheckDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/16 趙林鵬(中訊) 新規作成 ＤＢレイアウトNo.667
*/

package webbroker3.common.define;

/**
 * 投信：受渡方法@チェック実施定数定義インタフェイス<BR>
 * (部店用プリファ@レンステーブルのプリファ@レンスの値の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3MfPaymentMethodCheckDef
{
    /**
     * 0：受渡方法@チェック要
     */
    public final static String DEFAULT = "0";

    /**
     * 1：受渡方法@チェック不要
     */
    public final static String NO_CHECK = "1";
}@
