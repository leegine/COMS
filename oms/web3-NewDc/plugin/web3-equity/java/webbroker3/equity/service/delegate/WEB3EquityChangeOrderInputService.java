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
filename	WEB3EquityChangeOrderInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������̓T�[�r�X�C���^�[�t�F�[�X(WEB3EquityChangeOrderService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/12 ����(���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i���������������̓T�[�r�X�C���^�[�t�F�[�X�j�B
 * @@version 1.0
 */
public interface WEB3EquityChangeOrderInputService extends WEB3BusinessService 
{
   
   /**
    * (execute)<BR>
    * <BR>
    * �T�[�r�X�G���g���[�|�C���g<BR>
    * 
    * @@param l_request
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 4042C943028C
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request)
       throws WEB3BaseException;
}
@
