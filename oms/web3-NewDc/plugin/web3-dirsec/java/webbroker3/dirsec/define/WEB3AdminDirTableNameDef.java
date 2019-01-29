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
filename	WEB3AdminDirTableNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : テーブル名定義インタフェイス(WEB3AdminDirTableNameDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/21 柴双紅(中訊) 新規作成 モデルNo.116
*/

package webbroker3.dirsec.define;

/**
 * テーブル名
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3AdminDirTableNameDef
{
    /**
     * submit_trigger_info
     */
    public static final String SUBMIT_TRIGGER_INFO = "submit_trigger_info";

    /**
     * MQ_MESSAGE_ID_MAPPINGS
     */
    public static final String MQ_MESSAGE_ID_MAPPINGS = "MQ_MESSAGE_ID_MAPPINGS";

    /**
     * trading_time
     */
    public static final String TRADING_TIME = "trading_time";
}@
