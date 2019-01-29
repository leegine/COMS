head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 状況区分 定数定義インタフェイス(WEB3AccInfoStateDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * 状況区分 定数定義インタフェイス
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3AccInfoStateDivDef
{
    /**
     * 0：DEFAULT（禁止中でない）　@　@
     */
    public final static String DEFAULT = "0";
    
    /**
     * 1：禁止中
     */
    public final static String PROHIBITING = "1"; 
}
@
