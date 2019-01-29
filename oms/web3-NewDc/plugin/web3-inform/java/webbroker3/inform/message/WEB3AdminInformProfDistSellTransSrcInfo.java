head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金・売却代金振込先一覧サービス実装クラス(WEB3AdminInformProfDistSellTransSrcListServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/05 謝旋(中訊) 新規作成 モデルNo.054
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (各種金融機@関情報)<BR>
 * 各種金融機@関情報
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcInfo extends Message
{

    /**
     * (金融機@関名)<BR>
     * 金融機@関名
     */
    public String financialInstitutionName;

    /**
     * (金融機@関名（カナ）)<BR>
     * 金融機@関名（カナ）
     */
    public String financialInstitutionNameKana;

    /**
     * (支店名)<BR>
     * 支店名
     */
    public String financialBranchName;

    /**
     * (支店名（カナ）)<BR>
     * 支店名（カナ）
     */
    public String financialBranchNameKana;

    /**
     * (各種金融機@関情報)<BR>
     * コンストラクタ
     * @@roseuid 46496A8F003D
     */
    public WEB3AdminInformProfDistSellTransSrcInfo()
    {

    }
}
@
