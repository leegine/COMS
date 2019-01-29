head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MrfAllowDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : MRF口座許可区分定数定義インタフェイス(WEB3MrfAllowDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/16 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * MRF口座許可区分定数定義インタフェイス<BR>
 * (会社別FXシステム条件テーブルのMRF口座許可区分の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3MrfAllowDivDef
{
    /**
     * ０：不可
     */
    public static final String DISABLED = "0";

    /**
     * １：許可
     */
    public static final String ENABLED = "1";
}@
