head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TodayClearanceDeterminationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 当日消し込み判定区分定数定義インタフェイス(WEB3TodayClearanceDeterminationDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/10/21 陸文静(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 当日消し込み判定区分定数定義インタフェイス<BR>
 * (部店用プリファ@レンステーブルのプリファ@レンスの値の參照)<BR>
 * <BR>
 * @@author 陸文静<BR>
 * @@version 1.0<BR>
 */
public interface WEB3TodayClearanceDeterminationDivDef
{
    /**
     * 1：追証解消金額の計算に当日返済入金相当額を追加する
     */
    public final static String EXECUTE = "1";
}
@
