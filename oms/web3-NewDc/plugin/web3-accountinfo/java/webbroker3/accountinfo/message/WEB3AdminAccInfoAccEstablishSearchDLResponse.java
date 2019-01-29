head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchDLResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽﾄ(WEB3AdminAccInfoAccEstablishSearchDLResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09 何文敏(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (クラス 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽﾄ)<BR>
 * クラス 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽﾄ<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0 
 */

public class WEB3AdminAccInfoAccEstablishSearchDLResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_dl";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082152L;

    /**
     * @@roseuid 418F39F700AB
     */
    public WEB3AdminAccInfoAccEstablishSearchDLResponse()
    {

    }
    
    /**
     * (ダウンロードファ@イル)<BR>
     * ダウンロードファ@イル<BR> 
     * <BR>
     * ※ CSVファ@イル行の配列<BR> 
     */
    public String[] downloadFile;
    
    /**
     * (現在日時)<BR>
     * 現在日時<BR> 
     */
    public Date currentDate;
    
    /**
     * (データ内容番号)<BR>
     * データ内容番号<BR> 
     * <BR>
     * 00：　@データ内容未選択<BR> 
     * 01：　@新規口座開設案内用データ<BR> 
     * 02：　@振込みカード用データ<BR> 
     * 03：　@口座移管案内用データ<BR>
     */
    public String dataContentNumber;
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccInfoAccEstablishSearchDLResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
