head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PayinfoRgstStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 有料情報登録状態定義クラス(WEB3PayinfoRgstStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 有料情報登録状態定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3PayinfoRgstStatusDef
{
    /**
     * 未登録
     */
    public static final String UN_REGISTERING = "0";
    
    /**
     * 登録済
     */
    public static final String REGISTERED = "1";
    
    /**
     * 無料
     */
    public static final String NO_CHARGE = "2";
    
    /**
     * 試用
     */
    public static final String TRIAL = "3";
}@
