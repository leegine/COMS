head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3QuestionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 質問区分  定数定義インタフェイス(WEB3QuestionDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20　@周勇 (中訊) 新規作成
Revesion History : 2008/02/21　@栄イ(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo609
*/
package webbroker3.common.define;

/**
 * 質問テーブルの質問区分　@定数定義インタフェイス
 *
 * @@author 周勇 (中訊)
 * @@version 1.0
 */
public interface WEB3QuestionDivDef
{
    /**
     * 0001：為替保証金　@
     */
    public static final String  FX = "0001";
    
    /**
     * 0002：外国株式振替
     */
    public static final String  FEQ_TRANS = "0002";

    /**
     * 0003：PTS
     */
    public static final String  PTS = "0003";
}
@
