head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�T�[�r�X(WEB3EquityOrderNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 �R�w�� (���u) �V�K�쐬
                   2004/12/15 �����a��(SAR) �c�Č��Ή� �m��.�O�V�T
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * �i���������ʒm�T�[�r�X�j�B<br>
 * <br>
 * ���������ʒm�T�[�r�X�C���^�[�t�F�[�X<br>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@version 1.0
 */
public interface WEB3EquityOrderNotifyService extends WEB3BackBusinessService 
{
   
   /**
    * �iexecute�j�B<br>
    * <br>
    * �������������ʒm�T�[�r�X���������{����<br>
    * @@param l_request - ���N�G�X�g
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 40A16D45035E
    */
   public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
