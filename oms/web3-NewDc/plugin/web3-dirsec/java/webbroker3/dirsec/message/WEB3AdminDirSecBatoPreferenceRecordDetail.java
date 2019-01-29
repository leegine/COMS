head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecBatoPreferenceRecordDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 電子鳩システム会社部店別プリファ@レンステーブルレコード詳細(WEB3AdminDirSecBatoPreferenceRecordDetail.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/28 柴双紅(中訊) 新規作成 モデルNo.117
*/

package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (電子鳩システム会社部店別プリファ@レンステーブルレコード詳細)<BR>
 * 電子鳩システム会社部店別プリファ@レンステーブルレコード詳細<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminDirSecBatoPreferenceRecordDetail extends Message
{

    /**
     * (部店コード)<BR>
     * 部店コード。<BR>
     */
    public String branchCode;

    /**
     * (システム障害フラグ)<BR>
     * システム障害フラグ。<BR>
     */
    public String systemTroubleDiv;

    /**
     * @@roseuid 481155FD0288
     */
    public WEB3AdminDirSecBatoPreferenceRecordDetail()
    {

    }
}
@
