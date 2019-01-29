head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FeqNettingDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株ネッティング会社区分定数定義インタフェイス(WEB3FeqNettingDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2010/01/02 趙林鵬(中訊) 新規作成 ＤＢレイアウト702
*/

package webbroker3.common.define;

/**
 * 外株ネッティング会社区分定数定義インタフェイス<BR>
 * (証券会社プリファ@レンステーブルのプリファ@レンスの値の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3FeqNettingDivDef
{
    /**
     * 0：外株ネッティング採用なし
     */
    public final static String DEFAULT = "0";

    /**
     * 1：外株ネッティング採用
     */
    public final static String EXECUTE = "1";
}@
