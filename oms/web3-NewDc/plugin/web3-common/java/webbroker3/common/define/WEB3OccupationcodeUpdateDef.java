head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OccupationcodeUpdateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報職業コード更新定数定義インタフェイス(WEB3OccupationcodeUpdateDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/19 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * お客様情報職業コード更新定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3OccupationcodeUpdateDef
{
    /**
     * 0:職業コードの更新を実行しない。
     */
    public static final String DEFAULT = "0";

    /**
     * 1:職業コードの更新を実行する。
     */
    public static final String EXECUTE = "1";
}
@
