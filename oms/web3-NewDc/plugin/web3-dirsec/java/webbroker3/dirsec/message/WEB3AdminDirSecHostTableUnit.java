head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キューテーブル一覧(WEB3AdminDirSecHostTableUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 奚翔(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (キューテーブル一覧)<BR>
 * キューテーブル一覧クラス。<BR>
 * 
 * @@author 奚翔
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableUnit extends Message 
{
    
    /**
     * (テーブル名)<BR>
     * テーブル名（和名）。
     */
    public String tableJpnName;
    
    /**
     * (テーブル物理名)<BR>
     * テーブル物理名。
     */
    public String tableName;
    
    /**
     * @@roseuid 442A1C8802AF
     */
    public WEB3AdminDirSecHostTableUnit() 
    {
     
    }
}
@
