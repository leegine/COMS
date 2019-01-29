head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 預り資産画面表示レスポンス(WEB3TPAssetResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (預り資産画面表示レスポンス)<BR>
 * 預り資産画面表示レスポンスクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPAssetResponse extends WEB3GenResponse 
{

   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_asset";
   

   /**
    * 余力計算結果ID
    */
   public String calcResultId;
      
   /**
    * 日付
    */
   public Date bizDate;
   
   /**
    * 預り資産明細一覧
    */
   public WEB3TPAssetUnit[] assetUnits;
   
   
   /**
    * (コンストラクタ)
    * @@param l_request
    * @@roseuid 41B690630241
    */
   protected WEB3TPAssetResponse(WEB3GenRequest l_request) 
   {
       super( l_request );
   }
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B54A5E03A1
    */
   public WEB3TPAssetResponse() 
   {
   }
   
}
@
