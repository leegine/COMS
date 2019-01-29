head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPOrderAccProductDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付時間区分インターフェース(WEB3AdminTPOrderAccProductDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/13 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * WEB3AdminTPOrderAccProductDefインターフェース。
 * 受付時間区分のデフォルトを定義する。
 * @@author 堀野 和美(FLJ)
 * @@version 1.0
 *
 */
public interface WEB3AdminTPOrderAccProductDef {

    /**
     * 00: その他
     */
    public static final String DEFAULT = "00";

}
@
