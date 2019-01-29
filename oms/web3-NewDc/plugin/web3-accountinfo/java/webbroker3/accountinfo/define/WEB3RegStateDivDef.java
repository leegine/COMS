head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3RegStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 登録状況区分 定数定義インタフェイス(WEB3RegStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 李海波 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 登録状況区分 定数定義インタフェイス
 * 
 * @@author 李海波(中訊)
 * @@version 1.0
 */
public interface WEB3RegStateDivDef
{
    /**
     * 0：　@チェックしない　@　@
     */
    public final static String CHECK = "0";
    
    /**
     * 1：　@警告のみ　@　@
     */
    public final static String WARNING = "1";
    
    /**
     * 2：　@注文停止　@　@
     */
    public final static String STOP_ORDER = "2";
 
}
@
