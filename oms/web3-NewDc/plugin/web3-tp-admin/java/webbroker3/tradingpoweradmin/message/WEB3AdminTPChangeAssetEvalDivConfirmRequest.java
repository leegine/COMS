head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPChangeAssetEvalDivConfirmRequest�N���X(WEB3AdminTPChangeAssetEvalDivConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �]�͌v�Z���@@�ύX�m�F���N�G�X�g�N���X
 */
public class WEB3AdminTPChangeAssetEvalDivConfirmRequest extends WEB3AdminTPChangeAssetEvalDivCommonRequest
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_assetevaldiv_confirm";

   /**
    * @@roseuid 41DBC21A0016
    */
   public WEB3AdminTPChangeAssetEvalDivConfirmRequest()
   {

   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC20300D2
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPChangeAssetEvalDivConfirmResponse();
   }

}
@
