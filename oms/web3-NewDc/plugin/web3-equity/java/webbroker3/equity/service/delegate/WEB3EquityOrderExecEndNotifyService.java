head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecEndNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���I���ʒm�T�[�r�X�C���^�t�F�[�X(WEB3EquityOrderExecEndNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.common.WEB3BaseException;

/**
 * �i�o���I���ʒm�T�[�r�X�C���^�t�F�[�X�j�B
 * @@version 1.0
 */
public interface WEB3EquityOrderExecEndNotifyService extends WEB3BackBusinessService 
{
   
   /**
    * �o���I���ʒm�T�[�r�X���������{����<BR>
    * @@param l_request - ���N�G�X�g<BR>
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 4056EB8E03D5
    */
   public WEB3BackResponse execute(WEB3BackRequest l_request)
       throws WEB3BaseException;
}
@
