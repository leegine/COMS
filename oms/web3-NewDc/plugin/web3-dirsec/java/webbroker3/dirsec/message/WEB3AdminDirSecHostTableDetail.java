head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キューテーブルレコード詳細(WEB3AdminDirSecHostTableDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 奚翔(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (キューテーブルレコード詳細)<BR>
 * キューテーブルレコード詳細クラス。<BR>
 * 
 * @@author 奚翔
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableDetail extends Message
{
    
    /**
     * (会社コード)<BR>
     * 会社コード<BR>
     */
    public String institutionCode;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String identityCode;
    
    /**
     * (更新前ステータス)<BR>
     * 更新前ステータス。<BR>
     */
    public String beforeStatus;
    
    /**
     * (作成日付)<BR>
     * 作成日付。<BR>
     */
    public Date createDate;
    
    /**
     * @@roseuid 442A1C85036B
     */
    public WEB3AdminDirSecHostTableDetail() 
    {
     
    }
}
@
