head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoBuyOrderInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������t�������̓T�[�r�X�C���^�t�F�[�X(WEB3RuitoBuyOrderInputService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/07 �m�X (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * �ݐϓ������t�������̓T�[�r�X�C���^�t�F�[�X<BR>
 */
public interface WEB3RuitoBuyOrderInputService extends WEB3BusinessService 
{
   
   /**
    * �ݐϓ������t�������̓T�[�r�X���������{����B<BR>
    * @@param l_request - ���N�G�X�g�f�[�^
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 4069320902DE
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
