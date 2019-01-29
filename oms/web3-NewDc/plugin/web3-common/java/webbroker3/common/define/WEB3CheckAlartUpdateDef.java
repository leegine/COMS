head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CheckAlartUpdateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 部店用プリファ@レンステーブル・口座開設審査 定数定義インタフェイス(WEB3CheckAlartUpdateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/12 凌建平(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 口座開設審査 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3CheckAlartUpdateDef
{

    /**
     * 0：チェック不要-アラート表示無-審査待ちテーブルUPDATE無
     */
    public final static String DEFAULT = "0";

    /**
     * 1：チェック実行-アラート表示有-審査待ちテーブルUPDATE無
     */
    public final static String CHECK_ALART_UPD_1 = "1";

    /**
     * 2：チェック実行-アラート表示無-審査待ちテーブルUPDATE有
     */
    public final static String CHECK_ALART_UPD_2 = "2";

    /**
     * 3：チェック実行-アラート表示有-審査待ちテーブルUPDATE有
     */
    public final static String CHECK_ALART_UPD_3 = "3";
}@
