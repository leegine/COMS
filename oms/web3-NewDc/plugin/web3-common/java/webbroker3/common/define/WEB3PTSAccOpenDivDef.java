head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PTSAccOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS口座開設区分 定数定義インタフェイス(WEB3PTSAccOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/19 孟暁シン(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * PTS口座開設区分 定数定義インタフェイス
 *
 * @@author 孟暁シン(中訊) 
 * @@version 1.0
 */
public interface WEB3PTSAccOpenDivDef
{
    /**
     * 0：DEFAULT（口座なし）
     */
    public static final String DEFAULT = "0";
    
    /**
     * 1：口座開設
     */
    public static final String ACCOUNT_OPEN = "1";
}@
