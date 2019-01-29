head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoInsiderInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 内部者情報一覧Unit(WEB3AccInfoInsiderInfoListUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 李海波 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;


/**
 * (内部者情報一覧Unit)<BR>
 * 内部者情報一覧Unitメッセージ<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoInsiderInfoUnit extends WEB3AccInfoInsiderInfo
{
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (顧客名（漢字）)<BR>
     * 顧客名（漢字）<BR>
     */
    public String accountName;
    
    /**
     * 
     * (内部者情報一覧Unit)<BR>
     *  デフォルトコンストラクタ<BR>
     */
    public WEB3AccInfoInsiderInfoUnit()
    {
        
    }
}
@
