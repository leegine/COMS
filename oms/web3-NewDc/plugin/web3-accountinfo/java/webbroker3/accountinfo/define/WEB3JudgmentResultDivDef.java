head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3JudgmentResultDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 判定結果区分定数定義インタフェイス(WEB3JudgmentResultDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  カク寛新 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 判定結果区分 定数定義インタフェイス
 *
 * @@author カク寛新
 * @@version 1.0
 */
public interface WEB3JudgmentResultDivDef
{

    /**
     *0：　@DEFAULT　@　@
     */
    public final static String DEFAULT = "0";
    
    /**
     *1：　@承認　@　@
     */
    public final static String CONSENT = "1";

    /**
     * 2：　@不可　@ 　@　@　@ 　@　@
     */
    public final static String IMPOSSIBILITY = "2";
}@
