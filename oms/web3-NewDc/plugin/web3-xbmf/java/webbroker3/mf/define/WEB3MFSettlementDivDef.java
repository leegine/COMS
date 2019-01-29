head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFSettlementDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨MMF注文キューテーブルの決済区分インタフェイス(WEB3MFSettlementDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 張騰宇(中訊) 新規作成（モデル533）
*/
package webbroker3.mf.define;

/**
 * 外貨MMF注文キューテーブルの決済区分インタフェイス
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3MFSettlementDivDef 
{
    /**
     * 0:円貨決済
     */
    public static final String EN_SETTLE = "0";

    /**
     * 1:外貨決済
     */
    public static final String FOREIGN_SETTLE = "1";
}
@
