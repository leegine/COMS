head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExtConnectSystemCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外部接続システムコード定数定義インタフェイス(WEB3ExtConnectSystemCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/25 陸文静 (中訊) 新規作成
Revision History : 2009/06/26 趙林鵬 (中訊)【入出金】仕様変更管理台帳モデルNo.1172
*/
package webbroker3.common.define;

/**
 * 外部接続システムコード定数定義インタフェイス<BR>
 * (会社別FXシステム条件テーブルの外部接続システムコードの參照)<BR>
 * <BR>
 * @@author 陸文静 (中訊)
 * @@version 1.0
 */
public interface WEB3ExtConnectSystemCodeDef
{
    /**
     * 01：GFT
     */
    public static final String GFT = "01";

    /**
     * 02：TFX
     */
    public static final String TFX = "02";

    /**
     * 03：Hits
     */
    public static final String HITS = "03";

    /**
     * 04：FXP
     */
    public static final String FXP = "04";

    /**
     * 05:シンプレクス
     */
    public static final String SIMPLEX = "05";
}
@
