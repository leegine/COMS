head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �a�莑�Y��ʕ\�����N�G�X�g�N���X(WEB3TPAssetRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�a�莑�Y��ʕ\�����N�G�X�g)<BR>
 * �a�莑�Y��ʕ\�����N�G�X�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPAssetRequest extends WEB3GenRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "tradingpower_asset";
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B5469E0334
    */
   public WEB3TPAssetRequest() 
   {
    
   }
   
   /**
    * (create���X�|���X)
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B5469E0353
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPAssetResponse(this);
   }
   
}
@
