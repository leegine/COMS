head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3YellowAccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Ｙ客区分 定数定義インタフェイス(WEB3YellowAccountDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 李海波 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * Ｙ客区分 定数定義インタフェイス
 * 
 * @@author 李海波(中訊)
 * @@version 1.0
 */
public interface WEB3YellowAccountDivDef
{
    /**
     *  0：　@DEFAULT（Ｙ客でない）<BR>
     * 　@　@
     */
    public final static String DEFAULT = "0";
    
    /**
     * 1：　@Ｙ客　@　@
     */
    public final static String YELLOW_ACCOUNT = "1";
 
}
@
