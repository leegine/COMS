head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocReadingDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 目論見書閲覧区分定数定義インタフェイス(WEB3DocReadingDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/22 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * IPO銘柄テーブルの目論見書閲覧区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3DocReadingDivDef
{
    /**
     * 0：DEFAULT（閲覧要）
     */
    public static final String DEFAULT = "0";

    /**
     * 1：需要申告時閲覧不要
     */
    public static final String NO_READ = "1";
}
@
