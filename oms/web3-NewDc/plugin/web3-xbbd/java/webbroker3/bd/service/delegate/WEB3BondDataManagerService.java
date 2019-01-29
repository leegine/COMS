head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDataManagerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券データマネージャーサービス(WEB3BondDataManagerService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 齊珂(中訊) 新規作成         
*/

package webbroker3.bd.service.delegate;

import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.bd.data.CustodianRow;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

/**
 * (債券データマネージャーサービス)<BR>
 * 債券データマネージャーサービスインターフェース
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public interface WEB3BondDataManagerService extends Service
{
    
    /**
     * (getカストディアン一覧)<BR>
     * カストディアン一覧を返す。
     * @@param l_institution - 証券会社
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44C0ADFE0132
     */
    public List getCustodianList(Institution l_institution) throws WEB3BaseException;
    
    /**
     * (getカストディアン)<BR>
     * カストディアンRowを取得する。
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト
     * @@param l_strCustodianCode - (カストディアンコード)<BR>
     * カストディアンコード
     * @@return CustodianRow
     * @@throws WEB3BaseException
     * @@roseuid 44C7620403C0
     */
    public CustodianRow getCustodian(Institution l_institution, String l_strCustodianCode) 
        throws WEB3BaseException;
}
@
