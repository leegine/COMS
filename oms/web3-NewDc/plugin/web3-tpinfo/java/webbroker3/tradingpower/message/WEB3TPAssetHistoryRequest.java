head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Y�]���z�������N�G�X�g(WEB3TPAssetHistoryRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25  䈋�(���u) �V�K�쐬
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (���Y�]���z�������N�G�X�g)<BR>
 * ���Y�]���z�������N�G�X�g�N���X �B<BR>
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3TPAssetHistoryRequest extends WEB3GenRequest 
{
    
   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_asset_history";
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B5544C02A7
    */
   public WEB3TPAssetHistoryRequest() 
   {
   }
 
   /**
    * (create���X�|���X)
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B5469E0353
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPAssetHistoryResponse(this);
   }
}
@
