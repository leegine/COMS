head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SecondDepositMarginOpenTpStopDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 第二水準追証発生時の新規建て余力停止定数定義インタフェイス(WEB3SecondDepositMarginOpenTpStopDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/02 趙林鵬(中訊) 新規作成 ＤＢレイアウト664
*/

package webbroker3.common.define;

/**
 * 第二水準追証発生時の新規建て余力停止定数定義インタフェイス<BR>
 * (部店用プリファ@レンステーブルのプリファ@レンスの値の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3SecondDepositMarginOpenTpStopDef
{
    /**
     * 0：余力停止を実施しない
     */
    public final static String DEFAULT = "0";

    /**
     * 1：余力停止を実施する
     */
    public final static String EXECUTE = "1";
}@
