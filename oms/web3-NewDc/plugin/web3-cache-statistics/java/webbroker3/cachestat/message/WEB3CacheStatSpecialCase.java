head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatSpecialCase.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatSpecialConditionクラス(WEB3CacheStatSpecialCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/22 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * ヒット率情報の検索時に指定された特別な内容
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatSpecialCase extends Message
{
    
    /**
     * 項目名
     */
    public String name;
    
    /**
     * 値
     */
    public String value;

}
@
