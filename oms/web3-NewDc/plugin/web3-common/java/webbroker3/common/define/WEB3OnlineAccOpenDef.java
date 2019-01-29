head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OnlineAccOpenDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン口座開設実施区分定数定義インタフェイス(WEB3OnlineAccOpenDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/18 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * オンライン口座開設実施区分定数定義インタフェイス<BR>
 * (会社別FXシステム条件テーブルのオンライン口座開設実施区分の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3OnlineAccOpenDef
{
    /**
     * ０：オンライン口座開設未実施
     */
    public static final String ONLINE_ACC_OPEN_NOT_ENFORCEMENT = "0";

    /**
     * １：オンライン口座開設実施
     */
    public static final String ONLINE_ACC_OPEN_ENFORCEMENT = "1";
}@
