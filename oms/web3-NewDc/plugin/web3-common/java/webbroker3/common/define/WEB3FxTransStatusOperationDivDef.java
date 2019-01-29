head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FxTransStatusOperationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3GftTransStatusOperationDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 屈陽 (中訊) 新規作成
Revesion History : 2006/05/15 凌建平 (中訊) ＤＢレイアウト(GFT振替状況テーブル)による
Revesion History : 2006/07/12 栄イ (中訊)【入出金】仕様変更・ＤＢレイアウトNo.098
*/

package webbroker3.common.define;

/**
 * 為替保証金振替処理区分　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */
public interface WEB3FxTransStatusOperationDivDef
{
    /**
     * 01：証券口座から為替保証金へ(FX出金)
     */
    public static final String TO_FX = "01";
    
    /**
     * 02：為替保証金から証券口座へ(FX出金)
     */
    public static final String FROM_FX = "02";

    /**
     * 03：証券口座から株先証拠金へ(先OP出金)
     */
    public static final String TO_FUOP = "03";

    /**
     * 04：株先証拠金から証券口座へ(先OP入金)
     */
    public static final String FROM_FUOP = "04";
}
@
