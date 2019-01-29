head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BranchNameSerialNoDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : プリファ@レンス名の連番定数定義インタフェイス(WEB3BranchNameSerialNoDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/13 栄イ(中訊) 新規作成
Revision History : 2008/03/12 栄イ(中訊)【共通】仕様変更・モデルNo.320
*/
package webbroker3.common.define;

/**
 * 部店用プリファ@レンステーブルのプリファ@レンス名の連番定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3BranchNameSerialNoDef
{
    /**
     * 1：株式・信用
     */
    public final static String EQUITY_MARGIN = "1";

    /**
     * 2：先物オプション
     */
    public final static String FUTURE_OPTION = "2";
}
@
