head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReleaseDepositAutoTransferConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋������U�֒�~�����m�F���N�G�X�g�N���X(WEB3AdminTPReleaseDepositAutoTransferConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �ۏ؋������U�֒�~�����m�F���N�G�X�g�N���X
 */
public class WEB3AdminTPReleaseDepositAutoTransferConfirmRequest extends WEB3AdminTPReleaseDepositAutoTransferCommonRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_release_depositautotransfer_confirm";

     /**
      * @@return webbroker3.common.message.WEB3GenResponse
      * @@roseuid 41DBC97803AB
      */
     public WEB3GenResponse createResponse()
     {
         return new WEB3AdminTPReleaseDepositAutoTransferConfirmResponse();
     }


}
@
