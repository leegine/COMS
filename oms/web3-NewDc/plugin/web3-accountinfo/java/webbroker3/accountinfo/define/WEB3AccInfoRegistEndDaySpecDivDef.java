head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoRegistEndDaySpecDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 変更申込締切指定日 定数定義インタフェイス(WEB3AccInfoRegistEndDaySpecDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/04 黄建 (中訊) 新規作成
Revesion History : 2008/08/19 趙林鵬 (中訊)ＤＢレイアウトNo.027
*/

package webbroker3.accountinfo.define;

/**
 * 変更申込締切指定日 定数定義インタフェイス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface WEB3AccInfoRegistEndDaySpecDivDef
{
    /**
     * 00：毎日
     */
    public static final String EVERYDAY = "00";

    /**
     * 01:毎週
     */
    public static final String EVERYWEEK = "01";
}
@
