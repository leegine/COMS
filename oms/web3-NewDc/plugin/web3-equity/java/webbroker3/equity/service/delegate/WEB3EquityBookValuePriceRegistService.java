head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBookValuePriceRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������뉿�P���o�^�T�[�r�X�C���^�t�F�C�X(WEB3EquityBookValuePriceRegistService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �i���������뉿�P���o�^�T�[�r�X�C���^�t�F�C�X�j�B<BR>
 * <BR>
 * ���������뉿�P���o�^�T�[�r�X�C���^�t�F�C�X<BR>
 */
public interface WEB3EquityBookValuePriceRegistService extends WEB3BusinessService 
{
   
   /**
    * ���������뉿�P���o�^�������s���B<BR>
    * @@param l_request - ���N�G�X�g<BR>
    * @@return WEB3GenResponse<BR>
    * @@throws WEB3BaseException<BR>
    * @@roseuid 41B9251F0378
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
