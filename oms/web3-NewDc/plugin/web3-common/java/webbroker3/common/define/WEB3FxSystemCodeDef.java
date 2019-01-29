head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FxSystemCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3FxSystemCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 屈陽 (中訊) 新規作成
Revesion History : 2006/07/13 栄イ 【入出金】仕様変更管理台帳・ＤＢレイアウト097を対応
*/

package webbroker3.common.define;

/**
 * FXシステムコード区分　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */
public interface WEB3FxSystemCodeDef
{
    /**
     * 01：GFT
     */
    public static final String GFT = "01";

    /**
     * 05：HitsFX振替
     */
    public static final String HITS_FX_TRANSFER = "05";

    /**
     * 06：Hits先OP振替
     */
    public static final String HITS_FUOP_TRANSFER = "06";
}
@
