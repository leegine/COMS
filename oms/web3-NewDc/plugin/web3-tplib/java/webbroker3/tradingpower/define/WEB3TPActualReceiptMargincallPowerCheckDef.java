head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPActualReceiptMargincallPowerCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「信用現引余力の追証余力チェック方法@-XXX時」の定数定義インターフェース
                    (WEB3TPActualreceiptMargincallpowerCheckDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/01 李木子(中訊) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * (「信用現引余力の追証余力チェック方法@-XXX時」の定数定義インターフェース)
 * <BR>
 * @@author 李木子(中訊)
 * @@version 1.0
 */
public interface WEB3TPActualReceiptMargincallPowerCheckDef {

    /**
     * 追証余力チェックを実施しない。<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * 追証余力チェックを実施する。（追証余力が0より小さい場合信用現引余力は0となる）<BR>
     */
    public final static String EXECUTE = "1";

}
@
