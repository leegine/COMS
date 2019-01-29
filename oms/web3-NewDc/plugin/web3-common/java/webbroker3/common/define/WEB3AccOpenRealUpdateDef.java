head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccOpenRealUpdateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座区分リアル更新定義インタフェイス(WEB3AccOpenRealUpdateDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/23 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 口座区分リアル更新定義インタフェイス<BR>
 * (会社別FXシステム条件テーブルの口座区分リアル更新の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3AccOpenRealUpdateDef
{
    /**
     * ０：リアル更新しない
     */
    public final static String NOT_REAL_UPDATE = "0";

    /**
     * １：リアル更新
     */
    public final static String REAL_UPDATE = "1";
}
@
