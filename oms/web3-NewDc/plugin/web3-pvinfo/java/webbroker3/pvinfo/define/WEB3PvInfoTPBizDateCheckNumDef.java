head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoTPBizDateCheckNumDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  余力部分の指定発注日の最大値定義(WEB3PvInfoTPBizDateCheckNumDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/09 王暁傑(中訊) 新規作成
*/
package webbroker3.pvinfo.define;

/**
 * 余力部分の指定発注日の最大値定義
 *
 * @@author 王暁傑
 * @@version 1.00
 */
public interface WEB3PvInfoTPBizDateCheckNumDef
{
    /**
     * 指定発注日の最大値
     */
    public final static int MAX_DAYS = 5;     
}

@
