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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �a�莑�Y��ʕ\�����X�|���X(WEB3TPAssetResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�a�莑�Y��ʕ\�����X�|���X)<BR>
 * �a�莑�Y��ʕ\�����X�|���X�N���X�B<BR>
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
    * �]�͌v�Z����ID
    */
   public String calcResultId;
      
   /**
    * ���t
    */
   public Date bizDate;
   
   /**
    * �a�莑�Y���׈ꗗ
    */
   public WEB3TPAssetUnit[] assetUnits;
   
   
   /**
    * (�R���X�g���N�^)
    * @@param l_request
    * @@roseuid 41B690630241
    */
   protected WEB3TPAssetResponse(WEB3GenRequest l_request) 
   {
       super( l_request );
   }
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B54A5E03A1
    */
   public WEB3TPAssetResponse() 
   {
   }
   
}
@
