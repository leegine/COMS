head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FinDelDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 登録区分定数定義(WEB3FinDelDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/09 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 外貨振込先金融機@関テーブルの登録区分　@定数定義インタフェイス                             
 * @@author 栄イ
 * @@version 1.0
 */
public interface WEB3FinDelDivDef
{
    /**
     * 0：抹消以外
     */
    public static final String EXCEPT_OBLITERATION = "0";

    /**
     * 1：抹消
     */
    public static final String OBLITERATION = "1";
}
@
