head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 下り処理情報(WEB3AdminDirSecAPMngForcedStartInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/21 楊夫志(中訊) 新規作成 モデル 132
Revision History : 2008/07/30 劉剣(中訊) モデル 136
*/
package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (下り処理情報)<BR>
 * 下り処理情報クラス。<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartInfoUnit extends Message
{

    /**
     * (伝票コード)<BR>
     * 伝票コード。<BR>
     */
    public String requestCode;

    /**
     * PTYPE。<BR>
     */
    public String pType;

    /**
     * AP下り処理名。<BR>
     */
    public String apName;

    /**
     * (識別コード有無区分)<BR>
     * 識別コード有無区分。<BR>
     * <BR>
     * 0：無し<BR>
     * 1：有り<BR>
     */
    public String requestNumberDiv;

    /**
     * (スレッド番号有無区分)<BR>
     * スレッド番号有無区分。<BR>
     * <BR>
     * 0：無し<BR>
     * 1：有り<BR>
     */
    public String threadNumberDiv;

    /**
     * (下り処理情報)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 4875CFFF0390
     */
    public WEB3AdminDirSecAPMngForcedStartInfoUnit()
    {

    }
}
@
