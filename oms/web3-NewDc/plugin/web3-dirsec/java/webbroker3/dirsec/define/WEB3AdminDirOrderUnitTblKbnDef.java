head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirOrderUnitTblKbnDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文単位テーブル区分定義インタフェイス(WEB3AdminDirOrderUnitTblKbnDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/08 周捷(中訊) 新規作成
Revesion History : 2007/10/30 武波(中訊) 仕様変更・モデル114
Revesion History : 2008/07/15 劉剣(中訊) モデルNo.130
*/
package webbroker3.dirsec.define;

/**
 * (注文単位テーブル区分)<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminDirOrderUnitTblKbnDef
{
    /**
     * 0：　@外株
     */
    public static final String FEQ = "0";

    /**
     * 1：　@入出金
     */
    public static final String AIO = "1";


    /**
     * 2：　@投信
     */
    public static final String MUTUAL = "2";

    /**
     * 3：　@株式
     */
    public static final String EQ = "3";

    /**
     * 4：　@先物OP
     */
    public static final String IFO = "4";
}
@
