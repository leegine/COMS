head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DataClassDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : デ－タ種別(WEB3DataClassDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
Revesion History : 2007/01/23 栄イ (中訊) ＤＢレイアウト(取残・電子交付・特定口座伝票（GI311）テーブル)による
*/

package webbroker3.common.define;

/**
 * デ－タ種別 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3DataClassDef
{

    /**
     * 01：データレコード
     */
    public final static String DATA_RECORD  = "01";

    /**
     * 02：変更レコード
     */
    public final static String CHANGE_RECORD  = "02";
}@
