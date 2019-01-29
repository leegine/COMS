head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontOrderCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (フロント発注共通サービス) (WEB3AdminFrontOrderCommonService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.118
*/
package webbroker3.eqtypeadmin.service.delegate;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontMarketNoticeHistoryUnit;

/**
 * (管理者フロント発注共通サービス)<BR>
 * <BR>
 * 管理者フロント発注共通サービスインタフェース<BR>
 * <BR>
 * WEB3AdminFrontOrderCommonService interface<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public interface WEB3AdminFrontOrderCommonService extends Service {   
     /**
      * 市場通知管理テーブルの検索に適した市場コードを取得し、<BR>
      * 返却する。<BR>
      * @@param 証券会社コード - 証券会社コード。<BR>
      * @@return tring[]<BR>
      * @@roseuid 42F7104D0060
      */
    public String[] getFindPossibleMarketCode(String l_strInstitutionCode) throws WEB3BusinessLayerException, WEB3SystemLayerException;
   
     /**
      * フロント発注市場コードから、フロント発注取引所区分コードを取得する。<BR>
      * @@param フロント発注市場コード - フロント発注市場コード。<BR>
      * @@return String<BR>
      * @@roseuid 42F710A40139
      */
    public String getFrontOrderExchangeCode(String l_strConvertMarketCode);
   
     /**
      * フロント発注市場コードから、フロント発注システム区分を取得する。<BR>
      * @@param フロント発注市場コード - フロント発注市場コード。<BR>
      * @@return String<BR>
      * @@roseuid 42F711C40131
      */
    public String getFrontSystemDiv(String l_strConvertMarketCode);
   
     /**
      * 通知履歴参照一覧を作成する。<BR>
      * @@param 市場通知管理一覧 - 市場通知管理一覧List。<BR>
      * @@return 市場通知履歴明細[]<BR>
      * @@roseuid 42F7126200DF
      */
    public WEB3AdminFrontMarketNoticeHistoryUnit[] createNoticeHistryRefList(List l_histList);
   
     /**
      * 引数の市場コード、フロント発注システム区分コードから、画面表示用の<BR>
      * 市場コードに変換し、返却する。<BR>
      * @@param 市場コード - 市場コード。<BR>
      * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
      * @@return String<BR>
      * @@roseuid 42F71618033C
      */
    public String getFrontOrderMarketCode(String l_strMarketCode, String l_strFrontSystemCode);
   
    /**
     * ４営業日前までの営業日一覧を取得し、返却する。<BR>
     * @@return Date[]<BR>
     * @@roseuid 4303F05800A7
     */
    public Date[] getNoticeReceivedDateRef() throws WEB3SystemLayerException;
    
}
@
