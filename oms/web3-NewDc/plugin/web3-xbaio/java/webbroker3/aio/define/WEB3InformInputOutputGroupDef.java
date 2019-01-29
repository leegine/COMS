head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3InformInputOutputGroupDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出入グループ区分インタフェイス(WEB3InformInputOutputGroupDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/27 凌建平(中訊) 作成
*/

package webbroker3.aio.define;

/**
 * 出入グループ区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3InformInputOutputGroupDef
{
       
    /**
     * Z:入出庫
     */
    public static final String IN_OUT_BASE = "Z";
    
    /**
     * A:出庫
     */
    public static final String INPUT_BASE = "A";
    
    /**
     * B:入庫
     */
    public static final String OUTPUT_BASE = "B";
}
@
