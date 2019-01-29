head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AssetEvaluationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 預り証券評価制区分 定数定義インタフェイス(WEB3AssetEvaluationDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/21 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 預り証券評価制区分 定数定義インタフェイス<BR>
 *<BR>
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3AssetEvaluationDivDef
{
    /**
     * 0:未実施
     */
    public final static String NOT_ENFORCEMENT  = "0";

    /**
     * 1:実施
     */
    public final static String ENFORCEMENT  = "1";
}
@
